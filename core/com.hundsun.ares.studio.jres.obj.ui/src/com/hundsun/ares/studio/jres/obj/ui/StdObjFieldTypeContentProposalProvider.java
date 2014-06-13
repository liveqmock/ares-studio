package com.hundsun.ares.studio.jres.obj.ui;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.hundsun.ares.studio.biz.core.ObjectRefTypes;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

public class StdObjFieldTypeContentProposalProvider extends AresContentProposalProvider {

	private static Logger logger = Logger.getLogger(StdObjFieldTypeContentProposalProvider.class);
	
	IARESProject project;
    public StdObjFieldTypeContentProposalProvider(IContentProposalProviderHelper helper, IARESProject project) {
    	super(helper);
    	this.project = project;
    }

	@Override
	public void updateContent(Object element) {
		Collection<ReferenceInfo> references = ObjectRefTypes.INSTANCE.getObjectReferences(project, true);
		setInput(references.toArray());
	}

}
