/**
 * 源程序名称：IBizInterfaceWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.api.biz;

/**
 * @author sundl
 *
 */
public interface IBizInterfaceWrap {
	/**
	 * 对象号
	 * @return
	 */
	String getId();
	
	/**
	 * 中文名
	 * @return
	 */
	String getChineseName();
	
	/**
	 * 接口标志
	 * @return
	 */
	String getInterfaceFlag();
	
	/**
	 * 说明信息
	 * @return
	 */
	String getDescription();
	
	/**
	 * 版本信息，即修改记录中的最大版本号
	 * @return
	 */
	String getVersion();
	
	/**
	 * 修改日期，修改记录中最近修改日期
	 * @return
	 */
	String getUpdateDate();
	
	/**
	 * 是否输入结果集
	 * 
	 * @return
	 */
	boolean isInputCollection();
	
	/**
	 * 是否输出结果集
	 * 
	 * @return
	 */
	boolean isOutputCollection();
	
	/**
	 * 获取输入参数
	 * 
	 * @return
	 */
	IParameterWrap[] getInputParameters();
	
	/**
	 * 获取输出参数
	 * 
	 * @return
	 */
	IParameterWrap[] getOutputParameters();
	
	/**
	 * 根据参数ID ，删除输入参数
	 * 
	 * @param id
	 */
	void deleteInputParameter(String id);
	
	/**
	 * 根据参数ID ，删除输出参数
	 * 
	 * @param id
	 */
	void deleteOutputParameter(String id);
}
