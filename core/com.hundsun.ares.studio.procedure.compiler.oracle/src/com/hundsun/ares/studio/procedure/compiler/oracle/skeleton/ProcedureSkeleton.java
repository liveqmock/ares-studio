/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.oracle.skeleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenFilter;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.parser.IPseudocodeParser;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.parser.PseudocodeParserFactory;
import com.hundsun.ares.studio.engin.skeleton.ISkeleton;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonInput;
import com.hundsun.ares.studio.engin.token.CodeTokenFactory;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.DomainHandler;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITextWithParamsToken;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.oracle.constant.IProcedureEngineContextConstantOracle;
import com.hundsun.ares.studio.procedure.compiler.oracle.macro.IProcedureMacroTokenService;
import com.hundsun.ares.studio.procedure.compiler.oracle.macro.MacroHandlerFactory;
import com.hundsun.ares.studio.procedure.compiler.oracle.macro.ProcedureMacroTokenService;
import com.hundsun.ares.studio.procedure.compiler.oracle.macro.filter.DefaultMacroTokenFilter;
import com.hundsun.ares.studio.procedure.compiler.oracle.skeleton.util.DefaultSkeletonAttributeHelper;
import com.hundsun.ares.studio.procedure.compiler.oracle.skeleton.util.ParamDefineHelper;
import com.hundsun.ares.studio.procedure.compiler.oracle.skeleton.util.ParamReplaceUtil;
import com.hundsun.ares.studio.procedure.compiler.oracle.skeleton.util.ProcedureCompilerUtil;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.PROCPoxyCodeToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureAnnotationToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureBeginCodeToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureBeginToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureCreateToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureEndCodeToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureEndToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureExceptionHandlerToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureIntoHsobjectToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureParameterInitToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureParametersDefineToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureRelatedBasicDataInfoCodeToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureRelatedTableInfoCodeToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureReturnDefineToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureReturnValueToken;
import com.hundsun.ares.studio.procedure.compiler.oracle.token.ProcedureVariableDefineToken;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.usermacro.compiler.handlers.IUserMacroTokenService;
import com.hundsun.ares.studio.usermacro.compiler.handlers.UserMacroTokenService;

/**
 * @author qinyuan
 *
 */
public class ProcedureSkeleton implements ISkeleton {
	
	IARESResource resource;
	Map<Object, Object> context = new HashMap<Object, Object>();

