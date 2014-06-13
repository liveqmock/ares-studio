package com.hundsun.ares.studio.internal.ui;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;

import com.hundsun.ares.studio.core.builder.IAresMarkers;
import com.hundsun.ares.studio.ui.ARESUI;

public class AresProjectProblemProvider implements ILightweightLabelDecorator {

	public static final String ICONS_FOLDER_PREFIX = "icons/";
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
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
		if (element instanceof IProject) {
			IProject project = (IProject) element;
			if (!project.isAccessible())
				return;
			
			try {
				int level = project.findMaxProblemSeverity(IAresMarkers.MARKER_ID, true, IResource.DEPTH_INFINITE);
				if (level == IMarker.SEVERITY_ERROR) {
					decoration.addOverlay(ARESUI.getImageDescriptor(ICONS_FOLDER_PREFIX + ARESUI.IMG_PATH_ERROR), IDecoration.BOTTOM_LEFT);
				} else if (level == IMarker.SEVERITY_WARNING) {
					decoration.addOverlay(ARESUI.getImageDescriptor(ICONS_FOLDER_PREFIX + ARESUI.IMG_PATH_WARNING), IDecoration.BOTTOM_LEFT);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}


}
