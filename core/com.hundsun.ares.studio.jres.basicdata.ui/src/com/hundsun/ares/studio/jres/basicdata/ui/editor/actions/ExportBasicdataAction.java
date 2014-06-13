package com.hundsun.ares.studio.jres.basicdata.ui.editor.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPartSite;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataBaseModelImpl;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.InfoTableLocatorHelper;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.MasterSlaveLinkModelEditor;
import com.hundsun.ares.studio.jres.basicdata.ui.wizard.ExportBasicdataUtil;
import com.hundsun.ares.studio.jres.metadata.service.IDictionaryType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ExportMetadataAction;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IAttributeHelper;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;

public class ExportBasicdataAction extends ExportMetadataAction implements IUpdateAction {

	EPackage ePackage;
	String className;
	//主键对应的EAttribute
	List<EAttribute> masterKeyAttrs = new ArrayList<EAttribute>();
	//主键对应的中文名
	List<String> masterKeyTitles = new ArrayList<String>();
	
	
	
	
	/**
	 * @param ePackage the ePackage to set
	 */
	public void setePackage(EPackage ePackage) {
		this.ePackage = ePackage;
		EClass masterEclass = (EClass)ePackage.getEClassifier(className);
		String[] masterKeys = BasicDataEpackageUtil.getMasterKeyAnnotation(masterEclass);//主表主键
		
		masterKeyAttrs.clear();
		masterKeyTitles.clear();
		for(String key : masterKeys){
			EAttribute attr = BasicDataEpackageUtil.getEAttribute(masterEclass, key);
			if (attr == null) {
				continue;
			}
			masterKeyAttrs.add(attr);
			masterKeyTitles.add(BasicDataEpackageUtil.getAttrColumnName(getResource(),attr));
		}
	}


	public ExportBasicdataAction(IARESResource resource, IWorkbenchPartSite site,EPackage ePackage,String className,String dialogTitle,Image dialogImage,String dialogMessage) {
		super(resource, site,dialogTitle,dialogImage,dialogMessage);
		this.className = className;
		setePackage(ePackage);
	}


	@Override
	protected MetadataResourceData getResourceInfo() throws ARESModelException {
		if (StringUtils.equals(IBasicDataRestypes.STDModelAndData, resource.getType())) {
			StandardFieldModelAndData list = resource.getInfo(StandardFieldModelAndData.class);
			if (list != null) {
				return list.getData();
			}
		}else {
			BasicDataBaseModel list = resource.getInfo(BasicDataBaseModel.class);
			if (list != null) {
				return list;
			}
		}
		return null;
	}
	
	@Override
	protected void exportOtherMetaDataInfoToExcel(Map<String, List<List<String>>> contents,
			List<List<String>> table, IARESResource resource) throws Exception {
		
		EClass masterEclass = (EClass)ePackage.getEClassifier(className);
		if(StringUtils.equals(resource.getType(), IBasicDataRestypes.STDModelAndData)){
			StandardFieldModelAndData info = resource.getInfo(StandardFieldModelAndData.class);
			BasicDataBaseModelImpl data = (BasicDataBaseModelImpl) info.getData();
			masterEclass = (EClass) data.eClass().getEPackage().getEClassifier(className);
			table = ExportBasicdataUtil.getMasterTableInfo(resource, data, masterEclass, true);
			
			String sheetName = "数据";
			
			if (StringUtils.isNotBlank(info.getData().getChineseName())) {
				sheetName = info.getData().getChineseName();
			}
			contents.put(sheetName, table);
			
			List<List<String>> modelDefineResult = getModelDefine(info);
			contents.put("模型定义", modelDefineResult);
			
		}else{
			BasicDataBaseModelImpl info = resource.getInfo(BasicDataBaseModelImpl.class);//模型
			table = ExportBasicdataUtil.getMasterTableInfo(resource, info, masterEclass,true);
			if(StringUtils.equals(resource.getType(),IBasicDataRestypes.singleTable)){
				String sheetName = "二维表";
				if (info != null) {
					if (StringUtils.isNotBlank(info.getChineseName())) {
						sheetName = info.getChineseName();
					}
					contents.put(sheetName, table);
				}else {
					contents.put(sheetName, table);
				}
			}else if(StringUtils.equals(resource.getType(),IBasicDataRestypes.MasterSlaveTable)){
				contents.put("主表", table);
				// 从表信息
				List<List<String>> slaveInfoResult = getSlaveTableInfo(resource,info, masterEclass);
				contents.put("从表", slaveInfoResult);
				
			}else if(StringUtils.equals(resource.getType(),IBasicDataRestypes.MasterSlaveLinkTable)){
				contents.put("主表", table);
				// 从表信息
				List<List<String>> slaveInfoResult = getSlaveTableInfo(resource,info, masterEclass);
				contents.put("从表", slaveInfoResult);
				// 关联表信息
				List<List<String>> linkInfoResult = getLinkTableInfo(resource,info);
				contents.put("关联表", linkInfoResult);
			}
		}
	}

