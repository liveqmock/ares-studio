/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.support;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.clearinghouse.constant.IClearingHouseConstant;
import com.hundsun.ares.studio.jres.clearinghouse.provider.ExtendsLabelProvider;
import com.hundsun.ares.studio.jres.database.oracle.ui.OracleExtensibleModelEditingSupport;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.ui.editor.extend.DerivedEMPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;

/**
 * @author yanwj06282
 *
 */
public class CHouseOracleExtensibleEditingSupport extends OracleExtensibleModelEditingSupport {

	@Override
	public String getName() {
		return "数据库(表空间)";
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
		
		ExtendsLabelProvider extendProvider = new ExtendsLabelProvider(ARESElementUtil.getARESBundle(aresElement),IClearingHouseConstant.TABLE_SPACE_RELATION_KEY,ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE);
		if (aresElement instanceof IARESResource) {
			try {
				extendProvider.setExtensibleModel(((IARESResource) aresElement).getInfo(ExtensibleModel.class));
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		DerivedEMPropertyDescriptor hisSpace = 
			new DerivedEMPropertyDescriptor(ChousePackage.Literals.TABLE_BASE_PROPERTY__HISTORY_SPACE, extendProvider);
//		hisSpace.setCategory(getName());
		hisSpace.setDisplayName("历史表空间");
		descriptors.add(hisSpace);
		
		
		extendProvider = new ExtendsLabelProvider(ARESElementUtil.getARESBundle(aresElement),IClearingHouseConstant.TABLE_SPACE_RELATION_KEY,ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE);
		if (aresElement instanceof IARESResource) {
			try {
				extendProvider.setExtensibleModel(((IARESResource) aresElement).getInfo(ExtensibleModel.class));
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		DerivedEMPropertyDescriptor hisIndexSpace = 
			new DerivedEMPropertyDescriptor(ChousePackage.Literals.TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE, extendProvider);
//		hisIndexSpace.setCategory(getName());
		hisIndexSpace.setDisplayName("历史索引表空间");
		descriptors.add(hisIndexSpace);
		
		extendProvider = new ExtendsLabelProvider(ARESElementUtil.getARESBundle(aresElement),IClearingHouseConstant.TABLE_SPACE_RELATION_KEY,ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE);
		if (aresElement instanceof IARESResource) {
			try {
				extendProvider.setExtensibleModel(((IARESResource) aresElement).getInfo(ExtensibleModel.class));
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		DerivedEMPropertyDescriptor fileSpace = 
			new DerivedEMPropertyDescriptor(ChousePackage.Literals.TABLE_BASE_PROPERTY__FILE_SPACE, extendProvider);
//		fileSpace.setCategory(getName());
		fileSpace.setDisplayName("归档表空间");
		descriptors.add(fileSpace);
		
		extendProvider = new ExtendsLabelProvider(ARESElementUtil.getARESBundle(aresElement),IClearingHouseConstant.TABLE_SPACE_RELATION_KEY,ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE);
		if (aresElement instanceof IARESResource) {
			try {
				extendProvider.setExtensibleModel(((IARESResource) aresElement).getInfo(ExtensibleModel.class));
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		DerivedEMPropertyDescriptor fileIndexSpace = 
			new DerivedEMPropertyDescriptor(ChousePackage.Literals.TABLE_BASE_PROPERTY__FILE_INDEX_SPACE, extendProvider);
//		fileIndexSpace.setCategory(getName());
		fileIndexSpace.setDisplayName("归档索引表空间");
		descriptors.add(fileIndexSpace);
		
		extendProvider = new ExtendsLabelProvider(ARESElementUtil.getARESBundle(aresElement),IClearingHouseConstant.TABLE_SPACE_KEY,ChousePackage.Literals.TABLE_SPACE_PROPERTY__REDU_TABLE);
		if (aresElement instanceof IARESResource) {
			try {
				extendProvider.setExtensibleModel(((IARESResource) aresElement).getInfo(ExtensibleModel.class));
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		DerivedEMPropertyDescriptor redu = 
			new DerivedEMPropertyDescriptor(ChousePackage.Literals.TABLE_BASE_PROPERTY__REDU, extendProvider);
//		redu.setCategory(getName());
		redu.setDisplayName("冗余表空间");
		descriptors.add(redu);
		
		extendProvider = new ExtendsLabelProvider(ARESElementUtil.getARESBundle(aresElement),IClearingHouseConstant.TABLE_SPACE_KEY,ChousePackage.Literals.TABLE_SPACE_PROPERTY__CHEAR_TABLE);
		if (aresElement instanceof IARESResource) {
			try {
				extendProvider.setExtensibleModel(((IARESResource) aresElement).getInfo(ExtensibleModel.class));
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		DerivedEMPropertyDescriptor clear = 
			new DerivedEMPropertyDescriptor(ChousePackage.Literals.TABLE_BASE_PROPERTY__CHEAR, extendProvider);
//		clear.setCategory(getName());
		clear.setDisplayName("清算表空间");
		descriptors.add(clear);
		
		extendProvider = new ExtendsLabelProvider(ARESElementUtil.getARESBundle(aresElement),IClearingHouseConstant.TABLE_SPACE_KEY,ChousePackage.Literals.TABLE_SPACE_PROPERTY__CHEAR_TABLE_INDEX);
		if (aresElement instanceof IARESResource) {
			try {
				extendProvider.setExtensibleModel(((IARESResource) aresElement).getInfo(ExtensibleModel.class));
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		DerivedEMPropertyDescriptor clearIndex = 
			new DerivedEMPropertyDescriptor(ChousePackage.Literals.TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE, extendProvider);
//		clearIndex.setCategory(getName());
		clearIndex.setDisplayName("清算索引表空间");
		descriptors.add(clearIndex);
		
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]) ;
	}

}
