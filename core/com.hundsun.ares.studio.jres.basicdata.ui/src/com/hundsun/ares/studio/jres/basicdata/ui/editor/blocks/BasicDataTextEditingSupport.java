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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.jres.basicdata.ui.proposal.BasicDataContentProposalProvider;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider;
import com.hundsun.ares.studio.ui.cellEditor.TextCellEditorWithContentAssist;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

public class BasicDataTextEditingSupport extends EMFEditingSupport {

	private AresContentProposalProvider proposalProvider;

	/**
	 * @param viewer
	 * @param featureProvider
	 */
	public BasicDataTextEditingSupport(ColumnViewer viewer,
			IEStructuralFeatureProvider featureProvider,AresContentProposalProvider proposalProvider) {
		super(viewer, featureProvider);
		this.proposalProvider = proposalProvider;
	}

	@Override
	protected CellEditor doGetCellEditor(Object element) {
		if(null == featureProvider.getFeature(element)){
			return null;
		}
//		return super.doGetCellEditor(element);
		TextCellEditorWithContentAssist celleditor = (TextCellEditorWithContentAssist) super.doGetCellEditor(element);
		AresContentProposalProvider proposalProvider = (AresContentProposalProvider) celleditor.getProposalProvider();
		proposalProvider.updateContent(element);
		return cellEditor;
	}
	
	@Override
	protected CellEditor createCellEditor() {
//		return new TextCellEditor((Composite) getViewer().getControl());
		return new TextCellEditorWithContentAssist((Composite)getViewer().getControl()) {
			@Override
			public IContentProposalProvider getProposalProvider() {
				return proposalProvider;
			}
		};
	}
	
	@Override
	protected boolean doCanEdit(Object element) {
		boolean can = super.doCanEdit(element);
		if (!can) {
			EObject owner = getOwner(element);
			Map<String , EAttribute> attMap = new HashMap<String, EAttribute>();
			for(EAttribute att : owner.eClass().getEAllAttributes()){
				attMap.put(att.getName(), att);
			}
			if (element != null && owner != null && attMap.get(featureProvider.getFeature(element).getName()) != null) {
				return true;
			}else {
				return false;
			}
		}else {
			return true;
		}
	}
	
	@Override
	protected Object getValue(Object element) {
		Map<String , EAttribute> attMap = new HashMap<String, EAttribute>();
		EObject owner = getOwner(element);
		Object res = null;
		if (owner != null) {
			for(EAttribute att : owner.eClass().getEAllAttributes()){
				attMap.put(att.getName(), att);
			}
			res = owner.eGet(attMap.get(featureProvider.getFeature(element).getName()));
		}
		// 防止null设置到Text中去
		return ObjectUtils.defaultIfNull(res, "");
	}
	
	@Override
	protected void setValue(Object element, Object value) {
		// 如果没有修改的必要，直接返回
		Object oldValue = getValue(element);
		if(proposalProvider instanceof BasicDataContentProposalProvider){
			Object[] input = ((BasicDataContentProposalProvider) proposalProvider).getInput();
			if(input != null){
				for(Object item : input){
					String name = ((DeDictionaryItem)item).getValue() + "_" + ((DeDictionaryItem)item).getChineseName();
					if(name.equals(value)){
						value = ((DeDictionaryItem)item).getValue();
						break;
					}
				}
			}
		}
		if (ObjectUtils.equals(oldValue, value)) {
			return;
		}
		EObject owner = getOwner(element);
		
		if (EcoreUtil.getRootContainer(owner.eContainer()) instanceof ChangeDescription) {
			// FIXME 当在激活celleditor的时候进行撤销重做，可能导致这个对象成为变化的一部分，这种情况下虽然无法得到编辑域，但是也不应该进行编辑
			return;
		}
		
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(owner);
		
		Map<String , EAttribute> attMap = new HashMap<String, EAttribute>();
		for(EAttribute att : owner.eClass().getEAllAttributes()){
			attMap.put(att.getName(), att);
		}
		EStructuralFeature feature = attMap.get(featureProvider.getFeature(element).getName());
		
		if (editingDomain != null) {
			Command command = SetCommand.create(editingDomain, owner, feature, value);
			editingDomain.getCommandStack().execute(command);
		} else {
			owner.eSet(feature, value);
		}
		
		// 如果使用RefreshViewerJob会延迟显示正确内容
		getViewer().update(element, null);
	}

}
