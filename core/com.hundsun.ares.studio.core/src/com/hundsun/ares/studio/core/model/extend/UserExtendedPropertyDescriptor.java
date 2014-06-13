/**
 * 源程序名称：UserExtendedPropertyDescriptor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model.extend;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;

/**
 * 
 * @author sundl
 */
public class UserExtendedPropertyDescriptor extends BasicExtendPropertyDescriptor {
	
	private String key;
	private UserExtendedPropertyTypes type;
	
	public UserExtendedPropertyDescriptor(String key, UserExtendedPropertyTypes type) {
		this.key = key;
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor#getValue(java.lang.Object)
	 */
	@Override
	public String getValue(ExtensibleModel model) {
		UserExtensibleProperty userProperties = (UserExtensibleProperty) model.getData2().get(Constants.USER_DATA2_KEY);
		return userProperties == null ? null : userProperties.getMap().get(key);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor#setValue(java.lang.Object, java.lang.String)
	 */
	@Override
	public void setValue(ExtensibleModel model, String value) {
		if (type == UserExtendedPropertyTypes.BOOLEAN) {
			if (StringUtils.equalsIgnoreCase(value, "Y")
					|| StringUtils.equals(value, "true")) {
				value = "true";
			} else {
				value = "false";
			}
		}
		
		UserExtensibleProperty userProperties = (UserExtensibleProperty) model.getData2().get(Constants.USER_DATA2_KEY);
		if (userProperties != null) 
			userProperties.getMap().put(key, value);
		else {
			UserExtensibleProperty extendProperty = CoreFactory.eINSTANCE.createUserExtensibleProperty();
			extendProperty.getMap().put(key, value);
			model.getData2().put(Constants.USER_DATA2_KEY, extendProperty);
		}
	}

}
