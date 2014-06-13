/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IResPathEntry;

/**
 * 外部文件的引用资源包
 * @author sundl
 */
public class ExternalFileReferencedLibrary extends ReferencedLibrary {

	private File file;
	
	public ExternalFileReferencedLibrary(IARESElement parent, File file, IResPathEntry entry) {
		super(parent, entry);
		this.file = file;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementName()
	 */
	public String getElementName() {
		return file.getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	public IResource getResource() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
	 */
	public IResource getCorrespondingResource() throws ARESModelException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	public IPath getPath() {
		return new Path(file.getAbsolutePath());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ReferencedLibrary#getZipFile()
	 */
	@Override
	protected ZipFile getZipFile() throws ZipException, IOException {
		return new ZipFile(file);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.Openable#validateExistence(org.eclipse.core.resources.IResource)
	 */
	@Override
	protected IStatus validateExistence(IResource underlyingResource) {
		if (file.isFile() && file.exists()) 
			return ARESModelStatus.VERIFIED_OK;
		
		return newDoesNotExistStatus();
	}

	public boolean isExternal() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getDescriptionStr()
	 */
	@Override
	public String getDescriptionStr() {
		return String.format("外部引用资源包: %s(%s)", file.getName(), file.getAbsolutePath());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getRequiredBundles()
	 */
	@Override
	public IARESBundle[] getRequiredBundles() {
		return null;
	}

}
