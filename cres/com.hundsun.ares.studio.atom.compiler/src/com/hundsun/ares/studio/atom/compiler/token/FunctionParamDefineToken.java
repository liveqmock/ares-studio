/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.InternalParam;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.AtomFunctionCompilerUtil;
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
	private IParamDefineHelper defineHelper=null ;
	private AtomFunction atomFunction =null;
	private Map<Object, Object> context;
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
		// 在parseEx的过程中，已经把所有用到的变量都统一写入到“伪代码使用变量列表”中，可直接使用，注意，不要与PROC变量列表声明重复
		// 本token完成以下工作：
		// 1.PRO*C变量声明（不需要初始化），不管是否有Proc变量，只要AS或AF使用PRO*C宏，就要声明sql_context
		// 2.输入参数声明、初始化（从lpInUnPacker根据类型取值）
		// 3.输出参数声明、初始化（从默认值初始化）
		// 4.内部变量声明（包括伪代码中使用的标准字段变量）初始化（从默认值初始化）
		// 5.pack变量声明（数据类型为IF2UnPacker *）
		// 6.生成以下语句：
		/*
		 *  EXEC SQL BEGIN DECLARE SECTION;
			sql_context ctx = NULL;
			// 定义PROC变量
			EXEC SQL END DECLARE SECTION;
			
			// 初始化在输入输出参数中的PROC变量
			struct sqlca sqlca;
			// 输入参数声明、初始化
			// 输出参数声明、初始化
			// 内部变量声明、初始化
			// pack变量声明（初始化由用户自行完成，可参考2103734）
		 */
		/*
		 * 7.输入输出参数Clob数据类型的处理：
		 * int pi_[参数名] = 0;
		   void * p_[参数名] = NULL;
		   p_[参数名] = lpInUnPacker->GetRaw("[参数名]",&pi_[参数名]);
		   8.pack变量声明，生成语句如下：
		   IF2PackSvr * lpPackService = lpContext->GetF2PackSvr();（这一句，如果已经有声明，则不需要重复声明）
		   IF2UnPacker * v_[变量名] = NULL;
		   9.内部变量声明时，支持数组类型，数组一般在pro*c select ... into 到一个数组时的使用，如2112052的component_code_t，其类型为HsStockCode[500]。
		   	声明数组变量生成的代码如下：
				声明：[数据类型] v_[变量名][[数据类型长度]][[变量数组长度]];
				初始化：memset(v_[变量名],0,sizeof([数据类型])*[变量数组长度]-1*[数据类型长度]);
		 */
        this.context = context;
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		List<String> pseudoObjectParaList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_OBJECT_PARA_LIST);
		
		//PRO*C变量列表
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		
		//变量定义帮助类
		 defineHelper = (IParamDefineHelper)context.get(IAtomEngineContextConstant.PARAM_DEFINE_HELPER);
		//defineHelper.addInit(IParamDefineHelper.STD, para.getName());//判断该变量是否已经定义
		
		//特殊变量定义帮助类
		SpecialParamDefineHelper specialParamDefineHelper= new SpecialParamDefineHelper();
		
		atomFunction = (AtomFunction) context.get(IAtomEngineContextConstant.ResourceModel);
		

		EList<InternalParam> internalVars = atomFunction.getInternalVariables();
		EList<Parameter> inputParams = atomFunction.getInputParameters();
		EList<Parameter> outputParams = atomFunction.getOutputParameters();

		
		//资源的输入输出参数加入到公共区域中，以便后续处理，提高访问效率。
		for(int i = 0;i < inputParams.size();i++){
			Parameter parameter = inputParams.get(i);
			String paramName = parameter.getId();
			if(parameter.getParamType().getValue() != ParamType.PARAM_GROUP_VALUE){
				helper.addAttribute(IAtomEngineContextConstant.ATTR_IN_OUT_PARAM_LIST,paramName);
			}
			if(parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE ){
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
					helper.addAttribute(IAtomEngineContextConstant.ATTR_IN_OUT_PARAM_LIST,stdParameter.getId());
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
				helper.addAttribute(IAtomEngineContextConstant.ATTR_IN_OUT_PARAM_LIST,paramName);
			}
			if(parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE ){
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
					helper.addAttribute(IAtomEngineContextConstant.ATTR_IN_OUT_PARAM_LIST,stdParameter.getId());
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
		
		
		
		//Set<String> databaseMacroList = helper.getAttribute(IAtomEngineContextConstant.ATTR_DATABASE_MACRO);
		Set<String> procMacroList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_MACRO);
		Set<String> procVarList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST);
		Set<String> procCursorVarList = helper.getAttribute(IAtomEngineContextConstant.ATTR_CURSOR_PROC_VARIABLE_LIST);
		IARESProject project = (IARESProject)context.get(IAtomEngineContextConstant.Aresproject);
		StringBuffer codeBuffer = new StringBuffer();
		List<Procedure> nonPro = new ArrayList<Procedure>();
		//返回结果集的过程调用列表
		Set<String> nrsPrcCallList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROCEDURE_CALL_NOTRSRETURN);
		if((procMacroList.size()) > 0 || (nrsPrcCallList.size() > 0)){
			//必须在存在[PRO*C语句块开始]的情况下，PRO*C变量定义中添加iRecturnCode定义，否则不添加
			if(procMacroList.contains(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME) || (nrsPrcCallList.size() > 0)){
				codeBuffer.append(DECLARE_SECTION_BEGIN_IRETURNCODE);
			}else{
				codeBuffer.append(DECLARE_SECTION_BEGIN);
			}
			if(procVarList.size() > 0 || procCursorVarList.size() > 0){
				codeBuffer.append("//PRO*C变量声明" + ITokenConstant.NL);
			}
			//普通proc变量声明
			for(Iterator<String> ite = procVarList.iterator();ite.hasNext();){
				String procVarName = ite.next();
				if(defineHelper.canInit(IParamDefineHelper.STD, procVarName)){
					String defineStr = getParamDefineCodeStr(procVarName,project,internalVars,"",specialParamDefineHelper,false);
					if(StringUtils.isNotBlank(defineStr)){
						codeBuffer.append(defineStr);
						defineHelper.addInit(IParamDefineHelper.STD, procVarName);
					
					}
					
				}
			}
			//特殊的不在标准字段的PRO*C变量定义
			codeBuffer.append(specialParamDefineHelper.getSpecialParamsDefineCodeStr(project, context,procVarList));
			//error_no,error_info,error_pathinfo,error_id,error_sysinfo添加到普通变量列表中，否则这些变量的@符号将不会被替换
			
			//游标proc变量初始化，游标proc变量，统一加_cur后缀
			for(Iterator<String> ite = procCursorVarList.iterator();ite.hasNext();){
				String procVarName = ite.next();
				//判断时，变量名要加后缀_cur，否则可能遇到对应的变量已经在PROC变量中定义，但同名的_cur没有定义的问题。
				if(defineHelper.canInit(IParamDefineHelper.STD, procVarName + "_cur")){//ResultSet结尾的@变量，为打包解包器，不需要做伪代码变量声明{
					codeBuffer.append(getParamDefineCodeStr(procVarName,project,internalVars,"_cur",specialParamDefineHelper,false));
					defineHelper.addInit(IParamDefineHelper.STD, procVarName + "_cur");
				}
				
			}
			codeBuffer.append(DECLARE_SECTION_END);
			codeBuffer.append(ITokenConstant.NL);
		}else{
			codeBuffer.append(iReturnCode_DEFINE_STR);//添加iReturnCode定义
		}
		codeBuffer.append("//IF2PackSvr声明及初始化\r\n");
		codeBuffer.append("IF2PackSvr * lpPackService = lpContext->GetF2PackSvr();\r\n");	
		helper.addAttribute(IAtomEngineContextConstant.ATTR_LP_PACK_SERVICE_LIST, "lpPackService");
		if((procMacroList.size()) > 0 || (nrsPrcCallList.size() > 0)){
			if(procVarList.size() > 0){
				codeBuffer.append("//PRO*C变量初始化" + ITokenConstant.NL);
			}
			for(Iterator<String> ite = procVarList.iterator();ite.hasNext();){
				String procVarName = ite.next();
					if(containsKey(procVarName,inputParams,context) != null){
						codeBuffer.append(getParamInitCodeStr(procVarName,project,""));
						defineHelper.addInit(IParamDefineHelper.STD, procVarName);
					}else if (containsKey(procVarName,outputParams,context)!=null){
						Parameter outputP = containsKey(procVarName,outputParams,context);
						if((outputP!= null) && (outputP.getFlags() != null) && outputP.getFlags().equalsIgnoreCase("IO"))
						{
							codeBuffer.append(getParamInitCodeStr(procVarName,project,""));
							defineHelper.addInit(IParamDefineHelper.STD, procVarName);
						}
					}else {
						Parameter parameter = containsKey(procVarName,internalVars,context);
						if(parameter!=null && InternalParameterArrayHelper.isArrayParameter(parameter, internalVars)){//如果是数组
								codeBuffer.append(getParamInitCodeStr(procVarName,project,""));
								defineHelper.addInit(IParamDefineHelper.STD, procVarName);
						}
					
				}
			}
				
		
			codeBuffer.append("//结构体sqlca初始化" + ITokenConstant.NL);
			codeBuffer.append(STRUCT_SQLCA);
		}
		if(inputParams.size() > 0){
			codeBuffer.append("//输入参数初始化" + ITokenConstant.NL);
		}
		StringBuffer paramGroupBuffer = new StringBuffer();
		for(int i = 0;i < inputParams.size();i++){
			Parameter p = inputParams.get(i);
			if (p.getParamType().getValue()== ParamType.STD_FIELD_VALUE && defineHelper.canInit(IParamDefineHelper.STD, p.getId())) {
				codeBuffer.append(getParamDefineInitCodeStr(p.getId(),project,"",specialParamDefineHelper));
				defineHelper.addInit(IParamDefineHelper.STD, p.getId());
			}else if( p.getParamType().getValue()==ParamType.OBJECT_VALUE){//如果是对象

				if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, p.getId())){
					codeBuffer.append(getObjectParamDefineInitCodeStr(p.getId(),context,p.getId(),true));
					defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, p.getId());
					helper.addAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_INIT_VARIABLE_LIST, p.getId());
					popVarList.remove(p.getId());
				}
			
			}else if( p.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){//如果参数是参数组
					String path = p.getType();
					paramGroupBuffer.append("//初始化输入参数组参数("+path+")"+"\r\n");
					List<Parameter> stdParameters = new ArrayList<Parameter>();
					parserParamGroup(p,stdParameters,true,1,context);
					for(Parameter stdParameter:stdParameters){
						if(defineHelper.canInit(IParamDefineHelper.STD, stdParameter.getId())){
							paramGroupBuffer.append(getParamDefineInitCodeStr(stdParameter.getId(),project,"",specialParamDefineHelper));
							defineHelper.addInit(IParamDefineHelper.STD, stdParameter.getId());
						}
						
					}
					paramGroupBuffer.append("\r\n");
				
			}
		}
		//初始化需要从lpInUnPacker初始化的对象(由宏产生)
		Set<String> initObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_INIT_VARIABLE_LIST);
		for(String objectParam:initObjectList){
			if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, objectParam)){
				codeBuffer.append(getObjectParamDefineInitCodeStr(objectParam,context,objectParam,true));
				defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, objectParam);
				pseudoObjectParaList.add(objectParam);
				popVarList.remove(objectParam);
			}
			
		}
		
		//初始化需不要从lpInUnPacker初始化的输入对象(由宏产生)
		Set<String> noInitObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_NOINIT_VARIABLE_LIST);
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
			if(p.getParamType().getValue()== ParamType.STD_FIELD_VALUE
					&& (p.getFlags() != null) && p.getFlags().equalsIgnoreCase("IO") && defineHelper.canInit(IParamDefineHelper.STD, p.getId())
				){
				String varDefine = getParamDefineInitCodeStr(p.getId(),project,"",specialParamDefineHelper);
				if(StringUtils.isNotBlank(varDefine)){
					codeBuffer.append(varDefine);
					defineHelper.addInit(IParamDefineHelper.STD, p.getId());
				}
				
			}else if(p.getParamType().getValue()== ParamType.STD_FIELD_VALUE && defineHelper.canInit(IParamDefineHelper.STD, p.getId())){
				String varDefine  = getParamDefineCodeStr(p.getId(),project,null,"",specialParamDefineHelper,false);
				if(StringUtils.isNotBlank(varDefine)){
					defineHelper.addInit(IParamDefineHelper.STD, p.getId());
					codeBuffer.append(varDefine);
				}
				
				
			}else if( p.getParamType().getValue()==ParamType.OBJECT_VALUE){//如果是对象
				if(defineHelper.canInit(IParamDefineHelper.RECORD_OBJECT, p.getId())){
					codeBuffer.append(getOutObjectParamDefineInitCodeStr(p.getId(),context,p.getId()));
					defineHelper.addInit(IParamDefineHelper.RECORD_OBJECT, p.getId());
					helper.addAttribute(IAtomEngineContextConstant.ATTR_OUT_OBJECT_INIT_VARIABLE_LIST, p.getId());
					pseudoObjectParaList.add(p.getId());
					popVarList.remove(p.getId());
				}
			
			}else if( p.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){//如果参数是参数组
				List<Parameter> stdParameters = new ArrayList<Parameter>();
				parserParamGroup(p, stdParameters, false,1, context);
				for (Parameter stdParameter : stdParameters) {
					if (defineHelper.canInit(IParamDefineHelper.STD,
							stdParameter.getId())) {
						String varDefine = getParamDefineInitCodeStr(stdParameter.getId(), project, "",specialParamDefineHelper);
						if(StringUtils.isNotBlank(varDefine)){
							paramGroupBuffer.append(varDefine);
							defineHelper.addInit(IParamDefineHelper.STD,stdParameter.getId());
						}
						
					}

				}
				paramGroupBuffer.append("\r\n");
			}
				
		}
		//输出参数对象初始化(定义由宏加入的输出对象)
		Set<String> initOutObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_OUT_OBJECT_INIT_VARIABLE_LIST);
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
			Parameter p = internalVars.get(i);
			if((p.getId() != null) && (InternalParameterArrayHelper.isArrayParameter(p, internalVars)||defineHelper.canInit(IParamDefineHelper.STD, p.getId()))){
				codeBuffer.append(internalVarDefineInitCodeStr(p.getId(),project,internalVars,""));
				defineHelper.addInit(IParamDefineHelper.STD, p.getId());
			}
		}
		
		//伪代码
		for(int i = 0;i < popVarList.size();i++){
			String fieldName = popVarList.get(i);
			if(!specialsParams.contains(fieldName)&& (fieldName != null) && defineHelper.canInit(IParamDefineHelper.STD, fieldName)){//如未声明，则添加
					//特殊变量，也不需要声明，后面会自动处理。
					StandardField sField = MetadataServiceProvider.getMetadataModelByName(project, fieldName, IMetadataRefType.StdField, StandardField.class);
					if(sField == null && !specialParamDefineHelper.isSpecialParam(fieldName) ){
							throw new Exception(String.format("标准字段：%1$s不存在！",fieldName));	
					}
					String defineStr = getParamDefineCodeStr(fieldName,project,null,"",specialParamDefineHelper,false);
					if(StringUtils.isNotBlank(defineStr)){
						codeBuffer.append(getParamDefineCodeStr(fieldName,project,null,"",specialParamDefineHelper,false));
						defineHelper.addInit(IParamDefineHelper.STD, fieldName);
					}
					
				}
		}
		//特殊的伪代码中非标准字段声明
		specialVarIntoPopVarList(popVarList);
		codeBuffer.append(specialParamDefineHelper.getSpecialParamsDefineCodeStr(project, context,popVarList));
		
		//添加参数组始化代码
		if(StringUtils.isNotBlank(paramGroupBuffer.toString())){
			codeBuffer.append(paramGroupBuffer.toString());
		}
		
		//添加error_path_info的特殊处理
		codeBuffer.append(getErrorPathInfoDefineCodeStr(context));
		Set<String> rsPrcCallList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROCEDURE_CALL_RSRETURN);
		Set<String> funcCallList = helper.getAttribute(IAtomEngineContextConstant.ATTR_FUNC_CALL);
		if (funcCallList.size() > 0 || (rsPrcCallList.size() > 0) || (nrsPrcCallList.size() > 0)) {
			codeBuffer.append("hs_strncpy(@error_pathinfo_tmp,@error_pathinfo,500);\r\n");//函数调用，过程调用，将当前错误路径，用临时变量保存
		}
		
		
		return codeBuffer.toString();
	}
	/**
	 * @return
	 */
	private String getErrorPathInfoDefineCodeStr(Map<Object, Object> context) {
		IARESProject project = (IARESProject)context.get(IAtomEngineContextConstant.Aresproject);
		String length = "500";//error_pathinfo默认长度500
		StandardField stdField = MetadataServiceProvider.getMetadataModelByName(project, "error_pathinfo", IMetadataRefType.StdField, StandardField.class);
		if(stdField != null){
			BusinessDataType bType = MetadataServiceProvider.getMetadataModelByName(project, stdField.getDataType(), IMetadataRefType.BizType, BusinessDataType.class);
			if(bType != null){
				length = bType.getLength();
			}
		}
		AtomFunction af = (AtomFunction) context.get(IAtomEngineContextConstant.ResourceModel);
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
	 * 
	 * @param paramName
	 * @param project
	 * @param internalVars
	 * @param suffix，区分_cur的游标变量，与普通的proc变量
	 * @return
	 */
	private String getParamDefineCodeStr(String paramName,IARESProject project,List<InternalParam> internalVars,String suffix,SpecialParamDefineHelper specialParamDefineHelper,boolean isInternalDefineCall){
		StringBuffer codeBuffer = new StringBuffer();
		if(AtomFunctionCompilerUtil.isParameterINInternalVariablesByName(atomFunction, paramName) && !isInternalDefineCall){
			codeBuffer.append(varDefineCodeStr(paramName,project,internalVars,suffix));
		}else{
			StandardDataType stdType = null;
			try {
				stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(project, paramName);
			} catch (Exception e) {
				
				if(!specialParamDefineHelper.isSpecialParam(paramName)){
					ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
					String message = String.format("标准字段[%s]所对应的标准数据类型不存在", paramName);
					manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
					return "";
				}
			}
			TypeDefaultValue typpeDefValue = null;
			try {
				typpeDefValue = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName(project, paramName);
			} catch (Exception e) {
				if(!specialParamDefineHelper.isSpecialParam(paramName)){
					ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
					String message = String.format("标准字段[%s]关联的默认值不存在", paramName);
					manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
					return "";
				}
			}
			BusinessDataType busType = null;
			try {
				busType = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(project, paramName);
			} catch (Exception e) {
				if(!specialParamDefineHelper.isSpecialParam(paramName)){
					ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
					String message = String.format("标准字段[%s]关联的业务数据类型不存在。", paramName);
					manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
					return "";
				}
			}
			
			
			if((stdType != null) && ( typpeDefValue!= null) && ( busType!= null))//标准字段
			{
				String dataType = StringUtils.defaultIfBlank(stdType.getValue(MetadataServiceProvider.C_TYPE), "");
				String defValue = typpeDefValue.getValue(MetadataServiceProvider.C_TYPE);
				int length = 0;
				if(busType.getLength() != null){
					try {
						length = Integer.parseInt(busType.getLength()) + 1;
					} catch (Exception e) {
						//throw new Exception(String.format("业务数据类型:%1$s的长度为非法数字：%2$s。", busType.getName(),busType.getLength()));
					}
					//声明Char数组变量时，长度要加1
					dataType = dataType.replace("$L", length + "");
				}
				if(TypeRule.typeRuleCharArray(dataType)){
					codeBuffer.append(String.format(PARAM_DEFINE_STR,paramName + suffix,length));
				}else{
					codeBuffer.append(String.format(PARAM_DEFINE,dataType,paramName + suffix,defValue));
				}
				
			}
		}
		return codeBuffer.toString();
	}
	
	private String varDefineCodeStr(String paramName,IARESProject project,List<InternalParam> internalVars,String suffix){
		StringBuffer codeBuffer = new StringBuffer();
		if(internalVars == null){
			return "";
		}
		Parameter internalVar = containsKey(paramName,internalVars);
		String type =  internalVar.getType();
		if(InternalParameterArrayHelper.isArrayParameter(internalVar, internalVars)){
			type = InternalParameterArrayHelper.getArrayBusType(internalVar);
		}
		if(internalVar != null){
			if(internalVar.getParamType().getValue() == ParamType.NON_STD_FIELD_VALUE){

				StandardDataType internalDataType = null;
				try {
					internalDataType = MetadataServiceProvider.getStandardDataTypeOfBizTypeByName(project, type);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String defValue = internalVar.getDefaultValue();
				if(defValue == null){
					TypeDefaultValue internalTypeDefValue = null;
					try {
						internalTypeDefValue = MetadataServiceProvider.getTypeDefaultValueOfBizTypeByName(project, type);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(internalTypeDefValue != null){
						defValue = internalTypeDefValue.getValue(MetadataServiceProvider.C_TYPE);
					}
				}
				
				if(internalDataType != null){
					String dataType = internalDataType.getValue(MetadataServiceProvider.C_TYPE);
					BusinessDataType busType = null;
					try {
						busType = MetadataServiceProvider.getBusinessDataTypeByName(project, type);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int length = 0;
				    boolean hasTwoArray = false;//是否可以是二维数组
					if((busType != null) && (busType.getLength() != null)){
						try {
							length = Integer.parseInt(busType.getLength()) + 1;
						} catch (Exception e) {
							hasTwoArray = false;
							//throw new Exception(String.format("业务数据类型:%1$s的长度为非法数字：%2$s。", busType.getName(),busType.getLength()));
						}
						//定义Char数组变量时，长度要加1
						if(StringUtils.indexOf(dataType, "$L")>-1){//如果有$L那么可定义成二组数据
							hasTwoArray = true;
						}else {
							if(TypeRule.typeRuleChar(dataType)){//对于字符要特殊处理
								length = 2;
								hasTwoArray = true;
							}
						}
						dataType = dataType.replace("$L" , length + "");
					}
					
					if(InternalParameterArrayHelper.isArrayParameter(internalVar, internalVars)){//如果内部变量是二维的数组
						if(defineHelper.canInit(IParamDefineHelper.STD, paramName)){
							ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
					    if(hasTwoArray){
					    	 codeBuffer.append(String.format(PARAM_DEFINE_ARRAY2,InternalParameterArrayHelper.getArrayDataType(dataType),paramName + suffix,InternalParameterArrayHelper.getArrayLength(internalVar),length));
							 defineHelper.addInit(IParamDefineHelper.STD, paramName);
							 Set<String> procVarList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST);
							 if(!procVarList.contains(paramName)){
								 codeBuffer.append(String.format(PARAM_DEFINE_INIT_ARRAY2,paramName + suffix,InternalParameterArrayHelper.getArrayDataType(dataType),InternalParameterArrayHelper.getArrayLength(internalVar),length)); 
							 }
							
					    }else{
					    	 codeBuffer.append(String.format(PARAM_DEFINE_ARRAY1,InternalParameterArrayHelper.getArrayDataType(dataType),paramName + suffix,InternalParameterArrayHelper.getArrayLength(internalVar)));
					    	 defineHelper.addInit(IParamDefineHelper.STD, paramName);
					    	 Set<String> procVarList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST);
					    	 if(!procVarList.contains(paramName)){
					    		 codeBuffer.append(String.format(PARAM_DEFINE_INIT_ARRAY1,paramName + suffix,InternalParameterArrayHelper.getArrayDataType(dataType),InternalParameterArrayHelper.getArrayLength(internalVar))); 
					    	 }
					    	 

							}
						}
						
					
					}else if(TypeRule.typeRuleCharArray(dataType)){
							codeBuffer.append(String.format(PARAM_DEFINE_STR,paramName + suffix,length));
					}else{
						codeBuffer.append(String.format(PARAM_DEFINE,dataType,paramName + suffix,defValue));
					}
				}
			}else if(internalVar.getParamType().getValue() == ParamType.STD_FIELD_VALUE){
				//如果是标准字段，其声明方式与输入输出参数声明方式一致，注意这里的内部变量列表必须传为空，否则会递归调用，出现死循环
				codeBuffer.append(getParamDefineCodeStr(internalVar.getId(),project,null,"",null,true));
			}
		}
		return codeBuffer.toString();
	}
	
	/**
	 * 内部变量声明并初始化
	 * @param paramName
	 * @param project
	 * @param internalVars
	 * @param suffix
	 * @return
	 */
	private String internalVarDefineInitCodeStr(String paramName,IARESProject project,List<InternalParam> internalVars,String suffix){
		StringBuffer codeBuffer = new StringBuffer();
		if(internalVars == null){
			return "";
		}
		Parameter internalVar = containsKey(paramName,internalVars);
		String type =  internalVar.getType();
		if(InternalParameterArrayHelper.isArrayParameter(internalVar, internalVars)){
			type = InternalParameterArrayHelper.getArrayBusType(internalVar);
		}
		if(internalVar != null){
			if(internalVar.getParamType().getValue() == ParamType.NON_STD_FIELD_VALUE){

				StandardDataType internalDataType = null;
				try {
					internalDataType = MetadataServiceProvider.getStandardDataTypeOfBizTypeByName(project, type);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String defValue = internalVar.getDefaultValue();
				if(defValue == null){
					TypeDefaultValue internalTypeDefValue = null;
					try {
						internalTypeDefValue = MetadataServiceProvider.getTypeDefaultValueOfBizTypeByName(project, type);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(internalTypeDefValue != null){
						defValue = internalTypeDefValue.getValue(MetadataServiceProvider.C_TYPE);
					}
				}
				
				if(internalDataType != null){
					String dataType = internalDataType.getValue(MetadataServiceProvider.C_TYPE);
					BusinessDataType busType = null;
					try {
						busType = MetadataServiceProvider.getBusinessDataTypeByName(project, type);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int length = 0;
				    boolean hasTwoArray = false;//是否可以是二维数组
					if((busType != null) && (busType.getLength() != null)){
						try {
							length = Integer.parseInt(busType.getLength()) + 1;
						} catch (Exception e) {
							hasTwoArray = false;
							//throw new Exception(String.format("业务数据类型:%1$s的长度为非法数字：%2$s。", busType.getName(),busType.getLength()));
						}
						//定义Char数组变量时，长度要加1
						if(StringUtils.indexOf(dataType, "$L")>-1){//如果有$L那么可定义成二组数据
							hasTwoArray = true;
						}else {
							if(TypeRule.typeRuleChar(dataType)){//对于字符要特殊处理
								length = 2;
								hasTwoArray = true;
							}
						}
						dataType = dataType.replace("$L" , length + "");
					}
					if(InternalParameterArrayHelper.isArrayParameter(internalVar, internalVars)){
					
						//如果内部变量是二维的数组
						if(defineHelper.canInit(IParamDefineHelper.STD, paramName)){
							ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
					    if(hasTwoArray){
					    	 codeBuffer.append(String.format(PARAM_DEFINE_ARRAY2,InternalParameterArrayHelper.getArrayDataType(dataType),paramName + suffix,InternalParameterArrayHelper.getArrayLength(internalVar),length));
							 defineHelper.addInit(IParamDefineHelper.STD, paramName);
							 Set<String> procVarList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST);
							 if(!procVarList.contains(paramName)){
								 codeBuffer.append(String.format(PARAM_DEFINE_INIT_ARRAY2,paramName + suffix,InternalParameterArrayHelper.getArrayDataType(dataType),InternalParameterArrayHelper.getArrayLength(internalVar),length)); 
							 }
							
					    }else{
					    	 codeBuffer.append(String.format(PARAM_DEFINE_ARRAY1,InternalParameterArrayHelper.getArrayDataType(dataType),paramName + suffix,InternalParameterArrayHelper.getArrayLength(internalVar)));
					    	 defineHelper.addInit(IParamDefineHelper.STD, paramName);
					    	 Set<String> procVarList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST);
					    	 if(!procVarList.contains(paramName)){
					    		 codeBuffer.append(String.format(PARAM_DEFINE_INIT_ARRAY1,paramName + suffix,InternalParameterArrayHelper.getArrayDataType(dataType),InternalParameterArrayHelper.getArrayLength(internalVar))); 
					    	 }
							}
						}
						
					
					
						
					
					}else if(TypeRule.typeRuleCharArray(dataType)){
							codeBuffer.append(String.format(PARAM_DEFINE_STR,paramName + suffix,length));
					}else{
						codeBuffer.append(String.format(PARAM_DEFINE,dataType,paramName + suffix,defValue));
					}
				}
			}else if(internalVar.getParamType().getValue() == ParamType.STD_FIELD_VALUE){
				//如果是标准字段，其声明方式与输入输出参数声明方式一致，注意这里的内部变量列表必须传为空，否则会递归调用，出现死循环
				codeBuffer.append(getParamDefineCodeStr(internalVar.getId(),project,null,"",null,true));
			}
		}
		return codeBuffer.toString();
	}
	
	private String getParamInitCodeStr(String paramName,IARESProject project,String suffix) throws Exception{
		Parameter parameter = InternalParameterArrayHelper.getInternalParameter(paramName,atomFunction.getInternalVariables());
		if(InternalParameterArrayHelper.isArrayParameter(parameter, atomFunction.getInternalVariables())){//proc数组的初始化特殊处理
			return getParamInitProcArrayCodeStr(parameter, project, suffix);
		}
		StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(project, paramName);
		BusinessDataType busType = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(project, paramName);
		StringBuffer codeBuffer = new StringBuffer();
		 boolean hasTwoArray = false;//是否可以是二维数组
		String dataType = stdType.getValue(MetadataServiceProvider.C_TYPE);
		int length = 0;
		if(busType.getLength() != null){
			try {
				//声明Char数组变量时，长度要加1，但是初始化时不要加1
				length = Integer.parseInt(StringUtils.defaultIfBlank(busType.getLength(), "0"));
			} catch (Exception e) {
				hasTwoArray = false;
				throw new Exception(String.format("业务数据类型:%1$s的长度为非法数字：%2$s。", busType.getName(),busType.getLength()));
			}
			if(StringUtils.indexOf(dataType, "$L")>-1){//如果有$L那么可定义成二组数据
				hasTwoArray = true;
			}else {
				if(TypeRule.typeRuleChar(dataType)){//对于字符要特殊处理
					length = 2;
					hasTwoArray = true;
				}
			}
			dataType = dataType.replace("$L" , length + "");
		}
		
		//如果是数组
		if(parameter!=null && InternalParameterArrayHelper.isArrayParameter(parameter, atomFunction.getInternalVariables())){//如果内部变量是二维的数组
				ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		    if(hasTwoArray){
		    	Set<String> procVarList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST);
				 if(procVarList.contains(paramName)){
					 codeBuffer.append(String.format(PARAM_DEFINE_INIT_ARRAY2,paramName + suffix,InternalParameterArrayHelper.getArrayDataType(dataType),InternalParameterArrayHelper.getArrayLength(parameter),length)); 
				 }
				
		    }else{
		    	Set<String> procVarList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST);
		    	 if(procVarList.contains(paramName)){
		    		 codeBuffer.append(String.format(PARAM_DEFINE_INIT_ARRAY1,paramName + suffix,InternalParameterArrayHelper.getArrayDataType(dataType),InternalParameterArrayHelper.getArrayLength(parameter))); 
				}

			}
		}
		else if(TypeRule.typeRuleCharArray(dataType)){
			//声明Char数组变量时，长度要加1，但是初始化时不要加1
			codeBuffer.append(String.format(PARAM_INIT_STR,paramName + suffix,length));
		}else
		{
			if (TypeRule.typeRuleInt(dataType)) {
				codeBuffer.append(String.format(PARAM_INIT, "Int",paramName + suffix));
			} else if (TypeRule.typeRuleDouble(dataType)) {
				codeBuffer.append(String.format(PARAM_INIT, "Double",paramName + suffix));
			}else if (TypeRule.typeRuleChar(dataType)) {
				codeBuffer.append(String.format(PARAM_INIT_CHAR,paramName + suffix));
			}else if (TypeRule.typeRulePacker(dataType)) {
				codeBuffer.append(String.format(PARAM_DEFIN_UNPACKER,paramName ));
			}else if (TypeRule.typeRuleClob(dataType)) {
				codeBuffer.append(getClobParamDefineInitCodeStr(paramName));
			}else {
				throw new Exception(String.format("没有对数据类型:%1$s的初始化方式",dataType));
			}
		}
		return codeBuffer.toString();
	}
	
	
	/**
	 * 初始化proc数组
	 * @param parameter
	 * @param project
	 * @param suffix
	 * @return
	 */
	private String getParamInitProcArrayCodeStr(Parameter parameter,IARESProject project,String suffix){
		StringBuffer codeBuffer = new StringBuffer();
		String   type = InternalParameterArrayHelper.getArrayBusType(parameter);
			if(parameter.getParamType().getValue() == ParamType.NON_STD_FIELD_VALUE){

				StandardDataType internalDataType = null;
				try {
					internalDataType = MetadataServiceProvider.getStandardDataTypeOfBizTypeByName(project, type);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String defValue = parameter.getDefaultValue();
				if(defValue == null){
					TypeDefaultValue internalTypeDefValue = null;
					try {
						internalTypeDefValue = MetadataServiceProvider.getTypeDefaultValueOfBizTypeByName(project, type);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(internalTypeDefValue != null){
						defValue = internalTypeDefValue.getValue(MetadataServiceProvider.C_TYPE);
					}
				}
				
				if(internalDataType != null){
					String dataType = internalDataType.getValue(MetadataServiceProvider.C_TYPE);
					BusinessDataType busType = null;
					try {
						busType = MetadataServiceProvider.getBusinessDataTypeByName(project, type);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int length = 0;
				    boolean hasTwoArray = false;//是否可以是二维数组
					if((busType != null) && (busType.getLength() != null)){
						try {
							length = Integer.parseInt(busType.getLength()) + 1;
						} catch (Exception e) {
							hasTwoArray = false;
							//throw new Exception(String.format("业务数据类型:%1$s的长度为非法数字：%2$s。", busType.getName(),busType.getLength()));
						}
						//定义Char数组变量时，长度要加1
						if(StringUtils.indexOf(dataType, "$L")>-1){//如果有$L那么可定义成二组数据
							hasTwoArray = true;
						}else {
							if(TypeRule.typeRuleChar(dataType)){//对于字符要特殊处理
								length = 2;
								hasTwoArray = true;
							}
						}
						dataType = dataType.replace("$L" , length + "");
					}
					
						ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
					    if(hasTwoArray){
							 Set<String> procVarList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST);
							 if(procVarList.contains( parameter.getId())){
								 codeBuffer.append(String.format(PARAM_DEFINE_INIT_ARRAY2, parameter.getId() + suffix,InternalParameterArrayHelper.getArrayDataType(dataType),InternalParameterArrayHelper.getArrayLength(parameter),length)); 
							 }
							
					    }else{
					    	 Set<String> procVarList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST);
					    	 if(procVarList.contains( parameter.getId())){
					    		 codeBuffer.append(String.format(PARAM_DEFINE_INIT_ARRAY1, parameter.getId() + suffix,InternalParameterArrayHelper.getArrayDataType(dataType),InternalParameterArrayHelper.getArrayLength(parameter))); 
					    	 }

				}

			}
		}
		return codeBuffer.toString();
	}
	
	
	
	private String getParamDefineInitCodeStr(String paramName,IARESProject project,String suffix,SpecialParamDefineHelper specialParamDefineHelper) throws Exception{
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
			codeBuffer.append(String.format(PARAM_DEFINE_INIT_CHAR,paramName + suffix));
		}else if(TypeRule.typeRuleCharArray(dataType)){
			codeBuffer.append(getParamDefineCodeStr(paramName,project,null,suffix,specialParamDefineHelper,false));
			//声明及初始化Char数组变量时，长度要加1
			codeBuffer.append(String.format(PARAM_INIT_STR,paramName + suffix,length));
		}
		else
		{
			if (TypeRule.typeRuleInt(dataType)) {
				codeBuffer.append(String.format(PARAM_DEFINE_INIT,dataType, paramName + suffix,"Int"));
			} else if (TypeRule.typeRuleDouble(dataType)) {
				codeBuffer.append(String.format(PARAM_DEFINE_INIT,dataType,paramName + suffix, "Double"));
			}else if (TypeRule.typeRuleClob(dataType)) {
				codeBuffer.append(getClobParamDefineInitCodeStr(paramName));
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
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
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
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
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
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
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
