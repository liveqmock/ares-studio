/**
 * 源程序名称：IProblemPool.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.validate;

import java.util.Set;

/**
 * @author gongyf
 *
 */
public interface IProblemPool {
	void addView(IProblemView view);
	void removeView(IProblemView view);
	
	void beginChange();
	void endChange();
	
	void addProblem(KeyParameter key, Object problem);
	void addProblem(Object problem);
	void addProblems(Object[] problem);
	public void setKeyConstructor(IKeyConstructor handler);
	
	public Object[] getProblem(KeyParameter key);
	public boolean hasProblem(KeyParameter key);
	
	public void clear();
	public void clear(KeyParameter key);
	
	public Set<KeyParameter> getKeys();
}
