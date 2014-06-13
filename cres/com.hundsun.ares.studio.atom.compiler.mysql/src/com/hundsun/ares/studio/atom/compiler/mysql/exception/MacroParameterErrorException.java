/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.exception;

import com.hundsun.ares.studio.engin.exception.HSException;

/**
 * @author zhuyf
 *
 */
public class MacroParameterErrorException extends HSException {

	
	private static final String MESSAGE = "宏[%1$s]中，SQL错误：[%2$s]，错误原因分析：[%3$s]。";
	/**
	 * @param errorInfo
	 */
	public MacroParameterErrorException(String macroName,String SQL ,String reason) {
		super(String.format(MESSAGE, macroName,SQL,reason));
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8637123673513891276L;
	
	

}
