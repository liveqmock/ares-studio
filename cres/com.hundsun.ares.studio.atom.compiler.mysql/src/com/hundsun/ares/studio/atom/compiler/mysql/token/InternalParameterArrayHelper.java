/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

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
	 * 判断内部变量数据类型是否为数组
	 * @param var 内部变量
	 * @return boolean
	 */
	public static boolean  isArrayParameter(InternalParam var){
			String type = var.getType();
			int index1 = StringUtils.indexOf(type, "[");
			int index2 = StringUtils.indexOf(type, "]");
			if(index2>index1 && StringUtils.endsWith(type, "]")){
				return true;
			}
		return false;
	}
	
	/**
	 * 获得内部变量业务类型（去除数组长度）
	 * @param var 内部变量
	 * @return String 业务类型
	 */
	public static String getArrayBusType(InternalParam var){
		String type = var.getType();
		int index1 = StringUtils.indexOf(type, "[");
		int index2 = StringUtils.indexOf(type, "]");
		if(index2>index1 && StringUtils.endsWith(type, "]")){
			return StringUtils.substring(type, 0, index1);
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 获得内部变量业务类型的数组长度
	 * @param var
	 * @return
	 */
	public static String getArrayLength(InternalParam var){
		String type = var.getType();
		int index1 = StringUtils.indexOf(type, "[");
		int index2 = StringUtils.indexOf(type, "]");
		if(index2>index1 && StringUtils.endsWith(type, "]")){
			return StringUtils.substring(type, index1+1, index2);
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 获得业务类型（去除数组长度）
	 * @param bizType 业务类型（含数组长度）
	 * @return String 业务类型
	 */
	public static String getArrayDataType(String bizType){
		int index1 = StringUtils.indexOf(bizType, "[");
		int index2 = StringUtils.indexOf(bizType, "]");
		if(index2>index1 && StringUtils.endsWith(bizType, "]")){
			return StringUtils.substring(bizType, 0, index1);
		}
		return bizType;
	}
}
