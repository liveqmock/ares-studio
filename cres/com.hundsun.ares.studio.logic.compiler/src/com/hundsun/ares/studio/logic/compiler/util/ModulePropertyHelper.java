/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.util;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.cres.extend.core.constants.ICresExtendConstants;
import com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty;

/**
 * @author liaogc
 *
 */
public class ModulePropertyHelper {
	/**
	 * 获取模块属性
	 * @param module
	 * @return
	 * @throws Exception
	 */
	public static ModuleProperty getModuleProperty(IARESModule module) throws Exception{
		IARESResource res = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
		return res.getInfo(ModuleProperty.class);
	}

	
	/**
	 * 获取模块中的CRES属性页的SubSysID
	 * 如果不存在数据库，则抛出异常
	 * 
	 * @param resName
	 * @param type
	 * @return
	 */
	public static String getCRESModuleSubSysId(IARESModule aresModule) {

		if (aresModule != null) {
			try {
				Stack<IARESModule> stack = new Stack<IARESModule>();
				stack.push(aresModule);
				while (!stack.isEmpty()) {
					IARESModule module = stack.pop();
					IARESResource mr = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
					ModuleProperty mp = mr.getInfo(ModuleProperty.class);
					Object mProperty = mp.getMap().get(ICresExtendConstants.CRES_EXTEND_MOUDLE_PROPERTY);
					if (mProperty instanceof CresMoudleExtendProperty && StringUtils.isNotBlank(((CresMoudleExtendProperty) mProperty).getSubSysID())) {
						return ((CresMoudleExtendProperty) mProperty).getSubSysID();
					} else if (module.getParentModule() instanceof IARESModule) {
						stack.push((IARESModule) module.getParentModule());
					}
					//break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return StringUtils.EMPTY;
	}

}
