/**
 * 
 */
package com.hundsun.ares.studio.jres.script.internal.util;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonParser;


/**
 * @author yanwj06282
 *
 */
public class JSONUtilImpl implements IJSONUtil {
	
	JsonParser paser = new JsonParser();

	public String getStringFromJSON (String content , String key) {
		try {
			return paser.parse(content).getAsJsonObject().get(key).getAsString();
		} catch (Exception e) {
		}
		return StringUtils.EMPTY;
	}
	


}
