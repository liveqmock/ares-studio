package com.hundsun.ares.studio.core;

import org.eclipse.core.expressions.PropertyTester;

public class AresResourcePropertyTester extends PropertyTester {

	private static final String TYPE = "type";
	
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof IARESResource) {
			IARESResource resource = (IARESResource) receiver;
			if (property.equals(TYPE)) {
				return resource.getType().equals(expectedValue);
			}
		}
		return false;
	}

}
