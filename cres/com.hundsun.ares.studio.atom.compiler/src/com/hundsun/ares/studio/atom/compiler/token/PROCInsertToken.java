/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
/***********************************************************************************************************************************************
   修订时间          修订版本    修改单    修改人    申请人      修改内容      修改原因          备注 
 2013-03-31                  zhuyf         由C_TYPE改为ORACLE_TYPE，插入表记录等都是对数据库操作，应取数据库数据类型 
************************************************************************************************************************************************/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.MarkConfig;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * @author liaogc
 *
 */
public class PROCInsertToken implements ICodeToken{
	public static final String NL = ITokenConstant.NL;
	
	private IMacroToken macroToken ;//当前处理的宏
	private AtomFunction atomFunction;//原子函数模型
	private Map<String, String> defaultValueMap =  new HashMap<String, String>();//默认值列表
	private List<String> fieldNames = new ArrayList<String>(40);//表对应的字段

	private List<TableColumn> tableColumns;
	
	
	
	public PROCInsertToken(IMacroToken macroToken,Map<String, String> defaultValueMap,final List<TableColumn> tableColumns){
		this.macroToken= macroToken;
		this.tableColumns = tableColumns;
		this.initFieldNames(tableColumns);
		this.defaultValueMap = defaultValueMap;
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return ICodeToken.CODE_TEXT;
	}

	


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		StringBuffer insertCode = new StringBuffer();
		
		String tName = getTableName(this.macroToken.getParameters()[0]);
		insertCode.append(String.format("// 插入表%1$s记录",tName)).append(ITokenConstant.NL);
		BasicResourceInfo brInfo = (BasicResourceInfo) context.get(IAtomEngineContextConstant.ResourceModel);
		insertCode.append("EXEC SQL insert "+"/*"+brInfo.getObjectId()+"*/"+" into ");
		insertCode.append(tName);//表名
		insertCode.append(MarkConfig.MARK_LEFTSIGN);
		
		// 表字段处理
		insertCode.append(getTableFieldStr(tableColumns));
		
		insertCode.append(NL);
		insertCode.append(MarkConfig.MARK_RIGHTSIGN);
		insertCode.append(MarkConfig.MARK_BLANK + MarkConfig.MARK_VALUE +MarkConfig. MARK_LEFTSIGN);
		insertCode.append(NL);
		
