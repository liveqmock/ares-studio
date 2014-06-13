/**
 * 源程序名称：Test.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：dollyn
 */
package com.hundsun.ares.studio.biz.excel.export.writer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author dollyn
 *
 */
public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet("new sheet");

	    Row row = sheet.createRow((short) 1);
	    Cell cell = row.createCell((short) 1);
	    cell.setCellValue("This is a test of merging");

	    sheet.addMergedRegion(new CellRangeAddress(
	            1, //first row (0-based)
	            1, //last row  (0-based)
	            1, //first column (0-based)
	            2  //last column  (0-based)
	    ));

	    // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("d:\\workbook.xls");
	    wb.write(fileOut);
	    fileOut.close();
	}

}
