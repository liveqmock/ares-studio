package com.hundsun.ares.studio.logic.script;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicFunctionWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicModuleWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicServiceWrap;
import com.hundsun.ares.studio.logic.constants.ILogicResType;

public class LogicModuleWrap implements ILogicModuleWrap {

	private IARESModule module;
	
	public LogicModuleWrap(IARESModule module) {
		this.module = module;
	}
	
	@Override
	public ILogicServiceWrap[] getLogicServices(boolean recursive) {
		List<ILogicServiceWrap> services = new ArrayList<ILogicServiceWrap>();
		IARESResource[] resources = module.getARESResources(ILogicResType.LOGIC_SERVICE, recursive);
		for (IARESResource res : resources) {
			services.add(new LogicServiceWrap(res));
		}
		return services.toArray(new ILogicServiceWrap[0]);
	}

	@Override
	public ILogicFunctionWrap[] getLogicFunctions(boolean recursive) {
		List<ILogicFunctionWrap> services = new ArrayList<ILogicFunctionWrap>();
		IARESResource[] resources = module.getARESResources(ILogicResType.LOGIC_FUNCTION, recursive);
		for (IARESResource res : resources) {
			services.add(new LogicFunctionWrap(res));
		}
		return services.toArray(new ILogicFunctionWrap[0]);
	}

}
