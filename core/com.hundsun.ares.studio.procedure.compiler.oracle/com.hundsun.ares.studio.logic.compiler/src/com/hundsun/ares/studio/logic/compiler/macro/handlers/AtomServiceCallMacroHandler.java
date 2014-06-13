/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.macro.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.compiler.constant.DomainConstant;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.TokenDomain;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.biz.util.BizInterfaceParameterUtil;
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
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.LogicService;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;
import com.hundsun.ares.studio.logic.compiler.tokens.AtomServiceCallToken;

/**
 * 原子服务调用宏处理
 * 注意逻辑服务调用原子服务与逻辑函数调用原子服务的区别
 * 参考恒生开发工具原子服务调用宏处理类：
 * (LS->AS):CallAtomicServiceInterpreter;(LF->AS):CallAtomicServiceUseVariableOnlyInterpreter
 * @author liaogc
 *
 */
public class AtomServiceCallMacroHandler implements IMacroTokenHandler {
	
	private String key;
	private IARESResource resource;

	/**
	 * @param chineseName
	 * @param resource
	 */
	public AtomServiceCallMacroHandler(String chineseName,
			IARESResource resource) {
		this.key = chineseName;
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return key;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		IARESProject project = (IARESProject) context.get(ILogicEngineContextConstant.Aresproject);
		AtomFunction logic =  (AtomFunction) context.get(ILogicEngineContextConstant.ResourceModel);
		Map<String, String> defaultValueList = getDefaultValuseList(token);//解析默认值列表
		AtomService atomService = (AtomService) resource.getAdapter(AtomFunction.class);//获取被调用资源模型
		List<Parameter> inParameters = getInParameters(atomService,context);//获取被调用输入参数
		Set<String> inList = getInList(inParameters, defaultValueList, atomService,project);//输入重定向列表
		Set<Parameter> outList = getOutList(defaultValueList,atomService,logic,project);//输出重定向列参数名列表
		
		Map<String,String> resultsetParameters = getResultsetParameters(context);//结果集字段(不能在形成代码是获得)
		//对象校验
		validateParameter(context, logic);
		
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(ILogicEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(ILogicEngineContextConstant.ATTR_ATOM_SERVICE_CALL,getKey());
		
		//调用方加入到结果集变量声明中去
		if(logic.isOutputCollection()){
			helper.addAttribute(ILogicEngineContextConstant.ATTR_RESULTSET_LIST,logic.getObjectId());
			if(StringUtils.isBlank(logic.getObjectId())){
				fireEventLessFunctionId(context,logic.getName());
			}
		}
		//被调用的加入到结果集变量声明中
		helper.addAttribute(ILogicEngineContextConstant.ATTR_RESULTSET_LIST,atomService.getObjectId());
		if(StringUtils.isBlank(atomService.getObjectId())){
			fireEventLessFunctionId(context,atomService.getName());
		}
		//2014年4月14日13:45:16 秦元  如果函数调用有N标志，则不进行结果集替换
		if(!StringUtils.contains(token.getFlag(), "N")){
			//取得就近的结果集Id，凡是可以用lpResultSet->取就近结果集的，都需要加到这个列表中
			helper.addAttribute(ILogicEngineContextConstant.ATTR_GETLAST_RESULTSET,atomService.getObjectId());
		}
		
		/**
		 * 添加输出重定现到变量列表中
		 */
		addOutParameterToPopVarList(outList,context);
		if(token.getParameters().length>0){
			addPopVarList(context,token);
		}
		if(atomService.isOutputCollection()){//把对象号添加到域中
			addDomain(context, atomService.getObjectId());
		}
		List<ICodeToken> tokens = new ArrayList<ICodeToken>(1);
		tokens.add(new AtomServiceCallToken( logic, atomService, defaultValueList, inParameters , inList ,outList,resultsetParameters,resource,token, context));
		addOutParameters(atomService,context);//把被调用的输出参数添加取最新结果集列表中去
		if(StringUtils.isBlank(atomService.getObjectId())){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = "资源:"+atomService.getName()+"请设置功能号";
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
		}
		return tokens.iterator();
	}
	
	/**
	 * 把输出中的字段添加到输出结果集中
	 * @param atomService
	 * @param context
	 */
  private void addOutParameters(AtomService atomService,Map<Object, Object> context){
	  Map<String, String> resultParameters =(Map<String, String>) context.get(ILogicEngineContextConstant.ATTR_RESULTSET_PARAMETER);
	  IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
	  for(Parameter parameter:atomService.getOutputParameters()){
		  if(parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE){
			  List<Parameter> parameters = new ArrayList<Parameter>();
			  ParamGroupUtil.parserParamGroup(parameter, parameters,1, project);
			  for(Parameter p:parameters){
				  resultParameters.put(p.getId(), atomService.getObjectId()); 
			  }
		  }else{
			  resultParameters.put(parameter.getId(), atomService.getObjectId()); 
		  }
		 
		}

	}
  
  /**
   * 添加输出重定向到变量列表中
   * @param context
   * @param token
   */
  private void addOutParameterToPopVarList(Set<Parameter> outList,Map<Object, Object> context){
	  List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		for(Parameter parameter:outList){
			if(parameter.getParamType().getValue()!= ParamType.PARAM_GROUP_VALUE && parameter.getParamType().getValue()!= ParamType.OBJECT_VALUE){
				popVarList.add(parameter.getId());
			}
			
		}
  }
	
  /**
   * 添加变量
   * @param context
   * @param token
   */
  private void addPopVarList(Map<Object, Object> context,IMacroToken token){
	  List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
	  ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(ILogicEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
	  List<String> popObjectVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_OBJECT_PARA_LIST);
	  AtomService atomService = (AtomService) resource.getAdapter(AtomFunction.class);
		if(token.getParameters().length>0){
		Map<String,String>  defaultValueMap = PseudoCodeParser.parserKeyValueWithAt(token.getParameters()[0]);
		for(String paramKey: defaultValueMap.keySet()){
			String valueVarName =defaultValueMap.get(paramKey);
			if (valueVarName.indexOf("@") >= 0) {// 如果默认参数值为变量
				String procVarName = valueVarName.substring(valueVarName.indexOf("@") + 1);
				Parameter parameter = getParameterInOutPutParameter(paramKey);
				if(parameter!=null && parameter.getParamType().getValue()!=ParamType.OBJECT_VALUE){
					} else if (parameter!=null && parameter.getParamType().getValue() == ParamType.OBJECT_VALUE) {
						helper.addAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_NOINIT_VARIABLE_LIST,paramKey);
						popObjectVarList.add(procVarName);//加入到对象伪代码列表中
						popVarList.remove(procVarName);//从普通伪代码列表 中删除
					}
				}
			}
		}
		List<String> vars = new ArrayList<String>(4);//固定要加的代码
		
		  for(String var:vars){
				popVarList.add(var);
		  }
		  AtomFunction logic =  (AtomFunction) context.get(ILogicEngineContextConstant.ResourceModel);
		  if(logic instanceof LogicFunction){
				if(((LogicFunction)logic).isIsTransFunc()){
					//helper.addAttribute(ILogicEngineContextConstant.ATTR_PROC_VARIABLE_LIST,"cancel_serialno");
					popVarList.add("cancel_serialno");
				}
			}
	  
  }
	
