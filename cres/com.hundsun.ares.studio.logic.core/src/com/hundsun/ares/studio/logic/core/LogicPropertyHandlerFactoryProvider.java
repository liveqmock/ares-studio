package com.hundsun.ares.studio.logic.core;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.model.extend.IPropertyHandlerFactoryProvider;
import com.hundsun.ares.studio.logic.LogicPackage;
import com.hundsun.ares.studio.logic.excel.LogicFunctionPropertyHandlerFactory;
import com.hundsun.ares.studio.logic.excel.LogicServicePropertyHandlerFactory;

public class LogicPropertyHandlerFactoryProvider implements IPropertyHandlerFactoryProvider {

	public LogicPropertyHandlerFactoryProvider() {
	}

	@Override
	public IPropertyHandlerFactory getFactory(EClass eClass) {
		if (eClass == LogicPackage.Literals.LOGIC_FUNCTION) {
			return LogicFunctionPropertyHandlerFactory.INSTANCE;
		} else if(eClass == LogicPackage.Literals.LOGIC_SERVICE) {
			return LogicServicePropertyHandlerFactory.INSTANCE;
		}
		return null;
	}

}
