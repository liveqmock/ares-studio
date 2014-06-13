/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor.viewers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 提供一种简单的多特性字段提供器
 * @author gongyf
 *
 */
public class MultiTypeEStructuralFeatureProvider implements
		IEStructuralFeatureProvider {

	Map<EClass, EStructuralFeature> featureMap;
	
	/**
	 * 必须保证2个数组长度一致
	 * 
	 * @param eClasses
	 * @param features
	 */
	public MultiTypeEStructuralFeatureProvider(EClass[] eClasses, EStructuralFeature[] features) {
		Assert.isNotNull(eClasses);
		Assert.isNotNull(features);
		Assert.isTrue(eClasses.length == features.length);
		
		featureMap = new HashMap<EClass, EStructuralFeature>();
		for (int i = 0; i < eClasses.length; i++) {
			featureMap.put(eClasses[i], features[i]);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.IEStructuralFeatureProvider#getFeature(java.lang.Object)
	 */
	@Override
	public EStructuralFeature getFeature(Object element) {
		if (element instanceof EObject) {
			return featureMap.get(((EObject) element).eClass());
		}
		return null;
	}

}
