/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.ConstantList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryList;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoList;
import com.hundsun.ares.studio.jres.model.metadata.MenuList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataResScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class MetadataScriptWrapImpl implements IMetadataScriptWrap {

	private IARESProject project;

	public MetadataScriptWrapImpl(IARESProject input) {
		this.project = input;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public IMetadataResScriptWrap getMetadataInfoByType(String type, boolean includeRequiredBundles) {
		try {
			IARESResource resource = project.findResource(type, type);
			if (resource != null) {
				if (StringUtils.equals(type, IMetadataResType.StdField)) {
					StandardFieldList std = resource.getInfo(StandardFieldList.class);
					if (std != null) {
						return new StandardFieldResScriptWrapImpl(std, resource, includeRequiredBundles);
					}
				}if (StringUtils.equals(type, IMetadataResType.StdType)) {
					StandardDataTypeList std = resource.getInfo(StandardDataTypeList.class);
					if (std != null) {
						return new StandardDataTypeResScriptWrapImpl(std, resource, includeRequiredBundles);
					}
				}if (StringUtils.equals(type, IMetadataResType.DefValue)) {
					TypeDefaultValueList dv = resource.getInfo(TypeDefaultValueList.class);
					if (dv != null) {
						return new TypeDefaultValueResScriptWrapImpl(dv, resource, includeRequiredBundles);
					}
				}else if (StringUtils.equals(type, IMetadataResType.Dict)) {
					DictionaryList dict = resource.getInfo(DictionaryList.class);
					if (dict != null) {
						return new DictResScriptWrapImpl(dict, resource, includeRequiredBundles);
					}
				}else if (StringUtils.equals(type, IMetadataResType.Const)) {
					ConstantList cont = resource.getInfo(ConstantList.class);
					if (cont != null) {
						return new ConstantResScriptWrapImpl(cont, resource, includeRequiredBundles);
					}
				}else if (StringUtils.equals(type, IMetadataResType.Menu)) {
					MenuList menu = resource.getInfo(MenuList.class);
					if (menu != null) {
						return new MenuResScriptWrapImpl(menu, resource, includeRequiredBundles);
					}
				}else if (StringUtils.equals(type, IMetadataResType.BizType)) {
					BusinessDataTypeList menu = resource.getInfo(BusinessDataTypeList.class);
					if (menu != null) {
						return new BusDataTypeResScriptWrapImpl(menu, resource, includeRequiredBundles);
					}
				}else if (StringUtils.equals(type, IMetadataResType.ErrNo)) {
					ErrorNoList error = resource.getInfo(ErrorNoList.class);
					if (error != null) {
						return new ErrorResScriptWrapImpl(error, resource, includeRequiredBundles);
					}
				}else {
					MetadataResourceData metadata = resource.getInfo(MetadataResourceData.class);
					if (metadata != null) {
						return new MetadataResScriptWrapImpl(metadata, resource, includeRequiredBundles);
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IMetadataScriptWrap#getMetadataInfoByType(java.lang.String, boolean)
	 */
	@Override
	public IMetadataResScriptWrap getMetadataInfoByType(String type) {
		return getMetadataInfoByType(type, false);
	}

}
