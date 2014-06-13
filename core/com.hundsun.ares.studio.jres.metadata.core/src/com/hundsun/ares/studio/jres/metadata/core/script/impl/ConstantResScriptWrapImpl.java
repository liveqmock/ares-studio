/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.ConstantList;
import com.hundsun.ares.studio.jres.script.api.metadata.IConstantItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class ConstantResScriptWrapImpl extends MetadataResScriptWrapImpl {

	public ConstantResScriptWrapImpl(ConstantList dict, IARESResource resource, boolean includeRequiredBundles) {
		super(dict, resource, includeRequiredBundles);
	}

	@Override
	public ConstantList getOriginalInfo() {
		return (ConstantList) super.getOriginalInfo();
	}
	
	@Override
	public IConstantItemScriptWrap[] getItems() {
		List<IConstantItemScriptWrap> items = new ArrayList<IConstantItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getItems()
		for (IMetadataItemScriptWrap item : super.getItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			ConstantItem constItem = (ConstantItem) metadataItem.getOriginalInfo();
			items.add(new ConstantItemScriptWrapImpl(constItem, metadataItem.getResource()));
		}
		return items.toArray(new IConstantItemScriptWrap[items.size()]);
	}
	
	@Override
	public IConstantItemScriptWrap[] getNotCateItems() {
		List<IConstantItemScriptWrap> items = new ArrayList<IConstantItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getNotCateItems()
		for (IMetadataItemScriptWrap item : super.getNotCateItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			ConstantItem constItem = (ConstantItem) metadataItem.getOriginalInfo();
			items.add(new ConstantItemScriptWrapImpl(constItem, metadataItem.getResource()));
		} 
		return items.toArray(new IConstantItemScriptWrap[items.size()]);
	}
	
}
