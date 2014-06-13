/**
 * 源程序名称：selectEidtorDialog.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.model.database.util.DatabaseUtil;

/**
 * @author wangxh
 *
 */
public class SelectDialog extends Dialog {
	//标题
	protected String title;
	
	//应用后得到的结果
	protected List<?> result;
	
	protected List<?> choiceInput;
	
	protected List<?>resultInput;
	
	protected ILabelProvider labelProvider;
	
	protected IContentProvider contentProvider;
	
	protected ItemProvider choiceValues;
	
	protected ItemProvider resultValues;
	
	protected TableViewer choiceTableViewer;
	protected TableViewer resultTableViewer;
	
	/**
	 * @param parentShell
	 */
	public SelectDialog(Shell parentShell,String Title,List<?> ChoiceInput,List<?>resultInput,ILabelProvider labelProvider) {
		super(parentShell);
		this.title = Title;
		this.choiceInput= ChoiceInput;
		this.resultInput = resultInput;
		this.labelProvider = labelProvider;
		
		AdapterFactory adapterFactory = new ComposedAdapterFactory(Collections.<AdapterFactory>emptyList());
		choiceValues = new ItemProvider(adapterFactory,ChoiceInput);
		resultValues = new ItemProvider(adapterFactory, resultInput);
	    contentProvider = new AdapterFactoryContentProvider(adapterFactory);
	    
	    for (Object value : resultInput) {
			for (Iterator<?> it = choiceValues.getChildren().iterator(); it.hasNext();) {
				Object o = it.next();
				if (DatabaseUtil.equals(o, value))
					it.remove();
			}
	    }
	}
	
