/**
 * 源程序名称：SequenceAssistantProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.assist.IAssistantProvider;

/**
 * @author yanwj06282
 *
 */
public class NewBasicDataWizardAssistantProvider implements IAssistantProvider {

	NewBasicDataWizard wizard;
	public NewBasicDataWizardAssistantProvider(NewBasicDataWizard wizard) {
		this.wizard = wizard;
	}
	
	@Override
	public Object[] getProposals() {
		List<Object> inputItems = new ArrayList<Object>();
		ReferenceManager manager = ReferenceManager.getInstance();
		List<ReferenceInfo> refList = manager.getReferenceInfos(wizard.getProject(), wizard.modeDfine.inputType, true);
		for (ReferenceInfo ref : refList) {
			inputItems.add(ref.getResource());
		}
		return inputItems.toArray();
	}

	@Override
	public String getContent(Object obj) {
		if (obj instanceof IARESResource) {
			return ((IARESResource) obj).getName();
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
