package com.hundsun.ares.studio.jres.basicdata.ui.editor.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataItem;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.ui.BasicDataUI;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.STDModelAndDataEditor;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.metadata.ui.dialog.ImportDialog;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2AttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2MapAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IAttributeHelper;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IMapExtensibleModelPropertyDescriptor;

public class ImportBasicdataAction extends ColumnViewerAction {

	EPackage ePackage;
	String className;
	EClass masterEclass;
	//主键对应的EAttribute
	List<EAttribute> masterKeyAttrs = new ArrayList<EAttribute>();
	//主键对应的中文名
	List<String> masterKeyTitles = new ArrayList<String>();
	private IARESResource resource;
	private static final String MATER_KEY_ATTR_SEP = "@&&@";
	
	int importType = 0;
	
	/**
	 * @param ePackage the ePackage to set
	 */
	public void setePackage(EPackage ePackage) {
		this.ePackage = ePackage;
		masterEclass = (EClass)ePackage.getEClassifier(className);
		String[] masterKeys = BasicDataEpackageUtil.getMasterKeyAnnotation(masterEclass);//主表主键
		
		masterKeyAttrs.clear();
		masterKeyTitles.clear();

		for(String key : masterKeys){
			EAttribute attr = BasicDataEpackageUtil.getEAttribute(masterEclass, key);
			if (attr == null) {
				continue;
			}
			masterKeyAttrs.add(attr);
			masterKeyTitles.add(BasicDataEpackageUtil.getAttrColumnName(resource,attr));
		}
	}


	public ImportBasicdataAction(IARESResource resource, TreeViewer viewer,
			EditingDomain editingDomain,EPackage ePackage,String className) {
		super(viewer, editingDomain);
		setText("导入");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/import_wiz.gif"));
		setId(IMetadataActionIDConstant.CV_IMPORT_METADATA);
		this.resource =resource;
		this.className = className;
		setePackage(ePackage);
	}

	@Override
	protected boolean calculateEnabled() {
		MetadataResourceData<MetadataItem> owner = (MetadataResourceData<MetadataItem>) getViewer().getInput();
		if(null == owner)
			return false;
		else 
			return !((TransactionalEditingDomain)getEditingDomain()).isReadOnly(owner.eResource());
	}
	
