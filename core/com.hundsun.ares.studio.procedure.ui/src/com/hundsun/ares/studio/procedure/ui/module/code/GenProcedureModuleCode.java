package com.hundsun.ares.studio.procedure.ui.module.code;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.preferences.IPreferencesService;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESProblem;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.cres.constant.ICresUIConstant;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.GenCresModuleCode;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.FileNameHelper;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleResourceStatisticsInfo;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.RevisionHistoryGenUtil;
import com.hundsun.ares.studio.engin.logic.ResourceEngin;
import com.hundsun.ares.studio.engin.skeleton.DefaultSkeletonInput;
import com.hundsun.ares.studio.jres.database.utils.ProjectSettingUtil;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureResType;
import com.hundsun.ares.studio.procdure.provider.ProcedureUI;
import com.hundsun.ares.studio.procedure.compiler.mysql.constant.IProcedureSkeletonFactoryConstantMySQL;
import com.hundsun.ares.studio.procedure.compiler.oracle.constant.IProcedureEngineContextConstantOracle;
import com.hundsun.ares.studio.procedure.compiler.oracle.constant.IProcedureSkeletonFactoryConstantOracle;
import com.hundsun.ares.studio.procedure.ui.util.ProcedureModuleGeneratorHelper;
import com.hundsun.ares.studio.ui.ARESUI;


public class GenProcedureModuleCode extends GenCresModuleCode {
	
