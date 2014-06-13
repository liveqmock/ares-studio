/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.token.ResultSetObjectReturnToken;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

/**
 * 结果集对象赋值
 * @author qinyuan
 *
 */
public class ResultSetObjectSetValueMacroHandler implements IMacroTokenHandler{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.RESULTSET_OBJECT_SET_VALUE_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		List<ICodeToken> codeTokens= new ArrayList<ICodeToken>();
		if (token.getParameters().length !=1) {
			ITokenListenerManager  manager =(ITokenListenerManager) context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("宏[%s]缺少参数。", MacroConstant.RESULTSET_OBJECT_SET_VALUE_MACRONAME);
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
		}else{
			String objectParam = StringUtils.trim(token.getParameters()[0]);
			if(!StringUtils.endsWith(objectParam,"ResultSet")){//如果对象参数不是以ResultSet则append the suffix ResultSet
				objectParam +="ResultSet";
			}
			if(StringUtils.indexOf(objectParam,"@")>-1){
				objectParam = StringUtils.substring(objectParam,StringUtils.indexOf(objectParam,"@")+1);
			}
			codeTokens.add(new ResultSetObjectReturnToken(objectParam));
		}
		return codeTokens.iterator();
	}

}
