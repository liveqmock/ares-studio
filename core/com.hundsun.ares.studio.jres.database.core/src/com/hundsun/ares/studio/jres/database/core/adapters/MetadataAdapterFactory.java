/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.database.core.adapters;

import org.eclipse.core.runtime.IAdapterFactory;

import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;

/**
 * @author gongyf
 *
 */
public class MetadataAdapterFactory implements IAdapterFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof StandardField) {
			
			StandardField sf = (StandardField) adaptableObject;
			
			if (adapterType.equals(TableColumn.class)) {
				TableColumn column = DatabaseFactory.eINSTANCE.createTableColumn();
				column.setName(sf.getName());
				return column;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	@Override
	public Class[] getAdapterList() {
		return new Class[]{ 
				TableColumn.class
		};
	}

}
