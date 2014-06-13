/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.provider;


import com.hundsun.ares.studio.jres.database.oracle.OracleCore;

import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;

import com.hundsun.ares.studio.jres.model.database.provider.DatabaseResourceDataItemProvider;

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
 * This is the item provider adapter for a {@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SequenceResourceDataItemProvider
	extends DatabaseResourceDataItemProvider
	implements
		IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, IItemFontProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceResourceDataItemProvider(AdapterFactory adapterFactory) {
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

			addTableNamePropertyDescriptor(object);
			addStartPropertyDescriptor(object);
			addIncrementPropertyDescriptor(object);
			addMinValuePropertyDescriptor(object);
			addMaxValuePropertyDescriptor(object);
			addCyclePropertyDescriptor(object);
			addCachePropertyDescriptor(object);
			addUseCachePropertyDescriptor(object);
			addIsHistoryPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Table Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTableNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SequenceResourceData_tableName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SequenceResourceData_tableName_feature", "_UI_SequenceResourceData_type"),
				 OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__TABLE_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Start feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStartPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SequenceResourceData_start_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SequenceResourceData_start_feature", "_UI_SequenceResourceData_type"),
				 OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__START,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Increment feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncrementPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SequenceResourceData_increment_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SequenceResourceData_increment_feature", "_UI_SequenceResourceData_type"),
				 OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__INCREMENT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Min Value feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMinValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SequenceResourceData_minValue_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SequenceResourceData_minValue_feature", "_UI_SequenceResourceData_type"),
				 OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__MIN_VALUE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Max Value feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMaxValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SequenceResourceData_maxValue_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SequenceResourceData_maxValue_feature", "_UI_SequenceResourceData_type"),
				 OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__MAX_VALUE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Cycle feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCyclePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SequenceResourceData_cycle_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SequenceResourceData_cycle_feature", "_UI_SequenceResourceData_type"),
				 OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__CYCLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Cache feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCachePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SequenceResourceData_cache_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SequenceResourceData_cache_feature", "_UI_SequenceResourceData_type"),
				 OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__CACHE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Use Cache feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUseCachePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SequenceResourceData_useCache_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SequenceResourceData_useCache_feature", "_UI_SequenceResourceData_type"),
				 OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__USE_CACHE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is History feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsHistoryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SequenceResourceData_isHistory_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SequenceResourceData_isHistory_feature", "_UI_SequenceResourceData_type"),
				 OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__IS_HISTORY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns SequenceResourceData.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SequenceResourceData"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((SequenceResourceData)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_SequenceResourceData_type") :
			getString("_UI_SequenceResourceData_type") + " " + label;
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

		switch (notification.getFeatureID(SequenceResourceData.class)) {
			case OraclePackage.SEQUENCE_RESOURCE_DATA__TABLE_NAME:
			case OraclePackage.SEQUENCE_RESOURCE_DATA__START:
			case OraclePackage.SEQUENCE_RESOURCE_DATA__INCREMENT:
			case OraclePackage.SEQUENCE_RESOURCE_DATA__MIN_VALUE:
			case OraclePackage.SEQUENCE_RESOURCE_DATA__MAX_VALUE:
			case OraclePackage.SEQUENCE_RESOURCE_DATA__CYCLE:
			case OraclePackage.SEQUENCE_RESOURCE_DATA__CACHE:
			case OraclePackage.SEQUENCE_RESOURCE_DATA__USE_CACHE:
			case OraclePackage.SEQUENCE_RESOURCE_DATA__IS_HISTORY:
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
