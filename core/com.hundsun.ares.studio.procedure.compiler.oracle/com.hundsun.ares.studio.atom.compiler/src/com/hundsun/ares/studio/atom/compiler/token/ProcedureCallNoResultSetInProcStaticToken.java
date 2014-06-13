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
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.usermacro.compiler.contants.IUserMacroEnginConstant;

public class ProcedureCallNoResultSetInProcStaticToken implements ICodeToken {

	Procedure procedure;
	IMacroToken token;
	
	public ProcedureCallNoResultSetInProcStaticToken(Procedure procedure,IMacroToken token) {
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
		
		StringBuffer elseBuffer = new StringBuffer();
		boolean noText =true;
		if (isInTranInProcBlock(context)) {
			noText = false;
			elseBuffer.append("rollback;\n");
		}
		if (token.getFlag().indexOf("M") < 0) {
			noText = false;
			elseBuffer.append("goto svr_end;").append("\r\n");
		}
		if(noText){
			elseBuffer.append("NULL;");
		}
		
		buffer.append(String.format(CALL_BODY, name,paramBuffer.toString(),elseBuffer.toString()));
		
		return buffer.toString();
	}
	
	//是否在事物处理块中
	private boolean isInTranInProcBlock(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler)  context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain tokenDomain =handler.getDomain(IUserMacroEnginConstant.TRAN_BLOCK_BEGIN_MACRONAME);
		return tokenDomain!=null;
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

	private final String CALL_BODY = ":iReturnCode := %1$s (" + ITokenConstant.NL
									+"%2$s" + ITokenConstant.NL
									+");" + ITokenConstant.NL
									+"if :iReturnCode != 0 then" + ITokenConstant.NL
									+"%3$s" + ITokenConstant.NL
									+"else" + ITokenConstant.NL
									+":@error_pathinfo := :v_error_pathinfo_tmp;" + ITokenConstant.NL
									+"end if;" + ITokenConstant.NL;
}
