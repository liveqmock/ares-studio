/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow.impl;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import com.hundsun.ares.studio.flow.model.flow.Bendpoint;
import com.hundsun.ares.studio.flow.model.flow.ColorView;
import com.hundsun.ares.studio.flow.model.flow.Connection;
import com.hundsun.ares.studio.flow.model.flow.FigureNode;
import com.hundsun.ares.studio.flow.model.flow.FlowFactory;
import com.hundsun.ares.studio.flow.model.flow.FlowPackage;
import com.hundsun.ares.studio.flow.model.flow.IDataView;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FlowFactoryImpl extends EFactoryImpl implements FlowFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FlowFactory init() {
		try {
			FlowFactory theFlowFactory = (FlowFactory)EPackage.Registry.INSTANCE.getEFactory("http://com.hundsun.ares.studio.flow"); 
			if (theFlowFactory != null) {
				return theFlowFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FlowFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowFactoryImpl() {
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
			case FlowPackage.FIGURE_NODE: return createFigureNode();
			case FlowPackage.IDATA_VIEW: return createIDataView();
			case FlowPackage.CONNECTION: return createConnection();
			case FlowPackage.COLOR_VIEW: return createColorView();
			case FlowPackage.BENDPOINT: return createBendpoint();
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
			case FlowPackage.RGB:
				return createRGBFromString(eDataType, initialValue);
			case FlowPackage.POINT:
				return createPointFromString(eDataType, initialValue);
			case FlowPackage.RECTANGLE:
				return createRectangleFromString(eDataType, initialValue);
			case FlowPackage.DIMENSION:
				return createDimensionFromString(eDataType, initialValue);
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
			case FlowPackage.RGB:
				return convertRGBToString(eDataType, instanceValue);
			case FlowPackage.POINT:
				return convertPointToString(eDataType, instanceValue);
			case FlowPackage.RECTANGLE:
				return convertRectangleToString(eDataType, instanceValue);
			case FlowPackage.DIMENSION:
				return convertDimensionToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FigureNode createFigureNode() {
		FigureNodeImpl figureNode = new FigureNodeImpl();
		return figureNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IDataView createIDataView() {
		IDataViewImpl iDataView = new IDataViewImpl();
		return iDataView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Connection createConnection() {
		ConnectionImpl connection = new ConnectionImpl();
		connection.setColorView(createColorView());
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColorView createColorView() {
		ColorViewImpl colorView = new ColorViewImpl();
		return colorView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bendpoint createBendpoint() {
		BendpointImpl bendpoint = new BendpointImpl();
		return bendpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public RGB createRGBFromString(EDataType eDataType, String initialValue) {
		String[] values = initialValue.split(",");
		int red = Integer.parseInt(values[0]);
		int green = Integer.parseInt(values[1]);
		int blue = Integer.parseInt(values[2]);
		RGB rgb = new RGB(red, green, blue);
		return rgb;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertRGBToString(EDataType eDataType, Object instanceValue) {
		RGB rgb = (RGB)instanceValue;
		return rgb.red + "," + rgb.green + "," + rgb.blue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Point createPointFromString(EDataType eDataType, String initialValue) {
		String[] values = initialValue.split(",");
		int x = Integer.parseInt(values[0]);
		int y = Integer.parseInt(values[1]);
		Point pt = new Point(x, y);
		return pt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertPointToString(EDataType eDataType, Object instanceValue) {
		Point pt = (Point)instanceValue;
		return pt.x + "," + pt.y;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Rectangle createRectangleFromString(EDataType eDataType, String initialValue) {
		String[] values = initialValue.split(",");
		int x = Integer.parseInt(values[0]);
		int y = Integer.parseInt(values[1]);
		int width = Integer.parseInt(values[2]);
		int height = Integer.parseInt(values[3]);
		Rectangle rect = new Rectangle(x, y, width, height);
		return rect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertRectangleToString(EDataType eDataType, Object instanceValue) {
		Rectangle rect = (Rectangle)instanceValue;
		return rect.x + "," + rect.y + "," + rect.width + "," + rect.height;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Dimension createDimensionFromString(EDataType eDataType, String initialValue) {
		String[] values = initialValue.split(",");
		int width = Integer.parseInt(values[0]);
		int height = Integer.parseInt(values[1]);
		Dimension dim = new Dimension(width, height);
		return dim;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertDimensionToString(EDataType eDataType, Object instanceValue) {
		Dimension dim = (Dimension)instanceValue;
		return dim.width + "," + dim.height;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowPackage getFlowPackage() {
		return (FlowPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FlowPackage getPackage() {
		return FlowPackage.eINSTANCE;
	}

} //FlowFactoryImpl
