/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList;
import com.hundsun.ares.studio.jres.script.api.metadata.IStandardDataTypeItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class StandardDataTypeResScriptWrapImpl extends MetadataResScriptWrapImpl {

	public StandardDataTypeResScriptWrapImpl(StandardDataTypeList stdType,	IARESResource resource,  boolean includeRequiredBundles) {
		super(stdType, resource, includeRequiredBundles);
	}

	@Override
	public StandardDataTypeList getOriginalInfo() {
		return (StandardDataTypeList) super.getOriginalInfo();
	}
	
	@Override
	public IStandardDataTypeItemScriptWrap[] getItems() {
		List<IStandardDataTypeItemScriptWrap> items = new ArrayList<IStandardDataTypeItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getItems()
		for (IMetadataItemScriptWrap item : super.getItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			StandardDataType standardDataType = (StandardDataType) metadataItem.getOriginalInfo();
			items.add(new StandardDataTypeItemScriptWrapImpl(standardDataType, metadataItem.getResource()));
		}
		return items.toArray(new IStandardDataTypeItemScriptWrap[items.size()]);
	}
	
	@Override
	public IMetadataItemScriptWrap[] getNotCateItems() {
		List<IStandardDataTypeItemScriptWrap> items = new ArrayList<IStandardDataTypeItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getNotCateItems()
		for (IMetadataItemScriptWrap item : super.getNotCateItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			StandardDataType standardDataType = (StandardDataType) metadataItem.getOriginalInfo();
			items.add(new StandardDataTypeItemScriptWrapImpl(standardDataType, metadataItem.getResource()));
		}
		return items.toArray(new IStandardDataTypeItemScriptWrap[items.size()]);
	}
	
}
