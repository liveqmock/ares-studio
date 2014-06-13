/**
 * 源程序名称：BizExcelStructEntity.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：yanwj06282
 */
package com.hundsun.ares.studio.biz.ui.excel;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Excel信息封装模型
 * 
 * @author yanwj06282
 *
 */
public class BizResExcelStructEntity {

	/**
	 * 每个资源的key,用于超链接定位,所以必须唯一,可以使用资源长名+类型
	 */
	private String hyperlinkKey ;
	
	/**
	 * 基础属性的最大长度，决定了我们一行最多允许放多少单元格
	 * 由于我们基础属性是key和value方式一一对应的，如果解析过程中，value的单元格超出此值，超出部分的value不会换行
	 */
	private int basicParmasMAXCellLength = 3;
	
	//第一块区域:基本属性，包含扩展属性
	private Map<ExcelBasicParamStuctEntity ,ExcelBasicParamStuctEntity> basicParams = new LinkedHashMap<ExcelBasicParamStuctEntity ,ExcelBasicParamStuctEntity>();
	
	//第二块区域:参数
	private Map<String ,ParameterStructEntity> parameterMaps = new LinkedHashMap<String ,ParameterStructEntity>();
	
	//第三块区域:自定义单元格
	private Map<ExcelBasicParamStuctEntity ,ExcelBasicParamStuctEntity> endAres = new LinkedHashMap<ExcelBasicParamStuctEntity ,ExcelBasicParamStuctEntity>();
	
	public void putBasicParams(ExcelBasicParamStuctEntity key ,ExcelBasicParamStuctEntity value){
		basicParams.put(key, value);
	}
	
	public void putParameterMaps(String key ,ParameterStructEntity value){
		parameterMaps.put(key, value);
	}

	public Map<ExcelBasicParamStuctEntity, ExcelBasicParamStuctEntity> getBasicParams() {
		return basicParams;
	}

	public Map<String, ParameterStructEntity> getParameterMaps() {
		return parameterMaps;
	}

	public int getBasicParmasMAXCellLength() {
		return basicParmasMAXCellLength;
	}

	public void setBasicParmasMAXCellLength(int basicParmasMAXCellLength) {
		this.basicParmasMAXCellLength = basicParmasMAXCellLength;
	}

	public String getHyperlinkKey() {
		return hyperlinkKey;
	}

	public void setHyperlinkKey(String hyperlinkKey) {
		this.hyperlinkKey = hyperlinkKey;
	}

	public Map<ExcelBasicParamStuctEntity, ExcelBasicParamStuctEntity> getEndAres() {
		return endAres;
	}

	public void putEndAres(ExcelBasicParamStuctEntity key , ExcelBasicParamStuctEntity value) {
		endAres.put(key, value);
	}

}
