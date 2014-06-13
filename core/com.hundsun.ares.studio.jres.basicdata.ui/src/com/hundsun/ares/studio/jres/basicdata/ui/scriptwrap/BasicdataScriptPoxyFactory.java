/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.scriptwrap;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory;

/**
 * @author lvgao
 *
 */
public class BasicdataScriptPoxyFactory implements IScriptPoxyFactory{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IScriptPoxyFactory#createPoxy(java.lang.Object, java.util.Map)
	 */
	@Override
	public Object createPoxy(Object input, Map<Object, Object> context) {
		if(input instanceof IARESProject){
			return new BasicdataScriptWrapImpl((IARESProject)input);
		}
		return null;
	}

	@Override
	public Object createModuleProxy(Object input) {
		// TODO Auto-generated method stub
		return null;
	}

}
