package com.hundsun.ares.studio.internal.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ModelProvider;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.resources.mapping.ResourceMappingContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.hundsun.ares.studio.ui.ARESUI;

public class ARESModelProvider extends ModelProvider {

	public static String ID = ARESUI.PLUGIN_ID + ".aresmodelprovider";

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.mapping.ModelProvider#getMappings(org.eclipse.core.resources.IResource, org.eclipse.core.resources.mapping.ResourceMappingContext, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public ResourceMapping[] getMappings(IResource resource, ResourceMappingContext context, IProgressMonitor monitor) throws CoreException {
		
		return super.getMappings(resource, context, monitor);
	}
	
}
