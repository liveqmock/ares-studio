/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.oracle.exception;

import com.hundsun.ares.studio.engin.exception.HSException;

/**
 * @author liaogc
 *
 */
public class TableFieldNotFoundInStdFieldException extends HSException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7508646265450259951L;
	
	private static final String MESSAGE = "请设置表%1$s中的%2$s字段对应的标准字段";// 异常提示信息
	
	public TableFieldNotFoundInStdFieldException(String tableName,String field) {
		super(String.format(MESSAGE, tableName,field));
	}


	

}
