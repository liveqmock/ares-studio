/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureResType;
import com.hundsun.ares.studio.ui.search.ARESSearchQuery;
import com.hundsun.ares.studio.ui.search.IARESSarcher;

/**
 * @author liaogc
 *
 */
public class ProcedureSearcher implements IARESSarcher {

	public static String RES_TYPE_LOGIC_PRUCEDURE = "过程";
	public static String SEARCH_ITEM_NAME = "英文名";
	public static String SEARCH_ITEM_CAME = "中文名";
	public static String SEARCH_ITEM_PSEUDOCODE = "伪代码";
	public static String SEARCH_ITEM_OBJECT_ID = "对象号";
	public static String SEARCH_ITEM_IN_OUT_INTERNAL_VARS = "输入输出和内部变量";
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
		if ( searchResTypes.contains(RES_TYPE_LOGIC_PRUCEDURE)) {
			if (scopes!=null && scopes.length> 0 ) {
				for(IARESElement scope:scopes){
					Map<Procedure,IARESResource> procedureMap = this.getResources(new String[]{IProcedureResType.PROCEDURE}, scope);
					if(searchItems.contains(SEARCH_ITEM_NAME)||
							searchItems.contains(SEARCH_ITEM_CAME)||
							searchItems.contains(SEARCH_ITEM_OBJECT_ID)||
							searchItems.contains(SEARCH_ITEM_PSEUDOCODE)||
							searchItems.contains(SEARCH_ITEM_IN_OUT_INTERNAL_VARS)
							){
						
						for (Procedure procedure : procedureMap.keySet()) {
							/*如果要搜索英文名*/
							if (searchItems.contains(SEARCH_ITEM_NAME)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(procedure.getName(), "")).matches()){
									query.addMatch(procedureMap.get(procedure));
									continue;
								}
							}
							/*如果要搜索中文名*/
							if (searchItems.contains(SEARCH_ITEM_CAME)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(procedure.getChineseName(), "")).matches()){
									query.addMatch(procedureMap.get(procedure));
									continue;
								}
							}
							/*如果要搜索对象号*/
							if (searchItems.contains(SEARCH_ITEM_OBJECT_ID)) {
								if(searchPattern.matcher(StringUtils.defaultIfEmpty(procedure.getObjectId(), "")).matches()){
									query.addMatch(procedureMap.get(procedure));
									continue;
								}
							}
							/*如果要搜索伪代码*/
							if (searchItems.contains(SEARCH_ITEM_PSEUDOCODE)) {
								Matcher m = searchPattern.matcher(StringUtils.defaultIfEmpty(procedure.getPseudoCode(), ""));
								if (m.find()) {
									query.addMatch(procedureMap.get(procedure));
									continue;
								}
							}
							
							/*如果要搜索输入输出内部变量*/
							if (searchItems.contains(SEARCH_ITEM_IN_OUT_INTERNAL_VARS)) {
								List<Parameter>  parameters = new ArrayList<Parameter>();
								parameters.addAll(procedure.getInputParameters());
								parameters.addAll(procedure.getOutputParameters());
								parameters.addAll(procedure.getInternalVariables());
								for(Parameter parameter:parameters){
									if(searchPattern.matcher(StringUtils.defaultIfEmpty(parameter.getId(), "")).matches()){
										query.addMatch(procedureMap.get(procedure));
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
	private Map<Procedure,IARESResource> getResources(String[] types ,IARESElement scope){

		Map<Procedure,IARESResource> procedureMap = new HashMap<Procedure,IARESResource>();
		
		if(scope instanceof IARESProject){
			IARESProject project = (IARESProject) scope;
			
			try {
				IARESResource[] resources = project.getResources(types);
				for(IARESResource resource:resources){
					Procedure procedure = (Procedure) resource.getInfo(EObject.class);
					procedureMap.put(procedure, resource);
				}
			} catch (Exception e) {
				console.error("搜索过程时出错:"+e.getMessage());
			}
			
		}else if(scope instanceof IARESModuleRoot){
			IARESModuleRoot moduleRoot = (IARESModuleRoot) scope;
			try {
				IARESModule[] modules = moduleRoot.getModules();
				for(IARESModule module:modules){
					
					IARESResource[] resources = module.getARESResources(types, true);;
					for(IARESResource resource:resources){
						Procedure procedure = (Procedure) resource.getInfo(EObject.class);
						procedureMap.put(procedure, resource);
					}
				}
				
			} catch (Exception e) {
				console.error("搜索过程时出错:"+e.getMessage());
			}
		}else if(scope instanceof IARESModule){

			IARESModule module = (IARESModule) scope;
			try {
					IARESResource[] resources = module.getARESResources(types, true);;
					for(IARESResource resource:resources){
						Procedure procedure = (Procedure) resource.getInfo(EObject.class);
						procedureMap.put(procedure, resource);
					}
				
			} catch (Exception e) {
				console.error("搜索过程时出错:"+e.getMessage());
			}
		
		} else if (scope instanceof IARESResource) {

			IARESResource resource = (IARESResource) scope;
			try {


				EObject eobject = resource.getInfo(EObject.class);
				if(eobject instanceof Procedure){
					Procedure procedure = (Procedure) eobject;
					procedureMap.put(procedure, resource);
				}

			} catch (Exception e) {
				console.error("搜索过程时出错:"+e.getMessage());
			}

		}
	
		return procedureMap;

	}
}
