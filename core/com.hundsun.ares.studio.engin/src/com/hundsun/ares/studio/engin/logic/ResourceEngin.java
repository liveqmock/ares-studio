package com.hundsun.ares.studio.engin.logic;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESProblem;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.parser.IPseudocodeParser;
import com.hundsun.ares.studio.engin.skeleton.ISkeleton;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonFactory;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonInput;
import com.hundsun.ares.studio.engin.skeleton.SkeletonFactoryManager;
import com.hundsun.ares.studio.engin.token.DefaultTokenLisetenerManager;
import com.hundsun.ares.studio.engin.token.IBranchNode;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

public class ResourceEngin {

	private static final int VALIDATE_LEVEL = 0;  
	
	private static final int GENERATE_LEVEL = 1;
	
	private static final int FORMATE_LEVEL = 2;
	
	public static ResourceEngin instance = new ResourceEngin();
	
	/**
	 * 生成代码
	 * @param input
	 * 			输入模型
	 * @param context
	 * 			上下文
	 * @param msgQueue
	 *     		 消息队列，用于返回错误信息。在模块代码生成时需要
	 * @return
	 * @throws Exception
	 */
	public String generate(ISkeletonInput input,Map<Object, Object> context,Queue<IARESProblem> msgQueue) throws Exception{
		//消息列表 
		return generate(input,msgQueue,FORMATE_LEVEL, context);
	}
	
	/**
	 * 生成代码
	 * @param input
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public String generate(ISkeletonInput input,Map<Object, Object> context) throws Exception{
		//消息列表 
		Queue<IARESProblem> msgQueue = new ArrayDeque<IARESProblem>();
		return generate(input,msgQueue,FORMATE_LEVEL, context);
	}
	
	/**
	 * 错误检查
	 * @param input
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public IARESProblem[] validate(ISkeletonInput input,Map<Object, Object> context) throws Exception{
		//消息列表 
		Queue<IARESProblem> msgQueue = new ArrayDeque<IARESProblem>();
		generate(input,msgQueue,VALIDATE_LEVEL, context);
		return msgQueue.toArray(new IARESProblem[0]);
	}
	
	/**
	 * 获取token
	 * @param input
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public String generateWithoutFromat(ISkeletonInput input,Map<Object, Object> context) throws Exception{
		//消息列表 
		Queue<IARESProblem> msgQueue = new ArrayDeque<IARESProblem>();
		return generate(input,msgQueue,GENERATE_LEVEL, context);
	}
	
	
	
	/**
	 * 生成代码
	 * @param input
	 * @param msgQueue
	 * @param checkOnly
	 * @param context
	 * @return
	 * @throws Exception
	 */
	private String generate(ISkeletonInput input,
			Queue<IARESProblem> msgQueue,
			int level,
			Map<Object, Object> context) throws Exception{
		if(!context.containsKey(IEngineContextConstant.ENGINE_EXCEPTION)){
			context.put(IEngineContextConstant.ENGINE_EXCEPTION,new HashMap<ISkeletonInput,  Queue<IARESProblem>>());
		}
		//上下文准备
		List<ICodeToken> queue = new ArrayList<ICodeToken>();
		context.put(IEngineContextConstant.TOKEN_LIST, queue);
		
		//添加时间字符串
		if(!context.containsKey(IEngineContextConstant.DATE_STR)){
			Format f = new SimpleDateFormat("yyyyMMDD HH:mm:ss");
			String value = f.format(new Date());
			context.put(IEngineContextConstant.DATE_STR, value);
		}
		if(!context.containsKey(IEngineContextConstant.USER_NAME)){
			context.put(IEngineContextConstant.USER_NAME, " ");
		}
		
		//蓝图和token处理
		ISkeletonFactory SkeletonFactory = SkeletonFactoryManager.getInstance().getSkeletonFactory(input.getType());
		ISkeleton skeleton = SkeletonFactory.createSkeleton(input.getInput(), context);
		if(null == skeleton){
			return "";
		}
		
		//运行时上下文
		Map<Object, Object> runtimeConext = skeleton.getRuntimeContext();
		//把全局上下文中信息，加入到运行时上下统一到BussinessSkeleton的构造函数中初始化
		//runtimeConext.putAll(context);
		
		//添加骨架上下文到输入
		runtimeConext.put(IEngineContextConstant.SKELETON_INPUT, input);
		
		//添加监听控制器
		if(!runtimeConext.containsKey(IEngineContextConstant.TOKEN_LISTENER_MANAGER)){
			runtimeConext.put(IEngineContextConstant.TOKEN_LISTENER_MANAGER, new DefaultTokenLisetenerManager());
		}
		ITokenListenerManager  manager = (ITokenListenerManager)runtimeConext.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);

		//添加引擎消息监听器
		EnginMessageListener listener = new EnginMessageListener();
		manager.addListener(listener);
		
		Iterator<ICodeToken> tokenIterator = skeleton.getSkeletonToken();
		//生成token列表
		handleToken(skeleton,input,tokenIterator, queue, runtimeConext,msgQueue);
		
