package com.hundsun.ares.studio.jres.service.core;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.model.extend.IPropertyHandlerFactoryProvider;
import com.hundsun.ares.studio.jres.service.ServicePackage;
import com.hundsun.ares.studio.jres.service.core.excel.ServicePropertyHandlerFactory;

public class ServicePropertyHandlerFactoryProvider implements IPropertyHandlerFactoryProvider {

	public ServicePropertyHandlerFactoryProvider() {
	}

	@Override
	public IPropertyHandlerFactory getFactory(EClass eClass) {
		if (eClass == ServicePackage.Literals.SERVICE) {
			return ServicePropertyHandlerFactory.INSTANCE;
		}
		return null;
	}

}
