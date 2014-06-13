/**
 * 源程序名称：DecryptAdapterFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem;
import com.hundsun.ares.studio.jres.metadata.service.IDictionaryType;
import com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem;
import com.hundsun.ares.studio.jres.metadata.service.IStandardDataType;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue;
import com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem;
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
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage
 * @generated
 */
public class DecryptAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DecryptPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecryptAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DecryptPackage.eINSTANCE;
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
	protected DecryptSwitch<Adapter> modelSwitch =
		new DecryptSwitch<Adapter>() {
			@Override
			public Adapter caseITypeDefaultValue(ITypeDefaultValue object) {
				return createITypeDefaultValueAdapter();
			}
			@Override
			public Adapter caseIStandardDataType(IStandardDataType object) {
				return createIStandardDataTypeAdapter();
			}
			@Override
			public Adapter caseIBusinessDataType(IBusinessDataType object) {
				return createIBusinessDataTypeAdapter();
			}
			@Override
			public Adapter caseIStandardField(IStandardField object) {
				return createIStandardFieldAdapter();
			}
			@Override
			public Adapter caseIDictionaryType(IDictionaryType object) {
				return createIDictionaryTypeAdapter();
			}
			@Override
			public Adapter caseIDictionaryItem(IDictionaryItem object) {
				return createIDictionaryItemAdapter();
			}
			@Override
			public Adapter caseIErrorNoItem(IErrorNoItem object) {
				return createIErrorNoItemAdapter();
			}
			@Override
			public Adapter caseIUserConstantItem(IUserConstantItem object) {
				return createIUserConstantItemAdapter();
			}
			@Override
			public <T extends MetadataItem> Adapter caseDeMetadataItem(DeMetadataItem<T> object) {
				return createDeMetadataItemAdapter();
			}
			@Override
			public Adapter caseDeTypeDefaultValue(DeTypeDefaultValue object) {
				return createDeTypeDefaultValueAdapter();
			}
			@Override
			public Adapter caseDeStandardDataType(DeStandardDataType object) {
				return createDeStandardDataTypeAdapter();
			}
			@Override
			public Adapter caseDeBusinessDataType(DeBusinessDataType object) {
				return createDeBusinessDataTypeAdapter();
			}
			@Override
			public Adapter caseDeStandardField(DeStandardField object) {
				return createDeStandardFieldAdapter();
			}
			@Override
			public Adapter caseDeDictionaryType(DeDictionaryType object) {
				return createDeDictionaryTypeAdapter();
			}
			@Override
			public Adapter caseDeDictionaryItem(DeDictionaryItem object) {
				return createDeDictionaryItemAdapter();
			}
			@Override
			public Adapter caseDeConstantItem(DeConstantItem object) {
				return createDeConstantItemAdapter();
			}
			@Override
			public Adapter caseDeErrorNoItem(DeErrorNoItem object) {
				return createDeErrorNoItemAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue <em>IType Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue
	 * @generated
	 */
	public Adapter createITypeDefaultValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.metadata.service.IStandardDataType <em>IStandard Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IStandardDataType
	 * @generated
	 */
	public Adapter createIStandardDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType <em>IBusiness Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType
	 * @generated
	 */
	public Adapter createIBusinessDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.metadata.service.IStandardField <em>IStandard Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IStandardField
	 * @generated
	 */
	public Adapter createIStandardFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.metadata.service.IDictionaryType <em>IDictionary Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IDictionaryType
	 * @generated
	 */
	public Adapter createIDictionaryTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem <em>IDictionary Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem
	 * @generated
	 */
	public Adapter createIDictionaryItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem <em>IError No Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem
	 * @generated
	 */
	public Adapter createIErrorNoItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem <em>IUser Constant Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem
	 * @generated
	 */
	public Adapter createIUserConstantItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem <em>De Metadata Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem
	 * @generated
	 */
	public Adapter createDeMetadataItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeTypeDefaultValue <em>De Type Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeTypeDefaultValue
	 * @generated
	 */
	public Adapter createDeTypeDefaultValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType <em>De Standard Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType
	 * @generated
	 */
	public Adapter createDeStandardDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType <em>De Business Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType
	 * @generated
	 */
	public Adapter createDeBusinessDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField <em>De Standard Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField
	 * @generated
	 */
	public Adapter createDeStandardFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType <em>De Dictionary Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType
	 * @generated
	 */
	public Adapter createDeDictionaryTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem <em>De Dictionary Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem
	 * @generated
	 */
	public Adapter createDeDictionaryItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeConstantItem <em>De Constant Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeConstantItem
	 * @generated
	 */
	public Adapter createDeConstantItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeErrorNoItem <em>De Error No Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeErrorNoItem
	 * @generated
	 */
	public Adapter createDeErrorNoItemAdapter() {
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

} //DecryptAdapterFactory
