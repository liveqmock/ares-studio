/**
 * 
 */
package com.hundsun.ares.studio.ui.editor;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * @author gongyf
 *
 */
public class ActionBarContributorDecoratorManager {
	
	private final static String EP_NAME = "ContributorDecorator";
	private final static String EP_ATTRIBUTE_ID = "id";
	private final static String EP_ATTRIBUTE_EditorId = "editorId";
	private final static String EP_ATTRIBUTE_Class = "class";
	
	private Logger logger = Logger.getLogger(getClass());
	
	private ActionBarContributorDecoratorManager() {
		loadDecorators();
	}
	
	private static ActionBarContributorDecoratorManager instance;
	
	public static ActionBarContributorDecoratorManager getInstance() {
		if (instance == null) {
			instance = new ActionBarContributorDecoratorManager();
		}
		return instance;
	}
	
	/**
	 * 以编辑器id为key
	 */
	private Multimap<String, IActionBarContributorDecorator> decoratorMap = HashMultimap.create();
	
	private void loadDecorators() {
		logger.info("开始加载编辑器装饰扩展点");
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(ARESEditorPlugin.PLUGIN_ID , EP_NAME);
		
		for (IConfigurationElement element : elements) {
			try {
//				String id = element.getAttribute(EP_ATTRIBUTE_ID);
				String editorId = element.getAttribute(EP_ATTRIBUTE_EditorId);
				IActionBarContributorDecorator decorator = (IActionBarContributorDecorator) element.createExecutableExtension(EP_ATTRIBUTE_Class);
				
				decoratorMap.put(editorId, decorator);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		logger.info("结束加载编辑器装饰扩展点");
	}
	
	public IActionBarContributorDecorator[] getDecorators(String editorId) {
		return decoratorMap.get(editorId).toArray(new IActionBarContributorDecorator[0]);
	}
	
}
