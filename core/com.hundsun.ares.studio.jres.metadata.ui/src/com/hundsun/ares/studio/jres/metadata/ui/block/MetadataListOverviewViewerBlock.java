/**
 * 源程序名称：MetadataOverviewViewerBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.block;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.metadata.ui.actions.JumpAction;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataOverviewContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author yanwj06282
 *
 */
public abstract class MetadataListOverviewViewerBlock extends MetadataListViewerBlock{

	private static final String ID_REFRESH = "MetadataOverviewPage_Refresh";
	
	/**
	 * @param page
	 * @param editingDomain
	 * @param site
	 * @param resource
	 * @param problemPool
	 */
	public MetadataListOverviewViewerBlock(FormPage page,
			EditingDomain editingDomain, IWorkbenchPartSite site,
			IARESResource resource, IProblemPool problemPool) {
		super(page, editingDomain, site, resource, problemPool);
	}

	@Override
	protected void createMenus(IMenuManager menuManager) {
		IAction action = getActionRegistry().getAction(ID_REFRESH);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_JUMP);
		menuManager.add(action);
	}
	
	@Override
	protected void createActions() {
		super.createActions();
		IAction refreshAction = new Action("刷新"){
			@Override
			public void run() {
				getColumnViewer().refresh();
			}
		};
		refreshAction.setId(ID_REFRESH);
		refreshAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/refresh.gif"));
		getActionRegistry().registerAction(refreshAction);
		getSelectionActions().add(refreshAction.getId());
		
		IAction jumpAction = new JumpAction(getColumnViewer());
		getActionRegistry().registerAction(jumpAction);
		getSelectionActions().add(jumpAction.getId());
	}

	@Override
	protected boolean getDefaultShowCategory() {
		return false;
	}
	
	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		super.createToolbarItems(manager);
		IAction action = getActionRegistry().getAction(ID_REFRESH);
		manager.add(action);
	}
	
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new MetadataOverviewContentProvider(getARESResource(), getReferenceType());
	}
	
	protected abstract String getReferenceType();
	
}
