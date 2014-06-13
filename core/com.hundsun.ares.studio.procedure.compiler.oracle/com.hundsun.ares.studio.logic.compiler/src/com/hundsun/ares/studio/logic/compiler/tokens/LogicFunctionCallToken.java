/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.tokens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.BizInterfaceParameterUtil;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.LogicService;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;
import com.hundsun.ares.studio.logic.compiler.util.LogicCompilerUtil;

/**
 * @author liaogc
 *
 */
public class LogicFunctionCallToken implements ICodeToken{
	private static String NL = ICodeToken.NEWLINE;
	private static String FORMAT_ADD_FIELD = "%s->AddField(\"%s\" %s);\r\n";
	private static String FORMAT_ADD_STR = "%1$s->AddStr(%2$s);	//%3$s \r\n";
	private static String FORMAT_GET_STR = "%s->GetString(\"%s\")";
	private static String FORMAT_GET_INT = "%s->GetInt(\"%s\")";
	private static String FORMAT_GET_DOUBLE = "%s->GetDouble(\"%s\")";
	private static String FORMAT_GET_CHAR = "%s->GetChar(\"%s\")";
	private static String FORMAT_GET_RAW = "%s->GetRaw(\"%s\", &%s)";
	protected String FORMAT_GET_STR_CALL_FUNCTION_END_GET_VALUE = "%1$s->GetStr(\"%2$s\"),sizeof(%3$s) - 1";
	private final static String RESULTSET_GETVALUE_CHARARRAY = "hs_strncpy(%1$s, lpResultSet%2$s->GetStr(\"%3$s\"), sizeof(%1$s) - 1);";
	private static int TYPE_INT =0;//类型为整型
	private static int TYPE_CHAR =1;//类型为字符
	private static int TYPE_STRING =2;//类型为字符串
	private static int TYPE_DOUBLE =3;//类型为float或者double
	private static int TYPE_RAW =4;//类型为RAW
	private static String FLAG_M="m";//
	private static String RESULT_SET_PREFIX = "lpResultSet";
	private static String X_FLAG_PLACEHOLDER = "___XXX___";
	private static final String FORMAT_USER_VARIABLE_SET = 
		"else\r\n{\r\n%s\r\n}\r\n";
	
	private static String NAME_SERVICE_PARENTRESULT = "lpInUnPackerParent";
	
	private static String NAME_SERVICE_INUNPACKER = "lpInUnPacker";
	private static String NAME_SERVICE_OUTPACKER = "lpOutPacker";
	private static String NAME_SERVICE_PACK_SERVICE = "lpPackService";
	private static String NAME_SERVICE_FUNC_INPACKER = "lpFuncInPacker";
	
	private AtomFunction logic;//调用者模型
	AtomFunction logicFunction;//被调用者模型
	private Map<String, String> defaultValueList ;//默认值列表
	private   Map<Object, Object> context;
	private IMacroToken macroToken;//宏
	private IARESProject project;
	private List<Parameter> inParameters ;//被调用的所有的输入参数
	private List<String> callInParameters ;//调用的所有的输入参数名
	private Map<String,String> resultsetParameters;//结果集字段列表
	private Set<String> inList ;//输入重定向列表
	private Set<Parameter> outList;//输出重定向列参数名列表
	private Set<String> outListName;//输出重定向列参数名列表
	private List<String> popVarList ;//伪代码列表
	
