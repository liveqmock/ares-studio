/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.search;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.hundsun.ares.studio.ui.ARESUI;

/**
 * 
 * @author liaogc
 */
public class ARESSearcherManager {

	private static Logger logger = Logger.getLogger(ARESSearcherManager.class.getName());
	private static final String EXTENSION_POINT_ID = "areasearch";
	private Map<String ,ARESSearcherElement> searcherElements = new HashMap<String,ARESSearcherElement>();
	

	private static ARESSearcherManager instance;
	

	
	public static ARESSearcherManager getInstance() {
		if (instance == null) {
			instance = new ARESSearcherManager();
		}
		return instance;
	}
	
	private ARESSearcherManager() {
		init();
	}
	
	private void init() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(ARESUI.PLUGIN_ID, EXTENSION_POINT_ID);
		for (IConfigurationElement element : elements) {
			
		 if (element.getName().equals("searcher")) {
				ARESSearcherElement searchElement = new ARESSearcherElement();
				
				String id = StringUtils.defaultIfBlank(element.getAttribute("id"), StringUtils.EMPTY);
				searchElement.setSearchId(id);
				String[] items = StringUtils.split(StringUtils.defaultIfBlank(element.getAttribute("items"), StringUtils.EMPTY),",");
				for(String item :items){
					searchElement.getSearchItems().add(item);
				}
				String[] resTypes = StringUtils.split(StringUtils.defaultIfBlank(element.getAttribute("resTypes"), StringUtils.EMPTY),",");
				for(String resType :resTypes){
					searchElement.getSearcherResTypes().add(resType);
				}
				String order = StringUtils.defaultIfBlank(element.getAttribute("order"), "-1");
				searchElement.setOrder(order);
				try {
					IARESSarcher searcher = (IARESSarcher)element.createExecutableExtension("class");
					searchElement.setSearcher(searcher);
				} catch (CoreException e) {
					logger.error(e.getMessage());
				}
				searcherElements.put(id, searchElement);
				
			} 
		}
	}
	/**
	 * @return the searcherElements
	 */
	public Map<String, ARESSearcherElement> getSearcherElements() {
		return searcherElements;
	}
	
	
	
}
