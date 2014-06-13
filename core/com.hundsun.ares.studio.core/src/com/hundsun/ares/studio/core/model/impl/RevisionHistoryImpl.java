/**
 * 源程序名称：RevisionHistoryImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model.impl;

import com.hundsun.ares.studio.core.model.CorePackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import com.hundsun.ares.studio.core.model.RevisionHistory;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Revision History</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl#getModifiedDate <em>Modified Date</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl#getOrderNumber <em>Order Number</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl#getModifiedBy <em>Modified By</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl#getCharger <em>Charger</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl#getModifiedReason <em>Modified Reason</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl#getModified <em>Modified</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl#getLocation <em>Location</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RevisionHistoryImpl extends ExtensibleModelImpl implements RevisionHistory {
	/**
	 * The default value of the '{@link #getModifiedDate() <em>Modified Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiedDate()
	 * @generated
	 * @ordered
	 */
	protected static final String MODIFIED_DATE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getModifiedDate() <em>Modified Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiedDate()
	 * @generated
	 * @ordered
	 */
	protected String modifiedDate = MODIFIED_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrderNumber() <em>Order Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderNumber()
	 * @generated
	 * @ordered
	 */
	protected static final String ORDER_NUMBER_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getOrderNumber() <em>Order Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderNumber()
	 * @generated
	 * @ordered
	 */
	protected String orderNumber = ORDER_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getModifiedBy() <em>Modified By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiedBy()
	 * @generated
	 * @ordered
	 */
	protected static final String MODIFIED_BY_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getModifiedBy() <em>Modified By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiedBy()
	 * @generated
	 * @ordered
	 */
	protected String modifiedBy = MODIFIED_BY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCharger() <em>Charger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharger()
	 * @generated
	 * @ordered
	 */
	protected static final String CHARGER_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getCharger() <em>Charger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharger()
	 * @generated
	 * @ordered
	 */
	protected String charger = CHARGER_EDEFAULT;

	/**
	 * The default value of the '{@link #getModifiedReason() <em>Modified Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiedReason()
	 * @generated
	 * @ordered
	 */
	protected static final String MODIFIED_REASON_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getModifiedReason() <em>Modified Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiedReason()
	 * @generated
	 * @ordered
	 */
	protected String modifiedReason = MODIFIED_REASON_EDEFAULT;

	/**
	 * The default value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
	protected static final String MODIFIED_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
	protected String modified = MODIFIED_EDEFAULT;

	/**
	 * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected String comment = COMMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RevisionHistoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.REVISION_HISTORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifiedDate(String newModifiedDate) {
		String oldModifiedDate = modifiedDate;
		modifiedDate = newModifiedDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.REVISION_HISTORY__MODIFIED_DATE, oldModifiedDate, modifiedDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.REVISION_HISTORY__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderNumber(String newOrderNumber) {
		String oldOrderNumber = orderNumber;
		orderNumber = newOrderNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.REVISION_HISTORY__ORDER_NUMBER, oldOrderNumber, orderNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifiedBy(String newModifiedBy) {
		String oldModifiedBy = modifiedBy;
		modifiedBy = newModifiedBy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.REVISION_HISTORY__MODIFIED_BY, oldModifiedBy, modifiedBy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCharger() {
		return charger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharger(String newCharger) {
		String oldCharger = charger;
		charger = newCharger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.REVISION_HISTORY__CHARGER, oldCharger, charger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModifiedReason() {
		return modifiedReason;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifiedReason(String newModifiedReason) {
		String oldModifiedReason = modifiedReason;
		modifiedReason = newModifiedReason;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.REVISION_HISTORY__MODIFIED_REASON, oldModifiedReason, modifiedReason));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModified() {
		return modified;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModified(String newModified) {
		String oldModified = modified;
		modified = newModified;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.REVISION_HISTORY__MODIFIED, oldModified, modified));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.REVISION_HISTORY__COMMENT, oldComment, comment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(String newLocation) {
		String oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.REVISION_HISTORY__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.REVISION_HISTORY__MODIFIED_DATE:
				return getModifiedDate();
			case CorePackage.REVISION_HISTORY__VERSION:
				return getVersion();
			case CorePackage.REVISION_HISTORY__ORDER_NUMBER:
				return getOrderNumber();
			case CorePackage.REVISION_HISTORY__MODIFIED_BY:
				return getModifiedBy();
			case CorePackage.REVISION_HISTORY__CHARGER:
				return getCharger();
			case CorePackage.REVISION_HISTORY__MODIFIED_REASON:
				return getModifiedReason();
			case CorePackage.REVISION_HISTORY__MODIFIED:
				return getModified();
			case CorePackage.REVISION_HISTORY__COMMENT:
				return getComment();
			case CorePackage.REVISION_HISTORY__LOCATION:
				return getLocation();
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
			case CorePackage.REVISION_HISTORY__MODIFIED_DATE:
				setModifiedDate((String)newValue);
				return;
			case CorePackage.REVISION_HISTORY__VERSION:
				setVersion((String)newValue);
				return;
			case CorePackage.REVISION_HISTORY__ORDER_NUMBER:
				setOrderNumber((String)newValue);
				return;
			case CorePackage.REVISION_HISTORY__MODIFIED_BY:
				setModifiedBy((String)newValue);
				return;
			case CorePackage.REVISION_HISTORY__CHARGER:
				setCharger((String)newValue);
				return;
			case CorePackage.REVISION_HISTORY__MODIFIED_REASON:
				setModifiedReason((String)newValue);
				return;
			case CorePackage.REVISION_HISTORY__MODIFIED:
				setModified((String)newValue);
				return;
			case CorePackage.REVISION_HISTORY__COMMENT:
				setComment((String)newValue);
				return;
			case CorePackage.REVISION_HISTORY__LOCATION:
				setLocation((String)newValue);
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
			case CorePackage.REVISION_HISTORY__MODIFIED_DATE:
				setModifiedDate(MODIFIED_DATE_EDEFAULT);
				return;
			case CorePackage.REVISION_HISTORY__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case CorePackage.REVISION_HISTORY__ORDER_NUMBER:
				setOrderNumber(ORDER_NUMBER_EDEFAULT);
				return;
			case CorePackage.REVISION_HISTORY__MODIFIED_BY:
				setModifiedBy(MODIFIED_BY_EDEFAULT);
				return;
			case CorePackage.REVISION_HISTORY__CHARGER:
				setCharger(CHARGER_EDEFAULT);
				return;
			case CorePackage.REVISION_HISTORY__MODIFIED_REASON:
				setModifiedReason(MODIFIED_REASON_EDEFAULT);
				return;
			case CorePackage.REVISION_HISTORY__MODIFIED:
				setModified(MODIFIED_EDEFAULT);
				return;
			case CorePackage.REVISION_HISTORY__COMMENT:
				setComment(COMMENT_EDEFAULT);
				return;
			case CorePackage.REVISION_HISTORY__LOCATION:
				setLocation(LOCATION_EDEFAULT);
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
			case CorePackage.REVISION_HISTORY__MODIFIED_DATE:
				return MODIFIED_DATE_EDEFAULT == null ? modifiedDate != null : !MODIFIED_DATE_EDEFAULT.equals(modifiedDate);
			case CorePackage.REVISION_HISTORY__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case CorePackage.REVISION_HISTORY__ORDER_NUMBER:
				return ORDER_NUMBER_EDEFAULT == null ? orderNumber != null : !ORDER_NUMBER_EDEFAULT.equals(orderNumber);
			case CorePackage.REVISION_HISTORY__MODIFIED_BY:
				return MODIFIED_BY_EDEFAULT == null ? modifiedBy != null : !MODIFIED_BY_EDEFAULT.equals(modifiedBy);
			case CorePackage.REVISION_HISTORY__CHARGER:
				return CHARGER_EDEFAULT == null ? charger != null : !CHARGER_EDEFAULT.equals(charger);
			case CorePackage.REVISION_HISTORY__MODIFIED_REASON:
				return MODIFIED_REASON_EDEFAULT == null ? modifiedReason != null : !MODIFIED_REASON_EDEFAULT.equals(modifiedReason);
			case CorePackage.REVISION_HISTORY__MODIFIED:
				return MODIFIED_EDEFAULT == null ? modified != null : !MODIFIED_EDEFAULT.equals(modified);
			case CorePackage.REVISION_HISTORY__COMMENT:
				return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
			case CorePackage.REVISION_HISTORY__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
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
		result.append(" (modifiedDate: ");
		result.append(modifiedDate);
		result.append(", version: ");
		result.append(version);
		result.append(", orderNumber: ");
		result.append(orderNumber);
		result.append(", modifiedBy: ");
		result.append(modifiedBy);
		result.append(", charger: ");
		result.append(charger);
		result.append(", modifiedReason: ");
		result.append(modifiedReason);
		result.append(", modified: ");
		result.append(modified);
		result.append(", comment: ");
		result.append(comment);
		result.append(", location: ");
		result.append(location);
		result.append(')');
		return result.toString();
	}

} //RevisionHistoryImpl
