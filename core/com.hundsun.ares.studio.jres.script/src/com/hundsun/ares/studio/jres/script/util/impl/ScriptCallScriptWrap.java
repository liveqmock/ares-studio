/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.scripting.AresScriptEngineManager;
import com.hundsun.ares.studio.jres.script.ScriptPlugin;
import com.hundsun.ares.studio.jres.script.api.wrap.IScriptCallScriptWarp;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;
import com.hundsun.ares.studio.jres.script.util.wizard.ScriptGenInteWizardModel;
import com.hundsun.ares.studio.jres.script.util.wizard.ScriptGenIntegratedWizard;

/**
 * 脚本调用脚本API封装
 * @author liaogc
 *
 */
public class ScriptCallScriptWrap implements IScriptCallScriptWarp{
	private static final Logger console = ConsoleHelper.getLogger();
	private static Logger logger = Logger.getLogger(ScriptCallScriptWrap.class);
	public static final String MAIN = "main";
	public static final String MODE = "mode";
	public static final int MODE_CONTEXT_MENU = 1;
	public static final int MODE_EDITOR_BUTTON = 2;
	public static final int MODE_BUILDER = 3;
    private IARESProject project ;//调用者脚本
    private ScriptGenIntegratedWizard wizard= null;
    private List<ScriptGenInteWizardModel> scriptModelList = new ArrayList<ScriptGenInteWizardModel>();
	public ScriptCallScriptWrap(IARESProject project ){
		this.project = project;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.util.IScriptCallScriptUitl#runSrcipt(java.lang.String[])
	 */
	@Override
	public void runSrcipt(String[][] scripts) {
		if(null == scripts){
			return ;
		}
		
		for(String[] script:scripts){
			try {
				IARESResource calledJSResource = getJSResource(script[1]);//获得被调用脚本
				
				if(calledJSResource==null){
					console.info(script[1]+"脚本不存在!");
					return ;
				}
				ScriptGenInteWizardModel scriptModel = new ScriptGenInteWizardModel(script[0],script[1]);
				scriptModel.setJsResource(calledJSResource);
				scriptModelList.add(scriptModel);
				
				
			} catch (Exception e) {
				console.error(e.getMessage());
			}
		}
		
		if (Display.getDefault().getThread() != Thread.currentThread()) {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					createIntegratedWizard();
					if (!wizard.isOK()) {
						return;
					}
				}
			});
		} else {

			createIntegratedWizard();
			if (!wizard.isOK()) {
				return;
			}
		}
		if (!wizard.isOK()) {
			return;
		}
		for(ScriptGenInteWizardModel selectedModel :wizard.getSelectedElements()){
			IARESResource calledJSResource = selectedModel.getJsResource();
			try {
				excuteJS(ScriptUtils.MODE_BUILDER, calledJSResource, null, null, getClass().getClassLoader(), wizard.getContextByJsResource(calledJSResource));
			} catch (NoSuchMethodException e1) {
				String baseScriptMessage = "执行脚本错误("+calledJSResource.getElementName()+")";
				logger.error(baseScriptMessage, e1);
				ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
						baseScriptMessage,
						"脚本中不存在main函数", 
						new Status(Status.ERROR, ScriptPlugin.PLUGIN_ID, "脚本中不存在main函数", e1));
			} catch (Exception e) {
				String baseScriptMessage = "执行脚本错误("+calledJSResource.getElementName()+")";
				logger.error("执行脚本错误", e);
				ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), baseScriptMessage, 
						e.getMessage(),
						new Status(Status.ERROR, ScriptPlugin.PLUGIN_ID, e.getMessage(), e));
			}
		
		}
		
	}
	
	/**
	 * 执行js脚本中的main函数
	 * @param mode 模式 1代表右键执行；2代表编辑器中的按钮触发；3代表builder
	 * @param jsResource 脚本资源
	 * @param res        资源，可以是null，在特定的模式或者特定的脚本中才会有
	 * @param info	  	    资源，可以是null，在特定的模式或者特定的脚本中才会有
	 * @param loader     classloader
	 * @param extContext 额外的context函数,如果没有可以为null; 也可以用于覆盖默认的context中的值，慎用。
	 * @throws IOException
	 * @throws CoreException
	 * @throws ScriptException
	 * @throws NoSuchMethodException
	 * @see MODE_CONTEXT_MENU
	 * @see MODE_EDITOR_BUTTON
	 * @see MODE_BUILDER
	 */
	private void excuteJS(  int mode,
									final IARESResource jsResource,
									IARESResource res,
									JRESResourceInfo info,
									ClassLoader loader, 
									Map<String, Object> extContext) 
									throws IOException, CoreException, ScriptException, NoSuchMethodException {
		final ScriptEngine engine = AresScriptEngineManager.getScriptEngineManager().getEngineByName(ARESCore.SCRIPT_ENGINE_NAME);
		InputStream stream = null;
		if(jsResource == null || !jsResource.exists()){
			throw new FileNotFoundException("js脚本文件不存在！");
		}
		try {
			stream = jsResource.openStream();
			String script = null;
			final IResource jsRes = jsResource.getResource();
			
			if (jsRes instanceof IFile) {
				script = IOUtils.toString(stream, ((IFile) jsRes).getCharset());
			} else {
				script = IOUtils.toString(stream, "UTF-8");
			}
			
			// 创建默认脚本上下文
			final Map<String, Object> defaultContext = ScriptUtils.createDefaultScriptContext(mode,jsResource, res,info, loader);
			if (extContext != null)
				defaultContext.putAll(extContext);
			defaultContext.put(ScriptUtils.MODE, mode);
			engine.setBindings(ScriptUtils.toScriptBindings(defaultContext), ScriptContext.ENGINE_SCOPE);
			engine.eval(script);
	
			
			Job job = new Job("") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {
						monitor.beginTask(jsRes.getName() +"   正在执行...", IProgressMonitor.UNKNOWN);
						((Invocable) engine).invokeFunction(MAIN, defaultContext);
						monitor.done();
					} catch (ScriptException e) {
						logger.error("脚本执行出错"+"("+jsResource.getElementName()+")...", e);
						ConsoleHelper.getLogger().error("执行脚本出错...", e);
					} catch (NoSuchMethodException e) {
						logger.error("脚本执行出错"+"("+jsResource.getElementName()+")...", e);
						ConsoleHelper.getLogger().error("脚本执行出错"+"("+jsResource.getElementName()+")...", e);
					}
					return Status.OK_STATUS;
				}
			};
			job.setUser(true);
			job.schedule();
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}
	
	/**
	 * 根据脚本路径 获得对应的脚本资源
	 * @param path(相对于工具资源)
	 * @return
	 * @throws Exception
	 */
	private   IARESResource getJSResource(String path) throws Exception {
		if(StringUtils.isBlank(path)){
			throw new Exception("脚本路径为空!");
		}
		IARESModuleRoot tools = project.getModuleRoot("tools");
		IARESResource[] toolsResources = tools.getResources();
		for(IARESResource resource:toolsResources){
			if(StringUtils.lastIndexOf(resource.getResource().getFullPath().toString(), path)>-1){
				return resource;
			}
		}
	
			return null;
	}
	
	/**
	 * 创建统一向导
	 */
	private void createIntegratedWizard(){
		wizard = new ScriptGenIntegratedWizard();
		wizard.setInputScriptList(scriptModelList);
		wizard.setProject(project);
		WizardDialog wd = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
		Point point = new Point(640, 480);
		wd.setMinimumPageSize(point);
		wd.setPageSize(point);
		wd.create();
		wd.open();
	}

}
