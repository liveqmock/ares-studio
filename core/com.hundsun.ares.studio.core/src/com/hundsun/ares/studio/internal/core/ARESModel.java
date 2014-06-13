/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModel;
import com.hundsun.ares.studio.core.IARESModelStatusConstants;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;

/**
 * ARES Model的默认实现。
 * @author sundl
 */
public class ARESModel extends Openable implements IARESModel {

	public ARESModel(IARESElement parent) {
		super(parent);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModel#getWorkspace()
	 */
	public IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#exists()
	 */
	public boolean exists() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getAncestor(int)
	 */
	public IARESElement getAncestor(int ancestorType) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementName()
	 */
	public String getElementName() {
		return "";
	}

	protected void getHandleMemento(StringBuffer buff) {
		buff.append(getElementName());
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementType()
	 */
	public int getElementType() {
		return ARES_MODEL;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getHandleIdentifier()
	 */
	public String getHandleIdentifier() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getParent()
	 */
	public IARESElement getParent() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	public IPath getPath() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	public IResource getResource() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#isReadOnly()
	 */
	public boolean isReadOnly() {
		return false;
	}

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#buildStructure(com.hundsun.ares.devtool.v2.internal.core.OpenableElementInfo)
	 */
	@Override
	protected boolean buildStructure(OpenableElementInfo info)
			throws ARESModelException {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		int length = projects.length;
		List<IARESElement> children = new ArrayList<IARESElement>();
		for(int i = 0; i < length; i++) {
			IProject project = projects[i];
			if(ARESProject.hasARESNature(project)) {
				children.add(new ARESProject(this, project));
			}
		}
		((ARESElementInfo)info).setChildren(children);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#validateExistence(org.eclipse.core.resources.IResource)
	 */
	@Override
	protected IStatus validateExistence(IResource underlyingResource) {
		return ARESModelStatus.OK_STATUS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModel#copy(com.hundsun.ares.studio.core.IARESElement[], com.hundsun.ares.studio.core.IARESElement[], java.lang.String[], boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void copy(IARESElement[] elements, IARESElement[] containers, String[] renamings, boolean replace, IProgressMonitor monitor) throws ARESModelException {
		if (containers != null && containers.length == 1) {
			CopyResourceElementsOperation op = new CopyResourceElementsOperation(elements, containers, true);
			op.runOperation(monitor);
		}
		else 
			throw new ARESModelException(new ARESModelStatus(IARESModelStatusConstants.UNSUPPORTED_OPERATION));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModel#delete(com.hundsun.ares.studio.core.IARESElement[], boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void delete(IARESElement[] elements, boolean force, IProgressMonitor monitor) throws ARESModelException {
		DeleteResouceElementsOperation op = new DeleteResouceElementsOperation(elements, true);
		op.runOperation(monitor);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModel#getARESProject(java.lang.String)
	 */
	public IARESProject getARESProject(String name) {
		return new ARESProject(this, ResourcesPlugin.getWorkspace().getRoot().getProject(name));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModel#getARESProjects()
	 */
	public IARESProject[] getARESProjects() throws ARESModelException {
		IARESElement[] list = getChildrenOfType(ARES_PROJECT);
		IARESProject[] projects = new IARESProject[list.length];
		System.arraycopy(list, 0, projects, 0, list.length);
		return projects;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModel#move(com.hundsun.ares.studio.core.IARESElement[], com.hundsun.ares.studio.core.IARESElement, java.lang.String[], boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void move(IARESElement[] elements, IARESElement[] containers, String[] renamings, boolean replace, IProgressMonitor monitor) throws ARESModelException {
		MoveResourceElementsOperation op = new MoveResourceElementsOperation(elements, containers, true);
		op.runOperation(monitor);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModel#rename(com.hundsun.ares.studio.core.IARESElement[], com.hundsun.ares.studio.core.IARESElement[], java.lang.String[], boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void rename(IARESElement[] elements, String[] names, boolean replace, IProgressMonitor monitor) throws ARESModelException {
		if (elements.length > 0) {
			IARESElement element = elements[0];
			IARESElement parent = null;
			if (element instanceof IARESModule) {
				parent = ((IARESModule) element).getParentModule();
				if (parent == null) 
					parent = element.getParent();
			} else {
				parent = element.getParent();
			}
			RenameResourceElementsOperation op = new RenameResourceElementsOperation(elements, new IARESElement[] {parent}, names, true);
			op.runOperation(monitor);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
	 */
	public IResource getCorrespondingResource() throws ARESModelException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleMementoDelimiter()
	 */
	@Override
	protected char getHandleMementoDelimiter() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleFromMemento(java.lang.String, com.hundsun.ares.studio.internal.core.MementoTokenizer)
	 */
	@Override
	public IARESElement getHandleFromMemento(String token, MementoTokenizer memento) {
		switch (token.charAt(0)) {
		case AEM_ARESPROJECT:
			if (!memento.hasMoreTokens()) return this;
			String projectName = memento.nextToken();
			ARESElement project = (ARESElement)getARESProject(projectName);
			return project.getHandleFromMemento(memento);
		}
		return null;
	}

}
