package com.hundsun.ares.studio.atom.compiler.skeleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.macro.filter.DefaultMacroTokenFilter;
import com.hundsun.ares.studio.atom.compiler.macro.func.FunctionMacroTokenService;
import com.hundsun.ares.studio.atom.compiler.macro.procedure.ProcedureMacroTokenService;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.DefaultSkeletonAttributeHelper;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.ParamDefineHelper;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.ParamReplaceUtil;
import com.hundsun.ares.studio.atom.compiler.token.PROCPoxyCodeToken;
import com.hundsun.ares.studio.atom.compiler.token.ResultSetProxyToken;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.IResStatisticProvider;
import com.hundsun.ares.studio.core.context.JRESContextManager;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.skeleton.ISkeleton;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.DomainHandler;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.IResultSetToken;
import com.hundsun.ares.studio.engin.token.ITextWithParamsToken;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.usermacro.compiler.handlers.UserMacroTokenService;

public abstract class BussinessSkeleton implements ISkeleton{

	IARESResource resource;
	Map<Object, Object> context = new HashMap<Object, Object>();
	
	public BussinessSkeleton(IARESResource resource,Map<Object, Object> context){
		this.resource = resource;
		if(!this.context.containsKey(IAtomEngineContextConstant.Aresproject)){
			this.context.put(IAtomEngineContextConstant.Aresproject, resource.getARESProject());
		}
		if(!this.context.containsKey(IEngineContextConstant.CURR_RESOURCE)){
			this.context.put(IEngineContextConstant.CURR_RESOURCE, resource);
		}
		if(!this.context.containsKey(IAtomEngineContextConstant.MACRO_FILTER)){
			this.context.put(IAtomEngineContextConstant.MACRO_FILTER, new DefaultMacroTokenFilter(resource.getARESProject()));
		}
//		if(!this.context.containsKey(IAtomEngineContextConstant.Statistic_Provider)){
//			this.context.put(IAtomEngineContextConstant.Statistic_Provider, JRESContextManager.getStatisticProvider(resource.getARESProject()));
//		}
		if(!this.context.containsKey(IAtomEngineContextConstant.Function_Macro_Service)){
			this.context.put(IAtomEngineContextConstant.Function_Macro_Service, new FunctionMacroTokenService(resource.getARESProject()));
		}
		if(!this.context.containsKey(IAtomEngineContextConstant.UserMacro_Service)){
			this.context.put(IAtomEngineContextConstant.UserMacro_Service, new UserMacroTokenService(resource.getARESProject() ,false));
		}
		if(!this.context.containsKey(IAtomEngineContextConstant.Procedure_Macro_Service)){
			this.context.put(IAtomEngineContextConstant.Procedure_Macro_Service, new ProcedureMacroTokenService(resource.getARESProject()));
		}
		//全局上下文加入到运行时上下文
		this.context.putAll(context);
		if(!this.context.containsKey(IEngineContextConstant.DOMAIN_HANDLER)){
			IDomainHandler domainHandler = new DomainHandler();
			this.context.put(IEngineContextConstant.DOMAIN_HANDLER, domainHandler);
		}
		if(!this.context.containsKey(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER)){
			ISkeletonAttributeHelper helper = new DefaultSkeletonAttributeHelper();
			this.context.put(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER,helper );
		}
		//如果上下文中没有模型，从资源读取模型
		if(!this.context.containsKey(IAtomEngineContextConstant.ResourceModel)){
			try {
				this.context.put(IAtomEngineContextConstant.ResourceModel, resource.getInfo(EObject.class));
			} catch (ARESModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!this.context.containsKey(IAtomEngineContextConstant.PARAM_DEFINE_HELPER)){
			this.context.put(IAtomEngineContextConstant.PARAM_DEFINE_HELPER, new ParamDefineHelper());
		}
	}
	
	@Override
	public Map<Object, Object> getRuntimeContext() throws Exception {
		//这里只取上下文，初始化在构造函数中完成。
		return this.context;
	}


	@Override
	public Object[] postValidate(){
		List<String> tlist = new ArrayList<String>();
		IDomainHandler domainHandler = (IDomainHandler)context.get(IEngineContextConstant.DOMAIN_HANDLER);
		//域检查
		if(domainHandler.getDomains().length > 0){
			for(ITokenDomain item:domainHandler.getDomains()){
				if(ITokenDomain.GLOABL.equals(item.getType())){
					tlist.add(String.format("宏[%s]的域没有结束。", item.getKey()));
				}
			}
		}
		validateParameter();
		return tlist.toArray();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#handleTextToken(com.hundsun.ares.studio.engin.token.ICodeToken, java.util.Map)
	 */
	@Override
	public ICodeToken handleTextToken(ICodeToken token, Map<Object, Object> context)	throws Exception {
		boolean inBlock = false;  //是否在proc语句块当中
		IDomainHandler handler = (IDomainHandler)context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain domain = handler.getDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
		if(null == domain){
			inBlock = false;
		}else{
			inBlock = true;
		}

		//添加普通文本中的proc变量列表
		if((token instanceof ITextWithParamsToken) && inBlock){
			ITextWithParamsToken txtToken = (ITextWithParamsToken)token;
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
			for(String paramName : txtToken.getUsedParams()){
				helper.addAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST, paramName);
			}
			//如果在proc块当中，使用代理
			return new PROCPoxyCodeToken(token);
		}else if(token instanceof IResultSetToken){
			//ResultSet->肯定不是在Proc语句块中使用，故肯定不需要是TextWithParamsToken
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
			Set<String> rsIdSet = helper.getAttribute(IAtomEngineContextConstant.ATTR_GETLAST_RESULTSET);
			String lastId = "";
			if(rsIdSet.size() > 0){
				lastId = (String)rsIdSet.toArray()[rsIdSet.size() - 1];
			}
			return new ResultSetProxyToken(token,lastId);
		}else{
			//如果不在proc块当中，直接使用原来的token
			return token;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#onFinish(java.util.Map)
	 */
	@Override
	public void onFinish(Map<Object, Object> context) throws Exception {
		// 参考UFT的ParaHandleToken完成对@变量的处理，以及<>错误号与用户常量的处理
		List<String> tlist = (List<String>) context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		if(tlist == null){
			return;
		}
		StringBuffer buffer = (StringBuffer)context.get(IEngineContextConstant.BUFFER);
		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		//IResStatisticProvider provider = (IResStatisticProvider)context.get(IAtomEngineContextConstant.Statistic_Provider);
		Set<String> inoutParamList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OUT_PARAM_LIST);
		
		List<String> popObjectVarList = (List<String>) context.get(IEngineContextConstant.PSEUDO_CODE_OBJECT_PARA_LIST);
		//对特殊的变量的处理
		String specialResult = ParamReplaceUtil.handleSpecialParams("", buffer.toString(), getSpecialsParams());
		//对象变量替换
		String objStrResult = ParamReplaceUtil.handleObjectParams("", specialResult, popObjectVarList);
		//替换普通的标准字段
		String result = ParamReplaceUtil.handleParams(objStrResult, tlist.toArray(new String[0]), inoutParamList);
		
		
		if(result.toString().indexOf("<") == -1 || result.toString().indexOf(">") == -1){
			//不包含常量
		}else{
			//解析所有常量
			List<String> constantList =  ParamReplaceUtil.findConstAll(result);
			//替换所有常量
			IARESProject aresProject = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
			if (aresProject != null) {
				for(String constant:constantList){
					try {
						result = handleConstantReplace(result, constant, aresProject);
					} catch (Exception e) {
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,e.getMessage()));
					}
				}
			}
		}
		
		
		buffer.setLength(0);
		buffer.append(result);
	}
	