	public LogicFunctionCallToken(AtomFunction logic,AtomFunction logicFunction,Map<String, String> defaultValueList,List<Parameter> inParameters ,Set<String> inList ,Set<Parameter> outList,Map<String,String> resultsetParameters,IMacroToken macroToken,Map<Object, Object> context){
		this.logic = logic;
		this.logicFunction = logicFunction;
		this.defaultValueList = defaultValueList;
		this.context = context;
		this.macroToken = macroToken;
		this.inList =inList;
		this.outList = outList;
		this.resultsetParameters = resultsetParameters;
		this.outListName = getOutListName();
		this.inParameters =inParameters;
		
		
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return ICodeToken.CODE_TEXT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		this.project = (IARESProject) context.get(ILogicEngineContextConstant.Aresproject);
		this.callInParameters=getCallInParameters();//被调用的所有的输入参数
		this.popVarList = (List<String>)context.get(ILogicEngineContextConstant.PseudoCode_Para_LIST);
		
		StringBuffer code = new StringBuffer();
		code.append("//调用").append(logicFunction.getChineseName()).append(NL);
		code.append(getCallFunctionPackCode()).append(NL);//参数打包
		code.append("//将打包结果转为参数解包器供函数使用").append(NL);
		code.append(questPacker()).append(NL);//调用
		code.append(getErrorCode());//子函数调用错误处理
		String functionEndGetValue =getCallFunctionEndGetValue();
		if(StringUtils.isNotBlank(functionEndGetValue)){//输出重定向
			code.append(String.format(FORMAT_USER_VARIABLE_SET, functionEndGetValue));
			
		}
		return code.toString();
	}
	/**
	 * 参数打包
	 * @param parameters
	 * @return
	 */
	private String getCallFunctionPackCode(){
		StringBuffer pack = new StringBuffer();
		StringBuffer addFieldsCode = new StringBuffer();
		StringBuffer addFieldsValueCode = new StringBuffer();
		addFieldsCode.append(getAddFeildCodeOfSpecialParameter());
		addFieldsValueCode.append(getAddFeildValueCodeOfSpecialParameter());
		for(Parameter parameter:inParameters){
			Map<String,String> parameterInfo = getParameterInfo(parameter);
			addFieldsCode.append(getAddFieldCode(parameter,parameterInfo));//添加字段
			addFieldsValueCode.append(getAddFieldValueCode(parameter,parameterInfo)).append("//"+parameter.getId()).append(NL);//添加字段
			
		}
		String error_pathinfo_length = StringUtils.defaultIfBlank(this.getStandardFieldParameterInfo("error_pathinfo").get("length"), "500");
		addFieldsCode.append("lpFuncInPacker->AddField(\"error_pathinfo\",'S',"+error_pathinfo_length+");\r\n");//特殊处理
		addFieldsValueCode.append("lpFuncInPacker->AddStr(@error_pathinfo);\r\n");
		pack.append("lpFuncInPacker->BeginPack();").append(NL);
		pack.append(addFieldsCode.toString());
		pack.append(addFieldsValueCode.toString());
		pack.append("lpFuncInPacker->EndPack();").append(NL);
		
		return pack.toString();
	}
	private String questPacker(){
		StringBuffer packerAndUnpacker = new StringBuffer();
		String objectId = logicFunction.getObjectId();
		String functionName = "";
		if(StringUtils.isBlank(objectId)){
			objectId = logicFunction.getName();
			functionName = logicFunction.getName();
		}else {
			functionName = "F"+ objectId;
		}
		String lpOutVarName = "lpOut"+ objectId;
		String resultSetPrefix = StringUtils.EMPTY;
		String flag = StringUtils.defaultIfBlank(logic.getInterfaceFlag(), StringUtils.EMPTY).toLowerCase();
		if(flag.indexOf("j") != -1){
			resultSetPrefix = RESULT_SET_PREFIX + "_J";
		}else if(flag.indexOf("x") != -1) {//X标记
			resultSetPrefix = RESULT_SET_PREFIX ;//+ X_FLAG_PLACEHOLDER;
		}else{
			resultSetPrefix = RESULT_SET_PREFIX;
		}
		String lpResultSetVarName = resultSetPrefix+objectId;
		packerAndUnpacker.append(lpOutVarName).append("->BeginPack();").append(NL);
		
		packerAndUnpacker.append("iReturnCode = ");
		packerAndUnpacker.append(functionName).append("(");
		packerAndUnpacker.append("lpContext,");
		packerAndUnpacker.append("lpFuncInPacker->UnPack(),");
		packerAndUnpacker.append(lpOutVarName);
		packerAndUnpacker.append(");\r\n");
		
		packerAndUnpacker.append(lpOutVarName).append("->EndPack();").append(NL);
		packerAndUnpacker.append(lpResultSetVarName).append(" = ").append(lpOutVarName).append("->UnPack();").append(" //结果解包").append(NL);
		
		return packerAndUnpacker.toString();
	}
	
