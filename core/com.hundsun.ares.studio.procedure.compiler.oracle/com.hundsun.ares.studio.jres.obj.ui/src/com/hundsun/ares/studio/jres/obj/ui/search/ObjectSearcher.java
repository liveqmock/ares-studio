/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.obj.ui.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.search.ARESSearchQuery;
import com.hundsun.ares.studio.ui.search.IARESSarcher;

/**
 * @author liaogc
 *
 */
public class ObjectSearcher implements IARESSarcher {

	public static String RES_TYPE_OBJECT = "对象";
	public static String SEARCH_ITEM_NAME = "英文名";
	public static String SEARCH_ITEM_CAME = "中文名";
	public static String SEARCH_ITEM_OBJECT_ID = "对象号";
	public static String SEARCH_ITEM_PROP = "对象属性";
	static final Logger console = ConsoleHelper.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.ui.search.IARESSarcher#search(java.lang.String,
	 * boolean, java.util.List, java.util.List,
	 * com.hundsun.ares.studio.core.IARESElement)
	 */
	@Override
	public void search(List<String> searchResTypes, List<String> searchItems, IARESElement[] scopes, ARESSearchQuery query) {
		Pattern searchPattern = query.getSearchPattern ();
		if (searchResTypes.contains(RES_TYPE_OBJECT)) {
			if (scopes!=null && scopes.length> 0 ) {
				for(IARESElement scope:scopes){
					Map<ARESObject,IARESResource> objectMap = this.getResources(new String[]{"object"}, scope);
					if(searchItems.contains(SEARCH_ITEM_NAME)||
							searchItems.contains(SEARCH_ITEM_CAME)||
							searchItems.contains(SEARCH_ITEM_OBJECT_ID)||
							searchItems.contains(SEARCH_ITEM_PROP)
							){
						
						for (ARESObject object : objectMap.keySet()) {
							/*如果要搜索英文名*/
							if (searchItems.contains(SEARCH_ITEM_NAME)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(object.getName(), "")).matches()){
									query.addMatch(objectMap.get(object));
									continue;
								}
							}
							/*如果要搜索中文名*/
							if (searchItems.contains(SEARCH_ITEM_CAME)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(object.getChineseName(), "")).matches()){
									query.addMatch(objectMap.get(object));
									continue;
								}
							}
							/*如果要搜索表对象号*/
							if (searchItems.contains(SEARCH_ITEM_OBJECT_ID)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(object.getObjectId(), "")).matches()){
									query.addMatch(objectMap.get(object));
									continue;
								}
							}
							/*如果要搜索属性*/
							if (searchItems.contains(SEARCH_ITEM_PROP)) {
								EList<Parameter> properties = object.getProperties();
								boolean isFound = false;
								for(Parameter propertiy:properties){
									if(searchPattern.matcher(StringUtils.defaultIfEmpty(propertiy.getId(), "")).matches()){
										query.addMatch(objectMap.get(object));
										isFound = true;
										break;
									}	
								}
								if(isFound){
									continue;
								}
								
							}
							
							
						}
					}
					
					

				}
			}
			
		}

	}
	/**
	 * 获得资源
	 * @param types
	 * @param scope
	 * @return
	 */
	private Map<ARESObject,IARESResource> getResources(String[] types ,IARESElement scope){

		Map<ARESObject,IARESResource> objectMap = new HashMap<ARESObject,IARESResource>();
		
		if(scope instanceof IARESProject){
			IARESProject project = (IARESProject) scope;
			
			try {
				IARESResource[] resources = project.getResources(types);
				for(IARESResource resource:resources){
					ARESObject object = (ARESObject) resource.getInfo(EObject.class);
					objectMap.put(object, resource);
				}
			} catch (Exception e) {
				console.error("搜索对象时出错:"+e.getMessage());
			}
			
		}else if(scope instanceof IARESModuleRoot){
			IARESModuleRoot moduleRoot = (IARESModuleRoot) scope;
			try {
				IARESModule[] modules = moduleRoot.getModules();
				for(IARESModule module:modules){
					
					IARESResource[] resources = module.getARESResources(types, true);;
					for(IARESResource resource:resources){
						ARESObject object = (ARESObject) resource.getInfo(EObject.class);
						objectMap.put(object, resource);
					}
				}
				
			} catch (Exception e) {
				console.error("搜索对象时出错:"+e.getMessage());
			}
		}else if(scope instanceof IARESModule){

			IARESModule module = (IARESModule) scope;
			try {
					IARESResource[] resources = module.getARESResources(types, true);;
					for(IARESResource resource:resources){
						ARESObject object = (ARESObject) resource.getInfo(EObject.class);
						objectMap.put(object, resource);
					}
				
			}catch (Exception e) {
				console.error("搜索对象时出错:"+e.getMessage());
			}
		
		} else if (scope instanceof IARESResource) {

			IARESResource resource = (IARESResource) scope;
			try {
				EObject eobject = resource.getInfo(EObject.class);
				if(eobject instanceof ARESObject){
					ARESObject object = (ARESObject) eobject;
					objectMap.put(object, resource);
				}
				
			} catch (Exception e) {
				console.error("搜索对象时出错:"+e.getMessage());
			}

		}
	
		return objectMap;

	}
}

