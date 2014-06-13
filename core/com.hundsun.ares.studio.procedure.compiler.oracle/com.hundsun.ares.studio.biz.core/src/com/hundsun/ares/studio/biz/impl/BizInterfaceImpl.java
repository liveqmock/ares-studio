/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.biz.impl;

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

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ErrorInfo;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.BizInterfaceImpl#isInputCollection <em>Input Collection</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.BizInterfaceImpl#isOutputCollection <em>Output Collection</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.BizInterfaceImpl#getInputParameters <em>Input Parameters</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.BizInterfaceImpl#getOutputParameters <em>Output Parameters</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.BizInterfaceImpl#getInterfaceFlag <em>Interface Flag</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.BizInterfaceImpl#getErrorInfos <em>Error Infos</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BizInterfaceImpl extends EObjectImpl implements BizInterface {
	/**
	 * The default value of the '{@link #isInputCollection() <em>Input Collection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInputCollection()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INPUT_COLLECTION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInputCollection() <em>Input Collection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInputCollection()
	 * @generated
	 * @ordered
	 */
	protected boolean inputCollection = INPUT_COLLECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #isOutputCollection() <em>Output Collection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOutputCollection()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OUTPUT_COLLECTION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOutputCollection() <em>Output Collection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOutputCollection()
	 * @generated
	 * @ordered
	 */
	protected boolean outputCollection = OUTPUT_COLLECTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInputParameters() <em>Input Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> inputParameters;

	/**
	 * The cached value of the '{@link #getOutputParameters() <em>Output Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> outputParameters;

	/**
	 * The default value of the '{@link #getInterfaceFlag() <em>Interface Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaceFlag()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERFACE_FLAG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInterfaceFlag() <em>Interface Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaceFlag()
	 * @generated
	 * @ordered
	 */
	protected String interfaceFlag = INTERFACE_FLAG_EDEFAULT;

	/**
	 * The cached value of the '{@link #getErrorInfos() <em>Error Infos</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorInfos()
	 * @generated
	 * @ordered
	 */
	protected EList<ErrorNoItem> errorInfos;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BizInterfaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BizPackage.Literals.BIZ_INTERFACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInputCollection() {
		return inputCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInputCollection(boolean newInputCollection) {
		boolean oldInputCollection = inputCollection;
		inputCollection = newInputCollection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BizPackage.BIZ_INTERFACE__INPUT_COLLECTION, oldInputCollection, inputCollection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOutputCollection() {
		return outputCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputCollection(boolean newOutputCollection) {
		boolean oldOutputCollection = outputCollection;
		outputCollection = newOutputCollection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BizPackage.BIZ_INTERFACE__OUTPUT_COLLECTION, oldOutputCollection, outputCollection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Parameter> getInputParameters() {
		if (inputParameters == null) {
			inputParameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, BizPackage.BIZ_INTERFACE__INPUT_PARAMETERS);
		}
		return inputParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Parameter> getOutputParameters() {
		if (outputParameters == null) {
			outputParameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, BizPackage.BIZ_INTERFACE__OUTPUT_PARAMETERS);
		}
		return outputParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInterfaceFlag() {
		return interfaceFlag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterfaceFlag(String newInterfaceFlag) {
		String oldInterfaceFlag = interfaceFlag;
		interfaceFlag = newInterfaceFlag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BizPackage.BIZ_INTERFACE__INTERFACE_FLAG, oldInterfaceFlag, interfaceFlag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ErrorNoItem> getErrorInfos() {
		if (errorInfos == null) {
			errorInfos = new EObjectContainmentEList<ErrorNoItem>(ErrorNoItem.class, this, BizPackage.BIZ_INTERFACE__ERROR_INFOS);
		}
		return errorInfos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BizPackage.BIZ_INTERFACE__INPUT_PARAMETERS:
				return ((InternalEList<?>)getInputParameters()).basicRemove(otherEnd, msgs);
			case BizPackage.BIZ_INTERFACE__OUTPUT_PARAMETERS:
				return ((InternalEList<?>)getOutputParameters()).basicRemove(otherEnd, msgs);
			case BizPackage.BIZ_INTERFACE__ERROR_INFOS:
				return ((InternalEList<?>)getErrorInfos()).basicRemove(otherEnd, msgs);
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
			case BizPackage.BIZ_INTERFACE__INPUT_COLLECTION:
				return isInputCollection();
			case BizPackage.BIZ_INTERFACE__OUTPUT_COLLECTION:
				return isOutputCollection();
			case BizPackage.BIZ_INTERFACE__INPUT_PARAMETERS:
				return getInputParameters();
			case BizPackage.BIZ_INTERFACE__OUTPUT_PARAMETERS:
				return getOutputParameters();
			case BizPackage.BIZ_INTERFACE__INTERFACE_FLAG:
				return getInterfaceFlag();
			case BizPackage.BIZ_INTERFACE__ERROR_INFOS:
				return getErrorInfos();
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
			case BizPackage.BIZ_INTERFACE__INPUT_COLLECTION:
				setInputCollection((Boolean)newValue);
				return;
			case BizPackage.BIZ_INTERFACE__OUTPUT_COLLECTION:
				setOutputCollection((Boolean)newValue);
				return;
			case BizPackage.BIZ_INTERFACE__INPUT_PARAMETERS:
				getInputParameters().clear();
				getInputParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case BizPackage.BIZ_INTERFACE__OUTPUT_PARAMETERS:
				getOutputParameters().clear();
				getOutputParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case BizPackage.BIZ_INTERFACE__INTERFACE_FLAG:
				setInterfaceFlag((String)newValue);
				return;
			case BizPackage.BIZ_INTERFACE__ERROR_INFOS:
				getErrorInfos().clear();
				getErrorInfos().addAll((Collection<? extends ErrorNoItem>)newValue);
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
			case BizPackage.BIZ_INTERFACE__INPUT_COLLECTION:
				setInputCollection(INPUT_COLLECTION_EDEFAULT);
				return;
			case BizPackage.BIZ_INTERFACE__OUTPUT_COLLECTION:
				setOutputCollection(OUTPUT_COLLECTION_EDEFAULT);
				return;
			case BizPackage.BIZ_INTERFACE__INPUT_PARAMETERS:
				getInputParameters().clear();
				return;
			case BizPackage.BIZ_INTERFACE__OUTPUT_PARAMETERS:
				getOutputParameters().clear();
				return;
			case BizPackage.BIZ_INTERFACE__INTERFACE_FLAG:
				setInterfaceFlag(INTERFACE_FLAG_EDEFAULT);
				return;
			case BizPackage.BIZ_INTERFACE__ERROR_INFOS:
				getErrorInfos().clear();
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
			case BizPackage.BIZ_INTERFACE__INPUT_COLLECTION:
				return inputCollection != INPUT_COLLECTION_EDEFAULT;
			case BizPackage.BIZ_INTERFACE__OUTPUT_COLLECTION:
				return outputCollection != OUTPUT_COLLECTION_EDEFAULT;
			case BizPackage.BIZ_INTERFACE__INPUT_PARAMETERS:
				return inputParameters != null && !inputParameters.isEmpty();
			case BizPackage.BIZ_INTERFACE__OUTPUT_PARAMETERS:
				return outputParameters != null && !outputParameters.isEmpty();
			case BizPackage.BIZ_INTERFACE__INTERFACE_FLAG:
				return INTERFACE_FLAG_EDEFAULT == null ? interfaceFlag != null : !INTERFACE_FLAG_EDEFAULT.equals(interfaceFlag);
			case BizPackage.BIZ_INTERFACE__ERROR_INFOS:
				return errorInfos != null && !errorInfos.isEmpty();
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
		result.append(" (inputCollection: ");
		result.append(inputCollection);
		result.append(", outputCollection: ");
		result.append(outputCollection);
		result.append(", interfaceFlag: ");
		result.append(interfaceFlag);
		result.append(')');
		return result.toString();
	}

} //BizInterfaceImpl
