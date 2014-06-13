package com.hundsun.ares.studio.jres.model.metadata.util;

import java.io.IOException;
import java.util.Map;

import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;

public class MetadataScriptUtil {

	public static IARESResource getJSResource(String path) throws Exception {
		if(StringUtils.isBlank(path)){
			throw new Exception("脚本路径为空！");
		}
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = root.getFile(new Path(path));
		return (IARESResource) ARESCore.create(file);
	}
	
	/**
	 * 执行一个Metadata中的用户操作
	 * @param operation 用户操作定义对象
	 * @param mode		脚本的执行模式，参考ScriptUtils中的常量定义
	 * @param res		
	 * @param info
	 * @param loader
	 * @param extContext 	额外的context参数
	 * @throws NoSuchMethodException 
	 * @throws ScriptException 
	 * @throws CoreException 
	 * @throws IOException 
	 */
	public static void runMetadataOperation(Operation operation, 
												int mode, 
												IARESResource res, 
												JRESResourceInfo info, 
												ClassLoader loader,
												Map<String, Object> extContext) throws IOException, CoreException, ScriptException, NoSuchMethodException,Exception {
		IARESResource jsResource = getJSResource(operation.getFile());
		ScriptUtils.excuteJS(mode, jsResource, res, info, loader, extContext);
	}
	
}
