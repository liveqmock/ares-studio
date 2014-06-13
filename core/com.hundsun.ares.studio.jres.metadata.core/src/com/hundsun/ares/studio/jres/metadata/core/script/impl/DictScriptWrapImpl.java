/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.script.api.metadata.IDictSubEntryScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IDictEntryScriptWrap;



/**
 * @author yanwj06282
 *
 */
public class DictScriptWrapImpl extends MetadataItemScriptWrapImpl implements IDictEntryScriptWrap {

	private DictionaryType dictionaryType;
	
	public DictScriptWrapImpl(DictionaryType item, IARESResource resource) {
		super(item, resource);
		this.dictionaryType = item;
	}

	@Override
	public IDictSubEntryScriptWrap[] getSubEntries() {
		List<IDictSubEntryScriptWrap> dictItems = new ArrayList<IDictSubEntryScriptWrap>();
		for (DictionaryItem item : dictionaryType.getItems()){
			dictItems.add(new DictItemScriptWrapImpl(item,resource));
		}
		return dictItems.toArray(new IDictSubEntryScriptWrap[0]);
	}

	@Override
	public String getName() {
		return dictionaryType.getName();
	}

	public String getChineseName(){
		return dictionaryType.getChineseName();
	}
	
}
