package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.converter.IModelConverter;
import com.hundsun.ares.studio.core.model.converter.IModelConverter2;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.util.EPackageUtil;
import com.hundsun.ares.studio.jres.basicdata.logic.util.JRESResProviderUtil;
import com.hundsun.ares.studio.jres.basicdata.manage.BasicdataDefineManageUtil;

public class NewBasicDataWizardPage extends ResourceWizardPage {

	private static Logger logger = Logger.getLogger(NewBasicDataWizardPage.class);
	
	public NewBasicDataWizardPage(String pageName, IWorkbench workbench,
			IARESElement selection, String resType) {
		super(pageName, workbench, selection, resType);
		setTitle("新建基础数据");
	}

	@Override
	protected void createText(Composite composite) {
		// 创建控件
		Group groupModeSelect = new Group(composite, SWT.BORDER);
		groupModeSelect.setText("模式选择");

		Button radioSingle = new Button(groupModeSelect, SWT.RADIO);
		radioSingle.setText("二维表");
		radioSingle.setSelection(true); // 初始化

		Button radioMasterSlave = new Button(groupModeSelect, SWT.RADIO);
		radioMasterSlave.setText("主从表");

		Button radioMasterSlaveLink = new Button(groupModeSelect, SWT.RADIO);
		radioMasterSlaveLink.setText("主从关联表");

		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING)
				.grab(true, false).span(2, 1).applyTo(groupModeSelect);

		groupModeSelect.setLayout(new GridLayout(3, true));

