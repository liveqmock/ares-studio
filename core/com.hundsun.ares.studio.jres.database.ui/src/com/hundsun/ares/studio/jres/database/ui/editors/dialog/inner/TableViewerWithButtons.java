package com.hundsun.ares.studio.jres.database.ui.editors.dialog.inner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

/**
 * 比普通表格右侧多一列，一般用来放按钮，如果想要表格个按钮显示在同一行(一起占满整行)，则父容器的布局列数必须>1
 * 
 * @author yanyl
 * 
 */
public abstract class TableViewerWithButtons extends BasicTableViewer {

	private int btnPosition = SWT.RIGHT;

	/**
	 * 新建默认样式的带按钮的表格，按钮在表格右侧
	 */
	public TableViewerWithButtons(Composite parent) {
		this(parent, BasicTableViewer.DEFAULT_STYLE, SWT.RIGHT);
	}

	/**
	 * 新建默认样式的带按钮的表格
	 * 
	 * @param btnPosition
	 *            按钮位置，可用位置：SWT.RIGHT：表格右侧 SWT.BOTTOM表格底部
	 */
	public TableViewerWithButtons(Composite parent, int btnPosition) {
		this(parent, BasicTableViewer.DEFAULT_STYLE, btnPosition);
	}

	/**
	 * 新建带按钮的表格
	 * 
	 * @param btnPosition
	 *            按钮位置，可用位置：SWT.RIGHT：表格右侧 SWT.BOTTOM表格底部
	 */
	public TableViewerWithButtons(Composite parent, int style, int btnPosition) {
		super(parent, style);

		this.btnPosition = btnPosition;

		boolean onBottom = btnPosition == SWT.BOTTOM;

		Layout parentlayout = (Layout) parent.getLayout();
		getTable().setLayoutData(
				getDefaultLayoutData(parentlayout instanceof GridLayout ? ((GridLayout) parentlayout).numColumns : 1));

		Composite buttonsComposite = new Composite(parent, 0);
		createButtons(buttonsComposite);

		GridLayoutFactory.fillDefaults().numColumns(onBottom ? buttonsComposite.getChildren().length : 1)
				.applyTo(buttonsComposite);
		GridDataFactory.fillDefaults().grab(onBottom, false)
				.align(onBottom ? SWT.BEGINNING : SWT.CENTER, onBottom ? SWT.CENTER : SWT.BEGINNING)
				.applyTo(buttonsComposite);
	}

	@Override
	protected GridData getDefaultLayoutData(int cols) {
		// 根据父容器的列数和按钮位置调整表格布局
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		if (btnPosition == SWT.BOTTOM) {
			data.horizontalSpan = cols;
		} else {
			data.horizontalSpan = cols - 1;
		}
		data.heightHint = 220;
		return data;
	}
	
	/**
	 * 创建作用到表格的按钮
	 * 
	 * @param parent
	 *            父容器
	 */
	protected abstract void createButtons(Composite parent);
}

/**
 * 按钮创建工厂，提供了一些表格常用的按钮
 * 
 * @author yanyl
 * 
 */
