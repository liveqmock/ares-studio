/**
 * 源程序名称：ScriptUtils.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.script.engin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.window.Window;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.scripting.AresScriptEngineManager;
import com.hundsun.ares.studio.jres.script.util.impl.ARESProjectScriptWrapImpl;
import com.hundsun.ares.studio.jres.script.util.impl.ScriptCalendarUtilImpl;
import com.hundsun.ares.studio.jres.script.util.impl.ScriptFileUtilImpl;
import com.hundsun.ares.studio.jres.script.util.impl.ScriptInUtilImpl;
import com.hundsun.ares.studio.jres.script.util.impl.ScriptOutUtilImpl;
import com.hundsun.ares.studio.jres.script.util.impl.ScriptReferenceUtilImpl;
import com.hundsun.ares.studio.jres.script.util.impl.ScriptStringUtilImpl;
import com.hundsun.ares.studio.jres.script.util.impl.ScriptSysUtilImpl;
import com.hundsun.ares.studio.jres.script.util.impl.ScriptXMLUtilImpl;

/**
 * 脚本工具类。
 * 
 * @author mawb
 * @author sundl
 *
 */
public class ScriptUtils {
	
	private static Logger logger = Logger.getLogger(ScriptUtils.class);
	public static final String MAIN = "main";
	public static final String MODE = "mode";
	public static final int MODE_CONTEXT_MENU = 1;
	public static final int MODE_EDITOR_BUTTON = 2;
	public static final int MODE_BUILDER = 3;
	public static final int MODE_CMD_BUILDER = 4;//命令行编译
	
	private static final ScriptUtils INSTANCE = new ScriptUtils();
	
	static final Logger console = ConsoleHelper.getLogger();
	
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
	public static void excuteJS(  int mode,
									IARESResource jsResource,
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
			ScriptInUtilImpl input = (ScriptInUtilImpl) defaultContext.get("input");
			if (extContext != null)
				defaultContext.putAll(extContext);
			defaultContext.put(ScriptUtils.MODE, mode);
			engine.setBindings(ScriptUtils.toScriptBindings(defaultContext), ScriptContext.ENGINE_SCOPE);
			engine.eval(script);
			if(input.getInput()==Window.CANCEL){
				return;
			}
			Job job = new Job("") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {
						monitor.beginTask(jsRes.getName() +"   正在执行...", IProgressMonitor.UNKNOWN);
						((Invocable) engine).invokeFunction(MAIN, defaultContext);
						monitor.done();
					} catch (ScriptException e) {
						logger.error("脚本执行出错...", e);
						ConsoleHelper.getLogger().error("执行脚本出错...", e);
					} catch (NoSuchMethodException e) {
						logger.error("脚本执行出错...", e);
						ConsoleHelper.getLogger().error("执行脚本出错...", e);
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
	 * 创建一个默认的脚本上下文
	 * @return
	 */
	public static Map<String, Object> createDefaultScriptContext(int mode,IARESResource jsResource,IARESResource res, JRESResourceInfo info, ClassLoader loader) {
		Map<String, Object> context = new HashMap<String, Object>();

		
		if (jsResource != null){
			context.put("project",new ARESProjectScriptWrapImpl( jsResource.getARESProject()));
		}
	
//		model = 3;
		context.put("stringutil", ScriptStringUtilImpl.instance);
		context.put("input", new ScriptInUtilImpl(mode, jsResource,context));
		context.put("output",new ScriptOutUtilImpl(mode));
		context.put("file", ScriptFileUtilImpl.instance);
		context.put("xml", ScriptXMLUtilImpl.instance);
		context.put("sys", ScriptSysUtilImpl.instance);
		context.put("calendar", ScriptCalendarUtilImpl.instance);
		context.put("reference", ScriptReferenceUtilImpl.instance);//增加引用工具类
		context.put("logger", logger);
		return context;
	}
	
	/**
	 * 转化为脚本引擎使用的上下文
	 * @param context
	 * @return
	 */
	public static Bindings toScriptBindings(Map<String, Object> context) {
		return new SimpleBindings(context);
	}
	


    
}
