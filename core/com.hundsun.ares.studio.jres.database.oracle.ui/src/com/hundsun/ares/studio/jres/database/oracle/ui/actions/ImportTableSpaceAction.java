/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.database.oracle.ui.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.database.oracle.ui.OracleUI;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.dialog.ImportDialog;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.ImportMetaDataHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;

/**
 * @author qinyuan
 *
 */
public class ImportTableSpaceAction extends ColumnViewerAction{

	public static final String CV_IMPORT_TABLE_SPACE = "cv.import.tablespace";
	
	IARESResource resource;
	
	private String sheetName;
	
	private String path = "";
	
	private int importType;
	
	private static final String sheetName1 = "表空间";
	private static final String sheetName2 = "数据库对象";
	
	public ImportTableSpaceAction(IARESResource resource,TreeViewer viewer,
			EditingDomain editingDomain) {
		super(viewer, editingDomain);
		
		setText("导入");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/import_wiz.gif"));
		setId(CV_IMPORT_TABLE_SPACE);
		this.resource =resource;
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		OracleSpaceResourceData owner = (OracleSpaceResourceData) getViewer().getInput();
		if(null == owner)
			return false;
		else 
			return !((TransactionalEditingDomain)getEditingDomain()).isReadOnly(owner.eResource());
	}
	
