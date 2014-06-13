/**
 * 源程序名称：MergePojo.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.model;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;

/**
 * @author yanwj06282
 *
 */
public class MergePojo {
	
	List<StandardField> stdFields = new ArrayList<StandardField>();
	List<BusinessDataType> busTypes = new ArrayList<BusinessDataType>();
	List<String> subSyses = new ArrayList<String>();
	private IARESProject project;
	/**
	 * @return the project
	 */
	public IARESProject getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(IARESProject project) {
		this.project = project;
	}
	/**
	 * @return the stdFields
	 */
	public List<StandardField> getStdFields() {
		return stdFields;
	}
	/**
	 * @return the busTypes
	 */
	public List<BusinessDataType> getBusTypes() {
		return busTypes;
	}
	/**
	 * @return the subSyses
	 */
	public List<String> getSubSyses() {
		return subSyses;
	}
	
}
