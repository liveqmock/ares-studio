/**
 * 源程序名称：ITableScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.script.api.database;

import java.util.List;

import com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseResScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ITableRevHistoryScriptWrap;

/**
 * 数据库表
 * 
 * @author lvgao
 *
 */
public interface ITableScriptWrap  extends IDatabaseResScriptWrap{

	/**
	 * 获取表名
	 * 
	 * @return
	 */
	public String getTableName();
	
	/**
	 * 返回表类型
	 * 0 "一般表";
	 * 1 "临时表(不保留数据)";
	 * 2 "临时表(保留数据)";
	 *	}
	 * @return
	 */
	public int getTableType();
	
	/**
	 * 	返回分区字段（可通过扩展字段的json取法取得）
	 * 	2013年5月27日14:56:26 mod qinyuan 
	 *  如果为自定义，取表自身分区字段；否则取模块分区字段
	 *  注：如果模块分区信息为空，则取祖父模块
	 * @return
	 */
	public String  getPartitionfield();
	
	/**
	 * 	是否自定义分区（可通过扩展字段的json取法取得）
	 * @return
	 */
	public boolean isPartitionByUser();
	
	/**
	 * 设置是否生成历史表
	 * 
	 * @param isGenHisTable
	 */
	public void setGenHisTable(boolean isGenHisTable);
	
	/**
	 * 是否存在历史表
	 */
	public boolean isGenHisTable ();
	
	/**
	 * 是否存在冗余表
	 */
	public boolean isGenReduTable ();
	
	/**
	 * 是否存在清算表
	 */
	public boolean isGenSettTable ();
	
	/**
	 * 设置对象号
	 * 
	 * @param objectId
	 */
	public void setObjectId(String objectId);
	
	
	/**
	 * 获得索引表空间
	 * 
	 * @param prefix
	 * @return
	 */
	public String getIndexTableSpace(String prefix);
	
	/**
	 * 	获得分区开始时间
	 * 2013年5月27日14:56:26 mod qinyuan 
	 *  如果为自定义，取表自身分区开始时间；否则取模块分区开始时间
	 *  注：如果模块分区信息为空，则取祖父模块
	 * @return
	 */
	public String getPartitionStartDate();
	
	/**
	 * 	获得分区数量
	 * 2013年5月27日14:56:26 mod qinyuan 
	 *  如果为自定义，取表自身分数量；否则取模块分区数量
	 *  注：如果模块分区信息为空，则取祖父模块
	 * @return
	 */
	public int getPartitionNum();
	
	/**
	 * 获得表注释的sql语句
	 * @param prefix
	 * @param 	true:创建数据库资源SQL，带用户 ;false:创建数据库资源SQL，不带用户
	 * 
	 * @return
	 */
	public String getCommentSql(String prefix , boolean isUser);
	
	/**
	 * 	获得注释头sql（按修订记录倒序组织）
	 * @return
	 */
	public String getHistoryComment(String commentMark);
	
	/**
	 * 设置表格列
	 * @return
	 */
	public void setTableColumns(List<ITableColScriptWrap> columns);
	
	/**
	 * 设置表格索引
	 * @return
	 */
	public void setTableIndexs(List<ITableIndexScriptWrap> indexs);
	
	/**
	 * 设置表格约束
	 * @return
	 */
	public void setTableKeys(List<ITableKeyScriptWrap> keys);
	
	/**
	 * 获取表格列
	 * @return
	 */
	public ITableColScriptWrap[] getTableColumns();
	
	/**
	 * 获取表格索引
	 * @return
	 */
	public ITableIndexScriptWrap[] getTableIndexs();
	
	/**
	 * 获取表格索引
	 * @return
	 */
	public ITableKeyScriptWrap[] getTableKeys();
	
	/**
	 * 获取字段的中文名
	 * 
	 * @param fieldName
	 * @return
	 */
	public String getStdFieldChineseName(String fieldName);
	
	/**
	 * 获取数据类型
	 * 
	 * @param type
	 * @return
	 */
	public String getDataTypeOracle(String type);
	
	/**
	 * 获取表封装对象
	 * 
	 * @param revHistory
	 * @return
	 */
	public ITableScriptWrap getTableInfoByHisInfo(ITableRevHistoryScriptWrap revHistory);
	
	/**
	 * 设置修订记录的封装对象
	 * 
	 * @param history
	 */
	public void setHistory(ITableRevHistoryScriptWrap history);
	
	
	/**
	 * 获取修订记录的封装对象
	 */
	public ITableRevHistoryScriptWrap getHistory();
	
	/**
	 * 根据表字段名，获取字段对象
	 * @param column_name
	 * @return
	 */
	public ITableColScriptWrap getTableColumnByName(String column_name);
	
}
