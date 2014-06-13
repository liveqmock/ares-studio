package com.hundsun.ares.studio.engin.macrohandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.hundsun.ares.studio.engin.constant.IEngineConstant;

public class MacroTokenHandlerManager {

	private static Logger logger = Logger.getLogger(MacroTokenHandlerManager.class);
	private static MacroTokenHandlerManager instance;
	
	private MacroTokenHandlerManager(){
		init();
	}
	
	public static MacroTokenHandlerManager getInstance(){
		synchronized (MacroTokenHandlerManager.class) {
			if(null == instance){
				instance = new MacroTokenHandlerManager();
			}
			return instance;
		}
	}
	
	
	
	public IMacroTokenHandler getHandler(String key){
		for(FactoryItem item:fList){
			if(item.adapter.canHandle(key)){
				return item.adapter.create(key);
			}
		}
		return null;
	}
	
	/**
	 * 获取能处理的宏的key
	 * @return
	 */
	public String[] getMacroKeys(){
		List<String> tlist = new ArrayList<String>();
		for(FactoryItem item:fList){
			for(IMacroTokenHandler handler: item.adapter.getHandledMacros()){
				tlist.add(handler.getKey());
			}
		}
		return tlist.toArray(new String[0]);
	}
	
	
	List<FactoryItem> fList = new ArrayList<FactoryItem>();
	
	
	private void init(){
		logger.info("加载UFT宏处理器工厂扩展点。");
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(IEngineConstant.PLUGIN_ID , IMacroTokenHandlerFactoryExtentionPoint.EP_NAME);
		for (IConfigurationElement element : elements) {
			try {
				FactoryItem tmp = new FactoryItem();
				tmp.id = element.getAttribute(IMacroTokenHandlerFactoryExtentionPoint.EP_ATTR_ID);
				tmp.type = element.getAttribute(IMacroTokenHandlerFactoryExtentionPoint.EP_ATTR_TYPE);
				tmp.adapter = (IMacroTokenHandlerFactory)element.createExecutableExtension(IMacroTokenHandlerFactoryExtentionPoint.EP_ATTR_CLASS);
				fList.add(tmp);
			}catch (Exception e) {
				logger.error(String.format("读取扩展点%s.%s失败", IEngineConstant.PLUGIN_ID,
						IMacroTokenHandlerFactoryExtentionPoint.EP_NAME));
				e.printStackTrace();
			}
		}
	}
}

class FactoryItem{
	public String id;
	public String type;
	public IMacroTokenHandlerFactory adapter;
}
