/**
 * 源程序名称：BizParameterUtil.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：qinyuan
 */
package com.hundsun.ares.studio.biz.util;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.Parameter;

/**
 * 服务接口输入输出参数帮助类
 * 
 * 主要判断一个字段是否在输入输出参数中
 * @author qinyuan
 */
public class BizParameterUtil {
	
	public static final String FLAG_IO = "IO";
	
	/**
	 * 是否为输入参数
	 * @param biz 服务接口资源
	 * @param field 字段名
	 * @return
	 */
	public static boolean isInputParameter(BizInterface biz, String field){
		for (Parameter p : biz.getInputParameters()) {
			if(StringUtils.equals(p.getId(), field)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否为输出参数
	 * @param biz 服务接口资源
	 * @param field 字段名
	 * @return
	 */
	public static boolean isOutputParameter(BizInterface biz, String field){
		for (Parameter p : biz.getOutputParameters()) {
			if(StringUtils.equals(p.getId(), field)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否为带'IO'标志的输出参数
	 * @param biz 服务接口资源
	 * @param field 字段名
	 * @return
	 */
	public static boolean isOutputParameterWithIO(BizInterface biz, String field){
		for (Parameter p : biz.getOutputParameters()) {
			if(StringUtils.equals(p.getId(), field) && 
					StringUtils.equalsIgnoreCase(p.getFlags(), FLAG_IO)){
				return true;
			}
		}
		return false;
	}

}
