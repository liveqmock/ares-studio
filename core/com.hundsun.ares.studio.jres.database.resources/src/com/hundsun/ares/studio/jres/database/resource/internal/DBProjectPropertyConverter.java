/**
 * 源程序名称：DBProjectPropertyConverter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.resource.internal;

import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.modelconvert.EMFExtendModelConverter;

/**
 * @author gongyf
 *
 */
public class DBProjectPropertyConverter extends EMFExtendModelConverter {
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.internal.EMFExtendModelConverter#prepareEPackageRegistry()
	 */
	@Override
	protected void prepareEPackageRegistry() {
		super.prepareEPackageRegistry();
		EPackage.Registry.INSTANCE.put(DatabasePackage.eNS_URI, DatabasePackage.eINSTANCE);
	}
}
