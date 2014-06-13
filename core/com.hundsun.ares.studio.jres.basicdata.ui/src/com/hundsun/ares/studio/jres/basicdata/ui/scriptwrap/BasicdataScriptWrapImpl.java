/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.scriptwrap;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.JRESContextManager;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.script.api.wrap.IBasicdataScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMasterSlaveLinkTableScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMasterSlaveTableScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ISingleTableScriptWrap;
import com.hundsun.ares.studio.jres.script.tool.JRESResourceHelper;

/**
 * @author lvgao
 *
 */
public class BasicdataScriptWrapImpl  implements IBasicdataScriptWrap{

	IARESProject project;
	
	public BasicdataScriptWrapImpl(IARESProject project){
		this.project = project;
	}
	
	@Override
	public ISingleTableScriptWrap[] getAllTableBasicData() {
		Object[] objs = JRESContextManager.getStatisticProvider(project).getResouceByType(IBasicDataRestypes.singleTable);
		List<ISingleTableScriptWrap> tlist = new ArrayList<ISingleTableScriptWrap>();
		for(Object obj:objs){
			IARESResource resource = JRESResourceHelper.getResource(obj);
			if(null != resource){
				tlist.add(new SingleTableScriptWrapImpl(resource));
			}
		}
		return tlist.toArray(new ISingleTableScriptWrap[0]);
	}

	@Override
	public ISingleTableScriptWrap getTableBasicDataByName(String name) {
		Object[] objs = JRESContextManager.getStatisticProvider(project).getResouce(name, IResourceTable.Scope_IGNORE_NAMESPACE, IBasicDataRestypes.singleTable);
		if(objs.length > 0){
			IARESResource resource = JRESResourceHelper.getResource(objs[0]);
			return new SingleTableScriptWrapImpl(resource);
		}
		return null;
	}

