package com.hundsun.ares.studio.biz;

import java.util.Map;

import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.extend.IRefExtendPropertyProvider;
import com.hundsun.ares.studio.core.model.extendable.ExtensibleModelUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

public class ParameterUserExtendPropertyProvider implements
		IRefExtendPropertyProvider {

	private Map<String, String> config;
	private IARESProject project;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IRefExtendPropertyProvider#config(java.util.Map)
	 */
	@Override
	public void config(Map<String, String> config, IARESProject project) {
		this.config = config;
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IRefExtendPropertyProvider#getValue(com.hundsun.ares.studio.core.model.ExtensibleModel)
	 */
	@Override
	public String getValue(ExtensibleModel model) {
		String key = config.get("key");
		if (model instanceof Parameter) {
			Parameter p = (Parameter) model;
			if (p.getParamType() == ParamType.STD_FIELD) {
				// 如果是标准字段，就找到对应的标准字段，然后查找
				String id = p.getId();
				ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdField, id, true);
				if (ref != null) {
					StandardField std = (StandardField) ref.getObject();
					return ExtensibleModelUtil.getUserExtendedProperty(std, key);
				}
			} else if (p.getParamType() == ParamType.OBJECT) {
				String id = p.getType();
				ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IBizRefType.Object, id, true);
				if (ref != null) {
					ARESObject obj = (ARESObject) ref.getObject();
					return ExtensibleModelUtil.getUserExtendedProperty(obj, key);
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IRefExtendPropertyProvider#setValue(com.hundsun.ares.studio.core.model.ExtensibleModel, java.lang.String)
	 */
	@Override
	public void setValue(ExtensibleModel model, String value) {
		
	}

}
