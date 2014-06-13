/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.ParamGroupUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * @author liaogc
 *
 */
public class PROCResultSetReturnTokenHelper {
	private String cursorName;//游标名称
	private String []sqlFields;//打包字段
	private Map<Object, Object> context;//止下文列表
	private IMacroToken macroToken ;//当前处理的宏
	
	public PROCResultSetReturnTokenHelper(Map<Object, Object> context,String cursorName,String []sqlFields){
		this.context = context;
		this.cursorName = cursorName;
		this.sqlFields = sqlFields; 
	}
	
	public String getPackAddField(){
		StringBuffer sb = new StringBuffer();
		String define = "lpOutPacker";
		
		//参数
		for(String fieldName : sqlFields){
			try{
			String type = getDataType(context, fieldName, MetadataServiceProvider.C_TYPE);
			//TODO:参数还要修改，目前只支持默认
			sb.append(define + "->" + getMethodTemp(context ,type, fieldName) +"\r\n");}
			catch(Exception e){
				//e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * 处理PackAddValue
	 * @return
	 */
	
	public String getPackAddFieldValue(){
		StringBuffer sb = new StringBuffer();
		String define = "lpOutPacker";
		
		for(String fieldName : sqlFields){
			try{
				String type =getDataType(context, fieldName,"c") ;
				if(TypeRule.typeRuleCharArray(type)){
					sb.append(define + "->" + tranTypeMethod(type) + "(trimb(@"+ fieldName + "_cur" + "));" + "//" + fieldName+ "\r\n");
				} else {
					sb.append(define + "->" + tranTypeMethod(type) + "(@"+ fieldName + "_cur" + ");" + "//" + fieldName+ "\r\n");
				}
			}
			
			catch(Exception e){
				//e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 处理PackAddValue
	 * @return
	 */
	
	public String getFetchCursor(){
		StringBuffer fetchCursor = new StringBuffer(100);
		fetchCursor.append("EXEC SQL FETCH ").append(cursorName).append(" INTO ").append(getFetchCursorVarString()).append(";");
		return fetchCursor.toString();
	}
	
	/**
	 * 获得FetchCursor变量
	 * @return
	 */
	private String getFetchCursorVarString(){
		StringBuffer varString = new StringBuffer();
		for(int i=1;i<=sqlFields.length;i++){
			 if(i!=sqlFields.length && sqlFields.length>1){
				varString.append(":").append("@").append(sqlFields[i-1] + "_cur").append(",");
			}else if(i==sqlFields.length){
				varString.append(":").append("@").append(sqlFields[i-1] + "_cur");
			}
			if(i%4==0){
				varString.append("\r\n");
			}
		}
		return varString.toString();
	}
	
	public static String getDataType(Map<Object, Object> context , String fieldName , String type) throws Exception{
		AtomFunction af = (AtomFunction) context.get(IAtomEngineContextConstant.ResourceModel);
		IARESProject project =(IARESProject)context.get(IAtomEngineContextConstant.Aresproject);
		List<Parameter> inputParameters = new ArrayList<Parameter>();
		ParamGroupUtil.parserParameters(af.getInputParameters(), inputParameters, project);
		for(Parameter param : inputParameters){
			if (StringUtils.equals(param.getId(), fieldName)) {
				return AtomFunctionCompilerUtil.getRealDataType(fieldName, project, MetadataServiceProvider.C_TYPE);
			}
		}
		List<Parameter> outputParameters = new ArrayList<Parameter>();
		ParamGroupUtil.parserParameters(af.getOutputParameters(), outputParameters, project);
		for(Parameter param : outputParameters){
			if (StringUtils.equals(param.getId(), fieldName)) {
				return AtomFunctionCompilerUtil.getRealDataType(fieldName, project, MetadataServiceProvider.C_TYPE);
			}
		}
		for(Parameter param : af.getInternalVariables()){
			if (StringUtils.equals(param.getId(), fieldName)) {
				if (param.getParamType().getValue() == ParamType.STD_FIELD_VALUE) {
					return AtomFunctionCompilerUtil.getRealDataType(fieldName, project, MetadataServiceProvider.C_TYPE);
				}
			}
		}
		Map<String,String> parameterInfo = getStandardFieldParameterInfo(fieldName,project);
		return StringUtils.defaultIfBlank(parameterInfo.get("type"), StringUtils.EMPTY);
		
	}
	
	/**
	 * 获取标准字段参数信息
	 * @param name
	 * @return
	 */
	private static Map<String,String> getStandardFieldParameterInfo( String name,IARESProject project ){
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
	
	
	
	private String getMethodTemp(Map<Object, Object> context ,String type ,String fieldName){
		String macro = "";
		List<String> ps = new ArrayList<String>();
		Map<String,String> parameterInfo = getStandardFieldParameterInfo(fieldName,(IARESProject)context.get(IAtomEngineContextConstant.Aresproject));
		String length = StringUtils.defaultIfBlank(parameterInfo.get("length"), "0");
		ps.add(fieldName);
		 if (TypeRule.typeRuleChar(type)) {
			macro = "AddField(\"{0}\", '''C''');";
		}else if (TypeRule.typeRuleDouble(type) ||TypeRule.typeRuleFloat(type)) {
			if (StringUtils.isNotBlank(type)) {
				String precision = StringUtils.defaultIfBlank(parameterInfo.get("precision"), "0");
				ps.add((String.valueOf(NumberUtils.toInt(length)+NumberUtils.toInt(precision))));
				ps.add(precision);
			}
			macro = "AddField(\"{0}\", '''D''', {1},{2});";
		}else if (TypeRule.typeRuleInt(type)) {
			macro = "AddField(\"{0}\",'''I''');";
		} else if (TypeRule.typeRuleClob(type)) {
			ps.add("p");
			macro = "AddField(\"{0}\", '''R''',{1}i_{0});";
		}else  if (TypeRule.typeRuleCharArray(type) && TypeRule.greaterThan255(type)) {// charArray类型，且长度大于255
			ps.add(length);
			macro = "AddField(\"{0}\", '''S''', {1});";
		} else if (TypeRule.typeRuleCharArray(type)) {
			macro = "AddField(\"{0}\", '''S''');";
		}else {
			macro = "AddField(\"{0}\",'''S''');";
		}
		return MessageFormat.format(macro, ps.toArray(new String[0]));
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
}
