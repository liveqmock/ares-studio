/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.macro.TokenDomain;
import com.hundsun.ares.studio.atom.compiler.token.PROCGetRecordBeginToken;
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
		String cursorName = "";
		{
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
			Set<String> cursorNames = helper.getAttribute(IAtomEngineContextConstant.ATTR_CURSOR_LIST);
			IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
			ITokenDomain curTokenDomain = handler.getDomain("curList");
			if(curTokenDomain!=null){
				handler.removeDomain("curList");
			}
			
			handler.addDomain(new TokenDomain("curList", cursorNames.toArray(new String[0])));
		}
		{
			IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
			ITokenDomain curTokenDomain = handler.getDomain("curList");
			if (curTokenDomain != null) {
				cursorName ="cursor"+ curTokenDomain.getArgs()[curTokenDomain.getArgs().length -1].toString();
			}
		}
		//String cursorName = getCursorString(context);
		codeToken.add(new PROCGetRecordBeginToken(token,context,cursorName));//加添codeToken
	   addDomain(context);//添加域
	  return codeToken.iterator();//返回此宏codeToken列表
	}
	
	/**
	 * 解析SQL语句中的@变量，加入到PRO*C变量声明列表中
	 * @param procVarList
	 */
	private void addProcVarLis(IMacroToken token,Map<Object, Object> context ){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		Pattern p = Pattern.compile("@[\\w\\d_]+");
		//sql语句在第一参数中
		Matcher m = p.matcher(token.getParameters()[0]);
		while (m.find()) {
			helper.addAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST, m.group().substring(1));
		}
		List<String> varList = new ArrayList<String>(4);
		varList.add("error_no");
		varList.add("error_info");
		varList.add("error_sysinfo");
		varList.add("error_id");
		for (String varName : varList) {
			helper.addAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST, varName);
			popVarList.add(varName);
		}
		
	}

	/**
	 * 添加域
	 */
	private void addDomain(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		handler.addDomain(new TokenDomain(getKey(),null));
	}

}
