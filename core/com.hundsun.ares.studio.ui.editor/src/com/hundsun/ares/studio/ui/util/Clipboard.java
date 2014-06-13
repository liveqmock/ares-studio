/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.util;

/**
 * 作为系统的剪贴板
 * 
 * @author gongyf
 *
 */
public class Clipboard {
	
	final public static Clipboard instance = new Clipboard();
	private Object data;
	
	private Clipboard() {
		
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
}
