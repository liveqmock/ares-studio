package com.hundsun.ares.studio.internal.core;

import org.eclipse.core.expressions.PropertyTester;

import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;

public class ModuleRootPropertyTester extends PropertyTester {
	
	private static final String isResTypeAllowed = "isResTypeAllowed";
	private static final String MAX_MODULE_LEVEL = "maxModuleLevel";
	
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		IARESModuleRoot root = (IARESModuleRoot)receiver;
		if (root == null) return false;
		
		if (property.equals(isResTypeAllowed)) {
			// isResTypeAllowed必须有一个参数，即要测试的资源类型
			if (args.length == 1) {
				String expectArg = String.valueOf(args[0]);
				ModuleRootType2ResTypeMap map = ModuleRootType2ResTypeMap.getInstance();
				boolean result = map.isAllowed(root.getType(), expectArg);
				return expectedValue.equals(result);
			}
		} else if (property.equals(MAX_MODULE_LEVEL)) {
			String expectValue = String.valueOf(expectedValue);
			ModulesRootTypeRegistry reg = ModulesRootTypeRegistry.getInstance();
			IModuleRootDescriptor desc = reg.getModuleRootDescriptor(root.getType());
			return expectValue.equals(String.valueOf(desc.getMaxModuleLevel()));
		} else if (property.equals("rootType")) {
			String rootType = root.getType();
			return rootType.equals(String.valueOf(expectedValue));
		}
		
		return false;
	}

}
