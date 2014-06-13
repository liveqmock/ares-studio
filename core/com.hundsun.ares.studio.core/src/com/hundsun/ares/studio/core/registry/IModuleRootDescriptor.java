/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import com.hundsun.ares.studio.core.IARESModuleRoot;

/**
 * 模块根注册信息描述对象
 * @author sundl
 */
public interface IModuleRootDescriptor extends ICommonDescriptor, IOrderable{

	public static final int MODULE_LEVEL_NO_CONSTRAINT = -1;
	
	/**
	 * 是否显示默认模块
	 * @return
	 */
	boolean useDefaultModule();
	
	/**
	 * 允许的模块层级，默认不限层级数
	 * @return
	 */
	int getMaxModuleLevel();
	
	/**
	 * 是否可以删除的
	 * @return
	 */
	boolean isDeletable();
	
	/**
	 * 可否重命名
	 * @return
	 */
	boolean isRenameable();
	
	IARESModuleRoot createRoot();

	public boolean useProperty();
	
}
