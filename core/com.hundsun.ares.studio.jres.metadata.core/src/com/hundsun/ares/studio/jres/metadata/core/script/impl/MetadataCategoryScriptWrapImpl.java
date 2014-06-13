/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataCategoryScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * 
 * @author yanwj06282
 *
 */
public class MetadataCategoryScriptWrapImpl extends CommonScriptWrap<MetadataCategory> implements
		IMetadataCategoryScriptWrap {

	public MetadataCategoryScriptWrapImpl(MetadataCategory cate ,IARESResource resource ) {
		super(cate ,resource);
	}

	@Override
	public IMetadataItemScriptWrap[] getItems() {
		List<IMetadataItemScriptWrap> items = new ArrayList<IMetadataItemScriptWrap>();
		for(MetadataItem item : getOriginalInfo().getItems()){
			if (item instanceof DictionaryType) {
				items.add(new DictScriptWrapImpl((DictionaryType) item,resource));
			}else if (item instanceof ConstantItem){
				items.add(new ConstantItemScriptWrapImpl((ConstantItem) item,resource));
			}else if (item instanceof MenuItem){
				items.add(new MenuItemScriptWrapImpl((MenuItem) item, resource));
			}else if (item instanceof BusinessDataType){
				items.add(new BusDataTypeItemScriptWrapImpl((BusinessDataType) item,resource));
			}else if (item instanceof ErrorNoItem){
				items.add(new ErrorItemScriptWrapImpl((ErrorNoItem) item,resource));
			}else {
				items.add(new MetadataItemScriptWrapImpl(item, resource));
			}
		}
		return items.toArray(new IMetadataItemScriptWrap[0]);
	}
	
	public IMetadataCategoryScriptWrap[] getCategories (){
		List<IMetadataCategoryScriptWrap> cates = new ArrayList<IMetadataCategoryScriptWrap>();
		List<MetadataCategory> metaList =  getOriginalInfo().getChildren();
		for(MetadataCategory cate : metaList){
			cates.add(new MetadataCategoryScriptWrapImpl(cate ,resource));
		}
		return cates.toArray(new IMetadataCategoryScriptWrap[0]);
	}
	
	@Override
	public String getName() {
		return getOriginalInfo().getName();
	}
	
}
