/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.celleditor;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.ui.control.formatter.AresNumberFormatter;

/**
 * 
 * @author maxh
 */
public class NumberTextCellEditor extends TextCellEditor{
	
    public NumberTextCellEditor(Composite parent) {
        super(parent);
    }
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createControl(Composite parent) {
		Control control = super.createControl(parent);
		FormattedText format = new FormattedText(text);
		AresNumberFormatter formatter = new AresNumberFormatter();
		format.setFormatter(formatter);
		return control;
	}

}
