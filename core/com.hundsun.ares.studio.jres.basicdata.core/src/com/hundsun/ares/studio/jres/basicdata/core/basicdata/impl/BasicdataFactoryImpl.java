/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BasicdataFactoryImpl extends EFactoryImpl implements BasicdataFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BasicdataFactory init() {
		try {
			BasicdataFactory theBasicdataFactory = (BasicdataFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.hundsun.com/ares/studio/jres/basicdata/1.0.0"); 
			if (theBasicdataFactory != null) {
				return theBasicdataFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BasicdataFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicdataFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BasicdataPackage.EPACAKGE_DEFINE_LIST: return createEpacakgeDefineList();
			case BasicdataPackage.SINGLE_TABLE: return createSingleTable();
			case BasicdataPackage.MASTER_SLAVE_TABLE: return createMasterSlaveTable();
			case BasicdataPackage.MASTER_SLAVE_LINK_TABLE: return createMasterSlaveLinkTable();
			case BasicdataPackage.BASIC_DATA_BASE_MODEL: return createBasicDataBaseModel();
			case BasicdataPackage.STANDARD_FIELD_PACKAGE_DEFINE: return createStandardFieldPackageDefine();
			case BasicdataPackage.STANDARD_FIELD_MODEL_AND_DATA: return createStandardFieldModelAndData();
			case BasicdataPackage.STANDARD_FIELD_COLUMN: return createStandardFieldColumn();
			case BasicdataPackage.BASIC_DATA_FIELD: return createBasicDataField();
			case BasicdataPackage.BASICDATA_ITEM: return createBasicdataItem();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EpacakgeDefineList createEpacakgeDefineList() {
		EpacakgeDefineListImpl epacakgeDefineList = new EpacakgeDefineListImpl();
		return epacakgeDefineList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleTable createSingleTable() {
		SingleTableImpl singleTable = new SingleTableImpl();
		return singleTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterSlaveTable createMasterSlaveTable() {
		MasterSlaveTableImpl masterSlaveTable = new MasterSlaveTableImpl();
		return masterSlaveTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterSlaveLinkTable createMasterSlaveLinkTable() {
		MasterSlaveLinkTableImpl masterSlaveLinkTable = new MasterSlaveLinkTableImpl();
		return masterSlaveLinkTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicDataBaseModel createBasicDataBaseModel() {
		BasicDataBaseModelImpl basicDataBaseModel = new BasicDataBaseModelImpl();
		return basicDataBaseModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardFieldPackageDefine createStandardFieldPackageDefine() {
		StandardFieldPackageDefineImpl standardFieldPackageDefine = new StandardFieldPackageDefineImpl();
		return standardFieldPackageDefine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardFieldModelAndData createStandardFieldModelAndData() {
		StandardFieldModelAndDataImpl standardFieldModelAndData = new StandardFieldModelAndDataImpl();
		return standardFieldModelAndData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardFieldColumn createStandardFieldColumn() {
		StandardFieldColumnImpl standardFieldColumn = new StandardFieldColumnImpl();
		return standardFieldColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicDataField createBasicDataField() {
		BasicDataFieldImpl basicDataField = new BasicDataFieldImpl();
		return basicDataField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicdataItem createBasicdataItem() {
		BasicdataItemImpl basicdataItem = new BasicdataItemImpl();
		return basicdataItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicdataPackage getBasicdataPackage() {
		return (BasicdataPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BasicdataPackage getPackage() {
		return BasicdataPackage.eINSTANCE;
	}

} //BasicdataFactoryImpl
