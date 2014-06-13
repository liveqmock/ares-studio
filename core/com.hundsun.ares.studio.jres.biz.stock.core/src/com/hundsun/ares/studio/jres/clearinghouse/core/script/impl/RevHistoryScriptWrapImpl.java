/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleResType;
import com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseResScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IRevHistoryScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;
import com.hundsun.ares.studio.jres.script.internal.util.IJSONUtil;
import com.hundsun.ares.studio.jres.script.util.IScriptStringUtil;

/**
 * @author yanwj06282
 *
 */
public class RevHistoryScriptWrapImpl extends CommonScriptWrap<RevisionHistory> implements IRevHistoryScriptWrap {

	public RevHistoryScriptWrapImpl(RevisionHistory history ,IARESResource resource) {
		super(history ,resource);
	}

	public String getVersion(){
		return getOriginalInfo().getVersion();
	}
	
	public String getModifiedDate(){
		return getOriginalInfo().getModifiedDate();
	}
	
	public String getModifiedReason(){
		return getOriginalInfo().getModifiedReason();
	}
	
	public String getModified(){
		return getOriginalInfo().getModified();
	}
	
	public String getModifiedBy(){
		return getOriginalInfo().getModifiedBy();
	}
	
	public String getOrderNumber(){
		return getOriginalInfo().getOrderNumber();
	}
	
	public String getComment(){
		return getOriginalInfo().getComment();
	}
	
	public String getCharger(){
		return getOriginalInfo().getCharger();
	}
	
	@Override
	public String getHistoryComment() {
		List<List<String>> list = new ArrayList<List<String>>();
		
		List<String> content = new ArrayList<String>();
		content.add("--" + getOriginalInfo().getVersion());
		content.add(getOriginalInfo().getModified());
		content.add(getOriginalInfo().getModifiedBy());
//		content.add(IJSONUtil.instance.getStringFromJSON(getOriginalInfo().toJSON(),
//				"Stock3_actionDescription"));
		content.add(getOriginalInfo().getComment());

		list.add(content);
		return IScriptStringUtil.instance.genStringTable(list);
	}

	@Override
	public IDatabaseResScriptWrap getResourceInfo() {
		if (StringUtils.equals(resource.getType(), IDatabaseResType.Table)) {
			return new TableScriptWrapImpl(resource);
		}else if (StringUtils.equals(resource.getType(), IDatabaseResType.View)) {
			return new ViewScriptWrapImpl(resource);
		}else if (StringUtils.equals(resource.getType(), IOracleResType.Sequence)) {
			return new SequenceScriptWrapImpl(resource);
		}else if (StringUtils.equals(resource.getType(), IOracleResType.Trigger)) {
			return new TriggerScriptWrapImpl(resource);
		}
		return null;
	}
	
}
