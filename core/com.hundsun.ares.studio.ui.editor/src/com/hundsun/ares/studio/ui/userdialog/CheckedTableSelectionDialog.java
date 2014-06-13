package com.hundsun.ares.studio.ui.userdialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.jface.gridviewer.GridViewerColumn;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionStatusDialog;

import com.hundsun.ares.studio.ui.celleditor.BooleanCheckboxCellEditor;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.grid.table.FilteredGridTable;
import com.hundsun.ares.studio.ui.grid.table.HSGridTableFilter;

public abstract class CheckedTableSelectionDialog<T> extends SelectionStatusDialog {
	protected GridTableViewer viewer;
	protected List<T> input;
	protected List<T> selection = new ArrayList<T>();
	protected List<T> deselection = new ArrayList<T>();

	private String[] titles;
	protected EAttribute[] attributes;
	
	protected BooleanCheckboxCellEditor booleanCellEditor;

	private int fWidth = 90;
	private int fHeight = 20;

	public CheckedTableSelectionDialog(Shell parent, String[] titles, EAttribute[] attributes) {
		super(parent);
		this.titles = titles;
		this.attributes = attributes;
	}

	/**
	 * Sets the size of the tree in unit of characters.
	 * 
	 * @param width
	 *            the width of the tree.
	 * @param height
	 *            the height of the tree.
	 */
	public void setSize(int width, int height) {
		fWidth = width;
		fHeight = height;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		createMessageArea(composite);
		createTableViewer(composite);
		createSelectionButtons(composite);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = convertWidthInCharsToPixels(fWidth);
		data.heightHint = convertHeightInCharsToPixels(fHeight);
		Grid tableWidget = viewer.getGrid();
		tableWidget.setLayoutData(data);
		tableWidget.setFont(parent.getFont());
		return composite;
	}

