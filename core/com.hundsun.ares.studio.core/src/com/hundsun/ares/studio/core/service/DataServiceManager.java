/**
 * 源程序名称：DataServiceManager.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.core.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.hundsun.ares.studio.core.IARESProject;

/**
 * @author gongyf
 *
 */
public class DataServiceManager {
	
	static private class ServiceFactory {
		public String id;
		public Class<? extends IDataService> type;
		public IDataServiceFactory factory;
	}
	
	
	private static DataServiceManager instance;
	private DataServiceManager() {
		loadFactories();
	}
	
	public static DataServiceManager getInstance() {
		if (instance == null) {
			instance = new DataServiceManager();
		}
		return instance;
	}
	
	private Logger logger = Logger.getLogger(getClass());
	private Map<Class<? extends IDataService>, ServiceFactory> factoryMap = new HashMap<Class<? extends IDataService>, ServiceFactory>();
	private void loadFactories() {
		logger.info("加载JRES数据服务扩展点");
		
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(IServiceExtentionPoint.NAMESPACE, IServiceExtentionPoint.EP_Name);
		for (IConfigurationElement element : elements) {
			try {
				ServiceFactory sf = new ServiceFactory();
				
				sf.id = element.getAttribute(IServiceExtentionPoint.EP_Attribute_ID);
				sf.factory = (IDataServiceFactory) element.createExecutableExtension(IServiceExtentionPoint.EP_Attribute_Factory);
				sf.type = (Class<? extends IDataService>) sf.factory.getClass().getClassLoader().loadClass(element.getAttribute(IServiceExtentionPoint.EP_Attribute_Type));
				
				factoryMap.put(sf.type, sf);
			
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		
	}
	
	/**
	 * 获取一个信息服务，每次获取将会返回新的服务对象<BR>
	 * 如果没有相应的创建工厂则返回null
	 * @param project
	 * @param serviceClass
	 * @return
	 */
	public <T extends IDataService> T getService(IARESProject project, Class<T> serviceClass)  {
		ServiceFactory sf = factoryMap.get(serviceClass);
		if (sf == null || sf.factory == null) {
			throw new UnsupportedOperationException("没有找到该类型服务的创建工厂");
		}
		return (T) sf.factory.createService(project);
	}
	
	
}


