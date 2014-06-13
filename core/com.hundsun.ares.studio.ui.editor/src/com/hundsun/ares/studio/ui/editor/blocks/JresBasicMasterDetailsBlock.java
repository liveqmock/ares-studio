/**
 * 源程序名称：JresBasicMasterDetailsBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.IEMFFormPage;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;
import com.hundsun.ares.studio.ui.editor.editable.IEditableControl;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;
import com.hundsun.ares.studio.ui.util.ARESEMFClipboard;
import com.hundsun.ares.studio.ui.util.IJRESClipboardListener;

/**
 * 2012-09-17 sundl 增加actions分组列表，用于只读控制监听。
 * @author qinyuan
 * @author sundl
 *
 */
public abstract class JresBasicMasterDetailsBlock  extends MasterDetailsBlock implements CommandStackListener, IPropertyListener, ISelectionChangedListener{
	protected EMFFormPage page;
	protected ColumnViewer viewer;
	protected FormToolkit toolkit;
	
	private IEditableControl editableControl;

	private ActionRegistry actionRegistry;
	private List<String> selectionActions;
	private List<String> stackActions;
	private List<String> propertyActions;
	private List<String> clipboardActions;
	
	private IJRESClipboardListener clipboardListener = new IJRESClipboardListener() {
		@Override
		public void clipboardChanged(ARESEMFClipboard clipboard) {
			updateActions(getClipboardActions());
		}
		
		@Override
		public void clipboardAboutToBeChanged(ARESEMFClipboard clipboard) {
			
		}
	};
	
	public JresBasicMasterDetailsBlock(EMFFormPage page, IEditableControl editableControl) {
		this.page = page;
		this.editableControl = editableControl;
	}
	
