package com.hundsun.ares.studio.emfadapter;

import java.util.Map;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.SAXXMLHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.hundsun.ares.studio.core.model.extendable.ExtendModelConverterManager;

public class AresSAXXMLHandler extends SAXXMLHandler {
	Map options;

	public AresSAXXMLHandler(XMLResource xmiResource, XMLHelper helper,
			Map<?, ?> options) {
		super(xmiResource, helper, options);
		this.options = options;
	}

	/**
	 * Create an object based on the prefix and type name.
	 */
	protected EObject createObjectByType(String prefix, String name, boolean top) {
		if (top) {
			handleTopLocations(prefix, name);
		}

		EFactory eFactory = getFactoryForPrefix(prefix);
		String uri = helper.getURI(prefix);
		if (eFactory == null && prefix.equals("") && uri == null) {
			EPackage ePackage = handleMissingPackage(null);
			if (ePackage == null) {
				error(new PackageNotFoundException(null, getLocation(),
						getLineNumber(), getColumnNumber()));
			} else {
				eFactory = ePackage.getEFactoryInstance();
			}
		}

		documentRoot = createDocumentRoot(prefix, uri, name, eFactory, top);

		if (documentRoot != null)
			return documentRoot;

		EObject newObject = null;
		if (top && options != null
				&& options.get(AresXMLProcessor.ROOT) != null
				&& options.get(AresXMLProcessor.ROOT) instanceof EObject) {
			newObject = (EObject) options.get(AresXMLProcessor.ROOT);
			if (newObject != null) {
				if (disableNotify)
					newObject.eSetDeliver(false);

				handleObjectAttribs(newObject);
			}
		} else {
			if (useNewMethods) {
				newObject = createObject(eFactory, helper.getType(eFactory,
						name), false);
			} else {
				newObject = createObjectFromFactory(eFactory, name);
			}
		}
		newObject = validateCreateObjectFromFactory(eFactory, name, newObject,
				top);

		if (top) {
			processTopObject(newObject);
			// check for simple feature
			if (extendedMetaData != null && newObject != null) {
				EStructuralFeature simpleFeature = extendedMetaData
						.getSimpleFeature(newObject.eClass());
				if (simpleFeature != null) {
					isSimpleFeature = true;
					isIDREF = simpleFeature instanceof EReference;
					objects.push(null);
					mixedTargets.push(null);
					types.push(simpleFeature);
					text = new StringBuffer();
				}
			}
		}
		return newObject;
	}
	
	boolean extendModel = false;
	StringBuffer buffer = new StringBuffer();

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		if(name.equals(ExtendModelConverterManager.MAP_STRING)){
			extendModel = true;
			buffer = new StringBuffer();
		}
		if(extendModel){
			buffer.append("<");
			buffer.append(name);
			for(int i = 0; i < attributes.getLength(); i++){
				buffer.append(" ");
				buffer.append(attributes.getQName(i));
				buffer.append("=\"");
				buffer.append(attributes.getValue(i));
				buffer.append("\"");
			}
			buffer.append(">");
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String name) {
		super.endElement(uri, localName, name);
		if(extendModel){
			buffer.append("</");
			buffer.append(name);
			buffer.append(">");
		}
		if(name.equals(ExtendModelConverterManager.MAP_STRING)){
			extendModel = false;
			options.put(AresXMLLoadImpl.EXTEND_STRING, buffer.toString());
			buffer = null;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		if(extendModel){
			for(int i = 0;i < length;i++){
				buffer.append(ch[start+i]);
			}
		}
	}

}
