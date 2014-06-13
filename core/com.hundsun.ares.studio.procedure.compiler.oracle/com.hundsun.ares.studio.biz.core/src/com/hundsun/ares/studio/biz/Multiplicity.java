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
 * A representation of the literals of the enumeration '<em><b>Multiplicity</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.biz.BizPackage#getMultiplicity()
 * @model
 * @generated
 */
public enum Multiplicity implements Enumerator {
	/**
	 * The '<em><b>ONE ONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ONE_ONE_VALUE
	 * @generated
	 * @ordered
	 */
	ONE_ONE(1, "ONE_ONE", "[1..1]"),

	/**
	 * The '<em><b>ZERO ONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ZERO_ONE_VALUE
	 * @generated
	 * @ordered
	 */
	ZERO_ONE(0, "ZERO_ONE", "[0..1]"),

	/**
	 * The '<em><b>ZERO MORE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ZERO_MORE_VALUE
	 * @generated
	 * @ordered
	 */
	ZERO_MORE(2, "ZERO_MORE", "[0..n]"),

	/**
	 * The '<em><b>ONE MORE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ONE_MORE_VALUE
	 * @generated
	 * @ordered
	 */
	ONE_MORE(3, "ONE_MORE", "[1..n]");

	/**
	 * The '<em><b>ONE ONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ONE ONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ONE_ONE
	 * @model literal="[1..1]"
	 * @generated
	 * @ordered
	 */
	public static final int ONE_ONE_VALUE = 1;

	/**
	 * The '<em><b>ZERO ONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ZERO ONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ZERO_ONE
	 * @model literal="[0..1]"
	 * @generated
	 * @ordered
	 */
	public static final int ZERO_ONE_VALUE = 0;

	/**
	 * The '<em><b>ZERO MORE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ZERO MORE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ZERO_MORE
	 * @model literal="[0..n]"
	 * @generated
	 * @ordered
	 */
	public static final int ZERO_MORE_VALUE = 2;

	/**
	 * The '<em><b>ONE MORE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ONE MORE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ONE_MORE
	 * @model literal="[1..n]"
	 * @generated
	 * @ordered
	 */
	public static final int ONE_MORE_VALUE = 3;

	/**
	 * An array of all the '<em><b>Multiplicity</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Multiplicity[] VALUES_ARRAY =
		new Multiplicity[] {
			ONE_ONE,
			ZERO_ONE,
			ZERO_MORE,
			ONE_MORE,
		};

	/**
	 * A public read-only list of all the '<em><b>Multiplicity</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Multiplicity> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Multiplicity</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Multiplicity get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Multiplicity result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Multiplicity</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Multiplicity getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Multiplicity result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Multiplicity</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Multiplicity get(int value) {
		switch (value) {
			case ONE_ONE_VALUE: return ONE_ONE;
			case ZERO_ONE_VALUE: return ZERO_ONE;
			case ZERO_MORE_VALUE: return ZERO_MORE;
			case ONE_MORE_VALUE: return ONE_MORE;
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
	private Multiplicity(int value, String name, String literal) {
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
	
} //Multiplicity
