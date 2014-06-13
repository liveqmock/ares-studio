/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.TokenDomain;
import com.hundsun.ares.studio.atom.compiler.mysql.token.PROCGetRecordEndToken;
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
public class PROCGetRecordEndMacroHandler implements IMacroTokenHandler {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.PROC_GET_RECORD_END_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 输入描述：
			[PRO*C记录获取结束]
			处理流程：
			1.检查是否与[PRO*C记录获取开始]成对匹配
			2.生成以下语句：
					EXEC SQL FETCH cursor[对象号]+[编号] INTO [字段列表@转换为proc变量];
			        if ((SQLCODE != OK_SUCCESS) && (SQLCODE != 100) && (SQLCODE != 1403))
			              {
			                 iReturnCode = SQLCODE;
			                 @error_no = SQLCODE;
			                 
			                 hs_strcpy(@error_info, sqlca.sqlerrm.sqlerrmc);
			                 @error_id = 0;
			              }
			            } while (SQLCODE == OK_SUCCESS);
			        }
			        else if ((SQLCODE != 100) && (SQLCODE != 1403))
			        {
			            iReturnCode = SQLCODE;
			            @error_no = SQLCODE;
			            hs_strcpy(@error_info, sqlca.sqlerrm.sqlerrmc);
			            @error_id = 0;
			        }
			        EXEC SQL CLOSE cursor[对象号]+[编号];
			        if ( (iReturnCode != 0) || (SQLCODE != OK_SUCCESS) )
			            goto svr_end;
			其中字段列表取自成对匹配的[PRO*C记录获取开始][要获得字段列表]
		 */
		addMacroNameToMacroList(token,context);//把宏名加到入数据库列表以及proc列表中
		List<ICodeToken> codeList = new ArrayList<ICodeToken>();
		
		if(!checkIsMacroMatch(context)){//如果PRO*C记录获取结束宏是否有PRO*C记录获取开始宏匹配
			fireEvent(context);// 发送缺少PRO*C记录获取开始宏事件
		}
		String lastId = getRsId(context);
		List<String> sqlFields =this.getSqlFields(context);//取得字段列表
		codeList.add(new PROCGetRecordEndToken(token,lastId));//添加宏codeToken
		removeDomain(context);//删除域
	   return codeList.iterator();
	}
	
	 /* *
	  * 检查是PRO*C记录获取结束宏是否有PRO*C记录获取开始宏匹配
	 * @return
	 */
	private boolean checkIsMacroMatch(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain procGetRecordBeginDomain =handler.getDomain(MacroConstant.PROC_GET_RECORD_BEGIN_MACRONAME);
		//如果前面有PRO*C记录获取开始则procGetRecordBeginDomain不为null
		return procGetRecordBeginDomain!=null;
	}
	
	/**
	 * 删除域
	 */
	private void removeDomain(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		handler.removeDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
	}

	/**
	 * 返回域
	 */
	private ITokenDomain getDomain(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		return handler.getDomain(MacroConstant.PROC_GET_RECORD_BEGIN_MACRONAME);
	}

	/**
	 * 发送缺少PRO*C记录获取开始事件
	 */
	private void fireEvent(Map<Object, Object> context){

		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = String.format("宏[%1s]缺少宏[%2s]", this.getKey(),MacroConstant.PROC_GET_RECORD_BEGIN_MACRONAME);
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	
		
	}
	/**
	 * 把宏名加入到宏列表中
	 */
	private void addMacroNameToMacroList(IMacroToken token,Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_DATABASE_MACRO,token.getKeyword());//添加到数据库列表中
	    helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_MACRO,token.getKeyword());//添加到proc宏列表中
	}
	
	/**
	 * @return 取行字段名称
	 */
	private List<String> getSqlFields(Map<Object, Object> context){
		ITokenDomain domain = getBeginDomain(context);
		return (List<String>)domain.getArgs()[1];
	}
	
	private String getRsId(Map<Object, Object> context){
		ITokenDomain domain = getBeginDomain(context);
		return (String)domain.getArgs()[0];
	}
	

	/**
	 * 获得域
	 */
	private ITokenDomain getBeginDomain(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		return handler.getDomain(MacroConstant.PROC_GET_RECORD_BEGIN_MACRONAME);
	}
	
	
	

}
