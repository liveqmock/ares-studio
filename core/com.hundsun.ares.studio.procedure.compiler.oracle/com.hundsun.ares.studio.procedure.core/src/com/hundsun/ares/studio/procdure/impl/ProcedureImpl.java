/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.procdure.impl;

import java.util.Collection;

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
import com.hundsun.ares.studio.procdure.BizType;
import com.hundsun.ares.studio.procdure.DefineType;
import com.hundsun.ares.studio.core.model.util.EMFJSONUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.core.model.util.ReferenceUtil;
import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.RelatedInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Procedure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getData <em>Data</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getData2 <em>Data2</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getObjectId <em>Object Id</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getHistories <em>Histories</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getFullyQualifiedName <em>Fully Qualified Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getPseudoCode <em>Pseudo Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getInternalVariables <em>Internal Variables</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getBeginCode <em>Begin Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getEndCode <em>End Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getDefineType <em>Define Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getBizType <em>Biz Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getCreateDate <em>Create Date</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getRelatedTableInfo <em>Related Table Info</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#getRelatedBasicDataInfo <em>Related Basic Data Info</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl#isAutoTransaction <em>Auto Transaction</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcedureImpl extends BizInterfaceImpl implements Procedure {
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
	protected static final String DATABASE_EDEFAULT = "";

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
	protected static final String PSEUDO_CODE_EDEFAULT = "";

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
	protected EList<Parameter> internalVariables;

	/**
	 * The default value of the '{@link #getBeginCode() <em>Begin Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeginCode()
	 * @generated
	 * @ordered
	 */
	protected static final String BEGIN_CODE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getBeginCode() <em>Begin Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeginCode()
	 * @generated
	 * @ordered
	 */
	protected String beginCode = BEGIN_CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndCode() <em>End Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndCode()
	 * @generated
	 * @ordered
	 */
	protected static final String END_CODE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getEndCode() <em>End Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndCode()
	 * @generated
	 * @ordered
	 */
	protected String endCode = END_CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected static final String RETURN_TYPE_EDEFAULT = "number";

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected String returnType = RETURN_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefineType() <em>Define Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefineType()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFINE_TYPE_EDEFAULT = "AS";

	/**
	 * The cached value of the '{@link #getDefineType() <em>Define Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefineType()
	 * @generated
	 * @ordered
	 */
	protected String defineType = DEFINE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBizType() <em>Biz Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBizType()
	 * @generated
	 * @ordered
	 */
	protected static final String BIZ_TYPE_EDEFAULT = "function";

	/**
	 * The cached value of the '{@link #getBizType() <em>Biz Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBizType()
	 * @generated
	 * @ordered
	 */
	protected String bizType = BIZ_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreateDate() <em>Create Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateDate()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATE_DATE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getCreateDate() <em>Create Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateDate()
	 * @generated
	 * @ordered
	 */
	protected String createDate = CREATE_DATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRelatedTableInfo() <em>Related Table Info</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedTableInfo()
	 * @generated
	 * @ordered
	 */
	protected EList<RelatedInfo> relatedTableInfo;

	/**
	 * The cached value of the '{@link #getRelatedBasicDataInfo() <em>Related Basic Data Info</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedBasicDataInfo()
	 * @generated
	 * @ordered
	 */
	protected EList<RelatedInfo> relatedBasicDataInfo;

	/**
	 * The default value of the '{@link #isAutoTransaction() <em>Auto Transaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoTransaction()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTO_TRANSACTION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAutoTransaction() <em>Auto Transaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoTransaction()
	 * @generated
	 * @ordered
	 */
	protected boolean autoTransaction = AUTO_TRANSACTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ProcedureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcdurePackage.Literals.PROCEDURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getData() {
		if (data == null) {
			data = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, ProcdurePackage.PROCEDURE__DATA);
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
			data2 = new EcoreEMap<String,EObject>(CorePackage.Literals.ESTRING_TO_EOBJECT_MAP_ENTRY, EStringToEObjectMapEntryImpl.class, this, ProcdurePackage.PROCEDURE__DATA2);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__CHINESE_NAME, oldChineseName, chineseName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__DESCRIPTION, oldDescription, description));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__OBJECT_ID, oldObjectId, objectId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__GROUP, oldGroup, group));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RevisionHistory> getHistories() {
		if (histories == null) {
			histories = new EObjectContainmentEList<RevisionHistory>(RevisionHistory.class, this, ProcdurePackage.PROCEDURE__HISTORIES);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__FULLY_QUALIFIED_NAME, oldFullyQualifiedName, fullyQualifiedName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__DATABASE, oldDatabase, database));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__PSEUDO_CODE, oldPseudoCode, pseudoCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Parameter> getInternalVariables() {
		if (internalVariables == null) {
			internalVariables = new EObjectContainmentEList<Parameter>(Parameter.class, this, ProcdurePackage.PROCEDURE__INTERNAL_VARIABLES);
		}
		return internalVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBeginCode() {
		return beginCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBeginCode(String newBeginCode) {
		String oldBeginCode = beginCode;
		beginCode = newBeginCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__BEGIN_CODE, oldBeginCode, beginCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEndCode() {
		return endCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndCode(String newEndCode) {
		String oldEndCode = endCode;
		endCode = newEndCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__END_CODE, oldEndCode, endCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnType(String newReturnType) {
		String oldReturnType = returnType;
		returnType = newReturnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__RETURN_TYPE, oldReturnType, returnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefineType() {
		return defineType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefineType(String newDefineType) {
		String oldDefineType = defineType;
		defineType = newDefineType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__DEFINE_TYPE, oldDefineType, defineType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBizType() {
		return bizType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBizType(String newBizType) {
		String oldBizType = bizType;
		bizType = newBizType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__BIZ_TYPE, oldBizType, bizType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreateDate(String newCreateDate) {
		String oldCreateDate = createDate;
		createDate = newCreateDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__CREATE_DATE, oldCreateDate, createDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RelatedInfo> getRelatedTableInfo() {
		if (relatedTableInfo == null) {
			relatedTableInfo = new EObjectContainmentEList<RelatedInfo>(RelatedInfo.class, this, ProcdurePackage.PROCEDURE__RELATED_TABLE_INFO);
		}
		return relatedTableInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RelatedInfo> getRelatedBasicDataInfo() {
		if (relatedBasicDataInfo == null) {
			relatedBasicDataInfo = new EObjectContainmentEList<RelatedInfo>(RelatedInfo.class, this, ProcdurePackage.PROCEDURE__RELATED_BASIC_DATA_INFO);
		}
		return relatedBasicDataInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutoTransaction() {
		return autoTransaction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutoTransaction(boolean newAutoTransaction) {
		boolean oldAutoTransaction = autoTransaction;
		autoTransaction = newAutoTransaction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcdurePackage.PROCEDURE__AUTO_TRANSACTION, oldAutoTransaction, autoTransaction));
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
			case ProcdurePackage.PROCEDURE__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
			case ProcdurePackage.PROCEDURE__DATA2:
				return ((InternalEList<?>)getData2()).basicRemove(otherEnd, msgs);
			case ProcdurePackage.PROCEDURE__HISTORIES:
				return ((InternalEList<?>)getHistories()).basicRemove(otherEnd, msgs);
			case ProcdurePackage.PROCEDURE__INTERNAL_VARIABLES:
				return ((InternalEList<?>)getInternalVariables()).basicRemove(otherEnd, msgs);
			case ProcdurePackage.PROCEDURE__RELATED_TABLE_INFO:
				return ((InternalEList<?>)getRelatedTableInfo()).basicRemove(otherEnd, msgs);
			case ProcdurePackage.PROCEDURE__RELATED_BASIC_DATA_INFO:
				return ((InternalEList<?>)getRelatedBasicDataInfo()).basicRemove(otherEnd, msgs);
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
			case ProcdurePackage.PROCEDURE__DATA:
				if (coreType) return getData();
				else return getData().map();
			case ProcdurePackage.PROCEDURE__DATA2:
				if (coreType) return getData2();
				else return getData2().map();
			case ProcdurePackage.PROCEDURE__NAME:
				return getName();
			case ProcdurePackage.PROCEDURE__CHINESE_NAME:
				return getChineseName();
			case ProcdurePackage.PROCEDURE__DESCRIPTION:
				return getDescription();
			case ProcdurePackage.PROCEDURE__OBJECT_ID:
				return getObjectId();
			case ProcdurePackage.PROCEDURE__GROUP:
				return getGroup();
			case ProcdurePackage.PROCEDURE__HISTORIES:
				return getHistories();
			case ProcdurePackage.PROCEDURE__FULLY_QUALIFIED_NAME:
				return getFullyQualifiedName();
			case ProcdurePackage.PROCEDURE__DATABASE:
				return getDatabase();
			case ProcdurePackage.PROCEDURE__PSEUDO_CODE:
				return getPseudoCode();
			case ProcdurePackage.PROCEDURE__INTERNAL_VARIABLES:
				return getInternalVariables();
			case ProcdurePackage.PROCEDURE__BEGIN_CODE:
				return getBeginCode();
			case ProcdurePackage.PROCEDURE__END_CODE:
				return getEndCode();
			case ProcdurePackage.PROCEDURE__RETURN_TYPE:
				return getReturnType();
			case ProcdurePackage.PROCEDURE__DEFINE_TYPE:
				return getDefineType();
			case ProcdurePackage.PROCEDURE__BIZ_TYPE:
				return getBizType();
			case ProcdurePackage.PROCEDURE__CREATE_DATE:
				return getCreateDate();
			case ProcdurePackage.PROCEDURE__RELATED_TABLE_INFO:
				return getRelatedTableInfo();
			case ProcdurePackage.PROCEDURE__RELATED_BASIC_DATA_INFO:
				return getRelatedBasicDataInfo();
			case ProcdurePackage.PROCEDURE__AUTO_TRANSACTION:
				return isAutoTransaction();
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
			case ProcdurePackage.PROCEDURE__DATA:
				((EStructuralFeature.Setting)getData()).set(newValue);
				return;
			case ProcdurePackage.PROCEDURE__DATA2:
				((EStructuralFeature.Setting)getData2()).set(newValue);
				return;
			case ProcdurePackage.PROCEDURE__NAME:
				setName((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__CHINESE_NAME:
				setChineseName((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__OBJECT_ID:
				setObjectId((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__GROUP:
				setGroup((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__HISTORIES:
				getHistories().clear();
				getHistories().addAll((Collection<? extends RevisionHistory>)newValue);
				return;
			case ProcdurePackage.PROCEDURE__FULLY_QUALIFIED_NAME:
				setFullyQualifiedName((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__DATABASE:
				setDatabase((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__PSEUDO_CODE:
				setPseudoCode((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__INTERNAL_VARIABLES:
				getInternalVariables().clear();
				getInternalVariables().addAll((Collection<? extends Parameter>)newValue);
				return;
			case ProcdurePackage.PROCEDURE__BEGIN_CODE:
				setBeginCode((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__END_CODE:
				setEndCode((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__RETURN_TYPE:
				setReturnType((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__DEFINE_TYPE:
				setDefineType((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__BIZ_TYPE:
				setBizType((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__CREATE_DATE:
				setCreateDate((String)newValue);
				return;
			case ProcdurePackage.PROCEDURE__RELATED_TABLE_INFO:
				getRelatedTableInfo().clear();
				getRelatedTableInfo().addAll((Collection<? extends RelatedInfo>)newValue);
				return;
			case ProcdurePackage.PROCEDURE__RELATED_BASIC_DATA_INFO:
				getRelatedBasicDataInfo().clear();
				getRelatedBasicDataInfo().addAll((Collection<? extends RelatedInfo>)newValue);
				return;
			case ProcdurePackage.PROCEDURE__AUTO_TRANSACTION:
				setAutoTransaction((Boolean)newValue);
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
			case ProcdurePackage.PROCEDURE__DATA:
				getData().clear();
				return;
			case ProcdurePackage.PROCEDURE__DATA2:
				getData2().clear();
				return;
			case ProcdurePackage.PROCEDURE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__CHINESE_NAME:
				setChineseName(CHINESE_NAME_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__OBJECT_ID:
				setObjectId(OBJECT_ID_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__GROUP:
				setGroup(GROUP_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__HISTORIES:
				getHistories().clear();
				return;
			case ProcdurePackage.PROCEDURE__FULLY_QUALIFIED_NAME:
				setFullyQualifiedName(FULLY_QUALIFIED_NAME_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__DATABASE:
				setDatabase(DATABASE_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__PSEUDO_CODE:
				setPseudoCode(PSEUDO_CODE_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__INTERNAL_VARIABLES:
				getInternalVariables().clear();
				return;
			case ProcdurePackage.PROCEDURE__BEGIN_CODE:
				setBeginCode(BEGIN_CODE_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__END_CODE:
				setEndCode(END_CODE_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__RETURN_TYPE:
				setReturnType(RETURN_TYPE_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__DEFINE_TYPE:
				setDefineType(DEFINE_TYPE_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__BIZ_TYPE:
				setBizType(BIZ_TYPE_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__CREATE_DATE:
				setCreateDate(CREATE_DATE_EDEFAULT);
				return;
			case ProcdurePackage.PROCEDURE__RELATED_TABLE_INFO:
				getRelatedTableInfo().clear();
				return;
			case ProcdurePackage.PROCEDURE__RELATED_BASIC_DATA_INFO:
				getRelatedBasicDataInfo().clear();
				return;
			case ProcdurePackage.PROCEDURE__AUTO_TRANSACTION:
				setAutoTransaction(AUTO_TRANSACTION_EDEFAULT);
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
			case ProcdurePackage.PROCEDURE__DATA:
				return data != null && !data.isEmpty();
			case ProcdurePackage.PROCEDURE__DATA2:
				return data2 != null && !data2.isEmpty();
			case ProcdurePackage.PROCEDURE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ProcdurePackage.PROCEDURE__CHINESE_NAME:
				return CHINESE_NAME_EDEFAULT == null ? chineseName != null : !CHINESE_NAME_EDEFAULT.equals(chineseName);
			case ProcdurePackage.PROCEDURE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ProcdurePackage.PROCEDURE__OBJECT_ID:
				return OBJECT_ID_EDEFAULT == null ? objectId != null : !OBJECT_ID_EDEFAULT.equals(objectId);
			case ProcdurePackage.PROCEDURE__GROUP:
				return GROUP_EDEFAULT == null ? group != null : !GROUP_EDEFAULT.equals(group);
			case ProcdurePackage.PROCEDURE__HISTORIES:
				return histories != null && !histories.isEmpty();
			case ProcdurePackage.PROCEDURE__FULLY_QUALIFIED_NAME:
				return FULLY_QUALIFIED_NAME_EDEFAULT == null ? fullyQualifiedName != null : !FULLY_QUALIFIED_NAME_EDEFAULT.equals(fullyQualifiedName);
			case ProcdurePackage.PROCEDURE__DATABASE:
				return DATABASE_EDEFAULT == null ? database != null : !DATABASE_EDEFAULT.equals(database);
			case ProcdurePackage.PROCEDURE__PSEUDO_CODE:
				return PSEUDO_CODE_EDEFAULT == null ? pseudoCode != null : !PSEUDO_CODE_EDEFAULT.equals(pseudoCode);
			case ProcdurePackage.PROCEDURE__INTERNAL_VARIABLES:
				return internalVariables != null && !internalVariables.isEmpty();
			case ProcdurePackage.PROCEDURE__BEGIN_CODE:
				return BEGIN_CODE_EDEFAULT == null ? beginCode != null : !BEGIN_CODE_EDEFAULT.equals(beginCode);
			case ProcdurePackage.PROCEDURE__END_CODE:
				return END_CODE_EDEFAULT == null ? endCode != null : !END_CODE_EDEFAULT.equals(endCode);
			case ProcdurePackage.PROCEDURE__RETURN_TYPE:
				return RETURN_TYPE_EDEFAULT == null ? returnType != null : !RETURN_TYPE_EDEFAULT.equals(returnType);
			case ProcdurePackage.PROCEDURE__DEFINE_TYPE:
				return DEFINE_TYPE_EDEFAULT == null ? defineType != null : !DEFINE_TYPE_EDEFAULT.equals(defineType);
			case ProcdurePackage.PROCEDURE__BIZ_TYPE:
				return BIZ_TYPE_EDEFAULT == null ? bizType != null : !BIZ_TYPE_EDEFAULT.equals(bizType);
			case ProcdurePackage.PROCEDURE__CREATE_DATE:
				return CREATE_DATE_EDEFAULT == null ? createDate != null : !CREATE_DATE_EDEFAULT.equals(createDate);
			case ProcdurePackage.PROCEDURE__RELATED_TABLE_INFO:
				return relatedTableInfo != null && !relatedTableInfo.isEmpty();
			case ProcdurePackage.PROCEDURE__RELATED_BASIC_DATA_INFO:
				return relatedBasicDataInfo != null && !relatedBasicDataInfo.isEmpty();
			case ProcdurePackage.PROCEDURE__AUTO_TRANSACTION:
				return autoTransaction != AUTO_TRANSACTION_EDEFAULT;
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
				case ProcdurePackage.PROCEDURE__DATA: return CorePackage.EXTENSIBLE_MODEL__DATA;
				case ProcdurePackage.PROCEDURE__DATA2: return CorePackage.EXTENSIBLE_MODEL__DATA2;
				default: return -1;
			}
		}
		if (baseClass == BasicResourceInfo.class) {
			switch (derivedFeatureID) {
				case ProcdurePackage.PROCEDURE__NAME: return CorePackage.BASIC_RESOURCE_INFO__NAME;
				case ProcdurePackage.PROCEDURE__CHINESE_NAME: return CorePackage.BASIC_RESOURCE_INFO__CHINESE_NAME;
				case ProcdurePackage.PROCEDURE__DESCRIPTION: return CorePackage.BASIC_RESOURCE_INFO__DESCRIPTION;
				case ProcdurePackage.PROCEDURE__OBJECT_ID: return CorePackage.BASIC_RESOURCE_INFO__OBJECT_ID;
				case ProcdurePackage.PROCEDURE__GROUP: return CorePackage.BASIC_RESOURCE_INFO__GROUP;
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
				case ProcdurePackage.PROCEDURE__HISTORIES: return CorePackage.JRES_RESOURCE_INFO__HISTORIES;
				case ProcdurePackage.PROCEDURE__FULLY_QUALIFIED_NAME: return CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME;
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
				case CorePackage.EXTENSIBLE_MODEL__DATA: return ProcdurePackage.PROCEDURE__DATA;
				case CorePackage.EXTENSIBLE_MODEL__DATA2: return ProcdurePackage.PROCEDURE__DATA2;
				default: return -1;
			}
		}
		if (baseClass == BasicResourceInfo.class) {
			switch (baseFeatureID) {
				case CorePackage.BASIC_RESOURCE_INFO__NAME: return ProcdurePackage.PROCEDURE__NAME;
				case CorePackage.BASIC_RESOURCE_INFO__CHINESE_NAME: return ProcdurePackage.PROCEDURE__CHINESE_NAME;
				case CorePackage.BASIC_RESOURCE_INFO__DESCRIPTION: return ProcdurePackage.PROCEDURE__DESCRIPTION;
				case CorePackage.BASIC_RESOURCE_INFO__OBJECT_ID: return ProcdurePackage.PROCEDURE__OBJECT_ID;
				case CorePackage.BASIC_RESOURCE_INFO__GROUP: return ProcdurePackage.PROCEDURE__GROUP;
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
				case CorePackage.JRES_RESOURCE_INFO__HISTORIES: return ProcdurePackage.PROCEDURE__HISTORIES;
				case CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME: return ProcdurePackage.PROCEDURE__FULLY_QUALIFIED_NAME;
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
		result.append(", beginCode: ");
		result.append(beginCode);
		result.append(", endCode: ");
		result.append(endCode);
		result.append(", returnType: ");
		result.append(returnType);
		result.append(", defineType: ");
		result.append(defineType);
		result.append(", bizType: ");
		result.append(bizType);
		result.append(", createDate: ");
		result.append(createDate);
		result.append(", autoTransaction: ");
		result.append(autoTransaction);
		result.append(')');
		return result.toString();
	}

} //ProcedureImpl