	@Override
	public void run() {
		String dialogTitle = "导入表空间";
		String dialogMessage = "将已存在表空间的Excel文件导入到项目中.";
		Image dialogImage = AbstractUIPlugin.imageDescriptorFromPlugin(OracleUI.PLUGIN_ID, "icons/oracle_tablespace.png").createImage();
		ImportDialog dialog = new ImportDialog(((TreeViewer)getViewer()).getTree().getShell(), true,dialogTitle,dialogImage,dialogMessage){
			@Override
			public boolean validate(String fileText) {
				File file = new File(fileText);
				if (!file.exists()) {
					setErrorMessage("指定文件不存在!");
					importButton.setEnabled(false);
					return false;
				}
				List<String> types = new ArrayList<String>();
				types.add(sheetName1);
				types.add(sheetName2);
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
		
		importType = dialog.getImportType();
		
		if (importType == ImportDialog.IMPORT_TYPE_COVER) {
			MessageDialog msg = new MessageDialog(((TreeViewer)getViewer()).getTree().getShell(), 
					"提示", null, "导入将删除原有数据，是否继续？", MessageDialog.QUESTION, new String[]{"是","否"}, 0);
			msg.open();
			if(msg.getReturnCode() != Window.OK){
				throw new OperationCanceledException();
			}
		}
		
		Job job = new Job("导入表空间资源") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("导入表空间资源。。。", IProgressMonitor.UNKNOWN);
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
				List<EObject> tableSpaces = null;
				List<EObject> tsrs = null;
				Map< String, List< List<String> > > contents = null;
				ImportMetaDataHelper helper = ImportMetaDataHelper.getInstance();
				{
					try {
						excelStream = new FileInputStream(path);
						OracleSpaceResourceData space = (OracleSpaceResourceData) getViewer().getInput();
						if(null == space)
							throw new OperationCanceledException();
						
						//删除原数据
						if (importType == ImportDialog.IMPORT_TYPE_COVER) {
							space.getRelations().clear();
							space.getSpaces().clear();
							space.getHistories().clear();
						}
						
						HSSFWorkbook workBook = new HSSFWorkbook(excelStream);
						
						List<List<String>>  revHisContents = POIUtils.getAresContents(workBook, "版本页", 11, 1);
						List<EObject> revHisInfos = helper.getRevHisesInfos(resource, revHisContents);
						
						if (StringUtils.equals(sheetName, sheetName1)) {
							contents = POIUtils.getExcelStringForCate(workBook, 
									new String[]{sheetName,"关联表空间"}, new int[]{1 ,1}, new int[]{1 ,1});
							table = contents.get(sheetName);
							tableSpaces = POIUtils.importExcelStringTable(table, OraclePackage.Literals.TABLE_SPACE,
									new String[]{"表空间名", "逻辑名", "中文名","数据库用户" ,"文件名" ,"大小" ,"备注"}, 
									new EStructuralFeature[]{
									OraclePackage.Literals.TABLE_SPACE__NAME,
									OraclePackage.Literals.TABLE_SPACE__LOGIC_NAME,
									OraclePackage.Literals.TABLE_SPACE__CHINESE_NAME,
									OraclePackage.Literals.TABLE_SPACE__USER,
									OraclePackage.Literals.TABLE_SPACE__FILE,
									OraclePackage.Literals.TABLE_SPACE__SIZE,
									OraclePackage.Literals.TABLE_SPACE__DESCRIPTION}, 
									true, 
									new String[0], new String[0], resource);
							
							table = contents.get("关联表空间");
							tsrs = POIUtils.importExcelStringTable(table, OraclePackage.Literals.TABLE_SPACE_RELATION,
									new String[]{ "主表空间", "索引表空间"}, 
									new EStructuralFeature[]{
									OraclePackage.Literals.TABLE_SPACE_RELATION__MAIN_SPACE,
									OraclePackage.Literals.TABLE_SPACE_RELATION__INDEX_SPACE}, 
									true, 
									new String[0], new String[0], resource);
							
						}else if (StringUtils.equals(sheetName, sheetName2)) {
							contents = POIUtils.getExcelStringForCate(workBook, 
									new String[]{sheetName}, new int[]{1}, new int[]{1});
							table = contents.get(sheetName);

							tableSpaces = POIUtils.importExcelStringTable(table, OraclePackage.Literals.TABLE_SPACE,
									new String[]{"数据库或表空间", "数据库逻辑名", "数据库中文名","数据库分布" ,"FILENAME" ,"SIZE" ,"数据库备注"}, 
									new EStructuralFeature[]{
									OraclePackage.Literals.TABLE_SPACE__NAME,
									OraclePackage.Literals.TABLE_SPACE__LOGIC_NAME,
									OraclePackage.Literals.TABLE_SPACE__CHINESE_NAME,
									OraclePackage.Literals.TABLE_SPACE__USER,
									OraclePackage.Literals.TABLE_SPACE__FILE,
									OraclePackage.Literals.TABLE_SPACE__SIZE,
									OraclePackage.Literals.TABLE_SPACE__DESCRIPTION}, 
									true, 
									new String[0], new String[0], resource);
							
							try {
								table.get(0).set(10, "历史表空间");
								table.get(0).set(11, "历史索引表空间");
								table.get(0).set(12, "归档表空间");
								table.get(0).set(13, "归档索引表空间");
								table.get(0).set(14, "冗余表空间");
								table.get(0).set(15, "清算表空间");
								table.get(0).set(16, "清算索引表空间");
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							for (int i = 1; i < table.size(); i++) {
								if (StringUtils.isBlank(table.get(i).get(10))
										&& StringUtils.isBlank(table.get(i).get(11))
										&& StringUtils.isBlank(table.get(i).get(12))
										&& StringUtils.isBlank(table.get(i).get(13))
										&& StringUtils.isBlank(table.get(i).get(14))
										&& StringUtils.isBlank(table.get(i).get(15))
										&& StringUtils.isBlank(table.get(i).get(16))) {
									table.remove(i);
									i--;
								}
							}
							
							tsrs = POIUtils.importExcelStringTable(table, OraclePackage.Literals.TABLE_SPACE_RELATION,
									new String[]{ "数据库或表空间", "索引所在数据库"}, 
									new EStructuralFeature[]{
									OraclePackage.Literals.TABLE_SPACE_RELATION__MAIN_SPACE,
									OraclePackage.Literals.TABLE_SPACE_RELATION__INDEX_SPACE}, 
									true, 
									new String[0], new String[0], resource);
						}
						
						// 添加到资源
						space.getSpaces().addAll((Collection<? extends TableSpace>) tableSpaces);
						space.getRelations().addAll((Collection<? extends TableSpaceRelation>) tsrs);
						space.getHistories().addAll((Collection<? extends RevisionHistory>) revHisInfos);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						IOUtils.closeQuietly(excelStream);
					}
				}
			}
		};
		return cc;
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
