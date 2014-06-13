/**
 * 源程序名称：DatabaseScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleResType;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.script.api.database.IDatabaseUserScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ISequenceScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableSpaceScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITriggerScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.IViewScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseResScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ITableRevHistoryScriptWrap;
import com.hundsun.ares.studio.jres.script.internal.util.IJSONUtil;
import com.hundsun.ares.studio.jres.script.tool.HistoryCommentCompator;
import com.hundsun.ares.studio.jres.script.util.IScriptStringUtil;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author lvgao
 *
 */
public class DatabaseScriptWrap implements IDatabaseScriptWrap{

	private IARESProject project;
	
	/**
	 * @param input
	 */
	public DatabaseScriptWrap(IARESProject input) {
		this.project = input;
	}

	@Override
	public ITableScriptWrap[] getAllTable() {
		List<ReferenceInfo> infoListList = ReferenceManager.getInstance().getReferenceInfos(project, IDatabaseRefType.Table, true);
		List<Object> tlist = new ArrayList<Object>();
		for(ReferenceInfo referenceInfo:infoListList){
			IARESResource resource = referenceInfo.getResource();
			if(null != resource){
				tlist.add(new TableScriptWrapImpl(resource));
			}
		}
		return tlist.toArray(new ITableScriptWrap[0]);
	}

	@Override
	public IViewScriptWrap[] getAllView() {
		List<ReferenceInfo> infoListList = ReferenceManager.getInstance().getReferenceInfos(project, IDatabaseRefType.View, true);
		List<Object> tlist = new ArrayList<Object>();
		for(ReferenceInfo referenceInfo:infoListList){
			IARESResource resource = referenceInfo.getResource();
			if(null != resource){
				tlist.add(new ViewScriptWrapImpl(resource));
			}
		}
		return tlist.toArray(new IViewScriptWrap[0]);
	}
	
	@Override
	public ISequenceScriptWrap[] getAllSequence() {
		List<ReferenceInfo> infoListList = ReferenceManager.getInstance().getReferenceInfos(project, IOracleRefType.Sequence, true);
		List<Object> tlist = new ArrayList<Object>();
		for(ReferenceInfo referenceInfo:infoListList){
			IARESResource resource = referenceInfo.getResource();
			if(null != resource){
				tlist.add(new SequenceScriptWrapImpl(resource));
			}
		}
		return tlist.toArray(new ISequenceScriptWrap[0]);
	}
	
	@Override
	public ITriggerScriptWrap[] getAllTrigger() {
		List<ReferenceInfo> infoListList = ReferenceManager.getInstance().getReferenceInfos(project, IOracleRefType.Trigger, true);
		List<Object> tlist = new ArrayList<Object>();
		for(ReferenceInfo referenceInfo:infoListList){
			IARESResource resource = referenceInfo.getResource();
			if(null != resource){
				tlist.add(new TriggerScriptWrapImpl(resource));
			}
		}
		return tlist.toArray(new ITriggerScriptWrap[0]);
	}
	
	@Override
	public ITableScriptWrap[] getTableByName(String name) {
		List<ITableScriptWrap> tables = new ArrayList<ITableScriptWrap>();
		List<ReferenceInfo> refs = ReferenceManager.getInstance().getReferenceInfos(project, IDatabaseRefType.Table, name, true);
		for(ReferenceInfo ref : refs){
			IARESResource resource = ref.getResource();
			if(null != resource){
				tables.add(new TableScriptWrapImpl(resource));
			}
		}
		return tables.toArray(new ITableScriptWrap[0]);
	}

	@Override
	public IViewScriptWrap[] getViewByName(String name) {
		List<IViewScriptWrap> views = new ArrayList<IViewScriptWrap>();
		List<ReferenceInfo> refs = ReferenceManager.getInstance().getReferenceInfos(project, IDatabaseRefType.View, name, true);
		for(ReferenceInfo ref : refs){
			IARESResource resource = ref.getResource();
			if(null != resource){
				views.add(new ViewScriptWrapImpl(resource));
			}
		}
		return views.toArray(new IViewScriptWrap[0]);
	}
	
