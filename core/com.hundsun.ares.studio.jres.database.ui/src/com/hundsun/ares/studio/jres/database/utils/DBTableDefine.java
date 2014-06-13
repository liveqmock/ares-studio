/**
 * 源程序名称：DBTableDefine.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @author yanwj06282
 *
 */
public class DBTableDefine {
	
	private static List<String> textTitles = new ArrayList<String>(); 
	private static List<String> columnTitles = new ArrayList<String>();
	private static List<String> indexTitles = new ArrayList<String>();
	private static List<String> keyTitles = new ArrayList<String>();
	private static Map<String , Integer> tableType = new HashMap<String, Integer>();
	
	static {
		textTitles.add(DBTableDefine.BASE_NAME);
		textTitles.add(DBTableDefine.BASE_CHINESE_NAME);
		textTitles.add(DBTableDefine.BASE_DESCRIPTION);
//		textTitles.add(DBTableDefine.BASE_VERSION);
//		textTitles.add(DBTableDefine.BASE_TYPE);
//		textTitles.add(DBTableDefine.BASE_DATABASE);
		
		columnTitles.add(DBTableDefine.COLUMN_STATUS);
		columnTitles.add(DBTableDefine.COLUMN_NAME);
//		columnTitles.add(DBTableDefine.COLUMN_IS_PRIMARY_KEY);
		columnTitles.add(DBTableDefine.COLUMN_NULL);
		columnTitles.add(DBTableDefine.COLUMN_DESCRIPTION);
//		columnTitles.add(DBTableDefine.COLUMN_MARK);
		
		indexTitles.add(DBTableDefine.INDEX_STATUS);
		indexTitles.add(DBTableDefine.INDEX_NAME);
		indexTitles.add(DBTableDefine.INDEX_UNIQUE);
		indexTitles.add(DBTableDefine.INDEX_CLUST);
		indexTitles.add(DBTableDefine.INDEX_COLUMNS);
		indexTitles.add(DBTableDefine.INDEX_MARK);
		
		keyTitles.add(DBTableDefine.KEY_DEFINE);
		keyTitles.add(DBTableDefine.KEY_NAME);
		keyTitles.add(DBTableDefine.KEY_TYPE);
		keyTitles.add(DBTableDefine.KEY_COLUMNS);
		keyTitles.add(DBTableDefine.KEY_FOREIGN_KEY_TABLE);
		keyTitles.add(DBTableDefine.KEY_FOREIGN_KEY_FIELD);
		
		tableType.put("U", 	0);
		tableType.put("T",  1);
		tableType.put("M", 	2);
	}
	public static final String DATABASE_MENU = "数据表目录";
	public static final String DATABASE_MODULE_INFO = "模块信息";
	public static final String BASE_OBJECT_NUM = "对象号";
	public static final String BASE_NAME = "表名";
	public static final String BASE_CHINESE_NAME = "中文名";
	public static final String BASE_DESCRIPTION = "说明";
	public static final String BASE_VERSION = "版本号";
	public static final String BASE_TYPE = "表类型";
	public static final String VIEW_TYPE = "V";
	public static final String BASE_DATABASE = "所在数据库";
	public static final String BASE_CUS_DATABASE_PART = "是否自定义表分区";
	public static final String BASE_DATABASE_PART_COL = "表分区字段";
	public static final String BASE_DATABASE_PART_NUM = "分区个数";
	public static final String BASE_DATABASE_PART_START_DATE = "分区开始年月";
	public static final String IS_HISTORY_TABLE = "存在历史表";
	public static final String IS_DIR_TABLE = "存在冗余表";
	public static final String IS_CLEAR_TABLE = "存在清算表";
	public final static String FIELD_DEFINE = "字段";
	public final static String INDEX_DEFINE = "索引";
	public final static String KEY_DEFINE = "键约束";
	public final static String INDEX_DEFINE_UTIL = "索引字段";
	public final static String MODIFY_DEFINE = "修改记录";
	
	public final static String COLUMN_PRIMARY_KEY = "Y";
	public final static String COLUMN_NULL_ABLE_N = "N";
	public final static String COLUMN_NULL_ABLE_Y = "Y";
	public final static String COLUMN_STATUS = "字段";
	public final static String COLUMN_NAME = "字段名";
	public final static String COLUMN_CHINESE_NAME = "中文名";
	public final static String COLUMN_TYPE = "字段类型";
	public final static String COLUMN_NULL = "空值";
	public final static String COLUMN_DESCRIPTION = "字段说明";
	public final static String COLUMN_COMMENTS = "备注";
	public final static String COLUMN_REMARK = "字段注释";
	public final static String COLUMN_MARK = "标记";
	public final static String COLUMN_IS_PRIMARY_KEY = "是否主键";
	public final static String COLUMN_DEFAULT_VALUE = "默认值";
	
	public final static String INDEX_UNIQUE_Y = "Y";
	public final static String INDEX_CLUST_Y = "Y";
	public final static String INDEX_STATUS = "索引";
	public final static String INDEX_MARK = "标记";
	public final static String INDEX_NAME = "索引名称";
	public final static String INDEX_UNIQUE = "唯一";
	public final static String INDEX_CLUST = "聚簇";
	public final static String INDEX_COLUMNS = "索引字段";
	
	public final static String KEY_MARK = "标记";
	public final static String KEY_NAME = "名称";
	public final static String KEY_TYPE = "类型";
	public final static String KEY_COLUMNS = "字段列表";
	public final static String KEY_FOREIGN_KEY_TABLE = "外键参照表";
	public final static String KEY_FOREIGN_KEY_FIELD = "外键参照列";
	
	public final static String VIEW_SQL_NAME = "视图定义";
	
	
	public final static String SEQUENCE_INC = "步长";
	public final static String SEQUENCE_MIN = "最小值";
	public final static String SEQUENCE_MAX = "最大值";
	public final static String SEQUENCE_START = "起始值";
	public final static String SEQUENCE_CYC = "是否循环";
	public final static String SEQUENCE_CACHE = "是否缓存";
	public final static String SEQUENCE_CACHE_SIZE = "缓存大小";
	public final static String SEQUENCE_DATABASE_NAME = "数据库表";
	
	/**
	 * 获取表类型
	 * 
	 * @param key
	 * @return
	 */
	public static Integer getTableType (String key){
		return tableType.get(key) == null ? 0 : tableType.get(key);
	}
	
	/**
	 * 查看传入的标题是否是表格的主属性
	 * 
	 * @param title
	 * @return
	 */
	public static boolean isMainText (String title){
		if (textTitles.contains(title)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查看传入的标题是否是表格的主标题
	 * 
	 * @param title
	 * @return
	 */
	public static boolean isColumnMainTitle (String title){
		if (columnTitles.contains(title)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查看传入的标题是否是表格的主标题
	 * 
	 * @param title
	 * @return
	 */
	public static boolean isIndexMainTitle (String title){
		if (indexTitles.contains(title)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查看传入的标题是否是表格的主标题
	 * 
	 * @param title
	 * @return
	 */
	public static boolean isKeyMainTitle (String title){
		if (keyTitles.contains(title)) {
			return true;
		}
		return false;
	}
	
	public static boolean isExtendsText (String text){
		if (!textTitles.contains(text) && !StringUtils.equals(FIELD_DEFINE, text) && !StringUtils.equals(INDEX_DEFINE, text)) {
			return true;
		}
		return false;
	}
	
}
