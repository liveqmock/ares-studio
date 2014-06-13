/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.constants;

import java.util.HashMap;


/**
 * @author qinyuan
 *
 */
public class LogicResourceMapping {
	private static HashMap<String, String> typeMap = new HashMap<String, String>();
	
	static{
		typeMap.put(ILogicResType.LOGIC_SERVICE, ILogicRefType.LOGIC_SERVICE_CNAME);
		typeMap.put(ILogicResType.LOGIC_FUNCTION, ILogicRefType.LOGIC_FUNCTION_CNAME);
	}
	
	/**
	 * 获取中文名类型
	 * @param type
	 * @return
	 */
	public static String getCNameType(String type){
		return typeMap.get(type);
	}


}
