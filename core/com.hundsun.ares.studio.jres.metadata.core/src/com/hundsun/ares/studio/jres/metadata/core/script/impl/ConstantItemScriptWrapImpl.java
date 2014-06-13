/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.script.api.metadata.IConstantItemScriptWrap;


/**
 * @author yanwj06282
 *
 */
public class ConstantItemScriptWrapImpl extends MetadataItemScriptWrapImpl implements IConstantItemScriptWrap {

	private ConstantItem contItem;
	
	public ConstantItemScriptWrapImpl(ConstantItem item, IARESResource resource) {
		super(item, resource);
		this.contItem = item;
	}

	@Override
	public String getName() {
		return contItem.getName();
	}

	public String getChineseName(){
		return contItem.getChineseName();
	}

	@Override
	public ConstantItem getOriginalInfo() {
		return contItem;
	}
	
	@Override
	public String getDataType(String type) {
		IMetadataService service = DataServiceManager.getInstance().getService(resource.getARESProject(), IMetadataService.class);
		IBusinessDataType dataType = service.getBusinessDataType(contItem.getDataType());
		if (dataType != null) {
			return dataType.getRealType(type);
			
		}
		return StringUtils.EMPTY;
	}
	
	@Override
	public String getContantValue() {
		return getOriginalInfo().getValue();
	}
	
}
