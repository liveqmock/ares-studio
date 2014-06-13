/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.celleditor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

public class BooleanCheckboxCellEditor extends CheckboxCellEditor implements ISprecialCellEditor {
	String showText = "";
	
	public BooleanCheckboxCellEditor(Composite parent, int style) {
		super(parent,style);
	}
	
	public BooleanCheckboxCellEditor(Composite parent, int style, String showText) {
		super(parent,style);
		this.showText = showText;
	}
	
	public String getShowText() {
		return showText;
	}
	
	public void setShowText(String showText) {
		this.showText = showText;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.ui.celleditor.ISprecialCellEditor#getImage(java.lang.Object)
	 */
	public Image getImage(Object value){
		if(value.toString().equalsIgnoreCase("true")){
			return ARESEditorPlugin.getImage("checked.gif");
		}else{
			return ARESEditorPlugin.getImage("unchecked.gif");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.ui.celleditor.ISprecialCellEditor#getText(java.lang.Object)
	 */
	public String getText(Object value){
		return showText;
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
			return value;
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
	
	public Object getRealGetValue(Object value) {
		if(value instanceof String){
			return Boolean.valueOf((String)value);
		}
		return value;
	}

	public Object getRealSetValue(Object value) {
		return value;
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
