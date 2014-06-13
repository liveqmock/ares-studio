/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.script.api.metadata.IStandardDataTypeItemScriptWrap;


/**
 * 标准数据类型明细对象
 * 
 * @author yanwj06282
 *
 */
public class StandardDataTypeItemScriptWrapImpl extends MetadataItemScriptWrapImpl implements IStandardDataTypeItemScriptWrap {

	private StandardDataType dataType;
	
	public StandardDataTypeItemScriptWrapImpl(StandardDataType item, IARESResource resource) {
		super(item, resource);
		this.dataType = item;
	}

	@Override
	public String getRealType(String type) {
		return dataType.getValue(type);
	}
	
	@Override
	public StandardDataType getOriginalInfo() {
		return dataType;
	}
	
}
