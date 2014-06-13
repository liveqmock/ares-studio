/**
 * 源程序名称：EMFModelConverter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.modelconvert
 * 功能说明：文件读取和反序列中的适配扩展实现
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.modelconvert;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;

/**
 * 适用于EMF读写的支持类
 * @author gongyf
 *
 */
public class EMFModelConverter extends BaseModelConverter {
	

	@Override
	protected void processInfoOnRead(Object info, IARESResource resource) {
		if (info instanceof JRESResourceInfo) {
			// 添加临时信息
			((JRESResourceInfo) info).setFullyQualifiedName(resource.getFullyQualifiedName());
//			((JRESResourceInfo) info).setProject(new JRESProjectHandlerImpl(resource.getARESProject()));
			((JRESResourceInfo) info).setName(resource.getName());
		}
	}
	
	@Override
	protected void processInfoOnWrite(Object info, IARESResource resource) {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.modelconvert.BaseModelConverter#getDefaultModelConverterHandle(java.lang.String)
	 */
	@Override
	protected ModelConverterHandle getDefaultModelConverterHandle(String type) {
		return new DefaultEMFModelConverterHandle();
	}
}
