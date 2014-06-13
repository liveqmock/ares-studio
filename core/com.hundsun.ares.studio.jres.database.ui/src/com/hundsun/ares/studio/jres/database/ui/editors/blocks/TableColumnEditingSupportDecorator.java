/**
 * 源程序名称：TableColumnEditingSupportDecorator.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors.blocks;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.viewers.CellEditor;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator;

/**
 * @author sundl
 *
 */
public class TableColumnEditingSupportDecorator implements IEditingSupportDecorator{

	private IARESProject project;
	private EAttribute attribute;
	
	/**
	 * @param project
	 * @param attribute
	 */
	public TableColumnEditingSupportDecorator(IARESProject project, EAttribute attribute) {
		super();
		this.project = project;
		this.attribute = attribute;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator#decorateGetCellEditor(org.eclipse.jface.viewers.CellEditor, java.lang.Object)
	 */
	@Override
	public CellEditor decorateGetCellEditor(CellEditor cellEditor, Object element) {
		return cellEditor;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator#decorateCanEdit(boolean, java.lang.Object)
	 */
	@Override
	public boolean decorateCanEdit(boolean canEdit, Object element) {
		if (element instanceof TableColumn) {
			TableColumn c = (TableColumn) element;
			// 非标准字段所有属性都可以编辑
			if (c.getColumnType() == ColumnType.NON_STD_FIELD) {
				return true;
			} else if (c.getColumnType() == ColumnType.STD_FIELD) {
				// 标准字段
				if (attribute.equals(DatabasePackage.Literals.TABLE_COLUMN__CHINESE_NAME)
						|| attribute.equals(DatabasePackage.Literals.TABLE_COLUMN__DATA_TYPE)
						|| attribute.equals(DatabasePackage.Literals.TABLE_COLUMN__DESCRIPTION)) {
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
	}
	
}
