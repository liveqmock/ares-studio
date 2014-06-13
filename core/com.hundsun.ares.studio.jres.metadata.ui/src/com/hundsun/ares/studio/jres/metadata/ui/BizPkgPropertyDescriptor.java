/**
 * 源程序名称：BizPkgPropertyDescriptor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.metadata.ui;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.ui.editor.extend.AbstractMapEMPropertyDescriptor;

/**
 * 用户属性扩展中的 “业务包”类型的扩展属性的类型描述
 * @author sundl
 *
 */
public class BizPkgPropertyDescriptor extends AbstractMapEMPropertyDescriptor{

	/**
	 * @param structuralFeature
	 * @param key
	 * @param extendModelKey
	 */
	public BizPkgPropertyDescriptor(EStructuralFeature structuralFeature, Object key, String extendModelKey) {
		super(structuralFeature, key, extendModelKey);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor#getLabelProvider()
	 */
	@Override
	public ILabelProvider getLabelProvider() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
