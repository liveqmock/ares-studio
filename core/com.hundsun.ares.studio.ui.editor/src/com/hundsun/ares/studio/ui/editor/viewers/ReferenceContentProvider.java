/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.viewers;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author gongyf
 *
 */
public class ReferenceContentProvider implements IStructuredContentProvider {

	static Object[] NONE = new Object[0];
	EReference reference;
	
	
	
	/**
	 * @param reference
	 */
	public ReferenceContentProvider(EReference reference) {
		super();
		this.reference = reference;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof EObject) {
			if (!((EObject) inputElement).eClass().getEAllContainments().contains(reference)) {
				return NONE;
			}
			Object children = ((EObject) inputElement).eGet(reference);
			if (children instanceof Collection) {
				return ((Collection) children).toArray();
			}
		}
		return NONE;
	}

}
