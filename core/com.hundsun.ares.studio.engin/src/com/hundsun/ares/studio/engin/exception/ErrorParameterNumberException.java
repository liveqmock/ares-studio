/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.engin.exception;

import java.text.MessageFormat;

/**
 * 宏参数个数错误抛出的异常
 * 
 * @author zhuyf
 * 
 */
public class ErrorParameterNumberException extends HSException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5376473981367851674L;

	private static final String WHATSAYWHENEXCEPTION = "[{0}]缺少第{1}个参数：{2}，出现位置：{3}中第{4}行";// 异常抛出提示信息

	public ErrorParameterNumberException(String macroName, int indexParam, String paramDesc, String functionName, int rowNum) {
		super(MessageFormat.format(ErrorParameterNumberException.WHATSAYWHENEXCEPTION, new String[] { macroName,
				new Integer(indexParam).toString(), paramDesc, functionName, new Integer(rowNum + 1).toString() }));
	}
	
	public ErrorParameterNumberException(String macroName, String para, int indexParam, String paramDesc) {
		super(MessageFormat.format(ErrorParameterNumberException.ERROR_MSG,new String[] {macroName,
				new Integer(indexParam).toString(),para,paramDesc}));
	}

	private static final String ERROR_MSG = "[{0}]第{1}个参数[{2}]输入错误。格式应为{3}";// 异常抛出提示信息
	

}
