/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.extend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.extend.RefExtendedPropertyProviderManager;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.ui.editor.extend.user.IUserExtendPropertyTypeProvider;
import com.hundsun.ares.studio.ui.editor.extend.user.UserExtendedPropertyTypeProviderDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.user.UserExtendedPropertyTypeManager;

/**
 * @author gongyf
 *
 */
public class UserExtensibleModelEditingSupport extends AbstractExtensibleModelEditingSupport {

	private static Logger logger = Logger.getLogger(UserExtensibleModelEditingSupport.class);
	
	/**
	 * 
	 */
	public UserExtensibleModelEditingSupport() {
	}

	@Override
	public String getName() {
		return "用户扩展";
	}

	@Override
	public String getKey() {
		return Constants.USER_DATA2_KEY;
	}

	@Override
	public boolean isEnable(IARESElement aresElement, EClass eClass) {
		for (Element element : getExsibleModelConfigList(aresElement)) {
			EClass clazz = getEClass(element.attributeValue("uri"), element.attributeValue("class"));
			if (clazz != null && clazz.isSuperTypeOf(eClass)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public EObject createMapValueObject() {
		return CoreFactory.eINSTANCE.createUserExtensibleProperty();
	}

	@Override
	public IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(IARESElement aresElement, EClass eClass) {
		List<IExtensibleModelPropertyDescriptor> descriptors = new ArrayList<IExtensibleModelPropertyDescriptor>();

		for (Element element : getExsibleModelConfigList(aresElement)) {
			EClass clazz = getEClass(element.attributeValue("uri"), element.attributeValue("class"));
			
			if (clazz != null && clazz.isSuperTypeOf(eClass)) {
				
				for (Element attributeElement : (List<Element>)element.elements("Attribute")) {
					String id = attributeElement.attributeValue("id");
					String name = attributeElement.attributeValue("name");
					String type = attributeElement.attributeValue("type");
					
					AbstractEMPropertyDescriptor attribute = null;
					if ("boolean".equalsIgnoreCase(type)) {
						attribute = new BooleanMapEMPropertyDescriptor(CorePackage.Literals.USER_EXTENSIBLE_PROPERTY__MAP, id);
					} else if ("bigstring".equalsIgnoreCase(type)) {
						attribute = new LongTextMapEMPropertyDescriptor(CorePackage.Literals.USER_EXTENSIBLE_PROPERTY__MAP, id);
					} else if ("combo".equalsIgnoreCase(type)) {
						String value = attributeElement.attributeValue("value");
						String[] values = null;
						if(StringUtils.isBlank(value)){
							values = ArrayUtils.EMPTY_STRING_ARRAY;
						}else {
							if(StringUtils.contains(value, ",")){
								values = value.split(",");
							}else {
								values = new String[]{value};
							}
						}
						
						attribute = new ComboMapEMPropertyDescriptor(values , CorePackage.Literals.USER_EXTENSIBLE_PROPERTY__MAP, id);
					}else if (StringUtils.equals(type, "ref")) {
						Map<String, String> config = new HashMap<String, String>();
						for (Object obj : attributeElement.attributes()) {
							Attribute attr = (Attribute) obj;
							config.put(attr.getName(), attr.getValue());
						}
						
						if (aresElement != null) {
							IARESProject project = aresElement.getARESProject();
							attribute = new RefExtendedPropertyDescriptor(config, project);
						}
					}  else {
						Collection<UserExtendedPropertyTypeProviderDescriptor> types = UserExtendedPropertyTypeManager.getInstance().get(type);
						if (!types.isEmpty()) {
							UserExtendedPropertyTypeProviderDescriptor typeDescriptor = types.iterator().next();
							IUserExtendPropertyTypeProvider typeProvider = typeDescriptor.get();
							Map<String, String> config = new HashMap<String, String>();
							for (Object obj : attributeElement.attributes()) {
								Attribute attr = (Attribute) obj;
								config.put(attr.getName(), attr.getValue());
							}
							
							IExtensibleModelPropertyDescriptor descriptor = typeProvider.createPropertyDescriptor(aresElement.getARESProject(), config); 
							if (descriptor != null) {
								descriptors.add(descriptor);
							}
						} else {
							attribute = new TextMapEMPropertyDescriptor(CorePackage.Literals.USER_EXTENSIBLE_PROPERTY__MAP, id);
						}
					}
					
					if (attribute != null) {
						attribute.setDisplayName(name);
						descriptors.add(attribute);
					} 
				}
				
			}
		}
		
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]);
	}

	private List<Element> getExsibleModelConfigList(IARESElement aresElement) {
		try {
			// FIXME 需要对XML解析结果进行缓存
			IARESBundle bundle = ARESElementUtil.getARESBundle(aresElement);
			ExtensibleModelConfigProperty config = (ExtensibleModelConfigProperty) bundle.getInfo().getMap().get("ExtensibleModelConfigProperty");
			if (config == null) {
				config = CoreFactory.eINSTANCE.createExtensibleModelConfigProperty();
			}
			
			String xml = config.getXml();
			
			if (StringUtils.isNotBlank(xml)) {
				Document doc = config.getXmlCache();
				if (doc != null) {
					return doc.getRootElement().elements("ExtensibleModel");
				}
			}
			
		} catch (Exception e) {
			
		}
		
		return Collections.emptyList();
	}

	private EClass getEClass(String uri, String className) {
		EPackage p = EPackage.Registry.INSTANCE.getEPackage(uri);
		if (p != null) {
			return (EClass) p.getEClassifier(className);
		}
		return null;
	}
	
//	private EClass createEClass(IARESBundle bundle) {
//		EClass myClass = classMap.get(bundle);
//		if (myClass == null) {
//			ExtensibleModelConfigProperty config = (ExtensibleModelConfigProperty) bundle.getInfo().getMap().get("ExtensibleModelConfigProperty");
//			if (config == null) {
//				config = CoreFactory.eINSTANCE.createExtensibleModelConfigProperty();
//			}
//			
////			<config>		
////			<ExtensibleModel class="">
////			  <attribute id="" name="测试" type="string" />
////			</ExtensibleModel>
////			</config>
//			
//			myClass = EcoreFactory.eINSTANCE.createEClass();
//			myClass.setName("User");
//			Document doc =  PersistentUtil.readDocument(new StringReader(config.getXml()));
//			
//			List<Element> elements = doc.getRootElement().elements("ExtensibleModel");
//			for (Element element : elements) {
//				String id = element.attributeValue("id");
//				String name = element.attributeValue("name");
//				String type = element.attributeValue("type");
//				
//				EAttribute attribute = EcoreFactory.eINSTANCE.createEAttribute();
//				attribute.setEType(EcorePackage.Literals.ESTRING);
//				attribute.setName(id);
//				attribute.setDefaultValue("");
//				
//				myClass.getEStructuralFeatures().add(attribute);
//			}
//			
////			EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
////			pack.setName("user");
////			pack.setNsPrefix("user");
////			pack.setNsURI("http://www.hundsun.com/ares/jres/user/1.0.0");
////			pack.getEClassifiers().add(myClass);
//			
////			EPackage.Registry.INSTANCE.put(pack.getNsURI(), pack);
//			
//			CorePackage.eINSTANCE.getEClassifiers().add(myClass);
//			
//			classMap.put(bundle, myClass);
////			EPackage.Registry.INSTANCE.put(StdFieldModelPackage.eNS_URI, StdFieldModelPackage.eINSTANCE);
//		}
//
//		
//		return myClass;
//		
//	}

}
