/**
 * 源程序名称：ParameterRefPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.handlers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.reference.RefEMFPropertyHandler;

/**
 * 参数的中文名，说明等引用信息的RefEMFPropertyHandler。 
 * 单独实现类是为了实现区分标准字段类型的参数和非标准字段类型的参数，如果不需要区分可以直接使用
 * RefEMFPropertyHandler
 * @author sundl
 *
 */
public class ParameterRefPropertyHandler extends RefEMFPropertyHandler {

	public ParameterRefPropertyHandler(EStructuralFeature targetFeature, EStructuralFeature feature) {
		super(BizPackage.Literals.PARAMETER__ID, null, targetFeature, feature);
	}
	
	protected String getRefType(EObject object) {
		if (object instanceof Parameter) {
			Parameter p = (Parameter) object;
			switch (p.getParamType()) {
			case STD_FIELD:
				return IMetadataRefType.StdField;	// 引用标准字段
			case OBJECT:
				return IBizRefType.Object;			// 引用对象
			case NON_STD_FIELD:
			case COMPONENT:
				return null;
			default:
				break;
			}
		}
		return null;
	}
	
	@Override
	protected EStructuralFeature getFeature(EObject object) {
		if (object instanceof Parameter) {
			Parameter p = (Parameter) object;
			switch (p.getParamType()) {
			case OBJECT:
				EStructuralFeature feature = super.getFeature(object);
				if (feature == MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME) {
					feature = CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME;
				} else if (feature == MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION) {
					feature = CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION;
				}
				return feature;
			default:
				return super.getFeature(object);
			}
		}
		return super.getFeature(object);
	}
	
}
