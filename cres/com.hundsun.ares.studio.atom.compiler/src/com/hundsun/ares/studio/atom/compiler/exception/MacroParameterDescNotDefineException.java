/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.exception;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.exception.HSException;

/**
 * @author liaogc
 *
 */
public class MacroParameterDescNotDefineException extends HSException{
		


	private static final long serialVersionUID = 5049629016231976830L;
		private static final String MESSAGE = "[%1$s]的第%2$s个参数说明信息没有配置，错误位置：%3$s中的第%4$s行";// 异常提示信息
		
		public MacroParameterDescNotDefineException(String macroName, int indexParam, String functionName, int rowNum) {
			super(String.format(MacroParameterDescNotDefineException.MESSAGE, macroName,StringUtils.EMPTY+indexParam,functionName,StringUtils.EMPTY+rowNum));
		}
}
