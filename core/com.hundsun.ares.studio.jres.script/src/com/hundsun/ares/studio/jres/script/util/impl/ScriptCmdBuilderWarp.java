/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.scripting.AresScriptEngineManager;
import com.hundsun.ares.studio.jres.script.ScriptPlugin;
import com.hundsun.ares.studio.jres.script.api.wrap.IScriptCmdBuilderWarp;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;
import com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionConfigReader;
import com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionRoot;
import com.hundsun.ares.studio.jres.script.util.wizard.ScriptGenInteWizardModel;

/**
 * 命令行编译脚本封装
 * @author liaogc
 *
 */
public class ScriptCmdBuilderWarp implements IScriptCmdBuilderWarp{

	private static final Logger console = ConsoleHelper.getLogger();/*ARES控制台*/
	private static Logger logger = Logger.getLogger(ScriptCallScriptWrap.class);/*日志*/
    private IARESProject project ;/*脚本所在*/
    private List<ScriptGenInteWizardModel> scriptModelList = new ArrayList<ScriptGenInteWizardModel>();/*脚本*/
    private Map<IARESResource,Map<String, Object>>contexts =  new HashMap<IARESResource,Map<String, Object>>();// 总的上下文
	public ScriptCmdBuilderWarp(IARESProject project ){
		this.project = project;
	}
	
	@Override
	public void build(String[][] scripts,Map<String,Object> parameters) {
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
		loadConfigXML();//加脚本对应的配置信息
		
		for(ScriptGenInteWizardModel model :scriptModelList){
			IARESResource calledJSResource = model.getJsResource();
			try {
				excuteJS(calledJSResource, null, null, getClass().getClassLoader(), contexts.get(calledJSResource));
			} catch (NoSuchMethodException e1) {
				String baseScriptMessage = "执行脚本错误("+calledJSResource.getElementName()+")";
				logger.error(baseScriptMessage, e1);
			} catch (Exception e) {
				String baseScriptMessage = "执行脚本错误("+calledJSResource.getElementName()+")";
				logger.error(baseScriptMessage, e);
			}
		
		}
		
	}
	/**
	 * 加载脚本脚本对应的配置信息
	 */
	private void loadConfigXML(){
		if(scriptModelList==null || scriptModelList.size()==0){
			return ;
		}
		for(ScriptGenInteWizardModel model :scriptModelList ){
			Map<String, Object>context = new HashMap<String, Object>();
			String filepath = String.format("/%s.xml", model.getJsResource().getName());
			
			//2013年5月13日9:27:22 脚本带模块，xml文件可能不在默认模块下面，需要取脚本直接所在的模块目录
			IFolder rootFolder = (IFolder) model.getJsResource().getParent().getResource();
			
			if(rootFolder == null){
				console.error(String.format(".respath中不存在扩展点[%s]对应的模块根配置。", "com.hundsun.ares.studio.jres.moduleroot.tools"));
				return ;
			}
			IFile file =  rootFolder.getFile(filepath);
			Map<String, Object>jsContext = new HashMap<String, Object>();
			if(!file.exists()){
				if(context!=null){
					jsContext.putAll(context);
					contexts.put(model.getJsResource(), jsContext);
				}
				continue;
			}
			UserOptionConfigReader instance = new UserOptionConfigReader();
			try {
				final UserOptionRoot root = instance.read(file.getContents());
				root.setDefaultValue();
				model.setOptionRoot(root);
				jsContext.putAll(root.getOptionValue());
				contexts.put(model.getJsResource(), jsContext);
				
			} catch (Exception e) {
				e.printStackTrace();
				console.error(String.format("获取用户输入失败，详细信息:%s", e.getMessage()));
			}
				
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
	 * 执行js脚本中的main函数
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
	private void excuteJS( final IARESResource jsResource,
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
			final Map<String, Object> defaultContext = ScriptUtils.createDefaultScriptContext(ScriptUtils.MODE_CMD_BUILDER,jsResource, res,info, loader);
			if (extContext != null)
				defaultContext.putAll(extContext);
			defaultContext.put(ScriptUtils.MODE, ScriptUtils.MODE_CMD_BUILDER);
			engine.setBindings(ScriptUtils.toScriptBindings(defaultContext), ScriptContext.ENGINE_SCOPE);
			engine.eval(script);
			

			try {
				console.info(jsRes.getName() +"   正在执行...");
				((Invocable) engine).invokeFunction(ScriptUtils.MAIN, defaultContext);
				console.info(jsRes.getName() +"   完成");
			} catch (ScriptException e) {
				logger.error("脚本执行出错"+"("+jsResource.getElementName()+")...", e);
				ConsoleHelper.getLogger().error("执行脚本出错...", e);
			} catch (NoSuchMethodException e) {
				logger.error("脚本执行出错"+"("+jsResource.getElementName()+")...", e);
				ConsoleHelper.getLogger().error("脚本执行出错"+"("+jsResource.getElementName()+")...", e);
			}
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}
	


}
