/**
 * 源程序名称：ProblemPoolChangeEvent.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.validate;

import java.util.Map;

/**
 * @author gongyf
 *
 */
public class ProblemPoolChangeEvent {
	
	
	private IProblemPool source;
	private Map<Object, Object> context;
	private Object[] removeedProblems;
	private Object[] addProblems;
	
	/**
	 * @param source
	 * @param removeedProblems
	 * @param addProblems
	 * @param context
	 */
	public ProblemPoolChangeEvent(IProblemPool source,
			Object[] removeedProblems, Object[] addProblems,
			Map<Object, Object> context) {
		super();
		this.source = source;
		this.removeedProblems = removeedProblems;
		this.addProblems = addProblems;
		this.context = context;
	}
	/**
	 * @return the source
	 */
	public IProblemPool getSource() {
		return source;
	}
	/**
	 * @return the context
	 */
	public Map<Object, Object> getContext() {
		return context;
	}
	
	/**
	 * @return the removeedProblems
	 */
	public Object[] getRemoveedProblems() {
		return removeedProblems;
	}
	
	/**
	 * @return the addProblems
	 */
	public Object[] getAddProblems() {
		return addProblems;
	}
}
