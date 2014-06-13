/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.token.PROCBlockEndToken;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

/**
 * @author zhuyf
 *
 */
public class PROCBlockEndMacroHandler implements IMacroTokenHandler {


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.PROC_BLOCK_END_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 检查是否与[PRO*C语句块开始]成对匹配
		 * 输出描述：
				<<svr_end>>
				: iReturnCode := : p_error_no;
				END;
				END-EXEC;
				if ( (SQLCODE == -28) || (SQLCODE == -1012) )
				lpConn->setErrMessage(HSDB_CONNECTION_STATUS_DISCONN,SQLCODE,sqlca.sqlerrm.sqlerrmc);
				if (SQLCODE != OK_SUCCESS)
				{
				p_error_no = 119;
				iReturnCode = 119;
				hs_strcpy(p_error_info,"PROC语句块内部错误");
				v_error_id = SQLCODE;
				hs_strcpy(v_error_sysinfo,sqlca.sqlerrm.sqlerrmc);
				goto svr_end;
				}

		 */
		addMacroNameToMacroList(token,context);//把宏名加到入数据库列表以及proc列表中
		List<ICodeToken> codeList = new ArrayList<ICodeToken>();
		addVarList(token,context);
		if(!checkIsMacroMatch(context)){//如果此宏不与PRO*C语句块开始不匹配
			fireEvent(context);//发送缺少PRO*C语句块开始宏事件
		}
		codeList.add(new PROCBlockEndToken());//添加处理codeToken
		removeDomain(context);//删除域
		
	  return codeList.iterator();//返回codeToken列表
	}
	
	
	/**
	 * 检查是PRO*C语句块结束是否有PRO*C语句块开始匹配
	 * @return
	 */
	private boolean checkIsMacroMatch(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain procBlockBeginDomain =handler.getDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
		//如果前面有PRO*C语句块开始则procBlockBeginDomain不为null
		return procBlockBeginDomain!=null;
	}
	
	
	/**
	 * 删除域
	 */
	private void removeDomain(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		handler.removeDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
	}
	/**
	 * 发送PRO*C语句块开始事件
	 */
	private void fireEvent(Map<Object, Object> context){
		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = String.format("宏[%1s]缺少宏[%2s]", MacroConstant.PROC_BLOCK_END_MACRONAME,MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	}
	
	/**
	 * 把变量添加到proc列表中去
	 * 
	 * @param procVarList
	 */
	private void addVarList(IMacroToken token,
			Map<Object, Object> context) {
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
			//加入到proc变量列表中，所有proc变量必须在proc定义区声明
		List<String> varList = new ArrayList<String>(4);
			varList.add("error_no");
		for(String varName:varList){
			helper.addAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST, varName);
			popVarList.add(varName);
		}
			
	}
	/**
	 * 把宏名加入到宏列表中
	 */
	private void addMacroNameToMacroList(IMacroToken token,Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstant.ATTR_DATABASE_MACRO,token.getKeyword());//添加到数据库列表中
	    helper.addAttribute(IAtomEngineContextConstant.ATTR_PROC_MACRO,token.getKeyword());//添加到proc宏列表中
	}
	
}
