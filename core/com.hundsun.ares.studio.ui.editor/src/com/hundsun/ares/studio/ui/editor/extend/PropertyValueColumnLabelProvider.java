/**
 * 源程序名称：PropertyValueColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;

import com.hundsun.ares.studio.ui.editor.extend.user.IUserExtendedPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * 
 * 提供了对 {@link IEMLabelProviderExtension}的支持
 * @author gongyf
 *
 */
public class PropertyValueColumnLabelProvider extends EObjectColumnLabelProvider {
	
	/**
	 * @param attribute
	 */
	public PropertyValueColumnLabelProvider() {
		super(new IEStructuralFeatureProvider(){

			@Override
			public EStructuralFeature getFeature(Object element) {
				if (element instanceof ExtensibleModelEditingEntry) {
					ExtensibleModelEditingEntry entry = (ExtensibleModelEditingEntry) element;
					return entry.getDescriptor().getStructuralFeature();
				}
				return null;
			}});
	}

	@Override
	protected Diagnostic getDiagnostic(Object element) {
		if (getDiagnosticProvider() != null) {
			if (element instanceof ExtensibleModelEditingEntry) {
				ExtensibleModelEditingEntry entry = (ExtensibleModelEditingEntry) element;
				EObject owner = getOwner(element);
				if (owner != null) {
					// 对于map类型的特殊处理，一般用于用户自定义扩展
					IExtensibleModelPropertyDescriptor descriptor = entry.getDescriptor();
					if (descriptor instanceof IMapExtensibleModelPropertyDescriptor) {
						return getDiagnosticProvider().getDiagnostic(owner, getEStructuralFeature(element), ((IMapExtensibleModelPropertyDescriptor) descriptor).getKey());
					} else {
						return getDiagnosticProvider().getDiagnostic(owner, getEStructuralFeature(element));
					}
					
				}
			}
			
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.BaseEObjectColumnLabelProvider#getOwner(java.lang.Object)
	 */
	@Override
	protected EObject getOwner(Object element) {
		if (element instanceof ExtensibleModelEditingEntry) {
			ExtensibleModelEditingEntry entry = (ExtensibleModelEditingEntry) element;
			String key = entry.getGroup().getEditingSupport().getKey();
			return entry.getGroup().getRoot().getModel().getData2().get(key);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.BaseEObjectColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof ExtensibleModelEditingEntry) {
			ExtensibleModelEditingEntry entry = (ExtensibleModelEditingEntry) element;
			EObject owner = getOwner(element);
			if (owner != null) {
				IExtensibleModelPropertyDescriptor descriptor = entry.getDescriptor();
				if (descriptor instanceof IUserExtendedPropertyDescriptor) {
					// 新的处理方式，完全把element交给descriptor处理
					ILabelProvider labelProvider = descriptor.getLabelProvider();
					if (labelProvider != null) {
						return labelProvider.getText(entry.getGroup().getRoot().getModel());
					}
				} else {
					EStructuralFeature feature = descriptor.getStructuralFeature();
					ILabelProvider labelProvider = descriptor.getLabelProvider();
					if (labelProvider instanceof IEMLabelProviderExtension) {
						((IEMLabelProviderExtension) labelProvider).setExtensibleModel(entry.getGroup().getRoot().getModel());
					}
					
					Object value = owner.eGet(feature);
					if (descriptor instanceof IMapExtensibleModelPropertyDescriptor) {
						Assert.isTrue(value instanceof EMap<?, ?>);
						value = ((EMap<?, ?>) value).get(((IMapExtensibleModelPropertyDescriptor) descriptor).getKey());
					}
					
					return labelProvider.getText(value);
				}
			}
			
		}
		return StringUtils.EMPTY;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.EObjectColumnLabelProvider#doGetImage(java.lang.Object)
	 */
	@Override
	protected Image doGetImage(Object element) {
		if (element instanceof ExtensibleModelEditingEntry) {
			ExtensibleModelEditingEntry entry = (ExtensibleModelEditingEntry) element;
			EObject owner = getOwner(element);
			if (owner != null) {
				IExtensibleModelPropertyDescriptor descriptor = entry.getDescriptor();
				if (descriptor instanceof IUserExtendedPropertyDescriptor) {
					// 新的处理方式，完全把element交给descriptor处理
					ILabelProvider labelProvider = descriptor.getLabelProvider();
					if (labelProvider != null) {
						return labelProvider.getImage(entry.getGroup().getRoot().getModel());
					}
				} else {
					EStructuralFeature feature = descriptor.getStructuralFeature();
					ILabelProvider labelProvider = descriptor.getLabelProvider();
					if (labelProvider instanceof IEMLabelProviderExtension) {
						((IEMLabelProviderExtension) labelProvider).setExtensibleModel(entry.getGroup().getRoot().getModel());
					}
					
					Object value = owner.eGet(feature);
					if (descriptor instanceof IMapExtensibleModelPropertyDescriptor) {
						Assert.isTrue(value instanceof EMap<?, ?>);
						value = ((EMap<?, ?>) value).get(((IMapExtensibleModelPropertyDescriptor) descriptor).getKey());
					}
					
					return labelProvider.getImage(value);
				}
			}
		}
		return null;
	}
	
}
