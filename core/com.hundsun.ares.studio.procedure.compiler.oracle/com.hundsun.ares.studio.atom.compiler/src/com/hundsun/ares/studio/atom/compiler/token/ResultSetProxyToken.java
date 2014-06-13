/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.ParamReplaceUtil;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;

/**
 * @author zhuyf
 * ResultSet匹配最近结果集序号
 */
public class ResultSetProxyToken implements ICodeToken {
	
	ICodeToken proxy;
	
	String lastRsId;
	
	

	/**
	 * @param proxy
	 */
	public ResultSetProxyToken(ICodeToken proxy,String lastRsId) {
		this.proxy = proxy;
		this.lastRsId = lastRsId;
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
		if(StringUtils.isBlank(lastRsId)){
			fireEventLessFunctionId(context);
		}
		return code.replace("lpResultSet->", "lpResultSet" + lastRsId + "->");
	}
	
	/**
	 * 发送缺少参数事件
	 */
	
	private void fireEventLessFunctionId(Map<Object, Object> context){

		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = "获取功能号失败,无法正常生成lpResultSet";
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	
	}

}
