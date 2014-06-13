/**
 */
package com.hundsun.ares.studio.core.model.impl;

import com.hundsun.ares.studio.core.model.*;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.dom4j.Document;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModelAttribute;
import com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.ModuleExtensibleModel;
import com.hundsun.ares.studio.core.model.ModuleRevisionHistoryList;
import com.hundsun.ares.studio.core.model.ProjectExtensibleModel;
import com.hundsun.ares.studio.core.model.ProjectRevisionHistoryProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CoreFactoryImpl extends EFactoryImpl implements CoreFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CoreFactory init() {
		try {
			CoreFactory theCoreFactory = (CoreFactory)EPackage.Registry.INSTANCE.getEFactory(CorePackage.eNS_URI);
			if (theCoreFactory != null) {
				return theCoreFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CoreFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreFactoryImpl() {
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
			case CorePackage.JRES_RESOURCE_INFO: return createJRESResourceInfo();
			case CorePackage.BASIC_RESOURCE_INFO: return createBasicResourceInfo();
			case CorePackage.REVISION_HISTORY: return createRevisionHistory();
			case CorePackage.ESTRING_TO_EOBJECT_MAP_ENTRY: return (EObject)createEStringToEObjectMapEntry();
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY: return createExtensibleModelConfigProperty();
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE: return createExtensibleModelAttribute();
			case CorePackage.USER_EXTENSIBLE_PROPERTY: return createUserExtensibleProperty();
			case CorePackage.MODULE_EXTENSIBLE_MODEL: return createModuleExtensibleModel();
			case CorePackage.PROJECT_EXTENSIBLE_MODEL: return createProjectExtensibleModel();
			case CorePackage.PROJECT_REVISION_HISTORY_PROPERTY: return createProjectRevisionHistoryProperty();
			case CorePackage.MODULE_REVISION_HISTORY_LIST: return createModuleRevisionHistoryList();
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
			case CorePackage.DOM4J_DOCUMENT:
				return createDom4jDocumentFromString(eDataType, initialValue);
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
			case CorePackage.DOM4J_DOCUMENT:
				return convertDom4jDocumentToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JRESResourceInfo createJRESResourceInfo() {
		JRESResourceInfoImpl jresResourceInfo = new JRESResourceInfoImpl();
		return jresResourceInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicResourceInfo createBasicResourceInfo() {
		BasicResourceInfoImpl basicResourceInfo = new BasicResourceInfoImpl();
		return basicResourceInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public RevisionHistory createRevisionHistory() {
		RevisionHistoryImpl revisionHistory = new RevisionHistoryImpl();
		// 在新增修订记录时，如果工具已注册，自动将注册用户名填入修订人一栏；如果使用的是试用版本，需用户自己手动输入修订人。
		// TODO 自动添加用户名
		// revisionHistory.setModifiedBy(RegisterUtil.getInstance().getUserName());
		revisionHistory.setModifiedDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
		return revisionHistory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, EObject> createEStringToEObjectMapEntry() {
		EStringToEObjectMapEntryImpl eStringToEObjectMapEntry = new EStringToEObjectMapEntryImpl();
		return eStringToEObjectMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtensibleModelConfigProperty createExtensibleModelConfigProperty() {
		ExtensibleModelConfigPropertyImpl extensibleModelConfigProperty = new ExtensibleModelConfigPropertyImpl();
		return extensibleModelConfigProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtensibleModelAttribute createExtensibleModelAttribute() {
		ExtensibleModelAttributeImpl extensibleModelAttribute = new ExtensibleModelAttributeImpl();
		return extensibleModelAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserExtensibleProperty createUserExtensibleProperty() {
		UserExtensiblePropertyImpl userExtensibleProperty = new UserExtensiblePropertyImpl();
		return userExtensibleProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModuleExtensibleModel createModuleExtensibleModel() {
		ModuleExtensibleModelImpl moduleExtensibleModel = new ModuleExtensibleModelImpl();
		return moduleExtensibleModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProjectExtensibleModel createProjectExtensibleModel() {
		ProjectExtensibleModelImpl projectExtensibleModel = new ProjectExtensibleModelImpl();
		return projectExtensibleModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProjectRevisionHistoryProperty createProjectRevisionHistoryProperty() {
		ProjectRevisionHistoryPropertyImpl projectRevisionHistoryProperty = new ProjectRevisionHistoryPropertyImpl();
		return projectRevisionHistoryProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModuleRevisionHistoryList createModuleRevisionHistoryList() {
		ModuleRevisionHistoryListImpl moduleRevisionHistoryList = new ModuleRevisionHistoryListImpl();
		return moduleRevisionHistoryList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Document createDom4jDocumentFromString(EDataType eDataType, String initialValue) {
		return (Document)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDom4jDocumentToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorePackage getCorePackage() {
		return (CorePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CorePackage getPackage() {
		return CorePackage.eINSTANCE;
	}

} //CoreFactoryImpl
