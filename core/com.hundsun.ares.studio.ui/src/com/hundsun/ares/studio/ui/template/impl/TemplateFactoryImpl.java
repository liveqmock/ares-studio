/**
 */
package com.hundsun.ares.studio.ui.template.impl;

import com.hundsun.ares.studio.ui.template.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import com.hundsun.ares.studio.ui.template.Template;
import com.hundsun.ares.studio.ui.template.TemplateFactory;
import com.hundsun.ares.studio.ui.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TemplateFactoryImpl extends EFactoryImpl implements TemplateFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TemplateFactory init() {
		try {
			TemplateFactory theTemplateFactory = (TemplateFactory)EPackage.Registry.INSTANCE.getEFactory(TemplatePackage.eNS_URI);
			if (theTemplateFactory != null) {
				return theTemplateFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TemplateFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplateFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TemplatePackage.TEMPLATE: return createTemplate();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Template createTemplate() {
		TemplateImpl template = new TemplateImpl();
		return template;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplatePackage getTemplatePackage() {
		return (TemplatePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TemplatePackage getPackage() {
		return TemplatePackage.eINSTANCE;
	}
	
	public static void main(String[] args) {
		Template t1 = TemplateFactory.eINSTANCE.createTemplate();
		t1.setId("001");
		t1.setName("Demo4Start");
		t1.setPath("Demo4Start.zip");
		t1.setDescription("ARES Studio快速入门<br />" +
				"本工程系统的介绍ARES Studio基础平台及其二次扩展相关功能，包括：<br />" +
				"标准数据规范（标准字段、数据类型、数据字典）、数据库资源管理及SQL脚本生成（全量及增量）、基础数据、" +
				"业务服务接口信息描述、存储过程伪代码编辑及系统内置宏与用户自定义宏的使用。");
		
		Template t2 = TemplateFactory.eINSTANCE.createTemplate();
		t2.setId("002");
		t2.setName("Demo4CRES");
		t2.setPath("Demo4CRES.zip");
		t2.setDescription("ARES Studio4CRES快速入门<br />" +
				"本工程系统的介绍ARES Studio4CRES即CRES开发平台解决方案及其二次扩展相关功能，包括：<br />" +
				"业务逻辑（LS、LF）、原子逻辑（AS、AF、AP）伪代码编辑及系统内置宏与用户自定义宏的使用。");
		
		//ResourceSet resourceSet = new ResourceSetImpl();
		//resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("template", new XMLResourceFactoryImpl());
		//URI uri = URI.createURI("file:/c:/aaa.template");
		//Resource resource = resourceSet.createResource(uri);\
		Resource resource = new XMIResourceImpl();
//		EList<Template> templates  = new BasicEList<Template>();
//		templates.add(t1);
//		templates.add(t2);
		resource.getContents().add(t1);
		resource.getContents().add(t2);
		
		Map<String, String> options = new HashMap<String, String>();
		options.put(XMLResource.OPTION_ENCODING, "utf-8");
		
		try {
			resource.save(new FileOutputStream(new File("d:\\index.xml")) , options);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*List<Template> templates = new ArrayList<Template>();
		try {
			URL url = new URL("ftp://test:123456@192.168.94.92/index.xml");
			InputStream is = url.openStream();
			Resource res = new XMIResourceImpl();
			res.load(is, null);
			System.out.println(res.getContents());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

} //TemplateFactoryImpl
