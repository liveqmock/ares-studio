package com.hundsun.ares.studio.emfadapter;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMISaveImpl;

import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;

public class AresXMLSaveImpl extends XMISaveImpl {
	public AresXMLSaveImpl(XMLHelper helper) {
		super(helper);
	}

	public AresXMLSaveImpl(Map<?, ?> options, XMLHelper helper, String encoding) {
		super(getExtendSaveOptions(options), helper, encoding);
	}

	public AresXMLSaveImpl(Map<?, ?> options, XMLHelper helper,
			String encoding, String xmlVersion) {
		super(getExtendSaveOptions(options), helper, encoding, xmlVersion);
	}
	
	/**
	 * 使得保存文件时使用CDATA
	 * @param options
	 * @return
	 */
	static protected Map getExtendSaveOptions(Map options) {
		if (options == null) {
			options = new HashMap();
		}
		
		//options.put(XMIResource.OPTION_ESCAPE_USING_CDATA, Boolean.TRUE);
		
		return options;
	}

	protected void endSaveFeatures(EObject o, int elementType, String content) {
		if (o == root && (o instanceof IExtendAbleModel)) {
			Map map = ((IExtendAbleModel) o).getMap();
			if(map != null && !map.isEmpty()){
				elementType = CONTENT_ELEMENT;
				if (content == null) {
					content = "";
				}
				content = content
						+ EmfExtendModelConverter.getDefault().writeExtendMap(
								(IExtendAbleModel) o);
			}
		}
		if (processElementExtensions(o)) {
			if (!toDOM) {
				doc.endElement();
			}
		} else {
			switch (elementType) {
			case EMPTY_ELEMENT: {
				if (!toDOM) {
					doc.endEmptyElement();
				}
				break;
			}
			case CONTENT_ELEMENT: {
				if (!toDOM) {
					doc.endContentElement(content);
				}
				break;
			}
			default: {
				if (!toDOM) {
					doc.endElement();
				}
				break;
			}
			}
		}
		if (toDOM) {
			currentNode = currentNode.getParentNode();
		}
	}

}
