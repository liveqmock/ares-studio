package com.hundsun.ares.studio.biz.core;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IAdapterFactory;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.core.model.CommonModel;
import com.hundsun.ares.studio.core.model.ICommonModel;

public class ObjectAdapterFactory implements IAdapterFactory {
	
	private static Class[] ADAPTERS = new Class[] {ICommonModel.class};

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (! (adaptableObject instanceof ARESObject)) 
			return null;
		
		final ARESObject obj = (ARESObject) adaptableObject;
		if (adapterType == ICommonModel.class) {
			return new CommonModel() {
				@Override
				public void setValue(String key, Object value) {
				}
				@Override
				public Object getValue(String key) {
					if (StringUtils.equals(key, ICommonModel.CNAME)) {
						return obj.getChineseName();
					}
					return null;
				}
			};
		}
		
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return ADAPTERS;
	}

}
