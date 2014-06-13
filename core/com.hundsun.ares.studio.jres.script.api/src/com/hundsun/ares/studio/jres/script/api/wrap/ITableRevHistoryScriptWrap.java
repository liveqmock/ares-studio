/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableScriptWrap;

/**
 * 修订记录
 * 
 * @author yanwj06282
 *
 */
public interface ITableRevHistoryScriptWrap extends IRevHistoryScriptWrap{

	/**
	 * 获取修改类型
	 * 
	 * @return
	 */
	public String getActionType ();
	
	/**
	 * 获取修订记录信息
	 * 
	 * <li>
	 * 根据当前修订记录的类型，得到各自的包装对象，列表如下:<br>
	 * 	<i>增加表，{@link IAddTableModificationScriptWrap}</i><br>
	 * 	<i>增加表字段，{@link IAddColModificationScriptWrap}</i><br>
	 *	<i>删除表字段，{@link IRemoveColModificationScriptWrap}</i><br>
	 *	<i>重命名表字段，{@link IRenameTableColModificationScriptWrap}</i><br>
	 * 	<i>增加索引，{@link IAddIndexModificationScriptWrap}</i><br>
	 * 	<i>删除索引，{@link IRemoveIndexModificationScriptWrap}</i><br>
	 * 	<i>修改表类型，{@link ITableColTypeModificationScriptWrap}</i><br>
	 * 	<i>增加主键，{@link IAddPKModificationScriptWrap}</i><br>
	 * 	<i>修改主键，{@link ITableColPKModificationScriptWrap}</i><br>
	 * 	<i>删除主键，{@link IRemovePKModificationScriptWrap}</i><br>
	 * 	<i>修改表字段为空，{@link ITableColNullableModificationScriptWrap}</i><br>
	 * 	<i>增加唯一约束，{@link IAddUniqueModificationScriptWrap}</i><br>
	 * 	<i>修改唯一约束，{@link ITableColUniqueModificationSctiptWrap}</i><br>
	 * 	<i>删除唯一约束，{@link IRemoveUniqueModificationScriptWrap}</i><br>
	 * </li>
	 * @return
	 * 
	 */
	public IModificationScriptWrap getAction ();
	
	/**
	 * 获取表封装对象
	 * 
	 * @return
	 */
	public ITableScriptWrap getTableInfo ();
	
}
