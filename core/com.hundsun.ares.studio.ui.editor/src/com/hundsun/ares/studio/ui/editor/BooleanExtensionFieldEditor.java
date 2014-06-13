/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

/**
 * 布尔值编辑器
 * @author sundl
 */
public class BooleanExtensionFieldEditor extends SingleExtensionFieldEditor {

	private Button checkButton;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.ExtensionFieldEditor#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	public void createControls(FormToolkit toolkit, final ImporveControlWithDitryStateContext context) {
		checkButton = toolkit.createButton(context.getParent(), getName(), SWT.CHECK);
		GridDataFactory.fillDefaults().span(2, 1).applyTo(checkButton);
		checkButton.addSelectionListener(new SelectionAdapter() {

			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				properties.setValue(getId(), checkButton.getSelection());
				context.getDirtyStatus().setValue(true);
			}
			
		});
		if (properties.getBoolean(getId(), false)) {
			checkButton.setSelection(true);
		} else {
			checkButton.setSelection(false);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.ExtensionFieldEditor#refresh()
	 */
	@Override
	public void refresh() {
		if (properties.getBoolean(getId(), false)) {
			checkButton.setSelection(true);
		} else {
			checkButton.setSelection(false);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.control.IEditable#setEditable(boolean)
	 */
	public void setEditable(boolean editable) {
		checkButton.setEnabled(editable);
	}

}
