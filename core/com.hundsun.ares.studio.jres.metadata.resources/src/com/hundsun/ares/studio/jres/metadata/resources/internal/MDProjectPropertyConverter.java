/**
 * 源程序名称：MDProjectPropertyConverter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.metadata.resources.internal;

import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.modelconvert.EMFExtendModelConverter;

/**
 * @author gongyf
 *
 */
public class MDProjectPropertyConverter extends EMFExtendModelConverter {
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.internal.EMFExtendModelConverter#prepareEPackageRegistry()
	 */
	@Override
	protected void prepareEPackageRegistry() {
		super.prepareEPackageRegistry();
		EPackage.Registry.INSTANCE.put(MetadataPackage.eNS_URI, MetadataPackage.eINSTANCE);
	}
}
