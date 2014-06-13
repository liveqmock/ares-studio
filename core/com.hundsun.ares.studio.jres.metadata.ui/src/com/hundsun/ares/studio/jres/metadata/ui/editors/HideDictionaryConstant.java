package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.jres.metadata.ui.utils.DictoniaryUtils;
import com.hundsun.ares.studio.ui.control.CheckButtonAdaptor;
import com.hundsun.ares.studio.ui.editor.ExtensionFieldEditor;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

public class HideDictionaryConstant extends ExtensionFieldEditor {

	CheckButtonAdaptor hideDictConstant;
	private IARESProjectProperty properties;
	
	@Override
	public void setEditable(boolean editable) {
		if(hideDictConstant != null){
			hideDictConstant.setEditable(editable);
		}
	}

	@Override
	public void init(IARESProjectProperty properties) {
		this.properties = properties;
	}

	@Override
	public void createControls(FormToolkit toolkit,
			ImporveControlWithDitryStateContext context) {
		hideDictConstant = new CheckButtonAdaptor("隐藏数据字典常量",SWT.CHECK, context, DictoniaryUtils.HIDE_DICTIONARY_CONSTANT){
			@Override
			public void syncControl() {
				control.setSelection(shouldFixedHeight());
			}

			@Override
			public void syncModel() {
				properties.setValue(DictoniaryUtils.HIDE_DICTIONARY_CONSTANT,control.getSelection());
			}
		};
		hideDictConstant.syncControl();
		context.getDirtyStatus().setValue(false);
	}

	/**
	 * @return
	 */
	protected boolean shouldFixedHeight() {
		if(properties != null){
			return properties.getBoolean(DictoniaryUtils.HIDE_DICTIONARY_CONSTANT);
		}
		return false;
	}

	@Override
	public void refresh() {
		
	}

}
