/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

/**
 * 按钮控件。
 * 
 * @author mawb
 */
public class ButtonAdaptor extends ControlWithUndoSupport<Button> {
	
	protected SelectionAdapter selectionAdapter;
	
	/**
	 * 构造一个Button按钮。
	 * 
	 * @param context 上下文
	 * @param label 按钮名称
	 * @param controlStyle 按钮样式
	 */
	public ButtonAdaptor(ImporveControlWithDitryStateContext context, String label, int controlStyle) {
		super(context, controlStyle);
		control.setText(label);
	}
	
	/**
	 * 构造一个Button按钮。
	 * 
	 * @param context 上下文
	 * @param label 按钮名称
	 * @param controlStyle 按钮样式
	 * @param beanFieldName 绑定字段名
	 */
	public ButtonAdaptor(ImporveControlWithDitryStateContext context, String label, int controlStyle, String beanFieldName) {
		super(context, controlStyle, beanFieldName);
		control.setText(label);
	}
	
	/**
	 * 构造一个Button按钮。
	 * 
	 * @param context 上下文
	 * @param label 按钮名称
	 * @param controlStyle 按钮样式
	 * @param model 绑定模型
	 * @param beanFieldName 绑定字段名
	 */
	public ButtonAdaptor(ImporveControlWithDitryStateContext context, String label, int controlStyle, Object model, String beanFieldName) {
		super(context, controlStyle, model, beanFieldName);
		control.setText(label);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.control.ControlWithDitryState#addModifyListener()
	 */
	@Override
	public void addModifyListener() {
		if (null != this.control) {
			if(selectionAdapter == null){
				selectionAdapter = new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						fireControlValueChange();
					}
				};
			}
			this.control.addSelectionListener(selectionAdapter);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.control.ControlWithDitryState#addMouseListener()
	 */
	@Override
	protected void addMouseListener() {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.control.ControlWithDitryState#getValue()
	 */
	@Override
	public Object getValue() {
		return getControl().getSelection();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.control.ControlWithDitryState#initControl()
	 */
	@Override
	protected Button initControl() {
		Button button = new Button(parent, controlStyle); 
		return button;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.control.ControlWithDitryState#removeModifyListener()
	 */
	@Override
	public void removeModifyListener() {
		if (control != null) {
			this.control.removeSelectionListener(selectionAdapter);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.control.ControlWithDitryState#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object object) {
		getControl().setSelection(Boolean.valueOf(object.toString()));
	}

}
