/**
 * 源程序名称：ProjectSettingUtil.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;

/**
 * @author zhuyf
 *
 */
public class ProjectSettingUtil {
	
	static Logger logger = Logger.getLogger(ProjectSettingUtil.class);
	
	public static final String MYSQL = "mysql";
	
	public static final String ORACLE = "oracle";
	
	/**
	 * 
	 * @param project
	 * @return
	 */
	public static String getDatabaseType(IARESProject project){
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
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("项目属性/系统配置/数据表脚本设置信息不符合要求，无法解析！具体原因：" + e.getMessage());
		}
		return databaseType;
	}

}
