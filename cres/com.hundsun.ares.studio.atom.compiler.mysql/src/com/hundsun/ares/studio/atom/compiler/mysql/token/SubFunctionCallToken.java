/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.atom.constants.IAtomRefType;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.ParamGroupUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.constant.MarkConfig;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * @author qinyuan
 *
 */
public class SubFunctionCallToken implements ICodeToken{
	
	//子函数调用宏
	private IMacroToken token; 
	
	//被调用的函数资源
	private IARESResource calledRes;
	//调用的函数资源,如af/as等
	private AtomFunction callRes;
	
	//子函数调用最终返回代码
	//[cName][ipField][ipValue][objID][, lpConn][resultset][errinfolength][第一列标志K处理][第一列标志E处理][svr_end][结果集取值修改本地变量][函数名,“F”]
	private final static String SUB_FUNC_CALL_RETURN_CODE = NEWLINE +"//调用子函数:%1$s" + NEWLINE +
			"lpFuncInPacker->BeginPack();" + NEWLINE +
			"%2$s" + NEWLINE +
			"%3$s" + NEWLINE +
			"lpFuncInPacker->EndPack();" + NEWLINE +
			"//将打包结果转为参数解包器供函数使用" + NEWLINE +
			"lpOut%4$s->BeginPack();" + NEWLINE +
			"iReturnCode = %12$s%4$s(lpContext,lpFuncInPacker->UnPack(),lpOut%4$s%5$s);" + NEWLINE +
			"lpOut%4$s->EndPack();" + NEWLINE +
			"lpResultSet%6$s = lpOut%4$s->UnPack();//结果解包" + NEWLINE +
			"if ( 0 != iReturnCode&& iReturnCode != ERR_SYSWARNING)" + NEWLINE +
			"{" + NEWLINE +
			"@error_no = iReturnCode;" + NEWLINE +
			"hs_strncpy(@error_info, lpResultSet%6$s->GetStr(\"error_info\"),%7$s);" + NEWLINE +
			"%8$s" + NEWLINE +
			"%9$s" + NEWLINE +
			"%10$s" + NEWLINE +
			"}" + NEWLINE +
			"else" + NEWLINE +
			"{" + NEWLINE +
			"%11$s" + NEWLINE +
			"}" + NEWLINE;
	
	//chararry 类型 添加字段
	private final static String IP_PAKER_ADDFIELD_CHARARRAY = "lpFuncInPacker->AddField(\"%1$s\", \'S\', %2$s);";
	private final static String IP_PAKER_ADDFIELD_CHARARRAY2 = "lpFuncInPacker->AddField(\"%1$s\", \'S\');";
	//char 类型 添加字段
	private final static String IP_PAKER_ADDFIELD_CHAR = "lpFuncInPacker->AddField(\"%1$s\", \'C\');";
	//clob 类型 添加字段
	private final static String IP_PAKER_ADDFIELD_COLB = "lpFuncInPacker->AddField(\"%1$s\", \'R\', %2$s);";
	//float 类型 添加字段
	private final static String IP_PAKER_ADDFIELD_FLOAT = "lpFuncInPacker->AddField(\"%1$s\", \'D\', %2$s, %3$s);";
	//int 类型 添加字段
	private final static String IP_PAKER_ADDFIELD_INT = "lpFuncInPacker->AddField(\"%1$s\",\'I\');";
	//default 类型 添加字段
	private final static String IP_PAKER_ADDFIELD_DEFAULT = "lpFuncInPacker->AddField(\"%1$s\",\'S\');";
	//error_pathinfo 字段  添加
	private final static String IP_PAKER_ADDFIELD_ERROR_PATHINFO = "lpFuncInPacker->AddField(\"error_pathinfo\", \'S\', %1$s);";
	
//	//int 类型 添加值
//	private final static String IP_PAKER_ADDVALUE_INT = "lpFuncInPacker->AddInt(%1$s);\t//%2$s";
//	//chararry 类型 添加值
//	private final static String IP_PAKER_ADDVALUE_CHARARRAY = "lpFuncInPacker->AddStr(%1$s);\t//%2$s";
//	//char 类型 添加值
//	private final static String IP_PAKER_ADDVALUE_CHAR = "lpFuncInPacker->AddChar(%1$s);\t//%2$s";
//	//float 类型 添加值
//	private final static String IP_PAKER_ADDVALUE_FLOAT = "lpFuncInPacker->AddDouble(%1$s);\t//%2$s";
	//error_pathinfo 字段值  添加
	private final static String IP_PAKER_ADDVALUE_ERROR_PATHINFO = "lpFuncInPacker->AddStr(%1$s);\t//%2$s";
	//clob 类型 添加值
	private final static String IP_PAKER_ADDVALUE_COLB = "lpFuncInPacker->AddRaw(%1$s,%2$s);\t//%3$s";
	//default 类型 添加值
	private final static String IP_PAKER_ADDVALUE_DEFAULT = "lpFuncInPacker->Add%1$s(%2$s);\t//%3$s";
	
	//第一列标志K处理
	private final static String FIRST_ROW_FLAG_E = "EXEC SQL CLOSE cursor%s;";
	//第一列标志E处理
	
