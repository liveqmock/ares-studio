/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.exception;

import com.hundsun.ares.studio.engin.exception.HSException;

/**
 * @author liaogc
 *
 */
public class MacroNotInProcBlockException extends HSException{
		

	private static final long serialVersionUID = 788181414949547427L;
		private static final String MESSAGE = "不能在PROC块以外使用宏%1$s";// 异常提示信息
		
		public MacroNotInProcBlockException(String message) {
			super(String.format(MESSAGE, message));
		}

}
