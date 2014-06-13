package com.hundsun.ares.studio.jres.script.api.database;


public interface ITableKeyScriptWrap {
	
	/**名称*/
	public String getName();
	
	/**字段列表*/
	public ITableColScriptWrap[] getColumns();
	
	/**类型，值为：主键/唯一/外键*/
	public String getType();
	
	/**外键列表*/
	public ITableColForergnKeyScriptWrap[] getForeignKey(); 
	
	/**标记*/
	public String getMark();
}
