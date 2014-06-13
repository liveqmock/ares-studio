/**
 * 源程序名称：IndexColumnEditingSupport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.ui.viewer;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.editors.celleditor.TableIndexColomnsCellEditor;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;

/**
 * 表索引字段列表的编辑支持
 * 
 * @author wangxh
 * 
 */
public class IndexColumnEditingSupport extends EMFEditingSupport {

	IARESResource resource;
	/**
	 * @param viewer
	 * @param feature
	 * @param resource
	 */
	public IndexColumnEditingSupport(ColumnViewer viewer,
			EStructuralFeature feature,IARESResource resource) {
		super(viewer, feature);
		this.resource = resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.ui.editingsupports.BaseEditingSupport#
	 * createCellEditor()
	 */
	@Override
	protected CellEditor createCellEditor() {
		return new TableIndexColomnsCellEditor(getViewer(), resource,"表字段", "已选列");
	}
}
