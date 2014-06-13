/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * 引用包里的模块
 * @author sundl
 */
public class ArchiveARESModule extends ARESModule implements IARESModule {

	public ArchiveARESModule(IARESElement parent, String[] names) {
		super(parent, names);	
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#buildStructure(com.hundsun.ares.devtool.v2.internal.core.OpenableElementInfo)
	 */
	@Override
	protected boolean buildStructure(OpenableElementInfo info) throws ARESModelException {
		ArchiveARESModuleRoot root = (ArchiveARESModuleRoot)getParent();
		ReferencedLibrary lib = (ReferencedLibrary)root.getParent();
		ReferencedLibraryInfo libInfo = (ReferencedLibraryInfo)lib.getElementInfo();
		List<IARESResource> moduleInfo = libInfo.moduleInfo.get(this);
		if (moduleInfo == null) {
			moduleInfo = new ArrayList<IARESResource>();
			libInfo.moduleInfo.put(this, moduleInfo);
		}
		info.setChildren(moduleInfo.toArray(new IARESElement[0]));
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.ARESElement#generateElementInfo(java.lang.Object)
	 */
	@Override
	protected void generateElementInfo(Object info) throws ARESModelException {
		openAncestors();
		IResource resource = getRoot().getResource();
		IStatus status = validateExistence(resource);
		if (!status.isOK()) {
			throw newARESModelException(status);
		}
		
		OpenableElementInfo openableInfo = (OpenableElementInfo)info;
		boolean isStructureKnown = buildStructure(openableInfo);
		openableInfo.setStructureKnown(isStructureKnown);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#validateExistence(org.eclipse.core.resources.IResource)
	 */
	@Override
	protected IStatus validateExistence(IResource underlyingResource) {
		ArchiveARESModuleRoot root = (ArchiveARESModuleRoot) getParent();
		return root.validateExistence(underlyingResource);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
	 */
	@Override
	public IResource getCorrespondingResource() throws ARESModelException {
		return null;
	}

	public IARESResource getARESResource(String name) {
		return new ArchiveARESResource(this, name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	@Override
	public IPath getPath() {
		return getParent().getPath();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	@Override
	public IResource getResource() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#copy(com.hundsun.ares.studio.core.IARESElement, java.lang.String, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void copy(IARESElement container, String rename, boolean replace,
			IProgressMonitor monitor) throws ARESModelException {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#delete(boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void delete(boolean force, IProgressMonitor monitor)
			throws ARESModelException {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#move(com.hundsun.ares.studio.core.IARESElement, java.lang.String, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void move(IARESElement container, String rename, boolean replace,
			IProgressMonitor monitor) throws ARESModelException {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#rename(java.lang.String, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void rename(String name, boolean replace, IProgressMonitor monitor)
			throws ARESModelException {
	}
	
	@Override
	public boolean isReadOnly() {
		return getRoot().isReadOnly();
	}
	
}