		//蓝图后检查,用于处理所有token就绪后的一些检查
		Object[] message = skeleton.postValidate();
		if(message.length > 0){
			IARESProblem problem = getTokenError(input,null,message[0].toString());
			msgQueue.add(problem);
			((Map)(context.get(IEngineContextConstant.ENGINE_EXCEPTION))).put(input, msgQueue);
			return "//" + message[0].toString();
		}
		
		//处理警告信息:要在问题视图中展示的警告
		for(IARESProblem item: listener.getARESProblem()){
			addMessageAttr(item, input, null);
			msgQueue.add(item);
		}
		listener.clear();
		
		if( level  <= VALIDATE_LEVEL){  //仅做检查
			return "";
		}
		
//////////////////////生成代码/////////////////////////////////////////////////////
		StringBuffer buffer = new StringBuffer();
		runtimeConext.put(IEngineContextConstant.BUFFER, buffer);
		ICodeToken token1 =null;
		boolean isAddResourceInfo = false;
		for(ICodeToken ttoken:queue){
			try{
				token1 = ttoken;
				buffer.append(ttoken.genCode(runtimeConext));
			}catch(Exception e){
				if(!isAddResourceInfo){
					if(runtimeConext.get(IEngineContextConstant.CURR_RESOURCE)!=null){
						IARESResource resource = (IARESResource) runtimeConext.get(IEngineContextConstant.CURR_RESOURCE);

						IARESProblem problem = ARESProblem.createError();
						problem.setMessage("所在资源:"+resource.getFullyQualifiedName()+"\r\n");
						msgQueue.add(problem);
						isAddResourceInfo= true;
					}
				}
				msgQueue.add(getTokenError(input,ttoken, e.getMessage()));
			}
		}
		
		/*增加后处理逻辑，统一处理如@变量转换为p,v变量，<>错误常量等的值翻译*/
		try{
			skeleton.onFinish(runtimeConext);
		}catch(Exception e){
			if(!isAddResourceInfo){
				if(runtimeConext.get(IEngineContextConstant.CURR_RESOURCE)!=null){
					IARESResource resource = (IARESResource) runtimeConext.get(IEngineContextConstant.CURR_RESOURCE);

					IARESProblem problem = ARESProblem.createError();
					problem.setMessage("所在资源:"+resource.getFullyQualifiedName()+"\r\n");
					msgQueue.add(problem);
					isAddResourceInfo= true;
				}
			}
		}
		
		
		((Map)(context.get(IEngineContextConstant.ENGINE_EXCEPTION))).put(input, msgQueue);
		
		//处理警告信息:这里的警告不会在问题视图中出现
		for(IARESProblem item: listener.getARESProblem()){
			addMessageAttr(item, input, null);
			msgQueue.add(item);
		}
		
		if( level  <= GENERATE_LEVEL){  //仅做生成
			return buffer.toString();
		}
		
		StringBuffer realBuffer = new StringBuffer();
		//在代码头注释错误信息
		if(msgQueue.size() > 0){
			realBuffer.append("/***" + "\r\n");
			if(!isAddResourceInfo){
				if(runtimeConext.get(IEngineContextConstant.CURR_RESOURCE)!=null){
					IARESResource resource = (IARESResource) runtimeConext.get(IEngineContextConstant.CURR_RESOURCE);
					realBuffer.append("所在资源:"+resource.getFullyQualifiedName()+"\r\n");
					isAddResourceInfo= true;
				}
			}
			for(IARESProblem pItem: msgQueue){
				realBuffer.append(getCodeErrorMessage(pItem));
				realBuffer.append("\r\n");
			}
			realBuffer.append("***/" + "\r\n");
		}
		
		realBuffer.append(buffer);
		
		//是否有资源代码生成后需要返回给全局上下文的内容
		if(runtimeConext.containsKey(IEngineContextConstant.RETURN_CONTANT_TO_GLOBAL_CONTEXT)){
			context.put(IEngineContextConstant.RETURN_CONTANT_TO_GLOBAL_CONTEXT, runtimeConext.get(IEngineContextConstant.RETURN_CONTANT_TO_GLOBAL_CONTEXT));
		}
		
