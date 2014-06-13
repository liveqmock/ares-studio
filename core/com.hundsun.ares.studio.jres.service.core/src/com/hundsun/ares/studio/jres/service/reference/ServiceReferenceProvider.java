/**
 * 源程序名称：ServiceReferenceProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.service.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.service.reference;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicEList;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.IReferenceProvider2;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceImpl;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.service.Service;

/**
 * @author sundl
 *
 */
public class ServiceReferenceProvider implements IReferenceProvider2 {
	
	public static ServiceReferenceProvider INSTANCE = new ServiceReferenceProvider();
	
	private ServiceReferenceProvider() {}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.IReferenceProvider2#getReferences(java.lang.Object, com.hundsun.ares.studio.core.IARESProject)
	 */
	@Override
	public List<Reference> getReferences(Object obj, IARESProject aresProject) {
		List<Reference> references = new ArrayList<Reference>();
		if (obj instanceof Service) {
			Service service = (Service) obj;
			BasicEList<Parameter> parametesrs = new BasicEList<Parameter>();
			parametesrs.addAll(service.getInterface().getInputParameters());//添加输入参数
			parametesrs.addAll(service.getInterface().getOutputParameters());//添加输出参数
			
			for(Parameter parametesr:parametesrs){
				Reference ref = null;
				// 根据参数类型的不同，引用的类型也不一样
				switch (parametesr.getParamType()) {
					case STD_FIELD:
						if (!StringUtils.isEmpty(parametesr.getId())) 
							ref = new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdField, parametesr, BizPackage.Literals.PARAMETER__ID);
						break;
					case OBJECT:
					case PARAM_GROUP:
						// 如果使用对象标准字段，字段名引用对象标准字段
						if (BizUtil.hasStdObjList(aresProject)) {
							if (!StringUtils.isEmpty(parametesr.getId())) {
								ref = new TextAttributeReferenceImpl(IBizRefType.Std_Obj, parametesr, BizPackage.Literals.PARAMETER__ID);
							}
						} else 	if (!StringUtils.isEmpty(parametesr.getType())) {
							// 不使用对象标准字段的情况下，类型引用对象资源
							ref = new TextAttributeReferenceImpl(IBizRefType.Object, parametesr, BizPackage.Literals.PARAMETER__TYPE);
						}
						break;
					default:
						break;
				}
				
				if (ref != null)
					references.add(ref);
			}
		}
		return references;
	}

}
