/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.oracle.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.oracle.constant.IProcedureEngineContextConstantOracle;

/**
 * @author qinyuan
 *
 */
public class ProcedureEndToken implements ICodeToken {

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
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		
		Procedure procedure = (Procedure)context.get(IProcedureEngineContextConstantOracle.ResourceModel);
		//2014年5月13日13:47:37 证券二部需要使用过程名称，与原恒生开发工具保持一致，属性过程名称只在证券二部扩展界面展示
		String procName = StringUtils.isNotBlank(procedure.getProcName())?procedure.getProcName():procedure.getName();
		return String.format(proc_end_info, procName);
	}
	
	private static final String proc_end_info = 
		NEWLINE  +
		"end %s;" + NEWLINE  +
		"/" + NEWLINE ;

}
