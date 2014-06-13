/**
 * 源程序名称：IARESResourceProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据编辑器相关
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author gongyf
 *
 */
public interface IARESResourceProvider {
	
	/**
	 * 获取指定对象所在的IARESResource对象
	 * @param element
	 * @return
	 */
	IARESResource getResource(Object element);
	
	/**
	 * 一个最简单的实现
	 * @author gongyf
	 *
	 */
	static class Impl implements IARESResourceProvider {
		private IARESResource resource;

	
		/**
		 * @param resource
		 */
		public Impl(IARESResource resource) {
			super();
			this.resource = resource;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.jres.metadata.ui.viewer.IARESResourceProvider#getResource(java.lang.Object)
		 */
		@Override
		public IARESResource getResource(Object element) {
			return resource;
		}
		
	}
}
