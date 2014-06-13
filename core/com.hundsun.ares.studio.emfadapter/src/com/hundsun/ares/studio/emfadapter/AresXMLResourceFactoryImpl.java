package com.hundsun.ares.studio.emfadapter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class AresXMLResourceFactoryImpl extends XMIResourceFactoryImpl {
	  @Override
	  public Resource createResource(URI uri)
	  {
	    return new AresXMLResourceImpl(uri);
	  }
}
