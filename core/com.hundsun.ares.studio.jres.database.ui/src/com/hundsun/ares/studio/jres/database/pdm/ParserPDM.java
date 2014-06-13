/**
 * 源程序名称：ParserPDM2.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMDomainDataType;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMModule;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTable;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableColumn;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableField;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableIndex;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableIndexColumn;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMView;
import com.hundsun.ares.studio.jres.database.ui.wizard.TableImportWizard;
import com.hundsun.ares.studio.jres.database.utils.HanziToPingtin;


/**
 * @author liaogc
 *
 */
public class ParserPDM {
	private  Map<String, PDMDomainDataType> domainDataType =  new  HashMap<String, PDMDomainDataType>();//domain中的信息
	private  List<PDMModule> createModuleList =  new  ArrayList<PDMModule>();// PDM中模块
	
	private  Map<IFolder, List<PDMTable>> tableMap = new  HashMap<IFolder, List<PDMTable>>();//PDM中表格
	private  Map<IFolder, List<PDMView>> viewMap = new  HashMap<IFolder, List<PDMView>>();//PDM中的视图
	
    //private List<PDMTableField>allTableField = new ArrayList<PDMTableField>();//PDM中表格是所有的字段
    private Map<String,List<PDMTableField>>allTableFieldMap = new HashMap<String,List<PDMTableField>>();//PDM中表格是所有的字段
    private Map<String, List<Element>> elementMap;//各种初次解析列表
    private List<String> packagePath = new ArrayList<String>(10);
    private IARESModule iaresModule;
    private IARESProject project;//所在项目
    private String subSystem;
    static final Logger console = ConsoleHelper.getLogger();
	/**
 * @return the tableField
 */
public Map<String,List<PDMTableField>> getAllTableFieldMap() {
	return allTableFieldMap;
}
	/**
	 * @return the tableMap
	 */
	public Map<IFolder, List<PDMTable>> getTableMap() {
		return tableMap;
	}
	
	/**
	 * @return the viewMap
	 */
	public Map<IFolder, List<PDMView>> getViewMap() {
		return viewMap;
	}
	/**
	 * @return the createModule
	 */
	public List<PDMModule> getCreateModules() {
		return createModuleList;
	}
	/**
	 * @return the domainDataType
	 */
	public Map<String, PDMDomainDataType> getDomainDataType() {
		return domainDataType;
	}
	
