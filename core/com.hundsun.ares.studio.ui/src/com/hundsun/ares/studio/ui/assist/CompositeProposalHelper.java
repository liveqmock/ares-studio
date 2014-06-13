/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.assist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * 多个组合，依次处理，直到能处理为止。 
 * 注意顺序可能还会影响结果。。。
 * @author sundl
 */
public class CompositeProposalHelper implements IContentProposalProviderHelper {
	
	private List<IContentProposalProviderHelper> delegates = new ArrayList<IContentProposalProviderHelper>();
	
	public CompositeProposalHelper(IContentProposalProviderHelper... delegates) {
		this.delegates = Arrays.asList(delegates);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper#getProposal(java.lang.String, int, java.lang.Object)
	 */
	@Override
	public IContentProposal getProposal(String contents, int position, Object element) {
		for (IContentProposalProviderHelper d : this.delegates) {
			IContentProposal p = d.getProposal(contents, position, element);
			if (p != null)
				return p;
		}
		return null;
	}

}
