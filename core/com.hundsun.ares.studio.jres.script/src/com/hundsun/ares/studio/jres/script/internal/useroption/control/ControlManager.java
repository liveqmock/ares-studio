/**
 * 源程序名称：ControlManager.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.internal.useroption.control;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.registry.CommonMapRegistry;
import com.hundsun.ares.studio.jres.script.ScriptPlugin;

/**
 * 用户选项配置里的 控件类型管理
 * @author sundl
 *
 */
public class ControlManager extends CommonMapRegistry<ControlType>{
	
	private static final Logger logger = Logger.getLogger(ControlManager.class);
	
	private static ControlManager INSTANCE = null;
	
	public static ControlManager getInstance() {
//		if (INSTANCE == null) {
			INSTANCE = new ControlManager();
//		}
		return INSTANCE;
	}
	
	private  ControlManager () {
		init();
	}

	protected String getExtensionPointPluginId() {
		return ScriptPlugin.PLUGIN_ID;
	}
	
	public String getExtensionPointId() {
		return "userOptionControlTypeProvider";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		ControlType type = new ControlType(element);
		map.put(type.getId(), type);
	}
	
	public IUserOptionControlProvider getTypeProvider(String type) {
		Collection<ControlType> controlTypes = map.get(type);
		if (controlTypes.size() > 0) {
			ControlType controlType = controlTypes.iterator().next();
			return controlType.getControlProvider();
		}
		return null;
	}
	
}