	public List<String> getPackagePath(){
		return packagePath;
	}
	/**
	 * 
	 * key参考:
	 * @see #GROUP_DOMAIN
	 * @see #GROUP_PACKAGE
	 * @see #GROUP_TABLE
	 * @see #GROUP_VIEW
	 * 
	 * <p>有可能返回的Map中value数据为空,外部需要处理value = null情况</p>
	 * 
	 * @param file PDM文件位置的绝对路径
	 * @param monitor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private  Map<String,List<Element>> pdmRead ( String file ,IProgressMonitor monitor){
		Map<String,List<Element>> nodeMap = new HashMap<String, List<Element>>();
		
		{
			monitor.beginTask("开始导入", 10000);
			SAXReader reader = new SAXReader();
			try {
				monitor.subTask("解析PDM文件");
				
				Document doc = reader.read(new File(file));
				List<Element> typeElementList = doc.selectNodes(PMDConstant.NODE_DOMAINS);
				nodeMap.put(PMDConstant.GROUP_DOMAIN, typeElementList);
				//查找包结构定义,并且创建文件夹目录
				List<Element> packElements = doc.selectNodes(PMDConstant.NODE_PACKAGE);
				nodeMap.put(PMDConstant.GROUP_PACKAGE, packElements);
				if (packElements != null && packElements.size()>0) {
					for(Element element:packElements){
						String packageName = element.elementText(PMDConstant.NODE_CODE);
						List<Element> tableElementList = element.selectNodes(PMDConstant.NODE_TABLE);
						nodeMap.put(PMDConstant.GROUP_TABLE+packageName, tableElementList);
						monitor.worked(2000);
						
						List<Element> viewElementList = element.selectNodes(PMDConstant.NODE_VIEW);
						nodeMap.put(PMDConstant.GROUP_VIEW, viewElementList);
					}
				}
				List<Element> tableElementList = doc.selectNodes(PMDConstant.NODE_NO_PACK_TABLE);
					nodeMap.put(PMDConstant.GROUP_TABLE, tableElementList);
					List<Element> viewElementList = doc.selectNodes(PMDConstant.NODE_VIEW);
					nodeMap.put(PMDConstant.GROUP_VIEW, viewElementList);
				
				monitor.done();

			} catch (Exception e) {
				console.info(e.getMessage());
			}
		}
		return nodeMap;
	}
	public void parse( String file ,IARESModule iaresModule,IProgressMonitor monitor){
		try{
			this.iaresModule = iaresModule;
			project =iaresModule.getARESProject();
			elementMap =pdmRead(file,monitor);
			this.subSystem = PDMHelper.getSubSystem(iaresModule);
			List<Element> domainElementsList = elementMap.get(PMDConstant.GROUP_DOMAIN);
			parseDomainElements(domainElementsList);//解析并初始化domain中的信息
			List<Element> packElementList = elementMap.get(PMDConstant.GROUP_PACKAGE);//
			parserPackElements(packElementList);//解析package信息
			
		}catch(Exception e){
			console.info(e.getMessage());
		}
	}
	/**
	 * 解析domain中类型信息
	 */
	private void parseDomainElements(List<Element> domainElementsList ){
		if (domainElementsList != null) {
			for (Element element : domainElementsList) {
				PDMDomainDataType dmainDataType = new PDMDomainDataType();
				dmainDataType.setTypeName( StringUtils.defaultIfBlank(element.elementText(PMDConstant.NODE_CODE), ""));
				dmainDataType.setTypeDesc( StringUtils.defaultIfBlank(element.elementText(PMDConstant.NODE_NAME), ""));
				dmainDataType.setTypeReal(StringUtils.defaultIfBlank(element.elementText(PMDConstant.NODE_DATATYPE), ""));
				domainDataType.put(StringUtils.defaultIfBlank(element.attributeValue(PMDConstant.ATTRIBUTE_ID), ""),dmainDataType);
			}
		}
		
	}
	/**
	 * 解析package中的信息
	 * @param packElementList
	 * @throws CoreException 
	 */
	private void parserPackElements(List<Element> packElementList ) throws CoreException{
		if (packElementList != null && packElementList.size() > 0) {
			for (Element element : packElementList) {
				String name =element.elementText(PMDConstant.NODE_CODE);
				String pyName=HanziToPingtin.removea_Za_z_(HanziToPingtin.getFirstSpell(name,false));
				String chineseName = element.elementText(PMDConstant.NODE_NAME);
				String createPath =null;
					createPath = iaresModule.getElementName() + "."+ pyName;
			IARESModuleRoot root = getModuleRoot();
			IARESModule createdModule = root.findModule(createPath);
			if (createdModule == null) {
				createdModule = root.createModule(createPath);
			}
			IARESResource resource = createdModule.findResource(IARESModule.MODULE_PROPERTY_FILE);
			if (resource == null) {
				PDMHelper.createModuleProperty(root, pyName, chineseName, createPath);
			}
					
				List<Element> tableElementList = elementMap.get(PMDConstant.GROUP_TABLE+name);
				IFolder key = (IFolder) createdModule.getResource();
				
				parserTableElements(tableElementList,key);//解析table信息
				List<Element> viewElementList = elementMap.get(PMDConstant.GROUP_VIEW);
				parserViewElements(viewElementList, key);//解析view
			}
		}
		
			IFolder key = (IFolder) iaresModule.getResource();
			List<Element> tableElementList = elementMap.get(PMDConstant.GROUP_TABLE);
			parserTableElements(tableElementList,key);//解析table信息
			//List<Element> viewElementList = elementMap.get(PMDConstant.GROUP_VIEW);
			//parserViewElements(viewElementList, key);//解析view
		

	}
	
