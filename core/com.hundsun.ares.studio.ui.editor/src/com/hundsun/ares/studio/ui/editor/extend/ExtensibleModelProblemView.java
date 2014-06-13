/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.forms.IMessageManager;

import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.ui.editor.FormControlProblemView;
import com.hundsun.ares.studio.ui.validate.IProblemView;
import com.hundsun.ares.studio.ui.validate.KeyParameter;
import com.hundsun.ares.studio.ui.validate.ProblemPoolChangeEvent;

/**
 * @author gongyf
 *
 */
public class ExtensibleModelProblemView implements IProblemView {

	private ExtensibleModel model;
	
	private EObject userExtensibleModel;
	
	
	/**
	 * @param model
	 */
	public ExtensibleModelProblemView(ExtensibleModel model) {
		super();
		this.model = model;
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.validate.IProblemView#refresh(com.hundsun.ares.studio.jres.ui.validate.IProblemPool, java.util.Map)
	 */
	@Override
	public void refresh(ProblemPoolChangeEvent event) {
		userExtensibleModel = model.getData2().get(Constants.USER_DATA2_KEY);
		IMessageManager manager =  (IMessageManager) event.getContext().get(FormControlProblemView.KEY_MESSAGEMANAGER);
		if (manager != null) {
			for (Object problem : event.getRemoveedProblems()) {
				if (problem instanceof Diagnostic) {
					EObject owner = (EObject) ((Diagnostic) problem).getData().get(0);
//					if (EcoreUtil.isAncestor(model.getData2(), owner)) {
					if(userExtensibleModel != null && EcoreUtil.isAncestor(userExtensibleModel, owner)){
						manager.removeMessage(new KeyParameter(((Diagnostic) problem).getData().toArray()) );
					}
				}
			}
			
			for (Object problem : event.getAddProblems()) {
				if (problem instanceof Diagnostic) {
					if (problem instanceof Diagnostic) {
						EObject owner = (EObject) ((Diagnostic) problem).getData().get(0);
//						if (EcoreUtil.isAncestor(model.getData2(), owner)) {
						if(userExtensibleModel != null && EcoreUtil.isAncestor(userExtensibleModel, owner)){
							manager.addMessage(new KeyParameter(((Diagnostic) problem).getData().toArray()),  
								((Diagnostic) problem).getMessage(), problem, FormControlProblemView.convertDiagnosticSeverity(((Diagnostic) problem).getSeverity()));
						}
					}
				}
			}

		}
	}


}
