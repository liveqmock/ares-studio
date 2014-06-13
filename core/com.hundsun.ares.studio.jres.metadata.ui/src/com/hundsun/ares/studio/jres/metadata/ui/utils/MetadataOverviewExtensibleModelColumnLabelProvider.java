/**
 * 源程序名称：MetadataOverviewExtensibleModelColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：廖国承
 */
package com.hundsun.ares.studio.jres.metadata.ui.utils;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;

import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.jres.metadata.ui.model.MetadataOverviewElement;
import com.hundsun.ares.studio.ui.editor.extend.IEMLabelProviderExtension;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IMapExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * 
 * 对总预览列表扩展的支持
 * @author liaogc
 *
 */
public class MetadataOverviewExtensibleModelColumnLabelProvider extends
		EObjectColumnLabelProvider {

	IExtensibleModelEditingSupport editingSupport;
	IExtensibleModelPropertyDescriptor descriptor;
	
	
	
	/**
	 * @param editingSupport
	 * @param descriptior
	 */
	public MetadataOverviewExtensibleModelColumnLabelProvider(
			IExtensibleModelEditingSupport editingSupport,
			IExtensibleModelPropertyDescriptor descriptor) {
		super(descriptor.getStructuralFeature());
		this.editingSupport = editingSupport;
		this.descriptor = descriptor;
	}

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.BaseEObjectColumnLabelProvider#getOwner(java.lang.Object)
	 */
	@Override
	protected EObject getOwner(Object element) {
		if(element instanceof MetadataOverviewElement){
			MetadataOverviewElement model = (MetadataOverviewElement) element;
			if(model.getItem() instanceof ExtensibleModel){
				return model.getItem().getData2().get(editingSupport.getKey());
			}
				
			}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.BaseEObjectColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		EObject owner = getOwner(element);
		if (owner != null) {
			EStructuralFeature feature = descriptor.getStructuralFeature();
			ILabelProvider labelProvider = descriptor.getLabelProvider();
			if (labelProvider instanceof IEMLabelProviderExtension) {
				((IEMLabelProviderExtension) labelProvider).setExtensibleModel(((MetadataOverviewElement) element).getItem());
			}
			
			Object value = owner.eGet(feature);
			if (descriptor instanceof IMapExtensibleModelPropertyDescriptor) {
				Assert.isTrue(value instanceof EMap<?, ?>);
				value = ((EMap<?, ?>) value).get(((IMapExtensibleModelPropertyDescriptor) descriptor).getKey());
			}
			
			return labelProvider.getText(value);
		}
		return StringUtils.EMPTY;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.EObjectColumnLabelProvider#doGetImage(java.lang.Object)
	 */
	@Override
	protected Image doGetImage(Object element) {
		EObject owner = getOwner(element);
		if (owner != null) {
			EStructuralFeature feature = descriptor.getStructuralFeature();
			ILabelProvider labelProvider = descriptor.getLabelProvider();
			if (labelProvider instanceof IEMLabelProviderExtension) {
				((IEMLabelProviderExtension) labelProvider).setExtensibleModel(((MetadataOverviewElement) element).getItem());
			}
			
			Object value = owner.eGet(feature);
			if (descriptor instanceof IMapExtensibleModelPropertyDescriptor) {
				Assert.isTrue(value instanceof EMap<?, ?>);
				value = ((EMap<?, ?>) value).get(((IMapExtensibleModelPropertyDescriptor) descriptor).getKey());
			}
			
			return labelProvider.getImage(value);
			
		}
		return null;
	}
	
	@Override
	protected Diagnostic getDiagnostic(Object element) {
		if (getDiagnosticProvider() != null) {
			EObject owner = getOwner(element);
			EStructuralFeature feature = descriptor.getStructuralFeature();
			if (descriptor instanceof IMapExtensibleModelPropertyDescriptor) {
				return getDiagnosticProvider().getDiagnostic(owner, feature, ((IMapExtensibleModelPropertyDescriptor) descriptor).getKey());
			} else {
				return getDiagnosticProvider().getDiagnostic(owner, feature);
			}
		}
		return super.getDiagnostic(element);
	}

}
