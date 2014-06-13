/**
 * 源程序名称：IMetadataModifyCommand.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.model.metadata.util;

import com.hundsun.ares.studio.core.util.log.Log;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;

/**
 * 一个command 处理一个条目
 * @author sundl
 *
 */
public interface IMetadataModifyCommand {
	
	/**
	 * command 处理一个条目， 这个方法确定要处理哪条
	 * @return
	 */
	String getId();
	
	/**
	 * 这个命令的描述信息
	 * @return
	 */
	String getDescription();

	void excute(MetadataItem data, Log log);
}
