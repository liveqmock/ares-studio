package com.hundsun.ares.studio.jres.basicdata.logic.util;

import org.eclipse.core.databinding.UpdateValueStrategy;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.extensionpoint.EpackageFactoryItem;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.extensionpoint.EpackageFactoryManager;

public class EPackageUtil {

	/**
	 * 通过展示获取ID
	 * @param name
	 * @return
	 */
	public static String getEpackageFactoryItemID(String name) {
		for( EpackageFactoryItem  item: EpackageFactoryManager.getInstance().getFactoryList()){
			if(item.name.equals(name)){
				return item.id;
			}
		}
		return name;
	}
	
	/**
	 * 通过ID获取展示
	 * @param id
	 * @return
	 */
	public static String getEpackageFactoryItemName(String id) {
		for( EpackageFactoryItem  item: EpackageFactoryManager.getInstance().getFactoryList()){
			if(item.id.equals(id)){
				return item.name;
			}
		}
		return id;
	}
	
	/**
	 * 获取界面到模型更新策略
	 * @return
	 */
	public static UpdateValueStrategy getTypeTargetToModelStrategy(){
		return new UpdateValueStrategy(){
			@Override
			public Object convert(Object value) {
				if(value instanceof String){
					return getEpackageFactoryItemID((String) value);
				}
				return super.convert(value);
			}
		};
	}
	
	/**
	 * 获取模型到界面的更新策略
	 * @return
	 */
	public static UpdateValueStrategy getTypeModelToTargetStrategy(){
		return new UpdateValueStrategy(){
			@Override
			public Object convert(Object value) {
				if(value instanceof String){
					return getEpackageFactoryItemName((String) value);
				}
				return super.convert(value);
			}
		};
	}
	
	/**
	 * 获取基础数据的模型定义数据源的类型
	 * @param project
	 * @return
	 */
	public static String getBasicDataType(IARESProject project){
		try {
			IARESProjectProperty properties = project.getProjectProperty();
			String name = properties.getString(IBasicDataEpacakgeConstant.Property_Basic_Data_type_ID);
			if(name != null){
				return getEpackageFactoryItemID(name);
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return "";
	}
}
