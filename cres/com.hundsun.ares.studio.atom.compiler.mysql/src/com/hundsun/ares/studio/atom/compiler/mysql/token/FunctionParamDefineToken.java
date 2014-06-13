/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.InternalParam;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.token.InternalParameterArrayHelper;
import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.ParamGroupUtil;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IParamDefineHelper;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.procdure.Procedure;

/**
 * @author zhuyf
 *
 */
public class FunctionParamDefineToken implements ICodeToken {
	//private IParamDefineHelper defineHelper=null ;
	private AtomFunction atomFunction =null;
	private Map<Object, Object> context;
	private static List<String> specialsParams = new ArrayList<String>();
	{
		specialsParams.add("lpContext");
		specialsParams.add("lpInUnPacker");
		specialsParams.add("lpOutPacker");
	}
	//特殊变量定义帮助类
	SpecialParamDefineHelper specialParamDefineHelper = null;
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
        this.context = context;
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		List<String> pseudoObjectParaList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_OBJECT_PARA_LIST);
		
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		
		//变量定义帮助类
		IParamDefineHelper defineHelper = (IParamDefineHelper)context.get(IAtomEngineContextConstantMySQL.PARAM_DEFINE_HELPER);
		
		specialParamDefineHelper = new SpecialParamDefineHelper();//特殊变量定义帮助类初始化
		
		atomFunction = (AtomFunction) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		

		EList<InternalParam> internalVars = atomFunction.getInternalVariables();
		EList<Parameter> inputParams = atomFunction.getInputParameters();
		EList<Parameter> outputParams = atomFunction.getOutputParameters();

		
		//资源的输入输出参数加入到公共区域中，以便后续处理，提高访问效率。
		for(int i = 0;i < inputParams.size();i++){
			Parameter parameter = inputParams.get(i);
			String paramName = parameter.getId();
			if(parameter.getParamType().getValue() != ParamType.PARAM_GROUP_VALUE){
				helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_IN_OUT_PARAM_LIST,paramName);
			}
			if((parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE) || (parameter.getParamType().getValue() == ParamType.NON_STD_FIELD_VALUE) ){
				if(!popVarList.contains(paramName)){
					popVarList.add(paramName);
				}
			}else if(parameter.getParamType().getValue() == ParamType.OBJECT_VALUE){
				pseudoObjectParaList.add(parameter.getId());
				popVarList.remove(parameter.getId());
				
			}else if( parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){//如果参数是参数组
				List<Parameter> stdParameters = new ArrayList<Parameter>();
				parserParamGroup(parameter,stdParameters,true,1,context);
				for(Parameter stdParameter:stdParameters){
					helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_IN_OUT_PARAM_LIST,stdParameter.getId());
					if(!popVarList.contains(stdParameter.getId())){
						popVarList.add(stdParameter.getId());

					}

				}

			}
		}
		for(int i = 0;i < outputParams.size();i++){
			Parameter parameter = outputParams.get(i);
			String paramName = parameter.getId();
			if(parameter.getParamType().getValue() != ParamType.PARAM_GROUP_VALUE){
				helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_IN_OUT_PARAM_LIST,paramName);
			}
			if((parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE) || (parameter.getParamType().getValue() == ParamType.NON_STD_FIELD_VALUE) ){
				if(!popVarList.contains(paramName)){
					popVarList.add(paramName);
				}
			}else if(parameter.getParamType().getValue() == ParamType.OBJECT_VALUE){
				pseudoObjectParaList.add(parameter.getId());
				popVarList.remove(parameter.getId());
				
			}else if( parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){//如果参数是参数组
				List<Parameter> stdParameters = new ArrayList<Parameter>();
				parserParamGroup(parameter,stdParameters,false,1,context);
				for(Parameter stdParameter:stdParameters){
					helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_IN_OUT_PARAM_LIST,stdParameter.getId());
					if(!popVarList.contains(stdParameter.getId())){
						popVarList.add(stdParameter.getId());

					}

				}

			}
		}
		for(int i = 0;i < internalVars.size();i++){
			String paramName = internalVars.get(i).getId();
			if(!popVarList.contains(paramName)){
				popVarList.add(paramName);
			}
		}
		
		IARESProject project = (IARESProject)context.get(IAtomEngineContextConstantMySQL.Aresproject);
		StringBuffer codeBuffer = new StringBuffer();

		codeBuffer.append("//IF2PackSvr声明及初始化\r\n");
		codeBuffer.append("IF2PackSvr * lpPackService = lpContext->GetF2PackSvr();\r\n");	
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_LP_PACK_SERVICE_LIST, "lpPackService");
		if(inputParams.size() > 0){
			codeBuffer.append("//输入参数初始化" + ITokenConstant.NL);
		}
		StringBuffer paramGroupBuffer = new StringBuffer();
		for(int i = 0;i < inputParams.size();i++){
			Parameter p = inputParams.get(i);
			codeBuffer.append(getInParameterDefineInitCodeBuffer(p,project));
		}
		//定义并初始化需要从lpInUnPacker初始化的对象(由宏产生)
		Set<String> initObjectList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_IN_OBJECT_INIT_VARIABLE_LIST);
		for(String objectParam:initObjectList){
			if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, objectParam)){
				codeBuffer.append(getObjectParamDefineInitCodeStr(objectParam,context,objectParam,true));
				defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, objectParam);
				pseudoObjectParaList.add(objectParam);
				popVarList.remove(objectParam);
			}
			
		}
		
		//只定义但不初始化需要从lpInUnPacker初始化的输入对象(由宏产生)
		Set<String> noInitObjectList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_IN_OBJECT_NOINIT_VARIABLE_LIST);
		for(String objectParam:noInitObjectList){
			if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, objectParam)){
			codeBuffer.append(getObjectParamDefineInitCodeStr(objectParam,context,objectParam,false));
			defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, objectParam);
			pseudoObjectParaList.add(objectParam);
			popVarList.remove(objectParam);
		   }
		}
		
		if(outputParams.size() > 0){
			codeBuffer.append("//输出参数初始化" + ITokenConstant.NL);
		}
		for(int i = 0;i < outputParams.size();i++){
			Parameter p = outputParams.get(i);
			if(defineHelper.canInit(IParamDefineHelper.STD, p.getId())){
				String outParamDefineCode  = "";
				if(p.getFlags().equalsIgnoreCase("IO")){//IO输出参数，需调用输入参数定义及初始化方法
					outParamDefineCode = getInParameterDefineInitCodeBuffer(p,project);
				}else{
					outParamDefineCode = getOutParameterDefineCodeBuffer(p,project);
				}
				if(StringUtils.isNotBlank(outParamDefineCode)){
					defineHelper.addInit(IParamDefineHelper.STD, p.getId());
					codeBuffer.append(outParamDefineCode);
				}
			}
		}
		//输出参数对象初始化(定义由宏加入的输出对象)
		Set<String> initOutObjectList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_OUT_OBJECT_INIT_VARIABLE_LIST);
		for(String objectParam:initOutObjectList){
			if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, objectParam)){
			    codeBuffer.append(getOutObjectParamDefineInitCodeStr(objectParam,context,objectParam));
			    defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, objectParam);
			    pseudoObjectParaList.add(objectParam);
			    popVarList.remove(objectParam);
			}
		}
		
		if(internalVars.size() > 0){
			codeBuffer.append("//内部变量初始化" + ITokenConstant.NL);
		}
		for(int i = 0;i < internalVars.size();i++){
			InternalParam var = internalVars.get(i);
			if(defineHelper.canInit(IParamDefineHelper.STD, var.getId())){
				codeBuffer.append(getVarDefineCodeBuffer(var,project));
				defineHelper.addInit(IParamDefineHelper.STD, var.getId());
			}
		}
		
		//伪代码
		for(int i = 0;i < popVarList.size();i++){
			String fieldName = popVarList.get(i);
			//特殊变量，也不需要声明，后面会自动处理。
			if(!specialsParams.contains(fieldName) && !specialParamDefineHelper.isSpecialParam(fieldName) && (fieldName != null) && defineHelper.canInit(IParamDefineHelper.STD, fieldName)){//如未声明，则添加
				StandardField stdfield = MetadataServiceProvider.getStandardFieldByNameNoExp(project, fieldName);
				//游标变量
				if(fieldName.endsWith("_cur") && (stdfield == null)){
					codeBuffer.append(getPopVarDefineCodeBuffer(fieldName.replace("_cur",""),project,"_cur"));//游标变量定义
					defineHelper.addInit(IParamDefineHelper.STD, fieldName);
				}else{
					if(stdfield == null){
						String message = String.format("伪代码变量[%1$s]对应的标准字段不存在。", fieldName);
						throw new Exception(String.format(message,fieldName));
					}else{
						codeBuffer.append(getPopVarDefineCodeBuffer(fieldName,project,""));//普通变量定义
						defineHelper.addInit(IParamDefineHelper.STD, fieldName);
					}
				}
			}
		}
		//特殊的伪代码中非标准字段声明
		specialVarIntoPopVarList(popVarList);
		codeBuffer.append(specialParamDefineHelper.getSpecialParamsDefineCodeStr(project, context));
		
		//添加参数组始化代码
		if(StringUtils.isNotBlank(paramGroupBuffer.toString())){
			codeBuffer.append(paramGroupBuffer.toString());
		}
		
		//添加error_path_info的特殊处理
		codeBuffer.append(getErrorPathInfoDefineCodeStr(context));
		//返回结果集的过程调用列表
		Set<String> rsPrcCallList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_PROCEDURE_CALL_RSRETURN);
		//未返回结果集的过程调用列表
		Set<String> nrsPrcCallList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_PROCEDURE_CALL_NOTRSRETURN);
		Set<String> funcCallList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_FUNC_CALL);
		if (funcCallList.size() > 0 || (rsPrcCallList.size() > 0) || (nrsPrcCallList.size() > 0)) {
			codeBuffer.append("hs_strncpy(@error_pathinfo_tmp,@error_pathinfo,500);\r\n");//函数调用，过程调用，将当前错误路径，用临时变量保存
		}
		
		
		return codeBuffer.toString();
	}
	/**
	 * @return
	 */
	private String getErrorPathInfoDefineCodeStr(Map<Object, Object> context) {
		IARESProject project = (IARESProject)context.get(IAtomEngineContextConstantMySQL.Aresproject);
		String length = "500";//error_pathinfo默认长度500
		StandardField stdField = MetadataServiceProvider.getMetadataModelByName(project, "error_pathinfo", IMetadataRefType.StdField, StandardField.class);
		if(stdField != null){
			BusinessDataType bType = MetadataServiceProvider.getMetadataModelByName(project, stdField.getDataType(), IMetadataRefType.BizType, BusinessDataType.class);
			if(bType != null  && !StringUtils.isBlank(bType.getLength())){
				length = bType.getLength();
			}
		}
		AtomFunction af = (AtomFunction) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		String error_path_info_str  = 
			"hs_strncpy(@error_pathinfo,conversion((char *)lpInUnPacker->GetStr(\"error_pathinfo\"))," + length + ");\r\n" +
			"hs_strncat(@error_pathinfo,\"->" + 
			(StringUtils.isBlank(af.getObjectId())?af.getName():"F"+af.getObjectId()) + "()\"," + length + ");\r\n";
		return error_path_info_str;
	}

	/**
	 * 
	 */
	private void specialVarIntoPopVarList(List<String> popVarList) {
		// TODO Auto-generated method stub
		popVarList.add("error_no");//error_no
		popVarList.add("error_info");//error_info
		popVarList.add("error_id");//error_id
		popVarList.add("error_sysinfo");//error_sysinfo
		popVarList.add("error_pathinfo");//error_pathinfo
	}
	
	/**
	 * 获取输入参数定义语句，处理标准字段参数或非标参数或对象或参数组
	 * @param param 输入参数
	 * @param project 工程
	 * @return String 输入参数定义语句
	 */
	private String getInParameterDefineInitCodeBuffer(Parameter param,IARESProject project){
		StringBuffer codeBuffer = new StringBuffer();
		IParamDefineHelper defineHelper = (IParamDefineHelper)context.get(IAtomEngineContextConstantMySQL.PARAM_DEFINE_HELPER);
		if(defineHelper.canInit(IParamDefineHelper.STD, param.getId()) && ((param.getParamType() == ParamType.STD_FIELD) || (param.getParamType() == ParamType.NON_STD_FIELD)))//标准字段参数或非标参数
		{
			String bizTypeName = "";
			if(param.getParamType() == ParamType.STD_FIELD)//标准字段参数
			{
				StandardField stdfield = MetadataServiceProvider.getStandardFieldByNameNoExp(project, param.getId());//getId为参数名，getName为中文名
				if(stdfield == null){
					ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
					String message = String.format("输入参数[%1$s]对应的标准字段不存在。", param.getId());
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
				String message = String.format("输入参数[%1$s]对应的业务类型[%2$s]不存在。", param.getId(),bizTypeName);
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//业务类型不存在时，继续生成，但最后将所有错误信息都抛出。
			}
			try {
				length = Integer.parseInt(bizType.getLength()) + 1;//声明Char数组变量时，长度要加1
			} catch (Exception e) {
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("输入参数[%1$s]对应的业务类型:%2$s的长度为非法数字：%3$s。", param.getId(),bizTypeName,bizType.getLength());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//业务类型长度为非法数字时，继续生成，但最后将所有错误信息都抛出。
			}
			StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeByNameNoExp(project, bizType.getStdType());
			if(stdType == null){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("输入参数[%1$s]对应的业务类型[%2$s]中的标准类型[%3$s]不存在。", param.getId(),bizTypeName,bizType.getStdType());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//标准类型不存在时，继续生成，但最后将所有错误信息都抛出。
			}
			String dataType = StringUtils.defaultIfBlank(stdType.getValue(MetadataServiceProvider.C_TYPE), "");
			dataType = dataType.replace("$L", length + "");
			if(TypeRule.typeRuleChar(dataType)){
				codeBuffer.append(String.format(PARAM_DEFINE_INIT_CHAR,param.getId()));
				defineHelper.addInit(IParamDefineHelper.STD, param.getId());
			}else if(TypeRule.typeRuleCharArray(dataType)){
				codeBuffer.append(getOutParameterDefineCodeBuffer(param,project));
				//声明及初始化Char数组变量时，长度要加1
				codeBuffer.append(String.format(PARAM_INIT_STR,param.getId(),length));
				defineHelper.addInit(IParamDefineHelper.STD, param.getId());
			}
			else
			{
				if (TypeRule.typeRuleInt(dataType)) {
					codeBuffer.append(String.format(PARAM_DEFINE_INIT,dataType, param.getId(),"Int"));
					defineHelper.addInit(IParamDefineHelper.STD, param.getId());
				} else if (TypeRule.typeRuleDouble(dataType)) {
					codeBuffer.append(String.format(PARAM_DEFINE_INIT,dataType,param.getId(), "Double"));
					defineHelper.addInit(IParamDefineHelper.STD, param.getId());
				}else if (TypeRule.typeRuleClob(dataType)) {
					codeBuffer.append(getClobParamDefineInitCodeStr(param.getId()));
					defineHelper.addInit(IParamDefineHelper.STD, param.getId());
				}else if (TypeRule.typeRulePacker(dataType)) {
					codeBuffer.append(String.format(PARAM_DEFIN_UNPACKER,param.getId() ));
					defineHelper.addInit(IParamDefineHelper.STD, param.getId());
				}else {
					ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
					String message = String.format(String.format("输入参数[%1$s]对应的数据类型[%2$s]没有对应的初始化方式。",param.getId(),dataType));
					manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
					return "";//标准类型没有对应的初始化方式时，继续生成，但最后将所有错误信息都抛出。
				}
			}
		}
		else if(param.getParamType() == ParamType.OBJECT)//对象参数
		{
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
			
			if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, param.getId())){
				codeBuffer.append(getObjectParamDefineInitCodeStr(param.getId(),context,param.getId(),true));
				defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, param.getId());
				helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_IN_OBJECT_INIT_VARIABLE_LIST, param.getId());
				List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
				popVarList.remove(param.getId());
			}
		}
		else if(param.getParamType() == ParamType.PARAM_GROUP)//参数组
		{
			String path = param.getType();
			codeBuffer.append("//初始化输入参数组参数("+path+")"+"\r\n");
			List<Parameter> stdParameters = new ArrayList<Parameter>();
			parserParamGroup(param,stdParameters,true,1,context);
			for(Parameter stdParameter:stdParameters){
				codeBuffer.append(getInParameterDefineInitCodeBuffer(stdParameter,project));	
			}
			codeBuffer.append("\r\n");
		}else{
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("参数[%1$s]对应的参数类型未知。", param.getId());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//参数类型未知时，继续生成，但最后将所有错误信息都抛出。
		}
		return codeBuffer.toString();
	}

	/**
	 * 获取输出参数定义语句，处理标准字段参数或非标参数或对象或参数组
	 * 由于内部变量定义时，标准字段变量及非数组非标变量定义方式与输出参数定义方式一致，故可复用此方法完成这两种类型内部变量的定义语句
	 * 由于输入参数定义时，字符串类型的输入参数的定义方式与输出参数定义方式一致，故可复用此方案完成字符串类型输入参数的定义语句
	 * @param param 
	 * 1、输出参数
	 * 2、内部变量定义时，标准字段变量及非数组非标变量
	 * 3、输入参数定义时，字符串类型的输入参数
	 * @param project 工程
	 * @return String 输出参数定义语句
	 */
	private String getOutParameterDefineCodeBuffer(Parameter param,IARESProject project){
		StringBuffer codeBuffer = new StringBuffer();
		if((param.getParamType() == ParamType.STD_FIELD) || (param.getParamType() == ParamType.NON_STD_FIELD))//标准字段参数或非标参数
		{
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
			if(TypeRule.typeRuleCharArray(dataType)){//字符串变量定义
				codeBuffer.append(String.format(PARAM_DEFINE_STR,param.getId(),length));
			}else{
				//如果参数中对应的默认值不为空，则默认值以该值为准，注意这里允许使用标准默认值，同时也可以是真实默认值。
				if(StringUtils.isNotEmpty(param.getDefaultValue())){
					TypeDefaultValue typpeDefValue = MetadataServiceProvider.getTypeDefaultValueByNameNoExp(project, param.getDefaultValue());
					//如果找不到标准默认值，则统一按真实默认值处理，用户输入什么，就输出什么
					if(typpeDefValue == null){
						codeBuffer.append(String.format(PARAM_DEFINE,dataType,param.getId(),param.getDefaultValue()));
					}else{
						String defValue = typpeDefValue.getValue(MetadataServiceProvider.C_TYPE);
						codeBuffer.append(String.format(PARAM_DEFINE,dataType,param.getId(),defValue));
					}
				}
				//参数中默认值为空，取业务类型对应的标准默认值，这里必须是标准默认值，不存在要报错
				else{
					TypeDefaultValue typpeDefValue = MetadataServiceProvider.getTypeDefaultValueByNameNoExp(project, bizType.getDefaultValue());
					if(typpeDefValue == null){
						ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
						String message = String.format("参数[%1$s]对应的业务类型[%2$s]中的标准默认值[%3$s]不存在。", param.getId(), bizTypeName,bizType.getDefaultValue());
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
						return "";//标准默认值不存在时，继续生成，但最后将所有错误信息都抛出。
					}
					String defValue = typpeDefValue.getValue(MetadataServiceProvider.C_TYPE);
					codeBuffer.append(String.format(PARAM_DEFINE,dataType,param.getId(),defValue));
				}
			}
		}
		else if(param.getParamType() == ParamType.OBJECT)//对象参数
		{
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
			IParamDefineHelper defineHelper = (IParamDefineHelper)context.get(IAtomEngineContextConstantMySQL.PARAM_DEFINE_HELPER);
			if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, param.getId())){
				codeBuffer.append(getOutObjectParamDefineInitCodeStr(param.getId(),context,param.getId()));
				defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, param.getId());
				helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_OUT_OBJECT_INIT_VARIABLE_LIST, param.getId());
				List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
				List<String> pseudoObjectParaList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_OBJECT_PARA_LIST);
				pseudoObjectParaList.add(param.getId());
				popVarList.remove(param.getId());
			}
		}
		else if(param.getParamType() == ParamType.PARAM_GROUP)//参数组
		{
			IParamDefineHelper defineHelper = (IParamDefineHelper)context.get(IAtomEngineContextConstantMySQL.PARAM_DEFINE_HELPER);
			List<Parameter> stdParameters = new ArrayList<Parameter>();
			parserParamGroup(param, stdParameters, false,1, context);
			for (Parameter stdParameter : stdParameters) {
				if (defineHelper.canInit(IParamDefineHelper.STD,stdParameter.getId())) {
					String varDefine = getOutParameterDefineCodeBuffer(stdParameter, project);
					if(StringUtils.isNotBlank(varDefine)){
						codeBuffer.append(varDefine);
						defineHelper.addInit(IParamDefineHelper.STD,stdParameter.getId());
					}
				}
			}
			codeBuffer.append("\r\n");
		}else{
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("参数[%1$s]对应的参数类型未知。", param.getId());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//参数类型未知时，继续生成，但最后将所有错误信息都抛出。
		}
		return codeBuffer.toString();
	}
	
	/**
	 * 获取内部变量定义语句，处理标准字段变量或非标变量，注意这里不处理对象变量与参数组变量
	 * @param param 内部变量
	 * @param project 工程
	 * @return String 内部变量定义语句
	 */
	private String getVarDefineCodeBuffer(InternalParam var,IARESProject project){
		StringBuffer codeBuffer = new StringBuffer();
		if(var != null){
			if(var.getParamType().getValue() == ParamType.NON_STD_FIELD_VALUE){
				//如果内部变量是二维的数组
				if(InternalParameterArrayHelper.isArrayParameter(var)){
					String bizTypeName = InternalParameterArrayHelper.getArrayBusType(var);//去除长度
					int length = 0;
					BusinessDataType bizType = MetadataServiceProvider.getBusinessDataTypeByNameNoExp(project, bizTypeName);//这里用去除长度后的业务类型
					if(bizType == null){
						ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
						String message = String.format("内部变量[%1$s]对应的业务类型[%2$s]不存在。", var.getId(),bizTypeName);
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
						return "";//业务类型不存在时，继续生成，但最后将所有错误信息都抛出。
					}
					try {
						length = Integer.parseInt(bizType.getLength()) + 1;//声明Char数组变量时，长度要加1
					} catch (Exception e) {
						ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
						String message = String.format("内部[%1$s]对应的业务类型:%2$s的长度为非法数字：%3$s。", var.getId(),bizTypeName,bizType.getLength());
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
						return "";//业务类型长度为非法数字时，继续生成，但最后将所有错误信息都抛出。
					}
					StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeByNameNoExp(project, bizType.getStdType());
					if(stdType == null){
						ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
						String message = String.format("内部变量[%1$s]对应的业务类型[%2$s]中的标准类型[%3$s]不存在。", var.getId(),bizTypeName,bizType.getStdType());
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
						return "";//标准类型不存在时，继续生成，但最后将所有错误信息都抛出。
					}
					String dataType = StringUtils.defaultIfBlank(stdType.getValue(MetadataServiceProvider.C_TYPE), "");
					boolean hasTwoArray = false;//是否可以是二维数组
					if(StringUtils.indexOf(dataType, "$L")>-1){//如果有$L那么可定义成二组数据
						hasTwoArray = true;
					}else {
						if(TypeRule.typeRuleChar(dataType)){//对于字符要特殊处理
							length = 2;
							hasTwoArray = true;
						}
					}
					dataType = dataType.replace("$L", length + "");
					IParamDefineHelper defineHelper = (IParamDefineHelper)context.get(IAtomEngineContextConstantMySQL.PARAM_DEFINE_HELPER);
					if(defineHelper.canInit(IParamDefineHelper.STD, var.getId())){
					    if(hasTwoArray){
					    	 codeBuffer.append(String.format(PARAM_DEFINE_ARRAY2,InternalParameterArrayHelper.getArrayDataType(dataType),var.getId(),InternalParameterArrayHelper.getArrayLength(var),length));
							 defineHelper.addInit(IParamDefineHelper.STD, var.getId());
							 codeBuffer.append(String.format(PARAM_DEFINE_INIT_ARRAY2,var.getId(),InternalParameterArrayHelper.getArrayDataType(dataType),InternalParameterArrayHelper.getArrayLength(var),length)); 
							
					    }else{
					    	 codeBuffer.append(String.format(PARAM_DEFINE_ARRAY1,InternalParameterArrayHelper.getArrayDataType(dataType),var.getId(),InternalParameterArrayHelper.getArrayLength(var)));
					    	 defineHelper.addInit(IParamDefineHelper.STD, var.getId());
					    	 codeBuffer.append(String.format(PARAM_DEFINE_INIT_ARRAY1,var.getId(),InternalParameterArrayHelper.getArrayDataType(dataType),InternalParameterArrayHelper.getArrayLength(var))); 
						}
					}
				}else{
					//如果是非标变量且非数组，其声明方式与输出参数定义方式一致
					codeBuffer.append(getOutParameterDefineCodeBuffer(var,project));
				}
			}
			else if(var.getParamType().getValue() == ParamType.STD_FIELD_VALUE){
				//如果是标准字段，其声明方式与输出参数定义方式一致
				codeBuffer.append(getOutParameterDefineCodeBuffer(var,project));
			}
		}
		return codeBuffer.toString();
	}
	
	/**
	 * 获取伪代码变量定义语句，只处理标准字段（包括去除_cur的游标变量）
	 * @param fieldName 标准字段名称
	 * @param project 工程
	 * @param suffix 变量后缀，一般情况下为空，当处理_cur游标变量时，才需要传入
	 * @return String 伪代码变量定义语句
	 */
	private String getPopVarDefineCodeBuffer(String fieldName,IARESProject project,String suffix){
		StringBuffer codeBuffer = new StringBuffer();
		StandardField stdfield = MetadataServiceProvider.getStandardFieldByNameNoExp(project, fieldName);//伪代码变量，必须是标准字段
		if(stdfield == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("伪代码变量[%1$s]对应的标准字段不存在。", fieldName);
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//标准字段不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		int length = 0;
		BusinessDataType bizType = MetadataServiceProvider.getBusinessDataTypeByNameNoExp(project, stdfield.getDataType());
		if(bizType == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("伪代码变量[%1$s]对应的业务类型[%2$s]不存在。", fieldName,stdfield.getDataType());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//业务类型不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		try {
			length = Integer.parseInt(bizType.getLength()) + 1;//声明Char数组变量时，长度要加1
		} catch (Exception e) {
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("伪代码变量[%1$s]对应的业务类型:%2$s的长度为非法数字：%3$s。", fieldName,stdfield.getDataType(),bizType.getLength());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//业务类型长度为非法数字时，继续生成，但最后将所有错误信息都抛出。
		}
		StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeByNameNoExp(project, bizType.getStdType());
		if(stdType == null){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("伪代码变量[%1$s]对应的业务类型[%2$s]中的标准类型[%3$s]不存在。", fieldName,stdfield.getDataType(),bizType.getStdType());
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			return "";//标准类型不存在时，继续生成，但最后将所有错误信息都抛出。
		}
		String dataType = StringUtils.defaultIfBlank(stdType.getValue(MetadataServiceProvider.C_TYPE), "");
		dataType = dataType.replace("$L", length + "");
		if(TypeRule.typeRuleCharArray(dataType)){//字符串变量定义
			codeBuffer.append(String.format(PARAM_DEFINE_STR,fieldName + suffix,length));
		}else{
			TypeDefaultValue typpeDefValue = MetadataServiceProvider.getTypeDefaultValueByNameNoExp(project, bizType.getDefaultValue());
			if(typpeDefValue == null){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("参数[%1$s]对应的业务类型[%2$s]中的标准默认值[%3$s]不存在。", fieldName, stdfield.getDataType(),bizType.getDefaultValue());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//标准默认值不存在时，继续生成，但最后将所有错误信息都抛出。
			}
			String defValue = typpeDefValue.getValue(MetadataServiceProvider.C_TYPE);
			codeBuffer.append(String.format(PARAM_DEFINE,dataType,fieldName + suffix,defValue));
		}
	
		return codeBuffer.toString();
	}
	
	/**
	 * 对象定义初始化
	 * @param paramName
	 * @param context
	 * @param prefix
	 * @return
	 */
	private String getObjectParamDefineInitCodeStr(String paramName,Map<Object, Object> context,String prefix,boolean isInitObject){
		StringBuffer codeBuffer = new StringBuffer();
	
		codeBuffer.append("int pi_"+paramName+ " = 0;").append("\r\n");
		if(isInitObject){
			codeBuffer.append(String.format("void * %1$s = lpInUnPacker->GetRaw(%2$s,&%3$s);\r\n","p_"+paramName, "\""+paramName+"\"","pi_"+paramName));
			codeBuffer.append(String.format("IF2UnPacker * %1$s = lpPackService->GetUnPacker(%2$s,%3$s);\r\n", "v_"+prefix+"ResultSet","p_"+paramName,"pi_"+paramName));
		}else{
			codeBuffer.append(String.format("void * %1$s = NULL;\r\n","p_"+paramName));
			codeBuffer.append(String.format("IF2UnPacker * %1$s = NULL;\r\n", "v_"+prefix+"ResultSet"));
		}
		
		return codeBuffer.toString();
	}
	
	/**
	 * 对象定义初始化(输出参数)
	 * @param paramName
	 * @param context
	 * @param prefix
	 * @return
	 */
	private String getOutObjectParamDefineInitCodeStr(String paramName,Map<Object, Object> context,String prefix){
		StringBuffer codeBuffer = new StringBuffer();
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		codeBuffer.append("int pi_"+paramName+ " = 16*1024*1024;").append("\r\n");
		codeBuffer.append(String.format("void * %1$s = NULL;\r\n","p_"+paramName));
		codeBuffer.append(String.format("IF2Packer * %1$s = lpPackService->GetPacker(2);\r\n","v_"+prefix+"ResultSet"));
		
		return codeBuffer.toString();
	}
	
	
	
	private String getClobParamDefineInitCodeStr(String paramName){
		StringBuffer codeBuffer = new StringBuffer();
		codeBuffer.append("int pi_"+paramName+ " = 0;").append("\r\n");
		codeBuffer.append(String.format("void * %1$s = lpInUnPacker->GetRaw(%2$s,&%3$s);\r\n","p_"+paramName, "\""+paramName+"\"","pi_"+paramName));
		
		return codeBuffer.toString();
	}
	
	
	private Parameter containsKey(String key,List params,Map<Object, Object> context){
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
		for(int i = 0;i < params.size();i++){
			Parameter p = (Parameter) params.get(i);
			if( p.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){//如果参数是参数组
				List<Parameter> parameters = new ArrayList<Parameter>();
				ParamGroupUtil.parserParameters(params, parameters, project);
				for(Parameter pg:parameters){
					if((pg != null) && (pg.getId() != null) && (pg.getId() != "") && pg.getId().equalsIgnoreCase(key)){
						return pg;
					}
				}

			}
			else if((p != null) && (p.getId() != null) && (p.getId() != "") && p.getId().equalsIgnoreCase(key)){
				return p;
			}
		}
		return null;
	}
	
	private Parameter containsKey(String key,List params){
		for(int i = 0;i < params.size();i++){
			Parameter p = (Parameter) params.get(i);
			if((p != null) && (p.getId() != null) && (p.getId() != "") && p.getId().equalsIgnoreCase(key)){
				return p;
			}
		}
		return null;
	}
	/**
	 * 递归参数组参数
	 * @param gruopParam
	 * @param retParameter
	 * @param isInParameter
	 * @param context
	 */
	private void parserParamGroup( Parameter gruopParam,List<Parameter> retParameter,boolean isInParameter,int callDepth,Map<Object, Object> context){
		String path = gruopParam.getType();
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
		try {
			IARESResource objectRes = project.findResource(path, "object");
			ARESObject aresObject = objectRes.getInfo(ARESObject.class);
			List<Parameter> properties = aresObject.getProperties();
			for(Parameter parameter:properties){
				if( parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){//如果是参数组则递归调用
					if(callDepth<=5){
						parserParamGroup(parameter,retParameter,isInParameter,callDepth+1,context);
					}
					
				}else if( parameter.getParamType().getValue()==ParamType.OBJECT_VALUE && isInParameter){
					ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
					 helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_IN_OBJECT_INIT_VARIABLE_LIST,parameter.getId());
				}else if( parameter.getParamType().getValue()==ParamType.OBJECT_VALUE && !isInParameter){
					ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
					 helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_OUT_OBJECT_INIT_VARIABLE_LIST,parameter.getId());
				}else if( parameter.getParamType().getValue()==ParamType.STD_FIELD_VALUE ){
					retParameter.add(parameter);
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
	}
	

	
	//proc变量定义中包含iReturnCode
	private final static String DECLARE_SECTION_BEGIN_IRETURNCODE
			=
			"EXEC SQL BEGIN DECLARE SECTION;" + ITokenConstant.NL +
			"sql_context ctx = NULL;" + ITokenConstant.NL +
			"int iReturnCode = 0;" + ITokenConstant.NL;
	//proc变量定义中不包含iReturnCode
	private final static String DECLARE_SECTION_BEGIN 
	=
	"int iReturnCode = 0;" + ITokenConstant.NL +
	"EXEC SQL BEGIN DECLARE SECTION;" + ITokenConstant.NL + 
	"sql_context ctx = NULL;" + ITokenConstant.NL;
	
	private final static String DECLARE_SECTION_END
			=
			"EXEC SQL END DECLARE SECTION;" + ITokenConstant.NL;
	private final static String STRUCT_SQLCA = "struct sqlca sqlca;" + ITokenConstant.NL;
	
	private final static String PARAM_DEFINE = "%1$s @%2$s = %3$s;" + ITokenConstant.NL;
	
	private final static String PARAM_INIT = "@%2$s = lpInUnPacker->Get%1$s(\"%2$s\");" + ITokenConstant.NL;
	
	private final static String PARAM_INIT_CHAR = "@%1$s = conversion(lpInUnPacker->GetChar(\"%1$s\"));" + ITokenConstant.NL;
	
	private final static String PARAM_INIT_STR = "hs_strncpy(@%1$s,conversion((char *)lpInUnPacker->GetStr(\"%1$s\")),%2$s);" + ITokenConstant.NL;
	
	private final static String PARAM_DEFINE_STR = "char @%1$s[%2$s] = {0};" + ITokenConstant.NL;
	private final static String PARAM_DEFINE_ARRAY2 = "%1$s @%2$s[%3$s][%4$s] = {0};" + ITokenConstant.NL;//二维数组定义
	private final static String PARAM_DEFINE_INIT_ARRAY2 = "memset(@%1$s,0,sizeof(%2$s)*%3$s*%4$s);" + ITokenConstant.NL;//二维数组初始化
	
	private final static String PARAM_DEFINE_ARRAY1 = "%1$s @%2$s[%3$s] = {0};" + ITokenConstant.NL;//-维数组定义
	private final static String PARAM_DEFINE_INIT_ARRAY1 = "memset(@%1$s,0,sizeof(%2$s)*%3$s);" + ITokenConstant.NL;//-维数组初始化
	
	private final static String PARAM_DEFINE_INIT_CHAR = "char @%1$s = conversion(lpInUnPacker->GetChar(\"%1$s\"));" + ITokenConstant.NL;

	private final static String PARAM_DEFINE_INIT = "%1$s @%2$s = lpInUnPacker->Get%3$s(\"%2$s\");" + ITokenConstant.NL;
	private final static String PARAM_DEFINE_OBJECT_INIT = "%1$s @%2$s = %4$s->Get%3$s(\"%2$s\");" + ITokenConstant.NL;
	private final static String PARAM_DEFINE_INIT_OBJECT_CHAR = "@%1$s = conversion(%2$s->GetChar(\"%1$s\"));" + ITokenConstant.NL;
	private final static String PARAM_INIT_OBJECT_STR = "hs_strncpy(@%1$s,conversion((char *)%3$s->GetStr(\"%1$s\")),%2$s);" + ITokenConstant.NL;
	private final static String PARAM_DEFIN_UNPACKER = "IF2UnPacker * %1$s = NULL;"+ITokenConstant.NL;
	//private boolean isDefineLpPackService = false;//是否有clob参数
	
	private final static String iReturnCode_DEFINE_STR = "int iReturnCode = 0;" + ITokenConstant.NL;//iReturnCode统一在FunctionParamDefineToken中处理
	
}
