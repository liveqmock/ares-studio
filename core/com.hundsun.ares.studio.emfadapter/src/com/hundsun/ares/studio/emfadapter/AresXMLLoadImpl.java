package com.hundsun.ares.studio.emfadapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.dom4j.Element;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMILoadImpl;
import org.xml.sax.helpers.DefaultHandler;

import com.hundsun.ares.studio.core.model.extendable.ExtendModelConverterManager;
import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;
import com.hundsun.ares.studio.core.util.PersistentUtil;

public class AresXMLLoadImpl extends XMILoadImpl {
	public static final String EXTEND_STRING = "EXTEND_STRING";
	public AresXMLLoadImpl(XMLHelper helper) {
		super(helper);
	}
	
	@Override
	protected DefaultHandler makeDefaultHandler() {

		return new AresSAXXMLHandler(resource, helper, options);
	}
	
	@Override
	public void load(XMLResource resource, InputStream inputStream,
			Map<?, ?> options) throws IOException {
		super.load(resource, inputStream, options);
		if(options.get(EXTEND_STRING) != null){
			String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+options.get(EXTEND_STRING).toString();
				if(resource.getContents().size() > 0){
					if(resource.getContents().get(0) instanceof IExtendAbleModel){
						Element root = PersistentUtil.readRoot(new ByteArrayInputStream(xmlString.getBytes()));
						if(root != null){
							ExtendModelConverterManager.getDefault().readExtendMap((IExtendAbleModel)resource.getContents().get(0), root);
						}
					}
				}
		}

	}
	
	@Override
	protected void handleErrors() throws IOException {
	}

}
