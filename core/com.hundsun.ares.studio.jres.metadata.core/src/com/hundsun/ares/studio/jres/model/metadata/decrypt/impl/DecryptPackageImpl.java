/**
 * 源程序名称：DecryptPackageImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem;
import com.hundsun.ares.studio.jres.metadata.service.IDictionaryType;
import com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem;
import com.hundsun.ares.studio.jres.metadata.service.IStandardDataType;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue;
import com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeTypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptFactory;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage;
import com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DecryptPackageImpl extends EPackageImpl implements DecryptPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iTypeDefaultValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iStandardDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iBusinessDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iStandardFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iDictionaryTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iDictionaryItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iErrorNoItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iUserConstantItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deMetadataItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deTypeDefaultValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deStandardDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deBusinessDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deStandardFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deDictionaryTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deDictionaryItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deConstantItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deErrorNoItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iaresResourceEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType pairEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DecryptPackageImpl() {
		super(eNS_URI, DecryptFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link DecryptPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DecryptPackage init() {
		if (isInited) return (DecryptPackage)EPackage.Registry.INSTANCE.getEPackage(DecryptPackage.eNS_URI);

		// Obtain or create and register package
		DecryptPackageImpl theDecryptPackage = (DecryptPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DecryptPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DecryptPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		com.hundsun.ares.studio.core.model.CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		MetadataPackageImpl theMetadataPackage = (MetadataPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MetadataPackage.eNS_URI) instanceof MetadataPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MetadataPackage.eNS_URI) : MetadataPackage.eINSTANCE);

		// Create package meta-data objects
		theDecryptPackage.createPackageContents();
		theMetadataPackage.createPackageContents();

		// Initialize created meta-data
		theDecryptPackage.initializePackageContents();
		theMetadataPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDecryptPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DecryptPackage.eNS_URI, theDecryptPackage);
		return theDecryptPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getITypeDefaultValue() {
		return iTypeDefaultValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIStandardDataType() {
		return iStandardDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIBusinessDataType() {
		return iBusinessDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIStandardField() {
		return iStandardFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIDictionaryType() {
		return iDictionaryTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIDictionaryItem() {
		return iDictionaryItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIErrorNoItem() {
		return iErrorNoItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIUserConstantItem() {
		return iUserConstantItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeMetadataItem() {
		return deMetadataItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeTypeDefaultValue() {
		return deTypeDefaultValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeStandardDataType() {
		return deStandardDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeBusinessDataType() {
		return deBusinessDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeStandardField() {
		return deStandardFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeDictionaryType() {
		return deDictionaryTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeDictionaryItem() {
		return deDictionaryItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeConstantItem() {
		return deConstantItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeErrorNoItem() {
		return deErrorNoItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIARESResource() {
		return iaresResourceEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPair() {
		return pairEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecryptFactory getDecryptFactory() {
		return (DecryptFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		iTypeDefaultValueEClass = createEClass(ITYPE_DEFAULT_VALUE);

		iStandardDataTypeEClass = createEClass(ISTANDARD_DATA_TYPE);

		iBusinessDataTypeEClass = createEClass(IBUSINESS_DATA_TYPE);

		iStandardFieldEClass = createEClass(ISTANDARD_FIELD);

		iDictionaryTypeEClass = createEClass(IDICTIONARY_TYPE);

		iDictionaryItemEClass = createEClass(IDICTIONARY_ITEM);

		iErrorNoItemEClass = createEClass(IERROR_NO_ITEM);

		iUserConstantItemEClass = createEClass(IUSER_CONSTANT_ITEM);

		deMetadataItemEClass = createEClass(DE_METADATA_ITEM);

		deTypeDefaultValueEClass = createEClass(DE_TYPE_DEFAULT_VALUE);

		deStandardDataTypeEClass = createEClass(DE_STANDARD_DATA_TYPE);

		deBusinessDataTypeEClass = createEClass(DE_BUSINESS_DATA_TYPE);

		deStandardFieldEClass = createEClass(DE_STANDARD_FIELD);

		deDictionaryTypeEClass = createEClass(DE_DICTIONARY_TYPE);

		deDictionaryItemEClass = createEClass(DE_DICTIONARY_ITEM);

		deConstantItemEClass = createEClass(DE_CONSTANT_ITEM);

		deErrorNoItemEClass = createEClass(DE_ERROR_NO_ITEM);

		// Create data types
		iaresResourceEDataType = createEDataType(IARES_RESOURCE);
		pairEDataType = createEDataType(PAIR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		MetadataPackage theMetadataPackage = (MetadataPackage)EPackage.Registry.INSTANCE.getEPackage(MetadataPackage.eNS_URI);
		com.hundsun.ares.studio.core.model.CorePackage theCorePackage = (com.hundsun.ares.studio.core.model.CorePackage)EPackage.Registry.INSTANCE.getEPackage(com.hundsun.ares.studio.core.model.CorePackage.eNS_URI);

		// Create type parameters
		ETypeParameter deMetadataItemEClass_T = addETypeParameter(deMetadataItemEClass, "T");
		addETypeParameter(pairEDataType, "F");
		addETypeParameter(pairEDataType, "S");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(theMetadataPackage.getMetadataItem());
		deMetadataItemEClass_T.getEBounds().add(g1);

		// Add supertypes to classes
		g1 = createEGenericType(this.getDeMetadataItem());
		EGenericType g2 = createEGenericType(theMetadataPackage.getTypeDefaultValue());
		g1.getETypeArguments().add(g2);
		deTypeDefaultValueEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getITypeDefaultValue());
		deTypeDefaultValueEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDeMetadataItem());
		g2 = createEGenericType(theMetadataPackage.getStandardDataType());
		g1.getETypeArguments().add(g2);
		deStandardDataTypeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getIStandardDataType());
		deStandardDataTypeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDeMetadataItem());
		g2 = createEGenericType(theMetadataPackage.getBusinessDataType());
		g1.getETypeArguments().add(g2);
		deBusinessDataTypeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getIBusinessDataType());
		deBusinessDataTypeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDeMetadataItem());
		g2 = createEGenericType(theMetadataPackage.getStandardField());
		g1.getETypeArguments().add(g2);
		deStandardFieldEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getIStandardField());
		deStandardFieldEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDeMetadataItem());
		g2 = createEGenericType(theMetadataPackage.getDictionaryType());
		g1.getETypeArguments().add(g2);
		deDictionaryTypeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getIDictionaryType());
		deDictionaryTypeEClass.getEGenericSuperTypes().add(g1);
		deDictionaryItemEClass.getESuperTypes().add(this.getIDictionaryItem());
		g1 = createEGenericType(this.getDeMetadataItem());
		g2 = createEGenericType(theMetadataPackage.getConstantItem());
		g1.getETypeArguments().add(g2);
		deConstantItemEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getIUserConstantItem());
		deConstantItemEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDeMetadataItem());
		g2 = createEGenericType(theMetadataPackage.getErrorNoItem());
		g1.getETypeArguments().add(g2);
		deErrorNoItemEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getIErrorNoItem());
		deErrorNoItemEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes and features; add operations and parameters
		initEClass(iTypeDefaultValueEClass, ITypeDefaultValue.class, "ITypeDefaultValue", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iStandardDataTypeEClass, IStandardDataType.class, "IStandardDataType", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iBusinessDataTypeEClass, IBusinessDataType.class, "IBusinessDataType", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iStandardFieldEClass, IStandardField.class, "IStandardField", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iDictionaryTypeEClass, IDictionaryType.class, "IDictionaryType", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iDictionaryItemEClass, IDictionaryItem.class, "IDictionaryItem", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iErrorNoItemEClass, IErrorNoItem.class, "IErrorNoItem", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iUserConstantItemEClass, IUserConstantItem.class, "IUserConstantItem", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(deMetadataItemEClass, DeMetadataItem.class, "DeMetadataItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(deMetadataItemEClass, ecorePackage.getEString(), "getName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deMetadataItemEClass, ecorePackage.getEString(), "getChineseName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deMetadataItemEClass, ecorePackage.getEString(), "getDescription", 0, 1, IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(deMetadataItemEClass, ecorePackage.getEString(), "getDataMapValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(deMetadataItemEClass, ecorePackage.getEObject(), "getData2MapValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(deMetadataItemEClass, null, "getResolvedItem", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getPair());
		g2 = createEGenericType(deMetadataItemEClass_T);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(this.getIARESResource());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(deMetadataItemEClass, null, "getProxyItem", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(deMetadataItemEClass_T);
		initEOperation(op, g1);

		addEOperation(deMetadataItemEClass, this.getIARESResource(), "getResource", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deMetadataItemEClass, ecorePackage.getEStringToStringMapEntry(), "getDataMap", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deMetadataItemEClass, theCorePackage.getEStringToEObjectMapEntry(), "getData2Map", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(deTypeDefaultValueEClass, DeTypeDefaultValue.class, "DeTypeDefaultValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(deTypeDefaultValueEClass, ecorePackage.getEString(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(deStandardDataTypeEClass, DeStandardDataType.class, "DeStandardDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(deStandardDataTypeEClass, ecorePackage.getEString(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(deStandardDataTypeEClass, ecorePackage.getEString(), "getRealType", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "length", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "precision", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(deBusinessDataTypeEClass, DeBusinessDataType.class, "DeBusinessDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(deBusinessDataTypeEClass, this.getDeTypeDefaultValue(), "getDefaultValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deBusinessDataTypeEClass, ecorePackage.getEString(), "getDefaultValueId", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deBusinessDataTypeEClass, ecorePackage.getEString(), "getLength", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deBusinessDataTypeEClass, ecorePackage.getEString(), "getPrecision", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deBusinessDataTypeEClass, this.getDeStandardDataType(), "getStdType", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deBusinessDataTypeEClass, ecorePackage.getEString(), "getStdTypeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(deBusinessDataTypeEClass, ecorePackage.getEString(), "getRealDefaultValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(deBusinessDataTypeEClass, ecorePackage.getEString(), "getRealType", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(deStandardFieldEClass, DeStandardField.class, "DeStandardField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(deStandardFieldEClass, this.getDeBusinessDataType(), "getDataType", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deStandardFieldEClass, ecorePackage.getEString(), "getDataTypeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deStandardFieldEClass, this.getDeDictionaryType(), "getDictionaryType", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deStandardFieldEClass, ecorePackage.getEString(), "getDictionaryTypeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deStandardFieldEClass, ecorePackage.getEString(), "getLength", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deStandardFieldEClass, ecorePackage.getEString(), "getPrecision", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(deStandardFieldEClass, ecorePackage.getEString(), "getRealType", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(deDictionaryTypeEClass, DeDictionaryType.class, "DeDictionaryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(deDictionaryTypeEClass, this.getDeDictionaryItem(), "getItems", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(deDictionaryItemEClass, DeDictionaryItem.class, "DeDictionaryItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(deDictionaryItemEClass, this.getDeDictionaryType(), "getParent", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deDictionaryItemEClass, ecorePackage.getEString(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deDictionaryItemEClass, ecorePackage.getEString(), "getName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deDictionaryItemEClass, ecorePackage.getEString(), "getChineseName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deDictionaryItemEClass, ecorePackage.getEString(), "getConstantName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deDictionaryItemEClass, ecorePackage.getEString(), "getDescription", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(deConstantItemEClass, DeConstantItem.class, "DeConstantItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(deConstantItemEClass, this.getDeStandardDataType(), "getDataType", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deConstantItemEClass, ecorePackage.getEString(), "getDataTypeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deConstantItemEClass, ecorePackage.getEString(), "getLength", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deConstantItemEClass, ecorePackage.getEString(), "getPrecision", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deConstantItemEClass, ecorePackage.getEString(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(deErrorNoItemEClass, DeErrorNoItem.class, "DeErrorNoItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(deErrorNoItemEClass, ecorePackage.getEString(), "getConstantName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deErrorNoItemEClass, ecorePackage.getEString(), "getLevel", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deErrorNoItemEClass, ecorePackage.getEString(), "getMessage", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(deErrorNoItemEClass, ecorePackage.getEString(), "getNo", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize data types
		initEDataType(iaresResourceEDataType, IARESResource.class, "IARESResource", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(pairEDataType, Pair.class, "Pair", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
	}

} //DecryptPackageImpl
