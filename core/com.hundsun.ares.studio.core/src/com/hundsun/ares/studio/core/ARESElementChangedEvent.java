/**
 * 源程序名称：ARESElementChangedEvent.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core;

/**
 * 更改事件。
 * 目前暂时以一个事件类型来标识respath改变的事件。
 * @author sundl
 */
public class ARESElementChangedEvent {

	public static final int UNKNOWN = -1;
	public static final int RES_PATH = 0;
	
	private int type = UNKNOWN;
	private IARESElement elemnent;
	
	/**
	 * 涉及老代码的兼容问题，提供默认的不明事件。
	 */
	public ARESElementChangedEvent() {
		this(null, UNKNOWN);
	}
	
	/**
	 * @param element 对应的element，在type为respath变化的时候，element为对应的project
	 * @param type 类型，目前只有两种，未知和respath变化
	 */
	public ARESElementChangedEvent(IARESElement element, int type) {
		this.elemnent = element;
		this.type = type;
	}
	
	public IARESElement getElement() {
		return elemnent;
	}
	
	public int getType() {
		return this.type;
	}
	
}
