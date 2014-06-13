/**
 * 源程序名称：Resource.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：dollyn
 */
package com.hundsun.ares.studio.core.excel;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.util.log.Log.Location;

/**
 * @author sundl
 *
 */
public class Resource {
	
	public String name;
	public String type;
	public Object info;
	public Location startLoc;
	public Location endLoc;
	
	public void create(IARESModule module) throws ARESModelException {
		module.createResource(name + "." + type, info);
	}
	
	/**
	 * 获取资源稍详细的描述信息，比如： (对象号)中文名
	 * @return
	 */
	public String getDescription() {
		if (info instanceof BasicResourceInfo) {
			BasicResourceInfo basicInfo = (BasicResourceInfo) info;
			String id = basicInfo.getObjectId();
			String cName = basicInfo.getChineseName();
			if (!StringUtils.isEmpty(id)) {
				return String.format("(%s)%s", id, cName);
			} else {
				return cName;
			}
		}
		return name;
	}
}
