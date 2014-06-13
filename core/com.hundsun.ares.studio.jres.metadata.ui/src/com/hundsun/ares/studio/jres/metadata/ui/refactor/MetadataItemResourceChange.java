/**
 * 源程序名称：MetadataItemResourceChange.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.refactor;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.resource.ResourceChange;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.ResourceTypeMapping;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;

/**
 * @author qinyuan
 *
 */
public class MetadataItemResourceChange extends ResourceChange {
	
	private String oldName;
	private String newName;
	private MetadataItem item;
	private IARESResource resource;
	private EditingDomain editingDomain;
	
	/**
	 * @param oldName
	 * @param newName
	 * @param item
	 * @param editingDomain 
	 */
	public MetadataItemResourceChange(String oldName, String newName,
			MetadataItem item,IARESResource resource, EditingDomain editingDomain) {
		this.oldName = oldName;
		this.newName = newName;
		this.item = item;
		this.resource = resource;
		this.editingDomain = editingDomain;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.resource.ResourceChange#getModifiedResource()
	 */
	@Override
	protected IResource getModifiedResource() {
		return resource.getResource();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	@Override
	public String getName() {
		return item.getName() + " 重命名为: " + this.newName;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		
		//处理本资源引用 
		Command command = new ChangeCommand(item.eResource()) {
			
			@Override
			protected void doExecute() {
				EObject container = item.eContainer();
				@SuppressWarnings("unchecked")
				String type = ResourceTypeMapping.getInstance().getReferenceType(resource.getType());
				if(container instanceof MetadataResourceData){
					MetadataResourceData<? extends MetadataItem> resourceData = (MetadataResourceData<? extends MetadataItem>) container;
					for (Reference ref : resourceData.getReferences()) {
						// BUG #3155::业务数据类型中引用的标准数据类型及默认值的ID与业务数据类型ID相同，
						// 需要判断引用类型
						if(ref.getType().equals(type) &&  ref.getValue().equals(oldName)) {
							ref.setValue(newName);
						}
					}
				}
				
			}
		};
		
		//处理自身
		command = command.chain(SetCommand.create(editingDomain, item, MetadataPackage.Literals.NAMED_ELEMENT__NAME, newName)) ;
		editingDomain.getCommandStack().execute(command);
		
		return new MetadataItemResourceChange(oldName,newName,item,resource,editingDomain);
	}

}
