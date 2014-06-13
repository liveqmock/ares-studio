/**
 * 源程序名称：IExtensibleModelEditingSupport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * 扩展模型的编辑支持，需要保证是无状态的，只会被实例化一次并被多次使用
 * @author gongyf
 * 
 */
public interface IExtensibleModelEditingSupport {
	
	/**
	 * 判定此扩展是否适用于指定资源的编辑
	 * 
	 * 
	 * @return boolean
	 */
	boolean isEnable(IARESElement aresElement, EClass eClass);

	/**
	 * 获取名称,可用于扩展属性的分组
	 * @return
	 */
	String getName();
	
	/**
	 * 在map中的key
	 * @return
	 */
	String getKey();
	
	/**
	 * 创建一个用于编辑的对象，这个对象将存储在{@link ExtensibleModel#getData2()}的map中
	 * @return
	 */
	EObject createMapValueObject();
	
	/**
	 * 返回可以编辑的属性描述
	 * @return
	 */
	IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(IARESElement aresElement, EClass eClass);
}
