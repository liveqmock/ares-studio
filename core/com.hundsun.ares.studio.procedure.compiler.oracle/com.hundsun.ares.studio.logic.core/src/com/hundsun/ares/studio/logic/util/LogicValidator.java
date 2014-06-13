/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.logic.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;

import com.hundsun.ares.studio.atom.constants.IAtomConstants;
import com.hundsun.ares.studio.atom.util.AtomValidator;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.LogicPackage;
import com.hundsun.ares.studio.logic.LogicService;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.logic.LogicPackage
 * @generated
 */
public class LogicValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final LogicValidator INSTANCE = new LogicValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.hundsun.ares.studio.logic";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AtomValidator atomValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicValidator() {
		super();
		atomValidator = AtomValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return LogicPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case LogicPackage.LOGIC_FUNCTION:
				return validateLogicFunction((LogicFunction)value, diagnostics, context);
			case LogicPackage.LOGIC_SERVICE:
				return validateLogicService((LogicService)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLogicFunction(LogicFunction logicFunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(logicFunction, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(logicFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(logicFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(logicFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(logicFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(logicFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(logicFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(logicFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(logicFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validateLogicFunction_objectId(logicFunction, diagnostics, context);
		return result;
	}

	/**
	 * Validates the objectId constraint of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateLogicFunction_objectId(LogicFunction logicFunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String idExtendKey = IAtomConstants.FUNCTION_ID_RANGE_KEY;
		return atomValidator.validateObjectID(logicFunction, diagnostics, context, idExtendKey);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLogicService(LogicService logicService, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(logicService, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(logicService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(logicService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(logicService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(logicService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(logicService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(logicService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(logicService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(logicService, diagnostics, context);
		if (result || diagnostics != null) result &= validateLogicService_objectId(logicService, diagnostics, context);
		return result;
	}

	/**
	 * Validates the objectId constraint of '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateLogicService_objectId(LogicService logicService, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if(StringUtils.isBlank(logicService.getObjectId())){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						String.format("功能号不能为空"), 
						new Object[]{logicService,
						CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID}));
			}
			return false;
		}
		
		String idExtendKey = IAtomConstants.SERVICE_ID_RANGE_KEY;
		return atomValidator.validateObjectID(logicService, diagnostics, context, idExtendKey);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //LogicValidator
