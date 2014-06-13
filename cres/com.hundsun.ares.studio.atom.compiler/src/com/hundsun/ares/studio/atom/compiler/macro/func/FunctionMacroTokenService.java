/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.macro.func;

import java.util.HashMap;
import java.util.Map;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.macro.handlers.FunctionCallMacroHandler;
import com.hundsun.ares.studio.atom.constants.IAtomRefType;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;


/**
 * @author zhuyf
 *
 */
public class FunctionMacroTokenService implements IFunctionMacroTokenService {
	
	IARESProject project;
	Map<String, IMacroTokenHandler> funcMap = new HashMap<String, IMacroTokenHandler>();
	Map<String, IARESResource> funcResourceMap = new HashMap<String, IARESResource>();
	
	public FunctionMacroTokenService(IARESProject project){
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.atom.compiler.macro.callfunc.IFunctionMacroTokenService#isAtomFunction(java.lang.String)
	 */
	@Override
	public boolean isAtomFunction(String functionName) {
		loadAtomService(functionName);
		return funcMap.containsKey(functionName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.atom.compiler.macro.callfunc.IFunctionMacroTokenService#getFuncCallMacroHandler(java.lang.String)
	 */
	@Override
	public IMacroTokenHandler getHandler(String functionName) {
		loadAtomService(functionName);
		return funcMap.get(functionName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.atom.compiler.macro.func.IFunctionMacroTokenService#getFunction(java.lang.String)
	 */
	@Override
	public IARESResource getFunction(String functionName) {
		loadAtomService(functionName);
		return funcResourceMap.get(functionName);
	}
	/**
	 * 从缓存中加载原子函数
	 * @param serviceName
	 */
	private void loadAtomService(String functionName){
		if(!funcMap.containsKey(functionName)){
			ReferenceInfo refInfo =ReferenceManager.getInstance().getFirstReferenceInfo(project, IAtomRefType.ATOM_FUNCTION_CNAME, functionName, true);
			if(refInfo!=null){
				AtomFunction func = (AtomFunction) refInfo.getObject();
				if(func!=null){
					funcResourceMap.put(func.getChineseName(), refInfo.getResource());
					funcMap.put(func.getChineseName(), new FunctionCallMacroHandler(func.getChineseName(), refInfo.getResource()));
				}
			}
		}
	}

}
