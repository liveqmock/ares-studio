/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.token.PORCStatementToken;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;


/**
 * @author zhuyf
 *
 */
public class PROCStatementMacroHandler implements IMacroTokenHandler {
	
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.PROC_STATEMENT_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*1.解析SQL语句中的@变量，加入到PRO*C变量声明列表中
		 *2.构造一个生成需求文档中的对应语句的codetoken
		 */
		
		List<ICodeToken> codeToken = new ArrayList<ICodeToken>(1);
		
		addMacroNameToMacroList(token,context);//添加到宏列表表
		if(token.getParameters().length > 0){
			addProcVarLis(token,context);//解析SQL语句中的@变量，加入到PRO*C变量声明列表中
			BasicResourceInfo brInfo = (BasicResourceInfo) context.get(IAtomEngineContextConstant.ResourceModel);
			String sql = PseudoCodeParser.insertCommonForSql(token.getParameters()[0], brInfo.getObjectId());
			codeToken.add(new PORCStatementToken(sql));//生成对应的sql语句
		}else{
			fireEventLessParameter(context);//发送缺少参数事件
		}
		return codeToken.iterator();
	}
	
	/**
	 * 解析SQL语句中的@变量，加入到PRO*C变量声明列表中
	 * @param procVarList
	 */
	private void addProcVarLis(IMacroToken token,Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		Pattern p = Pattern.compile("@[\\w\\d_]+");
		//sql语句在第一参数中
		Matcher m = p.matcher(token.getParameters()[0]);
		while (m.find()) {
			String field = m.group().substring(1);
			helper.addAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST,field);
			//变量替换列表，注意这里不能是helper.addAttribute(IAtomEngineContextConstant.PSEUDO_CODE_PARA_LIST, field);
			popVarList.add(field);
		}
		
	}
	
	
	/**
	 * 把宏名加入到宏列表中
	 */
	private void addMacroNameToMacroList(IMacroToken token,Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstant.ATTR_DATABASE_MACRO,token.getKeyword());//添加到数据库列表中
	    helper.addAttribute(IAtomEngineContextConstant.ATTR_PROC_MACRO,token.getKeyword());//添加到proc宏列表中
	}
	
	/**
	 * 发送缺少参数事件
	 */
	
	private void fireEventLessParameter(Map<Object, Object> context){

		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = String.format("宏[%s]缺少参数，必须有SQL语句参数。", MacroConstant.PROC_STATEMENT_MACRONAME);
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	
	}
}
