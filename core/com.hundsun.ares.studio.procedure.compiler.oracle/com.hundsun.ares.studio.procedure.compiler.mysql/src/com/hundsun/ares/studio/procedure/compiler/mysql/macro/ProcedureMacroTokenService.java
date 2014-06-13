/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.mysql.macro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureResType;
import com.hundsun.ares.studio.procedure.compiler.mysql.macro.handlers.ProcedureCallMacroHandler;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author zhuyf
 *
 */
public class ProcedureMacroTokenService implements IProcedureMacroTokenService {
	
	IARESProject project;
	Map<String, IMacroTokenHandler> prcMap = new HashMap<String, IMacroTokenHandler>();
	Map<String, IARESResource> prcResourceMap = new HashMap<String, IARESResource>();
	
	public ProcedureMacroTokenService(IARESProject project){
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.atom.compiler.macro.procedure.IProcedureMacroTokenService#isProcedure(java.lang.String)
	 */
	@Override
	public boolean isProcedure(String procedureName) {
		loadProcedure(procedureName);
		return prcMap.containsKey(procedureName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.atom.compiler.macro.procedure.IProcedureMacroTokenService#getHandler(java.lang.String)
	 */
	@Override
	public IMacroTokenHandler getHandler(String procedureName) {
		loadProcedure(procedureName);
		return prcMap.get(procedureName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.atom.compiler.macro.procedure.IProcedureMacroTokenService#getProcedure(java.lang.String)
	 */
	@Override
	public IARESResource getProcedure(String procedureName) {
		loadProcedure(procedureName);
		return prcResourceMap.get(procedureName);
	}
	
	/**
	 * 从缓存中加载原子函数
	 * @param serviceName
	 */
	private void loadProcedure(String procedureName){
		if(!prcMap.containsKey(procedureName)){
			ReferenceInfo refInfo =ReferenceManager.getInstance().getFirstReferenceInfo(project, IProcedureResType.PROCEDURE, procedureName, true);
			if(refInfo!=null){
				Procedure procedure = (Procedure) refInfo.getObject();
				if(procedure!=null){
					prcResourceMap.put(procedure.getChineseName(), refInfo.getResource());
					prcMap.put(procedure.getChineseName(), new ProcedureCallMacroHandler(procedure.getChineseName(), refInfo.getResource()));
				}
			}
		}
	}

}
