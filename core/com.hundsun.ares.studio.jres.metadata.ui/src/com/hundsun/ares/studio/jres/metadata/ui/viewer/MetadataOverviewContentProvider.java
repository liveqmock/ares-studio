/**
 * 源程序名称：MetadataOverviewContentProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.model.MetadataOverviewElement;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author wangxh
 *
 */
public class MetadataOverviewContentProvider implements ITreeContentProvider {

	private IARESResource resource;
	private String type;

	/**
	 * 
	 */
	public MetadataOverviewContentProvider(IARESResource resource, String type) {
		this.resource = resource;
		this.type = type;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	

	@Override
	public void dispose() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		
		IARESProject curPrj = resource.getARESProject();
		
		List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(curPrj, type, true);
		List< MetadataOverviewElement > elementList = new ArrayList<MetadataOverviewElement>();
		
		for (ReferenceInfo referenceInfo : infoList) {
			IARESResource res = referenceInfo.getResource();
			MetadataItem owner = (MetadataItem) referenceInfo.getObject();
			
			// FIXME: 添加不是本工程的信息，本工程信息使用当前编辑模型的内容
			elementList.add(new MetadataOverviewElement(res, owner));
		}
		
		
		// 查找同名冲突的条目
		{
			Map<String, MetadataOverviewElement> nameSet = new HashMap<String, MetadataOverviewElement>();
			Set<String> errNameSet =  new HashSet<String>();
			
			for (MetadataOverviewElement element : elementList) {
				String name = element.getItem().getName();
				if (errNameSet.contains(name)) {
					// 是已经重复的名称
					element.setConflict(true);
				} else if (nameSet.containsKey(name)) {
					// 同样的名称已经被登记，则出现重名，将该名称移动到错误名称列表中
					element.setConflict(true);
					
					MetadataOverviewElement firstElement = nameSet.get(name);
					firstElement.setConflict(true);
					nameSet.remove(name);
					errNameSet.add(name);
				} else {
					element.setConflict(false);
					nameSet.put(name, element);
				}
			}
		}
		
		return elementList.toArray();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		return false;
	}

}
