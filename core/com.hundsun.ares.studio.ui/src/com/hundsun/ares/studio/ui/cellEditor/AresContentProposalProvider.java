/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.cellEditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

/**
 * 一个基于Input的ContentProposalProvider的实现，Input可以通过setInput()方法动态改变;
 * 通过Helper来根据当前输入对ContentProposal进行过滤
 * 
 * 简单地说，Provider实现基于当前上下文的过滤； （相当于根据“在哪里”提供不同的逻辑）
 * 比如，在接口中和在数据库中提示的字段的不同，通过不同的Provider来实现。
 * 
 * Helper则实现把提示对象转化为具体的IContentProposal对象，要把标准字段转化成提示的那个字符串，和要把
 * 一个数据类型转化成提示的字符串，这两种情况则通过不同的Helper来实现。 （相当于根据“提示什么”提供不同的Helper来实现）
 * 
 * 这样不同的Provider和Helper的组合，实现不同的逻辑， 比如
 * 		 “在表字段里” 提示“标准字段”
 * 		“在接口参数里” 提示 “标准字段”
 * 		 ...
 * @author sundl
 */
public class AresContentProposalProvider implements IContentProposalProvider {
	
	protected Object[] input;
	protected IContentProposalProviderHelper helper;
	
	public AresContentProposalProvider() {}
	
	public AresContentProposalProvider(IContentProposalProviderHelper helper) {
		this.helper = helper;
	}
	
	public void setHelper(IContentProposalProviderHelper helper) {
		this.helper = helper;
	}
	
	protected void setInput(Object[] input) {
		this.input = input;
	}
	
	/**
	 * 更新提示内容
	 */
	public void updateContent(Object element) {};

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String, int)
	 */
	@Override
	public IContentProposal[] getProposals(String contents, int position) {
		List<IContentProposal> proposals = new ArrayList<IContentProposal>();
		
		if (input != null) {
			for (Object obj : input) {
				IContentProposal proposal = helper.getProposal(contents, position, obj);
				if (proposal != null) {
					proposals.add(proposal);
				}
			}
		}
		
		Collections.sort(proposals, new AresContentProposalComparator(contents));
		return proposals.toArray(new IContentProposal[0]);
	}
	
}
