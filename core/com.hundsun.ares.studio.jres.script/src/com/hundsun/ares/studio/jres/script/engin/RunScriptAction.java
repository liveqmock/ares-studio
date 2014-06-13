/**
 * 源程序名称：RunScriptAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.script.engin;

import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.scripting.AresScriptEngineManager;
import com.hundsun.ares.studio.jres.script.ScriptPlugin;

/**
 * @author gongyf
 */
public class RunScriptAction extends Action  {
	protected String script;
	protected String functionName;
	protected Map<String, Object> context;
	
	@Override
	public String getId() {
		return toString();
	}
	
	/**
	 * @param script the script to set
	 */
	public void setScript(String script) {
		this.script = script;
	}
	
	public String getScript() {
		return script;
	}

	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Map<String, Object> context) {
		this.context = context;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		ScriptEngine engine = AresScriptEngineManager.getScriptEngineManager().getEngineByName(ARESCore.SCRIPT_ENGINE_NAME);
		try {
			engine.setBindings(ScriptUtils.toScriptBindings(context), ScriptContext.ENGINE_SCOPE);
			engine.eval(script);
			if(null!=functionName &&!"".equals(functionName)){
				((Invocable) engine).invokeFunction(functionName, context);
			}
		} catch (Exception e) {
			ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
					"脚本执行错误", e.getMessage(), new Status(Status.ERROR, ScriptPlugin.PLUGIN_ID, e.getMessage(), e));
		}
	}
	
}
