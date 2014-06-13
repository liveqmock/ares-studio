/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;

/**
 * @author liaogc
 *
 */
public class DictionaryItemListContentProvider implements IStructuredContentProvider{
	private DictionaryList dictionaryList;
	public DictionaryItemListContentProvider(DictionaryList dictionaryList){
		this.dictionaryList = dictionaryList;
		
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
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		List<DictionaryItem> dictionaryItemList = new ArrayList<DictionaryItem>();
		if(dictionaryList!=null){
					EList<DictionaryType> dictionaryTypeList = dictionaryList.getItems();
					
					for(DictionaryType dictionaryType:dictionaryTypeList){
						for(DictionaryItem item: dictionaryType.getItems()){
							dictionaryItemList.add(item);
						}
						
					}
		}
		return dictionaryItemList.toArray();
	}

}
