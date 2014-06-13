/**
 * 源程序名称：SequenceAssistantProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.assist;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author yanwj06282
 *
 */
public class IARESResourceAssistantProvider implements IAssistantProvider {

	private IARESProject project;
	private String resType;
	
	public IARESResourceAssistantProvider(IARESProject project , String refType) {
		this.project = project;
		this.resType = refType;
	}
	
	@Override
	public Object[] getProposals() {
		List<Object> inputItems = new ArrayList<Object>();
		ReferenceManager manager = ReferenceManager.getInstance();
		List<ReferenceInfo> refList = manager.getReferenceInfos(project, resType, true);
		for (ReferenceInfo inputItem : refList) {
				IARESResource res = inputItem.getResource();
				if (res != null) {
					inputItems.add(res);
				}
		}
		return inputItems.toArray();
	}

	@Override
	public String getContent(Object obj) {
		if (obj instanceof IARESResource) {
			return ((IARESResource) obj).getFullyQualifiedName();
		}
		return null;
	}

	@Override
	public String getDescription(Object obj) {
		return null;
	}

	@Override
	public String getLabel(Object obj) {
		return getContent(obj);
	}

}
