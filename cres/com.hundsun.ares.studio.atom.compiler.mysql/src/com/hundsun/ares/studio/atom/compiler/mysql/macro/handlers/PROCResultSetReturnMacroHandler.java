/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.token.PROCResultSetReturnToken;

import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

/**
 * @author zhuyf
 *
 */
public class PROCResultSetReturnMacroHandler implements IMacroTokenHandler {


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.PROC_RESULTSET_RETURN_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 输入描述：
			[PRO*C结果集返回]
			处理流程：
			1.生成以下语句：
			@result_num =  0;
			[游标参数打包加字段]
			while ( SQLCODE == OK_SUCCESS )
			'{'
			EXEC SQL FETCH cursor[对象号]+[编号] INTO: [列举结果集字段并在字段名后加游标后缀];
			if ( (SQLCODE == -28) || (SQLCODE == -1012) )
			lpConn->setErrMessage(HSDB_CONNECT_DISCONN,SQLCODE,sqlca.sqlerrm.sqlerrmc);    
			if ( SQLCODE == OK_SUCCESS)
			'{'
			@result_num = @result_num + 1;
			[游标参数打包加字段值]
			'}'
			else
			break;
			'}'
			EXEC SQL CLOSE cursor[对象号]+[编号];
			2.其中[游标参数打包加字段]为从预存的PROC结果集字段列表中遍历字段，且每一字段生成：
			lpOutPacker->AddField("[字段名]");
			注意这里根据类型的差别，生成语句也会有所变化
			3.[列举结果集字段并在字段名后加游标后缀]为从预存的PROC结果集字段列表中遍历字段，且每一字段生成：
			[字段名]_cur
			将其作为Proc变量，存入proc变量声明列表中
			4.[游标参数打包加字段值]为从预存的PROC结果集字段列表中遍历字段，且每一字段生成：
			lpOutPacker->Add[字段类型]([字段名]_cur); //[字段注释]
		 */
		
		 addMacroNameToMacroList(token,context);//把宏名回到到数据库宏以及proc宏列表中
		 addVarList(context);//把变量加入到变量列表中去
		 List<ICodeToken> codeList = new ArrayList<ICodeToken>();
		 String rsId = getRsId(context);
		 List<String> sqlFields =this.getSqlFields(context);//取得字段列表
		 codeList.add(new PROCResultSetReturnToken(rsId,sqlFields) );//添加CodeToken
		 removeDomain(context);//删除域
		return codeList.iterator();
	}
	
	
	
	/**
	 * 把宏名加入到宏列表中
	 */
	private void addMacroNameToMacroList(IMacroToken token,
			Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_DATABASE_MACRO,token.getKeyword());//添加到数据库列表中
	    helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_MACRO,token.getKeyword());//添加到proc宏列表中
	}
	
	/**
	 * 把字段估添加到变量列表中去
	 * 
	 * @param procVarList
	 */
	private void addVarList(Map<Object, Object> context) {
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
			//加入到proc变量列表中，所有proc变量必须在proc定义区声明
		List<String> varList = new ArrayList<String>(5);
		    varList.add("result_num");
			varList.add("error_no");
			varList.add("error_info");
			varList.add("error_sysinfo");
			varList.add("error_id");
		for(String varName:varList){
			popVarList.add(varName);
		}
			
	}
	
	/**
	 * @return 取行字段名称
	 */
	private List<String> getSqlFields(Map<Object, Object> context){
		ITokenDomain domain = getDomain(context);
		return (List<String>)domain.getArgs()[1];
	}
	
	private String getRsId(Map<Object, Object> context){
		ITokenDomain domain = getDomain(context);
		return (String)domain.getArgs()[0];
	}
	

	/**
	 * 获得域
	 */
	private ITokenDomain getDomain(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		return handler.getDomain(MacroConstant.PROC_RESULTSET_STATEMENT_MACRONAME);
	}
	
	/**
	 * 删除域
	 */
	private void removeDomain(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		handler.removeDomain(MacroConstant.PROC_RESULTSET_STATEMENT_MACRONAME);
	}
	

}
