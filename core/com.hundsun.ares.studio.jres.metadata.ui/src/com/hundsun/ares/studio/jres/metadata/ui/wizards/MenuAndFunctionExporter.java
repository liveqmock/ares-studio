package com.hundsun.ares.studio.jres.metadata.ui.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2AttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2MapAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleDataAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IHeaderSorter;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.NormalAttributeHelper;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MenuList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IMapExtensibleModelPropertyDescriptor;

public class MenuAndFunctionExporter {
	
	private static Logger log = Logger.getLogger(MenuAndFunctionExporter.class);
	
	public static final String MENU_SHEET = "菜单目录";
	public static final String FUN_SHEET = "功能目录";
	public static final String MENU_TO_FUN = "菜单功能对照目录";

	public static MenuList importMenusAndFunctions(HSSFWorkbook workBook, IARESElement element) throws Exception {
		 Map< String, List< List<String> > > excelStrings = 
		 POIUtils.getExcelStringForMenu(workBook, new String[] {MENU_SHEET, FUN_SHEET, MENU_TO_FUN}, new int[] {1, 1, 1}, new int[] {0,0,0});
		 
		 MenuList menuList = MetadataFactory.eINSTANCE.createMenuList();
		 List<List<String>> menuStrings = excelStrings.get(MENU_SHEET);
		 importMenus(menuList, menuStrings, element);
		 
		 List<List<String>> funStrings = excelStrings.get(FUN_SHEET);
		 importFunctions(menuList, funStrings, element);
		 
		 List<List<String>> funProxyStrings = excelStrings.get(MENU_TO_FUN);
		 importFunctionProxies(menuList, funProxyStrings, element);
		 
		 return menuList;
	}
	
