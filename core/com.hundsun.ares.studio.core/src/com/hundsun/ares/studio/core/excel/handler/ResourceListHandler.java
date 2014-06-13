/**
 * 源程序名称：ResourceListHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;

import com.hundsun.ares.studio.core.excel.AbstractSheetHandler;
import com.hundsun.ares.studio.core.excel.ISheetHandler;

/**
 * @author sundl
 *
 */
public class ResourceListHandler extends AbstractSheetHandler implements ISheetHandler {

	String[] header;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#startSheet()
	 */
	@Override
	public void startSheet(Sheet sheet) {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#startArea()
	 */
	@Override
	public void startArea(String startTag) {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#startBlock()
	 */
	@Override
	public void startBlock(String startTag, int type) {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#keyValue(java.util.Map)
	 */
	@Override
	public void keyValue(String key, String value) {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#header(java.lang.String[])
	 */
	@Override
	public void header(String[] header) {
		this.header = header;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#row(java.lang.String[])
	 */
	@Override
	public void row(String[] row) {
		int count = header.length;
		String moduleName = null;
		String moduleCname = null;
		for (int i = 0; i < count; i++) {
			String hd = header[i];
			if (StringUtils.equals(hd, "模块名")) {
				moduleName = row[i];
			} else if (StringUtils.equals(hd, "模块中文名")) {
				moduleCname = row[i];
			}
		}
		// 名字可能是有/或-分割的，先分割字符串
		if (moduleName != null && moduleCname != null) {
			String[] moduleNameSegments = split(moduleName);
			String[] moduleCNameSegments = split(moduleCname);
			// 分割结果中英文应该是对应的
			if (moduleCNameSegments.length == moduleNameSegments.length) {
				int length = moduleNameSegments.length;
				for (int i = 0; i< length; i++) {
					parser.exlParser.moduleNameMap.put(moduleCNameSegments[i], moduleNameSegments[i]);
				}
			}
		}
	}
	
	private String[] split(String fullName) {
		if (StringUtils.indexOf(fullName, '/') != -1) {
			return fullName.split("/");
		} else if (StringUtils.indexOf(fullName, '-') != -1){
			return fullName.split("-");
		}
		return new String[] {fullName};
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endBlock()
	 */
	@Override
	public void endBlock() {

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endArea()
	 */
	@Override
	public void endArea() {

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endSheet()
	 */
	@Override
	public void endSheet() {

	}

}
