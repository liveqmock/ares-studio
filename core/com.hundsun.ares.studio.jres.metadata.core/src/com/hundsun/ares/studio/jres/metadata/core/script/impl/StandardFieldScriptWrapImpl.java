/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IDictionaryType;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem;
import com.hundsun.ares.studio.jres.script.api.metadata.IBizDataTypeItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IDictEntryScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.ITypeDefaultValueItemScriptWrap;
import com.hundsun.ares.studio.jres.script.base.ScriptModelWrapBase;

/**
 * @author lvgao
 *
 */
public class StandardFieldScriptWrapImpl extends ScriptModelWrapBase implements IStandardFieldScriptWrap{

	protected IStandardField proxy;
	
	public StandardFieldScriptWrapImpl(IStandardField poxy ,IARESResource resource) {
		super(resource);
		this.proxy = poxy;
	}
	
	@Override
	public IBizDataTypeItemScriptWrap getBizDataType() {
		IBusinessDataType ibusType = proxy.getDataType();
		if (ibusType instanceof DeBusinessDataType) {
			BusinessDataType busType = ((DeBusinessDataType) ibusType).getProxyItem();
			if (busType != null) {
				return new BusDataTypeItemScriptWrapImpl(busType, resource);
			}
		}
		return null;
	}
	
	@Override
	public String getRealType(String type) {
		return proxy.getDataType().getRealType(type);
	}

	@Override
	public int getLength() {
		int length = 0;
		IBusinessDataType bdt = proxy.getDataType();
		if(StringUtils.isNotBlank(bdt.getLength())){
			try{
				length = Integer.parseInt(bdt.getLength());
			}catch (Exception e) {
				
			}
		}
		return length;
	}

	@Override
	public int getPrecision() {
		int precision = 0;
		IBusinessDataType bdt = proxy.getDataType();
		if(StringUtils.isNotBlank(bdt.getPrecision())){
			try {
				precision = Integer.parseInt(bdt.getPrecision());
			} catch (Exception e) {
				
			}
		}
		return precision;
	}
	
	public IDictEntryScriptWrap getDictInfo(){
		IDictionaryType dt = proxy.getDictionaryType();
		if (dt instanceof DeMetadataItem) {
			MetadataItem item = ((DeMetadataItem) dt).getProxyItem();
			if (item != null) {
				return new DictScriptWrapImpl((DictionaryType)item, resource);
			}
		}
		return null;
	}
	
	@Override
	public String getName() {
		return proxy.getName();
	}

	@Override
	public String getChineseName() {
		return proxy.getChineseName();
	}

	@Override
	public StandardField getOriginalInfo() {
		if (proxy instanceof DeMetadataItem) {
			return (StandardField) ((DeMetadataItem) proxy).getProxyItem();
		}
		return null;
	}

	public String getDescription(){
		return proxy.getDescription();
	}
	
	public void setName(String name){
		StandardField std = getOriginalInfo();
		if (std != null) {
			std.setName(name);
		}
	}
	
	public void setChineseName(String chinaeseName){
		StandardField std = getOriginalInfo();
		if (std != null) {
			std.setChineseName(chinaeseName);
		}
	}
	
	public void setDescription(String description){
		StandardField std = getOriginalInfo();
		if (std != null) {
			std.setDescription(description);
		}
	}
	
	public void setDictInfo(String dictId){
		StandardField std = getOriginalInfo();
		if (std != null) {
			std.setDictionaryType(dictId);
		}
	}
	
	public void setDataType(String dataType){
		StandardField std = getOriginalInfo();
		if (std != null) {
			std.setDataType(dataType);
		}
	}

	@Override
	public String getTrueDefaultValue(String type) {
		IBizDataTypeItemScriptWrap bizType = getBizDataType();
		if (bizType != null) {
			ITypeDefaultValueItemScriptWrap defaultValue = bizType.getDefaultValue();
			if (defaultValue != null) {
				return defaultValue.getValue(type);
			}
		}
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap#getStrBizDataType()
	 */
	@Override
	public String getStrBizDataType() {
		return proxy.getDataTypeId();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap#getStrDictInfo()
	 */
	@Override
	public String getStrDictInfo() {
		return proxy.getDictionaryTypeId();
	}
	
	public IStandardField getProxy() {
		return proxy;
	}
	
}
