/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.procdure.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.IJSONData;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.procdure.*;
import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.procdure.Procedure;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.procdure.ProcdurePackage
 * @generated
 */
public class ProcdureAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ProcdurePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcdureAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ProcdurePackage.eINSTANCE;
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
	protected ProcdureSwitch<Adapter> modelSwitch =
		new ProcdureSwitch<Adapter>() {
			@Override
			public Adapter caseProcedure(Procedure object) {
				return createProcedureAdapter();
			}
			@Override
			public Adapter caseRelatedInfo(RelatedInfo object) {
				return createRelatedInfoAdapter();
			}
			@Override
			public Adapter caseInternalParam(InternalParam object) {
				return createInternalParamAdapter();
			}
			@Override
			public Adapter caseBizInterface(BizInterface object) {
				return createBizInterfaceAdapter();
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
			public Adapter caseParameter(Parameter object) {
				return createParameterAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.procdure.Procedure <em>Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.procdure.Procedure
	 * @generated
	 */
	public Adapter createProcedureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.procdure.RelatedInfo <em>Related Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.procdure.RelatedInfo
	 * @generated
	 */
	public Adapter createRelatedInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.procdure.InternalParam <em>Internal Param</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.procdure.InternalParam
	 * @generated
	 */
	public Adapter createInternalParamAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.biz.BizInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.biz.BizInterface
	 * @generated
	 */
	public Adapter createBizInterfaceAdapter() {
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
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.biz.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.biz.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
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

} //ProcdureAdapterFactory
