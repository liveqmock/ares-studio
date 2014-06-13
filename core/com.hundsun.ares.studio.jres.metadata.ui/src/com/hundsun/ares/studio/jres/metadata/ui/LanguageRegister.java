/**
 * 源程序名称：LanguageRegister.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

/**
 * @author gongyf
 *
 */
public class LanguageRegister {
	
	private Language[] languages;
	
	private static LanguageRegister instance;
	
	private LanguageRegister() {
		
	}
	
	public static LanguageRegister getInstance() {
		if (instance == null) {
			instance = new LanguageRegister();
		}
		return instance;
	}
	
	
	public Language[] getRegisteredLanguages() {
		/*
		 * DESIGN#元数据扩展#龚叶峰#普通#秦元#读取语言扩展点信息
		 *
		 * 从扩展点读取目前注册的语言类型，本信息需要进行缓存
		 * 扩展点内容不会动态变化
		 */
		if (languages == null) {
			IExtensionPoint extension = Platform.getExtensionRegistry().getExtensionPoint(ExtensionPointConst.Languages.NAME);
			if (extension != null) {
				List<Language> languageList = new ArrayList<Language>();
				
				// 读取所有子节点
				IConfigurationElement[] elements = extension.getConfigurationElements();
				if (elements != null) {
					for (IConfigurationElement element : elements) {
						if (ExtensionPointConst.Languages.Language.NAME.equals(element.getName())) {
							Language l = new Language(
									element.getAttribute(ExtensionPointConst.Languages.Language.ATTR_ID), 
									element.getAttribute(ExtensionPointConst.Languages.Language.ATTR_NAME));
							
							languageList.add(l);
							
						}
					}
				}
				languages = languageList.toArray(new Language[languageList.size()]);
			}
			
			
		}
		return languages;
	}
	
	public Language getLanguageByName(String name) {
		for (Language lang : getRegisteredLanguages()) {
			if (StringUtils.equals(lang.getName(), name))
				return lang;
		}
		return null;
	}
}
