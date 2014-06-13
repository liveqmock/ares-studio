package com.hundsun.ares.studio.atom.excel;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.atom.AtomFactory;
import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.excel.Resource;

public class AtomFunctionSheetHandler extends AtomSheetHandler {
	private AtomFunction function;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#startArea(java.lang.String)
	 */
	@Override
	public void startArea(String startTag) {
		super.startArea(startTag);
		if (StringUtils.equals(startTag, "∂‘œÛ∫≈"))
			function = AtomFactory.eINSTANCE.createAtomFunction();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endArea()
	 */
	public void endArea() {
		super.endArea();
		if (function != null) {
			Resource resource = new Resource();
			resource.info = function;
			resource.name = function.getName();
			resource.type = IAtomResType.ATOM_FUNCTION;
			
			for (Parameter param : function.getInternalVariables()) {
				param.setParamType(ParamType.NON_STD_FIELD);
			}

			resourceFound(resource);
		}
	}
	
	@Override
	protected EObject getOwner() {
		return function;
	}
}
