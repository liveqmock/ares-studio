/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.database.oracle.resources.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESResourceObjectProvider;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleResType;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.EMFReferenceObjectProvider;
import com.hundsun.ares.studio.reference.IReferenceInfoProvider;

/**
 * @author gongyf
 *
 */
public class OracleReferenceInfoProvider implements IReferenceInfoProvider {

	/**
	 * 
	 */
	public OracleReferenceInfoProvider() {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.reference.IReferenceInfoProvider#getReferenceInfos(com.hundsun.ares.studio.core.IARESResource, java.util.Map)
	 */
	@Override
	public List<ReferenceInfo> getReferenceInfos(IARESResource resource,
			Map<Object, Object> context) {
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		
		try {
			if (resource.getType().equals(IOracleResType.Sequence)) {
				ReferenceInfo info = ReferenceFactory.eINSTANCE.createReferenceInfo();
				info.setRefName(resource.getFullyQualifiedName());
				info.setRefType(IOracleRefType.Sequence);
				info.setRefNamespace(StringUtils.EMPTY);
				info.setResource(resource);
				info.setObjectProvider(ARESResourceObjectProvider.INSTANCE);
				
				infoList.add(info);
				
			} else if (resource.getType().equals(IOracleResType.Trigger)) {
				ReferenceInfo info = ReferenceFactory.eINSTANCE.createReferenceInfo();
				info.setRefName(resource.getFullyQualifiedName());
				info.setRefType(IOracleRefType.Trigger);
				info.setRefNamespace(StringUtils.EMPTY);
				info.setResource(resource);
				info.setObjectProvider(ARESResourceObjectProvider.INSTANCE);
				
				infoList.add(info);
			} else if (resource.getType().equals(IOracleResType.Space)) {
				OracleSpaceResourceData data = resource.getInfo(OracleSpaceResourceData.class);

				if (data == null) {
					return infoList;
				}
				
				for (int i = 0; i < data.getSpaces().size(); i++ ) {
					TableSpace ts = data.getSpaces().get(i);
					
					ReferenceInfo info = ReferenceFactory.eINSTANCE.createReferenceInfo();
					info.setRefName(ts.getName());
					info.setRefType(IOracleRefType.Space);
					info.setRefNamespace(StringUtils.EMPTY);
					info.setResource(resource);
					info.setObjectProvider(new EMFReferenceObjectProvider(
							OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA, 
							OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA__SPACES, i));
					
					infoList.add(info);
				}
				
				
				for (int i = 0; i < data.getRelations().size(); i++ ) {
					TableSpaceRelation tsr = data.getRelations().get(i);
					
					ReferenceInfo info = ReferenceFactory.eINSTANCE.createReferenceInfo();
					info.setRefName(tsr.getMainSpace());
					info.setRefType(IOracleRefType.SpaceRelation);
					info.setRefNamespace(StringUtils.EMPTY);
					info.setResource(resource);
					info.setObjectProvider(new EMFReferenceObjectProvider(
							OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA, 
							OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA__RELATIONS, i));
					
					infoList.add(info);
				}
				
			} else if (resource.getType().equals(IOracleResType.User)) {
				OracleUserResourceData data = resource.getInfo(OracleUserResourceData.class);
				
				if (data == null) {
					return infoList;
				}
				
				for (int i = 0; i < data.getUsers().size(); i++ ) {
					OracleUser ou = data.getUsers().get(i);
					
					ReferenceInfo info = ReferenceFactory.eINSTANCE.createReferenceInfo();
					info.setRefName(ou.getName());
					info.setRefType(IOracleRefType.User);
					info.setRefNamespace(StringUtils.EMPTY);
					info.setResource(resource);
					info.setObjectProvider(new EMFReferenceObjectProvider(
							OraclePackage.Literals.ORACLE_USER_RESOURCE_DATA, 
							OraclePackage.Literals.ORACLE_USER_RESOURCE_DATA__USERS, i));
					
					infoList.add(info);
				}
			}
		} catch (ARESModelException e) {
		}
		
		return infoList;
	}

}
