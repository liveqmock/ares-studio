/**
 * 源程序名称：OracleTriggerAdapter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.service;

import com.hundsun.ares.studio.jres.database.oracle.service.IOracleTrigger;
import com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData;

/**
 * @author wangxh
 *
 */
public class OracleTriggerAdapter implements IOracleTrigger {

	protected final TriggerResourceData triggerResourceData;
	
	
	public OracleTriggerAdapter(TriggerResourceData triggerResourceData) {
		super();
		this.triggerResourceData = triggerResourceData;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleTrigger#getName()
	 */
	@Override
	public String getName() {
		return triggerResourceData.getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleTrigger#getChineseName()
	 */
	@Override
	public String getChineseName() {
		return triggerResourceData.getChineseName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleTrigger#getDescription()
	 */
	@Override
	public String getDescription() {
		return triggerResourceData.getDescription();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleTrigger#getSQL()
	 */
	@Override
	public String getSQL() {
		return triggerResourceData.getSql();
	}

}
