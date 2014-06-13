/**
 * 源程序名称：ConstraintModifyEditingSupport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.biz.stock.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.clearinghouse.celleditor;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;

/**
 * 
 * @author sundl
 *
 */
public class ConstraintColumnsEditingSupport extends EMFEditingSupport {

	private IARESResource resource;
	private TableResourceData tableResourceData;
		public ConstraintColumnsEditingSupport(ColumnViewer viewer, EStructuralFeature feature, IARESResource resource, TableResourceData table) {
		super(viewer, feature);
		this.resource = resource;
		this.tableResourceData = table;
	}

	protected CellEditor doGetCellEditor(Object element) {
		return new ConstraintColumnsCellEditor((Composite) getViewer().getControl(), (ConstraintModifyDetail) element, this.tableResourceData, this.resource);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.editingsupport.BaseEditingSupport#createCellEditor()
	 */
	@Override
	protected CellEditor createCellEditor() {
		// 因为上面重写了doGetCellEditor所以这个没有用。
		return null;
	}

}
