/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference.util;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.model.reference.*;
import com.hundsun.ares.studio.model.reference.ProjectReferenceCollection;
import com.hundsun.ares.studio.model.reference.ProjectRelationCollection;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.model.reference.ReferencePackage;
import com.hundsun.ares.studio.model.reference.ReferenceTable;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.model.reference.RelationTable;

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
 * @see com.hundsun.ares.studio.model.reference.ReferencePackage
 * @generated
 */
public class ReferenceSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ReferencePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceSwitch() {
		if (modelPackage == null) {
			modelPackage = ReferencePackage.eINSTANCE;
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
			case ReferencePackage.REFERENCE_TABLE: {
				ReferenceTable referenceTable = (ReferenceTable)theEObject;
				T result = caseReferenceTable(referenceTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReferencePackage.PROJECT_REFERENCE_COLLECTION: {
				ProjectReferenceCollection projectReferenceCollection = (ProjectReferenceCollection)theEObject;
				T result = caseProjectReferenceCollection(projectReferenceCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReferencePackage.PROJECT_TO_REFERENCES_MAP_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<IARESProject, ProjectReferenceCollection> projectToReferencesMapEntry = (Map.Entry<IARESProject, ProjectReferenceCollection>)theEObject;
				T result = caseProjectToReferencesMapEntry(projectToReferencesMapEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReferencePackage.REFERENCE_MAP_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, EList<ReferenceInfo>> referenceMapEntry = (Map.Entry<String, EList<ReferenceInfo>>)theEObject;
				T result = caseReferenceMapEntry(referenceMapEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReferencePackage.REFERENCE_INFO: {
				ReferenceInfo referenceInfo = (ReferenceInfo)theEObject;
				T result = caseReferenceInfo(referenceInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReferencePackage.RELATION_TABLE: {
				RelationTable relationTable = (RelationTable)theEObject;
				T result = caseRelationTable(relationTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReferencePackage.PROJECT_TO_RELATIONS_MAP_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<IARESProject, ProjectRelationCollection> projectToRelationsMapEntry = (Map.Entry<IARESProject, ProjectRelationCollection>)theEObject;
				T result = caseProjectToRelationsMapEntry(projectToRelationsMapEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReferencePackage.PROJECT_RELATION_COLLECTION: {
				ProjectRelationCollection projectRelationCollection = (ProjectRelationCollection)theEObject;
				T result = caseProjectRelationCollection(projectRelationCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReferencePackage.RELATION_INFO: {
				RelationInfo relationInfo = (RelationInfo)theEObject;
				T result = caseRelationInfo(relationInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceTable(ReferenceTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Project Reference Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Project Reference Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProjectReferenceCollection(ProjectReferenceCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Project To References Map Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Project To References Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProjectToReferencesMapEntry(Map.Entry<IARESProject, ProjectReferenceCollection> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceMapEntry(Map.Entry<String, EList<ReferenceInfo>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceInfo(ReferenceInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relation Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relation Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationTable(RelationTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Project To Relations Map Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Project To Relations Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProjectToRelationsMapEntry(Map.Entry<IARESProject, ProjectRelationCollection> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Project Relation Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Project Relation Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProjectRelationCollection(ProjectRelationCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relation Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relation Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationInfo(RelationInfo object) {
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

} //ReferenceSwitch
