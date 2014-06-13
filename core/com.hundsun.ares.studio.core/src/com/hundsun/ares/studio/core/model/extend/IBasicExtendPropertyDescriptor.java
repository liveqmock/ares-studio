/**
 * 源程序名称：IExtendPropertyDescriptor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model.extend;

import com.hundsun.ares.studio.core.model.ExtensibleModel;


/**
 * 扩展属性描述
 * @author sundl
 */
public interface IBasicExtendPropertyDescriptor {

	/**
	 * <ul>
	 * <li>分类信息，字符串，显示用； </li>
	 * <li>多个属于同一个分类下的属性显示的时候会显示到同一个分类下</li>
	 * <li>如果这里不提供分类信息(空串或者null)，就会按照提供这个Descriptor的EditingSupport进行分组显示</li>
	 * </ul>
	 * @return
	 */
	String getCategory();
	
	/**
	 * 显示的名称，可能用于列名或者行头
	 * @return
	 */
	String getDisplayName();
	
	/**
	 * 描述信息
	 * @return
	 */
	String getDescription();
	
	/**
	 * getter, setter都用字符串的形式以通用化，实现者需要自行在实现的时候进行类型转换
	 * @return
	 */
	String getValue(ExtensibleModel model);
	void setValue(ExtensibleModel model, String value);
	
}
