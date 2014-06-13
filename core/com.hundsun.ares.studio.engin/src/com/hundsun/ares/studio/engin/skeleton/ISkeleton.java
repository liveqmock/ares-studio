package com.hundsun.ares.studio.engin.skeleton;

import java.util.Iterator;
import java.util.Map;

import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.parser.IPseudocodeParser;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

public interface ISkeleton {

	/**
	 * 获取蓝图token
	 * @param resource
	 * @param context
	 * @return
	 */
	public Iterator<ICodeToken> getSkeletonToken()throws Exception;
	
	/**
	 * 创建运行时上下文
	 * @return
	 * @throws Exception
	 */
	public Map<Object, Object> getRuntimeContext()throws Exception;
	
	
	
	/**
	 * 运行后的检查
	 * @return
	 */
	public Object[] postValidate();
	
	
	
	/**
	 * 处理普通文本token
	 * @param token
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public ICodeToken handleTextToken(ICodeToken token,Map<Object, Object> context)throws Exception;
	
	/**
	 * 获取宏token的处理器
	 * @param macro      宏
	 * @param context   上下文
	 * @return
	 * @throws Exception
	 */
	public IMacroTokenHandler getMacroTokenHandler(IMacroToken macro,Map<Object, Object> context)throws Exception;

	
	/**
	 * 获取伪代码解析器
	 * @param token
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public IPseudocodeParser getPseudocodeParser(ICodeToken token,Map<Object, Object> context)throws Exception;
	
	//	public boolean IsNeedFormat();
	
	/**
	 * 统一处理如@变量转换为p,v变量，<>错误常量等的值翻译
	 */
	public void onFinish(Map<Object, Object> context)throws Exception;
	
	
	
	/**
	 * 获取注释类型
	 */
	public int getCommentType();
}
