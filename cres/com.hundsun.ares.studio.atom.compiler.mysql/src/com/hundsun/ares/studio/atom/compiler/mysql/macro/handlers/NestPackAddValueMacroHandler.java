/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.token.NestPackAddValueToken;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

/**
 * @author liaogc
 *
 */
public class NestPackAddValueMacroHandler implements IMacroTokenHandler{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.NEST_PACK_ADD_VALUE_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		

		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		List<ICodeToken> codeTokens= new ArrayList<ICodeToken>();
		if (token.getParameters().length !=2) {
			ITokenListenerManager  manager =(ITokenListenerManager) context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = String.format("宏[%s]缺少参数。", MacroConstant.NEST_PACK_ADD_VALUE_MACRONAME);
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
		}else{
			String param1 = StringUtils.trim(token.getParameters()[0]);
			String param2  = StringUtils.trim(token.getParameters()[1]);
			if(StringUtils.indexOf(param2,"@")>-1){//截取取得相应的参你参数的对象名
				param2 = StringUtils.substring(param2,StringUtils.indexOf(param2,"@")+1);
				if(StringUtils.endsWith(param2,"ResultSet")){
					param2 = StringUtils.substring(param2,0,StringUtils.indexOf(param2,"ResultSet"));
				}
				
			}
			if(StringUtils.indexOf(param1,"@")>-1){
				param1 = StringUtils.substring(param1,StringUtils.indexOf(param1,"@")+1);
				if(!StringUtils.endsWith(param1,"ResultSet")){
					param1+="ResultSet";
				}
				
			}
			codeTokens.add(new NestPackAddValueToken(param1,param2));//加入相应的token
			helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_OUT_OBJECT_INIT_VARIABLE_LIST, param2);//加入到要声明的对象输入对象列表中
			List<String> popObjectVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_OBJECT_PARA_LIST);
			popObjectVarList.add(param2);//加入到对象伪代码列表中
			List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
			popVarList.remove(param2);//从普通伪代码列表 中删除
		}
		
		
		return codeTokens.iterator();
	
		
		
	}

}
