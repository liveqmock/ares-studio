/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.constant.MarkConfig;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.constant.IProcedureEngineContextConstant;
import com.hundsun.ares.studio.procedure.compiler.skeleton.util.ProcedureCompilerUtil;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * 
 * 过程参数初始化（输出参数、变量、pathinfo）
 *
 * @author liaogc
 */
public class ProcedureParameterInitToken implements ICodeToken {
	private Procedure procedure;//过程模型
	private static  Map<Object, Object> context;
	private final static String PARAM_INIT = "\tv_%1$s  := %2$s ;" + ITokenConstant.NL;
	//输出参数前缀为P_
	private final static String EXPORT_PARAM_INIT = "\tp_%1$s  := %2$s ;" + ITokenConstant.NL;
	private static final String NL = ITokenConstant.NL;
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return ICodeToken.CODE_TEXT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		this.context =context;
         this.procedure = (Procedure)context.get(IProcedureEngineContextConstant.ResourceModel);
         
		IARESProject project = (IARESProject)context.get(IProcedureEngineContextConstant.Aresproject);
		StringBuffer initCode = new StringBuffer();
	
		//输出变量初始化
		List<Parameter> totleOutputParam = new ArrayList<Parameter>();
		getTotleParameters(project ,this.procedure.getOutputParameters() ,totleOutputParam);
		for(Parameter parameter:totleOutputParam){
			String defaultValue =getDefaultValue(parameter,project);
			
			if(StringUtils.equalsIgnoreCase(defaultValue, "null")) {
				//不做处理
				continue;
			}
			
			if(!procedure.isOutputCollection()){//如果不是返回结果集
				if(!StringUtils.equals(parameter.getFlags(), MarkConfig.MARK_IOFLAG)){//只对输出初始化
					if(defaultValue!=null){
						initCode.append(String.format(EXPORT_PARAM_INIT, parameter.getId(),defaultValue));
					}
				}
			}else{
				if(defaultValue!=null){
					initCode.append(String.format(EXPORT_PARAM_INIT, parameter.getId(),defaultValue));
				}
			}
		}
		
		Set<String> parameters = new  HashSet<String>();//记录变量是否初始化
		 //自动申明输入参数为变量
		 boolean autoDefineInputParam = 
				(Boolean) context.get(IProcedureEngineContextConstant.auto_define_input_param);
		 if(autoDefineInputParam) {//如果已经将输入参数定义为内部变量，不进行同名参数初始化
			//输出变量初始化
			for(Parameter parameter:procedure.getInputParameters()){
				parameters.add(parameter.getId());
			}
		 }
		
		//内部变量初始化
		for(Parameter parameter:procedure.getInternalVariables()){
			if(!parameters.contains(parameter.getId())){
				parameters.add(parameter.getId());
				String defaultValue =getDefaultValue(parameter,project);
				if(defaultValue!=null){
					initCode.append(String.format(PARAM_INIT, parameter.getId(),defaultValue));
				}
			}
				
		}
			
		//错误相关变量定义
		 for(String name:ProcedureCompilerUtil.getErrorVarsName()){
	    	if(!ProcedureCompilerUtil.isParameterINProcedureParameterByName(procedure, name,project)){//不在输入输出以及内部变量中
	    		if(!parameters.contains(name)){
					parameters.add(name);
					
					//错误相关变量未定义成标准字段时，使用约定的默认值
    				ReferenceInfo stdField = ReferenceManager.getInstance().getFirstReferenceInfo(project,IMetadataRefType.StdField, name, false);
    				if(null == stdField) {
    					initCode.append(String.format(PARAM_INIT, name,ProcedureCompilerUtil.getErrorVarDefaultValue(name)));
    				}else {
    					String defaultValue =getStandardFieldDefaultValue(name,project);
    					if(defaultValue!=null){
    						initCode.append(String.format(PARAM_INIT, name,defaultValue));
    					}
    				}
	    		}
	    	}
		 }

