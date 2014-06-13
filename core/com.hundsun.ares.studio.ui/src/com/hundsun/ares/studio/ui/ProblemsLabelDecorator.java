/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * @author sundl
 */
public class ProblemsLabelDecorator implements ILabelDecorator,	ILightweightLabelDecorator {
	
	public static class ProblemsLabelChangedEvent extends LabelProviderChangedEvent {

		private static final long serialVersionUID= 1L;

		private boolean fMarkerChange;

		/**
		 * @param eventSource the base label provider
		 * @param changedResource the changed resources
		 * @param isMarkerChange <code>true</code> if the change is a marker change; otherwise
		 *  <code>false</code>
		 */
		public ProblemsLabelChangedEvent(IBaseLabelProvider eventSource, IResource[] changedResource, boolean isMarkerChange) {
			super(eventSource, changedResource);
			fMarkerChange= isMarkerChange;
		}

		/**
		 * Returns whether this event origins from marker changes. If <code>false</code> an annotation
		 * model change is the origin. In this case viewers not displaying working copies can ignore these
		 * events.
		 *
		 * @return if this event origins from a marker change.
		 */
		public boolean isMarkerChange() {
			return fMarkerChange;
		}

	}
	
	private ListenerList fListeners;

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		if (fListeners == null) {
			fListeners= new ListenerList();
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object, org.eclipse.jface.viewers.IDecoration)
	 */
	public void decorate(Object element, IDecoration decoration) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image, java.lang.Object)
	 */
	public Image decorateImage(Image image, Object element) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelDecorator#decorateText(java.lang.String, java.lang.Object)
	 */
	public String decorateText(String text, Object element) {
		return text;
	}

}
