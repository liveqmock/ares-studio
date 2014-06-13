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

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procedure.compiler.oracle.constant.IProcedureEngineContextConstantOracle;
import com.hundsun.ares.studio.procedure.compiler.oracle.macro.MacroConstant;
import com.hundsun.ares.studio.procedure.compiler.oracle.skeleton.util.ParamReplaceUtil;
import com.hundsun.ares.studio.procedure.compiler.oracle.skeleton.util.ProcedureCompilerUtil;
import com.hundsun.ares.studio.procedure.compiler.oracle.skeleton.util.TableResourceUtil;
import com.hundsun.ares.studio.reference.ReferenceManager;

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
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IProcedureEngineContextConstantOracle.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IProcedureEngineContextConstantOracle.ATTR_PROC_MACRO, this.getKey());
		helper.addAttribute(IProcedureEngineContextConstantOracle.ATTR_DATABASE_MACRO, this.getKey());
		List<ICodeToken> tokens = new ArrayList<ICodeToken>();
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
//		ITokenDomain procBlockBeginDomain =handler.getDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
//		if (procBlockBeginDomain == null && !(context.get(IAtomEngineContextConstant.ResourceModel) instanceof Procedure)) {
//			throw new RuntimeException(getKey() + " 未在PROC语句块中使用");
//		}
		if (token.getParameters().length > 0) {
			TableResourceData table = getTableByName(context , getTableName(token.getParameters()[0]));
			if (table == null) {
				throw new RuntimeException(String.format("插入表记录,表:%1$s不存在。",token.getParameters()[0] ));
			}else {
				IARESProject project = (IARESProject)context.get(IProcedureEngineContextConstantOracle.Aresproject);
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
						helper.getAttribute(IProcedureEngineContextConstantOracle.ATTR_PROC_VARIABLE_LIST).add(tc.getFieldName());
						String defaultValue = getDefaultValue(defaultMap, context, project, tc.getFieldName());
//						tc.setDefaultValue(defaultValue);
						if(!defaultMap.containsKey(tc.getFieldName())){
							defaultMap.put(tc.getFieldName(), defaultValue);
						}
					}else {
						throw new RuntimeException(String.format("表字段：%1$s对应标准字段不存在。",tc.getFieldName()));
					}
				}
				tokens.add(new InsertTableCodeTokenImpl(table,defaultMap));
			}
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
		colomnType= ProcedureCompilerUtil.getRealDataType(fieldName, project, MetadataServiceProvider.C_TYPE);
		if (TypeRule.typeRuleInt(colomnType) || TypeRule.typeRuleDouble(colomnType) || TypeRule.typeRuleClob(colomnType))// 最终类型为int || double
		{
			return "@" + fieldName;

		}  else if (TypeRule.typeRuleChar(colomnType) || TypeRule.typeRuleCharArray(colomnType))// 最终类型为char和char[]
		{
			return "nvl(@"+fieldName+" , ' ')";
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 获得表名(短名)
	 * @param tableAllName
	 * @return
	 */
	private String getTableName(String tableAllName){
		if(StringUtils.isNotBlank(tableAllName)){
			//如果表名前面存在用户名
			if (tableAllName.indexOf(".") != -1) {
				return tableAllName = tableAllName.substring(tableAllName.indexOf(".") + 1);
			}else{
				return tableAllName;
			}
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 根据表名获得表资源
	 * @param tableName
	 * @return
	 */
	private TableResourceData getTableByName(Map<Object, Object> context ,String tableName){
		IARESProject aresProject = (IARESProject) (context.get(IProcedureEngineContextConstantOracle.Aresproject));
		ReferenceManager manager = ReferenceManager.getInstance();
		ReferenceInfo ref = manager.getFirstReferenceInfo(aresProject, IDatabaseRefType.Table, tableName, true);
		if (ref != null) {
			return (TableResourceData) ref.getObject();
		}else{
			ITokenListenerManager  tokenListenerManager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("表资源获取出错,请确定表资源是否存在或者是否同步", MacroConstant.INSERT_TABLE_INPROCBLOCK_MACRONAME);
			tokenListenerManager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
		}
		return null;
	}
	
	private class InsertTableCodeTokenImpl implements ICodeToken{

		private TableResourceData table;
		private Map<String ,String> defaultMap;
		//添加默认值，防止一个资源中多次超一个表中插入记录时，默认值冲突
		public InsertTableCodeTokenImpl(TableResourceData table,Map<String ,String> defaultMap){
			this.table = table;
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
			IARESProject aresProject = (IARESProject) (context.get(IProcedureEngineContextConstantOracle.Aresproject));
			StringBuffer insertCode = new StringBuffer();
			String tableName = table.getName();

			TableColumn[] columns = ParamReplaceUtil.getFieldsWithoutFlag(table , "H",tableName);
			BasicResourceInfo brInfo = (BasicResourceInfo) context.get(IProcedureEngineContextConstantOracle.ResourceModel);
			insertCode.append("\tinsert "+"/*"+brInfo.getObjectId()+"*/"+" into ");
			insertCode.append(tableName);
			insertCode.append("\r\n\t(\r\n\t\t");
			for (int i = 0; i < columns.length; i++) {
				TableColumn field = columns[i];
				if (i != 0) {
					insertCode.append(",");
					if (i % 4 == 0) {
						insertCode.append("\r\n\t\t");
					}
				}
				insertCode.append(ParamReplaceUtil.formatInsert(field.getName()));
			}
			insertCode.append("\r\n\t)\r\n");
			insertCode.append("\tvalues\r\n\t(\r\n\t\t");
			for (int i = 0; i < columns.length; i++) {
				TableColumn column = columns[i];
				if (i != 0) {
					insertCode.append(",");
					if (i % 4 == 0) {
						insertCode.append("\r\n\t\t");
					}
				}
				if(defaultMap.containsKey(column.getFieldName()) && StringUtils.isNotBlank(defaultMap.get(column.getFieldName()))){
					insertCode.append(defaultMap.get(column.getFieldName()));
				}else {
					String dv = column.getDefaultValue();
					if (StringUtils.isBlank(dv) && column.getColumnType() == ColumnType.STD_FIELD) {
						ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(aresProject, IMetadataRefType.StdField, column.getFieldName(), false);
						if (info != null && info.getObject() instanceof StandardField) {
							StandardField std = (StandardField) info.getObject();
							String bizType = std.getDataType();
							info = ReferenceManager.getInstance().getFirstReferenceInfo(aresProject, IMetadataRefType.BizType, bizType, false);
							if (info != null && info.getObject() instanceof BusinessDataType) {
								BusinessDataType busType = (BusinessDataType) info.getObject();
								String defaultValue = busType.getDefaultValue();
								info = ReferenceManager.getInstance().getFirstReferenceInfo(aresProject, IMetadataRefType.DefValue, defaultValue, false);
								if (info != null && info.getObject() instanceof TypeDefaultValue) {
									TypeDefaultValue tdv = (TypeDefaultValue) info.getObject();
									dv = tdv.getValue("oracle");
								}
							}
						}
					}
					insertCode.append(dv);
				}
			}
			insertCode.append("\r\n\t);\r\n");
		
			return insertCode.toString();
		}
	}
	
}
