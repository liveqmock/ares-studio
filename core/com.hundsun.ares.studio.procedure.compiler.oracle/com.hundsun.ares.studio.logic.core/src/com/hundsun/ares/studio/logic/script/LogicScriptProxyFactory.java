package com.hundsun.ares.studio.logic.script;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory;

public class LogicScriptProxyFactory implements IScriptPoxyFactory {

	@Override
	public Object createPoxy(Object input, Map<Object, Object> context) {
		if (input instanceof IARESProject) {
			return new LogicScriptWrap((IARESProject) input);
		}
		return null;
	}

	@Override
	public Object createModuleProxy(Object input) {
		if (input instanceof IARESModule) {
			return new LogicModuleWrap((IARESModule)input);
		}
		return null;
	}

}
