/**
 * 源程序名称：ColumnViewerListPage.java
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

import org.apache.log4j.Logger;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.actions.ButtonGroupManager;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;

/**
 * 以一个ColumnViewer作为主要编辑器区域的编辑器页<br>
 * 提供了Action注册器，能根据设置刷新继承于{@link IUpdateAction}的Action<BR>
 * Action的刷新分为3类
 * <li>在ColumnViewer的选择切换的时候刷新的 {@link #getSelectionActions()}</li>
 * <li>在命令栈变化的时候刷新的 {@link #getStackActions()}</li>
 * <li>编辑器属性变化的时候刷新的 {@link #getPropertyActions()}</li>
 * 
 * Action的使用需要先在{@link #createActions()}中进行创建和注册<br>
 * 在{@link #createButtons(ButtonGroupManager)}中用Action的引用创建按钮列表<br>
 * 在{@link #createMenus(IMenuManager)}中用Action的引用创建菜单列表
 * @author gongyf
 *
 */ 
public abstract class ColumnViewerListPage extends EMFFormPage implements ISelectionChangedListener {

	private static final Logger logger = Logger.getLogger(ColumnViewerListPage.class);
	
	private ColumnViewer columnViewer;
	
	private ActionRegistry actionRegistry;
	private List<String> selectionActions;
	private List<String> stackActions;
	private List<String> propertyActions;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ColumnViewerListPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/**
	 * @param columnViewer the columnViewer to set
	 */
	protected void setColumnViewer(ColumnViewer columnViewer) {
		if (this.columnViewer != null) {
			this.columnViewer.removeSelectionChangedListener(this);
		}
		this.columnViewer = columnViewer;
		if (this.columnViewer != null) {
			this.columnViewer.addSelectionChangedListener(this);
		}
	}
	
	/**
	 * @return the columnViewer
	 */
	public ColumnViewer getColumnViewer() {
		return columnViewer;
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
	 * @return the actionRegistry
	 */
	protected ActionRegistry getActionRegistry() {
		if (actionRegistry == null)
			actionRegistry = new ActionRegistry();
		return actionRegistry;
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
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		updateActions(getSelectionActions());
	}
	
	protected Control createClient(Composite parent, FormToolkit toolkit) {
		Composite client = toolkit.createComposite(parent);
		Control viewer = createColumnViewer(client, toolkit);
		Control buttons = createViewerButtons(client, toolkit);
		
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(client);
		GridDataFactory.fillDefaults().hint(100, 100).grab(true, true).applyTo(viewer);
		GridDataFactory.fillDefaults().grab(false, true).hint(80, -1).applyTo(buttons);
		
		return client;
	}
	
	/**
	 * 内部调用{@link #setColumnViewer(ColumnViewer)} 和{@link #configureColumnViewer(ColumnViewer)}
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected Control createColumnViewer(Composite parent, FormToolkit toolkit) {
		TableViewer viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		setColumnViewer(viewer);
		
		configureColumnViewer(getColumnViewer());
		
		return viewer.getControl();
	}
	

	protected abstract void createMenus(IMenuManager menuManager);
	
	protected abstract void createColumns(ColumnViewer viewer);
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite body = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		createClient(body, toolkit);
		body.setLayout(new FillLayout());
	}
	

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		
		getColumnViewer().setInput(getInfo());
		
		updateActions(getPropertyActions());
		updateActions(getSelectionActions());
		super.infoChange();
		updateActions(getStackActions());
	}
	
	protected Control createViewerButtons(Composite parent, FormToolkit toolkit) {
		
		ButtonGroupManager btnGroupManager = new ButtonGroupManager();
		createButtons(btnGroupManager);
		btnGroupManager.createControl(parent);
		
		
		toolkit.adapt(btnGroupManager.getControl());
		
		return btnGroupManager.getControl();
	}
	
	protected void createButtons(ButtonGroupManager manager) {
		
	}
	
	/**
	 * 获得表格的内容提供器
	 * @return
	 */
	protected abstract IContentProvider getColumnViewerContentProvider();
	
	protected void configureColumnViewer(ColumnViewer viewer) {
		viewer.setUseHashlookup(true);
		
		createColumns(viewer);
		viewer.setContentProvider(getColumnViewerContentProvider());
		
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
		
		// 开启表格tooltip显示
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.RECREATE);
		
		// 因为按钮几乎是表格相关的，所以必须等表格创建完成后初始化动作
		initializeActionRegistry();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#commandStackChanged(java.util.EventObject)
	 */
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		RefreshViewerJob.refresh(getColumnViewer(), null, false);
		Command cmd = ((CommandStack)event.getSource()).getMostRecentCommand();
		if (cmd != null) {
			RefreshViewerJob.refresh(getColumnViewer(), cmd.getAffectedObjects().toArray());
		}
		updateActions(getStackActions());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#firePropertyChange(int)
	 */
	@Override
	protected void firePropertyChange(int propertyId) {
		super.firePropertyChange(propertyId);
		updateActions(getPropertyActions());
	}
	
	protected void initializeActionRegistry() {
		createActions();
		updateActions(getPropertyActions());
		updateActions(getStackActions());
	}
	
	protected void createActions() {
		
	}

	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			ColumnViewer viewer = getColumnViewer();
			if (viewer != null) {
				getSite().setSelectionProvider(viewer);
				logger.debug("Selection provider: " + viewer.hashCode() + " Site: " + getSite().hashCode());
			} else {
				logger.debug("Page actived, but no viewer!");
			}
		} 
	}
	
}
