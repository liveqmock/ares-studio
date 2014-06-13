/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.reference;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IObjectProvider;

/**
 * @author gongyf
 *
 */
public class EMFReferenceObjectProvider implements IObjectProvider {

	private static Logger logger = Logger.getLogger(EMFReferenceObjectProvider.class);
	
	private EClass eClass;
	private EReference reference;
	private int index;
	
	/**
	 * @param eClass
	 * @param reference
	 * @param index
	 */
	public EMFReferenceObjectProvider(EClass eClass, EReference reference,	int index) {
		super();
		this.eClass = eClass;
		this.reference = reference;
		this.index = index;
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.reference.IObjectProvider#getObject(com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	public Object getObject(IARESResource resource) {
		try {
			EObject eObj = (EObject)resource.getInfo(eClass.getInstanceClass());
			
			List<Object> list = (List<Object>) eObj.eGet(reference);
			
			return list.get(index);
			
		} catch (Exception e) {
			logger.error(String.format("引用信息读取资源%s具体info的时候出错...", resource.getElementName()), e);
		}
		return null;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		
		if (getClass().equals(obj.getClass())) {
			EMFReferenceObjectProvider other = (EMFReferenceObjectProvider) obj;
			return ObjectUtils.equals(eClass, other.eClass) && ObjectUtils.equals(reference, other.reference) && index == other.index;
		}
		return super.equals(obj);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(eClass).append(reference).append(index).toHashCode();
	}
}
