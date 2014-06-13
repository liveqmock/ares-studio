/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.reference.collect;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author gongyf
 *
 */
public final class TreeKey {
	final private Object[] keys;
	private TreeKey[] resolved;
	
	/**
	 * 
	 */
	public TreeKey(Object... keys) {
		this.keys = keys;
	}
	
	public TreeKey[] resolve() {
		if (resolved == null) {
			resolved = new TreeKey[keys.length];
			for (int i = 0; i < keys.length; i++) {
				resolved[i] = new TreeKey(ArrayUtils.subarray(keys, 0, i + 1));
			}
		}
		return resolved;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if(!(obj instanceof TreeKey)){
			return false;
		}
		
		return Arrays.equals(keys, ((TreeKey)obj).keys);
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(keys).toHashCode();
	}
	
}