		//伪代码变量初始化
	  @SuppressWarnings("unchecked")
	  List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);//取得伪代码变量列表
		
		for(int i = 0;i < popVarList.size();i++){
			String fieldName = popVarList.get(i);
			if(!parameters.contains(fieldName)){
				parameters.add(fieldName);
				try{
					if(!ProcedureCompilerUtil.isParameterINProcedureParameterByName(procedure, fieldName,project)){
						String defaultValue =getStandardFieldDefaultValue(fieldName,project);
						if(defaultValue!=null){
							initCode.append(String.format(PARAM_INIT, fieldName,defaultValue));
						}
					}
				  
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
			
	    //对errorPathinfo初始化特殊处理
		initCode.append(initErrorPathInfo());
		
		return  initCode.toString();
	}
	
	/**
	 * 参数汇总，将参数组中的参数汇总到输入输出中
	 * 
	 * @param project
	 * @param params
	 * @param totleParams
	 */
	private void getTotleParameters(IARESProject project , EList<Parameter> params , List<Parameter> totleParams) {
		for(Parameter p :params){
			if (p.getParamType() == ParamType.OBJECT) {
				continue;
			}
			if (p.getParamType() == ParamType.PARAM_GROUP) {
				ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(project, IBizRefType.Object, p.getType(), false);
				if (info != null) {
					Object obj = info.getObject();
					if (obj instanceof ARESObject) {
						getTotleParameters(project ,((ARESObject) obj).getProperties(), totleParams);
					}
				}
			}else {
				totleParams.add(p);
			}
		}
	}
	
	/**
	 * 
	 * @param parameter
	 * @param project
	 * @return 返回参数默认值
	 */
	private String getDefaultValue(Parameter parameter ,IARESProject project){
		String defaultValue = parameter.getDefaultValue();
		//先判断是否是标准默认值的引用，如果是，则直接返回，不需要判断参数类型
		if (StringUtils.isNotBlank(defaultValue)) {
			try {
				TypeDefaultValue typeDev = MetadataServiceProvider.getTypeDefaultValueByName(project, defaultValue);
				if (typeDev != null) {
					return typeDev.getValue(ProcedureCompilerUtil.getDatabaseType(project));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(parameter.getParamType().getValue() == ParamType.NON_STD_FIELD_VALUE){//如果非标准字段
			if(StringUtils.isEmpty(defaultValue)){
				TypeDefaultValue typeDefValue = null;
				try {
					typeDefValue = MetadataServiceProvider.getTypeDefaultValueOfBizTypeByName(project, parameter.getType());
				} catch (Exception e) {
					ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
					String message = String.format("初始化参数[%1s]出错.出错原因:该参数为非标准字段参数,但是参数类型对应的业务类型不存在", parameter.getId());
					manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				}
				if(typeDefValue != null){
					defaultValue = typeDefValue.getValue(ProcedureCompilerUtil.getDatabaseType(project));
				}
			}
		}else if(parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE){
			//如果是标准字段
			try {
				//优先使用参数的默认值,参数默认值不存在再使用标准字段对应的默认值
				if(StringUtils.isEmpty(defaultValue)){
					defaultValue = getStandardFieldDefaultValue(parameter.getId(),project);
				}
			}
			catch(Exception e){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("初始化参数[%1s]出错.该参数为标准字段参数,但是获得标准字段相关信息时出错", parameter.getId());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			}
		}else if(parameter.getParamType().getValue() == ParamType.OBJECT_VALUE){
			//对象
			if(StringUtils.isNotEmpty(defaultValue)){
				return defaultValue;
			}else {
				return "null";
			}
		}
		return defaultValue;
	}
	
	/**
	 * 返回标准字段的默认值
	 * @param parameter
	 * @param project
	 * @return 返回默认值
	 */
	private String getStandardFieldDefaultValue(String paramName ,IARESProject project)throws Exception{
		String defaultValue = null;

		StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(project, paramName);
		TypeDefaultValue typpeDefValue = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName(project, paramName);
		BusinessDataType	busType = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(project, paramName);
		
		if((stdType != null) && ( typpeDefValue!= null) && ( busType!= null)){//标准字段
			defaultValue = typpeDefValue.getValue(ProcedureCompilerUtil.getDatabaseType(project));
		}
		return defaultValue;
		
	}
	private String initErrorPathInfo(){
		StringBuffer initCode = new StringBuffer();
		initCode.append("\t@error_pathinfo := substr(@error_pathinfo || '-->"+procedure.getName()+"',1,500);").append(NL);
		initCode.append("\tv_error_pathinfo_tmp := @error_pathinfo;").append(NL);
		return initCode.toString();
	}
	
	
	
}
