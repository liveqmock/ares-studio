package com.hundsun.ares.studio.jres.service.ui.wizard;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.biz.excel.handlers.InterfaceSheetHandler;
import com.hundsun.ares.studio.core.excel.Module;
import com.hundsun.ares.studio.core.excel.Resource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.service.Service;
import com.hundsun.ares.studio.jres.service.ServiceFactory;
import com.hundsun.ares.studio.jres.service.ServicePackage;

public class ServiceSheetHandler extends InterfaceSheetHandler{

	/** 转移到对应的PropertyHandlerFactory中 ---> ServiceHandlerFactory
	static{
		SERVICE_PROPERTY_HANDLERS.putAll(INTERFACE_PROPERTY_HANDLERS);
		SERVICE_PROPERTY_HANDLERS.put("功能号", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID));
		SERVICE_PROPERTY_HANDLERS.put("功能名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		//2014-01-28 modified by zhuyf 添加英文名（导入对外接口，时文档中有此格式信息）
		SERVICE_PROPERTY_HANDLERS.put("英文名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		SERVICE_PROPERTY_HANDLERS.put("功能中文名", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME));
		SERVICE_PROPERTY_HANDLERS.put("功能名称", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME));
		//现在在服务接口 名字为输入输出结果集
		SERVICE_PROPERTY_HANDLERS.put("输入结果集", new InOutCollectionPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__INPUT_COLLECTION));
		SERVICE_PROPERTY_HANDLERS.put("输出结果集", new InOutCollectionPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION));
		SERVICE_PROPERTY_HANDLERS.put("结果集返回", new InOutCollectionPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION));

		//兼容性考虑  不删除原来的名字
		SERVICE_PROPERTY_HANDLERS.put("输入是否数组", new InOutCollectionPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__INPUT_COLLECTION));
		SERVICE_PROPERTY_HANDLERS.put("输出是否数组", new InOutCollectionPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION));
		SERVICE_PROPERTY_HANDLERS.put("是否公开", NullPropertyHandler.INSTANCE);
		SERVICE_PROPERTY_HANDLERS.put("是否复核", NullPropertyHandler.INSTANCE);
		SERVICE_PROPERTY_HANDLERS.put("复核类型", NullPropertyHandler.INSTANCE);
		SERVICE_PROPERTY_HANDLERS.put("复核级数", NullPropertyHandler.INSTANCE);
		SERVICE_PROPERTY_HANDLERS.put("业务处理流程", NullPropertyHandler.INSTANCE);
		
		SERVICE_PARAM_PROPERTY_HANDLERS.putAll(PARAM_PROPERTY_HANDLERS);
		SERVICE_PARAM_PROPERTY_HANDLERS.put("xml标签", NullPropertyHandler.INSTANCE);
		SERVICE_PARAM_PROPERTY_HANDLERS.put("XML标签", NullPropertyHandler.INSTANCE);
	}*/
	
	private Service service;
	
	@Override
	public void startSheet(Sheet sheet) {
		super.startSheet(sheet);
		module = new Module();
		String cName = null;
		//中文分隔符—也支持
		String sheetName = StringUtils.replace(sheet.getSheetName(), "—", "-");
		if (StringUtils.startsWith(sheetName, "服务-")) {
			cName = StringUtils.removeStart(sheetName, "服务-");
		} else if (StringUtils.startsWith(sheetName, "功能接口定义-")) {
			cName = StringUtils.removeStart(sheetName, "功能接口定义-");
		} else {
			cName = sheetName;
		}
		module.cName = cName;
	}
	
	@Override
	public void startArea(String startTag) {
		super.startArea(startTag);
		if (StringUtils.equals(startTag, "对象号") || StringUtils.equals(startTag, "功能号"))
			service = ServiceFactory.eINSTANCE.createService();
	}
	
	@Override
	protected EObject getOwner() {
		if (isInterfacePropertyes() || isErrorInfoBlock()) {
			return service.getInterface();
		}
		return service;
	}
	
	protected EClass getEClass() {
		if (isInterfacePropertyes()) {
			return BizPackage.Literals.PARAMETER;
		} else if (isErrorInfoBlock()) {
			return BizPackage.Literals.ERROR_INFO;
		}
		return ServicePackage.Literals.SERVICE;
	}
	
	protected boolean isInterfacePropertyes() {
		boolean isInputOuputCollection = StringUtils.equals(parser.getCurrentBlockTag(), "输入结果集")
											|| StringUtils.equals(parser.getCurrentBlockTag(), "输出结果集")
											|| StringUtils.equals(parser.getCurrentBlockTag(), "输入是否数组")
											|| StringUtils.equals(parser.getCurrentBlockTag(), "输出是否数组");
		return isInputOuputCollection || isInputParmaBlock() || isOutputParmaBlock();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endArea()
	 */
	public void endArea() {
		super.endArea();
		if (service != null) {
			Resource resource = new Resource();
			resource.info = service;
			resource.name = service.getName();
			resource.type = IBizResType.Service;
			
			resourceFound(resource);
		}
	}
	
}
