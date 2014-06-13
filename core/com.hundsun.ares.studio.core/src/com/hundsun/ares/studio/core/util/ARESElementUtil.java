/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.ICommonModel;

/**
 * 
 * @author sundl
 */
public class ARESElementUtil {
	
	private static Logger logger = Logger.getLogger(ARESElementUtil.class);

	public static final String UNKNOWN = "ares.unknown";
	
	public static final IARESElement[] NO_ELEMENT = new IARESElement[0];
	
	/**
	 * 转换。
	 * 
	 * @param elements
	 * @return
	 */
	public static IARESElement[] toARESElement(Object[] elements) {
		List<IARESElement> result = new ArrayList<IARESElement>();
		for (Object obj : elements) {
			if (obj instanceof IResource) {
				IARESElement aresEelement = ARESCore.create((IResource) obj);
				if (aresEelement != null) {
					result.add(aresEelement);
				}
			} else if (obj instanceof IARESElement) {
				result.add((IARESElement) obj);
			}
		}
		return result.toArray(new IARESElement[result.size()]);
	}

	/**
	 * 转换。
	 * 
	 * @param element
	 * @return
	 */
	public static IARESElement toARESElement(Object obj) {
	
		if (obj instanceof IResource) {
			IARESElement aresEelement = ARESCore.create((IResource) obj);
			if (aresEelement != null) {
				return aresEelement;
			}
		} else if (obj instanceof IARESElement) {
			return (IARESElement) obj;
		}
		return null;
	}

	/**
	 * 转换到IResource数组
	 * @param elements 待转换的数组
	 * @return 转换后的数组
	 */
	public static IResource[] toResource(Object[] elements) {
		List<IResource> results = new ArrayList<IResource>();
		for (Object obj : elements) {
			if (obj instanceof IResource) {
				results.add((IResource) obj);
			} else if (obj instanceof IARESElement) {
				IARESElement aresElement = (IARESElement) obj;
				IResource res = aresElement.getResource();
				if (res != null) {
					results.add(res);
				}
			}
		}
		return results.toArray(new IResource[results.size()]);
	}

	/**
	 * 转换到指定扩展名IResource数组
	 * 
	 * @param elements 待转换的数组
	 * @param extension 待匹配的扩展名
	 * @return 转换后的数组
	 */
	public static IResource[] toResource(Object[] elements, String extension) {
		List<IResource> results = new ArrayList<IResource>();
		for (Object obj : elements) {
			if (obj instanceof IResource) {
				String srcExtension = ((IResource)obj).getFileExtension();
				if (srcExtension.equals(extension)) {
					results.add((IResource)obj);
				}
			}
		}
		return results.toArray(new IResource[results.size()]);
	}

