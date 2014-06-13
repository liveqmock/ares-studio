/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.tokens;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.skeleton.util.SerEndHelper;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.ParamGroupUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;

/**
 * @author qinyuan
 *
 */
public class LogicFunctionSvrEndToken extends LogicServiceSvrEndToken {
	private static final String RESULT_SET_PREFIX = "lpResultSet";
	private static final String X_FLAG_PLACEHOLDER = "___XXX___";

	private static final String LOGIC_FUNCTION_FORMAT_SVR_END = 
		"goto svr_end;\r\n" +
		"svr_end:\r\n" + 
		"%s\r\n" +
		"if (%s)\r\n" +
		"{\r\n" +
		"%s" +
		"\r\n}\r\nelse\r\n{" +
		"%s" +
		"\r\n}\r\n";
	
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		LogicFunction lf = (LogicFunction)context.get(ILogicEngineContextConstant.ResourceModel);
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(ILogicEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		resultsetParameters = this.getResultsetParameters(context);//结果集字段列表
		 project = (IARESProject) context.get(ILogicEngineContextConstant.Aresproject);
		this.popVarList = (List<String>)context.get(ILogicEngineContextConstant.PseudoCode_Para_LIST);
		List<Parameter> outputParameters = new ArrayList<Parameter>();
		ParamGroupUtil.parserParameters(lf.getOutputParameters(), outputParameters, project);
		StringBuffer sb = new StringBuffer();
		Set<String> rsSet = helper.getAttribute(ILogicEngineContextConstant.ATTR_RESULTSET_LIST);
		if (lf.isOutputCollection() && rsSet.size()>0) {
			sb.append("PackResultSet(");
			{
					String lastResultSetPrifex = getLastResultset(context);//取得最近结果集
					sb.append(getEndPack(lf.getInterfaceFlag(), lastResultSetPrifex));
			}
			sb.append(",");
			sb.append("lpOutPacker");
			sb.append(");\n");
		} else /*if(re.size()>0 && Boolean.parseBoolean(re.toArray(new String[0])[0]))*/{
			// 打包
			Set<String> in_set = new LinkedHashSet<String>();
			
			for (Parameter outParam : outputParameters) {
				in_set.add(outParam.getId());
			}
			//带IO标准的输入参数
			List<Parameter> inputParameters = new ArrayList<Parameter>();
			ParamGroupUtil.parserParameters(lf.getInputParameters(), inputParameters, project);
			for(Parameter inParam : inputParameters){
				if (StringUtils.defaultIfBlank(inParam.getFlags(), "").indexOf("IO") != -1) {
					in_set.add(inParam.getId());
				}
			}
			StringBuffer addField = new StringBuffer();
			StringBuffer addFieldValue = new StringBuffer();
			
			for (String key : in_set) {
				Parameter p = getParameter(lf, key);
				if(null == p) {
					continue;
				}
				Map<String,String> parameterInfo = getStandardFieldParameterInfo(p.getId());
				addField.append(getAddFieldCode(p,parameterInfo ));
				
				
				// 根据类型信息处理数据
				if(key.equals("error_pathinfo")){
					addFieldValue.append( String.format("%1$s->AddStr(%2$s);	//%3$s \n", "lpOutPacker", "@error_pathinfo", key) );
				}else{
					addFieldValue.append(getAddFieldValueCode(p, parameterInfo));
				}
			}
			sb.append(addField).append(addFieldValue);
		}
		
		StringBuffer false_section = new StringBuffer();
		SerEndHelper serEndHelper = new SerEndHelper();
		false_section.append( String.format("\nSystemErrorPacker(%1$s,%2$s,%3$s,%4$s);\n", NAME_SERVICE_OUTPACKER, "@error_pathinfo", "@error_no", "@error_info")+serEndHelper.getErrorFieldAndFieldValue(outputParameters, serEndHelper.getExcludeParameters(), context));
	
		false_section.append("\r\n");
		
		StringBuffer retsb = new StringBuffer();
		String strExtend = "";
		if (lf.isIsTransFunc()) {
			strExtend = String.format(" if(%1$s) %1$s->EndTrans();", "lpTransMonitor");
		}
		String	condition =  "iReturnCode == OK_SUCCESS || iReturnCode == ERR_SYSWARNING";
	    retsb.append( String.format(LOGIC_FUNCTION_FORMAT_SVR_END, strExtend ,condition, sb, false_section) );
	  
		return retsb.toString();
		}

	private String getEndPack(String flag, String objectId) {
		if (StringUtils.isNotBlank(flag)) {
			if (flag.toLowerCase().indexOf("j") != -1) {
				return RESULT_SET_PREFIX + "_J";
			} else if (flag.toLowerCase().indexOf("x") != -1) {// X标记
				return RESULT_SET_PREFIX + X_FLAG_PLACEHOLDER;
			}
		}
		return RESULT_SET_PREFIX + objectId;
	}

}
