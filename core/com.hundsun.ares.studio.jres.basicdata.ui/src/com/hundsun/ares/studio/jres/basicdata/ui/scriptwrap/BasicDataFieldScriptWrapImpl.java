/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.scriptwrap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataEAttribute;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.core.script.impl.BusDataTypeItemScriptWrapImpl;
import com.hundsun.ares.studio.jres.metadata.core.script.impl.DictScriptWrapImpl;
import com.hundsun.ares.studio.jres.metadata.core.script.impl.StandardFieldScriptWrapImpl;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IDictionaryType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem;
import com.hundsun.ares.studio.jres.script.api.metadata.IBizDataTypeItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IDictEntryScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.ITypeDefaultValueItemScriptWrap;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author liaogc
 * 基础数据脚本API实现(支持非标准字段)
 *
 */
public class BasicDataFieldScriptWrapImpl extends StandardFieldScriptWrapImpl{

	private EAttribute attribute;
	private IMetadataService service = null;
	private boolean isStandardField = false;
	private IBusinessDataType ibusType;
	static final Logger console = ConsoleHelper.getLogger();
	/**
	 * @param proxy
	 * @param resource
	 */
	public BasicDataFieldScriptWrapImpl(IMetadataService service,EAttribute attribute,
			IARESResource resource ) {
		super(null, resource);
		this.service = service;
		this.attribute = attribute;
		init();
	}
	
	private void init(){
		if (BasicDataEpackageUtil.isStandardField(attribute)) {
			proxy = service.getStandardField(attribute.getName());
			isStandardField = true;
			if(proxy==null){
				console.info(attribute.getName()+"(" +getChineseName()+")"+"标准字段不存在");
			}
			if(proxy!=null){
				ibusType = proxy.getDataType();
			}
			if(ibusType==null){
				console.info(attribute.getName()+"(" +getChineseName()+")"+"对应的业务数据类型不存在");
			}
		} else {
			isStandardField = false;
			proxy = null;
			if (attribute instanceof BasicDataEAttribute) {
				BasicDataEAttribute basicDataEAttribute = (BasicDataEAttribute) attribute;
				if (basicDataEAttribute.getData() != null) {
					if (basicDataEAttribute.getData() instanceof BasicDataField) {
						BasicDataField basicDataField = (BasicDataField) basicDataEAttribute
								.getData();
						ibusType = service.getBusinessDataType(basicDataField
								.getDataType());
						if(ibusType==null){
							console.info(attribute.getName()+"(" +getChineseName()+")"+"对应的业务数据类型不存在");
						}

					}
				}

			}
		}
	}
	
	
	@Override
	public IBizDataTypeItemScriptWrap getBizDataType() {
		if (ibusType instanceof DeBusinessDataType) {
			BusinessDataType busType = ((DeBusinessDataType) ibusType)
					.getProxyItem();
			if (busType != null) {
				return new BusDataTypeItemScriptWrapImpl(busType, resource);
			}
		}
		return null;
	}
	
	@Override
	public String getRealType(String type) {
		String realType = "";
		if (ibusType != null) {
			realType = ibusType.getRealType(type);
		}
		return realType;
	}

	@Override
	public int getLength() {
		int length = 0;
		if (ibusType != null) {
			if (StringUtils.isNotBlank(ibusType.getLength())) {
				try {
					length = Integer.parseInt(ibusType.getLength());
				} catch (Exception e) {

				}
			}
		}
	
		return length;
	}

	@Override
	public int getPrecision() {
		int precision = 0;
		if (ibusType != null) {
			if (StringUtils.isNotBlank(ibusType.getPrecision())) {
				try {
					precision = Integer.parseInt(ibusType.getPrecision());
				} catch (Exception e) {

				}
			}
		}
		
		return precision;
	}
	
	public IDictEntryScriptWrap getDictInfo(){
		if(isStandardField && proxy!=null){
			IDictionaryType dt = proxy.getDictionaryType();
			if (dt instanceof DeMetadataItem) {
				MetadataItem item = ((DeMetadataItem) dt).getProxyItem();
				if (item != null) {
					return new DictScriptWrapImpl((DictionaryType)item, resource);
				}
			}
		}
		
		return null;
	}
	
	@Override
	public String getName() {
		String name = "";
		if(isStandardField && proxy!=null){
			name = proxy.getName();
		}else{
			name = attribute.getName();
		}
		return name;
	}

	@Override
	public String getChineseName() {
		String chineseName = "";
		if(isStandardField && proxy!=null){
			chineseName = proxy.getChineseName();
		}
		else{
			 if(attribute instanceof BasicDataEAttribute){
				 BasicDataEAttribute basicDataEAttribute = (BasicDataEAttribute) attribute;
				if (basicDataEAttribute.getData() != null) {
					if (basicDataEAttribute.getData() instanceof BasicDataField) {
						BasicDataField basicDataField = (BasicDataField) basicDataEAttribute.getData();
						chineseName = basicDataField.getChineseName();
						
					}
				}

			}
		}
		return chineseName;
	}

	@Override
	public StandardField getOriginalInfo() {
		if (isStandardField && proxy instanceof DeMetadataItem && proxy!=null) {
			return (StandardField) ((DeMetadataItem) proxy).getProxyItem();
		}
		return null;
	}

	public String getDescription(){
		if(proxy!=null){
			return proxy.getDescription();
		}
		return "";
	}
	


	@Override
	public String getTrueDefaultValue(String type) {
		String dv = getDefaultValue(attribute ,type);
		if (StringUtils.isNotBlank(dv)) {
			return dv;
		}
		IBizDataTypeItemScriptWrap bizType = getBizDataType();
		if (bizType != null) {
			ITypeDefaultValueItemScriptWrap defaultValue = bizType.getDefaultValue();
			if (defaultValue != null) {
				return defaultValue.getValue(type);
			}
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 获取默认值，先取表字段的默认值，如果对应的表字段默认值为空，则去业务数据类型的标准字段
	 * @param attribute
	 * @return
	 */
	private String getDefaultValue(EAttribute attribute , String type){
		if(attribute instanceof BasicDataEAttribute ){
			BasicDataEAttribute basicDataEAttribute = (BasicDataEAttribute) attribute;
			if(basicDataEAttribute.getData() !=null){
				if(basicDataEAttribute.getData() instanceof BasicDataField){
					BasicDataField basicDataField = (BasicDataField) basicDataEAttribute.getData();
					String defv = basicDataField.getDefaultValue();
					if (StringUtils.isNotBlank(defv)) {
						ReferenceInfo devInfo = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.DefValue, defv, false);
						if (devInfo != null) {
							Object obj = devInfo.getObject();
							if (obj instanceof TypeDefaultValue) {
								return ((TypeDefaultValue) obj).getValue(type);
							}
						}else {
							return defv;
						}
					}
				}
			}
		}
		return StringUtils.EMPTY;
	}
	

}
