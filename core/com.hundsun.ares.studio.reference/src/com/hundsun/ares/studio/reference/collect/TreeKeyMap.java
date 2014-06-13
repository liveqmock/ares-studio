/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.reference.collect;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * 
 * 适用于put多于get的情况
 * 
 * @author gongyf
 *
 */
public class TreeKeyMap<V> {
	private ListMultimap<TreeKey, V> map = ArrayListMultimap.create();

	public boolean put(TreeKey key, V value) {
		boolean result = true;
		for (TreeKey k : key.resolve()) {
			result &= map.put(k, value);
		}
		return result;
	}

	/**
	 * 删除指定key所包含的所有value
	 * @param key
	 */
	public void remove(TreeKey key) {
		Collection<V> value = map.get(key);
		
		for (TreeKey k : key.resolve()) {
			for (V v : value) {
				map.remove(k, v);
			}
		}
	}

	/**
	 * @param key
	 * @return
	 * @see com.google.common.collect.ListMultimap#get(java.lang.Object)
	 */
	public List<V> get(TreeKey key) {
		return map.get(key);
	}

	/**
	 * 
	 * @see com.google.common.collect.Multimap#clear()
	 */
	public void clear() {
		map.clear();
	}
	
	
}
