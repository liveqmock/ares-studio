/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

/**
 * 扩展点使用的常量
 * @author sundl
 */
public interface ICommonExtensionConstants {

	// public
	String CLASS = "class";
	String CONVERTER = "converter";
	String INFO_CLASS = "info-class";
	String ICON = "icon";
	String ID = "id";
	String NAME = "name";
	String PATH = "path";
	
	String DELTEABLE = "deletable";
	String RENAMEABLE = "renameable";
	String COPYABLE = "copyable";
	String MOVEABLE = "moveable";
	
	// order
	String ORDER = "order";
	
	// module-root
	String USE_DEFAULT_MODULE = "useDefaultModule";
	String MAX_MODULE_LEVEL = "maxModuleLevel";
	String USE_PROPERTY = "useProperty";
	
	// root->res type map
	String ROOT_TYPE = "rootType";
	String RES_TYPES = "resTypes";
	
	// ares project
	// String ID						/// use public
	String NATURES = "natures";
	
	// default-module
	String PROJECT_TYPE = "projectType";
	// String ROOT_TYPE = "rootType";	/// use public
	// String ID = "id";				/// use public
	
	String NATURE = "nature";
	
	// ares resource
	String CATEGORY = "category";
	String RES_CATEGORY = "resCategory";
	String ARES_RESOURCE = "aresResource";
	String FILENAME = "filename";
	
	
	/** 扩展点的ID */
	String EP_ID_MODULEROOT = "moduleRoot";
	String EP_ID_RESOUCE = "aresResource";
	String EP_ID_MODULEROOT_RESTYPE = "rootToResTypeMaps";
	String EP_ID_ARESPROJECT = "aresproject";
	String EP_ID_DEFAULT_MODULE_ROOTS = "defaultModuleRoots";
	String EP_ID_ROOT_CONSTRUCTOR = "rootConstructor";
	String EP_ID_RESPATH_PROVIDER = "respathProvider";
}
