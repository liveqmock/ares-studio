/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IOpenable;

/**
 * IOpenable元素的抽象实现
 * @author sundl
 */
public abstract class Openable extends ARESElement implements IOpenable {

	private static final Logger logger = Logger.getLogger(Openable.class.getName());
	
	public Openable(IARESElement parent) {
		super(parent);	
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.ARESElement#createElementInfo()
	 */
	@Override
	protected Object createElementInfo() {
		return new OpenableElementInfo();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.ARESElement#generateElementInfo(java.lang.Object)
	 */
	@Override
	protected void generateElementInfo(Object info) throws ARESModelException {
		openAncestors();
		IResource resource = getResource();
		IStatus status = validateExistence(resource);
		if (!status.isOK()) {
			throw newARESModelException(status);
		}
		
		OpenableElementInfo openableInfo = (OpenableElementInfo)info;
		boolean isStructureKnown = buildStructure(openableInfo);
		openableInfo.setStructureKnown(isStructureKnown);
	}
	
	protected abstract boolean buildStructure(OpenableElementInfo info) throws ARESModelException;
	
	protected void openAncestors() throws ARESModelException {
		IOpenable openable = getOpenableParent();
		if (openable != null && !openable.isOpen()) {
			openable.open(null);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IOpenable#isOpen()
	 */
	public boolean isOpen() {
		return ARESModelManager.getManager().getInfo(this) != null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IOpenable#open(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void open(IProgressMonitor monitor) throws ARESModelException {
		getElementInfo();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#exists()
	 */
	public boolean exists() {
		if (ARESModelManager.getManager().getInfo(this) != null) 
			return true;
		
		return validateExistence(getResource()).isOK();
	}
	
	public IOpenable getOpenable() {
		return this;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#isReadOnly()
	 */
	public boolean isReadOnly() {
		//MAXH增加判断资源是否是readonly
		if(getResource() != null){
			ResourceAttributes attr = getResource().getResourceAttributes();
			if (attr != null)
				return attr.isReadOnly();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#isStructureKnown()
	 */
	public boolean isStructureKnown() {
		try {
			OpenableElementInfo info = (OpenableElementInfo) getElementInfo();
			return info.isStructureKnown;
		} catch (ARESModelException e) {
			logger.log(Level.WARNING, "Error while invoke method isStructureKnown.", e);
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * Validates the existence of this openable. Returns a non ok status if it doesn't exist.
	 */
	protected abstract IStatus validateExistence(IResource underlyingResource);
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name: ");
		sb.append(getElementName());
		sb.append(", Type: ");
		sb.append(getClass().getSimpleName());
		return sb.toString();
	}
	
}
