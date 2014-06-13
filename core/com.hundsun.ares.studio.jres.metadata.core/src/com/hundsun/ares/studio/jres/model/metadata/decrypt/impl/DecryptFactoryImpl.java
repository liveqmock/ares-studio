/**
 * 源程序名称：DecryptFactoryImpl.java
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DecryptFactoryImpl extends EFactoryImpl implements DecryptFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DecryptFactory init() {
		try {
			DecryptFactory theDecryptFactory = (DecryptFactory)EPackage.Registry.INSTANCE.getEFactory(DecryptPackage.eNS_URI);
			if (theDecryptFactory != null) {
				return theDecryptFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DecryptFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecryptFactoryImpl() {
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
			case DecryptPackage.DE_METADATA_ITEM: return createDeMetadataItem();
			case DecryptPackage.DE_TYPE_DEFAULT_VALUE: return createDeTypeDefaultValue();
			case DecryptPackage.DE_STANDARD_DATA_TYPE: return createDeStandardDataType();
			case DecryptPackage.DE_BUSINESS_DATA_TYPE: return createDeBusinessDataType();
			case DecryptPackage.DE_STANDARD_FIELD: return createDeStandardField();
			case DecryptPackage.DE_DICTIONARY_TYPE: return createDeDictionaryType();
			case DecryptPackage.DE_DICTIONARY_ITEM: return createDeDictionaryItem();
			case DecryptPackage.DE_CONSTANT_ITEM: return createDeConstantItem();
			case DecryptPackage.DE_ERROR_NO_ITEM: return createDeErrorNoItem();
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
			case DecryptPackage.PAIR:
				return createPairFromString(eDataType, initialValue);
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
			case DecryptPackage.PAIR:
				return convertPairToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <T extends MetadataItem> DeMetadataItem<T> createDeMetadataItem() {
		DeMetadataItemImpl<T> deMetadataItem = new DeMetadataItemImpl<T>();
		return deMetadataItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeTypeDefaultValue createDeTypeDefaultValue() {
		DeTypeDefaultValueImpl deTypeDefaultValue = new DeTypeDefaultValueImpl();
		return deTypeDefaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeStandardDataType createDeStandardDataType() {
		DeStandardDataTypeImpl deStandardDataType = new DeStandardDataTypeImpl();
		return deStandardDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeBusinessDataType createDeBusinessDataType() {
		DeBusinessDataTypeImpl deBusinessDataType = new DeBusinessDataTypeImpl();
		return deBusinessDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeStandardField createDeStandardField() {
		DeStandardFieldImpl deStandardField = new DeStandardFieldImpl();
		return deStandardField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeDictionaryType createDeDictionaryType() {
		DeDictionaryTypeImpl deDictionaryType = new DeDictionaryTypeImpl();
		return deDictionaryType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeDictionaryItem createDeDictionaryItem() {
		DeDictionaryItemImpl deDictionaryItem = new DeDictionaryItemImpl();
		return deDictionaryItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeConstantItem createDeConstantItem() {
		DeConstantItemImpl deConstantItem = new DeConstantItemImpl();
		return deConstantItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeErrorNoItem createDeErrorNoItem() {
		DeErrorNoItemImpl deErrorNoItem = new DeErrorNoItemImpl();
		return deErrorNoItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Pair<?, ?> createPairFromString(EDataType eDataType, String initialValue) {
		return (Pair<?, ?>)super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPairToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecryptPackage getDecryptPackage() {
		return (DecryptPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DecryptPackage getPackage() {
		return DecryptPackage.eINSTANCE;
	}

} //DecryptFactoryImpl
