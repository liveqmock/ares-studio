package com.hundsun.ares.studio.jres.metadata.core.handlers;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.model.extend.IPropertyHandlerFactoryProvider;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.propertyHandlers.ErrorNoItemPropertyHandlerFactory;

public class MetadataPropertyHandlerFactoryProvider implements IPropertyHandlerFactoryProvider {

	public MetadataPropertyHandlerFactoryProvider() {
	}

	@Override
	public IPropertyHandlerFactory getFactory(EClass eClass) {
		if (eClass == MetadataPackage.Literals.STANDARD_FIELD) {
			return StandardFiledPropertyHandlerFactory.INSTANCE;
		} else if (eClass == MetadataPackage.Literals.ERROR_NO_ITEM) {
			return ErrorNoItemPropertyHandlerFactory.INSTANCE;
		} else if (eClass == MetadataPackage.Literals.DICTIONARY_TYPE) {
			return DictTypePropertyHandlerFactory.INSTANCE;
		} else if (eClass == MetadataPackage.Literals.DICTIONARY_ITEM) {
			return DictItemPropertyHandlerFactory.INSTANCE;
		} else if (eClass == MetadataPackage.Literals.BIZ_PROPERTY_CONFIG) {
			return BizPkgPropertyHandlerFactory.INSTANCE;
		}
		return null;
	}

}
