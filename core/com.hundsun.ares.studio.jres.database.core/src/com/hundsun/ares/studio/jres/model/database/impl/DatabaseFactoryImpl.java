/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.impl;

import com.hundsun.ares.studio.jres.model.database.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.hundsun.ares.studio.jres.model.database.DBGenContext;
import com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DatabaseFactoryImpl extends EFactoryImpl implements DatabaseFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DatabaseFactory init() {
		try {
			DatabaseFactory theDatabaseFactory = (DatabaseFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.hundsun.com/ares/studio/jres/database/1.0.0"); 
			if (theDatabaseFactory != null) {
				return theDatabaseFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DatabaseFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseFactoryImpl() {
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
			case DatabasePackage.DB_MODULE_COMMON_PROPERTY: return createDBModuleCommonProperty();
			case DatabasePackage.DATABASE_RESOURCE_DATA: return createDatabaseResourceData();
			case DatabasePackage.TABLE_RESOURCE_DATA: return createTableResourceData();
			case DatabasePackage.TABLE_COLUMN: return createTableColumn();
			case DatabasePackage.TABLE_INDEX_COLUMN: return createTableIndexColumn();
			case DatabasePackage.TABLE_INDEX: return createTableIndex();
			case DatabasePackage.VIEW_RESOURCE_DATA: return createViewResourceData();
			case DatabasePackage.DB_GEN_CONTEXT: return createDBGenContext();
			case DatabasePackage.FOREIGN_KEY: return createForeignKey();
			case DatabasePackage.TABLE_KEY: return createTableKey();
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
			case DatabasePackage.KEY_TYPE:
				return createkey_typeFromString(eDataType, initialValue);
			case DatabasePackage.COLUMN_TYPE:
				return createColumnTypeFromString(eDataType, initialValue);
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
			case DatabasePackage.KEY_TYPE:
				return convertkey_typeToString(eDataType, instanceValue);
			case DatabasePackage.COLUMN_TYPE:
				return convertColumnTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DBModuleCommonProperty createDBModuleCommonProperty() {
		DBModuleCommonPropertyImpl dbModuleCommonProperty = new DBModuleCommonPropertyImpl();
		return dbModuleCommonProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseResourceData createDatabaseResourceData() {
		DatabaseResourceDataImpl databaseResourceData = new DatabaseResourceDataImpl();
		return databaseResourceData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableResourceData createTableResourceData() {
		TableResourceDataImpl tableResourceData = new TableResourceDataImpl();
		return tableResourceData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableColumn createTableColumn() {
		TableColumnImpl tableColumn = new TableColumnImpl();
		return tableColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableIndexColumn createTableIndexColumn() {
		TableIndexColumnImpl tableIndexColumn = new TableIndexColumnImpl();
		return tableIndexColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableIndex createTableIndex() {
		TableIndexImpl tableIndex = new TableIndexImpl();
		return tableIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewResourceData createViewResourceData() {
		ViewResourceDataImpl viewResourceData = new ViewResourceDataImpl();
		return viewResourceData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DBGenContext createDBGenContext() {
		DBGenContextImpl dbGenContext = new DBGenContextImpl();
		return dbGenContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForeignKey createForeignKey() {
		ForeignKeyImpl foreignKey = new ForeignKeyImpl();
		return foreignKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableKey createTableKey() {
		TableKeyImpl tableKey = new TableKeyImpl();
		return tableKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public key_type createkey_typeFromString(EDataType eDataType, String initialValue) {
		key_type result = key_type.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertkey_typeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColumnType createColumnTypeFromString(EDataType eDataType, String initialValue) {
		ColumnType result = ColumnType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertColumnTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabasePackage getDatabasePackage() {
		return (DatabasePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DatabasePackage getPackage() {
		return DatabasePackage.eINSTANCE;
	}

} //DatabaseFactoryImpl
