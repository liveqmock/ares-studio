/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.impl;

import com.hundsun.ares.studio.jres.model.database.oracle.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.hundsun.ares.studio.jres.model.database.oracle.OracleFactory;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleModuleProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleViewProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;
import com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.table_type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OracleFactoryImpl extends EFactoryImpl implements OracleFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OracleFactory init() {
		try {
			OracleFactory theOracleFactory = (OracleFactory)EPackage.Registry.INSTANCE.getEFactory(OraclePackage.eNS_URI);
			if (theOracleFactory != null) {
				return theOracleFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OracleFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleFactoryImpl() {
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
			case OraclePackage.ORACLE_TABLE_PROPERTY: return createOracleTableProperty();
			case OraclePackage.ORACLE_INDEX_PROPERTY: return createOracleIndexProperty();
			case OraclePackage.ORACLE_VIEW_PROPERTY: return createOracleViewProperty();
			case OraclePackage.ORACLE_MODULE_PROPERTY: return createOracleModuleProperty();
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA: return createOracleSpaceResourceData();
			case OraclePackage.TABLE_SPACE_RELATION: return createTableSpaceRelation();
			case OraclePackage.TABLE_SPACE: return createTableSpace();
			case OraclePackage.ORACLE_USER_RESOURCE_DATA: return createOracleUserResourceData();
			case OraclePackage.ORACLE_USER: return createOracleUser();
			case OraclePackage.ORACLE_PRIVILEGE: return createOraclePrivilege();
			case OraclePackage.TRIGGER_RESOURCE_DATA: return createTriggerResourceData();
			case OraclePackage.SEQUENCE_RESOURCE_DATA: return createSequenceResourceData();
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY: return createDatabaseModuleExtensibleProperty();
			case OraclePackage.ORACLE_SEQUENCE_PROPERTY: return createOracleSequenceProperty();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case OraclePackage.TABLE_TYPE:
				return createtable_typeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case OraclePackage.TABLE_TYPE:
				return converttable_typeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleTableProperty createOracleTableProperty() {
		OracleTablePropertyImpl oracleTableProperty = new OracleTablePropertyImpl();
		return oracleTableProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleIndexProperty createOracleIndexProperty() {
		OracleIndexPropertyImpl oracleIndexProperty = new OracleIndexPropertyImpl();
		return oracleIndexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleViewProperty createOracleViewProperty() {
		OracleViewPropertyImpl oracleViewProperty = new OracleViewPropertyImpl();
		return oracleViewProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleModuleProperty createOracleModuleProperty() {
		OracleModulePropertyImpl oracleModuleProperty = new OracleModulePropertyImpl();
		return oracleModuleProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleSpaceResourceData createOracleSpaceResourceData() {
		OracleSpaceResourceDataImpl oracleSpaceResourceData = new OracleSpaceResourceDataImpl();
		return oracleSpaceResourceData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableSpaceRelation createTableSpaceRelation() {
		TableSpaceRelationImpl tableSpaceRelation = new TableSpaceRelationImpl();
		return tableSpaceRelation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableSpace createTableSpace() {
		TableSpaceImpl tableSpace = new TableSpaceImpl();
		return tableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleUserResourceData createOracleUserResourceData() {
		OracleUserResourceDataImpl oracleUserResourceData = new OracleUserResourceDataImpl();
		return oracleUserResourceData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleUser createOracleUser() {
		OracleUserImpl oracleUser = new OracleUserImpl();
		return oracleUser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OraclePrivilege createOraclePrivilege() {
		OraclePrivilegeImpl oraclePrivilege = new OraclePrivilegeImpl();
		return oraclePrivilege;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggerResourceData createTriggerResourceData() {
		TriggerResourceDataImpl triggerResourceData = new TriggerResourceDataImpl();
		return triggerResourceData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceResourceData createSequenceResourceData() {
		SequenceResourceDataImpl sequenceResourceData = new SequenceResourceDataImpl();
		return sequenceResourceData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseModuleExtensibleProperty createDatabaseModuleExtensibleProperty() {
		DatabaseModuleExtensiblePropertyImpl databaseModuleExtensibleProperty = new DatabaseModuleExtensiblePropertyImpl();
		return databaseModuleExtensibleProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleSequenceProperty createOracleSequenceProperty() {
		OracleSequencePropertyImpl oracleSequenceProperty = new OracleSequencePropertyImpl();
		return oracleSequenceProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public table_type createtable_typeFromString(EDataType eDataType, String initialValue) {
		table_type result = table_type.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String converttable_typeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OraclePackage getOraclePackage() {
		return (OraclePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OraclePackage getPackage() {
		return OraclePackage.eINSTANCE;
	}

} //OracleFactoryImpl
