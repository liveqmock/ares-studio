package com.hundsun.ares.studio.atom.excel;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.atom.AtomFactory;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.excel.Resource;

/**
 * 原子服务Sheet页处理类
 * @author sundl
 */
public class AtomServiceSheetHandler extends AtomSheetHandler{
	
	private AtomService service;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#startArea(java.lang.String)
	 */
	@Override
	public void startArea(String startTag) {
		super.startArea(startTag);
		if (StringUtils.equals(startTag, "对象号"))
			service = AtomFactory.eINSTANCE.createAtomService();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endArea()
	 */
	public void endArea() {
		super.endArea();
		if (service != null) {
			Resource resource = new Resource();
			resource.info = service;
			resource.name = service.getName();
			resource.type = IAtomResType.ATOM_SERVICE;
			for (Parameter param : service.getInternalVariables()) {
				param.setParamType(ParamType.NON_STD_FIELD);
			}
			
			resourceFound(resource);
		}
	}
	
	@Override
	protected EObject getOwner() {
		return service;
	}
}
