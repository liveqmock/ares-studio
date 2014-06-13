/**
 * 源程序名称：DataStructCreateor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：yanwj06282
 */
package com.hundsun.ares.studio.jres.service.ui.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.biz.ui.excel.BizResExcelStructEntity;
import com.hundsun.ares.studio.biz.ui.excel.ExcelBasicParamStuctEntity;
import com.hundsun.ares.studio.biz.ui.excel.ExcelMenuItemEntity;
import com.hundsun.ares.studio.biz.ui.excel.ExcelMenuSheetStructEntity;
import com.hundsun.ares.studio.biz.ui.excel.ExcelSheetStructEntity;
import com.hundsun.ares.studio.biz.ui.excel.ExportExcelEntity;
import com.hundsun.ares.studio.biz.ui.excel.ParameterItemStructEntity;
import com.hundsun.ares.studio.biz.ui.excel.ParameterStructEntity;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2AttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2MapAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleDataAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IHeaderSorter;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.NormalAttributeHelper;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.service.Service;
import com.hundsun.ares.studio.jres.service.ServicePackage;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IMapExtensibleModelPropertyDescriptor;

/**
 * @author yanwj06282
 *
 */
public class DataStructCreateor {
	
	private static final Logger log = Logger.getLogger(DataStructCreateor.class);
	public static IMetadataService metadataService;
	public static IARESProject project;
	public static boolean devValueStatus;
	public static boolean multStatus;
	public static final String OBJECT_ID = "功能号";
	public static final String VERSION_ID = "版本号";
	public static final String UPDATE_DATE = "更新日期";
	public static final String SERVICE_NAME = "功能名";
	public static final String SERVICE_CHINESE_NAME = "功能中文名";
	public static final String SERVICE_DESCRIPTION = "说明";
	public static final String INPUT_PARAMETER = "输入参数";
	public static final String OUTPUT_PARAMETER = "输出参数";
	public static final String MODIFICATION = "修改记录";
	public static final String INPUT_COLLECTION = "输入结果集";
	public static final String OUTPUT_COLLECTION = "输出结果集";
	
	public static final String OBJ_ID = "对象号";
	public static final String OBJECT_NAME = "对象名";
	public static final String OBJECT_CHINESE_NAME = "对象中文名";
	public static final String OBJECT_PARAMETER = "对象属性";
	
