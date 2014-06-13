package com.hundsun.ares.studio.core.model.converter;

import java.io.InputStream;
import java.io.OutputStream;

import com.hundsun.ares.studio.core.PropertiesModel;

public class PropertiesConvertor implements IModelConverter {

	public PropertiesConvertor() {
	}

	@Override
	public void read(InputStream in, Object info) throws Exception {
		if (info instanceof PropertiesModel) {
			((PropertiesModel) info).properties.load(in);
		}
	}

	@Override
	public Object read(InputStream in) throws Exception {
		return null;
	}

	@Override
	public void write(OutputStream out, Object info) throws Exception {
		if (info instanceof PropertiesModel) {
			((PropertiesModel) info).properties.store(out, null);
		}
	}

}
