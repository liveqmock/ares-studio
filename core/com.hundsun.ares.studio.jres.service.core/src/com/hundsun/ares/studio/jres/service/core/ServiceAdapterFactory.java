package com.hundsun.ares.studio.jres.service.core;

import org.eclipse.core.runtime.IAdapterFactory;

import com.hundsun.ares.studio.core.model.IReferenceProvider2;
import com.hundsun.ares.studio.jres.service.Service;
import com.hundsun.ares.studio.jres.service.reference.ServiceReferenceProvider;

public class ServiceAdapterFactory implements IAdapterFactory {

	private static Class[] CLASSES = new Class[] {IReferenceProvider2.class};
	
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof Service) {
			if (adapterType == IReferenceProvider2.class) {
				return ServiceReferenceProvider.INSTANCE;
			}
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return CLASSES;
	}

}
