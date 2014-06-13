/**
 * 源程序名称：BaseEditingSupport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.editingsupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

/**
 * @author gongyf
 *
 */
public abstract class BaseEditingSupport extends EditingSupport {

	public CellEditor cellEditor;
	private IEditingSupportDecorator decorator;
	
	/**
	 * @param viewer
	 */
	public BaseEditingSupport(ColumnViewer viewer) {
		super(viewer);
	}

	/**
	 * @param decorator the decorator to set
	 */
	public void setDecorator(IEditingSupportDecorator decorator) {
		this.decorator = decorator;
	}

	@Override
	final public CellEditor getCellEditor(Object element) {
		if (decorator == null) {
			return doGetCellEditor(element);
		} else {
			return decorator.decorateGetCellEditor(doGetCellEditor(element), element);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
	 */
	@Override
	final protected boolean canEdit(Object element) {
		if (decorator == null) {
			return doCanEdit(element);
		} else {
			return decorator.decorateCanEdit(doCanEdit(element), element);
		}
	}
	
	
	protected CellEditor doGetCellEditor(Object element) {
		if (cellEditor == null) {
			cellEditor = createCellEditor();
		}
		return cellEditor;
	}
	
	protected boolean doCanEdit(Object element) {
		// 如果是EObject，则判断所在的Resource是否只读
		if (element instanceof EObject) {
			Resource resource = ((EObject) element).eResource();
			if (resource != null) {
				EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(element);
				if (editingDomain != null) {
					return !editingDomain.isReadOnly(resource);
				}
				
			}
		}
		
		return true;
	}
	/**
	 * 创建被使用的CellEditor
	 * @return
	 */
	protected abstract CellEditor createCellEditor();

}