		int index = 1;//用于控件换行
		for(int i=0;i< tableColumns.size();i++){
			TableColumn tableColumn = tableColumns.get(i);
			if (i != 0) {
				insertCode.append(MarkConfig.MARK_COMMA);
			}
			String valueVarName = defaultValueMap.get(tableColumn.getFieldName());
			if (valueVarName != null) {
				//String fFieldName = tableColumn.getFieldName();
				if (valueVarName.indexOf(MarkConfig.MARK_AT) >= 0) {// 如果默认参数值为变量
					insertCode.append(MarkConfig.MARK_COLON).append(valueVarName);
				} else {// 如果默认参数值为确定的值
					insertCode.append(valueVarName);
				}

			}else{
				
				ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
				List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
				Set<String> procVars = (Set<String>)helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST);
				String colomnType=StringUtils.EMPTY;
				colomnType = AtomFunctionCompilerUtil.getRealDataType(tableColumn.getFieldName(), (IARESProject)context.get(IAtomEngineContextConstant.Aresproject), MetadataServiceProvider.C_TYPE);
				AtomFunction atomFunction =(AtomFunction) context.get(IAtomEngineContextConstant.ResourceModel);
				IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
				if (TypeRule.typeRuleInt(colomnType))// 最终类型为int
				{
					// 如果字段名在输入参数，输出参数或内部变量中，默认值取变量值
					if (AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(atomFunction, tableColumn.getFieldName(),project)|| procVars.contains(tableColumn.getFieldName())||popVarList.contains(tableColumn.getFieldName()) ) {
						insertCode.append(MarkConfig.MARK_COLON + MarkConfig.MARK_AT);
						insertCode.append(tableColumn.getFieldName());
					} else{// 表示输入、输出参数、变量中都没有该定义，则默认值取定义的值
						
						TypeDefaultValue typeDefaultValue  = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName((IARESProject)context.get(IAtomEngineContextConstant.Aresproject), tableColumn.getFieldName());
						if(typeDefaultValue!=null){
							// 2014-03-31 modified by zhuyf 由C_TYPE改为ORACLE_TYPE，插入表记录等都是对数据库操作，应取数据库数据类型 
							insertCode.append(typeDefaultValue.getValue(MetadataServiceProvider.ORACLE_TYPE));
						}
						
					}

				} else if (TypeRule.typeRuleDouble(colomnType)){// 最终类型为double
					// 如果字段名在输入参数，输出参数或内部变量中，默认值取变量值
					if (AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(atomFunction, tableColumn.getFieldName(),project)|| procVars.contains(tableColumn.getFieldName())||popVarList.contains(tableColumn.getFieldName())) {
						insertCode.append(MarkConfig.MARK_COLON + MarkConfig.MARK_AT);
						insertCode.append(tableColumn.getFieldName());
					} else{// 表示输入、输出参数、变量中都没有该定义，则默认值取定义的值
						TypeDefaultValue typeDefaultValue  = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName((IARESProject)context.get(IAtomEngineContextConstant.Aresproject), tableColumn.getFieldName());
						if(typeDefaultValue!=null){
							// 2014-03-31 modified by zhuyf 由C_TYPE改为ORACLE_TYPE，插入表记录等都是对数据库操作，应取数据库数据类型 
							insertCode.append(typeDefaultValue.getValue(MetadataServiceProvider.ORACLE_TYPE));
						}
					}

				} else if (TypeRule.typeRuleClob(colomnType)){// 最终类型为double
					// 如果字段名在输入参数，输出参数或内部变量中，默认值取变量值
					if (AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(atomFunction, tableColumn.getFieldName(),project)|| procVars.contains(tableColumn.getFieldName())||popVarList.contains(tableColumn.getFieldName())) {
						insertCode.append(MarkConfig.MARK_COLON + MarkConfig.MARK_AT);
						insertCode.append(tableColumn.getFieldName());
					} else{// 表示输入、输出参数、变量中都没有该定义，则默认值取定义的值
					
						TypeDefaultValue typeDefaultValue  = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName((IARESProject)context.get(IAtomEngineContextConstant.Aresproject), tableColumn.getFieldName());
						if(typeDefaultValue!=null){
							// 2014-03-31 modified by zhuyf 由C_TYPE改为ORACLE_TYPE，插入表记录等都是对数据库操作，应取数据库数据类型 
							insertCode.append(typeDefaultValue.getValue(MetadataServiceProvider.ORACLE_TYPE));
						}
					}

				} else if (TypeRule.typeRuleChar(colomnType) || TypeRule.typeRuleCharArray(colomnType)){// 最终类型为char和char[]
					// 如果字段名不在输入参数，输出参数，函数变量中，默认值取定义的值
					if (AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(atomFunction, tableColumn.getFieldName(),project)|| procVars.contains(tableColumn.getFieldName())||popVarList.contains(tableColumn.getFieldName())) {
						TypeDefaultValue typeDefaultValue  = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName((IARESProject)context.get(IAtomEngineContextConstant.Aresproject), tableColumn.getFieldName());
						if(typeDefaultValue!=null){
							// 2014-03-31 modified by zhuyf 由C_TYPE改为ORACLE_TYPE，插入表记录等都是对数据库操作，应取数据库数据类型 
							insertCode.append(MarkConfig.MARK_NVL + MarkConfig.MARK_LEFTSIGN + MarkConfig.MARK_COLON + MarkConfig.MARK_AT + tableColumn.getFieldName()
									+ MarkConfig.MARK_COMMA + typeDefaultValue.getValue(MetadataServiceProvider.ORACLE_TYPE) + MarkConfig.MARK_RIGHTSIGN);
						}else{
						insertCode.append(MarkConfig.MARK_NVL + MarkConfig.MARK_LEFTSIGN + MarkConfig.MARK_COLON + MarkConfig.MARK_AT + tableColumn.getFieldName()
							+ MarkConfig.MARK_COMMA + MarkConfig.MARK_SINGLEBLANK + MarkConfig.MARK_RIGHTSIGN);
						}
					} else{// 表示输入、输出参数、变量中都没有该定义，则默认值取定义的值
					
						TypeDefaultValue typeDefaultValue  = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName((IARESProject)context.get(IAtomEngineContextConstant.Aresproject), tableColumn.getFieldName());
						if(typeDefaultValue!=null){
							// 2014-03-31 modified by zhuyf 由C_TYPE改为ORACLE_TYPE，插入表记录等都是对数据库操作，应取数据库数据类型 
							insertCode.append(typeDefaultValue.getValue(MetadataServiceProvider.ORACLE_TYPE));
						}
					}
				}/* else {
					throw new WrongRealDataTypeSettingException(colomnType, column.getType());
				}*/
				
			}
			// 每4个字段换行
			if (index% 4 == 0 && index != 0) {
				insertCode.append(NL);
			}
			index++;
		}
		insertCode.append(MarkConfig.MARK_RIGHTSIGN).append(MarkConfig.MARK_SEMICOLON);
		insertCode.append(NL);
		
		insertCode.append("if (CheckDbLinkMethod(lpConn,SQLCODE) < 0) ").append(NL);
		insertCode.append("{").append(NL);
		insertCode.append("if ((SQLCODE<= ERR_DB_NO_CONTINUE_FETCH) && (SQLCODE>= ERR_DB_FAILOVER_NETWORK_OPER_FAIL))").append(NL);
		insertCode.append("{").append(NL);
		insertCode.append("iReturnCode = SQLCODE;").append(NL);
		insertCode.append(" @error_no = SQLCODE; ").append(NL);
		insertCode.append("hs_strncpy(@error_info,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);").append(NL);
		insertCode.append(" @error_id = SQLCODE; ").append(NL);
		insertCode.append("hs_strncpy(@error_sysinfo,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);").append(NL);
		insertCode.append("EXEC SQL rollback;").append(NL);
		insertCode.append(NL);
		insertCode.append("goto svr_end;").append(NL);
		insertCode.append("}").append(NL);
		insertCode.append("lpConn->setErrMessage(HSDB_CONNECTION_STATUS_DISCONN,SQLCODE,sqlca.sqlerrm.sqlerrmc); ").append(NL);
		insertCode.append("}").append(NL);
		
	 return  insertCode.toString();
	
	}
	/**
	 * 根据表字段初始字段列表
	 * @param tableColumns
	 */
	private void  initFieldNames(List<TableColumn> tableColumns){
		for(TableColumn tableColumn:tableColumns){
			fieldNames.add(tableColumn.getFieldName());
		}
		
	}
	
	/**
	 * 根据表中的列生成select中的基本的字段列表
	 * @param columns
	 * @return
	 */
	private StringBuffer getTableFieldStr(List<TableColumn>  columns){
		StringBuffer tableFieldBuffer = new StringBuffer();
		int index = 1;
		for (TableColumn field : columns) {
			
			if (index > 1) {
				tableFieldBuffer.append(MarkConfig.MARK_COMMA);
			}
			tableFieldBuffer.append(field.getFieldName());
			//每4个字段换行
			if (index % 4 == 0)
				tableFieldBuffer.append(NL);
			index++;
		}
		return tableFieldBuffer;
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
				return tableAllName.substring(tableAllName.indexOf(".") + 1);
			}
		}
		return tableAllName;
		
		
	}
	

}
