/**
 * 源程序名称：ResourceTypeMapping.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author gongyf
 *
 */
public class ResourceTypeMapping {

	private static final String EP_NAME = "Res2RefTypeMapping";
	private final static String EP_ATTRIBUTE_ResType = "resType";
	private final static String EP_ATTRIBUTE_RefType = "refType"; 
	
	private Logger logger = Logger.getLogger(getClass());
	static ResourceTypeMapping instance;
	/**
	 * @return the instance
	 */
	public static ResourceTypeMapping getInstance() {
		if (instance == null) {
			instance = new ResourceTypeMapping();
		}
		return instance;
	}
	
	BiMap<String, String> typeMapping;
	
	private ResourceTypeMapping () {
		typeMapping = HashBiMap.create();
		
		logger.info("开始收集资源类型名和引用类型名之间的转换映射");
		
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(ARESCore.PLUGIN_ID , EP_NAME);
		
		for (IConfigurationElement element : elements) {
			try {
				Bundle bundle = ContributorFactoryOSGi.resolve(element.getContributor());
				Class<?> resTypeClass = bundle.loadClass(element.getAttribute(EP_ATTRIBUTE_ResType));
				Class<?> refTypeClass = bundle.loadClass(element.getAttribute(EP_ATTRIBUTE_RefType));
				
				for (Field field : resTypeClass.getFields()) {
					try {
						// 获取资源类型和引用类型
						String resType = (String)field.get(null);
						Field refField = refTypeClass.getField(field.getName());
						String refType = (String)refField.get(null);
						
						if (StringUtils.isNotBlank(resType) && StringUtils.isNotBlank(refType)) {
							typeMapping.put(resType, refType);
						}
					} catch (NoSuchFieldException e) {
						logger.error(String.format("Class: %s 没有定义属性：%s", refTypeClass.getName(), field.getName()), e);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		logger.info("结束收集资源类型名和引用类型名之间的转换映射");
//		//元数据
//		typeMapping.put(IResourceType.DefValue, IJRESReferenceType.DefValue);
//		typeMapping.put(IResourceType.StdType, IJRESReferenceType.StdType);
//		typeMapping.put(IResourceType.BizType, IJRESReferenceType.BizType);
//		typeMapping.put(IResourceType.StdField, IJRESReferenceType.StdField);
//		typeMapping.put(IResourceType.Dict, IJRESReferenceType.Dict);
//		typeMapping.put(IResourceType.ErrNo, IJRESReferenceType.ErrNo);
//		typeMapping.put(IResourceType.Const, IJRESReferenceType.Const);
//		//业务逻辑
//		typeMapping.put(IResourceType.Service, IJRESReferenceType.Service);
//		typeMapping.put(IResourceType.Subflow, IJRESReferenceType.Subflow);
//		typeMapping.put(IResourceType.VirSubflow, IJRESReferenceType.VirSubflow);
//		typeMapping.put(IResourceType.Component, IJRESReferenceType.Component);
//		typeMapping.put(IResourceType.Procedure, IJRESReferenceType.Procedure);
//		typeMapping.put(IResourceType.Object, IJRESReferenceType.Object);
//		typeMapping.put(IResourceType.Test, IJRESReferenceType.Test);
//		//数据库
//		typeMapping.put(IResourceType.Table, IJRESReferenceType.Table);
//		typeMapping.put(IResourceType.TableField, IJRESReferenceType.TableField);
//		typeMapping.put(IResourceType.View, IJRESReferenceType.View);
//		//UI
//		typeMapping.put(IResourceType.cw, IJRESReferenceType.cw);
//		typeMapping.put(IResourceType.pw, IJRESReferenceType.pw);
//		typeMapping.put(IResourceType.mw, IJRESReferenceType.mw);
		
	}
	


	public String getReferenceType(String resType) {
		return typeMapping.get(resType);
	}
	
	public String getResourceType(String refType) {
		return typeMapping.inverse().get(refType);
	}
}
