/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.dialog.ImportDialog;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.ImportMetaDataHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;

/**
 * @author qinyuan
 *
 */
public class ImportIDRangeAction extends ColumnViewerAction{

	IARESResource resource;
	
	private String path = "";
	
	private String sheetName;
	
	private MetadataResourceData info;
	
	/**
	 * @param viewer
	 * @param editingDomain
	 */
	public ImportIDRangeAction(IARESResource resource,TreeViewer viewer,EObject info,
			EditingDomain editingDomain) {
		super(viewer, editingDomain);
		
		setText("导入");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/import_wiz.gif"));
		setId(IMetadataActionIDConstant.CV_IMPORT_METADATA);
		this.resource =resource;
		this.info = (MetadataResourceData) info;
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		if(null == info)
			return false;
		else {
			return !((TransactionalEditingDomain)getEditingDomain()).isReadOnly(info.eResource());
		}
	}
	
	@Override
	public void run() {
		String dialogTitle = "导入对象号范围";
		String dialogMessage = "将已存在对象号范围的Excel文件导入到项目中.";
		Image dialogImage = AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/IDRange.gif").createImage();
		ImportDialog dialog = new ImportDialog(((TreeViewer)getViewer()).getTree().getShell(), false,dialogTitle,dialogImage,dialogMessage){
			@Override
			public boolean validate(String fileText) {
				File file = new File(fileText);
				if (!file.exists()) {
					setErrorMessage("指定文件不存在!");
					importButton.setEnabled(false);
					return false;
				}
				List<String> types = new ArrayList<String>();
				if (resource.getType().equals(IMetadataResType.IDRange)) {
					types.add("对象号范围");
				}else {
					types.add(getResorceType(resource));
				}
				boolean status = false;
				try {
					status = checkExcel(types, new FileInputStream(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				if (!status) {
					setErrorMessage("文件格式错误! 请参考  \"导出\"功能文档");
					importButton.setEnabled(false);
					return false;
				}
				filePath = fileText;
				setErrorMessage(null);
				importButton.setEnabled(true);
				return true;
			}
			
		};
		dialog.open();
		
		if(dialog.getReturnCode() != Window.OK){
			throw new OperationCanceledException();
		}
		
		path = dialog.getFilePath();
		
		Job job = new Job("导入对象号取值范围资源") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("导入对象号取值范围资源。。。", IProgressMonitor.UNKNOWN);
				Command command = createCommand();
				if (command != null) {
					if ( getViewer().isCellEditorActive()) {
						 getViewer().cancelEditing();
					}
					
					getEditingDomain().getCommandStack().execute(command);
					clearCommand();

					// 让表格选择影响操作的对象
					Command mostRecentCommand = getEditingDomain().getCommandStack()
							.getMostRecentCommand();
					if (mostRecentCommand != null) {
						setSelectionToViewer(mostRecentCommand.getAffectedObjects());
					}
				}
				monitor.done();
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();
		
		
	}
	
	@Override
	protected Command createCommand() {
		
		RecordingCommand cc = new RecordingCommand((TransactionalEditingDomain)getEditingDomain()) {
			
			@SuppressWarnings("unchecked")
			@Override
			protected void doExecute() {
				
				InputStream excelStream = null;
				List<List<String>> table = null;
				List<EObject> items = null;
				Map< String, List< List<String> > > contents = null;
				ImportMetaDataHelper helper = ImportMetaDataHelper.getInstance();
				{
					try {
						excelStream = new FileInputStream(path);
						if(null == info)
							throw new OperationCanceledException();
						
						HSSFWorkbook workBook = new HSSFWorkbook(excelStream);
						if (resource.getType().equals(IMetadataResType.IDRange)) {
							
							contents = POIUtils.getExcelString(workBook, 
									new String[]{"对象号范围"}, new int[]{1}, new int[]{1});
							table = contents.get("对象号范围");
							items = helper.getIdRangeItems(resource, table);
						}
						for (EObject obj : items) {
							try {
								if (obj instanceof IDRangeItem) {
									StringBuffer sb = new StringBuffer();
									String rangeName = ((IDRangeItem) obj).getName();
									String seq = "/";
									if (StringUtils.indexOf(rangeName, "\\")>-1) {
										seq = "\\";
									}
									String[] urls = StringUtils.split(rangeName , seq);
									sb.append(urls[0]);
									sb.append(tranCH2En(urls, resource.getARESProject()));
//									for (int i = 1;i<urls.length;i++) {
//										sb.append("/");
//										if (!isCH(urls[i])) {
//											sb.append("m"+StringUtils.replace(String.valueOf(urls[i].hashCode()), "-", ""));
//											continue;
//										}
//										sb.append(urls[i]);
//									}
									((IDRangeItem) obj).setName(getModuleEN(sb.toString() , resource.getARESProject()));
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						// 添加到资源
						List<List<String>>  revHisContents = POIUtils.getAresContents(workBook, "版本页", 11, 1);
						List<EObject> revHisInfos = helper.getRevHisesInfos(resource, revHisContents);
						if(null != items && items.size() > 0 ) {
							info.getItems().clear();
							info.getItems().addAll(items);
							info.getHistories().clear();//修改记录也需要先清除
							info.getHistories().addAll((Collection<? extends RevisionHistory>) revHisInfos);
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		return cc;
	}
	
	private String tranCH2En(String[] urls,IARESProject project) throws Exception{
		StringBuffer sb = new StringBuffer();
		IARESModuleRoot root = project.getModuleRoot(getModuleRootByCHName(urls[0], project));
		if (root != null) {
			for (int i = 1; i < urls.length; i++) {
				for(IARESModule module : root.getModules()){
					IARESResource property = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
					if (property != null && property.exists()) {
						try {
							ModuleProperty info = property.getInfo(ModuleProperty.class);
							if (info != null) {
								Object obj = info.getValue(ICommonModel.CNAME);
								if (obj != null) {
									if (StringUtils.equals(urls[i], obj.toString())) {
										sb.append("/");
										sb.append(module.getShortName());
										break;
									}
								}
							}
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			for(String enname : urls){
				root.getModule(enname);
			}
		}
		return sb.toString();
	}
	
	private boolean isCH(String key){
		if(key.matches("(^[a-z_][a-z0-9_]{0,49}$)")){
			return true;
		}
		return false;
	}
	
	private String getModuleRootByCHName(String chname ,IARESProject project) throws ARESModelException{
		if (StringUtils.equals(chname, "数据库")) {
			return "database";
		}else if (StringUtils.equals(chname, "业务逻辑")) {
			if (project.getModuleRoot("business") != null && project.getModuleRoot("business").exists()) {
				return "business";
			}else if(project.getModuleRoot("service") != null && project.getModuleRoot("service").exists()) {
				return "service";
			}
		}else if (StringUtils.equals(chname, "原子")) {
			return "atom";
		}else if (StringUtils.equals(chname, "过程")) {
			return "procedure";
		}else if (StringUtils.equals(chname, "逻辑")) {
			return "logic";
		}else if (StringUtils.equals(chname, "对象")) {
			return "objects";
		}
		return StringUtils.EMPTY;
	}
	
	private String getModuleEN(String key ,IARESProject project) throws ARESModelException{
		if (StringUtils.startsWith(key ,"数据库/")) {
			return StringUtils.replaceOnce(key, "数据库/", "database/");
		}else if (StringUtils.startsWith(key ,"数据库\\")) {
			return StringUtils.replaceOnce(key, "数据库\\", "database\\");
		}else if (StringUtils.startsWith(key ,"业务逻辑/")) {
			if (project.getModuleRoot("business") != null && project.getModuleRoot("business").exists()) {
				return StringUtils.replaceOnce(key, "业务逻辑/", "business/");
			}else if(project.getModuleRoot("service") != null && project.getModuleRoot("service").exists()) {
				System.out.println("asdasasasdad");
				return StringUtils.replaceOnce(key, "业务逻辑/", "service/");
			}
		}else if (StringUtils.startsWith(key ,"业务逻辑\\")) {
			if (project.getModuleRoot("business") != null && project.getModuleRoot("business").exists()) {
				return StringUtils.replaceOnce(key, "业务逻辑\\", "business\\");
			}else if(project.getModuleRoot("service") != null && project.getModuleRoot("service").exists()) {
				return StringUtils.replaceOnce(key, "业务逻辑\\", "service\\");
			}
		}else if (StringUtils.startsWith(key ,"原子/")) {
			return StringUtils.replaceOnce(key, "原子/", "atom/");
		}else if (StringUtils.startsWith(key ,"原子\\")) {
			return StringUtils.replaceOnce(key, "原子\\", "atom\\");
		}else if (StringUtils.startsWith(key ,"过程/")) {
			return StringUtils.replaceOnce(key, "过程/", "procedure/");
		}else if (StringUtils.startsWith(key ,"过程\\")) {
			return StringUtils.replaceOnce(key, "过程\\", "procedure\\");
		}else if (StringUtils.startsWith(key ,"对象/")) {
			return StringUtils.replaceOnce(key, "对象/", "objects/");
		}else if (StringUtils.startsWith(key ,"对象\\")) {
			return StringUtils.replaceOnce(key, "对象\\", "objects\\");
		}else if (StringUtils.startsWith(key ,"逻辑/")) {
			return StringUtils.replaceOnce(key, "逻辑/", "logic/");
		}else if (StringUtils.startsWith(key ,"逻辑\\")) {
			return StringUtils.replaceOnce(key, "逻辑\\", "logic\\");
		}
		return key;
	}
	
	/**
	 * 获取资源类型
	 * @return
	 */
	protected String getResorceType(IARESResource resource) {
		return "";
	}

	/**
	 * 导入其他元数据信息
	 * 重载此方法一定要先重载方法#getResorceType
	 * @param excelStream 
	 * @param contents
	 * @param table
	 * @param items
	 * @param owner
	 * @param resource2
	 */
	protected void importOtherMetadataInfo(
			InputStream excelStream, Map<String, List<List<String>>> contents, List<List<String>> table,
			List<EObject> items, MetadataResourceData<MetadataItem> owner,
			IARESResource resource) throws Exception{
		
	}

	private boolean checkExcel (List<String> sheetNames , InputStream excelStream){
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(excelStream);
			for (String sheetName : sheetNames) {
				HSSFSheet sheet = workBook.getSheet(sheetName);
				this.sheetName = sheetName;
				if (sheet != null) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public IARESResource getResource() {
		return resource;
	}
}
