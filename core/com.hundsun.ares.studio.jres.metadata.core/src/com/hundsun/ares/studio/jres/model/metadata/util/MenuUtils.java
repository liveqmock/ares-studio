/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.model.metadata.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.metadata.MetadataCore;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MenuList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author wangxh
 *
 */
public class MenuUtils {
	
	public static String getDepartment() {
		IPreferencesService service = Platform.getPreferencesService();
		return service.getString("com.hundsun.ares.studio.jres.metadata.ui", MetadataCore.PRE_APPLICATION_DEPARTMENT, StringUtils.EMPTY, null);
	}

	/**
	 * 工具使用部门是否为证劵部门
	 */
	public static boolean isStockDepartment() {
		return StringUtils.equals(getDepartment(), MetadataCore.PRE_APPLICATION_DEPARTMENT_STOCK);
	}
	
	/***
	 * 获取所有已定义的功能列表，包含引用包和引用工程中的
	 */
	public static List<Function> getFunctions(IARESResource resource){
		List<Function> funcs = new ArrayList<Function>();
			try {
				MenuList  menuList = resource.getInfo(MenuList.class);
				funcs.addAll(menuList.getFunctions());
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
			
		
		return funcs;
	}
	
	/***
	 * 根据名称获取菜单功能
	 */
	public static Function getFunctionByName(IARESResource resource,String name){
		if(isStockDepartment()){
			for(Function func : getFunctions(resource)){
				if(func.getName().equals(name)){
					return func;
				}
			}
		}else{
			for(Function func : getFunctions(resource)){
				if(func.getSubTransCode().equals(name)){
					return func;
				}
			}
		}
		return MetadataFactory.eINSTANCE.createFunction();
	}

	/***
	 * 根据菜单项来获取菜单资源的模型
	 */
	public static MenuList getMenuList(MenuItem item){
		EObject obj = item.eContainer();
		if(obj instanceof MenuItem){
			return getMenuList((MenuItem)obj);
		}else if(obj instanceof MenuList){
			return (MenuList) obj;
		}
		return MetadataFactory.eINSTANCE.createMenuList();
	}
	/***
	 * 获取当前资源模型中的菜单列表
	 */
	public static List<MenuItem> getMenuItems(MenuList list){
		List<MenuItem> items = new ArrayList<MenuItem>();
		for(MenuItem  item : list.getItems()){
			items.add(item);
			items.addAll(getItemChildren(item));
		}
		return items;
	}

	/***
	 * 获取当前菜单的所有子菜单
	 */
	private static List<MenuItem> getItemChildren(MenuItem item) {
		List<MenuItem> items = new ArrayList<MenuItem>();
		for(MenuItem child : item.getSubItems()){
			items.add(child);
			items.addAll(getItemChildren(child));
		}
		return items;
	}
	/***
	 * 获取当前菜单的分类
	 */
	public static EList<MetadataCategory> getCategories(MenuItem item) {
		EList<MetadataCategory> categories = new BasicEList<MetadataCategory>();
		if(item.getParent() != null){
			for (TreeIterator<EObject> iterator = EcoreUtil.getAllContents(item.getParent().getRoot(), true); iterator.hasNext(); ) {
				EObject obj = iterator.next();
				if (obj instanceof MetadataCategory) {
					// 查找所在分组
					List<MetadataItem>items = ((MetadataCategory) obj).getItems();
					if (items.contains(item)) {
						categories.add((MetadataCategory) obj);
					}else{
						for(MetadataItem metadataItem : items){
							if(metadataItem instanceof MenuItem && getItemChildren((MenuItem)metadataItem).contains(item)){
								categories.add((MetadataCategory) obj);
							}
						}
					}
				}
			}
		}
		return categories;
	}
	
	public static Map<String, ReferenceInfo>  getRefableMenuItems(IARESResource resource){
		IARESProject project = resource.getARESProject();
		
		List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, IMetadataRefType.Menu, true);
		Set<String> errorSet = new HashSet<String>();
		Set<String> processedSet = new HashSet<String>();
		Map<String, ReferenceInfo> processedObjects = new HashMap<String, ReferenceInfo>();
		
		for (ReferenceInfo referenceInfo : infoList) {
				MenuItem item = (MenuItem) referenceInfo.getObject();
				IARESResource res = referenceInfo.getResource();
				if(res.getBundle().equals(resource.getBundle())){
					continue;
				}
				if (!errorSet.contains(item.getName())) {
					if (processedSet.contains(item.getName())) {
						processedSet.remove(item.getName());
						errorSet.add(item.getName());
						processedObjects.remove(item.getName());
					} else {
						processedSet.add(item.getName());
						processedObjects.put(item.getName(), referenceInfo);
					}
				}
			}
		return processedObjects;
	}
	
	public static Pair<MenuItem, IARESResource> resolve(MenuItem item, IARESResource master){
		if(StringUtils.isNotBlank(item.getRefId())){
		ReferenceInfo obj= getRefableMenuItems(master).get(item.getRefId());
			if(obj != null ){
				MenuItem menuItem = (MenuItem)obj.getObject();
				IARESResource res = obj.getResource();
				return new Pair<MenuItem, IARESResource>(menuItem, res);
			}
		}
		return new Pair<MenuItem, IARESResource>(null,null);
	}
	
}
