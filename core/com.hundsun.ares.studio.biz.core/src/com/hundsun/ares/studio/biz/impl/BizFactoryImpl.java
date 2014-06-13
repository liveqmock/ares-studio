/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.biz.impl;

import com.hundsun.ares.studio.biz.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizFactory;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.Multiplicity;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BizFactoryImpl extends EFactoryImpl implements BizFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BizFactory init() {
		try {
			BizFactory theBizFactory = (BizFactory)EPackage.Registry.INSTANCE.getEFactory(BizPackage.eNS_URI);
			if (theBizFactory != null) {
				return theBizFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BizFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BizFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BizPackage.PARAMETER: return createParameter();
			case BizPackage.BIZ_INTERFACE: return createBizInterface();
			case BizPackage.ARES_OBJECT: return createARESObject();
			case BizPackage.STANDARD_OBJ_FIELD_LIST: return createStandardObjFieldList();
			case BizPackage.STANDARD_OBJ_FIELD: return createStandardObjField();
			case BizPackage.ERROR_INFO: return createErrorInfo();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case BizPackage.PARAM_TYPE:
				return createParamTypeFromString(eDataType, initialValue);
			case BizPackage.MULTIPLICITY:
				return createMultiplicityFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case BizPackage.PARAM_TYPE:
				return convertParamTypeToString(eDataType, instanceValue);
			case BizPackage.MULTIPLICITY:
				return convertMultiplicityToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BizInterface createBizInterface() {
		BizInterfaceImpl bizInterface = new BizInterfaceImpl();
		return bizInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ARESObject createARESObject() {
		ARESObjectImpl aresObject = new ARESObjectImpl();
		return aresObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardObjFieldList createStandardObjFieldList() {
		StandardObjFieldListImpl standardObjFieldList = new StandardObjFieldListImpl();
		return standardObjFieldList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardObjField createStandardObjField() {
		StandardObjFieldImpl standardObjField = new StandardObjFieldImpl();
		return standardObjField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ErrorInfo createErrorInfo() {
		ErrorInfoImpl errorInfo = new ErrorInfoImpl();
		return errorInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParamType createParamTypeFromString(EDataType eDataType, String initialValue) {
		ParamType result = ParamType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertParamTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Multiplicity createMultiplicityFromString(EDataType eDataType, String initialValue) {
		Multiplicity result = Multiplicity.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultiplicityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BizPackage getBizPackage() {
		return (BizPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BizPackage getPackage() {
		return BizPackage.eINSTANCE;
	}

} //BizFactoryImpl
