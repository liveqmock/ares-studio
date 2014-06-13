/**
 * 源程序名称：PseudoCodeReference.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：liaogc
 */
package com.hundsun.ares.studio.core.model.impl;

import java.util.Map;

import com.hundsun.ares.studio.core.model.Reference;

/**
 * 带有其他条件的引用,在重构时根据条件进行重构
 * @author liaogc
 */
public interface ConditionReference extends Reference{
	boolean canDo(Map<Object,Object> parameters);

}
