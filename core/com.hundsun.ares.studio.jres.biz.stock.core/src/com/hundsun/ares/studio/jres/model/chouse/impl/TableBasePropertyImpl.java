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
import com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Base Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getChear <em>Chear</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getRedu <em>Redu</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#isHistory <em>History</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getHistorySpace <em>History Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getHistoryIndexSpace <em>History Index Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getObjectID <em>Object ID</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getSplitField <em>Split Field</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getSplitNum <em>Split Num</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getStartData <em>Start Data</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#isUserSplit <em>User Split</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#isIsRedu <em>Is Redu</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#isIsClear <em>Is Clear</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getFileSpace <em>File Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getFileIndexSpace <em>File Index Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl#getClearIndexSpace <em>Clear Index Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableBasePropertyImpl extends EObjectImpl implements TableBaseProperty {
	/**
	 * The default value of the '{@link #getChear() <em>Chear</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChear()
	 * @generated
	 * @ordered
	 */
	protected static final String CHEAR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChear() <em>Chear</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChear()
	 * @generated
	 * @ordered
	 */
	protected String chear = CHEAR_EDEFAULT;

	/**
	 * The default value of the '{@link #getRedu() <em>Redu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedu()
	 * @generated
	 * @ordered
	 */
	protected static final String REDU_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRedu() <em>Redu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedu()
	 * @generated
	 * @ordered
	 */
	protected String redu = REDU_EDEFAULT;

	/**
	 * The default value of the '{@link #isHistory() <em>History</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHistory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HISTORY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHistory() <em>History</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHistory()
	 * @generated
	 * @ordered
	 */
	protected boolean history = HISTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getHistorySpace() <em>History Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHistorySpace()
	 * @generated
	 * @ordered
	 */
	protected static final String HISTORY_SPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHistorySpace() <em>History Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHistorySpace()
	 * @generated
	 * @ordered
	 */
	protected String historySpace = HISTORY_SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHistoryIndexSpace() <em>History Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHistoryIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String HISTORY_INDEX_SPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHistoryIndexSpace() <em>History Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHistoryIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected String historyIndexSpace = HISTORY_INDEX_SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getObjectID() <em>Object ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectID()
	 * @generated
	 * @ordered
	 */
	protected static final String OBJECT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getObjectID() <em>Object ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectID()
	 * @generated
	 * @ordered
	 */
	protected String objectID = OBJECT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getSplitField() <em>Split Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSplitField()
	 * @generated
	 * @ordered
	 */
	protected static final String SPLIT_FIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSplitField() <em>Split Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSplitField()
	 * @generated
	 * @ordered
	 */
	protected String splitField = SPLIT_FIELD_EDEFAULT;

	/**
	 * The default value of the '{@link #getSplitNum() <em>Split Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSplitNum()
	 * @generated
	 * @ordered
	 */
	protected static final String SPLIT_NUM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSplitNum() <em>Split Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSplitNum()
	 * @generated
	 * @ordered
	 */
	protected String splitNum = SPLIT_NUM_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartData() <em>Start Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartData()
	 * @generated
	 * @ordered
	 */
	protected static final String START_DATA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartData() <em>Start Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartData()
	 * @generated
	 * @ordered
	 */
	protected String startData = START_DATA_EDEFAULT;

	/**
	 * The default value of the '{@link #isUserSplit() <em>User Split</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUserSplit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USER_SPLIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUserSplit() <em>User Split</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUserSplit()
	 * @generated
	 * @ordered
	 */
	protected boolean userSplit = USER_SPLIT_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsRedu() <em>Is Redu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRedu()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_REDU_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsRedu() <em>Is Redu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRedu()
	 * @generated
	 * @ordered
	 */
	protected boolean isRedu = IS_REDU_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsClear() <em>Is Clear</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsClear()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CLEAR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsClear() <em>Is Clear</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsClear()
	 * @generated
	 * @ordered
	 */
	protected boolean isClear = IS_CLEAR_EDEFAULT;

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
	 * The default value of the '{@link #getClearIndexSpace() <em>Clear Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClearIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String CLEAR_INDEX_SPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClearIndexSpace() <em>Clear Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClearIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected String clearIndexSpace = CLEAR_INDEX_SPACE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableBasePropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.TABLE_BASE_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChear() {
		return chear;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChear(String newChear) {
		String oldChear = chear;
		chear = newChear;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__CHEAR, oldChear, chear));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRedu() {
		return redu;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRedu(String newRedu) {
		String oldRedu = redu;
		redu = newRedu;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__REDU, oldRedu, redu));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHistory() {
		return history;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHistory(boolean newHistory) {
		boolean oldHistory = history;
		history = newHistory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__HISTORY, oldHistory, history));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHistorySpace() {
		return historySpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHistorySpace(String newHistorySpace) {
		String oldHistorySpace = historySpace;
		historySpace = newHistorySpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__HISTORY_SPACE, oldHistorySpace, historySpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHistoryIndexSpace() {
		return historyIndexSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHistoryIndexSpace(String newHistoryIndexSpace) {
		String oldHistoryIndexSpace = historyIndexSpace;
		historyIndexSpace = newHistoryIndexSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE, oldHistoryIndexSpace, historyIndexSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getObjectID() {
		return objectID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObjectID(String newObjectID) {
		String oldObjectID = objectID;
		objectID = newObjectID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__OBJECT_ID, oldObjectID, objectID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSplitField() {
		return splitField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSplitField(String newSplitField) {
		String oldSplitField = splitField;
		splitField = newSplitField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__SPLIT_FIELD, oldSplitField, splitField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSplitNum() {
		return splitNum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSplitNum(String newSplitNum) {
		String oldSplitNum = splitNum;
		splitNum = newSplitNum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__SPLIT_NUM, oldSplitNum, splitNum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStartData() {
		return startData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartData(String newStartData) {
		String oldStartData = startData;
		startData = newStartData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__START_DATA, oldStartData, startData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUserSplit() {
		return userSplit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserSplit(boolean newUserSplit) {
		boolean oldUserSplit = userSplit;
		userSplit = newUserSplit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__USER_SPLIT, oldUserSplit, userSplit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsRedu() {
		return isRedu;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsRedu(boolean newIsRedu) {
		boolean oldIsRedu = isRedu;
		isRedu = newIsRedu;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__IS_REDU, oldIsRedu, isRedu));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsClear() {
		return isClear;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsClear(boolean newIsClear) {
		boolean oldIsClear = isClear;
		isClear = newIsClear;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__IS_CLEAR, oldIsClear, isClear));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__FILE_SPACE, oldFileSpace, fileSpace));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__FILE_INDEX_SPACE, oldFileIndexSpace, fileIndexSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClearIndexSpace() {
		return clearIndexSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClearIndexSpace(String newClearIndexSpace) {
		String oldClearIndexSpace = clearIndexSpace;
		clearIndexSpace = newClearIndexSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE, oldClearIndexSpace, clearIndexSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.TABLE_BASE_PROPERTY__CHEAR:
				return getChear();
			case ChousePackage.TABLE_BASE_PROPERTY__REDU:
				return getRedu();
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY:
				return isHistory();
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY_SPACE:
				return getHistorySpace();
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE:
				return getHistoryIndexSpace();
			case ChousePackage.TABLE_BASE_PROPERTY__OBJECT_ID:
				return getObjectID();
			case ChousePackage.TABLE_BASE_PROPERTY__SPLIT_FIELD:
				return getSplitField();
			case ChousePackage.TABLE_BASE_PROPERTY__SPLIT_NUM:
				return getSplitNum();
			case ChousePackage.TABLE_BASE_PROPERTY__START_DATA:
				return getStartData();
			case ChousePackage.TABLE_BASE_PROPERTY__USER_SPLIT:
				return isUserSplit();
			case ChousePackage.TABLE_BASE_PROPERTY__IS_REDU:
				return isIsRedu();
			case ChousePackage.TABLE_BASE_PROPERTY__IS_CLEAR:
				return isIsClear();
			case ChousePackage.TABLE_BASE_PROPERTY__FILE_SPACE:
				return getFileSpace();
			case ChousePackage.TABLE_BASE_PROPERTY__FILE_INDEX_SPACE:
				return getFileIndexSpace();
			case ChousePackage.TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE:
				return getClearIndexSpace();
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
			case ChousePackage.TABLE_BASE_PROPERTY__CHEAR:
				setChear((String)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__REDU:
				setRedu((String)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY:
				setHistory((Boolean)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY_SPACE:
				setHistorySpace((String)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE:
				setHistoryIndexSpace((String)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__OBJECT_ID:
				setObjectID((String)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__SPLIT_FIELD:
				setSplitField((String)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__SPLIT_NUM:
				setSplitNum((String)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__START_DATA:
				setStartData((String)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__USER_SPLIT:
				setUserSplit((Boolean)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__IS_REDU:
				setIsRedu((Boolean)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__IS_CLEAR:
				setIsClear((Boolean)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__FILE_SPACE:
				setFileSpace((String)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__FILE_INDEX_SPACE:
				setFileIndexSpace((String)newValue);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE:
				setClearIndexSpace((String)newValue);
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
			case ChousePackage.TABLE_BASE_PROPERTY__CHEAR:
				setChear(CHEAR_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__REDU:
				setRedu(REDU_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY:
				setHistory(HISTORY_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY_SPACE:
				setHistorySpace(HISTORY_SPACE_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE:
				setHistoryIndexSpace(HISTORY_INDEX_SPACE_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__OBJECT_ID:
				setObjectID(OBJECT_ID_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__SPLIT_FIELD:
				setSplitField(SPLIT_FIELD_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__SPLIT_NUM:
				setSplitNum(SPLIT_NUM_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__START_DATA:
				setStartData(START_DATA_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__USER_SPLIT:
				setUserSplit(USER_SPLIT_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__IS_REDU:
				setIsRedu(IS_REDU_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__IS_CLEAR:
				setIsClear(IS_CLEAR_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__FILE_SPACE:
				setFileSpace(FILE_SPACE_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__FILE_INDEX_SPACE:
				setFileIndexSpace(FILE_INDEX_SPACE_EDEFAULT);
				return;
			case ChousePackage.TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE:
				setClearIndexSpace(CLEAR_INDEX_SPACE_EDEFAULT);
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
			case ChousePackage.TABLE_BASE_PROPERTY__CHEAR:
				return CHEAR_EDEFAULT == null ? chear != null : !CHEAR_EDEFAULT.equals(chear);
			case ChousePackage.TABLE_BASE_PROPERTY__REDU:
				return REDU_EDEFAULT == null ? redu != null : !REDU_EDEFAULT.equals(redu);
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY:
				return history != HISTORY_EDEFAULT;
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY_SPACE:
				return HISTORY_SPACE_EDEFAULT == null ? historySpace != null : !HISTORY_SPACE_EDEFAULT.equals(historySpace);
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE:
				return HISTORY_INDEX_SPACE_EDEFAULT == null ? historyIndexSpace != null : !HISTORY_INDEX_SPACE_EDEFAULT.equals(historyIndexSpace);
			case ChousePackage.TABLE_BASE_PROPERTY__OBJECT_ID:
				return OBJECT_ID_EDEFAULT == null ? objectID != null : !OBJECT_ID_EDEFAULT.equals(objectID);
			case ChousePackage.TABLE_BASE_PROPERTY__SPLIT_FIELD:
				return SPLIT_FIELD_EDEFAULT == null ? splitField != null : !SPLIT_FIELD_EDEFAULT.equals(splitField);
			case ChousePackage.TABLE_BASE_PROPERTY__SPLIT_NUM:
				return SPLIT_NUM_EDEFAULT == null ? splitNum != null : !SPLIT_NUM_EDEFAULT.equals(splitNum);
			case ChousePackage.TABLE_BASE_PROPERTY__START_DATA:
				return START_DATA_EDEFAULT == null ? startData != null : !START_DATA_EDEFAULT.equals(startData);
			case ChousePackage.TABLE_BASE_PROPERTY__USER_SPLIT:
				return userSplit != USER_SPLIT_EDEFAULT;
			case ChousePackage.TABLE_BASE_PROPERTY__IS_REDU:
				return isRedu != IS_REDU_EDEFAULT;
			case ChousePackage.TABLE_BASE_PROPERTY__IS_CLEAR:
				return isClear != IS_CLEAR_EDEFAULT;
			case ChousePackage.TABLE_BASE_PROPERTY__FILE_SPACE:
				return FILE_SPACE_EDEFAULT == null ? fileSpace != null : !FILE_SPACE_EDEFAULT.equals(fileSpace);
			case ChousePackage.TABLE_BASE_PROPERTY__FILE_INDEX_SPACE:
				return FILE_INDEX_SPACE_EDEFAULT == null ? fileIndexSpace != null : !FILE_INDEX_SPACE_EDEFAULT.equals(fileIndexSpace);
			case ChousePackage.TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE:
				return CLEAR_INDEX_SPACE_EDEFAULT == null ? clearIndexSpace != null : !CLEAR_INDEX_SPACE_EDEFAULT.equals(clearIndexSpace);
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
		result.append(" (chear: ");
		result.append(chear);
		result.append(", redu: ");
		result.append(redu);
		result.append(", history: ");
		result.append(history);
		result.append(", historySpace: ");
		result.append(historySpace);
		result.append(", historyIndexSpace: ");
		result.append(historyIndexSpace);
		result.append(", objectID: ");
		result.append(objectID);
		result.append(", splitField: ");
		result.append(splitField);
		result.append(", splitNum: ");
		result.append(splitNum);
		result.append(", startData: ");
		result.append(startData);
		result.append(", userSplit: ");
		result.append(userSplit);
		result.append(", isRedu: ");
		result.append(isRedu);
		result.append(", isClear: ");
		result.append(isClear);
		result.append(", fileSpace: ");
		result.append(fileSpace);
		result.append(", fileIndexSpace: ");
		result.append(fileIndexSpace);
		result.append(", clearIndexSpace: ");
		result.append(clearIndexSpace);
		result.append(')');
		return result.toString();
	}

} //TableBasePropertyImpl
