package com.hundsun.ares.studio.biz.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.ui.assist.JRESContentPorposalHelper;
import com.hundsun.ares.studio.ui.cellEditor.ARESContentProposal;

public class ObjContentProposalHelper extends JRESContentPorposalHelper{
	
	public static ObjContentProposalHelper INSTANCE = new ObjContentProposalHelper();

    @Override
	protected IContentProposal getProposal(String contents, int position, EObject item, IARESResource resource) {
    	ARESObject aresObject = null;
		if (item instanceof ARESObject) {
			aresObject =  (ARESObject) item;
		} else if (item instanceof ReferenceInfo){
			ReferenceInfo refInfo = (ReferenceInfo) item;
			Object info = refInfo.getObject();
			if (info instanceof ARESObject) {
				aresObject = (ARESObject) info;
			}
		}
		
		if (aresObject != null) {
			String content = resource.getFullyQualifiedName();
			String desc = aresObject.getChineseName();
			return new ARESContentProposal(content, desc);
		}
		return null;
	}

}
