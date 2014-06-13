/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuyf
 *
 */
public class KeyWordEntity {
	
	private String name;//原字段名
	private String type;//数据库类型
	private String escape;//转义字段名
	
	public KeyWordEntity(String name,String type,String escape){
		this.name = name;
		this.type = type;
		this.escape = escape;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the escape
	 */
	public String getEscape() {
		return escape;
	}
	
	

}
