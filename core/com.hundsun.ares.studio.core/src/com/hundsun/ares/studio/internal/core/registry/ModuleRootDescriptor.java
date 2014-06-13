/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.internal.core.ARESModuleRoot;

/**
 * 模块根描述信息
 * @author sundl
 */
public class ModuleRootDescriptor extends OrderableDescriptor implements IModuleRootDescriptor {

	// 默认使用默认模块
	private boolean useDefaultModule;
	// 默认不限制最大模块层级数
	private int maxModuleLevel;
	// 可否删除
	private boolean deletable;
	// 
	private boolean renameable;
	// 是否支持属性文件
	public boolean useProperty;
	
	public ModuleRootDescriptor(IConfigurationElement e) {
		super(e);		
	}
	
	protected void loadFromExtension() {
		super.loadFromExtension();
		String useDefaultModule = configElement.getAttribute(ICommonExtensionConstants.USE_DEFAULT_MODULE);
		if (!StringUtils.isEmpty(useDefaultModule)) {
			this.useDefaultModule = Boolean.parseBoolean(useDefaultModule);
		} else {
			this.useDefaultModule = true;
		}
		String maxLevel = configElement.getAttribute(ICommonExtensionConstants.MAX_MODULE_LEVEL);
		if (!StringUtils.isEmpty(maxLevel)) {
			this.maxModuleLevel = Integer.parseInt(maxLevel);
		} else {
			this.maxModuleLevel = MODULE_LEVEL_NO_CONSTRAINT;
		}
		
		String deletable = configElement.getAttribute(ICommonExtensionConstants.DELTEABLE);
		if (!StringUtils.isEmpty(deletable)) {
			this.deletable = Boolean.parseBoolean(deletable);
		} else {
			this.deletable = false;
		}
		
		String rename = configElement.getAttribute(ICommonExtensionConstants.RENAMEABLE);
		if (!StringUtils.isEmpty(rename)) {
			this.renameable = Boolean.parseBoolean(rename);
		} else {
			this.renameable = false;
		}
		
		String usePro = configElement.getAttribute(ICommonExtensionConstants.USE_PROPERTY);
		if (!StringUtils.isEmpty(usePro)) {
			this.useProperty = Boolean.parseBoolean(usePro);
		} else {
			this.useProperty = false;
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.IModuleRootDescriptor#createRoot()
	 */
	public IARESModuleRoot createRoot() {
		try {
			return (IARESModuleRoot)configElement.createExecutableExtension(ICommonExtensionConstants.CLASS);
		} catch (CoreException e) {
			//e.printStackTrace();
			// cann't create instance, use default
		}
		return new ARESModuleRoot();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IModuleRootDescriptor#getMaxModuleLevel()
	 */
	public int getMaxModuleLevel() {
		return maxModuleLevel;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IModuleRootDescriptor#useDefaultModule()
	 */
	public boolean useDefaultModule() {
		return useDefaultModule;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.IModuleRootDescriptor#isDeletable()
	 */
	public boolean isDeletable() {
		return deletable;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.IModuleRootDescriptor#isRenameable()
	 */
	public boolean isRenameable() {
		return renameable;
	}

	public boolean useProperty() {
		return useProperty;
	}

	public void setUseProperty(boolean useProperty) {
		this.useProperty = useProperty;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */// 2012-03-22 sundl 自动生成toString方便调试
	@Override
	public String toString() {
		return "ModuleRootDescriptor [useDefaultModule=" + useDefaultModule + ", maxModuleLevel=" + maxModuleLevel + ", deletable=" + deletable + ", renameable=" + renameable + ", useProperty=" + useProperty + ", order=" + order + "]";
	}

}
