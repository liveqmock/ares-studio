/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.HisTableColumn;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.util.StockUtils;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Revision History Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.RevisionHistoryPropertyImpl#getAction <em>Action</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.RevisionHistoryPropertyImpl#getActionType <em>Action Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.RevisionHistoryPropertyImpl#getActionDescription <em>Action Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RevisionHistoryPropertyImpl extends HistoryPropertyImpl implements RevisionHistoryProperty {
	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected Modification action;

	/**
	 * The default value of the '{@link #getActionType() <em>Action Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionType()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTION_TYPE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getActionDescription() <em>Action Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTION_DESCRIPTION_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RevisionHistoryPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.REVISION_HISTORY_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modification getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAction(Modification newAction, NotificationChain msgs) {
		Modification oldAction = action;
		action = newAction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ChousePackage.REVISION_HISTORY_PROPERTY__ACTION, oldAction, newAction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setAction(Modification newAction) {
		if (newAction != action) {
			NotificationChain msgs = null;
			if (action != null)
				msgs = ((InternalEObject)action).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ChousePackage.REVISION_HISTORY_PROPERTY__ACTION, null, msgs);
			if (newAction != null)
				msgs = ((InternalEObject)newAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ChousePackage.REVISION_HISTORY_PROPERTY__ACTION, null, msgs);
			msgs = basicSetAction(newAction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.REVISION_HISTORY_PROPERTY__ACTION, newAction, newAction));
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getActionType() {
		if (getAction() == null) {
			return null;
		} else {
			return getAction().eClass().getName();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setActionType(String newActionType) {}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionDescription(String newActionDescription) {
		// TODO: implement this method to set the 'Action Description' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getActionDescription() {
		return StockUtils.getModificationDescription(null, getAction());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Reference> getReferences() {
		EList<Reference> references = new BasicEList<Reference>();
		if (getAction() instanceof AddTableColumnModification) {
			List<HisTableColumn> columns = ((AddTableColumnModification)getAction()).getColumns();
			for (HisTableColumn col : columns) {
				Reference stdName = new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdField, 
						col, 
						MetadataPackage.Literals.NAMED_ELEMENT__NAME);
				references.add(stdName);
			}
		}
		if (getAction() instanceof AddIndexModification) {
			List<TableIndex> indexs = ((AddIndexModification)getAction()).getIndexs();
			for (TableIndex index : indexs) {
				EList<TableIndexColumn>  indexCols = index.getColumns();
				for (TableIndexColumn ic : indexCols){
					Reference stdName = new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdField, 
							ic, 
							DatabasePackage.Literals.TABLE_INDEX__NAME);
					references.add(stdName);
				}
			}
		}
		return references;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION:
				return basicSetAction(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION:
				return getAction();
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION_TYPE:
				return getActionType();
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION_DESCRIPTION:
				return getActionDescription();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION:
				setAction((Modification)newValue);
				return;
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION_TYPE:
				setActionType((String)newValue);
				return;
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION_DESCRIPTION:
				setActionDescription((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION:
				setAction((Modification)null);
				return;
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION_TYPE:
				setActionType(ACTION_TYPE_EDEFAULT);
				return;
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION_DESCRIPTION:
				setActionDescription(ACTION_DESCRIPTION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION:
				return action != null;
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION_TYPE:
				return ACTION_TYPE_EDEFAULT == null ? getActionType() != null : !ACTION_TYPE_EDEFAULT.equals(getActionType());
			case ChousePackage.REVISION_HISTORY_PROPERTY__ACTION_DESCRIPTION:
				return ACTION_DESCRIPTION_EDEFAULT == null ? getActionDescription() != null : !ACTION_DESCRIPTION_EDEFAULT.equals(getActionDescription());
		}
		return super.eIsSet(featureID);
	}

} //RevisionHistoryPropertyImpl
