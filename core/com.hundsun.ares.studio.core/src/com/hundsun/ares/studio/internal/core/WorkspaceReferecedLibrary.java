/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IResPathEntry;

/**
 * 工作区间内的引用资源包（项目下的文件）
 * 
 * @author sundl
 */
public class WorkspaceReferecedLibrary extends ReferencedLibrary {

	private IFile file;

	public WorkspaceReferecedLibrary(IARESElement parent, IFile file, IResPathEntry entry) {
		super(parent, entry);
		this.file = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	public IPath getPath() {
		return file.getFullPath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.internal.core.ReferencedLibrary#getZipFile()
	 */
	@Override
	protected ZipFile getZipFile() throws ZipException, IOException {
		return new ZipFile(file.getLocation().toOSString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.devtool.v2.internal.core.Openable#validateExistence(
	 * org.eclipse.core.resources.IResource)
	 */
	@Override
	protected IStatus validateExistence(IResource underlyingResource) {
		if (onResPath() && file.exists())
			return ARESModelStatus.VERIFIED_OK;

		return newDoesNotExistStatus();
	}

	private boolean onResPath() {
		for (IResPathEntry entry : getARESProject().getRawResPath()) {
			if (entry.getPath().equals(file.getFullPath()))
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
	 */
	public IResource getCorrespondingResource() throws ARESModelException {
		return file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementName()
	 */
	public String getElementName() {
		return file.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	public IResource getResource() {
		return file;
	}

	public boolean isExternal() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getDescriptionStr()
	 */
	@Override
	public String getDescriptionStr() {
		return String.format("引用资源包 %s", getPath());
	}

}
