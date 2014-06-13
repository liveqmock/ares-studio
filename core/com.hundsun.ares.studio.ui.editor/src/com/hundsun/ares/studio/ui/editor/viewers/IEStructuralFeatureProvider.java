/**
 * 源程序名称：IEStructuralFeatureProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.viewers;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author gongyf
 *
 */
public interface IEStructuralFeatureProvider {
	/**
	 * 根据不同的对象返回不同的属性
	 * @param element
	 * @return
	 */
	EStructuralFeature getFeature(Object element);
	
	/**
	 * 一个最简单的实现
	 * @author gongyf
	 *
	 */
	static class Impl implements IEStructuralFeatureProvider {
		private EStructuralFeature attribute;

		/**
		 * @param attribute
		 */
		public Impl(EStructuralFeature attribute) {
			super();
			this.attribute = attribute;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.jres.ui.viewers.IEAttributeProvider#getAttribute(java.lang.Object)
		 */
		@Override
		public EStructuralFeature getFeature(Object element) {
			return attribute;
		}
		
	}
}
