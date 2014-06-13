/**
 * 源程序名称：MetadataItemRefResourceChange.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.refactor;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.resource.ResourceChange;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.ResourceTypeMapping;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.ConditionReference;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;

/**
 * @author qinyuan
 *
 */
public class MetadataItemRefResourceChange  extends ResourceChange {
	
	private String oldName;
	private String newName;
	private MetadataItem item;
	private IARESResource selfResource; 	//元数据的资源，重命名的发起者
	private IARESResource resource; 	//重命名的item所在的资源
	
	/**
	 * @param oldName
	 * @param newName
	 * @param item
	 */
	public MetadataItemRefResourceChange(String oldName, String newName,IARESResource selfRes ,IARESResource tarRes) {
		this.oldName = oldName;
		this.newName = newName;
		this.selfResource = selfRes;
		this.resource = tarRes;
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
		return oldName + " 重命名为: " + this.newName;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		
		Class<?> clazz = null;
		IReferenceProvider provider = (IReferenceProvider)resource.getAdapter(IReferenceProvider.class);
		String type = ResourceTypeMapping.getInstance().getReferenceType(selfResource.getType());
		boolean isChanged = false;
		for ( Reference  reference : provider.getReferences()) {
			if (reference instanceof ConditionReference){//带有其他条件的reference
				Map<Object,Object> parameters = new HashMap<Object,Object>();
				String version = selfResource.getARESProject().getProjectProperty().getVersion();
				parameters.put("oldValue", oldName);
				parameters.put("newValue", newName);
				parameters.put("version", version);
				if(StringUtils.equals(type, reference.getType())&& ((ConditionReference)reference).canDo(parameters)){//是否能够要根据其他条件来确定
					reference.setValue(newName);
					isChanged = true;
				}
			}else if(StringUtils.equals(type, reference.getType()) && StringUtils.equals(oldName, reference.getValue())){//一般性重构
				reference.setValue(newName);
				isChanged = true;
			}
		}
		//添加修改记录
		ARESResRegistry reg = ARESResRegistry.getInstance();
		IResDescriptor desc = reg.getResDescriptor(resource.getElementName());
		if (desc == null){
			desc = reg.getResDescriptor(resource.getType());
		}
		clazz = desc.createInfo().getClass();	
		
		
		if(isChanged){
			//添加修改记录
			resource.save(resource.getInfo(clazz), true, null);//每个资源只保存一次
		}
		
		
		return new MetadataItemRefResourceChange(newName , oldName ,selfResource , resource);
	}



}