	/**
	 * 解析table中的信息
	 * @param tableElementList
	 */
	private void parserTableElements(List<Element> tableElementList,IFolder path ){
		if(tableElementList != null && tableElementList.size() > 0){
			for (Element tableElement : tableElementList) {
				PDMTable table = new PDMTable();
				String tn = StringUtils.defaultIfBlank(tableElement.elementText(PMDConstant.NODE_CODE), "");//表英文名
				if (StringUtils.startsWith(tn, "*")) {
					tn = StringUtils.replaceOnce(tn, "*", "");
				}
				table.setName(tn);
				table.setChineseName(StringUtils.defaultIfBlank(tableElement.elementText(PMDConstant.NODE_NAME), ""));
				table.setDesc(StringUtils.defaultIfBlank(tableElement.elementText(PMDConstant.NODE_COMMENT), ""));
				
				List<Element> columnElements = tableElement.selectNodes(PMDConstant.NODE_COLUMN);
				Map<String, PDMTableColumn> columnIdPrimaryKeyMap = new HashMap<String, PDMTableColumn>();
			    Map<String, PDMTableColumn> columnIdIndexMap = new HashMap<String, PDMTableColumn>();
				for (Element columnElement : columnElements) {
					PDMTableColumn column = new PDMTableColumn();
					column.setFieldName(StringUtils.defaultIfBlank(columnElement.elementText(PMDConstant.NODE_CODE), ""));
					column.setPrimaryKey(false);
					column.setNullable("1".equals(columnElement.elementText(PMDConstant.NODE_MANDATORY)) ? false : true);
					column.setDefaultValue(StringUtils.defaultIfBlank(columnElement.elementText(PMDConstant.NODE_DEFAULTVALUE),""));
					column.setComment(StringUtils.defaultIfBlank(columnElement.elementText(PMDConstant.NODE_COMMENT), ""));
					table.getColumns().add(column);
					
					
					PDMTableField tableField = new PDMTableField();
					tableField.setName(StringUtils.defaultIfBlank(columnElement.elementText(PMDConstant.NODE_CODE), ""));
					tableField.setTable(table.getName());
					tableField.setChineseName(StringUtils.defaultIfBlank(columnElement.elementText(PMDConstant.NODE_NAME), ""));
					tableField.setType(StringUtils.defaultIfBlank(columnElement.elementText(PMDConstant.NODE_COLUMN_DATA_TYPE), ""));
					tableField.setDesc(StringUtils.defaultIfBlank(columnElement.elementText(PMDConstant.NODE_COMMENT), ""));
					tableField.setMandatory(columnElement.elementText(PMDConstant.NODE_MANDATORY) == null ? null: columnElement.elementText(PMDConstant.NODE_MANDATORY));
					tableField.setSubSystem(subSystem);
					tableField.getBeLongTable().add(table.getName());
					tableField.setNewName(tableField.getName());//唯一的新的字段名为原来的列名
					
					//allTableField.add(tableField);//添加到用于统计的所有的统计列中
					if(allTableFieldMap.get(tableField.getName())==null){
						List<PDMTableField> list = new ArrayList<PDMTableField>();
						list.add(tableField);
						allTableFieldMap.put(tableField.getName(), list);
						
					}else{
						allTableFieldMap.get(tableField.getName()).add(tableField);
					}
					
					columnIdPrimaryKeyMap.put(columnElement.attributeValue(PMDConstant.ATTRIBUTE_ID),column);
					columnIdIndexMap.put(columnElement.attributeValue(PMDConstant.ATTRIBUTE_ID),column);
				}
				List<Element> keyElements = tableElement.selectNodes(PMDConstant.NODE_PRIMARY_KEY);
				for (Element keyElement : keyElements) {
					PDMTableColumn column = columnIdPrimaryKeyMap.get(keyElement.attributeValue(PMDConstant.ATTRIBUTE_REF));
					if (column != null) {
						column.setPrimaryKey(true);
					}
					columnIdPrimaryKeyMap.remove(keyElement.attributeValue(PMDConstant.ATTRIBUTE_REF));
				}
				
				// 导入索引
				List<Element> indexElements = tableElement.selectNodes(PMDConstant.NODE_INDEX);
				Map<String, PDMTableIndex> indexMap = new HashMap<String, PDMTableIndex>();
				LableA: for (Element indexElement : indexElements) {
					PDMTableIndex index  = new PDMTableIndex();
					index.setName(StringUtils.defaultIfBlank(indexElement.elementText(PMDConstant.NODE_CODE), ""));
					index.setUnique(StringUtils.defaultIfBlank(indexElement.elementText(PMDConstant.NODE_UNIQUE), "0").equals("1"));
					index.setCluster(false);

					List<Element> indexColumnElements = indexElement.selectNodes(PMDConstant.NODE_INDEX_COLUMN);
					for (Element indexColumnElement : indexColumnElements) {
						Element e = (Element) indexColumnElement.selectSingleNode(PMDConstant.NODE_IND_COLUMN);
						if (e != null) {
							PDMTableColumn column = columnIdIndexMap.get(e.attributeValue(PMDConstant.ATTRIBUTE_REF));
							if (column != null) {
								PDMTableIndexColumn indexCol = new PDMTableIndexColumn();
								indexCol.setColumnName(column.getFieldName());
								index.getColumns().add(indexCol);
							} else {
								continue LableA;
							}
						}
					}

					indexMap.put(StringUtils.defaultIfBlank(indexElement.attributeValue(PMDConstant.ATTRIBUTE_ID),"unkown"), index);
					table.getIndexes().add(index);
				}
				
				List<Element> clusterElements = tableElement.selectNodes(PMDConstant.NODE_CLUSTER);
				for (Element clusterElementsElement : clusterElements) {
					String ref = clusterElementsElement.attributeValue(PMDConstant.ATTRIBUTE_REF);
					if (ref != null) {
						PDMTableIndex index = indexMap.get(ref);
				if (index != null) {
					index.setCluster(true);
						}
					}
				}
				
				if (tableMap.get(path) == null) {
					List<PDMTable> tableList = new ArrayList<PDMTable>();
					tableList.add(table);
					tableMap.put(path, tableList);
				} else {
					tableMap.get(path).add(table);
				}
			}
		}

	}
	
