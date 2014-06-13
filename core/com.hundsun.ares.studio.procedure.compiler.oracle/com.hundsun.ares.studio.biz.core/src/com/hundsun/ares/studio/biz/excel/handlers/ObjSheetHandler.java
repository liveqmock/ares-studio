/**
 * 源程序名称：ObjSheetHandler.java
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

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizFactory;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.core.excel.Module;
import com.hundsun.ares.studio.core.excel.Resource;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.EMFSheetHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;

/**
 * @author sundl
 *
 */
public class ObjSheetHandler extends EMFSheetHandler {
	
	private static Map<String, IPropertyHandler> OBJECT_PROPERTY_HANDLERS = new HashMap<String, IPropertyHandler>();
	private static Map<String, IPropertyHandler> OBJECT_PROPERTY_PROPERTY_HANDLERS = new HashMap<String, IPropertyHandler>();
	
	static {
		OBJECT_PROPERTY_HANDLERS.putAll(EMFPropertyHandler.BASIC_HANDLERS);
		OBJECT_PROPERTY_HANDLERS.put("对象名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		OBJECT_PROPERTY_HANDLERS.put("对象中文名", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		OBJECT_PROPERTY_HANDLERS.put("对象描述", EMFPropertyHandler.DESCRIPTION_PROPERTY_HANDLER);
		OBJECT_PROPERTY_HANDLERS.put("说明", EMFPropertyHandler.DESCRIPTION_PROPERTY_HANDLER);
		OBJECT_PROPERTY_HANDLERS.put("修改记录", NullPropertyHandler.INSTANCE);
		//OBJECT_PROPERTY_HANDLERS.put("XML标签", NullPropertyHandler.INSTANCE);
		//OBJECT_PROPERTY_HANDLERS.putAll(EMFPropertyHandler.createExtendedHandlers(BizPackage.Literals.ARES_OBJECT));
		
		OBJECT_PROPERTY_PROPERTY_HANDLERS.putAll(BizPropertyHandler.PARAM_BASIC_HANDLERS);
		OBJECT_PROPERTY_PROPERTY_HANDLERS.put("对象属性", BizPropertyHandler.PARAM_FLAG_PROPERTY_HANDLER);
		OBJECT_PROPERTY_PROPERTY_HANDLERS.put("类型", BizPropertyHandler.PARAM_TYPE_PROPERTY_HANDLER);
		//OBJECT_PROPERTY_PROPERTY_HANDLERS.put("XML标签", NullPropertyHandler.INSTANCE);
	}

	protected ARESObject obj;
	
	@Override
	public void startSheet(HSSFSheet sheet) {
		super.startSheet(sheet);
		module = new Module();
		//中文分隔符—也支持
		String sheetName = StringUtils.replace(sheet.getSheetName(), "—", "-");
		String cName = StringUtils.removeStart(sheetName, "对象-");
		cName = StringUtils.removeStart(cName, "业务对象-");
		module.cName = cName;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endArea()
	 */
	public void endArea() {
		super.endArea();
		if (obj != null) {
			Resource resource = new Resource();
			resource.info = obj;
			resource.name = obj.getName();
			resource.type = IBizResType.Object;
			resourceFound(resource);
		}
	}
	
	@Override
	public void startArea(String startTag) {
		super.startArea(startTag);
		if (StringUtils.startsWith(startTag, "对象名"))
			obj = BizFactory.eINSTANCE.createARESObject();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.EMFSheetHandler#getPropertyHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getPropertyHandlers() {
		if (StringUtils.equals(parser.getCurrentBlockTag(), "对象属性")) {
			return OBJECT_PROPERTY_PROPERTY_HANDLERS;
		}
		return OBJECT_PROPERTY_HANDLERS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.EMFSheetHandler#getOwner()
	 */
	@Override
	protected EObject getOwner() {
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.EMFSheetHandler#getTableFeature()
	 */
	@Override
	protected EStructuralFeature getTableFeature() {
		return BizPackage.Literals.ARES_OBJECT__PROPERTIES;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.EMFSheetHandler#getEClass()
	 */
	@Override
	protected EClass getEClass() {
		return BizPackage.Literals.PARAMETER;
	}

}
