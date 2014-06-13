/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.tokens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.InternalParam;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.token.SpecialParamDefineHelper;
import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.Multiplicity;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IParamDefineHelper;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;
import com.hundsun.ares.studio.logic.compiler.util.LogicCompilerUtil;

/**
 * @author qinyuan
 *
 */
public class LogicServiceVariableDefineToken implements ICodeToken {

	protected IARESProject project=null;
	protected AtomFunction ls;
	private Set<String> calledResultSetList;//被调用的AS或者LF等
	private static List<String> specialsParams = new ArrayList<String>();
	{
		specialsParams.add("lpContext");
		specialsParams.add("lpInUnPacker");
		specialsParams.add("lpOutPacker");
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
		  this.project = (IARESProject)context.get(IAtomEngineContextConstant.Aresproject);

		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		  List<String> popObjectVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_OBJECT_PARA_LIST);
		
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(ILogicEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		calledResultSetList = helper.getAttribute(ILogicEngineContextConstant.ATTR_FUNC_RESULTSET);
		
		//变量定义帮助类
		IParamDefineHelper defineHelper = (IParamDefineHelper)context.get(ILogicEngineContextConstant.PARAM_DEFINE_HELPER);
		
		//特殊变量定义帮助类
		SpecialParamDefineHelper specialParamDefineHelper= new SpecialParamDefineHelper();
		
		 ls = (AtomFunction) context.get(IAtomEngineContextConstant.ResourceModel);
		
		EList<InternalParam> internalVars =  ls .getInternalVariables();
		EList<Parameter> inputParams =  ls .getInputParameters();
		EList<Parameter> outputParams =  ls .getOutputParameters();
		
		//资源的输入输出参数加入到公共区域中，以便后续处理，提高访问效率。
		for(int i = 0;i < inputParams.size();i++){
			Parameter parameter = inputParams.get(i);
			String paramName = parameter.getId();
			if(parameter.getParamType().getValue() != ParamType.PARAM_GROUP_VALUE){
				helper.addAttribute(ILogicEngineContextConstant.ATTR_IN_OUT_PARAM_LIST,paramName);
			}
			
			if(parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE ){
				if(!popVarList.contains(paramName)){
					popVarList.add(paramName);
				}
			}else if(parameter.getParamType().getValue() == ParamType.OBJECT_VALUE){
			   popObjectVarList.add(parameter.getId());
			   popVarList.remove(parameter.getId());
				
			}else if( parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){//如果参数是参数组
				List<Parameter> stdParameters = new ArrayList<Parameter>();
				parserParamGroup(parameter,stdParameters,true,context);
				for(Parameter stdParameter:stdParameters){
					helper.addAttribute(ILogicEngineContextConstant.ATTR_IN_OUT_PARAM_LIST,stdParameter.getId());
					popVarList.add(stdParameter.getId());

				}
			}
		}
		for(int i = 0;i < outputParams.size();i++){
			Parameter parameter = outputParams.get(i);
			String paramName = parameter.getId();
			if(parameter.getParamType().getValue() != ParamType.PARAM_GROUP_VALUE){
				helper.addAttribute(IAtomEngineContextConstant.ATTR_IN_OUT_PARAM_LIST,paramName);
			}
			if(parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE ){
				if(!popVarList.contains(paramName)){
					popVarList.add(paramName);
				}
			}else if(parameter.getParamType().getValue() == ParamType.OBJECT_VALUE){
				popObjectVarList.add(parameter.getId());
				popVarList.remove(parameter.getId());
				
			}else if( parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){//如果参数是参数组
			
				//如果参数是参数组
				List<Parameter> stdParameters = new ArrayList<Parameter>();
				parserParamGroup(parameter,stdParameters,false,context);
				for(Parameter stdParameter:stdParameters){
					helper.addAttribute(ILogicEngineContextConstant.ATTR_IN_OUT_PARAM_LIST,stdParameter.getId());
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
		
		
		StringBuffer codeBuffer = new StringBuffer();
		codeBuffer.append(getCommonParamsDefineCodeStr(popVarList,defineHelper));
		codeBuffer.append(getUnPackerAndPackerDefineCodeStr());
		StringBuffer paramGroupBuffer = new StringBuffer();
		for(int i = 0;i < inputParams.size();i++){
			Parameter p = inputParams.get(i);
			if(p.getParamType().getValue()== ParamType.STD_FIELD_VALUE && defineHelper.canInit(IParamDefineHelper.STD, p.getId())){
				defineHelper.addInit(IParamDefineHelper.STD, p.getId());
				codeBuffer.append(getParamDefineInitCodeStr(p.getId()));
			}else if(p.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE){//输入参数是对象
				paramGroupBuffer.append("//初始化参数组参数("+p.getType()+")"+"\r\n");
				List<Parameter> stdParameters = new ArrayList<Parameter>();
				parserParamGroup(p,stdParameters,true,context);
				for(Parameter stdParameter:stdParameters){
					if(defineHelper.canInit(IParamDefineHelper.STD, stdParameter.getId())){
						paramGroupBuffer.append(getParamDefineInitCodeStr(stdParameter.getId()));
						defineHelper.addInit(IParamDefineHelper.STD, stdParameter.getId());
					}
					
				
				}
				paramGroupBuffer.append("\r\n");
			
				
			}else if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, p.getId())&& p.getParamType().getValue()==ParamType.OBJECT_VALUE){//输入参数是对象
				codeBuffer.append(getObjectParamDefineInitCodeStr(p.getId(),context,p.getId(),true));
				defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, p.getId());
				helper.addAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_INIT_VARIABLE_LIST, p.getId());
				popObjectVarList.add(p.getId());
				popVarList.remove(p.getId());
			}
		}
		//初始化需要从lpInUnPacker初始化的对象
		Set<String> initObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_INIT_VARIABLE_LIST);
		for(String objectParam:initObjectList){
			if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, objectParam)){
				codeBuffer.append(getObjectParamDefineInitCodeStr(objectParam,context,objectParam,true));
				defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, objectParam);
				popObjectVarList.add(objectParam);
				popVarList.remove(objectParam);
			}
			
		}
		
		//初始化需不要从lpInUnPacker初始化的对象
		Set<String> noInitObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_NOINIT_VARIABLE_LIST);
		for(String objectParam:noInitObjectList){
			if (defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT,objectParam)) {
				codeBuffer.append(getObjectParamDefineInitCodeStr(objectParam,context, objectParam, false));
				defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT,objectParam);
				popObjectVarList.add(objectParam);
				popVarList.remove(objectParam);
			}
		}
		for(int i = 0;i < outputParams.size();i++){
			Parameter p = outputParams.get(i);
			
			if((p.getParamType().getValue()== ParamType.STD_FIELD_VALUE &&   p.getFlags() != null) && p.getFlags().equalsIgnoreCase("IO") && defineHelper.canInit(IParamDefineHelper.STD, p.getId())){
				String varDefine = getParamDefineInitCodeStr(p.getId());
				if(StringUtils.isNotBlank(varDefine)){
					codeBuffer.append(varDefine);
					defineHelper.addInit(IParamDefineHelper.STD, p.getId());
				}
				
			}else if(p.getParamType().getValue()== ParamType.STD_FIELD_VALUE && defineHelper.canInit(IParamDefineHelper.STD, p.getId())){
				String varDefine = getParamDefineCodeStr(p.getId(),null);
				if(StringUtils.isNotBlank(varDefine)){
					codeBuffer.append(varDefine);
					defineHelper.addInit(IParamDefineHelper.STD, p.getId());
				}
				
			}else if( p.getParamType().getValue()==ParamType.OBJECT_VALUE){//如果是对象
				popVarList.remove(p.getId());
				if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, p.getId())){
					codeBuffer.append(getOutObjectParamDefineInitCodeStr(p.getId(),context,p.getId()));
					helper.addAttribute(IAtomEngineContextConstant.ATTR_OUT_OBJECT_INIT_VARIABLE_LIST, p.getId());
					defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, p.getId());
					popObjectVarList.add(p.getId());
				}
			
			}else if( p.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){//如果参数是参数组
				paramGroupBuffer.append("//初始化参数组参数("+p.getType()+")"+"\r\n");
				List<Parameter> stdParameters = new ArrayList<Parameter>();
				parserParamGroup(p,stdParameters,false,context);
				for(Parameter stdParameter:stdParameters){
					if(defineHelper.canInit(IParamDefineHelper.STD, stdParameter.getId())){
						String varDefine = getParamDefineInitCodeStr(stdParameter.getId());
						if(StringUtils.isNotBlank(varDefine)){
							paramGroupBuffer.append(varDefine);
							defineHelper.addInit(IParamDefineHelper.STD, stdParameter.getId());
						}
						
					}

				}
			}
		}
		//输出参数对象初始化(定义由宏加入的输出对象)
		Set<String> initOutObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_OUT_OBJECT_INIT_VARIABLE_LIST);
		for(String objectParam:initOutObjectList){
			if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, objectParam)){
			    codeBuffer.append(getOutObjectParamDefineInitCodeStr(objectParam,context,objectParam));
			    defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, objectParam);
			    popObjectVarList.add(objectParam);
			    popVarList.remove(objectParam);
			}
		}
		for(int i = 0;i < internalVars.size();i++){
			Parameter p = internalVars.get(i);
			if((p.getId() != null) && defineHelper.canInit(IParamDefineHelper.STD, p.getId())){
				String varDefineStr = varDefineCodeStr(p.getId(),project,internalVars);
				if(StringUtils.isNotBlank(varDefineStr)){
					codeBuffer.append(varDefineCodeStr(p.getId(),project,internalVars));
					defineHelper.addInit(IParamDefineHelper.STD, p.getId());
				}
				
			}
		}
		
		//伪代码
		for(int i = 0;i < popVarList.size();i++){
			String fieldName = popVarList.get(i);
			if(!specialsParams.contains(fieldName)&&(fieldName != null) && defineHelper.canInit(IParamDefineHelper.STD, fieldName)){
				StandardField sField = null;
				try{
					sField = MetadataServiceProvider.getStandardFieldByName(project, fieldName);
				}catch(Exception ex){
					ex.printStackTrace();
					
				}
				if(sField == null ){
					throw new Exception(String.format("标准字段：%1$s不存在！",fieldName));
				}
			codeBuffer.append(getParamDefineCodeStr(fieldName,null));
			defineHelper.addInit(IParamDefineHelper.STD, fieldName);
			}
		}
		
		//添加参数组始化代码
		if(StringUtils.isNotBlank(paramGroupBuffer.toString())){
			codeBuffer.append(paramGroupBuffer.toString());
		}
		//特殊的伪代码中非标准字段声明
		codeBuffer.append(specialParamDefineHelper.getSpecialParamsDefineCodeStr(project, context,popVarList));
		return codeBuffer.toString();
	}
	
	/**
	 * 
	 */
	protected String getCommonParamsDefineCodeStr(List<String> popVarList,IParamDefineHelper defineHelper) {
		StringBuffer code = new StringBuffer();
		String error_info_length = this.getStandardFieldParameterInfo("error_info").get("length");
		String error_pathinfo_length =  this.getStandardFieldParameterInfo("error_pathinfo").get("length");
		if(defineHelper.canInit(IParamDefineHelper.STD, "iReturnCode")){
			code.append("int iReturnCode = 0;\r\n");
			defineHelper.addInit(IParamDefineHelper.STD, "iReturnCode");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "error_no",project)&& !popVarList.contains("error_no")&& defineHelper.canInit(IParamDefineHelper.STD, "error_no")){
			code.append("int @error_no = 0;\r\n");
			defineHelper.addInit(IParamDefineHelper.STD, "error_no");
			popVarList.add("error_no");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "error_info",project)&& !popVarList.contains("error_info")&& defineHelper.canInit(IParamDefineHelper.STD, "error_info")){
			code.append("char @error_info["+(Integer.parseInt(error_info_length)+1)+"] = {0};\r\n");//初始化长度加1
			defineHelper.addInit(IParamDefineHelper.STD, "error_info");
			popVarList.add("error_info");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "error_pathinfo",project)&& !popVarList.contains("error_pathinfo")&& defineHelper.canInit(IParamDefineHelper.STD, "error_pathinfo")){
			code.append("char @error_pathinfo["+(Integer.parseInt(error_pathinfo_length)+1)+"] = {0};\r\n");//初始化长度加1
			code.append("hs_strcpy(v_error_pathinfo, "+"\"F"+ls.getObjectId()+"()\");\r\n");
			defineHelper.addInit(IParamDefineHelper.STD, "error_pathinfo");
			popVarList.add("error_pathinfo");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "branch_no",project)&& !popVarList.contains("branch_no")&& defineHelper.canInit(IParamDefineHelper.STD, "branch_no")){
			code.append("int @branch_no = lpInUnPacker->GetInt(\"branch_no\");\r\n");
			popVarList.add("branch_no");
			defineHelper.addInit(IParamDefineHelper.STD, "branch_no");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "sysnode_id",project)&& !popVarList.contains("sysnode_id")){
			code.append("int @sysnode_id = 0;\r\n");
			popVarList.add("sysnode_id");
			defineHelper.addInit(IParamDefineHelper.STD, "sysnode_id");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "subsys_id",project)&& !popVarList.contains("subsys_id")&& defineHelper.canInit(IParamDefineHelper.STD, "subsys_id")){
			code.append("int @subsys_id = 0;\r\n");
			popVarList.add("subsys_id");
			defineHelper.addInit(IParamDefineHelper.STD, "subsys_id");
			
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "timeout",project)&& !popVarList.contains("timeout")&& defineHelper.canInit(IParamDefineHelper.STD, "timeout")){
			code.append("int @timeout = 0;\r\n");
			popVarList.add("timeout");
			defineHelper.addInit(IParamDefineHelper.STD, "timeout");
		}
		
		
		if (!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "error_id",project) && ls.getInterfaceFlag() != null && ls.getInterfaceFlag().toLowerCase().indexOf("g") != -1 && !popVarList.contains("error_id")&& defineHelper.canInit(IParamDefineHelper.STD, "error_id")){
			code.append("int @error_id = 0;\r\n");
			defineHelper.addInit(IParamDefineHelper.STD, "error_id");
			popVarList.add("error_id");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "audit_action",project)&& !popVarList.contains("audit_action") && defineHelper.canInit(IParamDefineHelper.STD, "audit_action")){
			code.append("char p_audit_action = lpInUnPacker->GetChar(\"audit_action\");\r\n");
			defineHelper.addInit(IParamDefineHelper.STD, "audit_action");
			popVarList.add("audit_action");
		}
		code.append("IF2PackSvr * lpPackService = lpContext->GetF2PackSvr();\r\n");
		return code.toString();
	}
	
	private String getUnPackerAndPackerDefineCodeStr(){
		StringBuffer code = new StringBuffer();
		StringBuffer if2UnPackerCode = new StringBuffer();
		StringBuffer if2PackerCode = new StringBuffer();
		for(String id:calledResultSetList){
			if2UnPackerCode.append("IF2UnPacker * "+"lpResultSet"+id+"= NULL;\r\n" );
			if2PackerCode.append("IF2Packer * "+"lpOut"+id+"= lpPackService->GetPacker(2);\r\n" );
		}
		code.append("\r\n");
		code.append(if2UnPackerCode);
		code.append("\r\n");
		code.append("IF2Packer * lpFuncInPacker = lpPackService->GetPacker(2);\r\n");
		code.append(if2PackerCode);
		
		return code.toString();
	}

	/**
	 * 
	 * @param paramName
	 * @param project
	 * @param internalVars
	 * @return
	 */
	private String getParamDefineCodeStr(String paramName,List<InternalParam> internalVars){
		Map<String,String> parameterInfo =	getStandardFieldParameterInfo(paramName);
		StringBuffer codeBuffer = new StringBuffer();
		if(StringUtils.equals("true", parameterInfo.get("isStdParameter")))//标准字段
		{
			String dataType = parameterInfo.get("type");
			String defValue = parameterInfo.get("value");
			int length = 0;
			if(parameterInfo.get("length")!= null){
				//声明Char数组变量时，长度要加1
				length = org.apache.commons.lang.math.NumberUtils.toInt(parameterInfo.get("length")) + 1;
				dataType = dataType.replace("$L",length+"");
			}
			if(TypeRule.typeRuleCharArray(dataType)){
				codeBuffer.append(String.format(PARAM_DEFINE_STR,paramName,length));
			}else{
				codeBuffer.append(String.format(PARAM_DEFINE,dataType,paramName ,defValue));
			}
			
		}else //内部变量
		{
			codeBuffer.append(varDefineCodeStr(paramName,project,internalVars));
		}
		return codeBuffer.toString();
	}
	
	private String varDefineCodeStr(String paramName,IARESProject project,List<InternalParam> internalVars){
		StringBuffer codeBuffer = new StringBuffer();
		if(internalVars == null){
			return "";
		}
		Parameter internalVar = containsKey(paramName,internalVars);
		if(internalVar != null){
			if(internalVar.getParamType().getValue() == ParamType.NON_STD_FIELD_VALUE){

				Map<String,String> parameterInfo = getNonStandardFieldParameterInfo(internalVar);
					int length = 0;
				 String	dataType = StringUtils.defaultIfBlank(parameterInfo.get("type"), "");
				 String defValue = parameterInfo.get("value");
					if(parameterInfo.get("length")!= null){
						length = org.apache.commons.lang.math.NumberUtils.toInt(parameterInfo.get("length"));
						dataType = dataType.replace("$L",(length+1)+"");
						//定义Char数组变量时，长度要加1
						dataType = dataType.replace("$L" , length + "");
					}
					if(TypeRule.typeRuleCharArray(dataType)){
						//定义Char数组变量时，长度要加1
						codeBuffer.append(String.format(PARAM_DEFINE_STR,paramName ,length));
					}else{
						codeBuffer.append(String.format(PARAM_DEFINE,dataType,paramName ,defValue));
					}
				}
			}else if(internalVar.getParamType().getValue() == ParamType.STD_FIELD_VALUE){
				//如果是标准字段，其声明方式与输入输出参数声明方式一致，注意这里的内部变量列表必须传为空，否则会递归调用，出现死循环
				codeBuffer.append(getParamDefineCodeStr(internalVar.getId(),null));
			}
		return codeBuffer.toString();
	}
	
	
	protected String getParamDefineInitCodeStr(String paramName) throws Exception{
		StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(project, paramName);
		BusinessDataType busType = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(project, paramName);
		StringBuffer codeBuffer = new StringBuffer();
		String dataType = stdType.getValue(MetadataServiceProvider.C_TYPE);
		int length = 0;
		if(busType.getLength() != null){
			try {
				length = Integer.parseInt(StringUtils.defaultIfBlank(busType.getLength(), "0"));
			} catch (Exception e) {
				throw new Exception(String.format("业务数据类型:%1$s的长度为非法数字：%2$s。", busType.getName(),busType.getLength()));
			}
			//声明Char数组变量时，长度要加1，但是初始化不需要加1
			dataType = dataType.replace("$L", length + "");
		}
		if(TypeRule.typeRuleChar(dataType)){
			codeBuffer.append(String.format(PARAM_DEFINE_INIT_CHAR,paramName));
		}else if(TypeRule.typeRuleCharArray(dataType)){
			codeBuffer.append(getParamDefineCodeStr(paramName,null));
			//声明及初始化Char数组变量时，长度要加1
			codeBuffer.append(String.format(PARAM_INIT_STR,paramName ,length));
		}
		else
		{
			if (TypeRule.typeRuleInt(dataType)) {
				codeBuffer.append(String.format(PARAM_DEFINE_INIT,dataType, paramName,"Int"));
			} else if (TypeRule.typeRuleDouble(dataType)) {
				codeBuffer.append(String.format(PARAM_DEFINE_INIT,dataType,paramName , "Double"));
			}else if (TypeRule.typeRuleClob(dataType)) {
				codeBuffer.append(getClobParamDefineInitCodeStr(paramName));
				//this.existClobParameter = true;
			}else if (TypeRule.typeRulePacker(dataType)) {
				codeBuffer.append(String.format(PARAM_DEFIN_UNPACKER,paramName ));
			}else {
				throw new Exception(String.format("没有对数据类型:%1$s的初始化方式",dataType));
			}
		}
		return codeBuffer.toString();
	}
	
	
	/**
	 * 对象定义初始化
	 * @param paramName
	 * @param context
	 * @param suffix
	 * @param  isObjResultSet
	 * @return
	 */
	private String getObjectParamDefineInitCodeStr(String paramName,Map<Object, Object> context,String prefix,boolean isInitObject){
		StringBuffer codeBuffer = new StringBuffer();
	
		codeBuffer.append("int pi_"+paramName+ " = 0;").append("\r\n");
		//if(!isDefineLpPackService){
		//	codeBuffer.append("IF2PackSvr * lpPackService = lpContext->GetF2PackSvr();").append("\r\n");
		//	isDefineLpPackService = true;
		//}
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
		codeBuffer.append("int pi_"+paramName+ " = 16*1024*1024;").append("\r\n");
		codeBuffer.append(String.format("void * %1$s = NULL;\r\n","p_"+paramName));
		codeBuffer.append(String.format("IF2Packer * %1$s = lpPackService->GetPacker(2);\r\n","v_"+prefix+"ResultSet"));
		
		return codeBuffer.toString();
	}
	
	
	private String getClobParamDefineInitCodeStr(String paramName){
		StringBuffer codeBuffer = new StringBuffer();
	
		codeBuffer.append("int pi_"+paramName+ " = 0;").append("\r\n");
		//if(!isDefineLpPackService){
		//	codeBuffer.append("IF2PackSvr * lpPackService = lpContext->GetF2PackSvr();").append("\r\n");
		//}
		codeBuffer.append(String.format("void * %1$s = lpInUnPacker->GetRaw(%2$s,&%3$s);\r\n","p_"+paramName, "\""+paramName+"\"","pi_"+paramName));
		
		return codeBuffer.toString();
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
	 * 获取非标准字段参数信息
	 * @param name
	 * @return
	 */
	private Map<String,String> getNonStandardFieldParameterInfo( Parameter parameter){
		Map<String,String> parameterInfo = new HashMap<String,String>();

		parameterInfo.put("isStdParameter","false");
		StandardDataType internalDataType = null;
		try {
			internalDataType = MetadataServiceProvider.getStandardDataTypeOfBizTypeByName(project, parameter.getType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String defValue = parameter.getDefaultValue();
		if(defValue == null){
			TypeDefaultValue internalTypeDefValue = null;
			try {
				internalTypeDefValue = MetadataServiceProvider.getTypeDefaultValueOfBizTypeByName(project, parameter.getType());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(internalTypeDefValue != null){
				defValue = internalTypeDefValue.getValue(MetadataServiceProvider.C_TYPE);
			}
		}
		
		parameterInfo.put("value", defValue);
		
		if(internalDataType != null){
			String dataType = internalDataType.getValue(MetadataServiceProvider.C_TYPE);
			BusinessDataType busType = null;
			try {
				busType = MetadataServiceProvider.getBusinessDataTypeByName(project, parameter.getType());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			parameterInfo.put("type", dataType);
			if(busType != null){
				String  length = StringUtils.defaultIfBlank(busType.getLength(), "0");
				parameterInfo.put("length", length);
			}
				
			
		return parameterInfo;
		}
		parameterInfo.put("length",null);
		
		return parameterInfo;
	}
	/**
	 * 获取标准字段参数信息
	 * @param name
	 * @return
	 */
	protected Map<String,String> getStandardFieldParameterInfo( String name){
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
				parameterInfo.put("isStdParameter","true");
			return parameterInfo;
			}
			if(name == "error_no"){
				parameterInfo.put("type", "int");
				parameterInfo.put("value", "0");
				parameterInfo.put("length","0");
				parameterInfo.put("precision", "0");
			}else if(name == "error_info"){
				parameterInfo.put("type", "char[501]");
				parameterInfo.put("value", "{0}");
				parameterInfo.put("length","500");
				parameterInfo.put("precision", "0");
			}else if(name == "error_id"){
				parameterInfo.put("type", "int");
				parameterInfo.put("value", "0");
				parameterInfo.put("length","0");
				parameterInfo.put("precision", "0");
			}else if(name == "error_sysinfo"){
				parameterInfo.put("type", "char[501]");
				parameterInfo.put("value", "{0}");
				parameterInfo.put("length","500");
				parameterInfo.put("precision", "0");
			}else if(name == "error_pathinfo"){
				parameterInfo.put("type", "char[501]");
				parameterInfo.put("value", "{0}");
				parameterInfo.put("length","500");
				parameterInfo.put("precision", "0");
			}
			
			parameterInfo.put("isStdParameter","false");
			
		
		return parameterInfo;
	}
	/**
	 * 解析出对象
	 * @param path
	 * @param context
	 * @return
	 */
	private List<Parameter> parserObject(String path,Map<Object, Object> context){
		List<Parameter> parameters = new ArrayList<Parameter>();
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
		try {
			IARESResource objectRes = project.findResource(path, "object");
			ARESObject aresObject = objectRes.getInfo(ARESObject.class);
			parameters.addAll(aresObject.getProperties());
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return parameters;
		
	}
	
	/**
	 * 递归参数组参数
	 * @param gruopParam
	 * @param retParameter
	 * @param isInParameter
	 * @param context
	 */
	private void parserParamGroup( Parameter gruopParam,List<Parameter> retParameter,boolean isInParameter,Map<Object, Object> context){
		String path = gruopParam.getType();
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
		try {
			IARESResource objectRes = project.findResource(path, "object");
			ARESObject aresObject = objectRes.getInfo(ARESObject.class);
			List<Parameter> properties = aresObject.getProperties();
			for(Parameter parameter:properties){
				if( parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){//如果是参数组则递归调用
					parserParamGroup(parameter,retParameter,isInParameter,context);
				}else if( parameter.getParamType().getValue()==ParamType.OBJECT_VALUE && isInParameter){
					ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
					 helper.addAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_INIT_VARIABLE_LIST,parameter.getId());
				}else if( parameter.getParamType().getValue()==ParamType.OBJECT_VALUE && !isInParameter){
					ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
					 helper.addAttribute(IAtomEngineContextConstant.ATTR_OUT_OBJECT_INIT_VARIABLE_LIST,parameter.getId());
				}else if( parameter.getParamType().getValue()==ParamType.STD_FIELD_VALUE ){
					retParameter.add(parameter);
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
	}
	private final static String PARAM_DEFINE = "%1$s @%2$s = %3$s;" + ITokenConstant.NL;
	
	private final static String PARAM_INIT = "@%2$s = lpInUnPacker->Get%1$s(\"%2$s\");" + ITokenConstant.NL;
	
	private final static String PARAM_INIT_CHAR = "@%1$s = conversion(lpInUnPacker->GetChar(\"%1$s\"));" + ITokenConstant.NL;
	
	private final static String PARAM_INIT_STR = "hs_strncpy(@%1$s,conversion((char *)lpInUnPacker->GetStr(\"%1$s\")),%2$s);" + ITokenConstant.NL;
	
	private final static String PARAM_DEFINE_STR = "char @%1$s[%2$s] = {0};" + ITokenConstant.NL;
	
	private final static String PARAM_DEFINE_INIT_CHAR = "char @%1$s = conversion(lpInUnPacker->GetChar(\"%1$s\"));" + ITokenConstant.NL;

	private final static String PARAM_DEFINE_INIT = "%1$s @%2$s = lpInUnPacker->Get%3$s(\"%2$s\");" + ITokenConstant.NL;
	private final static String PARAM_DEFIN_UNPACKER = "IF2UnPacker * %1$s = NULL;"+ITokenConstant.NL;
	private boolean isDefineLpPackService = false;//是否有clob参数
	

	
}
