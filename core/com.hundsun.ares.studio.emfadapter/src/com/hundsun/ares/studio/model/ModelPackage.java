/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;
import com.hundsun.ares.studio.core.model.extendable.IExtendFieldModel;

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
 * @see com.hundsun.ares.studio.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://com.hundsun.ares.studio.model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "com.hundsun.ares.studio.model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = com.hundsun.ares.studio.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link IExtendAbleModel <em>IExtend Able Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see IExtendAbleModel
	 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getIExtendAbleModel()
	 * @generated
	 */
	int IEXTEND_ABLE_MODEL = 1;

	/**
	 * The number of structural features of the '<em>IExtend Able Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEXTEND_ABLE_MODEL_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.impl.EmfExtendAbleImpl <em>Emf Extend Able</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.impl.EmfExtendAbleImpl
	 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getEmfExtendAble()
	 * @generated
	 */
	int EMF_EXTEND_ABLE = 0;

	/**
	 * The feature id for the '<em><b>Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_EXTEND_ABLE__MAP = IEXTEND_ABLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Emf Extend Able</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMF_EXTEND_ABLE_FEATURE_COUNT = IEXTEND_ABLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.impl.StringMapEntryImpl <em>String Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.impl.StringMapEntryImpl
	 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getStringMapEntry()
	 * @generated
	 */
	int STRING_MAP_ENTRY = 2;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link Map <em>Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Map
	 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getMap()
	 * @generated
	 */
	int MAP = 5;

	/**
	 * The number of structural features of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.impl.EMapImproveImpl <em>EMap Improve</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.impl.EMapImproveImpl
	 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getEMapImprove()
	 * @generated
	 */
	int EMAP_IMPROVE = 3;

	/**
	 * The feature id for the '<em><b>Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAP_IMPROVE__MAP = MAP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EMap Improve</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAP_IMPROVE_FEATURE_COUNT = MAP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link IExtendFieldModel <em>IExtend Field Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see IExtendFieldModel
	 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getIExtendFieldModel()
	 * @generated
	 */
	int IEXTEND_FIELD_MODEL = 4;

	/**
	 * The number of structural features of the '<em>IExtend Field Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEXTEND_FIELD_MODEL_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.model.impl.ExtendFieldModelImpl <em>Extend Field Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.model.impl.ExtendFieldModelImpl
	 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getExtendFieldModel()
	 * @generated
	 */
	int EXTEND_FIELD_MODEL = 6;

	/**
	 * The feature id for the '<em><b>Extend Strings</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTEND_FIELD_MODEL__EXTEND_STRINGS = IEXTEND_FIELD_MODEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Extend Field Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTEND_FIELD_MODEL_FEATURE_COUNT = IEXTEND_FIELD_MODEL_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.model.EmfExtendAble <em>Emf Extend Able</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Emf Extend Able</em>'.
	 * @see com.hundsun.ares.studio.model.EmfExtendAble
	 * @generated
	 */
	EClass getEmfExtendAble();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.model.EmfExtendAble#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Map</em>'.
	 * @see com.hundsun.ares.studio.model.EmfExtendAble#getMap()
	 * @see #getEmfExtendAble()
	 * @generated
	 */
	EAttribute getEmfExtendAble_Map();

	/**
	 * Returns the meta object for class '{@link IExtendAbleModel <em>IExtend Able Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IExtend Able Model</em>'.
	 * @see IExtendAbleModel
	 * @model instanceClass="IExtendAbleModel"
	 * @generated
	 */
	EClass getIExtendAbleModel();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getStringMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringMapEntry()
	 * @generated
	 */
	EAttribute getStringMapEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringMapEntry()
	 * @generated
	 */
	EAttribute getStringMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.model.EMapImprove <em>EMap Improve</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EMap Improve</em>'.
	 * @see com.hundsun.ares.studio.model.EMapImprove
	 * @generated
	 */
	EClass getEMapImprove();

	/**
	 * Returns the meta object for the map '{@link com.hundsun.ares.studio.model.EMapImprove#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Map</em>'.
	 * @see com.hundsun.ares.studio.model.EMapImprove#getMap()
	 * @see #getEMapImprove()
	 * @generated
	 */
	EReference getEMapImprove_Map();

	/**
	 * Returns the meta object for class '{@link IExtendFieldModel <em>IExtend Field Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IExtend Field Model</em>'.
	 * @see IExtendFieldModel
	 * @model instanceClass="IExtendFieldModel"
	 * @generated
	 */
	EClass getIExtendFieldModel();

	/**
	 * Returns the meta object for class '{@link Map <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map</em>'.
	 * @see Map
	 * @model instanceClass="Map"
	 * @generated
	 */
	EClass getMap();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.model.ExtendFieldModel <em>Extend Field Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extend Field Model</em>'.
	 * @see com.hundsun.ares.studio.model.ExtendFieldModel
	 * @generated
	 */
	EClass getExtendFieldModel();

	/**
	 * Returns the meta object for the containment reference '{@link com.hundsun.ares.studio.model.ExtendFieldModel#getExtendStrings <em>Extend Strings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extend Strings</em>'.
	 * @see com.hundsun.ares.studio.model.ExtendFieldModel#getExtendStrings()
	 * @see #getExtendFieldModel()
	 * @generated
	 */
	EReference getExtendFieldModel_ExtendStrings();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.impl.EmfExtendAbleImpl <em>Emf Extend Able</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.impl.EmfExtendAbleImpl
		 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getEmfExtendAble()
		 * @generated
		 */
		EClass EMF_EXTEND_ABLE = eINSTANCE.getEmfExtendAble();

		/**
		 * The meta object literal for the '<em><b>Map</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMF_EXTEND_ABLE__MAP = eINSTANCE.getEmfExtendAble_Map();

		/**
		 * The meta object literal for the '{@link IExtendAbleModel <em>IExtend Able Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see IExtendAbleModel
		 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getIExtendAbleModel()
		 * @generated
		 */
		EClass IEXTEND_ABLE_MODEL = eINSTANCE.getIExtendAbleModel();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.impl.StringMapEntryImpl <em>String Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.impl.StringMapEntryImpl
		 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getStringMapEntry()
		 * @generated
		 */
		EClass STRING_MAP_ENTRY = eINSTANCE.getStringMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_MAP_ENTRY__KEY = eINSTANCE.getStringMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_MAP_ENTRY__VALUE = eINSTANCE.getStringMapEntry_Value();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.impl.EMapImproveImpl <em>EMap Improve</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.impl.EMapImproveImpl
		 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getEMapImprove()
		 * @generated
		 */
		EClass EMAP_IMPROVE = eINSTANCE.getEMapImprove();

		/**
		 * The meta object literal for the '<em><b>Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMAP_IMPROVE__MAP = eINSTANCE.getEMapImprove_Map();

		/**
		 * The meta object literal for the '{@link IExtendFieldModel <em>IExtend Field Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see IExtendFieldModel
		 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getIExtendFieldModel()
		 * @generated
		 */
		EClass IEXTEND_FIELD_MODEL = eINSTANCE.getIExtendFieldModel();

		/**
		 * The meta object literal for the '{@link Map <em>Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Map
		 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getMap()
		 * @generated
		 */
		EClass MAP = eINSTANCE.getMap();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.model.impl.ExtendFieldModelImpl <em>Extend Field Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.model.impl.ExtendFieldModelImpl
		 * @see com.hundsun.ares.studio.model.impl.ModelPackageImpl#getExtendFieldModel()
		 * @generated
		 */
		EClass EXTEND_FIELD_MODEL = eINSTANCE.getExtendFieldModel();

		/**
		 * The meta object literal for the '<em><b>Extend Strings</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTEND_FIELD_MODEL__EXTEND_STRINGS = eINSTANCE.getExtendFieldModel_ExtendStrings();

	}

} //ModelPackage
