/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.provider;


import com.hundsun.ares.studio.core.model.provider.ExtensibleModelItemProvider;

import com.hundsun.ares.studio.jres.database.oracle.OracleCore;

import com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.table_type;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DatabaseModuleExtensiblePropertyItemProvider
	extends ExtensibleModelItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource,
		IItemFontProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseModuleExtensiblePropertyItemProvider(AdapterFactory adapterFactory) {
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

			addTableTypePropertyDescriptor(object);
			addSpacePropertyDescriptor(object);
			addSplitFieldPropertyDescriptor(object);
			addSplitNumPropertyDescriptor(object);
			addStartDatePropertyDescriptor(object);
			addBizPkgPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Table Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTableTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DatabaseModuleExtensibleProperty_tableType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DatabaseModuleExtensibleProperty_tableType_feature", "_UI_DatabaseModuleExtensibleProperty_type"),
				 OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Space feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSpacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DatabaseModuleExtensibleProperty_space_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DatabaseModuleExtensibleProperty_space_feature", "_UI_DatabaseModuleExtensibleProperty_type"),
				 OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE,
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
				 getString("_UI_DatabaseModuleExtensibleProperty_splitField_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DatabaseModuleExtensibleProperty_splitField_feature", "_UI_DatabaseModuleExtensibleProperty_type"),
				 OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD,
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
				 getString("_UI_DatabaseModuleExtensibleProperty_splitNum_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DatabaseModuleExtensibleProperty_splitNum_feature", "_UI_DatabaseModuleExtensibleProperty_type"),
				 OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Start Date feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStartDatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DatabaseModuleExtensibleProperty_startDate_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DatabaseModuleExtensibleProperty_startDate_feature", "_UI_DatabaseModuleExtensibleProperty_type"),
				 OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Biz Pkg feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBizPkgPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DatabaseModuleExtensibleProperty_bizPkg_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DatabaseModuleExtensibleProperty_bizPkg_feature", "_UI_DatabaseModuleExtensibleProperty_type"),
				 OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns DatabaseModuleExtensibleProperty.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DatabaseModuleExtensibleProperty"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		table_type labelValue = ((DatabaseModuleExtensibleProperty)object).getTableType();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ?
			getString("_UI_DatabaseModuleExtensibleProperty_type") :
			getString("_UI_DatabaseModuleExtensibleProperty_type") + " " + label;
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

		switch (notification.getFeatureID(DatabaseModuleExtensibleProperty.class)) {
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE:
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE:
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD:
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM:
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE:
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG:
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
		return OracleCore.INSTANCE;
	}

}
