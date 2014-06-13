/**
 * 源程序名称：ServiceInterfacePage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.service.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：yanwj06282
 */
package com.hundsun.ares.studio.jres.service.ui.page;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ui.block.InputParameterBlock;
import com.hundsun.ares.studio.biz.ui.block.OutputParameterBlock;
import com.hundsun.ares.studio.biz.ui.block.ParameterDefineEditingSupportDecorator;
import com.hundsun.ares.studio.biz.ui.block.ServInterfaceParameterColLabProvider;
import com.hundsun.ares.studio.biz.ui.editor.page.InterfacePage;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalHelperWipeOffRepeatStd;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalProvider;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author yanwj06282
 *
 */
public class ServiceInterfacePage extends InterfacePage {

	/**
	 * @param interfaceFeature
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ServiceInterfacePage(EStructuralFeature interfaceFeature,
			EMFFormEditor editor, String id, String title) {
		super(interfaceFeature, editor, id, title);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.ui.editor.page.InterfacePage#createInputSection(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	protected Section createInputSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("输入参数");
		
		Composite content = toolkit.createComposite(section);
		GridLayoutFactory.fillDefaults().applyTo(content);
		
		Composite block = toolkit.createComposite(content);
		GridDataFactory.fillDefaults().indent(0, 0).grab(true, true).applyTo(block);
		block.setLayout(new FillLayout());
		
		// 列表
		inputParamBlock = new InputParameterBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool()){
			protected void createColumnDefaultValue(com.hundsun.ares.studio.core.IARESProject project, org.eclipse.jface.viewers.TreeViewer viewer, com.hundsun.ares.studio.ui.editor.IDiagnosticProvider problemView) {

				// 定义主属性
				EAttribute attribute = BizPackage.Literals.PARAMETER__DEFAULT_VALUE;
				
				// 创建表格列
				TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
				column.getColumn().setText("默认值");
				column.getColumn().setWidth(100);
				column.getColumn().setMoveable(true);
				
				// 设置标签提供器
				EObjectColumnLabelProvider provider = new ServInterfaceParameterColLabProvider(resource,attribute);
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
		};
		inputParamBlock.setEditableControl(getEditableControl());
		inputParamBlock.setDataType(getDataType());
		customizeInputParamBlock();
		inputParamBlock.createControl(block, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(inputParamBlock);
		
		addPropertyListener(inputParamBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(inputParamBlock);
		
		section.setClient(content);
		return section;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.ui.editor.page.InterfacePage#createOuputSection(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	protected Section createOuputSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("输出参数");
		
		Composite client = toolkit.createComposite(section);
		GridLayoutFactory.fillDefaults().applyTo(client);
		
		Composite block = toolkit.createComposite(client);
		GridDataFactory.fillDefaults().indent(0, 0).grab(true, true).applyTo(block);
		block.setLayout(new FillLayout());

		outputParamBlock = new OutputParameterBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool()){
			protected void createColumnDefaultValue(com.hundsun.ares.studio.core.IARESProject project, org.eclipse.jface.viewers.TreeViewer viewer, com.hundsun.ares.studio.ui.editor.IDiagnosticProvider problemView) {

				// 定义主属性
				EAttribute attribute = BizPackage.Literals.PARAMETER__DEFAULT_VALUE;
				
				// 创建表格列
				TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
				column.getColumn().setText("默认值");
				column.getColumn().setWidth(100);
				column.getColumn().setMoveable(true);
				
				// 设置标签提供器
				EObjectColumnLabelProvider provider = new ServInterfaceParameterColLabProvider(resource,attribute);
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
		};
		outputParamBlock.setEditableControl(getEditableControl());
		outputParamBlock.setDataType(getDataType());
		customizeOutputParamBlock();
		outputParamBlock.createControl(block, toolkit);
		
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(outputParamBlock);
		
		addPropertyListener(outputParamBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(outputParamBlock);
		
		section.setClient(client);

		return section;		
	}
	
}
