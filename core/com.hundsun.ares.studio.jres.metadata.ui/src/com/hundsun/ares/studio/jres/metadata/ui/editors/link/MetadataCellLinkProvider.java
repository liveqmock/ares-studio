/**
 * 源程序名称：MetadataCellLinkProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors.link;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.ui.editor.viewers.BaseEObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.viewers.link.ICellLinkProvider;

/**
 * @author gongyf
 *
 */
public class MetadataCellLinkProvider implements ICellLinkProvider {

	private ColumnViewer viewer;
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.link.ICellLinkProvider#getLinkedObject(org.eclipse.jface.viewers.ViewerCell)
	 */
	@Override
	public Pair<EObject, IARESResource> getLinkedObject(ViewerCell cell) {
		EStructuralFeature feature = ((BaseEObjectColumnLabelProvider) viewer.getLabelProvider(cell.getColumnIndex())).getEStructuralFeature(cell.getElement());
		
		return getLinkedObject(cell.getElement(), feature);
	}
	
	protected Pair<EObject, IARESResource> getLinkedObject(Object element, EStructuralFeature feature) {
		return null;
	}

	
}
