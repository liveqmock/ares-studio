/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory;


/**
 * @author yanwj06282
 *
 */
public class MetadataScriptPoxyFactory implements IScriptPoxyFactory {

	@Override
	public Object createPoxy(Object input, Map<Object, Object> context) {
		if(input instanceof IARESProject){
			return new MetadataScriptWrapImpl((IARESProject)input);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory#createModuleProxy(java.lang.Object)
	 */
	@Override
	public Object createModuleProxy(Object input) {
		return null;
	}

}
