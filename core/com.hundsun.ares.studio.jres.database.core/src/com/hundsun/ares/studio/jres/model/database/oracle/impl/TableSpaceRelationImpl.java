/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Space Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.TableSpaceRelationImpl#getMainSpace <em>Main Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.TableSpaceRelationImpl#getIndexSpace <em>Index Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableSpaceRelationImpl extends ExtensibleModelImpl implements TableSpaceRelation {
	/**
	 * The default value of the '{@link #getMainSpace() <em>Main Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMainSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String MAIN_SPACE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getMainSpace() <em>Main Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMainSpace()
	 * @generated
	 * @ordered
	 */
	protected String mainSpace = MAIN_SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIndexSpace() <em>Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String INDEX_SPACE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getIndexSpace() <em>Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected String indexSpace = INDEX_SPACE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableSpaceRelationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OraclePackage.Literals.TABLE_SPACE_RELATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMainSpace() {
		return mainSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMainSpace(String newMainSpace) {
		String oldMainSpace = mainSpace;
		mainSpace = newMainSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.TABLE_SPACE_RELATION__MAIN_SPACE, oldMainSpace, mainSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIndexSpace() {
		return indexSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexSpace(String newIndexSpace) {
		String oldIndexSpace = indexSpace;
		indexSpace = newIndexSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.TABLE_SPACE_RELATION__INDEX_SPACE, oldIndexSpace, indexSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OraclePackage.TABLE_SPACE_RELATION__MAIN_SPACE:
				return getMainSpace();
			case OraclePackage.TABLE_SPACE_RELATION__INDEX_SPACE:
				return getIndexSpace();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OraclePackage.TABLE_SPACE_RELATION__MAIN_SPACE:
				setMainSpace((String)newValue);
				return;
			case OraclePackage.TABLE_SPACE_RELATION__INDEX_SPACE:
				setIndexSpace((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OraclePackage.TABLE_SPACE_RELATION__MAIN_SPACE:
				setMainSpace(MAIN_SPACE_EDEFAULT);
				return;
			case OraclePackage.TABLE_SPACE_RELATION__INDEX_SPACE:
				setIndexSpace(INDEX_SPACE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OraclePackage.TABLE_SPACE_RELATION__MAIN_SPACE:
				return MAIN_SPACE_EDEFAULT == null ? mainSpace != null : !MAIN_SPACE_EDEFAULT.equals(mainSpace);
			case OraclePackage.TABLE_SPACE_RELATION__INDEX_SPACE:
				return INDEX_SPACE_EDEFAULT == null ? indexSpace != null : !INDEX_SPACE_EDEFAULT.equals(indexSpace);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mainSpace: ");
		result.append(mainSpace);
		result.append(", indexSpace: ");
		result.append(indexSpace);
		result.append(')');
		return result.toString();
	}

} //TableSpaceRelationImpl
