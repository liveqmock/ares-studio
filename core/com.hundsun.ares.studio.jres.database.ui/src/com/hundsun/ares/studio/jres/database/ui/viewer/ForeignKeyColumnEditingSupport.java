/**
 * 源程序名称：ForeignKeyColumnEditingSupport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.viewer;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.editors.celleditor.TableForeignKeyColumnCellEditor;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;

/**
 * @author liaogc
 * 
 */
public class ForeignKeyColumnEditingSupport extends EMFEditingSupport {
	
	private IARESResource resource;

	/**
	 * @param viewer
	 * @param feature
	 * @param resource
	 */
	public ForeignKeyColumnEditingSupport(ColumnViewer viewer,
			EStructuralFeature feature, IARESResource resource) {
		super(viewer, feature);
		this.resource = resource;
	}

	@Override
	protected CellEditor createCellEditor() {
		return new TableForeignKeyColumnCellEditor(getViewer(), resource, "表字段",	"外键");
	}

}
