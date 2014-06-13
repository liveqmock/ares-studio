/**
 * 源程序名称：DictItemPasteAction.java
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
public class DictItemPasteAction extends JresPasteAction {

	@Override
	protected Object getFeature() {
		return MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS;
	}
	
}
