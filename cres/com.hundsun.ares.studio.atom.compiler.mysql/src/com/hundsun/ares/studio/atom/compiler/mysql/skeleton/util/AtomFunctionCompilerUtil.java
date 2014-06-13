/**
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.InternalParam;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.BizInterfaceParameterUtil;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.cres.extend.core.constants.ICresExtendConstants;
import com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
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
	 * 获取参数或变量（有可能在输入，输出或内部变量中)
	 * 
	 * @param atomFunction
	 *            原子函数
	 * @param parameterName
	 *            参数名
	 * @return Parameter
	 */
	public static Parameter getParameterINAtomFunctionParameterByName(AtomFunction atomFunction, String parameterName) {
		EList<InternalParam> internalVars = atomFunction.getInternalVariables();
		EList<Parameter> inputParams = atomFunction.getInputParameters();
		EList<Parameter> outputParams = atomFunction.getOutputParameters();
		for(int i = 0;i < internalVars.size();i++){
			if(StringUtils.equals(internalVars.get(i).getId(),parameterName)){
				return internalVars.get(i);
			}
		}
		for(int i = 0;i < inputParams.size();i++){
			if(StringUtils.equals(inputParams.get(i).getId(),parameterName)){
				return inputParams.get(i);
			}
		}
		for(int i = 0;i < outputParams.size();i++){
			if(StringUtils.equals(outputParams.get(i).getId(),parameterName)){
				return outputParams.get(i);
			}
		}
		return null;
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
			List<Parameter> parameters, IARESProject project,Map<Object, Object> context) {
		Map<String, String> businessType = new HashMap<String, String>();
		for (Parameter param : parameters) {
			if (param.getParamType() == ParamType.NON_STD_FIELD) {
				businessType.put(param.getId(), param.getRealType());
			} else if (param.getParamType() == ParamType.STD_FIELD) {
				businessType.put(param.getId(),getRealDataType(param, project,context));
			}
		}
		return businessType;
	}

	/**
	 * 获取参数真实类型
	 * @param param 输入参数或输出参数
	 * @param project 工程
	 * @context 上下文
	 * @return String C真实类型
	 */
	public static String getRealDataType(Parameter param, IARESProject project,Map<Object, Object> context) {
		String bizTypeName = "";
		if(param.getParamType() == ParamType.STD_FIELD)//标准字段参数
		{
			StandardField stdfield = MetadataServiceProvider.getStandardFieldByNameNoExp(project, param.getId());//getId为参数名，getName为中文名
			if(stdfield == null){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("参数[%1$s]对应的标准字段不存在。", param.getId());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//标准字段不存在时，继续生成，但最后将所有错误信息都抛出。
			}
			bizTypeName = stdfield.getDataType();//标准字段时，取标准字段对应业务类型
		}else if(param.getParamType() == ParamType.NON_STD_FIELD){//非标参数
			bizTypeName = param.getType();//非标字段时，取参数中直接输入的业务类型
		}
		int length = 0;
		BusinessDataType bizType = MetadataServiceProvider.getBusinessDataTypeByNameNoExp(project, bizTypeName);//这里不能使用param.getType()，否则会引起非标时，取不到业务类型的异常
		if(bizType == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("参数[%1$s]对应的业务类型[%2$s]不存在。", param.getId(),bizTypeName);
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//业务类型不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		try {
			length = Integer.parseInt(bizType.getLength()) + 1;//声明Char数组变量时，长度要加1
		} catch (Exception e) {
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("参数[%1$s]对应的业务类型:%2$s的长度为非法数字：%3$s。", param.getId(),bizTypeName,bizType.getLength());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//业务类型长度为非法数字时，继续生成，但最后将所有错误信息都抛出。
		}
		StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeByNameNoExp(project, bizType.getStdType());
		if(stdType == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("参数[%1$s]对应的业务类型[%2$s]中的标准类型[%3$s]不存在。", param.getId(),bizTypeName,bizType.getStdType());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//标准类型不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		String dataType = StringUtils.defaultIfBlank(stdType.getValue(MetadataServiceProvider.C_TYPE), "");
		dataType = dataType.replace("$L", length + "");
		return dataType;
	}
	
	/**
	 * 根据字段名称获取标准字段真实类型
	 * @param stdName 标准字段名称
	 * @param project 工程
	 * @context 上下文
	 * @return String C真实类型
	 */
	public static String getRealDataType(String stdName, IARESProject project,Map<Object, Object> context) {
		StandardField stdfield = MetadataServiceProvider.getStandardFieldByNameNoExp(project, stdName);//getId为参数名，getName为中文名
		if(stdfield == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("标准字段[%1$s]不存在。", stdName);
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//标准字段不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		String bizTypeName = stdfield.getDataType();
		int length = 0;
		BusinessDataType bizType = MetadataServiceProvider.getBusinessDataTypeByNameNoExp(project, bizTypeName);
		if(bizType == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("标准字段[%1$s]对应的业务类型[%2$s]不存在。", stdName,bizTypeName);
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//业务类型不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		try {
			length = Integer.parseInt(bizType.getLength()) + 1;//声明Char数组变量时，长度要加1
		} catch (Exception e) {
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("标准字段[%1$s]对应的业务类型:%2$s的长度为非法数字：%3$s。", stdName,bizTypeName,bizType.getLength());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//业务类型长度为非法数字时，继续生成，但最后将所有错误信息都抛出。
		}
		StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeByNameNoExp(project, bizType.getStdType());
		if(stdType == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("标准字段[%1$s]对应的业务类型[%2$s]中的标准类型[%3$s]不存在。", stdName,bizTypeName,bizType.getStdType());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//标准类型不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		String dataType = StringUtils.defaultIfBlank(stdType.getValue(MetadataServiceProvider.C_TYPE), "");
		dataType = dataType.replace("$L", length + "");
		return dataType;
	}
	
	/**
	 * 获取表字段真实类型
	 * @param column 表字段
	 * @param project 工程
	 * @context 上下文
	 * @return String C真实类型
	 */
	public static String getRealDataType(String tableName,TableColumn column, IARESProject project,Map<Object, Object> context) {
		String bizTypeName = "";
		if(column.getColumnType() == ColumnType.STD_FIELD)//标准字段参数
		{
			StandardField stdfield = MetadataServiceProvider.getStandardFieldByNameNoExp(project, column.getName());
			if(stdfield == null){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("数据库表[%1$s]中表字段[%2$s]对应的标准字段不存在。", tableName ,column.getName());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//标准字段不存在时，继续生成，但最后将所有错误信息都抛出。
			}
			bizTypeName = stdfield.getDataType();//标准字段时，取标准字段对应业务类型
		}else if(column.getColumnType() == ColumnType.NON_STD_FIELD){//非标参数
			bizTypeName = column.getDataType();//非标字段时，取参数中直接输入的业务类型
		}
		int length = 0;
		BusinessDataType bizType = MetadataServiceProvider.getBusinessDataTypeByNameNoExp(project, bizTypeName);//这里不能使用param.getType()，否则会引起非标时，取不到业务类型的异常
		if(bizType == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("数据库表[%1$s]中表字段[%2$s]对应的业务类型[%3$s]不存在。", tableName ,column.getName(),bizTypeName);
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//业务类型不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		try {
			length = Integer.parseInt(bizType.getLength()) + 1;//声明Char数组变量时，长度要加1
		} catch (Exception e) {
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("数据库表[%1$s]中表字段[%2$s]对应的业务类型[%3$s]的长度为非法数字[%4$s]。", tableName ,column.getName(),bizTypeName,bizType.getLength());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//业务类型长度为非法数字时，继续生成，但最后将所有错误信息都抛出。
		}
		StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeByNameNoExp(project, bizType.getStdType());
		if(stdType == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("数据库表[%1$s]中表字段[%2$s]对应的业务类型[%3$s]中的标准类型[%4$s]不存在。", tableName ,column.getName(),bizTypeName,bizType.getStdType());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//标准类型不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		String dataType = StringUtils.defaultIfBlank(stdType.getValue(MetadataServiceProvider.C_TYPE), "");
		dataType = dataType.replace("$L", length + "");
		return dataType;
	}
	
	/**
	 * 获取表字段真实默认值
	 * @param column 表字段
	 * @param project 工程
	 * @context 上下文
	 * @return String Oracle真实默认值（生成的是SQL语句，故需用Oracle）
	 */
	public static String getRealDefaultValue(String tableName,TableColumn column, IARESProject project,Map<Object, Object> context) {
		String bizTypeName = "";
		if(column.getColumnType() == ColumnType.STD_FIELD)//标准字段参数
		{
			StandardField stdfield = MetadataServiceProvider.getStandardFieldByNameNoExp(project, column.getName());
			if(stdfield == null){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("数据库表[%1$s]中表字段[%2$s]对应的标准字段不存在。", tableName ,column.getName());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//标准字段不存在时，继续生成，但最后将所有错误信息都抛出。
			}
			bizTypeName = stdfield.getDataType();//标准字段时，取标准字段对应业务类型
		}else if(column.getColumnType() == ColumnType.NON_STD_FIELD){//非标参数
			bizTypeName = column.getDataType();//非标字段时，取参数中直接输入的业务类型
		}
		BusinessDataType bizType = MetadataServiceProvider.getBusinessDataTypeByNameNoExp(project, bizTypeName);
		if(bizType == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("数据库表[%1$s]中表字段[%2$s]对应的业务类型[%3$s]不存在。", tableName ,column.getName(),bizTypeName);
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//业务类型不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		//如果参数中对应的默认值不为空，则默认值以该值为准，注意这里允许使用标准默认值，同时也可以是真实默认值。
		if(StringUtils.isNotEmpty(column.getDefaultValue())){
			TypeDefaultValue typpeDefValue = MetadataServiceProvider.getTypeDefaultValueByNameNoExp(project, column.getDefaultValue());
			//如果找不到标准默认值，则统一按真实默认值处理，用户输入什么，就输出什么
			if(typpeDefValue == null){
				return column.getDefaultValue();
			}else{
				String defValue = typpeDefValue.getValue(MetadataServiceProvider.MYSQL_TYPE);
				return defValue;
			}
		}
		//参数中默认值为空，取业务类型对应的标准默认值，这里必须是标准默认值，不存在要报错
		else{
			TypeDefaultValue typpeDefValue = MetadataServiceProvider.getTypeDefaultValueByNameNoExp(project, bizType.getDefaultValue());
			if(typpeDefValue == null){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("参数[%1$s]对应的业务类型[%2$s]中的标准默认值[%3$s]不存在。", column.getName(), bizTypeName,bizType.getDefaultValue());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//标准默认值不存在时，继续生成，但最后将所有错误信息都抛出。
			}
			String defValue = typpeDefValue.getValue(MetadataServiceProvider.MYSQL_TYPE);
			return defValue;
		}
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
