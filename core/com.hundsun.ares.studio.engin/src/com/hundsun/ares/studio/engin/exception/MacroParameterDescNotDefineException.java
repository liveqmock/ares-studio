/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.engin.exception;

import java.text.MessageFormat;

/**
 * 抛出参数说明信息没有配置的异常
 * 
 * @author zhuyf
 * 
 */
public class MacroParameterDescNotDefineException extends HSException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9005560618098957090L;

	private static final String WHATSAYWHENEXCEPTION = "[{0}]的第{1}个参数说明信息没有配置，错误位置：{2}中的第{3}行";// 异常提示信息

	public MacroParameterDescNotDefineException(String macroName, int indexParam, String functionName, int rowNum) {
		super(MessageFormat.format(MacroParameterDescNotDefineException.WHATSAYWHENEXCEPTION, new String[] { macroName,
				new Integer(indexParam).toString(), functionName, new Integer(rowNum).toString() }));
	}

}
