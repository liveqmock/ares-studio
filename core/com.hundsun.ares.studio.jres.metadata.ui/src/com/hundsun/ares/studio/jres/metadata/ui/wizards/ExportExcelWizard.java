/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.metadata.ui.wizards;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.IProgressService;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.metadata.ui.Language;
import com.hundsun.ares.studio.jres.metadata.ui.LanguageRegister;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IHeaderSorter;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList;
import com.hundsun.ares.studio.ui.util.DialogHelper;

/**
 * @author gongyf
 *
 */
public class ExportExcelWizard extends Wizard implements IExportWizard {

	private IWorkbench workbench;
	private IStructuredSelection selection;
	private ExportExcelWizardPage onePage;
	
	/**
	 * 
	 */
	public ExportExcelWizard() {
		setWindowTitle("导出元数据");
		setNeedsProgressMonitor(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}

	@Override
	public void createPageControls(Composite pageContainer) {
		// TODO Auto-generated method stub
		super.createPageControls(pageContainer);
		onePage.getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/metadataFolder.gif").createImage());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		addPage(onePage = new ExportExcelWizardPage("one", selection));
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
				monitor.beginTask("开始导出。。。", 4);
				
				OutputStream excelStream = null;
				
				try {
					excelStream = new FileOutputStream(onePage.getExcelFile());
					
					Map< String, List< List<String> > > contents = new HashMap<String, List<List<String>>>();
					
					Language[] languages = LanguageRegister.getInstance().getRegisteredLanguages();
					String[] languageIds = new String[languages.length];
					String[] languageTitles = new String[languages.length];
					for (int i = 0; i < languageIds.length; i++) {
						languageIds[i] = languages[i].getId();
						languageTitles[i] = languages[i].getName();
					}
					
					IARESProject project = ARESCore.create(onePage.getSelectedProject());
					
					IHeaderSorter descriptionLast = new IHeaderSorter() {
						
						@Override
						public void sort(List<String> header) {
							int index = header.indexOf("说明");
							if (index >= 0) {
								header.remove(index);
								header.add("说明");
							}
						}
					};
					List<List<String>> revHisTotles = new ArrayList<List<String>>();
					// 标准字段
					{
						
						monitor.subTask("标准字段。。。");
						IARESResource resource = project.findResource(IMetadataResType.StdField, IMetadataResType.StdField);
						StandardFieldList list = resource.getInfo(StandardFieldList.class);
						
						List<List<String>> stdHises = getRevHises(resource, list);
						stdHises.remove(0);
						revHisTotles.addAll(stdHises);
						
						List< List<String> > table = POIUtils.exportExcelStringTable(list, MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS,MetadataPackage.Literals.STANDARD_FIELD,
								new String[]{"字段名","字段类型","字段名称","字典条目","说明"}, 
								new EStructuralFeature[]{
									MetadataPackage.Literals.NAMED_ELEMENT__NAME, 
									MetadataPackage.Literals.STANDARD_FIELD__DATA_TYPE,
									MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME,
									MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE,
									MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION}, 
								true, 
								ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY, resource, null);
						
						contents.put("标准字段", table);
						
						monitor.worked(1);
					}
					
					// 默认值
					{
						monitor.subTask("默认值。。。");
						IARESResource resource = project.findResource(IMetadataResType.DefValue, IMetadataResType.DefValue);
						TypeDefaultValueList list = resource.getInfo(TypeDefaultValueList.class);
						
						List<List<String>> devHises = getRevHises(resource, list);
						devHises.remove(0);
						revHisTotles.addAll(devHises);
						
						List< List<String> > table = POIUtils.exportExcelStringTable(list, MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS, MetadataPackage.Literals.TYPE_DEFAULT_VALUE,
								new String[]{"默认值名", "名称", "说明"}, 
								new EStructuralFeature[]{MetadataPackage.Literals.NAMED_ELEMENT__NAME, MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME, MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION}, 
								true, 
								languageTitles, languageIds, resource, descriptionLast);
						
						contents.put("默认值", table);
						
						monitor.worked(1);
					}
					
					
					// 标准数据类型
					{
						monitor.subTask("标准数据类型。。。");
						IARESResource resource = project.findResource(IMetadataResType.StdType, IMetadataResType.StdType);
						StandardDataTypeList list = resource.getInfo(StandardDataTypeList.class);
						
						List<List<String>> styHises = getRevHises(resource, list);
						styHises.remove(0);
						revHisTotles.addAll(styHises);
						
						List< List<String> > table = POIUtils.exportExcelStringTable(list, MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS,MetadataPackage.Literals.STANDARD_DATA_TYPE,
								new String[]{"类型名", "名称", "说明"}, 
								new EStructuralFeature[]{MetadataPackage.Literals.NAMED_ELEMENT__NAME, MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME, MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION}, 
								true, 
								languageTitles, languageIds, resource, descriptionLast);
						
						contents.put("标准数据类型", table);
						
						monitor.worked(1);
					}
					
					// 业务数据类型
					{
						monitor.subTask("业务数据类型。。。");
						IARESResource resource = project.findResource(IMetadataResType.BizType, IMetadataResType.BizType);
						BusinessDataTypeList list = resource.getInfo(BusinessDataTypeList.class);
						
						List<List<String>> bizHises = getRevHises(resource, list);
						bizHises.remove(0);
						revHisTotles.addAll(bizHises);
						
						List< List<String> > table = POIUtils.exportExcelStringTable(list, MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS,MetadataPackage.Literals.BUSINESS_DATA_TYPE,
								new String[]{"类型名","类型名称","标准类型","长度","精度","默认值","说明"}, 
								new EStructuralFeature[]{
									MetadataPackage.Literals.NAMED_ELEMENT__NAME, 
									MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME,
									MetadataPackage.Literals.BUSINESS_DATA_TYPE__STD_TYPE,
									MetadataPackage.Literals.BUSINESS_DATA_TYPE__LENGTH,
									MetadataPackage.Literals.BUSINESS_DATA_TYPE__PRECISION,
									MetadataPackage.Literals.BUSINESS_DATA_TYPE__DEFAULT_VALUE,
									MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION}, 
								true, 
								ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY, resource, null);
						
						contents.put("业务数据类型", table);
						
						monitor.worked(1);
					}
					
					POIUtils.putExcelString(excelStream, "元数据规范文档" ,contents,revHisTotles,
							new String[]{"标准字段", "业务数据类型","标准数据类型","默认值"}, 
							new int[]{1,1,1,1}, 
							new int[]{1,1,1,1});
					
					monitor.done();
				} catch (Exception e) {
					e.printStackTrace();
					throw new InvocationTargetException(e);
				} finally {
					IOUtils.closeQuietly(excelStream);
				}
				
			}
		};
		
		
		try {
//			getContainer().run(true, false, runnable);
			MessageDialog msgdialog = null;
			try {
				IProgressService progress = MetadataUI.getDefault().getWorkbench()
						.getProgressService();
				progress.run(true, false, runnable);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				msgdialog = new MessageDialog(null, "导出失败", null,
						"导出文件已被打开或无写入权限，请重新命名或关闭后再导出！", MessageDialog.WARNING,
						new String[] { "确定" }, 0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (null != msgdialog){
				msgdialog.open();
			}else {
				String path = onePage.getExcelFile().getPath();
				msgdialog = new MessageDialog(null, "导出成功", null,
						String.format("导出路径为%s,是否打开文件？", path), MessageDialog.INFORMATION, new String[] {
								"确定","取消"}, 0);
				if(Window.OK == msgdialog.open()){
					openFile(path);
				}
				return true;
			}
		} catch (Exception e) {
			DialogHelper.showErrorMessage(e.getMessage());
		} 
		return false;
	}
	
	/**
	 * 
	 * 获取修订信息
	 * 
	 * @param resource
	 * @param info
	 * @return
	 */
	private List<List<String>> getRevHises(IARESResource resource , MetadataResourceData info){
		List<List<String>> revHises = POIUtils
		.exportExcelStringTable(
				info,
				CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES,
				CorePackage.Literals.REVISION_HISTORY,
				new String[] {"修改版本", "修订日期","修改内容","修改原因", "修改单","申请人","负责人","备注"},
				new EStructuralFeature[] {
						CorePackage.Literals.REVISION_HISTORY__VERSION,
						CorePackage.Literals.REVISION_HISTORY__MODIFIED_DATE,
						CorePackage.Literals.REVISION_HISTORY__MODIFIED,
						CorePackage.Literals.REVISION_HISTORY__MODIFIED_REASON,
						CorePackage.Literals.REVISION_HISTORY__ORDER_NUMBER,
						CorePackage.Literals.REVISION_HISTORY__CHARGER,
						CorePackage.Literals.REVISION_HISTORY__MODIFIED_BY,
						CorePackage.Literals.REVISION_HISTORY__COMMENT},
				false, ArrayUtils.EMPTY_STRING_ARRAY,
				ArrayUtils.EMPTY_STRING_ARRAY,
				resource, null);
		
		return revHises;
	}
	
	private void openFile(final String path) {    
	    Runtime rn = Runtime.getRuntime(); 
	    String cmd="cmd.exe /c start \"\" \"" + path + "\"";  
	    try {    
	        rn.exec(cmd);  
	    } catch (Exception e) { 
	    	e.printStackTrace();
	    }    
	}

}
