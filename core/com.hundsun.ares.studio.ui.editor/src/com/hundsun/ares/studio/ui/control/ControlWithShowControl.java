/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Hyperlink;

import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;




/**
 * 带一个展示标签的控件
 * 展示标签可以是label也可以是超链接
 *
 * @author liuning.
 * Created on 2008-9-18.
 * Modified by xx on xx.
 */
public abstract class ControlWithShowControl<T extends Control> extends ControlWithUndoSupport<T> {
	
	
	/**
	 * 置于输入控件之前的Label控件，为输入控件提供说明。
	 */
	protected Control showControl;
	String label;

	/**
	 * 构造一个带Label的控件。
	 * @param toolkit 
	 * 
	 * @param parent 父组件。
	 * @param label 标签控件。
	 * @param labelStyle 标签控件的样式。
	 * @param dirtyStatus 输入型控件的脏状态对象，应传入编辑器脏状态对象。
	 * @param filtra 
	 * @param project 
	 */
	public ControlWithShowControl(String label, int controlStyle,ImporveControlWithDitryStateContext context) {
		super(context, controlStyle);
		this.label = label;
		initLabel();
	}
	
	public ControlWithShowControl(String label, int controlStyle,ImporveControlWithDitryStateContext context,String beanFieldName) {
		super(context, controlStyle,beanFieldName);
		this.label = label;
		initLabel();
	}
	
	public ControlWithShowControl(String label, int controlStyle,ImporveControlWithDitryStateContext context,Object model,String beanFieldName) {
		super(context, controlStyle,model,beanFieldName);
		this.label = label;
		initLabel();
	}
	
	public int GetLabelStyle(){
		return SWT.NONE;
	}
	
	protected void initLabel() {
		this.showControl = new Label(parent, GetLabelStyle());
		((Label)this.showControl).setText(StringUtil.getStringSafely(label));
		this.movePosition();
		this.setStyle();
		//调整控件布局
		adjustControl();
	}
	
	/**
	 * 设置组件布局
	 */
	protected void setStyle() {
		setLabelLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		setControlLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	}
	
	/**
	 * 互换输入控件与标签控件的位置。
	 */
	private void movePosition() {
		this.showControl.moveAbove(this.control);
	}

	/**
	 * @return the label
	 */
	public Control getShowControl() {
		return showControl;
	}

	/**
	 * @param label the label to set
	 */
	public void setShowControl(Control showControl) {
		this.showControl = showControl;
	}

	
	/**
	 * 为Label设置排版布局。
	 * 
	 * @param layoutData
	 */
	public void setLabelLayoutData(Object layoutData) {
		if (null != this.showControl) {
			this.showControl.setLayoutData(layoutData);
		}
	}
	
	/**
	 * 将表头控件转换为Hyperlink
	 * @return
	 */
	public Hyperlink switchHyperlink(){
		if(showControl instanceof Hyperlink){
			return (Hyperlink)showControl;
		}
		String text = "";
		if(showControl instanceof Label){
			text = ((Label)showControl).getText();
		}
		showControl.dispose();
		showControl = new Hyperlink(parent, GetLabelStyle());
		((Hyperlink)showControl).setUnderlined(true);
		((Hyperlink)showControl).setText(text);
		movePosition();
		return (Hyperlink)showControl;
	}
	
}
