package com.hundsun.ares.studio.logic.excel;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.excel.Resource;
import com.hundsun.ares.studio.logic.LogicFactory;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.constants.ILogicResType;

public class LogicFunctionSheetHandler extends LogicSheetHandler {
	private LogicFunction function;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#startArea(java.lang.String)
	 */
	@Override
	public void startArea(String startTag) {
		super.startArea(startTag);
		if (StringUtils.equals(startTag, "∂‘œÛ∫≈"))
			function = LogicFactory.eINSTANCE.createLogicFunction();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endArea()
	 */
	@Override
	public void endArea() {
		super.endArea();
		if (function != null) {
			Resource resource = new Resource();
			resource.info = function;
			resource.name = function.getName();
			resource.type = ILogicResType.LOGIC_FUNCTION;
			
			resourceFound(resource);
		}
	}
	
	@Override
	protected EObject getOwner() {
		return function;
	}
}
