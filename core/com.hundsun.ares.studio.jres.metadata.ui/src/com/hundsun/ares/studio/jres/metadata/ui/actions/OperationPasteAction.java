/**
 * 源程序名称：OperationPasteAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.actions.JresPasteAction;

/**
 * 
 * @author sundl
 */
public class OperationPasteAction extends JresPasteAction {
	@Override
	protected Object getFeature() {
		return MetadataPackage.Literals.METADATA_RESOURCE_DATA__OPERATIONS;
	}
}
