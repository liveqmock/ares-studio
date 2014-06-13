/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.commands.operations.IOperationApprover;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.ObjectUndoContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.operations.UndoRedoActionGroup;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.IARESResourceEditorInput;
import com.hundsun.ares.studio.ui.control.IEditable;
import com.hundsun.ares.studio.ui.editor.outline.AresAnnotationOutline;
import com.hundsun.ares.studio.ui.editor.outline.AresExtendPointOutline;
import com.hundsun.ares.studio.ui.page.FromPageWithMyDirtySystem;
import com.hundsun.ares.studio.ui.util.EditorDirtyStatus;

/**
 * 基本编辑器;
 * 
 * 2010-07-20 sundl 重新从AbstractHSFormEditor抽象，解决过度依赖IAresResource的问题；
 * [同时更新同步底层文件的实现，使用IFileSync 暂未实现]
 * 
 * @author maxh
 */
public abstract class BasicAresFormEditor<T> extends FormEditor implements
		PropertyChangeListener {

	private static final String ACTIVE_PAGE = "active_page";

	protected class EditorInputListener implements IEditorInputStateListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.hundsun.ares.studio.ui.editor.IEditorInputStateListener#
		 * editorInputDeleted(org.eclipse.ui.IEditorInput)
		 */
		public void editorInputDeleted(IEditorInput input) {
			if (input.equals(getEditorInput())) {
				Display display = getSite().getShell().getDisplay();
				display.asyncExec(new Runnable() {
					public void run() {
						getSite().getPage().closeEditor(
								BasicAresFormEditor.this, false);
					}
				});
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.hundsun.ares.studio.ui.editor.IEditorInputStateListener#
		 * editorInputMoved(org.eclipse.ui.IEditorInput,
		 * org.eclipse.ui.IEditorInput)
		 */
		public void editorInputMoved(final IEditorInput oldInput,
				final IEditorInput newInput) {
			if (oldInput != null && oldInput.equals(getEditorInput())) {
				Display display = getSite().getShell().getDisplay();
				display.asyncExec(new Runnable() {
					public void run() {
						handleEditorInputMoved(oldInput, newInput);
					}
				});
			}
		}

	}

	private IPartListener2 partListener = new IPartListener2() {

		@Override
		public void partActivated(IWorkbenchPartReference partRef) {
			IWorkbenchPart part = partRef.getPart(true);
			if (part == BasicAresFormEditor.this) {
				if (null != approver) {
					getOperationHistory().removeOperationApprover(approver);
				}
				approver = new AresBasicEditorApprover(undoContext);
				getOperationHistory().addOperationApprover(approver);
				if(undoContext!=null){
					getOperationHistory().setLimit(undoContext, undoLimit());
				}
				
			} else {
				if (null != approver) {
					getOperationHistory().removeOperationApprover(approver);
				}
			}
		}

		@Override
		public void partBroughtToTop(IWorkbenchPartReference partRef) {
		}

		@Override
		public void partClosed(IWorkbenchPartReference partRef) {
		}

		@Override
		public void partDeactivated(IWorkbenchPartReference partRef) {
			if (null != approver) {
				getOperationHistory().removeOperationApprover(approver);
			}
		}

		@Override
		public void partOpened(IWorkbenchPartReference partRef) {
		}

		@Override
		public void partHidden(IWorkbenchPartReference partRef) {
		}

		@Override
		public void partVisible(IWorkbenchPartReference partRef) {
		}

		@Override
		public void partInputChanged(IWorkbenchPartReference partRef) {
		}
	};

	protected boolean backUpPageIndex = true;
	protected EditorDirtyStatus dirty = new EditorDirtyStatus();
	protected T info; // info对象，子类负责为这个变量赋值，保存使用。

	private boolean forceReadOnly = false; // 强制只读，慎用

	AresExtendPointOutline outline1;
	AresAnnotationOutline outline2;

	/** 编辑器底层的文件 */
	protected IFile editingFile;

	private IARESElement aresElement;

	/** 项目 */
	protected IARESProject aresProject;

	private boolean useUndoSupport = true;

	/**
	 * 撤销重做上下文
	 */
	protected IUndoContext undoContext;
	/**
	 * 撤销重做匹配器
	 */
	IOperationApprover approver;

	private EditorInputListener editorInputListener = new EditorInputListener();

	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);

		// 初始化撤销重做上下文
		if (useUndoSupport)
			installUndoSupport();

		// 增加脏状态控制
		dirty.addPropertyChangeListener(this);

		EditorInputInfoManager.getInstance().addListener(editorInputListener);
	}

	protected void installUndoSupport() {
		initializeOperationHistory();
		UndoRedoActionGroup undoRedoGroup = new UndoRedoActionGroup(getSite(),
				undoContext, true);
		IActionBars actionBars = getEditorSite().getActionBars();
		undoRedoGroup.fillActionBars(actionBars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.forms.editor.FormEditor#addPage(org.eclipse.ui.IEditorPart
	 * , org.eclipse.ui.IEditorInput)
	 */
	@Override
	public int addPage(IEditorPart editor, IEditorInput input)
			throws PartInitException {
		if (editor instanceof FromPageWithMyDirtySystem) {
			FromPageWithMyDirtySystem fromPageWithMyDirtySystem = (FromPageWithMyDirtySystem) editor;
			addPageContext(fromPageWithMyDirtySystem);
			return super.addPage(editor, input);
		} else {
			return super.addPage(editor, input);
		}
	}

	@Override
	protected void setInput(IEditorInput input) {
		IEditorInput oldInput = getEditorInput();
		if (oldInput != null) {
			EditorInputInfoManager.getInstance().disconnect(oldInput);
		}

		super.setInput(input);

		updateInfo(oldInput, input);
		if (input instanceof IFileEditorInput) {
			EditorInputInfoManager.getInstance().connect(input);
		}

		// 设置编辑器标题
		updatePartName();

		if (input != null && oldInput != null) {
			updateControls();
		}
	}

	/**
	 * setInput的时候调用此方法更新编辑器保持的一些信息，例如文件的handle,project等等;
	 * 这个方法调用的时候，editor的input已经被设置为新的Input
	 */
	protected void updateInfo(IEditorInput oldInput, IEditorInput newInput) {
		IEditorInput input = getEditorInput();
		if (input instanceof IFileEditorInput) {
			this.editingFile = ((IFileEditorInput) input).getFile();
			aresElement = (IARESElement) ARESCore.create(editingFile);
			IProject project = editingFile.getProject();
			this.aresProject = ARESCore.create(project);
		} else if (input instanceof IARESResourceEditorInput) {
			IARESResourceEditorInput aresInput = (IARESResourceEditorInput) input;
			aresElement = aresInput.getARESResource();
			this.aresProject = aresElement.getARESProject();
		}
	}

	/**
	 * 编辑器外文件更改以后刷新界面，父类不能完全保证createPartControl()已经执行过，所以 子类需要在执行之前自行判断
	 */
	protected void updateControls() {
	}

	protected void handleEditorInputMoved(IEditorInput oldInput,
			final IEditorInput newInput) {
		setInput(newInput);
	}

	public void updatePartName() {
		String partName = getPartTitleName();
		if (isReadOnly()) {
			partName = partName + "（只读）";
		}
		setPartName(partName);
		setTitle(partName);
		// firePropertyChange(IWorkbenchPartConstants.PROP_PART_NAME);
	}

	protected String getPartTitleName() {
		if (editingFile != null)
			return editingFile.getName();
		return "";
	}

	protected boolean isUseUndoSupport() {
		return useUndoSupport;
	}

	protected void setUseUndoSupport(boolean useUndoSupport) {
		this.useUndoSupport = useUndoSupport;
	}

	/**
	 * 初始化撤销重做环境
	 */
	protected void initializeOperationHistory() {
		undoContext = new ObjectUndoContext(this);
		if (approver != null) {
			getOperationHistory().removeOperationApprover(approver);
		}
		approver = new AresBasicEditorApprover(undoContext);
		getOperationHistory().addOperationApprover(approver);
		getOperationHistory().setLimit(undoContext, undoLimit());
		UndoRedoActionGroup undoRedoGroup = new UndoRedoActionGroup(getSite(),
				undoContext, true);
		IActionBars actionBars = getEditorSite().getActionBars();
		undoRedoGroup.fillActionBars(actionBars);
	}

	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		updateEditableState();
	}

	/**
	 * 传递页面需要的上下文 传递脏状态 传递资源 传递撤销重做上下文 传递模型 注意不要重复传递变量
	 * 
	 * @param page
	 */
	protected void addPageContext(FromPageWithMyDirtySystem page) {
		page.setDirtyStatus(dirty);

		if (useUndoSupport)
			page.setUndoContext(undoContext);

		page.setInfo(info);
	}

	/**
	 * 更新编辑器的只读状态，此方法必须在控件创建完成之后调用。
	 * 
	 * @param element
	 */
	public void updateEditableState() {
		boolean readonly = isReadOnly();
		if (getActivePageInstance() instanceof IEditable) {
			((IEditable) getActivePageInstance()).setEditable(!readonly);
		} else if (getActiveEditor() instanceof IEditable) {
			((IEditable) getActiveEditor()).setEditable(!readonly);
		}
		updatePartName();
	}

	protected boolean isReadOnly() {
		if (forceReadOnly)
			return true;

		if (editingFile != null)
			return this.editingFile.isReadOnly();

		return false;
	}

	public boolean isForceReadOnly() {
		return forceReadOnly;
	}

	public void setForceReadOnly(boolean forceReadOnly) {
		this.forceReadOnly = forceReadOnly;
	}

	/**
	 * 获得撤销重做的最大步数
	 */
	protected int undoLimit() {
		return 100;
	}

	/**
	 * 获得撤销重做历史
	 */
	public static IOperationHistory getOperationHistory() {
		return PlatformUI.getWorkbench().getOperationSupport()
				.getOperationHistory();
	}

	/**
	 * 获取编辑器内存模型的类
	 */
	protected abstract Class getModelType();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.forms.editor.FormEditor#addPage(org.eclipse.ui.forms.editor
	 * .IFormPage)
	 */
	@Override
	public int addPage(IFormPage page) throws PartInitException {
		if (page instanceof FromPageWithMyDirtySystem) {
			FromPageWithMyDirtySystem fromPageWithMyDirtySystem = (FromPageWithMyDirtySystem) page;
			addPageContext(fromPageWithMyDirtySystem);
			return super.addPage(fromPageWithMyDirtySystem);
		} else {
			return super.addPage(page);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPage(int,
	 * org.eclipse.ui.forms.editor.IFormPage)
	 */
	@Override
	public void addPage(int index, IFormPage page) throws PartInitException {

	}

	@Override
	protected void createPages() {
		super.createPages();
		IDialogSettings mySettings = ARESEditorPlugin.getDefault().getDialogSettings()
				.getSection(getSite().getId());
		if (mySettings == null) {
			return;
		}
		if (mySettings.get(ACTIVE_PAGE) != null) {
			int index = mySettings.getInt(ACTIVE_PAGE);
			if (index > 0 && backUpPageIndex)
				try {
					setActivePage(index);
				} catch (Exception e) {
					setActivePage(0);
				}
		}
	}

	/**
	 * 移动到下一页，如果已经是最后一页，则移动到第一页
	 */
	public void nextPage() {
		int next = getCurrentPage() + 1;
		if (next >= getPageCount()) {
			next = 0;
		}
		setActivePage(next);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.forms.editor.FormEditor#createPageContainer(org.eclipse
	 * .swt.widgets.Composite) 要把帮助上下文设置进去
	 */
	@Override
	protected Composite createPageContainer(Composite parent) {
		Composite container = super.createPageContainer(parent);
		if (getHelpContextId() != null) {
			PlatformUI.getWorkbench().getHelpSystem()
					.setHelp(container, getHelpContextId());
		}
		return container;
	}

	/**
	 * 帮助上下文的ID 实现类重写该方法设置帮助上下文ID
	 * 
	 * @return
	 */
	protected String getHelpContextId() {
		return null;
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals(EditorDirtyStatus.PROPERTY_VALUE)) {
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
	}

	private void closeEditor(IResource resource) {
		try {
			Display display = getSite().getShell().getDisplay();
			display.asyncExec(new Runnable() {
				public void run() {
					getSite().getPage().closeEditor(BasicAresFormEditor.this,
							false);
				}
			});
		} catch (Exception e) {
		}
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	public void setFocus() {
		super.setFocus();
		// 为解决切换到编辑器，表格不自动获得焦点的问题。
		IFormPage page = getActivePageInstance();
		if (page != null)
			page.setFocus();
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IContentOutlinePage.class) {
			if (useDefaultOutline()) {
				outline2 = new AresAnnotationOutline();
				outline2.setInput(info);
				dirty.addPropertyChangeListener2(outline2);
				return outline2;
			}
		}
		return super.getAdapter(adapter);
	}

	public T getInfo() {
		return info;
	}

	/**
	 * 是否使用默认的大纲视图
	 */
	boolean useDefaultOutline() {
		return true;
	}

	public IARESProject getARESProject() {
		return aresProject;
	}

	public IARESElement getARESElement() {
		if (editingFile != null) {
			return ARESCore.create(editingFile);
		}
		return aresElement;
	}

	protected void setSite(IWorkbenchPartSite site) {
		super.setSite(site);
		getSite().getWorkbenchWindow().getPartService()
				.addPartListener(partListener);
	}

	@Override
	public void dispose() {
		EditorInputInfoManager.getInstance().disconnect(getEditorInput());
		EditorInputInfoManager.getInstance().removeListener(editorInputListener);
		IDialogSettings settings = ARESEditorPlugin.getDefault().getDialogSettings();
		IDialogSettings mySetting = settings.addNewSection(this.getSite().getId());
		mySetting.put(ACTIVE_PAGE, getCurrentPage());

		if (useUndoSupport)
			if (approver != null) {
				getOperationHistory().removeOperationApprover(approver);
			}
		
		approver = null;
		getSite().getWorkbenchWindow().getPartService()
				.removePartListener(partListener);
		if (pages != null) {
			super.dispose();
		}
	}

}
