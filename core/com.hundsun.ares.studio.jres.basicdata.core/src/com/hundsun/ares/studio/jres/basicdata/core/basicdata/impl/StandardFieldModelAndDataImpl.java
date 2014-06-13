/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceImpl;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BaiscDataReferenceImpl;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Field Model And Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldModelAndDataImpl#getModel <em>Model</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldModelAndDataImpl#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StandardFieldModelAndDataImpl extends EObjectImpl implements StandardFieldModelAndData {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardFieldModelAndDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicdataPackage.Literals.STANDARD_FIELD_MODEL_AND_DATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardFieldPackageDefine getModel() {
		return (StandardFieldPackageDefine)eGet(BasicdataPackage.Literals.STANDARD_FIELD_MODEL_AND_DATA__MODEL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(StandardFieldPackageDefine newModel) {
		eSet(BasicdataPackage.Literals.STANDARD_FIELD_MODEL_AND_DATA__MODEL, newModel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicDataBaseModel getData() {
		return (BasicDataBaseModel)eGet(BasicdataPackage.Literals.STANDARD_FIELD_MODEL_AND_DATA__DATA, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setData(BasicDataBaseModel newData) {
		eSet(BasicdataPackage.Literals.STANDARD_FIELD_MODEL_AND_DATA__DATA, newData);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Reference> getReferences() {
		EList<Reference> infoList=new BasicEList<Reference>();
		//模型部分
		for(StandardFieldColumn item:getModel().getFields()){
			TextAttributeReferenceImpl refer = new TextAttributeReferenceImpl(IMetadataRefType.StdField, 
					item, BasicdataPackage.Literals.STANDARD_FIELD_COLUMN__STANDARD_FIELD);
			infoList.add(refer);
		}
		
		//数据部分
		EPackage ePackage = this.getData().eClass().getEPackage();
		if(BasicDataEpackageUtil.contains(ePackage, IBasicDataEpacakgeConstant.MasterItem)){
			EClass master = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
			EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(master);
			addReferenceInfo(ePackage, infoList, attrArray);
		}
		return infoList;
	}
	
	private void addReferenceInfo(EPackage ePackage,List<Reference> infoList,EAttribute[] attrArray){
		for(EAttribute attr:attrArray){
			BaiscDataReferenceImpl info = new BaiscDataReferenceImpl(attr);
			infoList.add(info);
		}
	}

} //StandardFieldModelAndDataImpl