	/**
	 * 处理常量替换
	 * @param content
	 * @param variableName
	 * @param provider
	 * @return
	 * @throws Exception
	 */
	private String handleConstantReplace(String content,String variableName, IARESProject project) throws Exception{
		//去掉<>取得常量名
		String paraName =variableName.trim().substring(variableName.trim().indexOf("<")+1,variableName.trim().indexOf(">"));
		String definitionCode = null;
		
		ReferenceManager refManager = ReferenceManager.getInstance();
		//替换错误号
		ReferenceInfo ref = refManager.getFirstReferenceInfo(project, IMetadataRefType.ErrNo, paraName, true);
		if (ref == null) {
			// 常量
			ref = refManager.getFirstReferenceInfo(project, IMetadataRefType.Const, paraName, true);
		}

		//////////////////////////////////////待确认/////////////////////////////////////////
//		//替换功能号
//		objs = provider.getResouce(paraName, IResourceTable.Scope_IGNORE_NAMESPACE, IMetadataRefType.Menu_Function);
//		if(objs.length > 0){
//			Map<Object, Object> tmap= (Map<Object, Object>) objs[0];
//			Function item = (Function) tmap.get(IResourceTable.TARGET_OWNER);
//			definitionCode =  item.getName();
//		}
//		
//		//替换菜单
//		objs = provider.getResouce(paraName, IResourceTable.Scope_IGNORE_NAMESPACE, IMetadataRefType.Menu);
//		if(objs.length > 0){
//			Map<Object, Object> tmap= (Map<Object, Object>) objs[0];
//			MenuItem item = (MenuItem) tmap.get(IResourceTable.TARGET_OWNER);
//			definitionCode =  item.getRefId();
//		}
     //////////////////////////////////////待确认/////////////////////////////////////////
		
		if (ref != null) {
			definitionCode = ref.getRefName();
		}
		
		//没有找到相关定义
		if(null == definitionCode){
			throw new Exception(String.format("未找到的常量%s的相关定义", paraName));
		}
		
		//过程里要替换掉“"”
		definitionCode = definitionCode.replaceAll("\"", "'");
		//用常量的实际值替换常量名
		return ParamReplaceUtil.replaceContentWithNotString(content,variableName,definitionCode);	
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#getCommentType()
	 */
	@Override
	public int getCommentType() {
		return PseudoCodeParser.CPP_TYPE;
	}
	
	/**
	 * 获得特殊的变量,以便变量替换时进行特殊的变量替换
	 * @return
	 */
	private List<String> getSpecialsParams(){
		List<String> specialsParams = new ArrayList<String>();
		specialsParams.add("lpContext");
		specialsParams.add("lpInUnPacker");
		specialsParams.add("lpOutPacker");
		
		return specialsParams;
		
	}
	
	/**
	 * 校验参数:这里只检验对象资源是否存在
	 */
	protected void validateParameter(){
		AtomFunction logic  = (AtomFunction)context.get(IAtomEngineContextConstant.ResourceModel);
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.addAll(logic.getInputParameters());
		parameters.addAll(logic.getOutputParameters());
		parameters.addAll(logic.getInternalVariables());
		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		for(Parameter parameter:parameters){
			if(parameter.getParamType() == ParamType.OBJECT || parameter.getParamType() == ParamType.PARAM_GROUP){
				if(BizUtil.getObject(parameter, project)==null){
					if(logic.getInputParameters().contains(parameter)){
						String message = "输入参数:"+parameter.getComments()+"对应的对象资源"+parameter.getType()+"不存在!";
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
					}else if(logic.getOutputParameters().contains(parameter)){
						String message = "输出参数:"+parameter.getComments()+"对应的对象资源"+parameter.getType()+"不存在!";
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
					}else if(logic.getInternalVariables().contains(parameter)){
						String message = "内部变量:"+parameter.getComments()+"对应的对象资源"+parameter.getType()+"不存在!";
						manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
					}
				}
			}
		}
		
	}
	
	
}
