/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.FunctionImpl#getSubTransCode <em>Sub Trans Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.FunctionImpl#getServID <em>Serv ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionImpl extends NamedElementImpl implements Function {
	/**
	 * The default value of the '{@link #getSubTransCode() <em>Sub Trans Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubTransCode()
	 * @generated
	 * @ordered
	 */
	protected static final String SUB_TRANS_CODE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getSubTransCode() <em>Sub Trans Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubTransCode()
	 * @generated
	 * @ordered
	 */
	protected String subTransCode = SUB_TRANS_CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getServID() <em>Serv ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServID()
	 * @generated
	 * @ordered
	 */
	protected static final String SERV_ID_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getServID() <em>Serv ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServID()
	 * @generated
	 * @ordered
	 */
	protected String servID = SERV_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.FUNCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubTransCode() {
		return subTransCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubTransCode(String newSubTransCode) {
		String oldSubTransCode = subTransCode;
		subTransCode = newSubTransCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.FUNCTION__SUB_TRANS_CODE, oldSubTransCode, subTransCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServID() {
		return servID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServID(String newServID) {
		String oldServID = servID;
		servID = newServID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.FUNCTION__SERV_ID, oldServID, servID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.FUNCTION__SUB_TRANS_CODE:
				return getSubTransCode();
			case MetadataPackage.FUNCTION__SERV_ID:
				return getServID();
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
			case MetadataPackage.FUNCTION__SUB_TRANS_CODE:
				setSubTransCode((String)newValue);
				return;
			case MetadataPackage.FUNCTION__SERV_ID:
				setServID((String)newValue);
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
			case MetadataPackage.FUNCTION__SUB_TRANS_CODE:
				setSubTransCode(SUB_TRANS_CODE_EDEFAULT);
				return;
			case MetadataPackage.FUNCTION__SERV_ID:
				setServID(SERV_ID_EDEFAULT);
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
			case MetadataPackage.FUNCTION__SUB_TRANS_CODE:
				return SUB_TRANS_CODE_EDEFAULT == null ? subTransCode != null : !SUB_TRANS_CODE_EDEFAULT.equals(subTransCode);
			case MetadataPackage.FUNCTION__SERV_ID:
				return SERV_ID_EDEFAULT == null ? servID != null : !SERV_ID_EDEFAULT.equals(servID);
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
		result.append(" (subTransCode: ");
		result.append(subTransCode);
		result.append(", servID: ");
		result.append(servID);
		result.append(')');
		return result.toString();
	}

} //FunctionImpl
