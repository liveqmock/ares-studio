/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.atom.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.atom.InternalParam;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.impl.BizInterfaceImpl;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.IJSONData;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.impl.EStringToEObjectMapEntryImpl;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.core.model.util.EMFJSONUtil;
import com.hundsun.ares.studio.core.model.util.ReferenceUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getData <em>Data</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getData2 <em>Data2</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getObjectId <em>Object Id</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getHistories <em>Histories</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getFullyQualifiedName <em>Fully Qualified Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getPseudoCode <em>Pseudo Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl#getInternalVariables <em>Internal Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AtomFunctionImpl extends BizInterfaceImpl implements AtomFunction {
	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> data;

	/**
	 * The cached value of the '{@link #getData2() <em>Data2</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData2()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, EObject> data2;

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
	 * The default value of the '{@link #getDatabase() <em>Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected static final String DATABASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected String database = DATABASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPseudoCode() <em>Pseudo Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPseudoCode()
	 * @generated
	 * @ordered
	 */
	protected static final String PSEUDO_CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPseudoCode() <em>Pseudo Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPseudoCode()
	 * @generated
	 * @ordered
	 */
	protected String pseudoCode = PSEUDO_CODE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInternalVariables() <em>Internal Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<InternalParam> internalVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public AtomFunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtomPackage.Literals.ATOM_FUNCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getData() {
		if (data == null) {
			data = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, AtomPackage.ATOM_FUNCTION__DATA);
		}
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, EObject> getData2() {
		if (data2 == null) {
			data2 = new EcoreEMap<String,EObject>(CorePackage.Literals.ESTRING_TO_EOBJECT_MAP_ENTRY, EStringToEObjectMapEntryImpl.class, this, AtomPackage.ATOM_FUNCTION__DATA2);
		}
		return data2;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtomPackage.ATOM_FUNCTION__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtomPackage.ATOM_FUNCTION__CHINESE_NAME, oldChineseName, chineseName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtomPackage.ATOM_FUNCTION__DESCRIPTION, oldDescription, description));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtomPackage.ATOM_FUNCTION__OBJECT_ID, oldObjectId, objectId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtomPackage.ATOM_FUNCTION__GROUP, oldGroup, group));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RevisionHistory> getHistories() {
		if (histories == null) {
			histories = new EObjectContainmentEList<RevisionHistory>(RevisionHistory.class, this, AtomPackage.ATOM_FUNCTION__HISTORIES);
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtomPackage.ATOM_FUNCTION__FULLY_QUALIFIED_NAME, oldFullyQualifiedName, fullyQualifiedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabase(String newDatabase) {
		String oldDatabase = database;
		database = newDatabase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtomPackage.ATOM_FUNCTION__DATABASE, oldDatabase, database));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPseudoCode() {
		return pseudoCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPseudoCode(String newPseudoCode) {
		String oldPseudoCode = pseudoCode;
		pseudoCode = newPseudoCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtomPackage.ATOM_FUNCTION__PSEUDO_CODE, oldPseudoCode, pseudoCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InternalParam> getInternalVariables() {
		if (internalVariables == null) {
			internalVariables = new EObjectContainmentEList<InternalParam>(InternalParam.class, this, AtomPackage.ATOM_FUNCTION__INTERNAL_VARIABLES);
		}
		return internalVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Reference> getReferences() {
		BasicEList<Reference> references = new BasicEList<Reference>();
		BasicEList<Parameter> parametesrs = new BasicEList<Parameter>();
		parametesrs.addAll(getInputParameters());//添加输入参数
		parametesrs.addAll(getOutputParameters());//添加输出参数
		parametesrs.addAll(getInternalVariables());//添加内部变量
		for(Parameter parametesr:parametesrs){
			if(StringUtils.isNotBlank(parametesr.getId())&& parametesr.getParamType()==ParamType.STD_FIELD){
				Reference stdName = new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdField, 
						parametesr, 
						BizPackage.Literals.PARAMETER__ID);
				references.add(stdName);
			}
		}
		
		//伪代码Reference
		String pseudoCode =getPseudoCode();
		if(StringUtils.isNotBlank(pseudoCode)){
			Reference stdName = new PseudoCodeTextAttributeReferenceImpl(IMetadataRefType.StdField, 
					this, 
					AtomPackage.Literals.ATOM_FUNCTION__PSEUDO_CODE);
			references.add(stdName);
		}
		return references;
	}
	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toJSON() {
		return EMFJSONUtil.write(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AtomPackage.ATOM_FUNCTION__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
			case AtomPackage.ATOM_FUNCTION__DATA2:
				return ((InternalEList<?>)getData2()).basicRemove(otherEnd, msgs);
			case AtomPackage.ATOM_FUNCTION__HISTORIES:
				return ((InternalEList<?>)getHistories()).basicRemove(otherEnd, msgs);
			case AtomPackage.ATOM_FUNCTION__INTERNAL_VARIABLES:
				return ((InternalEList<?>)getInternalVariables()).basicRemove(otherEnd, msgs);
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
			case AtomPackage.ATOM_FUNCTION__DATA:
				if (coreType) return getData();
				else return getData().map();
			case AtomPackage.ATOM_FUNCTION__DATA2:
				if (coreType) return getData2();
				else return getData2().map();
			case AtomPackage.ATOM_FUNCTION__NAME:
				return getName();
			case AtomPackage.ATOM_FUNCTION__CHINESE_NAME:
				return getChineseName();
			case AtomPackage.ATOM_FUNCTION__DESCRIPTION:
				return getDescription();
			case AtomPackage.ATOM_FUNCTION__OBJECT_ID:
				return getObjectId();
			case AtomPackage.ATOM_FUNCTION__GROUP:
				return getGroup();
			case AtomPackage.ATOM_FUNCTION__HISTORIES:
				return getHistories();
			case AtomPackage.ATOM_FUNCTION__FULLY_QUALIFIED_NAME:
				return getFullyQualifiedName();
			case AtomPackage.ATOM_FUNCTION__DATABASE:
				return getDatabase();
			case AtomPackage.ATOM_FUNCTION__PSEUDO_CODE:
				return getPseudoCode();
			case AtomPackage.ATOM_FUNCTION__INTERNAL_VARIABLES:
				return getInternalVariables();
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
			case AtomPackage.ATOM_FUNCTION__DATA:
				((EStructuralFeature.Setting)getData()).set(newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__DATA2:
				((EStructuralFeature.Setting)getData2()).set(newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__NAME:
				setName((String)newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__CHINESE_NAME:
				setChineseName((String)newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__OBJECT_ID:
				setObjectId((String)newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__GROUP:
				setGroup((String)newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__HISTORIES:
				getHistories().clear();
				getHistories().addAll((Collection<? extends RevisionHistory>)newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__FULLY_QUALIFIED_NAME:
				setFullyQualifiedName((String)newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__DATABASE:
				setDatabase((String)newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__PSEUDO_CODE:
				setPseudoCode((String)newValue);
				return;
			case AtomPackage.ATOM_FUNCTION__INTERNAL_VARIABLES:
				getInternalVariables().clear();
				getInternalVariables().addAll((Collection<? extends InternalParam>)newValue);
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
			case AtomPackage.ATOM_FUNCTION__DATA:
				getData().clear();
				return;
			case AtomPackage.ATOM_FUNCTION__DATA2:
				getData2().clear();
				return;
			case AtomPackage.ATOM_FUNCTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AtomPackage.ATOM_FUNCTION__CHINESE_NAME:
				setChineseName(CHINESE_NAME_EDEFAULT);
				return;
			case AtomPackage.ATOM_FUNCTION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case AtomPackage.ATOM_FUNCTION__OBJECT_ID:
				setObjectId(OBJECT_ID_EDEFAULT);
				return;
			case AtomPackage.ATOM_FUNCTION__GROUP:
				setGroup(GROUP_EDEFAULT);
				return;
			case AtomPackage.ATOM_FUNCTION__HISTORIES:
				getHistories().clear();
				return;
			case AtomPackage.ATOM_FUNCTION__FULLY_QUALIFIED_NAME:
				setFullyQualifiedName(FULLY_QUALIFIED_NAME_EDEFAULT);
				return;
			case AtomPackage.ATOM_FUNCTION__DATABASE:
				setDatabase(DATABASE_EDEFAULT);
				return;
			case AtomPackage.ATOM_FUNCTION__PSEUDO_CODE:
				setPseudoCode(PSEUDO_CODE_EDEFAULT);
				return;
			case AtomPackage.ATOM_FUNCTION__INTERNAL_VARIABLES:
				getInternalVariables().clear();
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
			case AtomPackage.ATOM_FUNCTION__DATA:
				return data != null && !data.isEmpty();
			case AtomPackage.ATOM_FUNCTION__DATA2:
				return data2 != null && !data2.isEmpty();
			case AtomPackage.ATOM_FUNCTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AtomPackage.ATOM_FUNCTION__CHINESE_NAME:
				return CHINESE_NAME_EDEFAULT == null ? chineseName != null : !CHINESE_NAME_EDEFAULT.equals(chineseName);
			case AtomPackage.ATOM_FUNCTION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case AtomPackage.ATOM_FUNCTION__OBJECT_ID:
				return OBJECT_ID_EDEFAULT == null ? objectId != null : !OBJECT_ID_EDEFAULT.equals(objectId);
			case AtomPackage.ATOM_FUNCTION__GROUP:
				return GROUP_EDEFAULT == null ? group != null : !GROUP_EDEFAULT.equals(group);
			case AtomPackage.ATOM_FUNCTION__HISTORIES:
				return histories != null && !histories.isEmpty();
			case AtomPackage.ATOM_FUNCTION__FULLY_QUALIFIED_NAME:
				return FULLY_QUALIFIED_NAME_EDEFAULT == null ? fullyQualifiedName != null : !FULLY_QUALIFIED_NAME_EDEFAULT.equals(fullyQualifiedName);
			case AtomPackage.ATOM_FUNCTION__DATABASE:
				return DATABASE_EDEFAULT == null ? database != null : !DATABASE_EDEFAULT.equals(database);
			case AtomPackage.ATOM_FUNCTION__PSEUDO_CODE:
				return PSEUDO_CODE_EDEFAULT == null ? pseudoCode != null : !PSEUDO_CODE_EDEFAULT.equals(pseudoCode);
			case AtomPackage.ATOM_FUNCTION__INTERNAL_VARIABLES:
				return internalVariables != null && !internalVariables.isEmpty();
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
		if (baseClass == IJSONData.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ExtensibleModel.class) {
			switch (derivedFeatureID) {
				case AtomPackage.ATOM_FUNCTION__DATA: return CorePackage.EXTENSIBLE_MODEL__DATA;
				case AtomPackage.ATOM_FUNCTION__DATA2: return CorePackage.EXTENSIBLE_MODEL__DATA2;
				default: return -1;
			}
		}
		if (baseClass == BasicResourceInfo.class) {
			switch (derivedFeatureID) {
				case AtomPackage.ATOM_FUNCTION__NAME: return CorePackage.BASIC_RESOURCE_INFO__NAME;
				case AtomPackage.ATOM_FUNCTION__CHINESE_NAME: return CorePackage.BASIC_RESOURCE_INFO__CHINESE_NAME;
				case AtomPackage.ATOM_FUNCTION__DESCRIPTION: return CorePackage.BASIC_RESOURCE_INFO__DESCRIPTION;
				case AtomPackage.ATOM_FUNCTION__OBJECT_ID: return CorePackage.BASIC_RESOURCE_INFO__OBJECT_ID;
				case AtomPackage.ATOM_FUNCTION__GROUP: return CorePackage.BASIC_RESOURCE_INFO__GROUP;
				default: return -1;
			}
		}
		if (baseClass == IReferenceProvider.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == JRESResourceInfo.class) {
			switch (derivedFeatureID) {
				case AtomPackage.ATOM_FUNCTION__HISTORIES: return CorePackage.JRES_RESOURCE_INFO__HISTORIES;
				case AtomPackage.ATOM_FUNCTION__FULLY_QUALIFIED_NAME: return CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME;
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
		if (baseClass == IJSONData.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ExtensibleModel.class) {
			switch (baseFeatureID) {
				case CorePackage.EXTENSIBLE_MODEL__DATA: return AtomPackage.ATOM_FUNCTION__DATA;
				case CorePackage.EXTENSIBLE_MODEL__DATA2: return AtomPackage.ATOM_FUNCTION__DATA2;
				default: return -1;
			}
		}
		if (baseClass == BasicResourceInfo.class) {
			switch (baseFeatureID) {
				case CorePackage.BASIC_RESOURCE_INFO__NAME: return AtomPackage.ATOM_FUNCTION__NAME;
				case CorePackage.BASIC_RESOURCE_INFO__CHINESE_NAME: return AtomPackage.ATOM_FUNCTION__CHINESE_NAME;
				case CorePackage.BASIC_RESOURCE_INFO__DESCRIPTION: return AtomPackage.ATOM_FUNCTION__DESCRIPTION;
				case CorePackage.BASIC_RESOURCE_INFO__OBJECT_ID: return AtomPackage.ATOM_FUNCTION__OBJECT_ID;
				case CorePackage.BASIC_RESOURCE_INFO__GROUP: return AtomPackage.ATOM_FUNCTION__GROUP;
				default: return -1;
			}
		}
		if (baseClass == IReferenceProvider.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == JRESResourceInfo.class) {
			switch (baseFeatureID) {
				case CorePackage.JRES_RESOURCE_INFO__HISTORIES: return AtomPackage.ATOM_FUNCTION__HISTORIES;
				case CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME: return AtomPackage.ATOM_FUNCTION__FULLY_QUALIFIED_NAME;
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
		result.append(", database: ");
		result.append(database);
		result.append(", pseudoCode: ");
		result.append(pseudoCode);
		result.append(')');
		return result.toString();
	}
	

} //AtomFunctionImpl
