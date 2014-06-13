/**
 * 
 */
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.CodeUtil;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * 根据包头定义，生成包体
 * 
 * @author yanwj06282
 *
 */
public class PackAddFieldBodyToken implements ICodeToken {

	private String selfParam;
	private String params;
	private String yn;
	private String headFields;
	
	/**
	 * 
	 * @param selfParam 包体参数
	 * @param params 包头字段参数
	 */
	public PackAddFieldBodyToken(String selfParam ,String params,String yn,String  headFields) {
		this.selfParam = selfParam;
		this.params = params;
		this.yn = yn;
		this.headFields  = headFields;
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
		StringBuffer sb = new StringBuffer();
		String define = "lpOutPacker";
		
		if (StringUtils.isNotBlank(selfParam)) {
			define = "v_" + StringUtils.replace(selfParam, "@", "");
		}
		String[] pArray = params.split(",");
		String[] fields = headFields.split(",");
		//参数
		if(StringUtils.isNotBlank(params)){
			
			for(int i=0;i<pArray.length;i++ ){
				String fieldName = pArray[i].trim();
				//如果是对象
				if(isObjectParam(fieldName,context)){
					int startIndex = StringUtils.indexOf(fieldName, "@");
					int endIndex = StringUtils.lastIndexOf(fieldName, "ResultSet");
					if(endIndex==-1){
						endIndex = fieldName.length();
					}
					String objectName = StringUtils.substring(fieldName, startIndex+1, endIndex);
					sb.append(define+"->AddRaw("+"p_"+objectName+","+"pi_"+objectName+");");
				}else{
					//如果不是对象且是变量
					if(StringUtils.startsWith(fieldName.trim(), "@")){
						String type = PackAddFieldHeadToken.getDataType(context, CodeUtil.trimTab(StringUtils.trim(fieldName.replace("@", ""))),MetadataServiceProvider.C_TYPE);
						if(null == type || StringUtils.isBlank(type)){
							throw new RuntimeException(fieldName+"对应的类型不存在!请确定"+fieldName+"是否是标准字段");
						}
						sb.append(define + "->"+tranTypeMethod(type)+"(" + fieldName + "); \r\n");
					}else{
						//为常量
						if(pArray.length <= fields.length){
							String type = PackAddFieldHeadToken.getDataType(context, CodeUtil.trimTab(StringUtils.trim(fields[i])),MetadataServiceProvider.C_TYPE);
							if(null == type || StringUtils.isBlank(type)){
								throw new RuntimeException(fieldName+"对应的类型不存在!请确定"+fieldName+"是否是标准字段");
							}
							sb.append(define + "->"+tranTypeMethod(type)+"(" + fieldName + "); \r\n");
						}else{
						}
						
					}
					
				}
				
			}
		}
		
		//如果参数为[]则取打包头中字段变量
		if(StringUtils.isBlank(params)){
			for(int i=0;i<fields.length;i++ ){
				String fieldName = fields[i].trim();
				if(isObjectParam(fieldName,context)){
					int startIndex = StringUtils.indexOf(fieldName, "@");
					int endIndex = StringUtils.lastIndexOf(fieldName, "ResultSet");
					String objectName = StringUtils.substring(fieldName, startIndex+1, endIndex);
					sb.append(define+"->AddRaw("+"p_"+objectName+","+"pi_"+objectName+");");
				}else{
					String type = PackAddFieldHeadToken.getDataType(context, CodeUtil.trimTab(StringUtils.trim(fields[i])),MetadataServiceProvider.C_TYPE);
					if(null == type || StringUtils.isBlank(type)){
						throw new RuntimeException(fieldName+"对应的类型不存在!请确定"+fieldName+"是否是标准字段");
					}
					sb.append(define + "->"+tranTypeMethod(type)+"(" + "@"+fieldName + "); \r\n");
				}
				
			}
		}
		if(StringUtils.isNotBlank(selfParam) && (StringUtils.equalsIgnoreCase(yn, "Y")||StringUtils.isBlank(yn))){
			sb.append(define + "->EndPack(); \r\n");
		}else{
			sb.append("\r\n");
		}
		return sb.toString();
	}
	
	/**
	 * 根据相应的类型参数，获取对应的方法
	 * 
	 * @param type
	 * @return
	 */
	private String tranTypeMethod (String type){
		String typeMethod = "";
		if (TypeRule.typeRuleCharArray(type)) {
			typeMethod = "AddStr";
		}else if (TypeRule.typeRuleChar(type)) {
			typeMethod = "AddChar";
		}
		else if (TypeRule.typeRuleInt(type)) {
			typeMethod = "AddInt";
		}
		else if (TypeRule.typeRuleDouble(type)) {
			typeMethod = "AddDouble";
		}
		return typeMethod;
	}
	/**
	 * 判断参数是否是对象
	 * @param paramName
	 * @param context
	 * @return
	 */
	private boolean isObjectParam(String paramName, Map<Object, Object> context) {
		AtomFunction atomFunction = (AtomFunction) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		if(StringUtils.startsWith(paramName, "@") && StringUtils.endsWith(paramName, "ResultSet")){
			int startIndex = StringUtils.indexOf(paramName, "@");
			int endIndex = StringUtils.lastIndexOf(paramName, "ResultSet");
			String realParamName = StringUtils.substring(paramName, startIndex+1, endIndex);
			EList<Parameter>  parameters = atomFunction.getOutputParameters();
			for(Parameter parameter:parameters){
				if(parameter.getParamType().getValue() == ParamType.OBJECT_VALUE && StringUtils.equals(parameter.getId(),realParamName)){
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
