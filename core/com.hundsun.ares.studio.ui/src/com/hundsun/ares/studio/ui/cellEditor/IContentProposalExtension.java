/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.cellEditor;

import org.eclipse.jface.viewers.StyledString;

/**
 * Content Proposal接口的扩展，用StyledString显示说明信息。
 * @author sundl
 */
public interface IContentProposalExtension {

	/**
	 * Returns the styled string used to display this proposal in the list of completion proposals.
	 * This can for example be used to draw mixed colored labels.
	 * <p>
	 * <strong>Note:</strong> {@link ICompletionProposal#getDisplayString()} still needs to be
	 * correctly implemented as this method might be ignored in case of uninstalled owner draw
	 * support.
	 * </p>
	 *
	 * @return the string builder used to display this proposal
	 */
	StyledString getStyledDisplayString();
}
