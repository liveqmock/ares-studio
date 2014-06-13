/**
 * 源程序名称：${file_name}
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：${project_name}
 * 功能说明：$$desc
 * 相关文档：
 * 作者：${user}
 */
package com.hundsun.ares.studio.jres.service.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.hundsun.ares.studio.biz.BizFactory;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceImpl;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.service.Service;
import com.hundsun.ares.studio.jres.service.ServicePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.service.impl.ServiceImpl#getInterface <em>Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceImpl extends JRESResourceInfoImpl implements Service {
	/**
	 * The cached value of the '{@link #getInterface() <em>Interface</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected BizInterface interface_;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ServiceImpl() {
		super();
		interface_ = BizFactory.eINSTANCE.createBizInterface();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ServicePackage.Literals.SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BizInterface getInterface() {
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInterface(BizInterface newInterface, NotificationChain msgs) {
		BizInterface oldInterface = interface_;
		interface_ = newInterface;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ServicePackage.SERVICE__INTERFACE, oldInterface, newInterface);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(BizInterface newInterface) {
		if (newInterface != interface_) {
			NotificationChain msgs = null;
			if (interface_ != null)
				msgs = ((InternalEObject)interface_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServicePackage.SERVICE__INTERFACE, null, msgs);
			if (newInterface != null)
				msgs = ((InternalEObject)newInterface).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServicePackage.SERVICE__INTERFACE, null, msgs);
			msgs = basicSetInterface(newInterface, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicePackage.SERVICE__INTERFACE, newInterface, newInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ServicePackage.SERVICE__INTERFACE:
				return basicSetInterface(null, msgs);
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
			case ServicePackage.SERVICE__INTERFACE:
				return getInterface();
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
			case ServicePackage.SERVICE__INTERFACE:
				setInterface((BizInterface)newValue);
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
			case ServicePackage.SERVICE__INTERFACE:
				setInterface((BizInterface)null);
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
			case ServicePackage.SERVICE__INTERFACE:
				return interface_ != null;
		}
		return super.eIsSet(featureID);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		// 注意：
		// 因为此处需要判断是否使用对象标准字段，所以需要IARESProject对象，但直接引用会造成模型和IARES接口绑定
		// 所以此处使用Adapter模式实现，下面的逻辑不再有用； 实际逻辑参考SerivceReferenceProvider这个类
		// 后续的修改也要在SerivceReferenceProvider处修改
		BasicEList<Reference> references = new BasicEList<Reference>();
		BasicEList<Parameter> parametesrs = new BasicEList<Parameter>();
		parametesrs.addAll(getInterface().getInputParameters());//添加输入参数
		parametesrs.addAll(getInterface().getOutputParameters());//添加输出参数
		
		for(Parameter parametesr:parametesrs){
			Reference ref = null;
			// 根据参数类型的不同，引用的类型也不一样
			switch (parametesr.getParamType()) {
				case STD_FIELD:
					if (!StringUtils.isEmpty(parametesr.getId())) 
						ref = new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdField, parametesr, BizPackage.Literals.PARAMETER__ID);
					break;
				case OBJECT:
					//
					if (!StringUtils.isEmpty(parametesr.getType()))
						ref = new TextAttributeReferenceImpl(IBizRefType.Object, parametesr, BizPackage.Literals.PARAMETER__TYPE);
					break;
				default:
					break;
			}
			
			if (ref != null)
				references.add(ref);
		}
		return references;
	}
} //ServiceImpl
