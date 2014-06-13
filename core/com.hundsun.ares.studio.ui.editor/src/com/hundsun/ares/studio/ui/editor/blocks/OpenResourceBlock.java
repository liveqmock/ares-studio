/**
 * 源程序名称：OpenResourceBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.ui.editor.actions.OpenResourceExportAction;
import com.hundsun.ares.studio.ui.editor.viewers.BaseEObjectColumnLabelProvider;

/**
 * @author yanwj06282
 *
 */
public class OpenResourceBlock extends TableViewerBlock {

	protected IARESElement element;
	public String[] titiles;
	public EStructuralFeature[] features;
	//扩展列
	public Map<String,String> exTitles = new LinkedHashMap<String ,String>();
	private boolean compStat = false;
	
	
	public OpenResourceBlock(IARESElement element ,String[] titiles ,EStructuralFeature[] features) {
		this.element = element;
		this.titiles = titiles;
		this.features = features;
	}
	
	@Override
	protected String getID() {
		return getClass().getName();
	}

	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ArrayContentProvider();
	}

	@Override
	protected void createMenus(IMenuManager menuManager) {
	}

	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		List<OpenResourceInfo> databaseList = new ArrayList<OpenResourceInfo>();
		IARESResource[] resources = null;
		try {
			if(element instanceof IARESModuleRoot) {
				resources = ((IARESModuleRoot) element).getResources();
			}else if (element instanceof IARESModule) {
				resources = ((IARESModule) element).getARESResources(true);
			}
			
			for (IARESResource resource : resources) {
				EObject dbinfo = resource.getInfo(EObject.class);
				if (dbinfo != null) {
					OpenResourceInfo info = new OpenResourceInfo(resource, dbinfo);
					databaseList.add(info);
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		OpenResourceExportAction action = new OpenResourceExportAction(databaseList ,this);
		manager.add(action);
	}
	
	@Override
	protected void createColumns(final TableViewer viewer) {
		final TableViewer tableViewer = (TableViewer) viewer;
		
		final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
		tvColumn.getColumn().setWidth(80);
		tvColumn.getColumn().setText("类型");
		
		tvColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return OpenResourceHelper.getType(element);
			}
		});
		tvColumn.getColumn().setMoveable(true);
		
		tvColumn.getColumn().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object input = getColumnViewer().getInput();
				if (input instanceof List) {
					compartorResList((List<Object>) input, CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID);
					compStat = !compStat;
				}
				getColumnViewer().setInput(input);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		final TableViewerColumn pathColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
		pathColumn.getColumn().setWidth(80);
		pathColumn.getColumn().setText("路径");
		
		pathColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				if (element instanceof OpenResourceInfo) {
					return OpenResourceHelper.getChineseFileName("/" ,((OpenResourceInfo) element).getResource().getModule());
				}else {
					return StringUtils.EMPTY;
				}
			}
		});
		pathColumn.getColumn().setMoveable(true);
		
		pathColumn.getColumn().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object input = getColumnViewer().getInput();
				if (input instanceof List) {
					Collections.sort((List)input, new StringList());
					compStat = !compStat;
				}
				getColumnViewer().setInput(input);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		
		for (int i = 0; i < titiles.length; i++) {
			String title = titiles[i];
			final EStructuralFeature feature = features[i];
			BaseEObjectColumnLabelProvider labelProvider = new BaseEObjectColumnLabelProvider(feature){
				@Override
				protected EObject getOwner(Object element) {
					if (element instanceof OpenResourceInfo) {
						return ((OpenResourceInfo) element).getObj();
					}
					return null;
				}
			};
			
			final TableViewerColumn exColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			exColumn.getColumn().setWidth(80);
			exColumn.getColumn().setText(title);
			
			exColumn.setLabelProvider(labelProvider);
			
			exColumn.getColumn().setMoveable(true);
			
			exColumn.getColumn().addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					Object input = getColumnViewer().getInput();
					if (input instanceof List) {
						compartorResList((List<Object>) input, feature);
						compStat = !compStat;
					}
					getColumnViewer().setInput(input);
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
			
		}
		
		try {
			List<IARESResource> resList = new ArrayList<IARESResource>();
			if (element instanceof IARESModuleRoot) {
				resList.addAll(Arrays.asList(((IARESModuleRoot) element).getResources()));
			}else if (element instanceof IARESModule) {
				resList.addAll(Arrays.asList(((IARESModule) element).getARESResources(true)));
			}
			//添加模块或者模块根下面所有资源的扩展信息(基本信息)
			//每种类型只需要加载一次扩展信息
			Set<String> typeSet = new HashSet<String>();
			Set<String> exDupTitle = new HashSet<String>();
			for(IARESResource res : resList){
				if (typeSet.contains(res.getType())) {
					continue;
				}else {
					typeSet.add(res.getType());
				}
				//加载扩展
				exTitles.putAll(OpenResourceHelper.createExtensibleModelTableViewerColumns(getColumnViewer(), res , null ,exDupTitle));
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		//给表格增加双击监听
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection ss = (IStructuredSelection) viewer.getSelection();
				OpenResourceInfo element = (OpenResourceInfo) ss.getFirstElement();
				if (element == null)
					return;
				try {
					IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile) element.getResource().getResource());
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void setInput(Object input) {
		super.setInput(input);
		for (TableColumn column : getColumnViewer().getTable().getColumns()) {
			column.pack();
		}
	}
	
	private void compartorResList(List<Object> databaseList , EStructuralFeature attribute){
		Collections.sort(databaseList, new ResList(attribute));
	}
	
	class ResList implements Comparator<Object>{

		private EStructuralFeature attribute;
		
		public ResList(EStructuralFeature attribute){
			this.attribute = attribute;
		}
		
		@Override
		public int compare(Object obj1, Object obj2) {
			if ((obj1 instanceof OpenResourceInfo) && (obj2 instanceof OpenResourceInfo)) {
				OpenResourceInfo o1 = (OpenResourceInfo) obj1;
				OpenResourceInfo o2 = (OpenResourceInfo) obj2;
				Object v1 = o1.getObj().eGet(attribute);
				Object v2 = o2.getObj().eGet(attribute);
				if(v1 instanceof String && v2 instanceof String) {
					if (compStat) {
						return (((String)v2).toLowerCase()).compareTo(((String)v1).toLowerCase());
					}
					return (((String)v1).toLowerCase()).compareTo(((String)v2).toLowerCase());
				}
			}
			return 0;
		}
	}
	
	class StringList implements Comparator<OpenResourceInfo>{
		@Override
		public int compare(OpenResourceInfo o1, OpenResourceInfo o2) {
			String v1 = OpenResourceHelper.getChineseFileName("/" ,o1.getResource().getModule());
			String v2 = OpenResourceHelper.getChineseFileName("/" ,o2.getResource().getModule());
			if (compStat) {
				return (((String)v2).toLowerCase()).compareTo(((String)v1).toLowerCase());
			}
			return (((String)v1).toLowerCase()).compareTo(((String)v2).toLowerCase());
		}
		
	}
	
}