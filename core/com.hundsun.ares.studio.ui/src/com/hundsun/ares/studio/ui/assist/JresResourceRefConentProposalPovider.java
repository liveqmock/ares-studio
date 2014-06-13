/**
 * 源程序名称：JresResourceRefConentProposalPovider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.assist;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * Jres中资源引用使用的ContentProposalProvider； 这个实现使用IResStatisticProvider获取提示内容。
 * @author sundl
 */
public abstract class JresResourceRefConentProposalPovider extends AresContentProposalProvider{

	protected String resType;
	protected IARESProject project;
	
	/**
	 * @param helper
	 * @param resType 需要提示的资源类型
	 * @param project
	 */
	public JresResourceRefConentProposalPovider(IContentProposalProviderHelper helper, String resType, IARESProject project) {
		super(helper);
		this.resType = resType;
		this.project = project;
	}
	
	@Override
	public void updateContent(Object element) {
		List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, resType, true);
		// 因为createProposal()得不到element，所以提示元素的过滤必须在这里就处理掉
		List<Object> inputItems = new ArrayList<Object>();
		if (infoList != null) {
			for (ReferenceInfo inputItem : infoList) {
				if (filter(inputItem, element))
					inputItems.add(inputItem);
			}
		}
		
		setInput(inputItems.toArray());
	}
	
	/**
	 * 指定的inputItem是否应该出现在提示列表中; 
	 * @param inputItem
	 * @param element 一般是表/树中当前选中的那一行
	 * @return
	 */
	protected abstract boolean filter(Object inputItem, Object element);

}
