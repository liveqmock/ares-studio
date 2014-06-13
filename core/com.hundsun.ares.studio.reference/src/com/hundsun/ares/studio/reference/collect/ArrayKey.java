/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.reference.collect;

import java.util.Arrays;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class ArrayKey {
	Object[] array;
	/**
	 * 
	 */
	public ArrayKey(Object... array) {
		this.array = array;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if(!(obj instanceof ArrayKey)){
			return false;
		}
		
		return Arrays.equals(array, ((ArrayKey)obj).array);
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(array).toHashCode();
	}
}