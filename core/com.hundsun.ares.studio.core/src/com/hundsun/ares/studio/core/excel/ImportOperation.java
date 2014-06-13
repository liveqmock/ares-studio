/**
 * 源程序名称：ImportOperation.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：dollyn
 */
package com.hundsun.ares.studio.core.excel;

import java.io.File;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.dialogs.IOverwriteQuery;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.util.log.Log;

/**
 * @author sundl
 *
 */
public abstract class ImportOperation implements IWorkspaceRunnable{
	
	protected static final Logger logger = Logger.getLogger(ImportOperation.class);
	
	protected File[] files;
	public  BizExcelLog log  = new BizExcelLog();
	protected Multimap<Module, Resource>  resources = ArrayListMultimap.create();
	protected IOverwriteQuery overwriteQuery;
	public IARESProject project;
	protected ExcelParser exlPaser;
	
	/** 解析完成后需要进行的操作
	 *  目前用来完成类似解析XML标签这样的扩展属性的处理
	 */
	protected PostParseOperation postParseOperation = new PostParseOperation(log);
	
	/**
	 * 在资源冲突的情况下，用于询问用户如何处理
	 * @param files
	 * @param overwriteQuery
	 */
	public ImportOperation(File[] files, IOverwriteQuery overwriteQuery) {
		this.files = files;
		this.overwriteQuery = overwriteQuery;
	}

	protected abstract ExcelParser createParser(File file, Log log) ;
	protected abstract IARESModuleRoot getRoot(Resource res);

	protected  void checkRes(IProgressMonitor monitor) {
		monitor.beginTask("", resources.size() * 2);
		monitor.subTask("校验资源名...");
		Multimap<String, Resource> hashSet = ArrayListMultimap.create();
		for (Resource res : resources.values()) {
			hashSet.put(res.getDescription(), res);
			monitor.worked(1);
		}
		
		// 校验资源重复
		for (String key : hashSet.keySet()) {
			monitor.worked(1);
			// 资源名为空的就不提示重复了，因为有资源名为空的错误提示
			if (StringUtils.isEmpty(key)) 
				continue;
			
			if (hashSet.get(key).size() > 1) {
				for (Resource r : hashSet.get(key)) {
					log.error("资源重复：" + r.getDescription(), r.startLoc);
				}
			}
		}
		monitor.done();
	}
	
	protected void createResources(IProgressMonitor monitor) throws ARESModelException, ExcelHandlerException{
		// 写资源
		//monitor.subTask("生成资源文件");
		String queryResult = null;
		monitor.beginTask("", resources.size());

		// 中英文名翻译的辅助表  中文-->英文，BiMap也可以反向查找
		MODULES_LOOP: for (Module module : resources.keySet()) {
			for (Resource res : resources.get(module)) {
				String resDescription = res.getDescription();
				monitor.subTask("创建资源文件:" + resDescription);
				IARESModuleRoot root = getRoot(res);
				if (root == null) {
					String exception = String.format("未找到资源类型%s对应的模块根，请检查工程完整性！", res.type);
					log.logResFailed(res, "无法确定资源应该放在哪个模块根下");
					throw new ExcelHandlerException(exception);
				}
				
				// 是否已经存在
				boolean exists = module.exists(root);
				IARESModule aresModule = module.create(root, log);
				if (aresModule == null) {
					String exception = String.format("没有模块信息或创建模块失败(模块：%s)", module.getFullName());
					log.logResFailed(res, exception);
					throw new ExcelHandlerException(exception);
				} else {
					// 第一次创建，写入修改记录
					if (!exists) {
						IARESResource propertyRes = aresModule.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
						if (propertyRes.exists()) {
							ModuleProperty mp = propertyRes.getWorkingCopy(ModuleProperty.class);
							if (mp != null) {
								JRESResourceInfo info = (JRESResourceInfo) mp.getMap().get(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY);
								if (info == null) {
									info = CoreFactory.eINSTANCE.createJRESResourceInfo();
									mp.getMap().put(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY, info);
								}
								Collection<RevisionHistory> histories = exlPaser.histories.get("全部");
								for (RevisionHistory his : histories) {
									info.getHistories().add(EcoreUtil.copy(his));
								}
								propertyRes.save(mp, true, null);
							}
						}
					}
				}
				
				IARESResource existingRes = aresModule.getARESResource(res.name, res.type);
				if (existingRes != null && existingRes.exists()) {
					BasicResourceInfo basicInfo = existingRes.getInfo(BasicResourceInfo.class);
					Resource old = new Resource();
					old.name = basicInfo.getName();
					old.info = basicInfo;
					
					// 已经存在，是否需要询问用户？
					if (StringUtils.equals(queryResult, IOverwriteQuery.ALL)) {
						// 如果前面已经询问过，而且结果是 全部为是,直接覆盖
						createRes(res, aresModule);
						log.logResOverwritten(res);
					} else if (StringUtils.equals(queryResult, IOverwriteQuery.NO_ALL)) {
						// 如果前面已经询问过，而且结果是 全部为否，直接跳过
						log.logResSkipped(res);
					} else if (StringUtils.equals(queryResult, IOverwriteQuery.CANCEL)) {
						break MODULES_LOOP;
					} else {
						// 如不是上述两种情况，则询问用户是否覆盖
						queryResult = this.overwriteQuery.queryOverwrite(resDescription);
						if (StringUtils.equals(queryResult, IOverwriteQuery.ALL) ||
								StringUtils.equals(queryResult, IOverwriteQuery.YES)) {
							createRes(res, aresModule);
							log.logResOverwritten(res);
						} else if (StringUtils.equals(queryResult, IOverwriteQuery.NO)) {
							log.logResSkipped(res);
						}
					}
				} else {
					// 资源不存在，直接创建
					createRes(res, aresModule);
					log.logResCreted(res);
				}
				monitor.worked(1);
			}
		}
	}
	
	protected void createRes(Resource res, IARESModule module) {
		try {
			res.create(module);
		} catch (ARESModelException e) {
			log.logResFailed(res, e.getMessage());
		}
	}
	
	protected void dispose() {
		files = null;
		log = null;
		resources.clear();
		resources=null;
	}
	
}
