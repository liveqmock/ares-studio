package com.hundsun.ares.studio.cres.ui.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.GenCresModuleCodeManager;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.IGenCresModuleCode;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.MakeFileGenerator;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.ui.action.PopupAction;

public class GenModuleCodeWithPathAction extends PopupAction{
	
	private static Logger logger = Logger.getLogger(GenModuleCodeWithPathAction.class);
	static final Logger console = ConsoleHelper.getLogger();

	static boolean running = false;//是否已在运行
	
	private IARESProject project;
	private Set<IARESModule> modules = new HashSet<IARESModule>();
	
	protected boolean isHeadCode = false;
	
	protected boolean isEndCode = false;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		
		if (running) {
			MessageDialog dialog = new MessageDialog(getShell(), "模块代码生成", null, "其他模块代码正在生成，请稍后在生成", MessageDialog.INFORMATION, new String[] { "确定" }, 0);
			dialog.open();
			return;
		}
		
		Job job = new Job("生成模块代码") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					running = true;
					HashSet<IGenCresModuleCode> genModuleCodeSet = GenCresModuleCodeManager.getInstance().getSet();
					String newPath = ModuleGeneratorHelper.getModuleGenCodePath(project);
					//在不带目录生成的情况下，根据依赖关系确定编译顺序
					if(!isGenCodeWithPath()){
						MakeFileGenerator generator = new MakeFileGenerator(modules);
						generator.createMakeFileWithoutFolder(newPath);
					}
					
					monitor.beginTask("生成模块代码", (modules.size() + genModuleCodeSet.size())*10 + 1);
					monitor.worked(1);
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
										isGenCodeWithPath(), isGenCodeWithCNamePath(), new SubProgressMonitor(monitor, 10));
							}
							monitor.worked(10);
						} 
						openErrorLog(errLog, true);
						if(monitor.isCanceled()){
							break;
						}
						long l2 = System.currentTimeMillis();
						System.out.println("模块[" + module.getElementName() +"]代码生成用时：" + (l2-l1) + " ms");
					}
					
					for (IGenCresModuleCode genModuleCode : genModuleCodeSet) {
						genModuleCode.clearCache();
					}
					monitor.done();
					running = false;
					
				} catch (Exception e) {
					running = false;
				}
				
				openDoneDialog();
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();
		
	}
	
	public static void genModuleCode() {
		
	}
	
	/**
	 * 打开完成对话框
	 */
	protected void openDoneDialog() {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				//添加路径弹出框
				String path = ModuleGeneratorHelper.getModuleGenCodePath(project);
				MessageDialog dialogPath = new MessageDialog(getShell(), "代码生成完成", null, "生成跟路径：" + path, MessageDialog.INFORMATION, new String[] { "确定" }, 0);
				dialogPath.open();
			}
		});
	}
	
	/**
	 * 代码生成是否带目录结构
	 * @return
	 */
	protected boolean isGenCodeWithPath() {
		return true;
	}
	
	/**
	 * 代码生成目录是否使用模块中文名作为目录名
	 * @return
	 */
	protected boolean isGenCodeWithCNamePath() {
		return false;
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
					getShell().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							try {
								IDE.openEditor(getWorkbenchPart().getSite().getPage(), fReport, true);
							} catch (PartInitException e) {
								e.printStackTrace();
							}
						}
					});
				}
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.action.PopupAction#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (running) {
			action.setEnabled(false);
			return;
		}
		modules.clear();
		project = null;
		super.selectionChanged(action, selection);
		
		Iterator i = ((IStructuredSelection)selection).iterator();
		while (i.hasNext()) {
			Object obj = i.next();
			if (obj instanceof IARESModule) {
				modules.add((IARESModule) obj);
				if (project == null) {
					project = ((IARESModule) obj).getARESProject();
				}
			}
		}
		//只有逻辑、原子、过程才可用
		boolean isCresModule = false;
		if (modules.size() > 0) {
			for (IARESModule module : modules) {
				if(module.getParent().getElementName().equalsIgnoreCase("logic")||
						module.getParent().getElementName().equalsIgnoreCase("atom")||
						module.getParent().getElementName().equalsIgnoreCase("procedure")) {
					
					isCresModule = true;
					break;
				}
			}
		} 
		if(isCresModule) {
			action.setEnabled(true);
		}else {
			action.setEnabled(false);
		}
	}

}
