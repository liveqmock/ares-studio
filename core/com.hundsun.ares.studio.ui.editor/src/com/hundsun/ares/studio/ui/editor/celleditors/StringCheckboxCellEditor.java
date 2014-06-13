/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.celleditors;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

/**
 * @author gongyf
 *
 */
public class StringCheckboxCellEditor extends CheckboxCellEditor {
	
	
	
	/**
	 * 
	 */
	public StringCheckboxCellEditor() {
		super();
	}

	/**
	 * @param parent
	 * @param style
	 */
	public StringCheckboxCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param parent
	 */
	public StringCheckboxCellEditor(Composite parent) {
		super(parent);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CheckboxCellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		Object value = super.doGetValue();
		
		if (value == null) {
			return StringUtils.EMPTY;
		} else {
			return value.toString();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CheckboxCellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(Object value) {
		if (value instanceof String) {
			value = Boolean.valueOf((String)value);
		}
		super.doSetValue(value);
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CheckboxCellEditor#activate(org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent)
	 */
	@Override
	public void activate(ColumnViewerEditorActivationEvent activationEvent) {
		// 鼠标单击时，只有单击左键才触发激活事件，屏蔽鼠标中键和右键事件
		if (activationEvent.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION) {
			MouseEvent event = (MouseEvent) activationEvent.sourceEvent;
			if (event.button != 1) {
				return;
			}
		}
		super.activate(activationEvent);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CheckboxCellEditor#activate()
	 */
	@Override
	public void activate() {
		if ((getStyle() & SWT.READ_ONLY) == 0) {
			super.activate();
		}
	}
}
