/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.script;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.api.biz.cres.IProcedureModuleWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.IProcedureWrap;
import com.hundsun.ares.studio.procdure.constants.IProcedureResType;

/**
 * @author qinyuan
 *
 */
public class ProcedureModuleWrap implements IProcedureModuleWrap{

	private IARESModule module;
	
	/**
	 * 
	 */
	public ProcedureModuleWrap(IARESModule module) {
		this.module = module;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.cres.IProcedureModuleWrap#getProcedures(boolean)
	 */
	@Override
	public IProcedureWrap[] getProcedures(boolean recursive) {
		List<IProcedureWrap> procedures = new ArrayList<IProcedureWrap>();
		IARESResource[] resources = module.getARESResources(IProcedureResType.PROCEDURE, recursive);
		for (IARESResource res : resources) {
			procedures.add(new ProcedureWrap(res));
		}
		return procedures.toArray(new IProcedureWrap[0]);
	}

}
