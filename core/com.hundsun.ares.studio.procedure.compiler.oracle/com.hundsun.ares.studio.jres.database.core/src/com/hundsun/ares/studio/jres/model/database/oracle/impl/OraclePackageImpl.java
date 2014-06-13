/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.impl;

import com.hundsun.ares.studio.core.model.CorePackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleFactory;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleModuleProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSequenceProperty;
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
import com.hundsun.ares.studio.jres.model.database.oracle.util.OracleValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OraclePackageImpl extends EPackageImpl implements OraclePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oracleTablePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oracleIndexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oracleViewPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oracleModulePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oracleSpaceResourceDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableSpaceRelationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableSpaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oracleUserResourceDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oracleUserEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oraclePrivilegeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass triggerResourceDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequenceResourceDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass databaseModuleExtensiblePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oracleSequencePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum table_typeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OraclePackageImpl() {
		super(eNS_URI, OracleFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link OraclePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OraclePackage init() {
		if (isInited) return (OraclePackage)EPackage.Registry.INSTANCE.getEPackage(OraclePackage.eNS_URI);

		// Obtain or create and register package
		OraclePackageImpl theOraclePackage = (OraclePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OraclePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OraclePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		DatabasePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theOraclePackage.createPackageContents();

		// Initialize created meta-data
		theOraclePackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theOraclePackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return OracleValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theOraclePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OraclePackage.eNS_URI, theOraclePackage);
		return theOraclePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOracleTableProperty() {
		return oracleTablePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleTableProperty_Space() {
		return (EAttribute)oracleTablePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleTableProperty_Tabletype() {
		return (EAttribute)oracleTablePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOracleIndexProperty() {
		return oracleIndexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleIndexProperty_Reverse() {
		return (EAttribute)oracleIndexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOracleViewProperty() {
		return oracleViewPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleViewProperty_Space() {
		return (EAttribute)oracleViewPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOracleModuleProperty() {
		return oracleModulePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleModuleProperty_Space() {
		return (EAttribute)oracleModulePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOracleSpaceResourceData() {
		return oracleSpaceResourceDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOracleSpaceResourceData_Spaces() {
		return (EReference)oracleSpaceResourceDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOracleSpaceResourceData_Relations() {
		return (EReference)oracleSpaceResourceDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableSpaceRelation() {
		return tableSpaceRelationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceRelation_MainSpace() {
		return (EAttribute)tableSpaceRelationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceRelation_IndexSpace() {
		return (EAttribute)tableSpaceRelationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableSpace() {
		return tableSpaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpace_Name() {
		return (EAttribute)tableSpaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpace_ChineseName() {
		return (EAttribute)tableSpaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpace_User() {
		return (EAttribute)tableSpaceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpace_File() {
		return (EAttribute)tableSpaceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpace_Size() {
		return (EAttribute)tableSpaceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpace_Description() {
		return (EAttribute)tableSpaceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpace_LogicName() {
		return (EAttribute)tableSpaceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOracleUserResourceData() {
		return oracleUserResourceDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOracleUserResourceData_Users() {
		return (EReference)oracleUserResourceDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOracleUserResourceData_Privileges() {
		return (EReference)oracleUserResourceDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOracleUser() {
		return oracleUserEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleUser_Name() {
		return (EAttribute)oracleUserEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleUser_Decription() {
		return (EAttribute)oracleUserEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleUser_Attributes() {
		return (EAttribute)oracleUserEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleUser_Enable() {
		return (EAttribute)oracleUserEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOracleUser_Privileges() {
		return (EReference)oracleUserEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleUser_Password() {
		return (EAttribute)oracleUserEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleUser_DefaultTableSpace() {
		return (EAttribute)oracleUserEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOraclePrivilege() {
		return oraclePrivilegeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOraclePrivilege_Name() {
		return (EAttribute)oraclePrivilegeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOraclePrivilege_Type() {
		return (EAttribute)oraclePrivilegeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOraclePrivilege_Decription() {
		return (EAttribute)oraclePrivilegeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTriggerResourceData() {
		return triggerResourceDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerResourceData_Sql() {
		return (EAttribute)triggerResourceDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSequenceResourceData() {
		return sequenceResourceDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceResourceData_TableName() {
		return (EAttribute)sequenceResourceDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceResourceData_Start() {
		return (EAttribute)sequenceResourceDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceResourceData_Increment() {
		return (EAttribute)sequenceResourceDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceResourceData_MinValue() {
		return (EAttribute)sequenceResourceDataEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceResourceData_MaxValue() {
		return (EAttribute)sequenceResourceDataEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceResourceData_Cycle() {
		return (EAttribute)sequenceResourceDataEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceResourceData_Cache() {
		return (EAttribute)sequenceResourceDataEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceResourceData_UseCache() {
		return (EAttribute)sequenceResourceDataEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceResourceData_IsHistory() {
		return (EAttribute)sequenceResourceDataEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDatabaseModuleExtensibleProperty() {
		return databaseModuleExtensiblePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseModuleExtensibleProperty_TableType() {
		return (EAttribute)databaseModuleExtensiblePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseModuleExtensibleProperty_Space() {
		return (EAttribute)databaseModuleExtensiblePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseModuleExtensibleProperty_SplitField() {
		return (EAttribute)databaseModuleExtensiblePropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseModuleExtensibleProperty_SplitNum() {
		return (EAttribute)databaseModuleExtensiblePropertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseModuleExtensibleProperty_StartDate() {
		return (EAttribute)databaseModuleExtensiblePropertyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseModuleExtensibleProperty_BizPkg() {
		return (EAttribute)databaseModuleExtensiblePropertyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOracleSequenceProperty() {
		return oracleSequencePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOracleSequenceProperty_Space() {
		return (EAttribute)oracleSequencePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum gettable_type() {
		return table_typeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleFactory getOracleFactory() {
		return (OracleFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		oracleTablePropertyEClass = createEClass(ORACLE_TABLE_PROPERTY);
		createEAttribute(oracleTablePropertyEClass, ORACLE_TABLE_PROPERTY__SPACE);
		createEAttribute(oracleTablePropertyEClass, ORACLE_TABLE_PROPERTY__TABLETYPE);

		oracleIndexPropertyEClass = createEClass(ORACLE_INDEX_PROPERTY);
		createEAttribute(oracleIndexPropertyEClass, ORACLE_INDEX_PROPERTY__REVERSE);

		oracleViewPropertyEClass = createEClass(ORACLE_VIEW_PROPERTY);
		createEAttribute(oracleViewPropertyEClass, ORACLE_VIEW_PROPERTY__SPACE);

		oracleModulePropertyEClass = createEClass(ORACLE_MODULE_PROPERTY);
		createEAttribute(oracleModulePropertyEClass, ORACLE_MODULE_PROPERTY__SPACE);

		oracleSpaceResourceDataEClass = createEClass(ORACLE_SPACE_RESOURCE_DATA);
		createEReference(oracleSpaceResourceDataEClass, ORACLE_SPACE_RESOURCE_DATA__SPACES);
		createEReference(oracleSpaceResourceDataEClass, ORACLE_SPACE_RESOURCE_DATA__RELATIONS);

		tableSpaceRelationEClass = createEClass(TABLE_SPACE_RELATION);
		createEAttribute(tableSpaceRelationEClass, TABLE_SPACE_RELATION__MAIN_SPACE);
		createEAttribute(tableSpaceRelationEClass, TABLE_SPACE_RELATION__INDEX_SPACE);

		tableSpaceEClass = createEClass(TABLE_SPACE);
		createEAttribute(tableSpaceEClass, TABLE_SPACE__NAME);
		createEAttribute(tableSpaceEClass, TABLE_SPACE__CHINESE_NAME);
		createEAttribute(tableSpaceEClass, TABLE_SPACE__USER);
		createEAttribute(tableSpaceEClass, TABLE_SPACE__FILE);
		createEAttribute(tableSpaceEClass, TABLE_SPACE__SIZE);
		createEAttribute(tableSpaceEClass, TABLE_SPACE__DESCRIPTION);
		createEAttribute(tableSpaceEClass, TABLE_SPACE__LOGIC_NAME);

		oracleUserResourceDataEClass = createEClass(ORACLE_USER_RESOURCE_DATA);
		createEReference(oracleUserResourceDataEClass, ORACLE_USER_RESOURCE_DATA__USERS);
		createEReference(oracleUserResourceDataEClass, ORACLE_USER_RESOURCE_DATA__PRIVILEGES);

		oracleUserEClass = createEClass(ORACLE_USER);
		createEAttribute(oracleUserEClass, ORACLE_USER__NAME);
		createEAttribute(oracleUserEClass, ORACLE_USER__DECRIPTION);
		createEAttribute(oracleUserEClass, ORACLE_USER__ATTRIBUTES);
		createEAttribute(oracleUserEClass, ORACLE_USER__ENABLE);
		createEReference(oracleUserEClass, ORACLE_USER__PRIVILEGES);
		createEAttribute(oracleUserEClass, ORACLE_USER__PASSWORD);
		createEAttribute(oracleUserEClass, ORACLE_USER__DEFAULT_TABLE_SPACE);

		oraclePrivilegeEClass = createEClass(ORACLE_PRIVILEGE);
		createEAttribute(oraclePrivilegeEClass, ORACLE_PRIVILEGE__NAME);
		createEAttribute(oraclePrivilegeEClass, ORACLE_PRIVILEGE__TYPE);
		createEAttribute(oraclePrivilegeEClass, ORACLE_PRIVILEGE__DECRIPTION);

		triggerResourceDataEClass = createEClass(TRIGGER_RESOURCE_DATA);
		createEAttribute(triggerResourceDataEClass, TRIGGER_RESOURCE_DATA__SQL);

		sequenceResourceDataEClass = createEClass(SEQUENCE_RESOURCE_DATA);
		createEAttribute(sequenceResourceDataEClass, SEQUENCE_RESOURCE_DATA__TABLE_NAME);
		createEAttribute(sequenceResourceDataEClass, SEQUENCE_RESOURCE_DATA__START);
		createEAttribute(sequenceResourceDataEClass, SEQUENCE_RESOURCE_DATA__INCREMENT);
		createEAttribute(sequenceResourceDataEClass, SEQUENCE_RESOURCE_DATA__MIN_VALUE);
		createEAttribute(sequenceResourceDataEClass, SEQUENCE_RESOURCE_DATA__MAX_VALUE);
		createEAttribute(sequenceResourceDataEClass, SEQUENCE_RESOURCE_DATA__CYCLE);
		createEAttribute(sequenceResourceDataEClass, SEQUENCE_RESOURCE_DATA__CACHE);
		createEAttribute(sequenceResourceDataEClass, SEQUENCE_RESOURCE_DATA__USE_CACHE);
		createEAttribute(sequenceResourceDataEClass, SEQUENCE_RESOURCE_DATA__IS_HISTORY);

		databaseModuleExtensiblePropertyEClass = createEClass(DATABASE_MODULE_EXTENSIBLE_PROPERTY);
		createEAttribute(databaseModuleExtensiblePropertyEClass, DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE);
		createEAttribute(databaseModuleExtensiblePropertyEClass, DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE);
		createEAttribute(databaseModuleExtensiblePropertyEClass, DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD);
		createEAttribute(databaseModuleExtensiblePropertyEClass, DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM);
		createEAttribute(databaseModuleExtensiblePropertyEClass, DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE);
		createEAttribute(databaseModuleExtensiblePropertyEClass, DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG);

		oracleSequencePropertyEClass = createEClass(ORACLE_SEQUENCE_PROPERTY);
		createEAttribute(oracleSequencePropertyEClass, ORACLE_SEQUENCE_PROPERTY__SPACE);

		// Create enums
		table_typeEEnum = createEEnum(TABLE_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		DatabasePackage theDatabasePackage = (DatabasePackage)EPackage.Registry.INSTANCE.getEPackage(DatabasePackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		oracleSpaceResourceDataEClass.getESuperTypes().add(theDatabasePackage.getDatabaseResourceData());
		tableSpaceRelationEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		tableSpaceEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		oracleUserResourceDataEClass.getESuperTypes().add(theDatabasePackage.getDatabaseResourceData());
		oracleUserEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		oraclePrivilegeEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		triggerResourceDataEClass.getESuperTypes().add(theDatabasePackage.getDatabaseResourceData());
		sequenceResourceDataEClass.getESuperTypes().add(theDatabasePackage.getDatabaseResourceData());
		databaseModuleExtensiblePropertyEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());

		// Initialize classes and features; add operations and parameters
		initEClass(oracleTablePropertyEClass, OracleTableProperty.class, "OracleTableProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOracleTableProperty_Space(), ecorePackage.getEString(), "space", "", 0, 1, OracleTableProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOracleTableProperty_Tabletype(), this.gettable_type(), "tabletype", "COMMON", 0, 1, OracleTableProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oracleIndexPropertyEClass, OracleIndexProperty.class, "OracleIndexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOracleIndexProperty_Reverse(), ecorePackage.getEBoolean(), "reverse", null, 0, 1, OracleIndexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oracleViewPropertyEClass, OracleViewProperty.class, "OracleViewProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOracleViewProperty_Space(), ecorePackage.getEString(), "space", "", 0, 1, OracleViewProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oracleModulePropertyEClass, OracleModuleProperty.class, "OracleModuleProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOracleModuleProperty_Space(), ecorePackage.getEString(), "space", "", 0, 1, OracleModuleProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oracleSpaceResourceDataEClass, OracleSpaceResourceData.class, "OracleSpaceResourceData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOracleSpaceResourceData_Spaces(), this.getTableSpace(), null, "spaces", null, 0, -1, OracleSpaceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOracleSpaceResourceData_Relations(), this.getTableSpaceRelation(), null, "relations", null, 0, -1, OracleSpaceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableSpaceRelationEClass, TableSpaceRelation.class, "TableSpaceRelation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableSpaceRelation_MainSpace(), ecorePackage.getEString(), "mainSpace", "", 0, 1, TableSpaceRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpaceRelation_IndexSpace(), ecorePackage.getEString(), "indexSpace", "", 0, 1, TableSpaceRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableSpaceEClass, TableSpace.class, "TableSpace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableSpace_Name(), ecorePackage.getEString(), "name", "", 0, 1, TableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpace_ChineseName(), ecorePackage.getEString(), "chineseName", "", 0, 1, TableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpace_User(), ecorePackage.getEString(), "user", "", 0, 1, TableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpace_File(), ecorePackage.getEString(), "file", "", 0, 1, TableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpace_Size(), ecorePackage.getEString(), "size", "", 0, 1, TableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpace_Description(), ecorePackage.getEString(), "description", "", 0, 1, TableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpace_LogicName(), ecorePackage.getEString(), "logicName", null, 0, 1, TableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oracleUserResourceDataEClass, OracleUserResourceData.class, "OracleUserResourceData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOracleUserResourceData_Users(), this.getOracleUser(), null, "users", null, 0, -1, OracleUserResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOracleUserResourceData_Privileges(), this.getOraclePrivilege(), null, "privileges", null, 0, -1, OracleUserResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oracleUserEClass, OracleUser.class, "OracleUser", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOracleUser_Name(), ecorePackage.getEString(), "name", "", 0, 1, OracleUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOracleUser_Decription(), ecorePackage.getEString(), "decription", "", 0, 1, OracleUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOracleUser_Attributes(), ecorePackage.getEString(), "attributes", "", 0, 1, OracleUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOracleUser_Enable(), ecorePackage.getEBoolean(), "enable", "true", 0, 1, OracleUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOracleUser_Privileges(), this.getOraclePrivilege(), null, "privileges", null, 0, -1, OracleUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOracleUser_Password(), ecorePackage.getEString(), "password", "", 0, 1, OracleUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOracleUser_DefaultTableSpace(), ecorePackage.getEString(), "defaultTableSpace", "", 0, 1, OracleUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oraclePrivilegeEClass, OraclePrivilege.class, "OraclePrivilege", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOraclePrivilege_Name(), ecorePackage.getEString(), "name", "", 0, 1, OraclePrivilege.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOraclePrivilege_Type(), ecorePackage.getEString(), "type", "", 0, 1, OraclePrivilege.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOraclePrivilege_Decription(), ecorePackage.getEString(), "decription", "", 0, 1, OraclePrivilege.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(triggerResourceDataEClass, TriggerResourceData.class, "TriggerResourceData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTriggerResourceData_Sql(), ecorePackage.getEString(), "sql", "", 0, 1, TriggerResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sequenceResourceDataEClass, SequenceResourceData.class, "SequenceResourceData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSequenceResourceData_TableName(), ecorePackage.getEString(), "tableName", "", 0, 1, SequenceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequenceResourceData_Start(), ecorePackage.getEString(), "start", "1", 0, 1, SequenceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequenceResourceData_Increment(), ecorePackage.getEString(), "increment", "1", 0, 1, SequenceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequenceResourceData_MinValue(), ecorePackage.getEString(), "minValue", "1", 0, 1, SequenceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequenceResourceData_MaxValue(), ecorePackage.getEString(), "maxValue", "999999999999999999999999999", 0, 1, SequenceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequenceResourceData_Cycle(), ecorePackage.getEBoolean(), "cycle", "true", 0, 1, SequenceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequenceResourceData_Cache(), ecorePackage.getEString(), "cache", "20", 0, 1, SequenceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequenceResourceData_UseCache(), ecorePackage.getEBoolean(), "useCache", "true", 0, 1, SequenceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequenceResourceData_IsHistory(), ecorePackage.getEBoolean(), "isHistory", null, 0, 1, SequenceResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(databaseModuleExtensiblePropertyEClass, DatabaseModuleExtensibleProperty.class, "DatabaseModuleExtensibleProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDatabaseModuleExtensibleProperty_TableType(), this.gettable_type(), "tableType", null, 0, 1, DatabaseModuleExtensibleProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDatabaseModuleExtensibleProperty_Space(), ecorePackage.getEString(), "space", null, 0, 1, DatabaseModuleExtensibleProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDatabaseModuleExtensibleProperty_SplitField(), ecorePackage.getEString(), "splitField", null, 0, 1, DatabaseModuleExtensibleProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDatabaseModuleExtensibleProperty_SplitNum(), ecorePackage.getEString(), "splitNum", null, 0, 1, DatabaseModuleExtensibleProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDatabaseModuleExtensibleProperty_StartDate(), ecorePackage.getEString(), "startDate", null, 0, 1, DatabaseModuleExtensibleProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDatabaseModuleExtensibleProperty_BizPkg(), ecorePackage.getEString(), "bizPkg", null, 0, 1, DatabaseModuleExtensibleProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oracleSequencePropertyEClass, OracleSequenceProperty.class, "OracleSequenceProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOracleSequenceProperty_Space(), ecorePackage.getEString(), "space", "", 0, 1, OracleSequenceProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(table_typeEEnum, table_type.class, "table_type");
		addEEnumLiteral(table_typeEEnum, table_type.COMMON);
		addEEnumLiteral(table_typeEEnum, table_type.TEMP_NO_VALUE);
		addEEnumLiteral(table_typeEEnum, table_type.TEMP_WITH_VALUE);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";		
		addAnnotation
		  (oracleTablePropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "space indexSpace"
		   });			
		addAnnotation
		  (oracleIndexPropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "reverse"
		   });		
		addAnnotation
		  (oracleViewPropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "space"
		   });		
		addAnnotation
		  (oracleModulePropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "space"
		   });		
		addAnnotation
		  (tableSpaceRelationEClass, 
		   source, 
		   new String[] {
			 "constraints", "mainSpace indexSpace"
		   });		
		addAnnotation
		  (tableSpaceEClass, 
		   source, 
		   new String[] {
			 "constraints", "name user file size logicName"
		   });		
		addAnnotation
		  (oracleUserEClass, 
		   source, 
		   new String[] {
			 "constraints", "name attributes password"
		   });		
		addAnnotation
		  (oraclePrivilegeEClass, 
		   source, 
		   new String[] {
			 "constraints", "name type"
		   });		
		addAnnotation
		  (triggerResourceDataEClass, 
		   source, 
		   new String[] {
			 "constraints", "sql name"
		   });		
		addAnnotation
		  (sequenceResourceDataEClass, 
		   source, 
		   new String[] {
			 "constraints", "name objectId start increment minValue maxValue cycle cache"
		   });		
		addAnnotation
		  (oracleSequencePropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "space"
		   });
	}

} //OraclePackageImpl
