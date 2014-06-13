/**
 * 源程序名称：BizPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.handlers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * @author sundl
 *
 */
public class BizPropertyHandler extends EMFPropertyHandler {

	/** 标志位 */
	public static IPropertyHandler PARAM_FLAG_PROPERTY_HANDLER = new EMFPropertyHandler(BizPackage.Literals.PARAMETER__FLAGS);
	/** id = 字段名，英文名 */
	public static IPropertyHandler PARAM_ID_PROPERTY_HANDLER = new EMFPropertyHandler(BizPackage.Literals.PARAMETER__ID);
	/** 名称，中文名 */
	public static IPropertyHandler PARAM_NAME_PROPERTY_HANDLER = new ParameterRefPropertyHandler(MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME, BizPackage.Literals.PARAMETER__NAME);
	/** 业务类型 */
	public static IPropertyHandler PARAM_TYPE_PROPERTY_HANDLER = new ParameterRefPropertyHandler(MetadataPackage.Literals.STANDARD_FIELD__DATA_TYPE, BizPackage.Literals.PARAMETER__TYPE);
	/** 默认值 */
	public static IPropertyHandler PARAM_DEFAULT_VALUE_HANDLER = new EMFPropertyHandler(BizPackage.Literals.PARAMETER__DEFAULT_VALUE);
	/** 数量关系 */
	public static IPropertyHandler PARAM_MULTIPLICITY_HANDLER = new MultiplicityPropertyHandler(BizPackage.Literals.PARAMETER__MULTIPLICITY);
	/**　备注　*/
	public static IPropertyHandler PARAM_COMMENTS_HANDLER = new EMFPropertyHandler(BizPackage.Literals.PARAMETER__COMMENTS);
	/** 描述 */
	public static IPropertyHandler PARAM_DESCRIPTION_HANDLER = new ParameterRefPropertyHandler(MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION, BizPackage.Literals.PARAMETER__DESCRIPTION);
	
	public static Map<String, IPropertyHandler> PARAM_BASIC_HANDLERS = new HashMap<String, IPropertyHandler>();
	
	static {
		PARAM_BASIC_HANDLERS.put("输入参数", PARAM_FLAG_PROPERTY_HANDLER);
		PARAM_BASIC_HANDLERS.put("输出参数", PARAM_FLAG_PROPERTY_HANDLER);
		PARAM_BASIC_HANDLERS.put("对象属性", PARAM_FLAG_PROPERTY_HANDLER);

		PARAM_BASIC_HANDLERS.put("参数名", PARAM_ID_PROPERTY_HANDLER);
		PARAM_BASIC_HANDLERS.put("中文名", PARAM_NAME_PROPERTY_HANDLER);
		PARAM_BASIC_HANDLERS.put("属性名", PARAM_ID_PROPERTY_HANDLER);
		PARAM_BASIC_HANDLERS.put("类型", PARAM_TYPE_PROPERTY_HANDLER);
		PARAM_BASIC_HANDLERS.put("参数类型", PARAM_TYPE_PROPERTY_HANDLER);
		PARAM_BASIC_HANDLERS.put("说明", PARAM_NAME_PROPERTY_HANDLER);
//		PARAM_BASIC_HANDLERS.put("缺省值", NullPropertyHandler.INSTANCE);
		PARAM_BASIC_HANDLERS.put("缺省值", PARAM_DEFAULT_VALUE_HANDLER);
		PARAM_BASIC_HANDLERS.put("默认值", PARAM_DEFAULT_VALUE_HANDLER);
		PARAM_BASIC_HANDLERS.put("长度", NullPropertyHandler.INSTANCE);
		PARAM_BASIC_HANDLERS.put("关系属性", PARAM_MULTIPLICITY_HANDLER);
		PARAM_BASIC_HANDLERS.put("备注", PARAM_COMMENTS_HANDLER);
		PARAM_BASIC_HANDLERS.put("描述", PARAM_DESCRIPTION_HANDLER);
		PARAM_BASIC_HANDLERS.put("数据类型", NullPropertyHandler.INSTANCE);
		PARAM_BASIC_HANDLERS.put("注释", PARAM_COMMENTS_HANDLER);
		
	}
	
	/**
	 * @param eAttribute
	 */
	public BizPropertyHandler(EAttribute eAttribute) {
		super(eAttribute);
	}

}
