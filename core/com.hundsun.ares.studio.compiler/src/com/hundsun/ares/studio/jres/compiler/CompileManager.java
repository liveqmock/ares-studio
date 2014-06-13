/**
 * 源程序名称：CompileManager.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.compiler;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.ArrayListMultimap;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author gongyf
 *
 */
public class CompileManager {
	
	static private final String PRIORITY[] = {
			"Highest","Higher","High","Normal","Low","Lower","Lowest",
	};
	
//	private static class TEClass {
//		public String uri;
//		public String name;
//	}
	
	private static class CompilerFactoryItem implements Comparable<CompilerFactoryItem> {
		public String id;
		public String types;
		public String name;
		public String priority;
		public IResourceCompilerFactory adapter;
		
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(CompilerFactoryItem o) {
			return ArrayUtils.indexOf(PRIORITY, priority) - ArrayUtils.indexOf(PRIORITY, o.priority);
		}
	}
	
	private static Logger logger = Logger.getLogger(CompileManager.class);
	
	private CompileManager () {
		
		
		loadFactories();
	}
	
	private static CompileManager instance;
	
	/**
	 * @return the instance
	 */
	public static CompileManager getInstance() {
		if (instance == null) {
			instance = new CompileManager();
		}
		return instance;
	}
	
	
	private ArrayListMultimap<String, CompilerFactoryItem> factories = ArrayListMultimap.create();
	
	private void loadFactories() {
		/*
		 * TODO#资源编译#龚叶峰#一般#吕高#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #读取扩展点信息
		 *
		 * 读取扩展点上已经注册的资源编译工厂，并实例化加入到支持的资源类型map映射中去
		 */
		logger.info("加载JRES编译器工厂扩展点。");
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(CompilerPlugin.PLUGIN_ID , ICompilerFactoriyExtentionPoint.EP_Name);
		for (IConfigurationElement element : elements) {
			try {
				CompilerFactoryItem tmp = new CompilerFactoryItem();
				tmp.id = element.getAttribute(ICompilerFactoriyExtentionPoint.EP_Attribute_ID);
				tmp.types = element.getAttribute(ICompilerFactoriyExtentionPoint.EP_Attribute_Types);
				tmp.name = element.getAttribute(ICompilerFactoriyExtentionPoint.EP_Attribute_Name);
				tmp.priority = element.getAttribute(ICompilerFactoriyExtentionPoint.EP_Attribute_Priority);
				tmp.adapter = (IResourceCompilerFactory)element.createExecutableExtension(ICompilerFactoriyExtentionPoint.EP_Attribute_Class);
				
				// 查找节点下挂载的Eclass类型
				StringBuffer sb = new StringBuffer(StringUtils.defaultString(tmp.types) );
				for (IConfigurationElement childElement : element.getChildren(ICompilerFactoriyExtentionPoint.EP_Element_EClass)) {
					String uri = childElement.getAttribute(ICompilerFactoriyExtentionPoint.EP_Attribute_EClass_Uri);
					String name = childElement.getAttribute(ICompilerFactoriyExtentionPoint.EP_Attribute_EClass_Name);
					
					sb.append(",");
					sb.append(CompileUtils.getEClassCompileType(uri, name));
				}
				
				tmp.types = sb.toString();
				
				for(String item:StringUtils.split(tmp.types,",")){
					if (StringUtils.isNotBlank(item)) {
						factories.put(item, tmp);
					}
				}
				
				logger.info(String.format("JRES编译器工厂扩展点%s:%s", tmp.id,tmp.types));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		/*
		 * TODO#资源编译#龚叶峰#一般#吕高#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #对资源编译工厂列表进行排序
		 *
		 * 使用记录的资源编译工厂的优先级，对map中每个资源类型对应的资源编译工厂列表进行排序，优先级最高的在前面
		 * 优先级是 Highest > High > Normal > Low > Lowest
		 */
		
	}
	
	public IResourceCompilerFactory getFactoryByType(String resourceType) {
		List<CompilerFactoryItem> result = factories.get(resourceType);
		for (CompilerFactoryItem item : result) {
			return item.adapter;
		}
		return null;
	}
	
	public IResourceCompilerFactory getFactory(Object resource) {
		String type = getCompileType(resource);
		if(null == type){
			return null;
		}
		/*
		 * TODO#资源编译#龚叶峰#一般#吕高#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #查找对应资源的资源编译工厂
		 *
		 * 根据优先级依次读取资源编译工厂列表，并坚持该工厂是否支持当前资源所在项目，如果支持则返回，即返回能使用的资源编译工厂中优先级最高的
		 */

		//暂时取第一个
		List<CompilerFactoryItem> result = factories.get(type);
		if (result == null || result.isEmpty()) {
			return null;
		}
		
		Collections.sort(result);
		
		for (CompilerFactoryItem item : result) {
			//if (item.adapter.isSupport(project)) {
				return item.adapter;
			//}
		}
		
		return null;
	}
	
	
	/**
	 * 根据不同的资源返回不同的编译类型
	 * @param resource
	 * @return
	 */
	private String getCompileType(Object resource){
		if(resource instanceof IARESResource){
			return ((IARESResource)resource).getType();
		}
		
		if(resource instanceof IARESModule){
			return CompilerConstants.Compile_Module;
		}

		if (resource instanceof EObject) {
			return CompileUtils.getEObjectCompileType((EObject) resource);
		}
		
		return null;
	}
}


