/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.MarkConfig;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.TableResourceUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.token.PROCInsertToken;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.jres.model.database.TableColumn;

/**
 * @author zhuyf
 *
 */
public class PROCInserTableMacroHandler implements IMacroTokenHandler {


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.PROC_INSERT_TABLE_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * [PRO*C插入表记录][table][默认值]
		 * 1.根据表名得到表所有的字段名，字段都塞入Proc变量列表中
		 * 2.再每个字段值插入具体的值，取值规则如下：
				首先判断在[默认值]中是否有该字段的默认值，
				如果存在和该字段同名的变量则将该变量的值作为插入值，
				如果有则取到该默认值，如果没有就从输入输出参数和内部变量中寻找，
				如果还没有则根据字段名从标准字段列表中取的该字段的真实默认值作为插入值。
			3.添加错误处理语句，如果该宏的第一列标志位包含“E”，那在出错处理中要关闭游标，
			如果再函数接口标志或该宏的第一列标志中包含“M”，那再出错处理中不包含“goto svr_end”语句。
			4.支持跨用户表名，如[PRO*C插入表记录][his_clientjour][clientjour]
			[PRO*C插入表记录][fil_clientjour][clientjour]
			[PRO*C插入表记录][r_clientjour][clientjour]
			[PRO*C插入表记录][rl_clientjour][clientjour]
		 */
		 
		  
		
		List<ICodeToken> codeToken = new ArrayList<ICodeToken>(3);
		
		addMacroNameToMacroList(token,context);//把宏名加到入数据库列表以及proc列表中
		if(token.getParameters().length > 0){
			IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
			List<TableColumn> tableColumns = TableResourceUtil.getFieldsWithoutFlag("H",token.getParameters()[0],project);//获取表名对应的列
			Map<String, String> defaultValueMap =  new HashMap<String, String>();//默认值列表
			if(isContainDefauleValue(token)){
				defaultValueMap =getDefaultValueList(token);
			}
			List<String> fieldNames = getFieldNames(tableColumns);//表对应的字段
			addPVarList(token,context,defaultValueMap,fieldNames);//把各字段加入到变量列表中去
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
			Set<String> stateList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_STATEMENT_LIST);
			AtomFunction func = (AtomFunction)context.get(IAtomEngineContextConstantMySQL.ResourceModel);
			int stateTotalSizeAdd1 = stateList.size() + 1;
			String objectId = func.getObjectId();
			if(StringUtils.isBlank(objectId) && (func instanceof AtomService)){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = "获取功能号失败,无法正常生成lpResultSet";
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			}else if(StringUtils.isBlank(objectId) && !(func instanceof AtomService)){
				objectId = func.getName();
			}
			String stateID = objectId + stateTotalSizeAdd1;
			helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_STATEMENT_LIST,stateID);//所需释放的动态语句列表
			codeToken.add(new PROCInsertToken(token,defaultValueMap,tableColumns,stateID));//添加proc插入语句基本select语句处理Token
			
		}else{
			fireEventLessParameter(context);//发送缺少参数事件
		}
		return codeToken.iterator();
	}
	
	/**
	 * 获得字段列表
	 * @param tableColumns
	 */
	private List<String>  getFieldNames(List<TableColumn> tableColumns){
		List<String> fieldNames = new ArrayList<String>(40);
		for(TableColumn tableColumn:tableColumns){
			fieldNames.add(tableColumn.getFieldName());
		}
		return fieldNames;
		
	}
	
	/**
	 * 判断是否存在默认值
	 * @param token
	 * @return
	 */
	private boolean isContainDefauleValue(IMacroToken token){
		return token.getParameters().length==2 && StringUtils.isNotBlank(token.getParameters()[1]);
		
	}

	/**
	 * 把字段估添加到变量列表中去
	 * 
	 * @param procVarList
	 * @param tableColumns
	 */
	private void addPVarList(IMacroToken token,Map<Object, Object> context,Map<String, String> defaultValueMap,List<String> fieldNames) {
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		for (String fieldName : fieldNames) {
			if (!defaultValueMap.containsKey(fieldName)) {//如果默认值列表中没有表字段对应的值
				helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_VARIABLE_LIST,fieldName);
				popVarList.add(fieldName);
			}else if(defaultValueMap.containsKey(fieldName)){//如果默认值中有字段对应的默认值
				String valueVarName = defaultValueMap.get(fieldName);
				if (valueVarName.indexOf(MarkConfig.MARK_AT) >= 0) {// 如果默认参数值为变量
					String procVarName = valueVarName.substring(valueVarName.indexOf(MarkConfig.MARK_AT) + 1);
					helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_VARIABLE_LIST,procVarName);
					popVarList.add(procVarName);
				}
			}
		}
		
		
	
	}
	
	/**
	 * 返回默认值列表
	 * @return
	 */
	
	private Map<String, String> getDefaultValueList(IMacroToken token) {
		Map<String, String> defaultValueMap = new Hashtable<String, String>();
		// 如果PROC插入表记录语句有默认值列表
		if (token.getParameters().length > 1) {
			String fieldStr =token.getParameters()[1];
			//fieldStr = EngineUtil.replaceConstant(fieldStr, this.getFunction(), true);
			defaultValueMap = PseudoCodeParser.parserKeyValueWithAt(fieldStr);
		}
		return defaultValueMap;
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
		String message = String.format("宏[%s]缺少参数，必须有表名参数。", MacroConstant.PROC_INSERT_TABLE_MACRONAME);
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	
	}

	

	
	
	
}
