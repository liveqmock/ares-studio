/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.util;

/**
 * 
 * @author maxh
 */
public class ArrayUtil {
	/**
	 * 在数组中找某个对象
	 * @param items
	 * @param key
	 * @return
	 */
	public static int findInArray(Object[] items,Object key){
		for(int i = 0;i < items.length;i++){
			if(items[i].equals(key)){
				return i;
			}
		}
		return -1;
	}
}
