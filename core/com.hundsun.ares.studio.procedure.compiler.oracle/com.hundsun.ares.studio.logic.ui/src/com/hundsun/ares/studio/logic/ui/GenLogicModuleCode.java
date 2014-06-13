package com.hundsun.ares.studio.logic.ui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.ParamGroupUtil;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESProblem;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.cres.constant.ICresUIConstant;
import com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.GenCresModuleCode;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.MakeFileBuilder;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.RevisionHistoryGenUtil;
import com.hundsun.ares.studio.engin.format.CodeFormater;
import com.hundsun.ares.studio.engin.logic.ResourceEngin;
import com.hundsun.ares.studio.engin.skeleton.DefaultSkeletonInput;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.LogicService;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicSkeletonFactoryConstant;
import com.hundsun.ares.studio.logic.constants.ILogicResType;
import com.hundsun.ares.studio.logic.ui.util.LogicModuleGeneratorHelper;
import com.hundsun.ares.studio.ui.ARESUI;

public class GenLogicModuleCode extends GenCresModuleCode {

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
			IARESResource[] mRess = module.getARESResources();
			monitor.beginTask(newPath, mRess.length + subModules.length*10 + 1);
			if(mRess.length > 0) {//有资源才生成
				if(mRess.length == 1 && StringUtils.equals(mRess[0].getType(), "module.xml")) {//有可能是模块属性
					System.out.println(mRess[0].getType());
				}else {
					createLSFunctionFile(monitor,module,newPath,charset);//逻辑函数
					createLSServiceFile(monitor,module,newPath,charset);//逻辑服务
					createMakefile(module,newPath,isWithPath,charset);//编译文件
					monitor.worked(1);
					generateLogicServiceTest(module,newPath,charset);//逻辑服务测试文件
//					createFuncPasFile(monitor,module,newPath);//
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

	/**
	 * @param module
	 * @param newPath
	 */
	private void createMakefile(IARESModule module, String newPath,boolean isWithPath,String charset) throws Exception{
		
		//=================gcc
		String gccName = "s_ls_" + module.getShortName() + "flow.gcc";
		createMakeOrderFile(gccName,newPath,isWithPath,charset);
		//资源生成代码
		String gccFileName = newPath + gccName;
		//模块依赖
		HashSet<String> gccIncludes = new HashSet<String>();
		EList<MoudleDepend> depends = ModuleGeneratorHelper.getCresMoudleExtendProperty(module).getDepends();
		for (MoudleDepend moudleDepend : depends) {
			//根据moudleDepend找到对应的模块
			for (IARESModule m : module.getARESProject().getModules()) {
				if(StringUtils.equals(m.getElementName(), moudleDepend.getModulePath())){
					// 原子的不需要添加,只添加逻辑模块
					if(m.getParent().getElementName().equals(ICresUIConstant.LOGIC_ROOT_NAME)){
						gccIncludes.add("s_ls_" + moudleDepend.getName() + "flow");//这里加lib库，添加依赖项时，应该为so，故用flow，核对恒生开发工具也是如此规则处理
					}
				}
			}
		}
		// 自己不用依赖
		gccIncludes.remove("s_ls_" + module.getShortName() + "flow");
		StringBuffer gccCodeBuffer = new StringBuffer();
		MakeFileBuilder.writeLSGccMakeFile(module.getARESProject(), gccCodeBuffer, 
				"s_ls_" + module.getShortName(), "s_ls_" + module.getShortName() + "flow", 
				"s_ls_" + module.getShortName() + "func", gccIncludes.toArray(new String[0]));
		//写入文件
		writeToFile(gccFileName, gccCodeBuffer.toString(),charset);
		
//		//=================mvc
//		//资源生成代码
//		String mvcFileName = newPath + "s_ls_" + module.getShortName() + "flow.mvc";
//		HashSet<String> mvcDepends = new HashSet<String>();
//		// mvc 取所有依赖
//		List<MoudleDepend> mvcDps = ModuleGeneratorHelper.getAllDepends(module);
//		for (MoudleDepend md : mvcDps) {
//			//根据moudleDepend找到对应的模块
//			for (IARESModule m : module.getARESProject().getModules()) {
//				if(StringUtils.equals(m.getElementName(), md.getModulePath())){
//					// 原子的不需要添加,只添加逻辑模块
//					if(m.getParent().getElementName().equals(LOGIC_ROOT_NAME)){
//						mvcDepends.add("s_ls_" + md.getName() + "func");
//					}
//				}
//			}
//		}
//		// 自己不用依赖
//		mvcDepends.remove("s_ls_" + module.getShortName() + "func");
//		
//		StringBuffer mvc = new StringBuffer();
//		MakeFileBuilder.writeLSMvcMakeFile(module.getARESProject(), mvc, 
//				"s_ls_" + module.getShortName(), "s_ls_" + module.getShortName() + "flow", 
//				"s_ls_" + module.getShortName() + "func", depends.toArray(new String[0]));
//		
//		writeToFile(mvcFileName, mvc.toString());
		
	}

	/**
	 * @param monitor
	 * @param module
	 * @param newPath
	 */
	private void createLSServiceFile(IProgressMonitor monitor,
			IARESModule module, String newPath,String charset) throws Exception{
		IARESResource[] moduleRes = module.getARESResources(ILogicResType.LOGIC_SERVICE);//逻辑服务
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
		Collections.sort(modulesReslist,new Comparator<IARESResource>() {
			
			@Override
			public int compare(IARESResource o1, IARESResource o2) {
				try {
					//此时资源为逻辑服务
					LogicService ls1 = o1.getInfo(LogicService.class);
					LogicService ls2 = o2.getInfo(LogicService.class);
					if(ls1.getObjectId() != null && ls1.getObjectId() != "" && ls2.getObjectId() != null && ls2.getObjectId() != ""){
						return Integer.valueOf(ls1.getObjectId()) - Integer.valueOf(ls2.getObjectId());
					}
					return 0;
					
				} catch (ARESModelException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		
		//生成代码时，错误信息输出的消息队列
		Queue<IARESProblem> msgQueue = new ArrayDeque<IARESProblem>();
		
		//资源生成代码
		String codeFileName = newPath + "s_ls_" + module.getShortName() + "flow.cpp";
		StringBuffer codeBuffer = new StringBuffer();
		
		// 写入注释头
		ModuleGeneratorHelper.writeCommentHeader(codeBuffer, 
				ModuleGeneratorHelper.getCresProjectExtendProperty(module).getHeadFile(), 
				codeFileName, module.getShortName(), new Date());
		codeBuffer.append("\n");
		// 加入修改记录
		codeBuffer.append(RevisionHistoryGenUtil.getAllRevisionHistoryInfo(LogicModuleGeneratorHelper.getLogicModuleHistorys(module), "//"));
		codeBuffer.append("\n");
		
		// 添加依赖模块的英文名，本模块为必定依赖
		List<String> includes = new ArrayList<String>();
		includes.add("s_ls_" + module.getShortName() + "func.h");
		ModuleGeneratorHelper.writeIncludeSection(codeBuffer, includes);
		codeBuffer.append("\n");

		//单个逻辑函数代码生成
		ArrayList<BasicResourceInfo> services = new ArrayList<BasicResourceInfo>();
		for (IARESResource iaresResource : modulesReslist) {
			LogicService logicService = iaresResource.getInfo(LogicService.class);
			services.add(logicService);
			if(monitor.isCanceled()){
				return;
			}
			monitor.setTaskName("逻辑服务："+iaresResource.getFullyQualifiedName());
			codeBuffer.append(genLogicServiceCode(iaresResource,msgQueue));
			monitor.worked(1);
		}
		
		codeBuffer.append("\n");
		//添加中间件方法
		ModuleGeneratorHelper.writeMiddlewareEnumerateMethod2(codeBuffer, services);
		ModuleGeneratorHelper.writeStartupMethods2(codeBuffer, LogicModuleGeneratorHelper.getLogicModuleLastVersion(module));
		
		//写入文件
		writeToFile(codeFileName, codeBuffer.toString(),charset);
		//================================== err
		String errLogFileName = "s_ls_" + module.getElementName() + "flow.errorlog";
		createErrLog(msgQueue, errLogFileName,newPath,charset);
	}

	/**
	 * @param monitor
	 * @param module
	 * @param newPath
	 */
	private void createLSFunctionFile(IProgressMonitor monitor,
			IARESModule module, String newPath,String charset) throws Exception{
		IARESResource[] moduleRes = module.getARESResources(ILogicResType.LOGIC_FUNCTION);//逻辑函数
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
		Collections.sort(modulesReslist,new Comparator<IARESResource>() {
			
			@Override
			public int compare(IARESResource o1, IARESResource o2) {
				try {
					//此时资源为逻辑函数
					LogicFunction lf1 = o1.getInfo(LogicFunction.class);
					LogicFunction lf2 = o2.getInfo(LogicFunction.class);
					if(lf1.getObjectId() != null && lf1.getObjectId() != "" && lf2.getObjectId() != null && lf2.getObjectId() != ""){
						return Integer.valueOf(lf1.getObjectId()) - Integer.valueOf(lf2.getObjectId());
					}
					return 0;
					
				} catch (ARESModelException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		
		//生成代码时，错误信息输出的消息队列
		Queue<IARESProblem> msgQueue = new ArrayDeque<IARESProblem>();
		
		//资源生成代码
		String codeFileName = newPath + "s_ls_" + module.getShortName() + "func.cpp";
		StringBuffer codeBuffer = new StringBuffer();
		// 写入注释头
		ModuleGeneratorHelper.writeCommentHeader(codeBuffer, 
				ModuleGeneratorHelper.getCresProjectExtendProperty(module).getHeadFile(), 
				codeFileName, module.getShortName(), new Date());
		codeBuffer.append("\n");
		// 加入修改记录
		codeBuffer.append(RevisionHistoryGenUtil.getAllRevisionHistoryInfo(LogicModuleGeneratorHelper.getLogicModuleHistorys(module), "//"));
		codeBuffer.append("\n");
		//根据函数代码生车cpp头文件
		StringBuffer cppHeadBuffer = new StringBuffer();
		// 添加依赖模块的英文名，本模块为必定依赖
		List<String> includes = new ArrayList<String>();
		includes.add("s_ls_" + module.getShortName() + "func.h");
		ModuleGeneratorHelper.writeIncludeSection(cppHeadBuffer, includes);
		codeBuffer.append("\n");
		//单个逻辑函数代码生成
		for (IARESResource iaresResource : modulesReslist) {
			if(monitor.isCanceled()){
				return;
			}
			monitor.setTaskName("逻辑函数："+iaresResource.getFullyQualifiedName());
			cppHeadBuffer.append(genLogicFunctionCode(iaresResource,msgQueue));
			monitor.worked(1);
		}
		codeBuffer.append(cppHeadBuffer);
		//写入文件
		writeToFile(codeFileName, codeBuffer.toString(),charset);
		
		//================================头文件
		String hppFileName = newPath + "s_ls_" + module.getShortName() + "func.h";
		List<String> headIncludes = new ArrayList<String>();
		headIncludes.add("hshead.h");

		//模块依赖
		EList<MoudleDepend> depends = ModuleGeneratorHelper.getCresMoudleExtendProperty(module).getDepends();
		for (MoudleDepend moudleDepend : depends) {
			
			//根据moudleDepend找到对应的模块
			for (IARESModule m : module.getARESProject().getModules()) {
				if(StringUtils.equals(m.getElementName(), moudleDepend.getModulePath())){
					// 原子的不需要添加,只添加逻辑模块
					if(m.getParent().getElementName().equals(ICresUIConstant.LOGIC_ROOT_NAME)){
						headIncludes.add("s_ls_" + moudleDepend.getName() + "func.h");
					}
				}
			}
		}
		StringBuffer hppFileBuffer = ModuleGeneratorHelper.generateHeaderFile(cppHeadBuffer, "s_ls_" + module.getShortName() + "func.h", headIncludes.toArray(new String[includes.size()]), null);
		//写入文件
		writeToFile(hppFileName, hppFileBuffer.toString(),charset);
		
		//================================== err
		String errLogFileName = "s_ls_" + module.getElementName() + "func.errorlog";
		createErrLog(msgQueue, errLogFileName,newPath,charset);
	}
	
	/**
	 * 生成逻辑服务测试文件
	 * @param path 
	 * @param module 
	 * @param charSet
	 */
	private void generateLogicServiceTest(IARESModule module, String path,String charset) throws Exception{
		String codeFileNname = path + "s_ls_" + module.getShortName() + ".xml";
		StringBuffer codeBuffer = new StringBuffer();
		
		codeBuffer.append("<?xml  version=\"1.0\" encoding=\"GBK\"?>");
		codeBuffer.append("\r\n");
		codeBuffer.append("<TEST_PACK note=\"ct所用功能函数组件测试包\">");
		codeBuffer.append("\r\n");
		codeBuffer.append("  <Test>");
		codeBuffer.append("\r\n");
		
		//逻辑服务
		IARESResource[] moduleRes = module.getARESResources(ILogicResType.LOGIC_SERVICE);//逻辑服务
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
		Collections.sort(modulesReslist,new Comparator<IARESResource>() {
			
			@Override
			public int compare(IARESResource o1, IARESResource o2) {
				try {
					//此时资源为逻辑服务
					LogicService as1 = o1.getInfo(LogicService.class);
					LogicService as2 = o2.getInfo(LogicService.class);
					return Integer.valueOf(as1.getObjectId()) - Integer.valueOf(as2.getObjectId());
					
				} catch (ARESModelException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		String inparams = "        <in name=\"%s\" value=\"%s\"/>";
		for (IARESResource res : modulesReslist) {
			LogicService service = res.getInfo(LogicService.class);
			if(!ParamGroupUtil.isContainObjectParameter(service.getInputParameters(),res.getARESProject()) && !ParamGroupUtil.isContainObjectParameter(service.getOutputParameters(),res.getARESProject())){
				codeBuffer.append("    <sub id=\"" + service.getObjectId() + "\" block=\"1\"  livetime=\"5000\" pri=\"8\" pack_ver=\"32\" note=\"" + service.getChineseName() + "\">");
				codeBuffer.append("\r\n");
				codeBuffer.append("      <route system=\"\" sub_system=\"\" branch=\"\" esb_name=\"\" esb_no=\"0\" neighbor=\"\" plugin=\"\"/>");
				codeBuffer.append("\r\n");
				codeBuffer.append("      <inparams note=\"" + service.getChineseName() + "\">");
				codeBuffer.append("\r\n");
				List<Parameter> allInputParametersWithNoObjectParameter = new ArrayList<Parameter>();
				ParamGroupUtil.parserParametersWithNoObjectParameter(service.getInputParameters(), allInputParametersWithNoObjectParameter, res.getARESProject());
				for(Parameter inParameter:allInputParametersWithNoObjectParameter){
					codeBuffer.append(String.format(inparams, inParameter.getId(), "")).append("\r\n");
					}
				List<Parameter> allOutParametersWithNoObjectParameter = new ArrayList<Parameter>();
				ParamGroupUtil.parserParametersWithNoObjectParameter(service.getOutputParameters(), allInputParametersWithNoObjectParameter, res.getARESProject());
				for (Parameter outParameter : allOutParametersWithNoObjectParameter) {
					if (StringUtils.indexOf(outParameter.getId(), "IO") >= 0) {
						codeBuffer.append(String.format(inparams, outParameter.getId(), "")).append("\r\n");
					}
				}
				
				codeBuffer.append("      </inparams>");
				codeBuffer.append("\r\n");
				codeBuffer.append("    </sub>");
				codeBuffer.append("\r\n");
			}
			
		}
		codeBuffer.append("  </Test>");
		codeBuffer.append("\r\n");
		codeBuffer.append("</TEST_PACK>");
		codeBuffer.append("\r\n");
		
		writeToFile(codeFileNname, codeBuffer.toString(),charset);
	}
	
	/**
	 * 单个逻辑服务代码生成
	 * @param iaresResource
	 * @param msgQueue
	 * @return
	 */
	private StringBuffer genLogicServiceCode(IARESResource iaresResource,
			Queue<IARESProblem> msgQueue)  throws Exception{
		StringBuffer ret = new StringBuffer();
		Map<Object, Object> context = new HashMap<Object, Object>();
		LogicService logicService = iaresResource.getInfo(LogicService.class);
		// 标记为D的服务不生成代码
		if(StringUtils.contains(logicService.getInterfaceFlag(), "D")){
			return ret;
		}
		
		///如果为多个对象号（以“，”分割），需要生成多分
		String objectID = logicService.getObjectId();
		if(StringUtils.isBlank(objectID)) {
			String message = String.format("逻辑服务[(%s)%s]功能号为空。", logicService.getName(),logicService.getChineseName());
			IARESProblem problem = ARESProblem.createError();
			problem.setMessage(message);
			msgQueue.add(problem);
		}else {
			if(objectID.contains(",")){
				String[] objIDs = objectID.split(",");
				for (String objID : objIDs) {
					logicService.setObjectId(objID.trim());
					context.put(ILogicEngineContextConstant.ResourceModel, logicService);
					ret.append(ResourceEngin.instance.generate(
							new DefaultSkeletonInput(
									ILogicSkeletonFactoryConstant.SKELETONTYPE_LOGIC_SERVICE,
									iaresResource), context,msgQueue));
					ret.append("\r\n");
				}
			}else{
				context.put(ILogicEngineContextConstant.ResourceModel, logicService);
				ret.append(ResourceEngin.instance.generate(
						new DefaultSkeletonInput(
								ILogicSkeletonFactoryConstant.SKELETONTYPE_LOGIC_SERVICE,
								iaresResource), context,msgQueue));
				ret.append("\r\n");
			}
		}
		
		return CodeFormater.formatCByForce(ret);
	}

	/**
	 * 逻辑函数
	 * @param iaresResource
	 * @param msgQueue
	 * @return
	 */
	private StringBuffer genLogicFunctionCode(IARESResource iaresResource,
			Queue<IARESProblem> msgQueue)  throws Exception{
		StringBuffer ret = new StringBuffer();
		Map<Object, Object> context = new HashMap<Object, Object>();
		LogicFunction logicFunction = iaresResource.getInfo(LogicFunction.class);
		// 标记为D的服务不生成代码
		if(StringUtils.contains(logicFunction.getInterfaceFlag(), "D")){
			return ret;
		}
		
		///如果为多个对象号（以“，”分割），需要生成多分
		String objectID = logicFunction.getObjectId();
		if(StringUtils.isNotBlank(objectID) && objectID.contains(",")){
			String[] objIDs = objectID.split(",");
			for (String objID : objIDs) {
				logicFunction.setObjectId(objID.trim());
				context.put(ILogicEngineContextConstant.ResourceModel, logicFunction);
				ret.append(ResourceEngin.instance.generate(
						new DefaultSkeletonInput(
								ILogicSkeletonFactoryConstant.SKELETONTYPE_LOGIC_FUNCTION,
								iaresResource), context,msgQueue));
				ret.append("\r\n");
			}
		}else{
			context.put(ILogicEngineContextConstant.ResourceModel, logicFunction);
			ret.append(ResourceEngin.instance.generate(
					new DefaultSkeletonInput(
							ILogicSkeletonFactoryConstant.SKELETONTYPE_LOGIC_FUNCTION,
							iaresResource), context,msgQueue));
			ret.append("\r\n");
		}
		return CodeFormater.formatCByForce(ret);
	}

	@Override
	public boolean canGenCode(IARESModule module) {
		String rootName = module.getParent().getElementName();
		if(StringUtils.equals(rootName, ICresUIConstant.LOGIC_ROOT_NAME)){
			return true;
		}
		return false;
	}

}
