/**
 * 源程序名称：ViewScriptWrapImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.model.chouse.util.RevHistoryCompator;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.script.api.database.IViewScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IRevHistoryScriptWrap;
import com.hundsun.ares.studio.jres.script.internal.util.IJSONUtil;
import com.hundsun.ares.studio.jres.script.tool.HistoryCommentCompator;

/**
 * @author yanwj06282
 *
 */
public class ViewScriptWrapImpl extends DatabaseResScriptWrapImpl implements IViewScriptWrap {

	ViewResourceData view ;
	
	public ViewScriptWrapImpl(IARESResource resource) {
		super(resource);
	}

	@Override
	public String getViewName() {
		return getOriginalInfo().getName();
	}
	
	@Override
	public String getChineseName() {
		return getOriginalInfo().getChineseName();
	}
	
	@Override
	public String getTableType() {
		return "视图";
	}

	@Override
	public String getTableSpace(String prefix) {
		return IJSONUtil.instance.getStringFromJSON(toJSON(), "Oracle_space");
	}

	@Override
	public String getSql() {
		ViewResourceData view = getOriginalInfo();
		if (view != null) {
			return view.getSql();
		}
		return StringUtils.EMPTY;
	}

	@Override
	public ViewResourceData getOriginalInfo() {
		if (view == null) {
			try {
				view = resource.getInfo(ViewResourceData.class);
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return view;
	}
	
	@Override
	public String getObjectId() {
		return StringUtils.defaultString(getOriginalInfo().getObjectId());
	}
	
	public IRevHistoryScriptWrap[] getHistories(){
		List<IRevHistoryScriptWrap>	hisWraps = new ArrayList<IRevHistoryScriptWrap>();
		List<RevisionHistory> histories = new ArrayList<RevisionHistory>();
		
		for (RevisionHistory his : getOriginalInfo().getHistories()){
			try {
				if (HistoryCommentCompator.compareVersion(his.getVersion(), resource.getARESProject().getProjectProperty().getVersion())) {
					histories.add(his);
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		//排序,从前到后
		Collections.sort(histories, new RevHistoryCompator());
		for (RevisionHistory his : histories){
			hisWraps.add(new TableRevHistoryScriptWrapImpl(his, resource));
		}
		return hisWraps.toArray(new IRevHistoryScriptWrap[0]);
	}
	
	@Override
	public boolean isGenHisTable() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseResScriptWrap#setObjectId(java.lang.String)
	 */
	@Override
	public void setObjectId(String objectId) {
		getOriginalInfo().setObjectId(objectId);
		
	}
	
}
