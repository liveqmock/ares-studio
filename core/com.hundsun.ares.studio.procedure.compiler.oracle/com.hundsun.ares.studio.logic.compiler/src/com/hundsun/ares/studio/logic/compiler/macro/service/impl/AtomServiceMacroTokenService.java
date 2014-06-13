/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.macro.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.constants.IAtomRefType;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.logic.compiler.macro.handlers.AtomServiceCallMacroHandler;
import com.hundsun.ares.studio.logic.compiler.macro.service.IAtomServiceMacroTonkenService;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author qinyuan
 *
 */
public class AtomServiceMacroTokenService implements IAtomServiceMacroTonkenService{
	
	IARESProject project;
	Map<String, IMacroTokenHandler> funcMap = new HashMap<String, IMacroTokenHandler>();
	Map<String, IARESResource> funcResourceMap = new HashMap<String, IARESResource>();

	/**
	 * @param aresProject
	 */
	public AtomServiceMacroTokenService(IARESProject project) {
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.logic.compiler.macro.service.IAtomServiceMacroTonkenService#isAtomService(java.lang.String)
	 */
	@Override
	public boolean isAtomService(String serviceName) {
		loadAtomService(serviceName);
		return funcMap.containsKey(serviceName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.logic.compiler.macro.service.IAtomServiceMacroTonkenService#getHandler(java.lang.String)
	 */
	@Override
	public IMacroTokenHandler getHandler(String serviceName) {
		loadAtomService(serviceName);
		return funcMap.get(serviceName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.logic.compiler.macro.service.IAtomServiceMacroTonkenService#getAresResource(java.lang.String)
	 */
	@Override
	public IARESResource getAresResource(String serviceName) {
		loadAtomService(serviceName);
		return funcResourceMap.get(serviceName);
	}
	/**
	 * 从缓存中加载原子服务
	 * @param serviceName
	 */
	private void loadAtomService(String serviceName){
		if(!funcMap.containsKey(serviceName)){
			ReferenceInfo refInfo =ReferenceManager.getInstance().getFirstReferenceInfo(project, IAtomRefType.ATOM_SERVICE_CNAME, serviceName, true);
			if(refInfo!=null){
				AtomService service = (AtomService) refInfo.getObject();
				if(service!=null){
					funcResourceMap.put(service.getChineseName(), refInfo.getResource());
					funcMap.put(service.getChineseName(), new AtomServiceCallMacroHandler(service.getChineseName(), refInfo.getResource()));
				}
			}
			
		}
	}

}
