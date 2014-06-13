/**
 * 源程序名称：IReferenceParser.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model.util;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.model.Reference;

/**
 * 用于解析一个模型中一个字段或引用的引用信息的分析器<BR>
 * 分析器只被实例化一次，会被复用，所以不应该保存任何分析有关信息
 * @author gongyf
 *
 */
public interface IReferenceParser {
	
	/**
	 * 不要返回null，如果没有内容，请返回{@link Collections#emptyList()}
	 * @param object
	 * @param feature
	 * @param parameters
	 * @return
	 */
	List<Reference> analyse(EObject object, EStructuralFeature feature, String[] parameters);
}
