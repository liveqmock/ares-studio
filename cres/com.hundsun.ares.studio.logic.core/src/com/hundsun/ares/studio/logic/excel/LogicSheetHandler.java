package com.hundsun.ares.studio.logic.excel;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.biz.excel.handlers.InterfaceSheetHandler;
import com.hundsun.ares.studio.core.excel.Module;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;

public abstract class LogicSheetHandler  extends InterfaceSheetHandler{

	/** 参数属性处理对应列表 */
	public static Map<String, IPropertyHandler> VAR_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();
	
	public static Map<String, IPropertyHandler> LOGIC_CODE_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();
	
	/** 移动到对应的PropertyHandlerFactory中 --> LogicPropertyHandlerFactory, VarPropertyHandlerFactory
	static {
		LOGIC_PROPERTY_HANDLERS.putAll(INTERFACE_PROPERTY_HANDLERS);
		LOGIC_PROPERTY_HANDLERS.put("所属数据库", NullPropertyHandler.INSTANCE);
		LOGIC_PROPERTY_HANDLERS.put("服务编号", NullPropertyHandler.INSTANCE);
		LOGIC_PROPERTY_HANDLERS.put("是否复核", NullPropertyHandler.INSTANCE);
		LOGIC_PROPERTY_HANDLERS.put("复核类型", NullPropertyHandler.INSTANCE);
		LOGIC_PROPERTY_HANDLERS.put("复核级数", NullPropertyHandler.INSTANCE);

		// 内部变量
		VAR_PROPERTY_HANDLERS.put("变量", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__FLAGS));
		VAR_PROPERTY_HANDLERS.put("参数名", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__ID));
		VAR_PROPERTY_HANDLERS.put("类型", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__TYPE));
		VAR_PROPERTY_HANDLERS.put("说明", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__NAME));
		VAR_PROPERTY_HANDLERS.put("缺省值", NullPropertyHandler.INSTANCE);
		VAR_PROPERTY_HANDLERS.put("长度", NullPropertyHandler.INSTANCE);
		
		LOGIC_CODE_PROPERTY_HANDLERS.put("业务处理流程", new EMFPropertyHandler(AtomPackage.Literals.ATOM_FUNCTION__PSEUDO_CODE));
		LOGIC_CODE_PROPERTY_HANDLERS.put("出错说明", NullPropertyHandler.INSTANCE);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.AbstractSheetHandler#startSheet(org.apache.poi.hssf.usermodel.HSSFSheet)
	 */
	@Override
	public void startSheet(Sheet sheet) {
		super.startSheet(sheet);
		module = new Module();
		//中文分隔符—也支持
		String sheetName = StringUtils.replace(sheet.getSheetName(), "—", "-");
		String cName = StringUtils.removeStart(sheetName, "逻辑函数-");
		cName = StringUtils.removeStart(cName, "逻辑服务-");
		module.cName = cName;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.AbstractSheetHandler#endSheet()
	 */
	@Override
	public void endSheet() {
		super.endSheet();
	}

	@Override
	protected EStructuralFeature getTableFeature() {
		if (isVarBlock()) {
			return AtomPackage.Literals.ATOM_FUNCTION__INTERNAL_VARIABLES;
		}
		return super.getTableFeature();
	}
	
	protected boolean isVarBlock() {
		return StringUtils.equals(parser.getCurrentBlockTag(), "变量");
	}
	
	protected boolean isTextBlock() {
		return StringUtils.equals(parser.getCurrentBlockTag(), "业务处理流程");
	}
	
}
