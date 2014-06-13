/**
 * 源程序名称：MetadataPackageImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig;
import com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfigList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.ConstantList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoList;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem;
import com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigList;
import com.hundsun.ares.studio.jres.model.metadata.IDRange;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeItem;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeList;
import com.hundsun.ares.studio.jres.model.metadata.MDModuleCommonProperty;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MenuList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.NamedElement;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MetadataPackageImpl extends EPackageImpl implements MetadataPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mdModuleCommonPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metadataResourceDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metadataItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metadataCategoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeDefaultValueListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeDefaultValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass standardDataTypeListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass standardDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass businessDataTypeListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass businessDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass standardFieldListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass standardFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dictionaryListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dictionaryTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dictionaryItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constantListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constantItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorNoListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorNoItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generalDataConfigListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generalDataConfigItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass menuItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionProxyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass menuListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass idRangeItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass idRangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bizPropertyConfigListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bizPropertyConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass idRangeListEClass = null;

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
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MetadataPackageImpl() {
		super(eNS_URI, MetadataFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link MetadataPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MetadataPackage init() {
		if (isInited) return (MetadataPackage)EPackage.Registry.INSTANCE.getEPackage(MetadataPackage.eNS_URI);

		// Obtain or create and register package
		MetadataPackageImpl theMetadataPackage = (MetadataPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MetadataPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MetadataPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		DecryptPackageImpl theDecryptPackage = (DecryptPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DecryptPackage.eNS_URI) instanceof DecryptPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DecryptPackage.eNS_URI) : DecryptPackage.eINSTANCE);

		// Create package meta-data objects
		theMetadataPackage.createPackageContents();
		theDecryptPackage.createPackageContents();

		// Initialize created meta-data
		theMetadataPackage.initializePackageContents();
		theDecryptPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theMetadataPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return MetadataValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theMetadataPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MetadataPackage.eNS_URI, theMetadataPackage);
		return theMetadataPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMDModuleCommonProperty() {
		return mdModuleCommonPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMDModuleCommonProperty_UseRefFeature() {
		return (EAttribute)mdModuleCommonPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_Title() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_File() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_OutPath() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_FunctionName() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_Code() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_Uixml() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_Autobuild() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetadataResourceData() {
		return metadataResourceDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetadataResourceData_Operations() {
		return (EReference)metadataResourceDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetadataResourceData_Root() {
		return (EReference)metadataResourceDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetadataResourceData_Items() {
		return (EReference)metadataResourceDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_ChineseName() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Description() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetadataItem() {
		return metadataItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetadataItem_RefId() {
		return (EAttribute)metadataItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetadataItem_Parent() {
		return (EReference)metadataItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetadataCategory() {
		return metadataCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetadataCategory_Children() {
		return (EReference)metadataCategoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetadataCategory_Items() {
		return (EReference)metadataCategoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetadataCategory_Parent() {
		return (EReference)metadataCategoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeDefaultValueList() {
		return typeDefaultValueListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeDefaultValue() {
		return typeDefaultValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStandardDataTypeList() {
		return standardDataTypeListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStandardDataType() {
		return standardDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBusinessDataTypeList() {
		return businessDataTypeListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBusinessDataType() {
		return businessDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBusinessDataType_StdType() {
		return (EAttribute)businessDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBusinessDataType_Length() {
		return (EAttribute)businessDataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBusinessDataType_Precision() {
		return (EAttribute)businessDataTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBusinessDataType_DefaultValue() {
		return (EAttribute)businessDataTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStandardFieldList() {
		return standardFieldListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStandardField() {
		return standardFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStandardField_Length() {
		return (EAttribute)standardFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStandardField_Precision() {
		return (EAttribute)standardFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStandardField_DataType() {
		return (EAttribute)standardFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStandardField_DictionaryType() {
		return (EAttribute)standardFieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDictionaryList() {
		return dictionaryListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDictionaryType() {
		return dictionaryTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDictionaryType_Items() {
		return (EReference)dictionaryTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDictionaryItem() {
		return dictionaryItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDictionaryItem_Parent() {
		return (EReference)dictionaryItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDictionaryItem_Value() {
		return (EAttribute)dictionaryItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDictionaryItem_Name() {
		return (EAttribute)dictionaryItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDictionaryItem_ChineseName() {
		return (EAttribute)dictionaryItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDictionaryItem_ConstantName() {
		return (EAttribute)dictionaryItemEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDictionaryItem_Description() {
		return (EAttribute)dictionaryItemEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstantList() {
		return constantListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstantItem() {
		return constantItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstantItem_DataType() {
		return (EAttribute)constantItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstantItem_Value() {
		return (EAttribute)constantItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstantItem_Length() {
		return (EAttribute)constantItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstantItem_Precision() {
		return (EAttribute)constantItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getErrorNoList() {
		return errorNoListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getErrorNoItem() {
		return errorNoItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getErrorNoItem_No() {
		return (EAttribute)errorNoItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getErrorNoItem_Message() {
		return (EAttribute)errorNoItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getErrorNoItem_ConstantName() {
		return (EAttribute)errorNoItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getErrorNoItem_Level() {
		return (EAttribute)errorNoItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneralDataConfigList() {
		return generalDataConfigListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneralDataConfigList_Items() {
		return (EReference)generalDataConfigListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneralDataConfigItem() {
		return generalDataConfigItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralDataConfigItem_Id() {
		return (EAttribute)generalDataConfigItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralDataConfigItem_Type() {
		return (EAttribute)generalDataConfigItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralDataConfigItem_Value() {
		return (EAttribute)generalDataConfigItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralDataConfigItem_ChineseName() {
		return (EAttribute)generalDataConfigItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralDataConfigItem_Discription() {
		return (EAttribute)generalDataConfigItemEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMenuItem() {
		return menuItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMenuItem_Url() {
		return (EAttribute)menuItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMenuItem_Icon() {
		return (EAttribute)menuItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMenuItem_FunctionProxys() {
		return (EReference)menuItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMenuItem_SubItems() {
		return (EReference)menuItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionProxy() {
		return functionProxyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionProxy_FunCode() {
		return (EAttribute)functionProxyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionProxy_Description() {
		return (EAttribute)functionProxyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunction() {
		return functionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunction_SubTransCode() {
		return (EAttribute)functionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunction_ServID() {
		return (EAttribute)functionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMenuList() {
		return menuListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMenuList_Functions() {
		return (EReference)menuListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIDRangeItem() {
		return idRangeItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIDRange() {
		return idRangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIDRange_Range() {
		return (EAttribute)idRangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBizPropertyConfigList() {
		return bizPropertyConfigListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBizPropertyConfig() {
		return bizPropertyConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBizPropertyConfig_Ename() {
		return (EAttribute)bizPropertyConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIDRangeList() {
		return idRangeListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataFactory getMetadataFactory() {
		return (MetadataFactory)getEFactoryInstance();
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
		mdModuleCommonPropertyEClass = createEClass(MD_MODULE_COMMON_PROPERTY);
		createEAttribute(mdModuleCommonPropertyEClass, MD_MODULE_COMMON_PROPERTY__USE_REF_FEATURE);

		operationEClass = createEClass(OPERATION);
		createEAttribute(operationEClass, OPERATION__TITLE);
		createEAttribute(operationEClass, OPERATION__FILE);
		createEAttribute(operationEClass, OPERATION__OUT_PATH);
		createEAttribute(operationEClass, OPERATION__FUNCTION_NAME);
		createEAttribute(operationEClass, OPERATION__CODE);
		createEAttribute(operationEClass, OPERATION__UIXML);
		createEAttribute(operationEClass, OPERATION__AUTOBUILD);

		metadataResourceDataEClass = createEClass(METADATA_RESOURCE_DATA);
		createEReference(metadataResourceDataEClass, METADATA_RESOURCE_DATA__OPERATIONS);
		createEReference(metadataResourceDataEClass, METADATA_RESOURCE_DATA__ROOT);
		createEReference(metadataResourceDataEClass, METADATA_RESOURCE_DATA__ITEMS);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__CHINESE_NAME);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__DESCRIPTION);

		metadataItemEClass = createEClass(METADATA_ITEM);
		createEAttribute(metadataItemEClass, METADATA_ITEM__REF_ID);
		createEReference(metadataItemEClass, METADATA_ITEM__PARENT);

		metadataCategoryEClass = createEClass(METADATA_CATEGORY);
		createEReference(metadataCategoryEClass, METADATA_CATEGORY__CHILDREN);
		createEReference(metadataCategoryEClass, METADATA_CATEGORY__ITEMS);
		createEReference(metadataCategoryEClass, METADATA_CATEGORY__PARENT);

		typeDefaultValueListEClass = createEClass(TYPE_DEFAULT_VALUE_LIST);

		typeDefaultValueEClass = createEClass(TYPE_DEFAULT_VALUE);

		standardDataTypeListEClass = createEClass(STANDARD_DATA_TYPE_LIST);

		standardDataTypeEClass = createEClass(STANDARD_DATA_TYPE);

		businessDataTypeListEClass = createEClass(BUSINESS_DATA_TYPE_LIST);

		businessDataTypeEClass = createEClass(BUSINESS_DATA_TYPE);
		createEAttribute(businessDataTypeEClass, BUSINESS_DATA_TYPE__STD_TYPE);
		createEAttribute(businessDataTypeEClass, BUSINESS_DATA_TYPE__LENGTH);
		createEAttribute(businessDataTypeEClass, BUSINESS_DATA_TYPE__PRECISION);
		createEAttribute(businessDataTypeEClass, BUSINESS_DATA_TYPE__DEFAULT_VALUE);

		standardFieldListEClass = createEClass(STANDARD_FIELD_LIST);

		standardFieldEClass = createEClass(STANDARD_FIELD);
		createEAttribute(standardFieldEClass, STANDARD_FIELD__LENGTH);
		createEAttribute(standardFieldEClass, STANDARD_FIELD__PRECISION);
		createEAttribute(standardFieldEClass, STANDARD_FIELD__DATA_TYPE);
		createEAttribute(standardFieldEClass, STANDARD_FIELD__DICTIONARY_TYPE);

		dictionaryListEClass = createEClass(DICTIONARY_LIST);

		dictionaryTypeEClass = createEClass(DICTIONARY_TYPE);
		createEReference(dictionaryTypeEClass, DICTIONARY_TYPE__ITEMS);

		dictionaryItemEClass = createEClass(DICTIONARY_ITEM);
		createEReference(dictionaryItemEClass, DICTIONARY_ITEM__PARENT);
		createEAttribute(dictionaryItemEClass, DICTIONARY_ITEM__VALUE);
		createEAttribute(dictionaryItemEClass, DICTIONARY_ITEM__NAME);
		createEAttribute(dictionaryItemEClass, DICTIONARY_ITEM__CHINESE_NAME);
		createEAttribute(dictionaryItemEClass, DICTIONARY_ITEM__CONSTANT_NAME);
		createEAttribute(dictionaryItemEClass, DICTIONARY_ITEM__DESCRIPTION);

		constantListEClass = createEClass(CONSTANT_LIST);

		constantItemEClass = createEClass(CONSTANT_ITEM);
		createEAttribute(constantItemEClass, CONSTANT_ITEM__DATA_TYPE);
		createEAttribute(constantItemEClass, CONSTANT_ITEM__VALUE);
		createEAttribute(constantItemEClass, CONSTANT_ITEM__LENGTH);
		createEAttribute(constantItemEClass, CONSTANT_ITEM__PRECISION);

		errorNoListEClass = createEClass(ERROR_NO_LIST);

		errorNoItemEClass = createEClass(ERROR_NO_ITEM);
		createEAttribute(errorNoItemEClass, ERROR_NO_ITEM__NO);
		createEAttribute(errorNoItemEClass, ERROR_NO_ITEM__MESSAGE);
		createEAttribute(errorNoItemEClass, ERROR_NO_ITEM__CONSTANT_NAME);
		createEAttribute(errorNoItemEClass, ERROR_NO_ITEM__LEVEL);

		generalDataConfigListEClass = createEClass(GENERAL_DATA_CONFIG_LIST);
		createEReference(generalDataConfigListEClass, GENERAL_DATA_CONFIG_LIST__ITEMS);

		generalDataConfigItemEClass = createEClass(GENERAL_DATA_CONFIG_ITEM);
		createEAttribute(generalDataConfigItemEClass, GENERAL_DATA_CONFIG_ITEM__ID);
		createEAttribute(generalDataConfigItemEClass, GENERAL_DATA_CONFIG_ITEM__TYPE);
		createEAttribute(generalDataConfigItemEClass, GENERAL_DATA_CONFIG_ITEM__VALUE);
		createEAttribute(generalDataConfigItemEClass, GENERAL_DATA_CONFIG_ITEM__CHINESE_NAME);
		createEAttribute(generalDataConfigItemEClass, GENERAL_DATA_CONFIG_ITEM__DISCRIPTION);

		menuItemEClass = createEClass(MENU_ITEM);
		createEAttribute(menuItemEClass, MENU_ITEM__URL);
		createEAttribute(menuItemEClass, MENU_ITEM__ICON);
		createEReference(menuItemEClass, MENU_ITEM__SUB_ITEMS);
		createEReference(menuItemEClass, MENU_ITEM__FUNCTION_PROXYS);

		functionProxyEClass = createEClass(FUNCTION_PROXY);
		createEAttribute(functionProxyEClass, FUNCTION_PROXY__FUN_CODE);
		createEAttribute(functionProxyEClass, FUNCTION_PROXY__DESCRIPTION);

		functionEClass = createEClass(FUNCTION);
		createEAttribute(functionEClass, FUNCTION__SUB_TRANS_CODE);
		createEAttribute(functionEClass, FUNCTION__SERV_ID);

		menuListEClass = createEClass(MENU_LIST);
		createEReference(menuListEClass, MENU_LIST__FUNCTIONS);

		idRangeItemEClass = createEClass(ID_RANGE_ITEM);

		idRangeListEClass = createEClass(ID_RANGE_LIST);

		idRangeEClass = createEClass(ID_RANGE);
		createEAttribute(idRangeEClass, ID_RANGE__RANGE);

		bizPropertyConfigListEClass = createEClass(BIZ_PROPERTY_CONFIG_LIST);

		bizPropertyConfigEClass = createEClass(BIZ_PROPERTY_CONFIG);
		createEAttribute(bizPropertyConfigEClass, BIZ_PROPERTY_CONFIG__ENAME);
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
		DecryptPackage theDecryptPackage = (DecryptPackage)EPackage.Registry.INSTANCE.getEPackage(DecryptPackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theDecryptPackage);

		// Create type parameters
		ETypeParameter metadataResourceDataEClass_T = addETypeParameter(metadataResourceDataEClass, "T");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(this.getMetadataItem());
		metadataResourceDataEClass_T.getEBounds().add(g1);

		// Add supertypes to classes
		metadataResourceDataEClass.getESuperTypes().add(theCorePackage.getJRESResourceInfo());
		namedElementEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		metadataItemEClass.getESuperTypes().add(this.getNamedElement());
		metadataCategoryEClass.getESuperTypes().add(this.getNamedElement());
		g1 = createEGenericType(this.getMetadataResourceData());
		EGenericType g2 = createEGenericType(this.getTypeDefaultValue());
		g1.getETypeArguments().add(g2);
		typeDefaultValueListEClass.getEGenericSuperTypes().add(g1);
		typeDefaultValueEClass.getESuperTypes().add(this.getMetadataItem());
		g1 = createEGenericType(this.getMetadataResourceData());
		g2 = createEGenericType(this.getStandardDataType());
		g1.getETypeArguments().add(g2);
		standardDataTypeListEClass.getEGenericSuperTypes().add(g1);
		standardDataTypeEClass.getESuperTypes().add(this.getMetadataItem());
		g1 = createEGenericType(this.getMetadataResourceData());
		g2 = createEGenericType(this.getBusinessDataType());
		g1.getETypeArguments().add(g2);
		businessDataTypeListEClass.getEGenericSuperTypes().add(g1);
		businessDataTypeEClass.getESuperTypes().add(this.getMetadataItem());
		g1 = createEGenericType(this.getMetadataResourceData());
		g2 = createEGenericType(this.getStandardField());
		g1.getETypeArguments().add(g2);
		standardFieldListEClass.getEGenericSuperTypes().add(g1);
		standardFieldEClass.getESuperTypes().add(this.getMetadataItem());
		g1 = createEGenericType(this.getMetadataResourceData());
		g2 = createEGenericType(this.getDictionaryType());
		g1.getETypeArguments().add(g2);
		dictionaryListEClass.getEGenericSuperTypes().add(g1);
		dictionaryTypeEClass.getESuperTypes().add(this.getMetadataItem());
		dictionaryItemEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		g1 = createEGenericType(this.getMetadataResourceData());
		g2 = createEGenericType(this.getConstantItem());
		g1.getETypeArguments().add(g2);
		constantListEClass.getEGenericSuperTypes().add(g1);
		constantItemEClass.getESuperTypes().add(this.getMetadataItem());
		g1 = createEGenericType(this.getMetadataResourceData());
		g2 = createEGenericType(this.getErrorNoItem());
		g1.getETypeArguments().add(g2);
		errorNoListEClass.getEGenericSuperTypes().add(g1);
		errorNoItemEClass.getESuperTypes().add(this.getMetadataItem());
		generalDataConfigListEClass.getESuperTypes().add(theCorePackage.getJRESResourceInfo());
		menuItemEClass.getESuperTypes().add(this.getMetadataItem());
		functionProxyEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		functionEClass.getESuperTypes().add(this.getNamedElement());
		g1 = createEGenericType(this.getMetadataResourceData());
		g2 = createEGenericType(this.getMenuItem());
		g1.getETypeArguments().add(g2);
		menuListEClass.getEGenericSuperTypes().add(g1);
		idRangeItemEClass.getESuperTypes().add(this.getMetadataItem());
		g1 = createEGenericType(this.getMetadataResourceData());
		g2 = createEGenericType(this.getIDRangeItem());
		g1.getETypeArguments().add(g2);
		idRangeListEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getMetadataResourceData());
		g2 = createEGenericType(this.getBizPropertyConfig());
		g1.getETypeArguments().add(g2);
		bizPropertyConfigListEClass.getEGenericSuperTypes().add(g1);
		bizPropertyConfigEClass.getESuperTypes().add(this.getMetadataItem());

		// Initialize classes and features; add operations and parameters
		initEClass(mdModuleCommonPropertyEClass, MDModuleCommonProperty.class, "MDModuleCommonProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMDModuleCommonProperty_UseRefFeature(), ecorePackage.getEBoolean(), "useRefFeature", "false", 0, 1, MDModuleCommonProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperation_Title(), ecorePackage.getEString(), "title", "", 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_File(), ecorePackage.getEString(), "file", "", 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_OutPath(), ecorePackage.getEString(), "outPath", "", 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_FunctionName(), ecorePackage.getEString(), "functionName", "", 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Code(), ecorePackage.getEString(), "code", "", 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Uixml(), ecorePackage.getEString(), "uixml", "", 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Autobuild(), ecorePackage.getEBoolean(), "autobuild", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metadataResourceDataEClass, MetadataResourceData.class, "MetadataResourceData", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetadataResourceData_Operations(), this.getOperation(), null, "operations", null, 0, -1, MetadataResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetadataResourceData_Root(), this.getMetadataCategory(), null, "root", null, 0, 1, MetadataResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(metadataResourceDataEClass_T);
		initEReference(getMetadataResourceData_Items(), g1, this.getMetadataItem_Parent(), "items", null, 0, -1, MetadataResourceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(metadataResourceDataEClass, null, "find", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(metadataResourceDataEClass_T);
		initEOperation(op, g1);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", "", 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNamedElement_ChineseName(), ecorePackage.getEString(), "chineseName", "", 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNamedElement_Description(), ecorePackage.getEString(), "description", "", 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metadataItemEClass, MetadataItem.class, "MetadataItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetadataItem_RefId(), ecorePackage.getEString(), "refId", "", 0, 1, MetadataItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getMetadataResourceData());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getMetadataItem_Parent(), g1, this.getMetadataResourceData_Items(), "parent", null, 0, 1, MetadataItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(metadataItemEClass, this.getMetadataCategory(), "getCategories", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(metadataCategoryEClass, MetadataCategory.class, "MetadataCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetadataCategory_Children(), this.getMetadataCategory(), this.getMetadataCategory_Parent(), "children", null, 0, -1, MetadataCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetadataCategory_Items(), this.getMetadataItem(), null, "items", null, 0, -1, MetadataCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetadataCategory_Parent(), this.getMetadataCategory(), this.getMetadataCategory_Children(), "parent", null, 0, 1, MetadataCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeDefaultValueListEClass, TypeDefaultValueList.class, "TypeDefaultValueList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(typeDefaultValueEClass, TypeDefaultValue.class, "TypeDefaultValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(typeDefaultValueEClass, ecorePackage.getEString(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(typeDefaultValueEClass, null, "setValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(standardDataTypeListEClass, StandardDataTypeList.class, "StandardDataTypeList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(standardDataTypeEClass, StandardDataType.class, "StandardDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(standardDataTypeEClass, ecorePackage.getEString(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(standardDataTypeEClass, null, "setValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(businessDataTypeListEClass, BusinessDataTypeList.class, "BusinessDataTypeList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(businessDataTypeEClass, BusinessDataType.class, "BusinessDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBusinessDataType_StdType(), ecorePackage.getEString(), "stdType", "", 0, 1, BusinessDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBusinessDataType_Length(), ecorePackage.getEString(), "length", "", 0, 1, BusinessDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBusinessDataType_Precision(), ecorePackage.getEString(), "precision", "", 0, 1, BusinessDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBusinessDataType_DefaultValue(), ecorePackage.getEString(), "defaultValue", "", 0, 1, BusinessDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(standardFieldListEClass, StandardFieldList.class, "StandardFieldList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(standardFieldEClass, StandardField.class, "StandardField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStandardField_Length(), ecorePackage.getEString(), "length", "", 0, 1, StandardField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStandardField_Precision(), ecorePackage.getEString(), "precision", "", 0, 1, StandardField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStandardField_DataType(), ecorePackage.getEString(), "dataType", "", 0, 1, StandardField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStandardField_DictionaryType(), ecorePackage.getEString(), "dictionaryType", "", 0, 1, StandardField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dictionaryListEClass, DictionaryList.class, "DictionaryList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dictionaryTypeEClass, DictionaryType.class, "DictionaryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDictionaryType_Items(), this.getDictionaryItem(), this.getDictionaryItem_Parent(), "items", null, 0, -1, DictionaryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dictionaryItemEClass, DictionaryItem.class, "DictionaryItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDictionaryItem_Parent(), this.getDictionaryType(), this.getDictionaryType_Items(), "parent", null, 0, 1, DictionaryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDictionaryItem_Value(), ecorePackage.getEString(), "value", "", 0, 1, DictionaryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDictionaryItem_Name(), ecorePackage.getEString(), "name", "", 0, 1, DictionaryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDictionaryItem_ChineseName(), ecorePackage.getEString(), "chineseName", "", 0, 1, DictionaryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDictionaryItem_ConstantName(), ecorePackage.getEString(), "constantName", "", 0, 1, DictionaryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDictionaryItem_Description(), ecorePackage.getEString(), "description", "", 0, 1, DictionaryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constantListEClass, ConstantList.class, "ConstantList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(constantItemEClass, ConstantItem.class, "ConstantItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstantItem_DataType(), ecorePackage.getEString(), "dataType", "", 0, 1, ConstantItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstantItem_Value(), ecorePackage.getEString(), "value", "", 0, 1, ConstantItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstantItem_Length(), ecorePackage.getEString(), "length", "", 0, 1, ConstantItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstantItem_Precision(), ecorePackage.getEString(), "precision", "", 0, 1, ConstantItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(errorNoListEClass, ErrorNoList.class, "ErrorNoList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(errorNoItemEClass, ErrorNoItem.class, "ErrorNoItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getErrorNoItem_No(), ecorePackage.getEString(), "no", "", 0, 1, ErrorNoItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorNoItem_Message(), ecorePackage.getEString(), "message", "", 0, 1, ErrorNoItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorNoItem_ConstantName(), ecorePackage.getEString(), "constantName", "", 0, 1, ErrorNoItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorNoItem_Level(), ecorePackage.getEString(), "level", "", 0, 1, ErrorNoItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(generalDataConfigListEClass, GeneralDataConfigList.class, "GeneralDataConfigList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGeneralDataConfigList_Items(), this.getGeneralDataConfigItem(), null, "items", null, 0, -1, GeneralDataConfigList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(generalDataConfigItemEClass, GeneralDataConfigItem.class, "GeneralDataConfigItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeneralDataConfigItem_Id(), ecorePackage.getEString(), "id", null, 0, 1, GeneralDataConfigItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneralDataConfigItem_Type(), ecorePackage.getEString(), "type", null, 0, 1, GeneralDataConfigItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneralDataConfigItem_Value(), ecorePackage.getEString(), "value", null, 0, 1, GeneralDataConfigItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneralDataConfigItem_ChineseName(), ecorePackage.getEString(), "chineseName", null, 0, 1, GeneralDataConfigItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneralDataConfigItem_Discription(), ecorePackage.getEString(), "discription", null, 0, 1, GeneralDataConfigItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(menuItemEClass, MenuItem.class, "MenuItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMenuItem_Url(), ecorePackage.getEString(), "url", "", 0, 1, MenuItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMenuItem_Icon(), ecorePackage.getEString(), "icon", null, 0, 1, MenuItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMenuItem_SubItems(), this.getMenuItem(), null, "subItems", null, 0, -1, MenuItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMenuItem_FunctionProxys(), this.getFunctionProxy(), null, "functionProxys", null, 0, -1, MenuItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(functionProxyEClass, FunctionProxy.class, "FunctionProxy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunctionProxy_FunCode(), ecorePackage.getEString(), "funCode", "", 0, 1, FunctionProxy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFunctionProxy_Description(), ecorePackage.getEString(), "description", "", 0, 1, FunctionProxy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(functionEClass, Function.class, "Function", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunction_SubTransCode(), ecorePackage.getEString(), "subTransCode", "", 0, 1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFunction_ServID(), ecorePackage.getEString(), "servID", "", 0, 1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(menuListEClass, MenuList.class, "MenuList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMenuList_Functions(), this.getFunction(), null, "functions", null, 0, -1, MenuList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(idRangeItemEClass, IDRangeItem.class, "IDRangeItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(idRangeListEClass, IDRangeList.class, "IDRangeList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(idRangeEClass, IDRange.class, "IDRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIDRange_Range(), ecorePackage.getEString(), "range", "0-0", 0, 1, IDRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bizPropertyConfigListEClass, BizPropertyConfigList.class, "BizPropertyConfigList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(bizPropertyConfigEClass, BizPropertyConfig.class, "BizPropertyConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBizPropertyConfig_Ename(), ecorePackage.getEString(), "ename", "", 0, 1, BizPropertyConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.hundsun.com/ares/studio/jres/references
		createReferencesAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";			
		addAnnotation
		  (metadataCategoryEClass, 
		   source, 
		   new String[] {
			 "constraints", "name"
		   });		
		addAnnotation
		  (typeDefaultValueEClass, 
		   source, 
		   new String[] {
			 "constraints", "name refId"
		   });			
		addAnnotation
		  (standardDataTypeEClass, 
		   source, 
		   new String[] {
			 "constraints", "name refId"
		   });		
		addAnnotation
		  (businessDataTypeEClass, 
		   source, 
		   new String[] {
			 "constraints", "name refId stdType length precision defaultValue"
		   });		
		addAnnotation
		  (standardFieldEClass, 
		   source, 
		   new String[] {
			 "constraints", "name refId length precision dataType dictionaryType"
		   });		
		addAnnotation
		  (dictionaryTypeEClass, 
		   source, 
		   new String[] {
			 "constraints", "name refId"
		   });		
		addAnnotation
		  (dictionaryItemEClass, 
		   source, 
		   new String[] {
			 "constraints", "value name constantName"
		   });		
		addAnnotation
		  (constantItemEClass, 
		   source, 
		   new String[] {
			 "constraints", "name refId dataType value length precision"
		   });		
		addAnnotation
		  (errorNoItemEClass, 
		   source, 
		   new String[] {
			 "constraints", "name refId constantName message no level"
		   });		
		addAnnotation
		  (menuItemEClass, 
		   source, 
		   new String[] {
			 "constraints", "name refID"
		   });		
		addAnnotation
		  (functionProxyEClass, 
		   source, 
		   new String[] {
			 "constraints", "funCode"
		   });		
		addAnnotation
		  (functionEClass, 
		   source, 
		   new String[] {
			 "constraints", "name subTransCode"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.hundsun.com/ares/studio/jres/references</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createReferencesAnnotations() {
		String source = "http://www.hundsun.com/ares/studio/jres/references";					
		addAnnotation
		  (typeDefaultValueEClass, 
		   source, 
		   new String[] {
			 "refId", "com.hundsun.ares.studio.jres.model.metadata.util.MetadataReferenceParser jres.md.typedef"
		   },
		   new URI[] {
			 URI.createURI(eNS_URI).appendFragment("//TypeDefaultValue")
		   });										
	}

} //MetadataPackageImpl
