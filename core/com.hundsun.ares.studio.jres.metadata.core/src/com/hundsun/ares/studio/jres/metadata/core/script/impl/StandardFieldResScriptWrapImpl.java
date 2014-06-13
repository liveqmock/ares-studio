/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class StandardFieldResScriptWrapImpl extends MetadataResScriptWrapImpl {

	public StandardFieldResScriptWrapImpl(StandardFieldList dict, IARESResource resource, boolean includeRequiredBundles) {
		super(dict, resource, includeRequiredBundles);
	}

	@Override
	public StandardFieldList getOriginalInfo() {
		return (StandardFieldList) super.getOriginalInfo();
	}
	
	@Override
	public IMetadataItemScriptWrap[] getItems() {
		List<IStandardFieldScriptWrap> items = new ArrayList<IStandardFieldScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getItems()
		for (IMetadataItemScriptWrap item : super.getItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			StandardField standardField = (StandardField) metadataItem.getOriginalInfo();
			items.add(new StandardFieldScriptWrapImpl(MetadataUtil.decrypt(standardField, metadataItem.getResource()), metadataItem.getResource()));
		}
		return items.toArray(new IMetadataItemScriptWrap[items.size()]);
	}
	
	@Override
	public IStandardFieldScriptWrap[] getNotCateItems() {
		List<IStandardFieldScriptWrap> items = new ArrayList<IStandardFieldScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getNotCateItems()
		for (IMetadataItemScriptWrap item : super.getNotCateItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			StandardField standardField = (StandardField) metadataItem.getOriginalInfo();
			items.add(new StandardFieldScriptWrapImpl(MetadataUtil.decrypt(standardField, metadataItem.getResource()), metadataItem.getResource()));
		}
		return items.toArray(new IStandardFieldScriptWrap[items.size()]);
	}
	
	public IStandardFieldScriptWrap addItem(){
		StandardField std = MetadataFactory.eINSTANCE.createStandardField();
		getOriginalInfo().getItems().add(std);
		return new StandardFieldScriptWrapImpl(MetadataUtil.decrypt(std, resource),resource);
	}
	
	@Override
	public void removeItem(IMetadataItemScriptWrap item) {
		if (item instanceof StandardFieldScriptWrapImpl) {
			StandardFieldScriptWrapImpl stdfield = (StandardFieldScriptWrapImpl) item;
			DeStandardField de = (DeStandardField) stdfield.getProxy();
			getOriginalInfo().getItems().remove(de.getProxyItem());
		}
	}
	
}
