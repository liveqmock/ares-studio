/**
 * 
 */
package com.hundsun.ares.studio.jres.script.internal.util;


/**
 * 
 * JSONπ§æﬂ¿‡
 * 
 * @author yanwj06282
 *
 */
public interface IJSONUtil {

	public static IJSONUtil instance = new JSONUtilImpl();
	
	public String getStringFromJSON (String content , String key);
	
//	public Object getObectFromJSON (String content , String key) ;
	
}
