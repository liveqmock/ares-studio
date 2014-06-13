/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.model.reference.ReferenceFactory
 * @model kind="package"
 * @generated
 */
public interface ReferencePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "reference";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/jres/reference/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "reference";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReferencePackage eINSTANCE = com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.reference.impl.ReferenceTableImpl <em>Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferenceTableImpl
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getReferenceTable()
	 * @generated
	 */
	int REFERENCE_TABLE = 0;

	/**
	 * The feature id for the '<em><b>Projects</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_TABLE__PROJECTS = 0;

	/**
	 * The number of structural features of the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_TABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.reference.impl.ProjectReferenceCollectionImpl <em>Project Reference Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.reference.impl.ProjectReferenceCollectionImpl
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getProjectReferenceCollection()
	 * @generated
	 */
	int PROJECT_REFERENCE_COLLECTION = 1;

	/**
	 * The feature id for the '<em><b>References</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_REFERENCE_COLLECTION__REFERENCES = 0;

	/**
	 * The number of structural features of the '<em>Project Reference Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_REFERENCE_COLLECTION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.reference.impl.ProjectToReferencesMapEntryImpl <em>Project To References Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.reference.impl.ProjectToReferencesMapEntryImpl
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getProjectToReferencesMapEntry()
	 * @generated
	 */
	int PROJECT_TO_REFERENCES_MAP_ENTRY = 2;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_TO_REFERENCES_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_TO_REFERENCES_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Project To References Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_TO_REFERENCES_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.reference.impl.ReferenceMapEntryImpl <em>Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferenceMapEntryImpl
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getReferenceMapEntry()
	 * @generated
	 */
	int REFERENCE_MAP_ENTRY = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.reference.impl.ReferenceInfoImpl <em>Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferenceInfoImpl
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getReferenceInfo()
	 * @generated
	 */
	int REFERENCE_INFO = 4;

	/**
	 * The feature id for the '<em><b>Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_INFO__REF_NAME = 0;

	/**
	 * The feature id for the '<em><b>Ref Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_INFO__REF_NAMESPACE = 1;

	/**
	 * The feature id for the '<em><b>Ref Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_INFO__REF_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_INFO__RESOURCE = 3;

	/**
	 * The feature id for the '<em><b>Object Provider</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_INFO__OBJECT_PROVIDER = 4;

	/**
	 * The number of structural features of the '<em>Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_INFO_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.reference.impl.RelationTableImpl <em>Relation Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.reference.impl.RelationTableImpl
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getRelationTable()
	 * @generated
	 */
	int RELATION_TABLE = 5;

	/**
	 * The feature id for the '<em><b>Projects</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_TABLE__PROJECTS = 0;

	/**
	 * The number of structural features of the '<em>Relation Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_TABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.reference.impl.ProjectToRelationsMapEntryImpl <em>Project To Relations Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.reference.impl.ProjectToRelationsMapEntryImpl
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getProjectToRelationsMapEntry()
	 * @generated
	 */
	int PROJECT_TO_RELATIONS_MAP_ENTRY = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_TO_RELATIONS_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_TO_RELATIONS_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Project To Relations Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_TO_RELATIONS_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.reference.impl.ProjectRelationCollectionImpl <em>Project Relation Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.reference.impl.ProjectRelationCollectionImpl
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getProjectRelationCollection()
	 * @generated
	 */
	int PROJECT_RELATION_COLLECTION = 7;

	/**
	 * The number of structural features of the '<em>Project Relation Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_RELATION_COLLECTION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.reference.impl.RelationInfoImpl <em>Relation Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.reference.impl.RelationInfoImpl
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getRelationInfo()
	 * @generated
	 */
	int RELATION_INFO = 8;

	/**
	 * The feature id for the '<em><b>Host Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INFO__HOST_RESOURCE = 0;

	/**
	 * The feature id for the '<em><b>Used Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INFO__USED_REF_NAME = 1;

	/**
	 * The feature id for the '<em><b>Used Ref Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INFO__USED_REF_NAMESPACE = 2;

	/**
	 * The feature id for the '<em><b>Used Ref Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INFO__USED_REF_TYPE = 3;

	/**
	 * The number of structural features of the '<em>Relation Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INFO_FEATURE_COUNT = 4;


	/**
	 * The meta object id for the '<em>Relations</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.reference.IRelations
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getRelations()
	 * @generated
	 */
	int RELATIONS = 13;

	/**
	 * The meta object id for the '<em>IARES Project</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.IARESProject
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getIARESProject()
	 * @generated
	 */
	int IARES_PROJECT = 9;

	/**
	 * The meta object id for the '<em>IARES Resource</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.IARESResource
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getIARESResource()
	 * @generated
	 */
	int IARES_RESOURCE = 10;


	/**
	 * The meta object id for the '<em>IObject Provider</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.reference.IObjectProvider
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getIObjectProvider()
	 * @generated
	 */
	int IOBJECT_PROVIDER = 11;


	/**
	 * The meta object id for the '<em>IARES Bundle</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.IARESBundle
	 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getIARESBundle()
	 * @generated
	 */
	int IARES_BUNDLE = 12;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.model.reference.ReferenceTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ReferenceTable
	 * @generated
	 */
	EClass getReferenceTable();

	/**
	 * Returns the meta object for the map '{@link com.hundsun.ares.studio.model.reference.ReferenceTable#getProjects <em>Projects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Projects</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ReferenceTable#getProjects()
	 * @see #getReferenceTable()
	 * @generated
	 */
	EReference getReferenceTable_Projects();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.model.reference.ProjectReferenceCollection <em>Project Reference Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project Reference Collection</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ProjectReferenceCollection
	 * @generated
	 */
	EClass getProjectReferenceCollection();

	/**
	 * Returns the meta object for the map '{@link com.hundsun.ares.studio.model.reference.ProjectReferenceCollection#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>References</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ProjectReferenceCollection#getReferences()
	 * @see #getProjectReferenceCollection()
	 * @generated
	 */
	EReference getProjectReferenceCollection_References();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Project To References Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project To References Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="com.hundsun.ares.studio.model.reference.IARESProject"
	 *        valueType="com.hundsun.ares.studio.model.reference.ProjectReferenceCollection" valueContainment="true"
	 * @generated
	 */
	EClass getProjectToReferencesMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getProjectToReferencesMapEntry()
	 * @generated
	 */
	EAttribute getProjectToReferencesMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getProjectToReferencesMapEntry()
	 * @generated
	 */
	EReference getProjectToReferencesMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="com.hundsun.ares.studio.model.reference.ReferenceInfo" valueMany="true"
	 * @generated
	 */
	EClass getReferenceMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getReferenceMapEntry()
	 * @generated
	 */
	EAttribute getReferenceMapEntry_Key();

	/**
	 * Returns the meta object for the reference list '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getReferenceMapEntry()
	 * @generated
	 */
	EReference getReferenceMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Info</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ReferenceInfo
	 * @generated
	 */
	EClass getReferenceInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefName <em>Ref Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ref Name</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefName()
	 * @see #getReferenceInfo()
	 * @generated
	 */
	EAttribute getReferenceInfo_RefName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefNamespace <em>Ref Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ref Namespace</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefNamespace()
	 * @see #getReferenceInfo()
	 * @generated
	 */
	EAttribute getReferenceInfo_RefNamespace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefType <em>Ref Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ref Type</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefType()
	 * @see #getReferenceInfo()
	 * @generated
	 */
	EAttribute getReferenceInfo_RefType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ReferenceInfo#getResource()
	 * @see #getReferenceInfo()
	 * @generated
	 */
	EAttribute getReferenceInfo_Resource();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getObjectProvider <em>Object Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Provider</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ReferenceInfo#getObjectProvider()
	 * @see #getReferenceInfo()
	 * @generated
	 */
	EAttribute getReferenceInfo_ObjectProvider();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.model.reference.RelationTable <em>Relation Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation Table</em>'.
	 * @see com.hundsun.ares.studio.model.reference.RelationTable
	 * @generated
	 */
	EClass getRelationTable();

	/**
	 * Returns the meta object for the map '{@link com.hundsun.ares.studio.model.reference.RelationTable#getProjects <em>Projects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Projects</em>'.
	 * @see com.hundsun.ares.studio.model.reference.RelationTable#getProjects()
	 * @see #getRelationTable()
	 * @generated
	 */
	EReference getRelationTable_Projects();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Project To Relations Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project To Relations Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="com.hundsun.ares.studio.model.reference.IARESProject"
	 *        valueType="com.hundsun.ares.studio.model.reference.ProjectRelationCollection" valueContainment="true"
	 * @generated
	 */
	EClass getProjectToRelationsMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getProjectToRelationsMapEntry()
	 * @generated
	 */
	EAttribute getProjectToRelationsMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getProjectToRelationsMapEntry()
	 * @generated
	 */
	EReference getProjectToRelationsMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.model.reference.ProjectRelationCollection <em>Project Relation Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project Relation Collection</em>'.
	 * @see com.hundsun.ares.studio.model.reference.ProjectRelationCollection
	 * @generated
	 */
	EClass getProjectRelationCollection();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.model.reference.RelationInfo <em>Relation Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation Info</em>'.
	 * @see com.hundsun.ares.studio.model.reference.RelationInfo
	 * @generated
	 */
	EClass getRelationInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.model.reference.RelationInfo#getHostResource <em>Host Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Host Resource</em>'.
	 * @see com.hundsun.ares.studio.model.reference.RelationInfo#getHostResource()
	 * @see #getRelationInfo()
	 * @generated
	 */
	EAttribute getRelationInfo_HostResource();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefName <em>Used Ref Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Used Ref Name</em>'.
	 * @see com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefName()
	 * @see #getRelationInfo()
	 * @generated
	 */
	EAttribute getRelationInfo_UsedRefName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefNamespace <em>Used Ref Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Used Ref Namespace</em>'.
	 * @see com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefNamespace()
	 * @see #getRelationInfo()
	 * @generated
	 */
	EAttribute getRelationInfo_UsedRefNamespace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefType <em>Used Ref Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Used Ref Type</em>'.
	 * @see com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefType()
	 * @see #getRelationInfo()
	 * @generated
	 */
	EAttribute getRelationInfo_UsedRefType();

	/**
	 * Returns the meta object for data type '{@link com.hundsun.ares.studio.model.reference.IRelations <em>Relations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Relations</em>'.
	 * @see com.hundsun.ares.studio.model.reference.IRelations
	 * @model instanceClass="com.hundsun.ares.studio.model.reference.IRelations"
	 * @generated
	 */
	EDataType getRelations();

	/**
	 * Returns the meta object for data type '{@link com.hundsun.ares.studio.core.IARESProject <em>IARES Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IARES Project</em>'.
	 * @see com.hundsun.ares.studio.core.IARESProject
	 * @model instanceClass="com.hundsun.ares.studio.core.IARESProject"
	 * @generated
	 */
	EDataType getIARESProject();

	/**
	 * Returns the meta object for data type '{@link com.hundsun.ares.studio.core.IARESResource <em>IARES Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IARES Resource</em>'.
	 * @see com.hundsun.ares.studio.core.IARESResource
	 * @model instanceClass="com.hundsun.ares.studio.core.IARESResource"
	 * @generated
	 */
	EDataType getIARESResource();

	/**
	 * Returns the meta object for data type '{@link com.hundsun.ares.studio.reference.IObjectProvider <em>IObject Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IObject Provider</em>'.
	 * @see com.hundsun.ares.studio.reference.IObjectProvider
	 * @model instanceClass="com.hundsun.ares.studio.reference.IObjectProvider"
	 * @generated
	 */
	EDataType getIObjectProvider();

	/**
	 * Returns the meta object for data type '{@link com.hundsun.ares.studio.core.IARESBundle <em>IARES Bundle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IARES Bundle</em>'.
	 * @see com.hundsun.ares.studio.core.IARESBundle
	 * @model instanceClass="com.hundsun.ares.studio.core.IARESBundle"
	 * @generated
	 */
	EDataType getIARESBundle();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ReferenceFactory getReferenceFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.reference.impl.ReferenceTableImpl <em>Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferenceTableImpl
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getReferenceTable()
		 * @generated
		 */
		EClass REFERENCE_TABLE = eINSTANCE.getReferenceTable();

		/**
		 * The meta object literal for the '<em><b>Projects</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_TABLE__PROJECTS = eINSTANCE.getReferenceTable_Projects();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.reference.impl.ProjectReferenceCollectionImpl <em>Project Reference Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.reference.impl.ProjectReferenceCollectionImpl
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getProjectReferenceCollection()
		 * @generated
		 */
		EClass PROJECT_REFERENCE_COLLECTION = eINSTANCE.getProjectReferenceCollection();

		/**
		 * The meta object literal for the '<em><b>References</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT_REFERENCE_COLLECTION__REFERENCES = eINSTANCE.getProjectReferenceCollection_References();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.reference.impl.ProjectToReferencesMapEntryImpl <em>Project To References Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.reference.impl.ProjectToReferencesMapEntryImpl
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getProjectToReferencesMapEntry()
		 * @generated
		 */
		EClass PROJECT_TO_REFERENCES_MAP_ENTRY = eINSTANCE.getProjectToReferencesMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT_TO_REFERENCES_MAP_ENTRY__KEY = eINSTANCE.getProjectToReferencesMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT_TO_REFERENCES_MAP_ENTRY__VALUE = eINSTANCE.getProjectToReferencesMapEntry_Value();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.reference.impl.ReferenceMapEntryImpl <em>Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferenceMapEntryImpl
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getReferenceMapEntry()
		 * @generated
		 */
		EClass REFERENCE_MAP_ENTRY = eINSTANCE.getReferenceMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_MAP_ENTRY__KEY = eINSTANCE.getReferenceMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_MAP_ENTRY__VALUE = eINSTANCE.getReferenceMapEntry_Value();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.reference.impl.ReferenceInfoImpl <em>Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferenceInfoImpl
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getReferenceInfo()
		 * @generated
		 */
		EClass REFERENCE_INFO = eINSTANCE.getReferenceInfo();

		/**
		 * The meta object literal for the '<em><b>Ref Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_INFO__REF_NAME = eINSTANCE.getReferenceInfo_RefName();

		/**
		 * The meta object literal for the '<em><b>Ref Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_INFO__REF_NAMESPACE = eINSTANCE.getReferenceInfo_RefNamespace();

		/**
		 * The meta object literal for the '<em><b>Ref Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_INFO__REF_TYPE = eINSTANCE.getReferenceInfo_RefType();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_INFO__RESOURCE = eINSTANCE.getReferenceInfo_Resource();

		/**
		 * The meta object literal for the '<em><b>Object Provider</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_INFO__OBJECT_PROVIDER = eINSTANCE.getReferenceInfo_ObjectProvider();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.reference.impl.RelationTableImpl <em>Relation Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.reference.impl.RelationTableImpl
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getRelationTable()
		 * @generated
		 */
		EClass RELATION_TABLE = eINSTANCE.getRelationTable();

		/**
		 * The meta object literal for the '<em><b>Projects</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION_TABLE__PROJECTS = eINSTANCE.getRelationTable_Projects();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.reference.impl.ProjectToRelationsMapEntryImpl <em>Project To Relations Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.reference.impl.ProjectToRelationsMapEntryImpl
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getProjectToRelationsMapEntry()
		 * @generated
		 */
		EClass PROJECT_TO_RELATIONS_MAP_ENTRY = eINSTANCE.getProjectToRelationsMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT_TO_RELATIONS_MAP_ENTRY__KEY = eINSTANCE.getProjectToRelationsMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT_TO_RELATIONS_MAP_ENTRY__VALUE = eINSTANCE.getProjectToRelationsMapEntry_Value();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.reference.impl.ProjectRelationCollectionImpl <em>Project Relation Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.reference.impl.ProjectRelationCollectionImpl
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getProjectRelationCollection()
		 * @generated
		 */
		EClass PROJECT_RELATION_COLLECTION = eINSTANCE.getProjectRelationCollection();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.reference.impl.RelationInfoImpl <em>Relation Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.reference.impl.RelationInfoImpl
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getRelationInfo()
		 * @generated
		 */
		EClass RELATION_INFO = eINSTANCE.getRelationInfo();

		/**
		 * The meta object literal for the '<em><b>Host Resource</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION_INFO__HOST_RESOURCE = eINSTANCE.getRelationInfo_HostResource();

		/**
		 * The meta object literal for the '<em><b>Used Ref Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION_INFO__USED_REF_NAME = eINSTANCE.getRelationInfo_UsedRefName();

		/**
		 * The meta object literal for the '<em><b>Used Ref Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION_INFO__USED_REF_NAMESPACE = eINSTANCE.getRelationInfo_UsedRefNamespace();

		/**
		 * The meta object literal for the '<em><b>Used Ref Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION_INFO__USED_REF_TYPE = eINSTANCE.getRelationInfo_UsedRefType();

		/**
		 * The meta object literal for the '<em>Relations</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.reference.IRelations
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getRelations()
		 * @generated
		 */
		EDataType RELATIONS = eINSTANCE.getRelations();

		/**
		 * The meta object literal for the '<em>IARES Project</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.IARESProject
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getIARESProject()
		 * @generated
		 */
		EDataType IARES_PROJECT = eINSTANCE.getIARESProject();

		/**
		 * The meta object literal for the '<em>IARES Resource</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.IARESResource
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getIARESResource()
		 * @generated
		 */
		EDataType IARES_RESOURCE = eINSTANCE.getIARESResource();

		/**
		 * The meta object literal for the '<em>IObject Provider</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.reference.IObjectProvider
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getIObjectProvider()
		 * @generated
		 */
		EDataType IOBJECT_PROVIDER = eINSTANCE.getIObjectProvider();

		/**
		 * The meta object literal for the '<em>IARES Bundle</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.IARESBundle
		 * @see com.hundsun.ares.studio.model.reference.impl.ReferencePackageImpl#getIARESBundle()
		 * @generated
		 */
		EDataType IARES_BUNDLE = eINSTANCE.getIARESBundle();

	}

} //ReferencePackage
