/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.mysql.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.mysql.constant.IProcedureEngineContextConstantMySQL;

/**
 * 过程返回值处理
 * <br>证券一部默认返回0，证券二部有其他处理方式，
 * 例如不返回等，需注意
 * @author qinyuan
 *
 */
public class ProcedureReturnValueToken implements ICodeToken {

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
		
		//兼容证券二部情况，只在定义为function,并且返回类型为number时，才有return(0)
		if(StringUtils.equalsIgnoreCase(procedure.getBizType(), IProcedureEngineContextConstantMySQL.function) &&
				StringUtils.equalsIgnoreCase(procedure.getReturnType(), IProcedureEngineContextConstantMySQL.return_type)){
			return proc_return_value;
		}else {
			return "";
		}
						
	}
	
	private static final String proc_return_value = NEWLINE + "" + NEWLINE;

}
