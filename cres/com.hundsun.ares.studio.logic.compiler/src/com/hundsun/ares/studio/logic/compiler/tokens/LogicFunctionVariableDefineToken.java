/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.tokens;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.token.IParamDefineHelper;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.compiler.util.LogicCompilerUtil;

/**
 * @author qinyuan
 *
 */
public class LogicFunctionVariableDefineToken extends LogicServiceVariableDefineToken {
	/**
	 * 
	 */
	protected String getCommonParamsDefineCodeStr(List<String> popVarList,IParamDefineHelper defineHelper) {
		StringBuffer code = new StringBuffer();
		String error_info_length = this.getStandardFieldParameterInfo("error_info").get("length");
		String error_pathinfo_length =  this.getStandardFieldParameterInfo("error_pathinfo").get("length");
		code.append("int iReturnCode = 0;\r\n");
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "error_no",project)&& !popVarList.contains("error_no")&& defineHelper.canInit(IParamDefineHelper.STD, "error_no")){
			code.append("int @error_no = 0;\r\n");
			popVarList.add("error_no");
			defineHelper.addInit(IParamDefineHelper.STD, "error_no");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "error_info",project)&& !popVarList.contains("error_info") && defineHelper.canInit(IParamDefineHelper.STD, "error_info")){
			code.append("char @error_info["+(Integer.parseInt(error_info_length)+1)+"] = {0};\r\n");//初始化要加1
			defineHelper.addInit(IParamDefineHelper.STD, "error_info");
			popVarList.add("error_info");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "error_pathinfo",project)&& !popVarList.contains("error_pathinfo")&& defineHelper.canInit(IParamDefineHelper.STD, "error_pathinfo")){
			code.append("char @error_pathinfo["+(Integer.parseInt(error_pathinfo_length)+1)+"] = {0};\r\n");//初始化要加1
			code.append("hs_strncpy(@error_pathinfo,conversion((char *)lpInUnPacker->GetStr(\"error_pathinfo\")),"+error_pathinfo_length+");\r\n");
			if(ls instanceof LogicFunction){
				code.append("hs_strcat(v_error_pathinfo, "+"\"->"+
						(StringUtils.isBlank(ls.getObjectId())?ls.getName():"F"+ls.getObjectId())
						+"()\");\r\n");
			}else{
				code.append("hs_strcat(v_error_pathinfo, "+"\"F"+
						(StringUtils.isBlank(ls.getObjectId())?ls.getName():ls.getObjectId())
						+"()\");\r\n");
			}
			defineHelper.addInit(IParamDefineHelper.STD, "error_pathinfo");
			popVarList.add("error_pathinfo");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "branch_no",project)&& !popVarList.contains("branch_no") && defineHelper.canInit(IParamDefineHelper.STD, "branch_no")){
			code.append("int @branch_no = lpInUnPacker->GetInt(\"branch_no\");\r\n");
			popVarList.add("branch_no");
			defineHelper.addInit(IParamDefineHelper.STD, "branch_no");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "sysnode_id",project)&& !popVarList.contains("sysnode_id")&& defineHelper.canInit(IParamDefineHelper.STD, "sysnode_id")){
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
			popVarList.add("error_id");
			defineHelper.addInit(IParamDefineHelper.STD, "error_id");
		}
		if(!LogicCompilerUtil.isParameterINLogicParameterByName(ls, "audit_action",project)&& !popVarList.contains("audit_action")&& defineHelper.canInit(IParamDefineHelper.STD, "audit_action")){
			code.append("char p_audit_action = lpInUnPacker->GetChar(\"audit_action\");\r\n");
			popVarList.add("audit_action");
			defineHelper.addInit(IParamDefineHelper.STD, "audit_action");
		}
		code.append("IF2PackSvr * lpPackService = lpContext->GetF2PackSvr();\r\n");
		
		if(ls instanceof LogicFunction && ((LogicFunction)ls).isIsTransFunc()){
			code.append("IAS2TM * lpTransMonitor = NULL; // 事务监视器\r\n");
			code.append("lpContext->QueryInterface(SID_TM,(IKnown **) &lpTransMonitor);\r\n");
			code.append("char ls_cancel_serialno[65] = {0};\r\n");
		}
		return code.toString();
	}
}
