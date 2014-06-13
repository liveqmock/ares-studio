/**
 */
package com.hundsun.ares.studio.atom.provider;


import com.hundsun.ares.studio.atom.AtomFactory;
import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomPackage;

import com.hundsun.ares.studio.biz.BizFactory;
import com.hundsun.ares.studio.biz.BizPackage;

import com.hundsun.ares.studio.biz.provider.BizInterfaceItemProvider;

import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.CorePackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.hundsun.ares.studio.atom.AtomFunction} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AtomFunctionItemProvider
	extends BizInterfaceItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomFunctionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addChineseNamePropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addObjectIdPropertyDescriptor(object);
			addGroupPropertyDescriptor(object);
			addFullyQualifiedNamePropertyDescriptor(object);
			addDatabasePropertyDescriptor(object);
			addPseudoCodePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BasicResourceInfo_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BasicResourceInfo_name_feature", "_UI_BasicResourceInfo_type"),
				 CorePackage.Literals.BASIC_RESOURCE_INFO__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Chinese Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addChineseNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BasicResourceInfo_chineseName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BasicResourceInfo_chineseName_feature", "_UI_BasicResourceInfo_type"),
				 CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BasicResourceInfo_description_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BasicResourceInfo_description_feature", "_UI_BasicResourceInfo_type"),
				 CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Object Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addObjectIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BasicResourceInfo_objectId_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BasicResourceInfo_objectId_feature", "_UI_BasicResourceInfo_type"),
				 CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Group feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGroupPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BasicResourceInfo_group_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BasicResourceInfo_group_feature", "_UI_BasicResourceInfo_type"),
				 CorePackage.Literals.BASIC_RESOURCE_INFO__GROUP,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Fully Qualified Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFullyQualifiedNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_JRESResourceInfo_fullyQualifiedName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_JRESResourceInfo_fullyQualifiedName_feature", "_UI_JRESResourceInfo_type"),
				 CorePackage.Literals.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Database feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDatabasePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AtomFunction_database_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AtomFunction_database_feature", "_UI_AtomFunction_type"),
				 AtomPackage.Literals.ATOM_FUNCTION__DATABASE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Pseudo Code feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPseudoCodePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AtomFunction_pseudoCode_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AtomFunction_pseudoCode_feature", "_UI_AtomFunction_type"),
				 AtomPackage.Literals.ATOM_FUNCTION__PSEUDO_CODE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(CorePackage.Literals.EXTENSIBLE_MODEL__DATA);
			childrenFeatures.add(CorePackage.Literals.EXTENSIBLE_MODEL__DATA2);
			childrenFeatures.add(CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES);
			childrenFeatures.add(AtomPackage.Literals.ATOM_FUNCTION__INTERNAL_VARIABLES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns AtomFunction.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AtomFunction"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AtomFunction)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AtomFunction_type") :
			getString("_UI_AtomFunction_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(AtomFunction.class)) {
			case AtomPackage.ATOM_FUNCTION__NAME:
			case AtomPackage.ATOM_FUNCTION__CHINESE_NAME:
			case AtomPackage.ATOM_FUNCTION__DESCRIPTION:
			case AtomPackage.ATOM_FUNCTION__OBJECT_ID:
			case AtomPackage.ATOM_FUNCTION__GROUP:
			case AtomPackage.ATOM_FUNCTION__FULLY_QUALIFIED_NAME:
			case AtomPackage.ATOM_FUNCTION__DATABASE:
			case AtomPackage.ATOM_FUNCTION__PSEUDO_CODE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case AtomPackage.ATOM_FUNCTION__DATA:
			case AtomPackage.ATOM_FUNCTION__DATA2:
			case AtomPackage.ATOM_FUNCTION__HISTORIES:
			case AtomPackage.ATOM_FUNCTION__INTERNAL_VARIABLES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(BizPackage.Literals.BIZ_INTERFACE__INPUT_PARAMETERS,
				 AtomFactory.eINSTANCE.createInternalParam()));

		newChildDescriptors.add
			(createChildParameter
				(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_PARAMETERS,
				 AtomFactory.eINSTANCE.createInternalParam()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.EXTENSIBLE_MODEL__DATA2,
				 CoreFactory.eINSTANCE.create(CorePackage.Literals.ESTRING_TO_EOBJECT_MAP_ENTRY)));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES,
				 CoreFactory.eINSTANCE.createRevisionHistory()));

		newChildDescriptors.add
			(createChildParameter
				(AtomPackage.Literals.ATOM_FUNCTION__INTERNAL_VARIABLES,
				 AtomFactory.eINSTANCE.createInternalParam()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == BizPackage.Literals.BIZ_INTERFACE__INPUT_PARAMETERS ||
			childFeature == BizPackage.Literals.BIZ_INTERFACE__OUTPUT_PARAMETERS ||
			childFeature == AtomPackage.Literals.ATOM_FUNCTION__INTERNAL_VARIABLES;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return AtomUI.INSTANCE;
	}

}
