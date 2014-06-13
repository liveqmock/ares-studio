/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow;

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
 * @see com.hundsun.ares.studio.flow.model.flow.FlowFactory
 * @model kind="package"
 * @generated
 */
public interface FlowPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "flow";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://com.hundsun.ares.studio.flow";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "flow";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FlowPackage eINSTANCE = com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.IViewProviderImpl <em>IView Provider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.IViewProviderImpl
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getIViewProvider()
	 * @generated
	 */
	int IVIEW_PROVIDER = 0;

	/**
	 * The number of structural features of the '<em>IView Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IVIEW_PROVIDER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.FigureNodeImpl <em>Figure Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FigureNodeImpl
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getFigureNode()
	 * @generated
	 */
	int FIGURE_NODE = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE_NODE__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Color View</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE_NODE__COLOR_VIEW = 1;

	/**
	 * The feature id for the '<em><b>Bounds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE_NODE__BOUNDS = 2;

	/**
	 * The number of structural features of the '<em>Figure Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE_NODE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.BeginImpl <em>Begin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.BeginImpl
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getBegin()
	 * @generated
	 */
	int BEGIN = 2;

	/**
	 * The feature id for the '<em><b>Figure Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN__FIGURE_NODE = IVIEW_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Begin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_FEATURE_COUNT = IVIEW_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.EndImpl <em>End</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.EndImpl
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getEnd()
	 * @generated
	 */
	int END = 3;

	/**
	 * The feature id for the '<em><b>Figure Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__FIGURE_NODE = IVIEW_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>End</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_FEATURE_COUNT = IVIEW_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.FlowNodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowNodeImpl
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getFlowNode()
	 * @generated
	 */
	int FLOW_NODE = 4;

	/**
	 * The feature id for the '<em><b>Figure Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_NODE__FIGURE_NODE = IVIEW_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_NODE_FEATURE_COUNT = IVIEW_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.IDataViewImpl <em>IData View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.IDataViewImpl
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getIDataView()
	 * @generated
	 */
	int IDATA_VIEW = 5;

	/**
	 * The number of structural features of the '<em>IData View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDATA_VIEW_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.ConnectionImpl
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 6;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__TARGET = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__SOURCE = 1;

	/**
	 * The feature id for the '<em><b>Color View</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__COLOR_VIEW = 2;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__VALUE = 3;

	/**
	 * The feature id for the '<em><b>Bendpoints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__BENDPOINTS = 4;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.ColorViewImpl <em>Color View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.ColorViewImpl
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getColorView()
	 * @generated
	 */
	int COLOR_VIEW = 7;

	/**
	 * The feature id for the '<em><b>Frontground</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_VIEW__FRONTGROUND = 0;

	/**
	 * The feature id for the '<em><b>Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_VIEW__BACKGROUND = 1;

	/**
	 * The number of structural features of the '<em>Color View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_VIEW_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.FlowModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowModelImpl
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getFlowModel()
	 * @generated
	 */
	int FLOW_MODEL = 9;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.BendpointImpl <em>Bendpoint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.BendpointImpl
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getBendpoint()
	 * @generated
	 */
	int BENDPOINT = 8;

	/**
	 * The feature id for the '<em><b>First Relative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENDPOINT__FIRST_RELATIVE = 0;

	/**
	 * The feature id for the '<em><b>Second Relative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENDPOINT__SECOND_RELATIVE = 1;

	/**
	 * The number of structural features of the '<em>Bendpoint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENDPOINT_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Begin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_MODEL__BEGIN = 0;

	/**
	 * The feature id for the '<em><b>Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_MODEL__ENDS = 1;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_MODEL__NODES = 2;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_MODEL__CONNECTIONS = 3;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_MODEL_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '<em>RGB</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.swt.graphics.RGB
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getRGB()
	 * @generated
	 */
	int RGB = 10;

	/**
	 * The meta object id for the '<em>Point</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.swt.graphics.Point
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getPoint()
	 * @generated
	 */
	int POINT = 11;

	/**
	 * The meta object id for the '<em>Rectangle</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.draw2d.geometry.Rectangle
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getRectangle()
	 * @generated
	 */
	int RECTANGLE = 12;


	/**
	 * The meta object id for the '<em>Dimension</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.draw2d.geometry.Dimension
	 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getDimension()
	 * @generated
	 */
	int DIMENSION = 13;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.flow.model.flow.IViewProvider <em>IView Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IView Provider</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.IViewProvider
	 * @generated
	 */
	EClass getIViewProvider();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.flow.model.flow.FigureNode <em>Figure Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Figure Node</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FigureNode
	 * @generated
	 */
	EClass getFigureNode();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.flow.model.flow.FigureNode#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FigureNode#getDescription()
	 * @see #getFigureNode()
	 * @generated
	 */
	EAttribute getFigureNode_Description();

	/**
	 * Returns the meta object for the containment reference '{@link com.hundsun.ares.studio.flow.model.flow.FigureNode#getColorView <em>Color View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Color View</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FigureNode#getColorView()
	 * @see #getFigureNode()
	 * @generated
	 */
	EReference getFigureNode_ColorView();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.flow.model.flow.FigureNode#getBounds <em>Bounds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bounds</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FigureNode#getBounds()
	 * @see #getFigureNode()
	 * @generated
	 */
	EAttribute getFigureNode_Bounds();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.flow.model.flow.Begin <em>Begin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Begin</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Begin
	 * @generated
	 */
	EClass getBegin();

	/**
	 * Returns the meta object for the containment reference '{@link com.hundsun.ares.studio.flow.model.flow.Begin#getFigureNode <em>Figure Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Figure Node</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Begin#getFigureNode()
	 * @see #getBegin()
	 * @generated
	 */
	EReference getBegin_FigureNode();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.flow.model.flow.End <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.End
	 * @generated
	 */
	EClass getEnd();

	/**
	 * Returns the meta object for the containment reference '{@link com.hundsun.ares.studio.flow.model.flow.End#getFigureNode <em>Figure Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Figure Node</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.End#getFigureNode()
	 * @see #getEnd()
	 * @generated
	 */
	EReference getEnd_FigureNode();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.flow.model.flow.FlowNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowNode
	 * @generated
	 */
	EClass getFlowNode();

	/**
	 * Returns the meta object for the containment reference '{@link com.hundsun.ares.studio.flow.model.flow.FlowNode#getFigureNode <em>Figure Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Figure Node</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowNode#getFigureNode()
	 * @see #getFlowNode()
	 * @generated
	 */
	EReference getFlowNode_FigureNode();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.flow.model.flow.IDataView <em>IData View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IData View</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.IDataView
	 * @generated
	 */
	EClass getIDataView();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.flow.model.flow.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the reference '{@link com.hundsun.ares.studio.flow.model.flow.Connection#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Connection#getTarget()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Target();

	/**
	 * Returns the meta object for the reference '{@link com.hundsun.ares.studio.flow.model.flow.Connection#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Connection#getSource()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Source();

	/**
	 * Returns the meta object for the containment reference '{@link com.hundsun.ares.studio.flow.model.flow.Connection#getColorView <em>Color View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Color View</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Connection#getColorView()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_ColorView();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.flow.model.flow.Connection#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Connection#getValue()
	 * @see #getConnection()
	 * @generated
	 */
	EAttribute getConnection_Value();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.flow.model.flow.Connection#getBendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bendpoints</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Connection#getBendpoints()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Bendpoints();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.flow.model.flow.ColorView <em>Color View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Color View</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.ColorView
	 * @generated
	 */
	EClass getColorView();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.flow.model.flow.ColorView#getFrontground <em>Frontground</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frontground</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.ColorView#getFrontground()
	 * @see #getColorView()
	 * @generated
	 */
	EAttribute getColorView_Frontground();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.flow.model.flow.ColorView#getBackground <em>Background</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Background</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.ColorView#getBackground()
	 * @see #getColorView()
	 * @generated
	 */
	EAttribute getColorView_Background();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.flow.model.flow.FlowModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowModel
	 * @generated
	 */
	EClass getFlowModel();

	/**
	 * Returns the meta object for the containment reference '{@link com.hundsun.ares.studio.flow.model.flow.FlowModel#getBegin <em>Begin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Begin</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowModel#getBegin()
	 * @see #getFlowModel()
	 * @generated
	 */
	EReference getFlowModel_Begin();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.flow.model.flow.FlowModel#getEnds <em>Ends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ends</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowModel#getEnds()
	 * @see #getFlowModel()
	 * @generated
	 */
	EReference getFlowModel_Ends();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.flow.model.flow.FlowModel#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowModel#getNodes()
	 * @see #getFlowModel()
	 * @generated
	 */
	EReference getFlowModel_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.flow.model.flow.FlowModel#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowModel#getConnections()
	 * @see #getFlowModel()
	 * @generated
	 */
	EReference getFlowModel_Connections();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.flow.model.flow.Bendpoint <em>Bendpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bendpoint</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Bendpoint
	 * @generated
	 */
	EClass getBendpoint();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.flow.model.flow.Bendpoint#getFirstRelative <em>First Relative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Relative</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Bendpoint#getFirstRelative()
	 * @see #getBendpoint()
	 * @generated
	 */
	EAttribute getBendpoint_FirstRelative();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.flow.model.flow.Bendpoint#getSecondRelative <em>Second Relative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Second Relative</em>'.
	 * @see com.hundsun.ares.studio.flow.model.flow.Bendpoint#getSecondRelative()
	 * @see #getBendpoint()
	 * @generated
	 */
	EAttribute getBendpoint_SecondRelative();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.swt.graphics.RGB <em>RGB</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>RGB</em>'.
	 * @see org.eclipse.swt.graphics.RGB
	 * @model instanceClass="org.eclipse.swt.graphics.RGB"
	 * @generated
	 */
	EDataType getRGB();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.swt.graphics.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Point</em>'.
	 * @see org.eclipse.swt.graphics.Point
	 * @model instanceClass="org.eclipse.swt.graphics.Point"
	 * @generated
	 */
	EDataType getPoint();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.draw2d.geometry.Rectangle <em>Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Rectangle</em>'.
	 * @see org.eclipse.draw2d.geometry.Rectangle
	 * @model instanceClass="org.eclipse.draw2d.geometry.Rectangle"
	 * @generated
	 */
	EDataType getRectangle();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.draw2d.geometry.Dimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Dimension</em>'.
	 * @see org.eclipse.draw2d.geometry.Dimension
	 * @model instanceClass="org.eclipse.draw2d.geometry.Dimension"
	 * @generated
	 */
	EDataType getDimension();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FlowFactory getFlowFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.IViewProviderImpl <em>IView Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.IViewProviderImpl
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getIViewProvider()
		 * @generated
		 */
		EClass IVIEW_PROVIDER = eINSTANCE.getIViewProvider();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.FigureNodeImpl <em>Figure Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FigureNodeImpl
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getFigureNode()
		 * @generated
		 */
		EClass FIGURE_NODE = eINSTANCE.getFigureNode();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIGURE_NODE__DESCRIPTION = eINSTANCE.getFigureNode_Description();

		/**
		 * The meta object literal for the '<em><b>Color View</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIGURE_NODE__COLOR_VIEW = eINSTANCE.getFigureNode_ColorView();

		/**
		 * The meta object literal for the '<em><b>Bounds</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIGURE_NODE__BOUNDS = eINSTANCE.getFigureNode_Bounds();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.BeginImpl <em>Begin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.BeginImpl
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getBegin()
		 * @generated
		 */
		EClass BEGIN = eINSTANCE.getBegin();

		/**
		 * The meta object literal for the '<em><b>Figure Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEGIN__FIGURE_NODE = eINSTANCE.getBegin_FigureNode();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.EndImpl <em>End</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.EndImpl
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getEnd()
		 * @generated
		 */
		EClass END = eINSTANCE.getEnd();

		/**
		 * The meta object literal for the '<em><b>Figure Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference END__FIGURE_NODE = eINSTANCE.getEnd_FigureNode();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.FlowNodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowNodeImpl
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getFlowNode()
		 * @generated
		 */
		EClass FLOW_NODE = eINSTANCE.getFlowNode();

		/**
		 * The meta object literal for the '<em><b>Figure Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_NODE__FIGURE_NODE = eINSTANCE.getFlowNode_FigureNode();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.IDataViewImpl <em>IData View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.IDataViewImpl
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getIDataView()
		 * @generated
		 */
		EClass IDATA_VIEW = eINSTANCE.getIDataView();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.ConnectionImpl
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__TARGET = eINSTANCE.getConnection_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__SOURCE = eINSTANCE.getConnection_Source();

		/**
		 * The meta object literal for the '<em><b>Color View</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__COLOR_VIEW = eINSTANCE.getConnection_ColorView();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION__VALUE = eINSTANCE.getConnection_Value();

		/**
		 * The meta object literal for the '<em><b>Bendpoints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__BENDPOINTS = eINSTANCE.getConnection_Bendpoints();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.ColorViewImpl <em>Color View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.ColorViewImpl
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getColorView()
		 * @generated
		 */
		EClass COLOR_VIEW = eINSTANCE.getColorView();

		/**
		 * The meta object literal for the '<em><b>Frontground</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR_VIEW__FRONTGROUND = eINSTANCE.getColorView_Frontground();

		/**
		 * The meta object literal for the '<em><b>Background</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR_VIEW__BACKGROUND = eINSTANCE.getColorView_Background();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.FlowModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowModelImpl
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getFlowModel()
		 * @generated
		 */
		EClass FLOW_MODEL = eINSTANCE.getFlowModel();

		/**
		 * The meta object literal for the '<em><b>Begin</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_MODEL__BEGIN = eINSTANCE.getFlowModel_Begin();

		/**
		 * The meta object literal for the '<em><b>Ends</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_MODEL__ENDS = eINSTANCE.getFlowModel_Ends();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_MODEL__NODES = eINSTANCE.getFlowModel_Nodes();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_MODEL__CONNECTIONS = eINSTANCE.getFlowModel_Connections();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.flow.model.flow.impl.BendpointImpl <em>Bendpoint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.BendpointImpl
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getBendpoint()
		 * @generated
		 */
		EClass BENDPOINT = eINSTANCE.getBendpoint();

		/**
		 * The meta object literal for the '<em><b>First Relative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENDPOINT__FIRST_RELATIVE = eINSTANCE.getBendpoint_FirstRelative();

		/**
		 * The meta object literal for the '<em><b>Second Relative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENDPOINT__SECOND_RELATIVE = eINSTANCE.getBendpoint_SecondRelative();

		/**
		 * The meta object literal for the '<em>RGB</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.swt.graphics.RGB
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getRGB()
		 * @generated
		 */
		EDataType RGB = eINSTANCE.getRGB();

		/**
		 * The meta object literal for the '<em>Point</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.swt.graphics.Point
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getPoint()
		 * @generated
		 */
		EDataType POINT = eINSTANCE.getPoint();

		/**
		 * The meta object literal for the '<em>Rectangle</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.draw2d.geometry.Rectangle
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getRectangle()
		 * @generated
		 */
		EDataType RECTANGLE = eINSTANCE.getRectangle();

		/**
		 * The meta object literal for the '<em>Dimension</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.draw2d.geometry.Dimension
		 * @see com.hundsun.ares.studio.flow.model.flow.impl.FlowPackageImpl#getDimension()
		 * @generated
		 */
		EDataType DIMENSION = eINSTANCE.getDimension();

	}

} //FlowPackage
