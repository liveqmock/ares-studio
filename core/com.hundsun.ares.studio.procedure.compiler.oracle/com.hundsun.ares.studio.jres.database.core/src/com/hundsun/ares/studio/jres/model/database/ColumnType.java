/**
 */
package com.hundsun.ares.studio.jres.model.database;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Column Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getColumnType()
 * @model
 * @generated
 */
public enum ColumnType implements Enumerator {
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
	NON_STD_FIELD(1, "NON_STD_FIELD", "NON_STD_FIELD");

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
	 * An array of all the '<em><b>Column Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ColumnType[] VALUES_ARRAY =
		new ColumnType[] {
			STD_FIELD,
			NON_STD_FIELD,
		};

	/**
	 * A public read-only list of all the '<em><b>Column Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ColumnType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Column Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ColumnType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ColumnType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Column Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ColumnType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ColumnType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Column Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ColumnType get(int value) {
		switch (value) {
			case STD_FIELD_VALUE: return STD_FIELD;
			case NON_STD_FIELD_VALUE: return NON_STD_FIELD;
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
	private ColumnType(int value, String name, String literal) {
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
	
} //ColumnType
