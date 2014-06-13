/**
 * 源程序名称：OutputParameterBlock.java
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
 * 编辑输出参数列表的Block
 * @author sundl
 *
 */
public class OutputParameterBlock extends ParameterViewerBlock {

	/**
	 * @param reference
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public OutputParameterBlock(EditingDomain editingDomain, IARESResource resource, IProblemPool problemPool) {
		super(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_PARAMETERS, editingDomain, resource, problemPool);
	}
	

	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}


}
