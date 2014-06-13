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
import com.hundsun.ares.studio.atom.compiler.token.PROCBlockBeginToken;
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
public class ProcBlockBeginMacroHandler implements IMacroTokenHandler {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.PROC_BLOCK_BEGIN_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 输入描述：
		[PRO*C语句块开始]，[PRO*C语句块结束]
		处理流程：
		在伪代码编辑器中输入[PRO*C语句块开始]，[PRO*C语句块结束]，点击代码预览tab页，查看对应的真实代码。
		再这两个宏之间的代码要做以下特殊处理：
		在该两个宏之间的代码可以使用宏[事务处理开始]，[事务处理结束]，[插入表记录]，[select插入表记录]，具体生产规则见这些宏的说明。
		在这两个宏之间出现的变量要声明在PROC声明中，具体做法为如果出现的变量还未在变量声明队列中出现，则将它加入PROC变量声明队列中。
		在两个宏之间出现的变量，再最后对@符号进行替换的时候要统一加上‘：’。
		在两个宏之间调用过程要使用PROC调用方式。
		输出描述：（包括输出表格形式）
		EXEC SQL EXECUTE //proc语句块开始;
		BEGIN

		 */
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstant.ATTR_PROC_MACRO, this.getKey());
		helper.addAttribute(IAtomEngineContextConstant.ATTR_DATABASE_MACRO, this.getKey());
		List<ICodeToken> codeTokens = new ArrayList<ICodeToken>();
		if(!checkIsMacroMatch(context)){//检查PRO*C语句块开始]宏前面是否有其他的PRO*C语句块开始]宏
			fireEvent(context);//发送宏错误匹配事件
		}
		codeTokens.add(new PROCBlockBeginToken());//添加宏处理codeToken
		addDomain(context);//添加域
		
		return codeTokens.iterator();
	}
	
	/**
	 * 检查是PRO*C语句块开始宏是否有其他的PRO*C语句块开始宏
	 * @return
	 */
	private boolean checkIsMacroMatch(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain procBlockBeginDomain =handler.getDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
		//如果前面有PRO*C语句块开始则procBlockBeginDomain不为null
		return procBlockBeginDomain==null;
	}
	
	
	
	
	/**
	 * 发送宏错误匹配事件
	 */
	private void fireEvent(Map<Object, Object> context){

		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = String.format("宏"+MacroConstant.PROC_BLOCK_BEGIN_MACRONAME+"前面不能有未结束的[%s]宏", MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
		//发送PRO*C语句块开始]宏前面不能有其他的PRO*C语句块开始]宏事件
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	
		
	}
	
	/**
	 * 添加域
	 */
	private void addDomain(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		handler.addDomain(new TokenDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME, null));
		
	}
	

}
