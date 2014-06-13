package com.hundsun.ares.studio.jres.basicdata.ui.proposal;

import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem;
import com.hundsun.ares.studio.ui.cellEditor.ARESContentProposal;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

public class BasicDataContentProposalProviderHelper implements
		IContentProposalProviderHelper {

	@Override
	public IContentProposal getProposal(String contents, int position,
			Object element) {
		if(element instanceof DeDictionaryItem){
			DeDictionaryItem item = (DeDictionaryItem)element;
			return new ARESContentProposal(item.getValue() + "_" + item.getChineseName(), "");
		}
		return null;
	}

}