	//proc事务回滚
	private final static String ROLL_BACK = "EXEC SQL rollback;";
	//goto_svr_end
	private final static String goto_svr_end = "hs_strncpy(@error_pathinfo,lpResultSet%1$s->GetStr(\"error_pathinfo\"),%2$s);\r\n%3$s";
	
	//结果集取值修改本地变量 chararray类型
	private final static String RESULTSET_GETVALUE_CHARARRAY = "hs_strncpy(%1$s, lpResultSet%2$s->GetStr(\"%3$s\"), sizeof(%1$s) - 1);";
	//结果集取值修改本地变量 clob类型
	private final static String RESULTSET_GETVALUE_CLOB = "%1$s = lpResultSet%2$s->GetRaw(\"%3$s\",&%4$s);";
	//结果集取值修改本地变量 default默认类型，主要有Char\Double\Int
	private final static String RESULTSET_GETVALUE_DEAFULT = "%1$s = lpResultSet%2$s->Get%3$s(\"%4$s\");";
	private List<String> pseudoObjectParaList = new ArrayList<String>();
	
	/**
	 * 子函数调用
	 */
	public SubFunctionCallToken(IMacroToken token,IARESResource resource) {
		this.token = token;
		this.calledRes = resource;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return CODE_TEXT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		
		callRes = (AtomFunction)context.get(IAtomEngineContextConstantMySQL.ResourceModel);//调用资源，有可能是af/as
		pseudoObjectParaList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_OBJECT_PARA_LIST);
		IARESProject project = (IARESProject)context.get(IAtomEngineContextConstantMySQL.Aresproject);
		AtomFunction atomFuc = calledRes.getInfo(AtomFunction.class);//被调用的原子函数
		List<Parameter> ipParas = new ArrayList<Parameter>();//被调用的原子函数的输入参数
		for(Parameter parameter:atomFuc.getInputParameters()){
			
			if(parameter.getParamType().getValue()== ParamType.PARAM_GROUP_VALUE){
				List<Parameter> parameters = new ArrayList<Parameter>();
				ParamGroupUtil.parserParamGroup(parameter, parameters,1, project);
				ipParas.addAll(parameters);
			}else{
				ipParas.add(parameter);
			}
			
		}
		List<Parameter> opParas = new ArrayList<Parameter>();//被调用的原子函数的输出参数
	    for(Parameter parameter:atomFuc.getOutputParameters()){
			
			if(parameter.getParamType().getValue()== ParamType.PARAM_GROUP_VALUE){
				List<Parameter> parameters = new ArrayList<Parameter>();
				ParamGroupUtil.parserParamGroup(parameter, parameters,1 ,project);
				opParas.addAll(parameters);
			}else{
				opParas.add(parameter);
			}
			
		}
		
		String[] macroParas = token.getParameters();//宏参数
		String defValuePara = "";//
		if(macroParas.length > 0) {
			defValuePara = macroParas[0];
		}
		//解析后的宏默认值参数
		Map<String, String> defaultValue = PseudoCodeParser.parserKeyValueWithAt(defValuePara);
		
