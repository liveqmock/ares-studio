/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.util;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.BizInterfaceParameterUtil;
import com.hundsun.ares.studio.core.IARESProject;

/**
 * 业务逻辑编译帮助类
 * @author liaogc
 *
 */
public class LogicCompilerUtil {
	/**
	 * 判断给定的参数名是否在业务逻辑的输入变量中
	 * @param logicResource 业务逻辑
	 * @param parameterName 参数名
	 * @return
	 */
	public static boolean isParameterINInputParameterByName(AtomFunction logicResource, String parameterName,IARESProject project) {
		return BizInterfaceParameterUtil.isInputParameter(logicResource, parameterName,project);
	}
	
	/**
	 * 判断给定的参数名是否在业务逻辑的输出变量中
	 * @param logicResource 业务逻辑
	 * @param parameterName 参数名
	 * @return
	 */
	public static boolean isParameterINOutputParameterByName(AtomFunction logicResource, String parameterName,IARESProject project) {
		return BizInterfaceParameterUtil.isOutputParameter(logicResource, parameterName,project);
	}
	
	/**
	 * 判断给定的参数名是否在业务逻辑的输入输出变量中
	 * @param procedure 业务逻辑
	 * @param parameterName 参数名
	 * @return
	 */
	public static boolean isParameterINInputAndOutputParameterByName(AtomFunction logicResource, String parameterName,IARESProject project) {
		return isParameterINInputParameterByName(logicResource, parameterName,project) ||
				isParameterINOutputParameterByName(logicResource, parameterName,project);
	}

	/**
	 * 判断给定的参数名是否在业务逻辑的内部变量中
	 * @param procedure 业务逻辑
	 * @param parameterName 参数名
	 * @return
	 */
	public static boolean isParameterINInternalVariablesByName(AtomFunction logicResource, String parameterName) {
		List<String> names = new ArrayList<String>();
		for (Parameter para : logicResource.getInternalVariables()) {
			names.add(para.getId());
		}
		return names.contains(parameterName);
	}
	
	/**
	 * 判断给定的参数名是否在业务逻辑的输入输出以及内部变量中
	 * @param procedure 业务逻辑
	 * @param parameterName 参数名
	 * @return
	 */
	public static boolean isParameterINLogicParameterByName(AtomFunction logicResource, String parameterName,IARESProject project) {
		return isParameterINInputParameterByName(logicResource, parameterName,project) ||
				isParameterINOutputParameterByName(logicResource, parameterName,project) ||
				isParameterINInternalVariablesByName(logicResource, parameterName);
	}
}
