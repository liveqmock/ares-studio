/**
 * 源程序名称：ExcelParser.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.util.log.Log;

/**
 * 解析Excel
 * @author sundl
 *
 */
public class ExcelParser {
	
	private static final Logger logger = Logger.getLogger(ExcelParser.class);
	
	public Log log;
	public File file;
	public ISheetParserFactory factory;
	
	/** 模块中英文对照表，使用BiMap,可以反向查找 */
	public BiMap<String, String> moduleNameMap = HashBiMap.create();
	/** 存储解析结果 */
	public Multimap<Module, Resource> resources = ArrayListMultimap.create();
	
	/** 其他的扩展字段 */
	public Map<String, Object> context = new HashMap<String, Object>();
	
	/** 对象号 --> 修改记录 */
	public Multimap<String, RevisionHistory> histories = ArrayListMultimap.create();
	
	/** 解析完成后需要进行的操作
	 *  目前用来完成类似解析XML标签这样的扩展属性的处理
	 */
	public PostParseOperation postParseOperation = null; // 变量是整个Operation一个，operation创建后传过来的
//	public MetadataModifyOperation<StandardField> stdModifyOperation = null;
	
	public ExcelParser(File file, Log log) {
		this.file = file;
		this.log = log;
	}
	
	public void parse() {
		if (factory == null) {
			// 理论上不应该发生这种情况
			log.error("内部错误，ISheetParserFactory为空", null);
			return;
		}
		
		if (file == null || !file.exists()) {
			log.error(String.format("找不到文件：%s", file.getAbsolutePath()), null);
		}
		
		InputStream is = null;
		try {
			is = new FileInputStream(this.file);
			HSSFWorkbook wb = new HSSFWorkbook(is);
			int sheetCount = wb.getNumberOfSheets();
			for (int i = 0; i < sheetCount; i++) {
				HSSFSheet sheet = wb.getSheetAt(i);
				SheetParser parser = factory.createParser(this, sheet, this.log);
				if (parser == null) {
					log.info(String.format("内部错误,Sheet页“%s”没有找到对应的解析器进行处理!", sheet.getSheetName()), null);
					continue;
				}
				try {
					parser.evaluator = wb.getCreationHelper().createFormulaEvaluator();
					parser.parse(sheet);
				} catch (Exception e) {
					log.error(String.format("解析Sheet页“%s”的时候发生内部异常. %s", sheet.getSheetName(), e.getMessage()), null);
					logger.error(String.format("解析Sheet页“%s”的时候发生内部异常.", sheet.getSheetName()), e);
				}
			}
		} catch (FileNotFoundException e) {
			log.error(String.format("找不到文件：%s", file.getAbsolutePath()), null);
		} catch (IOException e) {
			log.error(String.format("读取文件%s出错：%s", file.getAbsolutePath(), e.getMessage()), null);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

}
