/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.macro.TokenDomain;
import com.hundsun.ares.studio.atom.compiler.token.PROCGetRecordEndToken;
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
		String curId = "";
		{
			IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
			ITokenDomain curTokenDomain = handler.getDomain("curList");
			if(curTokenDomain!=null){
				curId = curTokenDomain.getArgs()[curTokenDomain.getArgs().length -1].toString();
				Object[] objs = curTokenDomain.getArgs();
				String[] str = new String[objs.length - 1];
				System.arraycopy(objs, 0, str, 0, objs.length-1);
				//当域中有两个参数的时候，删除原始域，添加一个同名的域（去除原始域的最后一位参数）
				if (objs.length > 1) {
					handler.removeDomain("curList");
					handler.addDomain(new TokenDomain("curList",str));
				}else if (objs.length <= 1) {//如果域中只有一个参数，则删除域
					handler.removeDomain("curList");
				}
			}
		}
		String cursorName = "cursor"+curId;
		codeList.add(new PROCGetRecordEndToken(token,cursorName));//添加宏codeToken
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
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstant.ATTR_DATABASE_MACRO,token.getKeyword());//添加到数据库列表中
	    helper.addAttribute(IAtomEngineContextConstant.ATTR_PROC_MACRO,token.getKeyword());//添加到proc宏列表中
	}
	
	
	

}