class TableButtonFactory {
	/**
	 * 添加按钮，点击后触发creator的create方法，并将创建出来的新对象添加到表格输入的末尾
	 * 
	 * @param <T>
	 *            点击添加按钮后新建出来的对象的类型
	 * @param parent
	 *            添加按钮父容器
	 * @param tableViewer
	 *            添加按钮生效的表格
	 * @param creator
	 *            实现InstanceCreator接口的新对象的创建器
	 * @return 附加操作将在成功新增后执行
	 * @see InstanceCreator
	 * @see ActionBuilder
	 */
	public static <T> ActionBuilder createAddBtn(Composite parent, final TableViewerWithButtons tableViewer,
			final InstanceCreator<T> creator) {
		Button add = new Button(parent, SWT.None);
		add.setText("添加");
//		add.setImage(ImageProvider.getImage(ImageProvider.ADD));

		final ActionBuilder builder = new ActionBuilder();
		add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				@SuppressWarnings("unchecked")
				Collection<T> inputs = (Collection<T>) tableViewer.getInput();
				T newInstance = creator.create(inputs);
				if (newInstance != null) {
					inputs.add(newInstance);
				}
				// 创建的实例为null时可能是添加逻辑已经在create()方法体中执行了
				builder.excute();
				tableViewer.refresh();
			}
		});
		return builder;
	}

	/**
	 * 创建删除按钮，点击后删除当前选中记录，并将焦点移动到下一条记录，若没有下一条记录则将焦点移动到上一条记录
	 * 
	 * @param parent
	 *            按钮父容器
	 * @param tableViewer
	 *            按钮生效的表格
	 * @return 附加动作将在成功删除了节点后执行
	 * @see ActionBuilder
	 */
	public static ActionBuilder createRemoveBtn(Composite parent, final TableViewerWithButtons tableViewer) {
		Button remove = new Button(parent, SWT.None);
		remove.setText("删除");
//		remove.setImage(ImageProvider.getImage(ImageProvider.REMOVE));
		final ActionBuilder builder = new ActionBuilder();
		remove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> params = (List<?>) tableViewer.getInput();
				StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
				Object object = selection.getFirstElement();
				if (object != null) {
					int index = params.indexOf(object);
					params.remove(object);
					// 果如list中还有数据则选中下一条
					if (index >= 0 && params.size() > 0) {
						// 若删除的最后一条则选中它的前一条数据
						while (index >= params.size()) {
							index--;
						}
						tableViewer.setSelection(new StructuredSelection(params.get(index)));
					}
					builder.excute();
					tableViewer.refresh();
				}
			}
		});
		return builder;
	}

	/**
	 * 创建上移按钮，将选中记录向上移动一格
	 * 
	 * @param parent
	 *            父容器
	 * @param tableViewer
	 *            生效的表格
	 * @return 附加操作将在成功上移后执行
	 * @see ActionBuilder
	 */
	public static ActionBuilder createMoveUpBtn(Composite parent, final TableViewerWithButtons tableViewer) {
		Button up = new Button(parent, SWT.None);
		up.setText("上移");
//		up.setImage(ImageProvider.getImage(ImageProvider.UP));
		final ActionBuilder builder = new ActionBuilder();
		up.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> inputs = (List<?>) tableViewer.getInput();
				StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
				Object object = selection.getFirstElement();
				if (object != null) {
					int index = inputs.indexOf(object);
					if (index > 0) {
						swap(inputs, index, index - 1);
						builder.excute();
						tableViewer.refresh();
					}
				}
			}
		});
		return builder;
	}

	/**
	 * 创建下移按钮，将选中记录向下移动一格
	 * 
	 * @param parent
	 *            父容器
	 * @param tableViewer
	 *            生效的表格
	 * @return 附加操作将在成功下移后执行
	 * @see ActionBuilder
	 */
	public static ActionBuilder createMoveDownBtn(Composite parent, final TableViewerWithButtons tableViewer) {
		Button down = new Button(parent, SWT.None);
		down.setText("下移");
//		up.setImage(ImageProvider.getImage(ImageProvider.DOWN));
		final ActionBuilder builder = new ActionBuilder();
		down.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> inputs = (List<?>) tableViewer.getInput();
				StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
				Object object = selection.getFirstElement();
				if (object != null) {
					int index = inputs.indexOf(object);
					if (index < inputs.size() - 1) {
						swap(inputs, index + 1, index);
						builder.excute();
						tableViewer.refresh();
					}
				}
			}
		});
		return builder;
	}

	/**
	 * 交换List中元素的位置，Collections中的swap交换有unique限定的List中的元素时可能会抛异常
	 * @param l 列表
	 * @param i 元素的index
	 * @param j 要交换的元素的index
	 */
	@SuppressWarnings("unchecked")
	private static void swap(@SuppressWarnings("rawtypes") List l, int i, int j) {
		int b = (i >= j) ? i : j;
		int s = (i < j) ? i : j;
		// remove动作从大的开始做
		Object target = l.remove(b);
		Object src = l.remove(s);
		// add动作从小的开始
		l.add(s, target);
		l.add(b, src);
	}

}

/**
 * TableButtonFactory中新增按钮用来创建新对象使用的接口
 * 
 * @author yanyl
 * 
 */
interface InstanceCreator<T> {
	/**
	 * 在Add按钮点击后调用，用于创建一个新的对象实例；也可以直接在方法体里面按自己的需要向inputs中添加数据
	 * 
	 * @param inputs
	 *            tableViewer的现有输入
	 * @return 新的实例，该实例将会被添加到表格的输入列表中去，若使用自定规则添加则返回null
	 */
	T create(Collection<T> inputs);
}

/**
 * 附加操作添加器，通过该对象可以添加需要在按钮本身的操作执行结束后运行的动作
 * 
 * @author yanyl
 * 
 */
class ActionBuilder {
	private List<ExtendAction> actions = new ArrayList<ExtendAction>();

	/**
	 * 添加附加动作
	 * 
	 * @param action
	 * @return
	 * @see ExtendAction
	 */
	public ActionBuilder append(ExtendAction action) {
		actions.add(action);
		return this;
	}

	/**
	 * 执行所有的附加动作
	 */
	public void excute() {
		for (ExtendAction action : actions) {
			action.run();
		}
	}
}

/**
 * 附加操作接口
 * 
 * @author yanyl
 * 
 */
interface ExtendAction {
	/**
	 * 执行任意附加操作
	 */
	void run();
}
