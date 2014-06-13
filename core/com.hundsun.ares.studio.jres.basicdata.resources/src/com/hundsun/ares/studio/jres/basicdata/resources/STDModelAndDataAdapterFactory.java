package com.hundsun.ares.studio.jres.basicdata.resources;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IAdapterFactory;

import com.hundsun.ares.studio.core.model.CommonModel;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;

public class STDModelAndDataAdapterFactory implements IAdapterFactory {

	public Object getAdapter(final Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof StandardFieldModelAndData && adapterType == ICommonModel.class) {
			return new CommonModel() {
				public Object getValue(String key) {
					if (key.equals(CNAME)) {
						BasicDataBaseModel model = ((StandardFieldModelAndData)adaptableObject).getData();
						return model == null  ? StringUtils.EMPTY : model.getChineseName();
					} else if (key.equals(NAME)) {
						BasicDataBaseModel model = ((StandardFieldModelAndData)adaptableObject).getData();
						return model == null  ? StringUtils.EMPTY : model.getChineseName();					
					}
					return null;
				}

				public void setValue(String key, Object value) {
				}
			};
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] {ICommonModel.class};
	}

}