		List<String> pararms = (List<String>) context.get(IAtomEngineContextConstantMySQL.PseudoCode_Para_LIST);
		return String.format(SUB_FUNC_CALL_RETURN_CODE, atomFuc.getChineseName(),genInputParamentPackAddField(ipParas,opParas,defaultValue,context),
				genInputParamentPackAddValue(ipParas, opParas,pararms , defaultValue,context),
				(StringUtils.isBlank(atomFuc.getObjectId())?atomFuc.getName():atomFuc.getObjectId())
				,deliverDBConnection(atomFuc ,project),
				(StringUtils.isBlank(atomFuc.getObjectId())?atomFuc.getName():atomFuc.getObjectId()),getFieldTypeLength("error_info"),
				getFirstRowFlagK(),getFirstRowFlagE(context),getGoToSvrEnd(context),resultSetGetValue(context,opParas, defaultValue),
				(StringUtils.isBlank(atomFuc.getObjectId())?"":"F"));
	}
	
	/**
	 * 输出参数重定向
	 * @param context 上下文
	 * @param opParas 调用函数的输出参数
	 * @param defaultValue 宏参数-默认值
	 * @return
	 * @throws Exception
	 */
	private String resultSetGetValue(Map<Object, Object> context,List<Parameter> opParas,Map<String, String> defaultValue) throws Exception {
		StringBuffer ret = new StringBuffer();
		for (Parameter parameter : opParas) {
			if((parameter.getFlags() != null) &&parameter.getFlags().contains(MarkConfig.MARK_IOFLAG)) {
				continue;
			}
			String outParamName = parameter.getId();
			String localVariableName ="";
			
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
			Set<String> rsList = (Set<String>)helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_VARIABLE_LIST);
			if(defaultValue.containsKey(outParamName)) {
				String stdFieldName = defaultValue.get(outParamName).replace("@", "");
				//TODO 判断proc声明以及非proc声明
//				if (stdField != null || getFunction().getCommonInformation().getProc_declare_list().isContain(stdFieldName)
//						|| getFunction().getCommonInformation().getNon_proc_declare_list().isContain(stdFieldName))
				//if(rsList.contains(stdFieldName))
					localVariableName = defaultValue.get(outParamName);
			}else if(defaultValue.containsKey(MarkConfig.MARK_AT + outParamName)){
				String stdFieldName = defaultValue.get(MarkConfig.MARK_AT + outParamName).replace("@", "");
				//TODO 判断proc声明以及非proc声明
				//if(rsList.contains(stdFieldName))
					localVariableName = defaultValue.get(outParamName);
			}else{
				continue;
			}
			
			if (localVariableName.indexOf(MarkConfig.MARK_AT) < 0)// 没有@,需要加上
			{
				localVariableName = "@" + localVariableName;
			}
			AtomFunction atomFuc = calledRes.getInfo(AtomFunction.class);//被调用的原子函数
			String resultSetId = StringUtils.isBlank(atomFuc.getObjectId())?atomFuc.getName():atomFuc.getObjectId();//结果集Id
			if(!localVariableName.equals("")){
				StandardDataType stdType = null;
				String type = StringUtils.EMPTY;
				BusinessDataType busType = null;
				try {
					if(parameter.getParamType().getValue()!=ParamType.OBJECT_VALUE && parameter.getParamType().getValue()!=ParamType.PARAM_GROUP_VALUE){
						stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(calledRes.getARESProject(), outParamName);
						if(stdType!=null){
							 type = stdType.getValue(MetadataServiceProvider.C_TYPE);
						}
						busType = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(calledRes.getARESProject(), outParamName);
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				int length = 0;
				if(busType!=null && busType.getLength() != null){
					try {
						length = Integer.parseInt(busType.getLength()) ;
					} catch (Exception e) {
						//throw new Exception(String.format("业务数据类型:%1$s的长度为非法数字：%2$s。", busType.getName(),busType.getLength()));
					}
					type = type.replace("$L", length + "");
				}
				if(TypeRule.typeRuleClob(type)|| parameter.getParamType().getValue()==ParamType.OBJECT_VALUE){// 最终类型为Clob或者为对象
					String firstFlag = "";
					String firstIntFlag = "";
					String localVariableNameWithOutAT = StringUtils.replaceOnce(localVariableName, MarkConfig.MARK_AT, "").trim();
					String realParam = StringUtils.EMPTY;
					//对于对象特殊处理
					if( StringUtils.endsWith(localVariableNameWithOutAT,"ResultSet")){
						realParam = StringUtils.substring(localVariableNameWithOutAT,0,StringUtils.indexOf(localVariableNameWithOutAT,"ResultSet")).trim();
					}else{
						realParam = localVariableNameWithOutAT;
					}
					//输入输出中都可
					if(AtomFunctionCompilerUtil.isParameterINInputAndOutputParameterByName(callRes,
							localVariableNameWithOutAT,calledRes.getARESProject()) ){
						firstFlag += "p_";
						firstIntFlag += "pi_";
						ret.append(String.format(RESULTSET_GETVALUE_CLOB, firstFlag + localVariableNameWithOutAT,resultSetId,
								outParamName,firstIntFlag + localVariableNameWithOutAT));
					}else if(AtomFunctionCompilerUtil.isParameterINInputAndOutputParameterByName(callRes,
							realParam,calledRes.getARESProject()) ){
						firstFlag += "p_";
						firstIntFlag += "pi_";
						ret.append(String.format(RESULTSET_GETVALUE_CLOB, firstFlag + realParam,resultSetId,
								outParamName,firstIntFlag + realParam));
					}else if( StringUtils.endsWith(localVariableNameWithOutAT,"ResultSet")&& AtomFunctionCompilerUtil.isParameterINInputAndOutputParameterByName(atomFuc,
							realParam,calledRes.getARESProject()) ){//在调用输入输出中没有,但如果是对象且在被调用的输入输出中则特殊处理
						firstFlag += "p_";
						firstIntFlag += "pi_";
						ret.append(String.format(RESULTSET_GETVALUE_CLOB, firstFlag + realParam,resultSetId,
								outParamName,firstIntFlag + realParam));
					}else if(AtomFunctionCompilerUtil.isParameterINInputAndOutputParameterByName(atomFuc,
							realParam,calledRes.getARESProject()) ){//在调用输入输出中没有,但如果是对象且在被调用的输入输出中则特殊处理
						firstFlag += "p_";
						firstIntFlag += "pi_";
						ret.append(String.format(RESULTSET_GETVALUE_CLOB, firstFlag + realParam,resultSetId,
								outParamName,firstIntFlag + realParam));
					}
					else {
						firstFlag += "v_";
						firstIntFlag += "vi_";
						ret.append(String.format(RESULTSET_GETVALUE_CLOB, firstFlag + localVariableNameWithOutAT,resultSetId,
								outParamName,firstIntFlag + localVariableNameWithOutAT));
					}
					
				}else if (TypeRule.typeRuleCharArray(type)){// 最终类型为char[]
					ret.append(String.format(RESULTSET_GETVALUE_CHARARRAY, localVariableName,resultSetId,outParamName));
				}else {
					if (TypeRule.typeRuleInt(type)){// 最终类型为int
						ret.append(String.format(RESULTSET_GETVALUE_DEAFULT, localVariableName,resultSetId,"Int",outParamName));
					}else if (TypeRule.typeRuleChar(type)){// 最终类型为char
						ret.append(String.format(RESULTSET_GETVALUE_DEAFULT, localVariableName,resultSetId,"Char",outParamName));
					} else if (TypeRule.typeRuleDouble(type)){// 最终类型为double
						ret.append(String.format(RESULTSET_GETVALUE_DEAFULT, localVariableName,resultSetId,"Double",outParamName));
					}
				}
			}
			ret.append(NEWLINE);
		}
		
		return ret.toString();
	}
	
	/**
	 * goto_svr_end处理
	 * @return
	 */
	private String getGoToSvrEnd(Map<Object, Object> context) throws Exception{
		AtomFunction atomFuc = calledRes.getInfo(AtomFunction.class);//被调用的原子函数
		String resultSet = StringUtils.isBlank(atomFuc.getObjectId())?atomFuc.getName():atomFuc.getObjectId();//getAttrLastValue(context,IAtomEngineContextConstant.ATTR_FUNC_CALL);
		String error_pathinfo_length = getFieldTypeLength("error_pathinfo");
		String otherInfo = "goto svr_end;\r\n";
		if((token.getFlag() != null) && (token.getFlag() != "") && token.getFlag().contains(MarkConfig.MARK_IFLAG_M))
		{
			otherInfo = "";
		}else if((callRes.getInterfaceFlag() != null) && (callRes.getInterfaceFlag() != "") && callRes.getInterfaceFlag().contains(MarkConfig.MARK_IFLAG_M)){
			otherInfo = "";
		}
		
		return String.format(goto_svr_end, resultSet,error_pathinfo_length,otherInfo);
	}
	
	/**
	 * 获取参数的最后一个值
	 * @param context
	 * @param arg 参数，例如获取最近的结果集为IAtomEngineContextConstant.ATTR_FUNC_RESULTSET
	 * 			<br>参考{@link com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant}
	 * @return
	 * @throws Exception
	 */
//	private String getAttrLastValue(Map<Object, Object> context,String arg) throws Exception{
//		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
//		Set<String> rsList = helper.getAttribute(arg);
//		int size = rsList.size();
//		String[] rss = rsList.toArray(new String[size]);
//		if(size > 0) {
//			IFunctionMacroTokenService fmservice = (IFunctionMacroTokenService)context.get(IAtomEngineContextConstant.Function_Macro_Service);
//			String functionName = rss[size - 1];
//			AtomFunction func = fmservice.getFunction(functionName).getInfo(AtomFunction.class);
//			return func.getObjectId();
//		}
//		return "";
//	}
	
	/**
	 * 第一列标志E处理
	 * @return
	 */
	private String getFirstRowFlagE(Map<Object, Object> context) {
		if(token.getFlag().contains(MarkConfig.MARK_IFLAG_E)) {
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
			Set<String> curVars = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_CURSOR_LIST);
			if(curVars.size()>0){
				return String.format(FIRST_ROW_FLAG_E, (curVars.toArray(new String[0]))[curVars.size()-1]);
			}
			
		}
		
		return "";
	}
	
	/**
	 * 第一列标志K处理
	 * @return
	 */
	private String getFirstRowFlagK() {
		if(token.getFlag().contains(MarkConfig.MARK_IFLAG_K)) {
			return ROLL_BACK;
		}
		return "";
	}
	
	/**
	 * 获取字段类型长度
	 * @param fieldName 字段名
	 * @return 如果字段为标准字段，返回字段对应标准类型的长度，否则返回“500”
	 * @throws Exception
	 */
	private String getFieldTypeLength(String fieldName) throws Exception{
		StandardField field = MetadataServiceProvider.getMetadataModelByName(calledRes.getARESProject(), fieldName, IMetadataRefType.StdField, StandardField.class);

		if((null != field)) {
			BusinessDataType type = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(calledRes.getARESProject(), fieldName);
			if(type != null){
				return "500";
			}
			return type.getLength();
		}else{
			return "500";
		}
	}
	
	/**
	 * 传递数据库连接<br>
	 * 被调用的函数是否和主函数连接相同,并且被调用的函数不包含"V"标志，则传递数据库连接
	 * @param atomFuc 调用的原子函数
	 * @return
	 */
	private String deliverDBConnection(AtomFunction atomFuc ,IARESProject project) {
		String database = "";
		if(atomFuc instanceof AtomService){
			database = AtomFunctionCompilerUtil.getAtomDatabase(project, atomFuc.getDatabase(), atomFuc.getChineseName(), IAtomRefType.ATOM_SERVICE_CNAME ,atomFuc.getInterfaceFlag());
		}else{
			database = AtomFunctionCompilerUtil.getAtomDatabase(project, atomFuc.getDatabase(), atomFuc.getChineseName(), IAtomRefType.ATOM_FUNCTION_CNAME ,atomFuc.getInterfaceFlag());
		}
		String ad = "";
		if(callRes instanceof AtomService){
			ad = AtomFunctionCompilerUtil.getAtomDatabase(project, callRes.getDatabase(), callRes.getChineseName(), IAtomRefType.ATOM_SERVICE_CNAME ,callRes.getInterfaceFlag());
		}else{
			ad = AtomFunctionCompilerUtil.getAtomDatabase(project, callRes.getDatabase(), callRes.getChineseName(), IAtomRefType.ATOM_FUNCTION_CNAME ,callRes.getInterfaceFlag());
		}
		
		if(StringUtils.isNotBlank(database) && StringUtils.equals(ad, database) && !StringUtils.equalsIgnoreCase(callRes.getInterfaceFlag(), MarkConfig.MARK_IFLAG_R)){
			if (StringUtils.isNotBlank(atomFuc.getInterfaceFlag()) && !atomFuc.getInterfaceFlag().contains(MarkConfig.MARK_IFLAG_V)) {
				return ", lpConn";
			}
			return ", lpConn";
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 输入参数打包添加字段
	 * @param ipParas 输入参数
	 * @param opParas 输出参数
	 * @return
	 * @throws Exception
	 */
	private String genInputParamentPackAddField(List<Parameter> ipParas,List<Parameter> opParas,Map<String, String> defaultValue,Map<Object, Object> context)  throws Exception{
		StringBuffer ret = new StringBuffer();
		boolean isErrorPathInfo = false;
		
		//1.函数输入参数
		for (Parameter parameter : ipParas) {
			setInputParaPackAddField(ret, parameter,defaultValue,context);
			if(parameter.getId().equals("error_pathinfo")){
				isErrorPathInfo = true;
			}
		}
		
		//2.函数中带“IO”的输出参数
		for (Parameter parameter : opParas) {
			if((parameter.getFlags() != null) && parameter.getFlags().toUpperCase().equals(MarkConfig.MARK_IOFLAG)) {
				setInputParaPackAddField(ret, parameter,defaultValue,context);
				if(parameter.getId().equals("error_pathinfo")){
					isErrorPathInfo = true;
				}
			}
		}
		
		//error_pathinfo 特殊处理，如果输入参数没有，则需要默认添加
		if(!isErrorPathInfo) { 
			StandardField sField = MetadataServiceProvider.getMetadataModelByName(calledRes.getARESProject(), "error_pathinfo", IMetadataRefType.StdField, StandardField.class);
			//error_pathinfo为标准字段，且其业务数据类型存在
			if((sField != null)){
				BusinessDataType bType = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(calledRes.getARESProject(), "error_pathinfo");
				if(bType != null)
				{
					ret.append(String.format(IP_PAKER_ADDFIELD_ERROR_PATHINFO, bType.getLength()));
					ret.append(NEWLINE);
				}else{
					ret.append(String.format(IP_PAKER_ADDFIELD_ERROR_PATHINFO, 500));
					ret.append(NEWLINE);
				}
			}else{
				ret.append(String.format(IP_PAKER_ADDFIELD_ERROR_PATHINFO, 500));
				ret.append(NEWLINE);
			}
			
		}
		return ret.toString();
	}

	private void setInputParaPackAddField(StringBuffer ret, Parameter parameter, Map<String, String> defaultValue,Map<Object,Object> context)  throws Exception{
		String type = "";
		if(parameter.getParamType().getValue()!=ParamType.OBJECT_VALUE && parameter.getParamType().getValue()!=ParamType.PARAM_GROUP_VALUE){
			type = AtomFunctionCompilerUtil.getRealDataType(parameter, calledRes.getARESProject(),context);
		}
		
		/*if(parameter.getParamType().getValue()==ParamType.STD_FIELD_VALUE && StringUtils.isBlank(type)){
			throw new Exception("获取参数"+parameter.getId()+"类型出错,请该参数是否是标准字段以及对应的类型是否存在");
		}*/
		Map<String,String> parameterInfo = AtomFunctionCompilerUtil.getStandardFieldParameterInfo(parameter.getId(), calledRes.getARESProject());
		 if (TypeRule.typeRuleChar(type)) {//字符
			ret.append(String.format(IP_PAKER_ADDFIELD_CHAR, parameter.getId()));
		}else if (TypeRule.typeRuleDouble(type) ||TypeRule.typeRuleFloat(type)) {//Double或者Float
			String length = NumberUtils.toInt(StringUtils.defaultIfBlank(parameterInfo.get("length"), ""),0)+"";
			String precision = StringUtils.defaultIfBlank(parameterInfo.get("precision"), "");
			ret.append(String.format(IP_PAKER_ADDFIELD_FLOAT, parameter.getId(),length,
					precision));
		}else if (TypeRule.typeRuleInt(type)) {
			ret.append(String.format(IP_PAKER_ADDFIELD_INT, parameter.getId()));
		}else if (parameter.getParamType().getValue()==ParamType.OBJECT_VALUE||TypeRule.typeRuleClob(type)) {//对象
			String paramNameOrValue = StringUtils.defaultIfBlank(defaultValue.get(parameter.getId()), "");//参数名或者默认值
			String objectName = StringUtils.replaceOnce(paramNameOrValue, MarkConfig.MARK_AT, "").trim();
			if(StringUtils.endsWith(objectName, "ResultSet")){//对象支持别名
				objectName = StringUtils.substring(objectName, 0,StringUtils.lastIndexOf(objectName, "ResultSet"));
			}else{
				if(StringUtils.isBlank(paramNameOrValue)){
					objectName = parameter.getId();
				}
				
			}
			ret.append(String.format(IP_PAKER_ADDFIELD_COLB, parameter.getId(), "pi_" + objectName));
		}else if (TypeRule.typeRuleCharArray(type) && TypeRule.greaterThan255(type)) {//字符串类型类型，且长度大于255
			String length = StringUtils.defaultIfBlank(parameterInfo.get("length"), "");
			ret.append(String.format(IP_PAKER_ADDFIELD_CHARARRAY, parameter.getId(), length));
		}else if(TypeRule.typeRuleCharArray(type) ) {//字符吕类型类型长度小于等于255
			ret.append(String.format(IP_PAKER_ADDFIELD_CHARARRAY2, parameter.getId()));
		} else {//默认为字符串类型长度小于等于255
			ret.append(String.format(IP_PAKER_ADDFIELD_DEFAULT, parameter.getId()));
		}
		ret.append(NEWLINE);
		
	}
	
	/**
	 * 输入参数打包添加值
	 * @param ipParas 输入参数
	 * @param opParas 输出参数
	 * @param pararms 伪代码中的参数，带"@"
	 * @param defaultValue 宏参数-默认值
	 * @return
	 * @throws Exception
	 */
	private String genInputParamentPackAddValue(List<Parameter> ipParas,List<Parameter> opParas,List<String> pararms ,Map<String, String> defaultValue,Map<Object,Object> context)  throws Exception{
		
		StringBuffer ret = new StringBuffer();
		boolean isErrorPathInfo = false;
		//1.函数输入参数
		for (Parameter parameter : ipParas) {
			setInputParaPackAddValue(ret, defaultValue, parameter ,pararms,context);
			if(parameter.getId().equals("error_pathinfo")){
				isErrorPathInfo = true;
			}
		}
		
		//2.函数中带“IO”的输出参数
		for (Parameter parameter : opParas) {
			if((parameter.getFlags() != null) && parameter.getFlags().toUpperCase().equals(MarkConfig.MARK_IOFLAG)) {
				setInputParaPackAddValue(ret, defaultValue, parameter ,pararms,context);
				if(parameter.getId().equals("error_pathinfo")){
					isErrorPathInfo = true;
				}
			}
		}
		
		//3.error_pathinfo 特殊处理，如果输入参数没有，则需要默认添加
		if(!isErrorPathInfo) { 
//			StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(calledRes.getARESProject(), "error_pathinfo");
//			String realType = stdType.getValue(MetadataServiceProvider.C_TYPE);
			if(((token.getFlag() != null) && token.getFlag().contains(MarkConfig.MARK_IFLAG_M)) || ((callRes.getInterfaceFlag() != null) && callRes.getInterfaceFlag().contains(MarkConfig.MARK_IFLAG_M))){
				ret.append(String.format(IP_PAKER_ADDVALUE_ERROR_PATHINFO,"v_error_pathinfo_tmp" ,"v_error_pathinfo_tmp"));
			}else {
				ret.append(String.format(IP_PAKER_ADDVALUE_ERROR_PATHINFO,"@error_pathinfo" ,"error_pathinfo"));
			}
			ret.append(NEWLINE);
		}
		
		return ret.toString();
	}
	
	private void setInputParaPackAddValue(StringBuffer ret, Map<String, String> defaultValue, Parameter parameter ,List<String> pararms,Map<Object,Object> context) throws Exception{
		String paraName = parameter.getId();//参数名
		String type = "";
		if(parameter.getParamType().getValue()!=ParamType.OBJECT_VALUE && parameter.getParamType().getValue()!=ParamType.PARAM_GROUP_VALUE){
			type = AtomFunctionCompilerUtil.getRealDataType(parameter, calledRes.getARESProject(),context);
		}
		
		String paramNameOrValue = defaultValue.get(paraName);//参数名或者默认值
		if(paramNameOrValue != null) {//默认值存在
			if(paramNameOrValue.indexOf(MarkConfig.MARK_AT) < 0) {//默认值不带“@”，需获取真实值
				paramNameOrValue = AtomFunctionCompilerUtil.getTrueDefaultValueByType(type, paramNameOrValue, calledRes.getARESProject());
			}
		}else {
			
			if (SPECIAL_PARAM_SUBSTITUTE.containsKey(paraName)) {
				// 该参数改被替代为另一个参数
				paraName = SPECIAL_PARAM_SUBSTITUTE.get(paraName);
			}
			
			if(AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(callRes, paraName,calledRes.getARESProject()) || pseudoObjectParaList.contains(paraName)|| isPsoudoCode(pararms, paraName)) {
				//参数在调用函数的变量中
				paramNameOrValue = MarkConfig.MARK_AT + paraName.trim();
			}else {
				// 恒生开发工具特殊处理
				StringBuffer spDftValue = new StringBuffer();
				if(!beforeAddValueUseDefaultValueV2(paraName,spDftValue)) {
					if((parameter.getParamType() == ParamType.STD_FIELD) || (parameter.getParamType() == ParamType.NON_STD_FIELD))//标准字段参数或非标参数
					{
						String bizTypeName = "";
						if(parameter.getParamType() == ParamType.STD_FIELD)//标准字段参数
						{
							StandardField stdfield = MetadataServiceProvider.getStandardFieldByNameNoExp(calledRes.getARESProject(), parameter.getId());//getId为参数名，getName为中文名
							if(stdfield == null){
								ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
								String message = String.format("参数[%1$s]对应的标准字段不存在。", parameter.getId());
								manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
								return ;//标准字段不存在时，继续生成，但最后将所有错误信息都抛出。
							}
							bizTypeName = stdfield.getDataType();//标准字段时，取标准字段对应业务类型
						}else if(parameter.getParamType() == ParamType.NON_STD_FIELD){//非标参数
							bizTypeName = parameter.getType();//非标字段时，取参数中直接输入的业务类型
						}
						int length = 0;
						BusinessDataType bizType = MetadataServiceProvider.getBusinessDataTypeByNameNoExp(calledRes.getARESProject(), bizTypeName);//这里不能使用param.getType()，否则会引起非标时，取不到业务类型的异常
						if(bizType == null){
							ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
							String message = String.format("参数[%1$s]对应的业务类型[%2$s]不存在。", parameter.getId(),bizTypeName);
							manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
							return ;//业务类型不存在时，继续生成，但最后将所有错误信息都抛出。
						}
						try {
							length = Integer.parseInt(bizType.getLength()) + 1;//声明Char数组变量时，长度要加1
						} catch (Exception e) {
							ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
							String message = String.format("参数[%1$s]对应的业务类型:%2$s的长度为非法数字：%3$s。", parameter.getId(),bizTypeName,bizType.getLength());
							manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
							return ;//业务类型长度为非法数字时，继续生成，但最后将所有错误信息都抛出。
						}
						StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeByNameNoExp(calledRes.getARESProject(), bizType.getStdType());
						if(stdType == null){
							ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
							String message = String.format("参数[%1$s]对应的业务类型[%2$s]中的标准类型[%3$s]不存在。", parameter.getId(),bizTypeName,bizType.getStdType());
							manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
							return ;//标准类型不存在时，继续生成，但最后将所有错误信息都抛出。
						}
						String dataType = StringUtils.defaultIfBlank(stdType.getValue(MetadataServiceProvider.C_TYPE), "");
						dataType = dataType.replace("$L", length + "");
						if(TypeRule.typeRuleCharArray(dataType)){//字符串变量定义
							spDftValue.append("\" \"");
						}else{
							//如果参数中对应的默认值不为空，则默认值以该值为准，注意这里允许使用标准默认值，同时也可以是真实默认值。
							if(StringUtils.isNotEmpty(parameter.getDefaultValue())){
								TypeDefaultValue typpeDefValue = MetadataServiceProvider.getTypeDefaultValueByNameNoExp(calledRes.getARESProject(), parameter.getDefaultValue());
								//如果找不到标准默认值，则统一按真实默认值处理，用户输入什么，就输出什么
								if(typpeDefValue == null){
									spDftValue.append(parameter.getDefaultValue());
								}else{
									String defValue = typpeDefValue.getValue(MetadataServiceProvider.C_TYPE);
									spDftValue.append(defValue);
								}
							}
							//参数中默认值为空，取业务类型对应的标准默认值，这里必须是标准默认值，不存在要报错
							else{
								TypeDefaultValue typpeDefValue = MetadataServiceProvider.getTypeDefaultValueByNameNoExp(calledRes.getARESProject(), bizType.getDefaultValue());
								if(typpeDefValue == null){
									ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
									String message = String.format("参数[%1$s]对应的业务类型[%2$s]中的标准默认值[%3$s]不存在。", parameter.getId(), bizTypeName,bizType.getDefaultValue());
									manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
									return ;//标准默认值不存在时，继续生成，但最后将所有错误信息都抛出。
								}
								String defValue = typpeDefValue.getValue(MetadataServiceProvider.C_TYPE);
								spDftValue.append(defValue);
							}
						}
					}
					

				}
				paramNameOrValue = spDftValue.toString();
			}
		}
		
		
		if(StringUtils.equals("error_pathinfo", paraName) && (
				token.getFlag().contains(MarkConfig.MARK_IFLAG_M) || callRes.getInterfaceFlag().contains(MarkConfig.MARK_IFLAG_M))) {
			ret.append("lpFuncInPacker->AddStr(v_error_pathinfo1);"); 
		}else {
			
			if(parameter.getParamType().getValue()==ParamType.OBJECT_VALUE || TypeRule.typeRuleClob(type)){
				String firstFlag = "";
				String firstIntFlag = "";
				List<Parameter> inputParameters = new ArrayList<Parameter>();
				ParamGroupUtil.parserParameters(callRes.getInputParameters(), inputParameters, calledRes.getARESProject());
				List<Parameter> outParameters = new ArrayList<Parameter>();
				ParamGroupUtil.parserParameters(callRes.getOutputParameters(), outParameters, calledRes.getARESProject());
				if(parameter.getParamType().getValue()==ParamType.OBJECT_VALUE|| inputParameters.contains(parameter)|| outParameters.contains(parameter)) {
					firstFlag += "p_";
					firstIntFlag += "pi_";
				}else {
					firstFlag += "v_";
					firstIntFlag += "vi_";
				}
				if(parameter.getParamType().getValue()==ParamType.OBJECT_VALUE){
					String objectName = StringUtils.replaceOnce(paramNameOrValue, MarkConfig.MARK_AT, "").trim();
					if(StringUtils.endsWith(objectName, "ResultSet")){//对象支持别名
						objectName = StringUtils.substring(objectName, 0,StringUtils.lastIndexOf(objectName, "ResultSet"));
						paraName = objectName;
					}else{
						paraName = objectName;
					}
					ret.append(String.format(IP_PAKER_ADDVALUE_COLB, firstFlag + paraName, firstIntFlag + paraName, paraName));
				}else{
					ret.append(String.format(IP_PAKER_ADDVALUE_COLB, firstFlag + parameter.getId(), firstIntFlag + paramNameOrValue, paraName));
				}
				
			}else {
				if (TypeRule.typeRuleInt(type)) {
					ret.append(String.format(IP_PAKER_ADDVALUE_DEFAULT, "Int",paramNameOrValue,paraName));
				} else if (TypeRule.typeRuleDouble(type)) {
					ret.append(String.format(IP_PAKER_ADDVALUE_DEFAULT, "Double",paramNameOrValue,paraName));
				} else if (TypeRule.typeRuleChar(type)) {
					ret.append(String.format(IP_PAKER_ADDVALUE_DEFAULT, "Char",paramNameOrValue,paraName));
				} else {
					ret.append(String.format(IP_PAKER_ADDVALUE_DEFAULT, "Str",paramNameOrValue,paraName));
				}
			}
		}
		ret.append(NEWLINE);
	}
	
	private boolean isPsoudoCode(List<String> pararms , String paramName){
		for(String param : pararms){
			if (StringUtils.equals(param, paramName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 恒生开发工具需要被特殊处理的字段<BR>
	 * 也就是当前者没有被指定时，将作为后者的身份进行解释
	 * 
	 */
	private final static HashMap<String, String> SPECIAL_PARAM_SUBSTITUTE = new HashMap<String, String>();
	static {
		SPECIAL_PARAM_SUBSTITUTE.put("op_station_on", "station_no");
	}
	
	/**
	 * 恒生开发工具特殊处理
	 * 对于特殊的字段打包加值，使用特殊值
	 * @param fieldName
	 * @param valueBuffer
	 * @return
	 */
	private boolean beforeAddValueUseDefaultValueV2(String fieldName, StringBuffer valueBuffer) {
		/*
		 * '20050126 zhouwm 函数_设置服务对应参数要求根据 开始 'BodyCodeCol.Add (Space(iSpace) &
		 * "lpFuncInPacker->AddValue(""" & ParamInitValue(sProcParamType) & """);") If
		 * (InStr(sProcParamName, "inout_flag") > 0) Then BodyCodeCol.Add
		 * (Space(iSpace) & "lpFuncInPacker->AddValue(0);") ElseIf
		 * (InStr(sProcParamName, "param_name") > 0) Then BodyCodeCol.Add
		 * (Space(iSpace) &
		 * "lpFuncInPacker->AddValue(lpInUnPackerParent->getColName(v_i));") ElseIf
		 * (InStr(sProcParamName, "param_type") > 0) Then BodyCodeCol.Add
		 * (Space(iSpace) &
		 * "lpFuncInPacker->AddValue(lpInUnPackerParent->getColType(v_i));") ElseIf
		 * (InStr(sProcParamName, "param_width") > 0) Then BodyCodeCol.Add
		 * (Space(iSpace) &
		 * "lpFuncInPacker->AddValue(lpInUnPackerParent->getColWidth(v_i));") ElseIf
		 * (InStr(sProcParamName, "param_scale") > 0) Then BodyCodeCol.Add
		 * (Space(iSpace) &
		 * "lpFuncInPacker->AddValue(lpInUnPackerParent->getColScale(v_i));") ElseIf
		 * (InStr(sProcParamName, "param_value") > 0) Then BodyCodeCol.Add
		 * (Space(iSpace) &
		 * "lpFuncInPacker->AddValue(lpInUnPackerParent->getString(lpInUnPackerParent->getColName(v_i)));")
		 * Else BodyCodeCol.Add (Space(iSpace) & "lpFuncInPacker->AddValue(""" &
		 * ParamInitValue(sProcParamType) & """);") End If '20050126 zhouwm
		 * 函数_设置服务对应参数要求根据 结束
		 */

		// 10.20MXH修改 增加对版本的判断
		if (fieldName.indexOf("inout_flag") != -1) {
			valueBuffer.append("0");
			return true;
		} else if (fieldName.indexOf("param_name") != -1) {
			valueBuffer.append("lpInUnPackerParent->GetColName(v_i)");
			return true;
		} else if (fieldName.indexOf("param_type") != -1) {
			valueBuffer.append("lpInUnPackerParent->GetColType(v_i)");
			return true;
		} else if (fieldName.indexOf("param_width") != -1) {
			valueBuffer.append("lpInUnPackerParent->GetColWidth(v_i)");
			return true;
		} else if (fieldName.indexOf("param_scale") != -1) {
			valueBuffer.append("lpInUnPackerParent->GetColScale(v_i)");
			return true;
		} else if (fieldName.indexOf("param_value") != -1) {
			valueBuffer.append("lpInUnPackerParent->GetStr(lpInUnPackerParent->GetColName(v_i))");
			return true;
		}

		return false;
	}
	
}
