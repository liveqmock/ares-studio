/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.extend.ui.module.gencode.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author liaogc
 *
 */
public class FileNameHelper {
	/**
	 * 去除目录或者文件中不合法的字符
	 * @param name
	 * @return
	 */
	public static String legalFileOrDirName(String name) {
		String illegalStr = "\\/:*?\"<>|";
		char []illegalArray = illegalStr.toCharArray();
		String newName = name;
		for(int i=0;i<illegalArray.length;i++){
			newName = StringUtils.replace(newName, illegalArray[i]+"", "");
		}
		return newName;
	}
}
