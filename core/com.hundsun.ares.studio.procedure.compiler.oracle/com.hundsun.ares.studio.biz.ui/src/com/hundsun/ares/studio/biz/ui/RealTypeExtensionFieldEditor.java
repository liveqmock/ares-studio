package com.hundsun.ares.studio.biz.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.jres.metadata.ui.Language;
import com.hundsun.ares.studio.jres.metadata.ui.LanguageRegister;
import com.hundsun.ares.studio.ui.editor.SingleExtensionFieldEditor;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

public class RealTypeExtensionFieldEditor extends SingleExtensionFieldEditor {

	private CCombo combo;
	
	public void init(IARESProjectProperty properties) {
		super.init(properties);
		setId(BizUIConstants.REAL_TYPE_TO_DISPLAY);
	}
	
	@Override
	public void setEditable(boolean editable) {
		combo.setEditable(false);
	}

	@Override
	public void createControls(FormToolkit toolkit, final ImporveControlWithDitryStateContext context) {
		toolkit.createLabel(context.getParent(), "接口参数列表中显示的真实类型：");
		
		combo = new CCombo(context.getParent(), SWT.READ_ONLY | SWT.BORDER);
		toolkit.adapt(combo, true, true);
		Language[] languages = LanguageRegister.getInstance().getRegisteredLanguages();
		List<String> items = new ArrayList<String>();
		for (Language lang : languages) {
			items.add(lang.getName());
		}
		combo.setItems(items.toArray(new String[0]));
		{
			String currentLang = properties.getString(BizUIConstants.REAL_TYPE_TO_DISPLAY);
			if (currentLang != null) {
				int index = items.indexOf(currentLang);
				if (index != -1) {
					combo.select(index);
				}
			}
		}
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectedLang = combo.getText();
				String currentLang = properties.getString(BizUIConstants.REAL_TYPE_TO_DISPLAY);
				if (!StringUtils.equals(selectedLang, currentLang)) {
					properties.setValue(BizUIConstants.REAL_TYPE_TO_DISPLAY, selectedLang);
					context.getDirtyStatus().setValue(true);
				}
			}
		});
	}

	@Override
	public void refresh() {
		
	}

}
