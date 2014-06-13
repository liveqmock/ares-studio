package com.hundsun.ares.studio.atom.ui;

import java.text.SimpleDateFormat;
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

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomSkeletonFactoryConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomSkeletonFactoryConstantMySQL;
import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.atom.ui.util.AtomModuelGeneratorHelper;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.util.ParamGroupUtil;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESProblem;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.cres.constant.ICresUIConstant;
import com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty;
import com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.GenCresModuleCode;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.FileNameHelper;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.MakeFileBuilder;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.RevisionHistoryGenUtil;
import com.hundsun.ares.studio.engin.format.CodeFormater;
import com.hundsun.ares.studio.engin.logic.ResourceEngin;
import com.hundsun.ares.studio.engin.skeleton.DefaultSkeletonInput;
import com.hundsun.ares.studio.jres.database.utils.ProjectSettingUtil;
import com.hundsun.ares.studio.ui.ARESUI;

public class GenAtomModuleCode extends GenCresModuleCode {

	@Override
	public void genModuleCode(IARESModule module, Map<Object, Object> context,
			boolean isWithPath,  boolean isPathWithCname,IProgressMonitor monitor) {

		if(monitor.isCanceled()){
			return;
		}
		String newPath = ModuleGeneratorHelper.getModuleGenCodePath(module.getARESProject());
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
			IPreferencesService service = Platform.getPreferencesService();
			String charSet = service.getString(ARESUI.PLUGIN_ID, ARESUI.PRE_GENERATE_CHARSET, StringUtils.EMPTY, null);
			//子模块
			IARESModule[] subModules = module.getSubModules();
			//本模块资源
			IARESResource[] mRess = module.getARESResources();
			monitor.beginTask(newPath, mRess.length + subModules.length*10 + 2);
			if(mRess.length > 0) {//有资源才生成
				if(mRess.length == 1 && StringUtils.equals(mRess[0].getType(), "module.xml")) {//有可能是模块属性
					System.out.println(mRess[0].getType());
				}else {
					String databaseType = ProjectSettingUtil.getDatabaseType(module.getARESProject());
					if(databaseType.equalsIgnoreCase(ProjectSettingUtil.MYSQL)){
						createFlowCPPFile(monitor,module,newPath,isWithPath,charSet);//原子服务,MySQL时，生成CPP
						createFunctionCPPFile(monitor,module,newPath,charSet);//原子函数,MySQL时，生成CPP
						createFlowGCCFile_MySQL(monitor,module,newPath,isWithPath,charSet);//GCC文件单独方法维护
					}else{
						createFlowPCFile(monitor,module,newPath,isWithPath,charSet);//原子服务
						createFunctionPCFile(monitor,module,newPath,charSet);//原子函数
						createFlowGCCFile(monitor,module,newPath,isWithPath,charSet);//GCC文件单独方法维护
					}
					createrHFile("as_",module,newPath,charSet);//头信息
					generateAtomicServiceTest(module,newPath,charSet);//原子服务测试文件
					monitor.worked(1);
					//过程
//					new GenProcedureModuleCode(module, errLog).createProcedureFile(monitor, module, newPath);
//					monitor.worked(1);
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
	 * 生成原子服务测试文件
	 * @param path 
	 * @param module 
	 * @param charSet
	 */
	private void generateAtomicServiceTest(IARESModule module, String path,String charSet) throws Exception{
		String codeFileNname = path + "s_as_" + module.getShortName() + ".xml";
		StringBuffer codeBuffer = new StringBuffer();
		codeBuffer.append("<?xml  version=\"1.0\" encoding=\"GBK\"?>");
		codeBuffer.append("\r\n");
		codeBuffer.append("<TEST_PACK note=\"ct所用功能函数组件测试包\">");
		codeBuffer.append("\r\n");
		codeBuffer.append("  <Test>");
		codeBuffer.append("\r\n");
		//原子服务
		IARESResource[] moduleRes = module.getARESResources(IAtomResType.ATOM_SERVICE);//原子服务
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
		Collections.sort(modulesReslist,new Comparator<IARESResource>() {
			
			@Override
			public int compare(IARESResource o1, IARESResource o2) {
				try {
					//此时资源为原子服务
					AtomService as1 = o1.getInfo(AtomService.class);
					AtomService as2 = o2.getInfo(AtomService.class);
					return Integer.valueOf(as1.getObjectId()) - Integer.valueOf(as2.getObjectId());
					
				} catch (ARESModelException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		String inparams = "        <in name=\"%s\" value=\"%s\"/>";
		for (IARESResource res : modulesReslist) {
			AtomService service = res.getInfo(AtomService.class);
			if(!ParamGroupUtil.isContainObjectParameter(service.getInputParameters(),res.getARESProject()) && !ParamGroupUtil.isContainObjectParameter(service.getOutputParameters(),res.getARESProject())){
				codeBuffer.append("    <sub id=\"" + service.getObjectId() + "\" block=\"1\"  livetime=\"" + service.getTimeout() + "\" pri=\"8\" pack_ver=\"32\" note=\"" + service.getChineseName() + "\">");
				codeBuffer.append("\r\n");
				codeBuffer.append("      <route system=\"\" sub_system=\"\" branch=\"\" esb_name=\"\" esb_no=\"0\" neighbor=\"\" plugin=\"\"/>");
				codeBuffer.append("\r\n");
				codeBuffer.append("        <inparams note=\"" + service.getChineseName() + "\">");
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
		
		writeToFile(codeFileNname, codeBuffer.toString(),charSet);
	}

	/* 生成.h文件 */
	protected void createrHFile(String prefix_as, IARESModule module, String path,String charSet) throws Exception{
		String codeFileName = path + "s_" + prefix_as + module.getShortName() + "func.h";
		StringBuffer codeBuffer = new StringBuffer();
		// 写入注释头
		ModuleGeneratorHelper.writeCommentHeader(codeBuffer, 
				ModuleGeneratorHelper.getCresProjectExtendProperty(module).getHeadFile(), 
				codeFileName, module.getShortName(), new Date());
		codeBuffer.append("\r\n");
		codeBuffer.append(getHString(module));
		writeToFile(codeFileName, codeBuffer.toString(),charSet);
	}
	
	private StringBuffer getHString(IARESModule module) throws Exception{
		StringBuffer hStr = new StringBuffer();
		String upperModuleName = module.getShortName().toUpperCase();
		
		hStr.append("#ifndef _S_AS_" + upperModuleName + "FUNC_H\r\n");
		hStr.append("#define _S_AS_" + upperModuleName + "FUNC_H\r\n");
		
		// 头文件中加上对依赖项的引用
		List<String> includes = new ArrayList<String>();
		includes.add("hshead.h");
		//=======模块依赖项
		EList<MoudleDepend> depends = ModuleGeneratorHelper.getCresMoudleExtendProperty(module).getDepends();
		for (MoudleDepend depend : depends) {
			includes.add("s_as_" + depend.getName() + "func.h");
		}

		// 写入include文件
		ModuleGeneratorHelper.writeIncludeSection(hStr, includes);
		// --------------------------end----------------------------------------------------------
		// hStr.append("#include \"hshead.h\"\n");
		IARESResource[] moduleRes = module.getARESResources(IAtomResType.ATOM_FUNCTION);//原子函数
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
		Collections.sort(modulesReslist,new Comparator<IARESResource>() {
			
			@Override
			public int compare(IARESResource o1, IARESResource o2) {
				try {
					//此时资源为原子服务
					AtomFunction af1 = o1.getInfo(AtomFunction.class);
					AtomFunction af2 = o2.getInfo(AtomFunction.class);
					if(af1.getObjectId() != null && af1.getObjectId() != "" && af2.getObjectId() != null && af2.getObjectId() != ""){
						return Integer.valueOf(af1.getObjectId()) - Integer.valueOf(af2.getObjectId());
					}
					return 0;
				} catch (ARESModelException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		for (IARESResource res : modulesReslist) {
			AtomFunction func;
			try {
				func = res.getInfo(AtomFunction.class);
				// 说明
				hStr.append("//" + func.getChineseName() + "\n");
				hStr.append("int FUNCTION_CALL_MODE ");
				hStr.append(StringUtils.isBlank(func.getObjectId())?func.getName():"F"+func.getObjectId());// 函数名
				
				hStr.append("(IAS2Context * lpContext,IF2UnPacker * lpInUnPacker,IF2Packer * lpOutPacker,IConnection * lpParentConn=NULL);\n");
				hStr.append("\n");
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		hStr.append("#endif /* _S_AS_" + upperModuleName + "FUNC_H */\r\n");
		return hStr;
	}

	/**
	 * @param monitor 
	 * @param module
	 * @param path 
	 */
	private void createFunctionPCFile(IProgressMonitor monitor, IARESModule module, String path,String charSet) throws Exception{
		IARESResource[] moduleRes = module.getARESResources(IAtomResType.ATOM_FUNCTION);//原子函数
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
		Collections.sort(modulesReslist,new Comparator<IARESResource>() {
			
			@Override
			public int compare(IARESResource o1, IARESResource o2) {
				try {
					//此时资源为原子服务
					AtomFunction af1 = o1.getInfo(AtomFunction.class);
					AtomFunction af2 = o2.getInfo(AtomFunction.class);
					if(af1.getObjectId() != null && af1.getObjectId() != "" && af2.getObjectId() != null && af2.getObjectId() != ""){
						return Integer.valueOf(af1.getObjectId()) - Integer.valueOf(af2.getObjectId());
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
		
		String codeFileName = path + "s_as_" + module.getShortName() + "func.pc";
		StringBuffer codeBuffer = new StringBuffer();//文件代码
		//注释头信息
		ModuleGeneratorHelper.writeCommentHeader(codeBuffer, 
				ModuleGeneratorHelper.getCresProjectExtendProperty(module).getHeadFile(), 
				codeFileName, module.getShortName(), new Date());
//		GeneratorHelper.writeCommentHeader(codeBuffer, module.getHSProject().getProperty().getCommentHeader(), codeFile.getName(), module.getElementName(), new Date());
		//文件头信息
		codeBuffer.append(getTopString("as_",module));
		//修改记录
		codeBuffer.append(RevisionHistoryGenUtil.getAllRevisionHistoryInfo(AtomModuelGeneratorHelper.getAtomModuleHistorys(module), "//"));
		//资源生成代码
		for (IARESResource iaresResource : modulesReslist) {
			if(monitor.isCanceled()) {
				return;
			}
			monitor.setTaskName("原子函数："+iaresResource.getFullyQualifiedName());
			codeBuffer.append(genAtomFunctionCode(iaresResource,msgQueue));
			monitor.worked(1);
		}
		//写入文件
		writeToFile(codeFileName, codeBuffer.toString(),charSet);
		
		// 日志文件
		String errorLogFileName = "s_as_" + module.getElementName() + "func.errorlog";
		createErrLog(msgQueue, errorLogFileName,path,charSet);
	}
	
	/**
	 * MySQL时，没有proc，直接生成CPP
	 * @param monitor 
	 * @param module
	 * @param path 
	 */
	private void createFunctionCPPFile(IProgressMonitor monitor, IARESModule module, String path,String charSet) throws Exception{
		IARESResource[] moduleRes = module.getARESResources(IAtomResType.ATOM_FUNCTION);//原子函数
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
		Collections.sort(modulesReslist,new Comparator<IARESResource>() {
			
			@Override
			public int compare(IARESResource o1, IARESResource o2) {
				try {
					//此时资源为原子服务
					AtomFunction af1 = o1.getInfo(AtomFunction.class);
					AtomFunction af2 = o2.getInfo(AtomFunction.class);
					if(af1.getObjectId() != null && af1.getObjectId() != "" && af2.getObjectId() != null && af2.getObjectId() != ""){
						return Integer.valueOf(af1.getObjectId()) - Integer.valueOf(af2.getObjectId());
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
		
		String codeFileName = path + "s_as_" + module.getShortName() + "func.cpp";
		StringBuffer codeBuffer = new StringBuffer();//文件代码
		//注释头信息
		ModuleGeneratorHelper.writeCommentHeader(codeBuffer, 
				ModuleGeneratorHelper.getCresProjectExtendProperty(module).getHeadFile(), 
				codeFileName, module.getShortName(), new Date());
//		GeneratorHelper.writeCommentHeader(codeBuffer, module.getHSProject().getProperty().getCommentHeader(), codeFile.getName(), module.getElementName(), new Date());
		//文件头信息
		codeBuffer.append(getTopString("as_",module));
		//修改记录
		codeBuffer.append(RevisionHistoryGenUtil.getAllRevisionHistoryInfo(AtomModuelGeneratorHelper.getAtomModuleHistorys(module), "//"));
		//资源生成代码
		for (IARESResource iaresResource : modulesReslist) {
			if(monitor.isCanceled()) {
				return;
			}
			monitor.setTaskName("原子函数："+iaresResource.getFullyQualifiedName());
			codeBuffer.append(genAtomFunctionCode(iaresResource,msgQueue));
			monitor.worked(1);
		}
		//写入文件
		writeToFile(codeFileName, codeBuffer.toString(),charSet);
		
		// 日志文件
		String errorLogFileName = "s_as_" + module.getElementName() + "func.errorlog";
		createErrLog(msgQueue, errorLogFileName,path,charSet);
	}

	/**
	 * @param monitor 
	 * @param module
	 * @param path 
	 */
	private void createFlowPCFile(IProgressMonitor monitor, IARESModule module, String path, boolean isWithPath,String charSet) throws Exception{
		IARESResource[] moduleRes = module.getARESResources(IAtomResType.ATOM_SERVICE);//原子服务
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
		Collections.sort(modulesReslist,new Comparator<IARESResource>() {
			
			@Override
			public int compare(IARESResource o1, IARESResource o2) {
				try {
					//此时资源为原子服务
					AtomService as1 = o1.getInfo(AtomService.class);
					AtomService as2 = o2.getInfo(AtomService.class);
					return Integer.valueOf(as1.getObjectId()) - Integer.valueOf(as2.getObjectId());
					
				} catch (ARESModelException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		
		//生成代码时，错误信息输出的消息队列
		Queue<IARESProblem> msgQueue = new ArrayDeque<IARESProblem>();
		
		//资源生成代码
		String codeFileName = path + "s_as_" + module.getShortName() + "flow.pc";
		StringBuffer codeBuffer = new StringBuffer();
		// 写入注释头
		ModuleGeneratorHelper.writeCommentHeader(codeBuffer, 
				ModuleGeneratorHelper.getCresProjectExtendProperty(module).getHeadFile(), 
				codeFileName, module.getShortName(), new Date());
		codeBuffer.append("\n");
		//文件头信息
		codeBuffer.append(getTopString("as_",module).toString());
		// 加入修改记录
		codeBuffer.append(RevisionHistoryGenUtil.getAllRevisionHistoryInfo(AtomModuelGeneratorHelper.getAtomModuleHistorys(module), "//"));
		//文件代码
		for (IARESResource iaresResource : modulesReslist) {
			if(monitor.isCanceled()){
				return;
			}
			monitor.setTaskName("原子服务："+iaresResource.getFullyQualifiedName());
			codeBuffer.append(genAtomServiceCode(iaresResource,msgQueue));
			monitor.worked(1);
		}
		//文件尾信息
		codeBuffer.append(getAtomicFlowEndString(modulesReslist,module).toString());
		writeToFile(codeFileName, codeBuffer.toString(),charSet);
		
		
		
//		// mvc
//		String mvcFileName = path + "s_as_" + module.getElementName() + "flow.mvc";
//		HashSet<String> mvcDepends = new HashSet<String>();
//		// mvc 取所有依赖
//		List<MoudleDepend> mvcDps = ModuleGeneratorHelper.getAllDepends(module);
//		for (MoudleDepend md : mvcDps) {
//			mvcDepends.add("s_as_" + md.getName() + "func");
//		}
//		mvcDepends.remove("s_as_" + module.getShortName() + "func");
//		
//		StringBuffer mvc = new StringBuffer();
//		MakeFileBuilder.writeASMvcMakeFile(module.getARESProject(), mvc, 
//				cresMoudleExtendProperty.getDataBaseConn(), 
//				"s_as_" + module.getShortName(),
//				"s_as_" + module.getShortName() + "flow", 
//				"s_as_" + module.getShortName() + "func", 
//				mvcDepends.toArray(new String[0]));
//		
//		writeToFile(mvcFileName, mvc.toString());
		// err
		String errLogFileName = "s_as_" + module.getElementName() + "flow.errorlog";
		createErrLog(msgQueue, errLogFileName,path,charSet);
			
	}
	
	/**
	 * MySQL时，没有proc，直接生成CPP
	 * @param monitor 
	 * @param module
	 * @param path 
	 */
	private void createFlowCPPFile(IProgressMonitor monitor, IARESModule module, String path, boolean isWithPath,String charSet) throws Exception{
		IARESResource[] moduleRes = module.getARESResources(IAtomResType.ATOM_SERVICE);//原子服务
		List<IARESResource> modulesReslist = Arrays.asList(moduleRes);
		//排序
		Collections.sort(modulesReslist,new Comparator<IARESResource>() {
			
			@Override
			public int compare(IARESResource o1, IARESResource o2) {
				try {
					//此时资源为原子服务
					AtomService as1 = o1.getInfo(AtomService.class);
					AtomService as2 = o2.getInfo(AtomService.class);
					return Integer.valueOf(as1.getObjectId()) - Integer.valueOf(as2.getObjectId());
					
				} catch (ARESModelException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		
		//生成代码时，错误信息输出的消息队列
		Queue<IARESProblem> msgQueue = new ArrayDeque<IARESProblem>();
		
		//资源生成代码
		String codeFileName = path + "s_as_" + module.getShortName() + "flow.cpp";
		StringBuffer codeBuffer = new StringBuffer();
		// 写入注释头
		ModuleGeneratorHelper.writeCommentHeader(codeBuffer, 
				ModuleGeneratorHelper.getCresProjectExtendProperty(module).getHeadFile(), 
				codeFileName, module.getShortName(), new Date());
		codeBuffer.append("\n");
		//文件头信息
		codeBuffer.append(getTopString("as_",module).toString());
		// 加入修改记录
		codeBuffer.append(RevisionHistoryGenUtil.getAllRevisionHistoryInfo(AtomModuelGeneratorHelper.getAtomModuleHistorys(module), "//"));
		//文件代码
		for (IARESResource iaresResource : modulesReslist) {
			if(monitor.isCanceled()){
				return;
			}
			monitor.setTaskName("原子服务："+iaresResource.getFullyQualifiedName());
			codeBuffer.append(genAtomServiceCode(iaresResource,msgQueue));
			monitor.worked(1);
		}
		//文件尾信息
		codeBuffer.append(getAtomicFlowEndString(modulesReslist,module).toString());
		writeToFile(codeFileName, codeBuffer.toString(),charSet);
		
		String errLogFileName = "s_as_" + module.getElementName() + "flow.errorlog";
		createErrLog(msgQueue, errLogFileName,path,charSet);
			
	}
	
	
	private void createFlowGCCFile(IProgressMonitor monitor, IARESModule module, String path, boolean isWithPath,String charSet) throws Exception{
		CresMoudleExtendProperty cresMoudleExtendProperty = ModuleGeneratorHelper.getCresMoudleExtendProperty(module);
		//==================== gcc
		String gccName = "s_as_" + module.getShortName() + "flow.gcc";
		//编译命令文件
		createMakeOrderFile(gccName, path,isWithPath,charSet);
		
		String gccFileName = path + gccName;
		// GCC 只取第一层依赖
		HashSet<String> gccDepends = new HashSet<String>();
		EList<MoudleDepend> depends = cresMoudleExtendProperty.getDepends();
		for (MoudleDepend moudleDepend : depends) {
			gccDepends.add("s_as_" + moudleDepend.getName() + "flow");
		}
		gccDepends.remove("s_as_" + module.getShortName() + "flow");
		
		StringBuffer gcc = new StringBuffer();
		MakeFileBuilder.writeASGccMakeFile(module.getARESProject(), gcc, 
				cresMoudleExtendProperty.getDataBaseConn(), "s_as_" + module.getShortName(), 
				"s_as_" + module.getShortName() + "flow", "s_as_" + module.getShortName() + "func", 
				gccDepends.toArray(new String[0]));

		writeToFile(gccFileName,gcc.toString(),charSet);
	}
	
	private void createFlowGCCFile_MySQL(IProgressMonitor monitor, IARESModule module, String path, boolean isWithPath,String charSet) throws Exception{
		CresMoudleExtendProperty cresMoudleExtendProperty = ModuleGeneratorHelper.getCresMoudleExtendProperty(module);
		//==================== gcc
		String gccName = "s_as_" + module.getShortName() + "flow.gcc";
		//编译命令文件
		createMakeOrderFile(gccName, path,isWithPath,charSet);
		
		String gccFileName = path + gccName;
		// GCC 只取第一层依赖
		HashSet<String> gccDepends = new HashSet<String>();
		EList<MoudleDepend> depends = cresMoudleExtendProperty.getDepends();
		for (MoudleDepend moudleDepend : depends) {
			gccDepends.add("s_as_" + moudleDepend.getName() + "flow");
		}
		gccDepends.remove("s_as_" + module.getShortName() + "flow");
		
		StringBuffer gcc = new StringBuffer();
		//MySQL时，只有CPP文件，数据库信息不需要
		MakeFileBuilder.writeLSGccMakeFile_MySQL(module.getARESProject(), gcc, "s_as_" + module.getShortName(), 
				"s_as_" + module.getShortName() + "flow", "s_as_" + module.getShortName() + "func", 
				gccDepends.toArray(new String[0]));

		writeToFile(gccFileName,gcc.toString(),charSet);
	}
	

	/**
	 * 原子服务代码生成后需要添加的代码
	 * @param ress
	 * @return
	 * @throws Exception
	 */
	private StringBuffer getAtomicFlowEndString(List<IARESResource> ress,IARESModule module) throws Exception{
		StringBuffer endBuffer = new StringBuffer();
		endBuffer.append("int  FUNCTION_CALL_MODE GetBizFunctionsInfo(int Index, LPBIZ_FUNC_INFO  ppv )\n");
		endBuffer.append("{\n");
		endBuffer.append("int iReturnCode = ASFC_EXISTS;\n");
		endBuffer.append("switch (Index)\n");
		endBuffer.append("{\n");
		for (int index = 0; index < ress.size(); index++) {
			AtomService as = ress.get(index).getInfo(AtomService.class);
			endBuffer.append("case " + index + ":\n");
			endBuffer.append("if  (ppv!=NULL)\n");
			endBuffer.append("{\n");
			endBuffer.append("ppv->dwFunctionNo = " +as.getObjectId() + ";\n");
			endBuffer.append("ppv->iVersion = " + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ";\n");
			endBuffer.append("ppv->ReqProc = F" + as.getObjectId() + ";\n");
			endBuffer.append("ppv->AnsProc = NULL;\n");
			endBuffer.append("ppv->Caption = \"" + as.getChineseName() + "\";\n");
			endBuffer.append("}\n");
			endBuffer.append("break;\n");
		}
		endBuffer.append("default:iReturnCode = ASFC_NONE;\n");
		endBuffer.append("}\n");
		endBuffer.append("return iReturnCode;\n");
		endBuffer.append("}\n");
		endBuffer.append("char *  ASFC_CALL_MODE GetLibVersion()\n");
		endBuffer.append("{\n");
		endBuffer.append("return \"" + AtomModuelGeneratorHelper.getAtomModuleLastVersion(module) + "\";\n");
		endBuffer.append("}\n");
		endBuffer.append("void  FUNCTION_CALL_MODE OnLoad(char * arg)\n");
		endBuffer.append("{\n");
		endBuffer.append("}\n");
		endBuffer.append("void  FUNCTION_CALL_MODE OnUnload()\n");
		endBuffer.append("{\n");
		endBuffer.append("}\n");
		endBuffer.append("void  FUNCTION_CALL_MODE OnStart()\n");
		endBuffer.append("{\n");
		endBuffer.append("}\n");
		endBuffer.append("void  FUNCTION_CALL_MODE OnStop()\n");
		endBuffer.append("{\n");
		endBuffer.append("}\n");
		return endBuffer;
	}
	
	/* 生成文件头信息 */
	private StringBuffer getTopString(String prefix_as,IARESModule module) {
		StringBuffer topStr = new StringBuffer();
		topStr.append("#include \"s_" + prefix_as + module.getShortName() + "func.h\"\r\n");
		//topStr.append("#define SQLCA_STORAGE_CLASS extern\r\n");
		//topStr.append("#include <sqlca.h>\r\n");
		//topStr.append("EXEC SQL CONTEXT USE DEFAULT;\r\n");
		return topStr;
	}
	
	private StringBuffer genAtomFunctionCode(IARESResource as,Queue<IARESProblem> msgQueue) throws Exception{
		StringBuffer ret = new StringBuffer();
		Map<Object, Object> context = new HashMap<Object, Object>();
		AtomFunction atomFunc = as.getInfo(AtomFunction.class);
		///存在多个对象号的情况
		String objectId = atomFunc.getObjectId();
		String databaseType = ProjectSettingUtil.getDatabaseType(as.getARESProject());
		if(StringUtils.isNotBlank(objectId) && objectId.contains(",")){
			String[] objIDs = objectId.split(",");
			for (String objID : objIDs) {
				atomFunc.setObjectId(objID.trim());
				context.put(IAtomEngineContextConstant.ResourceModel, atomFunc);
				if(databaseType.equalsIgnoreCase(ProjectSettingUtil.MYSQL)){
					ret.append(ResourceEngin.instance.generate(
							new DefaultSkeletonInput(
									IAtomSkeletonFactoryConstantMySQL.SKELETONTYPE_ATOM_FUNCTION_MYSQL,
									as), context,msgQueue));
				}else{
					ret.append(ResourceEngin.instance.generate(
							new DefaultSkeletonInput(
									IAtomSkeletonFactoryConstant.SKELETONTYPE_ATOM_FUNCTION,
									as), context,msgQueue));
				}
				
				ret.append("\r\n");
			}
		}else{
			context.put(IAtomEngineContextConstant.ResourceModel, atomFunc);
			if(databaseType.equalsIgnoreCase(ProjectSettingUtil.MYSQL)){
				ret.append(ResourceEngin.instance.generate(
						new DefaultSkeletonInput(
								IAtomSkeletonFactoryConstantMySQL.SKELETONTYPE_ATOM_FUNCTION_MYSQL,
								as), context,msgQueue));
			}else{
				ret.append(ResourceEngin.instance.generate(
						new DefaultSkeletonInput(
								IAtomSkeletonFactoryConstant.SKELETONTYPE_ATOM_FUNCTION,
								as), context,msgQueue));
			}
			
			ret.append("\r\n");
		}

		return CodeFormater.formatProc(ret);
	}
	
	private StringBuffer genAtomServiceCode(IARESResource as,Queue<IARESProblem> msgQueue) throws Exception{
		StringBuffer ret = new StringBuffer();
		Map<Object, Object> context = new HashMap<Object, Object>();
		///如果为多个对象号（以“，”分割），需要生成多分
		AtomService atomService = as.getInfo(AtomService.class);
		String databaseType = ProjectSettingUtil.getDatabaseType(as.getARESProject());
		String objectID = atomService.getObjectId();
		if(StringUtils.isBlank(objectID)) {
			String message = String.format("原子服务[(%s)%s]对象号为空。", atomService.getName(),atomService.getChineseName());
			IARESProblem problem = ARESProblem.createError();
			problem.setMessage(message);
			msgQueue.add(problem);
		}else {
			if(objectID.contains(",")){
				String[] objIDs = objectID.split(",");
				for (String objID : objIDs) {
					atomService.setObjectId(objID.trim());
					context.put(IAtomEngineContextConstant.ResourceModel, atomService);
					if(databaseType.equalsIgnoreCase(ProjectSettingUtil.MYSQL)){
						ret.append(ResourceEngin.instance.generate(
								new DefaultSkeletonInput(
										IAtomSkeletonFactoryConstantMySQL.SKELETONTYPE_ATOM_SERVICE_MYSQL,
										as), context,msgQueue));
					}else{
						ret.append(ResourceEngin.instance.generate(
								new DefaultSkeletonInput(
										IAtomSkeletonFactoryConstant.SKELETONTYPE_ATOM_SERVICE,
										as), context,msgQueue));
					}
					ret.append("\r\n");
				}
			}else{
				context.put(IAtomEngineContextConstant.ResourceModel, atomService);
				if(databaseType.equalsIgnoreCase(ProjectSettingUtil.MYSQL)){
					ret.append(ResourceEngin.instance.generate(
							new DefaultSkeletonInput(
									IAtomSkeletonFactoryConstantMySQL.SKELETONTYPE_ATOM_SERVICE_MYSQL,
									as), context,msgQueue));
				}else{
					ret.append(ResourceEngin.instance.generate(
							new DefaultSkeletonInput(
									IAtomSkeletonFactoryConstant.SKELETONTYPE_ATOM_SERVICE,
									as), context,msgQueue));
				}
				
				ret.append("\r\n");
			}
		}
		
		return CodeFormater.formatProc(ret);
	}
	

	@Override
	public boolean canGenCode(IARESModule module) {
		String rootName = module.getParent().getElementName();
		if(StringUtils.equals(rootName, ICresUIConstant.ATOM_ROOT_NAME)){
			return true;
		}
		return false;
	}
	

}
