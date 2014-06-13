package com.hundsun.ares.studio.jres.basicdata.logic.epackage.extensionpoint;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.hundsun.ares.studio.jres.basicdata.logic.epackage.IBaiscDataEpackageFactory;

public class EpackageFactoryManager {
	
	private static  EpackageFactoryManager  instance = null;
	
	private static final List<EpackageFactoryItem> factoryList = new ArrayList<EpackageFactoryItem>();
	
	private EpackageFactoryManager(){
		initEpackageFactory();
	}

	public static EpackageFactoryManager getInstance(){
		if(null == instance){
			instance = new EpackageFactoryManager();
		}
		return instance;
	}
	public void initEpackageFactory(){
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = registry.getExtensionPoint(IBaiscDataEpackageFactory.EXTPointID);
		
		for(IConfigurationElement item :extensionPoint.getConfigurationElements()){
			try {
				EpackageFactoryItem factoryItem = new EpackageFactoryItem();
				factoryItem.id = item.getAttribute(IBaiscDataEpackageFactory.EXTAttr_ID);
				factoryItem.name = item.getAttribute(IBaiscDataEpackageFactory.EXTAttr_Name);
				factoryItem.factory = (IBaiscDataEpackageFactory)item.createExecutableExtension(IBaiscDataEpackageFactory.EXTAttr_Class);
				
				factoryList.add(factoryItem);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 根据类型获取工厂
	 * @param type
	 * @return
	 */
	public IBaiscDataEpackageFactory getFactory(String type){
		for(EpackageFactoryItem item:factoryList){
			if(StringUtils.equals(item.id, type)){
				return item.factory;
			}
		}
		return null;
	}
	
	/**
	 * 获取工厂列表
	 * @return
	 */
	public List<EpackageFactoryItem> getFactoryList(){
		return factoryList;
	}
}
