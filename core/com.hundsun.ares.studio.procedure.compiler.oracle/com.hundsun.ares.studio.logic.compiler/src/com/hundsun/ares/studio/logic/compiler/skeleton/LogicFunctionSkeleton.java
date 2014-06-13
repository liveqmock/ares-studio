/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.skeleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.MacroHandlerFactory;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.ParamGroupUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenFilter;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonInput;
import com.hundsun.ares.studio.engin.token.CodeTokenFactory;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;
import com.hundsun.ares.studio.logic.compiler.macro.service.IAtomServiceMacroTonkenService;
import com.hundsun.ares.studio.logic.compiler.macro.service.ILogicFunctionMacroTokenService;
import com.hundsun.ares.studio.logic.compiler.tokens.LogicFunctionIsTransFunctionBeginToken;
import com.hundsun.ares.studio.logic.compiler.tokens.LogicFunctionIsTransFunctionEndToken;
import com.hundsun.ares.studio.logic.compiler.tokens.LogicFunctionPackerReleseToken;
import com.hundsun.ares.studio.logic.compiler.tokens.LogicFunctionSvrEndToken;
import com.hundsun.ares.studio.logic.compiler.tokens.LogicFunctionVariableDefineToken;
import com.hundsun.ares.studio.logic.compiler.tokens.LogicServiceBeginToken;
import com.hundsun.ares.studio.logic.compiler.tokens.LogicServiceEndToken;
import com.hundsun.ares.studio.logic.compiler.tokens.LogicServiceObjectReleseToken;
import com.hundsun.ares.studio.logic.compiler.tokens.LogicSubCallWithMFlagToken;
import com.hundsun.ares.studio.usermacro.compiler.handlers.IUserMacroTokenService;

/**
 * @author qinyuan
 *
 */
public class LogicFunctionSkeleton extends LogicBusinessSkeleton {

