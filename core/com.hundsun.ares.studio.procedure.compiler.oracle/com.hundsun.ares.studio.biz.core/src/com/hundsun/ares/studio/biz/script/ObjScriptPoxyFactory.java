package com.hundsun.ares.studio.biz.script;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory;

public class ObjScriptPoxyFactory implements IScriptPoxyFactory {

	@Override
	public Object createPoxy(Object input, Map<Object, Object> context) {
		if (input instanceof IARESProject) {
			return new ObjectScriptWrapImpl((IARESProject) input);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory#createModuleProxy(java.lang.Object)
	 */
	@Override
	public Object createModuleProxy(Object input) {
		if (input instanceof IARESModule) {
			return new ObjectModuleWrap((IARESModule) input);
		}
		return null;
	}

}
