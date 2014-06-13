/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.biz;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Param Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.biz.BizPackage#getParamType()
 * @model
 * @generated
 */
public enum ParamType implements Enumerator {
	/**
	 * The '<em><b>STD FIELD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STD_FIELD_VALUE
	 * @generated
	 * @ordered
	 */
	STD_FIELD(0, "STD_FIELD", "STD_FIELD"),

	/**
	 * The '<em><b>NON STD FIELD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NON_STD_FIELD_VALUE
	 * @generated
	 * @ordered
	 */
	NON_STD_FIELD(1, "NON_STD_FIELD", "NON_STD_FIELD"),

	/**
	 * The '<em><b>PARAM GROUP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARAM_GROUP_VALUE
	 * @generated
	 * @ordered
	 */
	PARAM_GROUP(2, "PARAM_GROUP", "PARAM_GROUP"),

	/**
	 * The '<em><b>OBJECT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OBJECT_VALUE
	 * @generated
	 * @ordered
	 */
	OBJECT(3, "OBJECT", "OBJECT"), /**
	 * The '<em><b>COMPONENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPONENT_VALUE
	 * @generated
	 * @ordered
	 */
	COMPONENT(4, "COMPONENT", "COMPONENT");

	/**
	 * The '<em><b>STD FIELD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STD FIELD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STD_FIELD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STD_FIELD_VALUE = 0;

	/**
	 * The '<em><b>NON STD FIELD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NON STD FIELD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NON_STD_FIELD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NON_STD_FIELD_VALUE = 1;

	/**
	 * The '<em><b>PARAM GROUP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PARAM GROUP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARAM_GROUP
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PARAM_GROUP_VALUE = 2;

	/**
	 * The '<em><b>OBJECT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OBJECT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OBJECT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OBJECT_VALUE = 3;

	/**
	 * The '<em><b>COMPONENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPONENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMPONENT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT_VALUE = 4;

	/**
	 * An array of all the '<em><b>Param Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ParamType[] VALUES_ARRAY =
		new ParamType[] {
			STD_FIELD,
			NON_STD_FIELD,
			PARAM_GROUP,
			OBJECT,
			COMPONENT,
		};

	/**
	 * A public read-only list of all the '<em><b>Param Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ParamType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Param Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParamType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParamType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Param Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParamType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParamType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Param Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParamType get(int value) {
		switch (value) {
			case STD_FIELD_VALUE: return STD_FIELD;
			case NON_STD_FIELD_VALUE: return NON_STD_FIELD;
			case PARAM_GROUP_VALUE: return PARAM_GROUP;
			case OBJECT_VALUE: return OBJECT;
			case COMPONENT_VALUE: return COMPONENT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ParamType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ParamType
