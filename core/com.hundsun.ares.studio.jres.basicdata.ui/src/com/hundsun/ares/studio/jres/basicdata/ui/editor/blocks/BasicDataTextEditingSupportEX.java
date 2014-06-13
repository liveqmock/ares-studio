/**
 * 源程序名称：TextEditingSupport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import org.apache.commons.lang.ObjectUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

public class BasicDataTextEditingSupportEX extends EMFEditingSupport {


	IKeyTableLocator locator;
	EStructuralFeature feature;

	/**
	 * @param viewer
	 * @param featureProvider
	 */
	public BasicDataTextEditingSupportEX(ColumnViewer viewer,
			IEStructuralFeatureProvider featureProvider,
			IKeyTableLocator locator,
			EStructuralFeature feature) {
		super(viewer, featureProvider);
		this.locator = locator;
		this.feature = feature;
	}

	@Override
	protected CellEditor doGetCellEditor(Object element) {
		if(null == featureProvider.getFeature(element)){
			return null;
		}
		return super.doGetCellEditor(element);
	}
	
	@Override
	protected CellEditor createCellEditor() {
		return new TextCellEditor((Composite) getViewer().getControl());
	}
	
	@Override
	protected Object getValue(Object element) {
		// 防止null设置到Text中去
		return ObjectUtils.defaultIfNull(super.getValue(element), "");
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editingsupports.EMFEditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(Object element, Object value) {
		// 如果没有修改的必要，直接返回
		Object oldValue = getValue(element);
		if (ObjectUtils.equals(oldValue, value)) {
			return;
		}
		EObject owner = getOwner(element);
		
		if (EcoreUtil.getRootContainer(owner.eContainer()) instanceof ChangeDescription) {
			// FIXME 当在激活celleditor的时候进行撤销重做，可能导致这个对象成为变化的一部分，这种情况下虽然无法得到编辑域，但是也不应该进行编辑
			return;
		}
		
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(owner);
		
		if (editingDomain != null) {
			
			CompoundCommand command = new CompoundCommand();
			
			
			Command setCommand = SetCommand.create(editingDomain, owner, featureProvider.getFeature(element), value);
			command.append(setCommand);
			
			try {
				EObject copyElement =  EcoreUtil.copy(((EObject)element)) ;
				copyElement.eSet(featureProvider.getFeature(element), value);
				EObject refer =  locator.getLinkObject(copyElement);
				
				Command setReferCommand = SetCommand.create(editingDomain, owner,feature, refer);
				command.append(setReferCommand);
			} catch (Exception e) {
			}
			
			editingDomain.getCommandStack().execute(command);
		} else {
			owner.eSet(featureProvider.getFeature(element), value);
		}
		
		// 如果使用RefreshViewerJob会延迟显示正确内容
		getViewer().update(element, null);
		
	}
}
