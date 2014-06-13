/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.token;

import java.util.Map;

import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.constant.IProcedureEngineContextConstant;

/**
 * @author qinyuan
 *
 */
public class ProcedureAnnotationToken implements ICodeToken {

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
		Procedure procedure = (Procedure)context.get(IProcedureEngineContextConstant.ResourceModel);
		
		return String.format(annotation_info, procedure.getName(),procedure.getDescription(),procedure.getObjectId());
	}
	
	/**
	 * 注释头信息
	 */
	private static final String annotation_info = 
		"/****************************************************************************************************/" +NEWLINE+
		"/* 名          称: %s                                                                                */" +NEWLINE+
		"/* 功          能: %s                                                                                */" +NEWLINE+
		"/* 功  能  编  号: %s                                                                                   */" +NEWLINE+
		"/* 返          回:                                                                                    */" +NEWLINE+
		"/* 调用函数或过程:                                                                                     */" +NEWLINE+
		"/*                                                                                                  */" +NEWLINE+
		"/****************************************************************************************************/" + NEWLINE;

}
