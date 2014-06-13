/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.ui.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.NormalAttributeHelper;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IMapExtensibleModelPropertyDescriptor;

/**
 * @author yanwj06282
 *
 */
public class DictoniaryUtils {
	

	/**
	 * 隐藏数据字典常量列
	 */
	public static final String HIDE_DICTIONARY_CONSTANT = "hide_dictionary_constant";
	
	/**
	 * 是否隐藏数据字典常量列
	 * @param project
	 * @return
	 */
	public static boolean isHideDictionaryConstant(IARESProject project) {
		try {
			IARESProjectProperty property = project.getProjectProperty();
			if(property != null){
				return property.getBoolean(HIDE_DICTIONARY_CONSTANT);
			}
		} catch (ARESModelException e) {
			return false;
		}
		return false;
	}

	/**
	 * 数据字典导入
	 * 
	 * @param resource
	 * @param dt
	 * @param path
	 * @param sheets
	 * @param rowStarts
	 * @param colStarts
	 * @return
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	public static DictionaryList importDict (IARESResource resource, DictionaryList dictList , File path , String[] sheets , int[] rowStarts  , int[] colStarts) throws FileNotFoundException, Exception{

			List<IAttributeHelper> helperList = new ArrayList<IAttributeHelper>();
			List<IAttributeHelper> subHelperList = new ArrayList<IAttributeHelper>();
			InputStream input = null;
			Map<String, List<List<String>>> map = null;
			try {
				input = new FileInputStream(path);
				map = POIUtils.getExcelStringForDict(new FileInputStream(path), new String[]{sheets[0]}, new int[]{rowStarts[0]}, new int[]{colStarts[0]});
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				IOUtils.closeQuietly(input);
			}
			
			for (Iterator<List<List<String>>> iterator = map.values().iterator(); iterator.hasNext();) {
				List<List<String>> strs = iterator.next();
				Map<String , Integer> sortMap = new HashMap<String, Integer>();
				Map<IAttributeHelper , Integer> helperSortMap = new HashMap<IAttributeHelper, Integer>();
				
				MetadataCategory mc = null;
				MetadataCategory subMc = null;
				for (int i = 0; i < strs.size(); i++) {
					List<String> columnStrs = strs.get(i);
					
					if (i == 0) {
						
						// 标题名和属性助手的映射
						Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
						helperMap.put("字典条目", new NormalAttributeHelper(MetadataPackage.Literals.NAMED_ELEMENT__NAME));
						helperMap.put("条目名称", new NormalAttributeHelper(MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME));
						helperMap.put("条目说明", new NormalAttributeHelper(MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME));
						helperMap.put("字典条目中文名", new NormalAttributeHelper(MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME));
						helperMap.put("说明", new NormalAttributeHelper(MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION));
						
						IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(resource, MetadataPackage.Literals.DICTIONARY_TYPE);
						for (IExtensibleModelEditingSupport support : supports) {
							for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(resource, MetadataPackage.Literals.DICTIONARY_TYPE)) {
								if (!desc.isDerived()) {
									if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
										helperMap.put(desc.getDisplayName(), new POIUtils.ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
									} else {
										helperMap.put(desc.getDisplayName(), new POIUtils.ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
									}
									
								}
							}
						}
						
						for (int j = 2; j < columnStrs.size(); j++) {
							String title = columnStrs.get(j);
							if (helperMap.get(title) != null) {
								helperList.add(helperMap.get(title));
								helperSortMap.put(helperMap.get(title), j);
							}
							sortMap.put(title ,j);
						}
						continue;
					}
					
					boolean isCate = false;
					if (columnStrs.size() > 0 && (StringUtils.isNotBlank(columnStrs.get(0)) || StringUtils.isNotBlank(columnStrs.get(1)))) {
						isCate = true;
					}
					
					if (isCate) {
						String mcName = columnStrs.get(0);
						if (StringUtils.isNotBlank(mcName)) {
							String[] cates = StringUtils.split(mcName, "/");
							mc = dictList.getRoot();
							for(String ca : cates){
								mc = createCate(ca, mc); 
							}
							subMc = null;
						}
						String subMcName = columnStrs.get(1);
						if (StringUtils.isNotBlank(subMcName)) {
							subMc = null;
							for(MetadataCategory smc : mc.getChildren()){
								if (StringUtils.equals(smc.getName(), subMcName)) {
									subMc = smc;
									break;
								}
							}
							if (subMc == null) {
								subMc = MetadataFactory.eINSTANCE.createMetadataCategory();
							}
							subMc.setName(subMcName);
							mc.getChildren().add(subMc);
//							mc = subMc;
						}
					}
					
					boolean blank = true;
					for (int j = 2; j < columnStrs.size(); j++) {
						if (StringUtils.isNotBlank(columnStrs.get(j))) {
							blank = false;
						}
					}
					if (blank) {
						continue;
					}
					
					DictionaryType dictType = MetadataFactory.eINSTANCE.createDictionaryType();
					dictList.getItems().add(dictType);
					if (subMc != null) {
						subMc.getItems().add(dictType);
					}else if (mc != null) {
						mc.getItems().add(dictType);
					}
					
					int desc = sortMap.get("说明")==null?-1:sortMap.get("说明");
					if (desc > -1) {
						dictType.setDescription(columnStrs.get(desc));
					}
					
					ExtensibleModelUtils.extend(resource, dictType, false);
					
					for (int j = 0; j < helperList.size(); j++) {
						IAttributeHelper helper = helperList.get(j);
						if (helper != null) {
							if (helperSortMap.get(helper)!=null) {
								String va = columnStrs.get(helperSortMap.get(helper));
								if (StringUtils.isNotBlank(va)) {
									helper.setValue(dictType, va.trim());
								}
							}	
						}
					}
				}
				
				InputStream subInput = null;
				Map<String, List<List<String>>> subMap = null;
				try {
					subInput = new FileInputStream(path);
					HSSFWorkbook workBook = new HSSFWorkbook(subInput); 
					subMap = POIUtils.getExcelString(workBook, new String[]{sheets[1]}, new int[]{rowStarts[1]}, new int[]{colStarts[1]});
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					IOUtils.closeQuietly(subInput);
				}
				
				for (Iterator<List<List<String>>> it = subMap.values().iterator(); it.hasNext();) {
					List<List<String>> subStrs = it.next();
					Map<String , Integer> subSortMap = new HashMap<String , Integer>();
					for (int i = 0; i < subStrs.size(); i++) {
						if (i == 0) {
							List<String> titile = subStrs.get(i);
							// 标题名和属性助手的映射
							Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
							
							helperMap.put("字典子项", new NormalAttributeHelper(MetadataPackage.Literals.DICTIONARY_ITEM__VALUE));
							helperMap.put("子项名称", new NormalAttributeHelper(MetadataPackage.Literals.DICTIONARY_ITEM__CHINESE_NAME));
							helperMap.put("子项说明", new NormalAttributeHelper(MetadataPackage.Literals.DICTIONARY_ITEM__CHINESE_NAME));
							helperMap.put("字典常量", new NormalAttributeHelper(MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME));
							helperMap.put("说明", new NormalAttributeHelper(MetadataPackage.Literals.DICTIONARY_ITEM__DESCRIPTION));
							
							IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(resource, MetadataPackage.Literals.DICTIONARY_ITEM);
							for (IExtensibleModelEditingSupport support : supports) {
								for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(resource, MetadataPackage.Literals.DICTIONARY_ITEM)) {
									if (!desc.isDerived()) {
										if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
											helperMap.put(desc.getDisplayName(), new POIUtils.ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
										} else {
											helperMap.put(desc.getDisplayName(), new POIUtils.ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
										}
										
									}
								}
							}
							
							for (int j = 2; j < titile.size(); j++) {
								String title = titile.get(j);
								if (helperMap.get(title) != null) {
									subHelperList.add(helperMap.get(title));
									helperSortMap.put(helperMap.get(title), j);
								}
								subSortMap.put(title, j);
							}
							
							continue;
						}
						List<String> datas = subStrs.get(i);
						DictionaryType type = getDictType(dictList, datas.get(0));
						if (type != null) {
							DictionaryItem item = MetadataFactory.eINSTANCE.createDictionaryItem();
							ExtensibleModelUtils.extend(resource, item, false);
							type.getItems().add(item);
							int desc = subSortMap.get("说明")==null?-1:subSortMap.get("说明");
							if (desc > -1) {
								item.setDescription(datas.get(desc));
							}
							for (int j = 0; j < subHelperList.size(); j++) {
								IAttributeHelper helper = subHelperList.get(j);
								if (helper != null) {
									if (helperSortMap.get(helper)!=null) {
										String va = datas.get(helperSortMap.get(helper));
										if (StringUtils.isNotBlank(va)) {
											helper.setValue(item, va.trim());
										}
									}	
								}
							}
							
						}
					}
					
				}
			}
		return dictList;
	}
	
	private static MetadataCategory createCate(String cate ,MetadataCategory parient){
		MetadataCategory even = null;
		for (MetadataCategory mc : parient.getChildren()) {
			if (StringUtils.equals(mc.getName(), cate)) {
				even = mc;
				break;
			}
		}
		if (even == null) {
			even = MetadataFactory.eINSTANCE.createMetadataCategory();
			even.setName(cate);
			parient.getChildren().add(even);
		}
		return even;
	}
	
	/**
	 * 数据字典导出
	 * 
	 * @param output
	 * @param contents
	 * @param startRows
	 * @param startCols
	 */
	public static void exportDict (OutputStream output , Map< String, List<List<String>>> contents ,List<List<String>> revHises ,int[] startRows , int[] startCols ){
		init();
		InputStream is;
		try {
			if (contents.size() == startCols.length && startCols.length == startRows.length && startRows.length != 0) {
				is = MetadataUI.getDefault().getBundle().getEntry("template/dictionaryListExportTemplate.xls").openStream();
				HSSFWorkbook wb = new HSSFWorkbook(is);
				int z = 0;
				
				//在版本页中，加入资源的修订信息
				if (revHises != null && revHises.size() > 1) {
					HSSFSheet versionSheet = wb.getSheet("版本页");
					for (int i = 1; i < revHises.size(); i++) {
						List<String> items = revHises.get(i);
						HSSFRow row = versionSheet.createRow(11+i);
						for (int j = 0; j < items.size(); j++) {
							String cv = items.get(j);
							HSSFCell cell = row.getCell(j+1);
							if (cell == null) {
								cell = row.createCell(j+1);
								cell.setCellStyle(getTextStyle(wb));
							}
							cell.setCellValue(cv);
						}
					}
				}
				
				for (Iterator<String> iterator = contents.keySet().iterator(); iterator
						.hasNext();) {
					String key = iterator.next();
					HSSFSheet sheet = wb.getSheet(key);
					if (sheet != null) {
						List< List<String>> sheetData = contents.get(key);
						for (int i = 0; i < sheetData.size(); i++) {
							if (i == 0) {
								List<String> titles = sheetData.get(i);
								HSSFRow row = sheet.createRow(startRows[z]);
								for (int j = 0; j < titles.size(); j++) {
									HSSFCell cell = row.createCell(startCols[0]+j);
									cell.setCellValue(titles.get(j));
									cell.setCellStyle(getTitleStyle(wb));
								}
								continue;
							}

							List<String> data = sheetData.get(i);
							HSSFRow row = sheet.createRow(startRows[z]+i);
							boolean cateStatus = false;
							for (int j = 0; j < data.size(); j++) {
								if (j == 0) {
									if (StringUtils.isNotBlank(data.get(j))) {
										cateStatus = true;
									}
								}else {
									if (data.get(j) != null) {
										cateStatus = false;
									}
								}
								HSSFCell cell = row.createCell(startCols[0]+j);
								cell.setCellValue(data.get(j));
								cell.setCellStyle(getTextStyle(wb));
							}
							if (cateStatus) {
								for (int k = 0; k < data.size()-1; k++) {
									row.getCell(k + startCols[z]).setCellStyle(getCateStyle(wb));
								}
								sheet.addMergedRegion(new CellRangeAddress(startRows[z]+i, startRows[z]+i ,startCols[z], startCols[z] + data.size()-1));
							}
						}
						for (int i = 0; sheetData.size() > 0 && i < sheetData.get(0).size(); i++) {
							sheet.autoSizeColumn(i);
							if (sheet.getColumnWidth(i) > 10000) {
								sheet.setColumnWidth(i, 10000);
							}
						}
					}
					z++;
				}
				wb.write(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static HSSFFont titleFont;
	private static HSSFCellStyle titleStyle;
	private static HSSFCellStyle textStyle;
	private static HSSFCellStyle cateStyle;
	
	private static void init (){
		if (titleFont != null) {
			titleFont = null;
		}
		if (titleStyle != null) {
			titleStyle = null;
		}
		if (textStyle != null) {
			textStyle = null;
		}
		if (cateStyle != null) {
			cateStyle = null;
		}
	}
	
	private static HSSFCellStyle getTitleStyle(HSSFWorkbook wb) {
		if (titleStyle == null) {
			titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBottomBorderColor(HSSFColor.BLACK.index);
			if (titleFont == null) {
				titleFont = wb.createFont();
				titleFont.setFontName("宋体");
				titleFont.setFontHeightInPoints((short) 10);
				titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			}
			titleStyle.setFont(titleFont);
		}
		return titleStyle;
	}
	
	/**
	 * 文本框的样式
	 * 
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle getTextStyle(HSSFWorkbook wb) {
		if (textStyle == null) {
			textStyle = wb.createCellStyle();
			textStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			textStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			textStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			textStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			textStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			textStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			textStyle.setBottomBorderColor(HSSFColor.BLACK.index);
			textStyle.setWrapText(true);
		}
		return textStyle;

	}
	
	private static HSSFCellStyle getCateStyle(HSSFWorkbook wb) {
		if (cateStyle == null) {
			cateStyle = wb.createCellStyle();
			cateStyle.setFillForegroundColor(HSSFColor.TAN.index);
			cateStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cateStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cateStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cateStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cateStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cateStyle.setBottomBorderColor(HSSFColor.BLACK.index);
			// 2012-09-11 sundl 在开头有空格的情况下，而且列宽AutoWidth又不完美，导致内容被换到下一行，默认状态看不到.
			//textStyle.setWrapText(true);
		}
		return cateStyle;
	}
	
	private static DictionaryType getDictType (DictionaryList dl , String dlID){
		for (DictionaryType type : dl.getItems()) {
			if (StringUtils.equals(type.getName(), dlID)) {
				return type;
			}
		}
		return null;
	}
	
	public static MetadataCategory isDupMc (MetadataCategory mc ,String type ){
		for (MetadataCategory m : mc.getChildren()) {
			if (StringUtils.equals(m.getName(), type)) {
				return m;
			}
		}
		return null;
	}
	
}
