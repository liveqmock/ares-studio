/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.ui.control.IEditable;
import com.hundsun.ares.studio.ui.control.TextAdaptor;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

/**
 * 字符类型的扩展字段的实现
 * @author sundl
 */
public class TextExtensionFieldEditor extends SingleExtensionFieldEditor implements IEditable{

	private static final String COLON = ":";
	protected TextAdaptor textAdapter;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.ExtensionField#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	public void createControls(FormToolkit toolkit, ImporveControlWithDitryStateContext context) {
//		parent.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 2));
//		toolkit.createLabel(parent, getName() + COLON);
//		text = toolkit.createText(parent, properties.getString(getId(), ""));
//		text.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
//		text.addModifyListener(new ModifyListener() {			
//			public void modifyText(ModifyEvent e) {
//				properties.setValue(getId(), text.getText());
//				
//			}
//		});
		textAdapter = new TextAdaptor(getName() + COLON, SWT.BORDER, context) {

			@Override
			public void syncControl() {
				text.setText(StringUtils.defaultString(properties.getString(getId())));
			}

			@Override
			public void syncModel() {
				properties.setValue(getId(), text.getText());
			}
			
		};
		textAdapter.syncControl();
		context.getDirtyStatus().setValue(false);
	}
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.ExtensionField#refresh()
	 */
	@Override
	public void refresh() {
//		text.setText(properties.getString(getId(), ""));
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.control.IEditable#setEditable(boolean)
	 */
	public void setEditable(boolean editable) {
		if (textAdapter != null)
			textAdapter.setEditable(editable);
	}
		
}

