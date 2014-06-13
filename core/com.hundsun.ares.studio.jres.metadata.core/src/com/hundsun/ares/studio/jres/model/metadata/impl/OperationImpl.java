/**
 * 源程序名称：OperationImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.Operation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl#getFile <em>File</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl#getOutPath <em>Out Path</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl#getFunctionName <em>Function Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl#getCode <em>Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl#getUixml <em>Uixml</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl#isAutobuild <em>Autobuild</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationImpl extends EObjectImpl implements Operation {
	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected String file = FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutPath() <em>Out Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutPath()
	 * @generated
	 * @ordered
	 */
	protected static final String OUT_PATH_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getOutPath() <em>Out Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutPath()
	 * @generated
	 * @ordered
	 */
	protected String outPath = OUT_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getFunctionName() <em>Function Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionName()
	 * @generated
	 * @ordered
	 */
	protected static final String FUNCTION_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getFunctionName() <em>Function Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionName()
	 * @generated
	 * @ordered
	 */
	protected String functionName = FUNCTION_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected String code = CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUixml() <em>Uixml</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUixml()
	 * @generated
	 * @ordered
	 */
	protected static final String UIXML_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getUixml() <em>Uixml</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUixml()
	 * @generated
	 * @ordered
	 */
	protected String uixml = UIXML_EDEFAULT;

	/**
	 * The default value of the '{@link #isAutobuild() <em>Autobuild</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutobuild()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTOBUILD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAutobuild() <em>Autobuild</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutobuild()
	 * @generated
	 * @ordered
	 */
	protected boolean autobuild = AUTOBUILD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.OPERATION__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFile() {
		return file;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFile(String newFile) {
		String oldFile = file;
		file = newFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.OPERATION__FILE, oldFile, file));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutPath() {
		return outPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutPath(String newOutPath) {
		String oldOutPath = outPath;
		outPath = newOutPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.OPERATION__OUT_PATH, oldOutPath, outPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionName(String newFunctionName) {
		String oldFunctionName = functionName;
		functionName = newFunctionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.OPERATION__FUNCTION_NAME, oldFunctionName, functionName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCode() {
		return code;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCode(String newCode) {
		String oldCode = code;
		code = newCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.OPERATION__CODE, oldCode, code));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUixml() {
		return uixml;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUixml(String newUixml) {
		String oldUixml = uixml;
		uixml = newUixml;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.OPERATION__UIXML, oldUixml, uixml));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutobuild() {
		return autobuild;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutobuild(boolean newAutobuild) {
		boolean oldAutobuild = autobuild;
		autobuild = newAutobuild;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.OPERATION__AUTOBUILD, oldAutobuild, autobuild));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.OPERATION__TITLE:
				return getTitle();
			case MetadataPackage.OPERATION__FILE:
				return getFile();
			case MetadataPackage.OPERATION__OUT_PATH:
				return getOutPath();
			case MetadataPackage.OPERATION__FUNCTION_NAME:
				return getFunctionName();
			case MetadataPackage.OPERATION__CODE:
				return getCode();
			case MetadataPackage.OPERATION__UIXML:
				return getUixml();
			case MetadataPackage.OPERATION__AUTOBUILD:
				return isAutobuild();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetadataPackage.OPERATION__TITLE:
				setTitle((String)newValue);
				return;
			case MetadataPackage.OPERATION__FILE:
				setFile((String)newValue);
				return;
			case MetadataPackage.OPERATION__OUT_PATH:
				setOutPath((String)newValue);
				return;
			case MetadataPackage.OPERATION__FUNCTION_NAME:
				setFunctionName((String)newValue);
				return;
			case MetadataPackage.OPERATION__CODE:
				setCode((String)newValue);
				return;
			case MetadataPackage.OPERATION__UIXML:
				setUixml((String)newValue);
				return;
			case MetadataPackage.OPERATION__AUTOBUILD:
				setAutobuild((Boolean)newValue);
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
			case MetadataPackage.OPERATION__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case MetadataPackage.OPERATION__FILE:
				setFile(FILE_EDEFAULT);
				return;
			case MetadataPackage.OPERATION__OUT_PATH:
				setOutPath(OUT_PATH_EDEFAULT);
				return;
			case MetadataPackage.OPERATION__FUNCTION_NAME:
				setFunctionName(FUNCTION_NAME_EDEFAULT);
				return;
			case MetadataPackage.OPERATION__CODE:
				setCode(CODE_EDEFAULT);
				return;
			case MetadataPackage.OPERATION__UIXML:
				setUixml(UIXML_EDEFAULT);
				return;
			case MetadataPackage.OPERATION__AUTOBUILD:
				setAutobuild(AUTOBUILD_EDEFAULT);
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
			case MetadataPackage.OPERATION__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case MetadataPackage.OPERATION__FILE:
				return FILE_EDEFAULT == null ? file != null : !FILE_EDEFAULT.equals(file);
			case MetadataPackage.OPERATION__OUT_PATH:
				return OUT_PATH_EDEFAULT == null ? outPath != null : !OUT_PATH_EDEFAULT.equals(outPath);
			case MetadataPackage.OPERATION__FUNCTION_NAME:
				return FUNCTION_NAME_EDEFAULT == null ? functionName != null : !FUNCTION_NAME_EDEFAULT.equals(functionName);
			case MetadataPackage.OPERATION__CODE:
				return CODE_EDEFAULT == null ? code != null : !CODE_EDEFAULT.equals(code);
			case MetadataPackage.OPERATION__UIXML:
				return UIXML_EDEFAULT == null ? uixml != null : !UIXML_EDEFAULT.equals(uixml);
			case MetadataPackage.OPERATION__AUTOBUILD:
				return autobuild != AUTOBUILD_EDEFAULT;
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
		result.append(" (title: ");
		result.append(title);
		result.append(", file: ");
		result.append(file);
		result.append(", outPath: ");
		result.append(outPath);
		result.append(", functionName: ");
		result.append(functionName);
		result.append(", code: ");
		result.append(code);
		result.append(", uixml: ");
		result.append(uixml);
		result.append(", autobuild: ");
		result.append(autobuild);
		result.append(')');
		return result.toString();
	}

} //OperationImpl
