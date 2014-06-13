/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.support;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.jres.clearinghouse.constant.IClearingHouseConstant;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.database.oracle.internal.ui.providers.OracleEMPropertyDescriptor;
import com.hundsun.ares.studio.jres.database.oracle.ui.OracleExtensibleModelEditingSupport;
import com.hundsun.ares.studio.jres.database.oracle.ui.viewer.OracleSpaceColumnProposalProvider;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.TextEMPropertyDescriptor;

/**
 * @author yanwj06282
 *
 */
public class TableSpaceRelationEditingSupport extends OracleExtensibleModelEditingSupport {

	@Override
	public String getName() {
		return "清算所";
	}

	@Override
	public String getKey() {
		return IClearingHouseConstant.TABLE_SPACE_RELATION_KEY;
	}

	@Override
	public EObject createMapValueObject() {
		return ChouseFactory.eINSTANCE.createTableSpaceRelationProperty();
	}

	@Override
	public IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(
			IARESElement aresElement, EClass eClass) {
		List<IExtensibleModelPropertyDescriptor> descriptors = new ArrayList<IExtensibleModelPropertyDescriptor>();
		
		OracleSpaceColumnProposalProvider proposalProvider = new OracleSpaceColumnProposalProvider(IOracleRefType.Space);
		TextEMPropertyDescriptor hisSpace = new OracleEMPropertyDescriptor(proposalProvider,ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE);
		hisSpace.setDisplayName("历史表空间");
		descriptors.add(hisSpace);
		
		TextEMPropertyDescriptor hisIndexSpace = new OracleEMPropertyDescriptor(proposalProvider,ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE);
		hisIndexSpace.setDisplayName("历史索引表空间");
		descriptors.add(hisIndexSpace);
		
		TextEMPropertyDescriptor fileSpace = new OracleEMPropertyDescriptor(proposalProvider,ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE);
		fileSpace.setDisplayName("归档表空间");
		descriptors.add(fileSpace);
		
		TextEMPropertyDescriptor fileIndexSpace = new OracleEMPropertyDescriptor(proposalProvider,ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE);
		fileIndexSpace.setDisplayName("归档索引表空间");
		descriptors.add(fileIndexSpace);
		
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]) ;
	}

}
