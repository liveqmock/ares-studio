/**
 * 
 */
package com.hundsun.ares.studio.jres.service.ui.wizard;

/**
 * 业务逻辑导出结构常量定义
 * 
 * @author yanwj06282
 *
 */
public class ExcelStructConstantDefine {

	//--------------------------------------对象-------------------------------------------
	
	//对象
	public static final String OBJECT_PREFIX = "对象-";
	public static final String OBJECT_NAME = "对象名";
	public static final String OBJECT_CHINESE_NAME = "对象中文名";
	public static final String OBJECT_VERSION = "版本号";
	public static final String OBJECT_DESCRIPTION = "对象描述";
	public static final String OBJECT_SHEET_NAME = "业务对象列表";
	
	/**
	 * 对象菜单页标题
	 */
	public static final String[] OBJECT_MENU_TITLES = new String[]{"模块名" ,"模块中文名","对象号","对象名","对象中文名","对象描述"};
	/**
	 * 对象参数标题
	 */
	public static final String[] OBJECT_PARAMETER_Titles = new String[]{"对象属性","属性名","中文名","类型","关系属性","默认值","备注"};
	

	//--------------------------------------服务-------------------------------------------
	
	
	//服务
	public static final String SERVICE_PREFIX = "服务-";
	public static final String SERIVCE_SHEET_NAME = "功能接口列表";
	public static final String SERIVCE_OBJECT_ID = "功能号";
	public static final String SERIVCE_NAME = "功能名";
	public static final String SERIVCE_CHINESE_NAME = "功能中文名";
	public static final String SERIVCE_VERSION = "版本号";
	public static final String SERIVCE_UPDATE_DATE = "更新日期";
	
	/**
	 * 功能菜单页标题
	 */
	public static final String[] SERVICE_MENU_TITLES = new String[]{"模块名","模块中文名","功能号","功能名称" ,"功能中文名","功能说明"};
	/**
	 * 服务输入参数标题
	 */
	public static final String[] SERVICE_INPUT_PARAMETER_TITLES = new String[]{"输入参数","参数名","中文名","类型" ,"关系属性","默认值","备注"};
	
	/**
	 * 服务输出参数标题
	 */
	public static final String[] SERVICE_OUTPUT_PARAMETER_TITLES = new String[]{"输出参数","参数名","中文名","类型","关系属性","默认值","备注"};
	
	
	
	//---------------------------------------------------------------------------------
	
	public static final String HYPERLINK = "HYPERLINK(\"#'%1$s'A%2$s\",\"%3$s\")";
	
	/**
	 * 获取超链接表达式
	 * 
	 * @param packageName
	 * @param resourceName
	 * @param rowNum
	 * @return
	 */
	public static String getRefExpression(String packageName ,String resourceName , int rowNum ){
		return String.format(ExcelStructConstantDefine.HYPERLINK, packageName , rowNum ,resourceName);
	}
	
}
