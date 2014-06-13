package com.hundsun.ares.studio.atom.core;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.atom.excel.AtomFunctionPropertyHandlerFactory;
import com.hundsun.ares.studio.atom.excel.AtomServicePropertyHandlerFactory;
import com.hundsun.ares.studio.atom.excel.VarPropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.model.extend.IPropertyHandlerFactoryProvider;

public class AtomPropertyHandlerFactoryProvider implements IPropertyHandlerFactoryProvider {

	public AtomPropertyHandlerFactoryProvider() {
	}

	@Override
	public IPropertyHandlerFactory getFactory(EClass eClass) {
		if (eClass == AtomPackage.Literals.ATOM_FUNCTION) {
			return AtomFunctionPropertyHandlerFactory.INSTANCE;
		} else if (eClass == AtomPackage.Literals.ATOM_SERVICE) {
			return AtomServicePropertyHandlerFactory.INSTANCE;
		} else if (eClass == AtomPackage.Literals.INTERNAL_PARAM) {
			return VarPropertyHandlerFactory.INSTANCE;
		}
		return null;
	}

}
