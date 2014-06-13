/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.commands.operations.IOperationApprover;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.control.IEditable;
import com.hundsun.ares.studio.ui.editor.outline.AresAnnotationOutline;
import com.hundsun.ares.studio.ui.editor.outline.AresExtendPointOutline;
import com.hundsun.ares.studio.ui.page.FromPageWithMyDirtySystem;
import com.hundsun.ares.studio.ui.util.EditorDirtyStatus;

/**
 * 基本编辑器;
 * 
 * 2010-07-20 sundl
 * 重新从AbstractHSFormEditor抽象，解决过度依赖IAresResource的问题；
 * [同时更新同步底层文件的实现，使用IFileSync  暂未实现]
 * 
 * 2010-8-30 9:27:57  吕高
 * 初步确认undocontext引起了其它编辑器不能使用undo redo的情况
 *所以去除
 * 
 * @author lvgao
 */
public abstract class BasicAresFormEditorNoUndoContext<T> extends FormEditor implements IResourceChangeListener, IResourceDeltaVisitor, PropertyChangeListener {
	
	private static final String ACTIVE_PAGE = "active_page";
	protected boolean backUpPageIndex = true;
	protected EditorDirtyStatus dirty = new EditorDirtyStatus();
	protected T info;			// info对象，子类负责为这个变量赋值，保存使用。
	protected FileSynchronizer synchronizer = new FileSynchronizer(this);
	
	AresExtendPointOutline outline1;
	AresAnnotationOutline outline2;
	
	/** 编辑器底层的文件 */
	protected IFile editingFile;
	
	/** 项目 */
	protected IARESProject aresProject;

	/**
	 * 撤销重做匹配器
	 */
	IOperationApprover approver;
	
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		//增加脏状态控制
		dirty.addPropertyChangeListener(this);

		//设置编辑器标题
		updatePartName();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPage(org.eclipse.ui.IEditorPart, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public int addPage(IEditorPart editor, IEditorInput input)
			throws PartInitException {
		if(editor instanceof FromPageWithMyDirtySystem){
			FromPageWithMyDirtySystem fromPageWithMyDirtySystem = (FromPageWithMyDirtySystem) editor;
			addPageContext(fromPageWithMyDirtySystem);
			return super.addPage(editor, input);
		}else{
			return super.addPage(editor, input);
		}
	}
	
	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		if (input instanceof IFileEditorInput) {
			this.editingFile = ((IFileEditorInput)input).getFile();
			IProject project = editingFile.getProject();
			this.aresProject = ARESCore.create(project);
			synchronizeWithFile();
		}
	}

	protected void updatePartName() {
		String partName = getPartTitleName();
		if (isReadOnly()) {
			partName = partName + "（只读）";
		}
		setPartName(partName);
	}
	
	protected String getPartTitleName(){
		if (editingFile != null)
			return editingFile.getName();
		return "";
	}
	
	private void synchronizeWithFile() {
		synchronizer.install();
//		try {
//			ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
//		} catch (Exception e) {
//			// logger.error("获取资源错误", e);
//		}
	}
	

	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		updateEditableState();
	}
	
	/**
	 * 传递页面需要的上下文
	 * 传递脏状态
	 * 传递资源
	 * 传递撤销重做上下文
	 * 传递模型
	 * 注意不要重复传递变量
	 * @param page
	 */
	protected void addPageContext(FromPageWithMyDirtySystem page){
		page.setDirtyStatus(dirty);
		page.setInfo(info);
	}
	
	/**
	 * 更新编辑器的只读状态，此方法必须在控件创建完成之后调用。
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
		return this.editingFile.isReadOnly();
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
		return PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
	}
	
	/**
	 * 获取编辑器内存模型的类
	 */
	protected abstract Class getModelType();

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPage(org.eclipse.ui.forms.editor.IFormPage)
	 */
	@Override
	public int addPage(IFormPage page) throws PartInitException {
		if(page instanceof FromPageWithMyDirtySystem){
			FromPageWithMyDirtySystem fromPageWithMyDirtySystem = (FromPageWithMyDirtySystem) page;
			addPageContext(fromPageWithMyDirtySystem);
			return super.addPage(fromPageWithMyDirtySystem);
		}else{
			return super.addPage(page);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPage(int, org.eclipse.ui.forms.editor.IFormPage)
	 */
	@Override
	public void addPage(int index, IFormPage page) throws PartInitException {

	}
	
	@Override
	protected void createPages() {
		super.createPages();
		IDialogSettings mySettings = ARESEditorPlugin.getDefault().getDialogSettings().getSection(getSite().getId());
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
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#createPageContainer(org.eclipse.swt.widgets.Composite)
	 * 要把帮助上下文设置进去
	 */
	@Override
	protected Composite createPageContainer(Composite parent) {
		Composite container = super.createPageContainer(parent);
		if(getHelpContextId() != null){
			PlatformUI.getWorkbench().getHelpSystem().setHelp(container, getHelpContextId());
		}
		return container;
	}
	
	/**
	 * 帮助上下文的ID 实现类重写该方法设置帮助上下文ID
	 * @return
	 */
	protected String getHelpContextId(){
		return null;
	}	

	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals(EditorDirtyStatus.PROPERTY_VALUE)) {
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
	}
	 
	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 * 项目关闭和资源删除要关闭编辑器
	 */
	public void resourceChanged(final IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i < pages.length; i++) {
						if(getEditorInput() instanceof FileEditorInput){
							if (((FileEditorInput)getEditorInput()).getFile().getProject().equals(event.getResource())) {
								IEditorPart editorPart = pages[i].findEditor(getEditorInput());
								pages[i].closeEditor(editorPart, true);
							}
						}
					}
				}
			});
		}
		if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
			try {
				event.getDelta().accept(this);
			} catch (CoreException e) {
			}
		}
	}

	public boolean visit(IResourceDelta delta) throws CoreException {
		if (!(delta.getKind() == IResourceDelta.REMOVED)) {
			return true;
		}
		IResource resource = delta.getResource();
		closeEditor(resource);
		return false;
	}

	private void closeEditor(IResource resource) {
		try{
			Display display = getSite().getShell().getDisplay();
			display.asyncExec(new Runnable() {
				public void run() {
					getSite().getPage().closeEditor(BasicAresFormEditorNoUndoContext.this, false);
				}
			});
		}catch (Exception e) {
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
			if(useDefaultOutline()){
				outline2 = new AresAnnotationOutline();
				outline2.setInput(info);
				dirty.addPropertyChangeListener2(outline2);
				return outline2;
			}
		}
		return super.getAdapter(adapter);
	}
	
	/**
	 * 是否使用默认的大纲视图
	 */
	boolean useDefaultOutline(){
		return true;
	}
	
	protected IARESProject getARESProject() {
		return aresProject;
	}
	
	protected IARESElement getARESElement(){
		if(editingFile != null){
			return ARESCore.create(editingFile);
		}
		return aresProject;
	}
	
	
	@Override
	public void dispose() {
		synchronizer.uninstall();
		IDialogSettings settings = ARESEditorPlugin.getDefault().getDialogSettings();
		IDialogSettings mySetting = settings.addNewSection(this.getSite().getId());
		mySetting.put(ACTIVE_PAGE, getCurrentPage());
//		getOperationHistory().removeOperationApprover(approver);
		super.dispose();
	}
}

