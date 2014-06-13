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
public class FieldDefVauleNotFindException extends HSException {

	private static final long serialVersionUID = 4148689458686019952L;
	private static final String MESSAGE = "标准字段：{%1$s}的默认值无法得到";// 异常提示信息

	public FieldDefVauleNotFindException(String fieldName) {
		super(String.format(MESSAGE, fieldName));
	}

}
