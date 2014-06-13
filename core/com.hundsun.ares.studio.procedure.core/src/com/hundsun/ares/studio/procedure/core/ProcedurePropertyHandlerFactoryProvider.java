package com.hundsun.ares.studio.procedure.core;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.model.extend.IPropertyHandlerFactoryProvider;
import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.procdure.excel.ProcedurePropertyHandlerFactory;
import com.hundsun.ares.studio.procdure.excel.VarPropertyHandlerFactory;

public class ProcedurePropertyHandlerFactoryProvider implements IPropertyHandlerFactoryProvider {

	public ProcedurePropertyHandlerFactoryProvider() {
	}

	@Override
	public IPropertyHandlerFactory getFactory(EClass eClass) {
		if (eClass == ProcdurePackage.Literals.PROCEDURE) {
			return ProcedurePropertyHandlerFactory.INSTANCE;
		} else if (eClass == ProcdurePackage.Literals.INTERNAL_PARAM) {
			return VarPropertyHandlerFactory.INSTANCE;
		}
		return null;
	}

}
