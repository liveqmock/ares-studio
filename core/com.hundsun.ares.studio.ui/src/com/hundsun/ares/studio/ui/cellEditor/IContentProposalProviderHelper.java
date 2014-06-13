/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.cellEditor;

import org.eclipse.jface.fieldassist.IContentProposal;

/**
 * 
 * @author sundl
 */
public interface IContentProposalProviderHelper {

	/**
	 * @param contents  当前输入
	 * @param position  位置
	 * @param obj       要提供内容的对象(input[]中的一条)
	 * @return
	 */
	public IContentProposal getProposal(String contents, int position, Object element);

}
