/**
 */
package com.hundsun.ares.studio.core.model.util;

import com.hundsun.ares.studio.core.model.*;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
 * @see com.hundsun.ares.studio.core.model.CorePackage
 * @generated
 */
public class CoreSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CorePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreSwitch() {
		if (modelPackage == null) {
			modelPackage = CorePackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
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
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CorePackage.JRES_RESOURCE_INFO: {
				JRESResourceInfo jresResourceInfo = (JRESResourceInfo)theEObject;
				T result = caseJRESResourceInfo(jresResourceInfo);
				if (result == null) result = caseExtensibleModel(jresResourceInfo);
				if (result == null) result = caseBasicResourceInfo(jresResourceInfo);
				if (result == null) result = caseIReferenceProvider(jresResourceInfo);
				if (result == null) result = caseIJSONData(jresResourceInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.BASIC_RESOURCE_INFO: {
				BasicResourceInfo basicResourceInfo = (BasicResourceInfo)theEObject;
				T result = caseBasicResourceInfo(basicResourceInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.REVISION_HISTORY: {
				RevisionHistory revisionHistory = (RevisionHistory)theEObject;
				T result = caseRevisionHistory(revisionHistory);
				if (result == null) result = caseExtensibleModel(revisionHistory);
				if (result == null) result = caseIJSONData(revisionHistory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.EXTENSIBLE_MODEL: {
				ExtensibleModel extensibleModel = (ExtensibleModel)theEObject;
				T result = caseExtensibleModel(extensibleModel);
				if (result == null) result = caseIJSONData(extensibleModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.ESTRING_TO_EOBJECT_MAP_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, EObject> eStringToEObjectMapEntry = (Map.Entry<String, EObject>)theEObject;
				T result = caseEStringToEObjectMapEntry(eStringToEObjectMapEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.REFERENCE: {
				Reference reference = (Reference)theEObject;
				T result = caseReference(reference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.REFERENCE_WITH_NAMESPACE: {
				ReferenceWithNamespace referenceWithNamespace = (ReferenceWithNamespace)theEObject;
				T result = caseReferenceWithNamespace(referenceWithNamespace);
				if (result == null) result = caseReference(referenceWithNamespace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IREFERENCE_PROVIDER: {
				IReferenceProvider iReferenceProvider = (IReferenceProvider)theEObject;
				T result = caseIReferenceProvider(iReferenceProvider);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IJSON_DATA: {
				IJSONData ijsonData = (IJSONData)theEObject;
				T result = caseIJSONData(ijsonData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY: {
				ExtensibleModelConfigProperty extensibleModelConfigProperty = (ExtensibleModelConfigProperty)theEObject;
				T result = caseExtensibleModelConfigProperty(extensibleModelConfigProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE: {
				ExtensibleModelAttribute extensibleModelAttribute = (ExtensibleModelAttribute)theEObject;
				T result = caseExtensibleModelAttribute(extensibleModelAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.USER_EXTENSIBLE_PROPERTY: {
				UserExtensibleProperty userExtensibleProperty = (UserExtensibleProperty)theEObject;
				T result = caseUserExtensibleProperty(userExtensibleProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.MODULE_EXTENSIBLE_MODEL: {
				ModuleExtensibleModel moduleExtensibleModel = (ModuleExtensibleModel)theEObject;
				T result = caseModuleExtensibleModel(moduleExtensibleModel);
				if (result == null) result = caseExtensibleModel(moduleExtensibleModel);
				if (result == null) result = caseIJSONData(moduleExtensibleModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.PROJECT_EXTENSIBLE_MODEL: {
				ProjectExtensibleModel projectExtensibleModel = (ProjectExtensibleModel)theEObject;
				T result = caseProjectExtensibleModel(projectExtensibleModel);
				if (result == null) result = caseExtensibleModel(projectExtensibleModel);
				if (result == null) result = caseIJSONData(projectExtensibleModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.PROJECT_REVISION_HISTORY_PROPERTY: {
				ProjectRevisionHistoryProperty projectRevisionHistoryProperty = (ProjectRevisionHistoryProperty)theEObject;
				T result = caseProjectRevisionHistoryProperty(projectRevisionHistoryProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.MODULE_REVISION_HISTORY_LIST: {
				ModuleRevisionHistoryList moduleRevisionHistoryList = (ModuleRevisionHistoryList)theEObject;
				T result = caseModuleRevisionHistoryList(moduleRevisionHistoryList);
				if (result == null) result = caseJRESResourceInfo(moduleRevisionHistoryList);
				if (result == null) result = caseExtensibleModel(moduleRevisionHistoryList);
				if (result == null) result = caseBasicResourceInfo(moduleRevisionHistoryList);
				if (result == null) result = caseIReferenceProvider(moduleRevisionHistoryList);
				if (result == null) result = caseIJSONData(moduleRevisionHistoryList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>JRES Resource Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>JRES Resource Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJRESResourceInfo(JRESResourceInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Basic Resource Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Basic Resource Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBasicResourceInfo(BasicResourceInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Revision History</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Revision History</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRevisionHistory(RevisionHistory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extensible Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extensible Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtensibleModel(ExtensibleModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EString To EObject Map Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EString To EObject Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEStringToEObjectMapEntry(Map.Entry<String, EObject> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReference(Reference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference With Namespace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference With Namespace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceWithNamespace(ReferenceWithNamespace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IReference Provider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IReference Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIReferenceProvider(IReferenceProvider object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IJSON Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IJSON Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIJSONData(IJSONData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extensible Model Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extensible Model Config Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtensibleModelConfigProperty(ExtensibleModelConfigProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extensible Model Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extensible Model Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtensibleModelAttribute(ExtensibleModelAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Extensible Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Extensible Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserExtensibleProperty(UserExtensibleProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Extensible Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Extensible Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModuleExtensibleModel(ModuleExtensibleModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Project Extensible Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Project Extensible Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProjectExtensibleModel(ProjectExtensibleModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Project Revision History Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Project Revision History Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProjectRevisionHistoryProperty(ProjectRevisionHistoryProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Revision History List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Revision History List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModuleRevisionHistoryList(ModuleRevisionHistoryList object) {
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
	public T defaultCase(EObject object) {
		return null;
	}

} //CoreSwitch
