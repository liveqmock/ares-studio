package com.hundsun.ares.studio.jres.basicdata.database.factory;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BaseEPackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.logic.util.JRESResProviderUtil;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

public class SingleEPackageFactory extends BaseEPackageFactory{
	
	public static SingleEPackageFactory eInstance = new SingleEPackageFactory();

	public EPackage create(IARESProject project,PackageDefine define)throws Exception{
		if(define instanceof SingleTable){
			TableResourceData table = JRESResProviderUtil.getResourceModel(project, 
					((SingleTable)define).getMaster(),
					IDatabaseRefType.Table,
					TableResourceData.class);
			Map<String,BasicDataField> basicDataFields = new LinkedHashMap<String,BasicDataField>();
			if(table==null){
				throw new Exception(String.format("表[%s]已经不存在。", ((SingleTable)define).getMaster()));
			}
			for(TableColumn item:table.getColumns()){
				BasicDataField basicDataField = BasicdataFactory.eINSTANCE.createBasicDataField();
				basicDataField.setName(item.getFieldName());
				basicDataField.setDataType(item.getDataType());
				basicDataField.setColumnType(item.getColumnType().getValue());
				basicDataField.setChineseName(item.getChineseName());
				basicDataField.setDefaultValue(item.getDefaultValue());
				basicDataFields.put(item.getFieldName(),basicDataField);
			}
			
			if(0 == basicDataFields.size()){
				throw new Exception(String.format("表中[%s]没有字段。", ((SingleTable)define).getMaster()));
			}
			
			EPackage ePackage = theCoreFactory.createEPackage();
			ePackage.setName("SingleTablePackage");
			ePackage.setNsPrefix("singleTable");
			ePackage.setNsURI("http://www.hundsun.com/singletable");
			
			EClass rootClass = addResourceRoot(ePackage);
			
			EClass masterClass = addMasterItem(ePackage,
					IBasicDataEpacakgeConstant.MasterItem,
					basicDataFields);
			
			BasicDataEpackageUtil.addMasterKeyAnnotation(masterClass, DataBaseEPackageUtil.getMasterKeys(table).toArray(new String[]{}));
			return ePackage;
		}
		return null;
	}
}
