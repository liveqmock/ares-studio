/**
 * 源程序名称：BasicMetadataService.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.metadata.internal.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.IResStatisticProvider;
import com.hundsun.ares.studio.core.context.JRESContextManager;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IDictionaryType;
import com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataItem;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardDataType;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue;
import com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author gongyf
 *
 */
public class BasicMetadataService implements IMetadataService {

	final private IARESProject project;
	
	private List<ITypeDefaultValue> defValueList = null;
	private List<IStandardDataType> stdTypeList = null;
	private List<IBusinessDataType> bizTypeList = null;
	private List<IStandardField> stdFieldList = null;
	private List<IUserConstantItem> constList = null;
	private List<IErrorNoItem> errorNoList = null;
	private List<IDictionaryType> dictList = null;
	
	private Map<String, ITypeDefaultValue> defValueMap = null;
	private Map<String, IStandardDataType> stdTypeMap = null;
	private Map<String, IBusinessDataType> bizTypeMap = null;
	private Map<String, IStandardField> stdFieldMap = null;
	private Map<String, IUserConstantItem> constMap = null;
	private Map<String, IErrorNoItem> errorNoMap = null;
	private Map<String, IDictionaryType> dictMap = null;
	

	/**
	 * @param project
	 */
	public BasicMetadataService(IARESProject project) {
		super();
		this.project = project;
	}
	
	

	protected Map<String, ITypeDefaultValue> getAllTypeDefaultValueMap() {
		if (defValueMap == null) {
			defValueMap = getMetadataItemMap(IMetadataRefType.DefValue);
		}
		return defValueMap;
	}
	
	protected Map<String, IStandardDataType> getAllStandardDataTypeMap() {
		if (stdTypeMap == null) {
			stdTypeMap = getMetadataItemMap(IMetadataRefType.StdType);
		}
		return stdTypeMap;
	}
	
	protected Map<String, IBusinessDataType> getAllBusinessDataTypeMap() {
		if (bizTypeMap == null) {
			bizTypeMap = getMetadataItemMap(IMetadataRefType.BizType);
		}
		return bizTypeMap;
	}
	
	protected Map<String, IStandardField> getAllStandardFieldMap() {
		if (stdFieldMap == null) {
			stdFieldMap = getMetadataItemMap(IMetadataRefType.StdField);
		}
		return stdFieldMap;
	}
	
	protected Map<String, IUserConstantItem> getAllUserConstantMap() {
		if (constMap == null) {
			constMap = getMetadataItemMap(IMetadataRefType.Const);
		}
		return constMap;
	}
	
	protected Map<String, IErrorNoItem> getAllErrorNoMap() {
		if (errorNoMap == null) {
			errorNoMap = getMetadataItemMap(IMetadataRefType.ErrNo);
		}
		return errorNoMap;
	}
	
	protected Map<String, IDictionaryType> getAllDictionaryTypeMap() {
		if (dictMap == null) {
			dictMap = getMetadataItemMap(IMetadataRefType.Dict);
		}
		return dictMap;
	}
	
	
	protected <T extends IMetadataItem> List<T> getMetadataItemList(Map<String, T> map) {
		List<T> list = new ArrayList<T>(map.values());
		Collections.sort(list, new Comparator<T>(){

			@Override
			public int compare(T o1, T o2) {
				return o1.getName().compareTo(o2.getName());
			}});
		return list;
	}

	protected static DeMetadataItem<?> decrypt(MetadataItem item, IARESResource resource) {
		if (ObjectUtils.equals(item.eClass(), MetadataPackage.Literals.TYPE_DEFAULT_VALUE)) {
			return MetadataUtil.decrypt((TypeDefaultValue)item, resource);
		} else if (ObjectUtils.equals(item.eClass(), MetadataPackage.Literals.STANDARD_DATA_TYPE)) {
			return MetadataUtil.decrypt((StandardDataType)item, resource);
		} else if (ObjectUtils.equals(item.eClass(), MetadataPackage.Literals.BUSINESS_DATA_TYPE)) {
			return MetadataUtil.decrypt((BusinessDataType)item, resource);
		} else if (ObjectUtils.equals(item.eClass(), MetadataPackage.Literals.STANDARD_FIELD)) {
			return MetadataUtil.decrypt((StandardField)item, resource);
		} else if (ObjectUtils.equals(item.eClass(), MetadataPackage.Literals.ERROR_NO_ITEM)) {
			return MetadataUtil.decrypt((ErrorNoItem)item, resource);
		} else if (ObjectUtils.equals(item.eClass(), MetadataPackage.Literals.CONSTANT_ITEM)) {
			return MetadataUtil.decrypt((ConstantItem)item, resource);
		} else if (ObjectUtils.equals(item.eClass(), MetadataPackage.Literals.DICTIONARY_TYPE)) {
			return MetadataUtil.decrypt((DictionaryType)item, resource);
		} 
		throw new UnsupportedOperationException();
	}
	