	@Override
	public IDatabaseResScriptWrap[] getAllDatabaseResources() {
		List<IDatabaseResScriptWrap> objs = new ArrayList<IDatabaseResScriptWrap>();
		objs.addAll((Collection<? extends IDatabaseResScriptWrap>) Arrays.asList(getAllTable()));
		objs.addAll((Collection<? extends IDatabaseResScriptWrap>) Arrays.asList(getAllView()));
		objs.addAll((Collection<? extends IDatabaseResScriptWrap>) Arrays.asList(getAllSequence()));
		objs.addAll((Collection<? extends IDatabaseResScriptWrap>) Arrays.asList(getAllTrigger()));
		Collections.sort(objs, new DatabaseInfoCompartor());
		return objs.toArray(new IDatabaseResScriptWrap[objs.size()]);
	}

	public IDatabaseResScriptWrap[] getAllDatabaseResourcesBySubsys(String moduleName){
		return getModuleResources(moduleName, true);
	}
	
	@Override
	public String getAllHistoriesCommentBySubsys(String subsysName , String cont) {
		return getModuleAllHistoriesComment(subsysName, cont, true);
	}
	
	@Override
	public String getAllHistoriesCommentByModule(String subsysName , String cont) {
		return getModuleAllHistoriesComment(subsysName, cont, false);
	}
	
	@Override
	public ITableRevHistoryScriptWrap[] getAllHistoriesBySubsys(String subsysName) {
		return getModuleAllHistories(subsysName, true);
	}
	
	@Override
	public ITableRevHistoryScriptWrap[] getAllHistoriesByModule(String moduleName) {
		return getModuleAllHistories(moduleName, false);
	}
	
	class DatabaseInfoCompartor implements Comparator<IDatabaseResScriptWrap> {

		@Override
		public int compare(IDatabaseResScriptWrap o1, IDatabaseResScriptWrap o2) {
			String o1id = o1.getObjectId();
			String o2id = o2.getObjectId();
			try {
				int o1v = 0;
				int o2v = 0;
				if (StringUtils.isNotBlank(o1id)) {
					o1v = Integer.parseInt(o1id);
				}
				if (StringUtils.isNotBlank(o2id)) {
					o2v = Integer.parseInt(o2id);
				}
				return o1v-o2v;
			} catch (Exception e) {
			}
			return 0;
		}
	}

