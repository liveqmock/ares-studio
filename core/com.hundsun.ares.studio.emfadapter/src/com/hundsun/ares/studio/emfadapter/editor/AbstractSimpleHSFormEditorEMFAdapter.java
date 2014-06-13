package com.hundsun.ares.studio.emfadapter.editor;

import org.eclipse.emf.ecore.EObject;

/**
 * ²»´øsourceÒ³µÄEMF±à¼­Æ÷¡£
 * 
 * @author mawb
 *
 * @param <T>
 */
public abstract class AbstractSimpleHSFormEditorEMFAdapter<T extends EObject> extends AbstractHSFormEditorEMFAdapter<T> {

	@Override
	protected void createSourcePage() {
		// do nothing
	}
	
}
