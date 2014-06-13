/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author zhuyf
 *
 */
public class FunctionPackServiceDefineToken implements ICodeToken {

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
		// 当存在函数调用时，声明IF2PackSvr * lpPackService = lpContext->GetF2PackSvr();
		// 当存在函数调用时，声明IF2Packer * lpFuncInPacker = lpPackService->GetPacker(2);
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		Set<String> funcCallList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_FUNC_CALL);
		//IFunctionMacroTokenService fmservice = (IFunctionMacroTokenService)context.get(IAtomEngineContextConstant.Function_Macro_Service);

		StringBuffer sb = new StringBuffer();
		
//		sb.append("//IF2PackSvr声明及初始化\r\n");
//		sb.append("IF2PackSvr * lpPackService = lpContext->GetF2PackSvr();\r\n");	
//		helper.addAttribute(IAtomEngineContextConstant.ATTR_LP_PACK_SERVICE_LIST, "lpPackService");
		
		if(funcCallList.size() >0){
				
				
				sb.append("//IF2Packer声明及初始化\r\n");
				sb.append("IF2Packer * lpFuncInPacker = lpPackService->GetPacker(2);\r\n");
		}
			
		
		
		return sb.toString();
	}

}
