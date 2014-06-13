package com.hundsun.ares.studio.jres.basicdata.compile;

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
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.scripting.AresScriptEngineManager;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicdataConstants;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.ui.scriptwrap.MasterSlaveLinkTableScriptWrapImpl;
import com.hundsun.ares.studio.jres.basicdata.ui.scriptwrap.MasterSlaveTableScriptWrapImpl;
import com.hundsun.ares.studio.jres.basicdata.ui.scriptwrap.SingleTableScriptWrapImpl;
import com.hundsun.ares.studio.jres.compiler.CompilationResult;
import com.hundsun.ares.studio.jres.compiler.CompileUtils;
import com.hundsun.ares.studio.jres.compiler.IResourceCompiler;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;

public class BasicDataInfoCompiler implements IResourceCompiler {

	private String functionName;
	
	public BasicDataInfoCompiler(String functionName) {
		super();
		this.functionName = functionName;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.compiler.IResourceCompiler#compile(java.lang.Object, java.util.Map)
	 */
	@Override
	public CompilationResult compile(Object resource,
			Map<Object, Object> context) {

		BasicDataCompilationResult result = new BasicDataCompilationResult();
		
		IARESResource res = CompileUtils.getARESResource(resource, context);
		JRESResourceInfo info = (JRESResourceInfo) resource;
		
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
				
				IARESResource jsResource = getJSResource(project,resource);
				if (jsResource == null) {
					result.setSql("数据库脚本不存在");
					return result;
				}
				Object returnSql = runScript(ScriptUtils.MODE_BUILDER, jsResource, res, info, getClass().getClassLoader(), extContext);
				result.setSql(returnSql.toString());
			} catch (Exception e) {
				result.setSql("脚本调用失败：" + e.getMessage());
			}
		} else {
			result.setSql("目前不支持在引用包中生成sql");
		}

		return result;
	}

	/**
	 * @param project
	 * @param resource
	 * @param database
	 * @return
	 */
	private IARESResource getJSResource(IARESProject project, Object resource) {
		try {
			IARESProjectProperty property = project.getProjectProperty();
			String fileName = "";
			if (resource instanceof BasicDataBaseModel) {
				fileName = property.getString(IBasicdataConstants.BASICDATA_SCRIPT_DIR_ID);
			}
			if (StringUtils.isNotBlank(fileName)) {
				IFile file = project.getProject().getFile(fileName);
				return (IARESResource) ARESCore.create(file);
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void clean(Object resource, Map<Object, Object> context) {
		
	}

	/**
	 * 预览数据库脚本
	 * 
	 * @param mode
	 * @param jsResource
	 * @param res
	 * @param info
	 * @param loader
	 * @param extContext
	 * @throws IOException
	 * @throws CoreException
	 * @throws ScriptException
	 * @throws NoSuchMethodException
	 */
	private Object runScript (  int mode,
			IARESResource jsResource,
			IARESResource res,
			JRESResourceInfo info,
			ClassLoader loader, 
			Map<String, Object> extContext)throws IOException, CoreException, ScriptException, NoSuchMethodException{
		
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
			Map<String, Object> defaultContext = ScriptUtils.createDefaultScriptContext(mode,jsResource, res,info, loader);
			if (extContext != null)
				defaultContext.putAll(extContext);
			defaultContext.put(ScriptUtils.MODE, mode);
			engine.setBindings(ScriptUtils.toScriptBindings(defaultContext), ScriptContext.ENGINE_SCOPE);
			engine.eval(script);

			if (StringUtils.equals(res.getType(), IBasicDataRestypes.singleTable)){
				sql = ((Invocable) engine).invokeFunction(functionName, new SingleTableScriptWrapImpl(res), defaultContext);
			}else if (StringUtils.equals(res.getType(), IBasicDataRestypes.MasterSlaveTable)) {
				sql = ((Invocable) engine).invokeFunction(functionName, new MasterSlaveTableScriptWrapImpl(res), defaultContext);
			}else if (StringUtils.equals(res.getType(), IBasicDataRestypes.MasterSlaveLinkTable)) {
				sql = ((Invocable) engine).invokeFunction(functionName,new MasterSlaveLinkTableScriptWrapImpl(res), defaultContext);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			IOUtils.closeQuietly(stream);
		}
		return sql;
	}
	
}
