/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util;

import org.eclipse.emf.ecore.EObject;

/**
 * @author lvgao
 *
 */
public interface IKeyTableLocator {

	/**
	 * 获取关联对象
	 * @param obj
	 * @return
	 */
	public EObject getLinkObject(EObject obj) throws Exception;
	
	
	/**
	 * 获取关联对象计数
	 * @param obj
	 * @return
	 */
	public int getLinkObjectCount(EObject obj)throws Exception;
	
	/**
	 * 重置
	 */
	public void reset();
	
	/**
	 * 数据是否准备好
	 * @return
	 */
	public boolean isReady();
}
