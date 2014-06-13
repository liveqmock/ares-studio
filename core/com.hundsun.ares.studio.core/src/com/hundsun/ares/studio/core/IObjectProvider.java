/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.core;


/**
 * 从一个ARES资源获得一个对象<BR>
 * 需要注意对 {@link #equals(Object)} 和{@link #hashCode()}的重新实现
 * @author gongyf
 *
 */
public interface IObjectProvider {
	Object getObject(IARESResource resource);
}
