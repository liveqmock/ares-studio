/**
 * 源程序名称：DeMetadataItemImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage;
import com.hundsun.ares.studio.jres.model.metadata.util.CircularReferenceException;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>De Metadata Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DeMetadataItemImpl<T extends MetadataItem> extends EObjectImpl implements DeMetadataItem<T> {
	
	private IARESResource resource;
	private T proxyItem;
	private Pair<T, IARESResource> resolvedItem;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeMetadataItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DeMetadataItemImpl(T proxyItem, IARESResource resource) {
		super();
		this.proxyItem = proxyItem;
		this.resource = resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DecryptPackage.Literals.DE_METADATA_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IARESResource getResource() {
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T getProxyItem() {
		return proxyItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		if (!checkStatus()) return StringUtils.EMPTY;
		return getProxyItem().getName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChineseName() {
		if (!checkStatus()) return StringUtils.EMPTY;
		return getProxyItem().getChineseName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		if (!checkStatus()) return StringUtils.EMPTY;
		return getProxyItem().getDescription();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataMapValue(final String key) {
		if (!checkStatus()) return StringUtils.EMPTY;
		return getResolvedItem().first.getData().get(key);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getData2MapValue(final String key) {
		if (!checkStatus()) return null;
		return getResolvedItem().first.getData2().get(key);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Pair<T, IARESResource> getResolvedItem() {
		if (!checkStatus()) return null;
		// 不实时更新
		if (resolvedItem == null) {
			if ( MetadataUtil.isUseRefFeature(resource) ) {
				try {
					resolvedItem = MetadataUtil.resolve(getProxyItem(), getResource());
				} catch (CircularReferenceException e) {
				}
			}

			if (resolvedItem == null) {
				// 如果没有找到引用对象，则使用自己
				resolvedItem = new Pair<T, IARESResource>(getProxyItem(), getResource());
			}
		}
		return resolvedItem;
	}


	protected boolean checkStatus() {
		return getProxyItem() != null && getResource() != null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getDataMap() {
		if (!checkStatus()) return ECollections.emptyEMap();
		return getResolvedItem().first.getData();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, EObject> getData2Map() {
		if (!checkStatus()) return ECollections.emptyEMap();
		return getResolvedItem().first.getData2();
	}

} //DeMetadataItemImpl
