/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EList;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.model.reference.ReferencePackage
 * @generated
 */
public class ReferenceAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ReferencePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ReferencePackage.eINSTANCE;
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
	protected ReferenceSwitch<Adapter> modelSwitch =
		new ReferenceSwitch<Adapter>() {
			@Override
			public Adapter caseReferenceTable(ReferenceTable object) {
				return createReferenceTableAdapter();
			}
			@Override
			public Adapter caseProjectReferenceCollection(ProjectReferenceCollection object) {
				return createProjectReferenceCollectionAdapter();
			}
			@Override
			public Adapter caseProjectToReferencesMapEntry(Map.Entry<IARESProject, ProjectReferenceCollection> object) {
				return createProjectToReferencesMapEntryAdapter();
			}
			@Override
			public Adapter caseReferenceMapEntry(Map.Entry<String, EList<ReferenceInfo>> object) {
				return createReferenceMapEntryAdapter();
			}
			@Override
			public Adapter caseReferenceInfo(ReferenceInfo object) {
				return createReferenceInfoAdapter();
			}
			@Override
			public Adapter caseRelationTable(RelationTable object) {
				return createRelationTableAdapter();
			}
			@Override
			public Adapter caseProjectToRelationsMapEntry(Map.Entry<IARESProject, ProjectRelationCollection> object) {
				return createProjectToRelationsMapEntryAdapter();
			}
			@Override
			public Adapter caseProjectRelationCollection(ProjectRelationCollection object) {
				return createProjectRelationCollectionAdapter();
			}
			@Override
			public Adapter caseRelationInfo(RelationInfo object) {
				return createRelationInfoAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.model.reference.ReferenceTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.model.reference.ReferenceTable
	 * @generated
	 */
	public Adapter createReferenceTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.model.reference.ProjectReferenceCollection <em>Project Reference Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.model.reference.ProjectReferenceCollection
	 * @generated
	 */
	public Adapter createProjectReferenceCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Project To References Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createProjectToReferencesMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createReferenceMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.model.reference.ReferenceInfo
	 * @generated
	 */
	public Adapter createReferenceInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.model.reference.RelationTable <em>Relation Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.model.reference.RelationTable
	 * @generated
	 */
	public Adapter createRelationTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Project To Relations Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createProjectToRelationsMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.model.reference.ProjectRelationCollection <em>Project Relation Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.model.reference.ProjectRelationCollection
	 * @generated
	 */
	public Adapter createProjectRelationCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.model.reference.RelationInfo <em>Relation Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.model.reference.RelationInfo
	 * @generated
	 */
	public Adapter createRelationInfoAdapter() {
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

} //ReferenceAdapterFactory
