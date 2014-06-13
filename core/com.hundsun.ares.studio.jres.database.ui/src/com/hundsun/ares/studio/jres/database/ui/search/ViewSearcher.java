/**
 * 源程序名称：ViewSearcher.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.ui.search.ARESSearchQuery;
import com.hundsun.ares.studio.ui.search.IARESSarcher;

/**
 * @author liaogc
 *
 */
public class ViewSearcher implements IARESSarcher {

	public static String RES_TYPE_VIEW = "数据库视图";
	public static String SEARCH_ITEM_NAME = "英文名";
	public static String SEARCH_ITEM_CAME = "中文名";
	public static String SEARCH_ITEM_OBJECT_ID = "对象号";
	public static String SEARCH_ITEM_SQL = "视图SQL";
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
		if (searchResTypes.contains(RES_TYPE_VIEW)) {
			if (scopes!=null && scopes.length> 0 ) {
				for(IARESElement scope:scopes){
					Map<ViewResourceData,IARESResource> viewMap = this.getResources(new String[]{IDatabaseResType.View}, scope);
					if(searchItems.contains(SEARCH_ITEM_NAME)||
							searchItems.contains(SEARCH_ITEM_CAME)||
							searchItems.contains(SEARCH_ITEM_OBJECT_ID)||
							searchItems.contains(SEARCH_ITEM_SQL)
							){
						
						for (ViewResourceData view : viewMap.keySet()) {
							/*如果要搜索英文名*/
							if (searchItems.contains(SEARCH_ITEM_NAME)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(view.getName(), "")).matches()){
									query.addMatch(viewMap.get(view));
									continue;
								}
							}
							/*如果要搜索中文名*/
							if (searchItems.contains(SEARCH_ITEM_CAME)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(view.getChineseName(), "")).matches()){
									query.addMatch(viewMap.get(view));
									continue;
								}
							}
							/*如果要搜索对象号*/
							if (searchItems.contains(SEARCH_ITEM_OBJECT_ID)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(view.getObjectId(), "")).matches()){
									query.addMatch(viewMap.get(view));
									continue;
								}
							}
							/*如果要搜索sql*/
							if (searchItems.contains(SEARCH_ITEM_SQL)) {
								Matcher m = searchPattern.matcher(StringUtils.defaultIfEmpty(view.getSql(), ""));
								if (m.find()) {
									query.addMatch(viewMap.get(view));
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
	private Map<ViewResourceData,IARESResource> getResources(String[] types ,IARESElement scope){

		Map<ViewResourceData,IARESResource> viewMap = new HashMap<ViewResourceData,IARESResource>();
		
		if(scope instanceof IARESProject){
			IARESProject project = (IARESProject) scope;
			
			try {
				IARESResource[] resources = project.getResources(types);
				for(IARESResource resource:resources){
					ViewResourceData view = (ViewResourceData) resource.getInfo(EObject.class);
					viewMap.put(view, resource);
				}
			} catch (Exception e) {
				console.error("搜索数据库视图时出错:"+e.getMessage());
			}
			
		}else if(scope instanceof IARESModuleRoot){
			IARESModuleRoot moduleRoot = (IARESModuleRoot) scope;
			try {
				IARESModule[] modules = moduleRoot.getModules();
				for(IARESModule module:modules){
					
					IARESResource[] resources = module.getARESResources(types, true);;
					for(IARESResource resource:resources){
						ViewResourceData view = (ViewResourceData) resource.getInfo(EObject.class);
						viewMap.put(view, resource);
					}
				}
				
			} catch (Exception e) {
				console.error("搜索数据库视图时出错:"+e.getMessage());
			}
		}else if(scope instanceof IARESModule){

			IARESModule module = (IARESModule) scope;
			try {
					IARESResource[] resources = module.getARESResources(types, true);;
					for(IARESResource resource:resources){
						ViewResourceData view = (ViewResourceData) resource.getInfo(EObject.class);
						viewMap.put(view, resource);
					}
				
			} catch (Exception e) {
				console.error("搜索数据库视图时出错:"+e.getMessage());
			}
		
		} else if (scope instanceof IARESResource) {

			IARESResource resource = (IARESResource) scope;
			try {
				EObject eobject = resource.getInfo(EObject.class);
				if(eobject instanceof ViewResourceData){
					ViewResourceData view = (ViewResourceData) eobject;
					viewMap.put(view, resource);
				}

			}catch (Exception e) {
				console.error("搜索数据库视图时出错:"+e.getMessage());
			}

		}
	
		return viewMap;

	}

}

