/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procdure.constants;

import java.util.HashMap;

/**
 * @author qinyuan
 *
 */
public class ProcedureResourceMapping {
	
private static HashMap<String, String> typeMap = new HashMap<String, String>();
	
	static{
		typeMap.put(IProcedureResType.PROCEDURE, IProcedureRefType.PROCEDURE_CNAME);
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
