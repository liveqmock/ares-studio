package com.hundsun.ares.studio.jres.metadata.ui.block;

import java.util.EventObject;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ExportMetadataAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ImportIDRangeAction;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.IDExtendLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.IDExtentEditingSupport;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.IDRangeContentProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.IDRangeHeaderLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MenuColumnViewerProblemView;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.IDiagnosticProvider;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

public class IDRangeListViewerBlock extends TreeViewerBlock {

	private EMFFormPage page;
	private IWorkbenchPartSite site;

	public IDRangeListViewerBlock(EMFFormPage page,EditingDomain editingDomain,IWorkbenchPartSite site, IProblemPool problemPool) {
		this.problemPool = problemPool;
		this.page = page;
		this.site = site;
		this.editingDomain = editingDomain;
		this.resource = page.getEditor().getARESResource();
	}
	
	@Override
	protected String getID() {
		return getClass().getName();
	}

	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new IDRangeContentProvider(resource,MetadataPackage.Literals.ID_RANGE_ITEM);
	}

	@Override
	protected void createMenus(IMenuManager menuManager) {
		
	}

	@Override
	protected void createColumns(TreeViewer viewer) {
		EObjectColumnViewerProblemView problemView = new MenuColumnViewerProblemView(viewer);
		
		/**名称*/
		{
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("模块");
			column.getColumn().setWidth(200);
			// 设置标签提供器
			IDRangeHeaderLabelProvider provider = new IDRangeHeaderLabelProvider();
			column.setLabelProvider(provider);
			column.getColumn().setMoveable(true);
		}
		
		createExtensibleModelTreeViewerColumns(getColumnViewer(), resource, 
				MetadataPackage.Literals.ID_RANGE_ITEM, problemView);
		
		problemPool.addView(problemView);

	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		RefreshViewerJob.refresh(getColumnViewer(), null, true);
	}
	
	@Override
	protected void createActions() {
		ImportIDRangeAction importAction = new ImportIDRangeAction(resource, getColumnViewer(),page.getEditor().getInfo() ,editingDomain);
		getActionRegistry().registerAction(importAction);
		getSelectionActions().add(importAction.getId());
		String dialogTitle = "导出对象号范围";
		String dialogMessage = "将项目中的对象号范围导出(Excel文件).";
		Image dialogImage = AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/IDRange.gif").createImage();
		ExportMetadataAction exportAction = new ExportMetadataAction(resource, site,dialogTitle,dialogImage,dialogMessage);
		getActionRegistry().registerAction(exportAction);
		getSelectionActions().add(exportAction.getId());
		
		getEditableControl().addEditableUnit(new ActionEditableUnit(importAction));
	}
	
	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		IAction action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_IMPORT_METADATA);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_EXPORT_METADATA);
		manager.add(action);
	}
	
	private void createExtensibleModelTreeViewerColumns(
			TreeViewer viewer, IARESResource resource, EClass eClass,
			IDiagnosticProvider diagnosticProvider) {
		if (resource == null) {
			return;
		}
		IExtensibleModelEditingSupport[] editingSupports = ExtensibleModelUtils
				.getEndabledEditingSupports(resource, eClass);
		GC gc = new GC(viewer.getControl());

		try {
			for (IExtensibleModelEditingSupport support : editingSupports) {
				for (IExtensibleModelPropertyDescriptor descriptor : support
						.getPropertyDescriptors(resource, eClass)) {
					TreeViewerColumn tvColumn = new TreeViewerColumn(viewer,
							SWT.LEFT);

					// 长度根据现实的名称类决定
					String displayName = descriptor.getDisplayName();

					Point p = gc.stringExtent(displayName);

					tvColumn.getColumn().setWidth(p.x + 100);
					tvColumn.getColumn().setText(displayName);

					IDExtendLabelProvider provider = new IDExtendLabelProvider(
							support, descriptor , (IDRangeList) page.getEditor().getInfo());
					provider.setDiagnosticProvider(diagnosticProvider);
					tvColumn.setLabelProvider(provider);

					tvColumn.setEditingSupport(new IDExtentEditingSupport(
							viewer, support, descriptor,(IDRangeList) page.getEditor().getInfo()));
					tvColumn.getColumn().setMoveable(true);
				}

			}
		} finally {
			gc.dispose();
		}
	}
}
