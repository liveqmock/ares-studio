/**
 * 源程序名称：AttributeWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.script;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.jres.script.api.biz.IAttributeWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author sundl
 *
 */
public class AttributeWrap extends CommonScriptWrap<Parameter> implements IAttributeWrap {

	/**
	 * @param t
	 * @param resource
	 */
	public AttributeWrap(Parameter t, IARESResource resource) {
		super(t, resource);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IAttributeWrap#setName()
	 */
	@Override
	public void setName(String name) {
		getOriginalInfo().setId(name);
	}
	
	@Override
	public String getName() {
		return getOriginalInfo().getId();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#getCName()
	 */
	@Override
	public String getCName() {
		return BizUtil.getCName(getOriginalInfo(), resource.getARESProject());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#getDesc()
	 */
	@Override
	public String getDesc() {
		return BizUtil.getDesc(getOriginalInfo(), resource.getARESProject());
	} 

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#getParamType()
	 */
	@Override
	public String getParamType() {
		return getOriginalInfo().getParamType().getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#getMultiplicity()
	 */
	@Override
	public String getMultiplicity() {
		return getOriginalInfo().getMultiplicity().getLiteral();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#getBizType()
	 */
	@Override
	public String getBizType() {
		if (getOriginalInfo().getParamType() == ParamType.STD_FIELD) {
			ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.StdField, getName(), true);
			if (info != null) {
				Object obj = info.getObject();
				if (obj instanceof StandardField) {
					return StringUtils.defaultString(((StandardField) obj).getDataType());
				}
			}
		}
		return getOriginalInfo().getType();
	}

	@Override
	public void setBizType(String bizType){
		getOriginalInfo().setType(bizType);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#getDefaultValue()
	 */
	@Override
	public String getDefaultValue(String type) {
		String defValue = getOriginalInfo().getDefaultValue();
		ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.DefValue, defValue, true);
		if (info != null) {
			Object obj = info.getObject();
			if (obj instanceof TypeDefaultValue) {
				return StringUtils.defaultString(((TypeDefaultValue) obj).getValue(type));
			}
		}
		if (StringUtils.equals(getParamType(), ParamType.STD_FIELD.getName())) {
			info = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.StdField, getName(), true);
			if (info != null) {
				Object obj = info.getObject();
				if (obj instanceof StandardField) {
					String bizType = ((StandardField) obj).getDataType();
					if (StringUtils.isNotBlank(bizType)) {
						info = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.BizType, bizType, true);
						if (info != null) {
							obj = info.getObject();
							if (obj instanceof BusinessDataType) {
								String devValue = ((BusinessDataType) obj).getDefaultValue();
								if (StringUtils.isNotBlank(devValue)) {
									info = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.DefValue, devValue, true);
									if (info != null) {
										obj = info.getObject();
										if (obj instanceof TypeDefaultValue) {
											return StringUtils.defaultString(((TypeDefaultValue) obj).getValue(type));
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return StringUtils.defaultString(defValue);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#getComments()
	 */
	@Override
	public String getComments() {
		return getOriginalInfo().getComments();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#getRealType()
	 */
	@Override
	public String getRealType() {
		return "暂未实现";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IAttributeWrap#getJavaType()
	 */
	@Override
	public String getJavaType() {
		ParamType paramType = getOriginalInfo().getParamType();
		if (paramType == ParamType.NON_STD_FIELD) {
			String bizType = getBizType();
			StandardDataType stdType = getStdType(bizType);
			return stdType == null ? null : stdType.getValue("java");
		}else if(paramType == ParamType.STD_FIELD ){
			try {
				StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(resource.getARESProject(), getName());
				return stdType == null ? null : stdType.getValue("java");
			} catch (Exception e) {}
			return null;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#getCSharpType()
	 */
	@Override
	public String getCSharpType() {
		ParamType paramType = getOriginalInfo().getParamType();
		if (paramType == ParamType.NON_STD_FIELD) {
			String bizType = getBizType();
			StandardDataType stdType = getStdType(bizType);
			return stdType == null ? null : stdType.getValue("C#");
		}else if(paramType == ParamType.STD_FIELD ){
			try {
				StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(resource.getARESProject(), getName());
				return stdType == null ? null : stdType.getValue("C#");
			} catch (Exception e) {}
			return null;
		}
		return null;	
	}

	/**
	 * @param project
	 * @param bizType
	 * @return
	 */
	protected StandardDataType getStdType(String bizType) {
		try {
			return MetadataServiceProvider.getStandardDataTypeOfBizTypeByName(resource.getARESProject(), bizType);
		} catch (Exception e) {}
		return null;
	}

}
