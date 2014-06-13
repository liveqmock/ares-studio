/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

/**
 * @author gongyf
 *
 */
public class CheckBoxLabelProvider extends LabelProvider {
	
	private static String IMG_CHECK = "checked.gif";
	private static String IMG_UNCHECK = "unchecked.gif";
	
	
	@Override
	public Image getImage(Object element) {
		boolean value = false;
		
		if (element instanceof String) {
			value = Boolean.valueOf((String) element);
		} else if (element instanceof Boolean) {
			value = ((Boolean) element).booleanValue();
		}
		
		if (value) {
			return ARESEditorPlugin.getImage(IMG_CHECK);
		} else {
			return ARESEditorPlugin.getImage(IMG_UNCHECK);
		}
	}
	
	@Override
	public String getText(Object element) {
		return StringUtils.EMPTY;
	}
}