	/**
	 * @param resource
	 * @param context
	 */
	public ProcedureSkeleton(IARESResource resource, Map<Object, Object> context) {
		this.resource = resource;
		if(!this.context.containsKey(IProcedureEngineContextConstantOracle.Aresproject)){
			this.context.put(IProcedureEngineContextConstantOracle.Aresproject, resource.getARESProject());
		}
		if(!this.context.containsKey(IEngineContextConstant.CURR_RESOURCE)){
			this.context.put(IEngineContextConstant.CURR_RESOURCE, resource);
		}
		if(!this.context.containsKey(IProcedureEngineContextConstantOracle.MACRO_FILTER)){
			this.context.put(IProcedureEngineContextConstantOracle.MACRO_FILTER, new DefaultMacroTokenFilter(resource.getARESProject()));
		}

		if(!this.context.containsKey(IProcedureEngineContextConstantOracle.UserMacro_Service)){
			this.context.put(IProcedureEngineContextConstantOracle.UserMacro_Service, new UserMacroTokenService(resource.getARESProject(),true));
		}
		if(!this.context.containsKey(IProcedureEngineContextConstantOracle.Procedure_Macro_Service)){
			this.context.put(IProcedureEngineContextConstantOracle.Procedure_Macro_Service, new ProcedureMacroTokenService(resource.getARESProject()));
		}
		
		if(!this.context.containsKey(IEngineContextConstant.DOMAIN_HANDLER)){
			this.context.put(IEngineContextConstant.DOMAIN_HANDLER, new DomainHandler());
		}
		if(!this.context.containsKey(IProcedureEngineContextConstantOracle.SKELETON_ATTRIBUTE_HELPER)){
			this.context.put(IProcedureEngineContextConstantOracle.SKELETON_ATTRIBUTE_HELPER, new DefaultSkeletonAttributeHelper());
		}
		
		//全局上下文加入到运行时上下文
		this.context.putAll(context);
		
		//如果上下文中没有模型，从资源读取模型
		if(!this.context.containsKey(IProcedureEngineContextConstantOracle.ResourceModel)){
			try {
				this.context.put(IProcedureEngineContextConstantOracle.ResourceModel, resource.getInfo(EObject.class));
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		
		if(!this.context.containsKey(IProcedureEngineContextConstantOracle.PARAM_DEFINE_HELPER)){
			this.context.put(IProcedureEngineContextConstantOracle.PARAM_DEFINE_HELPER, new ParamDefineHelper());
		}

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#getSkeletonToken()
	 */
	@Override
	public Iterator<ICodeToken> getSkeletonToken() throws Exception {
		List<ICodeToken> tList = new ArrayList<ICodeToken>();
		
		Procedure procedure = (Procedure)getRuntimeContext().get(IProcedureEngineContextConstantOracle.ResourceModel);
		
		//是否需要出错处理-返回出错信息
		boolean isNotReturnErrorInfo = 
			(Boolean) getRuntimeContext().get(IProcedureEngineContextConstantOracle.return_error_info);
		//是否生成关联资源信息代码
		boolean isGenRelatedInfo = 
			(Boolean) getRuntimeContext().get(IProcedureEngineContextConstantOracle.gen_related_info);
		//是否生成前置代码
		boolean isGenBeginCode = 
			(Boolean) getRuntimeContext().get(IProcedureEngineContextConstantOracle.gen_begin_code);
		//是否生成后置代码
		boolean isGenEndCode = 
			(Boolean) getRuntimeContext().get(IProcedureEngineContextConstantOracle.gen_end_code);
		
		tList.add(new ProcedureAnnotationToken());//过程注释信息
		//前置代码
		if(isGenBeginCode && StringUtils.isNotBlank(procedure.getBeginCode())) {
			tList.add(new ProcedureBeginCodeToken());
		}
		//关联表
		if(isGenRelatedInfo) {
			tList.add(new ProcedureRelatedTableInfoCodeToken());
		}
		tList.add(new ProcedureCreateToken());//过程函数创建
		tList.add(new ProcedureParametersDefineToken());//过程参数声明（包括输入输出参数）
		tList.add(new ProcedureReturnDefineToken());//过程返回类型声明
		tList.add(new ProcedureVariableDefineToken());//过程变量声明（包括内部变量以及默认错误声明）
		tList.add(new ProcedureBeginToken());//过程开始
		tList.add(new ProcedureParameterInitToken());//过程参数初始化（输出参数、变量、pathinfo）
		tList.add(CodeTokenFactory.getInstance().createPseudoCodeToken(procedure.getPseudoCode()));//过程伪代码
		
		tList.add(new ProcedureReturnValueToken());//过程返回值
		if(!isNotReturnErrorInfo){
			tList.add(new ProcedureExceptionHandlerToken());//过程出错处理
		}
		tList.add(new ProcedureEndToken());//过程结束
		tList.add(new ProcedureIntoHsobjectToken(resource));//过程结束后处理（将过程写入hsobject）
		
		//关联基础数据
		if(isGenRelatedInfo) {
			tList.add(new ProcedureRelatedBasicDataInfoCodeToken());
		}
		
		//后置代码
		if(isGenEndCode && StringUtils.isNotBlank(procedure.getEndCode())) {
			tList.add(new ProcedureEndCodeToken());
		}
		
//		tList.add(new ProcedureAnnotationToken());//过程注释信息
//		tList.add(new ProcedureCreateToken());//过程函数创建
//		tList.add(new ProcedureParametersDefineToken());//过程参数声明（包括输入输出参数）
//		tList.add(new ProcedureReturnDefineToken());//过程返回类型声明
//		tList.add(new ProcedureVariableDefineToken());//过程变量声明（包括内部变量以及默认错误声明）
//		tList.add(new ProcedureBeginToken());//过程开始
//		tList.add(new ProcedureParameterInitToken());//过程参数初始化（输出参数、变量、pathinfo）
//		tList.add(CodeTokenFactory.getInstance().createPseudoCodeToken(procedure.getPseudoCode()));//过程伪代码
//		tList.add(new ProcedureReturnValueToken());//过程返回值
//		tList.add(new ProcedureExceptionHandlerToken());//过程出错处理
//		tList.add(new ProcedureEndToken());//过程结束
//		tList.add(new ProcedureIntoHsobjectToken());//过程结束后处理（将过程写入hsobject）

		
		return tList.iterator();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#getRuntimeContext()
	 */
	@Override
	public Map<Object, Object> getRuntimeContext() throws Exception {
			
		return this.context;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#postValidate()
	 */
	@Override
	public Object[] postValidate() {
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
		return tlist.toArray();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#handleTextToken(com.hundsun.ares.studio.engin.token.ICodeToken, java.util.Map)
	 */
	@Override
	public ICodeToken handleTextToken(ICodeToken token,
			Map<Object, Object> context) throws Exception {
		boolean inBlock = false;  //是否在proc语句块当中
		IDomainHandler handler = (IDomainHandler)context.get(IEngineContextConstant.DOMAIN_HANDLER);
		//ITokenDomain domain = handler.getDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
//		if(null == domain){
//			inBlock = false;
//		}else{
//			inBlock = true;
//		}

		//添加普通文本中的proc变量列表
		if((token instanceof ITextWithParamsToken) && inBlock){
			ITextWithParamsToken txtToken = (ITextWithParamsToken)token;
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IProcedureEngineContextConstantOracle.SKELETON_ATTRIBUTE_HELPER);
			for(String paramName : txtToken.getUsedParams()){
				helper.addAttribute(IProcedureEngineContextConstantOracle.ATTR_PROC_VARIABLE_LIST, paramName);
			}
		}
		
		if(inBlock){
			//如果在proc块当中，使用代理
			return new PROCPoxyCodeToken(token);
		}else{
			//如果不在proc块当中，直接使用原来的token
			return token;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#getMacroTokenHandler(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public IMacroTokenHandler getMacroTokenHandler(IMacroToken macro,
			Map<Object, Object> context) throws Exception {
		IMacroTokenHandler  handler = null;//MacroHandlerFactory.getInstance().create(macro.getKeyword());
		
		int macroType = -1;//默认为未匹配的宏
//		//函数宏处理
//		if(context.containsKey(IProcedureEngineContextConstant.Function_Macro_Service)){
//			IFunctionMacroTokenService fmservice = (IFunctionMacroTokenService)context.get(IProcedureEngineContextConstant.Function_Macro_Service);
//			if(fmservice.isAtomFunction(macro.getKeyword())){
//				macroType = 1;//原子函数调用
//				handler = fmservice.getHandler(macro.getKeyword());
//			}
//		}
		
		//存储过程宏处理
		if(context.containsKey(IProcedureEngineContextConstantOracle.Procedure_Macro_Service)){
			IProcedureMacroTokenService prcservice = (IProcedureMacroTokenService)context.get(IProcedureEngineContextConstantOracle.Procedure_Macro_Service);
			if(prcservice.isProcedure(macro.getKeyword())){
				macroType = 2;//存储过程调用
				handler = prcservice.getHandler(macro.getKeyword());
			}
		}
		
		//用户自定义宏处理
		if(macroType < 0 && context.containsKey(IProcedureEngineContextConstantOracle.UserMacro_Service)){
			IUserMacroTokenService fmservice = (IUserMacroTokenService)context.get(IProcedureEngineContextConstantOracle.UserMacro_Service);
			if(fmservice.isUserMacro(macro.getKeyword())){
				macroType = 3;
				Map<String,String> businessType = new HashMap<String, String>();
				Procedure pro = (Procedure)getRuntimeContext().get(IProcedureEngineContextConstantOracle.ResourceModel);
				IARESProject project = (IARESProject)getRuntimeContext().get(IProcedureEngineContextConstantOracle.Aresproject);
				businessType.putAll(ProcedureCompilerUtil.getParamterBusinessType2Map(pro.getInputParameters(), project));
				businessType.putAll(ProcedureCompilerUtil.getParamterBusinessType2Map(pro.getOutputParameters(), project));
				businessType.putAll(ProcedureCompilerUtil.getProVariableBusinessType2Map(pro, project));
				//给handler注入游标
				ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IProcedureEngineContextConstantOracle.SKELETON_ATTRIBUTE_HELPER);
				Set<String> cursorList = helper.getAttribute(IProcedureEngineContextConstantOracle.ATTR_CURSOR_LIST);
				Set<String> rsIdSet = helper.getAttribute(IProcedureEngineContextConstantOracle.ATTR_GETLAST_RESULTSET);
				String lastId = "";
				if(rsIdSet.size() > 0){
					lastId = (String)rsIdSet.toArray()[rsIdSet.size() - 1];
				}
				List<String> params = new ArrayList<String>();
				{
					for(Parameter input : pro.getInputParameters()){
						params.add(input.getId());
					}
					for (Parameter ouput : pro.getOutputParameters()) {
						params.add(ouput.getId());
					}
				}
				Map<String,Object> paramMap= new HashMap<String,Object>();
				paramMap.put("dataRealType", businessType);
				paramMap.put("lastCur", cursorList);
				paramMap.put("lastResId", lastId);
				paramMap.put("inoutParams", params);
				handler = fmservice.getUserMacroHandler(macro.getKeyword(),context ,paramMap);
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
		IMacroTokenFilter filter = (IMacroTokenFilter)context.get(IProcedureEngineContextConstantOracle.MACRO_FILTER);
		
		switch (macroType) {
		case -1:
			throw new Exception(String.format("没有找到宏[%s]相关的处理类。请确认宏文本书写正确。", macro.getKeyword()));
//		case 1:   //函数
//			break;
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

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#getPseudocodeParser(com.hundsun.ares.studio.engin.token.ICodeToken, java.util.Map)
	 */
	@Override
	public IPseudocodeParser getPseudocodeParser(ICodeToken token,
			Map<Object, Object> context) throws Exception {
		return PseudocodeParserFactory.instance.createParser();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#onFinish(java.util.Map)
	 */
	@Override
	public void onFinish(Map<Object, Object> context) throws Exception {
		// 参考UFT的ParaHandleToken完成对@变量的处理，以及<>错误号与用户常量的处理
		List<String> tlist = new ArrayList<String>();

		IARESProject project = (IARESProject)context.get(IProcedureEngineContextConstantOracle.Aresproject);
		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IProcedureEngineContextConstantOracle.SKELETON_ATTRIBUTE_HELPER);
		Set<String> inoutParamList = helper.getAttribute(IProcedureEngineContextConstantOracle.ATTR_IN_OUT_PARAM_LIST);

		//真实代码
		StringBuffer buffer = (StringBuffer)context.get(IEngineContextConstant.BUFFER);
		//替换普通的标准字段
		String result = buffer.toString();
		if(StringUtils.contains(result, "@")){
			//用proc解析一次，系统宏如select插入表记录解析后还会生成@变量
			Iterator<ICodeToken> codes = PseudoCodeParser.parseEx(buffer.toString(), PseudoCodeParser.PROC_COMMENT_PATTERN, PseudoCodeParser.PROC_STRING_PATTERN, context);
			
			//伪代码中未申明变量如果不为标准字段需要排除
			List<String> pseudoCodeParaList = (List<String>) context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
			for (String varName : pseudoCodeParaList) {
				ReferenceInfo std = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdField, varName, true);
				if(null != std) {
					tlist.add(varName);
				}
			}
			
			if(!tlist.contains("error_no")){
				tlist.add("error_no");
			}
			if(!tlist.contains("error_info")){
				tlist.add("error_info");
			}
			if(!tlist.contains("error_id")){
				tlist.add("error_id");
			}
			if(!tlist.contains("error_sysinfo")){
				tlist.add("error_sysinfo");
			}
			if(!tlist.contains("error_pathinfo")){
				tlist.add("error_pathinfo");
			}
			
			Procedure procedure = (Procedure)getRuntimeContext().get(IProcedureEngineContextConstantOracle.ResourceModel);
			//结果集返回，添加游标
			if(procedure.isOutputCollection()) {
				if(!tlist.contains("cursor")){
					tlist.add("cursor");
				}
			}
			//添加内部变量
			for(Parameter p :procedure.getInternalVariables()) {
				if(!tlist.contains(p.getId())) {
					tlist.add(p.getId());
				}
			}
			
			//替换标准字段
			result = ParamReplaceUtil.handleParams("",codes, tlist.toArray(new String[0]), inoutParamList);
		}
		
		if(result.toString().indexOf("<") == -1 || result.toString().indexOf(">") == -1){
			//不包含常量
		}else{
			//解析所有常量
			List<String> constantList =  ParamReplaceUtil.findConstAll(result,context);
			//替换所有常量
			for(String constant:constantList){
				try {
					result = handleConstantReplace(result, constant, project,context);
				} catch (Exception e) {
					manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,e.getMessage()));
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
	private String handleConstantReplace(String content,String variableName, IARESProject project,Map<Object, Object> context) throws Exception{
		//去掉<>取得常量名
		String paraName =variableName.trim().substring(variableName.trim().indexOf("<")+1,variableName.trim().indexOf(">"));
		String definitionCode = null;
		
		ReferenceManager manager = ReferenceManager.getInstance();
		ReferenceInfo ref = manager.getFirstReferenceInfo(project, IMetadataRefType.ErrNo, paraName, true);
		if (ref != null) {
			ErrorNoItem item = (ErrorNoItem) ref.getObject();
			definitionCode =  item.getNo();
		} else {
			ref = manager.getFirstReferenceInfo(project, IMetadataRefType.Const, paraName, true);
			if (ref != null) {
				ConstantItem item = (ConstantItem) ref.getObject();
				definitionCode =  item.getValue();
			}
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
		
		//没有找到相关定义
		if(null == definitionCode){
			throw new Exception(String.format("未找到的常量%s的相关定义", paraName));
		}
		
		//过程里要替换掉“"”
		definitionCode = definitionCode.replaceAll("\"", "'");
		//用常量的实际值替换常量名
		return ParamReplaceUtil.replaceContentWithNotString(content, variableName, definitionCode,context);
//		return content.replaceAll(variableName,definitionCode);	
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeleton#getCommentType()
	 */
	@Override
	public int getCommentType() {
		return PseudoCodeParser.PROC_TYPE;
	}

}