	/**
	 * 解析view中的信息
	 * @param viewElementList
	 */
	private void parserViewElements(List<Element> viewElementList,IFolder path ){
		if(viewElementList != null && viewElementList.size() > 0){
			for (Element viewElement : viewElementList) {
				PDMView view = new PDMView();
				view.setName(StringUtil.getStringSafely(viewElement.elementText(PMDConstant.NODE_CODE)));
				view.setChineseName(StringUtil.getStringSafely(viewElement.elementText(PMDConstant.NODE_NAME)));
				view.setSql(StringUtil.getStringSafely(viewElement.elementText(PMDConstant.NODE_VIEW_SQLQUERY)));

				if (viewMap.get(path) == null) {
					List<PDMView> viewList = new ArrayList<PDMView>();
					viewList.add(view);
					viewMap.put(path, viewList);
				} else {
					viewMap.get(viewMap).add(view);
				}
			
			}
		}

	}
	
	private IARESModuleRoot getModuleRoot() {
		IARESModuleRoot[] roots;
		try {
			roots = project.getModuleRoots();
			for (IARESModuleRoot root : roots) {
				String modulePath = root.getPath().lastSegment().toString();
				if (TableImportWizard.DATABASE_MODULE_ROOT.equals(modulePath)) {
					return root;
				}
			}
		} catch (ARESModelException e) {
			console.info(e.getMessage());
		}
		
		return null;

	}
}

