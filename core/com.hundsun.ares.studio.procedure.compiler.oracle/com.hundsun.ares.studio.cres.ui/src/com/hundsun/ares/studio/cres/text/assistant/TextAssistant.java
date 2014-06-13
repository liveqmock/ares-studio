/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.text.assistant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.text.TextUtil;

/**
 * @author wangxh
 * 智能提示类
 */
public class TextAssistant implements IContentAssistProcessor{
	
	//所有智能提示项加载器
	private List<IAssistantLoader> loaders = new ArrayList<IAssistantLoader>();
	IARESResource resource;
	
	public TextAssistant(IARESResource resource) {
		this.resource = resource;
		createAssistantLoader();
	}
	
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		IDocument document = viewer.getDocument();
		String prefix = TextUtil.getGeneralPrefix(document, offset);

		IRegion pre_Region = TextUtil.getGeneralPrefixRegion(document, offset);

		if (prefix == null || pre_Region == null)
			return null;
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		Map<String, IAssistantLoader> map = new HashMap<String, IAssistantLoader>();
		List<String> assistList = new ArrayList<String>();
		//加载所有提示项
		for(IAssistantLoader loader : loaders){
			for(String proposal :loader.loadAssitant(prefix,document,offset)){
				map.put(proposal, loader);
				assistList.add(proposal);
			}
		}
		//排序
		Collections.sort(assistList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		// 根据输入过滤
		List<String> allproposals = TextUtil.filter(assistList, prefix);
		for (String proposal : allproposals) {
			IAssistantLoader loader = map.get(proposal);
			String replacement = loader.getReplacement(proposal);
			String desc = loader.getDescription(proposal);
			proposals.add(new CompletionProposal(replacement, pre_Region
					.getOffset(), prefix.length(), replacement.length(), null,
					proposal, null, desc));
		}
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[]{'@','[','>'};
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}
	
	/**
	 * 初始化智能提示加载器
	 */
	protected void createAssistantLoader() {
	}
	
	public List<IAssistantLoader> getLoaders() {
		return loaders;
	}
	
	public IARESResource getResource() {
		return resource;
	}

}
