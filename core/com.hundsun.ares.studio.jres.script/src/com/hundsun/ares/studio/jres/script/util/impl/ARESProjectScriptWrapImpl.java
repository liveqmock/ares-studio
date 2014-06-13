/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFolder;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.script.api.biz.IBizObjectWrap;
import com.hundsun.ares.studio.jres.script.api.biz.IBizScriptWrap;
import com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap;
import com.hundsun.ares.studio.jres.script.api.biz.IObjScriptWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicFunctionWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicServiceWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.IProcedureWrap;
import com.hundsun.ares.studio.jres.script.api.database.IDatabaseUserScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ISequenceScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableSpaceScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITriggerScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.IViewScriptWrap;
import com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory;
import com.hundsun.ares.studio.jres.script.api.wrap.IARESModuleRootWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IARESModuleWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IARESProjectScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IBasicdataScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IBusinessResScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IBusinessScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseResScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IGenCode4UFTScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IGenCodeScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ILogicScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMasterSlaveLinkTableScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMasterSlaveTableScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataResScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IProcedureScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IScriptCallScriptWarp;
import com.hundsun.ares.studio.jres.script.api.wrap.IScriptCmdBuilderWarp;
import com.hundsun.ares.studio.jres.script.api.wrap.ISingleTableScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ITableRevHistoryScriptWrap;
import com.hundsun.ares.studio.jres.script.util.IJRESScript;
import com.hundsun.ares.studio.jres.script.wrap.ModelScriptWrapManager;

/**
 * @author lvgao
 *
 */
public class ARESProjectScriptWrapImpl  implements IARESProjectScriptWrap,IJRESScript{

	IARESProject project;
	
	private IBizScriptWrap bizProxy;
	private IObjScriptWrap objProxy;
	private ILogicScriptWrap logicProxy;
	private IGenCodeScriptWrap genCodeProxy;
	private IScriptCallScriptWarp scriptCallScript;
	private IScriptCmdBuilderWarp scriptCmdBuilder;
	private IGenCode4UFTScriptWrap genCode4UFTScriptWrap;
	private IProcedureScriptWrap procedureProxy;
	
	public ARESProjectScriptWrapImpl(IARESProject project){
		this.project = project;
		
		IScriptPoxyFactory factory = null;
		
		//存储过程
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("ares.procedure");
		if(null != factory){
			procedureProxy = (IProcedureScriptWrap)factory.createPoxy(project, null);
		}
		
		//基础数据
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("jres.basicdata");
		if(null != factory){
			basicdataPoxy = (IBasicdataScriptWrap)factory.createPoxy(project, null);
		}
		
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("jres.database");
		if(null != factory){
			databasePoxy = (IDatabaseScriptWrap)factory.createPoxy(project, null);
		}
		
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("jres.metadata");
		if(null != factory){
			metadataProxy = (IMetadataScriptWrap)factory.createPoxy(project, null);
		}
		
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("jres.business");
		if(null != factory){
			businessProxy = (IBusinessScriptWrap)factory.createPoxy(project, null);
		}
		
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("jres.obj");
		if (factory != null) {
			objProxy = (IObjScriptWrap) factory.createPoxy(project, null);
		}
		
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("jres.biz");
		if (factory != null) {
			bizProxy = (IBizScriptWrap) factory.createPoxy(project, null);
		}
		
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("cres.logic");
		if (factory != null) {
			logicProxy = (ILogicScriptWrap) factory.createPoxy(project, null);
		}
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("cres.gencode");
		if (factory != null) {
			genCodeProxy = (IGenCodeScriptWrap) factory.createPoxy(project, null);
		}
		
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("uft.gencode");
		if (factory != null) {
			genCode4UFTScriptWrap = (IGenCode4UFTScriptWrap) factory.createPoxy(project, null);
		}
	}
 
	@Override
	public String getProjectPath() {
		return project.getProject().getLocation().toOSString()+"\\";
	}
	
	public IARESProject getARESProject(){
		return project;
	}
	
