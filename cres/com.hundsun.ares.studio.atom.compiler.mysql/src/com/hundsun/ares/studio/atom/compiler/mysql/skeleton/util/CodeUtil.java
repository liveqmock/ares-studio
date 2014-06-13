/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util;


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
	
	public static String trimTab(String strValue) {

        String strResult = "";

        for (int i = 0; i < strValue.length(); i++) {

            char charTemp = strValue.charAt(i);

            if (charTemp != ' ' && charTemp != '　' && charTemp !='\t') {
                strResult = strValue.substring(i);
                break;
            }
        }

        for (int i = strResult.length() - 1; i >= 0; i--) {

            char charTemp = strResult.charAt(i);

            if (charTemp != ' ' && charTemp != '　' && charTemp != '\t') {
                strResult = strResult.substring(0, i + 1);
                break;
            }
        }

        return strResult;
    } 
	public static void main(String[] args) {
		
	  String Str = "	jjjj";
	  System.out.println("aa"+Str);
	  System.out.println("aa"+trimTab(Str));

	}
}
