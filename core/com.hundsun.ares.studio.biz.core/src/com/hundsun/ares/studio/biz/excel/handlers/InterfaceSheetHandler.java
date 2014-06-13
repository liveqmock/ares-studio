/**
 * 源程序名称：InterfaceListHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.handlers;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.excel.ISheetHandler;
import com.hundsun.ares.studio.core.excel.handler.EMFSheetHandler;

/**
 * 基于接口的Sheet页面的处理类
 * @author sundl
 *
 */
public abstract class InterfaceSheetHandler extends EMFSheetHandler implements ISheetHandler{
	
	public static Logger logger = Logger.getLogger(InterfaceSheetHandler.class);
	
	/* 转移到对应的PropertyHandlerFactory中 ---> InterfaceHandlerFactory
	static {
		// 基于Interface的一套属性和处理器列表； 子类尽量不要直接像这些map中加数据，以免子类之间互相干扰
		INTERFACE_PROPERTY_HANDLERS.put("对象号", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID));
		// 确定不需处理的属性，用NullPropertyHandler避免报错
		INTERFACE_PROPERTY_HANDLERS.put("版本号", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("更新日期", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("服务名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("服务名称", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("功能名称", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("服务说明", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("功能说明", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("函数名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("函数名称", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("函数说明", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("过程名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("过程名称", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("过程说明", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("结果集返回", new BooleanEMFPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION));
		INTERFACE_PROPERTY_HANDLERS.put("接口标志", new EMFPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__INTERFACE_FLAG));
		INTERFACE_PROPERTY_HANDLERS.put("说明", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION));
		INTERFACE_PROPERTY_HANDLERS.put("功能号", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("所属数据库", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("操作角色", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("需求类别", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("需求编号", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("需求级别", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("业务描述", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION));
		//2014-01-28 modified by zhuyf 添加功能描述（导入对外接口，时文档中有此格式信息）
		INTERFACE_PROPERTY_HANDLERS.put("功能描述", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION));
		INTERFACE_PROPERTY_HANDLERS.put("出错说明", NullPropertyHandler.INSTANCE);
		//2014-04-17 modified by zhuyf 添加操作提示（导入金融产品销售系统06香港版中有此信息，需去除。）
		INTERFACE_PROPERTY_HANDLERS.put("操作提示", NullPropertyHandler.INSTANCE);
		//2014-01-28 modified by zhuyf 添加业务说明（导入对外接口，时文档中有此格式信息）
		INTERFACE_PROPERTY_HANDLERS.put("业务说明", NullPropertyHandler.INSTANCE);
		
		INTERFACE_PROPERTY_HANDLERS.put("修改记录", NullPropertyHandler.INSTANCE);
		
		INTERFACE_PROPERTY_HANDLERS.put("变量", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("内部变量", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("流程变量", NullPropertyHandler.INSTANCE);

		// 参数的:
		PARAM_PROPERTY_HANDLERS.putAll(BizPropertyHandler.PARAM_BASIC_HANDLERS);
	} */
	
	protected EClass getEClass() {
		if (isInputParmaBlock() || isOutputParmaBlock()) {
			return BizPackage.Literals.PARAMETER;
		} else if (isErrorInfoBlock()) {
			return BizPackage.Literals.ERROR_INFO;
		}
		return BizPackage.Literals.BIZ_INTERFACE;
	}
	
	@Override
	protected EStructuralFeature getTableFeature() {
		if (isInputParmaBlock()) {
			return BizPackage.Literals.BIZ_INTERFACE__INPUT_PARAMETERS;
		} else if (isOutputParmaBlock()) {
			return BizPackage.Literals.BIZ_INTERFACE__OUTPUT_PARAMETERS;
		} else if (isErrorInfoBlock()) {
			return BizPackage.Literals.BIZ_INTERFACE__ERROR_INFOS;
		}
		return null;
	}

	/** 判断当前是否在输入参数Block中 */
	protected boolean isInputParmaBlock() {
		return StringUtils.equals(parser.getCurrentBlockTag(), "输入参数");
	}
	
	/** 判断当前是否在输入参数Block中 */
	protected boolean isOutputParmaBlock() {
		return StringUtils.equals(parser.getCurrentBlockTag(), "输出参数");
	}
	
	protected boolean isErrorInfoBlock() {
		return StringUtils.equals(parser.getCurrentBlockTag(), "出错说明");
	}
	
}
