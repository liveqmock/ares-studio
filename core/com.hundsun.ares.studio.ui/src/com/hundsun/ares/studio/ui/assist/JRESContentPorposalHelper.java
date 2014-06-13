/**
 * 源程序名称：JRESContentPorposalHelper.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui.contentassist
 * 功能说明：
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.assist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * JRES部分的IContentProposalHelper基类；
 * 这里实现了从Input[]中取数据的逻辑，这样子类可以直接由具体的对象(比如标准字段)构建IContentProposal对象。
 * @author sundl
 */
public abstract class JRESContentPorposalHelper implements IContentProposalProviderHelper {

	@Override
	public IContentProposal getProposal(String contents, int position, Object element) {
			ReferenceInfo referenceInfo = (ReferenceInfo) element;
			return getProposal(contents, position, (EObject)referenceInfo.getObject(), referenceInfo.getResource());
	}

	protected abstract IContentProposal getProposal(String contents, int position, EObject item, IARESResource resource);

}
