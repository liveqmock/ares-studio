/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.macro.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.compiler.macro.handlers.LogicFunctionCallMacroHandler;
import com.hundsun.ares.studio.logic.compiler.macro.service.ILogicFunctionMacroTokenService;
import com.hundsun.ares.studio.logic.constants.ILogicRefType;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author qinyuan
 *
 */
public class LogicFunctionMacroTokenService implements ILogicFunctionMacroTokenService{
	IARESProject project;
	Map<String, IMacroTokenHandler> funcMap = new HashMap<String, IMacroTokenHandler>();
	Map<String, IARESResource> funcResourceMap = new HashMap<String, IARESResource>();
	
	/**
	 * 
	 */
	public LogicFunctionMacroTokenService(IARESProject project) {
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.logic.compiler.macro.service.ILogicFunctionMacroTokenService#isLogicFunction(java.lang.String)
	 */
	@Override
	public boolean isLogicFunction(String functionName) {
		loadAtomFunction(functionName);
		return  funcMap.containsKey(functionName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.logic.compiler.macro.service.ILogicFunctionMacroTokenService#getHandler(java.lang.String)
	 */
	@Override
	public IMacroTokenHandler getHandler(String functionName) {
		loadAtomFunction(functionName);
		return funcMap.get(functionName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.logic.compiler.macro.service.ILogicFunctionMacroTokenService#getARESResource(java.lang.String)
	 */
	@Override
	public IARESResource getARESResource(String functionName) {
		loadAtomFunction(functionName);
		return funcResourceMap.get(functionName);
	}
	/**
	 * 从缓存中加载逻辑函数服务
	 * @param functionName
	 */
	private void loadAtomFunction(String functionName){
		if(!funcMap.containsKey(functionName)){
			ReferenceInfo refInfo =ReferenceManager.getInstance().getFirstReferenceInfo(project, ILogicRefType.LOGIC_FUNCTION_CNAME, functionName, true);
			if(refInfo!=null){
				LogicFunction func = (LogicFunction) refInfo.getObject();
				if(func!=null){
					funcResourceMap.put(func.getChineseName(), refInfo.getResource());
					funcMap.put(func.getChineseName(), new LogicFunctionCallMacroHandler(func.getChineseName(), refInfo.getResource()));
				}
			}

		}
	}
}
