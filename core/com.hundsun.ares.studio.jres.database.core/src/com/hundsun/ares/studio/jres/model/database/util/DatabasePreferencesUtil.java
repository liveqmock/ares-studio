/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.model.database.util;

import org.eclipse.core.runtime.Platform;

/**
 * @author liaogc
 *
 */
public class DatabasePreferencesUtil {
	
	public static String getDatabsePprfernce(String key,String defaultValue){
		return Platform.getPreferencesService().getString("com.hundsun.ares.studio.jres.database.ui", key, defaultValue, null);
		
	}

}
