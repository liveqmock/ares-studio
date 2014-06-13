/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow.util;

import com.hundsun.ares.studio.flow.model.flow.*;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.flow.model.flow.Begin;
import com.hundsun.ares.studio.flow.model.flow.Bendpoint;
import com.hundsun.ares.studio.flow.model.flow.ColorView;
import com.hundsun.ares.studio.flow.model.flow.Connection;
import com.hundsun.ares.studio.flow.model.flow.End;
import com.hundsun.ares.studio.flow.model.flow.FigureNode;
import com.hundsun.ares.studio.flow.model.flow.FlowModel;
import com.hundsun.ares.studio.flow.model.flow.FlowNode;
import com.hundsun.ares.studio.flow.model.flow.FlowPackage;
import com.hundsun.ares.studio.flow.model.flow.IDataView;
import com.hundsun.ares.studio.flow.model.flow.IViewProvider;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage
 * @generated
 */
public class FlowSwitch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FlowPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowSwitch() {
		if (modelPackage == null) {
			modelPackage = FlowPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T1 doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T1 doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case FlowPackage.IVIEW_PROVIDER: {
				IViewProvider iViewProvider = (IViewProvider)theEObject;
				T1 result = caseIViewProvider(iViewProvider);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.FIGURE_NODE: {
				FigureNode figureNode = (FigureNode)theEObject;
				T1 result = caseFigureNode(figureNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.BEGIN: {
				Begin begin = (Begin)theEObject;
				T1 result = caseBegin(begin);
				if (result == null) result = caseIViewProvider(begin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.END: {
				End end = (End)theEObject;
				T1 result = caseEnd(end);
				if (result == null) result = caseIViewProvider(end);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.FLOW_NODE: {
				FlowNode flowNode = (FlowNode)theEObject;
				T1 result = caseFlowNode(flowNode);
				if (result == null) result = caseIViewProvider(flowNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.IDATA_VIEW: {
				IDataView iDataView = (IDataView)theEObject;
				T1 result = caseIDataView(iDataView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.CONNECTION: {
				Connection connection = (Connection)theEObject;
				T1 result = caseConnection(connection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.COLOR_VIEW: {
				ColorView colorView = (ColorView)theEObject;
				T1 result = caseColorView(colorView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.BENDPOINT: {
				Bendpoint bendpoint = (Bendpoint)theEObject;
				T1 result = caseBendpoint(bendpoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.FLOW_MODEL: {
				FlowModel<?, ?, ?> flowModel = (FlowModel<?, ?, ?>)theEObject;
				T1 result = caseFlowModel(flowModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IView Provider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IView Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIViewProvider(IViewProvider object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Figure Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Figure Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFigureNode(FigureNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Begin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Begin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBegin(Begin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>End</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>End</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEnd(End object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFlowNode(FlowNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IData View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IData View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIDataView(IDataView object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseConnection(Connection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Color View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Color View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseColorView(ColorView object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends FlowNode, K extends Begin, V extends End> T1 caseFlowModel(FlowModel<T, K, V> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bendpoint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bendpoint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBendpoint(Bendpoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T1 defaultCase(EObject object) {
		return null;
	}

} //FlowSwitch
