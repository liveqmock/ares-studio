/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.tool;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;

/**
 * @author lvgao
 *
 */
public class JRESResourceHelper {

	/**
	 * 从Map中获取模型信息
	 * @param <T>
	 * @param obj
	 * @param clazz
	 * @return
	 */
	public static <T> T getResourceInfo(Object obj, Class<T> clazz){
		IARESResource resource = getResource(obj);
		if(null != resource){
			try {
				return resource.getInfo(clazz);
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	
	public static IARESResource getResource(Object obj){
		if(obj instanceof Map){
			Map tmap = (Map)obj;
			if(tmap.containsKey(IResourceTable.TARGET_RESOURCE)){
				IARESResource resource = (IARESResource)tmap.get(IResourceTable.TARGET_RESOURCE);
				return resource;
			}
		}
		return null;
	}
	
}