	@Override
	protected Command createCommand() {
		String dialogTitle = "导入基础数据";
		Image dialogImage = AbstractUIPlugin.imageDescriptorFromPlugin(BasicDataUI.PLUGIN_ID, "icons/full/obj16/BaiscData.png").createImage();
		String dialogMessage = "将已存在基础数据的Excel文件导入到项目中.";
		final ImportDialog dialog = new ImportDialog(((TreeViewer)getViewer()).getTree().getShell(), getNeedCombo(),dialogTitle,dialogImage,dialogMessage){
			@Override
			public boolean validate(String fileText) {
				File file = new File(fileText);
				if (!file.exists()) {
					setErrorMessage("指定文件不存在!");
					importButton.setEnabled(false);
					return false;
				}
				String type = getResorceType(resource);
				boolean status = false;
				try {
					status = checkExcel(type, new FileInputStream(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				if (!status) {
					setErrorMessage("文件格式错误! 请参考  \"导出\"功能文档");
					importButton.setEnabled(false);
					return false;
				}
				filePath = fileText;
				setErrorMessage(null);
				importButton.setEnabled(true);
				return true;
			}
		};
		dialog.open();
		
		if(dialog.getReturnCode() != Window.OK){
			throw new OperationCanceledException();
		}
		
		final String path = dialog.getFilePath();
		
		importType = dialog.getImportType();
		if (importType == ImportDialog.IMPORT_TYPE_COVER) {
			MessageDialog msg = new MessageDialog(((TreeViewer)getViewer()).getTree().getShell(), 
					"提示", null, "导入将删除原有数据，是否继续？", MessageDialog.QUESTION, new String[]{"是","否"}, 0);
			msg.open();
			if(msg.getReturnCode() != Window.OK){
				throw new OperationCanceledException();
			}
		}
		
		RecordingCommand cc = new RecordingCommand((TransactionalEditingDomain)getEditingDomain()) {
			
			@SuppressWarnings("unchecked")
			@Override
			protected void doExecute() {
				
				InputStream excelStream = null;
				List<List<String>> table = null;
				List<EObject> items = null;
				Map< String, List< List<String> > > contents = null;
				{
					try {
						excelStream = new FileInputStream(path);
						MetadataResourceData<BasicdataItem> owner = (MetadataResourceData<BasicdataItem>) getViewer().getInput();
						if(null == owner)
							throw new OperationCanceledException();
						
						//删除原数据
						if (importType == ImportDialog.IMPORT_TYPE_COVER) {
							owner.getItems().clear();
							owner.getRoot().getChildren().clear();
						}
						//导入其他元数据信息，例如导入基础数据信息
						importOtherMetadataInfo(excelStream,contents,table,items,owner,resource);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						IOUtils.closeQuietly(excelStream);
					}
				}
			}
		};
		
		return cc;
	}
	
	/**
	 * @return
	 */
	private boolean getNeedCombo() {
//		if(resource.getType().equals(IBasicDataRestypes.STDModelAndData)){
//			return false;
//		}
		return true;
	}


	protected void importOtherMetadataInfo(InputStream excelStream, 
			Map<String, List<List<String>>> contents,
			List<List<String>> table, List<EObject> items,
			 MetadataResourceData<BasicdataItem> owner, IARESResource resource) throws Exception {
		
		EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(masterEclass);//内容
		String[] names = new String[attrArray.length];//标题
		for (int i = 0; i < attrArray.length; i++) {
			names[i] = BasicDataEpackageUtil.getAttrColumnName(resource,attrArray[i]);
		}
		
		// 二维表
		HSSFWorkbook workBook = new HSSFWorkbook(excelStream);
		if(StringUtils.equals(resource.getType(),IBasicDataRestypes.singleTable)){
			String sheetName = "二维表";
			if (owner != null && StringUtils.isNotBlank(owner.getChineseName())) {
				sheetName = owner.getChineseName();
			}
			contents = POIUtils.getExcelStringForCate(workBook, 
					new String[]{sheetName}, new int[]{1}, new int[]{1});
			
			{
				table = contents.get(sheetName);
				importsingleTableForCate(table, owner, resource,attrArray,names);
			}
			
		}else if(StringUtils.equals(resource.getType(),IBasicDataRestypes.MasterSlaveTable)){
			contents = POIUtils.getExcelStringForCate(workBook, 
					new String[]{"主表","从表"}, new int[]{1,1}, new int[]{1,1});
			table = contents.get("主表");
			importsingleTableForCate( table, owner, resource, attrArray, names);
			table = contents.get("从表");
			importMasterSlaveTable(table, resource, owner,attrArray,names);
		}else if(StringUtils.equals(resource.getType(),IBasicDataRestypes.MasterSlaveLinkTable)){
			contents = POIUtils.getExcelStringForCate(workBook, 
					new String[]{"主表","从表","关联表"}, new int[]{1,1,1}, new int[]{1,1,1});
			table = contents.get("主表");
			importsingleTableForCate( table, owner, resource, attrArray, names);
			table = contents.get("从表");
			importMasterSlaveTable(table, resource, owner,attrArray,names);
			table = contents.get("关联表");
			importLinkTable(table, resource, owner);
		}else if(StringUtils.equals(resource.getType(), IBasicDataRestypes.STDModelAndData)){
			//获取当前编辑器，最好resource,info等信息都直接从编辑器中取得
			EMFFormEditor editor =((EMFFormEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
			String sheetName = "数据";
			if (owner != null && StringUtils.isNotBlank(owner.getChineseName())) {
				sheetName = owner.getChineseName();
			}
			contents = POIUtils.getExcelStringForCate(workBook, 
					new String[]{"模型定义",sheetName}, new int[]{1,1}, new int[]{1,1});
			table = contents.get("模型定义");
			if (table != null) {
				importModelDefine(table, editor);
			}
			
			owner = ((StandardFieldModelAndData) editor.getInfo()).getData();
			resource = editor.getARESResource();
			table = contents.get(sheetName);
			
			masterEclass = (EClass) owner.eClass().getEPackage().getEClassifier(className);
			attrArray = BasicDataEpackageUtil.filterAttr(masterEclass);//内容
			masterKeyAttrs.clear();
			masterKeyTitles.clear();
			masterKeyAttrs.addAll(Arrays.asList(attrArray));
			
			//标题
			for (int i = 0; i < attrArray.length; i++) {
				masterKeyTitles.add(BasicDataEpackageUtil.getAttrColumnName(resource,attrArray[i]));
			}
			
			if (table != null) {
				importsingleTableForCate(table, owner, resource,masterKeyAttrs.toArray(new EAttribute[0]),masterKeyTitles.toArray(new String[0]));
			}
		}
	}
	
	protected void getMetadataItemCategory1(MetadataResourceData ower ,
			List<EObject> items,EAttribute[] attrArray, Map<String, List<List<String>>> cateLineMap) {
		for(Iterator<String> it = cateLineMap.keySet().iterator();it.hasNext();){
			String key = it.next();
			List<List<String>> values = cateLineMap.get(key);
			String[] cates = StringUtils.split(key, "/");
			MetadataCategory mc = ower.getRoot();
			if (StringUtils.isBlank(key)) {
				continue;
			}
			for (String cate : cates) {
				mc = createCate(cate, mc);
			}
			LableA:for (int i = 0; i < values.size(); i++) {
				List<String> line = values.get(i);
				LableB :for (int j = 0; j < items.size(); j++) {
					EObject item = items.get(j);
					boolean status = true;
					for (int k = 0; k < attrArray.length; k++) {
						Object obj = item.eGet(attrArray[k]);
						if (obj == null || !StringUtils.equals(obj.toString(), line.get(k))) {
							status = false;
							continue LableB;
						}
					}
					if (status) {
						mc.getItems().add((MetadataItem) item);
						continue LableA;
					}
				}
			}
		}
	}
	
	/**
	 * 增加分组信息
	 * 
	 * @param ower
	 * @param cates
	 * @param parient 父分组
	 * @return 子分组
	 */
	private MetadataCategory createCate(String cate ,MetadataCategory parient){
		MetadataCategory even = null;
		for (MetadataCategory mc : parient.getChildren()) {
			if (StringUtils.equals(mc.getName(), cate)) {
				even = mc;
				break;
			}
		}
		if (even == null) {
			even = MetadataFactory.eINSTANCE.createMetadataCategory();
			even.setName(cate);
			parient.getChildren().add(even);
		}
		return even;
	}
	
	private Map<String , List<List<String>>> clearMetadataItems(List<List<String>> itemLines){
		Map<String , List<List<String>>> itemcCateStrs = new LinkedHashMap<String, List<List<String>>>();
		String cate = null;
		for(List<String> line : itemLines){
			
			boolean isCate = false;
			for (int i = 0; i < line.size(); i++) {
				if (i == 0) {
					if (StringUtils.isNotBlank(line.get(i))) {
						isCate = true;
					}
				}else {
					//只有第一列有值的列，统一作为标题
					if (line.get(i) != null) {
						isCate = false;
					}
				}
			}
			
			if (isCate) {
				cate = line.get(0);
				continue;
			}
			if (itemcCateStrs.get(cate) == null) {
				itemcCateStrs.put(cate, new ArrayList<List<String>>());
			}
			itemcCateStrs.get(cate).add(line);
		}
		return itemcCateStrs;
	}
	
	/**
	 * @param table
	 * @param editor
	 * @param owner
	 * @throws ARESModelException 
	 */
	private void importModelDefine(List<List<String>> table,EMFFormEditor editor) throws ARESModelException {
		StandardFieldModelAndData info = (StandardFieldModelAndData) editor.getInfo();//resource.getInfo(StandardFieldModelAndData.class);
		StandardFieldPackageDefine model = info.getModel();
		model.getFields().clear();
		List<String> titles = table.get(0);
		int index = titles.indexOf("字段名");
		for(int i=1; i<table.size(); i++){
			String name = table.get(i).get(index);
			StandardFieldColumn column = BasicdataFactory.eINSTANCE.createStandardFieldColumn();
			column.setStandardField(name);
			model.getFields().add(column);
		}
		info.setModel(model);
		((STDModelAndDataEditor)editor).save();
	}


	/***
	 * 导入关联表信息
	 * @param table
	 * @param resource
	 * @param owner
	 */
	private void importLinkTable(List<List<String>> table,
			IARESResource resource, MetadataResourceData<BasicdataItem> owner) {
		EClass slaveEclass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.InfoItem);
		EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(slaveEclass);
		String[] names = new String[attrArray.length];//标题
		for (int i = 0; i < attrArray.length; i++) {
			names[i] = BasicDataEpackageUtil.getAttrColumnName(resource,attrArray[i]);
		}
		List<EObject> items = POIUtils.importExcelStringTable(table, slaveEclass,
				names,attrArray,true, 
			ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY, resource);
		EStructuralFeature reference = owner.eClass().getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Info_Items);
		if(importType == ImportDialog.IMPORT_TYPE_COVER){
			((List<EObject>)owner.eGet(reference)).clear();
		}
		((List<EObject>)owner.eGet(reference)).addAll(items);
		
	}

	/***
	 * 导入从表信息
	 * @param table
	 * @param resource
	 * @param owner
	 * @param attrArray
	 * @param names
	 * @throws Exception
	 */
	private void importMasterSlaveTable(List<List<String>> table,IARESResource resource, 
			MetadataResourceData<BasicdataItem> owner,
			 EAttribute[] attrArray, String[] names) throws Exception {
		EClass slaveEclass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.SlaveItem);//从表类
		EAttribute[] slaveAttrArray = BasicDataEpackageUtil.filterAttr(slaveEclass);//从表属性
		List<String> titles = table.get(0);
		String[] slaveNames = new String[slaveAttrArray.length];//标题
		for (int i = 0; i < slaveAttrArray.length; i++) {
			String name = BasicDataEpackageUtil.getAttrColumnName(resource,slaveAttrArray[i]);
			slaveNames[i] = name;
		}
		
		IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(resource, slaveEclass);
		// 标题名和属性助手的映射
		Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
		for (IExtensibleModelEditingSupport support : supports) {
			for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(resource, slaveEclass)) {
				if (!desc.isDerived()) {
					if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
					} else {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
					}
				}
			}
		}
	
		for(int i = 1; i<table.size(); i++){
			EObject obj = slaveEclass.getEPackage().getEFactoryInstance().create(slaveEclass);
			List<String>values = table.get(i);
			for(int j=0; j<slaveNames.length; j++){
				String title = slaveNames[j];
				int index = titles.indexOf(title);
				obj.eSet(slaveAttrArray[j], values.get(index));
			}
			ExtensibleModelUtils.extend(resource, (ExtensibleModel) obj, false);
			for (Entry<String, IAttributeHelper> entry : helperMap.entrySet()) {
				//扩展属性
				int index = titles.indexOf(entry.getKey());
				if(index > -1){
					IAttributeHelper helper = entry.getValue();
					if (helper != null) {
						helper.setValue(obj, values.get(index));
					}
				}
				
			}
			BasicdataItem parent = getParentItem(owner,titles,values);
			if(parent != null){
				EStructuralFeature slaveItemsFeature = masterEclass.getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Slave_Items);
				((List<EObject> )parent.eGet(slaveItemsFeature)).add(obj);//从表信息
			}
		}
		
	}

	/**
	 * 根据主键值查找从表条目所属的主表条目
	 * @param owner
	 * @param titles
	 * @param values
	 * @return
	 */
	private BasicdataItem getParentItem(
			MetadataResourceData<BasicdataItem> owner, List<String> titles,
			List<String> values) {
		//依次判断主表条目的主键值和从表的对应主键值是否完全相同
		for(BasicdataItem item : owner.getItems()){
			for(int i=0; i<masterKeyTitles.size(); i++){
				String masterKeyTitle = masterKeyTitles.get(i);
				int index = titles.indexOf(masterKeyTitle);
				String value = values.get(index);
				Object obj= item.eGet(masterKeyAttrs.get(i));
				String objValue = "";
				if(obj != null){
					objValue = obj.toString();
				}
				if(!StringUtils.equals(value, objValue)){
					break;
				}else if(i == masterKeyTitles.size()-1){
					return item;
				}
				
			}
		}
		return null;
	}

	/**
	 * 导入主表（二维表）信息,分组方式
	 * @param table
	 * @param items
	 * @param owner
	 * @param resource
	 * @param attrArray
	 * @param names
	 * @throws Exception
	 */
	private List<EObject> importsingleTableForCate(List<List<String>> table,
			 MetadataResourceData<BasicdataItem> owner, 
			 IARESResource resource, EAttribute[] attrArray, String[] names)throws Exception {
		POIUtils.importExcelStringTableForSHClear(table, masterEclass,
						names,attrArray,true, 
					ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY,owner, resource);
		return null;
	}
	
	/**
	 * 导入主表（二维表）信息
	 * @param table
	 * @param items
	 * @param owner
	 * @param resource
	 * @param attrArray
	 * @param names
	 * @throws Exception
	 */
	private void importsingleTable(List<List<String>> table, List<EObject> items,
			 MetadataResourceData<MetadataItem> owner, 
			 IARESResource resource, EAttribute[] attrArray, String[] names)throws Exception {
		items = POIUtils.importExcelStringTable(table, masterEclass,
						names,attrArray,true, 
					ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY, resource);
		getMetadataItemCategory(owner , items , table);
		owner.getItems().addAll((Collection<? extends MetadataItem>) items);
	}
	
	protected String getResorceType(IARESResource resource) {
		if(StringUtils.equals(resource.getType(),IBasicDataRestypes.singleTable)){
			try {
				BasicDataBaseModel singleTable = resource.getInfo(BasicDataBaseModel.class);
				if (singleTable != null) {
					if (StringUtils.isNotBlank(singleTable.getChineseName())) {
						return singleTable.getChineseName();
					}else {
						return "二维表";
					}
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}else if(StringUtils.equals(resource.getType(),IBasicDataRestypes.MasterSlaveTable)){
			return "主表";
		}else if(StringUtils.equals(resource.getType(),IBasicDataRestypes.MasterSlaveLinkTable)){
			return "关联表";
		}else if(StringUtils.equals(resource.getType(), IBasicDataRestypes.STDModelAndData)){
			try {
				StandardFieldModelAndData data = resource.getInfo(StandardFieldModelAndData.class);
				String sheetName = "数据";
				if (data != null) {
					String sn = data.getData().getChineseName();
					if (StringUtils.isNotBlank(sn)) {
						sheetName = sn;
					}
				}
				return sheetName;
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return StringUtils.EMPTY;
	}
	
	protected void getMetadataItemCategory(MetadataResourceData ower ,
			List<EObject> items, List<List<String>> tables) {
		Map<String , MetadataItem> itemMap = new HashMap<String, MetadataItem>();
		List<String>titles = new ArrayList<String>();
		titles.addAll(tables.get(0));
		for (EObject obj : items){
			MetadataItem item = (MetadataItem) obj;
			itemMap.put(getMetadataItemKey(item), item);
		}
		MetadataCategory mc = null;
		MetadataCategory submc = null;
		for (int i=1 ; i<tables.size();i++){
			List<String> table = tables.get(i);
			String cate = table.get(0);
			String subCate = table.get(1);
			if (StringUtils.isNotBlank(cate)) {
				mc = isDupMc(ower.getRoot(), cate);
				if (mc == null) {
					mc = MetadataFactory.eINSTANCE.createMetadataCategory();
					mc.setName(cate);
				}
				submc = null;
				ower.getRoot().getChildren().add(mc);
			}
			if (StringUtils.isNotBlank(subCate)) {
				submc = MetadataFactory.eINSTANCE.createMetadataCategory();
				submc.setName(subCate);
				mc.getChildren().add(submc);
			}
			StringBuffer keyBuffer = new StringBuffer();
			for(int j=0 ; j<masterKeyTitles.size(); j++){
				int index = titles.indexOf(masterKeyTitles.get(j));
				keyBuffer.append(table.get(index));
				if(j<masterKeyTitles.size()-1){
					keyBuffer.append(MATER_KEY_ATTR_SEP);
				}
			}
			if (submc != null) {
				submc.getItems().add(itemMap.get(keyBuffer.toString()));
			}else {
				if (mc != null) {
					mc.getItems().add(itemMap.get(keyBuffer.toString()));
				}
			}
		}
	}
	
	/**
	 * 主键所组成的key,如：主键1@&&@主键2
	 * @param item
	 */
	private String getMetadataItemKey(MetadataItem item){
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<masterKeyAttrs.size(); i++){
			EAttribute attr = masterKeyAttrs.get(i);
			Object obj = item.eGet(attr);
			if(obj == null){
				buffer.append("");
			}else{
				buffer.append(obj.toString());
			}
			if(i<masterKeyAttrs.size()-1){
				buffer.append(MATER_KEY_ATTR_SEP);
			}
		}
		return buffer.toString();
	}
	
	private MetadataCategory isDupMc (MetadataCategory mc ,String type ){
		for (MetadataCategory m : mc.getChildren()) {
			if (StringUtils.equals(m.getName(), type)) {
				return m;
			}
		}
		return null;
	}
	
	private boolean checkExcel (String sheetName , InputStream excelStream){
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(excelStream);
			HSSFSheet sheet = workBook.getSheet(sheetName);
			if (sheet != null) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
