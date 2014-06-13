package com.hundsun.ares.studio.emfadapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

public class AresXMLProcessor extends XMLProcessor {

	public final static String ROOT = "AresXMLProcessorRoot";
	
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			Map<String, Resource.Factory> result = new HashMap<String, Resource.Factory>();
			result.put(STAR_EXTENSION, new AresXMLResourceFactoryImpl());
			registrations = result;
		}
		return registrations;
	}
	
	@Override
	public Resource load(InputStream is, Map<?, ?> options) throws IOException {
		Map map = new HashMap();
		if(options != null){
			map.putAll(options);
		}
		return super.load(is, map);
	}
	
	public void load(InputStream is, Map<?, ?> options,Object root) throws IOException {
		Map map = new HashMap();
		if(options != null){
			map.putAll(options);
		}
		map.put(ROOT, root);
		super.load(is, map);
	}
	
}
