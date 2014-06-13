/**
 * 源程序名称：RevisionHistoryOverviewBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.uft.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryVersion;
import com.hundsun.ares.studio.ui.ColumnSelectSorterListener;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.RevisionHistroyOverviewCopyAction;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.BaseEObjectColumnLabelProvider;

/**
 * 历史记录总览页面
 * @author sundl
 *
 */
public class RevisionHistoryOverviewBlock extends TableViewerBlock {

	private CommonElementLabelProvider labelprovider = new CommonElementLabelProvider(null);
	private IARESResource resource;
	
	/**
	 * 用于总览列表的元素
	 */
	public static class RevisionHistoryOverviewElement implements Comparable<RevisionHistoryOverviewElement>{
		IARESResource resource;
		private  RevisionHistory revision;
		
		/**
		 * @return the revision
		 */
		public RevisionHistory getRevision() {
			return revision;
		}

		public RevisionHistoryOverviewElement(IARESResource resource, RevisionHistory revision) {
			this.resource = resource;
			this.revision = revision;
		}

		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(RevisionHistoryOverviewElement o) {
			if (StringUtils.isEmpty(revision.getModifiedDate())) 
				return 1;
			
			if (StringUtils.isEmpty(o.revision.getModifiedDate())) 
				return -1;
			
			try {
				int result = revision.getModifiedDate().compareTo(o.revision.getModifiedDate());
				if (result == 0) {
					RevisionHistoryVersion v1 = new RevisionHistoryVersion(revision.getVersion());
					RevisionHistoryVersion v2 = new RevisionHistoryVersion(o.revision.getVersion());
					return v1.compareTo(v2);
				}
				return result;
			} catch (Exception e) {
				
			}
			return 1;
		}
	}
	
	static class RevisionHistoryOverviewElementLabelProvider extends BaseEObjectColumnLabelProvider {

		public RevisionHistoryOverviewElementLabelProvider(EStructuralFeature attribute) {
			super(attribute);
		}
		
		@Override
		protected EObject getOwner(Object element) {
			RevisionHistoryOverviewElement re = (RevisionHistoryOverviewElement) element;
			return re.revision;
		}
		
	}
	
	public RevisionHistoryOverviewBlock(IARESResource resource) {
		this.resource = resource;
	}
	
