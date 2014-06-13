/**
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.IJSONData;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.*;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.NamedElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage
 * @generated
 */
public class BasicdataSwitch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BasicdataPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicdataSwitch() {
		if (modelPackage == null) {
			modelPackage = BasicdataPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T1 doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T1 doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case BasicdataPackage.EPACAKGE_DEFINE_LIST: {
				EpacakgeDefineList epacakgeDefineList = (EpacakgeDefineList)theEObject;
				T1 result = caseEpacakgeDefineList(epacakgeDefineList);
				if (result == null) result = caseJRESResourceInfo(epacakgeDefineList);
				if (result == null) result = caseExtensibleModel(epacakgeDefineList);
				if (result == null) result = caseBasicResourceInfo(epacakgeDefineList);
				if (result == null) result = caseIReferenceProvider(epacakgeDefineList);
				if (result == null) result = caseIJSONData(epacakgeDefineList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BasicdataPackage.PACKAGE_DEFINE: {
				PackageDefine packageDefine = (PackageDefine)theEObject;
				T1 result = casePackageDefine(packageDefine);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BasicdataPackage.SINGLE_TABLE: {
				SingleTable singleTable = (SingleTable)theEObject;
				T1 result = caseSingleTable(singleTable);
				if (result == null) result = casePackageDefine(singleTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BasicdataPackage.MASTER_SLAVE_TABLE: {
				MasterSlaveTable masterSlaveTable = (MasterSlaveTable)theEObject;
				T1 result = caseMasterSlaveTable(masterSlaveTable);
				if (result == null) result = casePackageDefine(masterSlaveTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BasicdataPackage.MASTER_SLAVE_LINK_TABLE: {
				MasterSlaveLinkTable masterSlaveLinkTable = (MasterSlaveLinkTable)theEObject;
				T1 result = caseMasterSlaveLinkTable(masterSlaveLinkTable);
				if (result == null) result = casePackageDefine(masterSlaveLinkTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BasicdataPackage.BASIC_DATA_BASE_MODEL: {
				BasicDataBaseModel basicDataBaseModel = (BasicDataBaseModel)theEObject;
				T1 result = caseBasicDataBaseModel(basicDataBaseModel);
				if (result == null) result = caseMetadataResourceData(basicDataBaseModel);
				if (result == null) result = caseJRESResourceInfo(basicDataBaseModel);
				if (result == null) result = caseExtensibleModel(basicDataBaseModel);
				if (result == null) result = caseBasicResourceInfo(basicDataBaseModel);
				if (result == null) result = caseIReferenceProvider(basicDataBaseModel);
				if (result == null) result = caseIJSONData(basicDataBaseModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BasicdataPackage.STANDARD_FIELD_PACKAGE_DEFINE: {
				StandardFieldPackageDefine standardFieldPackageDefine = (StandardFieldPackageDefine)theEObject;
				T1 result = caseStandardFieldPackageDefine(standardFieldPackageDefine);
				if (result == null) result = casePackageDefine(standardFieldPackageDefine);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BasicdataPackage.STANDARD_FIELD_MODEL_AND_DATA: {
				StandardFieldModelAndData standardFieldModelAndData = (StandardFieldModelAndData)theEObject;
				T1 result = caseStandardFieldModelAndData(standardFieldModelAndData);
				if (result == null) result = caseIReferenceProvider(standardFieldModelAndData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BasicdataPackage.STANDARD_FIELD_COLUMN: {
				StandardFieldColumn standardFieldColumn = (StandardFieldColumn)theEObject;
				T1 result = caseStandardFieldColumn(standardFieldColumn);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BasicdataPackage.BASIC_DATA_FIELD: {
				BasicDataField basicDataField = (BasicDataField)theEObject;
				T1 result = caseBasicDataField(basicDataField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BasicdataPackage.BASICDATA_ITEM: {
				BasicdataItem basicdataItem = (BasicdataItem)theEObject;
				T1 result = caseBasicdataItem(basicdataItem);
				if (result == null) result = caseMetadataItem(basicdataItem);
				if (result == null) result = caseNamedElement(basicdataItem);
				if (result == null) result = caseExtensibleModel(basicdataItem);
				if (result == null) result = caseIJSONData(basicdataItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Epacakge Define List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Epacakge Define List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEpacakgeDefineList(EpacakgeDefineList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Define</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Define</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePackageDefine(PackageDefine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSingleTable(SingleTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Master Slave Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Master Slave Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMasterSlaveTable(MasterSlaveTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Master Slave Link Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Master Slave Link Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMasterSlaveLinkTable(MasterSlaveLinkTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Basic Data Base Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Basic Data Base Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBasicDataBaseModel(BasicDataBaseModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Standard Field Package Define</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Standard Field Package Define</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStandardFieldPackageDefine(StandardFieldPackageDefine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Standard Field Model And Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Standard Field Model And Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStandardFieldModelAndData(StandardFieldModelAndData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Standard Field Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Standard Field Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStandardFieldColumn(StandardFieldColumn object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Basic Data Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Basic Data Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBasicDataField(BasicDataField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBasicdataItem(BasicdataItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IReference Provider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IReference Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIReferenceProvider(IReferenceProvider object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IJSON Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IJSON Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIJSONData(IJSONData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extensible Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extensible Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseExtensibleModel(ExtensibleModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Basic Resource Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Basic Resource Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBasicResourceInfo(BasicResourceInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>JRES Resource Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>JRES Resource Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseJRESResourceInfo(JRESResourceInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends MetadataItem> T1 caseMetadataResourceData(MetadataResourceData<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMetadataItem(MetadataItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T1 defaultCase(EObject object) {
		return null;
	}

} //BasicdataSwitch
