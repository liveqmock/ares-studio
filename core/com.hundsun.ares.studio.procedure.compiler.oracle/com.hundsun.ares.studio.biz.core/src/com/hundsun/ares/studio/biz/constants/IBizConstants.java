/**
 * 源程序名称：IBizConstants.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.constants;

/**
 * @author sundl
 *
 */
public interface IBizConstants {
	
	String BIZ_ROOT = "com.hundsun.ares.studio.jres.moduleroot.business";
	/**　新旧两个id兼容，所以定义两个 */
	String BIZ_ROOT2="com.hundsun.ares.studio.biz.core.root";
	String OBJ_ROOT = "com.hundsun.ares.studio.jres.obj.root";
	
	
	/**业务对象对象号范围扩展key*/
	String OBJ_ID_RANGE_KEY = "objidrange";
	/**服务接口对象号范围扩展key*/
	String SRV_ID_RANGE_KEY = "siidrange";
	
}
