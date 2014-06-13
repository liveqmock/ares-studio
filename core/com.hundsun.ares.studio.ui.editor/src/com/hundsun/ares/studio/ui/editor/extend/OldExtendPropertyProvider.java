package com.hundsun.ares.studio.ui.editor.extend;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.model.extend.EMFExtendPropertyDescriptor;
import com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor;
import com.hundsun.ares.studio.core.model.extend.IExtendedPropertyProvider;
import com.hundsun.ares.studio.core.model.extend.IExtendedPropertyProvider2;
import com.hundsun.ares.studio.core.model.extend.UserExtendedPropertyDescriptor;
import com.hundsun.ares.studio.core.model.extend.UserExtendedPropertyTypes;

public class OldExtendPropertyProvider implements IExtendedPropertyProvider, IExtendedPropertyProvider2 {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IExtendPropertyProvider#isEnabled(com.hundsun.ares.studio.core.IARESElement, org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public boolean isEnabled(IARESElement element, EClass clazz) {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IExtendPropertyProvider#getKey()
	 */
	@Override
	public String getKey() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IExtendPropertyProvider#createMapValueObject()
	 */
	@Override
	public EObject createMapValueObject() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IExtendPropertyProvider#getExtendProperties()
	 */
	@Override
	public IBasicExtendPropertyDescriptor[] getExtendProperties() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IExtendPropertyProvider2#getExtendProperties(com.hundsun.ares.studio.core.IARESElement, org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public IBasicExtendPropertyDescriptor[] getExtendProperties(IARESElement element, EClass clazz) {
		List<IBasicExtendPropertyDescriptor> result = new ArrayList<IBasicExtendPropertyDescriptor>();
		IExtensibleModelEditingSupport[] supports = null;
		
		if (element != null) {
			supports = ExtensibleModelUtils.getEndabledEditingSupports(element, clazz);
		} else {
			supports = ExtensibleModelManager.getInstance().getExtensibleModelEditingSupports(clazz);
		}
		
		for (IExtensibleModelEditingSupport sp : supports) {
			IExtensibleModelPropertyDescriptor[] descriptors = sp.getPropertyDescriptors(element, clazz);
			for (IExtensibleModelPropertyDescriptor d : descriptors) {
//				// TODO: 暂时不处理
//				if (d.isDerived())
//					continue;
				
				String cate = d.getCategory();
				String displayName = d.getDisplayName();
				String desc = d.getDescription();
				if (d instanceof IBasicExtendPropertyDescriptor) {
					result.add((IBasicExtendPropertyDescriptor) d);
				} else 	if (d instanceof IMapExtensibleModelPropertyDescriptor) {
					IMapExtensibleModelPropertyDescriptor mapDesc = (IMapExtensibleModelPropertyDescriptor) d;
					// 默认是Text类型
					UserExtendedPropertyTypes type = UserExtendedPropertyTypes.TEXT;
					if (d instanceof BooleanMapEMPropertyDescriptor) {
						type = UserExtendedPropertyTypes.BOOLEAN;
					} else if (d instanceof LongTextMapEMPropertyDescriptor) {
						type = UserExtendedPropertyTypes.LONG_TEXT;
					} else if (d instanceof TextMapEMPropertyDescriptor) {
						type = UserExtendedPropertyTypes.TEXT;
					}
					UserExtendedPropertyDescriptor basic = new UserExtendedPropertyDescriptor(String.valueOf(mapDesc.getKey()), type);
					basic.setCategory(cate);
					basic.setDisplayName(displayName);
					basic.setDescription(desc);
					result.add(basic);
				} else {
					EStructuralFeature feature = d.getStructuralFeature();
					EMFExtendPropertyDescriptor basic = new EMFExtendPropertyDescriptor(sp.getKey(), feature);
					basic.setCategory(cate);
					basic.setDisplayName(displayName);
					basic.setDescription(desc);
					result.add(basic);
				}
				
			}
		}
		return result.toArray(new IBasicExtendPropertyDescriptor[0]);
	}


}
