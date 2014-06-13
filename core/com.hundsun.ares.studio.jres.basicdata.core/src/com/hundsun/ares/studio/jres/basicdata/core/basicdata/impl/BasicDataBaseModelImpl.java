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

import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataItem;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BaiscDataReferenceImpl;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.impl.MetadataResourceDataImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Basic Data Base Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataBaseModelImpl#getExtend <em>Extend</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataBaseModelImpl#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BasicDataBaseModelImpl extends MetadataResourceDataImpl<BasicdataItem> implements BasicDataBaseModel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicDataBaseModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicdataPackage.Literals.BASIC_DATA_BASE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 12;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExtend() {
		return (String)eGet(BasicdataPackage.Literals.BASIC_DATA_BASE_MODEL__EXTEND, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtend(String newExtend) {
		eSet(BasicdataPackage.Literals.BASIC_DATA_BASE_MODEL__EXTEND, newExtend);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFile() {
		return (String)eGet(BasicdataPackage.Literals.BASIC_DATA_BASE_MODEL__FILE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFile(String newFile) {
		eSet(BasicdataPackage.Literals.BASIC_DATA_BASE_MODEL__FILE, newFile);
	}

	@Override
	public EList<Reference> getReferences() {
		EList<Reference> infoList=new BasicEList<Reference>();
		EPackage ePackage = this.eClass().getEPackage();
		
		if(BasicDataEpackageUtil.contains(ePackage, IBasicDataEpacakgeConstant.MasterItem)){
			EClass master = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
			EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(master);
			addReferenceInfo(ePackage, infoList, attrArray);
		}
		
		if(BasicDataEpackageUtil.contains(ePackage, IBasicDataEpacakgeConstant.SlaveItem)){
			EClass master = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.SlaveItem);
			EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(master);
			addReferenceInfo(ePackage, infoList, attrArray);
		}
		
		if(BasicDataEpackageUtil.contains(ePackage, IBasicDataEpacakgeConstant.InfoItem)){
			EClass master = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.InfoItem);
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

} //BasicDataBaseModelImpl
