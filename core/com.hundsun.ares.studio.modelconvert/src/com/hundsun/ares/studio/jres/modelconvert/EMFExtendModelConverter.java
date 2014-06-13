/**
 * 源程序名称：EMFExtendModelConverter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.modelconvert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.converter.IExtendModelConverter;

/**
 * @author gongyf
 * @author sundl 从UI插件移动到ModelConverter插件
 */
public class EMFExtendModelConverter implements IExtendModelConverter {

	private Logger logger = Logger.getLogger(getClass());
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IExtendModelConverter#writeExtendModel(java.lang.Object, org.dom4j.Element)
	 */
	@Override
	public void writeExtendModel(Object extendModel, Element valueRoot) {
		Assert.isTrue(extendModel instanceof EObject);

		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			EObject eObj = (EObject) extendModel;
			if (eObj.eResource() == null) {
				XMLResource resource = new XMLResourceImpl();
				resource.getContents().add(eObj);
			}
			eObj.eResource().save(outputStream, ModelConverterUtils.EMF_SAVE_OPTIONS);
			String tmp = outputStream.toString(ModelConverterUtils.CODING_TYPE_UTF_8);
			valueRoot.addCDATA(tmp);
		} catch (Exception e) {
			logger.error("将工程扩展模型写出时发生错误", e);
		}
	}

	protected void prepareEPackageRegistry() {
		EPackage.Registry.INSTANCE.put(CorePackage.eNS_URI, CorePackage.eINSTANCE);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IExtendModelConverter#readExtendModel(org.dom4j.Element)
	 */
	@Override
	public Object readExtendModel(Element element) {

		String content =  element.getText();
		if(null == content|| "".equals(content)){
			return null;
		}
		
		prepareEPackageRegistry();
		
		Resource resource = new XMLResourceImpl(URI.createURI(getClass().getName()));
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(ModelConverterUtils.CODING_TYPE_UTF_8)); 
			resource.load(inputStream, ModelConverterUtils.EMF_LOAD_OPTIONS);
			return resource.getContents().get(0);
		} catch (Exception e) {
			logger.error("将工程扩展模型读入时发生错误", e);
		}

		return null;
	}

}
