/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.control;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

public class TextAssistAdaptor extends TextAdaptor {
	
	String[] items;
	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 */
	public TextAssistAdaptor(String label,int controlStyle,ImporveControlWithDitryStateContext context,String[] items) {
		super(label,controlStyle,context);
		this.items = items;
		((TextContentAssist)getControl()).setProposals(items);
	}
	
	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 * @param 绑定字段名称
	 */
	public TextAssistAdaptor(String label,int controlStyle,ImporveControlWithDitryStateContext context,String[] items,String beanFieldName) {
		super(label,controlStyle,context,beanFieldName);
		this.items = items;
		((TextContentAssist)getControl()).setProposals(items);
	}
	
	/**
	 * @param 显示字段
	 * @param 控件样式
	 * @param 上下文
	 * @param 绑定模型
	 * @param 绑定字段名称
	 */
	public TextAssistAdaptor(String label,int controlStyle,ImporveControlWithDitryStateContext context,String[] items,Object model,String beanFieldName) {
		super(label,controlStyle,context,model,beanFieldName);
		this.items = items;
		((TextContentAssist)getControl()).setProposals(items);
	}
	
	@Override
	protected Text initControl() {
		text = new TextContentAssist(parent, controlStyle|SWT.BORDER,new String[]{});
		format = new FormattedText(text);
		return text;
	}
	
	
}
