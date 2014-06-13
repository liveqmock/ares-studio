/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.atom.util;

import com.hundsun.ares.studio.atom.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.InternalParam;
import com.hundsun.ares.studio.atom.constants.IAtomConstants;
import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.util.BizValidator;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IValidateConstant;
import com.hundsun.ares.studio.core.ResourceTypeMapping;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.util.ResourcesUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.util.IDRangeUtil;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.atom.AtomPackage
 * @generated
 */
public class AtomValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final AtomValidator INSTANCE = new AtomValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.hundsun.ares.studio.atom";

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
	protected BizValidator bizValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomValidator() {
		super();
		bizValidator = BizValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return AtomPackage.eINSTANCE;
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
			case AtomPackage.ATOM_FUNCTION:
				return validateAtomFunction((AtomFunction)value, diagnostics, context);
			case AtomPackage.ATOM_SERVICE:
				return validateAtomService((AtomService)value, diagnostics, context);
			case AtomPackage.INTERNAL_PARAM:
				return validateInternalParam((InternalParam)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAtomFunction(AtomFunction atomFunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(atomFunction, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(atomFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(atomFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(atomFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(atomFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(atomFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(atomFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(atomFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(atomFunction, diagnostics, context);
		if (result || diagnostics != null) result &= validateAtomFunction_objectId(atomFunction, diagnostics, context);
		return result;
	}

	/**
	 * Validates the objectId constraint of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateAtomFunction_objectId(AtomFunction atomFunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		String type = resource.getType();
		String idExtendKey = StringUtils.equals(type, IAtomResType.ATOM_FUNCTION) ? IAtomConstants.FUNCTION_ID_RANGE_KEY : IAtomConstants.SERVICE_ID_RANGE_KEY;
		return validateObjectID(atomFunction, diagnostics, context, idExtendKey);
	}
	
	public boolean validateObjectID(AtomFunction function, DiagnosticChain diagnostics, Map<Object, Object> context,String idExtendKey){
		String objID = function.getObjectId();
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		if (StringUtils.isNotBlank(objID)) {
			try {
				int value = Integer.parseInt(objID);
				String range = IDRangeUtil.getIDRange(resource,idExtendKey);
				if(!IDRangeUtil.isInRange(value, range)){
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								String.format("功能号不在%s范围内",range), 
								new Object[]{function,
								CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID}));
					}
					return false;
				}
				
			} catch (Exception e) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"功能号不是数字", 
							new Object[]{function,
							CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID}));
				}
				return false;
			}
			
			IARESProject project =(IARESProject) context.get(IValidateConstant.KEY_RESOUCE_PROJECT);
			String type = ResourceTypeMapping.getInstance().getReferenceType(resource.getType());
			List<ReferenceInfo> referInfos = ReferenceManager.getInstance().getReferenceInfos(project, type, false);
			for(ReferenceInfo info : referInfos){
				Object obj = info.getObject();
				if (obj instanceof AtomFunction) {
					if (!StringUtils.equals(function.getFullyQualifiedName(), ((AtomFunction) obj).getFullyQualifiedName()) &&
							StringUtils.equals(((AtomFunction) obj).getObjectId(), function.getObjectId())) {
						if(diagnostics != null){
							StringBuffer sb = new StringBuffer();
							sb.append("对象号重复：");
							sb.append(ResourcesUtil.getChineseFileName("/", info.getResource().getModule())+"/"+((AtomFunction) obj).getChineseName()+"["+function.getName()+"]");
							diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									sb.toString(), 
									new Object[]{function,
									CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID}));
						}
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAtomService(AtomService atomService, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(atomService, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(atomService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(atomService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(atomService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(atomService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(atomService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(atomService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(atomService, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(atomService, diagnostics, context);
		if (result || diagnostics != null) result &= validateAtomService_objectId(atomService, diagnostics, context);
		return result;
	}

	/**
	 * Validates the objectId constraint of '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateAtomService_objectId(AtomService atomService, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if(StringUtils.isBlank(atomService.getObjectId())){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						String.format("功能号不能为空"), 
						new Object[]{atomService,
						CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID}));
			}
			return false;
		}
		return validateAtomFunction_objectId(atomService, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInternalParam(InternalParam internalParam, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(internalParam, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(internalParam, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(internalParam, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(internalParam, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(internalParam, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(internalParam, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(internalParam, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(internalParam, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(internalParam, diagnostics, context);
		if (result || diagnostics != null) result &= validateInternalParam_id(internalParam, diagnostics, context);
		if (result || diagnostics != null) result &= validateInternalParam_type(internalParam, diagnostics, context);
		return result;
	}

	/**
	 * Validates the id constraint of '<em>Internal Param</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateInternalParam_id(InternalParam internalParam, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return bizValidator.validateParameter_id(internalParam, diagnostics, context);
	}

	/**
	 * Validates the type constraint of '<em>Internal Param</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateInternalParam_type(InternalParam internalParam, DiagnosticChain diagnostics, Map<Object, Object> context) {		
		
		String type = internalParam.getType();
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		ParamType paramType = internalParam.getParamType();
		
		if(paramType.equals(ParamType.NON_STD_FIELD)){
			if(StringUtils.isBlank(type)){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"业务类型不能为空", 
							new Object[]{internalParam,
							BizPackage.Literals.PARAMETER__TYPE
					}));
				}
				return false;
			}
			
			Pattern pattern = Pattern.compile("^\\w*\\[[0-9]+\\]$");
			if(pattern.matcher(type).matches()){
				type = StringUtils.substringBefore(type, "[");
			}
			ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.BizType, type, true);
			if(info == null){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							String.format("业务类型%s不存在",type), 
							new Object[]{internalParam,
							BizPackage.Literals.PARAMETER__TYPE
					}));
				}
				return false;
			}
			return true;
		}
		
		return bizValidator.validateParameter_type(internalParam, diagnostics, context);
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

} //AtomValidator
