/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.support;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;

/**
 * @author liaogc
 *
 */
public class IndexFeildAddColumnEditingSupport  extends EMFEditingSupport {

	IARESResource resource;
	/**
	 * @param viewer
	 * @param feature
	 * @param resource
	 */
	public IndexFeildAddColumnEditingSupport(ColumnViewer viewer,
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
		TableResourceData tableResourceData = null;
		try {
			tableResourceData = resource.getInfo(TableResourceData.class);
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return new AddIndexColomnsCellEditor(getViewer(),resource,tableResourceData,"增加索引字段", "已选增加字段");
	}
}
