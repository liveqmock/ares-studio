/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.mysql.token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.constant.MarkConfig;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.mysql.constant.IProcedureEngineContextConstantMySQL;
import com.hundsun.ares.studio.procedure.compiler.mysql.skeleton.util.ProcedureCompilerUtil;

/**
 * 存储过程参数定义代码生成
 * @author liaogc
 *
 */
public class ProcedureParametersDefineToken implements ICodeToken {
	private static int IN_PARAMETER = 0;//输入参数
	private static int OUT_PARAMETER = 1;//输出参数
	private static int FLAG_IN = 0;//标记是输入"I"
	private static int FLAG_OUT = 1;//标记是输茁"O"
	private static int FLAG_INOUT = 2;//标记是输入输出"IO"
	private static String DATA_TYPE_CURSOR="cursor";//游标类型
	private static String BLANK = " ";//空格
	private static String NL = ICodeToken.NEWLINE;//换行
	private static String COMMENT="--";//注解
	private final static String PARAM_DEFINE = "\tp_%1$s  %2$s  hstype.%3$s%4$s";
	private Procedure procedure;//过程模型
	private static  Map<Object, Object> context;
	//private boolean notDefineConnectType;
	
	

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
		// TODO 过程参数声明（包括输入输出参数）
		// 参考宏[输入参数声明]
		//[输出参数声明]
		this.procedure = (Procedure)context.get(IProcedureEngineContextConstantMySQL.ResourceModel);
		this.context = context;
		//this.notDefineConnectType = (Boolean) context.get(IProcedureEngineContextConstantMySQL.not_define_connect_type);
		
		IARESProject project = (IARESProject)context.get(IProcedureEngineContextConstantMySQL.Aresproject);
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IProcedureEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		
		Set<String> parameters = new  HashSet<String>();//记录变量是定义
		StringBuffer declareCode = new StringBuffer();
		
		int index = 0;//计数
		List<Parameter> totleInputParam = new ArrayList<Parameter>();
		ProcedureCompilerUtil.getTotleParameters(project ,this.procedure.getInputParameters() ,totleInputParam);
		List<Parameter> totleOutputParam = new ArrayList<Parameter>();
		ProcedureCompilerUtil.getTotleParameters(project ,this.procedure.getOutputParameters() ,totleOutputParam);
		//输入参数定义
		for(Parameter parameter: totleInputParam){
			if(!parameters.contains(parameter.getId())){
				parameters.add(parameter.getId());
				helper.addAttribute(IProcedureEngineContextConstantMySQL.ATTR_IN_OUT_PARAM_LIST, parameter.getId());

				try {
					String defineStr = getParamDefineCodeStr(parameter,IN_PARAMETER, project);
					if(index != totleInputParam.size() -1 ){
						defineStr += ",";
					}else {//最后一个，要看是否有输出参数
						//有输出参数，或者为结果集返回，也要加“，”
						if(totleOutputParam.size() > 0 || procedure.isOutputCollection()){
							defineStr += ",";
						}
					}
					
					if(StringUtils.isNotBlank(defineStr)){
						String chineseName = parameter.getComments();//优先使用备注作为注释信息
						if(StringUtils.isBlank(chineseName)){
							if(parameter.getParamType().getValue()==ParamType.NON_STD_FIELD_VALUE){
								chineseName = parameter.getName();
							}
							if(StringUtils.isBlank(chineseName)){
								chineseName = this.getChineseName(project, parameter.getId());
							}
						}
						declareCode.append(defineStr).append(BLANK).append(COMMENT).append(chineseName).append(NL);
						}
					} catch (Exception e) {
				}
			}
			index++;
		}
		declareCode.append(NL);
		
		index = 0;//重新计数
		//输出参数定义
		
		if(procedure.isOutputCollection()) {
			//返回结果集为Ture，则声明游标输出参数以及带“IO”标志的输出参数，其它输出参数不声明
			for(Parameter parameter: totleOutputParam){
				if(StringUtils.equals(MarkConfig.MARK_IOFLAG, parameter.getFlags())){
					if (!parameters.contains(parameter.getId())) {
						parameters.add(parameter.getId());
						helper.addAttribute(IProcedureEngineContextConstantMySQL.ATTR_IN_OUT_PARAM_LIST, parameter.getId());
						try {
							String defineStr = getParamDefineCodeStr(parameter,OUT_PARAMETER, project);
							if(index != totleOutputParam.size() - 1) {
								defineStr += ",";
							}else {//最后一个，要看是否包含游标
								if(!ProcedureCompilerUtil.isParameterINInputAndOutputParameterByName(procedure, "cursor",project)){
									if (!parameters.contains("cursor")) {//不包含游标类型，还需要申明一个游标类型，此处还未到最后一个
										defineStr += ",";
									}
								}
							}
							
							if (StringUtils.isNotBlank(defineStr)) {
								String chineseName = parameter.getComments();//优先使用备注作为注释信息
								if(StringUtils.isBlank(chineseName)){
									if(parameter.getParamType().getValue()==ParamType.NON_STD_FIELD_VALUE){
										chineseName = parameter.getName();
									}
									if(StringUtils.isBlank(chineseName)){
										chineseName = this.getChineseName(project, parameter.getId());
									}
								}
								declareCode.append(defineStr).append(BLANK).append(COMMENT).append(chineseName).append(NL);
							}
						} catch (Exception e) {
						}
					}
				}
				index++;
			}
			//游标变量
				parameters.add("cursor");
				helper.addAttribute(IProcedureEngineContextConstantMySQL.ATTR_IN_OUT_PARAM_LIST, "cursor");
				declareCode.append("	p_cursor  out  hstype.t_cursor  -- 输出结果集游标\n");
		}else {
			for(Parameter parameter: totleOutputParam){
				if (!parameters.contains(parameter.getId())) {
					parameters.add(parameter.getId());
					helper.addAttribute(IProcedureEngineContextConstantMySQL.ATTR_IN_OUT_PARAM_LIST, parameter.getId());
					try {
						String defineStr = getParamDefineCodeStr(parameter,OUT_PARAMETER, project);
						if(index != totleOutputParam.size() - 1) {
							defineStr += ",";
						}//最后一个不需要添加","
						
						if (StringUtils.isNotBlank(defineStr)) {
							String chineseName = parameter.getComments();//优先使用备注作为注释信息
							if(StringUtils.isBlank(chineseName)){
								if(parameter.getParamType().getValue()==ParamType.NON_STD_FIELD_VALUE){
									chineseName = parameter.getName();
								}
								if(StringUtils.isBlank(chineseName)){
									chineseName = this.getChineseName(project, parameter.getId());
								}
							}
							declareCode.append(defineStr).append(BLANK).append(COMMENT).append(chineseName).append(NL);
						}
					} catch (Exception e) {
					}
				}
				index++;
			}
		}
		
