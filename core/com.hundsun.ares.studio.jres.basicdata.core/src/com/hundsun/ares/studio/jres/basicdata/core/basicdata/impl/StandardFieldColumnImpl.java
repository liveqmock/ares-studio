/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Field Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldColumnImpl#getStandardField <em>Standard Field</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StandardFieldColumnImpl extends EObjectImpl implements StandardFieldColumn {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardFieldColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicdataPackage.Literals.STANDARD_FIELD_COLUMN;
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
	public String getStandardField() {
		return (String)eGet(BasicdataPackage.Literals.STANDARD_FIELD_COLUMN__STANDARD_FIELD, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStandardField(String newStandardField) {
		eSet(BasicdataPackage.Literals.STANDARD_FIELD_COLUMN__STANDARD_FIELD, newStandardField);
	}

} //StandardFieldColumnImpl
