/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.model.metadata.util;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.context.JRESContextManager;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * 元数据服务提供器，主要处理一下信息：
 * <li>通过字段名获取元数据信息;
 * <li>通过元素数据之间的关系，查找对应元数据信息。
 * 
 * @author qinyuan
 *
 */
public class MetadataServiceProvider {
	
	/**
	 * c类型定义
	 */
	public static final String C_TYPE = "c";
	
	/**
	 * oracle类型定义
	 */
	public static final String ORACLE_TYPE = "oracle";
	
	//mysql数据库
	public static final String MYSQL_TYPE = "mysql";
	
	/**
	 * 根据标准字段名称获取标准字段
	 * @param project 工程
	 * @param name 字段名
	 * @return StandardField 标准字段
	 * @throws Exception
	 */
	public static StandardField getStandardFieldByName(IARESProject project,String name) throws Exception{
		
		StandardField  stdField = getMetadataModelByName(project, name, IMetadataRefType.StdField, StandardField.class);
		if(null == stdField){
			throw new Exception(String.format("标准字段[%s]不存在。", name));
		}
		return stdField;
	}
	
	/**
	 * 根据标准字段名称获取标准字段，为空时，直接返回
	 * @param project 工程
	 * @param name 字段名
	 * @return StandardField 标准字段
	 */
	public static StandardField getStandardFieldByNameNoExp(IARESProject project,String name){
		StandardField  stdField = getMetadataModelByName(project, name, IMetadataRefType.StdField, StandardField.class);
		return stdField;
	}
	
	/**
	 * 根据业务数据名称获取业务数据类型
	 * @param project 工程
	 * @param name 业务数据类型名
	 * @return BusinessDataType 业务数据类型
	 * @throws Exception
	 */
	public static BusinessDataType getBusinessDataTypeByName(IARESProject project,String name) throws Exception{
		BusinessDataType bizType = getMetadataModelByName(project, name, IMetadataRefType.BizType, BusinessDataType.class);
		if(null == bizType) {
			throw new Exception(String.format("业务数据类型[%s]不存在。", name));
		}
		return bizType;
	}
	
	/**
	 * 根据业务数据名称获取业务数据类型，为空时，直接返回
	 * @param project 工程
	 * @param name 业务数据类型名
	 * @return BusinessDataType 业务数据类型
	 */
	public static BusinessDataType getBusinessDataTypeByNameNoExp(IARESProject project,String name){
		BusinessDataType bizType = getMetadataModelByName(project, name, IMetadataRefType.BizType, BusinessDataType.class);
		return bizType;
	}
	
	/**
	 * 根据标准字段名称获取标准字段对应的业务数据类型
	 * @param project 工程
	 * @param name 字段名
	 * @return BusinessDataType 业务数据类型
	 * @throws Exception
	 */
	public static BusinessDataType getBusinessDataTypeOfStdFieldByName(IARESProject project,String name) throws Exception{
		
		StandardField stdField = getStandardFieldByName(project, name);
		BusinessDataType bizType =  getMetadataModelByName(project, stdField.getDataType(), IMetadataRefType.BizType, BusinessDataType.class);
		
		if(null == bizType){
			throw new Exception(String.format("标准字段[%s]关联的业务数据类型[%s]不存在。", name,stdField.getDataType()));
		}
		return bizType;
	}
	
	/**
	 * 根据标准类型名称获取标准类型
	 * @param project 工程
	 * @param name 标准类型名称
	 * @return StandardDataType 标准类型
	 * @throws Exception
	 */
	public static StandardDataType getStandardDataTypeByName(IARESProject project,String name) throws Exception{
		StandardDataType standType = getMetadataModelByName(project, name, IMetadataRefType.StdType, StandardDataType.class);
		if(null == standType){
			throw new Exception(String.format("标准数据类型[%s]不存在。", name));
		}
		return standType;
	}
	
	/**
	 * 根据标准类型名称获取标准类型
	 * @param project 工程
	 * @param name 标准类型名称
	 * @return StandardDataType 标准类型
	 */
	public static StandardDataType getStandardDataTypeByNameNoExp(IARESProject project,String name){
		StandardDataType standType = getMetadataModelByName(project, name, IMetadataRefType.StdType, StandardDataType.class);
		return standType;
	}
	
	/**
	 * 根据业务数据类型字段名称获取标准字段对应的标准数据类型
	 * @param project 工程
	 * @param name 字段名
	 * @return StandardDataType 标准数据类型
	 * @throws Exception
	 */
	public static StandardDataType getStandardDataTypeOfBizTypeByName(IARESProject project,String name) throws Exception{
		BusinessDataType bizType = getBusinessDataTypeByName(project, name);
		StandardDataType standType = getMetadataModelByName(project, bizType.getStdType(), IMetadataRefType.StdType, StandardDataType.class);
		if(null == standType){
			throw new Exception(String.format("业务数据类型[%s]所关联的标准数据类型[%s]不存在。", bizType.getName(),bizType.getStdType()));
		}
		return standType;
	}
	
	/**
	 * 根据标准字段名称获取标准字段对应的标准数据类型
	 * @param project 工程
	 * @param name 字段名
	 * @return StandardDataType 标准数据类型
	 * @throws Exception
	 */
	public static StandardDataType getStandardDataTypeOfStdFieldByName(IARESProject project,String name) throws Exception{
		BusinessDataType bizType = getBusinessDataTypeOfStdFieldByName(project, name);
		StandardDataType standType = getMetadataModelByName(project, bizType.getStdType(), IMetadataRefType.StdType, StandardDataType.class);
		if(null == standType){
			throw new Exception(String.format("标准字段[%s]所对应的标准数据类型[%s]不存在。", bizType.getName(),bizType.getStdType()));
		}
		
		return standType;
	}
	
