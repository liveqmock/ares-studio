package com.hundsun.ares.studio.jres.basicdata.ui.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.compiler.CompilationResult;
import com.hundsun.ares.studio.jres.compiler.CompileManager;
import com.hundsun.ares.studio.jres.compiler.CompileUtils;
import com.hundsun.ares.studio.jres.compiler.ICompilationResultExtension;
import com.hundsun.ares.studio.jres.compiler.IResourceCompilerFactory;

public class BasicDataGenCodeUtils {
	
	//基础数据安装模式编译器的扩展点types
	public static final String BASICDATA_SQL_INSTALL= "#basicdata.gensql.install#";
	//基础数据安装升级编译器的扩展点types
	public static final String BASICDATA_SQL_UPDATA= "#basicdata.gensql.update#";
	private static final String SEPARATOR = "-------------------------------------------------\n";
	private static final String NEWLINE = "\n";
	
	/**
	 * 获取基础数据全局模型
	 * @param resource 基础数据资源
	 * @param eObj 基础数据模型
	 * @return
	 */
	public static StringBuffer genBasicDataFullCode(IARESResource resource, EObject eObj) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(SEPARATOR);
		buffer.append("--安装模式");
		buffer.append(NEWLINE);
		buffer.append(SEPARATOR);
		
		// 上下文中需要输入IARESResource对象
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put(CompileUtils.ARES_RESOURCE, resource);
		
		appendTextCompilationResult(buffer, BASICDATA_SQL_INSTALL, eObj, context);
		
		return buffer;
	}
	
	/**
	 * 获取基础数据升级脚本
	 * @param resource 基础数据资源
	 * @param eObj 基础数据模型
	 * @return
	 */
	public static StringBuffer genBasicDataPatchCode(IARESResource resource, EObject eObj) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(NEWLINE);
		buffer.append(SEPARATOR);
		buffer.append("--升级模式");
		buffer.append(NEWLINE);
		buffer.append(SEPARATOR);
		
		// 上下文中需要输入IARESResource对象
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put(CompileUtils.ARES_RESOURCE, resource);
		
		appendTextCompilationResult(buffer, BASICDATA_SQL_UPDATA, eObj, context);
		return buffer;
	}
	
	private static void appendTextCompilationResult(StringBuffer buffer, String type, EObject eObj, Map<Object, Object> context) {
		IResourceCompilerFactory factory = CompileManager.getInstance().getFactoryByType(type);
		if (factory != null) {

			CompilationResult result = factory.create(eObj).compile(eObj, context);
			if (result instanceof ICompilationResultExtension) {
				buffer.append(((ICompilationResultExtension) result).getTextResult());
			} else {
				buffer.append("--无法显示编译结果，编译结果不是文本");
			}
		} else {
			buffer.append("--无法显示编译结果，没有对应的编译器支持");
		}
	}


}
