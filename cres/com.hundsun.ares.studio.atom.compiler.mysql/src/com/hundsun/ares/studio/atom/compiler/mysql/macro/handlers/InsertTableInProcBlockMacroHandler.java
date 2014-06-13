/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.MarkConfig;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.ParamReplaceUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.TableResourceUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.procdure.Procedure;

/**
 * @author zhuyf
 *
 */
public class InsertTableInProcBlockMacroHandler implements IMacroTokenHandler {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return MacroConstant.INSERT_TABLE_INPROCBLOCK_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 输入描述：
		[插入表记录][table][默认值]
		处理流程：
		1.在伪代码编辑器中输入[插入表记录][table][默认值]，点击代码预览tab页，查看对应的真实代码。
		在具体的处理过程中，首先取得表名，如果该表不存在，则抛出异常“插入表记录,表不存在”，再根据表名得到表所有的字段名，如果表字段在标准字段中找不到，则抛出异常“表字段不存在”，在每个字段值插入具体的值，取值规则如下：
		首先判断在[默认值]中是否有该字段的默认值，如果有则取到该默认值，如果没有就从输入输出参数和内部变量中寻找，如果存在和该字段同名的变量则将该变量的值作为插入值，如果还没有则根据字段名从标准字段列表中取的该字段的真实默认值作为插入值。
		2.该宏一般出现再过程或函数的PROC语句块中。
		3.如果未在PROC语句块中使用，则抛出异常。
		4.支持跨用户表名，如[插入表记录][his_clientjour][clientjour]
			[插入表记录][fil_clientjour][clientjour]
			[插入表记录][r_clientjour][clientjour]
			[插入表记录][rl_clientjour][clientjour]
		 */
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_MACRO, this.getKey());
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_DATABASE_MACRO, this.getKey());
		List<ICodeToken> tokens = new ArrayList<ICodeToken>();
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain procBlockBeginDomain =handler.getDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
		if (procBlockBeginDomain == null && !(context.get(IAtomEngineContextConstantMySQL.ResourceModel) instanceof Procedure)) {
			throw new RuntimeException(getKey() + " 未在PROC语句块中使用");
		}
		if (token.getParameters().length > 0) {
			IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
			List<TableColumn> tableColumns = TableResourceUtil.getFieldsWithoutFlag("H",token.getParameters()[0],project);
				Map<String ,String> defaultMap = new HashMap<String, String>();
				if (token.getParameters().length > 1) {
					for (String defaultValue : token.getParameters()[1].split(",")) {
						String[] pair = StringUtils.split(defaultValue, "=");
						if (StringUtils.isNotBlank(defaultValue) && StringUtils.split(defaultValue, "=").length > 1) {
							defaultMap.put(pair[0].trim(), pair[1].trim());
						}
					}
				}
				for(TableColumn tc : tableColumns){
					StandardField std = MetadataServiceProvider.getStandardFieldByName(project, tc.getFieldName());
					if (std != null) {
						//加入PROC变量定义
						helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_VARIABLE_LIST, tc.getFieldName());
						String defaultValue = getDefaultValue(defaultMap, context, project, tc.getFieldName());
//						tc.setDefaultValue(defaultValue);
						if(!defaultMap.containsKey(tc.getFieldName())){
							defaultMap.put(tc.getFieldName(), defaultValue);
						}else if(defaultMap.containsKey(tc.getFieldName())){//如果默认值中有字段对应的默认值
							String valueVarName = defaultMap.get(tc.getFieldName());
							if (valueVarName.indexOf(MarkConfig.MARK_AT) >= 0) {// 如果默认参数值为变量
								String procVarName = valueVarName.substring(valueVarName.indexOf(MarkConfig.MARK_AT) + 1);
								helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_VARIABLE_LIST,procVarName);
								defaultMap.put(tc.getFieldName(),":" +defaultMap.get(tc.getFieldName()));
							}
						}
					
					}else {
						throw new RuntimeException(String.format("表字段：%1$s对应标准字段不存在。",tc.getFieldName()));
					}
				}
				tokens.add(new InsertTableCodeTokenImpl(token.getParameters()[0],tableColumns,defaultMap));
		}
		
		return tokens.iterator();
	}
	
	/**
	 * 获取默认值
	 * 
	 * @param token
	 * @param context
	 * @param project
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	private String getDefaultValue(Map<String ,String> defaultMap ,Map<Object, Object> context , IARESProject project ,String fieldName) throws Exception{
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		popVarList.add(fieldName);
		
		if (defaultMap.get(fieldName) != null) {
			return defaultMap.get(fieldName);
		}
		
		String colomnType=StringUtils.EMPTY;
		colomnType= AtomFunctionCompilerUtil.getRealDataType(fieldName, project, context);
		if (TypeRule.typeRuleInt(colomnType) || TypeRule.typeRuleDouble(colomnType) || TypeRule.typeRuleClob(colomnType))// 最终类型为int || double
		{
			return ":@" + fieldName;

		}  else if (TypeRule.typeRuleChar(colomnType) || TypeRule.typeRuleCharArray(colomnType))// 最终类型为char和char[]
		{
			return "nvl(:@"+fieldName+" , ' ')";
		}
		return StringUtils.EMPTY;
	}
	

	private class InsertTableCodeTokenImpl implements ICodeToken{

		private String tableName;
		private List<TableColumn>columns; 
		private Map<String ,String> defaultMap;
		
		public InsertTableCodeTokenImpl(String tableName,List<TableColumn>columns,Map<String ,String> defaultMap){
			this.tableName = tableName;
			this.columns = columns;
			this.defaultMap = defaultMap;
		}
		
		@Override
		public String getContent() {
			return null;
		}

		@Override
		public int getType() {
			return ICodeToken.CODE_TEXT;
		}

		@Override
		public String genCode(Map<Object, Object> context) throws Exception {

			StringBuffer insertCode = new StringBuffer();

			BasicResourceInfo brInfo = (BasicResourceInfo) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
			insertCode.append("insert "+"/*"+brInfo.getObjectId()+"*/"+" into ");
			insertCode.append(tableName);
			insertCode.append("\r\n(\r\n");
			for (int i = 0; i < columns.size(); i++) {
				TableColumn field = columns.get(i);
				if (i != 0) {
					insertCode.append(",");
					if (i % 4 == 0) {
						insertCode.append("\r\n");
					}
				}
				insertCode.append(ParamReplaceUtil.formatInsert(field.getName()));
			}
			insertCode.append("\r\n)");
			insertCode.append(" \r\nvalues\r\n(\r\n");
			for (int i = 0; i < columns.size(); i++) {
				TableColumn column = columns.get(i);
				if (i != 0) {
					insertCode.append(",");
					if (i % 4 == 0) {
						insertCode.append("\r\n");
					}
				}
				if(defaultMap.containsKey(column.getFieldName())){
					insertCode.append(defaultMap.get(column.getFieldName()));
				}else {
					insertCode.append(column.getDefaultValue());
				}
			}
			insertCode.append("\r\n);\r\n");
		
			return insertCode.toString();
		}
	}
	
}
