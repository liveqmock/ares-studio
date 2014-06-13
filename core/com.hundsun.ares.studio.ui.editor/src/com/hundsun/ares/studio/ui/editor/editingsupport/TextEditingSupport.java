/**
 * 源程序名称：TextEditingSupport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.editingsupport;

import org.apache.commons.lang.ObjectUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

public class TextEditingSupport extends EMFEditingSupport {

	/**
	 * @param viewer
	 * @param feature
	 */
	public TextEditingSupport(ColumnViewer viewer, EStructuralFeature feature) {
		super(viewer, feature);
	}

	/**
	 * @param viewer
	 * @param featureProvider
	 */
	public TextEditingSupport(ColumnViewer viewer,
			IEStructuralFeatureProvider featureProvider) {
		super(viewer, featureProvider);
	}

	@Override
	protected CellEditor createCellEditor() {
		return new TextCellEditor((Composite) getViewer().getControl());
	}
	
	@Override
	protected Object getValue(Object element) {
		// 防止null设置到Text中去
		return ObjectUtils.defaultIfNull(super.getValue(element), "");
	}

}
