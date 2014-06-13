/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardDataType;
import com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeTypeDefaultValueImpl;
import com.hundsun.ares.studio.jres.script.api.metadata.IBizDataTypeItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IStandardDataTypeItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.ITypeDefaultValueItemScriptWrap;


/**
 * @author yanwj06282
 *
 */
public class BusDataTypeItemScriptWrapImpl extends MetadataItemScriptWrapImpl implements IBizDataTypeItemScriptWrap {

	private BusinessDataType dataType;
	
	public BusDataTypeItemScriptWrapImpl(BusinessDataType item, IARESResource resource) {
		super(item, resource);
		this.dataType = item;
	}

	@Override
	public String getName() {
		return dataType.getName();
	}

	public String getChineseName(){
		return dataType.getChineseName();
	}

	@Override
	public BusinessDataType getOriginalInfo() {
		return dataType;
	}
	
	@Override
	public IStandardDataTypeItemScriptWrap getStdType() {
		IMetadataService service = DataServiceManager.getInstance().getService(resource.getARESProject(), IMetadataService.class);
		IStandardDataType istd = service.getStandardDataType(getOriginalInfo().getStdType());
		if (istd != null && istd instanceof DeStandardDataType ) {
			StandardDataType std = ((DeStandardDataType)istd).getProxyItem();
			if (std != null) {
				return new StandardDataTypeItemScriptWrapImpl(std, resource);
			}
		}
		return null;
	}
	
	@Override
	public String getRealType(String type) {
		IMetadataService service = DataServiceManager.getInstance().getService(resource.getARESProject(), IMetadataService.class);
		IBusinessDataType dt = service.getBusinessDataType(dataType.getName());
		if (dt != null) {
			return dt.getRealType(type);
		}
		return StringUtils.EMPTY;
	}

	@Override
	public int getLength() {
		try {
			return Integer.parseInt(dataType.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getPrecision() {
		try {
			return Integer.parseInt(dataType.getPrecision());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getDefaultValueID(){
		return dataType.getDefaultValue();
	}
	
	public ITypeDefaultValueItemScriptWrap getDefaultValue(){
		IMetadataService ms = DataServiceManager.getInstance().getService(resource.getARESProject(), IMetadataService.class);
		String defaultValue = dataType.getDefaultValue();
		if (StringUtils.isNotBlank(defaultValue)) {
			ITypeDefaultValue dv = ms.getTypeDefaultValue(defaultValue);
			if (dv != null && dv instanceof DeTypeDefaultValueImpl ) {
				TypeDefaultValue tdv = ((DeTypeDefaultValueImpl)dv).getProxyItem();
				return new TypeDefaultValueItemScriptWrapImpl(tdv, resource);
			}
		}
		return null;
	}
	
}
