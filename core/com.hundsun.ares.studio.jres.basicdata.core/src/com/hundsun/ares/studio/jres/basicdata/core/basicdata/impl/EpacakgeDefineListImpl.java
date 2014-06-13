/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl;

import com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceImpl;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Epacakge Define List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.EpacakgeDefineListImpl#getItems <em>Items</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EpacakgeDefineListImpl extends JRESResourceInfoImpl implements EpacakgeDefineList {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EpacakgeDefineListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicdataPackage.Literals.EPACAKGE_DEFINE_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 9;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<PackageDefine> getItems() {
		return (EList<PackageDefine>)eGet(BasicdataPackage.Literals.EPACAKGE_DEFINE_LIST__ITEMS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Reference> getReferences() {
		BasicEList<Reference> references = new BasicEList<Reference>();
		// 收集所有包含内容的引用
		for (TreeIterator<EObject> iterator = eAllContents(); iterator.hasNext(); ) {
			EObject obj = iterator.next();
			if(obj instanceof  SingleTable){
				SingleTable table = (SingleTable)obj;
				references.add(new TextAttributeReferenceImpl(table.getType(), table, BasicdataPackage.Literals.SINGLE_TABLE__MASTER));
			}
			
			if(obj instanceof  MasterSlaveTable){
				MasterSlaveTable table = (MasterSlaveTable)obj;
				references.add(new TextAttributeReferenceImpl(table.getType(), table, BasicdataPackage.Literals.MASTER_SLAVE_TABLE__MASTER));
				references.add(new TextAttributeReferenceImpl(table.getType(), table, BasicdataPackage.Literals.MASTER_SLAVE_TABLE__SLAVE));
			}
			
			if(obj instanceof  MasterSlaveLinkTable){
				MasterSlaveLinkTable table = (MasterSlaveLinkTable)obj;
				references.add(new TextAttributeReferenceImpl(table.getType(), table, BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__MASTER));
				references.add(new TextAttributeReferenceImpl(table.getType(), table, BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__SLAVE));
				references.add(new TextAttributeReferenceImpl(table.getType(), table, BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__LINK));
			}
		}
		return references;
	}

} //EpacakgeDefineListImpl
