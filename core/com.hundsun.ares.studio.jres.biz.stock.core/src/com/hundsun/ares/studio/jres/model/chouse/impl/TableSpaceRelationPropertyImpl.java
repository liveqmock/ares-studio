/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Space Relation Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpaceRelationPropertyImpl#getHisSpace <em>His Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpaceRelationPropertyImpl#getHisIndexSpace <em>His Index Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpaceRelationPropertyImpl#getFileSpace <em>File Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpaceRelationPropertyImpl#getFileIndexSpace <em>File Index Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableSpaceRelationPropertyImpl extends EObjectImpl implements TableSpaceRelationProperty {
	/**
	 * The default value of the '{@link #getHisSpace() <em>His Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHisSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String HIS_SPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHisSpace() <em>His Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHisSpace()
	 * @generated
	 * @ordered
	 */
	protected String hisSpace = HIS_SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHisIndexSpace() <em>His Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHisIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String HIS_INDEX_SPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHisIndexSpace() <em>His Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHisIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected String hisIndexSpace = HIS_INDEX_SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileSpace() <em>File Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_SPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileSpace() <em>File Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSpace()
	 * @generated
	 * @ordered
	 */
	protected String fileSpace = FILE_SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileIndexSpace() <em>File Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_INDEX_SPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileIndexSpace() <em>File Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected String fileIndexSpace = FILE_INDEX_SPACE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableSpaceRelationPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHisSpace() {
		return hisSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHisSpace(String newHisSpace) {
		String oldHisSpace = hisSpace;
		hisSpace = newHisSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE, oldHisSpace, hisSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHisIndexSpace() {
		return hisIndexSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHisIndexSpace(String newHisIndexSpace) {
		String oldHisIndexSpace = hisIndexSpace;
		hisIndexSpace = newHisIndexSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE, oldHisIndexSpace, hisIndexSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFileSpace() {
		return fileSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileSpace(String newFileSpace) {
		String oldFileSpace = fileSpace;
		fileSpace = newFileSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE, oldFileSpace, fileSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFileIndexSpace() {
		return fileIndexSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileIndexSpace(String newFileIndexSpace) {
		String oldFileIndexSpace = fileIndexSpace;
		fileIndexSpace = newFileIndexSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE, oldFileIndexSpace, fileIndexSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE:
				return getHisSpace();
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE:
				return getHisIndexSpace();
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE:
				return getFileSpace();
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE:
				return getFileIndexSpace();
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
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE:
				setHisSpace((String)newValue);
				return;
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE:
				setHisIndexSpace((String)newValue);
				return;
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE:
				setFileSpace((String)newValue);
				return;
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE:
				setFileIndexSpace((String)newValue);
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
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE:
				setHisSpace(HIS_SPACE_EDEFAULT);
				return;
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE:
				setHisIndexSpace(HIS_INDEX_SPACE_EDEFAULT);
				return;
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE:
				setFileSpace(FILE_SPACE_EDEFAULT);
				return;
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE:
				setFileIndexSpace(FILE_INDEX_SPACE_EDEFAULT);
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
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE:
				return HIS_SPACE_EDEFAULT == null ? hisSpace != null : !HIS_SPACE_EDEFAULT.equals(hisSpace);
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE:
				return HIS_INDEX_SPACE_EDEFAULT == null ? hisIndexSpace != null : !HIS_INDEX_SPACE_EDEFAULT.equals(hisIndexSpace);
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE:
				return FILE_SPACE_EDEFAULT == null ? fileSpace != null : !FILE_SPACE_EDEFAULT.equals(fileSpace);
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE:
				return FILE_INDEX_SPACE_EDEFAULT == null ? fileIndexSpace != null : !FILE_INDEX_SPACE_EDEFAULT.equals(fileIndexSpace);
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
		result.append(" (hisSpace: ");
		result.append(hisSpace);
		result.append(", hisIndexSpace: ");
		result.append(hisIndexSpace);
		result.append(", fileSpace: ");
		result.append(fileSpace);
		result.append(", fileIndexSpace: ");
		result.append(fileIndexSpace);
		result.append(')');
		return result.toString();
	}

} //TableSpaceRelationPropertyImpl
