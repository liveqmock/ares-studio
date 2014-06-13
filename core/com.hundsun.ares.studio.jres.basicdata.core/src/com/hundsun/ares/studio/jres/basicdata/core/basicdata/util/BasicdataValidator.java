/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.util;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataItem;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine;
import com.hundsun.ares.studio.jres.basicdata.logic.util.EPackageUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc
 * -->
 * 
 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage
 * @generated
 */
public class BasicdataValidator extends EObjectValidator {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final BasicdataValidator INSTANCE = new BasicdataValidator();

	/**
	 * A constant for the
	 * {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of
	 * diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes}
	 * from this package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.hundsun.ares.studio.jres.basicdata.core.basicdata";

	/**
	 * A constant with a fixed name that can be used as the base value for
	 * additional hand written constants. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for
	 * additional hand written constants in a derived class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public BasicdataValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
		return BasicdataPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the
	 * model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
		case BasicdataPackage.EPACAKGE_DEFINE_LIST:
			return validateEpacakgeDefineList((EpacakgeDefineList) value,
					diagnostics, context);
		case BasicdataPackage.PACKAGE_DEFINE:
			return validatePackageDefine((PackageDefine) value, diagnostics,
					context);
		case BasicdataPackage.SINGLE_TABLE:
			return validateSingleTable((SingleTable) value, diagnostics,
					context);
		case BasicdataPackage.MASTER_SLAVE_TABLE:
			return validateMasterSlaveTable((MasterSlaveTable) value,
					diagnostics, context);
		case BasicdataPackage.MASTER_SLAVE_LINK_TABLE:
			return validateMasterSlaveLinkTable((MasterSlaveLinkTable) value,
					diagnostics, context);
		case BasicdataPackage.BASIC_DATA_BASE_MODEL:
			return validateBasicDataBaseModel((BasicDataBaseModel) value,
					diagnostics, context);
		case BasicdataPackage.STANDARD_FIELD_PACKAGE_DEFINE:
			return validateStandardFieldPackageDefine(
					(StandardFieldPackageDefine) value, diagnostics, context);
		case BasicdataPackage.STANDARD_FIELD_MODEL_AND_DATA:
			return validateStandardFieldModelAndData(
					(StandardFieldModelAndData) value, diagnostics, context);
		case BasicdataPackage.STANDARD_FIELD_COLUMN:
			return validateStandardFieldColumn((StandardFieldColumn) value,
					diagnostics, context);
		case BasicdataPackage.BASIC_DATA_FIELD:
			return validateBasicDataField((BasicDataField) value, diagnostics,
					context);
		case BasicdataPackage.BASICDATA_ITEM:
			return validateBasicdataItem((BasicdataItem) value, diagnostics,
					context);
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEpacakgeDefineList(
			EpacakgeDefineList epacakgeDefineList, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(epacakgeDefineList, diagnostics,
				context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(epacakgeDefineList,
				diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(epacakgeDefineList,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(epacakgeDefineList,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(
					epacakgeDefineList, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(epacakgeDefineList,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(epacakgeDefineList, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(epacakgeDefineList, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(epacakgeDefineList,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validateEpacakgeDefineList_url(epacakgeDefineList,
					diagnostics, context);
		return result;
	}

	/**
	 * Validates the url constraint of '<em>Epacakge Define List</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEpacakgeDefineList_url(
			EpacakgeDefineList epacakgeDefineList, DiagnosticChain diagnostics,
			Map<Object, Object> context) {

		boolean isOK = true;
		for (PackageDefine packageDefine : epacakgeDefineList.getItems()) {
			String url = packageDefine.getUrl();
			for (PackageDefine packageDefine2 : epacakgeDefineList.getItems()) {
				String url2 = packageDefine2.getUrl();
				if (StringUtils.equals(url, url2)
						&& packageDefine != packageDefine2) {
					diagnostics
							.add(new BasicDiagnostic(
									Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									String.format("url%1$s重复", url),
									new Object[] {
											packageDefine,
											BasicdataPackage.Literals.PACKAGE_DEFINE__URL }));
					isOK = false;
				}

			}
		}

		return isOK;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePackageDefine(PackageDefine packageDefine,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(packageDefine, diagnostics,
				context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSingleTable(SingleTable singleTable,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(singleTable, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(singleTable,
				diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(singleTable, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(singleTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(singleTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(singleTable, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(singleTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(singleTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(singleTable, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validateSingleTable_master(singleTable, diagnostics,
					context);
		return result;
	}

	/**
	 * Validates the master constraint of '<em>Single Table</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validateSingleTable_master(SingleTable singleTable,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		String master = singleTable.getMaster();
		IARESResource resource = (IARESResource) context
				.get(IValidateConstant.KEY_RESOUCE);
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		for (String type : new String[] { singleTable.getType() }) {
			infoList.addAll(ReferenceManager.getInstance().getReferenceInfos(
					resource.getARESProject(), type, true));
		}
		boolean find = false;
		for (ReferenceInfo referenceInfo : infoList) {
			String name = referenceInfo.getRefName();
			if (name.equals(master)) {
				find = true;
				break;
			}
		}
		if (!find && diagnostics != null) {
			diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
					DIAGNOSTIC_SOURCE, 0, String.format("主表关联的%1$s不存在",
							EPackageUtil.getEpackageFactoryItemName(singleTable
									.getType())), new Object[] { singleTable,
							BasicdataPackage.Literals.SINGLE_TABLE__MASTER }));
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMasterSlaveTable(MasterSlaveTable masterSlaveTable,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(masterSlaveTable, diagnostics,
				context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(masterSlaveTable,
				diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(masterSlaveTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(masterSlaveTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(
					masterSlaveTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(masterSlaveTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(masterSlaveTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(masterSlaveTable, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(masterSlaveTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validateMasterSlaveTable_master(masterSlaveTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validateMasterSlaveTable_slave(masterSlaveTable,
					diagnostics, context);
		return result;
	}

	/**
	 * Validates the master constraint of '<em>Master Slave Table</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validateMasterSlaveTable_master(
			MasterSlaveTable masterSlaveTable, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		String master = masterSlaveTable.getMaster();
		IARESResource resource = (IARESResource) context
				.get(IValidateConstant.KEY_RESOUCE);
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		for (String type : new String[] { masterSlaveTable.getType() }) {
			infoList.addAll(ReferenceManager.getInstance().getReferenceInfos(
					resource.getARESProject(), type, true));
		}
		boolean find = false;
		for (ReferenceInfo referenceInfo : infoList) {
			String name = (String) referenceInfo.getRefName();
			if (name.equals(master)) {
				find = true;
				break;
			}
		}
		if (!find && diagnostics != null) {
			diagnostics
					.add(new BasicDiagnostic(
							Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							String.format(
									"主表关联的%1$s不存在",
									EPackageUtil
											.getEpackageFactoryItemName(masterSlaveTable
													.getType())),
							new Object[] {
									masterSlaveTable,
									BasicdataPackage.Literals.MASTER_SLAVE_TABLE__MASTER }));
			return false;
		}
		return true;
	}

	/**
	 * Validates the slave constraint of '<em>Master Slave Table</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validateMasterSlaveTable_slave(
			MasterSlaveTable masterSlaveTable, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		String master = masterSlaveTable.getSlave();
		IARESResource resource = (IARESResource) context
				.get(IValidateConstant.KEY_RESOUCE);
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		for (String type : new String[] { masterSlaveTable.getType() }) {
			infoList.addAll(ReferenceManager.getInstance().getReferenceInfos(
					resource.getARESProject(), type, true));
		}
		boolean find = false;
		for (ReferenceInfo referenceInfo : infoList) {
			String name = referenceInfo.getRefName();
			if (name.equals(master)) {
				find = true;
				break;
			}
		}
		if (!find && diagnostics != null) {
			diagnostics
					.add(new BasicDiagnostic(
							Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							String.format(
									"主表关联的%1$s不存在",
									EPackageUtil
											.getEpackageFactoryItemName(masterSlaveTable
													.getType())),
							new Object[] {
									masterSlaveTable,
									BasicdataPackage.Literals.MASTER_SLAVE_TABLE__SLAVE }));
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMasterSlaveLinkTable(
			MasterSlaveLinkTable masterSlaveLinkTable,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(masterSlaveLinkTable, diagnostics,
				context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(
				masterSlaveLinkTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(masterSlaveLinkTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(masterSlaveLinkTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(
					masterSlaveLinkTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(masterSlaveLinkTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(masterSlaveLinkTable, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(masterSlaveLinkTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(masterSlaveLinkTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validateMasterSlaveLinkTable_master(masterSlaveLinkTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validateMasterSlaveLinkTable_slave(masterSlaveLinkTable,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validateMasterSlaveLinkTable_link(masterSlaveLinkTable,
					diagnostics, context);
		return result;
	}

	/**
	 * Validates the master constraint of '<em>Master Slave Link Table</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validateMasterSlaveLinkTable_master(
			MasterSlaveLinkTable masterSlaveLinkTable,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		String master = masterSlaveLinkTable.getMaster();
		IARESResource resource = (IARESResource) context
				.get(IValidateConstant.KEY_RESOUCE);
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		for (String type : new String[] { masterSlaveLinkTable.getType() }) {
			infoList.addAll(ReferenceManager.getInstance().getReferenceInfos(
					resource.getARESProject(), type, true));
		}
		boolean find = false;
		for (ReferenceInfo referenceInfo : infoList) {
			String name = referenceInfo.getRefName();
			if (name.equals(master)) {
				find = true;
				break;
			}
		}
		if (!find && diagnostics != null) {
			diagnostics
					.add(new BasicDiagnostic(
							Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							String.format(
									"主表关联的%1$s不存在",
									EPackageUtil
											.getEpackageFactoryItemName(masterSlaveLinkTable
													.getType())),
							new Object[] {
									masterSlaveLinkTable,
									BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__MASTER }));
			return false;
		}
		return true;
	}

	/**
	 * Validates the slave constraint of '<em>Master Slave Link Table</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validateMasterSlaveLinkTable_slave(
			MasterSlaveLinkTable masterSlaveLinkTable,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		String slave = masterSlaveLinkTable.getSlave();
		IARESResource resource = (IARESResource) context
				.get(IValidateConstant.KEY_RESOUCE);
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		for (String type : new String[] { masterSlaveLinkTable.getType() }) {
			infoList.addAll(ReferenceManager.getInstance().getReferenceInfos(
					resource.getARESProject(), type, true));
		}
		boolean find = false;
		for (ReferenceInfo referenceInfo : infoList) {
			String name = referenceInfo.getRefName();
			if (name.equals(slave)) {
				find = true;
				break;
			}
		}
		if (!find && diagnostics != null) {
			diagnostics
					.add(new BasicDiagnostic(
							Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							String.format(
									"从表关联的%1$s不存在",
									EPackageUtil
											.getEpackageFactoryItemName(masterSlaveLinkTable
													.getType())),
							new Object[] {
									masterSlaveLinkTable,
									BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__SLAVE }));
			return false;
		}
		return true;
	}

	/**
	 * Validates the link constraint of '<em>Master Slave Link Table</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validateMasterSlaveLinkTable_link(
			MasterSlaveLinkTable masterSlaveLinkTable,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		String link = masterSlaveLinkTable.getLink();
		IARESResource resource = (IARESResource) context
				.get(IValidateConstant.KEY_RESOUCE);
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		for (String type : new String[] { masterSlaveLinkTable.getType() }) {
			infoList.addAll(ReferenceManager.getInstance().getReferenceInfos(
					resource.getARESProject(), type, true));
		}
		boolean find = false;
		for (ReferenceInfo referenceInfo : infoList) {
			String name = referenceInfo.getRefName();
			if (name.equals(link)) {
				find = true;
				break;
			}
		}
		if (!find && diagnostics != null) {
			diagnostics
					.add(new BasicDiagnostic(
							Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							String.format(
									"信息表关联的%1$s不存在",
									EPackageUtil
											.getEpackageFactoryItemName(masterSlaveLinkTable
													.getType())),
							new Object[] {
									masterSlaveLinkTable,
									BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__LINK }));
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateBasicDataBaseModel(
			BasicDataBaseModel basicDataBaseModel, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(basicDataBaseModel, diagnostics,
				context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateStandardFieldPackageDefine(
			StandardFieldPackageDefine standardFieldPackageDefine,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(standardFieldPackageDefine,
				diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateStandardFieldModelAndData(
			StandardFieldModelAndData standardFieldModelAndData,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(standardFieldModelAndData,
				diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateStandardFieldColumn(
			StandardFieldColumn standardFieldColumn,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(standardFieldColumn, diagnostics,
				context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(
				standardFieldColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(standardFieldColumn,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(standardFieldColumn,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(
					standardFieldColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(standardFieldColumn,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(standardFieldColumn, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(standardFieldColumn, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(standardFieldColumn,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validateStandardFieldColumn_standardField(
					standardFieldColumn, diagnostics, context);
		return result;
	}

	/**
	 * Validates the standardField constraint of '<em>Standard Field Column</em>
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validateStandardFieldColumn_standardField(
			StandardFieldColumn standardFieldColumn,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		IARESProject project = (IARESProject) context
				.get(IValidateConstant.KEY_RESOUCE_PROJECT);
		ReferenceInfo info = ReferenceManager.getInstance()
				.getFirstReferenceInfo(project, IMetadataRefType.StdField,
						standardFieldColumn.getStandardField(), true);
		if (info == null) {
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"列对应的标准字段不存在：["
										+ standardFieldColumn
												.getStandardField() + "]",
								new Object[] {
										standardFieldColumn,
										BasicdataPackage.Literals.STANDARD_FIELD_COLUMN__STANDARD_FIELD }));
				return false;
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateBasicDataField(BasicDataField basicDataField,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(basicDataField, diagnostics,
				context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateBasicdataItem(BasicdataItem basicdataItem,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(basicdataItem, diagnostics,
				context);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this
	 * validator's diagnostics. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to
		// this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} // BasicdataValidator
