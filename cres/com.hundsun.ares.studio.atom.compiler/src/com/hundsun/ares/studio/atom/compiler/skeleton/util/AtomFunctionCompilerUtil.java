/**
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.atom.compiler.skeleton.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.BizInterfaceParameterUtil;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.cres.extend.core.constants.ICresExtendConstants;
import com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * 原子函数编译帮助类
 * 
 * @author qinyuan
 * 
 */
public class AtomFunctionCompilerUtil {

	/**
	 * 根据默认值的类型获取真实的默认值，如字符值需添加双引号
	 * 
	 * @param type
	 *            默认值类型
	 * @param defaultValue
	 *            默认值值
	 * @param project
	 *            工程
	 * @return 真实的默认值
	 * @throws Exception
	 */
	public static String getTrueDefaultValueByType(String type,
			String defaultValue, IARESProject project) throws Exception {
		if (TypeRule.typeRuleCharArray(type)) {
			if (defaultValue.startsWith("\"") && defaultValue.endsWith("\"")) {
				return defaultValue;
			}
			// 2013年8月21日14:36:49 字符串有可能以单引号出现
			if (defaultValue.startsWith("'") && defaultValue.endsWith("'")) {
				int length = defaultValue.length();
				if (length > 1) {
					return "\"" + defaultValue.substring(1, length - 1) + "\"";
				} else {// 就为字符“'”
					return defaultValue;
				}
			}

			if (MetadataServiceProvider.getConstantItemByName(project,
					defaultValue) == null
					&& MetadataServiceProvider.getErrorNoItemByName(project,
							defaultValue) == null) {// 不为用户常量,也不为标准错误号
				if (!defaultValue.startsWith("\"")) {
					defaultValue = "\"" + defaultValue;
				}
				if (!defaultValue.endsWith("\"")) {
					defaultValue += "\"";
				}
			}
			return defaultValue;
		} else if (TypeRule.typeRuleChar(type)) {
			if (defaultValue.startsWith("'") && defaultValue.endsWith("'")) {
				return defaultValue;
			}
			// 2013年8月21日14:37:15 字符有可能以双引号出现
			if (defaultValue.startsWith("\"") && defaultValue.endsWith("\"")) {
				int length = defaultValue.length();
				if (length > 1) {
					return "'" + defaultValue.substring(1, length - 1) + "'";
				} else {
					return defaultValue;
				}
			}

			if (MetadataServiceProvider.getConstantItemByName(project,
					defaultValue) == null
					&& MetadataServiceProvider.getErrorNoItemByName(project,
							defaultValue) == null) {
				if (!defaultValue.startsWith("'")) {
					defaultValue = "'" + defaultValue;
				}
				if (!defaultValue.endsWith("'")) {
					defaultValue += "'";
				}
			}
			return defaultValue;
		} else if (TypeRule.typeRuleInt(type)) {
			return defaultValue;
		} else if (TypeRule.typeRuleClob(type)) {
			return "NULL";
		} else if (TypeRule.typeRuleDouble(type)) {
			return defaultValue;
		} else if (TypeRule.typeRulePacker(type)) {
			return "NULL";
		} else {
			throw new Exception(String.format("没有对实际数据类型：[%s]进行处理", type));
		}
	}

	/**
	 * 判断给定的参数名是否在原子函数的输入变量中
	 * 
	 * @param atomFunction
	 *            原子函数
	 * @param parameterName
	 *            参数名
	 * @return
	 */
	public static boolean isParameterINInputParameterByName(
			AtomFunction atomFunction, String parameterName,
			IARESProject project) {
		return BizInterfaceParameterUtil.isInputParameter(atomFunction,
				parameterName, project);
	}

	/**
	 * 判断给定的参数名是否在原子函数的输入出变量中
	 * 
	 * @param atomFunction
	 *            原子函数
	 * @param parameterName
	 *            参数名
	 * @return
	 */
	public static boolean isParameterINOutputParameterByName(
			AtomFunction atomFunction, String parameterName,
			IARESProject project) {
		return BizInterfaceParameterUtil.isOutputParameter(atomFunction,
				parameterName, project);
	}

	/**
	 * 判断给定的参数名是否在原子函数的输入输出变量中
	 * 
	 * @param atomFunction
	 *            原子函数
	 * @param parameterName
	 *            参数名
	 * @return
	 */
	public static boolean isParameterINInputAndOutputParameterByName(
			AtomFunction atomFunction, String parameterName,
			IARESProject project) {
		return isParameterINInputParameterByName(atomFunction, parameterName,
				project)
				|| isParameterINOutputParameterByName(atomFunction,
						parameterName, project);
	}

	/**
	 * 判断给定的参数名是否在原子函数的内部变量中
	 * 
	 * @param atomFunction
	 *            原子函数
	 * @param parameterName
	 *            参数名
	 * @return
	 */
	public static boolean isParameterINInternalVariablesByName(
			AtomFunction atomFunction, String parameterName) {
		List<String> names = new ArrayList<String>();
		for (Parameter para : atomFunction.getInternalVariables()) {
			names.add(para.getId());
		}
		return names.contains(parameterName);
	}

	/**
	 * 判断给定的参数名是否在原子函数的输入输出以及内部变量中
	 * 
	 * @param atomFunction
	 *            原子函数
	 * @param parameterName
	 *            参数名
	 * @return
	 */
	public static boolean isParameterINAtomFunctionParameterByName(
			AtomFunction atomFunction, String parameterName,
			IARESProject project) {
		return isParameterINInputParameterByName(atomFunction, parameterName,
				project)
				|| isParameterINOutputParameterByName(atomFunction,
						parameterName, project)
				|| isParameterINInternalVariablesByName(atomFunction,
						parameterName);
	}

