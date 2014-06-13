/**
 * 源程序名称：StringProposal.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors.dialog.inner;

import org.eclipse.jface.fieldassist.IContentProposal;

/**
 * @author yanwj06282
 *
 */
public class StringProposal implements IContentProposal{
	private Object content;

	public StringProposal(Object content) {
		this.content = content;
	}

	@Override
	public String getLabel() {
		return content.toString();
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public int getCursorPosition() {
		return content.toString().length();
	}

	@Override
	public String getContent() {
		return content.toString();
	}
}
