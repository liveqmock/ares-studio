/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
/***********************************************************************************************************************************************
   修订时间          修订版本    修改单    修改人    申请人      修改内容      修改原因          备注 
 2013-03-31                  zhuyf         由C_TYPE改为ORACLE_TYPE，插入表记录等都是对数据库操作，应取数据库数据类型 
************************************************************************************************************************************************/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.MarkConfig;
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
	
	private String stateID;
	
	
	
	public PROCInsertToken(IMacroToken macroToken,Map<String, String> defaultValueMap,final List<TableColumn> tableColumns,String stateID){
		this.macroToken= macroToken;
		this.tableColumns = tableColumns;
		this.initFieldNames(tableColumns);
		this.defaultValueMap = defaultValueMap;
		this.stateID = stateID;
		
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
		StringBuffer insertSQLBuffer = new StringBuffer();
		StringBuffer setBuffer = new StringBuffer();
		
		String tName = getTableName(this.macroToken.getParameters()[0]);
		insertCode.append(String.format("// 插入表%1$s记录",tName)).append(ITokenConstant.NL);
		BasicResourceInfo brInfo = (BasicResourceInfo) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		insertSQLBuffer.append("insert "+"/*"+brInfo.getObjectId()+"*/"+" into ");
		insertSQLBuffer.append(tName);//表名
		insertSQLBuffer.append(MarkConfig.MARK_LEFTSIGN);
		
		// 表字段处理
		insertSQLBuffer.append(getTableFieldStr(tableColumns));
		
		//insertSQLBuffer.append(NL);
		insertSQLBuffer.append(MarkConfig.MARK_RIGHTSIGN);
		insertSQLBuffer.append(MarkConfig.MARK_BLANK + MarkConfig.MARK_VALUE +MarkConfig. MARK_LEFTSIGN);
		//insertSQLBuffer.append(NL);
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		Set<String> procVars = (Set<String>)helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_VARIABLE_LIST);
		AtomFunction atomFunction =(AtomFunction) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
		int index = 1;//用于控件换行
		
		for(int i=0;i< tableColumns.size();i++){
			TableColumn tableColumn = tableColumns.get(i);
			String colomnType = AtomFunctionCompilerUtil.getRealDataType(tName,tableColumn, (IARESProject)context.get(IAtomEngineContextConstantMySQL.Aresproject), context);
			if (i != 0) {
				insertSQLBuffer.append(MarkConfig.MARK_COMMA);
			}
			String valueVarName = defaultValueMap.get(tableColumn.getFieldName());
			if (valueVarName != null) {
				insertSQLBuffer.append("?");//一个值一个?
				if (TypeRule.typeRuleInt(colomnType)){// 最终类型为int
					setBuffer.append("lpSP" + stateID + "->setInt(" + (i + 1) + ", " + valueVarName + ");\r\n");//从1开始计数
				}else if (TypeRule.typeRuleDouble(colomnType)){// 最终类型为double
					setBuffer.append("lpSP" + stateID + "->setInt(" + (i + 1) + ", " + valueVarName + ");\r\n");//从1开始计数
				} else if (TypeRule.typeRuleChar(colomnType)){// 最终类型为char
					setBuffer.append("lpSP" + stateID + "->setChar(" + (i + 1) + ", " + valueVarName + ");\r\n");//从1开始计数
				}else if (TypeRule.typeRuleCharArray(colomnType)){// 最终类型为char[]
					setBuffer.append("lpSP" + stateID + "->setString(" + (i + 1) + ", " + valueVarName + ");\r\n");//从1开始计数
				}
			}else{
				if (TypeRule.typeRuleInt(colomnType))// 最终类型为int
				{
					insertSQLBuffer.append("?");//一个值一个?
					// 如果字段名在输入参数，输出参数或内部变量中，默认值取变量值
					if (AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(atomFunction, tableColumn.getFieldName(),project)|| procVars.contains(tableColumn.getFieldName())||popVarList.contains(tableColumn.getFieldName()) ) {
						setBuffer.append("lpSP" + stateID + "->setInt(" + (i + 1) + ", @" + tableColumn.getFieldName() + ");\r\n");//从1开始计数
					} else{// 表示输入、输出参数、变量中都没有该定义，则默认值取定义的值
						setBuffer.append("lpSP" + stateID + "->setInt(" + (i + 1) + ", " + AtomFunctionCompilerUtil.getRealDefaultValue(tName, tableColumn, project, context) + ");\r\n");//从1开始计数
					}

				} else if (TypeRule.typeRuleDouble(colomnType)){// 最终类型为double
					insertSQLBuffer.append("?");//一个值一个?
					// 如果字段名在输入参数，输出参数或内部变量中，默认值取变量值
					if (AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(atomFunction, tableColumn.getFieldName(),project)|| procVars.contains(tableColumn.getFieldName())||popVarList.contains(tableColumn.getFieldName())) {
						setBuffer.append("lpSP" + stateID + "->setDouble(" + (i + 1) + ", @" + tableColumn.getFieldName() + ");\r\n");//从1开始计数
					} else{// 表示输入、输出参数、变量中都没有该定义，则默认值取定义的值
						setBuffer.append("lpSP" + stateID + "->setDouble(" + (i + 1) + ", " + AtomFunctionCompilerUtil.getRealDefaultValue(tName, tableColumn, project, context) + ");\r\n");//从1开始计数
					}

				} else if (TypeRule.typeRuleChar(colomnType)){// 最终类型为char
					insertSQLBuffer.append("ifnull(?," + AtomFunctionCompilerUtil.getRealDefaultValue(tName, tableColumn, project, context) + ")");//一个值一个?
					// 如果字段名不在输入参数，输出参数，函数变量中，默认值取定义的值
					if (AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(atomFunction, tableColumn.getFieldName(),project)|| procVars.contains(tableColumn.getFieldName())||popVarList.contains(tableColumn.getFieldName())) {
						setBuffer.append("lpSP" + stateID + "->setChar(" + (i + 1) + ", " + MarkConfig.MARK_AT + tableColumn.getFieldName() + ");\r\n");//从1开始计数
					} else{// 表示输入、输出参数、变量中都没有该定义，则默认值取定义的值
						setBuffer.append("lpSP" + stateID + "->setChar(" + (i + 1) + ", " + AtomFunctionCompilerUtil.getRealDefaultValue(tName, tableColumn, project, context) + ");\r\n");//从1开始计数
					}
				}else if (TypeRule.typeRuleCharArray(colomnType)){// 最终类型为char[]
					insertSQLBuffer.append("ifnull(?," + AtomFunctionCompilerUtil.getRealDefaultValue(tName, tableColumn, project, context) + ")");//一个值一个?
					// 如果字段名不在输入参数，输出参数，函数变量中，默认值取定义的值
					if (AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(atomFunction, tableColumn.getFieldName(),project)|| procVars.contains(tableColumn.getFieldName())||popVarList.contains(tableColumn.getFieldName())) {
						setBuffer.append("lpSP" + stateID + "->setString(" + (i + 1) + ", " + MarkConfig.MARK_AT + tableColumn.getFieldName() + ");\r\n");//从1开始计数
					} else{// 表示输入、输出参数、变量中都没有该定义，则默认值取定义的值
						setBuffer.append("lpSP" + stateID + "->setString(" + (i + 1) + ", " + AtomFunctionCompilerUtil.getRealDefaultValue(tName, tableColumn, project, context) + ");\r\n");//从1开始计数
					}
				}/* else {
					throw new WrongRealDataTypeSettingException(colomnType, column.getType());
				}*/
				
			}
			// 每4个字段换行
			//if (index% 4 == 0 && index != 0) {
				//insertSQLBuffer.append(NL);
			//}
			index++;
		}
		insertSQLBuffer.append(MarkConfig.MARK_RIGHTSIGN);
		insertCode.append("lpSP" + stateID + " = lpConn->createCallableStatement();\r\n");
		insertCode.append("lpSP" + stateID + "->prepare(\"" + insertSQLBuffer.toString() + "\");\r\n");
		insertCode.append(setBuffer);
		insertCode.append("iReturnCode = lpSP" + stateID + "->exec();\r\n");
		insertCode.append("if(iReturnCode != 0)//成功返回0\r\n");
		insertCode.append("{\r\n");
		insertCode.append("iReturnCode = lpConn->getErrNo();\r\n");
		insertCode.append("@error_no = lpConn->getErrNo();\r\n");
		insertCode.append("hs_strncpy(@error_info,lpConn->getErrInfo(),256);\r\n");
		insertCode.append("@error_id = lpConn->getErrNo();\r\n");
		insertCode.append("hs_strncpy(@error_sysinfo,lpConn->getErrInfo(),256);\r\n");
		insertCode.append("//to do rollback.\r\n");
		insertCode.append("goto svr_end;\r\n");
		insertCode.append("}\r\n");
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
			//if (index % 4 == 0)
				//tableFieldBuffer.append(NL);
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
