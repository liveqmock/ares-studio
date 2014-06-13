/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Menu Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.MenuItemImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.MenuItemImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.MenuItemImpl#getSubItems <em>Sub Items</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.MenuItemImpl#getFunctionProxys <em>Function Proxys</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MenuItemImpl extends MetadataItemImpl implements MenuItem {
	/**
	 * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected String url = URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected String icon = ICON_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSubItems() <em>Sub Items</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubItems()
	 * @generated
	 * @ordered
	 */
	protected EList<MenuItem> subItems;

	/**
	 * The cached value of the '{@link #getFunctionProxys() <em>Function Proxys</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionProxys()
	 * @generated
	 * @ordered
	 */
	protected EList<FunctionProxy> functionProxys;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MenuItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.MENU_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrl(String newUrl) {
		String oldUrl = url;
		url = newUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.MENU_ITEM__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIcon(String newIcon) {
		String oldIcon = icon;
		icon = newIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.MENU_ITEM__ICON, oldIcon, icon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FunctionProxy> getFunctionProxys() {
		if (functionProxys == null) {
			functionProxys = new EObjectContainmentEList<FunctionProxy>(FunctionProxy.class, this, MetadataPackage.MENU_ITEM__FUNCTION_PROXYS);
		}
		return functionProxys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MenuItem> getSubItems() {
		if (subItems == null) {
			subItems = new EObjectContainmentEList<MenuItem>(MenuItem.class, this, MetadataPackage.MENU_ITEM__SUB_ITEMS);
		}
		return subItems;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetadataPackage.MENU_ITEM__SUB_ITEMS:
				return ((InternalEList<?>)getSubItems()).basicRemove(otherEnd, msgs);
			case MetadataPackage.MENU_ITEM__FUNCTION_PROXYS:
				return ((InternalEList<?>)getFunctionProxys()).basicRemove(otherEnd, msgs);
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
			case MetadataPackage.MENU_ITEM__URL:
				return getUrl();
			case MetadataPackage.MENU_ITEM__ICON:
				return getIcon();
			case MetadataPackage.MENU_ITEM__SUB_ITEMS:
				return getSubItems();
			case MetadataPackage.MENU_ITEM__FUNCTION_PROXYS:
				return getFunctionProxys();
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
			case MetadataPackage.MENU_ITEM__URL:
				setUrl((String)newValue);
				return;
			case MetadataPackage.MENU_ITEM__ICON:
				setIcon((String)newValue);
				return;
			case MetadataPackage.MENU_ITEM__SUB_ITEMS:
				getSubItems().clear();
				getSubItems().addAll((Collection<? extends MenuItem>)newValue);
				return;
			case MetadataPackage.MENU_ITEM__FUNCTION_PROXYS:
				getFunctionProxys().clear();
				getFunctionProxys().addAll((Collection<? extends FunctionProxy>)newValue);
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
			case MetadataPackage.MENU_ITEM__URL:
				setUrl(URL_EDEFAULT);
				return;
			case MetadataPackage.MENU_ITEM__ICON:
				setIcon(ICON_EDEFAULT);
				return;
			case MetadataPackage.MENU_ITEM__SUB_ITEMS:
				getSubItems().clear();
				return;
			case MetadataPackage.MENU_ITEM__FUNCTION_PROXYS:
				getFunctionProxys().clear();
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
			case MetadataPackage.MENU_ITEM__URL:
				return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
			case MetadataPackage.MENU_ITEM__ICON:
				return ICON_EDEFAULT == null ? icon != null : !ICON_EDEFAULT.equals(icon);
			case MetadataPackage.MENU_ITEM__SUB_ITEMS:
				return subItems != null && !subItems.isEmpty();
			case MetadataPackage.MENU_ITEM__FUNCTION_PROXYS:
				return functionProxys != null && !functionProxys.isEmpty();
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
		result.append(" (url: ");
		result.append(url);
		result.append(", icon: ");
		result.append(icon);
		result.append(')');
		return result.toString();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public MetadataResourceData<?> getParent() {
		return MenuUtils.getMenuList(this);
	}

} //MenuItemImpl