	public String[] getAllSubsys(){
		List<String> strs = new ArrayList<String>();
		try {
			IARESModuleRoot modultRoot = project.getModuleRoot("database");
			if (modultRoot == null) {
				return strs.toArray(new String[0]);
			}
			for(IARESModule module : modultRoot.getModules()){
				String moduleName = module.getElementName();
				if (StringUtils.isNotBlank(moduleName) && StringUtils.indexOf(moduleName, ".") == -1) {
					strs.add(moduleName);
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return strs.toArray(new String[0]);
	}
	
//////////////////////-----------------------------------------元数据代理--------------------------------------------------------////////	

	IMetadataScriptWrap metadataProxy;
	
	public IMetadataResScriptWrap getMetadataInfoByType(String type){
		return metadataProxy.getMetadataInfoByType(type);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IMetadataScriptWrap#getMetadataInfoByType(java.lang.String, boolean)
	 */
	@Override
	public IMetadataResScriptWrap getMetadataInfoByType(String type, boolean includeRequridProjects) {
		return metadataProxy.getMetadataInfoByType(type, includeRequridProjects);
	}
	
////////////////////////////////////////////////////////基础数据代理///////////////////////////////////////////////////////////////	
	IBasicdataScriptWrap basicdataPoxy;
	
	/**
	 * @return
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IBasicdataScriptWrap#getAllTableBasicData()
	 */
	public ISingleTableScriptWrap[] getAllTableBasicData() {
		return basicdataPoxy.getAllTableBasicData();
	}

	/**
	 * @param name
	 * @return
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IBasicdataScriptWrap#getTableBasicDataByName(java.lang.String)
	 */
	public ISingleTableScriptWrap getTableBasicDataByName(String name) {
		return basicdataPoxy.getTableBasicDataByName(name);
	}
	
	/**
	 * @return
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IBasicdataScriptWrap#getAllMetaDataBasicData()
	 */
	public ISingleTableScriptWrap[] getAllMetaDataBasicData() {
		return basicdataPoxy.getAllMetaDataBasicData();
	}

	/**
	 * @param name
	 * @return
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IBasicdataScriptWrap#getMetaDataBasicDataByName(java.lang.String)
	 */
	public ISingleTableScriptWrap getMetaDataBasicDataByName(String name) {
		return basicdataPoxy.getMetaDataBasicDataByName(name);
	}

	/**
	 * @param subsysName
	 * @return
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IBasicdataScriptWrap#getTableBasicDataBySubsys(java.lang.String)
	 */
	public ISingleTableScriptWrap[] getTableBasicDataBySubsys(String subsysName) {
		return basicdataPoxy.getTableBasicDataBySubsys(subsysName);
	}
	
	public ISingleTableScriptWrap[] getTableBasicDataByModule(String moduleName){
		return basicdataPoxy.getTableBasicDataByModule(moduleName);
	}
	
	@Override
	public IMasterSlaveTableScriptWrap[] getAllMasterSlaveTableBasicData() {
		return basicdataPoxy.getAllMasterSlaveTableBasicData();
	}

	@Override
	public IMasterSlaveLinkTableScriptWrap[] getAllMasterSlaveLinkTableBasicData() {
		return basicdataPoxy.getAllMasterSlaveLinkTableBasicData();
	}

	@Override
	public IMasterSlaveTableScriptWrap[] getMasterSlaveTableBasicDataBySubsys(
			String subsysName) {
		return basicdataPoxy.getMasterSlaveTableBasicDataBySubsys(subsysName);
	}

	@Override
	public IMasterSlaveTableScriptWrap[] getMasterSlaveTableBasicDataByModule(
			String moduleName) {
		return basicdataPoxy.getMasterSlaveTableBasicDataByModule(moduleName);
	}
	
	@Override
	public IMasterSlaveTableScriptWrap getMasterSlaveTableBasicDataByName(
			String name) {
		return basicdataPoxy.getMasterSlaveTableBasicDataByName(name);
	}
	
	@Override
	public IMasterSlaveLinkTableScriptWrap[] getMasterSlaveLinkTableBasicDataBySubsys(
			String subsysName) {
		return basicdataPoxy.getMasterSlaveLinkTableBasicDataBySubsys(subsysName);
	}

	@Override
	public IMasterSlaveLinkTableScriptWrap[] getMasterSlaveLinkTableBasicDataByModule(
			String subsysName) {
		return basicdataPoxy.getMasterSlaveLinkTableBasicDataByModule(subsysName);
	}

	@Override
	public IMasterSlaveLinkTableScriptWrap getMasterSlaveLinkTableBasicDataByName(
			String name) {
		return basicdataPoxy.getMasterSlaveLinkTableBasicDataByName(name);
	}
	

//////////////////////-----------------------------------------基础数据代理--------------------------------------------------------////////	
	
////////////////////////////////////////////////////////数据库代理///////////////////////////////////////////////////////////////	

	IDatabaseScriptWrap databasePoxy;

	/**
	 * @return
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseScriptWrap#getAllTable()
	 */
	public ITableScriptWrap[] getAllTable() {
		return databasePoxy.getAllTable();
	}

	@Override
	public ISequenceScriptWrap[] getAllSequence() {
		return databasePoxy.getAllSequence();
	}

	@Override
	public ITriggerScriptWrap[] getAllTrigger() {
		return databasePoxy.getAllTrigger();
	}
	
	/**
	 * @param name
	 * @return
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseScriptWrap#getTableByName(java.lang.String)
	 */
	public ITableScriptWrap[] getTableByName(String name) {
		return databasePoxy.getTableByName(name);
	}

	@Override
	public IDatabaseResScriptWrap[] getAllDatabaseResources() {
		return databasePoxy.getAllDatabaseResources();
	}

	public IDatabaseResScriptWrap[] getAllDatabaseResourcesBySubsys(String subsysName){
		return databasePoxy.getAllDatabaseResourcesBySubsys(subsysName);
	}
	
	public IDatabaseResScriptWrap[] getAllDatabaseResourcesByModule(String moduleName){
		return databasePoxy.getAllDatabaseResourcesByModule(moduleName);
	}
	
	@Override
	public String getAllHistoriesCommentBySubsys(String subsysName , String content) {
		return databasePoxy.getAllHistoriesCommentBySubsys(subsysName , content);
	}
	
	@Override
	public String getAllHistoriesCommentByModule(String subsysName , String content) {
		return databasePoxy.getAllHistoriesCommentByModule(subsysName , content);
	}
	
	@Override
	public ITableRevHistoryScriptWrap[] getAllHistoriesBySubsys(String subsysName) {
		return databasePoxy.getAllHistoriesBySubsys(subsysName);
	}
	
	@Override
	public ITableRevHistoryScriptWrap[] getAllHistoriesByModule(String subsysName) {
		return databasePoxy.getAllHistoriesByModule(subsysName);
	}
	
	@Override
	public IViewScriptWrap[] getAllView() {
		return databasePoxy.getAllView();
	}

	@Override
	public IViewScriptWrap[] getViewByName(String name) {
		return databasePoxy.getViewByName(name);
	}
	
	@Override
	public ITableSpaceScriptWrap getTableSpace() {
		return databasePoxy.getTableSpace();
	}

	@Override
	public IDatabaseUserScriptWrap getDBUser() {
		return databasePoxy.getDBUser();
	}
	
//////////////////////-----------------------------------------存储过程代理--------------------------------------------------------////////	
	@Override
	public IProcedureWrap[] getProcedures(){
		if(procedureProxy != null){
			return procedureProxy.getProcedures();
		}
		return new IProcedureWrap[0];
	}

	@Override
	public IProcedureWrap getProcedureByCName(String name) {
		if(procedureProxy != null){
			return procedureProxy.getProcedureByCName(name);
		}
		return null;
	}
	 
//////////////////////-----------------------------------------业务逻辑代理--------------------------------------------------------////////	

	IBusinessScriptWrap businessProxy;
	
	public IBusinessResScriptWrap[] getAllService(){
		return businessProxy.getAllService();
	}

	@Override
	public IBizServiceWrap[] getServices() {
		if (bizProxy != null) {
			return bizProxy.getServices();
		}
		return new IBizServiceWrap[0];
	}

	@Override
	public IBizObjectWrap[] getObjects() {
		if (objProxy != null) {
			return objProxy.getObjects();
		}
		return new IBizObjectWrap[0];
	}

	@Override
	public IBizObjectWrap[] getObjectsBySubsys(String subsysName) {
		if (objProxy != null) {
			return objProxy.getObjectsBySubsys(subsysName);
		}
		return new IBizObjectWrap[0];
	}

	@Override
	public IBizObjectWrap[] getObjectsByModule(String moduleName) {
		if (objProxy != null) {
			return objProxy.getObjectsByModule(moduleName);
		}
		return new IBizObjectWrap[0];
	}
	
	@Override
	public IBizObjectWrap getObjectByName(String name) {
		if (objProxy != null)
			return objProxy.getObjectByName(name);
		return null;
	}

	@Override
	public IBizServiceWrap getServiceByName(String name) {
		if (bizProxy != null)
			return bizProxy.getServiceByName(name);
		return null;
	}

	@Override
	public IBizServiceWrap getServiceByCName(String name) {
		if (bizProxy != null)
			return bizProxy.getServiceByCName(name);
		return null;
	}

	@Override
	public IBizServiceWrap[] getServicesBySubsys(String subsysName) {
		if (bizProxy != null)
			return bizProxy.getServicesBySubsys(subsysName);
		return null;
	}

	@Override
	public IBizServiceWrap[] getServicesByModule(String moduleName) {
		if (bizProxy != null)
			return bizProxy.getServicesByModule(moduleName);
		return null;
	}
	
	@Override
	public IARESModuleWrap[] getSubSystems(String rootType) {
		IFolder folder = ARESElementUtil.getModuleRootFolder(project, rootType);
		IARESModuleRoot root = project.getModuleRoot(folder);
		List<IARESModuleWrap> subSystems = new ArrayList<IARESModuleWrap>();
		try {
			for (IARESElement element : root.getChildren()) {
				if (element instanceof IARESModule) {
					IARESModule module = (IARESModule) element;
					String elementName = module.getElementName();
					if (StringUtils.isEmpty(elementName))
						continue;
					
					if (ARESElementUtil.isTopModule(module)) {
						subSystems.add(new ARESModuleWrap(module));
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return subSystems.toArray(new IARESModuleWrap[0]);
	}

	@Override
	public IARESModuleRootWrap getRoot(String type) {
		IFolder folder = ARESElementUtil.getModuleRootFolder(project, type);
		IARESModuleRoot root = project.getModuleRoot(folder);
		return new ARESModuleRootWrap(root);
	}

	@Override
	public ILogicServiceWrap[] getLogicServices() {
		if (logicProxy != null)
			return logicProxy.getLogicServices();
		return null;
	}

	@Override
	public ILogicFunctionWrap[] getLogicFunctions() {
		if (logicProxy != null)
			return logicProxy.getLogicFunctions();
		return null;
	}

	@Override
	public ILogicServiceWrap getLSByCName(String name) {
		if (logicProxy != null)
			return logicProxy.getLSByCName(name);
		return null;
	}

	@Override
	public ILogicFunctionWrap getLFByCName(String name) {
		if (logicProxy != null)
			return logicProxy.getLFByCName(name);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IGenCodeScriptWrap#genModuleCode(int, int)
	 */
	@Override
	public void genModuleCode(int resType, int genType) {
		
		if (genCodeProxy != null)
			 genCodeProxy.genModuleCode(resType, genType);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IScriptCallScriptWarp#runSrcipt(java.lang.String[][])
	 */
	@Override
	public void runSrcipt(String[][] scripts) {
		scriptCallScript = new ScriptCallScriptWrap(project);
		scriptCallScript.runSrcipt(scripts);
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IScriptCmdBuilderWarp#build(java.lang.String[][], java.util.Map)
	 */
	@Override
	public void build(String[][] scripts, Map<String, Object> parameters) {
		scriptCmdBuilder =  new ScriptCmdBuilderWarp(project);
		scriptCmdBuilder.build(scripts, parameters);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IGenCode4UFTScriptWrap#gen4UFTCode(int, int)
	 */
	@Override
	public void gen4UFTCode(int resType, int genType) {
		if (genCode4UFTScriptWrap != null)
			genCode4UFTScriptWrap.gen4UFTCode(resType, genType);
		
	}
	

}
