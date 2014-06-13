/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.metadata.ui.wizards;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.IProgressService;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.dialog.ImportDialog;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList;
import com.hundsun.ares.studio.ui.util.DialogHelper;

/**
 * @author gongyf
 *
 */
public class ImportExcelWizard extends Wizard implements IImportWizard {

	private IWorkbench workbench;
	private IStructuredSelection selection;
	private ImportExcelWizardPage onePage;
	private String[] sheetNames = new String[]{"标准字段", "业务数据类型","标准数据类型","默认值"};
	
	/**
	 * 
	 */
	public ImportExcelWizard() {
		setWindowTitle("导入标准字段");
		setNeedsProgressMonitor(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle("导入标准字段");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/stdFieldFile.png").createImage());
		onePage.setErrorMessage("选择需要导入标准字段.");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		addPage(onePage = new ImportExcelWizardPage("one", selection){
			protected void validate() {
				super.validate();
				if (StringUtils.isBlank(getErrorMessage())) {
					boolean status = true;
					try {
						HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(getExcelFile()));
						for (String sheetName : sheetNames) {
							HSSFSheet sheet = workBook.getSheet(sheetName);
							if (sheet == null) {
								status = false;
								break;
							}
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (!status) {
						setErrorMessage("文件格式错误! 请参考  \"元数据定义\"功能文档，此文档必须包含: [标准字段],[业务数据类型],[标准数据类型],[默认值]");
						setPageComplete(false);
					}
				}
			};
		});
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		
		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException,
					InterruptedException {
				monitor.beginTask("导入", 4);
				
				InputStream excelStream = null;
				
				try {
					excelStream = new FileInputStream(onePage.getExcelFile());
					
					HSSFWorkbook workBook = new HSSFWorkbook(excelStream);
					
					Map< String, List< List<String> > > contents = POIUtils.getExcelString(workBook, 
							sheetNames, new int[]{1,1,1,1}, new int[]{1,1,1,1});
					
					List< List<String> > table = null;
					ImportMetaDataHelper helper = ImportMetaDataHelper.getInstance();
					IARESProject project = ARESCore.create(onePage.getSelectedProject());
					int importType = onePage.getImportMode();
					
					IARESResource defRes = project.findResource(IMetadataResType.DefValue, IMetadataResType.DefValue);
					IARESResource stdtRes = project.findResource(IMetadataResType.StdType, IMetadataResType.StdType);
					IARESResource btRes = project.findResource(IMetadataResType.BizType, IMetadataResType.BizType);
					IARESResource stdRes = project.findResource(IMetadataResType.StdField, IMetadataResType.StdField);
					StringBuffer sb = new StringBuffer();
					if (defRes.isReadOnly()) {
						sb.append("默认值 ，");
					}
					if (stdtRes.isReadOnly()) {
						sb.append("标准数据类型 ，");
					}
					if (btRes.isReadOnly()) {
						sb.append("业务数据类型 ，");
					}
					if (stdRes.isReadOnly()) {
						sb.append("标准字段 ，");
					}
					if (StringUtils.isNotBlank(sb.toString())) {
						throw new InterruptedException("元数据导入错误 ，以下资源处于只读状态：[" +StringUtils.substring(sb.toString(), 0, sb.toString().length()-1) + "]");
					}
					// 默认值处理
					{
						monitor.subTask("默认值。。。");
						table = contents.get("默认值");
						if (table != null) {
							TypeDefaultValueList list = null;
							if (importType == ImportDialog.IMPORT_TYPE_COMB) {
								list = defRes.getInfo(TypeDefaultValueList.class);
							}else {
								list = MetadataFactory.eINSTANCE.createTypeDefaultValueList();
							}
							helper.importDefValue(defRes, table,list, monitor);
						}
						monitor.worked(1);
					}
					
					// 标准数据类型
					{
						monitor.subTask("标准数据类型。。。");
						table = contents.get("标准数据类型");
						if (table != null) {
							StandardDataTypeList list = null;
							if (importType == ImportDialog.IMPORT_TYPE_COMB) {
								list = stdtRes.getInfo(StandardDataTypeList.class);
							}else {
								list = MetadataFactory.eINSTANCE.createStandardDataTypeList();
							}
							helper.importStdType(stdtRes, table,list, monitor);
						}
						monitor.worked(1);
					}
					
					// 业务数据类型
					{
						monitor.subTask("业务数据类型。。。");
						table = contents.get("业务数据类型");
						if (table != null) {
							BusinessDataTypeList list = null;
							if (importType == ImportDialog.IMPORT_TYPE_COMB) {
								list = btRes.getInfo(BusinessDataTypeList.class);
							}else {
								list = MetadataFactory.eINSTANCE.createBusinessDataTypeList();
							}
							helper.importBizType(btRes, table,list, monitor);
						}
						monitor.worked(1);
					}
					
					// 标准字段
					{
						monitor.subTask("标准字段。。。");
						table = contents.get("标准字段");
						if (table != null) {
							StandardFieldList list = null;
							if (importType == ImportDialog.IMPORT_TYPE_COMB) {
								list = stdRes.getInfo(StandardFieldList.class);
							}else {
								list = MetadataFactory.eINSTANCE.createStandardFieldList();
							}
							helper.importStdFld(stdRes, table,list, monitor);
						}
						monitor.worked(1);
					}
					monitor.done();
				} catch (Exception e) {
					e.printStackTrace();
					throw new InvocationTargetException(e , e.getMessage());
				} finally {
					IOUtils.closeQuietly(excelStream);
				}
				
			}
		};
		
		try {
			MessageDialog msgdialog = null;
			try {
				IProgressService progress = MetadataUI.getDefault().getWorkbench()
						.getProgressService();
				progress.run(true, false, runnable);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				msgdialog = new MessageDialog(null, "导入失败", null,
						e.getMessage(), MessageDialog.ERROR,
						new String[] { "确定" }, 0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (null != msgdialog){
				msgdialog.open();
			}else {
				return true;
			}
		} catch (Exception e) {
			DialogHelper.showErrorMessage(e.getMessage());
		} 
		return false;
	}

}
