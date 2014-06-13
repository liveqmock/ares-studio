/**
 * 源程序名称：Pair.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 可以作为融合了2个值的对象
 * @author gongyf
 *
 */
public class Pair<F, S> {
	public final F first;
	public final S second;
	/**
	 * @param first
	 * @param second
	 */
	public Pair(F first, S second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Pair) {
			return ObjectUtils.equals(first, ((Pair) obj).first) 
				&& ObjectUtils.equals(second, ((Pair) obj).second);
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(first).append(second).toHashCode();
	}
}
