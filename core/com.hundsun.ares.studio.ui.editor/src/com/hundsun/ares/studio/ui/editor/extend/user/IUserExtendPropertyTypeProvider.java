/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.extend.user;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;

/**
 * 用户扩展属性类型扩展点接口
 * @author sundl
 */
public interface IUserExtendPropertyTypeProvider {

	/**
	 * 创建一个IExtensibleModelPropertyDescriptor对象，用来表示一种用户扩展的类型
	 * @param config 用户配置xml里的属性map, 比如 <Attribute id="customer" name="客户个性化" type="string" />，这里就会把这些属性放到这个map中
	 * @return
	 */
	IExtensibleModelPropertyDescriptor createPropertyDescriptor(IARESProject project, Map<String, String> config);
}
