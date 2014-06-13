/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.script.impl;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory;

/**
 * 原子以,逻辑,过程脚本工厂
 * @author liaogc
 *
 */
public class GenCodeScriptPoxyFactory implements IScriptPoxyFactory {

		@Override
		public Object createPoxy(Object input, Map<Object, Object> context) {
			if (input instanceof IARESProject) {
				return new GenCodeScriptWrap((IARESProject) input);
			}
			return null;
		}

		@Override
		public Object createModuleProxy(Object input) {
			
			return null;
		}

}
