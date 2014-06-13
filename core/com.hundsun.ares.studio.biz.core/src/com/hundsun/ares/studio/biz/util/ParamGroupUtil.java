/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.biz.util;

import java.util.List;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author liaogc
 *
 */
public class ParamGroupUtil {

	/**
	 * 递归参数组参数(返回所有类型为标准字段的参数以及对象的参数)
	 * @param gruopParam
	 * @param retParameter
	 * @param isInParameter
	 * @param project
	 */
	public static void parserParamGroup( Parameter gruopParam,List<Parameter> retParameter,int callDepth,IARESProject project){
		if(project==null){
			return;
		}
		String path = gruopParam.getType();
		try {
			IARESResource objectRes = project.findResource(path, "object");
			ARESObject aresObject = objectRes.getInfo(ARESObject.class);
			List<Parameter> properties = aresObject.getProperties();
			for(Parameter parameter:properties){
				if (parameter.getParamType().getValue() == ParamType.PARAM_GROUP_VALUE) {// 如果是参数组则递归调用
					if(callDepth<=5){
						parserParamGroup(parameter, retParameter,callDepth+1, project);
					}
					
				} else if (parameter.getParamType().getValue() == ParamType.OBJECT_VALUE) {
					retParameter.add(parameter);
				} else if (parameter.getParamType().getValue() == ParamType.STD_FIELD_VALUE) {
					retParameter.add(parameter);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 递归参返回除对象外的所有参数(参数组将展开)
	 * @param gruopParam
	 * @param retParameter
	 * @param isInParameter
	 * @param project
	 */
	public static void parserParametersWithNoObjectParameter( Parameter gruopParam,List<Parameter> retParameter,int callDepth,IARESProject project){
		if(project==null){
			return;
		}
		String path = gruopParam.getType();
		try {
			IARESResource objectRes = project.findResource(path, "object");
			ARESObject aresObject = objectRes.getInfo(ARESObject.class);
			List<Parameter> properties = aresObject.getProperties();
			for(Parameter parameter:properties){
				if (parameter.getParamType().getValue() == ParamType.PARAM_GROUP_VALUE) {// 如果是参数组则递归调用
					if(callDepth<=5){
						parserParametersWithNoObjectParameter(parameter, retParameter,callDepth+1, project);
					}
					
				} else if (parameter.getParamType().getValue() != ParamType.OBJECT_VALUE) {
					retParameter.add(parameter);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 返回输入或者输出参数:除对象外的所有参数(参数组将展开)
	 * @param parameters
	 * @param retParameter
	 * @param project
	 */
	
	public static void parserParametersWithNoObjectParameter( List<Parameter>parameters ,List<Parameter> retParameter,IARESProject project){
		int callDepth = 1;
		for(Parameter parameter1:parameters){
			if(parameter1.getParamType().getValue() == ParamType.PARAM_GROUP_VALUE){
				String path = parameter1.getType();
				try {
					IARESResource objectRes = project.findResource(path, "object");
					ARESObject aresObject = objectRes.getInfo(ARESObject.class);
					List<Parameter> properties = aresObject.getProperties();
					for(Parameter parameter2:properties){
						if (parameter2.getParamType().getValue() == ParamType.PARAM_GROUP_VALUE) {// 如果是参数组则递归调用
							if(callDepth<=5){
								parserParametersWithNoObjectParameter(parameter2, retParameter,callDepth+1, project);
							}
							
						} else if (parameter2.getParamType().getValue() != ParamType.OBJECT_VALUE) {
							retParameter.add(parameter2);
						} 
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(parameter1.getParamType().getValue() != ParamType.OBJECT_VALUE){
				retParameter.add(parameter1);
			}
		}
		
		
	}
	
	

	/**
	 * 判断输入输出中是否包含对象
	 * @param parameters
	 * @param project
	 * @return
	 */
	
	public static boolean isContainObjectParameter( List<Parameter>parameters ,IARESProject project){
		int callDepth = 1;
		for(Parameter parameter1:parameters){
			if(parameter1.getParamType().getValue() == ParamType.PARAM_GROUP_VALUE){
				String path = parameter1.getType();
				try {
					IARESResource objectRes = project.findResource(path, "object");
					ARESObject aresObject = objectRes.getInfo(ARESObject.class);
					List<Parameter> properties = aresObject.getProperties();
					for(Parameter parameter2:properties){
						if (parameter2.getParamType().getValue() == ParamType.PARAM_GROUP_VALUE) {// 如果是参数组则递归调用
							if(callDepth<=5){
								boolean isContainObjectParameter =  isContainsObjectParameter(parameter2,callDepth+1, project);
								if(isContainObjectParameter){
									return true;
								}
							}
							
						} else if (parameter2.getParamType().getValue() == ParamType.OBJECT_VALUE) {
							return true;
						} 
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(parameter1.getParamType().getValue() == ParamType.OBJECT_VALUE){
				return true;
			}
		}
		return false;
		
		
	}

	/**
	 * 判断参数组中是否包含对象
	 * @param gruopParam
	 * @param callDepth
	 * @param project
	 * @return
	 */
	public static boolean isContainsObjectParameter( Parameter gruopParam,int callDepth,IARESProject project){
		if(project==null){
			return false;
		}
		String path = gruopParam.getType();
		try {
			IARESResource objectRes = project.findResource(path, "object");
			ARESObject aresObject = objectRes.getInfo(ARESObject.class);
			List<Parameter> properties = aresObject.getProperties();
			for(Parameter parameter:properties){
				if (parameter.getParamType().getValue() == ParamType.PARAM_GROUP_VALUE) {// 如果是参数组则递归调用
					if(callDepth<=5){
						boolean isContainObjectParameter =isContainsObjectParameter(parameter,callDepth+1, project);
						if(isContainObjectParameter){
							return true;
						}
					}
					
				} else if (parameter.getParamType().getValue() == ParamType.OBJECT_VALUE) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * 返回输入或者输出参数
	 * @param parameters
	 * @param retParameter
	 * @param project
	 */
	
	public static void parserParameters( List<Parameter>parameters ,List<Parameter> retParameter,IARESProject project){
		int callDepth = 1;
		for(Parameter parameter1:parameters){
			if(parameter1.getParamType().getValue() == ParamType.PARAM_GROUP_VALUE){
				String path = parameter1.getType();
				try {
					IARESResource objectRes = project.findResource(path, "object");
					ARESObject aresObject = objectRes.getInfo(ARESObject.class);
					List<Parameter> properties = aresObject.getProperties();
					for(Parameter parameter2:properties){
						if (parameter2.getParamType().getValue() == ParamType.PARAM_GROUP_VALUE) {// 如果是参数组则递归调用
							if(callDepth<=5){
								parserParamGroup(parameter2, retParameter,callDepth+1, project);
							}
							
						} else if (parameter2.getParamType().getValue() == ParamType.OBJECT_VALUE) {
							retParameter.add(parameter2);
						} else if (parameter2.getParamType().getValue() == ParamType.STD_FIELD_VALUE) {
							retParameter.add(parameter2);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				retParameter.add(parameter1);
			}
		}
		
		
	}
}
