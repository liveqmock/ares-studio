/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.userdialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.core.model.extendable.ExtendFieldsHeader;

/**
 * xml配置模型
 * @author maxh
 */
public class XmlConfigInterface implements Cloneable {
	/*
	 * 菜单生成xml
	 */

	private Map<String, DialogInterfaceXml> menuInterfaceXml = new HashMap<String, DialogInterfaceXml>();
	private Map<String, List<ExtendFieldsHeader>> extendColumns = new HashMap<String, List<ExtendFieldsHeader>>();

	/**
	 * @return the menuInterfaceXml
	 */
	public DialogInterfaceXml getMenuInterfaceXml(String key) {
		return menuInterfaceXml.get(key);
	}

	/**
	 * @param menuInterfaceXml the menuInterfaceXml to set
	 */
	public void addMenuInterfaceXml(String key, DialogInterfaceXml menuInterfaceXml) {
		this.menuInterfaceXml.put(key, menuInterfaceXml);
	}
	
	public Set<String> getKeySet(){
		return menuInterfaceXml.keySet();
	}


	public Map<String, List<ExtendFieldsHeader>> getExtendColumns() {
		return extendColumns;
	}

}
