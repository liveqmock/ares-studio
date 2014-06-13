/**
 * 源程序名称：MetadataReferenceParser.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.util;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.core.model.util.IReferenceParser;

/**
 * @author gongyf
 *
 */
public class MetadataReferenceParser implements IReferenceParser {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.util.IReferenceParser#analyse(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.String[])
	 */
	@Override
	public List<Reference> analyse(EObject object, EStructuralFeature feature,
			String[] parameters) {
		if (parameters != null && parameters.length == 1) {
			if (feature instanceof EAttribute) {
				if (feature.getEType() == EcorePackage.Literals.ESTRING) {
					return Collections.singletonList(
							(Reference)new TextAttributeReferenceWithNamespaceImpl(
									parameters[0], object, (EAttribute)feature)) ;
				} else {
					System.out.println("解析引用时指定的属性不是文本类型");
				}
				
			} else {
				System.out.println("解析引用时指定的特性不是属性");
			}
			
		} else {
			System.out.println("解析引用时参数数目不正确");
		}
		return Collections.emptyList();
	}

}
