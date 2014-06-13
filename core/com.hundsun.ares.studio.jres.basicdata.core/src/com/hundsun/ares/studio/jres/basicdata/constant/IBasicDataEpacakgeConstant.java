package com.hundsun.ares.studio.jres.basicdata.constant;


public interface IBasicDataEpacakgeConstant {

	/**
	 * 资源根类
	 */
	public  static final String ResourceRoot = "Root";
	
	/**
	 * 主表定义
	 */
	public  static final String MasterItem = "MasterItem";
	
	/**
	 * 从表定义
	 */
	public  static final String SlaveItem = "SlaveItem";
	
	/**
	 * 信息表定义
	 */
	public  static final String InfoItem = "InfoItem";
	
	
	/**
	 * 从表数据
	 */
	public  static final String Attr_Slave_Items = "slaveItems";
	
	/**
	 * 信息表
	 */
	public  static final String Attr_Info_Items = "infoItems";
	
	public  static final String Attr_Master_Items = "items";
	
	public  static final String StandardField_Package_Define = "StandardFieldPackageDefine";
	
	public static final String Attr_Info_Content_Reference = "_info_refer";
//	public  static final String Attr_Link_Items = "linkItems";
	
	/**
	 * 
	 */
	public  static final String Annotation_Source = "www.hundsun.com";
	
	/**
	 * 主键
	 */
	public  static final String Annotation_Key_MasterKey = "masterkey";
	
	public  static final String Annotation_Key_Seprator = ",";
	//项目属性中基本数据类型的存储ID
	public static final String Property_Basic_Data_type_ID = "basicdatatype";
	
	/////////////////////////////////帮助类/////////////////////////////////
	public static final String Info_Table_Locator_Helper = "infotablelocatorhelper";
	
	
    /////////////////////////////////建模类型定义/////////////////////////////////
	public static final String MODLE_TYPE_DATABASE = "jres.db.table";
}
