/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>table type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#gettable_type()
 * @model
 * @generated
 */
public enum table_type implements Enumerator {
	/**
	 * The '<em><b>COMMON</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMMON_VALUE
	 * @generated
	 * @ordered
	 */
	COMMON(0, "COMMON", "COMMON"),

	/**
	 * The '<em><b>TEMP NO VALUE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEMP_NO_VALUE_VALUE
	 * @generated
	 * @ordered
	 */
	TEMP_NO_VALUE(1, "TEMP_NO_VALUE", "TEMP_NO_VALUE"),

	/**
	 * The '<em><b>TEMP WITH VALUE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEMP_WITH_VALUE_VALUE
	 * @generated
	 * @ordered
	 */
	TEMP_WITH_VALUE(2, "TEMP_WITH_VALUE", "TEMP_WITH_VALUE");

	/**
	 * The '<em><b>COMMON</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMMON</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMMON
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COMMON_VALUE = 0;

	/**
	 * The '<em><b>TEMP NO VALUE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TEMP NO VALUE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEMP_NO_VALUE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TEMP_NO_VALUE_VALUE = 1;

	/**
	 * The '<em><b>TEMP WITH VALUE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TEMP WITH VALUE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEMP_WITH_VALUE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TEMP_WITH_VALUE_VALUE = 2;

	/**
	 * An array of all the '<em><b>table type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final table_type[] VALUES_ARRAY =
		new table_type[] {
			COMMON,
			TEMP_NO_VALUE,
			TEMP_WITH_VALUE,
		};

	/**
	 * A public read-only list of all the '<em><b>table type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<table_type> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>table type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static table_type get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			table_type result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>table type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static table_type getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			table_type result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>table type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static table_type get(int value) {
		switch (value) {
			case COMMON_VALUE: return COMMON;
			case TEMP_NO_VALUE_VALUE: return TEMP_NO_VALUE;
			case TEMP_WITH_VALUE_VALUE: return TEMP_WITH_VALUE;
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
	private table_type(int value, String name, String literal) {
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
	
} //table_type
