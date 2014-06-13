package com.hundsun.ares.studio.jres.obj.ui.extend;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.biz.constants.IBizConstants;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.TextEMPropertyDescriptor;

public class ObjectIDExtendEditingSupport implements IExtensibleModelEditingSupport {

	@Override
	public boolean isEnable(IARESElement aresElement, EClass eClass) {
		return true;
	}

	@Override
	public String getName() {
		return IBizResType.Object;
	}

	@Override
	public String getKey() {
		return IBizConstants.OBJ_ID_RANGE_KEY;
	}

	@Override
	public EObject createMapValueObject() {
		return MetadataFactory.eINSTANCE.createIDRange();
	}

	@Override
	public IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(
			IARESElement aresElement, EClass eClass) {
		List<IExtensibleModelPropertyDescriptor> descriptors = new ArrayList<IExtensibleModelPropertyDescriptor>();
		{
			TextEMPropertyDescriptor objIDDesc = new TextEMPropertyDescriptor(MetadataPackage.Literals.ID_RANGE__RANGE);
			objIDDesc.setDisplayName("业务对象");
			descriptors.add(objIDDesc);
		}
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]) ;
	}

}