	/**
	 * 获取参数的真实类型，生成Map返回
	 * 
	 * key：参数名 value：参数真实类型
	 * 
	 * @param 参数列表
	 * @param project
	 * @return
	 */
	public static Map<String, String> getParamterBusinessType2Map(
			List<Parameter> parameters, IARESProject project) {
		Map<String, String> businessType = new HashMap<String, String>();
		for (Parameter param : parameters) {
			if (param.getParamType() == ParamType.NON_STD_FIELD) {
				businessType.put(param.getId(), param.getRealType());
			} else if (param.getParamType() == ParamType.STD_FIELD) {
				businessType.put(
						param.getId(),
						getRealDataType(param.getId(), project,
								MetadataServiceProvider.C_TYPE));
			}
		}
		return businessType;
	}

	/**
	 * 根据标准字段名称获得真实类型
	 * 
	 * @param stdName
	 * @param project
	 * @return
	 */
	public static String getRealDataType(String stdName, IARESProject project,
			String type) {
		if (StringUtils.isBlank(type)) {
			type = MetadataServiceProvider.C_TYPE;
		}
		StandardDataType stdType = null;
		try {
			stdType = MetadataServiceProvider
					.getStandardDataTypeOfStdFieldByName(project, stdName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BusinessDataType busType = null;
		try {
			busType = MetadataServiceProvider
					.getBusinessDataTypeOfStdFieldByName(project, stdName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ((stdType != null) && (busType != null))// 标准字段
		{
			String dataType = StringUtils.defaultIfBlank(
					stdType.getValue(type), "");
			int length = 0;
			if (StringUtils.isNotBlank(busType.getLength())) {
				try {
					length = NumberUtils.toInt(busType.getLength(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return dataType = dataType.replace("$L", length + "");
			}
			return dataType;
			/*
			 * if(busType.getPrecision() != null){ int precision = 0; try {
			 * precision = NumberUtils.toInt(busType.getPrecision(), 0) ; }
			 * catch (Exception e) { e.printStackTrace(); } dataType =
			 * dataType.replace("$P", precision + ""); return dataType;
			 * 
			 * }
			 */

		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 获取标准字段参数信息
	 * @param name
	 * @return
	 */
	public static Map<String,String> getStandardFieldParameterInfo( String name,IARESProject project){
		Map<String,String> parameterInfo = new HashMap<String,String>();
			StandardDataType stdType = null;
			try {
				stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(project, name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			TypeDefaultValue typpeDefValue = null;
			try {
				typpeDefValue = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName(project, name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			BusinessDataType busType = null;
			try {
				busType = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(project, name);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
			if((stdType != null) && ( typpeDefValue!= null) && ( busType!= null))//标准字段
			{
				String dataType = stdType.getValue(MetadataServiceProvider.C_TYPE);
				String defValue = typpeDefValue.getValue(MetadataServiceProvider.C_TYPE);
				String  length = StringUtils.defaultIfBlank(busType.getLength(), "0");
				String precision =StringUtils.defaultIfBlank(busType.getPrecision(),"0");
				dataType = dataType.replace("$L", length);
				parameterInfo.put("type", dataType);
				parameterInfo.put("value", defValue);
				parameterInfo.put("length", length);
				parameterInfo.put("precision", precision);
			
			}
		
		return parameterInfo;
	}

	/**
	 * 获取AS AF的数据库属性，如果本资源不存在，则去模块属性中获取
	 * 
	 * @param project
	 * @param database
	 * @param resName
	 * @param type
	 * @return
	 */
	public static String getAtomDatabase(IARESProject project, String database,
			String chineseName, String type, String flag) {
		if (StringUtils.isNotBlank(database)) {
			return database;
		}
		database = getCRESModuleDatabase(project, chineseName, type);
		/*
		 * if (StringUtils.isBlank(database) &&
		 * !StringUtils.equalsIgnoreCase(flag, "r")) { throw new
		 * RuntimeException(chineseName + ": 数据库必须设置"); }
		 */
		return database;
	}

	/**
	 * 获取模块中的CRES属性页的数据库 如果不存在数据库，则抛出异常
	 * 
	 * @param project
	 * @param resName
	 * @param type
	 * @return
	 */
	public static String getCRESModuleDatabase(IARESProject project,
			String resName, String type) {
		ReferenceManager manager = ReferenceManager.getInstance();

		ReferenceInfo ref = manager.getFirstReferenceInfo(project, type,
				resName, true);
		if (ref != null) {
			try {
				IARESResource res = ref.getResource();
				Stack<IARESModule> stack = new Stack<IARESModule>();
				stack.push(((IARESModule) res.getModule()));
				while (!stack.isEmpty()) {
					IARESModule module = stack.pop();
					IARESResource mr = module
							.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
					ModuleProperty mp = mr.getInfo(ModuleProperty.class);
					Object mProperty = mp.getMap().get(
							ICresExtendConstants.CRES_EXTEND_MOUDLE_PROPERTY);
					if (mProperty != null
							&& mProperty instanceof CresMoudleExtendProperty
							&& StringUtils
									.isNotBlank(((CresMoudleExtendProperty) mProperty)
											.getDataBaseName())) {
						return ((CresMoudleExtendProperty) mProperty)
								.getDataBaseName();
					} else if (module.getParentModule() instanceof IARESModule) {
						stack.push((IARESModule) module.getParentModule());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return StringUtils.EMPTY;
	}

}
