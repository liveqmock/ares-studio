/**
 * 源程序名称：JRESResourceInfoImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.ReferenceUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JRES Resource Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl#getObjectId <em>Object Id</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl#getHistories <em>Histories</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl#getFullyQualifiedName <em>Fully Qualified Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JRESResourceInfoImpl extends ExtensibleModelImpl implements JRESResourceInfo {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getChineseName() <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChineseName()
	 * @generated
	 * @ordered
	 */
	protected static final String CHINESE_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getChineseName() <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChineseName()
	 * @generated
	 * @ordered
	 */
	protected String chineseName = CHINESE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getObjectId() <em>Object Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectId()
	 * @generated
	 * @ordered
	 */
	protected static final String OBJECT_ID_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getObjectId() <em>Object Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectId()
	 * @generated
	 * @ordered
	 */
	protected String objectId = OBJECT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroup() <em>Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected String group = GROUP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHistories() <em>Histories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHistories()
	 * @generated
	 * @ordered
	 */
	protected EList<RevisionHistory> histories;

	/**
	 * The default value of the '{@link #getFullyQualifiedName() <em>Fully Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullyQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected static final String FULLY_QUALIFIED_NAME_EDEFAULT = "";
	/**
	 * The cached value of the '{@link #getFullyQualifiedName() <em>Fully Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullyQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected String fullyQualifiedName = FULLY_QUALIFIED_NAME_EDEFAULT;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JRESResourceInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.JRES_RESOURCE_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.JRES_RESOURCE_INFO__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChineseName(String newChineseName) {
		String oldChineseName = chineseName;
		chineseName = newChineseName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME, oldChineseName, chineseName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.JRES_RESOURCE_INFO__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObjectId(String newObjectId) {
		String oldObjectId = objectId;
		objectId = newObjectId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.JRES_RESOURCE_INFO__OBJECT_ID, oldObjectId, objectId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroup(String newGroup) {
		String oldGroup = group;
		group = newGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.JRES_RESOURCE_INFO__GROUP, oldGroup, group));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RevisionHistory> getHistories() {
		if (histories == null) {
			histories = new EObjectContainmentEList<RevisionHistory>(RevisionHistory.class, this, CorePackage.JRES_RESOURCE_INFO__HISTORIES);
		}
		return histories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullyQualifiedName() {
		return fullyQualifiedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFullyQualifiedName(String newFullyQualifiedName) {
		String oldFullyQualifiedName = fullyQualifiedName;
		fullyQualifiedName = newFullyQualifiedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME, oldFullyQualifiedName, fullyQualifiedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Reference> getReferences() {
		BasicEList<Reference> references = new BasicEList<Reference>();
		// 收集所有包含内容的引用
		for (TreeIterator<EObject> iterator = eAllContents(); iterator.hasNext(); ) {
			EObject obj = iterator.next();
			references.addAll(ReferenceUtil.INSTANCE.getReferences(obj));
		}
		return references;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.JRES_RESOURCE_INFO__HISTORIES:
				return ((InternalEList<?>)getHistories()).basicRemove(otherEnd, msgs);
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
			case CorePackage.JRES_RESOURCE_INFO__NAME:
				return getName();
			case CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME:
				return getChineseName();
			case CorePackage.JRES_RESOURCE_INFO__DESCRIPTION:
				return getDescription();
			case CorePackage.JRES_RESOURCE_INFO__OBJECT_ID:
				return getObjectId();
			case CorePackage.JRES_RESOURCE_INFO__GROUP:
				return getGroup();
			case CorePackage.JRES_RESOURCE_INFO__HISTORIES:
				return getHistories();
			case CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME:
				return getFullyQualifiedName();
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
			case CorePackage.JRES_RESOURCE_INFO__NAME:
				setName((String)newValue);
				return;
			case CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME:
				setChineseName((String)newValue);
				return;
			case CorePackage.JRES_RESOURCE_INFO__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CorePackage.JRES_RESOURCE_INFO__OBJECT_ID:
				setObjectId((String)newValue);
				return;
			case CorePackage.JRES_RESOURCE_INFO__GROUP:
				setGroup((String)newValue);
				return;
			case CorePackage.JRES_RESOURCE_INFO__HISTORIES:
				getHistories().clear();
				getHistories().addAll((Collection<? extends RevisionHistory>)newValue);
				return;
			case CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME:
				setFullyQualifiedName((String)newValue);
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
			case CorePackage.JRES_RESOURCE_INFO__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME:
				setChineseName(CHINESE_NAME_EDEFAULT);
				return;
			case CorePackage.JRES_RESOURCE_INFO__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CorePackage.JRES_RESOURCE_INFO__OBJECT_ID:
				setObjectId(OBJECT_ID_EDEFAULT);
				return;
			case CorePackage.JRES_RESOURCE_INFO__GROUP:
				setGroup(GROUP_EDEFAULT);
				return;
			case CorePackage.JRES_RESOURCE_INFO__HISTORIES:
				getHistories().clear();
				return;
			case CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME:
				setFullyQualifiedName(FULLY_QUALIFIED_NAME_EDEFAULT);
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
			case CorePackage.JRES_RESOURCE_INFO__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME:
				return CHINESE_NAME_EDEFAULT == null ? chineseName != null : !CHINESE_NAME_EDEFAULT.equals(chineseName);
			case CorePackage.JRES_RESOURCE_INFO__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CorePackage.JRES_RESOURCE_INFO__OBJECT_ID:
				return OBJECT_ID_EDEFAULT == null ? objectId != null : !OBJECT_ID_EDEFAULT.equals(objectId);
			case CorePackage.JRES_RESOURCE_INFO__GROUP:
				return GROUP_EDEFAULT == null ? group != null : !GROUP_EDEFAULT.equals(group);
			case CorePackage.JRES_RESOURCE_INFO__HISTORIES:
				return histories != null && !histories.isEmpty();
			case CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME:
				return FULLY_QUALIFIED_NAME_EDEFAULT == null ? fullyQualifiedName != null : !FULLY_QUALIFIED_NAME_EDEFAULT.equals(fullyQualifiedName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == BasicResourceInfo.class) {
			switch (derivedFeatureID) {
				case CorePackage.JRES_RESOURCE_INFO__NAME: return CorePackage.BASIC_RESOURCE_INFO__NAME;
				case CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME: return CorePackage.BASIC_RESOURCE_INFO__CHINESE_NAME;
				case CorePackage.JRES_RESOURCE_INFO__DESCRIPTION: return CorePackage.BASIC_RESOURCE_INFO__DESCRIPTION;
				case CorePackage.JRES_RESOURCE_INFO__OBJECT_ID: return CorePackage.BASIC_RESOURCE_INFO__OBJECT_ID;
				case CorePackage.JRES_RESOURCE_INFO__GROUP: return CorePackage.BASIC_RESOURCE_INFO__GROUP;
				default: return -1;
			}
		}
		if (baseClass == IReferenceProvider.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == BasicResourceInfo.class) {
			switch (baseFeatureID) {
				case CorePackage.BASIC_RESOURCE_INFO__NAME: return CorePackage.JRES_RESOURCE_INFO__NAME;
				case CorePackage.BASIC_RESOURCE_INFO__CHINESE_NAME: return CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME;
				case CorePackage.BASIC_RESOURCE_INFO__DESCRIPTION: return CorePackage.JRES_RESOURCE_INFO__DESCRIPTION;
				case CorePackage.BASIC_RESOURCE_INFO__OBJECT_ID: return CorePackage.JRES_RESOURCE_INFO__OBJECT_ID;
				case CorePackage.BASIC_RESOURCE_INFO__GROUP: return CorePackage.JRES_RESOURCE_INFO__GROUP;
				default: return -1;
			}
		}
		if (baseClass == IReferenceProvider.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", chineseName: ");
		result.append(chineseName);
		result.append(", description: ");
		result.append(description);
		result.append(", objectId: ");
		result.append(objectId);
		result.append(", group: ");
		result.append(group);
		result.append(", fullyQualifiedName: ");
		result.append(fullyQualifiedName);
		result.append(')');
		return result.toString();
	}

} //JRESResourceInfoImpl
