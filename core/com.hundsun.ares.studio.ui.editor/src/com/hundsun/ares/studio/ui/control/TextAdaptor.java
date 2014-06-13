/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control;


import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.ITextFormatter;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;



/**
 * 标签-文本控件组。
 * 
 * @author liuning. Created on 2008-9-18. Modified by xx on xx.
 * 
 */
public class TextAdaptor extends ControlWithShowControl<Text> {

	protected Text text;
	
	protected FormattedText format;
	ModifyListener modifyListener;

	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 */
	public TextAdaptor(String label,int controlStyle,ImporveControlWithDitryStateContext context) {
		super(label,controlStyle, context);
		this.controlStyle = controlStyle;
	}
	
	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 * @param 绑定字段名称
	 */
	public TextAdaptor(String label,int controlStyle,ImporveControlWithDitryStateContext context,String beanFieldName) {
		super(label,controlStyle, context,context.getInfo(),beanFieldName);
		this.controlStyle = controlStyle;
	}
	
	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 * @param 绑定模型
	 * @param 绑定字段名称
	 */
	public TextAdaptor(String label,int controlStyle,ImporveControlWithDitryStateContext context,Object model,String beanFieldName) {
		super(label,controlStyle, context,model,beanFieldName);
		this.controlStyle = controlStyle;
	}

	@Override
	public String getValue() {
		return this.control.getText();
	}
	
	@Override
	public void setValue(Object object) {
		//if(object instanceof String){
		//	this.control.setText((String)object);
		//}
		this.control.setText(object.toString());//竹一峰于2010-4-2，测试数字型文本输入时做以上修改
	}

	@Override
	protected Text initControl() {
		if (null != this.toolkit) {
			text = toolkit.createText(parent, "", controlStyle);
		} else {
			text = new Text(parent, controlStyle);
		}
		format = new FormattedText(text);
		return text;
	}

	@Override
	public void addModifyListener() {
		if (null != this.control) {
			if(modifyListener == null){
				modifyListener = new ModifyListener() {
					public void modifyText(ModifyEvent e) {
						fireControlValueChange();
					}
				};
			}
			this.control.addModifyListener(modifyListener);
		}
	}
	
	@Override
	public void removeModifyListener() {
		if (null != this.control) {
			this.control.removeModifyListener(modifyListener);
		}
	}

	@Override
	protected void addMouseListener() {
	}
	
	public void setFormatter(ITextFormatter formatter) {
		removeModifyListener();
		format.setFormatter(formatter);
		addModifyListener();
	}
}
