/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.util;

/**
 * 表格动作操作提供器。
 * 
 * @author mawb
 */
public interface ITableActionHandleProvider {
	
	/**
	 * 是否激活表格操作。
	 * 
	 * @return
	 */
	boolean isActivate();
	
	/**
	 * 设置是否激活表格操作。
	 * 
	 * @param isActivate
	 * @return
	 */
	void setActivate(Boolean isActivate);
	
	/**
	 * 是否可新增。
	 * 
	 * @return
	 */
	boolean canAdd();
	
	/**
	 * 是否可删除。
	 * 
	 * @return
	 */
	boolean canDelete();
	
	/**
	 * 是否可插入。
	 * 
	 * @return
	 */
	boolean canInsert();
	
	/**
	 * 是否可复制。
	 * 
	 * @return
	 */
	boolean canCopy();
	
	/**
	 * 是否可剪切。
	 * 
	 * @return
	 */
	boolean canCut();
	
	
	/**
	 * 是否可粘贴。
	 * 
	 * @return
	 */
	boolean canPaste();
	
	/**
	 * 添加。
	 */
	void add();
	
	/**
	 * 删除。
	 */
	void delete();
	
	/**
	 * 插入。
	 */
	void insert();
	
	/**
	 * 复制。
	 */
	void copy();
	
	/**
	 * 剪切。
	 */
	void cut();
	
	/**
	 * 粘贴。
	 */
	void paste();
	
}