	/**
	 * 根据默认值名称获取默认值
	 * @param project 工程
	 * @param name 默认值字段名
	 * @return TypeDefaultValue 默认值
	 * @throws Exception
	 */
	public static TypeDefaultValue getTypeDefaultValueByName(IARESProject project,String name) throws Exception{
		TypeDefaultValue typeDefValue = getMetadataModelByName(project, name, IMetadataRefType.DefValue, TypeDefaultValue.class);
		if(null == typeDefValue) {
			throw new Exception(String.format("默认值[%s]不存在。", name));
		}
		return typeDefValue;
	}
	
	/**
	 * 根据默认值名称获取默认值，为空时也直接返回
	 * @param project 工程
	 * @param name 默认值字段名
	 * @return TypeDefaultValue 默认值
	 */
	public static TypeDefaultValue getTypeDefaultValueByNameNoExp(IARESProject project,String name){
		TypeDefaultValue typeDefValue = getMetadataModelByName(project, name, IMetadataRefType.DefValue, TypeDefaultValue.class);
		return typeDefValue;
	}
	
	/**
	 * 根据业务数据类型名称获取默认值
	 * @param project 工程
	 * @param name 默认值字段名
	 * @return TypeDefaultValue 默认值
	 * @throws Exception
	 */
	public static TypeDefaultValue getTypeDefaultValueOfBizTypeByName(IARESProject project,String name) throws Exception{
		BusinessDataType bizType = getBusinessDataTypeByName(project, name);
		TypeDefaultValue typeDefValue = getMetadataModelByName(project, bizType.getDefaultValue(), IMetadataRefType.DefValue, TypeDefaultValue.class);
		if(null == typeDefValue) {
			throw new Exception(String.format("业务数据类型[%s]关联的默认值[%s]不存在。", name, bizType.getDefaultValue()));
		}
		return typeDefValue;
	}
	
	/**
	 * 根据标准字段名称获取默认值
	 * @param project 工程
	 * @param name 默认值字段名
	 * @return TypeDefaultValue 默认值
	 * @throws Exception
	 */
	public static TypeDefaultValue getTypeDefaultValueOfStdFieldByName(IARESProject project,String name) throws Exception{
		BusinessDataType bizType = getBusinessDataTypeOfStdFieldByName(project, name);
		TypeDefaultValue typeDefValue = getMetadataModelByName(project, bizType.getDefaultValue(), IMetadataRefType.DefValue, TypeDefaultValue.class);
		if(null == typeDefValue) {
			throw new Exception(String.format("标准字段[%s]关联的默认值[%s]不存在。", name, bizType.getDefaultValue()));
		}
		return typeDefValue;
	}
	
	/**
	 * 根据字段名获取错误号
	 * @param project 工程
	 * @param name 错误号
	 * @return ErrorNoItem 错误号值
	 * @throws Exception
	 */
	public static ErrorNoItem getErrorNoItemByName(IARESProject project,String name) throws Exception{
		ErrorNoItem errorNo = getMetadataModelByName(project, name, IMetadataRefType.ErrNo, ErrorNoItem.class);
		if(null == errorNo) {
			throw new Exception(String.format("错误号[%s]不存在。", name));
		}
		return errorNo;
	}
	
	/**
	 * 根据字段名获取常量
	 * @param project 工程
	 * @param name 常量名称
	 * @return ConstantItem 常量值
	 * @throws Exception
	 */
	public static ConstantItem getConstantItemByName(IARESProject project,String name) throws Exception{
		ConstantItem constant = getMetadataModelByName(project, name, IMetadataRefType.Const, ConstantItem.class);
		if(null == constant) {
			throw new Exception(String.format("常量[%s]不存在。", name));
		}
		return constant;
	}
	
	/**
	 * 根据字段名获取数据字典
	 * @param project 工程
	 * @param name 字典条目
	 * @return DictionaryType 数据字典
	 * @throws Exception
	 */
	public static DictionaryType getDictionaryTypeByName(IARESProject project,String name) throws Exception{
		DictionaryType dict = getMetadataModelByName(project, name, IMetadataRefType.Dict, DictionaryType.class);
		if(null == dict) {
			throw new Exception(String.format("数据字典[%s]不存在。", name));
		}
		return dict;
	}
	
	/**
	 * 根据字段名获取菜单
	 * @param project 工程
	 * @param name 菜单号
	 * @return MenuItem 菜单
	 * @throws Exception
	 */
	public static MenuItem getMenuItemByName(IARESProject project,String name) throws Exception{
		MenuItem menu = getMetadataModelByName(project, name, IMetadataRefType.Menu, MenuItem.class);
		if(null == menu) {
			throw new Exception(String.format("菜单[%s]不存在。", name));
		}
		return menu;
	}
	
	/**
	 * 根据元数据名称获取元数据字段
	 * @param <T>
	 * @param project 工程
	 * @param name 字段名
	 * @param restype 元数据类型 
	 * 			<li>类型详见{@link com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType}
	 * @param clazz 元数据类
	 * @return MetadataItem 元数据字段
	 */
	public static <T> T  getMetadataModelByName(IARESProject project,String name,String restype,Class<T> clazz){
		ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, restype, name, true);
		if(ref != null){
			return (T)ref.getObject();
		}
		return null;
	}

}
