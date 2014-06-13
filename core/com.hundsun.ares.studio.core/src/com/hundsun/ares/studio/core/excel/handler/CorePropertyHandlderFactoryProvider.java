package com.hundsun.ares.studio.core.excel.handler;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.extend.IPropertyHandlerFactoryProvider;

public class CorePropertyHandlderFactoryProvider implements IPropertyHandlerFactoryProvider {

	public CorePropertyHandlderFactoryProvider() {
	}

	@Override
	public IPropertyHandlerFactory getFactory(EClass eClass) {
		if (eClass == CorePackage.Literals.REVISION_HISTORY) {
			return RevisionHistoryPropertyHandlerFactory.INSTANCE;
		}
		return null;
	}

}
