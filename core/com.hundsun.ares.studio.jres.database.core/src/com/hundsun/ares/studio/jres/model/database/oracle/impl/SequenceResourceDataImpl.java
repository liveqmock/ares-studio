/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.model.database.impl.DatabaseResourceDataImpl;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence Resource Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl#getTableName <em>Table Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl#getStart <em>Start</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl#getIncrement <em>Increment</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl#getMinValue <em>Min Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl#getMaxValue <em>Max Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl#isCycle <em>Cycle</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl#getCache <em>Cache</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl#isUseCache <em>Use Cache</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl#isIsHistory <em>Is History</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SequenceResourceDataImpl extends DatabaseResourceDataImpl implements SequenceResourceData {
	/**
	 * The default value of the '{@link #getTableName() <em>Table Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTableName()
	 * @generated
	 * @ordered
	 */
	protected static final String TABLE_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTableName() <em>Table Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTableName()
	 * @generated
	 * @ordered
	 */
	protected String tableName = TABLE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final String START_EDEFAULT = "1";

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected String start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getIncrement() <em>Increment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncrement()
	 * @generated
	 * @ordered
	 */
	protected static final String INCREMENT_EDEFAULT = "1";

	/**
	 * The cached value of the '{@link #getIncrement() <em>Increment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncrement()
	 * @generated
	 * @ordered
	 */
	protected String increment = INCREMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinValue() <em>Min Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinValue()
	 * @generated
	 * @ordered
	 */
	protected static final String MIN_VALUE_EDEFAULT = "1";

	/**
	 * The cached value of the '{@link #getMinValue() <em>Min Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinValue()
	 * @generated
	 * @ordered
	 */
	protected String minValue = MIN_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxValue() <em>Max Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxValue()
	 * @generated
	 * @ordered
	 */
	protected static final String MAX_VALUE_EDEFAULT = "999999999999999999999999999";

	/**
	 * The cached value of the '{@link #getMaxValue() <em>Max Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxValue()
	 * @generated
	 * @ordered
	 */
	protected String maxValue = MAX_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isCycle() <em>Cycle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCycle()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CYCLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isCycle() <em>Cycle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCycle()
	 * @generated
	 * @ordered
	 */
	protected boolean cycle = CYCLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCache() <em>Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCache()
	 * @generated
	 * @ordered
	 */
	protected static final String CACHE_EDEFAULT = "20";

	/**
	 * The cached value of the '{@link #getCache() <em>Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCache()
	 * @generated
	 * @ordered
	 */
	protected String cache = CACHE_EDEFAULT;

	/**
	 * The default value of the '{@link #isUseCache() <em>Use Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseCache()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USE_CACHE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isUseCache() <em>Use Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseCache()
	 * @generated
	 * @ordered
	 */
	protected boolean useCache = USE_CACHE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsHistory() <em>Is History</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsHistory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_HISTORY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsHistory() <em>Is History</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsHistory()
	 * @generated
	 * @ordered
	 */
	protected boolean isHistory = IS_HISTORY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceResourceDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OraclePackage.Literals.SEQUENCE_RESOURCE_DATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setTableName(String newTableName) {
		String oldTableName = tableName;
		tableName = newTableName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.SEQUENCE_RESOURCE_DATA__TABLE_NAME, oldTableName, tableName));
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStart(String newStart) {
		String oldStart = start;
		start = newStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.SEQUENCE_RESOURCE_DATA__START, oldStart, start));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIncrement() {
		return increment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncrement(String newIncrement) {
		String oldIncrement = increment;
		increment = newIncrement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.SEQUENCE_RESOURCE_DATA__INCREMENT, oldIncrement, increment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMinValue() {
		return minValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinValue(String newMinValue) {
		String oldMinValue = minValue;
		minValue = newMinValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.SEQUENCE_RESOURCE_DATA__MIN_VALUE, oldMinValue, minValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaxValue() {
		return maxValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxValue(String newMaxValue) {
		String oldMaxValue = maxValue;
		maxValue = newMaxValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.SEQUENCE_RESOURCE_DATA__MAX_VALUE, oldMaxValue, maxValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCycle() {
		return cycle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCycle(boolean newCycle) {
		boolean oldCycle = cycle;
		cycle = newCycle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.SEQUENCE_RESOURCE_DATA__CYCLE, oldCycle, cycle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCache() {
		return cache;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCache(String newCache) {
		String oldCache = cache;
		cache = newCache;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.SEQUENCE_RESOURCE_DATA__CACHE, oldCache, cache));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUseCache() {
		return useCache;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUseCache(boolean newUseCache) {
		boolean oldUseCache = useCache;
		useCache = newUseCache;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.SEQUENCE_RESOURCE_DATA__USE_CACHE, oldUseCache, useCache));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsHistory() {
		return isHistory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsHistory(boolean newIsHistory) {
		boolean oldIsHistory = isHistory;
		isHistory = newIsHistory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.SEQUENCE_RESOURCE_DATA__IS_HISTORY, oldIsHistory, isHistory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OraclePackage.SEQUENCE_RESOURCE_DATA__TABLE_NAME:
				return getTableName();
			case OraclePackage.SEQUENCE_RESOURCE_DATA__START:
				return getStart();
			case OraclePackage.SEQUENCE_RESOURCE_DATA__INCREMENT:
				return getIncrement();
			case OraclePackage.SEQUENCE_RESOURCE_DATA__MIN_VALUE:
				return getMinValue();
			case OraclePackage.SEQUENCE_RESOURCE_DATA__MAX_VALUE:
				return getMaxValue();
			case OraclePackage.SEQUENCE_RESOURCE_DATA__CYCLE:
				return isCycle();
			case OraclePackage.SEQUENCE_RESOURCE_DATA__CACHE:
				return getCache();
			case OraclePackage.SEQUENCE_RESOURCE_DATA__USE_CACHE:
				return isUseCache();
			case OraclePackage.SEQUENCE_RESOURCE_DATA__IS_HISTORY:
				return isIsHistory();
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
			case OraclePackage.SEQUENCE_RESOURCE_DATA__TABLE_NAME:
				setTableName((String)newValue);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__START:
				setStart((String)newValue);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__INCREMENT:
				setIncrement((String)newValue);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__MIN_VALUE:
				setMinValue((String)newValue);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__MAX_VALUE:
				setMaxValue((String)newValue);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__CYCLE:
				setCycle((Boolean)newValue);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__CACHE:
				setCache((String)newValue);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__USE_CACHE:
				setUseCache((Boolean)newValue);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__IS_HISTORY:
				setIsHistory((Boolean)newValue);
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
			case OraclePackage.SEQUENCE_RESOURCE_DATA__TABLE_NAME:
				setTableName(TABLE_NAME_EDEFAULT);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__START:
				setStart(START_EDEFAULT);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__INCREMENT:
				setIncrement(INCREMENT_EDEFAULT);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__MIN_VALUE:
				setMinValue(MIN_VALUE_EDEFAULT);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__MAX_VALUE:
				setMaxValue(MAX_VALUE_EDEFAULT);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__CYCLE:
				setCycle(CYCLE_EDEFAULT);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__CACHE:
				setCache(CACHE_EDEFAULT);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__USE_CACHE:
				setUseCache(USE_CACHE_EDEFAULT);
				return;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__IS_HISTORY:
				setIsHistory(IS_HISTORY_EDEFAULT);
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
			case OraclePackage.SEQUENCE_RESOURCE_DATA__TABLE_NAME:
				return TABLE_NAME_EDEFAULT == null ? tableName != null : !TABLE_NAME_EDEFAULT.equals(tableName);
			case OraclePackage.SEQUENCE_RESOURCE_DATA__START:
				return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
			case OraclePackage.SEQUENCE_RESOURCE_DATA__INCREMENT:
				return INCREMENT_EDEFAULT == null ? increment != null : !INCREMENT_EDEFAULT.equals(increment);
			case OraclePackage.SEQUENCE_RESOURCE_DATA__MIN_VALUE:
				return MIN_VALUE_EDEFAULT == null ? minValue != null : !MIN_VALUE_EDEFAULT.equals(minValue);
			case OraclePackage.SEQUENCE_RESOURCE_DATA__MAX_VALUE:
				return MAX_VALUE_EDEFAULT == null ? maxValue != null : !MAX_VALUE_EDEFAULT.equals(maxValue);
			case OraclePackage.SEQUENCE_RESOURCE_DATA__CYCLE:
				return cycle != CYCLE_EDEFAULT;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__CACHE:
				return CACHE_EDEFAULT == null ? cache != null : !CACHE_EDEFAULT.equals(cache);
			case OraclePackage.SEQUENCE_RESOURCE_DATA__USE_CACHE:
				return useCache != USE_CACHE_EDEFAULT;
			case OraclePackage.SEQUENCE_RESOURCE_DATA__IS_HISTORY:
				return isHistory != IS_HISTORY_EDEFAULT;
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
		result.append(" (tableName: ");
		result.append(tableName);
		result.append(", start: ");
		result.append(start);
		result.append(", increment: ");
		result.append(increment);
		result.append(", minValue: ");
		result.append(minValue);
		result.append(", maxValue: ");
		result.append(maxValue);
		result.append(", cycle: ");
		result.append(cycle);
		result.append(", cache: ");
		result.append(cache);
		result.append(", useCache: ");
		result.append(useCache);
		result.append(", isHistory: ");
		result.append(isHistory);
		result.append(')');
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.JRESResourceInfoImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		// TODO 引用数据库表
		
		EList<Reference> references = new BasicEList<Reference>();
			if(getTableName() != null && StringUtils.isNotBlank(getTableName())){
				Reference tableName = new TextAttributeReferenceWithNamespaceImpl(IDatabaseRefType.Table, 
						this, 
						CorePackage.Literals.BASIC_RESOURCE_INFO__NAME);
						
				references.add(tableName);
			}
		return references;
	}

} //SequenceResourceDataImpl
