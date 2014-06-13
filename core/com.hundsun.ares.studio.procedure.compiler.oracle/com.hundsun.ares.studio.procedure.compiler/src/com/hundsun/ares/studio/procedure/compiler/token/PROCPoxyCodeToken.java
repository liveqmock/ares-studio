/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.token;

import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.procedure.compiler.constant.IProcedureEngineContextConstant;
import com.hundsun.ares.studio.procedure.compiler.skeleton.util.ParamReplaceUtil;

/**
 * @author lvgao
 *
 */
public  class PROCPoxyCodeToken  implements ICodeToken{


	ICodeToken proxy;
	/**
	 * 
	 * @param isInPROCSegment 是否在PROC语句块当中
	 */
	public PROCPoxyCodeToken(ICodeToken proxy){
		this.proxy = proxy;
	}
	


	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		return proxy.getContent();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return proxy.getType();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		String code = proxy.genCode(context);
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IProcedureEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		String[] params =  helper.getAttribute(IProcedureEngineContextConstant.ATTR_PROC_VARIABLE_LIST).toArray(new String[0]);
		Set<String> inoutParamList = helper.getAttribute(IProcedureEngineContextConstant.ATTR_IN_OUT_PARAM_LIST);
		return ParamReplaceUtil.handleParams(":",code, params, inoutParamList);
	}

}
