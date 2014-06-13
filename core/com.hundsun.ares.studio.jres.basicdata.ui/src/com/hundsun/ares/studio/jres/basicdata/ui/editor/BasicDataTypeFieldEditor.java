package com.hundsun.ares.studio.jres.basicdata.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.extensionpoint.EpackageFactoryItem;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.extensionpoint.EpackageFactoryManager;
import com.hundsun.ares.studio.ui.control.ComboAdaptor;
import com.hundsun.ares.studio.ui.editor.ExtensionFieldEditor;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

public class BasicDataTypeFieldEditor extends ExtensionFieldEditor {
	
	private IARESProjectProperty properties;
	
	ComboAdaptor comboAdapter;
	
	//基础数据类型工厂 
	List<EpackageFactoryItem>factoryList = EpackageFactoryManager.getInstance().getFactoryList();
	
	List<String> nameList = new ArrayList<String>();
	
	@Override
	public void setEditable(boolean editable) {
		if (comboAdapter != null){
			comboAdapter.setEditable(editable);
		}
	}

	@Override
	public void init(IARESProjectProperty properties) {
		this.properties = properties;
		nameList.clear();
		
		for(EpackageFactoryItem factoryItem : factoryList){
			nameList.add(factoryItem.name);
		}
	}

	@Override
	public void createControls(FormToolkit toolkit,
			ImporveControlWithDitryStateContext context) {
		comboAdapter = new ComboAdaptor("基础数据类型", SWT.READ_ONLY, context, nameList.toArray(new String[0])){
			@Override
			public void syncControl() {
				String content = StringUtils.defaultString(properties.getString(IBasicDataEpacakgeConstant.Property_Basic_Data_type_ID));
				control.select(getIndex(content));
			}

			@Override
			public void syncModel() {
				properties.setValue(IBasicDataEpacakgeConstant.Property_Basic_Data_type_ID,
						factoryList.get(control.getSelectionIndex()).id);
			}
		};
		comboAdapter.syncControl();
		context.getDirtyStatus().setValue(false);
	}
	
	/**
	 * 获取内容索引
	 * @param content
	 * @return
	 */
	private int getIndex(String content){
		for(int i = 0; i < factoryList.size();i++){
			if(StringUtils.equals(factoryList.get(i).id, content)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public void refresh() {
		
	}

}
