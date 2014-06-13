/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.util;

import com.hundsun.ares.studio.atom.compiler.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * @author qinyuan
 *
 */
public class LogicResourceCompilerUtil {

	/**
	 * 获取标准字段error_pathinfo类型长度
	 * @param context
	 * @throws Exception
	 */
	public static int getErrorPathInfoLen(IARESProject project)
			throws Exception {
		String type = AtomFunctionCompilerUtil.getRealDataType("error_pathinfo", project, MetadataServiceProvider.C_TYPE);
		if(TypeRule.typeRuleCharArray(type)) {
				return Integer.valueOf(TypeRule.getCharLength(type));
		}
		return 500;
	}
	
	/**
	 * 获取标准字段error_info类型长度
	 * @param context
	 * @throws Exception
	 */
	public static int getErrorInfoLen(IARESProject project)
			throws Exception {
		String type = AtomFunctionCompilerUtil.getRealDataType("error_info", project, MetadataServiceProvider.C_TYPE);
		if(TypeRule.typeRuleCharArray(type)) {
				return Integer.valueOf(TypeRule.getCharLength(type));
		}
		return 500;
	}
}
