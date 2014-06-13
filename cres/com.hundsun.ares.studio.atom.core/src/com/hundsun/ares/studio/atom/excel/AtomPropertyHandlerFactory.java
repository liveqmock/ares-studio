/**
 * 源程序名称：AtomPropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.atom.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.atom.excel;

import java.util.HashMap;
import java.util.Map;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.excel.handlers.InterfacePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;

/**
 * @author sundl
 *
 */
public abstract class AtomPropertyHandlerFactory extends ExtensiblePropertyHandlerFactory {

	/** 参数属性处理对应列表 */
	public static Map<String, IPropertyHandler> ATOM_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();
	
	static {
		ATOM_PROPERTY_HANDLERS.putAll(InterfacePropertyHandlerFactory.INTERFACE_PROPERTY_HANDLERS); 
		ATOM_PROPERTY_HANDLERS.put("所属数据库", new EMFPropertyHandler(AtomPackage.Literals.ATOM_FUNCTION__DATABASE));
		ATOM_PROPERTY_HANDLERS.put("所连数据库", new EMFPropertyHandler(AtomPackage.Literals.ATOM_FUNCTION__DATABASE));//金融产品销售系统06香港版中格式。
		ATOM_PROPERTY_HANDLERS.put("业务处理流程", new EMFPropertyHandler(AtomPackage.Literals.ATOM_FUNCTION__PSEUDO_CODE));
		ATOM_PROPERTY_HANDLERS.put("出错说明", NullPropertyHandler.INSTANCE);
		ATOM_PROPERTY_HANDLERS.put("操作提示", NullPropertyHandler.INSTANCE);//金融产品销售系统06香港版中有此信息，需去除。
		
		ATOM_PROPERTY_HANDLERS.put("业务处理流程", new EMFPropertyHandler(AtomPackage.Literals.ATOM_FUNCTION__PSEUDO_CODE));
		ATOM_PROPERTY_HANDLERS.put("出错说明", NullPropertyHandler.INSTANCE);
		ATOM_PROPERTY_HANDLERS.put("操作提示", NullPropertyHandler.INSTANCE);//金融产品销售系统06香港版中有此信息，需去除。
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return ATOM_PROPERTY_HANDLERS;
	}

}
