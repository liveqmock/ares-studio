/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.script.api.metadata.ITypeDefaultValueItemScriptWrap;


/**
 * 标准数据类型明细对象
 * 
 * @author yanwj06282
 *
 */
public class TypeDefaultValueItemScriptWrapImpl extends MetadataItemScriptWrapImpl implements ITypeDefaultValueItemScriptWrap {

	private TypeDefaultValue dataType;
	
	public TypeDefaultValueItemScriptWrapImpl(TypeDefaultValue item, IARESResource resource) {
		super(item, resource);
		this.dataType = item;
	}

	@Override
	public String getValue(String type) {
		return dataType.getValue(type);
	}
	
	@Override
	public TypeDefaultValue getOriginalInfo() {
		return dataType;
	}
	
}
