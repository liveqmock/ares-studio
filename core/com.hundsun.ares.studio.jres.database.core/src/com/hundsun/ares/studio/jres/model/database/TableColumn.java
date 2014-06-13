/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getDataType <em>Data Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#isPrimaryKey <em>Primary Key</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#isUnique <em>Unique</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#isNullable <em>Nullable</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getForeignkey <em>Foreignkey</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getMark <em>Mark</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getComments <em>Comments</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getColumnType <em>Column Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name columnName fieldName defaultValue dataType'"
 * @generated
 */
public interface TableColumn extends ExtensibleModel {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 如果用户定义了列名则返回列名，如果列名为空则返回引用的标准字段名
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 字段名，目前和FieldName属性一样。
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_Name()
	 * @model default="" transient="true" volatile="true"
	 *        extendedMetaData="namespace=''"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 等同于{@link TableColumn#setFieldName(String) setFieldName}
	 * </p>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chinese Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chinese Name</em>' attribute.
	 * @see #setChineseName(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_ChineseName()
	 * @model
	 * @generated
	 */
	String getChineseName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getChineseName <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chinese Name</em>' attribute.
	 * @see #getChineseName()
	 * @generated
	 */
	void setChineseName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 数据类型，只有非标准字段才有用。 注意这个值在界面上目前显示的是“字段类型”，注意和ColumnType这个属性区分。
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Type</em>' attribute.
	 * @see #setDataType(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_DataType()
	 * @model
	 * @generated
	 */
	String getDataType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getDataType <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' attribute.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(String value);

	/**
	 * Returns the value of the '<em><b>Column Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * “重命名”属性。
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Column Name</em>' attribute.
	 * @see #setColumnName(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_ColumnName()
	 * @model
	 * @generated
	 */
	String getColumnName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getColumnName <em>Column Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Name</em>' attribute.
	 * @see #getColumnName()
	 * @generated
	 */
	void setColumnName(String value);

	/**
	 * Returns the value of the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 字段名，目前和Name属性一致。
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field Name</em>' attribute.
	 * @see #setFieldName(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_FieldName()
	 * @model
	 * @generated
	 */
	String getFieldName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getFieldName <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Name</em>' attribute.
	 * @see #getFieldName()
	 * @generated
	 */
	void setFieldName(String value);

	/**
	 * Returns the value of the '<em><b>Primary Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Key</em>' attribute.
	 * @see #setPrimaryKey(boolean)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_PrimaryKey()
	 * @model
	 * @generated
	 */
	boolean isPrimaryKey();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#isPrimaryKey <em>Primary Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Key</em>' attribute.
	 * @see #isPrimaryKey()
	 * @generated
	 */
	void setPrimaryKey(boolean value);

	/**
	 * Returns the value of the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique</em>' attribute.
	 * @see #setUnique(boolean)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_Unique()
	 * @model
	 * @generated
	 */
	boolean isUnique();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#isUnique <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique</em>' attribute.
	 * @see #isUnique()
	 * @generated
	 */
	void setUnique(boolean value);

	/**
	 * Returns the value of the '<em><b>Nullable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nullable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nullable</em>' attribute.
	 * @see #setNullable(boolean)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_Nullable()
	 * @model default="true"
	 * @generated
	 */
	boolean isNullable();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#isNullable <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nullable</em>' attribute.
	 * @see #isNullable()
	 * @generated
	 */
	void setNullable(boolean value);

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' attribute.
	 * @see #setDefaultValue(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_DefaultValue()
	 * @model default=""
	 * @generated
	 */
	String getDefaultValue();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getDefaultValue <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' attribute.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(String value);

	/**
	 * Returns the value of the '<em><b>Foreignkey</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.ForeignKey}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Foreignkey</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreignkey</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_Foreignkey()
	 * @model containment="true"
	 * @generated
	 */
	EList<ForeignKey> getForeignkey();

	/**
	 * Returns the value of the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mark</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mark</em>' attribute.
	 * @see #setMark(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_Mark()
	 * @model
	 * @generated
	 */
	String getMark();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getMark <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mark</em>' attribute.
	 * @see #getMark()
	 * @generated
	 */
	void setMark(String value);

	/**
	 * Returns the value of the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' attribute.
	 * @see #setComments(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_Comments()
	 * @model
	 * @generated
	 */
	String getComments();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getComments <em>Comments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comments</em>' attribute.
	 * @see #getComments()
	 * @generated
	 */
	void setComments(String value);

	/**
	 * Returns the value of the '<em><b>Column Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.hundsun.ares.studio.jres.model.database.ColumnType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 字段类型，用于区分和标准字段和非标准字段。 注意界面上显示的“字段类型”实际上是Dataype这个属性，不是这个属性。这个属性在界面上不显示。
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Column Type</em>' attribute.
	 * @see com.hundsun.ares.studio.jres.model.database.ColumnType
	 * @see #setColumnType(ColumnType)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableColumn_ColumnType()
	 * @model
	 * @generated
	 */
	ColumnType getColumnType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getColumnType <em>Column Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Type</em>' attribute.
	 * @see com.hundsun.ares.studio.jres.model.database.ColumnType
	 * @see #getColumnType()
	 * @generated
	 */
	void setColumnType(ColumnType value);

} // TableColumn
