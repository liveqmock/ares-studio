/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.model.chouse.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * @author liaogc
 *
 */
public class DataBaseMetadataUtil {
	
	public static String KEY_REAL_TYPE = "type";
	public static String KEY_VALUE = "value";
	public static String KEY_LENGTH = "length";
	public static String KEY_PRECISION = "precision";
	
	/**
	 * 根据业务数据类型名获得默认值,标准数据类型,以及真实数据类型
	 * @param name
	 * @return
	 */
	public static Map<String,String> getInfoByBusinessName( String name,IARESProject project,String language){
		Map<String,String> parameterInfo = new HashMap<String,String>();
		BusinessDataType busType = null;
		try {
			 busType =  MetadataServiceProvider.getMetadataModelByName(project, name, IMetadataRefType.BizType, BusinessDataType.class);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		StandardDataType stdType = null;
		try {
			stdType = MetadataServiceProvider.getMetadataModelByName(project, busType.getStdType(), IMetadataRefType.StdType, StandardDataType.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		TypeDefaultValue typpeDefValue = null;
		if(busType!=null){
			try {
				 typpeDefValue = MetadataServiceProvider.getMetadataModelByName(project, busType.getDefaultValue(), IMetadataRefType.DefValue, TypeDefaultValue.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
			if((stdType != null) && ( typpeDefValue!= null) && ( busType!= null))//标准字段
			{
				String dataType = stdType.getValue(language);
				String defValue = typpeDefValue.getValue(language);
				String  length = StringUtils.defaultIfBlank(busType.getLength(), "0");
				String precision =StringUtils.defaultIfBlank(busType.getPrecision(),"0");
				if(StringUtils.contains(dataType, "$L")){
					dataType = dataType.replace("$L", length);
				}
				if(StringUtils.contains(dataType, "$P")){
					dataType = dataType.replace("$P", precision);
				}
				
				parameterInfo.put(KEY_REAL_TYPE, dataType);
				parameterInfo.put(KEY_VALUE, defValue);
				parameterInfo.put(KEY_LENGTH, length);
				parameterInfo.put(KEY_PRECISION, precision);
			
			}
		
		return parameterInfo;
	}
	/**
	 * 获得数据类型
	 * @param project
	 * @return
	 */
	public static String  getDataBaseType(IARESProject project){
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
		return  databaseType;
	}

}