	@Override
	public ISingleTableScriptWrap[] getAllMetaDataBasicData() {
		Object[] objs = JRESContextManager.getStatisticProvider(project).getResouceByType(IBasicDataRestypes.STDModelAndData);
		List<Object> tlist = new ArrayList<Object>();
		for(Object obj:objs){
			IARESResource resource = JRESResourceHelper.getResource(obj);
			if(null != resource){
				tlist.add(new MetaDataBasicDataScriptWrapImpl(resource));
			}
		}
		return tlist.toArray(new ISingleTableScriptWrap[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IBasicdataScriptWrap#getMetaDataBasicDataByName(java.lang.String)
	 */
	@Override
	public ISingleTableScriptWrap getMetaDataBasicDataByName(String name) {
		Object[] objs = JRESContextManager.getStatisticProvider(project).getResouce(name, IResourceTable.Scope_IGNORE_NAMESPACE, IBasicDataRestypes.STDModelAndData);
		if(objs.length > 0){
			IARESResource resource = JRESResourceHelper.getResource(objs[0]);
			return new MetaDataBasicDataScriptWrapImpl(resource);
		}
		return null;
	}

	@Override
	public ISingleTableScriptWrap[] getTableBasicDataBySubsys(String subsysName) {
		return getModuleResource(subsysName , true);
	}

	@Override
	public IMasterSlaveTableScriptWrap[] getAllMasterSlaveTableBasicData() {
		Object[] objs = JRESContextManager.getStatisticProvider(project).getResouceByType(IBasicDataRestypes.MasterSlaveTable);
		List<IMasterSlaveTableScriptWrap> tlist = new ArrayList<IMasterSlaveTableScriptWrap>();
		for(Object obj:objs){
			IARESResource resource = JRESResourceHelper.getResource(obj);
			if(null != resource){
				tlist.add(new MasterSlaveTableScriptWrapImpl(resource));
			}
		}
		return tlist.toArray(new IMasterSlaveTableScriptWrap[0]);
	}

	@Override
	public IMasterSlaveLinkTableScriptWrap[] getAllMasterSlaveLinkTableBasicData() {
		Object[] objs = JRESContextManager.getStatisticProvider(project).getResouceByType(IBasicDataRestypes.MasterSlaveLinkTable);
		List<IMasterSlaveLinkTableScriptWrap> tlist = new ArrayList<IMasterSlaveLinkTableScriptWrap>();
		for(Object obj:objs){
			IARESResource resource = JRESResourceHelper.getResource(obj);
			if(null != resource){
				tlist.add(new MasterSlaveLinkTableScriptWrapImpl(resource));
			}
		}
		return tlist.toArray(new IMasterSlaveLinkTableScriptWrap[0]);
	}

	@Override
	public IMasterSlaveTableScriptWrap[] getMasterSlaveTableBasicDataBySubsys(
			String subsysName) {
		return getModuleMasterSlaveTableBasicData(subsysName, true);
	}

	@Override
	public IMasterSlaveTableScriptWrap[] getMasterSlaveTableBasicDataByModule(
			String moduleName) {
		return getModuleMasterSlaveTableBasicData(moduleName, false);
	}
	
	@Override
	public IMasterSlaveLinkTableScriptWrap[] getMasterSlaveLinkTableBasicDataBySubsys(
			String subsysName) {
		return getModuleMasterSlaveLinkTableBasicData(subsysName, true);
	}

	@Override
	public IMasterSlaveLinkTableScriptWrap[] getMasterSlaveLinkTableBasicDataByModule(
			String moduleName) {
		return getModuleMasterSlaveLinkTableBasicData(moduleName, false);
	}
	
	@Override
	public IMasterSlaveTableScriptWrap getMasterSlaveTableBasicDataByName(String name) {
		Object[] objs = JRESContextManager.getStatisticProvider(project).getResouce(name, IResourceTable.Scope_IGNORE_NAMESPACE, IBasicDataRestypes.MasterSlaveTable);
		if(objs.length > 0){
			IARESResource resource = JRESResourceHelper.getResource(objs[0]);
			return new MasterSlaveTableScriptWrapImpl(resource);
		}
		return null;
	}

	@Override
	public IMasterSlaveLinkTableScriptWrap getMasterSlaveLinkTableBasicDataByName(String name){
		Object[] objs = JRESContextManager.getStatisticProvider(project).getResouce(name, IResourceTable.Scope_IGNORE_NAMESPACE, IBasicDataRestypes.MasterSlaveLinkTable);
		if(objs.length > 0){
			IARESResource resource = JRESResourceHelper.getResource(objs[0]);
			return new MasterSlaveLinkTableScriptWrapImpl(resource);
		}
		return null;
	}

	@Override
	public ISingleTableScriptWrap[] getTableBasicDataByModule(
			String moduleName) {
		return getModuleResource(moduleName , false);
	}
	
	private ISingleTableScriptWrap[] getModuleResource(String moduleName ,boolean recursion) {
		List<Object> tlist = new ArrayList<Object>();
		try {
			IARESModuleRoot moduleRoot = project.getModuleRoot("basicdata");
			if (moduleRoot == null) {
				moduleRoot = project.getModuleRoot("commondata");
			}
			if (moduleRoot != null) {
				IARESModule module = moduleRoot.getModule(moduleName);
				if (module != null) {
					IARESResource[] reses = module.getARESResources(IBasicDataRestypes.singleTable, recursion);
					for(IARESResource resource : reses){
						tlist.add(new SingleTableScriptWrapImpl(resource));
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return tlist.toArray(new ISingleTableScriptWrap[0]);
	}
	
	private IMasterSlaveTableScriptWrap[] getModuleMasterSlaveTableBasicData(
			String moduleName ,boolean recursion) {
		List<Object> tlist = new ArrayList<Object>();
		try {
			IARESModuleRoot moduleRoot = project.getModuleRoot("basicdata");
			if (moduleRoot == null) {
				moduleRoot = project.getModuleRoot("commondata");
			}
			if (moduleRoot != null) {
				IARESModule module = moduleRoot.getModule(moduleName);
				if (module != null) {
					IARESResource[] reses = module.getARESResources(IBasicDataRestypes.MasterSlaveTable, recursion);
					for(IARESResource resource : reses){
						tlist.add(new MasterSlaveTableScriptWrapImpl(resource));
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return tlist.toArray(new IMasterSlaveTableScriptWrap[0]);
	}
	
	public IMasterSlaveLinkTableScriptWrap[] getModuleMasterSlaveLinkTableBasicData(
			String moduleName,boolean recursion) {
		List<Object> tlist = new ArrayList<Object>();
		try {
			IARESModuleRoot moduleRoot = project.getModuleRoot("basicdata");
			if (moduleRoot == null) {
				moduleRoot = project.getModuleRoot("commondata");
			}
			if (moduleRoot != null) {
				IARESModule module = moduleRoot.getModule(moduleName);
				if (module != null) {
					IARESResource[] reses = module.getARESResources(IBasicDataRestypes.MasterSlaveLinkTable, recursion);
					for(IARESResource resource : reses){
						tlist.add(new MasterSlaveLinkTableScriptWrapImpl(resource));
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return tlist.toArray(new IMasterSlaveLinkTableScriptWrap[0]);
	}
	
}
