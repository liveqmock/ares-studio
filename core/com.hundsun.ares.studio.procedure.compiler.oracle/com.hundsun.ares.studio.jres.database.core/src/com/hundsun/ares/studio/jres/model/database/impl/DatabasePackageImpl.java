/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.impl;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.hundsun.ares.studio.jres.model.database.DBGenContext;
import com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.jres.model.database.util.DatabaseValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DatabasePackageImpl extends EPackageImpl implements DatabasePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dbModuleCommonPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass databaseResourceDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableResourceDataEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableColumnEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableIndexColumnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableIndexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass viewResourceDataEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dbGenContextEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass foreignKeyEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableKeyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum key_typeEEnum = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum columnTypeEEnum = null;
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
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DatabasePackageImpl() {
		super(eNS_URI, DatabaseFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DatabasePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DatabasePackage init() {
		if (isInited) return (DatabasePackage)EPackage.Registry.INSTANCE.getEPackage(DatabasePackage.eNS_URI);

		// Obtain or create and register package
		DatabasePackageImpl theDatabasePackage = (DatabasePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DatabasePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DatabasePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theDatabasePackage.createPackageContents();

		// Initialize created meta-data
		theDatabasePackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theDatabasePackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return DatabaseValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theDatabasePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DatabasePackage.eNS_URI, theDatabasePackage);
		return theDatabasePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDBModuleCommonProperty() {
		return dbModuleCommonPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDBModuleCommonProperty_Database() {
		return (EAttribute)dbModuleCommonPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDBModuleCommonProperty_SupportDatabases() {
		return (EAttribute)dbModuleCommonPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDatabaseResourceData() {
		return databaseResourceDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableResourceData() {
		return tableResourceDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTableResourceData_Columns() {
		return (EReference)tableResourceDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTableResourceData_Indexes() {
		return (EReference)tableResourceDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTableResourceData_Keys() {
		return (EReference)tableResourceDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableColumn() {
		return tableColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_Name() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_ChineseName() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_Description() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_DataType() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_ColumnName() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_FieldName() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_PrimaryKey() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_Unique() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_Nullable() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_DefaultValue() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTableColumn_Foreignkey() {
		return (EReference)tableColumnEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_Mark() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_Comments() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableColumn_ColumnType() {
		return (EAttribute)tableColumnEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableIndexColumn() {
		return tableIndexColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableIndexColumn_ColumnName() {
		return (EAttribute)tableIndexColumnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableIndexColumn_Ascending() {
		return (EAttribute)tableIndexColumnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableIndexColumn_ColumnType() {
		return (EAttribute)tableIndexColumnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableIndex() {
		return tableIndexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableIndex_Name() {
		return (EAttribute)tableIndexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableIndex_Unique() {
		return (EAttribute)tableIndexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableIndex_Cluster() {
		return (EAttribute)tableIndexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTableIndex_Columns() {
		return (EReference)tableIndexEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableIndex_Mark() {
		return (EAttribute)tableIndexEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getViewResourceData() {
		return viewResourceDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getViewResourceData_Sql() {
		return (EAttribute)viewResourceDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getViewResourceData_IsHistory() {
		return (EAttribute)viewResourceDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDBGenContext() {
		return dbGenContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getForeignKey() {
		return foreignKeyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForeignKey_TableName() {
		return (EAttribute)foreignKeyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForeignKey_FieldName() {
		return (EAttribute)foreignKeyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableKey() {
		return tableKeyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableKey_Name() {
		return (EAttribute)tableKeyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableKey_Type() {
		return (EAttribute)tableKeyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTableKey_Columns() {
		return (EReference)tableKeyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableKey_Mark() {
		return (EAttribute)tableKeyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTableKey_ForeignKey() {
		return (EReference)tableKeyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getkey_type() {
		return key_typeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getColumnType() {
		return columnTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseFactory getDatabaseFactory() {
		return (DatabaseFactory)getEFactoryInstance();
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
		dbModuleCommonPropertyEClass = createEClass(DB_MODULE_COMMON_PROPERTY);
		createEAttribute(dbModuleCommonPropertyEClass, DB_MODULE_COMMON_PROPERTY__DATABASE);
		createEAttribute(dbModuleCommonPropertyEClass, DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES);

		databaseResourceDataEClass = createEClass(DATABASE_RESOURCE_DATA);

		tableResourceDataEClass = createEClass(TABLE_RESOURCE_DATA);
		createEReference(tableResourceDataEClass, TABLE_RESOURCE_DATA__COLUMNS);
		createEReference(tableResourceDataEClass, TABLE_RESOURCE_DATA__INDEXES);
		createEReference(tableResourceDataEClass, TABLE_RESOURCE_DATA__KEYS);

		tableColumnEClass = createEClass(TABLE_COLUMN);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__NAME);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__CHINESE_NAME);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__DESCRIPTION);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__DATA_TYPE);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__COLUMN_NAME);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__FIELD_NAME);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__PRIMARY_KEY);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__UNIQUE);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__NULLABLE);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__DEFAULT_VALUE);
		createEReference(tableColumnEClass, TABLE_COLUMN__FOREIGNKEY);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__MARK);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__COMMENTS);
		createEAttribute(tableColumnEClass, TABLE_COLUMN__COLUMN_TYPE);

		tableIndexColumnEClass = createEClass(TABLE_INDEX_COLUMN);
		createEAttribute(tableIndexColumnEClass, TABLE_INDEX_COLUMN__COLUMN_NAME);
		createEAttribute(tableIndexColumnEClass, TABLE_INDEX_COLUMN__ASCENDING);
		createEAttribute(tableIndexColumnEClass, TABLE_INDEX_COLUMN__COLUMN_TYPE);

		tableIndexEClass = createEClass(TABLE_INDEX);
		createEAttribute(tableIndexEClass, TABLE_INDEX__NAME);
		createEAttribute(tableIndexEClass, TABLE_INDEX__UNIQUE);
		createEAttribute(tableIndexEClass, TABLE_INDEX__CLUSTER);
		createEReference(tableIndexEClass, TABLE_INDEX__COLUMNS);
		createEAttribute(tableIndexEClass, TABLE_INDEX__MARK);

		viewResourceDataEClass = createEClass(VIEW_RESOURCE_DATA);
		createEAttribute(viewResourceDataEClass, VIEW_RESOURCE_DATA__SQL);
		createEAttribute(viewResourceDataEClass, VIEW_RESOURCE_DATA__IS_HISTORY);

		dbGenContextEClass = createEClass(DB_GEN_CONTEXT);

		foreignKeyEClass = createEClass(FOREIGN_KEY);
		createEAttribute(foreignKeyEClass, FOREIGN_KEY__TABLE_NAME);
		createEAttribute(foreignKeyEClass, FOREIGN_KEY__FIELD_NAME);

		tableKeyEClass = createEClass(TABLE_KEY);
		createEAttribute(tableKeyEClass, TABLE_KEY__NAME);
		createEAttribute(tableKeyEClass, TABLE_KEY__TYPE);
		createEReference(tableKeyEClass, TABLE_KEY__FOREIGN_KEY);
		createEReference(tableKeyEClass, TABLE_KEY__COLUMNS);
		createEAttribute(tableKeyEClass, TABLE_KEY__MARK);

		// Create enums
		key_typeEEnum = createEEnum(KEY_TYPE);
		columnTypeEEnum = createEEnum(COLUMN_TYPE);
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
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		databaseResourceDataEClass.getESuperTypes().add(theCorePackage.getJRESResourceInfo());
		tableResourceDataEClass.getESuperTypes().add(this.getDatabaseResourceData());
		tableColumnEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		tableIndexColumnEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		tableIndexEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		viewResourceDataEClass.getESuperTypes().add(this.getDatabaseResourceData());
		dbGenContextEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		tableKeyEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());

		// Initialize classes and features; add operations and parameters
		initEClass(dbModuleCommonPropertyEClass, DBModuleCommonProperty.class, "DBModuleCommonProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDBModuleCommonProperty_Database(), ecorePackage.getEString(), "database", "Oracle", 0, 1, DBModuleCommonProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDBModuleCommonProperty_SupportDatabases(), ecorePackage.getEString(), "supportDatabases", "Oracle,DB2,MySQL,SQL Sever", 0, 1, DBModuleCommonProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(databaseResourceDataEClass, DatabaseResourceData.class, "DatabaseResourceData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(tableResourceDataEClass, TableResourceData.class, "TableResourceData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTableResourceData_Columns(), this.getTableColumn(), null, "columns", null, 0, -1, TableResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTableResourceData_Indexes(), this.getTableIndex(), null, "indexes", null, 0, -1, TableResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTableResourceData_Keys(), this.getTableKey(), null, "keys", null, 0, -1, TableResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableColumnEClass, TableColumn.class, "TableColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableColumn_Name(), ecorePackage.getEString(), "name", "", 0, 1, TableColumn.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_ChineseName(), ecorePackage.getEString(), "chineseName", null, 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_Description(), ecorePackage.getEString(), "description", null, 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_DataType(), ecorePackage.getEString(), "dataType", null, 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_ColumnName(), ecorePackage.getEString(), "columnName", null, 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_FieldName(), ecorePackage.getEString(), "fieldName", null, 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_PrimaryKey(), ecorePackage.getEBoolean(), "primaryKey", null, 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_Unique(), ecorePackage.getEBoolean(), "unique", null, 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_Nullable(), ecorePackage.getEBoolean(), "nullable", "true", 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_DefaultValue(), ecorePackage.getEString(), "defaultValue", "", 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTableColumn_Foreignkey(), this.getForeignKey(), null, "foreignkey", null, 0, -1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_Mark(), ecorePackage.getEString(), "mark", null, 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_Comments(), ecorePackage.getEString(), "comments", null, 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableColumn_ColumnType(), this.getColumnType(), "columnType", null, 0, 1, TableColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableIndexColumnEClass, TableIndexColumn.class, "TableIndexColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableIndexColumn_ColumnName(), ecorePackage.getEString(), "columnName", null, 0, 1, TableIndexColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableIndexColumn_Ascending(), ecorePackage.getEBoolean(), "ascending", null, 0, 1, TableIndexColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableIndexColumn_ColumnType(), this.getColumnType(), "columnType", null, 0, 1, TableIndexColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableIndexEClass, TableIndex.class, "TableIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableIndex_Name(), ecorePackage.getEString(), "name", "", 0, 1, TableIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableIndex_Unique(), ecorePackage.getEBoolean(), "unique", null, 0, 1, TableIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableIndex_Cluster(), ecorePackage.getEBoolean(), "cluster", null, 0, 1, TableIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTableIndex_Columns(), this.getTableIndexColumn(), null, "columns", null, 0, -1, TableIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableIndex_Mark(), ecorePackage.getEString(), "mark", null, 0, 1, TableIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(viewResourceDataEClass, ViewResourceData.class, "ViewResourceData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getViewResourceData_Sql(), ecorePackage.getEString(), "sql", "", 0, 1, ViewResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getViewResourceData_IsHistory(), ecorePackage.getEBoolean(), "isHistory", null, 0, 1, ViewResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dbGenContextEClass, DBGenContext.class, "DBGenContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(foreignKeyEClass, ForeignKey.class, "ForeignKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getForeignKey_TableName(), ecorePackage.getEString(), "tableName", null, 0, 1, ForeignKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForeignKey_FieldName(), ecorePackage.getEString(), "fieldName", null, 0, 1, ForeignKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableKeyEClass, TableKey.class, "TableKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableKey_Name(), ecorePackage.getEString(), "name", "", 0, 1, TableKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableKey_Type(), this.getkey_type(), "type", null, 0, 1, TableKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTableKey_ForeignKey(), this.getForeignKey(), null, "foreignKey", null, 0, -1, TableKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTableKey_Columns(), this.getTableColumn(), null, "columns", null, 0, -1, TableKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableKey_Mark(), ecorePackage.getEString(), "mark", "", 0, 1, TableKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(key_typeEEnum, key_type.class, "key_type");
		addEEnumLiteral(key_typeEEnum, key_type.PRIMARY);
		addEEnumLiteral(key_typeEEnum, key_type.UNIQUE);
		addEEnumLiteral(key_typeEEnum, key_type.FOREIGN);

		initEEnum(columnTypeEEnum, ColumnType.class, "ColumnType");
		addEEnumLiteral(columnTypeEEnum, ColumnType.STD_FIELD);
		addEEnumLiteral(columnTypeEEnum, ColumnType.NON_STD_FIELD);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
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
		  (dbModuleCommonPropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "database supportDatabases"
		   });		
		addAnnotation
		  (tableResourceDataEClass, 
		   source, 
		   new String[] {
			 "constraints", "name objectId"
		   });		
		addAnnotation
		  (tableColumnEClass, 
		   source, 
		   new String[] {
			 "constraints", "name columnName fieldName defaultValue dataType"
		   });								
		addAnnotation
		  (tableIndexColumnEClass, 
		   source, 
		   new String[] {
			 "constraints", "columnName"
		   });		
		addAnnotation
		  (tableIndexEClass, 
		   source, 
		   new String[] {
			 "constraints", "name columns"
		   });		
		addAnnotation
		  (viewResourceDataEClass, 
		   source, 
		   new String[] {
			 "constraints", "name sql objectId"
		   });		
		addAnnotation
		  (tableKeyEClass, 
		   source, 
		   new String[] {
			 "constraints", "name columns"
		   });
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";					
		addAnnotation
		  (getTableColumn_Name(), 
		   source, 
		   new String[] {
			 "namespace", ""
		   });									
	}

} //DatabasePackageImpl
