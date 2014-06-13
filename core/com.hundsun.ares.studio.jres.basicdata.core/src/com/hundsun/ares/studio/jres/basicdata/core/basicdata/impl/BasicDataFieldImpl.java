/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Basic Data Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl#getMark <em>Mark</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl#getColumnType <em>Column Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BasicDataFieldImpl extends EObjectImpl implements BasicDataField {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicDataFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicdataPackage.Literals.BASIC_DATA_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String)eGet(BasicdataPackage.Literals.BASIC_DATA_FIELD__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(BasicdataPackage.Literals.BASIC_DATA_FIELD__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChineseName() {
		return (String)eGet(BasicdataPackage.Literals.BASIC_DATA_FIELD__CHINESE_NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChineseName(String newChineseName) {
		eSet(BasicdataPackage.Literals.BASIC_DATA_FIELD__CHINESE_NAME, newChineseName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return (String)eGet(BasicdataPackage.Literals.BASIC_DATA_FIELD__DESCRIPTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		eSet(BasicdataPackage.Literals.BASIC_DATA_FIELD__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataType() {
		return (String)eGet(BasicdataPackage.Literals.BASIC_DATA_FIELD__DATA_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataType(String newDataType) {
		eSet(BasicdataPackage.Literals.BASIC_DATA_FIELD__DATA_TYPE, newDataType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultValue() {
		return (String)eGet(BasicdataPackage.Literals.BASIC_DATA_FIELD__DEFAULT_VALUE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(String newDefaultValue) {
		eSet(BasicdataPackage.Literals.BASIC_DATA_FIELD__DEFAULT_VALUE, newDefaultValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMark() {
		return (String)eGet(BasicdataPackage.Literals.BASIC_DATA_FIELD__MARK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMark(String newMark) {
		eSet(BasicdataPackage.Literals.BASIC_DATA_FIELD__MARK, newMark);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComments() {
		return (String)eGet(BasicdataPackage.Literals.BASIC_DATA_FIELD__COMMENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComments(String newComments) {
		eSet(BasicdataPackage.Literals.BASIC_DATA_FIELD__COMMENTS, newComments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getColumnType() {
		return (Integer)eGet(BasicdataPackage.Literals.BASIC_DATA_FIELD__COLUMN_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumnType(int newColumnType) {
		eSet(BasicdataPackage.Literals.BASIC_DATA_FIELD__COLUMN_TYPE, newColumnType);
	}

} //BasicDataFieldImpl
