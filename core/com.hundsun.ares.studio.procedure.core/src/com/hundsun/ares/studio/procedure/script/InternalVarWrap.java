package com.hundsun.ares.studio.procedure.script;

import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.script.ParameterWrap;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.api.biz.cres.IInternalVarWrap;

public class InternalVarWrap extends ParameterWrap implements IInternalVarWrap {

	public InternalVarWrap(Parameter p, IARESResource resource) {
		super(p, resource);
	}

}
