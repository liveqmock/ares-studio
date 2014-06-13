/**
 * 源程序名称：DefaultNamespaceHelper.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author lvgao
 *
 */
public class DefaultNamespaceHelper implements INamespaceHelper{
	
	public static DefaultNamespaceHelper instance = new DefaultNamespaceHelper();

	public static final String SEPRATOR =".";
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.context.INamespaceHelper#getSlaveNamespace(com.hundsun.ares.studio.core.IARESResource, java.lang.String)
	 */
	@Override
	public String getSlaveNamespace(IARESResource master, String referdata) {
		String nsData = "";
		if(referdata.contains(SEPRATOR)){
			String[] tarray = referdata.split("\\.");
			if(tarray.length == 2){
				nsData = tarray[0];
			}
		}
		return StringUtils.defaultIfBlank(nsData, getResourceNamespace(master));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.context.INamespaceHelper#removeNamespace(java.lang.String)
	 */
	@Override
	public String removeNamespace(String referdata) {
		if(null == referdata){
			return StringUtils.EMPTY;
		}
		if(referdata.contains(SEPRATOR)){
			String[] tarray = StringUtils.split(referdata, SEPRATOR);
			if(tarray.length == 2){
				return tarray[1];
			}
		}
		return referdata;
	}
	
	
	public String getResourceNamespace(IARESResource master){
		if(master.getLib() != null){
			return master.getLib().getId();
		}
		return getProjectNamespace(master.getARESProject());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.context.INamespaceHelper#getProjectNamespace(com.hundsun.ares.studio.core.IARESProject)
	 */
	@Override
	public String getProjectNamespace(IARESProject project) {
		try {
			return project.getProjectProperty().getId();
		} catch (Exception e) {
		}
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.context.INamespaceHelper#getSlaveNamespace(java.lang.String, java.lang.String)
	 */
	@Override
	public String getSlaveNamespace(String masternamespace, String referdata) {
		return StringUtils.defaultIfBlank(getNamespace(referdata), masternamespace);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.context.INamespaceHelper#getNamespace(java.lang.String)
	 */
	@Override
	public String getNamespace(String referdata) {
		if (referdata != null) {
			if(referdata.contains(SEPRATOR)){
				String[] tarray = StringUtils.split(referdata, SEPRATOR);
				if(tarray.length == 2){
					return tarray[0];
				}
			}
		}
		return StringUtils.EMPTY;
	}
}
