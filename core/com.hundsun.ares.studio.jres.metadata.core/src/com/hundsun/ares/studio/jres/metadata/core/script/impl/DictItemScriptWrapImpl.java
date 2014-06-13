/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.script.api.metadata.IDictEntryScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IDictSubEntryScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class DictItemScriptWrapImpl extends CommonScriptWrap<DictionaryItem> implements IDictSubEntryScriptWrap {

	public DictItemScriptWrapImpl(DictionaryItem item , IARESResource resource) {
		super(item ,resource);
	}

	@Override
	public String getName() {
		return getOriginalInfo().getName();
	}
	
	public String getChineseName(){
		return getOriginalInfo().getChineseName();
	}
	
	public String getSubEntryName(){
		return getOriginalInfo().getChineseName();
	}
	
	public String getSubEntry(){
		return getOriginalInfo().getValue();
	}

	public String getCnstName(){
		return getOriginalInfo().getConstantName();
	}
	
	@Override
	public IDictEntryScriptWrap getParent() {
		if (getOriginalInfo().eContainer() instanceof DictionaryType) {
			return new DictScriptWrapImpl((DictionaryType) getOriginalInfo().eContainer(),resource);
		}
		return null;
	}
	
	@Override
	public String getDescription() {
		return getOriginalInfo().getDescription();
	}
	
}
