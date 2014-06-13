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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.TokenDomain;
import com.hundsun.ares.studio.atom.compiler.mysql.token.PROCGetRecordBeginToken;
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
public class PROCGetRecordBeginMacroHandler implements IMacroTokenHandler {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.PROC_GET_RECORD_BEGIN_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 输入描述：
			[PRO*C记录获取开始][要获得字段列表]
			处理流程：
			1.获取字段列表，加入到PRO*C声明中
			2.生成如下语句：
			{
			EXEC SQL FETCH cursor[对象号]+[编号] INTO [字段列表@转换为proc变量];
			 if ( (SQLCODE == -28) || (SQLCODE == -1012) )
			lpConn->setErrMessage(HSDB_CONNECT_DISCONN,SQLCODE,sqlca.sqlerrm.sqlerrmc);
			 if (SQLCODE == OK_SUCCESS)
			 {
			   do
			   {

		 */
		List<ICodeToken> codeToken = new ArrayList<ICodeToken>(1);
		addProcVarLis( token,context);//解析SQL语句中的@变量，加入到PRO*C变量声明列表中
		//ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		String lastId = getRsId(context);
		List<String> sqlFields =this.getSqlFields(context);//取得字段列表
		codeToken.add(new PROCGetRecordBeginToken(token,context,lastId,sqlFields));//加添codeToken
	   addDomain(context);//添加域
	   removeDomain(context);
	  return codeToken.iterator();//返回此宏codeToken列表
	}
	
	/**
	 * 解析SQL语句中的@变量，加入到PRO*C变量声明列表中
	 * @param procVarList
	 */
	private void addProcVarLis(IMacroToken token,Map<Object, Object> context ){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		Pattern p = Pattern.compile("@[\\w\\d_]+");
		//sql语句在第一参数中
		Matcher m = p.matcher(token.getParameters()[0]);
		while (m.find()) {
			popVarList.add(m.group().substring(1));
		}
		
	}

	/**
	 * 添加域
	 */
	private void addDomain(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		Object[] args = new Object[2];
		args[0] = getRsId(context);//添加结果集Id，后续结果集返回或PRO*C记录获取开始与PRO*C记录获取结束宏生成代码时需要使用。
		args[1] = getSqlFields(context);//游标字段名称
		handler.addDomain(new TokenDomain(getKey(),args));
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
