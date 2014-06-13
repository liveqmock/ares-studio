package com.hundsun.ares.studio.emfadapter.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

import com.hundsun.ares.studio.core.model.converter.IModelConverter;
import com.hundsun.ares.studio.emfadapter.AresXMLProcessor;
import com.hundsun.ares.studio.emfadapter.AresXMLResourceImpl;

public class EmfModelConverter implements IModelConverter {

	protected AresXMLProcessor processor = new AresXMLProcessor();
	protected Map map = new HashMap();
	protected BasicExtendedMetaData metaData = new BasicExtendedMetaData();
	

	public EmfModelConverter() {
		map.put(XMLResource.OPTION_ENCODING, "UTF-8");
		map.put(XMLResource.OPTION_EXTENDED_META_DATA, metaData);
	}

	
	public void read(InputStream in, Object info) throws Exception {
//		System.out.println("read");
//		System.out.println(System.currentTimeMillis());
		processor.load(in, map,info);
//		System.out.println(System.currentTimeMillis());
	}

	public Object read(InputStream in) throws Exception {
		Resource res = processor.load(in, map);
		if(res != null && res.getContents().size() > 0){
			return res.getContents().get(0);
		}
		return null;
	}

	public void write(OutputStream out, Object info) throws Exception {
//		System.out.println("write");
//		System.out.println(System.currentTimeMillis());
		if(info instanceof EObject){
//			Resource res = ((EObject) info).eResource();
//			if (res == null) {
				Resource res = new AresXMLResourceImpl();
				res.getContents().add((EObject)info);
//			}
				if(res instanceof XMLResource){
					XMLResource xmlres = (XMLResource)res;
					String encoding = map.get(XMLResource.OPTION_ENCODING).toString();
					if(!encoding.equals(xmlres.getEncoding())){
						xmlres.setEncoding(encoding);
					}
				}
			processor.save(out, res, map);
		}
//		System.out.println(System.currentTimeMillis());
	}

}
