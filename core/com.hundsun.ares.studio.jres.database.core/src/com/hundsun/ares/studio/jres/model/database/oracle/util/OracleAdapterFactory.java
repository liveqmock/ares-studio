/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage
 * @generated
 */
public class OracleAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OraclePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = OraclePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OracleSwitch<Adapter> modelSwitch =
		new OracleSwitch<Adapter>() {
			@Override
			public Adapter caseOracleTableProperty(OracleTableProperty object) {
				return createOracleTablePropertyAdapter();
			}
			@Override
			public Adapter caseOracleIndexProperty(OracleIndexProperty object) {
				return createOracleIndexPropertyAdapter();
			}
			@Override
			public Adapter caseOracleViewProperty(OracleViewProperty object) {
				return createOracleViewPropertyAdapter();
			}
			@Override
			public Adapter caseOracleModuleProperty(OracleModuleProperty object) {
				return createOracleModulePropertyAdapter();
			}
			@Override
			public Adapter caseOracleSpaceResourceData(OracleSpaceResourceData object) {
				return createOracleSpaceResourceDataAdapter();
			}
			@Override
			public Adapter caseTableSpaceRelation(TableSpaceRelation object) {
				return createTableSpaceRelationAdapter();
			}
			@Override
			public Adapter caseTableSpace(TableSpace object) {
				return createTableSpaceAdapter();
			}
			@Override
			public Adapter caseOracleUserResourceData(OracleUserResourceData object) {
				return createOracleUserResourceDataAdapter();
			}
			@Override
			public Adapter caseOracleUser(OracleUser object) {
				return createOracleUserAdapter();
			}
			@Override
			public Adapter caseOraclePrivilege(OraclePrivilege object) {
				return createOraclePrivilegeAdapter();
			}
			@Override
			public Adapter caseTriggerResourceData(TriggerResourceData object) {
				return createTriggerResourceDataAdapter();
			}
			@Override
			public Adapter caseSequenceResourceData(SequenceResourceData object) {
				return createSequenceResourceDataAdapter();
			}
			@Override
			public Adapter caseDatabaseModuleExtensibleProperty(DatabaseModuleExtensibleProperty object) {
				return createDatabaseModuleExtensiblePropertyAdapter();
			}
			@Override
			public Adapter caseOracleSequenceProperty(OracleSequenceProperty object) {
				return createOracleSequencePropertyAdapter();
			}
			@Override
			public Adapter caseIJSONData(IJSONData object) {
				return createIJSONDataAdapter();
			}
			@Override
			public Adapter caseExtensibleModel(ExtensibleModel object) {
				return createExtensibleModelAdapter();
			}
			@Override
			public Adapter caseBasicResourceInfo(BasicResourceInfo object) {
				return createBasicResourceInfoAdapter();
			}
			@Override
			public Adapter caseIReferenceProvider(IReferenceProvider object) {
				return createIReferenceProviderAdapter();
			}
			@Override
			public Adapter caseJRESResourceInfo(JRESResourceInfo object) {
				return createJRESResourceInfoAdapter();
			}
			@Override
			public Adapter caseDatabaseResourceData(DatabaseResourceData object) {
				return createDatabaseResourceDataAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty <em>Table Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty
	 * @generated
	 */
	public Adapter createOracleTablePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty <em>Index Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty
	 * @generated
	 */
	public Adapter createOracleIndexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleViewProperty <em>View Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleViewProperty
	 * @generated
	 */
	public Adapter createOracleViewPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleModuleProperty <em>Module Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleModuleProperty
	 * @generated
	 */
	public Adapter createOracleModulePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData <em>Space Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData
	 * @generated
	 */
	public Adapter createOracleSpaceResourceDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation <em>Table Space Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation
	 * @generated
	 */
	public Adapter createTableSpaceRelationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace <em>Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpace
	 * @generated
	 */
	public Adapter createTableSpaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData <em>User Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData
	 * @generated
	 */
	public Adapter createOracleUserResourceDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUser
	 * @generated
	 */
	public Adapter createOracleUserAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege <em>Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege
	 * @generated
	 */
	public Adapter createOraclePrivilegeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData <em>Trigger Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData
	 * @generated
	 */
	public Adapter createTriggerResourceDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData <em>Sequence Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData
	 * @generated
	 */
	public Adapter createSequenceResourceDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty <em>Database Module Extensible Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty
	 * @generated
	 */
	public Adapter createDatabaseModuleExtensiblePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSequenceProperty <em>Sequence Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleSequenceProperty
	 * @generated
	 */
	public Adapter createOracleSequencePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.core.model.ExtensibleModel <em>Extensible Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModel
	 * @generated
	 */
	public Adapter createExtensibleModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.core.model.BasicResourceInfo <em>Basic Resource Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.core.model.BasicResourceInfo
	 * @generated
	 */
	public Adapter createBasicResourceInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.core.model.IReferenceProvider <em>IReference Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.core.model.IReferenceProvider
	 * @generated
	 */
	public Adapter createIReferenceProviderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.core.model.IJSONData <em>IJSON Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.core.model.IJSONData
	 * @generated
	 */
	public Adapter createIJSONDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.core.model.JRESResourceInfo <em>JRES Resource Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.core.model.JRESResourceInfo
	 * @generated
	 */
	public Adapter createJRESResourceInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.DatabaseResourceData <em>Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.DatabaseResourceData
	 * @generated
	 */
	public Adapter createDatabaseResourceDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //OracleAdapterFactory
