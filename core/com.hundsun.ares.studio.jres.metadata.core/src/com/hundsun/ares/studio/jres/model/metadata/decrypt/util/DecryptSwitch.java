/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem;
import com.hundsun.ares.studio.jres.metadata.service.IDictionaryType;
import com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem;
import com.hundsun.ares.studio.jres.metadata.service.IStandardDataType;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue;
import com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeTypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage
 * @generated
 */
public class DecryptSwitch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DecryptPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecryptSwitch() {
		if (modelPackage == null) {
			modelPackage = DecryptPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T1 doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T1 doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case DecryptPackage.ITYPE_DEFAULT_VALUE: {
				ITypeDefaultValue iTypeDefaultValue = (ITypeDefaultValue)theEObject;
				T1 result = caseITypeDefaultValue(iTypeDefaultValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.ISTANDARD_DATA_TYPE: {
				IStandardDataType iStandardDataType = (IStandardDataType)theEObject;
				T1 result = caseIStandardDataType(iStandardDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.IBUSINESS_DATA_TYPE: {
				IBusinessDataType iBusinessDataType = (IBusinessDataType)theEObject;
				T1 result = caseIBusinessDataType(iBusinessDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.ISTANDARD_FIELD: {
				IStandardField iStandardField = (IStandardField)theEObject;
				T1 result = caseIStandardField(iStandardField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.IDICTIONARY_TYPE: {
				IDictionaryType iDictionaryType = (IDictionaryType)theEObject;
				T1 result = caseIDictionaryType(iDictionaryType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.IDICTIONARY_ITEM: {
				IDictionaryItem iDictionaryItem = (IDictionaryItem)theEObject;
				T1 result = caseIDictionaryItem(iDictionaryItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.IERROR_NO_ITEM: {
				IErrorNoItem iErrorNoItem = (IErrorNoItem)theEObject;
				T1 result = caseIErrorNoItem(iErrorNoItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.IUSER_CONSTANT_ITEM: {
				IUserConstantItem iUserConstantItem = (IUserConstantItem)theEObject;
				T1 result = caseIUserConstantItem(iUserConstantItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.DE_METADATA_ITEM: {
				DeMetadataItem<?> deMetadataItem = (DeMetadataItem<?>)theEObject;
				T1 result = caseDeMetadataItem(deMetadataItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.DE_TYPE_DEFAULT_VALUE: {
				DeTypeDefaultValue deTypeDefaultValue = (DeTypeDefaultValue)theEObject;
				T1 result = caseDeTypeDefaultValue(deTypeDefaultValue);
				if (result == null) result = caseDeMetadataItem(deTypeDefaultValue);
				if (result == null) result = caseITypeDefaultValue(deTypeDefaultValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.DE_STANDARD_DATA_TYPE: {
				DeStandardDataType deStandardDataType = (DeStandardDataType)theEObject;
				T1 result = caseDeStandardDataType(deStandardDataType);
				if (result == null) result = caseDeMetadataItem(deStandardDataType);
				if (result == null) result = caseIStandardDataType(deStandardDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.DE_BUSINESS_DATA_TYPE: {
				DeBusinessDataType deBusinessDataType = (DeBusinessDataType)theEObject;
				T1 result = caseDeBusinessDataType(deBusinessDataType);
				if (result == null) result = caseDeMetadataItem(deBusinessDataType);
				if (result == null) result = caseIBusinessDataType(deBusinessDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.DE_STANDARD_FIELD: {
				DeStandardField deStandardField = (DeStandardField)theEObject;
				T1 result = caseDeStandardField(deStandardField);
				if (result == null) result = caseDeMetadataItem(deStandardField);
				if (result == null) result = caseIStandardField(deStandardField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.DE_DICTIONARY_TYPE: {
				DeDictionaryType deDictionaryType = (DeDictionaryType)theEObject;
				T1 result = caseDeDictionaryType(deDictionaryType);
				if (result == null) result = caseDeMetadataItem(deDictionaryType);
				if (result == null) result = caseIDictionaryType(deDictionaryType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.DE_DICTIONARY_ITEM: {
				DeDictionaryItem deDictionaryItem = (DeDictionaryItem)theEObject;
				T1 result = caseDeDictionaryItem(deDictionaryItem);
				if (result == null) result = caseIDictionaryItem(deDictionaryItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.DE_CONSTANT_ITEM: {
				DeConstantItem deConstantItem = (DeConstantItem)theEObject;
				T1 result = caseDeConstantItem(deConstantItem);
				if (result == null) result = caseDeMetadataItem(deConstantItem);
				if (result == null) result = caseIUserConstantItem(deConstantItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DecryptPackage.DE_ERROR_NO_ITEM: {
				DeErrorNoItem deErrorNoItem = (DeErrorNoItem)theEObject;
				T1 result = caseDeErrorNoItem(deErrorNoItem);
				if (result == null) result = caseDeMetadataItem(deErrorNoItem);
				if (result == null) result = caseIErrorNoItem(deErrorNoItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IType Default Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IType Default Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseITypeDefaultValue(ITypeDefaultValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IStandard Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IStandard Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIStandardDataType(IStandardDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IBusiness Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IBusiness Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIBusinessDataType(IBusinessDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IStandard Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IStandard Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIStandardField(IStandardField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IDictionary Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IDictionary Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIDictionaryType(IDictionaryType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IDictionary Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IDictionary Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIDictionaryItem(IDictionaryItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IError No Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IError No Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIErrorNoItem(IErrorNoItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IUser Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IUser Constant Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIUserConstantItem(IUserConstantItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>De Metadata Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>De Metadata Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends MetadataItem> T1 caseDeMetadataItem(DeMetadataItem<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>De Type Default Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>De Type Default Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDeTypeDefaultValue(DeTypeDefaultValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>De Standard Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>De Standard Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDeStandardDataType(DeStandardDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>De Business Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>De Business Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDeBusinessDataType(DeBusinessDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>De Standard Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>De Standard Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDeStandardField(DeStandardField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>De Dictionary Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>De Dictionary Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDeDictionaryType(DeDictionaryType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>De Dictionary Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>De Dictionary Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDeDictionaryItem(DeDictionaryItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>De Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>De Constant Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDeConstantItem(DeConstantItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>De Error No Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>De Error No Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDeErrorNoItem(DeErrorNoItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T1 defaultCase(EObject object) {
		return null;
	}

} //DecryptSwitch