		// 添加监听器
		radioSingle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				activeMode(NewBasicDataWizard.MODE_SINGLE);
			}
		});

		radioMasterSlave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				activeMode(NewBasicDataWizard.MODE_MASTERSLAVE);
			}
		});

		radioMasterSlaveLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				activeMode(NewBasicDataWizard.MODE_MASTERSLAVELINK);
			}
		});

		NewBasicDataWizard wizard = (NewBasicDataWizard) getWizard();
		try {
			wizard.modeDfine.inputType = getBasicDataType();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		super.createText(composite);
	}

	private void activeMode(int mode) {
		NewBasicDataWizard wizard = (NewBasicDataWizard) getWizard();
		wizard.modeDfine.mode = mode;
	}

	@Override
	protected void createResource() {
		String path = null;
		// long t1 = System.currentTimeMillis();
		String restype = getResType();
		
		IResDescriptor resDescriptor = ARESResRegistry.getInstance()
				.getResDescriptor(restype);
		if (resDescriptor != null) {
			IModelConverter converter = resDescriptor.getConverter();

			if (!StringUtils.isBlank(txt_packageName.getText())) {
				path = txt_packageName.getText().replaceAll("\\.", "/");
			}
			checkPath(path);
			IFolder folder = project.getProject().getFolder(
					getModuleRoot().getElementName() + "/" + path);
			String name = getNewName();
			name = name + "." + restype;
			IFile file = folder.getFile(name);
			if (!file.exists()) {
				//生成基础数据定义
				PackageDefine tableDefine = null;
				//资源模型
				Object info = null;
				try {
					tableDefine = createPackageDefine(file.getProjectRelativePath().toOSString());
					info = BasicDataEpackageFactory.eINSTANCE.createInstance(getProject(), tableDefine);
					initNewResourceInfo(info);
					{
						//获取基础数据的模型定义数据源的类型
						String type = EPackageUtil.getBasicDataType(project);
						if(info instanceof BasicDataBaseModel){
							try {
								String tableName = "";
								if (tableDefine instanceof SingleTable) {
									tableName = ((SingleTable) tableDefine).getMaster();
								}else if (tableDefine instanceof MasterSlaveTable) {
									tableName = ((MasterSlaveTable) tableDefine).getMaster();
								}else if (tableDefine instanceof MasterSlaveLinkTable) {
									tableName = ((MasterSlaveLinkTable) tableDefine).getMaster();
								}
								JRESResourceInfo table = JRESResProviderUtil.getResourceModel(project, 
										tableName,
										type,//"jres.db.table",
										JRESResourceInfo.class);
								if (StringUtils.isNotBlank(table.getChineseName())) {
									((BasicDataBaseModel)info).setChineseName(table.getChineseName());
								}else {
									((BasicDataBaseModel)info).setChineseName(getNewName());
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else if(info instanceof StandardFieldModelAndData){
							try {
								JRESResourceInfo table = JRESResProviderUtil.getResourceModel(project, 
										((StandardFieldModelAndData) info).getData().getFullyQualifiedName(),
										type,//"jres.db.table",
										JRESResourceInfo.class);
								if (StringUtils.isNotBlank(table.getChineseName())) {
									((StandardFieldModelAndData)info).getData().setChineseName(table.getChineseName());
								}else {
									((StandardFieldModelAndData)info).getData().setChineseName(getNewName());
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					BasicdataDefineManageUtil.savePackageDefine(project, tableDefine);
//					savePackageDefine(tableDefine);
				} catch (Exception e) {
					setErrorMessage(e.getMessage());
					return;
				}

				//保存模型
				if (converter instanceof IModelConverter2) {
					try {

						IARESResource resource = (IARESResource) ARESCore
								.create(file);
						file.create(
								new ByteArrayInputStream(
										((IModelConverter2) converter).write(
												resource, info)), true, null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						converter.write(bos, info);
						file.create(
								new ByteArrayInputStream(bos.toByteArray()),
								true, null);
					} catch (Exception e) {
						e.printStackTrace();
					}
					this.resource = file;
				}
				
				
				this.resource = file;
			}
		}
		// long t2 = System.currentTimeMillis();
		// logger.info("资源： " + getNewName() + " 创建成功，用时" + (t2 - t1) + "ms.");
	}
	
	protected String getResType() {
		NewBasicDataWizard wizard = (NewBasicDataWizard)getWizard();
		switch (wizard.modeDfine.mode) {
		case NewBasicDataWizard.MODE_SINGLE:
			return IBasicDataRestypes.singleTable;
		case NewBasicDataWizard.MODE_MASTERSLAVE:
			return IBasicDataRestypes.MasterSlaveTable;
		case NewBasicDataWizard.MODE_MASTERSLAVELINK:
			return IBasicDataRestypes.MasterSlaveLinkTable;
		default:
			break;
		}
		return null;
	}

//	/**
//	 * 创建基础数据定义文件
//	 * @return
//	 */
//	private IResource createEpacakgeDefineResource() {
//		IResDescriptor resDescriptor = ARESResRegistry.getInstance()
//				.getResDescriptor(IBasicDataRestypes.PackageDefine);
//		IModelConverter converter = resDescriptor.getConverter();
//		Object info = BasicdataFactory.eINSTANCE.createEpacakgeDefineList();
//		IFolder folder = project.getProject().getFolder(
//				getModuleRoot().getElementName() + "/");
//		String name = IBasicDataRestypes.PackageDefineResorceName;
//		IFile file = folder.getFile(name);
//		if (!file.exists()) {
//			if (converter instanceof IModelConverter2) {
//				try {
//
//					IARESResource resource = (IARESResource) ARESCore
//							.create(file);
//					file.create(
//							new ByteArrayInputStream(
//									((IModelConverter2) converter).write(
//											resource, info)), true, null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} else {
//				try {
//					ByteArrayOutputStream bos = new ByteArrayOutputStream();
//					converter.write(bos, info);
//					file.create(new ByteArrayInputStream(bos.toByteArray()),
//							true, null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return file;
//	}

	
	private PackageDefine createPackageDefine(String path){
		NewBasicDataWizard wizard = (NewBasicDataWizard) getWizard();
		
		PackageDefine defineObj = null;
		switch (wizard.modeDfine.mode) {
		case NewBasicDataWizard.MODE_SINGLE:
			SingleTable table = BasicdataFactory.eINSTANCE.createSingleTable();
			table.setMaster(wizard.modeDfine.single_masterTable);
			defineObj = table;
			break;
		case NewBasicDataWizard.MODE_MASTERSLAVE:
			MasterSlaveTable mtable = BasicdataFactory.eINSTANCE.createMasterSlaveTable();
			mtable.setMaster(wizard.modeDfine.MS_masterTable);
			mtable.setSlave(wizard.modeDfine.MS_slaveTable);
			defineObj = mtable;
			break;
		case NewBasicDataWizard.MODE_MASTERSLAVELINK:
			MasterSlaveLinkTable mltable = BasicdataFactory.eINSTANCE.createMasterSlaveLinkTable();
			mltable.setMaster(wizard.modeDfine.MSL_masterTable);
			mltable.setSlave(wizard.modeDfine.MSL_slaveTable);
			mltable.setLink(wizard.modeDfine.MSL_linkTable);
			defineObj = mltable;
			break;
		default:
			break;
		}
		
		defineObj.setType(wizard.modeDfine.inputType);
		defineObj.setUrl(path);
		
		return defineObj;
	}
	
	/**
	 * 创建资源模型
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Object createBasicDataInfo(String url)throws Exception{
		 	PackageDefine tableDefine = createPackageDefine(url);
			return BasicDataEpackageFactory.eINSTANCE.createInstance(getProject(), tableDefine);
	}
	
	
	
//	/**
//	 * 保存配置
//	 * @param define
//	 * @throws Exception
//	 */
//	private void savePackageDefine(PackageDefine define)  throws Exception{
//		//基本数据配置文件
//		IFile file = project.getProject().getFile(
//				String.format("%s/%s", getModuleRoot().getElementName(),
//						IBasicDataRestypes.PackageDefineResorceName));
//		if (!file.exists()) {
//			//如果不存在就创建
//			createEpacakgeDefineResource();
//		}
//		
//		try {
//			IARESResource resource = (IARESResource) ARESCore.create(file);
//			EpacakgeDefineList defineList = resource.getInfo(EpacakgeDefineList.class);
//
//			for(int i = defineList.getItems().size() - 1; i >= 0; i--){
//				if(StringUtils.equals(defineList.getItems().get(i).getUrl(), define.getUrl())){
//					defineList.getItems().remove(i);
//				}
//			}
//	
//			defineList.getItems().add(define);
//			resource.save(defineList, true, new NullProgressMonitor());
//		} catch (Exception e) {
//			String mesage = String.format("创建基础数据配置信息失败,详细信息:%s", e.getMessage());
//			logger.error(mesage,e);
//			throw new Exception(mesage);
//		}
//	}

	@Override
	public boolean validate() {
		try {
			IARESResource res = getProject().findResource(IBasicDataRestypes.PackageDefine, IBasicDataRestypes.PackageDefine);
			if(res != null && res.getResource() != null){
				IFile file = (IFile) res.getResource();
				if(file.exists() && file.isReadOnly()){
					setErrorMessage("表关联信息资源为只读状态，无法新建基础数据！");
					return false;
				}
			}
		} catch (ARESModelException e1) {
			e1.printStackTrace();
		}
		
		if (!super.validate()) {
			return false;
		}
		
		try {
			getBasicDataType();
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * 获取类型
	 * @return
	 * @throws Exception
	 */
	public String getBasicDataType() throws Exception{
		String type = StringUtils.defaultString(getProject().getProjectProperty().getString(IBasicDataEpacakgeConstant.Property_Basic_Data_type_ID));
		if(StringUtils.isEmpty(type)){
			throw new Exception("项目属性中没有指定[基础数据类型]");
		}
		return type;
	}

	public IARESProject getProject() {
		return this.project;
	}

}
