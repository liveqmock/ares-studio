/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.refactoring;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IProjectProperty;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;
import com.hundsun.ares.studio.core.util.ARESElementUtil;

/**
 * 工具类。
 * @author sundl
 */
public class RefactoringUtil {

	/**
	 * 判断是否可以删除
	 * @param element
	 * @return
	 */
	public static boolean canDelete(IARESElement element) {
		if (element instanceof IARESModuleRoot) {
			IARESModuleRoot root = (IARESModuleRoot) element;
			if (root.isArchive())
				return false;
			
			ModulesRootTypeRegistry reg = ModulesRootTypeRegistry.getInstance();
			IModuleRootDescriptor desc = (IModuleRootDescriptor) reg.getModuleRootDescriptor(root.getType());
			if (desc != null)
				return desc.isDeletable();
			
			return false;
		} else if (element instanceof IARESModule) {
			IARESModule module = (IARESModule)element;
			return !module.isDefaultModule();
		} else if (element instanceof IARESResource) {
			ARESResRegistry reg = ARESResRegistry.getInstance();
			IResDescriptor desc = reg.getResDescriptor((IARESResource) element);
			if (desc != null) {
				boolean deleteable = desc.isDeleteable();
				return deleteable && !element.isReadOnly();
			}
		} else if (element instanceof IProjectProperty) {
			return false;
		} else if (element instanceof IReferencedLibrary) {
			return false;
		}
		
		return !element.isReadOnly();
	}
	
	/**
	 * 单个元素是否可以复制。<br>
	 * <li>资源根据资源类型注册的时候的配置决定是否可以复制；<br>
	 * <li>普通模块可以复制，默认模块不可以复制。
	 * <li>模块根不可以复制
	 * @param element
	 * @return
	 */
	public static boolean canCopy(IARESElement element) {
		switch (element.getElementType()) {
		case IARESElement.ARES_RESOURCE:
			IARESResource aresRes = (IARESResource)element;
			String type = aresRes.getType();
			ARESResRegistry reg = ARESResRegistry.getInstance();
			IResDescriptor desc = reg.getResDescriptor(type);
			return desc == null ? false : desc.isCopyable();
		case IARESElement.COMMON_MODULE:
			IARESModule module = (IARESModule)element;
			return !module.isDefaultModule();
		case IARESElement.COMMON_MODULE_ROOT:
			return false;
		default:
			return false;
		}
	}
	
	public static boolean canPaste(IARESElement[] elements, IARESElement destination) {
		for (IARESElement element : elements) {
			if (!canPaste(element, destination)) 
				return false;
		}
		return true;
	}
	
	public static boolean canPaste(String[] files, IARESElement destination) {
		for (String file : files) {
			if (!canPaste(file, destination)) 
				return false;
		}
		return true;
	}
	
	/**
	 * 是否可以粘贴到指定的位置
	 * @param element
	 * @param destination
	 * @return
	 */
	public static boolean canPaste(IARESElement element, IARESElement destination) {
		switch (element.getElementType()) {
		// 资源 -- 只能粘贴到允许存放此种资源类型
		case IARESElement.ARES_RESOURCE:
			IARESResource aresResource = (IARESResource) element;
			if (destination instanceof IARESModule) {
				IARESModule module = (IARESModule) destination;
				IARESModuleRoot root = module.getRoot();
				return ModuleRootType2ResTypeMap.getInstance().isAllowed(root.getType(), aresResource.getType());
			} else {
				return false;
			}
		// 模块 -- 只能在同种类型的模块根下复制粘贴，并且父模块不能粘贴到下级(子)模块
		case IARESElement.COMMON_MODULE:
			IARESModule module = (IARESModule)element;
			if (destination instanceof IARESModule) {
				IARESModule destModule = (IARESModule) destination;
				boolean isParent = destination.getElementName().startsWith(module.getElementName() + ".");
				boolean isRootSameType = module.getRoot().getType().equals(destModule.getRoot().getType());
				IModuleRootDescriptor desc = getRootDescriptor(destModule.getRoot().getType());
				// 检查粘贴后是否会超出最大层级限制。
				int maxLevel = desc.getMaxModuleLevel();
				int moduleLevel = module.getElementName().split("\\.").length;
				int targetModuleLevel = destModule.getElementName().split("\\.").length;
				boolean moduleLevelOverflow = maxLevel == -1 ? false : targetModuleLevel + moduleLevel > maxLevel;
				return !isParent && isRootSameType && !destination.equals(module) && !moduleLevelOverflow;
			} else if (destination instanceof IARESModuleRoot) {
				IARESModuleRoot destRoot = (IARESModuleRoot) destination;
				return module.getRoot().getType().equals(destRoot.getType());
			}
		// 模块根 -- 暂时不支持
		case IARESElement.COMMON_MODULE_ROOT:
			return false;
		default:
			return false;
		}
	}
	
