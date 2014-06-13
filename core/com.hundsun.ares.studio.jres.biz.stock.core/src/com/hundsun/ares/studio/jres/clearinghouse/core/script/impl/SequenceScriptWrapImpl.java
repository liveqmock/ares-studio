/**
 * 源程序名称：SequenceScriptWrapImpl.java
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
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;
import com.hundsun.ares.studio.jres.script.api.database.ISequenceScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IRevHistoryScriptWrap;
import com.hundsun.ares.studio.jres.script.tool.HistoryCommentCompator;

/**
 * @author yanwj06282
 *
 */
public class SequenceScriptWrapImpl extends DatabaseResScriptWrapImpl implements ISequenceScriptWrap{

	private SequenceResourceData sequence;
	
	public SequenceScriptWrapImpl(IARESResource resource) {
		super(resource);
	}

	public String getCache(){
		return getOriginalInfo().getCache();
	}
	
	public boolean isCycle(){
		return getOriginalInfo().isCycle();
	}
	
	public boolean isUseCache(){
		return getOriginalInfo().isUseCache();
	}
	
	public String getObjectId(){
		return getOriginalInfo().getObjectId();
	}
	
	public String getChineseName(){
		return getOriginalInfo().getChineseName();
	}
	
	public String getStart(){
		return getOriginalInfo().getStart();
	}
	
	public String getTableName(){
		return getOriginalInfo().getTableName();
	}
	
	public String getIncrement(){
		return getOriginalInfo().getIncrement();
	}
	
	public String getMaxValue(){
		return getOriginalInfo().getMaxValue();
	}
	
	public String getMinValue(){
		return getOriginalInfo().getMinValue();
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
			hisWraps.add(new RevHistoryScriptWrapImpl(his, resource));
		}
		return hisWraps.toArray(new IRevHistoryScriptWrap[0]);
	}
	
	@Override
	public SequenceResourceData getOriginalInfo() {
		if (sequence == null) {
			try {
				sequence = resource.getInfo(SequenceResourceData.class);
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return sequence;
	}

	@Override
	public String getSeqName() {
		return getOriginalInfo().getName();
	}

	@Override
	public boolean isGenHisTable() {
		return getOriginalInfo().isIsHistory();
	}

	@Override
	public String getTableSpace() {
		return StringUtils.defaultString(getExtendsValue("Oracle_space"));
	}

	@Override
	public void setObjectId(String objectId) {
		getOriginalInfo().setObjectId(objectId);
	}
	
}
