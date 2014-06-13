/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.constant.MarkConfig;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.constant.IProcedureEngineContextConstant;
import com.hundsun.ares.studio.procedure.compiler.skeleton.util.ProcedureCompilerUtil;

/**
 * 过程调用子过程Token
 * @author liaogc
 *
 */
public class SubProcedureCallToken implements ICodeToken{
	private static final String NL = ITokenConstant.NL;
	private static String FLAG_M="M";//m标记
	private static String PREFIX_P="p_";//前缀p
	private static String PREFIX_V="v_";//前缀v
	private static String ORACLE_ASSIGN=" => ";
	private IMacroToken macroToken;
	private Procedure procedure;//调用的过程模型
	private Procedure subProcedure;//被调用的过程模型
	private Map<String, String> defaultValueMap ;//默认值列表Map<Object, Object> context
	private boolean isInTransaction;//是否在事务中
	
	
	public SubProcedureCallToken(IMacroToken macroToken,Procedure procedure,Procedure subProcedure,Map<String, String> defaultValueMap,boolean isInTransaction){
		this.macroToken = macroToken;
		this.procedure = procedure;
		this.subProcedure = subProcedure;
		this.defaultValueMap = defaultValueMap;
		this.isInTransaction = isInTransaction;
		
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
	
	/**
	 * 被调用的过程业务类型是否为过程
	 * 定义为过程时，没有返回值
	 * @return 
	 * 			true   过程 
	 * 			false  函数
	 */
	private boolean isSubProcedureProcdure(){
		if(null != subProcedure &&
				StringUtils.equalsIgnoreCase(subProcedure.getBizType(), IProcedureEngineContextConstant.procedure)) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		StringBuffer subCallCode = new StringBuffer();
		IARESProject project = (IARESProject)context.get(IProcedureEngineContextConstant.Aresproject);
		subCallCode.append("--调用过程 :" ).append(subProcedure.getChineseName()).append(NL) ;
		if(isSubProcedureProcdure()){//定义为过程时，没有返回值，不需要变量接收过程返回值，直接调用即可
			subCallCode.append(subProcedure.getName()).append(NL) ;
		}else {
			subCallCode.append("@error_no := ").append(subProcedure.getName()).append(NL) ;
		}
		subCallCode.append(MarkConfig.MARK_LEFTSIGN).append(NL);
		for(Parameter parameter :subProcedure.getInputParameters()){

			String defaultValue = this.defaultValueMap.get(parameter.getId());//在默认值里面找
			
			if (defaultValue != null){//在默认值里面找
				subCallCode.append(PREFIX_P).append(parameter.getId()).append(ORACLE_ASSIGN).append(defaultValue).append(NL);
				
			}else if(ProcedureCompilerUtil.isParameterINInputAndOutputParameterByName(procedure, parameter.getId(),project)){//在输入输出以前内部变量中
				subCallCode.append(PREFIX_P).append(parameter.getId()).append(ORACLE_ASSIGN).append(MarkConfig.MARK_AT).append(parameter.getId()).append(MarkConfig.MARK_COMMA).append(NL);
			} else {
				//在宏中要把这样的子过程输入字段增加到变量中去
				subCallCode.append(PREFIX_P).append(parameter.getId()).append(ORACLE_ASSIGN).append(PREFIX_V).append(parameter.getId()).append(MarkConfig.MARK_COMMA).append(NL);
				
			}
		}
		for(Parameter parameter :subProcedure.getOutputParameters()){

			String defaultValue = this.defaultValueMap.get(parameter.getId());//在默认值里面找
			
			if (defaultValue != null){//在默认值里面找
				subCallCode.append(PREFIX_P).append(parameter.getId()).append(ORACLE_ASSIGN).append(defaultValue).append(NL);
				
			}else if(ProcedureCompilerUtil.isParameterINInputAndOutputParameterByName(procedure, parameter.getId(),project)){//在输入输出以前内部变量中
				subCallCode.append(PREFIX_P).append(parameter.getId()).append(ORACLE_ASSIGN).append(MarkConfig.MARK_AT).append(parameter.getId()).append(MarkConfig.MARK_COMMA).append(NL);
			} else {
				//在宏中要把这样的子过程输入字段增加到变量中去
				subCallCode.append(PREFIX_P).append(parameter.getId()).append(ORACLE_ASSIGN).append(PREFIX_V).append(parameter.getId()).append(MarkConfig.MARK_COMMA).append(NL);
				
			}
			
		}
		if(subCallCode.lastIndexOf(",") != -1){//最后一个逗号处理一下
			subCallCode.deleteCharAt(subCallCode.lastIndexOf(","));
		}
		subCallCode.append(MarkConfig.MARK_RIGHTSIGN).append(MarkConfig.MARK_SEMICOLON).append(NL);
		
		if(!isSubProcedureProcdure()){//定义为过程时，没有错误值返回，也即不会有错误处理信息
			subCallCode.append(getErrorString());//错误处理
			subCallCode.append("else").append(NL);
			subCallCode.append("  @error_pathinfo := v_error_pathinfo_tmp;").append(NL);
			subCallCode.append("end if;").append(NL);
		}
		
		
		return subCallCode.toString();
	}
	/**
	 * 处理出错情况
	 * @return  错误处理字符串
	 */
	private String getErrorString(){
		StringBuffer error = new StringBuffer();
		error.append("if @error_no != 0 then").append(NL);
		boolean noText = true;
		
		
		if(isInTransaction){//如果子过程在事务之中，报错返回需要rollback
			noText = false;
			error.append("rollback;").append(NL);
		 }
		
		if(procedure.isOutputCollection()){//如果子过程是结果集返回，需要游标报错返回
			noText = false;
			error.append("open @cursor for").append(NL);
			error.append("select @error_pathinfo as error_pathinfo,@error_no as error_no, @error_info as error_info,@error_id as error_id, @error_sysinfo as error_sysinfo\n");
			error.append("from dual;").append(NL);
		}
		if(macroToken.getFlag().indexOf(FLAG_M) == -1){
			noText = false;
			error.append("  return(@error_no);").append(NL);
		}
		if(noText){
			error.append("  NULL;").append(NL);
		}
		return error.toString();
	}

}
