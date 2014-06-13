/**
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

import com.hundsun.ares.studio.jres.model.chouse.AddTableModification;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;

/**
 * This is the item provider adapter for a {@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AddTableModificationItemProvider
	extends ModificationItemProvider
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
	public AddTableModificationItemProvider(AdapterFactory adapterFactory) {
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

			addNewSelfTablePropertyDescriptor(object);
			addNewHistoryTablePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the New Self Table feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNewSelfTablePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AddTableModification_newSelfTable_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AddTableModification_newSelfTable_feature", "_UI_AddTableModification_type"),
				 ChousePackage.Literals.ADD_TABLE_MODIFICATION__NEW_SELF_TABLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the New History Table feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNewHistoryTablePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AddTableModification_newHistoryTable_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AddTableModification_newHistoryTable_feature", "_UI_AddTableModification_type"),
				 ChousePackage.Literals.ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
			childrenFeatures.add(ChousePackage.Literals.ADD_TABLE_MODIFICATION__COLUMNS);
			childrenFeatures.add(ChousePackage.Literals.ADD_TABLE_MODIFICATION__INDEXES);
			childrenFeatures.add(ChousePackage.Literals.ADD_TABLE_MODIFICATION__KEYS);
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
	 * This returns AddTableModification.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AddTableModification"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		AddTableModification addTableModification = (AddTableModification)object;
		return getString("_UI_AddTableModification_type") + " " + addTableModification.isNewSelfTable();
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

		switch (notification.getFeatureID(AddTableModification.class)) {
			case ChousePackage.ADD_TABLE_MODIFICATION__NEW_SELF_TABLE:
			case ChousePackage.ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ChousePackage.ADD_TABLE_MODIFICATION__COLUMNS:
			case ChousePackage.ADD_TABLE_MODIFICATION__INDEXES:
			case ChousePackage.ADD_TABLE_MODIFICATION__KEYS:
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
				(ChousePackage.Literals.ADD_TABLE_MODIFICATION__COLUMNS,
				 ChouseFactory.eINSTANCE.createHisTableColumn()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.ADD_TABLE_MODIFICATION__COLUMNS,
				 ChouseFactory.eINSTANCE.createRemovedTableColumn()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.ADD_TABLE_MODIFICATION__COLUMNS,
				 DatabaseFactory.eINSTANCE.createTableColumn()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.ADD_TABLE_MODIFICATION__INDEXES,
				 DatabaseFactory.eINSTANCE.createTableIndex()));

		newChildDescriptors.add
			(createChildParameter
				(ChousePackage.Literals.ADD_TABLE_MODIFICATION__KEYS,
				 DatabaseFactory.eINSTANCE.createTableKey()));
	}

}
