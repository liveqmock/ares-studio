/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.tokens;

import java.util.Map;

import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;

/**
 * @author qinyuan
 *
 */
public class LogicFunctionIsTransFunctionBeginToken implements ICodeToken {

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
		
		LogicFunction logicFunc = (LogicFunction) context.get(ILogicEngineContextConstant.ResourceModel);
		if(logicFunc.isIsTransFunc()) {
			StringBuffer ret = new StringBuffer();
			ret.append("if (lpTransMonitor)\n");
			ret.append("{\n");
			ret.append(String.format("hs_strcpy(%1$s, %2$s);\n", "ls_cancel_serialno", "lpTransMonitor->BeginTrans()"));
			
			return ret.toString();
		}
		return BlackString;
	}

}
