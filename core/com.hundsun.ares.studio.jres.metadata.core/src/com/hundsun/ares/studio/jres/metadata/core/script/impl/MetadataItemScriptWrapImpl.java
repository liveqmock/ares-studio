/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class MetadataItemScriptWrapImpl extends CommonScriptWrap<MetadataItem> implements
		IMetadataItemScriptWrap {

	public MetadataItemScriptWrapImpl(MetadataItem item , IARESResource resource) {
		super(item ,resource);
	}

	@Override
	public String getName() {
		return getOriginalInfo().getName();
	}
	
	public String getChineseName(){
		return getOriginalInfo().getChineseName();
	}
	
	public String getDescription(){
		return getOriginalInfo().getDescription();
	}
	
}
