/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.model.MetadataOverviewElement;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author liaogc
 * 数据字典冲突ContentProvider
 */
public class DictionaryConflictContentProvider  implements ITreeContentProvider{
	private IARESResource resource;
	private String type;

	/**
	 * 
	 */
	public DictionaryConflictContentProvider(IARESResource resource, String type) {
		this.resource = resource;
		this.type = type;
	}

	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataOverviewContentProvider#getElements(java.lang.Object)
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
		
		List< MetadataOverviewElement > conflictElementList = new ArrayList<MetadataOverviewElement>();//冲突的条目
		
		
		// 查找同名冲突的条目
		{
			Map<String, MetadataOverviewElement> nameSet = new HashMap<String, MetadataOverviewElement>();
			
			for (MetadataOverviewElement element : elementList) {
				String name = element.getItem().getName();
				 if (nameSet.containsKey(name)) {
					MetadataOverviewElement firstElement = nameSet.get(name);
					if(!conflictElementList.contains(firstElement)){
						conflictElementList.add(firstElement);
					}
					conflictElementList.add(element);
				} else {
					nameSet.put(name, element);
				}
			}
		}

		
		return conflictElementList.toArray();
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

}