	/**
	 * 获取默认值列表
	 * @param token
	 * @return
	 */
	private Map<String,String> getDefaultValuseList(IMacroToken token){
		if(token.getParameters().length>0){
			return PseudoCodeParser.parserKeyValueWithAt(token.getParameters()[0]);
		}
		return  new Hashtable<String, String>();
	}
	
	
	/**
	 * 
	 * @return 返回输入重定现列表
	 */
	private Set<String> getInList(List<Parameter> inParameters,Map<String, String> defaultValueList,AtomService atomService,IARESProject project){
		Set<String> inList = new HashSet<String>();
		for(Parameter parameter:inParameters){

			boolean io = false;
			
			if(BizInterfaceParameterUtil.isOutputParameter(atomService, parameter.getId(),project)){//如果在输出中
				io = true;
			}
			if (defaultValueList.containsKey(parameter.getId())) {
				// 被用户重新指定
				String text = defaultValueList.get(parameter.getId());
				if(text.startsWith("@") && io){//是输出重定向
					continue;
				}
				
				inList.add(parameter.getId());
			}
		
		}
		
		
		return inList;
		
	}
	
	/**
	 * 
	 * @return 返回输出重定现列表
	 */
	private Set<Parameter> getOutList(Map<String, String> defaultValueList,AtomService atomService,AtomFunction logic,IARESProject project){
		Set<Parameter> outList = new HashSet<Parameter>();
		List<Parameter> parameters = new ArrayList<Parameter>();
		for(Parameter parameter:atomService.getOutputParameters()){
			if(parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE){
				List<Parameter> groupParameters = new ArrayList<Parameter>();
				ParamGroupUtil.parserParamGroup(parameter, groupParameters,1, project);
				parameters.addAll(parameters);
			}else{
				parameters.add(parameter);
			}
		}
		for(Parameter parameter:parameters){

			boolean io = false;
			if(parameter.getFlags() != null && parameter.getFlags().indexOf("IO") >= 0){
				io = true;
			}
			if (defaultValueList.containsKey(parameter.getId()) || defaultValueList.containsKey("@" + parameter.getId())) {
				String s = defaultValueList.get(parameter.getId());
				if (s == null) {
					s = defaultValueList.get("@" + parameter.getId());
					if (s != null) {
						//指定输出参数赋值时，左边是字段，不应该以@开头
						
					}
				}
				
				if (s != null) {
					if (!s.startsWith("@")) {
						//如果是IO参数，当右边是变量，那还是作为输入重定向
						if(io){
							continue;
						}
						//指定输出参数赋值时，等号右边一定需要是变量，所以必定要@开头;
						s = "@" + s;
					}
					//同名参数在LS层不应该作为输出 4.23 周伟民
					if(logic instanceof LogicService){
						if(parameter.getId().trim().equals(s.substring(1).trim())){
							//同名变量在LS层不应该作为输出";
							continue;
						}
					}
					
					outList.add(parameter);
				}

			}
		
		}
		
		
		return outList;
		
	}
	
	
	
	
	/**
	 * 获取被调用函数所以的输入参数
	 * @return
	 */
	
