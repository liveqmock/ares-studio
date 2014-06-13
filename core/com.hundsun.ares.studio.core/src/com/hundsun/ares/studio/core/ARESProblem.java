/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;

import com.hundsun.ares.studio.core.builder.IAresMarkers;

/**
 * 问题对象
 * @author sundl
 */
public class ARESProblem implements IARESProblem{
	
	private Map<String, Object> attributes = new HashMap<String, Object>();
	private String type;

	public static IARESProblem createError() {
		return createError(IAresMarkers.MARKER_ID);
	}
	
	public static IARESProblem createError(String type) {
		ARESProblem problem = new ARESProblem(type);
		problem.setError(true);
		return problem;
	}
	
	public static IARESProblem createWaring() {
		return createWarning(IAresMarkers.MARKER_ID);
	}
	
	public static IARESProblem createWarning(String type) {
		ARESProblem problem = new ARESProblem(type);
		problem.setWaring(true);
		return problem;
	}
	
	public static IARESProblem createFrom(IStatus status) {
		IARESProblem problem = null;
		if (status.getSeverity() == IStatus.ERROR) {
			problem = createError();
		} else if (status.getSeverity() == IStatus.WARNING) {
			problem = createWaring();
		}
		return problem;
	}
	
	public ARESProblem() {}
	
	public ARESProblem(String type) {
		this.type = type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String attributeName) {
		return attributes.get(attributeName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#getAttribute(java.lang.String, int)
	 */
	public int getAttribute(String attributeName, int defaultValue) {
		Object obj = attributes.get(attributeName);
		if (obj instanceof Integer) {
			return ((Integer)obj).intValue();
		} else if (obj instanceof String) {
			try {
				return Integer.parseInt(String.valueOf(obj));
			} catch (Exception e) {
				// do nothing
			}		
		}
		return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#getAttribute(java.lang.String, java.lang.String)
	 */
	public String getAttribute(String attributeName, String defaultValue) {
		Object obj = attributes.get(attributeName);
		if (obj != null)
			return String.valueOf(obj);
		return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#getAttribute(java.lang.String, boolean)
	 */
	public boolean getAttribute(String attributeName, boolean defaultValue) {
		Object obj = attributes.get(attributeName);
		if (obj instanceof Boolean) {
			return ((Boolean)obj).booleanValue();
		} else if (obj instanceof String) {
			try {
				return Boolean.parseBoolean((String)obj);
			} catch (Exception e) {
				// do nothing
			}		
		}
		return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#getAttributes()
	 */
	public Map getAttributes() {
		return attributes;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#getAttributes(java.lang.String[])
	 */
	public Object[] getAttributes(String[] attributeNames) {
		Object[] values = new Object[attributeNames.length];
		for (int i = 0; i < attributeNames.length; i++) {
			values[i] = attributes.get(attributeNames[i]);
		}
		return values;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#getLocation()
	 */
	public String getLocation() {
		return getAttribute(IMarker.LOCATION, "");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#getMessage()
	 */
	public String getMessage() {
		return getAttribute(IMarker.MESSAGE, "");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#getType()
	 */
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#isError()
	 */
	public boolean isError() {
		int severity = getAttribute(IMarker.SEVERITY, 0);
		return severity == IMarker.SEVERITY_ERROR;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#isWarning()
	 */
	public boolean isWarning() {
		int severity = getAttribute(IMarker.SEVERITY, 0);
		return severity == IMarker.SEVERITY_WARNING;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#setAttribute(java.lang.String, int)
	 */
	public void setAttribute(String attributeName, int value) {
		attributes.put(attributeName, value);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#setAttribute(java.lang.String, java.lang.Object)
	 */
	public void setAttribute(String attributeName, Object value) {
		attributes.put(attributeName, value);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#setAttribute(java.lang.String, boolean)
	 */
	public void setAttribute(String attributeName, boolean value) {
		attributes.put(attributeName, value);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#setAttributes(java.lang.String[], java.lang.Object[])
	 */
	public void setAttributes(String[] attributeNames, Object[] values) {
		if (attributeNames.length == values.length) {
			for (int i = 0; i < attributeNames.length; i++) {
				attributes.put(attributeNames[i], values[i]);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#setAttributes(java.util.Map)
	 */
	public void setAttributes(Map attributes) {
		attributes.putAll(attributes);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#setError(boolean)
	 */
	public void setError(boolean isError) {
		if (isError) {
			setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
		} else {
			setAttribute(IMarker.SEVERITY, null);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#setLocation(int)
	 */
	public void setLocation(String location) {
		setAttribute(IMarker.LOCATION, location);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#setMessage(java.lang.String)
	 */
	public void setMessage(String message) {
		setAttribute(IMarker.MESSAGE, message);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProblem#setWaring(boolean)
	 */
	public void setWaring(boolean isWarning) {
		if (isWarning) {
			setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
		} else {
			setAttribute(IMarker.SEVERITY, null);
		}
	}

}
