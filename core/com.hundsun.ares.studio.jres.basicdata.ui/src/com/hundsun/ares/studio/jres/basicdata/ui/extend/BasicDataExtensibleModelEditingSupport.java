package com.hundsun.ares.studio.jres.basicdata.ui.extend;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty;
import com.hundsun.ares.studio.core.util.PersistentUtil;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicdataConstants;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.ui.editor.extend.AbstractEMPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.AbstractExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.BooleanMapEMPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.ComboMapEMPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.LongTextMapEMPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.RefExtendedPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.TextMapEMPropertyDescriptor;


public class BasicDataExtensibleModelEditingSupport extends AbstractExtensibleModelEditingSupport {
	
	@Override
	public String getName() {
		return "基础数据扩展";
	}

	@Override
	public String getKey() {
		return IBasicdataConstants.BASICDATA_EXTEND_KEY;
	}

	@Override
	public EObject createMapValueObject() {
		return CoreFactory.eINSTANCE.createUserExtensibleProperty();
	}
	
	@Override
	public boolean isEnable(IARESElement aresElement, EClass eClass) {
		//资源有可能不存在
		if(null == aresElement) {
			return false;
		}
		if(aresElement instanceof IARESResource){
			IARESResource resource = (IARESResource)aresElement;
			try {
				BasicDataBaseModel info = resource.getInfo(BasicDataBaseModel.class);
				if(info != null){
					//本身编辑器中的扩展模型（本身扩展模型无需写明uri，只需判断对应class是否存在）
					EPackage epackage = info.eClass().getEPackage();
					for(Element element : getExsibleModelConfigList(info)){
						EClass cls = (EClass) epackage.getEClassifier(element.attributeValue("class"));
						if(cls != null && cls.isSuperTypeOf(eClass)){
							return true;
						}
					}
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		//项目属性中的扩展模型，除了判断对应class是否存在，需要判断uri是否一致
		for(Element element : getProjectExsibleModelConfigList(aresElement.getARESProject())){
			EClass cls = (EClass) eClass.getEPackage().getEClassifier(element.attributeValue("class"));
			if(cls != null && cls.isSuperTypeOf(eClass) && StringUtils.equalsIgnoreCase( eClass.getEPackage().getNsURI(), element.attributeValue("uri"))){
				return true;
			}
		}
		return false;
	}

	@Override
	public IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(
			IARESElement aresElement, EClass eClass) {
		List<IExtensibleModelPropertyDescriptor> descriptors = new ArrayList<IExtensibleModelPropertyDescriptor>();
		//防止重复id出现，如果本身扩展和项目扩展有一样的id，则只取本身的扩展
		Set<String> idSet = new HashSet<String>();
		if(aresElement instanceof IARESResource){
			try {
				BasicDataBaseModel info = ((IARESResource)aresElement).getInfo(BasicDataBaseModel.class);
				//自身编辑器中的扩展属性			
				for (Element element : getExsibleModelConfigList(info)) {
					descriptors.addAll(getPropertyDescriptors(element, eClass, aresElement.getARESProject(),idSet));
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		
		//项目属性中定义的扩展属性
		EPackage epackage = eClass.getEPackage();
		for(Element element : getProjectExsibleModelConfigList(aresElement.getARESProject())){
			if(StringUtils.equalsIgnoreCase(epackage.getNsURI(), element.attributeValue("uri"))){
				descriptors.addAll(getPropertyDescriptors(element, eClass, aresElement.getARESProject(),idSet));
			}
		}
		
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]) ;
	}
	
	/**
	 * 获取扩展属性列相关
	 * @param element
	 * @param eClass
	 * @param project
	 * @return
	 */
	private List<IExtensibleModelPropertyDescriptor> getPropertyDescriptors(Element element,EClass eClass,IARESProject project,Set<String>idSet){
		EClass cls = (EClass) eClass.getEPackage().getEClassifier(element.attributeValue("class"));
		List<IExtensibleModelPropertyDescriptor> descriptors = new ArrayList<IExtensibleModelPropertyDescriptor>();
		
		if(cls != null && cls.isSuperTypeOf(eClass)){
			for (Element attributeElement : (List<Element>)element.elements("Attribute")) {
				String name = attributeElement.attributeValue("name");
				String id = attributeElement.attributeValue("id");
				String type = attributeElement.attributeValue("type");
				if(idSet.contains(id)){
					//已经存在了，则下一个
					continue;
				}
				idSet.add(id);
				
				AbstractEMPropertyDescriptor attribute = null;
				if ("boolean".equalsIgnoreCase(type)) {
					attribute = new BooleanMapEMPropertyDescriptor(CorePackage.Literals.USER_EXTENSIBLE_PROPERTY__MAP, id,getKey());
				} else if ("bigstring".equalsIgnoreCase(type)) {
					attribute = new LongTextMapEMPropertyDescriptor(CorePackage.Literals.USER_EXTENSIBLE_PROPERTY__MAP, id,getKey());
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
					
					attribute = new ComboMapEMPropertyDescriptor(values , CorePackage.Literals.USER_EXTENSIBLE_PROPERTY__MAP, id,getKey());
				}else if (StringUtils.equals(type, "ref")) {
					Map<String, String> config = new HashMap<String, String>();
					for (Object obj : attributeElement.attributes()) {
						Attribute attr = (Attribute) obj;
						config.put(attr.getName(), attr.getValue());
					}
					
					attribute = new RefExtendedPropertyDescriptor(config, project);
				} else {
					attribute = new TextMapEMPropertyDescriptor(CorePackage.Literals.USER_EXTENSIBLE_PROPERTY__MAP, id,getKey());
				}
				if (attribute != null) {
					attribute.setDisplayName(name);
					descriptors.add(attribute);
				} 
			}
		}
		
		return descriptors;
	}
	
	//获取当前编辑器中所有的扩展属性定义
	private List<Element> getExsibleModelConfigList(BasicDataBaseModel model) {
		List<Element> elements = new ArrayList<Element>();
		try {
			//解析本身模型中的扩展
			if(model != null){
				String xml = model.getExtend();
				
				if (StringUtils.isNotBlank(xml)) {
					Document doc = PersistentUtil.readDocument(new StringReader(xml));
					if (doc != null) {
						elements.addAll(doc.getRootElement().elements("ExtensibleModel"));
					}
				}
			}
		} catch (Exception e) {
			
		}
		return elements;
	}
	//获取当前项目属性中的所有扩展属性定义
	private List<Element> getProjectExsibleModelConfigList(IARESProject project) {
		List<Element> elements = new ArrayList<Element>();
		try {
			//解析项目属性中的扩展
			ExtensibleModelConfigProperty config = (ExtensibleModelConfigProperty) project.getInfo().getMap().get("ExtensibleModelConfigProperty");
			if (config == null) {
				config = CoreFactory.eINSTANCE.createExtensibleModelConfigProperty();
			}
			
			String xml = config.getXml();
			
			if (StringUtils.isNotBlank(xml)) {
				Document doc = config.getXmlCache();
				if (doc != null) {
					elements.addAll(doc.getRootElement().elements("ExtensibleModel"));
				}
			}
			
		} catch (Exception e) {
			
		}
		return elements;
	}
	
}
