/**
 * 源程序名称：TableSpaceScriptWrapImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.script.api.database.ITableSpaceItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableSpaceScriptWrap;
import com.hundsun.ares.studio.jres.script.base.ResourceWrapBase;

/**
 * @author yanwj06282
 *
 */
public class TableSpaceScriptWrapImpl extends ResourceWrapBase<OracleSpaceResourceData> implements ITableSpaceScriptWrap{

	public TableSpaceScriptWrapImpl(IARESResource resource) {
		super(resource);
	}

	public ITableSpaceItemScriptWrap[] getSpace(){
		List<ITableSpaceItemScriptWrap> spaces = new ArrayList<ITableSpaceItemScriptWrap>();
		for (TableSpace space : getOriginalInfo().getSpaces()){
			spaces.add(new TableSpaceItemScriptWrapImpl(space , resource));
		}
		return spaces.toArray(new ITableSpaceItemScriptWrap[spaces.size()]);
	}

	@Override
	public Class<OracleSpaceResourceData> getInfoClass() {
		return OracleSpaceResourceData.class;
	}
	
}