	/**
	 * @param resource
	 * @param context
	 */
	public LogicFunctionSkeleton(IARESResource resource,
			Map<Object, Object> context) {
		super(resource, context);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#getSkeletonToken()
	 */
	@Override
	public Iterator<ICodeToken> getSkeletonToken() throws Exception {
		
		List<ICodeToken> tList = new ArrayList<ICodeToken>();
		
		LogicFunction logicFuc = (LogicFunction)getRuntimeContext().get(ILogicEngineContextConstant.ResourceModel);
		
		//添加token处理
		tList.add(new LogicServiceBeginToken());//函数创建
		// 变量声明
		tList.add(new LogicFunctionVariableDefineToken());
		//子函数调用如果存在<M>标志，需特殊处理
		tList.add(new LogicSubCallWithMFlagToken());
		//LS增加许可证检查
//		tList.add(new LogicServiceCheckLicenseToken());
		
		//LF为事务函数处理开始
		tList.add(new LogicFunctionIsTransFunctionBeginToken());
		//伪代码
		tList.add(CodeTokenFactory.getInstance().createPseudoCodeToken(logicFuc.getPseudoCode()));
		//LF为事务函数处理结束
		tList.add(new LogicFunctionIsTransFunctionEndToken());
		//Svr_end处理
		tList.add(new LogicFunctionSvrEndToken());
		//打包器释放处理
		tList.add(new LogicFunctionPackerReleseToken());
		//释放业务对象
		tList.add(new LogicServiceObjectReleseToken());
		//函数结束处理：returnCode
		tList.add(new LogicServiceEndToken());
		
		return tList.iterator();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#getMacroTokenHandler(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public IMacroTokenHandler getMacroTokenHandler(IMacroToken macro,
			Map<Object, Object> context) throws Exception {
		IMacroTokenHandler  handler = null;//MacroHandlerFactory.getInstance().create(macro.getKeyword());
		
		int macroType = -1;//默认为未匹配的宏
		//函数宏处理
		if(context.containsKey(ILogicEngineContextConstant.Logic_Function_Macro_Service)){
			ILogicFunctionMacroTokenService fmservice = (ILogicFunctionMacroTokenService)context.get(ILogicEngineContextConstant.Logic_Function_Macro_Service);
			if(fmservice.isLogicFunction(macro.getKeyword())){
				macroType = 1;//原子函数调用
				handler = fmservice.getHandler(macro.getKeyword());
			}
		}
		
		//原子服务宏处理
		if(context.containsKey(ILogicEngineContextConstant.Atom_Service_Macro_Service)){
			IAtomServiceMacroTonkenService prcservice = (IAtomServiceMacroTonkenService)context.get(ILogicEngineContextConstant.Atom_Service_Macro_Service);
			if(prcservice.isAtomService(macro.getKeyword())){
				macroType = 2;//原子服务调用
				handler = prcservice.getHandler(macro.getKeyword());
			}
		}
		
		//用户自定义宏处理
		if(macroType < 0 && context.containsKey(ILogicEngineContextConstant.UserMacro_Service)){
			IUserMacroTokenService fmservice = (IUserMacroTokenService)context.get(ILogicEngineContextConstant.UserMacro_Service);
			if(fmservice.isUserMacro(macro.getKeyword())){
				macroType = 3;
				List<String> params = new ArrayList<String>();
				{
					IARESProject project = (IARESProject)context.get(IAtomEngineContextConstant.Aresproject);
					LogicFunction lf = (LogicFunction) context.get(ILogicEngineContextConstant.ResourceModel);
					List<Parameter> inputParameters = new ArrayList<Parameter>();
					ParamGroupUtil.parserParameters(lf.getInputParameters(), inputParameters, project);
					for(Parameter input : inputParameters){
						params.add(input.getId());
					}
					List<Parameter> outputParameters = new ArrayList<Parameter>();
					ParamGroupUtil.parserParameters(lf.getOutputParameters(), outputParameters, project);
					for (Parameter ouput : outputParameters) {
						params.add(ouput.getId());
					}
				}
				ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(ILogicEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
				Set<String> rsIdSet = helper.getAttribute(ILogicEngineContextConstant.ATTR_GETLAST_RESULTSET);
				String lastId = "";
				if(rsIdSet.size() > 0){
					lastId = (String)rsIdSet.toArray()[rsIdSet.size() - 1];
				}
				
				Map<String,Object> paramsMap= new HashMap<String,Object>();
				paramsMap.put("dataRealType", new HashMap<String, String>());
				paramsMap.put("lastCur", "");
				paramsMap.put("lastResId", lastId);
				paramsMap.put("inoutParams", params);
				handler = fmservice.getUserMacroHandler(macro.getKeyword(),context ,paramsMap);
			}
		}
		
		//内部宏处理 
		if(macroType < 0 ){
			IMacroTokenHandler tempHandler = MacroHandlerFactory.getInstance().create(macro.getKeyword());//MacroTokenHandlerManager.getInstance().getHandler(macro.getKeyword());
			if(null != tempHandler){
				macroType = 4;
				handler = tempHandler;
			}
		}
		/*从上下文中取出ISkeletonInput，在引擎调用String generate(ISkeletonInput input,Queue<IARESProblem> msgQueue,
		  int level,Map<Object, Object> context)时塞入*/
		ISkeletonInput input = (ISkeletonInput)context.get(IEngineContextConstant.SKELETON_INPUT);
		IMacroTokenFilter filter = (IMacroTokenFilter)context.get(ILogicEngineContextConstant.MACRO_FILTER);
		
		switch (macroType) {
		case -1:
			throw new Exception(String.format("没有找到宏[%s]相关的处理类。请确认宏文本书写正确。", macro.getKeyword()));
		case 1:   //函数
			break;
		case 2:   //存储过程
			break;
		case 3:   //自定义宏
			if(!filter.filte(input, macro)){
				throw new Exception(String.format("过滤器禁用宏[%s]相关的处理类。请确认用户自定义宏范围是否正确。", macro.getKeyword()));
			}
			break;
		case 4:  //系统宏
			if(!filter.filte(input, macro)){
				throw new Exception(String.format("过滤器禁用宏[%s]相关的处理类。请确认系统内置宏范围是否正确。", macro.getKeyword()));
			}
			break;
		default:
			break;
		}
		
		if(null != handler){
			return handler;
		}else{
			throw new Exception(String.format("没有找到宏[%s]相关的处理类。请确认宏文本书写正确。", macro.getKeyword()));
		}
	}
}
