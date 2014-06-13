/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.exception;

import com.hundsun.ares.studio.engin.exception.HSException;

/**
 * @author liaogc
 *
 */
public class TableFieldNotSettingException extends HSException{
		

	
	private static final long serialVersionUID = 4001788659826605526L;
		private static final String MESSAGE = "表：%1$s还没有设置字段";// 异常提示信息
		
		public TableFieldNotSettingException(String message) {
			super(String.format(MESSAGE, message));
		}
}
