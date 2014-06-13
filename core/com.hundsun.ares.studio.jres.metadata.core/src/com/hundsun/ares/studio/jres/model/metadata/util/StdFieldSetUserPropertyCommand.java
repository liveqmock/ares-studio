/**
 * 源程序名称：StdFieldSetUserPropertyCommand.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.util;

import com.hundsun.ares.studio.core.model.extendable.ExtensibleModelUtil;
import com.hundsun.ares.studio.core.util.log.Log;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;

public class StdFieldSetUserPropertyCommand extends AbstractMetadataModifyCommand {
	
	String key;
	String value;
	
	public StdFieldSetUserPropertyCommand(String id, String key, String value) {
		super(id);
		this.key = key;
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.metadata.util.MetadataModifyOperation.MetadataModifyCommand#excute(com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData, com.hundsun.ares.studio.core.util.log.Log)
	 */
	@Override
	public void excute(MetadataItem data, Log log) {
		ExtensibleModelUtil.setUserExtendedProperty(data, key, value);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.metadata.util.IMetadataModifyCommand#getDescription()
	 */
	@Override
	public String getDescription() {
		return String.format("设置用户属性'%s'为'%s'", key, value);
	}
	
}