/**
 * 源程序名称：IReferenceProvider2.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model;

import java.util.List;

import com.hundsun.ares.studio.core.IARESProject;


/**
 * 特殊情况下，Reference的返回值是与Project有关系的，但引用了IARESProject会污染模型，
 * 所以创建这个接口，并用adapter模式创建一下
 * @author sundl
 */
public interface IReferenceProvider2 {

	/**
	 * 计算给定的对象的Reference
	 * @param obj
	 * @param aresProject   给定的一个上下文，说明当前在那个项目中进行
	 * @return
	 */
	public List<Reference> getReferences(Object obj, IARESProject aresProject);
}
