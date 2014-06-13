/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Resource Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableResourceDataImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableResourceDataImpl#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableResourceDataImpl#getKeys <em>Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableResourceDataImpl extends DatabaseResourceDataImpl implements TableResourceData {
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
	public TableResourceDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatabasePackage.Literals.TABLE_RESOURCE_DATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableColumn> getColumns() {
		if (columns == null) {
			columns = new EObjectContainmentEList<TableColumn>(TableColumn.class, this, DatabasePackage.TABLE_RESOURCE_DATA__COLUMNS);
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
			indexes = new EObjectContainmentEList<TableIndex>(TableIndex.class, this, DatabasePackage.TABLE_RESOURCE_DATA__INDEXES);
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
			keys = new EObjectContainmentEList<TableKey>(TableKey.class, this, DatabasePackage.TABLE_RESOURCE_DATA__KEYS);
		}
		return keys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatabasePackage.TABLE_RESOURCE_DATA__COLUMNS:
				return ((InternalEList<?>)getColumns()).basicRemove(otherEnd, msgs);
			case DatabasePackage.TABLE_RESOURCE_DATA__INDEXES:
				return ((InternalEList<?>)getIndexes()).basicRemove(otherEnd, msgs);
			case DatabasePackage.TABLE_RESOURCE_DATA__KEYS:
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
			case DatabasePackage.TABLE_RESOURCE_DATA__COLUMNS:
				return getColumns();
			case DatabasePackage.TABLE_RESOURCE_DATA__INDEXES:
				return getIndexes();
			case DatabasePackage.TABLE_RESOURCE_DATA__KEYS:
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
			case DatabasePackage.TABLE_RESOURCE_DATA__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection<? extends TableColumn>)newValue);
				return;
			case DatabasePackage.TABLE_RESOURCE_DATA__INDEXES:
				getIndexes().clear();
				getIndexes().addAll((Collection<? extends TableIndex>)newValue);
				return;
			case DatabasePackage.TABLE_RESOURCE_DATA__KEYS:
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
			case DatabasePackage.TABLE_RESOURCE_DATA__COLUMNS:
				getColumns().clear();
				return;
			case DatabasePackage.TABLE_RESOURCE_DATA__INDEXES:
				getIndexes().clear();
				return;
			case DatabasePackage.TABLE_RESOURCE_DATA__KEYS:
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
			case DatabasePackage.TABLE_RESOURCE_DATA__COLUMNS:
				return columns != null && !columns.isEmpty();
			case DatabasePackage.TABLE_RESOURCE_DATA__INDEXES:
				return indexes != null && !indexes.isEmpty();
			case DatabasePackage.TABLE_RESOURCE_DATA__KEYS:
				return keys != null && !keys.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.JRESResourceInfoImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		// TODO 1、引用标准字段       
		// TODO 扩展属性表空间信息引用///////////未添加
		
		EList<Reference> references=new BasicEList<Reference>();
		//表字段
		references.addAll(getColumnRef(getColumns()));
		//表索引
		for (TableIndex index : getIndexes()) {
			references.addAll(getColumnIndexRef(index.getColumns()));
		}
		//修订记录的扩展属性表空间
		for (Iterator<EObject> it = EcoreUtil.getAllContents(this, true);it.hasNext();) {
			EObject obj = it.next();
			if (obj instanceof IReferenceProvider) {
				references.addAll(((IReferenceProvider)obj).getReferences());
			}
		}
 		return references;
	}
	
	private List<Reference> getColumnRef (EList<TableColumn> columns){
		EList<Reference> references=new BasicEList<Reference>();
		for(TableColumn data : columns){
			if(data.getFieldName() != null && StringUtils.isNotBlank(data.getFieldName())){
				Reference stdName = new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdField, 
						data, 
						MetadataPackage.Literals.NAMED_ELEMENT__NAME);
				references.add(stdName);
			}
		}
		return references;
	}
	
	private List<Reference> getColumnIndexRef (EList<TableIndexColumn> columns){
		EList<Reference> references=new BasicEList<Reference>();
		for(TableIndexColumn index : columns){
			if (StringUtils.isNotBlank(index.getColumnName())) {
				Reference stdName = new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdField, 
						index, 
						DatabasePackage.Literals.TABLE_INDEX__NAME);
				references.add(stdName);
			}
		}
		return references;
	}
	
} //TableResourceDataImpl
