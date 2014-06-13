/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.engin.exception;

/**
 * 异常类的基类
 * 
 * @author zhuyf
 * 
 */
public class HSException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1215302580689587751L;
	
	/**
	 * 异常提示信息
	 */
	private String errorInfo;

	public HSException(String errorInfo) {
		super(errorInfo);
		this.errorInfo = errorInfo;
	}
	
	public String getErrorInfo()
	{
		return errorInfo;
	}
	
	public void printErrorInfo()
	{
		System.out.println(getErrorInfo());
	}
}
