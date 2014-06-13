/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.compiler.constant.DomainConstant;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.TokenDomain;
import com.hundsun.ares.studio.atom.compiler.token.SubFunctionCallToken;
import com.hundsun.ares.studio.atom.impl.AtomServiceImpl;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.biz.util.ParamGroupUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

/**
 * @author zhuyf
 *
 */
public class FunctionCallMacroHandler implements IMacroTokenHandler {
	
	String key;
	
	IARESResource resource;
	
	public FunctionCallMacroHandler(String key,IARESResource resource){
		this.key = key;
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 处理要求：
		 * 1.输入参数可以是常量，如：[AS_账户存管公用_资产账号获取][input_content = CNST_ACCTINTYPE_CLIENTID, account_content = @client_id]
		 * 不要将常量名作为内容传入
		 * 2.如果调用的原子的字段是输入输出字段，那么如果指定了该字段为某个变量，则，输入就用默认值，变量作为输出，例如
				[AS_账户存管公用_资产账户状态校验][client_id=@client_id_dest]
				client_id是输入输出字段，那么调用的时候client_id输入打包的时候就为空字符串，而输出是用@client_id_dest变量接收
				而且这个变量还能作为下一个原子的输入调用
		 * 
		 */
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstant.ATTR_FUNC_CALL,getKey());
		AtomFunction af = resource.getInfo(AtomFunction.class);
		validateParameter(context,af);//对象检验
		//对象号为空，用英文名代替
		String objectid = af.getObjectId();
		
		if(af instanceof AtomService && StringUtils.isBlank(af.getObjectId())){//如果调用的是as时进行objectid为空提示
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = "资源:"+af.getName()+"请设置功能号";
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
		}
		if(StringUtils.isBlank(objectid)){
			objectid = af.getName();
		}
		if(StringUtils.isBlank(objectid)){
			fireEventLessFunctionId(context);
		}
		
		//2014年4月14日13:45:16 秦元  如果函数调用有N标志，则不进行结果集替换
		if(!StringUtils.contains(token.getFlag(), "N")){
			//取得就近的结果集Id，凡是可以用lpResultSet->取就近结果集的，都需要加到这个列表中
			helper.addAttribute(IAtomEngineContextConstant.ATTR_GETLAST_RESULTSET,objectid);
		}
		boolean returnResultSet = af.isOutputCollection();
		//if(returnResultSet){
		//由于如果返回结果集，调用返回结果集的AF以及[通用SELECT]都可以直接打包输出
		helper.addAttribute(IAtomEngineContextConstant.ATTR_FUNC_RESULTSET,objectid);
		//}
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		//普通变量列表中添加error_no,error_info,error_patchinfo,error_pathinfo_tmp
		popVarList.add("error_no");
		popVarList.add("error_info");
		popVarList.add("error_pathinfo");
		popVarList.add("error_pathinfo_tmp");
		if(af.isOutputCollection()){
			addDomain(context, objectid);
		}
		if(token.getParameters().length>0){
			addPopVarList(context,token);
		}
		List<ICodeToken> tokens = new ArrayList<ICodeToken>();
		SubFunctionCallToken subFuncCalltoken = new SubFunctionCallToken(token, resource);
		tokens.add(subFuncCalltoken);
		return tokens.iterator();
	}
	
	  /**
	   * 添加变量
	   * @param context
	   * @param token
	   */
	  private void addPopVarList(Map<Object, Object> context,IMacroToken token){
		  List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		  ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
			if(token.getParameters().length>0){
			Map<String,String>  defaultValueMap = PseudoCodeParser.parserKeyValueWithAt(token.getParameters()[0]);
			for(String paramKey: defaultValueMap.keySet()){
				String valueVarName =defaultValueMap.get(paramKey);
				if (valueVarName.indexOf("@") >= 0) {// 如果默认参数值为变量
					String procVarName = valueVarName.substring(valueVarName.indexOf("@") + 1);
					Parameter parameter = getParameterInOutPutParameter(paramKey);
					if(parameter!=null && parameter.getParamType().getValue()!=ParamType.OBJECT_VALUE){
						popVarList.add(procVarName);
						} else if (parameter!=null && parameter.getParamType().getValue() == ParamType.OBJECT_VALUE) {
							helper.addAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_NOINIT_VARIABLE_LIST,paramKey);
						}
					}
				}
			}
		  
	  }
	
	/**
	 * 根据id返回输入参数是与id相同的参数
	 * 
	 * @param id
	 * @return
	 */
	public Parameter getParameterInOutPutParameter(String id){
			AtomFunction atomFunction = (AtomFunction) resource.getAdapter(AtomFunction.class);
			List<Parameter> outputParameters = new ArrayList<Parameter>();
			ParamGroupUtil.parserParameters(atomFunction.getOutputParameters(), outputParameters, resource.getARESProject());
			for(Parameter parameter:outputParameters){
				if(StringUtils.equals(parameter.getId(),id)){
					return parameter;
				}
			}
			return null;
		}
	/**
	 * 添加域
	 */
	private void addDomain(Map<Object, Object> context,String objectId){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		String []args = new String[1];
		 args[0] = objectId;
		handler.addDomain(new TokenDomain(DomainConstant.FUNC_RESULT_OBJECT_RETURN_DOMAIN,args));
	}
	
	/**
	 * 校验参数:这里只检验对象资源是否存在
	 */
	protected void validateParameter(Map<Object, Object> context,AtomFunction af){
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.addAll(af.getInputParameters());
		parameters.addAll(af.getOutputParameters());
		parameters.addAll(af.getInternalVariables());
		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		for(Parameter parameter:parameters){
			if(parameter.getParamType() == ParamType.OBJECT || parameter.getParamType() == ParamType.PARAM_GROUP){
				if(BizUtil.getObject(parameter, project)==null){
					if(af.getInputParameters().contains(parameter)){
						String message = "输入参数:"+parameter.getComments()+"对应的对象资源"+parameter.getType()+"不存在!";
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
					}else if(af.getOutputParameters().contains(parameter)){
						String message = "输出参数:"+parameter.getComments()+"对应的对象资源"+parameter.getType()+"不存在!";
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
					}else if(af.getInternalVariables().contains(parameter)){
						String message = "内部变量:"+parameter.getComments()+"对应的对象资源"+parameter.getType()+"不存在!";
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
					}
				}
			}
		}
		
	}
	/**
	 * 缺少功能号
	 * @param context
	 */
	private void fireEventLessFunctionId(Map<Object, Object> context){
		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = "请设置被调用函数或者服务功能号";
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	
	}

}