		return declareCode.toString();
	}

	/**
	 * 生成一个参数的数库类型定义串
	 * @param parameter 参数
	 * @param type 输入或者输出参数
	 * @param project 当前项目
	 * @return
	 */
	
	private String getParamDefineCodeStr(Parameter parameter,int type,IARESProject project) throws Exception{
		StringBuffer codeBuffer = new StringBuffer();
		String inOutStr = StringUtils.EMPTY;
		int flag = FLAG_IN ;
		if(StringUtils.equals(MarkConfig.MARK_IOFLAG, parameter.getFlags())){
			inOutStr = "in  out ";
			flag = FLAG_INOUT;
		}else if(IN_PARAMETER==type){
			inOutStr = StringUtils.EMPTY;
			flag = FLAG_IN;
		}else if(OUT_PARAMETER==type){
			inOutStr = "out";
			flag = FLAG_OUT;
		}
		if(StringUtils.indexOf( parameter.getFlags(), "O")>-1 ||StringUtils.indexOf( parameter.getFlags(), "o")>-1){
			flag = FLAG_OUT;
		}
		if(parameter.getParamType().getValue() == ParamType.NON_STD_FIELD_VALUE){//如果非标准字段
			String dataType = parameter.getType();
			if(StringUtils.isNotBlank(dataType)){
				if(StringUtils.equals(DATA_TYPE_CURSOR, dataType)){//如是游标(数组情况暂时先不考虑)
					codeBuffer.append(String.format(PARAM_DEFINE, parameter.getId(),inOutStr,dataType,StringUtils.EMPTY));
				}else{//其他情况
					codeBuffer.append(String.format(PARAM_DEFINE, parameter.getId(),inOutStr,dataType,"%type"));
				}
			}
			
		}else if(parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE){
			BusinessDataType businessDataType = null;
			try {
				businessDataType = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(project, parameter.getId());
			} catch (Exception e) {
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("参数[%1s]]获得业务类型出错.出错原因:该参数为标准字段参数,但是获取对应的业务类型时出错",parameter.getId());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			}
			if(businessDataType!=null){
				String dataType= businessDataType.getName();
				String realType = StringUtils.EMPTY;
				StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeOfBizTypeByName(project, dataType);
				if(null != stdType) {
					realType = stdType.getValue(ProcedureCompilerUtil.getDatabaseType(project));
				}
				
				if(StringUtils.isNotBlank(dataType)){
					if(StringUtils.equals(DATA_TYPE_CURSOR, realType)){//如是游标(数组情况暂时先不考虑)
						codeBuffer.append(String.format(PARAM_DEFINE, parameter.getId(),inOutStr,dataType,StringUtils.EMPTY));
					}else{//其他情况					
						codeBuffer.append(String.format(PARAM_DEFINE, parameter.getId(),inOutStr,dataType,"%type"));
						
						//输入参数添加defaultValue
						if(IN_PARAMETER==type && flag==FLAG_IN){
							String defaultValue = "";
							try {
								defaultValue = ProcedureCompilerUtil.getParameterDefaultValue(parameter, project);
								codeBuffer.append("  DEFAULT  " + defaultValue);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
//		codeBuffer.append(",");
		return codeBuffer.toString();
	}
	
	/**
	 * 返回标准字段的中文名
	 * @param project
	 * @param fieldName
	 * @return
	 */
	private String getChineseName(IARESProject project, String fieldName){
		StandardField field=null;
		try {
			field = MetadataServiceProvider.getStandardFieldByName(project, fieldName);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		if(field!=null){
			return StringUtils.defaultIfBlank(field.getChineseName(), StringUtils.EMPTY);
		}else {
			return StringUtils.defaultIfBlank(fieldName, StringUtils.EMPTY);
		}
	}

}
