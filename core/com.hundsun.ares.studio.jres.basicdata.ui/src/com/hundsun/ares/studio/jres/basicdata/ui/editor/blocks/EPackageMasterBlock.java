package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IManagedForm;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveLinkTableImpl;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveTableImpl;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.SingleTableImpl;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.MaterSlaveLinkTableDetailPage;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.MaterSlaveTableDetailPage;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.SingelTableDetailPage;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.FilteredTable;
import com.hundsun.ares.studio.ui.TableViewerPatternFilter;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.IEMFFormPage;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;
import com.hundsun.ares.studio.ui.editor.viewers.SynRefreshViewerJob;
import com.hundsun.ares.studio.ui.validate.IProblemView;
import com.hundsun.ares.studio.ui.validate.ProblemPoolChangeEvent;

public class EPackageMasterBlock extends BaseBasicMasterDetailsBlock implements CommandStackListener {

	private TableViewer viewer;
	List<PackageDefine> input = new ArrayList<PackageDefine>();
	IARESResource resource;
	
	public EPackageMasterBlock(EMFFormPage page) {
		super(page);
		input = ((EpacakgeDefineList)page.getEditor().getInfo()).getItems();
		resource = page.getEditor().getARESResource();
	
	}

	@Override
	protected String getPageHeadName() {
		return null;
	}

	@Override
	protected String getSectionName() {
		return "表关联信息列表";
	}

	@Override
	protected String getDescription() {
		return null;
	}

	@Override
	protected Object[] getElementTypes() {
		return new Object[]{SingleTableImpl.class,MasterSlaveTableImpl.class,MasterSlaveLinkTableImpl.class};
	}

	@Override
	protected IDetailsPage getDetailPage(Object type) {
		IARESResource resource = page.getEditor().getARESResource();
		if(type.equals(MasterSlaveLinkTableImpl.class)){
			return new MaterSlaveLinkTableDetailPage(resource,viewer);
		}else if(type.equals(MasterSlaveTableImpl.class)){
			return new MaterSlaveTableDetailPage(resource,viewer);
		}else{
			return new SingelTableDetailPage(resource,viewer);
		}
	}

