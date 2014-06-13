/**
 * 源程序名称：BasicDataWizardContentProposalProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.basicdata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import java.util.List;

import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * 
 * @author sundl
 *
 */
public class BasicDataWizardContentProposalProvider extends AresContentProposalProvider {

	public BasicDataWizardContentProposalProvider(IContentProposalProviderHelper helper) {
		super(helper);
	}
	
	public void updateContent(Object element) {
		if (element instanceof NewBasicDataWizard) {
			NewBasicDataWizard wizard = (NewBasicDataWizard) element;
			String resType = wizard.modeDfine.inputType;
			ReferenceManager manager = ReferenceManager.getInstance();
			List<ReferenceInfo> refList = manager.getReferenceInfos(wizard.getProject(), resType, true);
			setInput(refList.toArray());
		}
	};
	
}
