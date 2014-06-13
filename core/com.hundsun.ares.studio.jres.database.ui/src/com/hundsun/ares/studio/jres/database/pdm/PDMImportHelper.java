/**
 * 源程序名称：PDMHelper.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMStandardField;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTable;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableColumn;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableField;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableIndex;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableIndexColumn;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMView;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardDataType;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;

/**
 * @author liaogc
 * pdm第一次导入帮助类
 */
public class PDMImportHelper {
	
	public static final String NUMBER_TYPE="NUMBER";
	public static final String NUMERIC_Type = "NUMERIC";
	
	
	/**
	 * 合并相同的字段并为每一个字段产生一个新的唯一的标准字段名
	 * @param tablefieldMap
	 * @return
	 */
	public List<PDMTableField> mergeTableField(Map<String,List<PDMTableField>> tablefieldMap){
		 List<PDMTableField> mergeList = new  ArrayList<PDMTableField>();//全并的字段列表
		 List<String> exclusionNames = new ArrayList<String>();//字段名冲突列表
		 List<String> fieleNames = new ArrayList<String>(tablefieldMap.keySet());//字段名列表
		 Collections.sort(fieleNames,new NameSorter());//排序名字段列表
		 for(String fieldName:fieleNames){
			 List<PDMTableField> fields = tablefieldMap.get(fieldName);
			 if(fields.size()==1){//如果没有相同的列
				 if(!exclusionNames.contains(fieldName)){//如果列名不冲突
					 mergeList.add(fields.get(0));
					 exclusionNames.add(fieldName);
					 fields.get(0).setChanged(true);
				 }else{//冲突取列获取一个唯一的列名
					 String newName = PDMHelper.getUniqueName(fieldName,exclusionNames);
					 fields.get(0).setNewName(newName);
					 mergeList.add(fields.get(0));
					 exclusionNames.add(newName);
					 fields.get(0).setChanged(true);
					 
				 }
				
				
			 }else if(fields.size()>1){//合并相同的字段
				 List<PDMTableField> subUniqueFeilds = new ArrayList<PDMTableField>();//字段名相同,但是中文名或者原始类型不同的字段列表
				 for(int i = 0;i < fields.size();i++){
					 PDMTableField field1 = fields.get(i);
					 if(!subUniqueFeilds.contains(field1)){
						 boolean found = false;
						 for(int j=0;j<subUniqueFeilds.size();j++){
							 PDMTableField field2 = subUniqueFeilds.get(j);
							 if(isTheSameTableField(field1,field2)){
								 found = true;
								 if(!StringUtils.equalsIgnoreCase(field1.getTable(), field2.getTable())){
									 if(!field2.getBeLongTable().contains(field1.getTable())){
										 field2.getBeLongTable().add(field1.getTable());//添加所属表
									 }
									 
								 }
								 break;
							 }
							 
						 }
						 if(!found){
							 subUniqueFeilds.add(field1);
							 
						 }
						
					 }
					
				 }
				 //合后相同字段名但是类型或者中文名不相同的字段列表进行字段名处理
				 for(PDMTableField field:subUniqueFeilds){
					 String name = field.getName();
					 if(!exclusionNames.contains(name)){//如果列名不冲突
						 mergeList.add(field);
						 exclusionNames.add(name);
						 field.setChanged(true);
					 }else{
						 String newName = PDMHelper.getUniqueName(name,exclusionNames);//如果列名冲突,则获取一个唯一的列名
						 field.setNewName(newName);
						 mergeList.add(field);
						 exclusionNames.add(newName);
						 field.setChanged(true);
						 
						 for(String tableName:field.getBeLongTable()){//更新与冲突相同的表的列(关键)
							 for(PDMTableField asociateTableField:fields){
								 if(StringUtils.equals(tableName, asociateTableField.getTable())){
									 //本应该再比较两个字段是否一相的,但是由于不可能在一个pdm中字段名相同的两个字段同时在一张表中,所以为了简单只比较表名就行了
									 if(!asociateTableField.isChanged()){//如果没有改变过
										 asociateTableField.setNewName(newName);
										 asociateTableField.setChanged(true);
									 }
								 }
							 }
						 }
						 
					 }
				 }
			 }
			 
		 }
		
		return mergeList;
		
	}
	/**
	 * 第一阶段合并后的字段再与原来标准字段进行合并
	 * @param mergeList 第一阶段合并后的表字段列表
	 * @param standardFields 原有标准字段列表
	 * @return
	 */
	public List<PDMTableField> mergeTableField(List<PDMTableField> mergeList ,List<StandardField> standardFields,IARESProject project){
		List<PDMTableField> newMergeList = new  ArrayList<PDMTableField>();
		String databaseType = "oracle";
		try {
			databaseType =  project.getProjectProperty().getString("tabledir");
			int _index = -1 ;
			int dotIndex = -1;
			if((_index=StringUtils.lastIndexOf(databaseType,"_" ))>-1  && (dotIndex=StringUtils.lastIndexOf(databaseType,"."))>-1 ){
				databaseType = StringUtils.substring(databaseType,_index+1, dotIndex).toLowerCase();
			}else{
				databaseType = "oracle";
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		 List<String> exclusionNames = new ArrayList<String>();
		 for(StandardField standardField:standardFields){
			 exclusionNames.add(standardField.getName());
		 }
		 for(PDMTableField pdmTableField:mergeList){
			 String name = StringUtils.isNotBlank(pdmTableField.getNewName())?pdmTableField.getNewName():pdmTableField.getName();
			 for(StandardField standardField:standardFields){
				 if(isTheSameStandardField(pdmTableField,standardField,project,databaseType)){//相同
					 break;
				 }else if(StringUtils.equals(name, standardField.getName())){//有冲突的
					
					 String newName = PDMHelper.getUniqueName(name,exclusionNames);
					 pdmTableField.setNewName(newName);//设置新的字段名
					 exclusionNames.add(newName);
					 break;
				 }
			 }
				 newMergeList.add(pdmTableField);
		 }
		 
		 return newMergeList;
		
	}
	
/**
 * 分析TableField,取得该TableField对应的标准字段信息与业标准字段信息
 * @param tableField
 * @param project
 * @return
 */
	public Map<String,String> analyseTableFeild(PDMTableField tableField,IARESProject project,String databaseType){
		Map<String,String> fieldInfo = new HashMap<String,String>();
		IMetadataService metadataService = DataServiceManager.getInstance().getService(project,IMetadataService.class);
		String type ="";//业务类型
		String length ="";//长度
		String precision = "";//精度
		String stdName = "";//标准类型名
		
		boolean find = false;
		//试着查找标准类型(第一次找)
		IStandardDataType standardDataType = findStandardDataTypeByRealType(tableField.getType(),metadataService.getStandardDataTypeList(),databaseType);
		if(standardDataType!=null){
			find = true;
			type = tableField.getType();
			stdName = standardDataType.getName();
		}
		//如果第一次没有找到,进行相应的空错处理把NUMBER(X)转化成NUMBER(X,0)或者NUMERIC(X)转化成NUMERIC(X,0)
		if(StringUtils.isBlank(type)){
			if(StringUtils.indexOf(StringUtils.defaultIfBlank(tableField.getType(), "").toLowerCase(), NUMBER_TYPE.toLowerCase())>-1
					||StringUtils.indexOf(StringUtils.defaultIfBlank(tableField.getType(), "").toLowerCase(), NUMERIC_Type.toLowerCase())>-1){
				String lp = StringUtils.substringBetween(StringUtils.defaultIfBlank(tableField.getType(), ""), "(", ")");
				if(lp!=null){
					if(StringUtils.indexOf(lp,",")== -1){
						int index1 = tableField.getType().indexOf("(");
						String prefix = tableField.getType().substring(0,index1);
						//把NUMBER(X)转化成NUMBER(X,0)或者NUMERIC(X)转化成NUMERIC(X,0)
						String fullType = prefix+"("+lp+",0)";
						standardDataType = findStandardDataTypeByRealType(fullType,metadataService.getStandardDataTypeList(),databaseType);
						if(standardDataType!=null){
							type = fullType;
							find = true;
							//tableField.setType(fullType);
							stdName = standardDataType.getName();
						}
						
					}
				}
			}
		}
		//只有长度的情况
		String lp = StringUtils.substringBetween(StringUtils.defaultIfBlank(tableField.getType(), ""), "(", ")");
		String lp2 = StringUtils.substringBetween(StringUtils.defaultIfBlank(tableField.getType(), ""), "（", "）");
		if(StringUtils.isNotBlank(lp)||StringUtils.isNotBlank(lp2)){
			
			if(StringUtils.isNotBlank(lp) && StringUtils.split(lp, ",").length == 1 || StringUtils.isNotBlank(lp2) && StringUtils.split(lp2, "，").length == 1){//当()中只有一个数字时
				length = lp ;
				if(!find){//试着第一次标准类型查找
					int index1 = tableField.getType().indexOf("(");
					if(index1==-1){
						index1 = tableField.getType().indexOf("（");
					}
					String prefix = tableField.getType().substring(0,index1);
					String fullType = prefix+"($L)";
					standardDataType = findStandardDataTypeByRealType(fullType,metadataService.getStandardDataTypeList(),databaseType);
					if(standardDataType!=null){
						type = fullType;
						find = true;
						stdName = standardDataType.getName();
					}
					if(!find){//加上精度试着第二次标准类型查找
						fullType = prefix+"($L,$P)";
						standardDataType = findStandardDataTypeByRealType(fullType,metadataService.getStandardDataTypeList(),databaseType);
						if(standardDataType!=null){
							type = fullType;
							find = true;
							stdName = standardDataType.getName();
						}
					}
				}
				
			}else if(StringUtils.isNotBlank(lp) && StringUtils.split(lp, ",").length == 2 || StringUtils.isNotBlank(lp2) && StringUtils.split(lp2, "，").length == 2){//当()中有两个数字时试着查询标准数据类型
				String []lplitArray ={""};
				if(StringUtils.isNotBlank(lp)){
					lplitArray = StringUtils.split(lp, ",");
				}
				if(lplitArray.length==0 || lplitArray.length==1){
					lplitArray = StringUtils.split(lp, "，");
				}
				length = lplitArray[0] ;
				precision = lplitArray[1];
				if(!find){
					int index1 = tableField.getType().indexOf("(");
					if(index1==-1){
						index1 = tableField.getType().indexOf("（");
					}
					String prefix = tableField.getType().substring(0,index1);
					String fullType = prefix+"($L,$P)";
					standardDataType = findStandardDataTypeByRealType(fullType,metadataService.getStandardDataTypeList(),databaseType);
					if(standardDataType!=null){
						type = fullType;
						find = true;
						stdName = standardDataType.getName();
					}
				}
				
			}
		}
		
		
		//业务数据类型信息
		fieldInfo.put("BusinessDataType_name", "Hs"+tableField.getNewName());
		fieldInfo.put("BusinessDataType_chinesName", tableField.getChineseName());
		fieldInfo.put("BusinessDataType_stdType", stdName);
		fieldInfo.put("BusinessDataType_length", length);
		fieldInfo.put("BusinessDataType_precision", precision);
		fieldInfo.put("BusinessDataType_comment", tableField.getChineseName());
		fieldInfo.put("BusinessDataType_no_std_name", convertOriginalTypeToBusinessTypeOfNoStd(tableField.getType()));//非标准字段时业务数据类型的名称
		
		//标准字段信息
		fieldInfo.put("StandardFeild_name", tableField.getNewName());
		fieldInfo.put("StandardFeild_chinesName", tableField.getChineseName());
		fieldInfo.put("StandardFeild_type","Hs"+tableField.getNewName());
		fieldInfo.put("StandardFeilde_comment", tableField.getChineseName());
		
		return fieldInfo;
	}
	

	
	/**
	 * 把PDM中的表信息设计到ares中对应的数据库表模型中去
	 * @param pdmTable
	 * @return
	 */
	public TableResourceData createTableResourceData(PDMTable pdmTable,Map<String,List<PDMTableField>> allTableFieldMap,boolean importMode ){
		TableResourceData table = DatabaseFactory.eINSTANCE.createTableResourceData();//新建表资源
		//根据解析的表信息设置表资源中各表信息
		table.setName(pdmTable.getName());//表名
		table.setChineseName(pdmTable.getChineseName());//表中文名
		table.setDescription(pdmTable.getDesc());//表说明信息
		//键约束增加后，导入修改
		TableKey tableKey = null;
		for( PDMTableColumn pdmColumn: pdmTable.getColumns()){//表各列信息
			TableColumn column = DatabaseFactory.eINSTANCE.createTableColumn();//新建表列
			List<PDMTableField> pdmTableFieldList = allTableFieldMap.get(pdmColumn.getFieldName());//获取相同名的各表格中所有的列
			int index =indexTableFieldByColumnName(pdmTableFieldList,pdmTable.getName(),pdmColumn.getFieldName());//根据表名,字段名(原始名)查找在整理过的字段列表中的位置
			if(index>-1){
				PDMTableField pdmTableField = pdmTableFieldList.get(index);//新的表字段信息
				String fieldName = StringUtils.isNotBlank(pdmTableField.getNewName())?pdmTableField.getNewName():pdmTableField.getName();//字段名
				column.setFieldName(fieldName);
				if(!importMode){
					column.setColumnType(ColumnType.STD_FIELD);
					
				}else{
					column.setDataType(pdmTableField.getBusType());
					column.setColumnType(ColumnType.NON_STD_FIELD);
					column.setDefaultValue(pdmColumn.getDefaultValue());
					column.setChineseName(pdmTableField.getChineseName());
				}
				StringBuffer modifyStr = new StringBuffer();//如果修改了字段名则产生一个字段修改记录
				if(StringUtils.isNotBlank(pdmTableField.getNewName())&& !StringUtils.equals(pdmTableField.getNewName(), pdmTableField.getName())){
					modifyStr.append("PDM导入--").append("字段名:").append("\""+pdmTableField.getName()+"\"").append("修改为:").append("\""+pdmTableField.getNewName()+"\"");
				}
				if(StringUtils.isNotBlank(modifyStr.toString())){
					StringBuffer newComments = new StringBuffer();
					newComments.append(modifyStr.toString());//新的备注在最前
					if(StringUtils.isNotBlank(pdmColumn.getComment())){//如果原来有备注
						newComments.append("\r\n");//新旧用连接
						newComments.append(pdmColumn.getComment());//旧的在后
					}
					column.setComments(newComments.toString());
				}else{
					column.setComments(pdmColumn.getComment());
				}
				
			}else{
				column.setFieldName(pdmColumn.getFieldName());
			}
			//设置其他信息
			//column.setPrimaryKey(pdmColumn.isPrimaryKey());//主键
			column.setNullable(pdmColumn.isNullable());//是允许NULL
			column.setDefaultValue(pdmColumn.getDefaultValue());//默认值
			
			
			if(pdmColumn.isPrimaryKey()){
				if(tableKey == null){
					tableKey = DatabaseFactory.eINSTANCE.createTableKey();
					tableKey.setName(pdmTable.getName() + "_pk");
					tableKey.setType(key_type.PRIMARY);
				}
				tableKey.getColumns().add(column);
			}
			
			table.getColumns().add(column);
	
		}
		if(tableKey != null){
			table.getKeys().add(tableKey);
		}
		for(PDMTableIndex pdmTableIndex :pdmTable.getIndexes()){//设置表索引信息
			TableIndex index = DatabaseFactory.eINSTANCE.createTableIndex();
			index.setName(pdmTableIndex.getName());
			index.setUnique(pdmTableIndex.isUnique());
			index.setCluster(pdmTableIndex.isCluster());
			
			for(PDMTableIndexColumn pdmTableIndexColumn :pdmTableIndex.getColumns()){//设置索引列信息
				TableIndexColumn indexCol = DatabaseFactory.eINSTANCE.createTableIndexColumn();
				indexCol.setColumnName(pdmTableIndexColumn.getColumnName());
				index.getColumns().add(indexCol);
			}
			table.getIndexes().add(index);
		}
		
		return table;
	}
	/**
	 * 根据pdmTableField创建PDMStandardField
	 * @param pdmTableField
	 * @param parameters
	 * @return PDMStandardField
	 */
	public PDMStandardField createPDMStandardField(PDMTableField pdmTableField,Map<String,String> parameters){
		 PDMStandardField pdmStandardField = new PDMStandardField();
		 
		 pdmStandardField.setOldName(pdmTableField.getName());
		 pdmStandardField.setOldChineseName(pdmTableField.getChineseName());
		 pdmStandardField.setOldBusType(pdmTableField.getType());
		 pdmStandardField.getBolongSubSystemList().add((pdmTableField.getSubSystem()));
		 pdmStandardField.setGenName(pdmTableField.getNewName());
		 pdmStandardField.setGenBusType(pdmTableField.getBusType());
		 pdmStandardField.setDictId("");
		 pdmStandardField.setOldComment(pdmTableField.getDesc());
		 pdmStandardField.setModefyDesc("");
		 pdmStandardField.setImportPath(StringUtils.defaultIfBlank(parameters.get("subSystem"), ""));//导入路径
		 pdmStandardField.getBelongTableList().addAll(pdmTableField.getBeLongTable());//字段所属表
		 
		 return pdmStandardField;
	}
	
	/**
	 * 把PDM中的视图信息设计到ares中对应的数据库视图模型中
	 * @param pdmView
	 * @return
	 */
	public ViewResourceData createVrewResourceData(PDMView pdmView){
		ViewResourceData view = DatabaseFactory.eINSTANCE.createViewResourceData();
		view.setName(pdmView.getName());
		view.setChineseName(pdmView.getChineseName());
		view.setSql(pdmView.getSql());
		return view;
		
	}
	
	/**
	 * 查找真实数据类型对应的标准类型
	 * @param realType
	 * @param standardDataTypeList
	 * @return
	 */
	private IStandardDataType findStandardDataTypeByRealType(String realType,List<IStandardDataType> standardDataTypeList,String databaseType){
		if(StringUtils.isBlank(databaseType)){
			databaseType = "oracle";
		}
		for(IStandardDataType sdt:standardDataTypeList){
			if(StringUtils.equalsIgnoreCase(realType, sdt.getValue(databaseType))){
				 return sdt;
			}
		}
		return null;
	}
	
	
	/**
	 * 判断两个字段是否相同:字段名,中文名,类型(原始类型)相同则相同
	 * @param field1
	 * @param field2
	 * @return
	 */
	private boolean isTheSameTableField(PDMTableField field1,PDMTableField field2){
		String name = StringUtils.isNotBlank(field1.getNewName())?field1.getNewName():field1.getName();
		return StringUtils.equalsIgnoreCase(name, field2.getName()) && StringUtils.equalsIgnoreCase(field1.getType(), field2.getType()) &&StringUtils.equalsIgnoreCase(field1.getChineseName(), field2.getChineseName()) ;
	}
	
	/**
	 * 判断表段与标准字段是否相同:由于标准字段是由Hs+原始类型,所以比较时要注意
	 * @param field1
	 * @param field2
	 * @return
	 */
	private boolean isTheSameStandardField(PDMTableField field1,StandardField field2,IARESProject project,String databaseType){
		 String name = StringUtils.isNotBlank(field1.getNewName())?field1.getNewName():field1.getName();
		 boolean nameSame = StringUtils.equalsIgnoreCase(field1.getName(), field2.getName())&& StringUtils.equalsIgnoreCase(field1.getChineseName(), field2.getChineseName());
		 boolean stdTypeSame = StringUtils.equalsIgnoreCase("Hs"+name, field2.getDataType())  ;
		 if(!stdTypeSame){//如果Hs+name不相同说明肯定不等
			 return nameSame && stdTypeSame;
		 }else if(nameSame && stdTypeSame ){
			 String realDataType = PDMHelper.getRealDataType(project, field2.getName(),databaseType);
			 if(StringUtils.isNotBlank(realDataType) && StringUtils.isNotBlank(field1.getType())){
				 stdTypeSame = compareRealDataType(field1.getType(),realDataType);//比较真实类型
			 }
			 
			 
		 }
		return  nameSame && stdTypeSame;
	}
	
	/**
	 * 比较两个原始类型是否相同
	 * @param realDataType1
	 * @param realDataType2
	 * @return
	 */
	private boolean compareRealDataType(String realDataType1, String realDataType2){
		  boolean isSame = StringUtils.equalsIgnoreCase(realDataType1, realDataType2);
		  if(isSame){
			  return true;
		  }

			if(StringUtils.indexOf(StringUtils.defaultIfBlank(realDataType1, "").toLowerCase(), NUMBER_TYPE.toLowerCase())>-1
					||StringUtils.indexOf(StringUtils.defaultIfBlank(realDataType1, "").toLowerCase(), NUMERIC_Type.toLowerCase())>-1){
				String lp = StringUtils.substringBetween(StringUtils.defaultIfBlank(realDataType1, ""), "(", ")");
				if(lp!=null){
					if(StringUtils.indexOf(lp,",")== -1){
						int index1 = realDataType1.indexOf("(");
						String prefix = realDataType1.substring(0,index1);
						//把NUMBER(X)转化成NUMBER(X,0)或者NUMERIC(X)转化成NUMERIC(X,0)
						String fullType = prefix+"("+lp+",0)";
						isSame =  StringUtils.equalsIgnoreCase(fullType, realDataType2);
						
					}
				}
			}
		
		  
		return isSame;
	}
	
	/**
	 * 在相同的字段列表中找到相应表对应的相应列,如果不存在相应的列
	 * 则返回-1,否则返回该表的列所在相同的字段列表中的位置
	 * @param pdmTableFieldList
	 * @param columnName
	 * @return
	 */
	private int indexTableFieldByColumnName(List<PDMTableField> pdmTableFieldList,String tableName,String columnName){
		if(pdmTableFieldList==null || pdmTableFieldList.size()==0){
			return -1;
		}
		int i=0;
		for(PDMTableField pdmTableField:pdmTableFieldList){
			//字段名相同,表相同则找到
			if(StringUtils.equals(pdmTableField.getName(), columnName) && StringUtils.equals(pdmTableField.getTable(), tableName)){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	/**
	 * 根据业务数据基本信息创建一个业务数据类型
	 * @param fieldInfo
	 * @return
	 */
	public BusinessDataType createBusinessDataType(Map<String,String> fieldInfo,boolean importMode){
		BusinessDataType businessDataType = MetadataFactory.eINSTANCE.createBusinessDataType();
		//业务数据类型
		if(!importMode){
			businessDataType.setChineseName(fieldInfo.get("BusinessDataType_chinesName"));//非标准字段不需要类型名称
			businessDataType.setName(fieldInfo.get("BusinessDataType_name"));
		}else{
			businessDataType.setName(fieldInfo.get("BusinessDataType_no_std_name"));
		}
		businessDataType.setStdType(fieldInfo.get("BusinessDataType_stdType"));
		if(StringUtils.isNotBlank(fieldInfo.get("BusinessDataType_length"))){
			businessDataType.setLength(fieldInfo.get("BusinessDataType_length"));	
		}
		if(StringUtils.isNotBlank(fieldInfo.get("BusinessDataType_precision"))){
			businessDataType.setPrecision(fieldInfo.get("BusinessDataType_precision"));
		}
		businessDataType.setDescription(fieldInfo.get("BusinessDataType_comment"));
       return businessDataType;
	}

	/**
	 * 根据标准字段基本创建标准字段
	 * @param fieldInfo
	 * @return
	 */
	public StandardField createcreateStandardField(Map<String,String> fieldInfo){
	StandardField standardField = MetadataFactory.eINSTANCE.createStandardField();
	
	//标准字段信息
	standardField.setName(fieldInfo.get("StandardFeild_name"));
	standardField.setChineseName(fieldInfo.get("StandardFeild_chinesName"));
	standardField.setDataType(fieldInfo.get("StandardFeild_type"));
	standardField.setDescription(fieldInfo.get("StandardFeilde_comment"));
	return standardField;
	}
	
	
	/**
	 * 根据原始的数据类型
	 * @param originalType
	 * @return
	 */
	private  String convertOriginalTypeToBusinessTypeOfNoStd(String originalType){
		String businessType = StringUtils.defaultIfBlank(originalType, "").trim();
		if(StringUtils.indexOf(businessType, "(")>-1){//把(转换成_
			businessType = StringUtils.replace(businessType,"(","_");
		}else if(StringUtils.indexOf(businessType, "（")>-1){//中文(
			businessType = StringUtils.replace(businessType,"（","_");
		}
		if(StringUtils.indexOf(businessType, ",")>-1){//把,转换成_
			businessType = StringUtils.replace(businessType,",", "_");
		}else if(StringUtils.indexOf(businessType, "，")>-1){//中文，
			businessType = StringUtils.replace(businessType,"，","_");
		}
		if(StringUtils.indexOf(businessType, ")")>-1){//把)转换成""空
			businessType = StringUtils.replace(businessType,")", "");
		}else if(StringUtils.indexOf(businessType, "）")>-1){//中文）
			businessType = StringUtils.replace(businessType,"）","_");
		}
	
		return businessType;
	}
	
	public static void  main(String[] args){
		/*System.out.println(convertOriginalTypeToBusinessTypeOfNoStd("number(10,0)"));
		System.out.println(convertOriginalTypeToBusinessTypeOfNoStd("number(10)"));
		System.out.println(convertOriginalTypeToBusinessTypeOfNoStd("varchar2(10)"));*/
	}
	

	
}
