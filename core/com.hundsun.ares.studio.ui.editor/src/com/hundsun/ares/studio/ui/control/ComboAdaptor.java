/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control;


import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;



/**
 * 标签-Combo控件组。
 * 
 * @author liuning. Created on 2008-9-18. Modified by xx on xx.
 */
public class ComboAdaptor extends ControlWithShowControl<Combo> {
	
	String[] items;
	
	SelectionAdapter selectionAdapter;
	
	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 * @param combo的选项
	 * @param 绑定字段名称
	 */
	public ComboAdaptor(String label,int controlStyle,ImporveControlWithDitryStateContext context,String[] items,String beanFieldName) {
		super(label,controlStyle,context,context.getInfo(),beanFieldName);
		initCombo(items);
	}
	
	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 * @param combo的选项
	 */
	public ComboAdaptor(String label,int controlStyle,ImporveControlWithDitryStateContext context,String[] items) {
		super(label,controlStyle,context);
		initCombo(items);
	}
	
	
	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 * @param combo的选项
	 * @param 绑定模型
	 * @param 绑定字段名称
	 */
	public ComboAdaptor(String label,int controlStyle,ImporveControlWithDitryStateContext context,String[] items,Object model,String beanFieldName) {
		super(label,controlStyle,context,model,beanFieldName);
		initCombo(items);
	}
	
	private void initCombo(String[] items){
		this.items = items;
		for(String item:items){
			getControl().add(item);
		}
		syncControl();
	}

	@Override
	public Object getValue() {
		return this.control.getText();
	}
	
	@Override
	public void setValue(Object object) {
		if(object instanceof String){
			 this.control.setText((String)object);
		}
	}
	
	@Override
	protected Combo initControl() {
		Combo combo = new Combo(parent, controlStyle);
		if (null != this.toolkit) {
			combo.setBackground(toolkit.getColors().getBackground());
		}
		return combo;
	}

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
	
	@Override
	public void removeModifyListener() {
		if (null != this.control) {
			this.control.removeSelectionListener(selectionAdapter);
		}
	}

	@Override
	protected void addMouseListener() {
	}
	
	public void setItems(String[] items) {
		this.items = items;
		getControl().removeAll();
		for(String item:items){
			getControl().add(item);
		}
	}
}
