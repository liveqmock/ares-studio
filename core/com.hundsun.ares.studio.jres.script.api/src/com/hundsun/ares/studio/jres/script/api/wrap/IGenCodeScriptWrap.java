/**
 * 源程序名称：IGenCodeScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：liaogc
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

/**
 * @author liaogc
 *
 */
public interface IGenCodeScriptWrap {
	/**
	 * 
	 * @param type 类型:1:原子,2业务逻辑
	 * @param type genType:1:带中文目录,2:带英文目录,3:不带目录
	 */
	void genModuleCode(int resType,int genType);
}
