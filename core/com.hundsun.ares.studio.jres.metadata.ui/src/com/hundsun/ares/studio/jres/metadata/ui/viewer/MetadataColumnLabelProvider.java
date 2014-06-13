/**
 * 源程序名称：MetadataColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.util.CircularReferenceException;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * 可以在引用状态下显示引用的数据值
 * @author gongyf
 *
 */
public class MetadataColumnLabelProvider extends EObjectColumnLabelProvider {

	private IARESResource resource;
	
	/**
	 * @param attribute
	 * @param resource
	 */
	public MetadataColumnLabelProvider(EAttribute attribute,
			IARESResource resource) {
		super(attribute);
		this.resource = resource;
	}

	/**
	 * @param attributeProvider
	 * @param resource
	 */
	public MetadataColumnLabelProvider(
			IEStructuralFeatureProvider attributeProvider,
			IARESResource resource) {
		super(attributeProvider);
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.EObjectColumnLabelProvider#getBackground(java.lang.Object)
	 */
	@Override
	public Color getBackground(Object element) {
//		EObject owner = getOwner(element);
//		if (owner != element) {
//			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
//		}
		if ( MetadataUtil.isUseRefFeature(resource) ) {
			if (element instanceof MetadataItem) {
				MetadataItem item = (MetadataItem)element;
				if (MetadataUtil.isReferencingItem(item) && MetadataUtil.isReferencableFeature(item, getEStructuralFeature(element))) {
					return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
				}
			}
		}
//		if (resource.isReadOnly()) {
//			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
//		}
		return super.getBackground(element);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.EObjectColumnLabelProvider#getDiagnostic(java.lang.Object)
	 */
	@Override
	protected Diagnostic getDiagnostic(Object element) {
		EObject owner = getOwner(element);
		// 引用不需要检查错误
		if (owner == element) {
			return super.getDiagnostic(element);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.EObjectColumnLabelProvider#getOwner(java.lang.Object)
	 */
	@Override
	protected EObject getOwner(Object element) {
		EObject owner = super.getOwner(element);
		if ( MetadataUtil.isUseRefFeature(resource) ) {
			// 如果项目启用了引用则处理
			
			if (owner instanceof MetadataItem) {
				// 对于引用的处理
				MetadataItem item = (MetadataItem)owner;
				if (MetadataUtil.isReferencingItem(item) && MetadataUtil.isReferencableFeature(item, getEStructuralFeature(element))) {
					MetadataItem entity = null;
					try {
						entity = MetadataUtil.defaultResolve(item, resource).first;
					} catch (CircularReferenceException e) {
					}
					return entity == null ? item : entity;
				}
			}
		}

		return owner;
	}
}
