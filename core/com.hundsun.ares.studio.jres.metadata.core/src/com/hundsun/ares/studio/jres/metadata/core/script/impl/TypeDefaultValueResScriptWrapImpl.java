/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList;
import com.hundsun.ares.studio.jres.script.api.metadata.ITypeDefaultValueItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class TypeDefaultValueResScriptWrapImpl extends MetadataResScriptWrapImpl {

	public TypeDefaultValueResScriptWrapImpl(TypeDefaultValueList stdType,	IARESResource resource, boolean includeRequiredBundles) {
		super(stdType, resource, includeRequiredBundles);
	}

	@Override
	public TypeDefaultValueList getOriginalInfo() {
		return (TypeDefaultValueList) super.getOriginalInfo();
	}
	
	@Override
	public ITypeDefaultValueItemScriptWrap[] getItems() {
		List<ITypeDefaultValueItemScriptWrap> items = new ArrayList<ITypeDefaultValueItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getItems()
		for (IMetadataItemScriptWrap item : super.getItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			TypeDefaultValue defaultValue = (TypeDefaultValue) metadataItem.getOriginalInfo();
			items.add(new TypeDefaultValueItemScriptWrapImpl(defaultValue, metadataItem.getResource()));
		}
		return items.toArray(new ITypeDefaultValueItemScriptWrap[items.size()]);
	}
	
	@Override
	public IMetadataItemScriptWrap[] getNotCateItems() {
		List<ITypeDefaultValueItemScriptWrap> items = new ArrayList<ITypeDefaultValueItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getNotCateItems()
		for (IMetadataItemScriptWrap item : super.getNotCateItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			TypeDefaultValue defaultValue = (TypeDefaultValue) metadataItem.getOriginalInfo();
			items.add(new TypeDefaultValueItemScriptWrapImpl(defaultValue, metadataItem.getResource()));
		}
		return items.toArray(new ITypeDefaultValueItemScriptWrap[items.size()]);
	}
	
}
