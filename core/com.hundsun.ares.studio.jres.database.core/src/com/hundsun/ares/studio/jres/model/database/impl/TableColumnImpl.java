/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.impl;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#isPrimaryKey <em>Primary Key</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#isNullable <em>Nullable</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getForeignkey <em>Foreignkey</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getMark <em>Mark</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl#getColumnType <em>Column Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableColumnImpl extends ExtensibleModelImpl implements TableColumn {
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
	 * The default value of the '{@link #getChineseName() <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChineseName()
	 * @generated
	 * @ordered
	 */
	protected static final String CHINESE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChineseName() <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChineseName()
	 * @generated
	 * @ordered
	 */
	protected String chineseName = CHINESE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected String dataType = DATA_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getColumnName() <em>Column Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnName()
	 * @generated
	 * @ordered
	 */
	protected static final String COLUMN_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getColumnName() <em>Column Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnName()
	 * @generated
	 * @ordered
	 */
	protected String columnName = COLUMN_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFieldName() <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldName()
	 * @generated
	 * @ordered
	 */
	protected static final String FIELD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldName() <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldName()
	 * @generated
	 * @ordered
	 */
	protected String fieldName = FIELD_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isPrimaryKey() <em>Primary Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrimaryKey()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRIMARY_KEY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrimaryKey() <em>Primary Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrimaryKey()
	 * @generated
	 * @ordered
	 */
	protected boolean primaryKey = PRIMARY_KEY_EDEFAULT;

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
	 * The default value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NULLABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullable()
	 * @generated
	 * @ordered
	 */
	protected boolean nullable = NULLABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_VALUE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected String defaultValue = DEFAULT_VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getForeignkey() <em>Foreignkey</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeignkey()
	 * @generated
	 * @ordered
	 */
	protected EList<ForeignKey> foreignkey;

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
	 * The default value of the '{@link #getComments() <em>Comments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected String comments = COMMENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getColumnType() <em>Column Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnType()
	 * @generated
	 * @ordered
	 */
	protected static final ColumnType COLUMN_TYPE_EDEFAULT = ColumnType.STD_FIELD;

	/**
	 * The cached value of the '{@link #getColumnType() <em>Column Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnType()
	 * @generated
	 * @ordered
	 */
	protected ColumnType columnType = COLUMN_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatabasePackage.Literals.TABLE_COLUMN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		if (StringUtils.isBlank(getColumnName())) {
			return getFieldName();
		}
		return getColumnName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setName(String newName) {
		setFieldName(newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChineseName(String newChineseName) {
		String oldChineseName = chineseName;
		chineseName = newChineseName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__CHINESE_NAME, oldChineseName, chineseName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataType(String newDataType) {
		String oldDataType = dataType;
		dataType = newDataType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__DATA_TYPE, oldDataType, dataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumnName(String newColumnName) {
		String oldColumnName = columnName;
		columnName = newColumnName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__COLUMN_NAME, oldColumnName, columnName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldName(String newFieldName) {
		String oldFieldName = fieldName;
		fieldName = newFieldName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__FIELD_NAME, oldFieldName, fieldName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimaryKey(boolean newPrimaryKey) {
		boolean oldPrimaryKey = primaryKey;
		primaryKey = newPrimaryKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__PRIMARY_KEY, oldPrimaryKey, primaryKey));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__UNIQUE, oldUnique, unique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNullable() {
		return nullable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullable(boolean newNullable) {
		boolean oldNullable = nullable;
		nullable = newNullable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__NULLABLE, oldNullable, nullable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(String newDefaultValue) {
		String oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__DEFAULT_VALUE, oldDefaultValue, defaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ForeignKey> getForeignkey() {
		if (foreignkey == null) {
			foreignkey = new EObjectContainmentEList<ForeignKey>(ForeignKey.class, this, DatabasePackage.TABLE_COLUMN__FOREIGNKEY);
		}
		return foreignkey;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__MARK, oldMark, mark));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComments(String newComments) {
		String oldComments = comments;
		comments = newComments;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__COMMENTS, oldComments, comments));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColumnType getColumnType() {
		return columnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumnType(ColumnType newColumnType) {
		ColumnType oldColumnType = columnType;
		columnType = newColumnType == null ? COLUMN_TYPE_EDEFAULT : newColumnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_COLUMN__COLUMN_TYPE, oldColumnType, columnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatabasePackage.TABLE_COLUMN__FOREIGNKEY:
				return ((InternalEList<?>)getForeignkey()).basicRemove(otherEnd, msgs);
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
			case DatabasePackage.TABLE_COLUMN__NAME:
				return getName();
			case DatabasePackage.TABLE_COLUMN__CHINESE_NAME:
				return getChineseName();
			case DatabasePackage.TABLE_COLUMN__DESCRIPTION:
				return getDescription();
			case DatabasePackage.TABLE_COLUMN__DATA_TYPE:
				return getDataType();
			case DatabasePackage.TABLE_COLUMN__COLUMN_NAME:
				return getColumnName();
			case DatabasePackage.TABLE_COLUMN__FIELD_NAME:
				return getFieldName();
			case DatabasePackage.TABLE_COLUMN__PRIMARY_KEY:
				return isPrimaryKey();
			case DatabasePackage.TABLE_COLUMN__UNIQUE:
				return isUnique();
			case DatabasePackage.TABLE_COLUMN__NULLABLE:
				return isNullable();
			case DatabasePackage.TABLE_COLUMN__DEFAULT_VALUE:
				return getDefaultValue();
			case DatabasePackage.TABLE_COLUMN__FOREIGNKEY:
				return getForeignkey();
			case DatabasePackage.TABLE_COLUMN__MARK:
				return getMark();
			case DatabasePackage.TABLE_COLUMN__COMMENTS:
				return getComments();
			case DatabasePackage.TABLE_COLUMN__COLUMN_TYPE:
				return getColumnType();
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
			case DatabasePackage.TABLE_COLUMN__NAME:
				setName((String)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__CHINESE_NAME:
				setChineseName((String)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__DATA_TYPE:
				setDataType((String)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__COLUMN_NAME:
				setColumnName((String)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__FIELD_NAME:
				setFieldName((String)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__PRIMARY_KEY:
				setPrimaryKey((Boolean)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__UNIQUE:
				setUnique((Boolean)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__NULLABLE:
				setNullable((Boolean)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__DEFAULT_VALUE:
				setDefaultValue((String)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__FOREIGNKEY:
				getForeignkey().clear();
				getForeignkey().addAll((Collection<? extends ForeignKey>)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__MARK:
				setMark((String)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__COMMENTS:
				setComments((String)newValue);
				return;
			case DatabasePackage.TABLE_COLUMN__COLUMN_TYPE:
				setColumnType((ColumnType)newValue);
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
			case DatabasePackage.TABLE_COLUMN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__CHINESE_NAME:
				setChineseName(CHINESE_NAME_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__DATA_TYPE:
				setDataType(DATA_TYPE_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__COLUMN_NAME:
				setColumnName(COLUMN_NAME_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__FIELD_NAME:
				setFieldName(FIELD_NAME_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__PRIMARY_KEY:
				setPrimaryKey(PRIMARY_KEY_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__NULLABLE:
				setNullable(NULLABLE_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__DEFAULT_VALUE:
				setDefaultValue(DEFAULT_VALUE_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__FOREIGNKEY:
				getForeignkey().clear();
				return;
			case DatabasePackage.TABLE_COLUMN__MARK:
				setMark(MARK_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__COMMENTS:
				setComments(COMMENTS_EDEFAULT);
				return;
			case DatabasePackage.TABLE_COLUMN__COLUMN_TYPE:
				setColumnType(COLUMN_TYPE_EDEFAULT);
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
			case DatabasePackage.TABLE_COLUMN__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case DatabasePackage.TABLE_COLUMN__CHINESE_NAME:
				return CHINESE_NAME_EDEFAULT == null ? chineseName != null : !CHINESE_NAME_EDEFAULT.equals(chineseName);
			case DatabasePackage.TABLE_COLUMN__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case DatabasePackage.TABLE_COLUMN__DATA_TYPE:
				return DATA_TYPE_EDEFAULT == null ? dataType != null : !DATA_TYPE_EDEFAULT.equals(dataType);
			case DatabasePackage.TABLE_COLUMN__COLUMN_NAME:
				return COLUMN_NAME_EDEFAULT == null ? columnName != null : !COLUMN_NAME_EDEFAULT.equals(columnName);
			case DatabasePackage.TABLE_COLUMN__FIELD_NAME:
				return FIELD_NAME_EDEFAULT == null ? fieldName != null : !FIELD_NAME_EDEFAULT.equals(fieldName);
			case DatabasePackage.TABLE_COLUMN__PRIMARY_KEY:
				return primaryKey != PRIMARY_KEY_EDEFAULT;
			case DatabasePackage.TABLE_COLUMN__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case DatabasePackage.TABLE_COLUMN__NULLABLE:
				return nullable != NULLABLE_EDEFAULT;
			case DatabasePackage.TABLE_COLUMN__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
			case DatabasePackage.TABLE_COLUMN__FOREIGNKEY:
				return foreignkey != null && !foreignkey.isEmpty();
			case DatabasePackage.TABLE_COLUMN__MARK:
				return MARK_EDEFAULT == null ? mark != null : !MARK_EDEFAULT.equals(mark);
			case DatabasePackage.TABLE_COLUMN__COMMENTS:
				return COMMENTS_EDEFAULT == null ? comments != null : !COMMENTS_EDEFAULT.equals(comments);
			case DatabasePackage.TABLE_COLUMN__COLUMN_TYPE:
				return columnType != COLUMN_TYPE_EDEFAULT;
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
		result.append(" (chineseName: ");
		result.append(chineseName);
		result.append(", description: ");
		result.append(description);
		result.append(", dataType: ");
		result.append(dataType);
		result.append(", columnName: ");
		result.append(columnName);
		result.append(", fieldName: ");
		result.append(fieldName);
		result.append(", primaryKey: ");
		result.append(primaryKey);
		result.append(", unique: ");
		result.append(unique);
		result.append(", nullable: ");
		result.append(nullable);
		result.append(", defaultValue: ");
		result.append(defaultValue);
		result.append(", mark: ");
		result.append(mark);
		result.append(", comments: ");
		result.append(comments);
		result.append(", columnType: ");
		result.append(columnType);
		result.append(')');
		return result.toString();
	}

} //TableColumnImpl
