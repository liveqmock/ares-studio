/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.oracle.macro.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.MarkConfig;
import com.hundsun.ares.studio.engin.exception.HSException;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.oracle.constant.IProcedureEngineContextConstantOracle;
import com.hundsun.ares.studio.procedure.compiler.oracle.macro.IProcedureMacroTokenService;
import com.hundsun.ares.studio.procedure.compiler.oracle.skeleton.util.ProcedureCompilerUtil;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.SubProcedureCallToken;

/**
 * @author qinyuan
 *
 */
public class ProcedureCallMacroHandler implements IMacroTokenHandler {
	 private IARESResource resource;//调用的过程模型
	 private String key;

	/**
	 * @param chineseName
	 * @param resource
	 */
	public ProcedureCallMacroHandler(String chineseName, IARESResource resource) {
		this.key = chineseName;
		this.resource = resource;
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return this.key;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		
		 Procedure procedure=null;//调用的过程模型
		 Procedure subProcedure=null;//被调用的过程模型
		 procedure = resource.getInfo(Procedure.class);
		 subProcedure = getSubProcedure(token.getKeyword(),context);
		 Map<String, String> defaultValueMap = null;
		 if(token.getParameters().length > 0){//过程调用参数个数判断
			 defaultValueMap = PseudoCodeParser.parserKeyValueWithAt(token.getParameters()[0]);
		 }else {
			 defaultValueMap = new HashMap<String, String>();
		 }
		 List<ICodeToken> tokens = new ArrayList<ICodeToken>();
		 if(subProcedure==null){
			 throw new HSException("--过程不存在"+token.getKeyword());
		 }
		 boolean isInTransaction =isInTransaction(context);//判断是否在事务中
		 addVarList(context,procedure,subProcedure,defaultValueMap);//添加变量
		 tokens.add(new SubProcedureCallToken(token,procedure,subProcedure,defaultValueMap,isInTransaction));
		return tokens.iterator();
	}
	/**
	 * 获取被调用子过程模型
	 * @param name
	 * @param context
	 * @return
	 */
	private Procedure getSubProcedure(String name,Map<Object, Object> context){
		
		IProcedureMacroTokenService service =(IProcedureMacroTokenService) context.get(IProcedureEngineContextConstantOracle.Procedure_Macro_Service);
		try {
			return  (service.getProcedure(name)).getInfo(Procedure.class);
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 是否存在事务开始宏
	 * @param context
	 * @return
	 */
	private boolean isInTransaction(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain procTransactionBegin =handler.getDomain(""/**MacroConstant.TRANSACTION_BEGIN*/);
		return procTransactionBegin!=null;
	}
	/**
	 * 把变量添加到列表中
	 * @param context
	 * @param subProcedure
	 * @param procedure
	 * @param defaultValueMap
	 */
	private void addVarList(Map<Object, Object> context,Procedure procedure,Procedure subProcedure,Map<String, String> defaultValueMap) {
		
		for(Parameter parameter :subProcedure.getInputParameters()){

			String defaultValue = defaultValueMap.get(parameter.getId());
			if(defaultValue!=null){
				String valueVarName =defaultValueMap.get(parameter.getId());
				if (valueVarName.indexOf(MarkConfig.MARK_AT) >= 0 && !ProcedureCompilerUtil.isParameterINProcedureParameterByName(procedure, valueVarName.substring(valueVarName.indexOf("@") + 1),resource.getARESProject())) {// 如果默认参数值为变量
					String varName = valueVarName.substring(valueVarName.indexOf(MarkConfig.MARK_AT) + 1);
					addToPVarList(context,varName);
				}
				
			}else if(!ProcedureCompilerUtil.isParameterINProcedureParameterByName(procedure,parameter.getId(),resource.getARESProject())){
				addToPVarList(context,parameter.getId());
			}

		}
			
		for(Parameter parameter :subProcedure.getOutputParameters()){

			String defaultValue = defaultValueMap.get(parameter.getId());
			if(defaultValue!=null){
				String valueVarName =defaultValueMap.get(parameter.getId());
				if (valueVarName.indexOf(MarkConfig.MARK_AT) >= 0 && !ProcedureCompilerUtil.isParameterINProcedureParameterByName(procedure, valueVarName.substring(valueVarName.indexOf("@") + 1),resource.getARESProject())) {// 如果默认参数值为变量
					String varName = valueVarName.substring(valueVarName.indexOf(MarkConfig.MARK_AT) + 1);
					addToPVarList(context,varName);
				}
				
			}else if(!ProcedureCompilerUtil.isParameterINProcedureParameterByName(procedure,parameter.getId(),resource.getARESProject())){
				addToPVarList(context,parameter.getId());
				
			}
			
			
		}	
			
	}
	/**
	 * 
	 * @param context
	 * @param name
	 */
	private void addToPVarList(Map<Object, Object> context ,String name){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IProcedureEngineContextConstantOracle.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
			helper.addAttribute(IProcedureEngineContextConstantOracle.ATTR_PROC_VARIABLE_LIST, name);
			popVarList.add(name);
		
	}


}