	@Override
	public void createContent(IManagedForm managedForm) {
		super.createContent(managedForm);
		
		ARESEMFClipboard.getInstance().addClipboardListener(clipboardListener);
		ScrolledForm form = managedForm.getForm();
		managedForm.getToolkit().decorateFormHeading(form.getForm());
		form.setText(getPageHeadName());
		form.setImage(getPageHeadImage());
		managedForm.getForm().getBody().setLayout(new GridLayout(1,false));
	}
	
	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent) {		
		toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText(StringUtil.getStringSafely(getSectionName()));
		
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		section.setDescription(StringUtils.defaultString(getDescription()));
		
		final SectionPart sPart = new SectionPart(section);
		managedForm.addPart(sPart);
		
		ColumnViewer columnViewer = createViewer(client,managedForm,page);
		setColumnViewer(columnViewer);
		
		viewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(sPart, event.getSelection());
			}
		});

		initializeActionRegistry();
		
		createButtons(client);
		
		createMenu(viewer);
		//布局
		setMasterLayout(client);
		
		section.setClient(client);
	}
	
	/**
	 * 设置内容
	 * @param input
	 */
	public void setInput(Object input) {
		if (viewer != null) {
			viewer.setInput(input);
			
			updateActions(getPropertyActions());
			updateActions(getSelectionActions());
			updateActions(getStackActions());
		}
	}
	
	/**
	 * 设置master界面布局
	 */
	protected void setMasterLayout(Composite client) {
		client.setLayout(new GridLayout());
	}
	
	/**
	 * 创建右键菜单
	 * @param client
	 */
	protected void createMenu(Viewer viewer) {
		MenuManager menuManager = new MenuManager("#popup");
		menuManager.addMenuListener(new IMenuListener() {
			
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				manager.removeAll();
				createMenus(manager);
			}
		});

		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
	}

	/**
	 * 创建右键菜单
	 * @param manager
	 */
	protected void createMenus(IMenuManager manager) {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresBasicMasterDetailsBlock#createButtons(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtons(Composite client) {
		ToolBar toolbar = new ToolBar(client, SWT.VERTICAL);
		GridDataFactory.fillDefaults().grab(false, true).applyTo(toolbar);
		toolkit.adapt(toolbar, false, false);
		ToolBarManager manager = new ToolBarManager(toolbar);
		// 创建表格辅助按钮栏
		createToolbarItems(manager);
		manager.update(true);
	}
	
	protected abstract void createToolbarItems(ToolBarManager manager);
	
	protected void initializeActionRegistry() {
		createActions();
		updateActions(getPropertyActions());
		updateActions(getStackActions());
		updateActions(getSelectionActions());
		updateActions(getClipboardActions());
	}
	
	protected void createActions() {
		
	}
	
	/**
	 * @return the selectionActions
	 */
	protected List<String> getSelectionActions() {
		if (selectionActions == null) {
			selectionActions = new ArrayList<String>();
		}
		return selectionActions;
	}
	
	/**
	 * @return the stackActions
	 */
	protected List<String> getStackActions() {
		if (stackActions == null) {
			stackActions = new ArrayList<String>();
		}
		return stackActions;
	}
	
	/**
	 * @return the propertyActions
	 */
	protected List<String> getPropertyActions() {
		if (propertyActions == null) {
			propertyActions = new ArrayList<String>();
		}
		return propertyActions;
	}
	
	/**
	 * @return the clipboardActions
	 */
	protected List<String> getClipboardActions() {
		if (clipboardActions == null) {
			clipboardActions = new ArrayList<String>();
		}
		return clipboardActions;
	}
	
	/**
	 * @return the actionRegistry
	 */
	public ActionRegistry getActionRegistry() {
		if (actionRegistry == null)
			actionRegistry = new ActionRegistry();
		return actionRegistry;
	}
	
	/**
	 * @param columnViewer the columnViewer to set
	 */
	protected void setColumnViewer(ColumnViewer columnViewer) {
		if (this.viewer != null) {
			this.viewer.removeSelectionChangedListener(this);
		}
		this.viewer = columnViewer;
		if (this.viewer != null) {
			this.viewer.addSelectionChangedListener(this);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		updateActions(getSelectionActions());
	}
	
	protected ColumnViewer getColumnViewer() {
		return viewer;
	}
	
	protected void updateActions(final List<String> actionIds) {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				ActionRegistry registry = getActionRegistry();
				Iterator<String> iter = actionIds.iterator();
				while (iter.hasNext()) {
					IAction action = registry.getAction(iter.next());
					if (action instanceof IUpdateAction)
						((IUpdateAction) action).update();
				}
			}
		});

	}
	
	/**
	 * @return the editingDomain
	 */
	public EditingDomain getEditingDomain() {
		return page.getEditingDomain();
	}
	
	@Override
	protected void registerPages(DetailsPart detailsPart) {
		for(Object type:getElementTypes()){
			IDetailsPage page = getDetailPage(type);
			if(this.page != null && page != null){
				if(page instanceof JresDetailsPage){
					((JresDetailsPage)page).setPage(this.page);
				}
				detailsPart.registerPage(type, page);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.CommandStackListener#commandStackChanged(java.util.EventObject)
	 */
	@Override
	public void commandStackChanged(EventObject event) {
		RefreshViewerJob.refresh((ColumnViewer)viewer, null, false);
		Command cmd = ((CommandStack)event.getSource()).getMostRecentCommand();
		if (cmd != null) {
			RefreshViewerJob.refresh((ColumnViewer) viewer, cmd.getAffectedObjects().toArray());
		}
		updateActions(getStackActions());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPropertyListener#propertyChanged(java.lang.Object, int)
	 */
	@Override
	public void propertyChanged(Object source, int propId) {
		updateActions(getPropertyActions());
	}
	
	/**
	 * @return the editableControl
	 */
	public IEditableControl getEditableControl() {
		return editableControl;
	}
	
	/**
	 * 获取页面的标题图片
	 * @return
	 */
	protected Image getPageHeadImage(){
		return null;
	}

	/**
	 * 获取页面的标题
	 * @return
	 */
	protected abstract String getPageHeadName();
	/**
	 * 获取左边section的标题
	 * @return
	 */
	protected abstract String getSectionName();
	/**
	 * 获取左边section的描述
	 * @return
	 */
	protected abstract String getDescription();
	/**
	 * 获取所有需要关联detial页面的类型
	 * @return
	 */
	protected abstract Object[] getElementTypes();
	/**
	 * 获取某个特定类型的detial页面
	 * @param type
	 * @return
	 */
	protected abstract IDetailsPage getDetailPage(Object type);
	/**
	 * 
	 * @param client
	 * @param managedForm
	 * @param page
	 * @return
	 */
	protected abstract ColumnViewer createViewer(Composite client,final IManagedForm managedForm,IEMFFormPage page);

}
