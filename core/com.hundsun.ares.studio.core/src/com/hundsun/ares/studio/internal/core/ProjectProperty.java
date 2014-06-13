/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IProjectProperty;
import com.hundsun.ares.studio.core.model.converter.ProjectPropertyConverter;

/**
 * 
 * @author sundl
 */
public class ProjectProperty extends Openable implements IProjectProperty {

	private IFile file;
	
	ProjectProperty(IARESElement parent, IFile file) {
		super(parent);
		this.file = file;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementName()
	 */
	public String getElementName() {
		return "项目属性";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementType()
	 */
	public int getElementType() {
		return PROJECT_PROPERTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	public IResource getResource() {
		return file;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
	 */
	public IResource getCorrespondingResource() throws ARESModelException {
		return file;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	public IPath getPath() {
		return file.getFullPath();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#exists()
	 */
	public boolean exists() {
		return file.exists();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleFromMemento(java.lang.String, com.hundsun.ares.studio.internal.core.MementoTokenizer)
	 */
	@Override
	public IARESElement getHandleFromMemento(String token, MementoTokenizer memento) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleMementoDelimiter()
	 */
	@Override
	protected char getHandleMementoDelimiter() {
		return AEM_PROJECT_PRO;
	}

	@Override
	protected Object createElementInfo() {
		return new ProjectPropertyInfo();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.Openable#buildStructure(com.hundsun.ares.studio.internal.core.OpenableElementInfo)
	 */
	@Override
	protected boolean buildStructure(OpenableElementInfo info) throws ARESModelException {
		ProjectPropertyInfo property = (ProjectPropertyInfo) info;
		property.realProperty = new ARESProjectProperty();
		try {
			ProjectPropertyConverter.getInstance().read(file.getContents(), property.realProperty);
		} catch (CoreException e) {
			throw new ARESModelException(e);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.Openable#validateExistence(org.eclipse.core.resources.IResource)
	 */
	@Override
	protected IStatus validateExistence(IResource underlyingResource) {
		if (underlyingResource.exists())
			return Status.OK_STATUS;
		else
			return newDoesNotExistStatus();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IProjectProperty#getInfo()
	 */
	@Override
	public IARESProjectProperty getInfo() throws ARESModelException {
		ProjectPropertyInfo info = (ProjectPropertyInfo) getElementInfo();
		return info.realProperty;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IProjectProperty#save(com.hundsun.ares.studio.core.IARESProjectProperty)
	 */
	@Override
	public void save(IARESProjectProperty property) throws CoreException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ProjectPropertyConverter.getInstance().write(out, property);
			file.setContents(new ByteArrayInputStream(out.toByteArray()), true, false, null);
		} catch (Exception e) {
			throw new ARESModelException(e, 0);
		}
	}

}
