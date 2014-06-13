/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.modelanddata;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BaseEPackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.IBaiscDataEpackageFactory;

/**
 * @author lvgao
 *
 */
public class StandardFieldModelAndDataEpackageFactory  extends BaseEPackageFactory
                                                                               implements IBaiscDataEpackageFactory{

	public static StandardFieldModelAndDataEpackageFactory instance = new StandardFieldModelAndDataEpackageFactory();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.logic.epackage.IBaiscDataEpackageFactory#createEPackage(com.hundsun.ares.studio.core.IARESProject, com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine)
	 */
	@Override
	public EPackage createEPackage(IARESProject project, PackageDefine define)
			throws Exception {
		if(define instanceof StandardFieldPackageDefine){
			StandardFieldPackageDefine sdefine = (StandardFieldPackageDefine)define;
			
			EPackage ePackage = theCoreFactory.createEPackage();
			ePackage.setName("StandardFieldModelAndDataPackage");
			ePackage.setNsPrefix("StandardFieldModelAndData");
			ePackage.setNsURI("www.hundsun.com/StandardFieldModelAndData");
			
			EClass rootClass = addResourceRoot(ePackage);
			
			Map<String,BasicDataField> basicDataFields = new LinkedHashMap<String,BasicDataField>();
			
			//获取所有标准字段
			for(StandardFieldColumn item: sdefine.getFields()){
				BasicDataField basicDataField = BasicdataFactory.eINSTANCE.createBasicDataField();
				basicDataField.setName(item.getStandardField());
				basicDataField.setDataType("");
				basicDataField.setColumnType(0);
				basicDataField.setChineseName("");
				basicDataFields.put(item.getStandardField(),basicDataField);
			}
			
			EClass masterClass = addMasterItem(ePackage,
					IBasicDataEpacakgeConstant.MasterItem,
					basicDataFields);
			return ePackage;
		}
		return null;
	}

	
}
