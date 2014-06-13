/**
 * 源程序名称：IExtensibleModelPropertyDescriptor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

/**
 * @author gongyf
 * @author sundl
 *
 */
public interface IExtensibleModelPropertyDescriptor {
	
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
	 * 编辑的特性
	 * @return
	 */
	EStructuralFeature getStructuralFeature();
	
	/**
	 * 用于显示内容
	 * @return
	 */
	ILabelProvider getLabelProvider();
	
	/**
	 * 用于编辑内容，可以返回null，表示不可编辑
	 * @param parent
	 * @return
	 */
	CellEditor createPropertyEditor(Composite parent);
	
	/**
	 * 是否是派生属性，派生属性并不一定对应一个对象中的属性
	 * @return
	 */
	boolean isDerived();
}