	/**
	 * 判断给定数组的元素是否都是给定的类型。
	 * 
	 * @param elements
	 *            元素数组
	 * @param type
	 *            类型代码
	 * @return 如果全部是指定的类型，则返回<code>true</code>
	 */
	public static boolean elementsIsOfType(Object[] elements, int type) {
		if (elements.length == 0) {
			return false;
		}
	
		for (Object o : elements) {
			if (!ARESElementUtil.elementIsOfType(o, type)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断给定的元素是否给定的类型。
	 * 
	 * @param element
	 *            要判断的元素
	 * @param type
	 *            类型代码
	 * @return 如果是指定的类型，则返回true，如果是不支持的类型，则返回false。
	 * @see IARESElement
	 */
	public static boolean elementIsOfType(Object element, int type) {
		switch (type) {
		case IARESElement.COMMON_MODULE:
			return element instanceof IARESModule;
		case IARESElement.ARES_RESOURCE:
			return element instanceof IARESResource;
		case IARESElement.COMMON_MODULE_ROOT:
			return element instanceof IARESModuleRoot;
		case IARESElement.ARES_PROJECT:
			return element instanceof IARESProject;
		}
		return false;
	}

	/**
	 * 转成IARESResource数组；调用前需要自己判断是否全是ARESResource,否则会出错。
	 * @param elements
	 * @return
	 */
	public static IARESResource[] toARESResource(IARESElement[] elements) {
		IARESResource[] resources = new IARESResource[elements.length];
		System.arraycopy(elements, 0, resources, 0, elements.length);
		return resources;
	}

	public static IARESModule[] toARESModule(IARESElement[] elements) {
		IARESModule[] modules = new IARESModule[elements.length];
		System.arraycopy(elements, 0, modules, 0, elements.length);
		return modules;
	}

	/**
	 * 是否在同一个模块下，暂时没有特殊处理默认模块，所以默认模块和第一级模块组成的数组会返回true
	 * @param modules
	 * @return
	 */
	public static boolean modulesHasSameParent(IARESModule[] modules) {
		if (modules == null || modules.length ==0) 
			return false;
		
		IARESModule firstParentModule = modules[0].getParentModule();
		for (int i = 1; i < modules.length; i++) {
			IARESModule module = modules[i];
			if (firstParentModule == null) {
				if (module.getParentModule() == null) {
					continue;
				} else {
					return false;
				}
			} else {
				if (firstParentModule.equals(module.getParentModule())) {
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static final IARESElement getModulesParent(IARESModule[] modules) {
		IARESModule firstParent = modules[0].getParentModule();
		for (int i = 1; i < modules.length; i++) {
			IARESModule module = modules[i];
			if (firstParent != null && firstParent.equals(module.getParentModule())) {
				continue;
			} else if (firstParent == null && module.getParentModule() == null) {
				continue;
			} else {
				return null;
			}
		}
		
		if (firstParent == null) {
			return modules[0].getRoot();
		}
		return firstParent;
	}

	/**
	 * 判断是否所有资源是指定的类型
	 * @param resources
	 * @param type
	 * @return
	 */
	public static boolean resourcesOfType(IARESResource[] resources, String type) {
		String realType = getResourcesType(resources);
		return realType.equals(type);
	}

	public static String getResourcesType(IARESResource[] resources) {
		int count = resources.length;
		if (count == 0)
			return UNKNOWN;
		
		String firstType = resources[0].getType();
		for (int i = 1; i < count; i++) {
			if (firstType.equals(resources[i].getType())) {
				continue;
			} else {
				return UNKNOWN;
			}
		}
		return firstType;
	}
	
	/**
	 * 返回给定的AresResource集合中包含的所有资源类型
	 * @param resources AresResoruce集合
	 * @return 所有包含的资源类型
	 */
	public static String[] getResourcesTypes(IARESResource[] resources) {
		Set<String> types = new HashSet<String>();
		for (IARESResource res : resources) {
			types.add(res.getType());
		}
		return types.toArray(new String[0]);
	}

	/**
	 * 判断给定的元素集合是否在同一个容器下面
	 * @param elements
	 * @return
	 */
	public static boolean hasSameParent(IARESElement[] elements) {
		if (elements == null || elements.length == 0)
			return false;
		if (elements.length == 1)
			return true;
		
		IARESElement parent = elements[0].getParent();
		if (parent == null)
			return false;
		
		for (int i = 1; i < elements.length; i++) {
			if (!elements[i].getParent().equals(parent))
				return false;
		}
		
		return true;
	}

	public static IARESElement getParent(IARESElement[] elements) {
		if (hasSameParent(elements)) {
			return elements[0].getParent();
		}
		return null;
	}

	/**
	 * 一个对象通过各种方式试图获取对应的ICommonMode对象，通过ICommonModel可以用统一的接口获取资源的中文名。
	 * 1. 如果对象本身实现了ICommonModel接口，直接返回
	 * 2. 如果对象实现了IAdaptable接口，通过这个接口适配
	 * 3. 通过AdapterManager（扩展点）机制获取
	 * @param model
	 * @return
	 */
	public static ICommonModel getCommonModel(Object model) {
		ICommonModel commonModel = null;
		if (model == null) {
			commonModel = null;
		} else if (model instanceof ICommonModel) {
			commonModel = (ICommonModel)model;
		} else if (model instanceof IAdaptable) {
			IAdaptable adapter = (IAdaptable)model;
			commonModel = (ICommonModel) adapter.getAdapter(ICommonModel.class);
		} else {
			IAdapterManager manager = Platform.getAdapterManager();
			commonModel = (ICommonModel)manager.getAdapter(model, ICommonModel.class);
		}
		return commonModel;
	}
	
	/**
	 * 返回第一级模块，比如aaa.bb.c, 其第一级模块就是aaa这个模块
	 * @param resource
	 * @return
	 */
	public static IARESModule getTopModule(IARESResource resource) {
		IARESModuleRoot root = resource.getRoot();
		IARESModule module = resource.getModule();
		if (isTopModule(module))
			return module;
//		// 模块在内存模型中都是平级的，把所有模块取出，根据名字判断
//		try {
//			for (IARESModule m : root.getModules()) {
//				if (module.getElementName().startsWith(m.getElementName() + "."))
//					return m;
//			}
//		} catch (ARESModelException e) {
//			e.printStackTrace();
//		}
//		return null;
		
		String moduleName = StringUtils.substringBefore(module.getElementName(), ".");
		return root.getModule(moduleName);
	}
	
	public static IARESModule getTopModule(IARESModule module) {
		IARESModule parent = module;
		while(!isTopModule(parent)) {
			parent = module.getParentModule();
		}
		return parent;
	}
	
	/**
	 * 判断指定的模块是否第一级模块.
	 * @param module
	 * @return
	 */
	public static boolean isTopModule(IARESModule module) {
		return module.getElementName().indexOf('.') == -1 ;
	}

	/** JRES20工程（老工程）下的数据库资源模块根类型 */
	public static final String OLD_DB_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.jres.database";

	/** JRES20工程（老工程）下的元数据资源模块根类型 */
	public static final String OLD_META_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.publicres.rootpublicres";

	/** 新JRES工程中的数据库模块根
	 *  这里采用约定的方式，具体的插件定义模块根的时候，必须和这里的字符串值相同。
	 */
	public static final String DB_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.jres.moduleroot.database";
	public static final String BUS_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.jres.moduleroot.business";
	public static final String MD_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.jres.moduleroot.metadata";
	
	
	/**
	 * UFT对象模块根，UFT对象的修改记录要以模块根为增加基线
	 */
	public static final String UFT_STRUCTURE_ROOT_TYPE = "com.hundsun.ares.studio.uft.moduleroot.structure";
	
	/**
	 * 判断给定的模块根类型是否UFT对象模块根
	 * @param type
	 * @return
	 */
	public static boolean isUFTStructureRoot(String type){
		return StringUtils.equals(UFT_STRUCTURE_ROOT_TYPE, type);
	}

	/**
	 * 判断给定的模块根类型是否数据库模块根； 这个方法是为了兼容老工程中使用的老的数据库模块根类型
	 * @param type 模块根类型ID
	 * @return 
	 */
	public static boolean isDatabaseRoot(String type) {
		return StringUtils.equals(DB_MODULE_ROOT_TYPE, type)
				|| StringUtils.equals(OLD_DB_MODULE_ROOT_TYPE, type);
	}

	/**
	 * 判断是否元数据模块根
	 * @param type
	 * @return
	 */
	public static boolean isMetadataRoot(String type) {
		return StringUtils.equals(MD_MODULE_ROOT_TYPE, type) 
				|| StringUtils.equals(OLD_META_MODULE_ROOT_TYPE, type);
	}

	public static IARESBundle getARESBundle(IARESElement aresElement) {
		if (aresElement instanceof IARESResource) {
			return ((IARESResource) aresElement).getBundle();
		} else {
			return aresElement.getARESProject();
		}
	}

	/**
	 * 查询所有的引用包
	 * 
	 * @param project
	 * @return
	 */
	public static List<IReferencedLibrary> getReferenceLib(IARESProject project){
		Set<IReferencedLibrary> refLibs = new HashSet<IReferencedLibrary>();
		try {
			//增加本工程的引用包
			refLibs.addAll(addLib(project));
			//增加引用工程的引用包
			List<IARESProject> projects = getRequiredProjects(project);
			for (IARESProject pro : projects) {
				refLibs.addAll(addLib(pro));
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return new ArrayList<IReferencedLibrary>(refLibs);
	}

	/**
	 * 查询所有的引用工程
	 * 
	 * @param project 当前工程对象
	 * @return
	 */
	public static List<IARESProject> getRequiredProjects(IARESProject project){
		List<IARESProject> projects = new ArrayList<IARESProject>();
		if (project == null) {
			return projects;
		}
		projects.add(project);
		checkCYC(project, new ArrayList<IARESProject>());
		getReqProjects(project, projects);
		return projects;
	}

	/**
	 * 判断工程是否循环依赖
	 * 
	 * @param project 当前工程
	 * @param mainProject 重复工程数组
	 * @return
	 */
	public static boolean checkCYC (IARESProject project,List<IARESProject> mainProject){
		mainProject.add(project);
		IARESProject[] pros = project.getRequiredProjects();
		for (IARESProject pro : pros) {
			if (mainProject.contains(pro)) {
				logger.error("工程：["+project.getElementName()+"],循环引用：["+pro.getElementName()+"]");
				ConsoleHelper.getLogger().error("工程：["+project.getElementName()+"],循环引用：["+pro.getElementName()+"]");
				return false;
			}
			if (pro.getRequiredProjects().length > 0) {
				if (!checkCYC(pro, mainProject)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static List<IReferencedLibrary> addLib(IARESProject project) throws ARESModelException{
		List<IReferencedLibrary> refLibs = new ArrayList<IReferencedLibrary>();
		if (project == null) {
			return refLibs;
		}
		IReferencedLibrary[] libs = project.getReferencedLibs();
		for (IReferencedLibrary lib : libs) {
			refLibs.add(lib);
		}
		return refLibs;
	}
	
	/**
	 * 递归查找所有的引用工程 
	 */
	public static void getReqProjects(IARESProject project,List<IARESProject> projects){
		IARESProject[] pros = project.getRequiredProjects();
		for (IARESProject pro : pros) {
			if (!projects.contains(pro)) {
				projects.add(pro);
			}else {
				continue;
			}
			if (pro.getRequiredProjects().length > 0) {
				getReqProjects(pro, projects);
			}
		}
	}

	/**
	 * 获取当前ARESProject的引用工程和引用包
	 * 
	 * @return
	 */
	public static IARESBundle[] getRefARESProjects(IARESProject project){
		List<IARESBundle> elements = new ArrayList<IARESBundle>();
		List<IARESProject>  projects = getRequiredProjects(project);
		List<IReferencedLibrary> reflibs = getReferenceLib(project);
		elements.addAll(projects);
		elements.addAll(reflibs);
		return elements.toArray(new IARESBundle[elements.size()]);
	}

	/**
	 * 查询指定工程下，包含引用包及其引用工程的指定资源
	 * 
	 * @param project
	 * @param resourceTypes
	 * @return
	 */
	public static IARESResource[] getAllResourceFromRefInType(IARESProject project ,String[] resourceTypes ){
	
		List<IARESResource> totalRes = new ArrayList<IARESResource>();
		List<IReferencedLibrary> reflibs = getReferenceLib(project);
		List<IARESProject>  projects = getRequiredProjects(project);
		//解析引用包
		for (IReferencedLibrary reflib : reflibs) {
			try {
				totalRes.addAll(Arrays.asList(reflib.getResources(resourceTypes)));
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		//解析引用工程
		for(IARESProject pro : projects){
			try {
				totalRes.addAll(Arrays.asList(pro.getResources(resourceTypes)));
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return totalRes.toArray(new IARESResource[totalRes.size()]);
	
	}

	/**
	 * 获取指定id的模块根文件夹
	 * @param project
	 * @param id
	 * @return
	 */
	public static IFolder getModuleRootFolder(IARESProject project, String id) {
		if (project == null)
			return null;
		try {
			for (IARESModuleRoot moduleRoot : project.getModuleRoots()) {
				if (StringUtils.equals(moduleRoot.getType(), id)) {
					return (IFolder) moduleRoot.getResource();
				}
			}
		} catch (ARESModelException e) {
		}
		return null;
	}
	
	/**
	 * 在指定的项目的依赖项目，引用包中查找资源；
	 * 可能返回null
	 * @param project
	 * @param fullName
	 * @param resType
	 * @return
	 */
	public static IARESResource findResource(IARESProject project, String fullName, String resType) {
		IARESBundle[] bundles = getRefARESProjects(project);
		for (IARESBundle bundle : bundles) {
			try {
				IARESResource res = bundle.findResource(fullName, resType);
				if (res != null)
					return res;
			} catch (ARESModelException e) {
				logger.error("", e);
			}
		}
		logger.error(String.format("找不到资源%s，类型%s", fullName, resType));
		return null;
	}

	/**
	 * 获取资源的分组信息。
	 * @param resource
	 * @return 可能返回null
	 */
	public static String getGroup(IARESResource resource) {
		try {
			BasicResourceInfo info = resource.getInfo(BasicResourceInfo.class);
			if (info != null) {
				return info.getGroup();
			}
		} catch (ARESModelException e) {
			logger.error(e);
		}
		return null;
	}
	
	public static final String getModuleCName(IARESModule module) {
		IARESResource property = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
		if (property != null && property.exists()) {
			String cname = ResourcesUtil.getCName(property);
			return cname;
		}
		return null;
	}
	
	public static final String getModuleFullCName(IARESModule module, String seperator) {
		IARESModule parentModule = module;
		String fullname = getModuleCName(module);
		while (!isTopModule(parentModule)) {
			parentModule = module.getParentModule();
			fullname = getModuleCName(parentModule) + seperator + fullname;
		}
		return fullname;
	}
	
}
