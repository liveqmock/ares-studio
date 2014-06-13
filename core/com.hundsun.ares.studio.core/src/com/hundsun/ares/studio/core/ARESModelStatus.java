/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.hundsun.ares.studio.internal.core.ARESElement;

/**
 * IARESModelStatus
 * @see IJavaModelStatus
 * @see JavaModelStatus
 * @author sundl
 */
public class ARESModelStatus extends Status implements IARESModelStatus, IARESModelStatusConstants {

	/**
	 * The elements related to the failure, or <code>null</code>
	 * if no elements are involved.
	 */
	protected IARESElement[] elements = new IARESElement[0];
	/**
	 * The path related to the failure, or <code>null</code>
	 * if no path is involved.
	 */
	protected IPath path;
	
	/**
	 * The <code>String</code> related to the failure, or <code>null</code>
	 * if no <code>String</code> is involved.
	 */
	protected String string;
	
	/**
	 * Empty children
	 */
	protected final static IStatus[] NO_CHILDREN = new IStatus[] {};
	protected IStatus[] children= NO_CHILDREN;

	/**
	 * Singleton OK object
	 */
	public static final IARESModelStatus VERIFIED_OK = new ARESModelStatus(OK, OK, "OK");

	private static String DEFAULT_MSG = "ARESModelStatus";
	
	protected ARESModelStatus() {
		super(ERROR, ARESCore.PLUGIN_ID, 0, DEFAULT_MSG, null);
	}
	
	public ARESModelStatus(int code) {
		super(ERROR, ARESCore.PLUGIN_ID, code, DEFAULT_MSG, null);
		this.elements = ARESElement.NO_CHILDREN;
	}
	
	public ARESModelStatus(int code, String string) {
		this(ERROR, code, string);
	}
	
	public ARESModelStatus(int code, IARESElement[] elements) {
		super(ERROR, ARESCore.PLUGIN_ID, code, DEFAULT_MSG, null);
		this.elements = elements;
		this.path = null;
	}
	
	public ARESModelStatus(int severity, int code, String string) {
		super(severity, ARESCore.PLUGIN_ID, code, DEFAULT_MSG, null);
		this.elements = ARESElement.NO_CHILDREN;
		this.path = null;
		this.string = string;
	}
	
	public ARESModelStatus(int code, Throwable throwable) {
		super(ERROR, ARESCore.PLUGIN_ID, code, DEFAULT_MSG, throwable);
		this.elements = ARESElement.NO_CHILDREN;
	}
	
	public ARESModelStatus(int code, IPath path) {
		super(ERROR, ARESCore.PLUGIN_ID, code, DEFAULT_MSG, null); //$NON-NLS-1$
		this.elements= ARESElement.NO_CHILDREN;
		this.path= path;
	}

	public ARESModelStatus(int code, IARESElement element) {
		this(code, new IARESElement[]{element});
	}
	
	public ARESModelStatus(int code, IARESElement element, String string) {
		this(code, new IARESElement[]{element});
		this.string = string;
	}

	public ARESModelStatus(int code, IARESElement element, IPath path) {
		this(code, new IARESElement[]{element});
		this.path = path;
	}

	public ARESModelStatus(int code, IARESElement element, IPath path, String string) {
		this(code, new IARESElement[]{element});
		this.path = path;
		this.string = string;
	}

    public ARESModelStatus(int severity, int code, IARESElement element, IPath path, String msg) {
    	super(severity, ARESCore.PLUGIN_ID, code, DEFAULT_MSG, null); //$NON-NLS-1$
    	this.elements= new IARESElement[]{element};
    	this.path = path;
    	this.string = msg;
    }

	public ARESModelStatus(CoreException coreException) {
		super(ERROR, ARESCore.PLUGIN_ID, CORE_EXCEPTION, DEFAULT_MSG, coreException); //$NON-NLS-1$
		this.elements= ARESElement.NO_CHILDREN;
	}
	
	protected int getBits() {
		int severity = 1 << (getCode() % 100 / 33);
		int category = 1 << ((getCode() / 100) + 3);
		return severity | category;
	}
	
	public String getMessage() {
		Throwable exception = getException();
		if (exception == null) {
			String msg = "Message to be done, Code is: " + getCode();
			if (elements != null) {
				msg = msg + "\nElements: \n" + getElementsStr();
			}
			return msg;
		} else {
			String message = exception.getMessage();
			if (message != null) {
				return message;
			} else {
				return exception.toString();
			}
		}
	}
	
	private String getElementsStr() {
		if (elements == null || elements.length == 0)
			return StringUtils.EMPTY;
		String str = StringUtils.EMPTY;
		for (int i = 0; i< elements.length; i++) {
			str = str + elements[i].toString();
		}
		return str;
	}
	
	/**
	 * @see IStatus
	 */
	public IStatus[] getChildren() {
		return this.children;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModelStatus#getElements()
	 */
	public IARESElement[] getElements() {
		return elements;
	}
	
	/**
	 * @see IStatus#getSeverity()
	 */
	public int getSeverity() {
		if (this.children == NO_CHILDREN) return super.getSeverity();
		int severity = -1;
		for (int i = 0, max = this.children.length; i < max; i++) {
			int childrenSeverity = this.children[i].getSeverity();
			if (childrenSeverity > severity) {
				severity = childrenSeverity;
			}
		}
		return severity;
	}
	/**
	 * @see IJavaModelStatus#isDoesNotExist()
	 */
	public boolean isDoesNotExist() {
		int code = getCode();
		return code == ELEMENT_DOES_NOT_EXIST || code == ELEMENT_NOT_ON_CLASSPATH;
	}
	/**
	 * @see IStatus#isMultiStatus()
	 */
	public boolean isMultiStatus() {
		return this.children != NO_CHILDREN;
	}
	/**
	 * @see IStatus#isOK()
	 */
	public boolean isOK() {
		return getCode() == OK;
	}
	
	/**
	 * @see IStatus#matches(int)
	 */
	public boolean matches(int mask) {
		if (! isMultiStatus()) {
			return matches(this, mask);
		} else {
			for (int i = 0, max = this.children.length; i < max; i++) {
				if (matches((ARESModelStatus) this.children[i], mask))
					return true;
			}
			return false;
		}
	}
	
	/**
	 * Helper for matches(int).
	 */
	protected boolean matches(ARESModelStatus status, int mask) {
		int severityMask = mask & 0x7;
		int categoryMask = mask & ~0x7;
		int bits = status.getBits();
		return ((severityMask == 0) || (bits & severityMask) != 0) && ((categoryMask == 0) || (bits & categoryMask) != 0);
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModelStatus#getPath()
	 */
	public IPath getPath() {
		return path;
	}
	
	public static IARESModelStatus newMultistatus(IARESModelStatus[] children) {
		ARESModelStatus ams = new ARESModelStatus();
		ams.children = children;
		return ams;
	}
	
	/**
	 * Returns a printable representation of this exception for debugging
	 * purposes.
	 */
	public String toString() {
		if (this == VERIFIED_OK){
			return "ARESModelStatus[OK]"; //$NON-NLS-1$
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("ARES Model Status ["); //$NON-NLS-1$
		buffer.append(getMessage());
		buffer.append("]"); //$NON-NLS-1$
		return buffer.toString();
	}
}
