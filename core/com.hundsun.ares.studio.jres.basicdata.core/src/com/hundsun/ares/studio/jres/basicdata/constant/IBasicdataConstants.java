/**
 * 
 */
package com.hundsun.ares.studio.jres.basicdata.constant;

/**
 * @author yanwj06282
 *
 */
public interface IBasicdataConstants {

	public String BASICDATA_ROOT_TYPE = "com.hundsun.ares.studio.jres.moduleroot.commondata";
	
	/**用户扩展属性*/
	public String BASICDATA_EXTEND_KEY = "user";
	
	/**项目属性中设置基础数据脚本路径的key*/
	public static final String BASICDATA_SCRIPT_DIR_ID = "basicdatasciptdir";
	/**脚本中二维表安装模式方法名*/
	public static final String GEN_SINGLE_INSTALL_FUNCTION = "genSingleTableSQL";
	/**脚本中主从表安装模式方法名*/
	public static final String GEN_MASTER_SLAVE_INSTALL_FUNCTION = "genMasterSlaveTableSQL";
	/**脚本中主从关联表安装模式方法名*/
	public static final String GEN_MASTER_SLAVE_LINK_INSTALL_FUNCTION = "genMasterSlaveLinkTableSQL";
	/**脚本中二维表升级模式方法名*/
	public static final String GEN_SINGLE_UPDATE_FUNCTION = "genSingleTablePatchSQL";
	/**脚本中主从表升级模式方法名*/
	public static final String GEN_MASTER_SLAVE_UPDATE_FUNCTION = "genMasterSlaveTablePatchSQL";
	/**脚本中主从关联表升级模式方法名*/
	public static final String GEN_MASTER_SLAVE_LINK_UPDATE_FUNCTION = "genMasterSlaveLinkTablePatchSQL";
	
}
