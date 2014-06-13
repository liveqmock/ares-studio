/**
 * 源程序名称：RevisionHistoryPropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.model.CorePackage;

/**
 * 
 * @author sundl
 */
public class RevisionHistoryPropertyHandlerFactory extends ExtensiblePropertyHandlerFactory {
	
	public static final RevisionHistoryPropertyHandlerFactory INSTANCE = new RevisionHistoryPropertyHandlerFactory();

	public static final Map<String, IPropertyHandler> HIS_PROPERTY_HANDLERS = new HashMap<String, IPropertyHandler>();
	
	static {
		HIS_PROPERTY_HANDLERS.put("修改版本", new HisVersionPropertyHandler());
		HIS_PROPERTY_HANDLERS.put("修订日期", new EMFPropertyHandler(CorePackage.Literals.REVISION_HISTORY__MODIFIED_DATE));
		HIS_PROPERTY_HANDLERS.put("修改内容", new EMFPropertyHandler(CorePackage.Literals.REVISION_HISTORY__MODIFIED));
		HIS_PROPERTY_HANDLERS.put("修改单编号", new EMFPropertyHandler(CorePackage.Literals.REVISION_HISTORY__ORDER_NUMBER));
		HIS_PROPERTY_HANDLERS.put("申请人", new EMFPropertyHandler(CorePackage.Literals.REVISION_HISTORY__MODIFIED_BY));
		HIS_PROPERTY_HANDLERS.put("负责人", new EMFPropertyHandler(CorePackage.Literals.REVISION_HISTORY__CHARGER));
		HIS_PROPERTY_HANDLERS.put("修改人", new EMFPropertyHandler(CorePackage.Literals.REVISION_HISTORY__CHARGER));

		HIS_PROPERTY_HANDLERS.put("业务范围", NullPropertyHandler.INSTANCE);
		HIS_PROPERTY_HANDLERS.put("功能状态", NullPropertyHandler.INSTANCE);
		HIS_PROPERTY_HANDLERS.put("对应产品", NullPropertyHandler.INSTANCE);
		HIS_PROPERTY_HANDLERS.put("与上版本是否兼容", NullPropertyHandler.INSTANCE);
		HIS_PROPERTY_HANDLERS.put("预计发布版本", NullPropertyHandler.INSTANCE);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return HIS_PROPERTY_HANDLERS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return CorePackage.Literals.REVISION_HISTORY;
	}

}
