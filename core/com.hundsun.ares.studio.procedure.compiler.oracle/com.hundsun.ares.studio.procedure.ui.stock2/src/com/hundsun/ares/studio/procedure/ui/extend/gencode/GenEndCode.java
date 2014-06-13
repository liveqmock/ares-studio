/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.extend.gencode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.preferences.IPreferencesService;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.GenCresModuleCode;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleResourceStatisticsInfo;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.RevisionHistoryGenUtil;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureResType;
import com.hundsun.ares.studio.procdure.provider.ProcedureUI;
import com.hundsun.ares.studio.procedure.ui.util.ProcedureModuleGeneratorHelper;
import com.hundsun.ares.studio.ui.ARESUI;

/**
 * @author qinyuan
 *
 */
public class GenEndCode extends GenCresModuleCode{
	
	public void genModuleEndCode(IARESModule module,
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
						newPath += moduleCName;
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
					createModuleFile(monitor,module,newPath,charset);//生成后置代码
				}
			}
			
			//递归模块
			for (IARESModule subModule : subModules) {
				genModuleEndCode(subModule,isWithPath,isPathWithCname,new SubProgressMonitor(monitor, 10));
			}
			
			monitor.done();
		} catch (Exception e) {
			e.printStackTrace();
			monitor.setCanceled(true);
		}
	}
	

	/**
	 * @param monitor
	 * @param module
	 * @param newPath
	 * @param charset
	 */
	private void createModuleFile(IProgressMonitor monitor, IARESModule module,
			String path, String charset)  throws Exception{
		//本模块资源
		IARESResource[] moduleRes = module.getARESResources(IProcedureResType.PROCEDURE);
		createEndCodeFile(moduleRes, "_proc_help_after.sql", monitor, module, path, charset);
		
		//本模块所有资源资源，递归子模块
		IARESResource[] moduleAllRes = module.getARESResources(IProcedureResType.PROCEDURE,true);
		if(moduleRes.length != moduleAllRes.length) {
			//当前模块没有子模块，不需要生成一份总体文件
			createEndCodeFile(moduleAllRes, "_all_proc_help_after.sql", monitor, module, path, charset);
		}
	}


	/**
	 * @param moduleAllRes
	 * @param string
	 * @param monitor
	 * @param module
	 * @param path
	 * @param charset
	 */
	private void createEndCodeFile(IARESResource[] moduleRes, String fileSuffixName,
			IProgressMonitor monitor, IARESModule module, String path,
			String charset) throws Exception{
		// 模块下没有过程，则不生成sql文件
		if(moduleRes == null || moduleRes.length < 1) {
			return;
		}
		
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
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
		
		//生成代码时，错误信息输出的消息队列
		Queue<IARESProblem> msgQueue = new ArrayDeque<IARESProblem>();
		
		//数据库名
		String dbName = "";
		
		String codeFileName = path + dbName + module.getShortName() + fileSuffixName;
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
			if(StringUtils.isBlank(procedure.getEndCode())){
				continue;
			}
			resCodeBuffer.append("\n/******************************************************\n");
			resCodeBuffer.append("**后置代码\n");
			resCodeBuffer.append("**资源名：" + procedure.getName() + "\n");
			resCodeBuffer.append("**对象号：" + procedure.getObjectId() + "\n");
			resCodeBuffer.append("******************************************************/\n");
			
			//2013年6月19日9:15:37 后置代码前后自动添加plsql配置设置
			resCodeBuffer.append("\n");
			resCodeBuffer.append("set define off;\n");
			resCodeBuffer.append("set feedback off;\n");
			resCodeBuffer.append("\n");
			resCodeBuffer.append(procedure.getEndCode());//后置代码
			resCodeBuffer.append("\r\n");
			resCodeBuffer.append("set define on;\n");
			resCodeBuffer.append("set feedback on;\n");
			resCodeBuffer.append("\n");
			infos.add(new ModuleResourceStatisticsInfo(procedure.getObjectId(), procedure.getName(), procedure.getChineseName(), procedure.getDescription()));
			monitor.worked(1);
		}
		
		codeBuffer.append(ModuleGeneratorHelper.getResourceStatisticsInfo(infos));
		codeBuffer.append("\n");
		// 加入修改记录
		codeBuffer.append(RevisionHistoryGenUtil.getAllRevisionHistoryInfo(ProcedureModuleGeneratorHelper.getProcedureModuleHistorys(module), "--"));
		codeBuffer.append("\n");
		
		//二部要求在文件头添加信息
		if(ProcedureUI.isStock2Procedure()) {
			codeBuffer.append("set define off;\r\n");
			codeBuffer.append("set feedback off;\r\n");
			codeBuffer.append("set serveroutput on;\r\n");
		}
		
		codeBuffer.append(resCodeBuffer);
		codeBuffer.append("\n");

		//写入文件
		writeToFile(codeFileName, codeBuffer.toString(),charset);
		//================================== err
		String errLogFileName = "s_as_" + module.getElementName() + "_end_proc.errorlog";
		createErrLog(msgQueue, errLogFileName,path,charset);
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.cres.extend.ui.module.gencode.IGenCresModuleCode#genModuleCode(com.hundsun.ares.studio.core.IARESModule, java.util.Map, boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void genModuleCode(IARESModule module, Map<Object, Object> context,
			boolean isWithPath, boolean isPathWithCname,
			IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.cres.extend.ui.module.gencode.IGenCresModuleCode#canGenCode(com.hundsun.ares.studio.core.IARESModule)
	 */
	@Override
	public boolean canGenCode(IARESModule module) {
		// TODO Auto-generated method stub
		return false;
	}

}
