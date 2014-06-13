/**
 */
package com.hundsun.ares.studio.jres.model.chouse.provider;


import com.hundsun.ares.studio.jres.clearinghouse.Activator;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
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

import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty;

/**
 * This is the item provider adapter for a {@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TableBasePropertyItemProvider
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
	public TableBasePropertyItemProvider(AdapterFactory adapterFactory) {
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

			addChearPropertyDescriptor(object);
			addReduPropertyDescriptor(object);
			addHistoryPropertyDescriptor(object);
			addHistorySpacePropertyDescriptor(object);
			addHistoryIndexSpacePropertyDescriptor(object);
			addObjectIDPropertyDescriptor(object);
			addSplitFieldPropertyDescriptor(object);
			addSplitNumPropertyDescriptor(object);
			addStartDataPropertyDescriptor(object);
			addUserSplitPropertyDescriptor(object);
			addIsReduPropertyDescriptor(object);
			addIsClearPropertyDescriptor(object);
			addFileSpacePropertyDescriptor(object);
			addFileIndexSpacePropertyDescriptor(object);
			addClearIndexSpacePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Chear feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addChearPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_chear_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_chear_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__CHEAR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Redu feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReduPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_redu_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_redu_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__REDU,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the History feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHistoryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_history_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_history_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__HISTORY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the History Space feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHistorySpacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_historySpace_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_historySpace_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__HISTORY_SPACE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the History Index Space feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHistoryIndexSpacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_historyIndexSpace_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_historyIndexSpace_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Object ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addObjectIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_objectID_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_objectID_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__OBJECT_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Split Field feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSplitFieldPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_splitField_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_splitField_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__SPLIT_FIELD,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Split Num feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSplitNumPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_splitNum_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_splitNum_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__SPLIT_NUM,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Start Data feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStartDataPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_startData_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_startData_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__START_DATA,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the User Split feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUserSplitPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_userSplit_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_userSplit_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__USER_SPLIT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Redu feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsReduPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_isRedu_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_isRedu_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__IS_REDU,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Clear feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsClearPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_isClear_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_isClear_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__IS_CLEAR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the File Space feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFileSpacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_fileSpace_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_fileSpace_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__FILE_SPACE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the File Index Space feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFileIndexSpacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_fileIndexSpace_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_fileIndexSpace_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__FILE_INDEX_SPACE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Clear Index Space feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addClearIndexSpacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TableBaseProperty_clearIndexSpace_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TableBaseProperty_clearIndexSpace_feature", "_UI_TableBaseProperty_type"),
				 ChousePackage.Literals.TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns TableBaseProperty.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TableBaseProperty"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((TableBaseProperty)object).getChear();
		return label == null || label.length() == 0 ?
			getString("_UI_TableBaseProperty_type") :
			getString("_UI_TableBaseProperty_type") + " " + label;
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

		switch (notification.getFeatureID(TableBaseProperty.class)) {
			case ChousePackage.TABLE_BASE_PROPERTY__CHEAR:
			case ChousePackage.TABLE_BASE_PROPERTY__REDU:
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY:
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY_SPACE:
			case ChousePackage.TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE:
			case ChousePackage.TABLE_BASE_PROPERTY__OBJECT_ID:
			case ChousePackage.TABLE_BASE_PROPERTY__SPLIT_FIELD:
			case ChousePackage.TABLE_BASE_PROPERTY__SPLIT_NUM:
			case ChousePackage.TABLE_BASE_PROPERTY__START_DATA:
			case ChousePackage.TABLE_BASE_PROPERTY__USER_SPLIT:
			case ChousePackage.TABLE_BASE_PROPERTY__IS_REDU:
			case ChousePackage.TABLE_BASE_PROPERTY__IS_CLEAR:
			case ChousePackage.TABLE_BASE_PROPERTY__FILE_SPACE:
			case ChousePackage.TABLE_BASE_PROPERTY__FILE_INDEX_SPACE:
			case ChousePackage.TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return Activator.INSTANCE;
	}

}
