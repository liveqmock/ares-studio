/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

/**
 * 监听器。
 * @author sundl
 */
public interface IARESElementChangeListener {

	/**
	 * 暂时简单实现一个监听结构，调用这个方法通知监听器。
	 */
	// sundl 增加参数，让监听器可以确定事件的具体信息，比如类型，更改的元素等，目前的实现仅支持respath变化的具体化
	// 其他还都是泛类型
	void elementChanged(ARESElementChangedEvent event);
}
