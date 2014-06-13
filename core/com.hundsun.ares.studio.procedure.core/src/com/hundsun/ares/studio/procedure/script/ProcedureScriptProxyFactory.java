package com.hundsun.ares.studio.procedure.script;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory;

public class ProcedureScriptProxyFactory implements IScriptPoxyFactory {

	public ProcedureScriptProxyFactory() {
	}

	@Override
	public Object createPoxy(Object input, Map<Object, Object> context) {
		if (input instanceof IARESProject) {
			return new ProcedureScriptWrap((IARESProject) input);
		}
		return null;
	}

	@Override
	public Object createModuleProxy(Object input) {
		if (input instanceof IARESModule) {
			return new ProcedureModuleWrap((IARESModule)input);
		}
		return null;
	}

}
