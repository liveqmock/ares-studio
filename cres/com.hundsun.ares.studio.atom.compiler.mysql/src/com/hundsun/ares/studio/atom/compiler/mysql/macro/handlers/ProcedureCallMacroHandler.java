/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.token.ProcedureCallNoResultSetInProcStaticToken;
import com.hundsun.ares.studio.atom.compiler.mysql.token.ProcedureCallNoResultSetStaticToken;
import com.hundsun.ares.studio.atom.compiler.mysql.token.ProcedureCallStaticToken;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.procdure.Procedure;

/**
 * @author zhuyf
 *
 */
public class ProcedureCallMacroHandler implements IMacroTokenHandler {
	
	String key;
	
	IARESResource resource;
	
	public ProcedureCallMacroHandler(String key,IARESResource resource){
		this.key = key;
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 存储过程调用，需满足以下需求：
		 * 1.调用返回结果集的存储过程，lpSP->prepare方式调用
		 * 2.不返回结果集，EXEC SQL EXECUTE方式调用
		 * 3.调用AP时，如果传入参数后面加  *-1，可正常生成代码
			例如：在AF里面调[AP_账户存管公用_存管资金余额更新冲正][occur_balance=@occur_balance*-1,current_balance=@occur_balance*-1]
		 */
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_DATABASE_MACRO, token.getKeyword());
		
		List<ICodeToken> codeList = new ArrayList<ICodeToken>();
		Procedure procedure = resource.getInfo(Procedure.class);
		if(StringUtils.isBlank(procedure.getObjectId())){
			fireEventLessFunctionId(context);
		}
		Set<String> rsList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_RESULTSET_LIST);
		Set<String> stateList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_STATEMENT_LIST);
		AtomFunction func = (AtomFunction)context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		int stateTotalSizeAdd1 = stateList.size() + 1;
		String stateID = func.getObjectId() + stateTotalSizeAdd1;
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_STATEMENT_LIST, stateID);
		int rsTotalSizeAdd1 = rsList.size() + 1;
		String rsID = func.getObjectId() + rsTotalSizeAdd1;
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_RESULTSET_LIST, rsID);
		//2014年4月14日13:45:16 秦元  如果过程调用有N标志，则不进行结果集替换（必须在返回结果集时，否则结果集也不会存在）
		if(!StringUtils.contains(token.getFlag(), "N") && procedure.isOutputCollection()){
			helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_GETLAST_RESULTSET,rsID);
		}
		codeList.add(new ProcedureCallStaticToken(procedure,token,rsID,stateID));
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		//把输入参数加到普通列表中
		for(Parameter parmeter:procedure.getInputParameters()){
			popVarList.add(parmeter.getId());
		}
		
		//把输出参数加到普通列表中
		for(Parameter parmeter:procedure.getOutputParameters()){
			popVarList.add(parmeter.getId());
		}
		
		//error_pathinfo_tmp特殊处理
		popVarList.add("error_pathinfo_tmp");
		
		return codeList.iterator();
	}
	
	/**
	 * 检查是否在proc语句块宏中
	 * @return
	 */
	private boolean isInProcBlock(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler)  context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain procBlockBeginDomain =handler.getDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
		//如果前面有PRO*C语句块开始则procBlockBeginDomain不为null
		return procBlockBeginDomain!=null;
	}
	/**
	 * 把字段估添加到变量列表中去
	 * 
	 * @param procVarList
	 */
	private void addVarList(Map<Object, Object> context) {
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		List<String> varList = new ArrayList<String>(5);
		    varList.add("error_pathinfo_tmp");
		for(String varName:varList){
			popVarList.add(varName);
		}
	}
	/**
	 * 缺少功能号
	 * @param context
	 */
	private void fireEventLessFunctionId(Map<Object, Object> context){

		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = "过程请设置功能号";
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	
	}

}
