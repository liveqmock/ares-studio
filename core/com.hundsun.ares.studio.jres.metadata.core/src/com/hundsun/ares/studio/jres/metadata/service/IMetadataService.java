/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.service;

import java.util.List;

import com.hundsun.ares.studio.core.service.IDataService;

/**
 * @author gongyf
 *
 */
public interface IMetadataService extends IDataService {
	
	/**
	 * 获取所有可用默认值，包含了引用工程和引用包
	 * @return
	 */
	List<ITypeDefaultValue> getTypeDefaultValueList();
	
	/**
	 * 获取指定名称的默认值
	 * @param name
	 * @return
	 */
	ITypeDefaultValue getTypeDefaultValue(String name);
	
	/**
	 * 获取所有可用的标准数据类型，包含了引用工程和引用包
	 * @return
	 */
	List<IStandardDataType> getStandardDataTypeList();
	
	/**
	 * 获取指定名称的标准数据类型
	 * @param name
	 * @return
	 */
	IStandardDataType getStandardDataType(String name);
	
	/**
	 * 获取所有可用的业务数据类型，包含了引用工程和引用包
	 * @return
	 */
	List<IBusinessDataType> getBusinessDataTypeList();
	
	/**
	 * 获取指定名称的业务数据类型
	 * @param name
	 * @return
	 */
	IBusinessDataType getBusinessDataType(String name);
	
	/**
	 * 获取所有可用的标准字段，包含了引用工程和引用包
	 * @return
	 */
	List<IStandardField> getStandardFieldList();
	
	/**
	 * 获取指定名称的标准字段
	 * @param name
	 * @return
	 */
	IStandardField getStandardField(String name);
	
	/**
	 * 获取所有可用的错误号，包含了引用工程和引用包
	 * @return
	 */
	List<IErrorNoItem> getErrorNoList();
	
	/**
	 * 获取指定名称的错误号
	 * @param name
	 * @return
	 */
	IErrorNoItem getErrorNo(String name);
	
	/**
	 * 获取所有可用的用户常量，包含了引用工程和引用包
	 * @return
	 */
	List<IUserConstantItem> getUserConstantList();
	
	/**
	 * 获取指定名称的用户常量
	 * @param name
	 * @return
	 */
	IUserConstantItem getUserConstant(String name);
	
	/**
	 * 获取所有可用的字典条目，包含了引用工程和引用包
	 * @return
	 */
	List<IDictionaryType> getDictionaryTypeList();
	
	/**
	 * 获取指定名称的字典条目
	 * @param name
	 * @return
	 */
	IDictionaryType getDictionary(String name);
}
