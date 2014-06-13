/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.wizard;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;

/**
 * 统一向导第一向导页对应的LabelProvider
 * @author liaogc
 *
 */
public class ScriptGenInteWizardLabelProvider extends LabelProvider implements
		IColorProvider, IBaseLabelProvider {

	public String getText(Object element) {
		if ((element instanceof ScriptGenInteWizardModel)) {
			ScriptGenInteWizardModel tn = (ScriptGenInteWizardModel) element;
			return tn.getScriptDesc();
		}
		return "error";
	}

	public Color getBackground(Object element) {
		return null;
	}

	public Color getForeground(Object element) {
		return null;
	}

}

