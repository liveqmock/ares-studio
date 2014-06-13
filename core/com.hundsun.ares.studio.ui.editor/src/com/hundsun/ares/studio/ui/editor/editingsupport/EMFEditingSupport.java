/**
 * 源程序名称：EMFEditingSupport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.editingsupport;

import org.apache.commons.lang.ObjectUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.ui.editor.blocks.DisplayItem;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * 对于EMF模型的编辑支持，可适用于EditDomain下的编辑，此时会采用命令的方式执行
 * @author gongyf
 */
public abstract class EMFEditingSupport extends BaseEditingSupport {

	protected IEStructuralFeatureProvider featureProvider = null;

	public EMFEditingSupport(ColumnViewer viewer, EStructuralFeature feature) {
		this(viewer, new IEStructuralFeatureProvider.Impl(feature));
	}

	public EMFEditingSupport(ColumnViewer viewer, IEStructuralFeatureProvider featureProvider) {
		super(viewer);
		this.featureProvider = featureProvider;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
	 */
	@Override
	protected Object getValue(Object element) {
		EObject owner = getOwner(element);
		if (owner == null) {
			return null;
		}
		return owner.eGet(featureProvider.getFeature(element));
	}
	
	@Override
	protected boolean doCanEdit(Object element) {
		
		if (element instanceof DisplayItem)
			return false;
		
		EObject owner = getOwner(element);
		if (owner == null) {
			return false;
		}
		return super.doCanEdit(element) && owner.eClass().getEAllStructuralFeatures().contains(featureProvider.getFeature(element));
	}
	
	/**
	 * 获取需要操作的EObject
	 * @param element
	 * @return
	 */
	protected EObject getOwner(Object element) {
		return (EObject) element;
	}
	
	protected EStructuralFeature getFeature(Object element) {
		return featureProvider.getFeature(element);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(Object element, Object value) {
		
		// 如果没有修改的必要，直接返回
		Object oldValue = getValue(element);
		if (ObjectUtils.equals(oldValue, value) || value == null) {
			return;
		}
		EObject owner = getOwner(element);
		
		if (EcoreUtil.getRootContainer(owner.eContainer()) instanceof ChangeDescription) {
			// FIXME 当在激活celleditor的时候进行撤销重做，可能导致这个对象成为变化的一部分，这种情况下虽然无法得到编辑域，但是也不应该进行编辑
			return;
		}
		
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(owner);
		
		if (editingDomain != null) {
			Command command = SetCommand.create(editingDomain, owner, featureProvider.getFeature(element), value);
			editingDomain.getCommandStack().execute(command);
		} else {
			owner.eSet(featureProvider.getFeature(element), value);
		}
		
		// 如果使用RefreshViewerJob会延迟显示正确内容
		getViewer().update(element, null);
	}


}
