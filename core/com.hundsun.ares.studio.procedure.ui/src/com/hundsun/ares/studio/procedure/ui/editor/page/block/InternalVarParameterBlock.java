/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.editor.page.block;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ui.block.ParameterDefineEditingSupportDecorator;
import com.hundsun.ares.studio.biz.ui.block.ParameterViewerBlock;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalHelperWipeOffRepeatStd;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalProvider;
import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.ui.ColumnViewerHoverToolTip;
import com.hundsun.ares.studio.ui.editor.IDiagnosticProvider;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author qinyuan
 *
 */
public class InternalVarParameterBlock extends ParameterViewerBlock{

	/**
	 * @param reference
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public InternalVarParameterBlock(EditingDomain editingDomain, IARESResource resource,
			IProblemPool problemPool) {
		super(ProcdurePackage.Literals.PROCEDURE__INTERNAL_VARIABLES, editingDomain, resource, problemPool);
	}
	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}
	
	protected EClass getParameterEClass(){
		return ProcdurePackage.Literals.INTERNAL_PARAM;
	}

	@Override
	protected void createColumnDefaultValue(IARESProject project,
			TreeViewer viewer, IDiagnosticProvider problemView) {
		// 定义主属性
		EAttribute attribute = BizPackage.Literals.PARAMETER__DEFAULT_VALUE;
		
		// 创建表格列
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setText("默认值");
		column.getColumn().setWidth(100);
		column.getColumn().setMoveable(true);
		
		// 设置标签提供器
		EObjectColumnLabelProvider provider = new ProcedureInterfaceParameterColLabProvider(resource,attribute);
		provider.setDiagnosticProvider(problemView);
		column.setLabelProvider(provider);
		
		// 设置编辑支持
		MetadataContentProposalHelperWipeOffRepeatStd helper = new MetadataContentProposalHelperWipeOffRepeatStd(resource.getARESProject());
		MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.DefValue, resource.getARESProject());
		
		JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
				viewer,
				attribute, 
				proposalProvider);
		es.setDecorator(new ParameterDefineEditingSupportDecorator(project, attribute));
		column.setEditingSupport(es);
	}
	
}
