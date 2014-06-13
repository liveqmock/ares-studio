/**
 * 源程序名称：TableSpaceItemScriptWrapImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.script.api.database.ITableSpaceItemScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class TableSpaceItemScriptWrapImpl extends CommonScriptWrap<TableSpace> implements
		ITableSpaceItemScriptWrap {

	public TableSpaceItemScriptWrapImpl(TableSpace space , IARESResource resource) {
		super(space ,resource);
	}

}
