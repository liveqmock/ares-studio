/**
 * 
 */
package com.hundsun.ares.studio.jres.database.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.scripting.AresScriptEngineManager;
import com.hundsun.ares.studio.jres.clearinghouse.core.script.impl.DatabaseUserScriptWrapImpl;
import com.hundsun.ares.studio.jres.compiler.CompilationResult;
import com.hundsun.ares.studio.jres.compiler.CompileUtils;
import com.hundsun.ares.studio.jres.compiler.IResourceCompiler;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;

/**
 * @author yanwj06282
 *
 */
public class TableUserInfoCompiler implements IResourceCompiler{

	private String functionName;
	
	public TableUserInfoCompiler(String functionName) {
		this.functionName = functionName;
	}
	
	@Override
	public CompilationResult compile(Object resource,
			Map<Object, Object> context) {

		DBCompilationResult result = new DBCompilationResult();
		
		String database = (String)context.get(IDBConstant.DATABASE_TYPE);
		
		if (database == null || StringUtils.isBlank(database.toString())) {
			result.setSql("生成错误,未选择数据库类型！");
			return result;
		}
		
		IARESResource res = CompileUtils.getARESResource(resource, context);
		DatabaseResourceData info = (DatabaseResourceData) resource;
		
		IARESBundle bundle = res.getBundle();
		if (bundle instanceof IARESProject) {
			IARESProject project = (IARESProject) bundle;
			try {
				Map<String, Object> extContext = new HashMap<String, Object>();
				extContext.put("functionName", functionName);
				
				for (Object key : context.keySet()) {
					if (key instanceof String) {
						extContext.put((String)key, context.get(key));
					}
				}
				
				IARESResource jsResource = getJSResource(project, database);
				if (jsResource == null) {
					result.setSql("Oracle用户权限脚本不存在");
					return result;
				}
				String sql = runScript(ScriptUtils.MODE_BUILDER, jsResource, res, info, getClass().getClassLoader(), extContext);
				
				result.setSql(sql);
			} catch (Exception e) {
				result.setSql("脚本调用失败：" + e.getMessage());
			}
		} else {
			result.setSql("目前不支持在引用包中生成sql");
		}

		return result;
	}

	private IARESResource getJSResource(IARESProject project, String database) {
		IARESProjectProperty property;
		try {
			property = project.getProjectProperty();
			String dir = property.getString("userdir");
			if (StringUtils.isNotBlank(dir)) {
				IFile file = project.getProject().getFile(dir);
				return (IARESResource) ARESCore.create(file);
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String runScript(int modeBuilder, IARESResource jsResource,
			IARESResource res, DatabaseResourceData info,
			ClassLoader classLoader, Map<String, Object> extContext) throws ScriptException, NoSuchMethodException, IOException, CoreException {
		
		Object sql = "";
		ScriptEngine engine = AresScriptEngineManager.getScriptEngineManager().getEngineByName(ARESCore.SCRIPT_ENGINE_NAME);
		InputStream stream = null;
		try {
			stream = jsResource.openStream();
			String script = null;
			IResource jsRes = jsResource.getResource();
			
			if (jsRes instanceof IFile) {
				script = IOUtils.toString(stream, ((IFile) jsRes).getCharset());
			} else {
				script = IOUtils.toString(stream, "UTF-8");
			}
			
			// 创建默认脚本上下文
			Map<String, Object> defaultContext = ScriptUtils.createDefaultScriptContext(modeBuilder,jsResource, res,info, classLoader);
			if (extContext != null)
				defaultContext.putAll(extContext);
			defaultContext.put(ScriptUtils.MODE, modeBuilder);
			engine.setBindings(ScriptUtils.toScriptBindings(defaultContext), ScriptContext.ENGINE_SCOPE);
			engine.eval(script);
			sql = ((Invocable) engine).invokeFunction(functionName,new DatabaseUserScriptWrapImpl(res), defaultContext);
		} finally {
			IOUtils.closeQuietly(stream);
		}
		return sql.toString();
	}

	@Override
	public void clean(Object resource, Map<Object, Object> context) {
		
	}

}
