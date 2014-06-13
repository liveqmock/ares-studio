package com.hundsun.ares.studio.logic.script;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.script.BizInterfaceWrap;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.api.biz.cres.IInternalVarWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicServiceWrap;
import com.hundsun.ares.studio.logic.LogicService;

public class LogicServiceWrap extends BizInterfaceWrap<LogicService> implements ILogicServiceWrap {
	
	private IInternalVarWrap[] varList;

	public LogicServiceWrap(IARESResource resource) {
		super(resource);
	}
	
	@Override
	public Class getInfoClass() {
		return LogicService.class;
	}
	
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
	
	@Override
	public void deleteInternalVar(String id){
		for (int i = 0; i < getOriginalInfo().getInternalVariables().size(); i++) {
			Parameter p = getOriginalInfo().getInternalVariables().get(i);
			if (StringUtils.equals(p.getId(), id)) {
				getOriginalInfo().getInternalVariables().remove(p);
				return;
			}
		}
	}

	@Override
	public String getCode() {
		return getOriginalInfo().getPseudoCode();
	}

	@Override
	protected BizInterface getInterface() {
		return getOriginalInfo();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicServiceWrap#getTimeOut()
	 */
	@Override
	public String getTimeOut() {
		// TODO Auto-generated method stub
		return getOriginalInfo().getTimeOut();
	}
}
