/**
 * 源程序名称：BaseEObjectColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.viewers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Font;

/**
 * @author gongyf
 *
 */
public class BaseEObjectColumnLabelProvider extends ColumnLabelProvider implements IColumnViewerTextCopySupport{

	private IEStructuralFeatureProvider attributeProvider;

	public BaseEObjectColumnLabelProvider(EStructuralFeature attribute) {
		this(new IEStructuralFeatureProvider.Impl(attribute));
		
	}
	
	public BaseEObjectColumnLabelProvider(IEStructuralFeatureProvider attributeProvider) {
		super();
		this.attributeProvider = attributeProvider;
	}

	public EStructuralFeature getEStructuralFeature(Object element) {
		return attributeProvider.getFeature(element);
	}

	/**
	 * @param attributeProvider the attributeProvider to set
	 */
	public void setAttributeProvider(IEStructuralFeatureProvider attributeProvider) {
		this.attributeProvider = attributeProvider;
	}

	@Override
	public String getText(Object element) {
		EStructuralFeature attribute = getEStructuralFeature(element);
		EObject owner = getOwner(element);
		if (attribute != null && owner != null && owner.eClass().getEAllAttributes().contains(attribute)) {
			Object value = owner.eGet(attribute);
			if (value == null) {
				value = "";
			}
			return String.valueOf(value );
		}
		return "";
	}

	/**
	 * 获取需要操作的EObject
	 * @param element
	 * @return
	 */
	protected EObject getOwner(Object element) {
		return (EObject) element;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.IColumnViewerTextCopySupport#getCopyText(java.lang.Object)
	 */
	@Override
	public String getCopyText(Object element) {
		return getText(element);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getFont(java.lang.Object)
	 */
	@Override
	public Font getFont(Object element) {
		return JFaceResources.getTextFont();
	}
}