/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.cellEditor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.viewers.StyledString;

/**
 * IContent proposal的一个默认的实现; 默认使用 内容 - 短描述 进行展示，且短描述使用浅色区分。
 * @author sundl
 */
public class ARESContentProposal implements IContentProposal, IContentProposalExtension{

	private static final String SEPERATOR = " - ";
	
	private String content;
	private String shortDesc;
	private String desc;
	
	/**
	 * @param content 要替换的内容 不可为空
	 * @param shortDesc 短描述 可以为空
	 * @param desc 长描述，将显示在右侧单独的窗口中，不显示则可以为null
	 */
	public ARESContentProposal(String content, String shortDesc, String desc) {
		this.content = content;
		this.shortDesc = shortDesc;
		this.desc = desc;
	}
	
	/**
	 * @param content 要替换的内容 不可为空
	 * @param shortDesc 短描述 可以为空
	 */
	public ARESContentProposal(String content, String shortDesc) {
		this(content, shortDesc, null);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.cellEditor.IContentProposalExtension#getStyledDisplayString()
	 */
	@Override
	public StyledString getStyledDisplayString() {
		StyledString styledString = new StyledString(content);
		if (!StringUtils.isEmpty(shortDesc)) {
			styledString.append(new StyledString(SEPERATOR + shortDesc, StyledString.DECORATIONS_STYLER));
		}
		return styledString;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
	 */
	@Override
	public String getContent() {
		return content;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
	 */
	@Override
	public int getCursorPosition() {
		return content.length();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
	 */
	@Override
	public String getLabel() {
		return StringUtils.isEmpty(shortDesc) ? content : content + SEPERATOR + shortDesc;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
	 */
	@Override
	public String getDescription() {
		return desc;
	}

}
