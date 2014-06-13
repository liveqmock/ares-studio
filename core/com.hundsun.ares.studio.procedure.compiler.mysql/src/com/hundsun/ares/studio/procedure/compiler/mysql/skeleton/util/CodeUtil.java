/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.mysql.skeleton.util;

/**
 * @author liaogc
 *
 */
public class CodeUtil {
	public static StringBuffer formatInsert(String str){
		int haveAt = 0;
		if(str.indexOf("@") != -1)
			haveAt = 1;
		StringBuffer buffer = new StringBuffer(str);
		for(int i = 0 ;20-buffer.length()-haveAt>0;i++){
			buffer.append(" ");
		}
		return buffer;
	 } 
	
	/**
	 * format过程中的insert语句
	 * @param str
	 * @param strLen
	 * @return
	 */
	public static StringBuffer formatInsert(String str,int strLen){ 
		int haveAt = 0;
		if(str.indexOf("@") != -1)
			haveAt = 1;
		StringBuffer buffer = new StringBuffer(str);
		for(int i = 0 ;strLen-buffer.length()-haveAt>0;i++){
			buffer.append(" ");
		}
		return buffer;
	 } 
}
