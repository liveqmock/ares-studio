/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.procedure.IProcedureMacroTokenService;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.procdure.Procedure;

/**
 * @author zhuyf
 *
 */
public class LpSPDefineToken implements ICodeToken {
	
	private final static String lpSP_DEFINE_STR = "ICallableStatement * lpSP = NULL;" + ITokenConstant.NL;

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
		/*
		 * 只有在存在存储过程调用，且存储过程返回为结果集时，才需要声明lpSP，生成以下语句：
		 * ICallableStatement * lpSP = NULL;
		 */
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		Set<String> prcCallList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROCEDURE_CALL_RSRETURN);
		//IProcedureMacroTokenService prcservice = (IProcedureMacroTokenService)context.get(IAtomEngineContextConstant.Procedure_Macro_Service);
		if(prcCallList.size()  > 0){
			return lpSP_DEFINE_STR;//返回结果集的过程调用，才需要lpSP，而且只需要定义一次。
		}
		return "";
	}

}
