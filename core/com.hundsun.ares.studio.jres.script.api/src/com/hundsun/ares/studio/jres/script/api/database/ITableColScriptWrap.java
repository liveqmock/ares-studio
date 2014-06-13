/**
 * 源程序名称：ITableColumnScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.script.api.database;

import com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap;


/**
 * 
 * 数据库表字段
 * 
 * @author lvgao
 *
 */
public interface ITableColScriptWrap {
	
	/**
	 * 获得字段对应的sql脚本语句,需要处理单引号的转义
	 * 可选类型：oracle\db2\sqlserver\c\java\mysql\informix\sybase\C#
	 * 
	 * @return
	 */
	public String getEscapeSql(String type);
	
    /**
     * 获得字段对应的sql脚本语句 ， 不需要处理单引号的转义
     * 可选类型：oracle\db2\sqlserver\c\java\mysql\informix\sybase\C#
     * @return
     */
	public String getSql(String type)	;
	
	/**
	 * 	获得该字段名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 获得字段对应的标准字段对象
	 * 
	 * @return
	 */
	public IStandardFieldScriptWrap getstdField();
	
	/**
	 * 是否主键
	 * 
	 * @return
	 */
	public boolean isPrimaryKey();
	
	/**
	 * 是否唯一
	 * @return
	 */
	public boolean isUnique();
	
	/**
	 * 是否允许为空
	 * 
	 * @return
	 */
	public boolean isNullable();
	
	/**
	 * 获取默认值
	 * 可选类型：oracle\db2\sqlserver\c\java\mysql\informix\sybase\C#
	 * 
	 * @return
	 */
	public String getDefaultValue(String type);
	
	/**
	 * 获取标记
	 * 
	 * @return
	 */
	public String getMark();
	
	/**
	 * 	获得此字段对应的外键
	 * @return
	 */
	public String[] getForeignkey();
	
	/**
	 * 	获得此字段对应的标准字段的中文名称
	 * @return
	 */
	public String getstdFieldChineseName();
	
	/**
	 * 获取外键
	 * 
	 * @return
	 */
	public ITableColForergnKeyScriptWrap[] getForeignKey(); 
	
	/**
	 * 获取该列对应的oracle的数据类型
	 * 可选类型：oracle\db2\sqlserver\c\java\mysql\informix\sybase\C#
	 * 
	 * @return
	 */
	public String getRealDataType(String type);
	
	/**
	 * 设置备注信息
	 * @param comments
	 */
	public void setComments(String comments);
	
	/**
	 * 设置字段名
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * 设置数据类型，只有在非标字段时，才有效
	 * @param name
	 */
	public void setBizType(String name);
	
	/**
	 * 获取数据类型，在非标字段下，获取该字段的文本业务类型，如果为标准字段，则取标准字段对应的文本业务类型
	 */
	public String getStrBizType();
}
