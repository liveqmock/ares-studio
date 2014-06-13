/**
 * 
 */
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.CodeUtil;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * 生成包头token
 * 
 * @author yanwj06282
 *
 */
public class PackAddFieldHeadToken implements ICodeToken {

	private String[] param_0;
	private String param_1;
	private boolean isObjectPack;
	
	/**
	 * 
	 * @param param_0 包头参数，下标0
	 * @param param_1 包头参数，下标1
	 */
	public PackAddFieldHeadToken(String[] param_0 , String param_1,boolean isObjectPack) {
		this.param_0 = param_0;
		this.param_1 = param_1;
		this.isObjectPack = isObjectPack;
	}
	
	@Override
	public String getContent() {
		return null;
	}

	@Override
	public int getType() {
		return ICodeToken.CODE_TEXT;
	}

	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		return genParamCode(context);
	}
	
	/**
	 * 生成包头代码
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String genParamCode(Map<Object, Object> context) throws Exception{
		StringBuffer sb = new StringBuffer();
		String define = "lpOutPacker";
		
		if (StringUtils.isNotBlank(param_1)) {
			define = "v_" + StringUtils.replace(param_1, "@", "");
			sb.append(define + "->BeginPack(); \r\n");
		}
		//参数
		for(String fieldName : param_0){
			String type = getDataType(context, CodeUtil.trimTab(StringUtils.trim(fieldName)), MetadataServiceProvider.C_TYPE);
			if(null == type || StringUtils.isBlank(type)){
				throw new RuntimeException(fieldName+"对应的类型不存在!请确定"+fieldName+"是否是标准字段");
			}
			//TODO:参数还要修改，目前只支持默认
			sb.append(define + "->" + getMethodTemp(context ,type, CodeUtil.trimTab(StringUtils.trim(fieldName))) +"\r\n");
		}
		return sb.toString();
	}
	
	public static String getDataType(Map<Object, Object> context , String fieldName , String type) throws Exception{
		AtomFunction af = (AtomFunction) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		IARESProject project = (IARESProject)context.get(IAtomEngineContextConstantMySQL.Aresproject);
		if(isObjectParam(fieldName,context)){
			return "object";
		}
		
		for(Parameter param : af.getInternalVariables()){
			if (StringUtils.equals(param.getId(), fieldName)) {
				if (param.getParamType().getValue() == ParamType.STD_FIELD_VALUE) {
					return AtomFunctionCompilerUtil.getRealDataType(fieldName, project,context);
				}
			}
		}
		return AtomFunctionCompilerUtil.getRealDataType(fieldName, project,context);
	}
	
	private String getMethodTemp(Map<Object, Object> context ,String type ,String fieldName) throws Exception{
		String macro = "";
		IARESProject project = (IARESProject)context.get(IAtomEngineContextConstantMySQL.Aresproject);
		List<String> ps = new ArrayList<String>();
		ps.add(fieldName.trim());
		 if (TypeRule.typeRuleChar(type)) {
			macro = "AddField(\"{0}\", '''C''');";
		} else if (TypeRule.typeRuleDouble(type)||TypeRule.typeRuleFloat(type)) {
			Map<String,String> parameterInfo = AtomFunctionCompilerUtil.getStandardFieldParameterInfo(fieldName, project);
			String length = NumberUtils.toInt(StringUtils.defaultIfBlank(parameterInfo.get("length"), ""),0)+"";
			String precision = StringUtils.defaultIfBlank(parameterInfo.get("precision"), "");
			if (StringUtils.isNotBlank(length)&& StringUtils.isNotBlank(precision)) {
				ps.add(length);
				ps.add(precision);
			}else{
				throw new Exception("数据类型设置错误");
			}
			
			macro = "AddField(\"{0}\", '''D''', {1},{2});";
		} else if (TypeRule.typeRuleInt(type)) {// charArray类型
			macro = "AddField(\"{0}\", '''I''');";
		}else if (TypeRule.typeRuleClob(type)) {
			ps.add("p");
			macro = "AddField(\"{0}\", '''R''',{1}i_{0});";
		} else if (TypeRule.typeRuleCharArray(type) && TypeRule.greaterThan255(type)) {// charArray类型，且长度大于255
			Map<String,String> parameterInfo = AtomFunctionCompilerUtil.getStandardFieldParameterInfo(fieldName, project);
			String length = parameterInfo.get("length");
			ps.add(length);
			macro = "AddField(\"{0}\", '''S''', {1});";
		} else if (TypeRule.typeRuleCharArray(type)) {// charArray类型
			macro = "AddField(\"{0}\", '''S''');";
		}else {
			macro = "AddField(\"{0}\",'''S''');";
		}
		return MessageFormat.format(macro, ps.toArray(new String[0]));
	}
	
	/**
	 * 判断参数是否是对象
	 * @param paramName
	 * @param context
	 * @return
	 */
	private static boolean isObjectParam(String paramName, Map<Object, Object> context) {
		AtomFunction atomFunction = (AtomFunction) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		if( StringUtils.endsWith(paramName, "ResultSet")){
			String objectName = "";
			if(StringUtils.startsWith(paramName, "@")){
				String varName = StringUtils.replaceOnce(paramName, "@", "");
				int endIndex = StringUtils.lastIndexOf(varName, "ResultSet");
				 objectName = StringUtils.substring(varName, 0, endIndex);
			}else{
				int endIndex = StringUtils.lastIndexOf(paramName, "ResultSet");
				 objectName = StringUtils.substring(paramName, 0, endIndex);
			}
			
			EList<Parameter>  parameters = atomFunction.getOutputParameters();
			for(Parameter parameter:parameters){
				if(parameter.getParamType().getValue() == ParamType.OBJECT_VALUE && StringUtils.equals(parameter.getId(),objectName)){
					return true;
				}
			}
		}else if(StringUtils.startsWith(paramName, "@") ){
			int startIndex = StringUtils.indexOf(paramName, "@");
			String realParamName = StringUtils.substring(paramName, startIndex+1);
			EList<Parameter>  parameters = atomFunction.getOutputParameters();
			for(Parameter parameter:parameters){
				if(parameter.getParamType().getValue() == ParamType.OBJECT_VALUE && StringUtils.equals(parameter.getId(),realParamName)){
					return true;
				}
			}
		}
		return false;
	}
	
}
