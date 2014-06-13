/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.script.api.metadata.IDictEntryScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class DictResScriptWrapImpl extends MetadataResScriptWrapImpl {

	public DictResScriptWrapImpl(DictionaryList dict, IARESResource resource, boolean includeRequiredBundles) {
		super(dict, resource, includeRequiredBundles);
	}

	@Override
	public DictionaryList getOriginalInfo() {
		return (DictionaryList) super.getOriginalInfo();
	}
	
	@Override
	public IDictEntryScriptWrap[] getItems() {
		List<IDictEntryScriptWrap> items = new ArrayList<IDictEntryScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getItems()
		for (IMetadataItemScriptWrap item : super.getItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			DictionaryType dictType = (DictionaryType) metadataItem.getOriginalInfo();
			items.add(new DictScriptWrapImpl(dictType, metadataItem.getResource()));
		}
		return items.toArray(new IDictEntryScriptWrap[items.size()]);
	}
	
	@Override
	public IDictEntryScriptWrap[] getNotCateItems() {
		List<IDictEntryScriptWrap> items = new ArrayList<IDictEntryScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getNotCateItems()
		for (IMetadataItemScriptWrap item : super.getNotCateItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			DictionaryType dictType = (DictionaryType) metadataItem.getOriginalInfo();
			items.add(new DictScriptWrapImpl(dictType, metadataItem.getResource()));
		}
		return items.toArray(new IDictEntryScriptWrap[items.size()]);
	}
	
}
