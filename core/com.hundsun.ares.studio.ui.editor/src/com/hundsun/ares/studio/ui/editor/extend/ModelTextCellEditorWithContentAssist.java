/**
 * 源程序名称：TextCellEditorWithContentAssist.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider;
import com.hundsun.ares.studio.ui.cellEditor.TextCellEditorWithContentAssist;

/**
 * @author yanwj06282
 *
 */
public class ModelTextCellEditorWithContentAssist extends TextCellEditorWithContentAssist implements IExtensibleModelCellEditor{

	private AresContentProposalProvider proposalProvider;
	
	/**
	 * @param parent
	 */
	public ModelTextCellEditorWithContentAssist(Composite parent,AresContentProposalProvider proposalProvider) {
		super(parent);
		this.proposalProvider = proposalProvider;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelCellEditor#setModel(com.hundsun.ares.studio.jres.model.core.ExtensibleModel)
	 */
	@Override
	public void setModel(ExtensibleModel model) {
		proposalProvider.updateContent(model);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.cellEditor.TextCellEditorWithContentAssist#getProposalProvider()
	 */
	@Override
	public IContentProposalProvider getProposalProvider() {
		return proposalProvider;
	}

}
