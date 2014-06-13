/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.mysql.token;

import java.util.Map;

import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.mysql.constant.IProcedureEngineContextConstantMySQL;

/**
 * @author qinyuan
 *
 */
public class ProcedureEndCodeToken implements ICodeToken {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
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
		StringBuffer result = new StringBuffer();
		
		Procedure procedure = (Procedure)context.get(IProcedureEngineContextConstantMySQL.ResourceModel);
		result.append(NEWLINE);
		result.append(end_code_desc_begin);
		result.append("set define off;\r\n");
		result.append("set feedback off;\r\n");
		result.append(procedure.getEndCode());
		result.append("\r\n");
		result.append("set define on;\r\n");
		result.append("set feedback on;\r\n");
		result.append(NEWLINE);
		result.append(end_code_desc_end);
		result.append(NEWLINE);
		return result.toString();
	}
	
	private final static String end_code_desc_begin = 
		"/*****************************************************/\r\n" +
		"/*   后置代码--开始                                               */\r\n" +
		"/*****************************************************/\r\n";
	private final static String end_code_desc_end = 
		"/*****************************************************/\r\n" +
		"/*   后置代码--结束                                               */\r\n" +
		"/*****************************************************/\r\n";


}
