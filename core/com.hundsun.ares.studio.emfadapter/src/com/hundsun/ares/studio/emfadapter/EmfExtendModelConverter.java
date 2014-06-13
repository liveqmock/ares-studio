package com.hundsun.ares.studio.emfadapter;

import org.dom4j.Element;

import com.hundsun.ares.studio.core.model.extendable.ExtendModelConverterManager;
import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;
import com.hundsun.ares.studio.core.util.PersistentUtil;

public class EmfExtendModelConverter {
	EmfExtendModelConverter() {
	}
	static EmfExtendModelConverter converter;
	
	public static EmfExtendModelConverter getDefault(){
		if(converter == null){
			converter = new EmfExtendModelConverter();
		}
		return converter;
	}
	
	//读取拓展MAP
	public void readExtendMap(IExtendAbleModel model,Element root){
		if(model.getMap() != null){
			ExtendModelConverterManager.getDefault().readExtendMap(model, root);
		}
	}
	
	//写入拓展MAP
	public String writeExtendMap(IExtendAbleModel model){
		Element root = PersistentUtil.createHSDocument().getRootElement();
		ExtendModelConverterManager.getDefault().writeExtendMap(model, root);
		if(root.element(ExtendModelConverterManager.MAP_STRING) != null){
			return root.element(ExtendModelConverterManager.MAP_STRING).asXML();
		}
		return "";
	}
}
