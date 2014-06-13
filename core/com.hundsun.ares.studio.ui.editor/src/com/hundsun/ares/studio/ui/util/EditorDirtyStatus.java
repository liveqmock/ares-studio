/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 代表编辑器的脏状态<BR>
 * 即编辑器内存是否在修改后未保存<BR>
 * 本类可以监听属性变化
 * 
 * @author gongyf
 *
 */
public class EditorDirtyStatus {
	
	public static final String PROPERTY_VALUE = "Dirty";
	public static final String SET_METHOD = "set";
	
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	private PropertyChangeSupport listeners2 = new PropertyChangeSupport(this);
	
	private boolean value;

	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		listeners.addPropertyChangeListener(arg0);
	}

	public void firePropertyChange(String arg0, Object arg1, Object arg2) {
		listeners.firePropertyChange(arg0, arg1, arg2);
	}

	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		listeners.removePropertyChangeListener(arg0);
	}

	/**
	 * 这个监听器无论值是否变化都进行响应
	 * 
	 * @param arg0
	 */
	public void addPropertyChangeListener2(PropertyChangeListener arg0) {
		listeners2.addPropertyChangeListener(arg0);
	}

	public void firePropertyChange2(String arg0, Object arg1, Object arg2) {
		listeners2.firePropertyChange(arg0, arg1, arg2);
	}

	public void removePropertyChangeListener2(PropertyChangeListener arg0) {
		listeners2.removePropertyChangeListener(arg0);
	}
	
	
	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		firePropertyChange2(SET_METHOD, null, value);
		Boolean oldv = new Boolean(this.value);
		Boolean newv = new Boolean(value);
		if (this.value != value) {
			this.value = value;
			
			firePropertyChange(PROPERTY_VALUE, oldv , newv);
		}
	}
	
}