	/**
	 * @param info
	 * @return
	 * @throws Exception 
	 */
	private List<List<String>> getModelDefine(StandardFieldModelAndData info) throws Exception {
		List<List<String>> list = new ArrayList<List<String>>();
		StandardFieldPackageDefine model = info.getModel();
		String[] titles = new String[]{"字段名","中文名","字段类型","字段说明"};
		list.add(Arrays.asList(titles));
		for(StandardFieldColumn column : model.getFields()){
			List<String> value = new ArrayList<String>();
			String field = column.getStandardField();
			value.add(field);
			IARESProject project = com.hundsun.ares.studio.core.util.ResourcesUtil.getARESProject(getResource().getBundle());
			IMetadataService service = DataServiceManager.getInstance().getService(project, IMetadataService.class);
			if (service != null) {
				String name = column.getStandardField();
				IStandardField item = service.getStandardField(name);
				if(item == null){
					throw new ExportExcelException(String.format("模型定义列表中的标准字段%s不存在，请确定是否已经被删除！", name));
				}
				value.add(item.getChineseName());
				value.add(item.getDataTypeId());
				IDictionaryType dict = item.getDictionaryType();
				StringBuffer text = new StringBuffer();
				if(dict != null && dict instanceof DeDictionaryType){
					for(DeDictionaryItem dictItem : ((DeDictionaryType)dict).getItems()){
						String dictValue = StringUtils.defaultString(dictItem.getValue());
						String chineseName = StringUtils.defaultString(dictItem.getChineseName());
						text.append(dictValue);
						text.append(":");
						text.append(chineseName);
						text.append(" ");
					}
				}
				value.add(text.toString());
			}else{
				value.add(StringUtils.EMPTY);
				value.add(StringUtils.EMPTY);
				value.add(StringUtils.EMPTY);
			}
			list.add(value);
		}
		return list;
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.actions.ExportMetadataAction#getDocTitle()
	 */
	@Override
	protected String getDocTitle() {
		return "基础数据规范文档";
	}
	
	private void getSubCate (MetadataCategory dictCate , Map<MetadataItem, Cate> cateMap){
		for (MetadataItem item : dictCate.getItems()) {
			cateMap.put(item, getCate(dictCate, dictCate));
		}
		if (dictCate.getChildren().size() > 0) {
			for (MetadataCategory mc : dictCate.getChildren()) {
				getSubCate(mc, cateMap);
			}
		}
	}
	
	private Cate getCate(MetadataCategory dictCate,MetadataCategory subCate){
		if ( dictCate.eContainer().eContainer() instanceof MetadataResourceData) {
			//如果是最外层分组，则subCate为空
			if(dictCate.equals(subCate)){
				return new Cate(StringUtils.EMPTY, dictCate.getName());
			}else{
				return new Cate(subCate.getName(), dictCate.getName());
			}
		}else if (dictCate.eContainer() instanceof MetadataCategory){
			return getCate((MetadataCategory)dictCate.eContainer(),subCate);
		}else {
			return new Cate(StringUtils.EMPTY, StringUtils.EMPTY);
		}
	}
	
	
	/**
	 * 获取关联表信息
	 * @param resource 
	 * @param info
	 * @return
	 */
	private List<List<String>> getLinkTableInfo(
			IARESResource resource, BasicDataBaseModelImpl info) {
		List<List<String>> linkInfoResult = new ArrayList<List<String>>();
		EClass linkEclass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.InfoItem);//关联表
		EAttribute[] linkAttrArray = BasicDataEpackageUtil.filterAttr(linkEclass);//关联表属性
//		String[] linkNames = new String[linkAttrArray.length];//名称
		List<String> linkNames = new ArrayList<String>();
		for (int i = 0; i < linkAttrArray.length; i++) {
			linkNames.add(BasicDataEpackageUtil.getAttrColumnName(getResource(),linkAttrArray[i]));
		}
		Map<String, IAttributeHelper> helperMap = ExportBasicdataUtil.getExtendHelpMap(resource, linkEclass);
		//扩展属性
		linkNames.addAll(helperMap.keySet());
		linkInfoResult.add(linkNames);
		
		EClass rootEclass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.ResourceRoot);//root
		EStructuralFeature linkItemsFeature = rootEclass.getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Info_Items);
		List<EObject> links = (List<EObject> )info.eGet(linkItemsFeature);//关联表信息
		for (EObject link : links) {
			List<String> linkInfo = new ArrayList<String>();
			for (EAttribute attr : linkAttrArray) {
				Object obj = link.eGet(attr);
				if(obj == null){
					linkInfo.add("");
				}else{
					linkInfo.add(obj.toString());
				}
			}
			for(Entry<String, IAttributeHelper> entry : helperMap.entrySet()){
				//扩展属性列的值
				linkInfo.add(entry.getValue().getValue(link));
			}
			linkInfoResult.add(linkInfo);
		}
		return linkInfoResult;
	}
	
	/**
	 * 获取从表信息
	 * @param resource 
	 * @param info
	 * @param masterEclass
	 * @return
	 */
	private List<List<String>> getSlaveTableInfo(
			IARESResource resource, BasicDataBaseModelImpl info, EClass masterEclass) {
		List<List<String>> slaveInfoResult = new ArrayList<List<String>>();
		EClass slaveEclass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.SlaveItem);//从表类
		EAttribute[] slaveAttrArray = BasicDataEpackageUtil.filterAttr(slaveEclass);//从表属性
		//从表和主表的关联主键
		List<String> titles = new ArrayList<String>();
		titles.addAll(masterKeyTitles);
		String[] slaveNames = new String[slaveAttrArray.length];//标题
		for (int i = 0; i < slaveAttrArray.length; i++) {
			String name = BasicDataEpackageUtil.getAttrColumnName(getResource(),slaveAttrArray[i]);
			slaveNames[i] = name;
			titles.add(name);
		}
		
		//主表扩展属性
		Map<String, IAttributeHelper> helperMap = ExportBasicdataUtil.getExtendHelpMap(resource, slaveEclass);
		titles.addAll(helperMap.keySet());
		
		Map<String, IAttributeHelper> infoHelperMap = null;
		
		//主从关联表，从表引用关联表的属性
		IEditorPart editor = getSite().getPage().getActiveEditor();
		InfoTableLocatorHelper locator = null;
		List<EAttribute> linkAttrs = new ArrayList<EAttribute>();
		if(editor instanceof MasterSlaveLinkModelEditor){
			locator = ((MasterSlaveLinkModelEditor) editor).getLocator();
			EClass infoEclass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.InfoItem);
			EAttribute[] infoAttrArray = BasicDataEpackageUtil.filterAttr(infoEclass);//infoEclass.getEAllAttributes().toArray(new EAttribute[0]);
			String[] keys = BasicDataEpackageUtil.getMasterKeyAnnotation(infoEclass); 
			for(EAttribute attr : infoAttrArray){
				//主键列去除
				if(!Arrays.asList(keys).contains(attr.getName())){
					linkAttrs.add(attr);
					titles.add(BasicDataEpackageUtil.getAttrColumnName(getResource(),attr));
				}
			}
			//信息表扩展列
			infoHelperMap = ExportBasicdataUtil.getExtendHelpMap(resource, infoEclass);
			titles.addAll(infoHelperMap.keySet());
		}
		
		slaveInfoResult.add(titles);
		
		EStructuralFeature slaveItemsFeature = masterEclass.getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Slave_Items);
		List<EObject> masters = (List<EObject> )info.eGet(MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS);//主表信息
		
		for (EObject obj : masters) {
			List<EObject> slaves = (List<EObject> )obj.eGet(slaveItemsFeature);//从表信息
			for (EObject eObject : slaves) {
				List<String> slaveInfo = new ArrayList<String>();
				//与主表关联的主表条目主键属性值
				for(EAttribute attr : masterKeyAttrs){
					Object object = obj.eGet(attr);
					if(object == null){
						slaveInfo.add("");
					}else{
						slaveInfo.add(object.toString());
					}
				}
				//从表条目本身属性值
				for (EAttribute attr : slaveAttrArray) {
					Object object = eObject.eGet(attr);
					if(object == null){
						slaveInfo.add("");
					}else{
						slaveInfo.add(object.toString());
					}
				}
				for(Entry<String, IAttributeHelper> entry : helperMap.entrySet()){
					//从表扩展属性列的值
					slaveInfo.add(entry.getValue().getValue(eObject));
				}
				if(locator != null){
					//获取从表引用关联表的引用列属性值
					try {
						EObject link = locator.getLinkObject(eObject);
						for(EAttribute attr : linkAttrs){
							Object object = link.eGet(attr);
							if(object == null){
								slaveInfo.add("");
							}else{
								slaveInfo.add(object.toString());
							}
						}
						for(Entry<String, IAttributeHelper> entry : infoHelperMap.entrySet()){
							//信息表的扩展属性列
							slaveInfo.add(entry.getValue().getValue(link));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				slaveInfoResult.add(slaveInfo);
			}
		}
		return slaveInfoResult;
	}
	
	/**
	 * 按分组进行添加
	 * @param cate
	 * @param cateName
	 * @param subCateName
	 * @param cateMap
	 * @param table 
	 * @param attrArray 
	 * @param includeGroup  是否需要有分组信息
	 */
	private void addCateItems(MetadataCategory cate, Map<MetadataItem, Cate> cateMap, List<List<String>> table, EAttribute[] attrArray, boolean includeGroup) {
		EList<MetadataItem> items = cate.getItems();
		for(int index=0; index<items.size(); index++){
			MetadataItem item = items.get(index);
			addMetadataItem(item,cateMap, table, attrArray,index,includeGroup);
		}
		for(MetadataCategory child : cate.getChildren()){
			addCateItems(child, cateMap,table,attrArray,includeGroup);
		}
	}


	/**
	 * 添加条目信息
	 * @param item
	 * @param cateMap
	 * @param table 
	 * @param attrArray 
	 * @param includeGroup 是否需要有分组信息
	 */
	private void addMetadataItem(MetadataItem item, Map<MetadataItem, Cate> cateMap,
			List<List<String>> table, EAttribute[] attrArray,int index, boolean includeGroup) {
		List<String> content = new ArrayList<String>();
		//如果需要添加分组信息，则前两列为分组信息
		if(includeGroup){
			Cate cate = cateMap.get(item);
			//只有是分组下第一条数据加上分组信息
			if(index > 0 || cate == null){
				content.add(StringUtils.EMPTY);
				content.add(StringUtils.EMPTY);
			}else{
				content.add(cate.getCate());
				content.add(cate.getSubCate());
			}
		}
		//添加各属性值，基础数据没扩展属性，所以不用做考虑
		for(EAttribute attr : attrArray){
			Object value = item.eGet(attr);
			if(value == null){
				content.add(StringUtils.EMPTY);
			}else{
				content.add(value.toString());
			}
		}
		table.add(content);
	}


	@Override
	public void update() {

	}
	
	class Cate {
		private String subCate = StringUtils.EMPTY;
		private String cate = StringUtils.EMPTY;
		
		public Cate(String subCate, String cate) {
			this.subCate = subCate;
			this.cate = cate;
		}
		
		public String getSubCate() {
			return subCate;
		}
		public String getCate() {
			return cate;
		}
		
	}
}
