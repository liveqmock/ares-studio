/**
 * 源程序名称：ExportExcelEntity.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：yanwj06282
 */
package com.hundsun.ares.studio.biz.ui.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * 导出Excel标准格式
 * 
 * @author yanwj06282
 *
 */
public class ExportExcelEntity {
	/**
	 * 菜单页数组，优先生成
	 */
	private List<ExcelMenuSheetStructEntity> menuList = new ArrayList<ExcelMenuSheetStructEntity>();
	
	/**
	 * 资源页数组，生成于菜单页后面
	 */
	private List<ExcelSheetStructEntity> sheetList = new ArrayList<ExcelSheetStructEntity>();

	
	public List<ExcelMenuSheetStructEntity> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<ExcelMenuSheetStructEntity> menuList) {
		this.menuList = menuList;
	}

	public List<ExcelSheetStructEntity> getSheetList() {
		return sheetList;
	}

	public void setSheetList(List<ExcelSheetStructEntity> sheetList) {
		this.sheetList = sheetList;
	}
	
}
