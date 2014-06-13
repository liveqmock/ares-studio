/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * @author liaogc
 *
 */
public class BasicDataDictTextEditingSupport extends EMFEditingSupport {

	private AresContentProposalProvider proposalProvider;

	/**
	 * @param viewer
	 * @param featureProvider
	 */
	public BasicDataDictTextEditingSupport(ColumnViewer viewer,
			IEStructuralFeatureProvider featureProvider,AresContentProposalProvider proposalProvider) {
		super(viewer, featureProvider);
		this.proposalProvider = proposalProvider;
	}

	@Override
	protected CellEditor doGetCellEditor(Object element) {
		if(null == featureProvider.getFeature(element)){
			return null;
		}
		BasicDataDictDialogCellEditor celleditor = (BasicDataDictDialogCellEditor) super.doGetCellEditor(element);
		//AresContentProposalProvider proposalProvider = (AresContentProposalProvider) celleditor.getProposalProvider();
		//proposalProvider.updateContent(element);
		return cellEditor;
	}
	
	@Override
	protected CellEditor createCellEditor() {
		BasicDataDictDialogCellEditor cellEditor = new BasicDataDictDialogCellEditor(getDictList(),(Composite)getViewer().getControl());
		return cellEditor;
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
		/*if(proposalProvider instanceof BasicDataContentProposalProvider){
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
		}*/
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
	
	/**
	 * 返回所有的数据字段项
	 * @return
	 */
	private List<DeDictionaryItem> getDictList(){
		 List<DeDictionaryItem> dictElements = new ArrayList<DeDictionaryItem>();
		 if(proposalProvider instanceof BasicDataContentProposalProvider){
				Object[] input = ((BasicDataContentProposalProvider) proposalProvider).getInput();
				if(input != null){
					for(Object item : input){
						DeDictionaryItem dictItem = ((DeDictionaryItem)item);
						dictElements.add(dictItem);
					}
				}
			}
		 return dictElements;
	}

}
