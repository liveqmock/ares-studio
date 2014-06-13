/**
 * 源程序名称：HisPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.model.RevisionHistory;

/**
 * 
 * @author sundl
 */
public class HisPropertyHandler extends EMFPropertyHandler {

	/**
	 * @param feature
	 */
	public HisPropertyHandler(EStructuralFeature feature) {
		super(feature);
	}
	
	@Override
	public void setValue(Object obj, String value) {
		// 如果不是EMF对象，什么都不做
//		if (obj instanceof EObject) {
//			((EObject) obj).eSet(feature, value);
//		}
	}

	@Override
	public String getValue(Object obj) {
		if (obj instanceof EObject) {
			List<RevisionHistory> histories = (List<RevisionHistory>) ((EObject) obj).eGet(feature);
			if (histories != null) {
				StringBuilder builder = new StringBuilder();
				for (RevisionHistory his : histories) {
					String version = his.getVersion();
					if (StringUtils.isNotEmpty(version)) {
						version = StringUtils.startsWithIgnoreCase(version, "v") ? version : "V" + version;
					}
					String str = StringUtils.join(new String[] {
								version, 
								his.getModifiedDate(), 
								his.getModifiedBy() + "申请",
								his.getCharger() + "修改",
								his.getModified()}, " ");
					builder.append(str);
					builder.append("\n");
				}
				return builder.toString();
			}
		}
		return null;
	}

}
