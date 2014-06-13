package com.hundsun.ares.studio.emfadapter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class AresXMLResourceImpl extends XMIResourceImpl {
	public AresXMLResourceImpl(URI uri) {
		super(uri);
	}
	public AresXMLResourceImpl() {
		super();
	}
	
	@Override
	protected XMLLoad createXMLLoad() {
		return new AresXMLLoadImpl(createXMLHelper());
	}
	
	@Override
	protected XMLSave createXMLSave() {
		return new AresXMLSaveImpl(createXMLHelper());
	}
	
}