	public static void importMenus(MenuList menuList, List<List<String>> menuStrings, IARESElement element) {
		// 标题名和属性助手的映射
		Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
		
		String[] titles = getMenuTitles();
		EStructuralFeature[] features = getMenuFeatures();
		
		for (int i = 0; i < titles.length; i++) {
			helperMap.put(titles[i], new NormalAttributeHelper(features[i]));
		}
			
		IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(element, MetadataPackage.Literals.MENU_ITEM);
		for (IExtensibleModelEditingSupport support : supports) {
			for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(element, MetadataPackage.Literals.MENU_ITEM)) {
				if (!desc.isDerived()) {
					if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
					} else {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
					}
				}
			}
		}
		
		// 根据实际标题名，排序属性助手，有可能存在空列
		List<IAttributeHelper> helperList = new ArrayList<IAttributeHelper>();
		int menuProsite = -1;
		for (int i = 0; i < menuStrings.get(0).size();i++) {
			String title = menuStrings.get(0).get(i);
			if (StringUtils.equals(title, "菜单位置")) {
				menuProsite = i;
			}
			helperList.add(helperMap.get(title));
		}
		
		Map<String , MenuItem> itemMap = new LinkedHashMap<String, MenuItem>();
		
		int lastDepth = 0;
		MenuItem lastMenu = null;
		LableA: for (int i = 1; i < menuStrings.size(); i++) {
			MenuItem item = (MenuItem) POIUtils.readObject(menuStrings.get(i), MetadataPackage.Literals.MENU_ITEM, helperList, true, element);
			
			{
				if (menuProsite > -1) {
					String mp = menuStrings.get(i).get(menuProsite);
					if (StringUtils.isNotBlank(mp)) {
						mp = mp.trim();
						itemMap.put(mp, item);
						if (mp.length() > 1) {
							{
								String subMp = mp.substring(0, mp.length()-1);
								while (StringUtils.isNotBlank(subMp)) {
									if (itemMap.get(subMp) != null) {
										itemMap.get(subMp).getSubItems().add(item);
										continue LableA;
									}else {
										subMp = subMp.substring(0, subMp.length()-1);
									}
								}
							}
						}
					}
					menuList.getItems().add(item);
				}else {
					String name = item.getChineseName();
					int depth = getDepth(name);
					
					if (depth == 0) {
						menuList.getItems().add(item);
					} else if (depth > lastDepth) {
						// 大于的情况，只可能大于1
						lastMenu.getSubItems().add(item);
					} else if (depth <= lastDepth) {
						int delta = lastDepth - depth;
						EObject parent = getParentMenu(lastMenu, delta/3 + 1);
						addToParent(parent, item);
					}
					
					item.setChineseName(StringUtils.trim(item.getChineseName()));
					lastMenu = item;
					lastDepth = depth;
				}
			}
		}
	}
	
	// 向上找 deltaDepth 层父菜单
	private static EObject getParentMenu(MenuItem item, int deltaDepth) {
		EObject parent = item;
		for (int i = 0; i < deltaDepth; i++) {
			parent = parent.eContainer();
		}
		return parent;
	}
	
	private static void addToParent(EObject parent, MenuItem item) {
		if (parent instanceof MenuList) {
			((MenuList) parent).getItems().add(item);
		} else if (parent instanceof MenuItem) {
			((MenuItem) parent).getSubItems().add(item);
		}
	}

	private static void importFunctions(MenuList menuList, List<List<String>> funStrings, IARESElement element) {
		String[] titles = getFunctionTitles();
		EStructuralFeature[] features = getFunctionFeatures();
		
		List<EObject> functions = POIUtils.importExcelStringTable(funStrings, MetadataPackage.Literals.FUNCTION, titles, features, true, new String[0], new String[0], element);
		for (EObject fun : functions) {
			menuList.getFunctions().add((Function) fun);
		}
	}
	
	private static void importFunctionProxies(MenuList menuList, List<List<String>> funProxyStrings, IARESElement element) {
		int rowCount = funProxyStrings.size();
		
		Map<String , Integer> titleMap = new HashMap<String, Integer>();
		
		if (rowCount > 0) {
			for (int i = 0; i < funProxyStrings.get(0).size(); i++) {
				String title = funProxyStrings.get(0).get(i);
				titleMap.put(title, i);
			}
		}
		
		// 第一行是标题，忽略
		MenuItem menu = null;
		for (int i = 1; i < rowCount; i++) {
			List<String> row = funProxyStrings.get(i);
			
			int menuNo = 0;
			if(!MenuUtils.isStockDepartment()){
				menuNo = titleMap.get("交易码");
			}else {
				menuNo = titleMap.get("菜单号");
			}
			if (StringUtils.isNotBlank(row.get(menuNo))) {
				menu = findMenu(menuList.getItems(), row.get(menuNo).trim() ,menu);
			}
			int funNo = 0;
			if(!MenuUtils.isStockDepartment()){
				funNo = titleMap.get("子交易码");
			}else {
				funNo = titleMap.get("功能编号");
			}
				
			if (menu == null) {
				log.error("导入菜单功能对照时，找不到菜单, 行数：" + rowCount);
			} else {
				FunctionProxy proxy = MetadataFactory.eINSTANCE.createFunctionProxy();
				if (!StringUtils.isEmpty(row.get(funNo)) && !StringUtils.equals(row.get(funNo), "-1")) {
					proxy.setFunCode(row.get(funNo));
					proxy.setDescription(row.get(1));
					menu.getFunctionProxys().add(proxy);	
				}
			}
		}
	}
	
	private static MenuItem findMenu(EList<MenuItem> menuItems, String menuId , MenuItem returnItem) {
		
		for (MenuItem item : menuItems) {
			if (StringUtils.equals(item.getName(), menuId)) {
				return item;
			}
			MenuItem item1 = findMenu(item.getSubItems(), menuId ,returnItem);
			if (item1 != null && StringUtils.equals(item1.getName(), menuId)) {
				return item1;
			}
		}
		return null;
	}
	
	public static void export(IARESResource resource, Map<String, List<List<String>>> contents) throws ARESModelException {
		MenuList menuList = resource.getInfo(MenuList.class);
		if (menuList == null)
			return;
		
		List<List<String>> menuListContents = exportExcelStringTable(resource, menuList);
		contents.put("菜单目录", menuListContents);
		
		List<List<String>> functionListContents = exportFunctionListTable(resource, menuList);
		contents.put("功能目录", functionListContents);
		
		List<List<String>> functionProxyListContents = exportFunctionProxyListTable(resource, menuList);
		contents.put("菜单功能对照目录", functionProxyListContents);
	}
	
	private static List< List<String> > exportExcelStringTable(IARESResource element, MenuList menuList) {
		List<List<String>> result = new ArrayList<List<String>>();
		// 首先构建标题列
		List<String> header = new ArrayList<String>();
		// 标题名和属性助手的映射
		Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
		String[] titles = new String[]{"菜单标题", "菜单号", "页面", "说明"};
		header.addAll(Arrays.asList(titles));
		EStructuralFeature[] features = new EStructuralFeature[] {
											MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME,
											MetadataPackage.Literals.NAMED_ELEMENT__NAME,
											MetadataPackage.Literals.MENU_ITEM__URL,
											MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION
										};
		
		for (int i = 0; i < titles.length; i++) {
			helperMap.put(titles[i], new NormalAttributeHelper(features[i]));
		}
		
		IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(element, MetadataPackage.Literals.MENU_ITEM);
		for (IExtensibleModelEditingSupport support : supports) {
			for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(element, MetadataPackage.Literals.MENU_ITEM)) {
				if (!desc.isDerived()) {
					header.add(desc.getDisplayName());
					if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
					} else {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
					}
				}
				
			}
		}
		
		// 根据实际标题名，排序属性助手，有可能存在空列
		List<IAttributeHelper> helperList = new ArrayList<IAttributeHelper>();
		for (String title : header) {
			helperList.add(helperMap.get(title));
		}
		
		result.add(header);
		
		// 其次构建内容
		List<MenuItem> items = menuList.getItems();
		result.addAll(generateContents(items, 0, helperList));
		
		return result;
	}
	
	private static String[] getMenuTitles() {
		return new String[]{"菜单标题", "菜单号", "页面", "说明"};
	}
	
	private static EStructuralFeature[] getMenuFeatures() {
		EStructuralFeature[] features = new EStructuralFeature[] {
				MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				MetadataPackage.Literals.MENU_ITEM__URL,
				MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION
			};
		return features;
	}
	
	private static List<List<String>> generateContents(List<MenuItem> items, int depth, List<IAttributeHelper> helperList) {
		List<List<String>> result = new ArrayList<List<String>>();
		
		for (MenuItem item : items) {
			// 先生成这个条目的
			List<String> content = new ArrayList<String>();
			for (IAttributeHelper helper : helperList) {
				String value = helper.getValue(item);
				// 名字前面附加空格表示层级
				if (helper instanceof NormalAttributeHelper) {
					NormalAttributeHelper nHelper = (NormalAttributeHelper) helper;
					if (nHelper.feature == MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME) {
						value = getPrefix(depth) + value;
					}
				}
				content.add(value);
			}
			result.add(content);
			
			// 条目可能还有子项:
			result.addAll(generateContents(item.getSubItems(), depth + 1, helperList));
		}
		
		return result;
	}
	
	// 根据层级加空格前缀，每层加2个空格
	private static String getPrefix(int depth) {
		String prefix = StringUtils.EMPTY;
		for (int i = 0; i < depth; i++) {
			prefix += "  ";
		}
		return prefix;
	}
	
	private static int getDepth(String str) {
		String realStr = StringUtils.trim(str);
		int index = StringUtils.indexOf(str, realStr);
		if (index == -1) {
			return 0;
		}
		return index / 2;
	}
	
	/**
	 * 处理导出功能列表
	 * 
	 */
	private static List<List<String>> exportFunctionListTable(IARESResource element, MenuList menuList) {
		return exportFunctionExcelStringTable(menuList,
											MetadataPackage.Literals.MENU_LIST__FUNCTIONS, 
											MetadataPackage.Literals.FUNCTION, 
											element, 
											null);
	}
	
	
	/** 处理导出菜单功能对照列表 */
	private static List<List<String>> exportFunctionProxyListTable(IARESResource element, MenuList menuList) {
		List<List<String>> result = new ArrayList<List<String>>();
		// 首先构建标题列
		List<String> header = new ArrayList<String>();
		// 标题名和属性助手的映射
		Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
		
		String[] titles = getFunctionProxyTitles();
		header.addAll(Arrays.asList(titles));
		
		addExtensionColumnsForFunction(element, new String[0], new String[0], header, helperMap);
		
		// 根据实际标题名，排序属性助手，有可能存在空列
		List<IAttributeHelper> helperList = new ArrayList<IAttributeHelper>();
		for (String title : header) {
			helperList.add(helperMap.get(title));
		}
		
		result.add(header);
		generateFunctionProxyStringTable(result, 0, menuList.getItems(), element, header, helperMap);
		return result;
	}
	
	private static void generateFunctionProxyStringTable(List<List<String>> result, int depth, List<MenuItem> menus, IARESResource resource, List<String> header, Map<String, IAttributeHelper> helperMap) {
		for (MenuItem menu : menus) {
			List<FunctionProxy> contentObjectList = menu.getFunctionProxys();
			boolean firstTime = true; // 对每个菜单，	仅在第一次添加菜单信息，后面只输出功能号
			if (contentObjectList == null || contentObjectList.isEmpty()) {
				List<String> content = new ArrayList<String>();
				content.addAll(Arrays.asList(getPrefix(depth) + menu.getName(), "", "", ""));
				if (header.size() > 4) {
					for (int i = 4; i < header.size(); i++) {
						content.add(StringUtils.EMPTY);
					}
				}
				result.add(content);
			} else {
				for (FunctionProxy proxy : contentObjectList) {
					List<String> content = new ArrayList<String>();
					// 前面几列固定写死
					if (firstTime) {
						content.add(getPrefix(depth) + menu.getName());
						firstTime = false;
					} else {
						content.add("");
					}
					
					content.add(proxy.getFunCode());
					content.add(MenuUtils.getFunctionByName(resource, proxy.getFunCode()).getChineseName());
					content.add(proxy.getDescription());
					
					// 后面处理扩展列，如果有的话...
					if (header.size() > 4) {
						for (int i = 4; i < header.size(); i++) {
							// 前面填入的Helper只能处理Function
							IAttributeHelper helper = helperMap.get(header.get(i));
							Function function = MenuUtils.getFunctionByName(resource, proxy.getFunCode());
							content.add(helper.getValue(function));
						}
					}
					
					result.add(content);
				}
			}
			generateFunctionProxyStringTable(result, depth + 1, menu.getSubItems(), resource, header, helperMap);
		}
	}
	
	private static String[] getFunctionProxyTitles() {
		List<String> titles = new ArrayList<String>();
		if(MenuUtils.isStockDepartment()){
			titles.add("菜单号");
			titles.add("功能编号");
		} else {
			titles.add("交易码");
			titles.add("子交易码");
		}
		titles.addAll(Arrays.asList("功能名称", "备注"));
		return titles.toArray(new String[0]);
	}
	
	private static EStructuralFeature[] getFunctionProxyFeatures() {
		// 这里前面两列和功能名称先随便填充一个属性，后面再填充正确的属性
		return new EStructuralFeature[] {
				MetadataPackage.Literals.FUNCTION_PROXY__FUN_CODE,
				MetadataPackage.Literals.FUNCTION_PROXY__FUN_CODE,
				MetadataPackage.Literals.FUNCTION_PROXY__FUN_CODE,
				MetadataPackage.Literals.FUNCTION_PROXY__DESCRIPTION
		};
	}
	
	public static List< List<String> > exportFunctionExcelStringTable(EObject owner, EReference reference, EClass itemClass, IARESElement element, IHeaderSorter sorter) {
		List<List<String>> result = new ArrayList<List<String>>();

		// 首先构建标题列
		List<String> header = new ArrayList<String>();
		// 标题名和属性助手的映射
		Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
		
		String[] titles = getFunctionTitles();
		EStructuralFeature[] features = getFunctionFeatures();
		
		header.addAll(Arrays.asList(titles));
		for (int i = 0; i < titles.length; i++) {
			helperMap.put(titles[i], new NormalAttributeHelper(features[i]));
		}
		
		addExtensionColumnsForFunction(element, new String[0], new String[0], header, helperMap);
		
		if (sorter != null) {
			sorter.sort(header);
		}
		
		// 根据实际标题名，排序属性助手，有可能存在空列
		List<IAttributeHelper> helperList = new ArrayList<IAttributeHelper>();
		for (String title : header) {
			helperList.add(helperMap.get(title));
		}
		
		result.add(header);
		
		// 其次构建内容
		List<EObject> contentObjectList = (List<EObject>) owner.eGet(reference);
		for (EObject eObject : contentObjectList) {
			List<String> content = new ArrayList<String>();
			for (IAttributeHelper helper : helperList) {
				content.add(helper.getValue(eObject));
			}
			
			result.add(content);
		}
		
		return result;
	}
	
	private static String[] getFunctionTitles() {
		List<String> titles = new ArrayList<String>();
		if(!MenuUtils.isStockDepartment()){
			titles.add("子交易码");
			titles.add("服务");
		}
		titles.addAll(Arrays.asList("功能编号", "功能名称", "备注"));
		return titles.toArray(new String[0]);
	}
	
	private static EStructuralFeature[] getFunctionFeatures() {
		List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();
		if(!MenuUtils.isStockDepartment()){
			features.add(MetadataPackage.Literals.FUNCTION__SUB_TRANS_CODE);
			features.add(MetadataPackage.Literals.FUNCTION__SERV_ID);
		}
		features.addAll(Arrays.asList(MetadataPackage.Literals.NAMED_ELEMENT__NAME, 
										MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME, 
										MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION));
		return features.toArray(new EStructuralFeature[0]);
	}
	

	
	private static void addExtensionColumnsForFunction(IARESElement element, String[] titles2, String[] dataKeys, List<String> header, Map<String, IAttributeHelper> helperMap) {
		for (int i = 0; i < titles2.length; i++) {
			header.add(titles2[i]); 
			helperMap.put(titles2[i], new ExtensibleDataAttributeHelper(dataKeys[i]));
		}
		
		IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(element, MetadataPackage.Literals.FUNCTION);
		for (IExtensibleModelEditingSupport support : supports) {
			for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(element, MetadataPackage.Literals.FUNCTION)) {
				if (!desc.isDerived()) {
					header.add(desc.getDisplayName());
					if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
					} else {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
					}
				}
				
			}
		}
	}
	
}