	@Override
	public void genModuleCode(IARESModule module, Map<Object, Object> context,
			boolean isWithPath, boolean isPathWithCname, IProgressMonitor monitor) {
		if(monitor.isCanceled()){
			return;
		}
		
		String newPath = ModuleGeneratorHelper.getModuleGenCodePath(module.getARESProject());
		IPreferencesService service = Platform.getPreferencesService();
		String charset = service.getString(ARESUI.PLUGIN_ID, ARESUI.PRE_GENERATE_CHARSET, StringUtils.EMPTY, null);
		try {
			if(isWithPath) {//带目录
				if(isPathWithCname){//使用中文名作为文件目录
					String moduleCName = ModuleGeneratorHelper.getModuleProperty(module).getString(ICommonModel.CNAME);
					if(moduleCName != null && moduleCName.trim().length() > 0){
						newPath += ModuleGeneratorHelper.getParentModuleCnamePath(module);//父类模块路径
						newPath += StringUtils.trim(FileNameHelper.legalFileOrDirName(moduleCName));//模块中文名两端的空格要去掉
						newPath += "\\";
						createFilePath(newPath);//创建文件目录
					}
				}else{
					String moduleName = module.getShortName();
					if(moduleName != null && moduleName.trim().length() > 0){
						newPath += ModuleGeneratorHelper.getParentModulePath(module);//父类模块路径
						newPath += moduleName;
						newPath += "\\";
						createFilePath(newPath);//创建文件目录
					}
				}
			}
			//子模块
			IARESModule[] subModules = module.getSubModules();
			//本模块资源
			IARESResource[] mRess = module.getARESResources(true);
			monitor.beginTask(newPath, mRess.length + subModules.length*10 + 1);
			if(mRess.length > 0) {//有资源才生成
				if(mRess.length == 1 && StringUtils.equals(mRess[0].getType(), "module.xml")) {//有可能是模块属性
					System.out.println(mRess[0].getType());
				}else {
					createModuleFile(monitor,module,newPath,charset,context);//生成过程
				}
			}
			
			//递归模块
			for (IARESModule subModule : subModules) {
				genModuleCode(subModule,context,isWithPath,isPathWithCname,new SubProgressMonitor(monitor, 10));
			}
			
			monitor.done();
		} catch (Exception e) {
			e.printStackTrace();
			monitor.setCanceled(true);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.cres.extend.ui.module.gencode.GenCresModuleCode#getContent(com.hundsun.ares.studio.core.IARESProject)
	 */
	@Override
	public Map<Object, Object> getContext(IARESProject project) {
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put(IProcedureEngineContextConstantOracle.auto_define_input_param, 
				ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_AUTO_DEFINE_PROC_INPARAM));
		context.put(IProcedureEngineContextConstantOracle.not_define_connect_type, 
				ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_NOT_DEFINE_CONNECT_TYPE));
		context.put(IProcedureEngineContextConstantOracle.return_error_info, 
				ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_RETURN_ERROR_INFO));
		context.put(IProcedureEngineContextConstantOracle.gen_related_info, 
				ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_GEN_RELATED_INFO));
		if(ProcedureUI.isStock2Procedure()) {
			context.put(IProcedureEngineContextConstantOracle.gen_begin_code, 
					isHeadCode());
			context.put(IProcedureEngineContextConstantOracle.gen_end_code, 
					isEndCode());
		}else {
			context.put(IProcedureEngineContextConstantOracle.gen_begin_code, false);
			context.put(IProcedureEngineContextConstantOracle.gen_end_code, false);
		}

		return context;
	}
	
	/**
	 * 生成过程脚本文件
	 * @param moduleRes 
	 * 			过程资源
	 * @param fileSuffixName
	 * 			文件后缀名
	 * @param monitor
	 * @param module
	 * @param path
	 * @param charset
	 * @throws Exception
	 */
	private void createProcedureFile(
			IARESResource[] moduleRes,String fileSuffixName,IProgressMonitor monitor,
			IARESModule module, String path,String charset,Map<Object, Object> context,boolean needSort) throws Exception{
		// 模块下没有过程，则不生成sql文件
		if(moduleRes == null || moduleRes.length < 1) {
			return;
		}
		
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
		if(needSort){
			Collections.sort(modulesReslist,new Comparator<IARESResource>() {
				
				@Override
				public int compare(IARESResource o1, IARESResource o2) {
					try {
						//此时资源为存储过程
						Procedure p1 = o1.getInfo(Procedure.class);
						Procedure p2 = o2.getInfo(Procedure.class);
						return Integer.valueOf(p1.getObjectId()) - Integer.valueOf(p2.getObjectId());
						
					} catch (ARESModelException e) {
						e.printStackTrace();
						return 0;
					}
				}
			});
		}
		
		//生成代码时，错误信息输出的消息队列
		Queue<IARESProblem> msgQueue = new ArrayDeque<IARESProblem>();
		
		//数据库名
		String dbName = "";
		
		String codeFileName = path + dbName + getModuleNameFromAutoCreateModuleName(module) + fileSuffixName;
		StringBuffer codeBuffer = new StringBuffer();
		
		// 写入注释头
		ModuleGeneratorHelper.writeCommentHeader(codeBuffer, 
				ModuleGeneratorHelper.getCresProjectExtendProperty(module).getHeadFile(), 
				codeFileName, module.getShortName(), new Date());
		codeBuffer.append("\n");
		
		//资源统计信息
		List<ModuleResourceStatisticsInfo> infos = new ArrayList<ModuleResourceStatisticsInfo>();
		StringBuffer resCodeBuffer = new StringBuffer();//资源代码
		//单个逻辑函数代码生成
		for (IARESResource iaresResource : modulesReslist) {
			if(monitor.isCanceled()){
				return;
			}
			monitor.setTaskName("存储过程："+iaresResource.getFullyQualifiedName());
			Procedure procedure = iaresResource.getInfo(Procedure.class);
			String id = procedure.getObjectId() + "&&" + procedure.getFullyQualifiedName();
			//生成代码时，单个资源错误信息输出的消息队列
			Queue<IARESProblem> msg = new ArrayDeque<IARESProblem>();
			if(!resCodeCache.containsKey(id)) {
				resCodeCache.put(id, genProcedureCode(iaresResource,procedure,msg,context));
			}
			msgQueue.addAll(msg);
			
			resCodeBuffer.append(resCodeCache.get(id));
			infos.add(new ModuleResourceStatisticsInfo(procedure.getObjectId(), procedure.getName(), procedure.getChineseName(), procedure.getDescription()));
			monitor.worked(1);
		}
		
		//二部要求在文件头添加信息
		if(ProcedureUI.isStock2Procedure()) {
			codeBuffer.append("set define off;\r\n");
			codeBuffer.append("set feedback off;\r\n");
			codeBuffer.append("set serveroutput on;\r\n");
		}
		
		codeBuffer.append(ModuleGeneratorHelper.getResourceStatisticsInfo(infos));
		codeBuffer.append("\n");
		// 加入修改记录
		codeBuffer.append(RevisionHistoryGenUtil.getAllRevisionHistoryInfo(ProcedureModuleGeneratorHelper.getProcedureModuleHistorys(module), "--"));
		codeBuffer.append("\n");
		
		
		codeBuffer.append(resCodeBuffer);
		codeBuffer.append("\n");

		//写入文件
		writeToFile(codeFileName, codeBuffer.toString(),charset);
		//================================== err
		String errLogFileName = "s_as_" + getModuleNameFromAutoCreateModuleName(module) + "proc.errorlog";
		createErrLog(msgQueue, errLogFileName,path,charset);
	}
	
	/**
	 * 生成过程squall文件 
	 * @param monitor
	 * @param module
	 * @param path
	 * @throws Exception
	 */
	private void createModuleFile(IProgressMonitor monitor,
			IARESModule module, String path,String charset,Map<Object, Object> context) throws Exception{
		//本模块资源
		IARESResource[] moduleRes = module.getARESResources(IProcedureResType.PROCEDURE);
		createProcedureFile(moduleRes, "_or.sql", monitor, module, path, charset,context,true);
		
		if(ProcedureUI.isStock2Procedure()) {//二部按模块进行排序
			//子模块
			IARESModule[] subModules = module.getSubModules();
			List<IARESModule> subModuleLists = Arrays.asList(subModules);
			Collections.sort(subModuleLists,new Comparator<IARESModule>(){//按子模块排序
				
				@Override
				public int compare(IARESModule o1, IARESModule o2) {
					
					return getModuleNameFromAutoCreateModuleName(o1).compareTo(getModuleNameFromAutoCreateModuleName(o2));
				}
				
			});
			List<IARESResource> allSubModules = new ArrayList<IARESResource>();
			for (IARESModule subModule : subModuleLists) {
				IARESResource[] ress = subModule.getARESResources(IProcedureResType.PROCEDURE);
				List<IARESResource> ressList = Arrays.asList(ress);
				//排序
				Collections.sort(ressList,new Comparator<IARESResource>() {
					
					@Override
					public int compare(IARESResource o1, IARESResource o2) {
						try {
							//此时资源为存储过程
							Procedure p1 = o1.getInfo(Procedure.class);
							Procedure p2 = o2.getInfo(Procedure.class);
							return Integer.valueOf(p1.getObjectId()) - Integer.valueOf(p2.getObjectId());
							
						} catch (ARESModelException e) {
							e.printStackTrace();
							return 0;
						}
					}
				});
				allSubModules.addAll(ressList);
			}
			if(allSubModules.size() > 0){
				//当前模块没有子模块，不需要生成一份总体文件
				createProcedureFile(allSubModules.toArray(new IARESResource[0]), "_all_or.sql", monitor, module, path, charset,context,false);
			}
		}else {
			//本模块所有资源资源，递归子模块
			IARESResource[] moduleAllRes = module.getARESResources(IProcedureResType.PROCEDURE,true);
			if(moduleRes.length != moduleAllRes.length) {
				//当前模块没有子模块，不需要生成一份总体文件
				createProcedureFile(moduleAllRes, "_all_or.sql", monitor, module, path, charset,context,true);
			}
		}
	}

	/**
	 * @param iaresResource
	 * @param msgQueue
	 * @return
	 */
	public StringBuffer genProcedureCode(IARESResource iaresResource,Procedure procedure,
			Queue<IARESProblem> msgQueue,Map<Object, Object> context) throws Exception{
		StringBuffer ret = new StringBuffer();
		///如果为多个对象号（以“，”分割），需要生成多分
		String objectID = procedure.getObjectId();
		if(StringUtils.isBlank(objectID)) {
			String message = String.format("存储过程[(%s)%s]对象号为空。", procedure.getName(),procedure.getChineseName());
			IARESProblem problem = ARESProblem.createError();
			problem.setMessage(message);
			msgQueue.add(problem);
		}else {
			String databaseType = ProjectSettingUtil.getDatabaseType(iaresResource.getARESProject());
			if(objectID.contains(",")){
				String[] objIDs = objectID.split(",");
				for (String objID : objIDs) {
					procedure.setObjectId(objID.trim());
					context.put(IProcedureEngineContextConstantOracle.ResourceModel, procedure);
					if(databaseType.equalsIgnoreCase(ProjectSettingUtil.MYSQL)){
						ret.append(ResourceEngin.instance.generate(
								new DefaultSkeletonInput(
										IProcedureSkeletonFactoryConstantMySQL.SKELETONTYPE_CRES_PROCEDURE_MYSQL,
										iaresResource), context,msgQueue));
					}else{
						ret.append(ResourceEngin.instance.generate(
								new DefaultSkeletonInput(
										IProcedureSkeletonFactoryConstantOracle.SKELETONTYPE_CRES_PROCEDURE_ORACLE,
										iaresResource), context,msgQueue));
					}
					
					ret.append("\r\n");
				}
			}else{
				context.put(IProcedureEngineContextConstantOracle.ResourceModel, procedure);
				if(databaseType.equalsIgnoreCase(ProjectSettingUtil.MYSQL)){
					ret.append(ResourceEngin.instance.generate(
							new DefaultSkeletonInput(
									IProcedureSkeletonFactoryConstantMySQL.SKELETONTYPE_CRES_PROCEDURE_MYSQL,
									iaresResource), context,msgQueue));
				}else{
					ret.append(ResourceEngin.instance.generate(
							new DefaultSkeletonInput(
									IProcedureSkeletonFactoryConstantOracle.SKELETONTYPE_CRES_PROCEDURE_ORACLE,
									iaresResource), context,msgQueue));
				}
				ret.append("\r\n");
			}
		}

//		return CodeFormater.formatProc(ret);
		return ret;
	}

	@Override
	public boolean canGenCode(IARESModule module) {
		String rootName = module.getParent().getElementName();
		if(StringUtils.equals(rootName, ICresUIConstant.ATOM_ROOT_NAME) || StringUtils.equals(rootName, ICresUIConstant.PROCEDURE_ROOT_NAME)){
			return true;
		}
		
		return false;
	}

}
