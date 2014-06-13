/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.exception;

import com.hundsun.ares.studio.engin.exception.HSException;

/**
 * 表资源没有找到抛出的异常
 * 
 * @author liaogc
 *
 */
public class TableNotFoundException extends HSException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1638288612798107990L;

	private static final String MESSAGE = "表资源：%1$s不存在";// 异常提示信息


	/**
	 * @param tableName
	 */
	public TableNotFoundException( String tableName) {
		super(String.format(TableNotFoundException.MESSAGE, tableName));
		
	}

	

	

}

