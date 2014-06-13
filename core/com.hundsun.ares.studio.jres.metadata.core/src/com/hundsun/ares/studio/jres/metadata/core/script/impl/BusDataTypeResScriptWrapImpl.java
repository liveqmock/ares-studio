/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.script.api.metadata.IBizDataTypeItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class BusDataTypeResScriptWrapImpl extends MetadataResScriptWrapImpl {

	public BusDataTypeResScriptWrapImpl(BusinessDataTypeList dataType,
			IARESResource resource, boolean includeRequiredProjects) {
		super(dataType, resource, includeRequiredProjects);
	}

	@Override
	public BusinessDataTypeList getOriginalInfo() {
		return (BusinessDataTypeList) super.getOriginalInfo();
	}
	
	@Override
	public IBizDataTypeItemScriptWrap[] getItems() {
		List<IBizDataTypeItemScriptWrap> items = new ArrayList<IBizDataTypeItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getItems()
		for (IMetadataItemScriptWrap item : super.getItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			items.add(new BusDataTypeItemScriptWrapImpl((BusinessDataType) metadataItem.getOriginalInfo(), metadataItem.getResource()));
		}
		return items.toArray(new IBizDataTypeItemScriptWrap[items.size()]);
	}
	
	@Override
	public IBizDataTypeItemScriptWrap[] getNotCateItems() {
		List<IBizDataTypeItemScriptWrap> items = new ArrayList<IBizDataTypeItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getNotCateItems()
		for (IMetadataItemScriptWrap item : super.getNotCateItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			BusinessDataType bizDataType = (BusinessDataType) metadataItem.getOriginalInfo();
			IARESResource refResource = metadataItem.getResource();
			items.add(new BusDataTypeItemScriptWrapImpl(bizDataType, refResource));
		}
		return items.toArray(new IBizDataTypeItemScriptWrap[items.size()]);
	}
	
}