	private List<Parameter> getInParameters(BizInterface bizInterface,Map<Object, Object> context){
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
		List<Parameter> inParameters = new ArrayList<Parameter>();
		for (Parameter parameter :bizInterface.getInputParameters()) {
			if(parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){
				List<Parameter> parameters = new ArrayList<Parameter>();
				ParamGroupUtil.parserParamGroup(parameter,parameters,1,project);
				inParameters.addAll(parameters);
			}else{
				inParameters.add(parameter);
			}
			
		}
		
		for ( Parameter parameter :bizInterface.getOutputParameters()) {
			if (StringUtils.defaultIfBlank(parameter.getFlags(), "").indexOf("IO") != -1) {
				if(parameter.getParamType().getValue()==ParamType.PARAM_GROUP_VALUE ){
					List<Parameter> parameters = new ArrayList<Parameter>();
					ParamGroupUtil.parserParamGroup(parameter,parameters,1,project);
					inParameters.addAll(parameters);
				}else{
					inParameters.add(parameter);
				}
			}
		}
		
		return inParameters;
	}
	
	/**
	 * 获取结果中的参数
	 * @return
	 */
	private Map<String,String> getResultsetParameters(Map<Object, Object> context){
		//一定要重新复制一份不然可以取到后面的结果集变量
		return  new HashMap<String,String>((Map<String, String>) context.get(ILogicEngineContextConstant.ATTR_RESULTSET_PARAMETER));
	}
	/**
	 * 根据id返回输入参数是与id相同的参数
	 * @param id
	 * @return
	 */
	public Parameter getParameterInOutPutParameter(String id){
		AtomService atomService = (AtomService) resource.getAdapter(AtomFunction.class);
		List<Parameter> outputParameters = new ArrayList<Parameter>();
		ParamGroupUtil.parserParameters(atomService.getOutputParameters(), outputParameters, resource.getARESProject());
		for(Parameter parameter:outputParameters){
			if(StringUtils.equals(parameter.getId(),id)){
				return parameter;
			}
		}
		return null;
	}
	
	/**
	 * 获取标准字段参数信息
	 * @param name
	 * @return
	 */
	private Map<String,String> getStandardFieldParameterInfo( String name,IARESProject project){
		Map<String,String> parameterInfo = new HashMap<String,String>();
			StandardDataType stdType = null;
			try {
				stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(project, name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			TypeDefaultValue typpeDefValue = null;
			try {
				typpeDefValue = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName(project, name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			BusinessDataType busType = null;
			try {
				busType = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(project, name);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
			if((stdType != null) && ( typpeDefValue!= null) && ( busType!= null))//标准字段
			{
				String dataType = stdType.getValue(MetadataServiceProvider.C_TYPE);
				String defValue = typpeDefValue.getValue(MetadataServiceProvider.C_TYPE);
				String  length = StringUtils.defaultIfBlank(busType.getLength(), "0");
				String precision =StringUtils.defaultIfBlank(busType.getPrecision(),"0");
				dataType = dataType.replace("$L", length);
				parameterInfo.put("type", dataType);
				parameterInfo.put("value", defValue);
				parameterInfo.put("length", length);
				parameterInfo.put("precision", precision);
			
			}
		
		return parameterInfo;
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
	 * 缺少功能号
	 * @param context
	 */
	private void fireEventLessFunctionId(Map<Object, Object> context,String resoureceName){
		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = resoureceName+"请设置功能号";
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
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
}
