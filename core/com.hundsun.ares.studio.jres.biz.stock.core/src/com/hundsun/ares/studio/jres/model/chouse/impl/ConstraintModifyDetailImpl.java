/**
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail;

import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.key_type;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint Modify Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.ConstraintModifyDetailImpl#getMark <em>Mark</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.ConstraintModifyDetailImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.ConstraintModifyDetailImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.ConstraintModifyDetailImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.ConstraintModifyDetailImpl#getForeignKey <em>Foreign Key</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintModifyDetailImpl extends EObjectImpl implements ConstraintModifyDetail {
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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

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
	 * The cached value of the '{@link #getColumns() <em>Columns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumns()
	 * @generated
	 * @ordered
	 */
	protected EList<TableColumn> columns;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final key_type TYPE_EDEFAULT = key_type.PRIMARY;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected key_type type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getForeignKey() <em>Foreign Key</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeignKey()
	 * @generated
	 * @ordered
	 */
	protected EList<ForeignKey> foreignKey;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintModifyDetailImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.CONSTRAINT_MODIFY_DETAIL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.CONSTRAINT_MODIFY_DETAIL__MARK, oldMark, mark));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.CONSTRAINT_MODIFY_DETAIL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableColumn> getColumns() {
		if (columns == null) {
			columns = new EObjectContainmentEList<TableColumn>(TableColumn.class, this, ChousePackage.CONSTRAINT_MODIFY_DETAIL__COLUMNS);
		}
		return columns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public key_type getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(key_type newType) {
		key_type oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.CONSTRAINT_MODIFY_DETAIL__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ForeignKey> getForeignKey() {
		if (foreignKey == null) {
			foreignKey = new EObjectContainmentEList<ForeignKey>(ForeignKey.class, this, ChousePackage.CONSTRAINT_MODIFY_DETAIL__FOREIGN_KEY);
		}
		return foreignKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__COLUMNS:
				return ((InternalEList<?>)getColumns()).basicRemove(otherEnd, msgs);
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__FOREIGN_KEY:
				return ((InternalEList<?>)getForeignKey()).basicRemove(otherEnd, msgs);
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
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__MARK:
				return getMark();
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__NAME:
				return getName();
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__COLUMNS:
				return getColumns();
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__TYPE:
				return getType();
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__FOREIGN_KEY:
				return getForeignKey();
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
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__MARK:
				setMark((String)newValue);
				return;
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__NAME:
				setName((String)newValue);
				return;
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection<? extends TableColumn>)newValue);
				return;
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__TYPE:
				setType((key_type)newValue);
				return;
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__FOREIGN_KEY:
				getForeignKey().clear();
				getForeignKey().addAll((Collection<? extends ForeignKey>)newValue);
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
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__MARK:
				setMark(MARK_EDEFAULT);
				return;
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__COLUMNS:
				getColumns().clear();
				return;
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__FOREIGN_KEY:
				getForeignKey().clear();
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
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__MARK:
				return MARK_EDEFAULT == null ? mark != null : !MARK_EDEFAULT.equals(mark);
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__COLUMNS:
				return columns != null && !columns.isEmpty();
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__TYPE:
				return type != TYPE_EDEFAULT;
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL__FOREIGN_KEY:
				return foreignKey != null && !foreignKey.isEmpty();
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
		result.append(" (mark: ");
		result.append(mark);
		result.append(", name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //ConstraintModifyDetailImpl
