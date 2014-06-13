/**
 * 源程序名称：IGenCode4UFTScriptWrap.java
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
public interface IGenCode4UFTScriptWrap {
	/**
	 * 
	 * @param type 0:为生成对象代,，1:为生成业务逻辑代码，2:为生成对象结构体 ,3:为原子代码
	 * @param type genType:1:带中文目录,2:不带目录
	 */
	void gen4UFTCode(int resType,int genType);

}
