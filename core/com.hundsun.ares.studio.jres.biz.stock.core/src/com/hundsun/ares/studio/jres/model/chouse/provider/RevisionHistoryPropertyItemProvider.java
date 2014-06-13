/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
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

import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;

/**
 * This is the item provider adapter for a {@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RevisionHistoryPropertyItemProvider
	extends HistoryPropertyItemProvider
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
	public RevisionHistoryPropertyItemProvider(AdapterFactory adapterFactory) {
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

			addActionTypePropertyDescriptor(object);
			addActionDescriptionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Action Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActionTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RevisionHistoryProperty_actionType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_RevisionHistoryProperty_actionType_feature", "_UI_RevisionHistoryProperty_type"),
				 ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Action Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActionDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RevisionHistoryProperty_actionDescription_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_RevisionHistoryProperty_actionDescription_feature", "_UI_RevisionHistoryProperty_type"),
				 ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION_DESCRIPTION,
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
			childrenFeatures.add(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION);
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
	 * This returns RevisionHistoryProperty.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/RevisionHistoryProperty"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((RevisionHistoryProperty)object).getInternalVersion();
		return label == null || label.length() == 0 ?
			getString("_UI_RevisionHistoryProperty_type") :
			getString("_UI_RevisionHistoryProperty_type") + " " + label;
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

		switch (notification.getFeatureID(RevisionHistoryProperty.class)) {
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION_TYPE:
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION_DESCRIPTION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION:
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
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createModification()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createAddTableModification()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createAddTableColumnModification()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createRemoveTableColumnModification()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createRenameTableColumnModification()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createChangeTableColumnTypeModification()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createAddIndexModification()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createRemoveIndexModification()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createChangeTableColumnPrimaryKeyModifycation()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createChangeTableColumnUniqueModifycation()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createChangeTableColumnNullableModifycation()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createAddTableColumnPKModification()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createRemoveTableColumnPKModification()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createAddTableColumnUniqueModifycation()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION,
				 ChouseFactory.eINSTANCE.createRemoveTableColumnUniqueModifycation()));
	}

}
