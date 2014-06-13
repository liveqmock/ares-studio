/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.script.impl;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.GenCresModuleCodeManager;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.IGenCresModuleCode;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.MakeFileGenerator;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.cres.ui.action.GenModuleCodeType;
import com.hundsun.ares.studio.jres.script.api.wrap.IGenCodeScriptWrap;

/**
 * 原子以,逻辑,过程生成代码脚本API封装
 * @author liaogc
 *
 */
public class GenCodeScriptWrap implements IGenCodeScriptWrap{
	private int resType; /*类型:1:原子,2业务逻辑*,3过程*/
	private int genType;/*genType:1:带中文目录,2:带英文目录,3:不带目录*/
	private IARESProject project;
	private Set<IARESModule> modules = new HashSet<IARESModule>();
	
	private static Logger logger = Logger.getLogger(GenCodeScriptWrap.class);
	static final Logger console = ConsoleHelper.getLogger();
	protected boolean isHeadCode = false;
	protected boolean isEndCode = false;

		
	public GenCodeScriptWrap( IARESProject project){
			this.project = project;
	}

	@Override
	public void genModuleCode(int resType, int genType) {
		this.resType = resType;
		this.genType = genType;
		try {
			init();
		} catch (ARESModelException e1) {
			console.error(e1.getMessage());
		}

		HashSet<IGenCresModuleCode> genModuleCodeSet = GenCresModuleCodeManager.getInstance().getSet();
		//在不带目录生成的情况下，根据依赖关系确定编译顺序
		if(!isGenCodeWithPath()){
			String newPath = ModuleGeneratorHelper.getModuleGenCodePath(project);
			MakeFileGenerator generator = new MakeFileGenerator(modules);
			generator.createMakeFileWithoutFolder(newPath);
		}
		console.info("模块生成开始");
		for (IARESModule module : modules) {
			long l1 = System.currentTimeMillis();
			StringBuffer errLog = new StringBuffer();
			GenCresModuleCodeManager.getInstance();
			for (IGenCresModuleCode genModuleCode : genModuleCodeSet) {
				if(genModuleCode.canGenCode(module)) {
					genModuleCode.setErrLog(errLog);
					genModuleCode.setIsHeadCode(isHeadCode);
					genModuleCode.setIsEndCode(isEndCode);
					genModuleCode.genModuleCode(module, genModuleCode.getContext(project), 
							isGenCodeWithPath(), isGenCodeWithCNamePath(), new NullProgressMonitor());
				}
			} 
			openErrorLog(errLog, true);
			long l2 = System.currentTimeMillis();
			console.info("模块[" + module.getElementName() +"]代码生成用时：" + (l2-l1) + " ms");
		}
		
		for (IGenCresModuleCode genModuleCode : genModuleCodeSet) {
			genModuleCode.clearCache();
		}
	
	}
	
	/**
	 * 根据不同的资源类型初始化对应类型的模块信息
	 * @throws ARESModelException
	 */
	private void init() throws ARESModelException {
		if (resType == 1) {//原子
			IARESModuleRoot root = this.project.getModuleRoot("atom");
			if (root != null) {
				for (IARESModule module : root.getModules()) {
					if(StringUtils.isNotBlank(module.getElementName())){
						this.modules.add(module);
					}
					
				}
			}

		} else if (resType == 2) {//逻辑

			IARESModuleRoot root = this.project.getModuleRoot("logic");
			if (root != null) {
				for (IARESModule module : root.getModules()) {
					if(StringUtils.isNotBlank(module.getElementName())){
						this.modules.add(module);
					}
				}
			}

		} else if (resType == 3) {//过程
			IARESModuleRoot root = this.project.getModuleRoot("procedure");
			if (root != null) {
				for (IARESModule module : root.getModules()) {
					this.modules.add(module);
				}
			}

		}
	}
	

	/**
	 * 打开错误日志
	 * @param errLog
	 * 			错误信息
	 * @param openErrorLog
	 * 			是否打开错误日志文件
	 */
	protected void openErrorLog(StringBuffer errLog,boolean openErrorLog) {
		
		// 错误日志为空的话，不写文件。
		if(!errLog.toString().trim().equals("")) {
			errLog.insert(0, "错误日志：\n");
			String fileName = "genCodeReport" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".txt";
			final IFile fReport = project.getProject().getFile(fileName);
			try {
				if (!fReport.exists()) {
					fReport.create(
						new ByteArrayInputStream(errLog.toString().getBytes(project.getProject().getDefaultCharset())), true,
						new NullProgressMonitor());
				} else {
					fReport.setContents(new ByteArrayInputStream(errLog.toString().getBytes(
						project.getProject().getDefaultCharset())), true, false, new NullProgressMonitor());
				}
				if(openErrorLog){
					//进行出错处理
				}
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean isGenCodeWithPath() {
		if (GenModuleCodeType.NODIR_GENMODULE.getType() != genType) {
			return true;
		}
		return false;
	}

	private boolean isGenCodeWithCNamePath() {
		if (GenModuleCodeType.CH_DIR_GENMODULE.getType() == genType) {
			return true;
		}
		return false;
	}

}
