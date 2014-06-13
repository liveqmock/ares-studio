package com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport;

import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
import com.hundsun.ares.studio.ui.cellEditor.ARESContentProposal;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

public class FuncProxyContentProposalHelper implements
		IContentProposalProviderHelper {

	IARESResource resource;
	public FuncProxyContentProposalHelper(IARESResource resource) {
		super();
		this.resource = resource;
	}


	@Override
	public IContentProposal getProposal(String contents, int position,
			Object element) {
		if(element instanceof Function){
			Function func = (Function)element;
			if(MenuUtils.isStockDepartment()){
				return new ARESContentProposal(func.getName(), func.getChineseName(),func.getDescription());
			}else{
				return new ARESContentProposal(func.getSubTransCode(), func.getChineseName(),func.getDescription());
			}
		}
		return null;
	}

}
