/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.constants.IAtomResType;
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
public class AtomSearcher implements IARESSarcher {

	public static String RES_TYPE_ATOM_FUNCTION = "CRES原子函数";
	public static String RES_TYPE_ATOM_SERVICE = "CRES原子服务";
	public static String SEARCH_ITEM_NAME = "英文名";
	public static String SEARCH_ITEM_CNAME = "中文名";
	public static String SEARCH_ITEM_PSEUDOCODE = "伪代码";
	public static String SEARCH_ITEM_IN_OUT_INTERNAL_VARS = "输入输出和内部变量";
	public static String SEARCH_ITEM_OBJECT_ID = "对象号";
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
	public void search( List<String> searchResTypes, List<String> searchItems, IARESElement[] scopes, ARESSearchQuery query) {
		Pattern searchPattern = query.getSearchPattern ();
		if ( searchResTypes.contains(RES_TYPE_ATOM_FUNCTION)||searchResTypes.contains(RES_TYPE_ATOM_SERVICE)) {
			if (scopes!=null && scopes.length> 0) {
				List<String> typeList = new ArrayList<String>();
				if(searchResTypes.contains(RES_TYPE_ATOM_FUNCTION)){
					typeList.add(IAtomResType.ATOM_FUNCTION);
				}
				if(searchResTypes.contains(RES_TYPE_ATOM_SERVICE)){
					typeList.add(IAtomResType.ATOM_SERVICE);
				}
				String[] types = typeList.toArray(new String[typeList.size()]);
				for(IARESElement scope:scopes){
					Map<AtomFunction,IARESResource> atomMap = this.getResources(types, scope);
					if(searchItems.contains(SEARCH_ITEM_NAME)||
							searchItems.contains(SEARCH_ITEM_CNAME)||
							searchItems.contains(SEARCH_ITEM_OBJECT_ID)||
							searchItems.contains(SEARCH_ITEM_PSEUDOCODE)||
							searchItems.contains(SEARCH_ITEM_IN_OUT_INTERNAL_VARS)
							){
						
						for (AtomFunction atom : atomMap.keySet()) {
							/*如果要搜索英文名*/
							if (searchItems.contains(SEARCH_ITEM_NAME)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(atom.getName(), "")).matches()){
									query.addMatch(atomMap.get(atom));
									continue;
								}
							}
							/*如果要搜索中文名*/
							if (searchItems.contains(SEARCH_ITEM_CNAME)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(atom.getChineseName(), "")).matches()){
									query.addMatch(atomMap.get(atom));
									continue;
								}
							}
							/*如果要搜索对象号*/
							if (searchItems.contains(SEARCH_ITEM_OBJECT_ID)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(atom.getObjectId(), "")).matches()){
									query.addMatch(atomMap.get(atom));
									continue;
								}
							}
							/*如果要搜索伪代码*/
							if (searchItems.contains(SEARCH_ITEM_PSEUDOCODE)) {
								Matcher m = searchPattern.matcher(StringUtils.defaultIfEmpty(atom.getPseudoCode(), ""));
								if (m.find()) {
									query.addMatch(atomMap.get(atom));
									continue;
								}
							}
							
							/*如果要搜索输入输出内部变量*/
							if (searchItems.contains(SEARCH_ITEM_IN_OUT_INTERNAL_VARS)) {
								List<Parameter>  parameters = new ArrayList<Parameter>();
								parameters.addAll(atom.getInputParameters());
								parameters.addAll(atom.getOutputParameters());
								parameters.addAll(atom.getInternalVariables());
								for(Parameter parameter:parameters){
									if(searchPattern.matcher(StringUtils.defaultIfEmpty(parameter.getId(), "")).matches()){
										query.addMatch(atomMap.get(atom));
										break;
									}
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
	private Map<AtomFunction,IARESResource> getResources(String[] types ,IARESElement scope){

		Map<AtomFunction,IARESResource> atomMap = new HashMap<AtomFunction,IARESResource>();
		
		if(scope instanceof IARESProject){
			IARESProject project = (IARESProject) scope;
			
			try {
				IARESResource[] resources = project.getResources(types);
				for(IARESResource resource:resources){
					AtomFunction atom = (AtomFunction) resource.getInfo(EObject.class);
					atomMap.put(atom, resource);
				}
			} catch (Exception e) {
				console.error("搜索原子时出错:"+e.getMessage());
			}
			
		}else if(scope instanceof IARESModuleRoot){
			IARESModuleRoot moduleRoot = (IARESModuleRoot) scope;
			try {
				IARESModule[] modules = moduleRoot.getModules();
				for(IARESModule module:modules){
					
					IARESResource[] resources = module.getARESResources(types, true);;
					for(IARESResource resource:resources){
						AtomFunction atom = (AtomFunction) resource.getInfo(EObject.class);
						atomMap.put(atom, resource);
					}
				}
				
			} catch (Exception e) {
				console.error("搜索原子时出错:"+e.getMessage());
			}
		}else if(scope instanceof IARESModule){

			IARESModule module = (IARESModule) scope;
			try {
					IARESResource[] resources = module.getARESResources(types, true);;
					for(IARESResource resource:resources){
						AtomFunction atom = (AtomFunction) resource.getInfo(EObject.class);
						atomMap.put(atom, resource);
					}
				
			} catch (Exception e) {
				console.error("搜索原子时出错:"+e.getMessage());
			}
		
		} else if (scope instanceof IARESResource) {

			IARESResource resource = (IARESResource) scope;
			try {
				EObject eobject = resource.getInfo(EObject.class);
				if(eobject instanceof AtomFunction){
					AtomFunction atom = (AtomFunction) eobject;
					atomMap.put(atom, resource);
				}
				
			

			} catch (Exception e) {
				console.error("搜索原子时出错:"+e.getMessage());
			}

		}
	
		return atomMap;

	}
}

