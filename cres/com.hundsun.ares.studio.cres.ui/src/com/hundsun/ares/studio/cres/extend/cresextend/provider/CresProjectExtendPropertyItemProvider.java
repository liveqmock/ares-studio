/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend.provider;


import com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendFactory;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage;

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
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CresProjectExtendPropertyItemProvider
	extends ItemProviderAdapter
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
	public CresProjectExtendPropertyItemProvider(AdapterFactory adapterFactory) {
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

			addVersionPropertyDescriptor(object);
			addCNamePropertyDescriptor(object);
			addShortCNamePropertyDescriptor(object);
			addIdPropertyDescriptor(object);
			addManagerPropertyDescriptor(object);
			addDeveloperPropertyDescriptor(object);
			addUserPropertyDescriptor(object);
			addRelationPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addWriterPropertyDescriptor(object);
			addNotePropertyDescriptor(object);
			addHeadFilePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Version feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_version_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_version_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__VERSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the CName feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_cName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_cName_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__CNAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Short CName feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addShortCNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_shortCName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_shortCName_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_id_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Manager feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addManagerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_manager_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_manager_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__MANAGER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Developer feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDeveloperPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_developer_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_developer_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the User feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUserPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_user_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_user_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__USER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Relation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRelationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_relation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_relation_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__RELATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
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
				 getString("_UI_CresProjectExtendProperty_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_name_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Writer feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWriterPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_writer_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_writer_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__WRITER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Note feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNotePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_note_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_note_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__NOTE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Head File feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHeadFilePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CresProjectExtendProperty_headFile_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CresProjectExtendProperty_headFile_feature", "_UI_CresProjectExtendProperty_type"),
				 CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE,
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
			childrenFeatures.add(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE);
			childrenFeatures.add(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE);
			childrenFeatures.add(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE);
			childrenFeatures.add(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE);
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
	 * This returns CresProjectExtendProperty.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CresProjectExtendProperty"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((CresProjectExtendProperty)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_CresProjectExtendProperty_type") :
			getString("_UI_CresProjectExtendProperty_type") + " " + label;
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

		switch (notification.getFeatureID(CresProjectExtendProperty.class)) {
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__VERSION:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__CNAME:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__ID:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MANAGER:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__USER:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__RELATION:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NAME:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__WRITER:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NOTE:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE:
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE:
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
				(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE,
				 CresextendFactory.eINSTANCE.createProcDefine()));

		newChildDescriptors.add
			(createChildParameter
				(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE,
				 CresextendFactory.eINSTANCE.createGccDefine()));

		newChildDescriptors.add
			(createChildParameter
				(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE,
				 CresextendFactory.eINSTANCE.createMvcDefine()));

		newChildDescriptors.add
			(createChildParameter
				(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE,
				 CresextendFactory.eINSTANCE.createFileDefine()));

		newChildDescriptors.add
			(createChildParameter
				(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE,
				 CresextendFactory.eINSTANCE.createProcDefine()));

		newChildDescriptors.add
			(createChildParameter
				(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE,
				 CresextendFactory.eINSTANCE.createGccDefine()));

		newChildDescriptors.add
			(createChildParameter
				(CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE,
				 CresextendFactory.eINSTANCE.createMvcDefine()));
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
			childFeature == CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE ||
			childFeature == CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE ||
			childFeature == CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE ||
			childFeature == CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE;

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
		return CresExtendEditPlugin.INSTANCE;
	}

}
