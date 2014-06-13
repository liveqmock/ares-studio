/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.chouse.reference.AddTableModificationReference;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.chouse.AddTableModification;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableKey;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Add Table Modification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableModificationImpl#isNewSelfTable <em>New Self Table</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableModificationImpl#isNewHistoryTable <em>New History Table</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableModificationImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableModificationImpl#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableModificationImpl#getKeys <em>Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AddTableModificationImpl extends ModificationImpl implements AddTableModification {
	/**
	 * The default value of the '{@link #isNewSelfTable() <em>New Self Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewSelfTable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEW_SELF_TABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNewSelfTable() <em>New Self Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewSelfTable()
	 * @generated
	 * @ordered
	 */
	protected boolean newSelfTable = NEW_SELF_TABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isNewHistoryTable() <em>New History Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewHistoryTable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEW_HISTORY_TABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNewHistoryTable() <em>New History Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewHistoryTable()
	 * @generated
	 * @ordered
	 */
	protected boolean newHistoryTable = NEW_HISTORY_TABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getColumns() <em>Columns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumns()
	 * @generated
	 * @ordered
	 */
	protected EList<TableColumn> columns;

	/**
	 * The cached value of the '{@link #getIndexes() <em>Indexes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexes()
	 * @generated
	 * @ordered
	 */
	protected EList<TableIndex> indexes;

	/**
	 * The cached value of the '{@link #getKeys() <em>Keys</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeys()
	 * @generated
	 * @ordered
	 */
	protected EList<TableKey> keys;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AddTableModificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.ADD_TABLE_MODIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNewSelfTable() {
		return newSelfTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewSelfTable(boolean newNewSelfTable) {
		boolean oldNewSelfTable = newSelfTable;
		newSelfTable = newNewSelfTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.ADD_TABLE_MODIFICATION__NEW_SELF_TABLE, oldNewSelfTable, newSelfTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNewHistoryTable() {
		return newHistoryTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewHistoryTable(boolean newNewHistoryTable) {
		boolean oldNewHistoryTable = newHistoryTable;
		newHistoryTable = newNewHistoryTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE, oldNewHistoryTable, newHistoryTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableColumn> getColumns() {
		if (columns == null) {
			columns = new EObjectContainmentEList<TableColumn>(TableColumn.class, this, ChousePackage.ADD_TABLE_MODIFICATION__COLUMNS);
		}
		return columns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableIndex> getIndexes() {
		if (indexes == null) {
			indexes = new EObjectContainmentEList<TableIndex>(TableIndex.class, this, ChousePackage.ADD_TABLE_MODIFICATION__INDEXES);
		}
		return indexes;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableKey> getKeys() {
		if (keys == null) {
			keys = new EObjectContainmentEList<TableKey>(TableKey.class, this, ChousePackage.ADD_TABLE_MODIFICATION__KEYS);
		}
		return keys;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ModificationImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		BasicEList<Reference> references = new BasicEList<Reference>();
		RevisionHistory  revisionHistory =  getRevisionHistoryImpl(this);
		String  version  = StringUtils.EMPTY;
		if(revisionHistory!=null){
			version = revisionHistory.getVersion();
		}
		//如果没有字段,或者版本信息为空则不用引用标准字段信息
		if((this.getColumns().size()>0||this.getIndexes().size()>0) && StringUtils.isNotBlank(version)){
			AddTableModificationReference addTableModificationReference = new AddTableModificationReference(IMetadataRefType.StdField,version,getColumns(),getIndexes());
			references.add(addTableModificationReference);
		}
		return references;
	}
	
	/**
	 * 获得eObject对应的RevisionHistory对象
	 * @param eObject
	 * @return
	 */
	private RevisionHistory getRevisionHistoryImpl(EObject eObject){
		if(eObject instanceof RevisionHistory) return (RevisionHistory)eObject;
		while(eObject!=null && eObject.eContainer()!=null){
			eObject = eObject.eContainer();
			if(eObject instanceof RevisionHistory){
				return (RevisionHistory)eObject;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChousePackage.ADD_TABLE_MODIFICATION__COLUMNS:
				return ((InternalEList<?>)getColumns()).basicRemove(otherEnd, msgs);
			case ChousePackage.ADD_TABLE_MODIFICATION__INDEXES:
				return ((InternalEList<?>)getIndexes()).basicRemove(otherEnd, msgs);
			case ChousePackage.ADD_TABLE_MODIFICATION__KEYS:
				return ((InternalEList<?>)getKeys()).basicRemove(otherEnd, msgs);
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
			case ChousePackage.ADD_TABLE_MODIFICATION__NEW_SELF_TABLE:
				return isNewSelfTable();
			case ChousePackage.ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE:
				return isNewHistoryTable();
			case ChousePackage.ADD_TABLE_MODIFICATION__COLUMNS:
				return getColumns();
			case ChousePackage.ADD_TABLE_MODIFICATION__INDEXES:
				return getIndexes();
			case ChousePackage.ADD_TABLE_MODIFICATION__KEYS:
				return getKeys();
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
			case ChousePackage.ADD_TABLE_MODIFICATION__NEW_SELF_TABLE:
				setNewSelfTable((Boolean)newValue);
				return;
			case ChousePackage.ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE:
				setNewHistoryTable((Boolean)newValue);
				return;
			case ChousePackage.ADD_TABLE_MODIFICATION__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection<? extends TableColumn>)newValue);
				return;
			case ChousePackage.ADD_TABLE_MODIFICATION__INDEXES:
				getIndexes().clear();
				getIndexes().addAll((Collection<? extends TableIndex>)newValue);
				return;
			case ChousePackage.ADD_TABLE_MODIFICATION__KEYS:
				getKeys().clear();
				getKeys().addAll((Collection<? extends TableKey>)newValue);
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
			case ChousePackage.ADD_TABLE_MODIFICATION__NEW_SELF_TABLE:
				setNewSelfTable(NEW_SELF_TABLE_EDEFAULT);
				return;
			case ChousePackage.ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE:
				setNewHistoryTable(NEW_HISTORY_TABLE_EDEFAULT);
				return;
			case ChousePackage.ADD_TABLE_MODIFICATION__COLUMNS:
				getColumns().clear();
				return;
			case ChousePackage.ADD_TABLE_MODIFICATION__INDEXES:
				getIndexes().clear();
				return;
			case ChousePackage.ADD_TABLE_MODIFICATION__KEYS:
				getKeys().clear();
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
			case ChousePackage.ADD_TABLE_MODIFICATION__NEW_SELF_TABLE:
				return newSelfTable != NEW_SELF_TABLE_EDEFAULT;
			case ChousePackage.ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE:
				return newHistoryTable != NEW_HISTORY_TABLE_EDEFAULT;
			case ChousePackage.ADD_TABLE_MODIFICATION__COLUMNS:
				return columns != null && !columns.isEmpty();
			case ChousePackage.ADD_TABLE_MODIFICATION__INDEXES:
				return indexes != null && !indexes.isEmpty();
			case ChousePackage.ADD_TABLE_MODIFICATION__KEYS:
				return keys != null && !keys.isEmpty();
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
		result.append(" (newSelfTable: ");
		result.append(newSelfTable);
		result.append(", newHistoryTable: ");
		result.append(newHistoryTable);
		result.append(')');
		return result.toString();
	}

} //AddTableModificationImpl
