/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.procdure.util;

import com.hundsun.ares.studio.biz.util.BizValidator;
import com.hundsun.ares.studio.procdure.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IValidateConstant;
import com.hundsun.ares.studio.core.ResourceTypeMapping;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.util.ResourcesUtil;
import com.hundsun.ares.studio.jres.model.metadata.util.IDRangeUtil;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.BizType;
import com.hundsun.ares.studio.procdure.DefineType;
import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.RelatedInfo;
import com.hundsun.ares.studio.procdure.constants.IProcedureConstant;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.procdure.ProcdurePackage
 * @generated
 */
public class ProcdureValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ProcdureValidator INSTANCE = new ProcdureValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.hundsun.ares.studio.procdure";

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
	public ProcdureValidator() {
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
	  return ProcdurePackage.eINSTANCE;
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
			case ProcdurePackage.PROCEDURE:
				return validateProcedure((Procedure)value, diagnostics, context);
			case ProcdurePackage.RELATED_INFO:
				return validateRelatedInfo((RelatedInfo)value, diagnostics, context);
			case ProcdurePackage.INTERNAL_PARAM:
				return validateInternalParam((InternalParam)value, diagnostics, context);
			case ProcdurePackage.DEFINE_TYPE:
				return validateDefineType((DefineType)value, diagnostics, context);
			case ProcdurePackage.BIZ_TYPE:
				return validateBizType((BizType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcedure(Procedure procedure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(procedure, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(procedure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(procedure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(procedure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(procedure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(procedure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(procedure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(procedure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(procedure, diagnostics, context);
		if (result || diagnostics != null) result &= validateProcedure_objectId(procedure, diagnostics, context);
		return result;
	}

	/**
	 * Validates the objectId constraint of '<em>Procedure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateProcedure_objectId(Procedure procedure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validateObjectID(procedure, diagnostics, context, IProcedureConstant.PROC_ID_RANGE_EXTEND_KEY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRelatedInfo(RelatedInfo relatedInfo, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(relatedInfo, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(relatedInfo, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(relatedInfo, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(relatedInfo, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(relatedInfo, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(relatedInfo, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(relatedInfo, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(relatedInfo, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(relatedInfo, diagnostics, context);
		if (result || diagnostics != null) result &= validateRelatedInfo_id(relatedInfo, diagnostics, context);
		return result;
	}

	/**
	 * Validates the id constraint of '<em>Related Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateRelatedInfo_id(RelatedInfo relatedInfo, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String id = relatedInfo.getId();
		if(StringUtils.isBlank(id)) {
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						String.format("参数名不能为空",id), 
						new Object[]{relatedInfo,
						ProcdurePackage.Literals.RELATED_INFO__ID
				}));
			}
			return false;
		}
		return true;
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
		if (result || diagnostics != null) result &= bizValidator.validateParameter_id(internalParam, diagnostics, context);
		if (result || diagnostics != null) result &= bizValidator.validateParameter_type(internalParam, diagnostics, context);
		return result;
	}

	private boolean validateObjectID(Procedure procedure, DiagnosticChain diagnostics, Map<Object, Object> context,String idExtendKey){
		String objID = procedure.getObjectId();
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
								new Object[]{procedure,
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
							new Object[]{procedure,
							CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID}));
				}
				return false;
			}
			
			IARESProject project =(IARESProject) context.get(IValidateConstant.KEY_RESOUCE_PROJECT);
			String type = ResourceTypeMapping.getInstance().getReferenceType(resource.getType());
			List<ReferenceInfo> referInfos = ReferenceManager.getInstance().getReferenceInfos(project, type, false);
			for(ReferenceInfo info : referInfos){
				Object obj = info.getObject();
				if (obj instanceof Procedure) {
					if (!StringUtils.equals(procedure.getFullyQualifiedName(), ((Procedure) obj).getFullyQualifiedName()) &&
							StringUtils.equals(((Procedure) obj).getObjectId(), procedure.getObjectId())) {
						if(diagnostics != null){
							StringBuffer sb = new StringBuffer();
							sb.append("对象号重复：");
							sb.append(ResourcesUtil.getChineseFileName("/", info.getResource().getModule())+"/"+((Procedure) obj).getChineseName()+"["+procedure.getName()+"]");
							diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									sb.toString(), 
									new Object[]{procedure,
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
	public boolean validateDefineType(DefineType defineType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBizType(BizType bizType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
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

} //ProcdureValidator
