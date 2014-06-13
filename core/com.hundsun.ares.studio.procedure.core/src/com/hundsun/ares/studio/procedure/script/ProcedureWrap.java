/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.script;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.script.BizInterfaceWrap;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.api.biz.cres.IInternalVarWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.IProcedureWrap;
import com.hundsun.ares.studio.procdure.Procedure;

/**
 * @author qinyuan
 *
 */
public class ProcedureWrap extends BizInterfaceWrap<Procedure> implements IProcedureWrap {

	private IInternalVarWrap[] varList;
	
	/**
	 * @param resource
	 */
	public ProcedureWrap(IARESResource resource) {
		super(resource);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.cres.ICRESBizWrap#getInternalVars()
	 */
	@Override
	public IInternalVarWrap[] getInternalVars() {
		if (varList == null) {
			List<IInternalVarWrap> vars = new ArrayList<IInternalVarWrap>();
			for (Parameter p : getOriginalInfo().getInternalVariables()) {
				vars.add(new InternalVarWrap(p, this.resource));
			}
			varList = (IInternalVarWrap[]) vars.toArray(new IInternalVarWrap[vars.size()]);
		}
		return varList;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.cres.ICRESBizWrap#getCode()
	 */
	@Override
	public String getCode() {
		return getOriginalInfo().getPseudoCode();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.script.BizInterfaceWrap#getInterface()
	 */
	@Override
	protected BizInterface getInterface() {
		return getOriginalInfo();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.base.ResourceWrapBase#getInfoClass()
	 */
	@Override
	public Class<Procedure> getInfoClass() {
		return Procedure.class;
	}

}
