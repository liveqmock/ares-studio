/**
 * 源程序名称：ErrorCheckPreference.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：liaogc
 */
package com.hundsun.ares.studio.core.preferences;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.Preferences;

/**
 * 解析错误检查首选项中各项配置
 * @author liaogc
 */
public class ErrorCheckPreferenceHelper {
	private static ErrorCheckPreferenceHelper instance = new ErrorCheckPreferenceHelper();
	private Map<String,Boolean> errorTypeMap = new HashMap<String,Boolean>();
	private Map<String,String> resTypeToErrorMap = new HashMap<String,String>();
	boolean isErrorCheck = false;
	private ErrorCheckPreferenceHelper(){
		resTypeToErrorMap.put("stdfield", ErrorCheckPreferenceConstant.METADATA_CHECK);
	    resTypeToErrorMap.put("datatype", ErrorCheckPreferenceConstant.METADATA_CHECK);
	    resTypeToErrorMap.put("dict", ErrorCheckPreferenceConstant.METADATA_CHECK);
	    resTypeToErrorMap.put("menu", ErrorCheckPreferenceConstant.METADATA_CHECK);
	    resTypeToErrorMap.put("errorno", ErrorCheckPreferenceConstant.METADATA_CHECK);
	    resTypeToErrorMap.put("constant", ErrorCheckPreferenceConstant.METADATA_CHECK);
	    resTypeToErrorMap.put("defaulttype", ErrorCheckPreferenceConstant.METADATA_CHECK);
	    resTypeToErrorMap.put("defaultvalue", ErrorCheckPreferenceConstant.METADATA_CHECK);
	    resTypeToErrorMap.put("idrange", ErrorCheckPreferenceConstant.METADATA_CHECK);
	    resTypeToErrorMap.put("jres_osequence", ErrorCheckPreferenceConstant.DATABASE_CHECK);
	    resTypeToErrorMap.put("table", ErrorCheckPreferenceConstant.DATABASE_CHECK);
	    resTypeToErrorMap.put("view", ErrorCheckPreferenceConstant.DATABASE_CHECK);
	    resTypeToErrorMap.put("dbuser", ErrorCheckPreferenceConstant.DATABASE_CHECK);
	    resTypeToErrorMap.put("dbobject", ErrorCheckPreferenceConstant.DATABASE_CHECK);
	    resTypeToErrorMap.put("singletabledata", ErrorCheckPreferenceConstant.BASIC_DATA_CHECK);
	    resTypeToErrorMap.put("masterslavetabledata", ErrorCheckPreferenceConstant.BASIC_DATA_CHECK);
	    resTypeToErrorMap.put("masterslavelinktabledata", ErrorCheckPreferenceConstant.BASIC_DATA_CHECK);
	    resTypeToErrorMap.put("epackage", ErrorCheckPreferenceConstant.BASIC_DATA_CHECK);
	    resTypeToErrorMap.put("stdmodelanddata", ErrorCheckPreferenceConstant.BASIC_DATA_CHECK);
	    resTypeToErrorMap.put("atomservice", ErrorCheckPreferenceConstant.ATOM_CHECK);
	    resTypeToErrorMap.put("atomfunction", ErrorCheckPreferenceConstant.ATOM_CHECK);
	    resTypeToErrorMap.put("logicservice", ErrorCheckPreferenceConstant.LOGIC_CHECK);
	    resTypeToErrorMap.put("logicfunction", ErrorCheckPreferenceConstant.LOGIC_CHECK);
	    resTypeToErrorMap.put("procedure", ErrorCheckPreferenceConstant.PROCEDURE_CHECK);
	    resTypeToErrorMap.put("service", ErrorCheckPreferenceConstant.SERVICE_CHECK);
	    resTypeToErrorMap.put("object", ErrorCheckPreferenceConstant.OBJECT_CHECK);
	    refresh();
	    
	}
	public static ErrorCheckPreferenceHelper getInstance(){
		return instance;
	}
	public void refresh(){
		IPreferencesService service = Platform.getPreferencesService();
		Preferences root = service.getRootNode();
		Preferences corenstanceNode = root.node(InstanceScope.SCOPE).node("com.hundsun.ares.studio.core");
		if(corenstanceNode!=null){
			 isErrorCheck = corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.ERROR_CHECK,true);
			 errorTypeMap.put(ErrorCheckPreferenceConstant.ERROR_CHECK, corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.ERROR_CHECK,true));
			 errorTypeMap.put(ErrorCheckPreferenceConstant.METADATA_CHECK, corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.METADATA_CHECK,true));
			 errorTypeMap.put(ErrorCheckPreferenceConstant.BASIC_DATA_CHECK, corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.BASIC_DATA_CHECK,true));
			 errorTypeMap.put(ErrorCheckPreferenceConstant.DATABASE_CHECK, corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.DATABASE_CHECK,true));
			 errorTypeMap.put(ErrorCheckPreferenceConstant.SERVICE_CHECK, corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.SERVICE_CHECK,true));
			 errorTypeMap.put(ErrorCheckPreferenceConstant.ATOM_CHECK, corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.ATOM_CHECK,true));
			 errorTypeMap.put(ErrorCheckPreferenceConstant.LOGIC_CHECK, corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.LOGIC_CHECK,true));
			 errorTypeMap.put(ErrorCheckPreferenceConstant.OBJECT_CHECK, corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.OBJECT_CHECK,true));
			 errorTypeMap.put(ErrorCheckPreferenceConstant.PROCEDURE_CHECK, corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.PROCEDURE_CHECK,true));
			 errorTypeMap.put(ErrorCheckPreferenceConstant.RELATION_CHECK, corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.RELATION_CHECK,true));
			if(!isErrorCheck){
				errorTypeMap.put(ErrorCheckPreferenceConstant.METADATA_CHECK, false);
				errorTypeMap.put(ErrorCheckPreferenceConstant.BASIC_DATA_CHECK, false);
				errorTypeMap.put(ErrorCheckPreferenceConstant.DATABASE_CHECK, false);
				errorTypeMap.put(ErrorCheckPreferenceConstant.SERVICE_CHECK, false);
				errorTypeMap.put(ErrorCheckPreferenceConstant.ATOM_CHECK, false);
				errorTypeMap.put(ErrorCheckPreferenceConstant.LOGIC_CHECK, false);
				errorTypeMap.put(ErrorCheckPreferenceConstant.OBJECT_CHECK, false);
				errorTypeMap.put(ErrorCheckPreferenceConstant.PROCEDURE_CHECK, false);
				errorTypeMap.put(ErrorCheckPreferenceConstant.RELATION_CHECK, false);
			}
			
		}

	}
	
	/**
	 * 根据资源类型判断是否要对此资源进行错误检查
	 * @param resType
	 * @return
	 */
	public boolean isErrorCheck(String resType){
		if (!isErrorCheck) {
			return false;
		}
		if(isErrorCheck && resTypeToErrorMap.containsKey(resType) && this.errorTypeMap.containsKey(resTypeToErrorMap.get(resType))){
			return this.errorTypeMap.get(resTypeToErrorMap.get(resType));
		}
		return true;
		
	}
	/**
	 * 是否检查相关资源
	 * @return
	 */
	public boolean isRelationCheck(){
		if (!isErrorCheck) {
			return false;
		}
		if(errorTypeMap.containsKey(ErrorCheckPreferenceConstant.RELATION_CHECK)){
			return errorTypeMap.get(ErrorCheckPreferenceConstant.RELATION_CHECK);
		}
		return true;
	}
	
	
	

}
