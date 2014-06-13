/**
 * 源程序名称：MetadataCategoryImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl#getItems <em>Items</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetadataCategoryImpl extends NamedElementImpl implements MetadataCategory {
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<MetadataCategory> children;

	/**
	 * The cached value of the '{@link #getItems() <em>Items</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItems()
	 * @generated
	 * @ordered
	 */
	protected EList<MetadataItem> items;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataCategoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.METADATA_CATEGORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetadataCategory> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<MetadataCategory>(MetadataCategory.class, this, MetadataPackage.METADATA_CATEGORY__CHILDREN, MetadataPackage.METADATA_CATEGORY__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetadataItem> getItems() {
		if (items == null) {
			items = new EObjectResolvingEList<MetadataItem>(MetadataItem.class, this, MetadataPackage.METADATA_CATEGORY__ITEMS);
		}
		return items;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataCategory getParent() {
		if (eContainerFeatureID() != MetadataPackage.METADATA_CATEGORY__PARENT) return null;
		return (MetadataCategory)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(MetadataCategory newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, MetadataPackage.METADATA_CATEGORY__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(MetadataCategory newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != MetadataPackage.METADATA_CATEGORY__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, MetadataPackage.METADATA_CATEGORY__CHILDREN, MetadataCategory.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.METADATA_CATEGORY__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetadataPackage.METADATA_CATEGORY__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
			case MetadataPackage.METADATA_CATEGORY__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((MetadataCategory)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetadataPackage.METADATA_CATEGORY__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case MetadataPackage.METADATA_CATEGORY__PARENT:
				return basicSetParent(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case MetadataPackage.METADATA_CATEGORY__PARENT:
				return eInternalContainer().eInverseRemove(this, MetadataPackage.METADATA_CATEGORY__CHILDREN, MetadataCategory.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.METADATA_CATEGORY__CHILDREN:
				return getChildren();
			case MetadataPackage.METADATA_CATEGORY__ITEMS:
				return getItems();
			case MetadataPackage.METADATA_CATEGORY__PARENT:
				return getParent();
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
			case MetadataPackage.METADATA_CATEGORY__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends MetadataCategory>)newValue);
				return;
			case MetadataPackage.METADATA_CATEGORY__ITEMS:
				getItems().clear();
				getItems().addAll((Collection<? extends MetadataItem>)newValue);
				return;
			case MetadataPackage.METADATA_CATEGORY__PARENT:
				setParent((MetadataCategory)newValue);
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
			case MetadataPackage.METADATA_CATEGORY__CHILDREN:
				getChildren().clear();
				return;
			case MetadataPackage.METADATA_CATEGORY__ITEMS:
				getItems().clear();
				return;
			case MetadataPackage.METADATA_CATEGORY__PARENT:
				setParent((MetadataCategory)null);
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
			case MetadataPackage.METADATA_CATEGORY__CHILDREN:
				return children != null && !children.isEmpty();
			case MetadataPackage.METADATA_CATEGORY__ITEMS:
				return items != null && !items.isEmpty();
			case MetadataPackage.METADATA_CATEGORY__PARENT:
				return getParent() != null;
		}
		return super.eIsSet(featureID);
	}

} //MetadataCategoryImpl