		return realBuffer.toString();
//		//代码格式化
//		if(UFTEngineTraceFlagHelper.getDebugTraceFlag()){
//			return realBuffer.toString();
//		}else{
//			if(skeleton.IsNeedFormat()){
//				return CodeFormater.formatC(realBuffer).toString();
//			}
//			return realBuffer.toString();
//		}
	}
	

	
	/**
	 * 处理标记
	 * @param tokenIterator
	 * @param buffer
	 * @param context
	 * @param map 
	 * @throws Exception
	 */
	private void handleToken(
			ISkeleton skeleton,
			ISkeletonInput input,
			Iterator<ICodeToken> tokenIterator,
			List<ICodeToken> queue,
			Map<Object, Object> context, 
			Queue<IARESProblem> msgQueue){
		if(tokenIterator == null)
		{
			return ;
		}
		boolean isAddResourceInfo = false;
		while (tokenIterator.hasNext()) {
			ICodeToken ttoken = (ICodeToken) tokenIterator.next();
			try{
				switch (ttoken.getType()) {
				case ICodeToken.CODE_TEXT:
					ICodeToken rtoken = skeleton.handleTextToken(ttoken, context);
					queue.add(rtoken);
					continue;
				case ICodeToken.COMMENT:
					queue.add(ttoken);
					continue;
				case ICodeToken.STRING:
					queue.add(ttoken);
					continue;
				case ICodeToken.PseudoCode:
					IPseudocodeParser parser = skeleton.getPseudocodeParser(ttoken, context);
					Iterator<ICodeToken> pIterator = parser.parse(StringUtils.defaultIfBlank(ttoken.getContent(), StringUtils.EMPTY), skeleton.getCommentType(),context);
					handleToken(skeleton,input,pIterator, queue, context,msgQueue);
					continue;
				case ICodeToken.MACRO:
					IMacroToken macro = (IMacroToken)ttoken;
					IMacroTokenHandler handler = skeleton.getMacroTokenHandler(macro, context);
					//迭代处理宏
					Iterator<ICodeToken> tIterator = handler.handle(macro, context);
					handleToken(skeleton,input,tIterator, queue, context,msgQueue);
					continue;
				default:
					break;
				}
			}catch (Exception e) {
				e.printStackTrace();
				
				//System.out.println(token1.getContent());
				if(!isAddResourceInfo){
					if(context.get(IEngineContextConstant.CURR_RESOURCE)!=null){
						IARESResource resource = (IARESResource) context.get(IEngineContextConstant.CURR_RESOURCE);

						IARESProblem problem = ARESProblem.createError();
						problem.setMessage("所在资源:"+resource.getFullyQualifiedName());
						msgQueue.add(problem);
						isAddResourceInfo= true;
					}
				}
				msgQueue.add(getTokenError(input,ttoken,e.getMessage()));
			}
		}
	}
	
//	/**
//	 * 过滤宏token
//	 * @param input
//	 * @param token
//	 * @param context
//	 * @throws Exception
//	 */
//	private void filteMacro(ISkeletonInput input,IMacroToken token,Map<Object, Object> context) throws Exception{
//		if(context.containsKey(IEngineContextConstant.MACRO_FILTER)){
//			IMacroTokenFilter filter = (IMacroTokenFilter)context.get(IEngineContextConstant.MACRO_FILTER);
//			if(!filter.filte(input, token)){
//				throw new Exception(String.format("宏[%s]不能适用于当前范围，请确认系统内置宏和系统用户宏的配置是否正确。", token.getKeyword()));
//			}
//		}
//	}
	
	
	/**
	 * 生成token的错误对象
	 * @param input
	 * @param token
	 * @param message
	 * @return
	 */
	private IARESProblem getTokenError(ISkeletonInput input,ICodeToken token,String message){
		IARESProblem problem = ARESProblem.createError();
		problem.setMessage(message);
		addMessageAttr(problem,input,token);
		return problem;
	}
	
	
	/**
	 * 添加属性到问题对象中
	 * @param problem
	 * @param input
	 * @param token
	 */
	private void addMessageAttr(IARESProblem problem,ISkeletonInput input,ICodeToken token){
		problem.setAttribute(IEngineContextConstant.MSG_ATTR_OWNER, token);
		if(token instanceof IMacroToken){
			problem.setAttribute("location", ((IMacroToken)token).getLineNo());
		}else{
			problem.setAttribute("location", 1);
		}
		problem.setAttribute(IEngineContextConstant.MSG_ATTR_INPUT, input);
	}
	
	/**
	 * 获取错误信息
	 * @param problem
	 * @return
	 */
	public String getCodeErrorMessage(IARESProblem problem){
		String msgType = "错误";
		if(!problem.isError()){
			msgType = "警告";
		}
		
		ICodeToken token = (ICodeToken)problem.getAttribute(IEngineContextConstant.MSG_ATTR_OWNER);
		IMacroToken macroToken = null;
		if(token instanceof IMacroToken){
			macroToken = (IMacroToken)token;
		}
		
		if(token instanceof IBranchNode ){
			if(((IBranchNode)token).getOwner() instanceof IMacroToken){
				macroToken = (IMacroToken)((IBranchNode)token).getOwner();
			}
		}
		if(null != macroToken){
			return String.format("%s：%d行 [%s] 详细信息:%s ",msgType, macroToken.getLineNo(),macroToken.getKeyword(),problem.getMessage());
		}
		if(token!=null){
			return String.format("%s：出错处[%s] 详细信息:%s \r\n",msgType,token.getClass().getSimpleName(),problem.getMessage());
		}
		return String.format("%s： 详细信息:%s \r\n",msgType,problem.getMessage());
	}
}
