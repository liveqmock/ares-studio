/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.extendpoint.manager;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.hundsun.ares.studio.core.util.ExtendPointUtil;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

/**
 * 界面描述拓展点
 * @author maxh
 *
 */
public class ExtendFieldDetailManager {
	Map<Class,AresExtendPointModelDetail> map;
	ExtendFieldDetailManager() {
		if(map == null){
			map = new HashMap<Class, AresExtendPointModelDetail>();
			for(IConfigurationElement ce:ExtendPointUtil.readAllConfiguredElements(ARESEditorPlugin.FIELD_DETAIL_ID)){
				try {
					Class type = Platform.getBundle(ce.getNamespaceIdentifier()).loadClass(ce.getAttribute("class"));
					AresExtendPointModelDetail info = new AresExtendPointModelDetail();
					info.setMainGetMethod(ce.getAttribute("mainGetMethod"));
					info.setShowName(ce.getAttribute("showName"));
					info.setShowPic(ce.getAttribute("showPic"));
					for(IConfigurationElement child:ce.getChildren("fieldDetail")){
						AresExtendPointFieldDetail field = new AresExtendPointFieldDetail();
						field.setFieldName(child.getAttribute("fieldName"));
						field.setShowName(child.getAttribute("showName"));
						field.setShowPic(child.getAttribute("showPic"));
						field.setShowControlType(child.getAttribute("showControlType"));
						field.setShowInOutline(Boolean.valueOf(child.getAttribute("showInOutline")));
						field.setGenUi(Boolean.valueOf(child.getAttribute("genUI")));
						field.setValue(child.getAttribute("value"));
						info.getFields().add(field);
					}
					map.put(type, info);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}
	static ExtendFieldDetailManager manager;
	static public ExtendFieldDetailManager getDefault(){
		if(manager == null){
			manager = new ExtendFieldDetailManager();
		}
		return manager;
	}
	
	public Map<Class, AresExtendPointModelDetail> getMap() {
		return map;
	}
}
