package com.hundsun.ares.studio.jres.basicdata.logic.util;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.modelconvert.BaseModelConverter;
import com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle;

public class BasicDataConventer extends BaseModelConverter{

	@Override
	protected ModelConverterHandle getDefaultModelConverterHandle(String type) {
		return new BaicDataModelConverterHandle();
	}

	@Override
	protected void processInfoOnRead(Object info, IARESResource resource) {
		
	}

	@Override
	protected void processInfoOnWrite(Object info, IARESResource resource) {
		
	}

}
