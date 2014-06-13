/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend.impl;

import com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage;
import com.hundsun.ares.studio.cres.extend.cresextend.FileDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.GccDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.MvcDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.ProcDefine;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cres Project Extend Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getCName <em>CName</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getShortCName <em>Short CName</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getManager <em>Manager</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getDeveloper <em>Developer</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getUser <em>User</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getRelation <em>Relation</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getWriter <em>Writer</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getNote <em>Note</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getHeadFile <em>Head File</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getProcDefine <em>Proc Define</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getGccDefine <em>Gcc Define</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getMvcDefine <em>Mvc Define</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl#getFuncDefine <em>Func Define</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CresProjectExtendPropertyImpl extends EObjectImpl implements CresProjectExtendProperty {
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getCName() <em>CName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCName()
	 * @generated
	 * @ordered
	 */
	protected static final String CNAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getCName() <em>CName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCName()
	 * @generated
	 * @ordered
	 */
	protected String cName = CNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getShortCName() <em>Short CName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortCName()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_CNAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getShortCName() <em>Short CName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortCName()
	 * @generated
	 * @ordered
	 */
	protected String shortCName = SHORT_CNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getManager() <em>Manager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManager()
	 * @generated
	 * @ordered
	 */
	protected static final String MANAGER_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getManager() <em>Manager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManager()
	 * @generated
	 * @ordered
	 */
	protected String manager = MANAGER_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeveloper() <em>Developer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeveloper()
	 * @generated
	 * @ordered
	 */
	protected static final String DEVELOPER_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDeveloper() <em>Developer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeveloper()
	 * @generated
	 * @ordered
	 */
	protected String developer = DEVELOPER_EDEFAULT;

	/**
	 * The default value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected String user = USER_EDEFAULT;

	/**
	 * The default value of the '{@link #getRelation() <em>Relation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelation()
	 * @generated
	 * @ordered
	 */
	protected static final String RELATION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getRelation() <em>Relation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelation()
	 * @generated
	 * @ordered
	 */
	protected String relation = RELATION_EDEFAULT;

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
	 * The default value of the '{@link #getWriter() <em>Writer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWriter()
	 * @generated
	 * @ordered
	 */
	protected static final String WRITER_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getWriter() <em>Writer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWriter()
	 * @generated
	 * @ordered
	 */
	protected String writer = WRITER_EDEFAULT;

	/**
	 * The default value of the '{@link #getNote() <em>Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNote()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getNote() <em>Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNote()
	 * @generated
	 * @ordered
	 */
	protected String note = NOTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeadFile() <em>Head File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadFile()
	 * @generated
	 * @ordered
	 */
	protected static final String HEAD_FILE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getHeadFile() <em>Head File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadFile()
	 * @generated
	 * @ordered
	 */
	protected String headFile = HEAD_FILE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProcDefine() <em>Proc Define</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcDefine()
	 * @generated
	 * @ordered
	 */
	protected EList<ProcDefine> procDefine;

	/**
	 * The cached value of the '{@link #getGccDefine() <em>Gcc Define</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGccDefine()
	 * @generated
	 * @ordered
	 */
	protected EList<GccDefine> gccDefine;

	/**
	 * The cached value of the '{@link #getMvcDefine() <em>Mvc Define</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMvcDefine()
	 * @generated
	 * @ordered
	 */
	protected EList<MvcDefine> mvcDefine;

	/**
	 * The cached value of the '{@link #getFuncDefine() <em>Func Define</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFuncDefine()
	 * @generated
	 * @ordered
	 */
	protected EList<FileDefine> funcDefine;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CresProjectExtendPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCName() {
		return cName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCName(String newCName) {
		String oldCName = cName;
		cName = newCName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__CNAME, oldCName, cName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShortCName() {
		return shortCName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortCName(String newShortCName) {
		String oldShortCName = shortCName;
		shortCName = newShortCName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME, oldShortCName, shortCName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManager(String newManager) {
		String oldManager = manager;
		manager = newManager;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MANAGER, oldManager, manager));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDeveloper() {
		return developer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeveloper(String newDeveloper) {
		String oldDeveloper = developer;
		developer = newDeveloper;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER, oldDeveloper, developer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUser() {
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUser(String newUser) {
		String oldUser = user;
		user = newUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__USER, oldUser, user));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelation(String newRelation) {
		String oldRelation = relation;
		relation = newRelation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__RELATION, oldRelation, relation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWriter() {
		return writer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWriter(String newWriter) {
		String oldWriter = writer;
		writer = newWriter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__WRITER, oldWriter, writer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNote() {
		return note;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNote(String newNote) {
		String oldNote = note;
		note = newNote;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NOTE, oldNote, note));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHeadFile() {
		return headFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeadFile(String newHeadFile) {
		String oldHeadFile = headFile;
		headFile = newHeadFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE, oldHeadFile, headFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProcDefine> getProcDefine() {
		if (procDefine == null) {
			procDefine = new EObjectContainmentEList<ProcDefine>(ProcDefine.class, this, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE);
		}
		return procDefine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GccDefine> getGccDefine() {
		if (gccDefine == null) {
			gccDefine = new EObjectContainmentEList<GccDefine>(GccDefine.class, this, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE);
		}
		return gccDefine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MvcDefine> getMvcDefine() {
		if (mvcDefine == null) {
			mvcDefine = new EObjectContainmentEList<MvcDefine>(MvcDefine.class, this, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE);
		}
		return mvcDefine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FileDefine> getFuncDefine() {
		if (funcDefine == null) {
			funcDefine = new EObjectContainmentEList<FileDefine>(FileDefine.class, this, CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE);
		}
		return funcDefine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE:
				return ((InternalEList<?>)getProcDefine()).basicRemove(otherEnd, msgs);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE:
				return ((InternalEList<?>)getGccDefine()).basicRemove(otherEnd, msgs);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE:
				return ((InternalEList<?>)getMvcDefine()).basicRemove(otherEnd, msgs);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE:
				return ((InternalEList<?>)getFuncDefine()).basicRemove(otherEnd, msgs);
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
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__VERSION:
				return getVersion();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__CNAME:
				return getCName();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME:
				return getShortCName();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__ID:
				return getId();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MANAGER:
				return getManager();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER:
				return getDeveloper();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__USER:
				return getUser();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__RELATION:
				return getRelation();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NAME:
				return getName();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__WRITER:
				return getWriter();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NOTE:
				return getNote();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE:
				return getHeadFile();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE:
				return getProcDefine();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE:
				return getGccDefine();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE:
				return getMvcDefine();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE:
				return getFuncDefine();
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
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__VERSION:
				setVersion((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__CNAME:
				setCName((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME:
				setShortCName((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__ID:
				setId((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MANAGER:
				setManager((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER:
				setDeveloper((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__USER:
				setUser((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__RELATION:
				setRelation((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NAME:
				setName((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__WRITER:
				setWriter((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NOTE:
				setNote((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE:
				setHeadFile((String)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE:
				getProcDefine().clear();
				getProcDefine().addAll((Collection<? extends ProcDefine>)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE:
				getGccDefine().clear();
				getGccDefine().addAll((Collection<? extends GccDefine>)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE:
				getMvcDefine().clear();
				getMvcDefine().addAll((Collection<? extends MvcDefine>)newValue);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE:
				getFuncDefine().clear();
				getFuncDefine().addAll((Collection<? extends FileDefine>)newValue);
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
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__CNAME:
				setCName(CNAME_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME:
				setShortCName(SHORT_CNAME_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__ID:
				setId(ID_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MANAGER:
				setManager(MANAGER_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER:
				setDeveloper(DEVELOPER_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__USER:
				setUser(USER_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__RELATION:
				setRelation(RELATION_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__WRITER:
				setWriter(WRITER_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NOTE:
				setNote(NOTE_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE:
				setHeadFile(HEAD_FILE_EDEFAULT);
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE:
				getProcDefine().clear();
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE:
				getGccDefine().clear();
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE:
				getMvcDefine().clear();
				return;
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE:
				getFuncDefine().clear();
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
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__CNAME:
				return CNAME_EDEFAULT == null ? cName != null : !CNAME_EDEFAULT.equals(cName);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME:
				return SHORT_CNAME_EDEFAULT == null ? shortCName != null : !SHORT_CNAME_EDEFAULT.equals(shortCName);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MANAGER:
				return MANAGER_EDEFAULT == null ? manager != null : !MANAGER_EDEFAULT.equals(manager);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER:
				return DEVELOPER_EDEFAULT == null ? developer != null : !DEVELOPER_EDEFAULT.equals(developer);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__USER:
				return USER_EDEFAULT == null ? user != null : !USER_EDEFAULT.equals(user);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__RELATION:
				return RELATION_EDEFAULT == null ? relation != null : !RELATION_EDEFAULT.equals(relation);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__WRITER:
				return WRITER_EDEFAULT == null ? writer != null : !WRITER_EDEFAULT.equals(writer);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__NOTE:
				return NOTE_EDEFAULT == null ? note != null : !NOTE_EDEFAULT.equals(note);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE:
				return HEAD_FILE_EDEFAULT == null ? headFile != null : !HEAD_FILE_EDEFAULT.equals(headFile);
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE:
				return procDefine != null && !procDefine.isEmpty();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE:
				return gccDefine != null && !gccDefine.isEmpty();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE:
				return mvcDefine != null && !mvcDefine.isEmpty();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE:
				return funcDefine != null && !funcDefine.isEmpty();
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
		result.append(" (version: ");
		result.append(version);
		result.append(", cName: ");
		result.append(cName);
		result.append(", shortCName: ");
		result.append(shortCName);
		result.append(", id: ");
		result.append(id);
		result.append(", manager: ");
		result.append(manager);
		result.append(", developer: ");
		result.append(developer);
		result.append(", user: ");
		result.append(user);
		result.append(", relation: ");
		result.append(relation);
		result.append(", name: ");
		result.append(name);
		result.append(", writer: ");
		result.append(writer);
		result.append(", note: ");
		result.append(note);
		result.append(", headFile: ");
		result.append(headFile);
		result.append(')');
		return result.toString();
	}

} //CresProjectExtendPropertyImpl
