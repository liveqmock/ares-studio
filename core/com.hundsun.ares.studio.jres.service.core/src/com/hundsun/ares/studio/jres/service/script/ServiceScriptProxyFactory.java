package com.hundsun.ares.studio.jres.service.script;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory;
import com.hundsun.ares.studio.jres.service.core.BizWrap;

public class ServiceScriptProxyFactory implements IScriptPoxyFactory {

	@Override
	public Object createPoxy(Object input, Map<Object, Object> context) {
		if (input instanceof IARESProject)
			return new BizWrap((IARESProject) input);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory#createModuleProxy(java.lang.Object)
	 */
	@Override
	public Object createModuleProxy(Object input) {
		if (input instanceof IARESModule) {
			return new BizModuleWrap((IARESModule) input);
		}
		return null;
	}

}
