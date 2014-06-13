/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoList;
import com.hundsun.ares.studio.jres.script.api.metadata.IErrorItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class ErrorResScriptWrapImpl extends MetadataResScriptWrapImpl {

	public ErrorResScriptWrapImpl(ErrorNoList error, IARESResource resource,  boolean includeRequiredBundles) {
		super(error, resource, includeRequiredBundles);
	}

	@Override
	public ErrorNoList getOriginalInfo() {
		return (ErrorNoList) super.getOriginalInfo();
	}
	
	@Override
	public IErrorItemScriptWrap[] getItems() {
		List<IErrorItemScriptWrap> items = new ArrayList<IErrorItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getItems()
		for (IMetadataItemScriptWrap item : super.getItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			ErrorNoItem errorNoItem = (ErrorNoItem) metadataItem.getOriginalInfo();
			items.add(new ErrorItemScriptWrapImpl(errorNoItem, metadataItem.getResource()));
		}
		return items.toArray(new IErrorItemScriptWrap[items.size()]);
	}
	
	@Override
	public IMetadataItemScriptWrap[] getNotCateItems() {
		List<IErrorItemScriptWrap> items = new ArrayList<IErrorItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getNotCateItems()
		for (IMetadataItemScriptWrap item : super.getNotCateItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			ErrorNoItem errorNoItem = (ErrorNoItem) metadataItem.getOriginalInfo();
			items.add(new ErrorItemScriptWrapImpl(errorNoItem, metadataItem.getResource()));
		}
		return items.toArray(new IErrorItemScriptWrap[0]);
	}
	
}
