/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.grid.table;


import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.ui.util.AresTableActionHandleProvider;
import com.hundsun.ares.studio.ui.util.ITableActionHandleProvider;

/**
 * 利用Eclipse3.3新增的API重写<BR>
 * 
 * 这一层主要为为表格编辑器增加了动作 和 菜单 如复制 黏贴等
 * 
 * @author gongyf
 * 
 * @param <T>
 */
public abstract class GridTableViewerExComponent<T> extends GridTableViewerEditorableComponent<T> {

	/** 用于类型测试的类 */
	protected T testClassInstance = createBlankData();
	
	/** 删除时是否需要确认是否删除 */
	protected boolean isConfirmDel = true;
	
	/** 复制粘贴操作提供器 */
	private ITableActionHandleProvider provider = new AresTableActionHandleProvider<T>(this, testClassInstance);
	
	@Override
	final public Composite create(FormToolkit toolkit, Composite parent) {
		Composite client = null;
		if (toolkit == null) {
			client = new Composite(parent, SWT.NONE);
		} else {
			client = toolkit.createComposite(parent);
		}
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		client.setLayout(layout);
		client.setLayoutData(new GridData(GridData.FILL_BOTH));

		// 初始化控件
		initComposite(client);

		buttons = createButtons(toolkit, client);

		int size = buttons.size();
		if (toolkit != null) {
			toolkit.adapt(tbComposite);
		}
		// 调整表格
		((GridData) tbComposite.getLayoutData()).verticalSpan = size > 0 ? size : 1;
		return client;
	}
	
	@Override
	protected void initComposite(Composite client) {
		adjustStyle();
		super.initComposite(client);
		
		createMenuManager(viewer.getControl());
		
		// 添加表格操作快捷键
		viewer.getGrid().addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				if (provider != null) {
					if (e.keyCode == 'c' && e.stateMask == SWT.CTRL) {
						if (provider.canCopy()) {
							provider.copy();
						}
					} else if (e.keyCode == 'x' && e.stateMask == SWT.CTRL) {
						if (provider.canCut()) {
							provider.cut();
						}
					} else if (e.keyCode == 'v' && e.stateMask == SWT.CTRL) {
						if (provider.canPaste()) {
							provider.paste();
						}
					} else if (e.keyCode == SWT.DEL) {
						if (provider.canDelete()) {
							provider.delete();
						}
					} else if (e.keyCode == 'a' && e.stateMask == SWT.CTRL) {
						viewer.getGrid().selectAll();
					} else if (e.keyCode == SWT.INSERT) {
						if (provider.canInsert()) {
							provider.insert();
						}
					}
				}
			}
		});
	}
	
	/**
	 * 创建表格视图右键菜单。
	 * 
	 * @param control viewer
	 */
	protected void createMenuManager(Control control) {
		MenuManager popupMenuManager = new MenuManager();
		IMenuListener listener = new IMenuListener() {
			public void menuAboutToShow(IMenuManager mng) {
				fillContextMenu(mng);
			}
		};
		
		popupMenuManager.addMenuListener(listener);
		popupMenuManager.setRemoveAllWhenShown(true);
		
		Menu menu = popupMenuManager.createContextMenu(control);
		control.setMenu(menu);
	}
	
	/**
	 * 填充Master视图右键菜单项。
	 * 
	 * @param manager
	 */
	protected void fillContextMenu(IMenuManager manager) {
		if (provider != null) {
			GridTableViewerActionGroup group = new GridTableViewerActionGroup(provider);
			group.fillContextMenu(manager);
		}
	}
	
	public ITableActionHandleProvider getProvider() {
		return provider;
	}

	public void setProvider(ITableActionHandleProvider provider) {
		this.provider = provider;
	}

	/**
	 * 设置删除时是否需要确认删除。
	 * 
	 * @param isConfirmDel
	 */
	public void setConfirmDel(boolean isConfirmDel) {
		this.isConfirmDel = isConfirmDel;
	}

	public boolean isConfirmDel() {
		return isConfirmDel;
	}
	
}