	@Override
	public ITableSpaceScriptWrap getTableSpace() {
		try {
			IARESResource res = project.findResource("dbobject", "dbobject");
			if (res != null) {
				return new TableSpaceScriptWrapImpl(res);
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IDatabaseUserScriptWrap getDBUser() {
		try {
			IARESResource res = project.findResource("dbuser", "dbuser");
			if (res != null) {
				return new DatabaseUserScriptWrapImpl(res);
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public IDatabaseResScriptWrap[] getAllDatabaseResourcesByModule(String moduleName){
		return getModuleResources(moduleName , false);
	}
	
	private IDatabaseResScriptWrap[] getModuleResources(String moduleName ,boolean recursion){
		List<IDatabaseResScriptWrap> tlist = new ArrayList<IDatabaseResScriptWrap>();
		try {
			IARESModuleRoot moduleRoot = project.getModuleRoot("database");
			IARESModule module = moduleRoot.getModule(moduleName);
			if (module != null) {
				IARESResource[] reses = module.getARESResources(IDatabaseResType.Table, recursion);
				for(IARESResource resource : reses){
					tlist.add(new TableScriptWrapImpl(resource));
				}
				reses = module.getARESResources(IDatabaseResType.View, recursion);
				for(IARESResource resource : reses){
					tlist.add(new ViewScriptWrapImpl(resource));
				}
				reses = module.getARESResources(IOracleResType.Sequence, recursion);
				for(IARESResource resource : reses){
					tlist.add(new SequenceScriptWrapImpl(resource));
				}
				reses = module.getARESResources(IOracleResType.Trigger, recursion);
				for(IARESResource resource : reses){
					tlist.add(new TriggerScriptWrapImpl(resource));
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		Collections.sort(tlist, new DatabaseInfoCompartor());
		return tlist.toArray(new IDatabaseResScriptWrap[0]);
	}
	
	class IRevHistoryScriptWrapCompartor implements Comparator<ITableRevHistoryScriptWrap> {

		@Override
		public int compare(ITableRevHistoryScriptWrap o1, ITableRevHistoryScriptWrap o2) {
			RevisionHistory rh1 = ((TableRevHistoryScriptWrapImpl)o1).getOriginalInfo();
			RevisionHistory rh2 = ((TableRevHistoryScriptWrapImpl)o2).getOriginalInfo();
			String v1 = rh1.getVersion();
			String v2 = rh2.getVersion();
			
			if (StringUtils.equals(v1, v2)) {
				return HistoryCommentCompator.compareDate(rh1.getModifiedDate(), rh2.getModifiedDate() ,1);
			}
			
			if (HistoryCommentCompator.compareVersion(v2, v1)) {
				return -1;
			}else {
				return 1;
			}
		}
	}

	private ITableRevHistoryScriptWrap[] getModuleAllHistories(String moduleName ,boolean recursion) {
		List<ITableRevHistoryScriptWrap> historyWraps = new ArrayList<ITableRevHistoryScriptWrap>();
		try {
			IARESModuleRoot moduleRoot = project.getModuleRoot("database");
			IARESModule module = moduleRoot.getModule(moduleName);
			if (module != null) {
				IARESResource[] reses = module.getARESResources(IDatabaseResType.Table, recursion);
				for(IARESResource resource : reses){
					TableResourceData tableInfo = resource.getInfo(TableResourceData.class);
					for (RevisionHistory his : tableInfo.getHistories()){
						if (HistoryCommentCompator.compareVersion(his.getVersion(), project.getProjectProperty().getVersion())) {
							historyWraps.add(new TableRevHistoryScriptWrapImpl(his, resource));
						}
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		//排序,从前到后
		Collections.sort(historyWraps, new IRevHistoryScriptWrapCompartor());
		
		return historyWraps.toArray(new ITableRevHistoryScriptWrap[0]);
	}
	
	private String getModuleAllHistoriesComment(String subsysName , String cont , boolean recusion) {

		List<List<String>> list = new ArrayList<List<String>>();

		{
			List<String> content = new ArrayList<String>();
			content.add("-- 修改版本"+"   ");
			content.add("修改日期"+"   ");
			content.add("修改单"+"        ");
			content.add("申请人"+"   ");
			content.add("负责人"+"   ");
			content.add("修改内容");
			content.add("备注");
			list.add(content);
		}

		List<RevisionHistory> histories = new ArrayList<RevisionHistory>();
		try {
			IARESModuleRoot moduleRoot = project.getModuleRoot("database");
			IARESModule module = moduleRoot.getModule(subsysName);
			if (module != null) {
				IARESResource[] reses = module.getARESResources(IDatabaseResType.Table, recusion);
				for(IARESResource resource : reses){
					TableResourceData tableInfo = resource.getInfo(TableResourceData.class);
					for (RevisionHistory his : tableInfo.getHistories()){
						if (HistoryCommentCompator.compareVersion(his.getVersion(), project.getProjectProperty().getVersion())) {
							histories.add(his);
						}
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		//排序,从前到后
		Collections.reverse(histories);
		Collections.sort(histories, new HistoryCommentCompator());
		for (RevisionHistory his : histories) {
			String version = his.getVersion();
			if (StringUtils.indexOf(cont, version) < 0) {
				continue;
			}
			List<String> content = new ArrayList<String>();
			content.add("-- V" + his.getVersion()+"   ");
			String modifyDate = his.getModifiedDate();
			String newDate = StringUtils.substring(modifyDate, 0, 10).replaceAll("-", "");
			content.add(newDate+"   ");
			content.add(his.getOrderNumber()+"        ");
			content.add(his.getModifiedBy()+"   ");
			content.add(his.getCharger()+"   ");
			content.add(IJSONUtil.instance.getStringFromJSON(his.toJSON(),
					"Stock3_actionDescription"));
			content.add(his.getComment());

			list.add(content);
		}

		return IScriptStringUtil.instance.genStringTable(list)+"\r\n";
	}
	
}
