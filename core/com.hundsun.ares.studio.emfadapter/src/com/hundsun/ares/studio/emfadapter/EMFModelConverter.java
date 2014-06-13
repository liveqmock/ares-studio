package com.hundsun.ares.studio.emfadapter;

import java.io.InputStream;
import java.io.OutputStream;

import com.hundsun.ares.studio.core.model.converter.IModelConverter;

public class EMFModelConverter implements IModelConverter {

	public void read(InputStream in, Object info) throws Exception {
		if (info instanceof DelegateAresResourceInfo) {
			DelegateAresResourceInfo delegate = (DelegateAresResourceInfo) info;
			
		} 
	}

	public Object read(InputStream in) throws Exception {
		return null;
	}

	public void write(OutputStream out, Object info) throws Exception {
		
	}

}
