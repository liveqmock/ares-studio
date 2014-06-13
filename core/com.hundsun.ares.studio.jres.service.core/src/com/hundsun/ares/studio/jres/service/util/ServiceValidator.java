/**
 * 源程序名称：${file_name}
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：${project_name}
 * 功能说明：$$desc
 * 相关文档：
 * 作者：${user}
 */
package com.hundsun.ares.studio.jres.service.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;

import com.hundsun.ares.studio.biz.constants.IBizConstants;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IValidateConstant;
import com.hundsun.ares.studio.core.ResourceTypeMapping;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.util.ResourcesUtil;
import com.hundsun.ares.studio.jres.model.metadata.util.IDRangeUtil;
import com.hundsun.ares.studio.jres.service.Service;
import com.hundsun.ares.studio.jres.service.ServicePackage;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.service.ServicePackage
 * @generated
 */
public class ServiceValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ServiceValidator INSTANCE = new ServiceValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.hundsun.ares.studio.jres.service";

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
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return ServicePackage.eINSTANCE;
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
			case ServicePackage.SERVICE:
				return validateService((Service)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateService(Service service, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(service, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(service, diagnostics, context);
		if (result || diagnostics != null) result &= validateService_objectId(service, diagnostics, context);
		return result;
	}

	/**
	 * Validates the objectId constraint of '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateService_objectId(Service service, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String objID = service.getObjectId();
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		if (StringUtils.isNotBlank(objID)) {
			try {
				int value = Integer.parseInt(objID);
				String range = IDRangeUtil.getIDRange(resource,IBizConstants.SRV_ID_RANGE_KEY);
				if(!IDRangeUtil.isInRange(value, range)){
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								String.format("功能号不在%s范围内",range), 
								new Object[]{service,
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
							new Object[]{service,
							CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID}));
				}
				return false;
			}
			
			IARESProject project =(IARESProject) context.get(IValidateConstant.KEY_RESOUCE_PROJECT);
			String type = ResourceTypeMapping.getInstance().getReferenceType(resource.getType());
			List<ReferenceInfo> referInfos = ReferenceManager.getInstance().getReferenceInfos(project, type, false);
			for(ReferenceInfo info : referInfos){
				Object obj = info.getObject();
				if (obj instanceof Service) {
					if (!StringUtils.equals(service.getFullyQualifiedName(), ((Service) obj).getFullyQualifiedName()) &&
							StringUtils.equals(((Service) obj).getObjectId(), service.getObjectId())) {
						if(diagnostics != null){
							StringBuffer sb = new StringBuffer();
							sb.append("对象号重复：");
							sb.append(ResourcesUtil.getChineseFileName("/", info.getResource().getModule())+"/"+((Service) obj).getChineseName()+"["+service.getName()+"]");
							diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									sb.toString(), 
									new Object[]{service,
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

} //ServiceValidator
