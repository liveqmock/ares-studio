/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.IJSONData;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.*;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleModuleProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleViewProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;
import com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData;

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
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage
 * @generated
 */
public class OracleSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OraclePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleSwitch() {
		if (modelPackage == null) {
			modelPackage = OraclePackage.eINSTANCE;
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
			case OraclePackage.ORACLE_TABLE_PROPERTY: {
				OracleTableProperty oracleTableProperty = (OracleTableProperty)theEObject;
				T result = caseOracleTableProperty(oracleTableProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.ORACLE_INDEX_PROPERTY: {
				OracleIndexProperty oracleIndexProperty = (OracleIndexProperty)theEObject;
				T result = caseOracleIndexProperty(oracleIndexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.ORACLE_VIEW_PROPERTY: {
				OracleViewProperty oracleViewProperty = (OracleViewProperty)theEObject;
				T result = caseOracleViewProperty(oracleViewProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.ORACLE_MODULE_PROPERTY: {
				OracleModuleProperty oracleModuleProperty = (OracleModuleProperty)theEObject;
				T result = caseOracleModuleProperty(oracleModuleProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA: {
				OracleSpaceResourceData oracleSpaceResourceData = (OracleSpaceResourceData)theEObject;
				T result = caseOracleSpaceResourceData(oracleSpaceResourceData);
				if (result == null) result = caseDatabaseResourceData(oracleSpaceResourceData);
				if (result == null) result = caseJRESResourceInfo(oracleSpaceResourceData);
				if (result == null) result = caseExtensibleModel(oracleSpaceResourceData);
				if (result == null) result = caseBasicResourceInfo(oracleSpaceResourceData);
				if (result == null) result = caseIReferenceProvider(oracleSpaceResourceData);
				if (result == null) result = caseIJSONData(oracleSpaceResourceData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.TABLE_SPACE_RELATION: {
				TableSpaceRelation tableSpaceRelation = (TableSpaceRelation)theEObject;
				T result = caseTableSpaceRelation(tableSpaceRelation);
				if (result == null) result = caseExtensibleModel(tableSpaceRelation);
				if (result == null) result = caseIJSONData(tableSpaceRelation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.TABLE_SPACE: {
				TableSpace tableSpace = (TableSpace)theEObject;
				T result = caseTableSpace(tableSpace);
				if (result == null) result = caseExtensibleModel(tableSpace);
				if (result == null) result = caseIJSONData(tableSpace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.ORACLE_USER_RESOURCE_DATA: {
				OracleUserResourceData oracleUserResourceData = (OracleUserResourceData)theEObject;
				T result = caseOracleUserResourceData(oracleUserResourceData);
				if (result == null) result = caseDatabaseResourceData(oracleUserResourceData);
				if (result == null) result = caseJRESResourceInfo(oracleUserResourceData);
				if (result == null) result = caseExtensibleModel(oracleUserResourceData);
				if (result == null) result = caseBasicResourceInfo(oracleUserResourceData);
				if (result == null) result = caseIReferenceProvider(oracleUserResourceData);
				if (result == null) result = caseIJSONData(oracleUserResourceData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.ORACLE_USER: {
				OracleUser oracleUser = (OracleUser)theEObject;
				T result = caseOracleUser(oracleUser);
				if (result == null) result = caseExtensibleModel(oracleUser);
				if (result == null) result = caseIJSONData(oracleUser);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.ORACLE_PRIVILEGE: {
				OraclePrivilege oraclePrivilege = (OraclePrivilege)theEObject;
				T result = caseOraclePrivilege(oraclePrivilege);
				if (result == null) result = caseExtensibleModel(oraclePrivilege);
				if (result == null) result = caseIJSONData(oraclePrivilege);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.TRIGGER_RESOURCE_DATA: {
				TriggerResourceData triggerResourceData = (TriggerResourceData)theEObject;
				T result = caseTriggerResourceData(triggerResourceData);
				if (result == null) result = caseDatabaseResourceData(triggerResourceData);
				if (result == null) result = caseJRESResourceInfo(triggerResourceData);
				if (result == null) result = caseExtensibleModel(triggerResourceData);
				if (result == null) result = caseBasicResourceInfo(triggerResourceData);
				if (result == null) result = caseIReferenceProvider(triggerResourceData);
				if (result == null) result = caseIJSONData(triggerResourceData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.SEQUENCE_RESOURCE_DATA: {
				SequenceResourceData sequenceResourceData = (SequenceResourceData)theEObject;
				T result = caseSequenceResourceData(sequenceResourceData);
				if (result == null) result = caseDatabaseResourceData(sequenceResourceData);
				if (result == null) result = caseJRESResourceInfo(sequenceResourceData);
				if (result == null) result = caseExtensibleModel(sequenceResourceData);
				if (result == null) result = caseBasicResourceInfo(sequenceResourceData);
				if (result == null) result = caseIReferenceProvider(sequenceResourceData);
				if (result == null) result = caseIJSONData(sequenceResourceData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY: {
				DatabaseModuleExtensibleProperty databaseModuleExtensibleProperty = (DatabaseModuleExtensibleProperty)theEObject;
				T result = caseDatabaseModuleExtensibleProperty(databaseModuleExtensibleProperty);
				if (result == null) result = caseExtensibleModel(databaseModuleExtensibleProperty);
				if (result == null) result = caseIJSONData(databaseModuleExtensibleProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OraclePackage.ORACLE_SEQUENCE_PROPERTY: {
				OracleSequenceProperty oracleSequenceProperty = (OracleSequenceProperty)theEObject;
				T result = caseOracleSequenceProperty(oracleSequenceProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOracleTableProperty(OracleTableProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Index Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOracleIndexProperty(OracleIndexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>View Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>View Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOracleViewProperty(OracleViewProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOracleModuleProperty(OracleModuleProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space Resource Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOracleSpaceResourceData(OracleSpaceResourceData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Space Relation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Space Relation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableSpaceRelation(TableSpaceRelation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Space</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Space</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableSpace(TableSpace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Resource Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOracleUserResourceData(OracleUserResourceData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOracleUser(OracleUser object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Privilege</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Privilege</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOraclePrivilege(OraclePrivilege object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trigger Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trigger Resource Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTriggerResourceData(TriggerResourceData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence Resource Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSequenceResourceData(SequenceResourceData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Database Module Extensible Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Database Module Extensible Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDatabaseModuleExtensibleProperty(DatabaseModuleExtensibleProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOracleSequenceProperty(OracleSequenceProperty object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDatabaseResourceData(DatabaseResourceData object) {
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

} //OracleSwitch
