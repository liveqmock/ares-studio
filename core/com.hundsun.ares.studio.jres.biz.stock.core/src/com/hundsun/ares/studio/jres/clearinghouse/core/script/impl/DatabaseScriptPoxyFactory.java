/**
 * 源程序名称：DatabaseScriptPoxyFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory;

/**
 * @author lvgao
 *
 */
public class DatabaseScriptPoxyFactory  implements IScriptPoxyFactory{

	@Override
	public Object createPoxy(Object input, Map<Object, Object> context) {
		if(input instanceof IARESProject){
			return new DatabaseScriptWrap((IARESProject)input);
		}
		return null;
	}

	@Override
	public Object createModuleProxy(Object input) {
		return null;
	}

}
