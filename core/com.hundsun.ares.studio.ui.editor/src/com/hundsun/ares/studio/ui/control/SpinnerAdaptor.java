/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Spinner;

import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

/**
 * 微调按钮适配器
 * 
 * @author mawb
 */
public class SpinnerAdaptor extends ControlWithShowControl<Spinner> {
	
	ModifyListener modifyListener;
	
	private Boolean isModify = false; 
	
	/**
	 * 微调按钮构造函数
	 * 
	 * @param label 显示字段
	 * @param controlStyle 控件样式
	 * @param context 上下文环境
	 * @param mininum 最小值
	 * @param maxinum 最大值
	 */
	public SpinnerAdaptor(String label, int controlStyle, ImporveControlWithDitryStateContext context, int mininum, int maxinum) {
		super(label, controlStyle, context);
		this.controlStyle = controlStyle;
		initControl(mininum, maxinum, 1);
	}
	
	/**
	 * 微调按钮构造函数
	 * 
	 * @param label 显示字段
	 * @param controlStyle 控件样式
	 * @param context 上下文环境
	 * @param mininum 最小值
	 * @param maxinum 最大值
	 * @param beanFieldName 绑定字段名称
	 */
	public SpinnerAdaptor(String label, int controlStyle, ImporveControlWithDitryStateContext context, int mininum, int maxinum, String beanFieldName) {
		super(label, controlStyle, context, context.getInfo(), beanFieldName);
		this.controlStyle = controlStyle;
		initControl(mininum, maxinum, 1);
	}
	
	/**
	 * 微调按钮构造函数
	 * 
	 * @param label 显示字段
	 * @param controlStyle 控件样式
	 * @param context 上下文环境
	 * @param mininum 最小值
	 * @param maxinum 最大值
	 * @param model 绑定模型
	 * @param beanFieldName 绑定字段名
	 */
	public SpinnerAdaptor(String label, int controlStyle, ImporveControlWithDitryStateContext context, int mininum, int maxinum, Object model, String beanFieldName) {
		super(label, controlStyle, context, model, beanFieldName);
		this.controlStyle = controlStyle;
		initControl(mininum, maxinum, 1);
	}
	
	/**
	 * 微调按钮构造函数
	 * 
	 * @param label 显示字段
	 * @param controlStyle 控件样式
	 * @param context 上下文环境
	 * @param mininum 最小值
	 * @param maxinum 最大值
	 * @param increment 增量值
	 */
	public SpinnerAdaptor(String label, int controlStyle, ImporveControlWithDitryStateContext context, int mininum, int maxinum, int increment) {
		super(label, controlStyle, context);
		this.controlStyle = controlStyle;
		initControl(mininum, maxinum, increment);
	}
	
	/**
	 * 微调按钮构造函数
	 * 
	 * @param label 显示字段
	 * @param controlStyle 控件样式
	 * @param context 上下文环境
	 * @param mininum 最小值
	 * @param maxinum 最大值
	 * @param increment 增量值
	 * @param beanFieldName 绑定字段名
	 */
	public SpinnerAdaptor(String label, int controlStyle, ImporveControlWithDitryStateContext context, int mininum, int maxinum, int increment, String beanFieldName) {
		super(label, controlStyle, context, context.getInfo(), beanFieldName);
		this.controlStyle = controlStyle;
		initControl(mininum, maxinum, increment);
	}
	
	/**
	 * 微调按钮构造函数
	 * 
	 * @param label 显示字段
	 * @param controlStyle 控件样式
	 * @param context 上下文环境
	 * @param mininum 最小值
	 * @param maxinum 最大值
	 * @param increment 增量值
	 * @param model 绑定模型
	 * @param beanFieldName 绑定字段名称
	 */
	public SpinnerAdaptor(String label, int controlStyle, ImporveControlWithDitryStateContext context, int mininum, int maxinum, int increment, Object model, String beanFieldName) {
		super(label, controlStyle, context, model, beanFieldName);
		this.controlStyle = controlStyle;
		initControl(mininum, maxinum, increment);
	}
	
	/**
	 * 初始化微调按钮参数
	 * 
	 * @param mininum 最小值
	 * @param maxinum 最大值
	 * @param increment 增量值
	 */
	protected void initControl(int mininum, int maxinum, int increment) {
		synchronized (isModify) {
			isModify = false;
			getControl().setMinimum(mininum);
			getControl().setMaximum(maxinum);
			getControl().setIncrement(increment);
			syncControl();
			isModify = true;
		}
	}
	
	@Override
	public void addModifyListener() {
		if(modifyListener == null){
			modifyListener = new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					fireControlValueChange(isModify);
				}
			};
		}
		control.addModifyListener(modifyListener);
	}
	
	/**
	 * 触发控件值变化事件
	 * 
	 * @param isModify Spinner设置最大最小值时会触发ModifyListener，此处的isModify用来控制是否触发该事件
	 */
	protected void fireControlValueChange(boolean isModify) {
		if (isModify) {
			super.fireControlValueChange();
		}
	}

	@Override
	protected void addMouseListener() {
	}

	@Override
	public Object getValue() {
		return control.getSelection();
	}
	
	@Override
	public void setValue(Object object) {
		if (object instanceof Integer) {
			control.setSelection((Integer) object);
		}
	}

	@Override
	protected Spinner initControl() {
		Spinner spinner = new Spinner(parent, controlStyle);
		if (toolkit != null) {
			spinner.setBackground(toolkit.getColors().getBackground());
		}
		return spinner;
	}

	@Override
	public void removeModifyListener() {
		if (control != null) {
			control.removeModifyListener(modifyListener);
		}
	}

}
