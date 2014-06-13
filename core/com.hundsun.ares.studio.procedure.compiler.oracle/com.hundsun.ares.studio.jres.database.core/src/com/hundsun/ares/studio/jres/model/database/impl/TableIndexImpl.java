/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexImpl#isCluster <em>Cluster</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexImpl#getMark <em>Mark</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableIndexImpl extends ExtensibleModelImpl implements TableIndex {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNIQUE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean unique = UNIQUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isCluster() <em>Cluster</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCluster()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLUSTER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCluster() <em>Cluster</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCluster()
	 * @generated
	 * @ordered
	 */
	protected boolean cluster = CLUSTER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getColumns() <em>Columns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumns()
	 * @generated
	 * @ordered
	 */
	protected EList<TableIndexColumn> columns;

	/**
	 * The default value of the '{@link #getMark() <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMark()
	 * @generated
	 * @ordered
	 */
	protected static final String MARK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMark() <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMark()
	 * @generated
	 * @ordered
	 */
	protected String mark = MARK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableIndexImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatabasePackage.Literals.TABLE_INDEX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_INDEX__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnique() {
		return unique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnique(boolean newUnique) {
		boolean oldUnique = unique;
		unique = newUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_INDEX__UNIQUE, oldUnique, unique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCluster() {
		return cluster;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCluster(boolean newCluster) {
		boolean oldCluster = cluster;
		cluster = newCluster;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_INDEX__CLUSTER, oldCluster, cluster));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableIndexColumn> getColumns() {
		if (columns == null) {
			columns = new EObjectContainmentEList<TableIndexColumn>(TableIndexColumn.class, this, DatabasePackage.TABLE_INDEX__COLUMNS);
		}
		return columns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMark(String newMark) {
		String oldMark = mark;
		mark = newMark;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_INDEX__MARK, oldMark, mark));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatabasePackage.TABLE_INDEX__COLUMNS:
				return ((InternalEList<?>)getColumns()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabasePackage.TABLE_INDEX__NAME:
				return getName();
			case DatabasePackage.TABLE_INDEX__UNIQUE:
				return isUnique();
			case DatabasePackage.TABLE_INDEX__CLUSTER:
				return isCluster();
			case DatabasePackage.TABLE_INDEX__COLUMNS:
				return getColumns();
			case DatabasePackage.TABLE_INDEX__MARK:
				return getMark();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DatabasePackage.TABLE_INDEX__NAME:
				setName((String)newValue);
				return;
			case DatabasePackage.TABLE_INDEX__UNIQUE:
				setUnique((Boolean)newValue);
				return;
			case DatabasePackage.TABLE_INDEX__CLUSTER:
				setCluster((Boolean)newValue);
				return;
			case DatabasePackage.TABLE_INDEX__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection<? extends TableIndexColumn>)newValue);
				return;
			case DatabasePackage.TABLE_INDEX__MARK:
				setMark((String)newValue);
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
			case DatabasePackage.TABLE_INDEX__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DatabasePackage.TABLE_INDEX__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case DatabasePackage.TABLE_INDEX__CLUSTER:
				setCluster(CLUSTER_EDEFAULT);
				return;
			case DatabasePackage.TABLE_INDEX__COLUMNS:
				getColumns().clear();
				return;
			case DatabasePackage.TABLE_INDEX__MARK:
				setMark(MARK_EDEFAULT);
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
			case DatabasePackage.TABLE_INDEX__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DatabasePackage.TABLE_INDEX__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case DatabasePackage.TABLE_INDEX__CLUSTER:
				return cluster != CLUSTER_EDEFAULT;
			case DatabasePackage.TABLE_INDEX__COLUMNS:
				return columns != null && !columns.isEmpty();
			case DatabasePackage.TABLE_INDEX__MARK:
				return MARK_EDEFAULT == null ? mark != null : !MARK_EDEFAULT.equals(mark);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", unique: ");
		result.append(unique);
		result.append(", cluster: ");
		result.append(cluster);
		result.append(", mark: ");
		result.append(mark);
		result.append(')');
		return result.toString();
	}

} //TableIndexImpl