	public static ExportExcelEntity createServiceExcelEntity(Multimap<IARESModule ,IARESResource> resourceMap){
		ExportExcelEntity entity = new ExportExcelEntity();
		List<IARESResource> menuResList = new ArrayList<IARESResource>();
		{
			for (Iterator<IARESModule> iterator = resourceMap.keySet().iterator();iterator.hasNext();) {
				IARESModule key = iterator.next();
				Collection<IARESResource> resed = resourceMap.get(key);
				ExcelSheetStructEntity sheetEntity = new ExcelSheetStructEntity();
				String moduleCName = getChineseFileName("-", key);
				sheetEntity.setCnamePrefix(ExcelStructConstantDefine.SERVICE_PREFIX);
				sheetEntity.setSheetCName(moduleCName);
				sheetEntity.setSheetEName(StringUtils.replace(key.getElementName(), ".", "-"));
				for (IARESResource res : resed) {
					try {
						Service service = res.getInfo(Service.class);
						if (service == null) {
							continue;
						}
						menuResList.add( res);
						String[] str = getModuleMaxVersion(key);
						BizResExcelStructEntity resEntity = new BizResExcelStructEntity();
						resEntity.setHyperlinkKey(res.getFullyQualifiedName()+"."+res.getType());
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(OBJECT_ID,1,1) , new ExcelBasicParamStuctEntity(service.getObjectId(),1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(VERSION_ID,1,1) , new ExcelBasicParamStuctEntity(str[0],1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(UPDATE_DATE,1,1) , new ExcelBasicParamStuctEntity(str[1],1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(SERVICE_NAME,1,1) , new ExcelBasicParamStuctEntity(service.getName(),1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(SERVICE_CHINESE_NAME,1,1) , new ExcelBasicParamStuctEntity(service.getChineseName(),1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity("",1,1) , new ExcelBasicParamStuctEntity("",1,1));
						//另起一行（作为一个block的开始，以便导入时做判断），导出输入输出结果集 TASK #9511 by wangxh
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(INPUT_COLLECTION,1,1) , new ExcelBasicParamStuctEntity(service.getInterface().isInputCollection()?"Y":"N",1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(OUTPUT_COLLECTION,1,1) , new ExcelBasicParamStuctEntity(service.getInterface().isOutputCollection()?"Y":"N",1,1));
//						resEntity.putBasicParams(new ExcelBasicParamStuctEntity("",1,1) , new ExcelBasicParamStuctEntity("",1,1));
						
						Map<ExcelBasicParamStuctEntity, ExcelBasicParamStuctEntity> extendsMap = getExtendsValueMap(res, service, ServicePackage.Literals.SERVICE);
						resEntity.getBasicParams().putAll(extendsMap);
						if (extendsMap.size()%resEntity.getBasicParmasMAXCellLength() != 0) {
							for (int i = 0; i < resEntity.getBasicParmasMAXCellLength() - extendsMap.size()%resEntity.getBasicParmasMAXCellLength(); i++) {
								resEntity.putBasicParams(new ExcelBasicParamStuctEntity("",1,1) , new ExcelBasicParamStuctEntity("",1,1));
							}
						}
						
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(SERVICE_DESCRIPTION,1,2) , new ExcelBasicParamStuctEntity(service.getDescription(),5,2));
						
						List<ParameterItemStructEntity> inputParams = exportExcelStringTable(service.getInterface(), BizPackage.Literals.BIZ_INTERFACE__INPUT_PARAMETERS,BizPackage.Literals.PARAMETER,
								ExcelStructConstantDefine.SERVICE_INPUT_PARAMETER_TITLES, 
								new EStructuralFeature[]{BizPackage.Literals.PARAMETER__FLAGS,BizPackage.Literals.PARAMETER__ID,
								BizPackage.Literals.PARAMETER__NAME ,
								BizPackage.Literals.PARAMETER__TYPE,
								BizPackage.Literals.PARAMETER__MULTIPLICITY,BizPackage.Literals.PARAMETER__DEFAULT_VALUE,BizPackage.Literals.PARAMETER__DESCRIPTION}, 
								true, 
								new String[]{}, new String[]{}, res, null
						);
						
						for (ParameterItemStructEntity item : inputParams) {
							if (StringUtils.contains(item.getItem().get(3), ".")) {
								item.setHyprelinkKey(item.getItem().get(3)+"."+IBizResType.Object);
							}
						}
						
						ParameterStructEntity inputStruct = new ParameterStructEntity(inputParams, new ArrayList<String>());
						List<String> inputaramTtile = new ArrayList<String>();
						if (!devValueStatus) {
							inputaramTtile.add("默认值");//默认导出默认值，在禁用导出默认值选项时，不导出默认值
						}
						if (!multStatus) {
							inputaramTtile.add("关系属性");//默认导出关系属性，在禁用导出关系属性选项时，不导出关系属性
						}
						inputStruct.setFilterTitles(inputaramTtile);
						resEntity.getParameterMaps().put(INPUT_PARAMETER, inputStruct);
						
						List<ParameterItemStructEntity> outParams = exportExcelStringTable(service.getInterface(), BizPackage.Literals.BIZ_INTERFACE__OUTPUT_PARAMETERS,BizPackage.Literals.PARAMETER,
								ExcelStructConstantDefine.SERVICE_OUTPUT_PARAMETER_TITLES, 
								new EStructuralFeature[]{BizPackage.Literals.PARAMETER__FLAGS,BizPackage.Literals.PARAMETER__ID,
								BizPackage.Literals.PARAMETER__NAME ,
								BizPackage.Literals.PARAMETER__TYPE, 
								BizPackage.Literals.PARAMETER__MULTIPLICITY,BizPackage.Literals.PARAMETER__DEFAULT_VALUE ,BizPackage.Literals.PARAMETER__DESCRIPTION}, 
								true, 
								new String[]{}, new String[]{}, res, null
						);
						
						for (ParameterItemStructEntity item : outParams) {
							if (StringUtils.contains(item.getItem().get(3), ".")) {
								item.setHyprelinkKey(item.getItem().get(3)+"."+IBizResType.Object);
							}
						}
						
						ParameterStructEntity outputStruct = new ParameterStructEntity(outParams, new ArrayList<String>());
						List<String> outputparamTtile = new ArrayList<String>();
						if (!devValueStatus) {
							outputparamTtile.add("默认值");//默认导出默认值，在禁用导出默认值选项时，不导出默认值
						}
						if (!multStatus) {
							outputparamTtile.add("关系属性");//默认导出关系属性，在禁用导出关系属性选项时，不导出关系属性
						}
						outputStruct.setFilterTitles(outputparamTtile);
						resEntity.getParameterMaps().put(OUTPUT_PARAMETER, outputStruct);
						
						resEntity.putEndAres(new ExcelBasicParamStuctEntity(MODIFICATION,1,2) , new ExcelBasicParamStuctEntity(buildReviceHistory(service.getHistories()),5,2));
						
						sheetEntity.getEntityList().add(resEntity);
					} catch (ARESModelException e) {
						log.error("模型解析异常：["+res.getElementName()+"]", e);
					}
				}
				entity.getSheetList().add(sheetEntity);
			}
			ExcelMenuSheetStructEntity menuEntity = new ExcelMenuSheetStructEntity();
			menuEntity.setSheetName(ExcelStructConstantDefine.SERIVCE_SHEET_NAME);
			menuEntity.getMenuItems().add(new ExcelMenuItemEntity("", Arrays.asList(ExcelStructConstantDefine.SERVICE_MENU_TITLES)));
			for (IARESResource res : menuResList) {
				ExcelMenuItemEntity itemEntity = new ExcelMenuItemEntity();
				List<String> item = new ArrayList<String>();
				Service service;
				try {
					service = res.getInfo(Service.class);
					if (service == null) {
						continue;
					}
					item.add(StringUtils.replace(res.getModule().getElementName(), ".", "-"));
					item.add(getChineseFileName("-", res.getModule()));
					item.add(service.getObjectId());
					item.add(service.getName());
					item.add(service.getChineseName());
					item.add(service.getDescription());
					itemEntity.setHyprelinkKey(res.getFullyQualifiedName()+"."+res.getType());
					itemEntity.setItem(item);
					menuEntity.getMenuItems().add(itemEntity);
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
			entity.getMenuList().add(menuEntity);
		}
		
		return entity;
	}
	
	public static ExportExcelEntity createObjectExcelEntity(Multimap<IARESModule ,IARESResource> resourceMap){
		
		ExportExcelEntity entity = new ExportExcelEntity();
		List<IARESResource> menuResList = new ArrayList<IARESResource>();
		{
			List<IARESModule> modules = new ArrayList<IARESModule>();
			modules.addAll(resourceMap.keySet());
			Collections.sort(modules, new ModuleNameComp());
			for (IARESModule key : modules) {
				Collection<IARESResource> resed = resourceMap.get(key);
				ExcelSheetStructEntity sheetEntity = new ExcelSheetStructEntity();
				String moduleCName = getChineseFileName("-", key);
				sheetEntity.setCnamePrefix(ExcelStructConstantDefine.OBJECT_PREFIX);
				sheetEntity.setSheetCName(moduleCName);
				sheetEntity.setSheetEName(StringUtils.replace(key.getElementName(), ".", "-"));
				//对象资源，按照对象号排序
				List<IARESResource> reses = sortObjectMap(resed);
				for (IARESResource res : reses) {
					try {
						ARESObject object = res.getInfo(ARESObject.class);
						if (object == null) {
							continue;
						}
						menuResList.add(res);
						String[] str = getModuleMaxVersion(key);
						BizResExcelStructEntity resEntity = new BizResExcelStructEntity();
						resEntity.setHyperlinkKey(res.getFullyQualifiedName()+"."+res.getType());
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(OBJECT_NAME,1,1) , new ExcelBasicParamStuctEntity(object.getName(),1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(OBJECT_CHINESE_NAME,1,1) , new ExcelBasicParamStuctEntity(object.getChineseName(),1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity("",1,1) , new ExcelBasicParamStuctEntity("",1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(OBJ_ID,1,1) , new ExcelBasicParamStuctEntity(object.getObjectId(),1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(VERSION_ID,1,1) , new ExcelBasicParamStuctEntity(str[0],1,1));
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(UPDATE_DATE,1,1) , new ExcelBasicParamStuctEntity(str[1],1,1));
						Map<ExcelBasicParamStuctEntity, ExcelBasicParamStuctEntity> extendsMap = getExtendsValueMap(res, object, BizPackage.Literals.ARES_OBJECT);
						resEntity.getBasicParams().putAll(extendsMap);
						if (extendsMap.size()%resEntity.getBasicParmasMAXCellLength() != 0) {
							for (int i = 0; i < resEntity.getBasicParmasMAXCellLength() - extendsMap.size()%resEntity.getBasicParmasMAXCellLength(); i++) {
								resEntity.putBasicParams(new ExcelBasicParamStuctEntity("",1,1) , new ExcelBasicParamStuctEntity("",1,1));
							}
						}
						resEntity.putBasicParams(new ExcelBasicParamStuctEntity(SERVICE_DESCRIPTION,1,1) , new ExcelBasicParamStuctEntity(object.getDescription(),5,1));
						
						List<ParameterItemStructEntity> objectParams = exportExcelStringTable(object, BizPackage.Literals.ARES_OBJECT__PROPERTIES,BizPackage.Literals.PARAMETER,
								ExcelStructConstantDefine.OBJECT_PARAMETER_Titles, 
								new EStructuralFeature[]{BizPackage.Literals.PARAMETER__FLAGS,BizPackage.Literals.PARAMETER__ID,
								BizPackage.Literals.PARAMETER__NAME ,BizPackage.Literals.PARAMETER__TYPE, 
								BizPackage.Literals.PARAMETER__MULTIPLICITY,BizPackage.Literals.PARAMETER__DEFAULT_VALUE,BizPackage.Literals.PARAMETER__DESCRIPTION}, 
								true, 
								new String[]{}, new String[]{}, res, null
						);
						
						for (ParameterItemStructEntity item : objectParams) {
							if (StringUtils.contains(item.getItem().get(3), ".")) {
								item.setHyprelinkKey(item.getItem().get(3)+"."+IBizResType.Object);
							}
						}
						ParameterStructEntity paramStruct = new ParameterStructEntity(objectParams, new ArrayList<String>());
						List<String> paramTtile = new ArrayList<String>();
						if (!devValueStatus) {//默认导出默认值，禁用选项时，表示不导出
							paramTtile.add("默认值");
						}
						if (!multStatus) {//默认导出关系属性，禁用选项时，表示不导出
							paramTtile.add("关系属性");
						}
						paramStruct.setFilterTitles(paramTtile);
						resEntity.getParameterMaps().put(OUTPUT_PARAMETER, paramStruct);
						
						
						resEntity.putEndAres(new ExcelBasicParamStuctEntity(MODIFICATION,1,2) , new ExcelBasicParamStuctEntity(buildReviceHistory(object.getHistories()),5,2));
						
						sheetEntity.getEntityList().add(resEntity);
					} catch (ARESModelException e) {
						log.error("模型解析异常：["+res.getElementName()+"]", e);
					}
				}
				entity.getSheetList().add(sheetEntity);
			}
			ExcelMenuSheetStructEntity menuEntity = new ExcelMenuSheetStructEntity();
			menuEntity.setSheetName(ExcelStructConstantDefine.OBJECT_SHEET_NAME);
			menuEntity.setHyperlinkIndex(3);
			menuEntity.getMenuItems().add(new ExcelMenuItemEntity("", Arrays.asList(ExcelStructConstantDefine.OBJECT_MENU_TITLES)));
			for (IARESResource res : menuResList) {
				ExcelMenuItemEntity itemEntity = new ExcelMenuItemEntity();
				List<String> item = new ArrayList<String>();
				ARESObject object;
				try {
					object = res.getInfo(ARESObject.class);
					if (object == null) {
						continue;
					}
					item.add(StringUtils.replace(res.getModule().getElementName(), ".", "-"));
					item.add(getChineseFileName("-", res.getModule()));
					item.add(StringUtils.defaultString(object.getObjectId()));
					item.add(object.getName());
					item.add(object.getChineseName());
					item.add(object.getDescription());
					itemEntity.setHyprelinkKey(res.getFullyQualifiedName()+"."+res.getType());
					itemEntity.setItem(item);
					menuEntity.getMenuItems().add(itemEntity);
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
			entity.getMenuList().add(menuEntity);
		}
		
		return entity;
	}
	
	/**
	 * 对象列表，按照对象号从小到大的顺序排序
	 * 
	 */
	private static List<IARESResource> sortObjectMap(Collection<IARESResource> resed){
		List<IARESResource> reses = new ArrayList<IARESResource>();
		reses.addAll(resed);
		Collections.sort(reses, new ObjectIdComp());
		return reses;
	}
	
	private static Map<ExcelBasicParamStuctEntity ,ExcelBasicParamStuctEntity> getExtendsValueMap(IARESResource res ,EObject info , EClass eclass){
		Map<String, Object> helperMap = new HashMap<String, Object>();
		IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(res, eclass);
		for (IExtensibleModelEditingSupport support : supports) {
			for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(res, eclass)) {
				if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
					helperMap.put(desc.getDisplayName(), new ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
				} else {
					helperMap.put(desc.getDisplayName(), new ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
				}
			}
		}
		
		Map<ExcelBasicParamStuctEntity, ExcelBasicParamStuctEntity> result = new HashMap<ExcelBasicParamStuctEntity,ExcelBasicParamStuctEntity>();
		
		for (Entry<String, Object> entry : helperMap.entrySet()) {
			if (entry.getValue() instanceof IAttributeHelper) {
				result.put(new ExcelBasicParamStuctEntity(entry.getKey(), 1, 1), new ExcelBasicParamStuctEntity(((IAttributeHelper)entry.getValue()).getValue(info), 1, 1));
			}else {
				result.put(new ExcelBasicParamStuctEntity(entry.getKey(), 1, 1), new ExcelBasicParamStuctEntity(entry.getValue().toString(), 1, 1));
			}
		}
		
		return result;
	}
	
	public static String getChineseFileName (String separator ,IARESModule module){
		StringBuffer sb = new StringBuffer();
		getModuleChineseName(module, sb ,separator);
		String[] ps = StringUtils.split(sb.toString(), separator);
		StringBuffer sbf = new StringBuffer();
		for (int i = ps.length-1; i > -1 && ps.length > 0; i--) {
			if (StringUtils.isNotBlank(sbf.toString())) {
				sbf.append(separator);
			}
			sbf.append(ps[i]);
		}
		return sbf.toString();
	}
	
	/**
	 * 获取模块中文名
	 * 
	 * @param module 选定模块
	 * @param sb 返回值，中文名
	 * @param separator 模块层级间的分隔符
	 */
	private static void getModuleChineseName (IARESModule module , StringBuffer sb , String separator){
		if (module != null) {
			IARESResource property = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
			if (property != null && property.exists()) {
				try {
					ModuleProperty info = property.getInfo(ModuleProperty.class);
					if (info != null) {
						Object obj = info.getValue(ICommonModel.CNAME);
						if (obj != null) {
							if (StringUtils.isNotBlank(sb.toString())) {
								sb.append(separator);
							}
							sb.append(obj.toString());
						}
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
			getModuleChineseName(module.getParentModule(), sb ,separator);
		}
	}
	
	private static String[] getModuleMaxVersion(IARESModule module){
		String[] str = new String[2];
		RevisionHistory his = RevisionHistoryUtil.getMaxVersionHisInfo(module);
		if (his != null) {
			str[0] = his.getVersion();
			str[1] = his.getModifiedDate();
		}else {
			String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(module.getARESProject());
			if (StringUtils.isBlank(projectVersion)) {
				projectVersion = "1.0.0.1";
			}
			str[0] = projectVersion;
			str[1] = "";
		}
		return str;
	}
	
	/**
	 * 
	 * 获取导出成excel的数据表
	 * 
	 * @param owner
	 * @param reference
	 * @param titles
	 * @param features
	 * @param includeExtend 是否导出扩展列
	 * @param element
	 * @return
	 */
	public static List<ParameterItemStructEntity> exportExcelStringTable(EObject owner, EReference reference, EClass itemClass, String[] titles, EStructuralFeature[] features,
			boolean includeExtend, String[] titles2, String[] dataKeys, IARESElement element, IHeaderSorter sorter) {
		List<ParameterItemStructEntity> result = new ArrayList<ParameterItemStructEntity>();

		// 首先构建标题列
		List<String> header = new ArrayList<String>();
		// 标题名和属性助手的映射
		Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
		
		header.addAll(Arrays.asList(titles));
		for (int i = 0; i < titles.length; i++) {
			helperMap.put(titles[i], new NormalAttributeHelper(features[i]));
		}
		
		if (includeExtend) {
			
			for (int i = 0; i < titles2.length; i++) {
				header.add(titles2[i]);
				helperMap.put(titles2[i], new ExtensibleDataAttributeHelper(dataKeys[i]));
			}
			
			IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(element, itemClass);
			for (IExtensibleModelEditingSupport support : supports) {
				for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(element, itemClass)) {
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
		
		if (sorter != null) {
			sorter.sort(header);
		}
		
		// 根据实际标题名，排序属性助手，有可能存在空列
		List<IAttributeHelper> helperList = new ArrayList<IAttributeHelper>();
		for (String title : header) {
			helperList.add(helperMap.get(title));
		}
		
		result.add(new ParameterItemStructEntity("", header));
		
		// 其次构建内容
		List<EObject> contentObjectList = (List<EObject>) owner.eGet(reference);
		for (EObject eObject : contentObjectList) {
			List<String> content = new ArrayList<String>();
			if (eObject.eGet(BizPackage.Literals.PARAMETER__PARAM_TYPE) == ParamType.STD_FIELD) {
				Object o = eObject.eGet(BizPackage.Literals.PARAMETER__ID);
				if (o instanceof String && StringUtils.isNotBlank(o.toString())) {
					//不管是否有标准字段，标记、Id
					content.add(((Parameter)eObject).getFlags());
					content.add(((Parameter)eObject).getId());
					String comment = ((Parameter)eObject).getComments();
					IStandardField std = metadataService.getStandardField(o.toString());
					if (std != null) {
						content.add(std.getChineseName());//导出标准字段对应的中文名
						content.add(std.getDataTypeId());//导出标准字段对应的业务类型
						if (StringUtils.isBlank(comment)) {
							StringBuffer text = new StringBuffer();
							ReferenceInfo  dictReferenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project,IMetadataRefType.Dict,std.getDictionaryTypeId(),true);
							if(dictReferenceInfo != null){
								DictionaryType objDictionaryType = (DictionaryType) dictReferenceInfo.getObject();
								if(objDictionaryType!=null){
									for(DictionaryItem item : objDictionaryType.getItems()){
										String value = StringUtils.defaultString(item.getValue());
										String chineseName = StringUtils.defaultString(item.getChineseName());
										text.append(value);
										text.append(":");
										text.append(chineseName);
										text.append(" ");
									}
								}
							}
							comment = text.toString() + "\r\n" + std.getDescription();//备注为空时，取标准字段关联字典和说明信息。
						}
					}else{
						content.add("");//就算标准字段不存在，中文名的列还是要加上“”，否则列会对不上
						content.add("");//就算标准字段不存在，业务类型的列还是要加上“”，否则列会对不上
					}
					//不管是否有标准字段，关系属性、默认值、备注都会导出
					content.add(((Parameter)eObject).getMultiplicity().getLiteral());
					content.add(((Parameter)eObject).getDefaultValue());
					content.add(comment);
					for (int i = titles.length; i < helperList.size(); i++) {
						content.add(helperList.get(i).getValue(eObject));
					}
					result.add(new ParameterItemStructEntity("", content));
				}
				continue;
			}else if (eObject.eGet(BizPackage.Literals.PARAMETER__PARAM_TYPE) == ParamType.OBJECT) {
				content.add(((Parameter)eObject).getFlags());
				content.add(((Parameter)eObject).getId());
				String chineseName = "";
				String desc = ((Parameter)eObject).getComments();
				ReferenceInfo objectInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project,IBizRefType.Object,((Parameter)eObject).getType(),true);
				if (objectInfo != null) {
					ARESObject obj = (ARESObject) objectInfo.getObject();
					chineseName = obj.getChineseName();
					if (StringUtils.isBlank(desc)) {
						desc = obj.getDescription();
					}
				}
				
				content.add(chineseName);
				content.add(((Parameter)eObject).getType());
				content.add(((Parameter)eObject).getMultiplicity().getLiteral());
				content.add(((Parameter)eObject).getDefaultValue());
				content.add(desc);
				for (int i = titles.length; i < helperList.size(); i++) {
					content.add(helperList.get(i).getValue(eObject));
				}
				result.add(new ParameterItemStructEntity("", content));
				
				continue;
			}else if(eObject.eGet(BizPackage.Literals.PARAMETER__PARAM_TYPE) == ParamType.NON_STD_FIELD) {
				content.add(((Parameter)eObject).getFlags());
				content.add(((Parameter)eObject).getId());
				content.add(((Parameter)eObject).getName());
				content.add(((Parameter)eObject).getType());
				content.add(((Parameter)eObject).getMultiplicity().getLiteral());
				content.add(((Parameter)eObject).getDefaultValue());
				String comment = ((Parameter)eObject).getComments();
				if (StringUtils.isBlank(comment)) {
					comment = ((Parameter)eObject).getDescription();
				}
				content.add(comment);
				for (int i = titles.length; i < helperList.size(); i++) {
						content.add(helperList.get(i).getValue(eObject));
				}
				result.add(new ParameterItemStructEntity("", content));
				continue;
			}
		}
		
		return result;
	}
	
	/**
	 * 获取数据库表的修订记录
	 * 
	 * @param table
	 * @return
	 */
	private static String buildReviceHistory(List<RevisionHistory> histories) {

		List<List<String>> list = new ArrayList<List<String>>();

		{
			if (histories.size() > 0) {
				List<String> content = new ArrayList<String>();
				content.add("修改版本"+"   ");
				content.add("修改日期"+"   ");
				content.add("修改单"+"        ");
				content.add("申请人"+"   ");
				content.add("负责人"+"   ");
				content.add("修改内容"+"        ");
				content.add("修改原因"+"        ");
				content.add("备注"+"        ");
				list.add(content);
			}
		}

		for (RevisionHistory his : histories) {
			String version = his.getVersion();
			List<String> content = new ArrayList<String>();
			content.add("V" + version+"   ");
			String modifyDate = his.getModifiedDate();
			String newDate = StringUtils.substring(modifyDate, 0, 10).replaceAll("-", "");
			content.add(newDate+"   ");
			content.add(his.getOrderNumber()+"        ");
			content.add(his.getModifiedBy()+"   ");
			content.add(his.getCharger()+"   ");
			content.add(his.getModified()+"        ");
			content.add(his.getModifiedReason()+"        ");
			content.add(StringUtils.defaultString(his.getComment())+"        ");

			list.add(content);
		}

		return genStringTable(list);
	}
	
	private static String genStringTable(List< List<String> > contents) {
		// 首先找出最长的字符串数组长度
		int maxLength = 0;
		for (List<String> content : contents) {
			maxLength = Math.max(maxLength, content.size());
		}
		
		if (maxLength == 0) {
			return StringUtils.EMPTY;
		}
		
		// 首先将所有字符串数组统一长度，多余的长度用空白字符串代替
		// 同时找出每一列必须的长度
		List<List<String>> contents_normalization = new ArrayList<List<String>>();
		int[] widths = new int[maxLength];
		
		for (int i = 0; i < contents.size(); i++) {
			List<String> content = contents.get(i);
			// 如果长度不需要变化，直接使用原始对象，减少时间空间成本
			if (maxLength != content.size()) {
				List<String> newContent = new ArrayList<String>();
				newContent.addAll(content);
				
				for (int j = content.size(); j < maxLength; j++) {
					newContent.add(StringUtils.EMPTY);
				}
				content = newContent;
			}
			
			contents_normalization.add(content);
			
			// 查找长度
			for (int j = 0; j < content.size(); j++) {
				int len = StringUtils.defaultString(content.get(j)).getBytes().length;
				widths[j] = Math.max(len, widths[j]);
			}
		}
		
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < contents_normalization.size(); i++) {
			List<String> content = contents_normalization.get(i);
			for (int j = 0; j < content.size(); j++) {
				// 计算需要补充的空格数量是总数量减去已经占的数量，其中汉字占2个空格长度
				int len = StringUtils.defaultString(content.get(j)).getBytes().length;
				int spaceWidth = widths[j] - len + 1; // 多加一个空格可以分割连续占满的2个单元格
				result.append(StringUtils.defaultString(content.get(j)));
				result.append(StringUtils.repeat(" ", spaceWidth));
				
			}
			result.append("\r\n");
		}
		return result.toString();
	}
	
	static class ModuleNameComp implements Comparator<IARESModule>{

		@Override
		public int compare(IARESModule m1, IARESModule m2) {
			String mn1 = m1.getElementName();
			String mn2 = m2.getElementName();
			
			return StringUtils.split(mn1, ".").length - StringUtils.split(mn2, ".").length;
		}
		
	}
	
	static class ObjectIdComp implements Comparator<IARESResource>{

		@Override
		public int compare(IARESResource o1, IARESResource o2) {
			try {
				ARESObject ao1 = o1.getInfo(ARESObject.class);
				ARESObject ao2 = o2.getInfo(ARESObject.class);
				
				if (ao1 != null && ao2 != null) {
					String obj1 = ao1.getObjectId();
					String obj2 = ao2.getObjectId();
					if (StringUtils.isBlank(obj1) || StringUtils.isBlank(obj2)) {
						return 0;
					}
					return Integer.parseInt(obj1) - Integer.parseInt(obj2);
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
			return 0;
		}
		
	}
	
}
