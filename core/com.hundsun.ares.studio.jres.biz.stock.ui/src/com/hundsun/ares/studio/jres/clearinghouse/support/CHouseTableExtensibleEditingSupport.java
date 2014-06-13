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
import com.hundsun.ares.studio.jres.database.oracle.ui.OracleExtensibleModelEditingSupport;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.TextEMPropertyDescriptor;

/**
 * @author yanwj06282
 *
 */
public class CHouseTableExtensibleEditingSupport extends OracleExtensibleModelEditingSupport {

	@Override
	public String getName() {
		return "表信息扩展";
	}

	@Override
	public String getKey() {
		return IClearingHouseConstant.TABLE_BASE_KEY;
	}

	@Override
	public EObject createMapValueObject() {
		return ChouseFactory.eINSTANCE.createTableBaseProperty();
	}

	@Override
	public IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(
			IARESElement aresElement, EClass eClass) {
		List<IExtensibleModelPropertyDescriptor> descriptors = new ArrayList<IExtensibleModelPropertyDescriptor>();
		
		TextEMPropertyDescriptor objIDDesc = new TextEMPropertyDescriptor(ChousePackage.Literals.TABLE_BASE_PROPERTY__SPLIT_FIELD);
		objIDDesc.setCategory(IClearingHouseConstant.CAT_SPLIT_TABLE);
		objIDDesc.setDisplayName("表分区字段");
		descriptors.add(objIDDesc);
		
		TextEMPropertyDescriptor splitNumDesc = new TextEMPropertyDescriptor(ChousePackage.Literals.TABLE_BASE_PROPERTY__SPLIT_NUM);
		splitNumDesc.setCategory(IClearingHouseConstant.CAT_SPLIT_TABLE);
		splitNumDesc.setDisplayName("分区个数");
		descriptors.add(splitNumDesc);
		TextEMPropertyDescriptor startDataDesc = new TextEMPropertyDescriptor(ChousePackage.Literals.TABLE_BASE_PROPERTY__START_DATA);
		startDataDesc.setCategory(IClearingHouseConstant.CAT_SPLIT_TABLE);
		startDataDesc.setDisplayName("分区开始日期");
		descriptors.add(startDataDesc);
		
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]) ;
	}

}
