/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.internal.useroption;

/**
 * @author lvgao
 *
 */
public interface IControlContainer {

	public  void addChildren(IControl control);
	
	/**
	 * 获取子控件
	 * @return
	 */
	public IControl[]  getChildren();
}
