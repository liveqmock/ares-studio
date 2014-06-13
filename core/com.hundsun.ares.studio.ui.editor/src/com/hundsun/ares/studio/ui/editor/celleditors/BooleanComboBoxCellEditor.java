/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor.celleditors;

import java.text.MessageFormat;

import org.apache.commons.lang.BooleanUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * @author gongyf
 *
 */
public class BooleanComboBoxCellEditor extends CellEditor {

	private String trueText;
	private String falseText;
	/**
	 * The custom combo box control.
	 * 确认下拉菜单，第一项是true，第二项是false
	 */
	private CCombo comboBox;
	
	private boolean bValue;
	
	/**
	 * Default ComboBoxCellEditor style
	 */
	private static final int defaultStyle = SWT.NONE;
	
	
	
	/**
	 * 改进的布尔类型celleditor，在鼠标操作的时候和一般的checkboxcelleditor一致，如果是由程序触发，或者tab键触发
	 * 则会显示一个combobox来编辑
	 * 
	 * @param parent
	 * @param trueText
	 * @param falseText
	 */
	public BooleanComboBoxCellEditor(Composite parent, String trueText,
			String falseText) {
		this(parent, trueText, falseText, defaultStyle);
	}

	
	
	/**
	 * @param parent
	 * @param trueText
	 * @param falseText
	 * @param style
	 */
	public BooleanComboBoxCellEditor(Composite parent,
			String trueText, String falseText, int style) {
		super(parent, style);
		setTrueText(trueText);
		setFalseText(falseText);
	}



	/**
	 * @param trueText the trueText to set
	 */
	public void setTrueText(String trueText) {
		this.trueText = trueText;
		populateComboBoxItems();
	}
	
	/**
	 * @return the trueText
	 */
	public String getTrueText() {
		return trueText;
	}
	
	/**
	 * @param falseText the falseText to set
	 */
	public void setFalseText(String falseText) {
		this.falseText = falseText;
		populateComboBoxItems();
	}
	
	/**
	 * @return the falseText
	 */
	public String getFalseText() {
		return falseText;
	}
	
	/**
	 * Updates the list of choices for the combo box for the current control.
	 */
	private void populateComboBoxItems() {
		if (comboBox != null && getTrueText() != null && getFalseText() != null) {
			comboBox.removeAll();
			comboBox.setItems(new String[]{getTrueText(), getFalseText()});

			setValueValid(true);
			bValue = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createControl(Composite parent) {
		comboBox = new CCombo(parent, getStyle() | SWT.READ_ONLY);
		comboBox.setFont(parent.getFont());

		populateComboBoxItems();

		comboBox.addKeyListener(new KeyAdapter() {
			// hook key pressed - see PR 14201
			public void keyPressed(KeyEvent e) {
				keyReleaseOccured(e);
			}
		});

		comboBox.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent event) {
				applyEditorValueAndDeactivate();
			}

			public void widgetSelected(SelectionEvent event) {
				bValue = comboBox.getSelectionIndex() == 0;
			}
		});

		comboBox.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_ESCAPE
						|| e.detail == SWT.TRAVERSE_RETURN) {
					e.doit = false;
				}
			}
		});

		comboBox.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				BooleanComboBoxCellEditor.this.focusLost();
			}
		});
		return comboBox;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		return bValue;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#doSetFocus()
	 */
	@Override
	protected void doSetFocus() {
		comboBox.setFocus();
	}

	public LayoutData getLayoutData() {
		LayoutData layoutData = super.getLayoutData();
		if ((comboBox == null) || comboBox.isDisposed()) {
			layoutData.minimumWidth = 60;
		} else {
			// make the comboBox 10 characters wide
			GC gc = new GC(comboBox);
			layoutData.minimumWidth = (gc.getFontMetrics()
					.getAverageCharWidth() * 10) + 10;
			gc.dispose();
		}
		return layoutData;
	}
	
	/**
	 * Applies the currently selected value and deactivates the cell editor
	 */
	void applyEditorValueAndDeactivate() {
		// must set the selection before getting value
		bValue = comboBox.getSelectionIndex() == 0;
		Object newValue = doGetValue();
		markDirty();
		boolean isValid = isCorrect(newValue);
		setValueValid(isValid);

		if (!isValid) {
			// combo so format using its text value
			setErrorMessage(MessageFormat.format(getErrorMessage(),
					new Object[] { comboBox.getText() }));
		}

		fireApplyEditorValue();
		deactivate();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(Object value) {
		Assert.isTrue(comboBox != null);
		bValue = BooleanUtils.toBoolean(String.valueOf(value));
		comboBox.select(bValue ? 0 : 1);
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#activate(org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent)
	 */
	@Override
	public void activate(ColumnViewerEditorActivationEvent activationEvent) {
		if (activationEvent.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL 
				|| activationEvent.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC) {
			// 通过tab键激活或者程序调用激活需要显示control
			super.activate(activationEvent);
			
		} else {
			
			// 这里特殊处理
			// 当用户用鼠标点击时希望直接改变值而不显示控件
			// 1、如果这里不用ui的异步执行，因为fireApplyEditorValue()方法中会将cellEditor这个内部变量设置为null，导致
			// org.eclipse.jface.viewers.ColumnViewerEditor.activateCellEditor(ColumnViewerEditorActivationEvent)的207行出现空指针错误
			// 利用异步执行后，会在activateCellEditor方法后才执行，就不会出错
			// 2、其次因为有个异步问题，会出现combobox出现一瞬间后消失，会让用户感觉上不好，所以需要屏蔽这种现象
			// 采用的方法是先禁用父容器的重绘，就是父容器的显示将不变化，当全部操作以后才恢复父容器的重绘，其中的闪现问题将消失
			getControl().getParent().setRedraw(false);
			Display.getDefault().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					
					bValue = !bValue;
					fireApplyEditorValue();
					getControl().getParent().setRedraw(true);
					
				}
			});

		}
		
	}
	
}
