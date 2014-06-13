/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.editor.extend;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.LabelProvider;

import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;

/**
 * 从扩展模型中取值(不支持从缓存中取)
 * @author liaogc
 *
 */
public class ExtendsLabelProvider  extends LabelProvider implements IEMLabelProviderExtension {
	
	private ExtensibleModel model;
	private String key;
	private EStructuralFeature feature;
	private String extendModelKey;
	
	public ExtendsLabelProvider(String key ,EStructuralFeature feature, String extendModelKey) {
		super();
		this.key = key;
		this.feature = feature;
		this.extendModelKey = extendModelKey;
	}

	@Override
	public String getText(Object element) {
		if (model != null) {
			EObject value = model.getData2().get(extendModelKey);
			if (value != null && value instanceof UserExtensibleProperty) {
				return StringUtils.defaultIfBlank(
						((UserExtensibleProperty) value).getMap().get(key), "");
			}
				
			return StringUtils.EMPTY;
			
		}

		return StringUtils.EMPTY;
	}

	@Override
	public void setExtensibleModel(ExtensibleModel model) {
		this.model = model;
	}

}

