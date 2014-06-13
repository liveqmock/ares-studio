/**
 */
package com.hundsun.ares.studio.biz.impl;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.StandardObjField;
import com.hundsun.ares.studio.biz.StandardObjFieldList;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceImpl;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.impl.MetadataResourceDataImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Obj Field List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class StandardObjFieldListImpl extends MetadataResourceDataImpl<StandardObjField> implements StandardObjFieldList {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public StandardObjFieldListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BizPackage.Literals.STANDARD_OBJ_FIELD_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<StandardObjField> getItems() {
		if (items == null) {
			items = new EObjectContainmentWithInverseEList<StandardObjField>(StandardObjField.class, this, BizPackage.STANDARD_OBJ_FIELD_LIST__ITEMS, MetadataPackage.METADATA_ITEM__PARENT) { private static final long serialVersionUID = 1L; @Override public Class<?> getInverseFeatureClass() { return MetadataItem.class; } };
		}
		return items;
	}

	@Override
	public EList<Reference> getReferences() {
		BasicEList<Reference> references = new BasicEList<Reference>();
		
		for(StandardObjField f : items){
			Reference ref = new TextAttributeReferenceImpl(IBizRefType.Object, f, BizPackage.Literals.STANDARD_OBJ_FIELD__TYPE);
			references.add(ref);
		}
		return references;
	}

} //StandardObjFieldListImpl
