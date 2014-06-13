/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.ui.assist.IAssistantProvider;
import com.hundsun.ares.studio.ui.assist.TextContentAssistEx;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

/**
 * 智能提示扩展文本框。
 * 
 * @author mawb
 */
public class TextAssistAdaptorEx extends TextAdaptor {
	
	/**
	 * @param label 显示字段
	 * @param controlStyle 控件样式
	 * @param context 上下文
	 * @param provider 智能提示提供器
	 */
	public TextAssistAdaptorEx(String label, int controlStyle, ImporveControlWithDitryStateContext context, IAssistantProvider provider) {
		super(label, controlStyle, context);
		((TextContentAssistEx)getControl()).setContentProvider(provider);
	}
	
	/**
	 * @param label 显示字段
	 * @param controlStyle 控件样式
	 * @param context 上下文
	 * @param separator 文本分隔符（文本框中有多个提示项时） 
	 * @param provider 智能提示提供器
	 */
	public TextAssistAdaptorEx(String label, int controlStyle, ImporveControlWithDitryStateContext context, String separator, IAssistantProvider provider) {
		this(label, controlStyle, context, provider);
		((TextContentAssistEx)getControl()).setContentSeparator(separator);
	}
	
	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 * @param 智能提示提供器
	 * @param 绑定字段名称
	 */
	public TextAssistAdaptorEx(String label, int controlStyle, ImporveControlWithDitryStateContext context, IAssistantProvider provider, String beanFieldName) {
		super(label,controlStyle,context,beanFieldName);
		((TextContentAssistEx)getControl()).setContentProvider(provider);
	}
	
	/**
	 * @param label 显示字段
	 * @param controlStyle 控件样式
	 * @param context 上下文
	 * @param separator 文本分隔符（文本框中有多个提示项时） 
	 * @param provider 智能提示提供器
	 * @param beanFieldName 绑定字段名称
	 */
	public TextAssistAdaptorEx(String label, int controlStyle, ImporveControlWithDitryStateContext context, String separator, IAssistantProvider provider, String beanFieldName) {
		this(label, controlStyle, context, provider, beanFieldName);
		((TextContentAssistEx)getControl()).setContentSeparator(separator);
	}
	
	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 * @param 智能提示提供器
	 * @param 绑定模型
	 * @param 绑定字段名称
	 */
	public TextAssistAdaptorEx(String label, int controlStyle, ImporveControlWithDitryStateContext context, IAssistantProvider provider, Object model, String beanFieldName) {
		super(label,controlStyle,context,model,beanFieldName);
		((TextContentAssistEx)getControl()).setContentProvider(provider);
	}
	
	/**
	 * @param label 显示字段
	 * @param controlStyle 控件样式
	 * @param context 上下文
	 * @param separator 文本分隔符（文本框中有多个提示项时）
	 * @param provider 智能提示提供器
	 * @param model 绑定模型
	 * @param beanFieldName 绑定字段名称
	 */
	public TextAssistAdaptorEx(String label, int controlStyle, ImporveControlWithDitryStateContext context, String separator, IAssistantProvider provider, Object model, String beanFieldName) {
		this(label, controlStyle, context, provider, model, beanFieldName);
		((TextContentAssistEx)getControl()).setContentSeparator(separator);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.control.TextAdaptor#initControl()
	 */
	@Override
	protected Text initControl() {
		text = new TextContentAssistEx(parent, controlStyle|SWT.BORDER);
		format = new FormattedText(text);
		return text;
	}

}
