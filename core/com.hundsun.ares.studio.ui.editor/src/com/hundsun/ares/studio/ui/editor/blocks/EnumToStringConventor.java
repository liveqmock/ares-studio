/**
 * 源程序名称：EnumToStringConventor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.core.databinding.conversion.IConverter;

public class EnumToStringConventor implements IConverter{

	@Override
	public Object getFromType() {
		return null;
	}

	@Override
	public Object getToType() {
		return null;
	}

	@Override
	public Object convert(Object fromObject) {
		return fromObject.toString();
	}

}
