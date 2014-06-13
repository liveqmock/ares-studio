/**
 * 源程序名称：UserExtendedPropertyUtils.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model.extend;

import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;

/**
 * 用户属性相关的工具类
 * @author sundl
 */
public class UserExtendedPropertyUtils {

	public static String getUserExtendedProperty(ExtensibleModel model, String key) {
		UserExtensibleProperty userProperties = (UserExtensibleProperty) model.getData2().get(Constants.USER_DATA2_KEY);
		return userProperties == null ? null : userProperties.getMap().get(key);
	}
	
}
