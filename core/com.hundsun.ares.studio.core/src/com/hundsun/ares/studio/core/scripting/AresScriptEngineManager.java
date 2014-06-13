/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.scripting;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.registry.ARESContextRegistry;
import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.core.validate.IAresContext;

/**
 * 脚本引擎管理器
 * @author sundl
 */
public class AresScriptEngineManager {

	public static AresScriptEngineManager instance;
	private ScriptEngineManager scriptEngineManager;
	
	private AresScriptEngineManager() {
		this.scriptEngineManager = new ScriptEngineManager();
	}
	
	/**
	 * 获取管理器的实例
	 * @return 脚本引擎管理器
	 */
	public static AresScriptEngineManager getInstance() {
		if (instance == null)
			instance = new AresScriptEngineManager();
		return instance;
	}
	
	public static ScriptEngineManager getScriptEngineManager() {
		return getInstance().scriptEngineManager;
	}
	
	/**
	 * 获取脚本引擎
	 * @param ext 扩展名，例如js
	 * @return 脚本引擎
	 */
	public ScriptEngine getEngineByExtension(String ext, IARESProject project) {
		Map<String, IAresContext> contexts = ARESContextRegistry.getInstance().createContexts(project);
		ScriptEngine engine = scriptEngineManager.getEngineByExtension(ext);
		if (engine != null) {
			engine.put("contexts", contexts);
			engine.put("project", project);
			engine.put("logger", ConsoleHelper.getLogger());
		}
		return engine;
	}
	
	public void runScript(String script, String ext, IARESProject project) throws ScriptException {
		ScriptEngine engine = getEngineByExtension(ext, project);
		if (engine != null) {
			engine.eval(script);
		}
	}
	
	public void runScript(String script, String ext, IARESProject project,Map<String, Object> para) throws ScriptException {
		ScriptEngine engine = getEngineByExtension(ext, project);
		if (engine != null) {
			for(String name : para.keySet()){
				engine.put(name, para.get(name));
			}
			engine.eval(script);
		}
	}
	
	public void runScript(IFile file) throws ScriptException, UnsupportedEncodingException, CoreException {
		runScript(new String(StringUtil.getString(file.getContents()).getBytes(), "UTF-8"),file.getFileExtension(),ARESCore.create(file.getProject()));
	}
	
	public void runJsScript(String script, IARESProject project,Map<String, Object> para) throws ScriptException {
		runScript(script, "js", project,para);
	}
	
	public void runJsScript(String script, IARESProject project) throws ScriptException {
		runScript(script, "js", project);
	}
	
	public boolean invokeScirptMethod(IFile file, String methodName, Object... args) throws ScriptException, NoSuchMethodException, UnsupportedEncodingException, CoreException {
		return invokeScirptMethod(new String(StringUtil.getString(file.getContents()).getBytes(), "UTF-8"),file.getFileExtension(),ARESCore.create(file.getProject()),methodName,args);
	}
	
	public boolean invokeScirptMethod(String script, String ext, IARESProject project,String methodName, Object... args) throws ScriptException, NoSuchMethodException, UnsupportedEncodingException, CoreException {
		ScriptEngine engine = getEngineByExtension(ext, project);
		if (engine == null)
			return false;
		engine.eval(script);
		Invocable invocable = (Invocable) engine;
		invocable.invokeFunction(methodName, args);
		return true;
	}
	
}