	private void createTableViewer(Composite parent) {
		FilteredGridTable filteredTable = new FilteredGridTable(parent, SWT.FULL_SELECTION | SWT.V_SCROLL
				| SWT.H_SCROLL | SWT.MULTI | SWT.BORDER, new HSGridTableFilter());
		viewer = filteredTable.getViewer();
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.getGrid().setHeaderVisible(true);
		viewer.getGrid().setRowHeaderVisible(true);

		if (titles.length == attributes.length && titles.length > 0) {
			for (int i = 0; i < titles.length; i++) {
				GridViewerColumn column = new GridViewerColumn(viewer, /*
																		 * i ==
																		 * 0 ?
																		 * SWT
																		 * .CHECK
																		 * :
																		 */SWT.NONE);
				column.setLabelProvider(new MyColumnLabelProvider(attributes[i]));
				column.getColumn().setText(titles[i]);
				column.getColumn().setWidth(100);
				column.setEditingSupport(new MyEditingSupport(viewer, attributes[i]));
			}
		}
		viewer.setInput(getMyInput());
		viewer.getGrid().setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	/**
	 * Adds the selection and deselection buttons to the dialog.
	 * 
	 * @param composite
	 *            the parent composite
	 * @return Composite the composite the buttons were created in.
	 */
	protected Composite createSelectionButtons(Composite composite) {
		Composite buttonComposite = new Composite(composite, SWT.RIGHT);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		buttonComposite.setLayout(layout);
		buttonComposite.setFont(composite.getFont());
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		composite.setData(data);
		Button selectButton = createButton(buttonComposite, IDialogConstants.SELECT_ALL_ID, "全选", false);
		selectButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<MyObject> myInput = (List<MyObject>) viewer.getInput();
				for (MyObject obj : myInput) {
					obj.setSelected(true);
				}
				viewer.setInput(myInput);
			}
		});

		Button deselectButton = createButton(buttonComposite, IDialogConstants.DESELECT_ALL_ID, "取消全选", false);
		deselectButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<MyObject> myInput = (List<MyObject>) viewer.getInput();
				for (MyObject obj : myInput) {
					obj.setSelected(false);
				}
				viewer.setInput(myInput);
			}
		});

		return buttonComposite;
	}

	private List<MyObject> getMyInput() {
		List<MyObject> myInput = new ArrayList<MyObject>();
		for (T obj : input) {
			myInput.add(new MyObject(obj, getInitialElementSelections().contains(obj)));
		}
		return myInput;
	}

	public void setInput(List<T> input) {
		this.input = input;
	}

	protected abstract String getText(T element, EAttribute attribute);

	/**
	 * 获取当前对话框的状态，有逻辑检查时需重写该方法。
	 * 
	 * @return
	 */
	protected IStatus getStatus(T element, boolean isSelected) {
		return Status.OK_STATUS;
	}

	protected Image getUncheckedImage(T element) {
		return null;
	}

	protected Image getDeleteCheckImage() {
		return ARESEditorPlugin.getImage("dechecked.gif");
	}

	protected CellEditor getCellEditor(Object element, EAttribute attribute) {
		if (attribute == attributes[0]) {
			if (booleanCellEditor == null) {
				booleanCellEditor = new BooleanCheckboxCellEditor(viewer.getGrid(), SWT.CHECK);
			}
			booleanCellEditor.setShowText(CheckedTableSelectionDialog.this.getText(
					((MyObject) element).getObj(), attribute));
			return booleanCellEditor;
		}
		return null;
	}

	@Override
	protected void computeResult() {
		selection.clear();
		deselection.clear();
		if (viewer.getInput() != null) {
			List<MyObject> myInput = (List<MyObject>) viewer.getInput();
			for (MyObject obj : myInput) {
				if (obj.isSelected) {
					selection.add(obj.getObj());
				} else {
					deselection.add(obj.getObj());
				}
			}
		}
	}

	/**
	 * 获取选择列表。
	 * 
	 * @return
	 */
	public List<T> getSelectedResults() {
		return selection;
	}

	/**
	 * 获取未选择列表。
	 * 
	 * @return
	 */
	public List<T> getDeselectedResults() {
		return deselection;
	}

	private class MyColumnLabelProvider extends ColumnLabelProvider {

		protected EAttribute attribute;

		/**
		 * @param attribute
		 */
		public MyColumnLabelProvider(EAttribute attribute) {
			super();
			this.attribute = attribute;
		}

		@Override
		public String getText(Object element) {
			return CheckedTableSelectionDialog.this.getText(((MyObject) element).getObj(), attribute);
		}

		@Override
		public Image getImage(Object element) {
			if (attribute == attributes[0]) {
				CellEditor cellEditor = getCellEditor(element, attribute);
				if (cellEditor instanceof BooleanCheckboxCellEditor) {
					if (((MyObject) element).isSelected()) {
						return ARESEditorPlugin.getImage("checked.gif");
					} else {
						Image image = getUncheckedImage(((MyObject) element).getObj());
						if (image != null) {
							return image;
						}
						return ARESEditorPlugin.getImage("unchecked.gif");
					}
				}
			}
			return super.getImage(element);
		}

	}

	/**
	 * 表格编辑辅助类。
	 * 
	 * @author mawb
	 * 
	 */
	private class MyEditingSupport extends EditingSupport {
		private EAttribute attribute;

		public MyEditingSupport(ColumnViewer viewer, EAttribute attribute) {
			super(viewer);
			this.attribute = attribute;
		}

		@Override
		protected boolean canEdit(Object element) {
			return attribute == attributes[0];
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return CheckedTableSelectionDialog.this.getCellEditor(element, attribute);
		}

		@Override
		protected Object getValue(Object element) {
			if (attribute == attributes[0]) {
				return ((MyObject) element).isSelected;
			}
			return null;
		}

		@Override
		protected void setValue(Object element, Object value) {
			if (attribute == attributes[0]) {
				if (value instanceof Boolean) {
					((MyObject) element).setSelected(((Boolean) value).booleanValue());
					viewer.refresh(element, false);
					updateStatus(getStatus(((MyObject) element).getObj(), ((Boolean) value).booleanValue()));
				}
			}
		}

	}

	/**
	 * 在原有模型基础上包装进来一个Boolean值，用来标识指定的模型对象是否被选中。
	 * 
	 * @author mawb
	 * 
	 */
	protected class MyObject {
		private boolean isSelected;
		private T obj;

		public MyObject(T obj, boolean isSelected) {
			this.obj = obj;
			this.isSelected = isSelected;
		}

		public boolean isSelected() {
			return isSelected;
		}

		public void setSelected(boolean isSelected) {
			this.isSelected = isSelected;
		}

		public T getObj() {
			return obj;
		}

		public void setObj(T obj) {
			this.obj = obj;
		}

	}

}
