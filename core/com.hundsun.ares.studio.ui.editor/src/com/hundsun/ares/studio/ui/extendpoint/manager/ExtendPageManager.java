/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.extendpoint.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.hundsun.ares.studio.core.util.ExtendPointUtil;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.extendpoint.PageExtendPointProvider;

/**
 * 扩展页面拓展点
 * @author maxh
 *
 */
public class ExtendPageManager {
	ExtendPageManager() {
		IConfigurationElement points[] = ExtendPointUtil.readAllConfiguredElements(ARESEditorPlugin.PAGE_EXTEND_ID);
		for (IConfigurationElement ce : points) {
			String name = ce.getAttribute(PageExtendPointProvider.PAGE_NAME);
			String id = ce.getAttribute(PageExtendPointProvider.PAGE_ID);
			String pointEditorId = ce.getAttribute(PageExtendPointProvider.EDITOR_ID);
			int order = NumberUtils.toInt(ce.getAttribute(PageExtendPointProvider.ORDER), Integer.MAX_VALUE);
			boolean hidden = BooleanUtils.toBoolean(ce.getAttribute(PageExtendPointProvider.HIDDEN));
			try {
				String className = ce.getAttribute("class");
				if (className != null && className.length() > 0) {
					Class cls = Platform.getBundle(ce.getNamespaceIdentifier()).loadClass(className);
					if(map.get(pointEditorId) == null){
						map.put(pointEditorId, new ArrayList<ExtendPageInfo>());
					}
					map.get(pointEditorId).add(new ExtendPageInfo(cls,id,name, order, hidden));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	static ExtendPageManager manager;
	static public ExtendPageManager getDefault(){
		if(manager == null){
			manager = new ExtendPageManager();
		}
		return manager;
	}
	Map<String,List<ExtendPageInfo>> map = new HashMap<String, List<ExtendPageInfo>>();
	
	public List<ExtendPageInfo> getPageInfo(String editorId){
		List<ExtendPageInfo> result = new ArrayList<ExtendPageInfo>();
		if(!map.containsKey(editorId)){
			return result;
		}
		result.addAll(map.get(editorId));
		
		Collections.sort(result);
		
		return result;
	}
	
	public void registerPage(String editorId,Class pageType,String pageId,String showName, int order, boolean hidden){
		if(map.get(editorId) == null){
			map.put(editorId, new ArrayList<ExtendPageInfo>());
		}
		map.get(editorId).add(new ExtendPageInfo(pageType,pageId,showName, order, hidden));
	}
}
