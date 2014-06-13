/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.grid.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.model.ICreateInstance;
import com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor;
import com.hundsun.ares.studio.ui.util.Clipboard;

/**
 * 
 * 这一层主要为为表格编辑器增加了动作 和 菜单 如复制 黏贴等
 * 
 * @author gongyf
 * 
 * @param <T>
 */
public abstract class GridTreeViewerExComponent<T> extends GridTreeViewerEditorableComponent<T> {
	/**
	 * 是否可拷贝
	 * 
	 * @return
	 */
	public boolean canCopy() {

		// 选择的对象需要是同一类型，且实现了ICreateInstance接口
		ITreeSelection sel = (ITreeSelection) viewer.getSelection();
		TreePath[] paths = sel.getPaths();

		// 需要有选择的项目
		if (paths.length == 0) {
			return false;
		}

		// 需要可以克隆的
		if (!(paths[0].getLastSegment() instanceof ICreateInstance)) {
			return false;
		}

		// 需要选择的项目是一种类型
		Class<?> cls = paths[0].getLastSegment().getClass();
		for (TreePath path : paths) {
			if (path.getLastSegment().getClass() != cls) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 是否可剪切
	 * 
	 * @return
	 */
	public boolean canCut() {
		return canCopy() && canDelete();
	}

	/**
	 * 是否可删除
	 * 
	 * @return
	 */
	public boolean canDelete() {

		if (readOnly) {
			return false;
		}

		// 必须有个位置被选择
		ITreeSelection sel = (ITreeSelection) viewer.getSelection();
		TreePath[] paths = sel.getPaths();
		if (paths.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 是否可插入
	 * 
	 * @return
	 */
	public boolean canInsert() {
		return !readOnly && ((ITreeSelection) viewer.getSelection()).getPaths().length > 0;
	}

	/**
	 * 是否可粘贴
	 * 
	 * @return
	 */
	public boolean canPaste() {

		if (readOnly) {
			return false;
		}
		// 必须有个位置被选择
		ITreeSelection sel = (ITreeSelection) viewer.getSelection();
		TreePath[] paths = sel.getPaths();
		if (paths.length == 0) {
			return false;
		}

		// 用来判断类型
		Object objTest = paths[0].getLastSegment();

		// 需要剪贴板不为空，且类型与当前位置的类型一致
		Object obj = Clipboard.instance.getData();
		if (obj != null && obj instanceof List) {
			// 空数据无粘贴意义
			if (!((List) obj).isEmpty()) {
				if (((List) obj).get(0).getClass() == objTest.getClass()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 进行复制操作
	 */
	public void copy() {
		ITreeSelection sel = (ITreeSelection) viewer.getSelection();
		TreePath[] paths = sel.getPaths();

		ArrayList<Object> copied = new ArrayList<Object>();
		for (TreePath path : paths) {
			copied.add(path.getLastSegment());
		}

		// 放入剪贴板
		Clipboard.instance.setData(copied);
	}

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

		initTreeMenu();

		int size = buttons.size();

		// 调整表格
		((GridData) filteredTree.getLayoutData()).verticalSpan = size > 0 ? size : 1;
		return client;
	}

	/**
	 * 进行剪切操作
	 */
	public void cut() {
		copy();
		deleteWithOutConfirmed();
	}

	/**
	 * 
	 * 进行删除操作
	 */
	public void delete() {
		boolean confirmed = MessageDialog.openConfirm(viewer.getGrid().getShell(), "", "确实要删除吗?");
		if (!confirmed) {
			return;
		}
		deleteWithOutConfirmed();
	}

	/**
	 * 进行删除操作
	 */
	public void deleteWithOutConfirmed() {

		ITreeSelection sel = (ITreeSelection) viewer.getSelection();
		TreePath[] paths = sel.getPaths();

		if (paths.length > 0) {
			// 先排序，路径短的在前面
			Arrays.sort(paths, new Comparator<TreePath>() {
				public int compare(TreePath o1, TreePath o2) {
					return o1.getSegmentCount() - o2.getSegmentCount();
				}
			});
			GridTreeDeleteItemOperation operation = new GridTreeDeleteItemOperation("", this, paths);
			operation.addContext(undoContext);
			try {
				AbstractHSFormEditor.getOperationHistory().execute(operation, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected int treeColumn = 0;

	@Override
	protected void initComposite(Composite client) {
		adjustStyle();
		super.initComposite(client);
		if (treeColumn >= 0 && viewer.getGrid().getColumnCount() > treeColumn) {
			if (viewer.getGrid().getColumn(treeColumn) != null) {
				viewer.getGrid().getColumn(treeColumn).setTree(true);
			}
		}

		viewer.getGrid().addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 'c' && e.stateMask == SWT.CTRL) {
					if (canCopy()) {
						copy();
					}
				} else if (e.keyCode == 'x' && e.stateMask == SWT.CTRL) {
					if (canCut()) {
						cut();
					}
				} else if (e.keyCode == 'v' && e.stateMask == SWT.CTRL) {
					if (canPaste()) {
						paste();
					}
				} else if (e.keyCode == SWT.DEL) {
					if (canDelete()) {
						delete();
					}
				} else if (e.keyCode == 'a' && e.stateMask == SWT.CTRL) {
					viewer.getGrid().selectAll();
				} else if (e.keyCode == SWT.INSERT) {
					if (canInsert()) {
						insert();
					}
				} else if (e.keyCode == SWT.F5) {
					viewer.refresh();
				}
			}

			public void keyReleased(KeyEvent e) {

			}
		});
	}

	/**
	 * 初始化菜单，子类可以实现该方法定义自己的菜单
	 */
	protected void initTreeMenu() {
		// 添加复制，粘贴，删除
		GridTreeViewerActionGroup group = new GridTreeViewerActionGroup(this);
		group.fillContextMenu(new MenuManager());
	}

	/**
	 * 进行插入操作
	 */
	public void insert() {
		ITreeSelection sel = (ITreeSelection) viewer.getSelection();
		TreePath[] paths = sel.getPaths();

		TreePath parentPath = paths[0].getParentPath();
		List addItems = new ArrayList();
		addItems.add(createBlankData(parentPath.getLastSegment()));
		GridTreeAddItemOperation operation = new GridTreeAddItemOperation("add", this, paths, addItems);
		operation.addContext(undoContext);
		try {
			AbstractHSFormEditor.getOperationHistory().execute(operation, null, null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 进行粘贴操作
	 */
	public void paste() {
		ITreeSelection sel = (ITreeSelection) viewer.getSelection();
		TreePath[] paths = sel.getPaths();
		List<Object> objs = (List) Clipboard.instance.getData();
		GridTreeAddItemOperation operation = new GridTreeAddItemOperation("", this, paths, objs);
		operation.addContext(undoContext);
		try {
			AbstractHSFormEditor.getOperationHistory().execute(operation, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
