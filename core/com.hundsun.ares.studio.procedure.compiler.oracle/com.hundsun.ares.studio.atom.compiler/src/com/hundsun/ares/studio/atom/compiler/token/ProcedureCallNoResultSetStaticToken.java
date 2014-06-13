package com.hundsun.ares.studio.atom.compiler.token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.procdure.Procedure;

public class ProcedureCallNoResultSetStaticToken implements ICodeToken {

	Procedure procedure;
	IMacroToken token;

	public ProcedureCallNoResultSetStaticToken(Procedure procedure,IMacroToken token) {
		this.procedure = procedure;
		this.token = token;
	}

	@Override
	public String getContent() {
		return null;
	}

	@Override
	public int getType() {
		return 0;
	}

	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(COMMENT_BEGIN);
		
		String name = procedure.getName();
		String[] params = token.getParameters();
		Map<String, String> values = new HashMap<String, String>();
		if(params.length>0){
			values.putAll(PseudoCodeParser.parserKeyValueWithAt(params[0]));
		}
		StringBuffer paramBuffer = new StringBuffer();
		int index = 0;
		List<Parameter> paramList = new ArrayList<Parameter>();
		paramList.addAll(procedure.getInputParameters());
		paramList.addAll(procedure.getOutputParameters());
		for(Parameter param : paramList){
			List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
			//添加存在于过程中而不存在于函数中的参数
			if(!popVarList.contains(param.getId())){
				popVarList.add(param.getId());
			}
			if(index > 0){
				paramBuffer.append(",");
			}
			if(values.containsKey(param.getId())){
				//存在默认值
				if(StringUtils.defaultIfBlank(values.get(param.getId()), "").indexOf("@")!=-1){
					paramBuffer.append(":"+getRealFieldName(values.get(param.getId()),getRealType(param, project),project));
					paramBuffer.append(ITokenConstant.NL);
				}else{
					paramBuffer.append(values.get(param.getId()));
					paramBuffer.append(ITokenConstant.NL);
				}
				
			}else{
				paramBuffer.append(":@" + param.getId());
				paramBuffer.append(ITokenConstant.NL);
			}
			index ++;
		}
		String end = StringUtils.defaultIfBlank(token.getFlag(), "").indexOf("M")<0 ? "goto svr_end;\r\n" : "";
		String cName = procedure.getChineseName();
		
		buffer.append(String.format(CALL_BODY, name,paramBuffer.toString(),end,cName));
		
		buffer.append(COMMENT_END);
		
		return buffer.toString();
	}
	
	//获取正确的参数，如@occur_balance*-1，获取到的应该是@occur_balance，非@开头的则取真实默认值
	private String getRealFieldName(String fieldName, String type, IARESProject project) throws Exception{
		Pattern p = Pattern.compile("@[\\w\\d_]+");
		Matcher m = p.matcher(fieldName);
		if(m.find()){
			return m.group();
		}
		return AtomFunctionCompilerUtil.getTrueDefaultValueByType(type, fieldName, project);
	}
	
	//根据type获取参数的真实类型
	private String getRealType(Parameter param,IARESProject project){
		return AtomFunctionCompilerUtil.getRealDataType(param.getId(), project, MetadataServiceProvider.C_TYPE);
	}
	
	private static final String COMMENT_BEGIN = "//子过程调用开始" + ITokenConstant.NL;
	private static final String CALL_BODY = "hs_strcpy(@error_pathinfo,v_error_pathinfo_tmp);" + ITokenConstant.NL
											+ "EXEC SQL EXECUTE" + ITokenConstant.NL
											+ "BEGIN" + ITokenConstant.NL
											+ "begin" + ITokenConstant.NL
											+ ":iReturnCode := %1$s( %2$s );" + ITokenConstant.NL
											+ "end;" + ITokenConstant.NL
											+ "if (:iReturnCode = 0) then" + ITokenConstant.NL
											+ ":@error_pathinfo := :@error_pathinfo_tmp;" + ITokenConstant.NL
											+ "end if;" + ITokenConstant.NL
											+ "END;" + ITokenConstant.NL
											+ "END-EXEC;" + ITokenConstant.NL
											+ "if (CheckDbLinkMethod(lpConn,SQLCODE) < 0) " + ITokenConstant.NL
											+ "{" + ITokenConstant.NL
											+ " if ((SQLCODE<= ERR_DB_NO_CONTINUE_FETCH) && (SQLCODE>= ERR_DB_FAILOVER_NETWORK_OPER_FAIL))" + ITokenConstant.NL
											+ "{" + ITokenConstant.NL
											+ "iReturnCode = SQLCODE; " + ITokenConstant.NL
											+ "@error_no = SQLCODE; " + ITokenConstant.NL
											+ "hs_strncpy(@error_info,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml); " + ITokenConstant.NL
											+ "@error_id = SQLCODE;" + ITokenConstant.NL
											+ "hs_strncpy(@error_sysinfo,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);" + ITokenConstant.NL
											+ "EXEC SQL rollback; " + ITokenConstant.NL
											+ ITokenConstant.NL
											+ "goto svr_end; " + ITokenConstant.NL
											+ "}" + ITokenConstant.NL
											+ "lpConn->setErrMessage(HSDB_CONNECTION_STATUS_DISCONN,SQLCODE,sqlca.sqlerrm.sqlerrmc); " + ITokenConstant.NL
											+ "}" + ITokenConstant.NL
											+ "if(iReturnCode != OK_SUCCESS)" + ITokenConstant.NL
											+ "{" + ITokenConstant.NL
											+ "%3$s" + ITokenConstant.NL
											+ "}" + ITokenConstant.NL
											+ "if (SQLCODE != OK_SUCCESS)" + ITokenConstant.NL
											+ "{" + ITokenConstant.NL
											+ "@error_no = 101;" +ITokenConstant.NL
											+ "iReturnCode = 101;" + ITokenConstant.NL
											+ "hs_strcpy(@error_info,\"执行存储过程错误:%4$s\");" + ITokenConstant.NL
											+ "@error_id = SQLCODE;" + ITokenConstant.NL
											+ "hs_strcpy(@error_sysinfo,sqlca.sqlerrm.sqlerrmc); " + ITokenConstant.NL
											+ "goto svr_end;" + ITokenConstant.NL
											+ "}" + ITokenConstant.NL;
	private static final String COMMENT_END = "//子过程调用结束" + ITokenConstant.NL; 

}
