/**
 * 源程序名称：MetadataTreeListWithDetailPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.actions.ButtonGroupManager;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;
import com.hundsun.ares.studio.ui.editor.blocks.ActionRegistry;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;

/**
 * 在树视图的元数据编辑器基础上增加了详细表的编辑器页<BR>
 * 详细表拥有自己的Action注册器，可以创建自己的按钮和菜单组
 * @author gongyf
 *
 */
public abstract class MetadataTreeListWithDetailPage extends MetadataTreeListPage {

	private ColumnViewer detailColumnViewer;
	private ActionRegistry detailActionRegistry;
	private List<String> detailSelectionActions;
	private List<String> detailStackActions;
	private List<String> detailPropertyActions;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public MetadataTreeListWithDetailPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	/**
	 * @return the detailActionRegistry
	 */
	protected ActionRegistry getDetailActionRegistry() {
		if (detailActionRegistry == null) {
			detailActionRegistry = new ActionRegistry();
		}
		return detailActionRegistry;
	}
	
	/**
	 * @return the detailPropertyActions
	 */
	protected List<String> getDetailPropertyActions() {
		if (detailPropertyActions == null) {
			detailPropertyActions = new ArrayList<String>();
		}
		return detailPropertyActions;
	}
	
	/**
	 * @return the detailStackActions
	 */
	protected List<String> getDetailStackActions() {
		if (detailStackActions == null) {
			detailStackActions = new ArrayList<String>();
		}
		return detailStackActions;
	}
	
	/**
	 * @return the detailSelectionActions
	 */
	protected List<String> getDetailSelectionActions() {
		if (detailSelectionActions == null) {
			detailSelectionActions = new ArrayList<String>();
		}
		return detailSelectionActions;
	}
	
	/**
	 * @return the detailViewer
	 */
	protected ColumnViewer getDetailColumnViewer() {
		return detailColumnViewer;
	}
	
	
	protected void initializeDetailActionRegistry() {
		createDetailActions();
		updateDetailActions(getDetailPropertyActions());
		updateDetailActions(getDetailStackActions());
	}

	/**
	 * 创建详细表的操作
	 */
	protected void createDetailActions() {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#firePropertyChange(int)
	 */
	@Override
	protected void firePropertyChange(int propertyId) {
		super.firePropertyChange(propertyId);
		updateDetailActions(getDetailPropertyActions());
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#commandStackChanged(java.util.EventObject)
	 */
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		RefreshViewerJob.refresh(getDetailColumnViewer(), null, false);
		updateDetailActions(getDetailStackActions());
	}
	
	@Override
	protected Control createClient(Composite parent, FormToolkit toolkit) {
		SashForm sash = new SashForm(parent, SWT.VERTICAL | SWT.SMOOTH);
		Control upPane = super.createClient(sash, toolkit);
		
		Composite downPane = toolkit.createComposite(sash);
		Control viewer = createDetailColumnViewer(downPane, toolkit);
		Control buttons = createDetailViewerButtons(downPane, toolkit);
		
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(downPane);
		GridDataFactory.fillDefaults().hint(100, 100).grab(true, true).applyTo(viewer);
		GridDataFactory.fillDefaults().grab(false, true).hint(80, -1).applyTo(buttons);
		
		sash.setWeights(new int[] { 70, 30 });
		return sash;
	}
	
	/**
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	private Control createDetailViewerButtons(Composite parent,
			FormToolkit toolkit) {
		
		ButtonGroupManager btnGroupManager = new ButtonGroupManager();
		createDetailButtons(btnGroupManager);
		btnGroupManager.createControl(parent);
		
		toolkit.adapt(btnGroupManager.getControl());
		
		
		return btnGroupManager.getControl();
	}

	/**
	 * 创建详细表的按键
	 * @param manager
	 */
	protected abstract void createDetailButtons(ButtonGroupManager manager);
	
	protected abstract EReference getDetailReference();
	
	/**
	 * @param columnViewer
	 */
	protected void configureDetailColumnViewer(ColumnViewer columnViewer) {
		createDetailColumns(columnViewer);
		columnViewer.setContentProvider(new ReferenceTreeContentProvider(getDetailReference()));
		
		MenuManager menuManager = new MenuManager("#popup.items");
		menuManager.addMenuListener(new IMenuListener() {
			
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				manager.removeAll();
				createDetailMenus(manager);
			}
		});

		Menu menu = menuManager.createContextMenu(columnViewer.getControl());
		columnViewer.getControl().setMenu(menu);
		
		// 开启表格tooltip显示
		ColumnViewerToolTipSupport.enableFor(columnViewer, ToolTip.RECREATE);
		
		// 因为按钮几乎是表格相关的，所以必须等表格创建完成后初始化动作
		initializeDetailActionRegistry();
	}

	/**
	 * 创建详细表的右键菜单
	 * @param manager
	 */
	protected abstract void createDetailMenus(IMenuManager manager);

	/**
	 * 创建详细表的列信息
	 * @param columnViewer
	 */
	protected abstract void createDetailColumns(ColumnViewer columnViewer);
	
	/**
	 * @param viewer
	 */
	protected void setDetailColumnViewer(ColumnViewer viewer) {
		if (this.detailColumnViewer != null) {
			this.detailColumnViewer.removeSelectionChangedListener(this);
		}
		this.detailColumnViewer = viewer;
		if (this.detailColumnViewer != null) {
			this.detailColumnViewer.addSelectionChangedListener(this);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelectionProvider() == getColumnViewer()) {
			super.selectionChanged(event);
			IStructuredSelection selection = (IStructuredSelection) event.getSelection();
			if (!selection.isEmpty()) {
				notifyDetailViewer((EObject) selection.getFirstElement());
			} else {
				notifyDetailViewer(null);
			}
			
		} else if (event.getSelectionProvider() == getDetailColumnViewer()) {
			updateDetailActions(getDetailSelectionActions());
		}
	}
	
	protected void notifyDetailViewer(EObject input) {
		getDetailColumnViewer().setInput(input);
		updateDetailActions(getDetailPropertyActions());
		updateDetailActions(getDetailSelectionActions());
		updateDetailActions(getDetailStackActions());
	}
	
	protected void updateDetailActions(final List<String> actionIds) {
		Display display = null;
		Shell shell = null;
		if(getSite() != null) {
			shell = getSite().getShell();
			if(shell != null) {
				display = shell.getDisplay();
			}
		}
		if(display != null && !display.isDisposed()) {
			 Runnable runnable = new Runnable() {
		            public void run() {
		            	ActionRegistry registry = getDetailActionRegistry();
		            	Iterator<String> iter = actionIds.iterator();
		            	while (iter.hasNext()) {
		            		IAction action = registry.getAction(iter.next());
		            		if (action instanceof IUpdateAction)
		            			((IUpdateAction) action).update();
		            	}

		             }
		      	};
		     display.syncExec(runnable);
		}
		
	}
	
	/**
	 * 创建详细表
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected Control createDetailColumnViewer(Composite parent,
			FormToolkit toolkit) {
		TableViewer table = new TableViewer( toolkit.createTable( parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI));
		
		table.getTable().setHeaderVisible(true);
		table.getTable().setLinesVisible(true);
		
		setDetailColumnViewer(table);
		
		configureDetailColumnViewer(getDetailColumnViewer());
		
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		//增加拖拽功能
		table.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(table));
		table.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(getEditingDomain(), table));

		return table.getControl();
	}
}
