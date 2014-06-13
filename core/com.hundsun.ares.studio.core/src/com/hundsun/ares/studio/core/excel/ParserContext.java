/**
 * 源程序名称：ParserContext.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sundl
 *
 */
public class ParserContext {

	private Map<String, Object> context = new HashMap<String, Object>();
	
	public Object get(String key) {
		return context.get(key);
	}
	
	public void put(String key, Object value) {
		this.context.put(key, value);
	}
	
}