	/**
	 * 判断操作系统中的文件(路径)是否可以粘贴到指定的位置
	 * @param file
	 * @param destination
	 * @return
	 */
	public static boolean canPaste(String filePath, IARESElement destination) {
		File file = new File(filePath);
		
		// 如果是文件，则只可能是一个资源
		if (file.isFile()) {
			if (destination instanceof IARESModule) {
				return true;
			} else
				return false;
		} else {
		// 如果是文件夹，则认为是一个模块
			if (destination instanceof IARESModule) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	/**
	 * 多个元素是否可以同时复制。<br>
	 * 首先，每个单独的元素都必须可以复制
	 * 其次，所以元素的类型必须相同
	 * @param element
	 * @return
	 */
	public static boolean canCopy(IARESElement[] elements) {
		for (IARESElement element : elements) {
			if (!canCopy(element)) 
				return false;
		}
		
		if (ARESElementUtil.elementsIsOfType(elements, IARESElement.COMMON_MODULE)) {
			return true;
		}
		
		if (ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_RESOURCE)) {
			IARESResource[] resources = ARESElementUtil.toARESResource(elements);
			boolean isResSameType = !ARESElementUtil.getResourcesType(resources).equals(ARESElementUtil.UNKNOWN);
			boolean isSameParent = ARESElementUtil.getParent(resources) != null;
			return isResSameType && isSameParent;
		}
		
		return false;
	}

	
	/**
	 * 多个元素是否可以同时移动。<br>
	 * 首先，每个单独的元素都必须可以移动
	 * 其次，所以元素的类型必须相同，并且必须在同一个容器下。
	 * @param element
	 * @return
	 */
	public static boolean canMove(IARESElement[] elements) {
		if (!ARESElementUtil.hasSameParent(elements)) {
			return false;
		}
		
		for (IARESElement element : elements) {
			if (!canMove(element)) 
				return false;
		}
		
		if (ARESElementUtil.elementsIsOfType(elements, IARESElement.COMMON_MODULE) ||
				ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_RESOURCE)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean canMoveTo(IARESElement element, IARESElement destination) {
		if (!canMove(element))
			return false;
		
		if (element.equals(destination)) {
			return false;
		} 
		
		IARESModuleRoot sourceRoot = null;
		IARESModuleRoot destRoot = null;
		
		boolean elementIsResource = false;
		boolean elementIsModule = false;
		
		if (element instanceof IARESResource) {
			IARESResource aresRes = (IARESResource) element;
			sourceRoot = aresRes.getRoot();
			elementIsResource = true;
		} else if (element instanceof IARESModule) {
			IARESModule module = (IARESModule) element;
			sourceRoot = module.getRoot();
			elementIsModule = true;
		}
		
		boolean targetIsRoot = false;
		boolean targetIsModule = false;
		
		if (destination instanceof IARESModule) {
			IARESModule module = (IARESModule) destination;
			destRoot = module.getRoot();
			targetIsModule = true;
		} else if (destination instanceof IARESModuleRoot) {
			destRoot = (IARESModuleRoot) destination;
			targetIsRoot = true;
		}
		
		if (sourceRoot == null || destRoot == null) {
			return false;
		}
		
		// 只能在同类型的模块根之间移动
		if (!sourceRoot.getType().equals(destRoot.getType()))
			return false;
		
		if (elementIsModule && targetIsModule) {
			IARESModule module = (IARESModule) element;
			IARESModule targetModule  = (IARESModule) destination;
			
			// 默认模块不能移动；不能移动到默认模块
			if (module.isDefaultModule() || targetModule.isDefaultModule()) 
				return false;
			
			IARESModule sourceParentModule = module.getParentModule();
			// 不能移动到自己的父模块
			if (sourceParentModule != null) {
				return !sourceParentModule.equals(targetModule);
			} /*else {
				return targetModule.getParentModule() != null;
			}*/
			
			ModulesRootTypeRegistry reg = ModulesRootTypeRegistry.getInstance();
			IModuleRootDescriptor desc = reg.getModuleRootDescriptor(targetModule.getRoot().getType());
			int max = desc.getMaxModuleLevel();
			if (max != -1) {
				String[] segments = targetModule.getElementName().split("\\.");
				if (segments.length >= max - 1) {
					return false;
				}	
			}
		} else if (elementIsModule && targetIsRoot) {
			IARESModule module = (IARESModule) element;
			IARESModule sourceParentModule = module.getParentModule();
			if (sourceParentModule == null) {
				return !destination.equals(module.getRoot());
			} else {
				return false;
			}
		} else if (elementIsResource && targetIsModule) {
			IARESModule sourceModule = (IARESModule) element.getParent();
			boolean targetIsParent = sourceModule.equals(destination);
			if (targetIsParent)
				return false;
		} else if (elementIsResource && targetIsRoot) {
			return false;
		}
		
		return true;
	}
	
	public static boolean canMoveTo(IARESElement[] elements, IARESElement destination) {
		for (IARESElement element : elements) {
			if (!canMoveTo(element, destination)) 
				return false;
		}
		return true;
	}
	
	/**
	 * 是否可以移动
	 * @param element
	 * @return
	 */
	public static boolean canMove(IARESElement element) {
		switch (element.getElementType()) {
		case IARESElement.ARES_RESOURCE:
			// 引用包里的不能移动...
			IARESResource res = (IARESResource) element;
			if (res.getRoot().isArchive())
				return false;
			
			IResDescriptor desc = getARESResourceDescriptor(res);
			if (desc != null)
				return desc.isMoveable();
			
			break;
		case IARESElement.COMMON_MODULE:
			IARESModule module = (IARESModule)element;
			if (module.getRoot().isArchive())
				return false;
			
			return !module.isDefaultModule();
		default:
			return false;
		}
		
		return false;
	}
	
	/**
	 * 全部都可以删除的话，才可以删除。
	 * @param elements
	 * @return
	 */
	public static boolean canDelete(IARESElement[] elements) {
		for (IARESElement element : elements) {
			if (!canDelete(element)) {
				return false;
			}
		}
		return true;
	}
	
	public static IARESElement[] setMinus(IARESElement[] setToRemoveFrom, IARESElement[] elementsToRemove) {
		Set<IARESElement> setMinus= new HashSet<IARESElement>(setToRemoveFrom.length - setToRemoveFrom.length);
		setMinus.addAll(Arrays.asList(setToRemoveFrom));
		setMinus.removeAll(Arrays.asList(elementsToRemove));
		return (IARESElement[]) setMinus.toArray(new IARESElement[setMinus.size()]);
	}
	
	private static IResDescriptor getARESResourceDescriptor(IARESResource aresRes) {
		String type = aresRes.getType();
		ARESResRegistry reg = ARESResRegistry.getInstance();
		IResDescriptor desc = reg.getResDescriptor(type);
		return desc;
	}
	
	private static IModuleRootDescriptor getRootDescriptor(String type) {
		ModulesRootTypeRegistry reg = ModulesRootTypeRegistry.getInstance();
		return reg.getModuleRootDescriptor(type);
	}
	
}
