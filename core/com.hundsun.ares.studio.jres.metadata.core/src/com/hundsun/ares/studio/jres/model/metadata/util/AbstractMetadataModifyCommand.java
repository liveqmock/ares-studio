/**
 * 源程序名称：AbstractMetadataModifyCommand.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.model.metadata.util;

/**
 * @author sundl
 *
 */
public abstract class AbstractMetadataModifyCommand implements IMetadataModifyCommand{

	private String id;
	
	public AbstractMetadataModifyCommand(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
}
