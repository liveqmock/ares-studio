/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 * @author liaogc
 */
public class FormToolkitEx extends FormToolkit{

	/**
	 * @param display
	 */
	public FormToolkitEx(Display display) {
		super(display);
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.widgets.FormToolkit#createText(org.eclipse.swt.widgets.Composite, java.lang.String)
	 */
	@Override
	public Text createText(Composite parent, String value) {
		 Text text  = super.createText(parent, value);
		 text.setFont(JFaceResources.getTextFont());
		 return text;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.widgets.FormToolkit#createText(org.eclipse.swt.widgets.Composite, java.lang.String, int)
	 */
	@Override
	public Text createText(Composite parent, String value, int style) {
		Text text  = super.createText(parent, value, style);
		text.setFont(JFaceResources.getTextFont());
		return text;
	}

}
