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

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.token.CommonSelectStatementToken;
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
public class CommonSelectStatementMacroHandler implements IMacroTokenHandler {
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.COMMON_SELECT_STATEMENT_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 输入描述：
		[通用SELECT][select 语句]
		处理流程：
		在伪代码编辑器中输入[通用SELECT][select 语句]，点击代码预览tab页，查看对应的真实代码。
		结果集队列中加入一个新的结果集，结果集编号为对象号加上结果集队列的长度。
		声明变量sQueryText，类型为char sQueryText[4096]，默认值为{0}。
		如果[select 语句]中取得的值为@符号开头的，则说明它是一个变量，直接写入lpConn->executeQuery()中，如果不是，这说明它是固定的字符串，如果该字符串没有加引号，自动为其加上，并将该字符串赋值给变量sQueryText，然后生成语句lpConn->executeQuery(sQueryText)。

		 */
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		Set<String> rsList = helper.getAttribute(IAtomEngineContextConstant.ATTR_RESULTSET_LIST);
		AtomFunction func = (AtomFunction)context.get(IAtomEngineContextConstant.ResourceModel);
		int rsTotalSizeAdd1 = rsList.size() + 1;
		
		
		List<ICodeToken> codeTokens= new ArrayList<ICodeToken>();
		String objectId = func.getObjectId();
		if(StringUtils.isBlank(objectId) && (func instanceof AtomService)){
			fireEventLessFunctionId(context);
		}else if(StringUtils.isBlank(objectId) && !(func instanceof AtomService)){
			objectId = func.getName();
		}
		String rsID = objectId + rsTotalSizeAdd1;
		//由于如果返回结果集，调用返回结果集的AF以及[通用SELECT]都可以直接打包输出
		helper.addAttribute(IAtomEngineContextConstant.ATTR_FUNC_RESULTSET,rsID);
		helper.addAttribute(IAtomEngineContextConstant.ATTR_RESULTSET_LIST,rsID);//所需释放的结果集列表
		helper.addAttribute(IAtomEngineContextConstant.ATTR_GETLAST_RESULTSET,rsID);//取得就近的结果集Id，凡是可以用lpResultSet->取就近结果集的，都需要加到这个列表中
		//[通用SELECT]为数据库宏
		helper.addAttribute(IAtomEngineContextConstant.ATTR_DATABASE_MACRO,getKey());
		String resultSetVarName = "lpResultSet"+objectId + rsTotalSizeAdd1;
		String sql = PseudoCodeParser.insertCommonForSql(token.getParameters()[0], func.getObjectId());
		codeTokens.add(new CommonSelectStatementToken(resultSetVarName,sql));//添加通用select处理Token
		return codeTokens.iterator();
	}
	
	/**
	 * 缺少功能号
	 * @param context
	 */
	private void fireEventLessFunctionId(Map<Object, Object> context){

		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = "获取功能号失败,无法正常生成lpResultSet";
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	
	}
	
	

}
