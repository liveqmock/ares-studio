/**
 * 源程序名称：ExtensibleModelColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.basicdata.ui.extend;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.ui.editor.blocks.DisplayItem;
import com.hundsun.ares.studio.ui.editor.extend.IEMLabelProviderExtension;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IMapExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.LongTextMapEMPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * 用于从表引用信息表的扩展属性列
 * @author wangxh
 *
 */
public class RefExtensibleModelColumnLabelProvider extends
		EObjectColumnLabelProvider {

	IExtensibleModelEditingSupport editingSupport;
	IExtensibleModelPropertyDescriptor descriptor;
	EStructuralFeature infoReference;
	
	/**
	 * @param editingSupport
	 * @param descriptior
	 */
	public RefExtensibleModelColumnLabelProvider(
			IExtensibleModelEditingSupport editingSupport,
			IExtensibleModelPropertyDescriptor descriptor,
			EStructuralFeature infoReference) {
		super(descriptor.getStructuralFeature());
		this.editingSupport = editingSupport;
		this.descriptor = descriptor;
		this.infoReference = infoReference;
	}

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.BaseEObjectColumnLabelProvider#getOwner(java.lang.Object)
	 */
	@Override
	protected EObject getOwner(Object element) {
		Object refer =  getRefObject(element);
		// 接口参数列表中，对象节点展开后的子节点，类型并非ExtensibleModel
		if (null != refer && refer instanceof ExtensibleModel) {
			ExtensibleModel model = (ExtensibleModel) refer;
			return model.getData2().get(editingSupport.getKey());
		} 
		return null;
	}
	
	private ExtensibleModel getRefObject(Object element){
		EObject eobj = (EObject) element;
		Object refer =  eobj.eGet(infoReference);
		//获取引用的信息表条目
		if(null != refer && refer instanceof ExtensibleModel){
			return (ExtensibleModel) refer;
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
				((IEMLabelProviderExtension) labelProvider).setExtensibleModel(getRefObject(element));
			}
			if(feature!=null){
				Object value = owner.eGet(feature);
				if (descriptor instanceof IMapExtensibleModelPropertyDescriptor) {
					Assert.isTrue(value instanceof EMap<?, ?>);
					value = ((EMap<?, ?>) value).get(((IMapExtensibleModelPropertyDescriptor) descriptor).getKey());
				}	
				return labelProvider.getText(value);
			}
			return StringUtils.EMPTY;
			
			
		} else {
			ILabelProvider labelProvider = descriptor.getLabelProvider();
			// 特殊的扩展列(XML标签之类，是动态计算，而非去扩展map里取值的)
			// 另外，为什么只有实现IEMLabelProviderExtension才返回值： 因为在元数据带分组信息的情况下，分组这个
			// 元素也会由于getOwner为空而执行到这个分支，所以暂时以这个labelprovider以示区分。
			if (labelProvider instanceof IEMLabelProviderExtension
					&& (element instanceof ExtensibleModel)) {
				((IEMLabelProviderExtension) labelProvider).setExtensibleModel((ExtensibleModel) element);
				String text = labelProvider.getText(element);
				if (text != null)
					return text;
			}else if (labelProvider instanceof IEMLabelProviderExtension
					&& (element instanceof DisplayItem)) {
				if(((DisplayItem)element).eObject instanceof ExtensibleModel){
					((IEMLabelProviderExtension) labelProvider).setExtensibleModel((ExtensibleModel) ((DisplayItem)element).eObject);
					String text = labelProvider.getText(element);
					if (text != null)
						return text;
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
		EObject owner = getOwner(element);
		if (owner != null) {
			EStructuralFeature feature = descriptor.getStructuralFeature();
			if (feature == null)
				return null;
			
			ILabelProvider labelProvider = descriptor.getLabelProvider();
			if (labelProvider instanceof IEMLabelProviderExtension) {
				((IEMLabelProviderExtension) labelProvider).setExtensibleModel((ExtensibleModel) element);
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
		return null;
	}
	
	@Override
	public String getToolTipText(Object element) {
		String toolTip = super.getToolTipText(element);
		if(StringUtils.isBlank(toolTip) && descriptor instanceof LongTextMapEMPropertyDescriptor){
			return getText(element);
		}
		return toolTip;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider#getBackground(java.lang.Object)
	 */
	@Override
	public Color getBackground(Object element) {
		return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	}
}
