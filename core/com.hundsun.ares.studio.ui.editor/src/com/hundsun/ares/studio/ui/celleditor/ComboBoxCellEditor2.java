/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.celleditor;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;

/**
 * 扩展ComboBoxCellEditor功能，主要应用于下拉列表显示值和真实值不同的情况。
 * 
 * @author mawb
 */
public class ComboBoxCellEditor2 extends ComboBoxCellEditor {
	
	/**
	 * <真实值，显示值>键值表。
	 */
	private Map<String, String> items;
	
	/**
	 * @param parent 父容器
	 * @param items <真实值，显示值>键值表
	 * @param style 单元格样式
	 */
	public ComboBoxCellEditor2(Composite parent, Map<String, String> items, int style) {
		super(parent, items.values().toArray(new String[items.size()]), style);
		this.items = items;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.celleditor.ComboBoxCellEditor#getRealGetValue(java.lang.Object)
	 */
	@Override
	public Object getRealGetValue(Object value) {
		if (value instanceof String) {
			value = getDisplayValue((String) value);
		}
		return super.getRealGetValue(value);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.celleditor.ComboBoxCellEditor#getRealSetValue(java.lang.Object)
	 */
	@Override
	public Object getRealSetValue(Object value) {
		value = super.getRealSetValue(value);
		if (value instanceof String) {
			return getRealValue((String) value);
		}
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.celleditor.ComboBoxCellEditor#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object value) {
		if (value instanceof String) {
			return getDisplayValue((String) value);
		}
		return super.getText(value);
	}
	
	/**
	 * 根据下拉列表的显示值获取真实值。
	 * 
	 * @param comboValues
	 * @param displayValue
	 * @return
	 */
	private String getRealValue(String displayValue) {
		for (String realValue : items.keySet()) {
			if (displayValue.equals(items.get(realValue))) {
				return realValue;
			}
		}
		return displayValue;
	}
	
	/**
	 * 根据下拉列表的真实值获取显示值。
	 * 
	 * @param comboValues
	 * @param displayValue
	 * @return
	 */
	private String getDisplayValue(String realValue) {
		String displayValue = items.get(realValue);
		if (displayValue != null) {
			return displayValue;
		}
		return realValue;
	}

}