	protected <T extends IMetadataItem> Map<String, T> getMetadataItemMap(String refType) {
		
		Map<String, T> map = new HashMap<String, T>();
		Set<String> errNameSet = new HashSet<String>();
		
		ReferenceManager manager = ReferenceManager.getInstance();
		List<ReferenceInfo> refInfos = manager.getReferenceInfos(project, refType, true);
		for (ReferenceInfo ref : refInfos) {
			IARESResource res = ref.getResource();
			MetadataItem owner = (MetadataItem) ref.getObject();
			
			DeMetadataItem<?> d = decrypt(owner, res);
			String key = d.getName();
			if (StringUtils.isBlank(key)) {
				continue;
			}
			if (map.containsKey(key)) {
				// 进行删除，并添加到黑名单
				map.remove(key);
				errNameSet.add(key);
			} else if (errNameSet.contains(key)) {
				// 已经列入黑名单，不需要添加
			} else {
				map.put(key, (T) d);
			}
		}
		
		return map;
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getTypeDefaultValueList()
	 */
	@Override
	public List<ITypeDefaultValue> getTypeDefaultValueList() {
		if (defValueList == null) {
			defValueList = Collections.unmodifiableList(getMetadataItemList(getAllTypeDefaultValueMap()));
		}
		return defValueList;
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getTypeDefaultValue(java.lang.String)
	 */
	@Override
	public ITypeDefaultValue getTypeDefaultValue(String name) {
		return getAllTypeDefaultValueMap().get(name);
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getStandardDataTypeList()
	 */
	@Override
	public List<IStandardDataType> getStandardDataTypeList() {
		if (stdTypeList == null) {
			stdTypeList = Collections.unmodifiableList(getMetadataItemList(getAllStandardDataTypeMap()));
		}
		return stdTypeList;
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getStandardDataType(java.lang.String)
	 */
	@Override
	public IStandardDataType getStandardDataType(String name) {
		return getAllStandardDataTypeMap().get(name);
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getBusinessDataTypeList()
	 */
	@Override
	public List<IBusinessDataType> getBusinessDataTypeList() {
		if (bizTypeList == null) {
			bizTypeList = Collections.unmodifiableList(getMetadataItemList(getAllBusinessDataTypeMap()));
		}
		return bizTypeList;
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getBusinessDataType(java.lang.String)
	 */
	@Override
	public IBusinessDataType getBusinessDataType(String name) {
		return getAllBusinessDataTypeMap().get(name);
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getStandardFieldList()
	 */
	@Override
	public List<IStandardField> getStandardFieldList() {
		if (stdFieldList == null) {
			stdFieldList = Collections.unmodifiableList(getMetadataItemList(getAllStandardFieldMap()));
		}
		return stdFieldList;
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getStandardField(java.lang.String)
	 */
	@Override
	public IStandardField getStandardField(String name) {
		return getAllStandardFieldMap().get(name);
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getErrorNoList()
	 */
	@Override
	public List<IErrorNoItem> getErrorNoList() {
		if (errorNoList == null) {
			errorNoList = Collections.unmodifiableList(getMetadataItemList(getAllErrorNoMap()));
		}
		return errorNoList;
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getErrorNo(java.lang.String)
	 */
	@Override
	public IErrorNoItem getErrorNo(String name) {
		return getAllErrorNoMap().get(name);
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getUserConstantList()
	 */
	@Override
	public List<IUserConstantItem> getUserConstantList() {
		if (constList == null) {
			constList = Collections.unmodifiableList(getMetadataItemList(getAllUserConstantMap()));
		}
		return constList;
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getUserConstant(java.lang.String)
	 */
	@Override
	public IUserConstantItem getUserConstant(String name) {
		return getAllUserConstantMap().get(name);
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getDictionaryTypeList()
	 */
	@Override
	public List<IDictionaryType> getDictionaryTypeList() {
		if (dictList == null) {
			dictList = Collections.unmodifiableList(getMetadataItemList(getAllDictionaryTypeMap()));
		}
		return dictList;
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.service.IMetadataService#getDictionary(java.lang.String)
	 */
	@Override
	public IDictionaryType getDictionary(String name) {
		return getAllDictionaryTypeMap().get(name);
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.service.IDataService#refresh()
	 */
	@Override
	public void refresh() {
		defValueList = null;
		defValueMap = null;
		stdTypeList = null;
		stdTypeMap = null;
		bizTypeList = null;
		bizTypeMap = null;
		stdFieldList = null;
		stdFieldMap = null;
		constList = null;
		constMap = null;
		errorNoList = null;
		errorNoMap = null;
		dictList = null;
		dictMap = null;
	}
}
