package com.hundsun.ares.studio.ui.editor.extend.user;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;


public class ReferenceUserExtendedPropertyTypeProvider implements IUserExtendPropertyTypeProvider {

	public ReferenceUserExtendedPropertyTypeProvider() {
	}

	@Override
	public IExtensibleModelPropertyDescriptor createPropertyDescriptor(IARESProject project, Map<String, String> config) {
		String id = config.get("id");
		String name = config.get("name");
		String refIdFeature = config.get("refIdFeature");
		String targetType = config.get("targetRefType");
		String targetFeature = config.get("targetFeature");
		String displayPattern = config.get("displayPattern");
		ReferenceUserExtendedPropertyDescriptor desc = new ReferenceUserExtendedPropertyDescriptor(project, id, refIdFeature, targetType, targetFeature, displayPattern);
		desc.setDisplayName(name);
		return desc;
	}

}
