/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.modelanddata;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle;
import com.hundsun.ares.studio.jres.modelconvert.ModelConverterUtils;

/**
 * @author lvgao
 *
 */
public class StandardFieldModelAndDataConventeHandle  extends ModelConverterHandle{

	public static final String ROOT = "modelanddata";
	public static final String MODEL = "model";
	public static final String DATA = "data";
	
	
	
	protected Resource createEMFResource(URI uri) {
		return new XMIResourceImpl(uri);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle#handleRead(com.hundsun.ares.studio.core.IARESResource, byte[], java.lang.Class)
	 */
	@Override
	public Object handleRead(IARESResource resource, byte[] contents,
			Class<?> clazz) throws Exception {
		
		String encoding = (String)ModelConverterUtils.EMF_SAVE_OPTIONS.get(XMLResource.OPTION_ENCODING);
		SAXReader reader = new SAXReader();
		reader.setEncoding(encoding);
		
		
		//这里保证root类对一个相应的EMF的resource
		Resource emfResRoot = null;
		IResource rawRes = resource.getCorrespondingResource();
		if (rawRes == null) {
			emfResRoot = createEMFResource(null);
		} else {
			emfResRoot = createEMFResource(URI.createPlatformResourceURI(rawRes.getFullPath().toString(), true));
		}
		ResourceSet rootRsSet = new ResourceSetImpl();
		rootRsSet.getResources().add(emfResRoot);
		
		StandardFieldModelAndData info = BasicdataFactory.eINSTANCE.createStandardFieldModelAndData();
		//添加模型
		emfResRoot.getContents().add(info);
		
		
		
		try {
			Document doc = reader.read(new ByteArrayInputStream(contents));
			
			//////////////////////////////////////模型读取////////////////////////////////////////////
			Element modelElement  =   doc.getRootElement().element("model");
			String modelText = modelElement.getText();
			
			Resource emfModelRes = new XMLResourceImpl();
			handleEMFRead(emfModelRes, modelText.getBytes(encoding));
			Object obj =  emfModelRes.getContents().get(0);
			if(null == obj){
				throw new Exception(String.format("读取资源[%s]模型定义失败.", resource.getResource().getFullPath().toOSString()));
			}
			StandardFieldPackageDefine define = (StandardFieldPackageDefine)obj;
			info.setModel(define);
			emfModelRes.getContents().clear();
			emfResRoot.getContents().add(define);
			
			//////////////////////////////////////动态建模////////////////////////////////////////////
			EPackage  epackage =  StandardFieldModelAndDataEpackageFactory.instance.createEPackage(resource.getARESProject(), define);
			ResourceSet rsset = new ResourceSetImpl();
			Resource emfDataRes = new XMLResourceImpl();
			rsset.getResources().add(emfDataRes);
			emfDataRes.getResourceSet().getPackageRegistry().put(epackage.getNsURI(), epackage);
			
			//////////////////////////////////////数据读取////////////////////////////////////////////
			EObject dataObj = null;
			//删除模型为空时创建默认的数据对象，为了保留数据对象中中文名等基本信息
//			if(define.getFields().isEmpty()){
//				dataObj = BasicDataEpackageFactory.eINSTANCE.createInstance(epackage);
//			}else{
				Element dataElement  =   doc.getRootElement().element("data");
				String dataText = dataElement.getText();
				handleEMFRead(emfDataRes, dataText.getBytes(encoding));
				dataObj =  emfDataRes.getContents().get(0);
				if(null == dataObj){
					dataObj = BasicDataEpackageFactory.eINSTANCE.createInstance(epackage);
				}
//			}
			//数据模型中填入资源名
			((BasicDataBaseModel)dataObj).setName(resource.getName());
			//设定数据对象
			info.setData((BasicDataBaseModel)dataObj);
			
			emfDataRes.getContents().clear();
			emfResRoot.getContents().add(dataObj);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle#handleWrite(com.hundsun.ares.studio.core.IARESResource, java.lang.Object)
	 */
	@Override
	public byte[] handleWrite(IARESResource resource, Object info)
			throws Exception {
		if(! (info instanceof StandardFieldModelAndData)){
			return null;
		}
		String encoding = (String)ModelConverterUtils.EMF_SAVE_OPTIONS.get(XMLResource.OPTION_ENCODING);
		
		StandardFieldModelAndData modelanddata = (StandardFieldModelAndData)info;
		
		//准备XML文件
		Element root = DocumentFactory.getInstance().createElement(ROOT);
		Document doc = DocumentFactory.getInstance().createDocument(root);
		doc.setXMLEncoding(encoding);
		
	    Element model =DocumentFactory.getInstance().createElement(MODEL);
	    root.add(model);
	    Element data = DocumentFactory.getInstance().createElement(DATA);
	    root.add(data);
	    
	    if(null == modelanddata.getModel()){
	    	modelanddata.setModel(BasicdataFactory.eINSTANCE.createStandardFieldPackageDefine());
	    }
	    
	    if(null == modelanddata.getData()){
	    	EPackage  epackage =  StandardFieldModelAndDataEpackageFactory.instance.
	    	createEPackage(resource.getARESProject(), modelanddata.getModel());
	    	EObject dataObj = BasicDataEpackageFactory.eINSTANCE.createInstance(epackage);
	    	modelanddata.setData((BasicDataBaseModel)dataObj);
	    }
		
	    //获取模型和数据
	    byte[] modelbuf = handleEMFWrite(modelanddata.getModel());
	    byte[] databuf = handleEMFWrite(modelanddata.getData());
		
	    //设定值
	    model.add(DocumentFactory.getInstance().createCDATA(new String(modelbuf,encoding)));
	    data.add(DocumentFactory.getInstance().createCDATA(new String(databuf,encoding)));
	    
	    //输出流
	    org.apache.commons.io.output.ByteArrayOutputStream out = new org.apache.commons.io.output.ByteArrayOutputStream();
	    
	    //格式化
	    OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding);
	    
		//写入数据
	    XMLWriter writer = new XMLWriter(out, format);
	    writer.write(doc);
	    writer.flush();
		return out.toByteArray();
	}
	
	
	public Object handleEMFRead(Resource emfRes, byte[] contents) throws Exception {
//		Resource emfRes = null;
//		IResource rawRes = resource.getCorrespondingResource();
//		if (rawRes == null) {
//			emfRes = createEMFResource(null);
//		} else {
//			emfRes = createEMFResource(URI.createPlatformResourceURI(rawRes.getFullPath().toString(), true));
//		}
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.putAll(ModelConverterUtils.EMF_LOAD_OPTIONS);
		emfRes.load(new ByteArrayInputStream(contents), options);
		EObject info = emfRes.getContents().get(0);
		
		return info;
	}


	public byte[] handleEMFWrite( EObject info)
			throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		Resource emfRes = new XMLResourceImpl();
		EObject eobj = EcoreUtil.copy(info);
		emfRes.getContents().add(eobj);
		
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.putAll(ModelConverterUtils.EMF_SAVE_OPTIONS);
		eobj.eResource().save(out, options);
		return out.toByteArray();
	}

}
