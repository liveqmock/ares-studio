/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.mysql.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.model.database.oracle.util.OracleDataBaseUtil;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.mysql.constant.IProcedureEngineContextConstantMySQL;

/**
 * @author qinyuan
 *
 */
public class ProcedureCreateToken implements ICodeToken {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return CODE_TEXT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		
		Procedure procedure = (Procedure)context.get(IProcedureEngineContextConstantMySQL.ResourceModel);
		IARESProject project = (IARESProject)context.get(IProcedureEngineContextConstantMySQL.Aresproject);
		//函数名，如果对应数据库用户名不为空，需要带上用户名
		StringBuffer functionNameBuffer = new StringBuffer();
		String dbName = procedure.getDatabase();
		// 根据数据库名获取数据库用户名
		TableSpace tableSpace = OracleDataBaseUtil.getTableSpaceByName(project, dbName);
		if(null != tableSpace) {
			String user = tableSpace.getUser();
			if(StringUtils.isNotBlank(user)){//用户不为空
				functionNameBuffer.append(user.trim());
				functionNameBuffer.append(".");
			}
		}
		functionNameBuffer.append(procedure.getName().trim());
		
		//输入输出参数不存在，不需要括号
		int inputParamNum = procedure.getInputParameters().size();//输入参数个数
		int outputParamNum = procedure.getOutputParameters().size();//输出参数个数
		if(inputParamNum <= 0 && outputParamNum <= 0 && !procedure.isOutputCollection()){//结果集返回要申明游标
			if(StringUtils.equalsIgnoreCase(procedure.getBizType(), IProcedureEngineContextConstantMySQL.procedure)){
				return String.format(procedure_create_info, functionNameBuffer.toString());
			}else {
				return String.format(function_create_info, functionNameBuffer.toString());
			}
		}else {
			if(StringUtils.equalsIgnoreCase(procedure.getBizType(), IProcedureEngineContextConstantMySQL.procedure)){
				return String.format(procedure_create_info, functionNameBuffer.toString())+ "(" + NEWLINE;
			}else {
				return String.format(function_create_info, functionNameBuffer.toString())+ "(" + NEWLINE;
			}
		}
	}
	
	private static final String function_create_info = 
		//"-- 这个是MySQL存储过程引擎。" + NEWLINE +
		"prompt create function '%1$s' ..." + NEWLINE +
		"create or replace function %1$s" + NEWLINE ;
	
	//定义类型，证券二部扩展使用
	private static final String procedure_create_info = 
		"prompt create procedure '%1$s' ..." + NEWLINE +
		"create or replace procedure %1$s" + NEWLINE ;
}
