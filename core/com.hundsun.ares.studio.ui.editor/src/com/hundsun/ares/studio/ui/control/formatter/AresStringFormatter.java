/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.control.formatter;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.nebula.widgets.formattedtext.StringFormatter;
import org.eclipse.swt.events.VerifyEvent;

import com.hundsun.ares.studio.core.model.ICreateInstance;

public class AresStringFormatter extends StringFormatter implements ICreateInstance<AresStringFormatter>, Cloneable{
	
	/**
	 * 字符串最大长度
	 */
	int maxLen = Integer.MAX_VALUE;
	/**
	 * 输入字符最小ASC码
	 */
	int minAsc = 0;
	/**
	 * 输入字符最大ASC码
	 */
	int maxAsc = Integer.MAX_VALUE;
//	/**
//	 * 输入字符串限制正则表达式
//	 */
//	String expression = null;
	/**
	 * 排除字符
	 */
	Set<Character> exclude = new HashSet<Character>();

	
	
	
	public int getMaxLen() {
		return maxLen;
	}

	public void setMaxLen(int maxLen) {
		this.maxLen = maxLen;
	}

	public int getMinAsc() {
		return minAsc;
	}

	public void setMinAsc(int minAsc) {
		this.minAsc = minAsc;
	}

	public int getMaxAsc() {
		return maxAsc;
	}

	public void setMaxAsc(int maxAsc) {
		this.maxAsc = maxAsc;
	}

//	public String getExpression() {
//		return expression;
//	}
//
//	public void setExpression(String expression) {
//		this.expression = expression;
//	}

	public void addExclude(char[] ch) {
		for(char c:ch){
			exclude.add(c);
		}
	}

	@Override
	public void verifyText(VerifyEvent e) {
		String newString = text.getText();
		newString = newString.substring(0,e.start)+ e.text+ newString.substring(e.end,newString.length());
		//检查最大长度
		if(newString.length() > maxLen){
			e.doit = false;
			beep();
			return;
		}
		//检查是否在ASC码范围内        检查排除字符
		for(char c:e.text.toCharArray()){
			if(c < minAsc || c > maxAsc || exclude.contains(c)){
				e.doit = false;
				beep();
				return;
			}
		}
//		//检查正则表达式
//		if(!newString.matches(expression)){
//			e.doit = false;
//			beep();
//			return;
//		}
	}

	public AresStringFormatter getNewInstance() {
		try {
			return (AresStringFormatter) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return new AresStringFormatter();
	}
}
