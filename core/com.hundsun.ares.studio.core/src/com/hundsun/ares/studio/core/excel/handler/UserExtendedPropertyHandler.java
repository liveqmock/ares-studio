/**
 * 源程序名称：UserExtendedPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import com.hundsun.ares.studio.core.excel.SheetParser;
import com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor;


/**
 * @author sundl
 *
 */
public class UserExtendedPropertyHandler extends ExtendedPropertyHandler {

	private SheetParser sheetParser;
	
	public UserExtendedPropertyHandler(SheetParser sheetParser, IBasicExtendPropertyDescriptor descriptor) {
		super(descriptor);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#setValue(java.lang.Object, java.lang.String)
	 */
	@Override
	public void setValue(Object obj, String value) {
		//sheetParser.exlParser.stdFieldModifyCommands;
	}

}
