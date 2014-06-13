/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.database.resource.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.ARESResourceObjectProvider;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.EMFReferenceObjectProvider;
import com.hundsun.ares.studio.reference.IReferenceInfoProvider;

/**
 * @author gongyf
 *
 */
public class DatabaseReferenceInfoProvider implements IReferenceInfoProvider {

	/**
	 * 
	 */
	public DatabaseReferenceInfoProvider() {

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.reference.IReferenceInfoProvider#getReferenceInfos(com.hundsun.ares.studio.core.IARESResource, java.util.Map)
	 */
	@Override
	public List<ReferenceInfo> getReferenceInfos(IARESResource resource,
			Map<Object, Object> context) {
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		try {
			if (IDatabaseResType.Table.equalsIgnoreCase(resource.getType())) {
				TableResourceData table = resource.getInfo(TableResourceData.class);
				if (table == null) {
					return infoList;
				}
				ReferenceInfo info = ReferenceFactory.eINSTANCE.createReferenceInfo();
				info.setResource(resource);
				info.setRefName(table.getName());
				info.setRefType(IDatabaseRefType.Table);
				info.setRefNamespace(StringUtils.EMPTY);
				info.setObjectProvider(ARESResourceObjectProvider.INSTANCE);
				infoList.add(info);
				
				
				// 表字段
				for (int i = 0; i < table.getColumns().size(); i++) {
					TableColumn col = table.getColumns().get(i);
					
					ReferenceInfo colInfo = ReferenceFactory.eINSTANCE.createReferenceInfo();
					colInfo.setRefName(col.getName());
					colInfo.setRefType(IDatabaseRefType.TableField);
					colInfo.setRefNamespace(StringUtils.EMPTY);
					colInfo.setObjectProvider(new EMFReferenceObjectProvider(
							DatabasePackage.Literals.TABLE_RESOURCE_DATA, DatabasePackage.Literals.TABLE_RESOURCE_DATA__COLUMNS, i));
					colInfo.setResource(resource);
					
					infoList.add(colInfo);
				}
			}
			if (IDatabaseResType.View.equalsIgnoreCase(resource.getType())) {
				ViewResourceData view = resource.getInfo(ViewResourceData.class);
				if (view == null) {
					return infoList;
				}
				ReferenceInfo info = ReferenceFactory.eINSTANCE.createReferenceInfo();
				info.setResource(resource);
				info.setRefName(view.getName());
				info.setRefType(IDatabaseRefType.View);
				info.setRefNamespace(StringUtils.EMPTY);
				info.setObjectProvider(ARESResourceObjectProvider.INSTANCE);
				infoList.add(info);
			}
			
		} catch (ARESModelException e) {
		}
		
		return infoList;
	}

}