	/**
	 * 获取调用函数后将输入参数赋值给用户变量的代码
	 * @return
	 */
	private String getCallFunctionEndGetValue(){
	
		StringBuffer code = new StringBuffer();
		for(Parameter parameter:this.outList){
			 Map<String,String> parameterInfo = getParameterInfo(parameter);
			 String dataType = parameterInfo.get("type");
			 int type = TYPE_INT;
			 String format = RESULTSET_GETVALUE_CHARARRAY;
			 if (StringUtils.isNotBlank(dataType)) {
					if (TypeRule.typeRuleInt(dataType)) {
						format = FORMAT_GET_INT;
						type = TYPE_INT;
					} else if (TypeRule.typeRuleChar(dataType)) {
						format = FORMAT_GET_CHAR;
						if(logic instanceof LogicFunction){
							format = "conversion("+format+")";
						}
						type = TYPE_CHAR;
					} else if (TypeRule.typeRuleCharArray(dataType)) {
						format = RESULTSET_GETVALUE_CHARARRAY;
						type = TYPE_STRING;
					} else if (TypeRule.typeRuleDouble(dataType)) {
						format = FORMAT_GET_DOUBLE;
						type = TYPE_DOUBLE;
					} else if (TypeRule.typeRuleClob(dataType)) {
						type = TYPE_RAW;
					}
				}
			 String objectId = logicFunction.getObjectId();
			 String strResultSet = "lpResultSet"+objectId;
			 String localVariableName = defaultValueList.get(parameter.getId());
			 
			 if(type == TYPE_STRING) {
				 code.append(String.format(format, localVariableName,objectId,parameter.getId())).append("\r\n");
				 //code.append("hs_strncpy("+"@"+parameter.getId()+","+ String.format(format, StringUtils.isBlank(strResultSet) ? NAME_SERVICE_INUNPACKER : strResultSet, parameter.getId(),parameter.getId())+")").append(";").append(NL);
			 }else if(parameter.getParamType().getValue()==ParamType.OBJECT_VALUE){
				 code.append("p_"+parameter.getId()+" = "+strResultSet+"->GetRaw("+"\""+parameter.getId()+"\","+"&pi_"+parameter.getId()+");\r\n");
			 }else{
				 code.append(localVariableName+" = "+String.format(format, StringUtils.isBlank(strResultSet) ? NAME_SERVICE_INUNPACKER : strResultSet, parameter.getId())).append(";").append(NL);
			 }
			
		}
		return code.toString();
	
	}
	/**
	 * 生成错误处理代码
	 * @return
	 */
	private String getErrorCode(){
		StringBuffer errorCode = new StringBuffer();
		String error_info_length = StringUtils.defaultIfBlank(this.getStandardFieldParameterInfo("error_info").get("length"), "500");
		String error_pathinfo_length = StringUtils.defaultIfBlank(this.getStandardFieldParameterInfo("error_pathinfo").get("length"), "500");
		errorCode.append("if ( 0 != iReturnCode && iReturnCode != ERR_SYSWARNING )").append(NL);
		errorCode.append("{").append(NL);
		errorCode.append("hs_strncpy(").append("@error_info,").append("lpResultSet"+
				(StringUtils.isBlank(logicFunction.getObjectId())?logicFunction.getName():logicFunction.getObjectId())
				).append("->GetStr(\"error_info\"),"+error_info_length+");").append(NL);
		errorCode.append("@error_no = iReturnCode;").append(NL);
		errorCode.append("hs_strncpy(@error_pathinfo, ").append("lpResultSet"+
				(StringUtils.isBlank(logicFunction.getObjectId())?logicFunction.getName():logicFunction.getObjectId())
				).append("->GetStr(\"error_pathinfo\"),"+error_pathinfo_length+");").append(NL);
		
		String flag1 = StringUtils.defaultIfBlank(macroToken.getFlag(), StringUtils.EMPTY).toLowerCase();
		String flag2 = StringUtils.defaultIfBlank(logic.getInterfaceFlag(), StringUtils.EMPTY).toLowerCase();
		boolean hasGoEnd = flag1.indexOf("m") == -1 && flag2.indexOf("m") == -1;
		//对m标记处理
		if(hasGoEnd){
			errorCode.append("goto svr_end;").append(NL);
		}
		errorCode.append("}");
		return errorCode.toString();
	}
	
	
	/**
	 * 添加字段生成代码
	 * @param parameter
	 * @param parameterInfo
	 * @return
	 */
	private String getAddFieldCode(Parameter parameter,Map<String,String> parameterInfo ){
		
		String type = parameterInfo.get("type");
		String type_param = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(type)) {
			 if (TypeRule.typeRuleChar(type)) {
				type_param = ",'C'";
			}  else if (TypeRule.typeRuleFloat(type)|| TypeRule.typeRuleDouble(type)) {
				String length = parameterInfo.get("length");// 有效位
				String precision = parameterInfo.get("precision");// 精度
				type_param = ",'D',"
						+ String.valueOf(NumberUtils.toInt(length)
								+ NumberUtils.toInt(precision) + 2) + ","
						+ precision;
			}else if (TypeRule.typeRuleInt(type)) {
				type_param = ",'I'";
			} else if (TypeRule.typeRuleClob(type)|| TypeRule.typeRuleObject(type)|| parameter.getParamType().getValue() == ParamType.OBJECT_VALUE) {
				String length = "pi_" + parameter.getId();// 长度
				if (parameter.getParamType().getValue() == ParamType.OBJECT_VALUE) {
					String varName = this.defaultValueList.get(parameter
							.getId());
					if (StringUtils.endsWith(varName, "ResultSet")) {// 参数名不一致的情况下(仅对象)
						int index = StringUtils.lastIndexOf(varName,
								"ResultSet");
						String objectName = StringUtils.substring(varName, 0,
								index);
						objectName = objectName.replaceFirst("@", "");
						length = "pi_" + objectName;

					}else{
						  String objectName = "";
						  if(StringUtils.isNotBlank(varName)){
							  objectName =   varName.replaceFirst("@", "");
						  }else{
							  objectName = parameter.getId(); 
						  }
						 
						  length = "pi_"+objectName;
					}
				}
				type_param = ",'R'," + length;
			}else if (TypeRule.typeRuleCharArray(type)) {
				String length = parameterInfo.get("length");// 长度
				if (NumberUtils.toInt(length) > 255) {
					type_param = ",'S'," + length;
				} else {
					type_param = ",'S'";
				}
			} else{
				type_param = ",'S'";
			}
			
			return String.format(FORMAT_ADD_FIELD, NAME_SERVICE_FUNC_INPACKER,
					parameter.getId(), type_param);
		} else if (StringUtils.isBlank(type)) {
			if (parameter.getParamType().getValue() == ParamType.OBJECT_VALUE) {
				String length = "pi_" + parameter.getId();// 长度
				type_param = ",'R'," + length;
			}
			return String.format(FORMAT_ADD_FIELD, NAME_SERVICE_FUNC_INPACKER,
					parameter.getId(), type_param);
		}
		
		
		return  StringUtils.EMPTY ;
	}
	
	
	/**
	 * 给字段赋值生成代码
	 * @param parameter
	 * @param parameterInfo
	 * @return
	 */
	private String getAddFieldValueCode(Parameter parameter,Map<String,String> parameterInfo ){
		StringBuffer addFieldValueCode = new StringBuffer();
		//function_id特殊处理
		String function_id ="function_id";
		if (StringUtils.equals(parameter.getId(), function_id)) {
			StringBuffer functionIdCode =new StringBuffer();
			functionIdCode.append("if (");
			functionIdCode.append("lpInUnPacker->GetInt(\"function_id\")");
			functionIdCode.append(" == 0)\r\n");
			functionIdCode.append(NAME_SERVICE_FUNC_INPACKER);
			functionIdCode.append("->AddInt(");
			functionIdCode.append(StringUtils.isBlank(logicFunction.getObjectId())?logicFunction.getName():logicFunction.getObjectId());
			addPopVarList(context,"function_id");
			functionIdCode.append(");\r\nelse\r\n");
			addFieldValueCode.append(functionIdCode.toString());
		}
		if(StringUtils.equalsIgnoreCase(parameter.getId(), "audit_action")){
			//addPopVarList(context,"audit_action");
			return addFieldValueCode.append("lpFuncInPacker->AddChar(@audit_action);").toString();
		}
		boolean isFlagm = StringUtils.indexOf(StringUtils.defaultIfBlank( logicFunction.getInterfaceFlag(), StringUtils.EMPTY).toLowerCase(), FLAG_M)!=-1 || StringUtils.indexOf(StringUtils.defaultIfBlank(this.macroToken.getFlag(), StringUtils.EMPTY).toLowerCase(), FLAG_M)!=-1;
		if(isFlagm && StringUtils.equals(parameter.getId(), "error_pathinfo")){
			return  String.format(FORMAT_ADD_STR, NAME_SERVICE_FUNC_INPACKER, "v_error_pathinfo1", parameter.getId()) ;
		}
		String dataType = parameterInfo.get("type");
		int type = -1; 
		if (StringUtils.isNotBlank(dataType)) {
			if (TypeRule.typeRuleInt(dataType)) {
				type = TYPE_INT;
			} else if (TypeRule.typeRuleChar(dataType)) {
				type = TYPE_CHAR;
			} else if (TypeRule.typeRuleCharArray(dataType)) {
				type = TYPE_STRING;
			} else if (TypeRule.typeRuleDouble(dataType)) {
				type = TYPE_DOUBLE;
			} else if (TypeRule.typeRuleClob(dataType)||StringUtils.equals(dataType, "Object")||parameter.getParamType().getValue()==ParamType.OBJECT_VALUE) {
				type = TYPE_RAW;
			}
		}
		if(StringUtils.isBlank(dataType)){
			if(parameter.getParamType().getValue()==ParamType.OBJECT_VALUE){
				type = TYPE_RAW;
			}
		}
		if(this.defaultValueList.keySet().contains(parameter.getId())){//如果在输入重新定向中
			addFieldValueCode.append(getAddFieldValueFromDefaultValueList(type,parameter,parameterInfo));
		}else if(this.resultsetParameters.keySet().contains(parameter.getId())/*&& (logic instanceof LogicService) */&& !BizInterfaceParameterUtil.isOutputParameterWithIO(logicFunction, parameter.getId(),this.project)){//在结果集列表中
			//只有在LS中才可以用结果集变量初始化
			addFieldValueCode.append(getAddFieldValueFromResultset(type,parameter.getId(),parameterInfo));
		}else if(this.isCallInParametersContains(parameter)){//在调用中的输入参数中
			addFieldValueCode.append(getAddFieldValueFromCallInParameter(type,parameter,parameterInfo));
			//addPopVarList(context,parameter.getId());
		}else if(LogicCompilerUtil.isParameterINInternalVariablesByName(logic, parameter.getId())){//在内部变量中
			addFieldValueCode.append(getAddFieldValueFromINInternal(type,parameter,parameterInfo));
			//addPopVarList(context,parameter.getId());
		}else if(popVarList.contains(parameter.getId())){//在伪代码中
			addFieldValueCode.append(getAddFieldValueFromPopVar(type,parameter,parameterInfo));
		}else {//最默认值
			addFieldValueCode.append(getDefaultValue(type,parameter,parameterInfo));
		}
		
		return   addFieldValueCode.toString();
	}
	/**
	 * 特殊参数字段
	 * @return
	 */
	private String getAddFeildCodeOfSpecialParameter(){
		StringBuffer code = new StringBuffer();
		if(logic instanceof LogicFunction){
			if(((LogicFunction)logic).isIsTransFunc()){
				code.append("lpFuncInPacker->AddField(\"cancel_serialno\" );").append(NL);
			}
		}
		return code.toString();
	}
	/**
	 * 特殊参数字段值
	 * @return
	 */
	private String getAddFeildValueCodeOfSpecialParameter(){
		StringBuffer code = new StringBuffer();
		if(logic instanceof LogicFunction){
			if(((LogicFunction)logic).isIsTransFunc()){
				code.append("lpFuncInPacker->AddStr(@cancel_serialno);").append(NL);
			}
		}
		return code.toString();
	}
	
	/**
	 * 从输入重定现是获取值
	 * @param type
	 * @param parameter
	 * @return
	 */
	private String getAddFieldValueFromDefaultValueList(int type,Parameter parameter,Map<String,String> parameterInfo){
		String code = StringUtils.EMPTY;
		String prefix = "lpFuncInPacker";
		String varName = StringUtils.defaultIfBlank(this.defaultValueList.get(parameter.getId()), "") ;
		String realName = "";
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(ILogicEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		Set<String> initObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_INIT_VARIABLE_LIST);
		Set<String> noInitObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_NOINIT_VARIABLE_LIST);
		if(varName.startsWith("@")){
			realName = varName.replaceFirst("@", "");
		}else{
			realName = parameter.getId();
		}
		if(this.resultsetParameters.keySet().contains(realName)&& (logic instanceof LogicService)){
			return getAddFieldValueFromResultset(type,varName.replaceFirst("@", ""),parameterInfo);
		}
		else if(initObjectList.contains(realName)||noInitObjectList.contains(realName)){
			return code = prefix+"->AddRaw"+"("+"p_"+realName+",pi_" + realName+");";
		}
		if(varName.startsWith("@")){
			//addPopVarList(context,parameter.getId());
		}
		if(TYPE_INT==type ){
			code = prefix+"->AddInt"+"("+this.defaultValueList.get(parameter.getId())+");";
		}else if(TYPE_DOUBLE==type){
			code = prefix+"->AddDouble"+"("+this.defaultValueList.get(parameter.getId())+");";
		}else if(TYPE_CHAR==type ){
			String value = this.defaultValueList.get(parameter.getId());
			if(this.isConstant(value)){
				if(StringUtils.startsWith(value, "\"")){
                	value = StringUtils.removeStart(value, "\"");
                }
                if(StringUtils.endsWith(value, "\"")){
                	value = StringUtils.removeEnd(value,"\"");
                }
				if(!StringUtils.startsWith(value, "'")&& !StringUtils.endsWith(value, "'")){//如果不存在单引号
					value = "'"+value+"'";
				}
				
				
			}
			code = prefix+"->AddChar"+"("+value+");";
			
		}else if(TYPE_STRING==type ){
			String value = this.defaultValueList.get(parameter.getId());
			if(this.isConstant(value)){
				if(StringUtils.startsWith(value, "'")){
                	value = StringUtils.removeStart(value, "'");
                }
                if(StringUtils.endsWith(value, "'")){
                	value = StringUtils.removeEnd(value, "'");
                }
                //2013年7月16日12:34:01 char[]数组默认值有可能为“{0}”
                if(StringUtils.startsWith(value, "{") && StringUtils.endsWith(value, "}")){
                	value = StringUtils.removeStart(value, "{");
                	value = StringUtils.removeEnd(value, "}");
                	if(StringUtils.equals(value, "0")){
                		value = "";//置为空字符串
                	}
                }
	             
				if(!StringUtils.startsWith(value, "\"")&& !StringUtils.startsWith(value, "\"")){//如果不存在双引号
					value = "\""+value+"\"";
				}
				
			}
			code = prefix+"->AddStr"+"("+value+");";
		}	
		return code;
	}
	
	/**
	 * 从结果集获取值
	 * @param type
	 * @param parameterName
	 * @return
	 */
	private String getAddFieldValueFromResultset(int type,String parameterName,Map<String,String> parameterInfo){
		String code = StringUtils.EMPTY;
		String id = this.resultsetParameters.get(parameterName);
		String prefix = "lpFuncInPacker";
		
		if(TYPE_INT==type ){
			String lpResultSetStr = "lpResultSet"+id+"->GetInt"+"(\""+parameterName+"\")";
			code = prefix+"->AddInt"+"("+lpResultSetStr +");";
		}else if(TYPE_DOUBLE==type){
			String lpResultSetStr = "lpResultSet"+id+"->GetDouble"+"(\""+parameterName+"\")";
			code = prefix+"->AddDouble"+"("+lpResultSetStr +");";
		}else if(TYPE_CHAR==type ){
			String lpResultSetStr = "lpResultSet"+id+"->GetChar"+"(\""+parameterName+"\")";
			code = prefix+"->AddChar"+"("+lpResultSetStr +");";
		}else if(TYPE_STRING==type ){
			String lpResultSetStr = "lpResultSet"+id+"->GetStr"+"(\""+parameterName+"\")";
			code = prefix+"->AddStr"+"("+lpResultSetStr +");";
		}else if(TYPE_RAW==type ){
			String length = parameterInfo.get("length");
			if(StringUtils.isBlank(length)){
				length = "pi_"+parameterName;
			}
			String lpResultSetStr = "lpResultSet"+id+"->GetRaw"+"(\""+parameterName+"\",&"+length+")";
			code = prefix+"->AddRaw"+"("+lpResultSetStr +","+length+");";
		}
		return code;
	}
	
	
	/**
	 * 从被调用中的输入参数中获取值
	 * @param type
	 * @param parameter
	 * @return
	 */
	private String getAddFieldValueFromCallInParameter(int type,Parameter parameter,Map<String,String> parameterInfo){
		String code = StringUtils.EMPTY;
		String prefix = "lpFuncInPacker";
		String lpInUnPackerStr = "@"+parameter.getId();
		if(TYPE_INT==type ){
			code = prefix+"->AddInt"+"("+lpInUnPackerStr +");";
		}else if(TYPE_DOUBLE==type){
			code = prefix+"->AddDouble"+"("+lpInUnPackerStr +");";
		}else if(TYPE_CHAR==type ){
			code = prefix+"->AddChar"+"("+lpInUnPackerStr +");";
		}else if(TYPE_STRING==type ){
			code = prefix+"->AddStr"+"("+lpInUnPackerStr +");";
		}
		else if(TYPE_RAW==type ){
			String varName = StringUtils.defaultIfBlank(this.defaultValueList.get(parameter.getId()), "") ;
			if(StringUtils.endsWith(varName, "ResultSet")){

				  int index = StringUtils.lastIndexOf(varName, "ResultSet");
				  String objectName = StringUtils.substring(varName, 0, index);
				  objectName = objectName.replaceFirst("@", "");
				code = prefix+"->AddRaw"+"("+"p_"+objectName+","+"pi_"+objectName+");";
			}else{
				if(StringUtils.isNotBlank(varName)){
					if(StringUtils.startsWith(varName, "@")){
						String objectName = varName;
						  objectName = objectName.replaceFirst("@", "");
						return code = prefix+"->AddRaw"+"("+"p_"+objectName+","+"pi_"+objectName+");";
					}
				}
				code = prefix+"->AddRaw"+"("+"p_"+parameter.getId()+",pi_" + parameter.getId()+");";
			}
			
		}
		return code;
	}
	
	/**
	 * 从被调用中的内部取值
	 * @param type
	 * @param parameter
	 * @return
	 */
	private String getAddFieldValueFromINInternal(int type,Parameter parameter,Map<String,String> parameterInfo){

		String code = StringUtils.EMPTY;
		String prefix = "lpFuncInPacker";
		if(TYPE_INT==type ){
			code = prefix+"->AddInt"+"("+"@"+parameter.getId()+");";
		}else if(TYPE_DOUBLE==type){
			code = prefix+"->AddDouble"+"("+"@"+parameter.getId()+");";
		}else if(TYPE_CHAR==type ){
			code = prefix+"->AddChar"+"("+"@"+parameter.getId()+");";
			
		}else if(TYPE_STRING==type ){
			code = prefix+"->AddStr"+"("+"@"+parameter.getId()+");";
			
		}	
		return code;
	
	}
	
	/**
	 * 从伪代码变量获取值
	 * @param type
	 * @param parameter
	 * @return
	 */
	private String getAddFieldValueFromPopVar(int type,Parameter parameter,Map<String,String> parameterInfo){
		String code = StringUtils.EMPTY;
		String prefix = "lpFuncInPacker";
		if(TYPE_INT==type ){
			code = prefix+"->AddInt"+"("+"@"+parameter.getId()+");";
		}else if(TYPE_DOUBLE==type){
			code = prefix+"->AddDouble"+"("+"@"+parameter.getId()+");";
		}else if(TYPE_CHAR==type ){
			code = prefix+"->AddChar"+"("+"@"+parameter.getId()+");";
			
		}else if(TYPE_STRING==type ){
			code = prefix+"->AddStr"+"("+"@"+parameter.getId()+");";
		}else if(TYPE_RAW==type)	{
			code = prefix+"->AddRaw"+"("+"@"+parameter.getId()+",vi_"+parameter.getId()+");";
		}	
		return code;
	}
	
	/**
	 * 获取默认值
	 * @param type
	 * @param parameter
	 * @return
	 */
	private String getDefaultValue(int type,Parameter parameter,Map<String,String> parameterInfo){
		String code = StringUtils.EMPTY;
		String prefix = "lpFuncInPacker";
		String value = parameterInfo.get("value");
		if(TYPE_INT==type ){
			code = prefix+"->AddInt"+"("+value+");";
		}else if(TYPE_DOUBLE==type){
			code = prefix+"->AddDouble"+"("+value+");";
		}else if(TYPE_CHAR==type ){
			if(this.isConstant(value)){
				if(StringUtils.startsWith(value, "\"")){
                	value = StringUtils.removeStart(value, "\"");
                }
                if(StringUtils.endsWith(value, "\"")){
                	value = StringUtils.removeEnd(value,"\"");
                }
				if(!StringUtils.startsWith(value, "'")&& !StringUtils.endsWith(value, "'")){//如果不存在单引号
					value = "'"+value+"'";
				}
				
			}
			code = prefix+"->AddChar"+"("+value+");";
			
		}else if(TYPE_STRING==type ){
			if(this.isConstant(value)){
                if(StringUtils.startsWith(value, "'")){
                	value = StringUtils.removeStart(value, "'");
                }
                if(StringUtils.endsWith(value, "'")){
                	value = StringUtils.removeEnd(value, "'");
                }
                //2013年7月16日12:34:01 char[]数组默认值有可能为“{0}”
                if(StringUtils.startsWith(value, "{") && StringUtils.endsWith(value, "}")){
                	value = StringUtils.removeStart(value, "{");
                	value = StringUtils.removeEnd(value, "}");
                	if(StringUtils.equals(value, "0")){
                		value = "";//置为空字符串
                	}
                }
                
				if(!StringUtils.startsWith(value, "\"")&& !StringUtils.endsWith(value, "\"")){//如果不存在双引号
					value = "\""+value+"\"";
				}
				
			}
			code = prefix+"->AddStr"+"("+value+");";
		}	
		return code;
	}
	
	private Set<String> getOutListName(){
		Set<String> names = new HashSet<String>();
		for(Parameter parameter :this.outList){
			names.add(parameter.getId());
		}
		return names;
	}
	
	
	
	
	/**
	 * 是否是常量 
	 * @param parameter
	 * @return
	 */
	private boolean isConstant(String parameter){
		return !StringUtils.startsWith(parameter, "@");
		
	}
	
	
	/**
	 * 获取被调用函数所以的输入参数
	 * @return
	 */
	
	private List<String> getCallInParameters(){
		List<String> callInParameters = new ArrayList<String>();
		for (Parameter parameter :logic.getInputParameters()) {
			if(parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){
				List<Parameter> stdParameters = new ArrayList<Parameter>();
				parserParamGroup(parameter,stdParameters,context);
				for(Parameter p:stdParameters){
					callInParameters.add(p.getId());
				}
			}else{
				callInParameters.add(parameter.getId());
			}
		}
		for ( Parameter parameter :logic.getOutputParameters()) {
			if (StringUtils.indexOf(parameter.getFlags(), "IO") != -1) {
				if(parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){
					List<Parameter> stdParameters = new ArrayList<Parameter>();
					parserParamGroup(parameter,stdParameters,context);
					for(Parameter p:stdParameters){
						callInParameters.add(p.getId());
					}
				}else{
					callInParameters.add(parameter.getId());
				}
			}
		}
		
		return callInParameters;
	}
	/**
	 * 递归参数组参数
	 * @param gruopParam
	 * @param retParameter
	 * @param isInParameter
	 * @param context
	 */
	private void parserParamGroup( Parameter gruopParam,List<Parameter> retParameter,Map<Object, Object> context){
		String path = gruopParam.getType();
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
		try {
			IARESResource objectRes = project.findResource(path, "object");
			ARESObject aresObject = objectRes.getInfo(ARESObject.class);
			List<Parameter> properties = aresObject.getProperties();
			for(Parameter parameter:properties){
				if (parameter.getParamType().getValue() == ParamType.PARAM_GROUP_VALUE) {// 如果是参数组则递归调用
					parserParamGroup(parameter, retParameter, context);
				} else if (parameter.getParamType().getValue() == ParamType.OBJECT_VALUE) {
					retParameter.add(parameter);
				} else if (parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE) {
					retParameter.add(parameter);
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 获取参数信息
	 * @param parameter
	 * @return
	 */
	private Map<String,String> getParameterInfo(Parameter parameter){
		Map<String,String> parameterInfo = new HashMap<String,String>();
		if(parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE){//如果是标准字段
			parameterInfo = getStandardFieldParameterInfo(parameter.getId());
		}else if(parameter.getParamType().getValue() == ParamType.NON_STD_FIELD_VALUE){
			String defValue = parameter.getDefaultValue();
			String dataType = parameter.getRealType();
			String  length = "0";
			String  precision = "0";
			if(defValue == null){
				TypeDefaultValue typeDefValue = null;
				try {
					typeDefValue = MetadataServiceProvider.getTypeDefaultValueOfBizTypeByName(project, parameter.getType());
				} catch (Exception e) {
					ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
					String message = String.format("获取参数[%1s]出错.出错原因:该参数为非标准字段参数,但是参数类型对应的业务类型不存在", parameter.getId());
					manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				}
				if(typeDefValue != null){
					defValue = typeDefValue.getValue(MetadataServiceProvider.C_TYPE);
				}
			}
			parameterInfo.put("type", dataType);
			parameterInfo.put("value", defValue);
			parameterInfo.put("length", length);
			parameterInfo.put("precision", precision);
		}
		
		return parameterInfo;
		
	}
	/**
	 * 获取标准字段参数信息
	 * @param name
	 * @return
	 */
	private Map<String,String> getStandardFieldParameterInfo( String name){
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
				String precision =StringUtils.defaultIfBlank(busType.getPrecision(),"-1");
				dataType = dataType.replace("$L", length);
				parameterInfo.put("type", dataType);
				parameterInfo.put("value", defValue);
				parameterInfo.put("length", length);
				parameterInfo.put("precision", precision);
			
			}
		
		return parameterInfo;
	}
	
	 /**
	   * 添加变量
	   * @param context
	   * @param token
	   */
	  private void addPopVarList(Map<Object, Object> context,String varName){
		  List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		  popVarList.add(varName);		  
	  }
	  /**
	   * 判断参数是否在被调参数中(对象特殊处理可以参数名不一样)
	   * @param parameter
	   * @return
	   */
	  private boolean isCallInParametersContains(Parameter parameter){
		  if(parameter!=null && parameter.getParamType().getValue()!= ParamType.OBJECT_VALUE){
			  return this.callInParameters.contains(parameter.getId());
		  }else if(parameter!=null && parameter.getParamType().getValue()== ParamType.OBJECT_VALUE){
			  String varName = this.defaultValueList.get(parameter.getId());
			  if(StringUtils.endsWith(varName, "ResultSet")&& StringUtils.isNotBlank(varName)){
				  int index = StringUtils.lastIndexOf(varName, "ResultSet");
				  String objectName = StringUtils.substring(varName, 0, index);
				  objectName = objectName.replaceFirst("@", "");
				   return this.callInParameters.contains(objectName);
			  }else if(StringUtils.isNotBlank(varName)){
				  String objectName = varName.replaceFirst("@", "");
				  return this.callInParameters.contains(objectName);
			  }else{
				  String objectName = parameter.getId();
				  return this.callInParameters.contains(objectName);
			  }
		  }
		  return false;
		  
	  }
}
