/**
 * 源程序名称：BizPkgExtendedPropertyLabelProvidedr.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.metadata.ui;

import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.extend.ExtendsLabelProvider;

/**
 * @author sundl
 *
 */
public class BizPkgExtendedPropertyLabelProvider extends ExtendsLabelProvider{
	
	private IARESProject project;

	public BizPkgExtendedPropertyLabelProvider(String key ,EStructuralFeature feature, String extendModelKey) {
		super(key, feature, extendModelKey);
	}
	
	@Override
	public String getText(Object element) {
		String id = super.getText(element);
		MetadataUtil.getBizPropertyConfig(id, project);
		return id;
	}
	
}
