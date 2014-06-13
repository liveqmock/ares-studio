/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.resources.mapping.ResourceMappingContext;
import org.eclipse.core.resources.mapping.ResourceTraversal;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.ui.ARESResourceCategory;

/**
 * 
 * @author sundl
 */
public class ARESResourceCategoryResourceMapping extends ResourceMapping {

	private ARESResourceCategory category;
	
	public ARESResourceCategoryResourceMapping(ARESResourceCategory category) {
		this.category = category;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.mapping.ResourceMapping#getModelObject()
	 */
	@Override
	public Object getModelObject() {
		return category;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.mapping.ResourceMapping#getModelProviderId()
	 */
	@Override
	public String getModelProviderId() {
		return ARESModelProvider.ID;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.mapping.ResourceMapping#getProjects()
	 */
	@Override
	public IProject[] getProjects() {
		return new IProject[] {category.getModule().getARESProject().getProject()};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.mapping.ResourceMapping#getTraversals(org.eclipse.core.resources.mapping.ResourceMappingContext, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public ResourceTraversal[] getTraversals(ResourceMappingContext context, IProgressMonitor monitor) throws CoreException {
		IARESResource[] aresResources = category.getResources();
		IResource[] resources = ARESElementUtil.toResource(aresResources);
		return new ResourceTraversal[] {new ResourceTraversal(resources, IResource.DEPTH_ZERO, IResource.NONE)};
	}

}
