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

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.token.PORCStatementToken;
import com.hundsun.ares.studio.core.IARESProject;
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
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;


/**
 * @author zhuyf
 *
 */
public class PROCStatementMacroHandler implements IMacroTokenHandler {
	
	private List<String> queryFieldList;//用于保存Select语句的查询字段
	
	private List<String> inFieldList;//用于保存动态语句的条件字段
	

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
		queryFieldList = new ArrayList<String>();
		inFieldList = new ArrayList<String>();
		
		addMacroNameToMacroList(token,context);//添加到宏列表表
		if(token.getParameters().length > 0){
			if((token.getParameters()[0].indexOf("select") >= 0) && (token.getParameters()[0].indexOf("into") >= 0)){//不符合MySQL语法要求
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("SQL[%s]不符合MySQL语法要求，去除select into写法，改为select as。", token.getParameters()[0]);
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			}
			splitFieldList(token,context);//解析SQL语句中的@变量，加入到PRO*C变量声明列表中
			BasicResourceInfo brInfo = (BasicResourceInfo) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
			String sql = PseudoCodeParser.insertCommonForSql(token.getParameters()[0], brInfo.getObjectId());
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
			Set<String> rsList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_RESULTSET_LIST);
			Set<String> stateList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_STATEMENT_LIST);
			AtomFunction func = (AtomFunction)context.get(IAtomEngineContextConstantMySQL.ResourceModel);
			int rsTotalSizeAdd1 = rsList.size() + 1;
			int stateTotalSizeAdd1 = stateList.size() + 1;
			String objectId = func.getObjectId();
			if(StringUtils.isBlank(objectId) && (func instanceof AtomService)){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = "获取功能号失败,无法正常生成lpResultSet";
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			}else if(StringUtils.isBlank(objectId) && !(func instanceof AtomService)){
				objectId = func.getName();
			}
			String rsID = objectId + rsTotalSizeAdd1;
			String stateID = objectId + stateTotalSizeAdd1;
			helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_STATEMENT_LIST,stateID);//所需释放的动态语句列表
			if(sql.startsWith("select") || sql.startsWith("SELECT")){//select时，才需要存入结果集编号
				helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_RESULTSET_LIST,rsID);//所需释放的结果集列表
				helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_GETLAST_RESULTSET,rsID);//
			}
			codeToken.add(new PORCStatementToken(sql,rsID,stateID,queryFieldList,inFieldList));//生成对应的sql语句
		}else{
			fireEventLessParameter(context);//发送缺少参数事件
		}
		return codeToken.iterator();
	}
	
	/**
	 * 解析SQL语句中的@变量，加入到PRO*C变量声明列表中
	 * @param procVarList
	 */
	private void splitFieldList(IMacroToken token,Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		Pattern p_as = Pattern.compile("\\s+as\\s+@[\\w\\d_]+");
		Pattern p = Pattern.compile("\\s*=\\s*@[\\w\\d_]+");
		//sql语句在第一参数中
		String sql = token.getParameters()[0];
		Matcher m_as = p_as.matcher(sql);
		while (m_as.find()) {
			int index = m_as.group().indexOf("@");
			String field = m_as.group().substring(index + 1);
			queryFieldList.add(field);
			//变量替换列表，注意这里不能是helper.addAttribute(IAtomEngineContextConstant.PSEUDO_CODE_PARA_LIST, field);
			popVarList.add(field);
		}
		Matcher m = p.matcher(sql);
		while (m.find()) {
			int index = m.group().indexOf("@");
			String field = m.group().substring(index + 1);
			inFieldList.add(field);
			//变量替换列表，注意这里不能是helper.addAttribute(IAtomEngineContextConstant.PSEUDO_CODE_PARA_LIST, field);
			popVarList.add(field);
		}
		
		
	}
	
	
	/**
	 * 把宏名加入到宏列表中
	 */
	private void addMacroNameToMacroList(IMacroToken token,Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_DATABASE_MACRO,token.getKeyword());//添加到数据库列表中
	    helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_MACRO,token.getKeyword());//添加到proc宏列表中
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
