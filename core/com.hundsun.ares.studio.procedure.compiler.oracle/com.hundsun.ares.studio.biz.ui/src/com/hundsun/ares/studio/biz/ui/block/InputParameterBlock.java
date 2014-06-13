/**
 * 源程序名称：InputParameterBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.ui.block;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreeViewer;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.ColumnViewerHoverToolTip;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * 输入参数编辑Block
 * @author sundl
 *
 */
public class InputParameterBlock extends ParameterViewerBlock{

	/**
	 * @param reference
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public InputParameterBlock(EditingDomain editingDomain, IARESResource resource, IProblemPool problemPool) {
		super(BizPackage.Literals.BIZ_INTERFACE__INPUT_PARAMETERS, editingDomain, resource, problemPool);
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#addToolTipSupport(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}

}
