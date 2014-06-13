/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.InternalParam;
import com.hundsun.ares.studio.biz.Parameter;

/**内部变量(数组)帮助类
 * @author liaogc
 *
 */
public class InternalParameterArrayHelper {
	/**
	 * 判断参数是否是内部变量中的数组参数
	 * @param parameter
	 * @param internalParameter
	 * @return
	 */
	public static boolean  isArrayParameter(Parameter parameter,List<InternalParam> internalParameter){
		if(internalParameter.contains(parameter)){
			String type = parameter.getType();
			int index1 = StringUtils.indexOf(type, "[");
			int index2 = StringUtils.indexOf(type, "]");
			if(index2>index1 && StringUtils.endsWith(type, "]")){
				return true;
			}
		}
		return false;
	}
	
	public static Parameter  getInternalParameter(String paramName,List<InternalParam> internalParameter){
		for(Parameter parameter:internalParameter){
			if(StringUtils.equals(parameter.getId(), paramName)){
				return parameter;
			}
		}
		return null;
	}
	/**
	 * 获得内部变量业务数据类型
	 * @param parameter
	 * @return
	 */
	public static String getArrayBusType(Parameter parameter){
		String type = parameter.getType();
		int index1 = StringUtils.indexOf(type, "[");
		int index2 = StringUtils.indexOf(type, "]");
		if(index2>index1 && StringUtils.endsWith(type, "]")){
			return StringUtils.substring(type, 0, index1);
		}
		return StringUtils.EMPTY;
	}
	/**
	 * 获得业务数据类型一维长度
	 * @param parameter
	 * @return
	 */
	public static String getArrayLength(Parameter parameter){
		String type = parameter.getType();
		int index1 = StringUtils.indexOf(type, "[");
		int index2 = StringUtils.indexOf(type, "]");
		if(index2>index1 && StringUtils.endsWith(type, "]")){
			return StringUtils.substring(type, index1+1, index2);
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 获得数据的真实类型
	 * @param type
	 * @return
	 */
	public static String getArrayDataType(String type){
		int index1 = StringUtils.indexOf(type, "[");
		int index2 = StringUtils.indexOf(type, "]");
		if(index2>index1 && StringUtils.endsWith(type, "]")){
			return StringUtils.substring(type, 0, index1);
		}
		return type;
	}
}
