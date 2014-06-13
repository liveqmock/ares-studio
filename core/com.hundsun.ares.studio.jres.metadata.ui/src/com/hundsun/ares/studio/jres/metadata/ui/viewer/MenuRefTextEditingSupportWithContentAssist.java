/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.apache.commons.lang.ObjectUtils;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;

/**
 * @author wangxh
 *
 */
public class MenuRefTextEditingSupportWithContentAssist extends
		JresTextEditingSupportWithContentAssist {
	
	IARESResource resource;
	
	public MenuRefTextEditingSupportWithContentAssist(ColumnViewer viewer,
			AresContentProposalProvider proposalProvider,IARESResource resource) {
		super(viewer, MetadataPackage.Literals.METADATA_ITEM__REF_ID, proposalProvider);
		this.resource = resource;
	}
	
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
		CompoundCommand cmd = new CompoundCommand();
		EObject resolveItem = getResolveItem(element, value);
		if (editingDomain != null) {
			cmd.append(SetCommand.create(editingDomain, owner, MetadataPackage.Literals.METADATA_ITEM__REF_ID, value));
			if(resolveItem != null && !resolveItem.equals(element)){
				MenuItem item = (MenuItem)resolveItem;
				cmd.append(SetCommand.create(editingDomain, owner, MetadataPackage.Literals.MENU_ITEM__URL, item.getUrl()));
				cmd.append(SetCommand.create(editingDomain, owner, MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME, item.getChineseName()));
				cmd.append(SetCommand.create(editingDomain, owner, MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION, item.getDescription()));
				cmd.append(SetCommand.create(editingDomain, owner, MetadataPackage.Literals.NAMED_ELEMENT__NAME, item.getName()));
				cmd.append(SetCommand.create(editingDomain, owner, MetadataPackage.Literals.MENU_ITEM__ICON, item.getIcon()));
//				EMap<String, EObject> data2 = getItemData2(item);
//				for(Entry<String, EObject> entry : data2.entrySet()){
//					cmd.append(new PutCommand(owner, CorePackage.Literals.EXTENSIBLE_MODEL__DATA2,entry.getKey() ,entry.getValue()));
//				}
			}
			
			editingDomain.getCommandStack().execute(cmd);
		} else {
			owner.eSet(MetadataPackage.Literals.METADATA_ITEM__REF_ID, value);
			if(resolveItem != null && !resolveItem.equals(element)){
				MenuItem item = (MenuItem)resolveItem;
				owner.eSet(MetadataPackage.Literals.MENU_ITEM__URL, item.getUrl());
				owner.eSet(MetadataPackage.Literals.NAMED_ELEMENT__NAME, item.getName());
				owner.eSet(MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME, item.getChineseName());
				owner.eSet(MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION, item.getDescription());
				owner.eSet(MetadataPackage.Literals.MENU_ITEM__ICON, item.getIcon());
//				owner.eSet(CorePackage.Literals.EXTENSIBLE_MODEL__DATA2, getItemData2(item));
			}
		}
		
		// 如果使用RefreshViewerJob会延迟显示正确内容
		getViewer().update(element, null);
	}

//	private EMap<String, EObject> getItemData2(ExtensibleModel model){
//		EMap<String, EObject> map = new BasicEMap<String, EObject>();
//		EMap<String, EObject> data2 = model.getData2();
//		for(Entry<String, EObject> entry :data2.entrySet()){
//			EClass eClass = entry.getValue().eClass();
//			EObject obj = eClass.getEPackage().getEFactoryInstance().create(eClass);
//			if(obj instanceof UserExtensibleProperty){
//				for(Entry<String, String> e : ((UserExtensibleProperty)entry.getValue()).getMap().entrySet()){
//					((UserExtensibleProperty)obj).getMap().put(e.getKey(), e.getValue());
//				}
//			}
//			map.put(entry.getKey(), obj);
//		}
//		return map;
//	}
	
	protected EObject getResolveItem(Object element, Object value) {
		if ( MetadataUtil.isUseRefFeature(resource)) {
			// 如果项目启用了引用则处理
			if (element instanceof MenuItem) {
				// 对于引用的处理
				MenuItem item = (MenuItem) ((MetadataItem)element).eClass().getEPackage().getEFactoryInstance().create(((MetadataItem)element).eClass());
				item.setRefId((String) value);
				MetadataItem entity = null;
				entity = MenuUtils.resolve(item, resource).first;
				return entity == null ? (MetadataItem)element : entity;
			}
		}

		return null;
	}
//	private EList<FunctionProxy> getFunctionProxys(MenuItem item){
//		EList<FunctionProxy> list = new BasicEList<FunctionProxy>();
//		for(FunctionProxy proxy : item.getFunctionProxys()){
//			FunctionProxy funcProxy = MetadataFactory.eINSTANCE.createFunctionProxy();
//			funcProxy.setFunCode(proxy.getFunCode());
//			funcProxy.setDescription(proxy.getDescription());
//			funcProxy.getData2().putAll(getItemData2(proxy));
//			list.add(funcProxy);
//		}
//		return list;
//	}
}
