/**
 * 
 */
package com.hundsun.ares.studio.jres.database.compiler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.script.ScriptEngine;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.scripting.AresScriptEngineManager;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;
import com.hundsun.ares.studio.jres.script.ScriptPlugin;

/**
 * @author gongyf
 *
 */
public class DBCompilerUtils {
	
	private static final String KEY_ScriptEngine = "编译用脚本引擎";
	
	/**
	 * 获取脚本引擎（有缓存）
	 * @param context
	 * @return
	 */
	public static  ScriptEngine getScriptEngine(Map<Object, Object> context) {
		ScriptEngine engine = (ScriptEngine) context.get(KEY_ScriptEngine);
		if (engine == null) {
			engine = AresScriptEngineManager.getScriptEngineManager().getEngineByName(ARESCore.SCRIPT_ENGINE_NAME);
		}
		return engine;
	}
	
	/**
	 * 获取数据库对应的脚本文件
	 * 
	 * @param project
	 * @return
	 * @throws CoreException
	 * @throws IOException
	 */
	public static String getScriptContent(IARESProject project , String database) throws CoreException, IOException {
		
		// 准备调用脚本，先找到脚本
		IFolder folder = ARESElementUtil.getModuleRootFolder(project, ScriptPlugin.TOOL_MODULE_ROOT_TYPE);
		if (folder == null || !folder.exists()) {
			folder = ARESElementUtil.getModuleRootFolder(project, ScriptPlugin.OLD_TOOL_MODULE_ROOT_TYPE);
		}
		IFile file = folder.getFile(StringUtils.lowerCase( String.format(IDBConstant.GEN_JS_FILE_FORMAT, database)));
		
		if (file.exists()) {
			InputStream is = null;
			try {
				is = file.getContents();
				
				return IOUtils.toString(is, file.getCharset());
				
			} finally {
				IOUtils.closeQuietly(is);
			}
		} else {
			throw new FileNotFoundException(file.getProjectRelativePath().toString());
		}
		
	}
	
	public static IARESResource getJSResource(IARESProject project, Object resource ,String database) {
		try {
			IARESProjectProperty property = project.getProjectProperty();
			String fileName = "";
			if (resource instanceof TableResourceData) {
				fileName = property.getString("tabledir");
			}else if (resource instanceof ViewResourceData) {
				fileName = property.getString("viewdir");
			}else if (resource instanceof SequenceResourceData) {
				fileName = property.getString("sequencedir");
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
	
}
