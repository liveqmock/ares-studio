/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import java.io.PrintStream;
import java.util.Collection;

/**
 * 注册表统一接口，方便调试使用。
 * @author sundl
 */
public interface ICommonRegistry<T> {
	String getExtensionPointId();
	void print(PrintStream ps);
	Collection<T> get(String id);
	public Collection<T> getDescriptors();
}
