package com.hundsun.ares.studio.logic.excel;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.excel.Resource;
import com.hundsun.ares.studio.logic.LogicFactory;
import com.hundsun.ares.studio.logic.LogicPackage;
import com.hundsun.ares.studio.logic.LogicService;
import com.hundsun.ares.studio.logic.constants.ILogicResType;

/**
 * 原子服务Sheet页处理类
 * @author sundl
 */
public class LogicServiceSheetHandler extends LogicSheetHandler{
	
	private LogicService service;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#startArea(java.lang.String)
	 */
	@Override
	public void startArea(String startTag) {
		super.startArea(startTag);
		if (StringUtils.equals(startTag, "对象号"))
			service = LogicFactory.eINSTANCE.createLogicService();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endArea()
	 */
	@Override
	public void endArea() {
		super.endArea();
		if (service != null) {
			Resource resource = new Resource();
			resource.info = service;
			resource.name = service.getName();
			resource.type = ILogicResType.LOGIC_SERVICE;
			
			resourceFound(resource);
		}
	}
	
	protected EClass getEClass() {
		if (isVarBlock()) {
			return AtomPackage.Literals.INTERNAL_PARAM;
		} else if (isTextBlock()) {
			return LogicPackage.Literals.LOGIC_SERVICE;
		} else if (isInputParmaBlock() || isOutputParmaBlock()) {
			return BizPackage.Literals.PARAMETER;
		}
		return LogicPackage.Literals.LOGIC_SERVICE;
	}
	
	@Override
	protected EObject getOwner() {
		return service;
	}
}