	 @Override
	  protected void configureShell(Shell shell) 
	  {
	    super.configureShell(shell);
	    shell.setText(title);
	    shell.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(DatabaseUI.PLUGIN_ID, "icons/table.gif").createImage());
	  }
	 
	@Override
	protected Button createButton(org.eclipse.swt.widgets.Composite parent,
			int id, String label, boolean defaultButton) {
		Button btn = null;
		if (IDialogConstants.OK_ID == id) {
			btn = super.createButton(parent, id, "应用", true);
		} else if (IDialogConstants.CANCEL_ID == id) {
			btn = super.createButton(parent, id, "取消", false);
		}
		return btn;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
	    Composite contents = (Composite)super.createDialogArea(parent);
	    
	    Composite client = new Composite(contents, SWT.NONE);
	    GridDataFactory.fillDefaults().indent(0, 0).span(0, 0).grab(true, true).applyTo(client);
	    GridLayoutFactory.fillDefaults().applyTo(client);

	    doCreateDialogArea(client);
	    return contents;
	  }
	
	protected void doCreateDialogArea(Composite parent) {
		Composite contents = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(contents);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(contents);
		
		Text patternText = null;

		{
			Group filterGroupComposite = new Group(contents, SWT.SEARCH);
			filterGroupComposite.setText("过滤");
			filterGroupComposite.setLayout(new GridLayout(2, false));
			filterGroupComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 3, 1));

			Label label = new Label(filterGroupComposite, SWT.NONE);
			label.setText("请输入过滤文本：");

			patternText = new Text(filterGroupComposite, SWT.SINGLE | SWT.BORDER | SWT.SEARCH | SWT.ICON_CANCEL);
			patternText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}

		Composite choiceComposite = new Composite(contents, SWT.NONE);
		{
			GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
			data.horizontalAlignment = SWT.END;
			choiceComposite.setLayoutData(data);

			GridLayout layout = new GridLayout();
			data.horizontalAlignment = SWT.FILL;
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			layout.numColumns = 1;
			choiceComposite.setLayout(layout);
		}

		Label choiceLabel = new Label(choiceComposite, SWT.NONE);
		choiceLabel.setText("待选项");
		GridData choiceLabelGridData = new GridData();
		choiceLabelGridData.verticalAlignment = SWT.FILL;
		choiceLabelGridData.horizontalAlignment = SWT.FILL;
		choiceLabel.setLayoutData(choiceLabelGridData);

		final Table choiceTable = new Table(choiceComposite, SWT.MULTI | SWT.BORDER);
		if (choiceTable != null) {
			GridData choiceTableGridData = new GridData();
			choiceTableGridData.widthHint = Display.getCurrent().getBounds().width / 8;
			choiceTableGridData.heightHint = Display.getCurrent().getBounds().height / 4;
			choiceTableGridData.verticalAlignment = SWT.FILL;
			choiceTableGridData.horizontalAlignment = SWT.FILL;
			choiceTableGridData.grabExcessHorizontalSpace = true;
			choiceTableGridData.grabExcessVerticalSpace = true;
			choiceTable.setLayoutData(choiceTableGridData);
		}

		choiceTableViewer = new TableViewer(choiceTable);
		if (choiceTableViewer != null) {
			choiceTableViewer.setContentProvider(contentProvider);
			choiceTableViewer.setLabelProvider(labelProvider);
			// 过滤器
			final PatternFilter filter = new PatternFilter() {
				@Override
				protected boolean isParentMatch(Viewer viewer, Object element) {
					return viewer instanceof AbstractTreeViewer && super.isParentMatch(viewer, element);
				}
			};
			choiceTableViewer.addFilter(filter);
			assert patternText != null;
			patternText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					// 过滤
					filter.setPattern(((Text) e.widget).getText());
					choiceTableViewer.refresh();
				}
			});
			choiceTableViewer.setInput(choiceValues);
		}

		Composite controlButtons = new Composite(contents, SWT.NONE);
		GridData controlButtonsGridData = new GridData();
		controlButtonsGridData.verticalAlignment = SWT.FILL;
		controlButtonsGridData.horizontalAlignment = SWT.FILL;
		controlButtons.setLayoutData(controlButtonsGridData);

		GridLayout controlsButtonGridLayout = new GridLayout();
		controlButtons.setLayout(controlsButtonGridLayout);

		new Label(controlButtons, SWT.NONE);

		final Button addButton = new Button(controlButtons, SWT.PUSH);
		addButton.setText("添加");
		GridData addButtonGridData = new GridData();
		addButtonGridData.verticalAlignment = SWT.FILL;
		addButtonGridData.horizontalAlignment = SWT.FILL;
		addButtonGridData.minimumWidth = 30;
		addButton.setLayoutData(addButtonGridData);

		final Button removeButton = new Button(controlButtons, SWT.PUSH);
		removeButton.setText("移除");
		GridData removeButtonGridData = new GridData();
		removeButtonGridData.verticalAlignment = SWT.FILL;
		removeButtonGridData.horizontalAlignment = SWT.FILL;
		removeButton.setLayoutData(removeButtonGridData);

		Label spaceLabel = new Label(controlButtons, SWT.NONE);
		GridData spaceLabelGridData = new GridData();
		spaceLabelGridData.verticalSpan = 2;
		spaceLabel.setLayoutData(spaceLabelGridData);

		final Button upButton = new Button(controlButtons, SWT.PUSH);
		upButton.setText("上移");
		GridData upButtonGridData = new GridData();
		upButtonGridData.verticalAlignment = SWT.FILL;
		upButtonGridData.horizontalAlignment = SWT.FILL;
		upButton.setLayoutData(upButtonGridData);

		final Button downButton = new Button(controlButtons, SWT.PUSH);
		downButton.setText("下移");
		GridData downButtonGridData = new GridData();
		downButtonGridData.verticalAlignment = SWT.FILL;
		downButtonGridData.horizontalAlignment = SWT.FILL;
		downButton.setLayoutData(downButtonGridData);

		Composite resultComposite = new Composite(contents, SWT.NONE);
		{
			GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
			data.horizontalAlignment = SWT.END;
			resultComposite.setLayoutData(data);

			GridLayout layout = new GridLayout();
			data.horizontalAlignment = SWT.FILL;
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			layout.numColumns = 1;
			resultComposite.setLayout(layout);
		}

		Label resultLabel = new Label(resultComposite, SWT.NONE);
		resultLabel.setText("已选项");
		GridData resultLabelGridData = new GridData();
		resultLabelGridData.horizontalSpan = 2;
		resultLabelGridData.horizontalAlignment = SWT.FILL;
		resultLabelGridData.verticalAlignment = SWT.FILL;
		resultLabel.setLayoutData(resultLabelGridData);

		final Table resultTable = new Table(resultComposite, SWT.MULTI | SWT.BORDER);
		GridData resultTableGridData = new GridData();
		resultTableGridData.widthHint = Display.getCurrent().getBounds().width / 8;
		resultTableGridData.heightHint = Display.getCurrent().getBounds().height / 4;
		resultTableGridData.verticalAlignment = SWT.FILL;
		resultTableGridData.horizontalAlignment = SWT.FILL;
		resultTableGridData.grabExcessHorizontalSpace = true;
		resultTableGridData.grabExcessVerticalSpace = true;
		resultTable.setLayoutData(resultTableGridData);

		resultTableViewer = new TableViewer(resultTable);
		resultTableViewer.setLabelProvider(labelProvider);
		resultTableViewer.setContentProvider(contentProvider);
		resultTableViewer.setInput(resultValues);

		if (choiceTableViewer != null) {
			choiceTableViewer.addDoubleClickListener(new IDoubleClickListener() {
				public void doubleClick(DoubleClickEvent event) {
					if (addButton.isEnabled()) {
						// 待选项双击表示增加
						addButton.notifyListeners(SWT.Selection, null);
					}
				}
			});

			resultTableViewer.addDoubleClickListener(new IDoubleClickListener() {
				public void doubleClick(DoubleClickEvent event) {
					if (removeButton.isEnabled()) {
						// 已选项双击表示移除
						removeButton.notifyListeners(SWT.Selection, null);
					}
				}
			});
		}

		// 设置按键置灰条件
		addButton.setEnabled(false);
		removeButton.setEnabled(false);
		downButton.setEnabled(false);
		upButton.setEnabled(false);

		choiceTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				removeButton.setEnabled(false);
				downButton.setEnabled(false);
				upButton.setEnabled(false);
				IStructuredSelection selection = (IStructuredSelection) choiceTableViewer.getSelection();
				if (selection != null && !selection.isEmpty()) {
					addButton.setEnabled(true);
				} else {
					addButton.setEnabled(false);
				}

			}
		});

		resultTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				addButton.setEnabled(false);
				IStructuredSelection selection = (IStructuredSelection) resultTableViewer.getSelection();
				List<?> resultInput = resultValues.getChildren();
				if (selection == null || selection.isEmpty()) {
					removeButton.setEnabled(false);
					upButton.setEnabled(false);
					downButton.setEnabled(false);
				} else {
					removeButton.setEnabled(true);
					List<?> selectObjects = selection.toList();
					int size = selectObjects.size();
					int last = resultInput.indexOf(selectObjects.get(size - 1));
					int first = resultInput.indexOf(selectObjects.get(0));
					if (resultInput.size() == size) {
						upButton.setEnabled(false);
						downButton.setEnabled(false);
					} else if (last == size - 1) {
						upButton.setEnabled(false);
						downButton.setEnabled(true);
					} else if (first == resultInput.size() - size) {
						upButton.setEnabled(true);
						downButton.setEnabled(false);
					} else {
						upButton.setEnabled(true);
						downButton.setEnabled(true);
					}
				}
			}
		});

		upButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				IStructuredSelection selection = (IStructuredSelection) resultTableViewer.getSelection();
				int minIndex = 0;
				for (Iterator<?> i = selection.iterator(); i.hasNext();) {
					Object value = i.next();
					int index = resultValues.getChildren().indexOf(value);
					resultValues.getChildren().move(Math.max(index - 1, minIndex++), value);
					resultTableViewer.setSelection(selection);
				}
			}
		});

		downButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				IStructuredSelection selection = (IStructuredSelection) resultTableViewer.getSelection();
				List<?> list = new ArrayList();
				list.addAll(selection.toList());
				int preIndex = resultValues.getChildren().size() - 1;
				while (!list.isEmpty()) {
					Object value = list.get(list.size() - 1);
					int index = resultValues.getChildren().indexOf(value);
					resultValues.getChildren().move(Math.min(index + 1, preIndex), value);
					preIndex = index;
					list.remove(value);
					resultTableViewer.setSelection(selection);
				}
			}
		});

		addButton.addSelectionListener(new SelectionAdapter() {
			// event is null when choiceTableViewer is double clicked
			@Override
			public void widgetSelected(SelectionEvent event) {
				IStructuredSelection selection = (IStructuredSelection) choiceTableViewer.getSelection();
				for (Iterator<?> i = selection.iterator(); i.hasNext();) {
					Object value = i.next();
					if (!DatabaseUtil.contains(resultValues.getChildren(), value)) {
						resultValues.getChildren().add(value);
						for (Iterator<?> it = choiceValues.getChildren().iterator(); it.hasNext();) {
							Object o = it.next();
							if (DatabaseUtil.equals(o, value))
								it.remove();
						}
						choiceTableViewer.refresh();
					}
				}
			}
		});

		removeButton.addSelectionListener(new SelectionAdapter() {
			// 移除
			@Override
			public void widgetSelected(SelectionEvent event) {
				IStructuredSelection selection = (IStructuredSelection) resultTableViewer.getSelection();
				Object firstValue = null;
				for (Iterator<?> i = selection.iterator(); i.hasNext();) {
					Object value = i.next();
					if (firstValue == null) {
						firstValue = value;
					}
					
					resultValues.getChildren().remove(value);
					List<Object> toBeAdded = new ArrayList<Object>();
					for (Object o : choiceInput) {
						if (DatabaseUtil.equals(o, value))
							toBeAdded.add(o);
					}
					choiceValues.getChildren().addAll(toBeAdded);
					choiceTableViewer.refresh();
					
				}
			}
		});
	}
	
	protected void performAdd(Object value) {
		resultValues.getChildren().add(value);
	}
	
	@Override
	protected void okPressed() {
		result = new BasicEList<Object>(resultValues.getChildren());
		super.okPressed();
	}

	@Override
	public boolean close() {
		contentProvider.dispose();
		return super.close();
	}

	//获取结果
	public List<?> getResult(){
		return result;
	}
}
