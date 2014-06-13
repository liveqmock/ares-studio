package com.hundsun.ares.studio.internal.core;

import org.eclipse.core.expressions.PropertyTester;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;

public class ARESModulePropertyTester extends PropertyTester {

	private static final String isResTypeAllowed = "isResTypeAllowed";

	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		IARESModule module = (IARESModule)receiver;
		if (module == null) return false;
		if (args.length == 1 && property.equals(isResTypeAllowed)) {
			String expectArg = String.valueOf(args[0]);
			ModuleRootType2ResTypeMap map = ModuleRootType2ResTypeMap.getInstance();
			boolean result = map.isAllowed(module.getRoot().getType(), expectArg);
			return expectedValue.equals(result);
		}
		return false;
	}

}
