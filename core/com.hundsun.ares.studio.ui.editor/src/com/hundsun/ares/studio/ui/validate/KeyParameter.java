/**
 * 源程序名称：KeyParameter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.validate;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 错误在错误池中的key，相对于一般map的key定义这个key是多重的<BR>
 * key(a)可以在错误池中取到key(a,*) key(a,*,*) key(a,*,*,*)...对应的错误列表
 * 
 * @author lvgao
 *
 */
public class KeyParameter {
	
	final private Object[] key;
	private KeyParameter[] resolved;
	
	public KeyParameter(Object... key) {
		this.key = key;
	}
	
	/**
	 * 返回键值的所有表述
	 * @return
	 */
	public KeyParameter[] resolve() {
		if (resolved == null) {
			resolved = new KeyParameter[key.length];
			for (int i = 0; i < key.length; i++) {
				resolved[i] = new KeyParameter(ArrayUtils.subarray(key, 0, i + 1));
			}
		}
		return resolved;
	}
	
	public boolean contains(KeyParameter key) {
		return ArrayUtils.contains(resolve(), key);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof KeyParameter)){
			return false;
		}
		
		return Arrays.equals(key, ((KeyParameter)obj).key);
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(key).toHashCode();
	}

}