	@Override
	protected void createColumns(final TableViewer viewer) {
		// 资源
		{
			// 创建表格列
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("修订位置");
			column.getColumn().setWidth( 120);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			ColumnLabelProvider provider = new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					RevisionHistoryOverviewElement re = (RevisionHistoryOverviewElement) element;
					// TASK #8690 修订记录汇总的修订信息信息显示资源的层级
					if (!re.resource.getType().equals(IARESModule.MODULE_PROPERTY_FILE)) {
						IARESModule module = re.resource.getModule();
						String location = getModulePath(module);
						location = location + "/" + labelprovider.getText(re.resource);
						return location;
					}
					
					// 如果是手动输入了location属性，直接显示(这种情况一般是模块属性中添加的)
					String location = re.revision.getLocation();
					if (!StringUtils.isEmpty(location))
						return location;
					
					// 模块修订记录，如果没有输入，在总览页面显示模块的路径
					return getModulePath(re.resource.getModule());
				}
			};
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
		}
		
		/**修订时间*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("修订时间");
			comlumn.getColumn().setWidth(130);
			RevisionHistoryOverviewElementLabelProvider provider = new RevisionHistoryOverviewElementLabelProvider(CorePackage.Literals.REVISION_HISTORY__MODIFIED_DATE);
			//provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			//comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__MODIFIED_DATE));
		}
		/**修订版本*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("修订版本");
			comlumn.getColumn().setWidth(160);
			RevisionHistoryOverviewElementLabelProvider provider = new RevisionHistoryOverviewElementLabelProvider(CorePackage.Literals.REVISION_HISTORY__VERSION);
			//provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			//comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__VERSION));
			comlumn.getColumn().setMoveable(true);
		}
		/**修订单号*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("修订单号");
			comlumn.getColumn().setWidth(160);
			RevisionHistoryOverviewElementLabelProvider provider = new RevisionHistoryOverviewElementLabelProvider(CorePackage.Literals.REVISION_HISTORY__ORDER_NUMBER);
			//provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			//comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__ORDER_NUMBER));
			comlumn.getColumn().setMoveable(true);
		}
				
		/**申请人*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("申请人");
			comlumn.getColumn().setWidth(100);
			RevisionHistoryOverviewElementLabelProvider provider = new RevisionHistoryOverviewElementLabelProvider(CorePackage.Literals.REVISION_HISTORY__MODIFIED_BY);
			//provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			//comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__MODIFIED_BY));
			comlumn.getColumn().setMoveable(true);
		}
		/**负责人*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("负责人");
			comlumn.getColumn().setWidth(100);
			comlumn.setLabelProvider(new RevisionHistoryOverviewElementLabelProvider(CorePackage.Literals.REVISION_HISTORY__CHARGER));
			//comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__CHARGER));
			comlumn.getColumn().setMoveable(true);
		}
		
		/**修改内容*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("修改内容");
			comlumn.getColumn().setWidth(200);
			comlumn.setLabelProvider(new RevisionHistoryOverviewElementLabelProvider(CorePackage.Literals.REVISION_HISTORY__MODIFIED));
			//comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__MODIFIED));
			comlumn.getColumn().setMoveable(true);
		}
		
		/**修改原因*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("修改原因");
			comlumn.getColumn().setWidth(200);
			comlumn.setLabelProvider(new RevisionHistoryOverviewElementLabelProvider(CorePackage.Literals.REVISION_HISTORY__MODIFIED_REASON));
			//comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__MODIFIED_REASON));
			comlumn.getColumn().setMoveable(true);
		}
		/**备注*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("备注");
			comlumn.getColumn().setWidth(200);
			RevisionHistoryOverviewElementLabelProvider provider = new RevisionHistoryOverviewElementLabelProvider(CorePackage.Literals.REVISION_HISTORY__COMMENT);
			//provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__COMMENT));
			comlumn.getColumn().setMoveable(true);
		}
		
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection ss = (IStructuredSelection) viewer.getSelection();
				RevisionHistoryOverviewElement element = (RevisionHistoryOverviewElement) ss.getFirstElement();
				//JRESUtils.getSuitableEditorDescriptor(element.resource.getResource().getName());
				// 2013-1-21 sundl 个别情况下，可能会出现element为空，很难重现，但确实有可能。
				if (element == null)
					return;
				
				try {
					IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile) element.resource.getResource());
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private String getModulePath(IARESModule module) {
		String location = labelprovider.getText(module);
		IARESModule parentModule = module.getParentModule();
		while (parentModule != null) {
			location = labelprovider.getText(parentModule) + "/" + location;
			parentModule = parentModule.getParentModule();
		}
		return location;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getID()
	 */
	@Override
	protected String getID() {
		return getClass().getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getColumnViewerContentProvider()
	 */
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new IStructuredContentProvider() {
			@Override
			public void dispose() {
			}
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				
			}
			@Override
			public Object[] getElements(Object inputElement) {
				List<RevisionHistoryOverviewElement> result = new ArrayList<RevisionHistoryOverviewElement>();
				IARESModule module = resource.getModule();
				IARESResource[] resources = module.getARESResources(true);
				for (IARESResource res : resources) {
					try {
						JRESResourceInfo info = res.getInfo(JRESResourceInfo.class);
						if (info == null) {
							ModuleProperty mp = res.getInfo(ModuleProperty.class);
							if (mp != null) {
								info = (JRESResourceInfo) mp.getMap().get(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY);
							}
						}
						if (info != null) {
							List<RevisionHistory> histories = info.getHistories();
							for (RevisionHistory his : histories) {
								result.add(new RevisionHistoryOverviewElement(res, his));
							}
						}
						
					} catch (ARESModelException e) {
						
					}
				}
				Collections.sort(result);
				// 倒序显示
				Collections.reverse(result);
				return result.toArray();
			}
		};	
	}
	
	@Override
	public void setInput(Object input) {
		super.setInput(input);
		getColumnViewer().getTable().getColumn(0).pack();
		getColumnViewer().getTable().getColumn(1).pack();
		getColumnViewer().getTable().getColumn(2).pack();
		getColumnViewer().getTable().getColumn(4).pack();
		getColumnViewer().getTable().getColumn(5).pack();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_COPY);
		menuManager.add(action);
		
	}
	
	protected void createActions() {
		IAction copyAction = new RevisionHistroyOverviewCopyAction(getColumnViewer());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
	};
	
	@Override
	protected boolean needButtonGroupArea() {
		return false;
	}

}
