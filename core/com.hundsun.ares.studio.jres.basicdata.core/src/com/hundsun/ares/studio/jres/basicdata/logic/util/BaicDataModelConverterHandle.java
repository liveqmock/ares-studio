/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.basicdata.logic.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle;
import com.hundsun.ares.studio.jres.modelconvert.ModelConverterUtils;

/**
 * @author gongyf
 *
 */
public class BaicDataModelConverterHandle extends ModelConverterHandle {
	
	protected Resource createEMFResource(URI uri) {
		return new XMIResourceImpl(uri);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle#handleRead(com.hundsun.ares.studio.core.IARESResource, byte[], java.lang.Class)
	 */
	@Override
	public Object handleRead(IARESResource resource, byte[] contents,
			Class<?> clazz) throws Exception {
		Resource emfRes = null;
		IResource rawRes = resource.getCorrespondingResource();
		if (rawRes == null) {
			emfRes = createEMFResource(null);
		} else {
			emfRes = createEMFResource(URI.createPlatformResourceURI(rawRes.getFullPath().toString(), true));
		}
		ResourceSet rsset = new ResourceSetImpl();
		rsset.getResources().add(emfRes);
		//添加动态模型
		EPackage tempPackagae = BasicDataEpackageFactory.eINSTANCE.createEPackage(resource);
		emfRes.getResourceSet().getPackageRegistry().put(tempPackagae.getNsURI(), tempPackagae);
		
		emfRes.load(new ByteArrayInputStream(contents), ModelConverterUtils.EMF_LOAD_OPTIONS);
		EObject info = emfRes.getContents().get(0);
		
//		Set<ExtensibleModel> models = new HashSet<ExtensibleModel>();
//		
//		if (info instanceof ExtensibleModel) {
//			models.add((ExtensibleModel) info);
//		}
//		
//		for (Iterator<Object> iterator = EcoreUtil.getAllContents(info, true); iterator.hasNext();) {
//			Object obj = iterator.next();
//			if (obj instanceof ExtensibleModel) {
//				models.add((ExtensibleModel) obj);
//			}
//		}
//		
//		for (ExtensibleModel extensibleModel : models) {
//			for (Iterator<Entry<String, EObject>> iterator = extensibleModel.getData2().iterator(); iterator.hasNext();) {
//				Entry<String, EObject> entry = iterator.next();
//				if (entry.getValue() == null) {
//					iterator.remove();
//				}
//			}
//		}
		
		return info;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle#handleWrite(com.hundsun.ares.studio.core.IARESResource, java.lang.Object)
	 */
	@Override
	public byte[] handleWrite(IARESResource resource, Object info)
			throws Exception {
		if (info instanceof EObject) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			EObject obj = (EObject) info;
			if (obj.eResource() == null) {
				Resource emfRes = new XMLResourceImpl();
				emfRes.getContents().add(obj);
			}
			obj.eClass().getEPackage();
//			BasicExtendedMetaData data =((EPackageImpl)obj.eClass().getEPackage()).getExtendedMetaData();
			obj.eResource().save(out, ModelConverterUtils.EMF_SAVE_OPTIONS);
			return out.toByteArray();
		} else {
			throw new UnsupportedOperationException("info对象不是EObject类型");
		}
	}

}
