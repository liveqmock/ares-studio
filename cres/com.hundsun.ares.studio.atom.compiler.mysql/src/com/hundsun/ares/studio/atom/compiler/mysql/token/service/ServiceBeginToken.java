/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token.service;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;

/**
 * @author zhuyf
 *
 */
public class ServiceBeginToken implements ICodeToken {

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
		// 本token完成以下工作：
		// 1.生成原子服务、原子函数的方法头，内容如下：
		/*[中文名]
	     int PLATFORM_EXPORT F[对象号](IASContext * pContext,IUnPacker * lpInUnPacker,IPacker * lpOutPacker)
	    '{'
           int iRetCode = 0;
		 */
		
		AtomService as = (AtomService)context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		StringBuffer codeBuffer = new StringBuffer();
		if(StringUtils.isBlank(as.getObjectId())){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = "资源:"+as.getName()+"请设置功能号";
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
		}
		codeBuffer.append(String.format(ATOM_SERV_HAND_MSG, as.getChineseName(),as.getObjectId()));
		return codeBuffer.toString();
	}

	private final static String ATOM_SERV_HAND_MSG = "//%1$s\r\n" +
					"int FUNCTION_CALL_MODE F%2$s(IAS2Context* lpContext,IF2UnPacker * lpInUnPacker,IF2Packer * lpOutPacker)\r\n" +
					"{\r\n" +
					"int iReturnCode = 0;\r\n";
}
