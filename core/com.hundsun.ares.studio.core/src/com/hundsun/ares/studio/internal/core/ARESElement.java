/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.PlatformObject;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModel;
import com.hundsun.ares.studio.core.IARESModelStatus;
import com.hundsun.ares.studio.core.IARESModelStatusConstants;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IOpenable;
import com.hundsun.ares.studio.core.util.Util;

/**
 * ARES Element的抽象实现。
 * @author sundl
 */
public abstract class ARESElement extends PlatformObject implements IARESElement{

	private static Logger logger = Logger.getLogger(ARESElement.class);
	
	public static IARESElement[] NO_CHILDREN = new IARESElement[0];
	
	public static final char AEM_ESCAPE = '\\';
	public static final char AEM_ARESPROJECT = '=';
	public static final char AEM_MODULEROOT = '/';
	public static final char AEM_MODULE = '<';
	public static final char AEM_RESOURCE = '{';
	public static final char AEM_LIB = '#';	
	public static final char AEM_PROJECT_PRO = '@';
	
	protected IARESElement parent;
	
	ARESElement(IARESElement parent) {
		this.parent = parent;
	}
	
	public IARESElement[] getChildren() throws ARESModelException {
		Object elementInfo = getElementInfo();
		
		if(elementInfo instanceof ARESElementInfo) {
			IARESElement[] children = ((ARESElementInfo)elementInfo).getChildren();
			return children;
		}
		return NO_CHILDREN;
	}
	
	public Object getElementInfo() throws ARESModelException {
		ARESModelManager manager = ARESModelManager.getManager();
		// try the cache...
		Object info = manager.getInfo(this);
		// if info is found in the cache, just return the info
		if (info != null) return info;
		// else create and build the info
	    info = createElementInfo();
		generateElementInfo(info);
		// put the info into the cache
		manager.putInfo(this, info);
		return info;
	}
		
	protected abstract Object createElementInfo();
	
	protected abstract void generateElementInfo(Object info) throws ARESModelException;
	
	public abstract IARESElement getHandleFromMemento(String token, MementoTokenizer memento);
	
	/*
	 * Creates a Java element handle from the given memento.
	 * The given working copy owner is used only for compilation unit handles.
	 */
	public IARESElement getHandleFromMemento(MementoTokenizer memento) {
		if (!memento.hasMoreTokens()) return this;
		String token = memento.nextToken();
		return getHandleFromMemento(token, memento);
	}
	
	public String getHandleIdentifier(){
		StringBuffer buff = new StringBuffer();
		getHandleMemento(buff);
		return buff.toString();
	}
	
	protected void getHandleMemento(StringBuffer buff) {
		((ARESElement)getParent()).getHandleMemento(buff);
		buff.append(getHandleMementoDelimiter());
		escapeMementoName(buff, getElementName());
	}

	protected void escapeMementoName(StringBuffer buffer, String mementoName) {
		for (int i = 0, length = mementoName.length(); i < length; i++) {
			char character = mementoName.charAt(i);
			switch (character) {
				case AEM_ESCAPE:
				case AEM_ARESPROJECT:
				case AEM_MODULEROOT:
				case AEM_MODULE:
				case AEM_RESOURCE:
				case AEM_LIB:
				case AEM_PROJECT_PRO:
					buffer.append(AEM_ESCAPE);
			}
			buffer.append(character);
		}
	}
	
	/**
	 * Returns the <code>char</code> that marks the start of this handles
	 * contribution to a memento.
	 */
	protected abstract char getHandleMementoDelimiter();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IOpenable#close()
	 */
	public void close() throws ARESModelException {
		ARESModelManager.getManager().removeInfoAndChildren(this);
	}

	public IARESElement[] getChildrenOfType(int type) throws ARESModelException {
		IARESElement[] children = getChildren();		        
		ArrayList<IARESElement> childrenOfType = new ArrayList<IARESElement>();
		for(IARESElement e : children) {
			if(e.getElementType() == type) {
				childrenOfType.add(e);
			}
		}
		return childrenOfType.toArray(new IARESElement[childrenOfType.size()]);
	}
	
	/*
	 * @see IParent
	 */
	public boolean hasChildren() throws ARESModelException {
		Object elementInfo = ARESModelManager.getManager().getInfo(this);
		if (elementInfo instanceof ARESElementInfo) {
			return ((ARESElementInfo)elementInfo).getChildren().length > 0;
		} else {
			return true;
		}
	}
	
	public IARESElement getParent() {
		return parent;
	}
	
	/*
	 * @see IARESElement
	 */
	public IARESElement getAncestor(int ancestorType) {
		IARESElement element = this;
		while (element != null) {
			if (element.getElementType() == ancestorType)  return element;
			element= element.getParent();
		}
		return null;
	}
	
	public IARESModel getARESModel() {
		IARESElement current = this;
		do {
			if (current instanceof IARESModel) return (IARESModel) current;
		} while ((current = current.getParent()) != null);
		return null;
	}

	
	public IARESProject getARESProject() {
		if(this instanceof IARESProject) {
			return (IARESProject)this;
		}
		
		IARESElement parent = getParent();
		while(parent != null) {
			if(parent instanceof IARESProject) {
				break;
			} else {
				parent = parent.getParent();
			}
		}
		return (IARESProject)parent;
	}
		
	public IOpenable getOpenable() {
		return getOpenableParent();
	}
	
	public IOpenable getOpenableParent() {
		return (IOpenable)parent;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.acide.core.IACElement#isReadOnly()
	 */
	public boolean isReadOnly() {
		IResource resource = getResource();
		if(resource == null) {
			return true;
		}
		
		return resource.getResourceAttributes().isReadOnly();
	}
	
	public ARESModelException newNotPresentException() {
		return new ARESModelException(newDoesNotExistStatus());
	}
	
	protected ARESModelStatus newDoesNotExistStatus() {
		return new ARESModelStatus(IARESModelStatusConstants.ELEMENT_DOES_NOT_EXIST, this);
	}
	
	public ARESModelException newARESModelException(IStatus status) {
		if (status instanceof IARESModelStatus)
			return new ARESModelException((IARESModelStatus) status);
		else
			return new ARESModelException(new ARESModelStatus(status.getSeverity(), status.getCode(), status.getMessage()));
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(this.parent == null) return super.equals(o);
		if(! (o instanceof ARESElement)) return false;
		
		ARESElement other = (ARESElement)o;
		return getElementName().equals(other.getElementName()) &&
			this.parent.equals(other.parent);
	}
	
	@Override
	public int hashCode() {
		if (this.parent == null) return super.hashCode();
		return Util.combineHashCodes(getElementName().hashCode(), this.parent.hashCode());
	}
	
}
