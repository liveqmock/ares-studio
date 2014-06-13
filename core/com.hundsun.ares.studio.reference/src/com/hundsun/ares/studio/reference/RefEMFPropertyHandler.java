/**
 * 源程序名称：RefEMFPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.reference;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler2;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;

/**
 * 这个Handler的getValue方法根据给定的Object和属性，refType等信息，找到对应的引用对象，返回目标对象的某个属性。
 * 比如参数的中文名实际上是应该引用标准字段的中文名属性，这个Handler可以实现这样的需求.
 * @author sundl
 */
public class RefEMFPropertyHandler extends EMFPropertyHandler implements IPropertyHandler2{

	private IARESProject project;
	private EStructuralFeature refIdFeature;
	private EStructuralFeature targetFeature;
	private String refType;
	
	/**
	 * @param refIdFeature 确定引用id的属性；比如参数的id，用来确定引用哪个标准字段
	 * @param refType 引用类型ID，比如引用标准字段，就是标准字段的refType
	 * @param targetFeature 目标对象的feature，比如引用标准字段的中文名属性，就是CName这个Feature.
	 * @param feature 同EMFPropertyHandler的feature参数，对于这种引用类型的情况，这个用于setValue, 其他参数用于getValue
	 */
	public RefEMFPropertyHandler(EStructuralFeature refIdFeature, String refType, EStructuralFeature targetFeature, EStructuralFeature feature) {
		super(feature);
		this.refIdFeature = refIdFeature;
		this.targetFeature = targetFeature;
		this.refType = refType;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.IPropertyHandler2#setProject(com.hundsun.ares.studio.core.IARESProject)
	 */
	@Override
	public void setProject(IARESProject project) {
		this.project = project;
	}
	
	@Override
	public String getValue(Object obj) {
		EObject owner = getOwner(obj);
		if (owner != null) {
			Object value = ((EObject) owner).eGet(getTargetFeature(owner));
			if (value != null) {
				return String.valueOf(value);
			} else {
				return StringUtils.EMPTY;
			}
		}
		return super.getValue(obj);
	}
	
	protected EStructuralFeature getTargetFeature(EObject object) {
		return targetFeature;
	}
	
	protected EObject getOwner(Object object) {
		if (object instanceof EObject) {
			EObject eObject = (EObject) object;
			EStructuralFeature refIdFeature = getRefIdFeature(eObject);
			if (refIdFeature == null)
				return null;
			
			String refName = String.valueOf(((EObject) object).eGet(refIdFeature));
			ReferenceManager manager = ReferenceManager.getInstance();
			String refType = getRefType(eObject);
			if (refType == null) {
				return null;
			}
			
			ReferenceInfo refInfo = manager.getFirstReferenceInfo(project, refType, refName, false);
			if (refInfo != null) {
				Object owner = refInfo.getObject();
				if (owner instanceof EObject) {
					return (EObject) owner;
				}
			}
		}
		return null;
	}
	
	protected EStructuralFeature getRefIdFeature(EObject object) {
		return this.refIdFeature;
	}
	
	protected String getRefType(EObject object) {
		return this.refType;
	}

}
