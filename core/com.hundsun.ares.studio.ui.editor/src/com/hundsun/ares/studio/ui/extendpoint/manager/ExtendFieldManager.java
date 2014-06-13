/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.extendpoint.manager;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.util.ExtendPointUtil;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.page.IExtendFieldLoader;

/**
 *拓展字段 拓展点添加方式
 * @author maxh
 *
 */
public class ExtendFieldManager {
	Map<String,IExtendFieldLoader> map;
	ExtendFieldManager() {
		map = new HashMap<String, IExtendFieldLoader>();
		IConfigurationElement points[] = ExtendPointUtil.readAllConfiguredElements(ARESEditorPlugin.EXTEND_FIELD_ID);
		for (IConfigurationElement ce : points) {
			try {
				IExtendFieldLoader loader = (IExtendFieldLoader)ce.createExecutableExtension("loader");
				map.put(ce.getAttribute("component_id"), loader);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
	static ExtendFieldManager manager;
	static public ExtendFieldManager getDefault(){
		if(manager == null){
			manager = new ExtendFieldManager();
		}
		return manager;
	}
	
	public Map<String, IExtendFieldLoader> getMap() {
		return map;
	}
}
