/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference.impl;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.*;
import com.hundsun.ares.studio.core.IObjectProvider;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReferenceFactoryImpl extends EFactoryImpl implements ReferenceFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferenceFactory init() {
		try {
			ReferenceFactory theReferenceFactory = (ReferenceFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.hundsun.com/ares/studio/jres/reference/1.0.0"); 
			if (theReferenceFactory != null) {
				return theReferenceFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ReferenceFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceFactoryImpl() {
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
			case ReferencePackage.REFERENCE_TABLE: return createReferenceTable();
			case ReferencePackage.PROJECT_REFERENCE_COLLECTION: return createProjectReferenceCollection();
			case ReferencePackage.PROJECT_TO_REFERENCES_MAP_ENTRY: return (EObject)createProjectToReferencesMapEntry();
			case ReferencePackage.REFERENCE_MAP_ENTRY: return (EObject)createReferenceMapEntry();
			case ReferencePackage.REFERENCE_INFO: return createReferenceInfo();
			case ReferencePackage.RELATION_TABLE: return createRelationTable();
			case ReferencePackage.PROJECT_TO_RELATIONS_MAP_ENTRY: return (EObject)createProjectToRelationsMapEntry();
			case ReferencePackage.PROJECT_RELATION_COLLECTION: return createProjectRelationCollection();
			case ReferencePackage.RELATION_INFO: return createRelationInfo();
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
			case ReferencePackage.IARES_PROJECT:
				return createIARESProjectFromString(eDataType, initialValue);
			case ReferencePackage.IARES_RESOURCE:
				return createIARESResourceFromString(eDataType, initialValue);
			case ReferencePackage.IOBJECT_PROVIDER:
				return createIObjectProviderFromString(eDataType, initialValue);
			case ReferencePackage.IARES_BUNDLE:
				return createIARESBundleFromString(eDataType, initialValue);
			case ReferencePackage.RELATIONS:
				return createRelationsFromString(eDataType, initialValue);
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
			case ReferencePackage.IARES_PROJECT:
				return convertIARESProjectToString(eDataType, instanceValue);
			case ReferencePackage.IARES_RESOURCE:
				return convertIARESResourceToString(eDataType, instanceValue);
			case ReferencePackage.IOBJECT_PROVIDER:
				return convertIObjectProviderToString(eDataType, instanceValue);
			case ReferencePackage.IARES_BUNDLE:
				return convertIARESBundleToString(eDataType, instanceValue);
			case ReferencePackage.RELATIONS:
				return convertRelationsToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceTable createReferenceTable() {
		ReferenceTableImpl referenceTable = new ReferenceTableImpl();
		return referenceTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProjectReferenceCollection createProjectReferenceCollection() {
		ProjectReferenceCollectionImpl projectReferenceCollection = new ProjectReferenceCollectionImpl();
		return projectReferenceCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<IARESProject, ProjectReferenceCollection> createProjectToReferencesMapEntry() {
		ProjectToReferencesMapEntryImpl projectToReferencesMapEntry = new ProjectToReferencesMapEntryImpl();
		return projectToReferencesMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, EList<ReferenceInfo>> createReferenceMapEntry() {
		ReferenceMapEntryImpl referenceMapEntry = new ReferenceMapEntryImpl();
		return referenceMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceInfo createReferenceInfo() {
		ReferenceInfoImpl referenceInfo = new ReferenceInfoImpl();
		return referenceInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationTable createRelationTable() {
		RelationTableImpl relationTable = new RelationTableImpl();
		return relationTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<IARESProject, ProjectRelationCollection> createProjectToRelationsMapEntry() {
		ProjectToRelationsMapEntryImpl projectToRelationsMapEntry = new ProjectToRelationsMapEntryImpl();
		return projectToRelationsMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProjectRelationCollection createProjectRelationCollection() {
		ProjectRelationCollectionImpl projectRelationCollection = new ProjectRelationCollectionImpl();
		return projectRelationCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationInfo createRelationInfo() {
		RelationInfoImpl relationInfo = new RelationInfoImpl();
		return relationInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IARESProject createIARESProjectFromString(EDataType eDataType, String initialValue) {
		return (IARESProject)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIARESProjectToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IARESResource createIARESResourceFromString(EDataType eDataType, String initialValue) {
		return (IARESResource)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIARESResourceToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IObjectProvider createIObjectProviderFromString(EDataType eDataType, String initialValue) {
		return (IObjectProvider)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIObjectProviderToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IARESBundle createIARESBundleFromString(EDataType eDataType, String initialValue) {
		return (IARESBundle)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIARESBundleToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IRelations createRelationsFromString(EDataType eDataType, String initialValue) {
		return (IRelations)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRelationsToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferencePackage getReferencePackage() {
		return (ReferencePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ReferencePackage getPackage() {
		return ReferencePackage.eINSTANCE;
	}

} //ReferenceFactoryImpl
