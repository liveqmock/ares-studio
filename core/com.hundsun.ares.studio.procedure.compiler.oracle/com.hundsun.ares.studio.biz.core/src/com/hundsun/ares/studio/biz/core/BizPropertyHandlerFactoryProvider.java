package com.hundsun.ares.studio.biz.core;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.excel.handlers.InterfacePropertyHandlerFactory;
import com.hundsun.ares.studio.biz.excel.handlers.ParameterPropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.model.extend.IPropertyHandlerFactoryProvider;

public class BizPropertyHandlerFactoryProvider implements IPropertyHandlerFactoryProvider {

	public BizPropertyHandlerFactoryProvider() {
	}

	@Override
	public IPropertyHandlerFactory getFactory(EClass eClass) {
		if (eClass == BizPackage.Literals.BIZ_INTERFACE) {
			return InterfacePropertyHandlerFactory.INSTANCE;
		} else if (eClass == BizPackage.Literals.PARAMETER) {
			return ParameterPropertyHandlerFactory.INSTANCE;
		}
		return null;
	}

}