	@Override
	protected Viewer createViewer(Composite client, IManagedForm managedForm,
			IEMFFormPage page) {
		FilteredTable table = new FilteredTable(client, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL, new TableViewerPatternFilter(), true);
		viewer = table.getViewer();
		viewer.getTable().setHeaderVisible(false);
		viewer.getTable().setLinesVisible(false);
		
		fillTableContents();
		
		
		// 开启表格tooltip显示
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.RECREATE);
		return viewer;
	}

	@Override
	protected void createToolBarActions(IManagedForm managedForm) {
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresBasicMasterDetailsBlock#createButtons(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtons(Composite client) {
		Button btnRefresh = new Button(client, SWT.BORDER);
		btnRefresh.setText("刷新配置");
		
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					refreshBasicData();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		final Button btneAddd = new Button(client, SWT.BORDER);
		btneAddd.setText("新增关联");
		btneAddd.addSelectionListener(new SelectionAdapter(){
			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					
					BasicDataTypeDialog basicDataTypeDialog = new BasicDataTypeDialog(btneAddd.getShell());
					int state = basicDataTypeDialog.open();
					if(Dialog.OK == state){
						int type = basicDataTypeDialog.getBasicDataType();
						PackageDefine tableDefine = (PackageDefine) createBasicDataInfo("",type);
						Command command = createPackageDefineCommand(tableDefine);
						page.getEditor().getEditingDomain().getCommandStack().execute(command);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		final Button btnRemove = new Button(client, SWT.BORDER);
		btnRemove.setText("删除关联");
		btnRemove.addSelectionListener(new SelectionAdapter(){
			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection structuredSelection  = (StructuredSelection) viewer.getSelection();
				Iterator<PackageDefine> iterator = structuredSelection.iterator();
				List<PackageDefine> removePackageDefine = new ArrayList<PackageDefine>();
				CompoundCommand command = new CompoundCommand("");
				while(iterator.hasNext()){
					PackageDefine packageDefine =(PackageDefine) iterator.next();
					command.append(removePackageDefineCommand(packageDefine)) ;
					removePackageDefine.add(packageDefine);
				}
				if(!command.isEmpty()){
					page.getEditor().getEditingDomain().getCommandStack().execute(command);
				}
				clearCommand(command);
			}
		});
	}
	
	/**
	 * 新增关系
	 * @param tableDefine
	 * @return
	 */
	private Command createPackageDefineCommand(PackageDefine tableDefine) {
		EObject owner = page.getEditor().getInfo();
		CompoundCommand command = new CompoundCommand("");
		command.append(AddCommand.create(page.getEditor().getEditingDomain(), owner, BasicdataPackage.Literals.EPACAKGE_DEFINE_LIST__ITEMS, tableDefine));
		return command.unwrap();
	}
	
	/**
	 * 删除关系
	 * @param tableDefine
	 * @return
	 */
	private Command removePackageDefineCommand(PackageDefine tableDefine) {
		EObject owner = page.getEditor().getInfo();
		CompoundCommand command = new CompoundCommand("");
		command.append(RemoveCommand.create(page.getEditor().getEditingDomain(), owner, BasicdataPackage.Literals.EPACAKGE_DEFINE_LIST__ITEMS, tableDefine));
		return command.unwrap();
	}
	
	/**
	 * 刷新基础数据关联
	 * @param tableDefines
	 * @return
	 */
	private Command refreshPackageDefineCommand(List<PackageDefine>tableDefines) {
		CompoundCommand command = new CompoundCommand("");
		EpacakgeDefineList resourceInfo;
		try {
			resourceInfo = resource.getInfo(EpacakgeDefineList.class);
			command.append(RemoveCommand.create(page.getEditor().getEditingDomain(), resourceInfo,BasicdataPackage.Literals.EPACAKGE_DEFINE_LIST__ITEMS,resourceInfo.getItems()));
			//command.append(AddCommand.create(page.getEditor().getEditingDomain(), resourceInfo, BasicdataPackage.Literals.EPACAKGE_DEFINE_LIST__ITEMS, tableDefines));
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		return command.unwrap();
	}
	
	private void refreshBasicData() throws Exception{

		EpacakgeDefineList resourceInfo = resource.getInfo(EpacakgeDefineList.class);
		IProject project = resource.getARESProject().getProject();

		List<PackageDefine>  defines = fillEpackageDefineList(resource.getARESProject());
		Command refreshCommand = refreshPackageDefineCommand(defines);
		page.getEditor().getEditingDomain().getCommandStack().execute(refreshCommand);
		//其次，刷新缓存
		for(PackageDefine item:resourceInfo.getItems()){
			IFile file = project.getFile(item.getUrl());
			IARESResource resource = (IARESResource)ARESCore.create(file);
			BasicDataEpackageFactory.eINSTANCE.clearEPackage(resource);
		}
		
	}
	
	/**
	 * @param input the input to set
	 */
	public void setInput(List<PackageDefine> input) {
		this.input = input;
		viewer.setInput(input);
	}
	
	/**
	 * 创建资源模型
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private PackageDefine createBasicDataInfo(String url,int type)throws Exception{
		 	PackageDefine tableDefine = createPackageDefine(url,type);
			return tableDefine;
	}
	
	
	private PackageDefine createPackageDefine(String path,int type){
		
		PackageDefine defineObj = null;
		switch (type) {
		case 0:
			SingleTable table = BasicdataFactory.eINSTANCE.createSingleTable();
			table.setMaster("");
			defineObj = table;
			break;
		case 1:
			MasterSlaveTable mtable = BasicdataFactory.eINSTANCE.createMasterSlaveTable();
			mtable.setMaster("");
			mtable.setSlave("");
			defineObj = mtable;
			break;
		case 2:
			MasterSlaveLinkTable mltable = BasicdataFactory.eINSTANCE.createMasterSlaveLinkTable();
			mltable.setMaster("");
			mltable.setSlave("");
			mltable.setLink("");
			defineObj = mltable;
			break;
		default:
			break;
		}
		
		defineObj.setType(getBasicDataType());
		defineObj.setUrl(path);
		
		return defineObj;
	}
	
	/**
	 * 获取类型
	 * @return
	 * @throws Exception
	 */
	private String getBasicDataType(){
		String type ="";
		try {
			type = StringUtils.defaultString(resource.getARESProject().getProjectProperty().getString(IBasicDataEpacakgeConstant.Property_Basic_Data_type_ID));
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		return type;
	}

	private List<PackageDefine>  fillEpackageDefineList(IARESProject project) throws Exception{
		List<PackageDefine> defines = new ArrayList<PackageDefine>();
		List<ReferenceInfo> infos = ReferenceManager.getInstance().getReferenceInfos(project, IBasicDataRestypes.singleTable, true);
		List<ReferenceInfo> msInfos = ReferenceManager.getInstance().getReferenceInfos(project, IBasicDataRestypes.MasterSlaveTable, true);
		List<ReferenceInfo> mslInfos = ReferenceManager.getInstance().getReferenceInfos(project, IBasicDataRestypes.MasterSlaveLinkTable, true);
		for(ReferenceInfo info : infos){
			if (StringUtils.equals(info.getResource().getResource().getFileExtension(), IBasicDataRestypes.singleTable)) {
				Object define = (Object) BasicDataEpackageFactory.eINSTANCE.getDefine(info.getResource());
				if (define instanceof PackageDefine) {
					defines.add((PackageDefine) define);
				}else {
					SingleTable pd = BasicdataFactory.eINSTANCE.createSingleTable();
					pd.setType("jres.db.table");
					pd.setUrl(StringUtils.substringAfter(info.getResource().getResource().getLocation().toOSString(), info.getResource().getARESProject().getElementName() + "\\"));
					pd.setMaster(StringUtils.EMPTY);
					defines.add(pd);
				}
			}
		}
		for(ReferenceInfo info : msInfos){
			if (StringUtils.equals(info.getResource().getResource().getFileExtension(), IBasicDataRestypes.MasterSlaveTable)) {
				Object define = (Object) BasicDataEpackageFactory.eINSTANCE.getDefine(info.getResource());
				if (define instanceof PackageDefine) {
					defines.add((PackageDefine) define);
				}else {
					MasterSlaveTable mst = BasicdataFactory.eINSTANCE.createMasterSlaveTable();
					mst.setType("jres.db.table");
					mst.setUrl(StringUtils.substringAfter(info.getResource().getResource().getLocation().toOSString(), info.getResource().getARESProject().getElementName() + "\\"));
					mst.setMaster(StringUtils.EMPTY);
					mst.setSlave(StringUtils.EMPTY);
					defines.add(mst);
				}
			}
		}
		for(ReferenceInfo info : mslInfos){
			if (StringUtils.equals(info.getResource().getResource().getFileExtension(), IBasicDataRestypes.MasterSlaveLinkTable)) {
				Object define = (Object) BasicDataEpackageFactory.eINSTANCE.getDefine(info.getResource());
				if (define instanceof PackageDefine) {
					defines.add((PackageDefine) define);
				}else {
					MasterSlaveLinkTable mst = BasicdataFactory.eINSTANCE.createMasterSlaveLinkTable();
					mst.setType("jres.db.table");
					mst.setUrl(StringUtils.substringAfter(info.getResource().getResource().getLocation().toOSString(), info.getResource().getARESProject().getElementName() + "\\"));
					mst.setMaster(StringUtils.EMPTY);
					mst.setSlave(StringUtils.EMPTY);
					mst.setLink(StringUtils.EMPTY);
					defines.add(mst);
				}
			}
		}
		return defines;
	}
	
	private void fillTableContents() {
		TableViewerColumn column = new TableViewerColumn(viewer, SWT.LEFT);
		column.setLabelProvider(new BlockLabelProvider(page.getProblemPool()));
		column.getColumn().setWidth(500);
		column.getColumn().setText("基础数据列表");
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(input);
		page.getProblemPool().addView(new IProblemView() {
			@Override
			public void refresh(ProblemPoolChangeEvent event) {
				//错误检查完，表格进行刷新
				Object[] problems = ArrayUtils.addAll(event.getAddProblems(), event.getRemoveedProblems());
				Set<Object> objects = new HashSet<Object>();
				for (Object problem : problems) {
					EObject eObj = (EObject) ((Diagnostic)problem).getData().get(0);
					objects.add( eObj );
				}
				RefreshViewerJob.refresh(viewer, objects.toArray(), true);
			}
		});
	}
	
	
	
	/**
	 * 基础数据关联关系选择
	 * @author liaogc
	 *
	 */
	class BasicDataTypeDialog extends Dialog{
		
		private int basicDataType = 0;

		
		/**
		 * @param parentShell
		 */
		protected BasicDataTypeDialog(Shell parentShell) {
			super(parentShell);
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		protected Control createDialogArea(Composite parent) {
			Group groupModeSelect = new Group(parent, SWT.BORDER);
			groupModeSelect.setText("模式选择");
			RowLayout layout = new RowLayout(SWT.HORIZONTAL);
			groupModeSelect.setLayout(layout);
			Button radioSingle = new Button(groupModeSelect, SWT.RADIO);
			radioSingle.setSelection(true);
			radioSingle.setText("二维表");
			radioSingle.setSelection(true); // 初始化

			Button radioMasterSlave = new Button(groupModeSelect, SWT.RADIO);
			radioMasterSlave.setText("主从表");

			Button radioMasterSlaveLink = new Button(groupModeSelect, SWT.RADIO);
			radioMasterSlaveLink.setText("主从关联表");
			
			// 添加监听器
			radioSingle.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					basicDataType = 0;
				}
			});

			radioMasterSlave.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					basicDataType = 1;
				}
			});

			radioMasterSlaveLink.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					basicDataType = 2;
				}
			});
			applyDialogFont(groupModeSelect);
			return groupModeSelect;
		}
		/**
		 * @return the basicDataType
		 */
		public int getBasicDataType() {
			return basicDataType;
		}

		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.CommandStackListener#commandStackChanged(java.util.EventObject)
	 */
	@Override
	public void commandStackChanged(EventObject event) {
		RefreshViewerJob.refresh(viewer, null, false);
		Command cmd = ((CommandStack)event.getSource()).getMostRecentCommand();
		if (cmd != null) {
			SynRefreshViewerJob.refresh(viewer, cmd.getAffectedObjects().toArray() ,cmd ,BasicdataPackage.Literals.PACKAGE_DEFINE__URL);
		}
	}
	protected void clearCommand(Command command) {
		if (command != null) {
			command.dispose();
			command = null;
		}
	}
	
}
