/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.database.oracle.ui.actions;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.IProgressService;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.database.oracle.ui.OracleUI;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.dialog.ExportDialog;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IHeaderSorter;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;

/**
 * @author yanwj06282
 * 
 */
public class ExportTableSpaceAction extends Action implements IUpdateAction {
	public static final String CV_EXPORT_TABLE_SPACE = "cv.export.tablespace";
	IARESResource resource;
	IWorkbenchPartSite site;

	public ExportTableSpaceAction(IARESResource resource, IWorkbenchPartSite site) {
		this.resource = resource;
		this.site = site;

		setText("导出");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/export_wiz.gif"));
		setId(CV_EXPORT_TABLE_SPACE);

		setEnabled(true);
	}

	@Override
	public void run() {
		String dialogTitle = "导出表空间";
		String dialogMessage = "将项目中的表空间的导出(Excel文件).";
		Image dialogImage = AbstractUIPlugin.imageDescriptorFromPlugin(OracleUI.PLUGIN_ID, "icons/oracle_tablespace.png").createImage();
		ExportDialog dialog = new ExportDialog(site.getShell(),dialogTitle,dialogImage,dialogMessage);
		dialog.open();

		if (dialog.getReturnCode() != Window.OK)
			return;

		final String path = dialog.getFilePath();

		IRunnableWithProgress operation = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException{
				monitor.beginTask("导出表空间。。。", IProgressMonitor.UNKNOWN);

				OutputStream excelStream = null;
				List<List<String>> table = null;
				Map<String, List<List<String>>> contents = new LinkedHashMap<String, List<List<String>>>();
				try {
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
					
					// 文档第一页大标题
					String title = null;
					
					OracleSpaceResourceData tableSpace = resource.getInfo(OracleSpaceResourceData.class);
					if (tableSpace == null) {
						return;
					}
					
					//加载修订记录
					List<List<String>> revHises = POIUtils
					.exportExcelStringTable(
							tableSpace,
							CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES,
							CorePackage.Literals.REVISION_HISTORY,
							new String[] {"修改版本", "修改日期","修改内容","修改原因", "修改单","申请人","负责人","备注"},
							new EStructuralFeature[] {
									CorePackage.Literals.REVISION_HISTORY__VERSION,
									CorePackage.Literals.REVISION_HISTORY__MODIFIED_DATE,
									CorePackage.Literals.REVISION_HISTORY__MODIFIED,
									CorePackage.Literals.REVISION_HISTORY__MODIFIED_REASON,
									CorePackage.Literals.REVISION_HISTORY__ORDER_NUMBER,
									CorePackage.Literals.REVISION_HISTORY__MODIFIED_BY,
									CorePackage.Literals.REVISION_HISTORY__CHARGER,
									CorePackage.Literals.REVISION_HISTORY__COMMENT},
							false, ArrayUtils.EMPTY_STRING_ARRAY,
							ArrayUtils.EMPTY_STRING_ARRAY,
							resource, null);
					
					table = POIUtils
							.exportExcelStringTable(
									tableSpace,
									OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA__SPACES,
									OraclePackage.Literals.TABLE_SPACE,
									new String[] { "表空间名", "逻辑名", "中文名","数据库用户","文件名","大小","备注" },
									new EStructuralFeature[] {
											OraclePackage.Literals.TABLE_SPACE__NAME,
											OraclePackage.Literals.TABLE_SPACE__LOGIC_NAME,
											OraclePackage.Literals.TABLE_SPACE__CHINESE_NAME,
											OraclePackage.Literals.TABLE_SPACE__USER,
											OraclePackage.Literals.TABLE_SPACE__FILE,
											OraclePackage.Literals.TABLE_SPACE__SIZE,
											OraclePackage.Literals.TABLE_SPACE__DESCRIPTION},
									true, new String[0], new String[0],
									resource, descriptionLast);
					contents.put("表空间", table);
					
					table = POIUtils.exportExcelStringTable(
							tableSpace,
							OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA__RELATIONS,
							OraclePackage.Literals.TABLE_SPACE_RELATION,
							new String[] { "主表空间", "索引表空间"},
							new EStructuralFeature[] {
									OraclePackage.Literals.TABLE_SPACE_RELATION__MAIN_SPACE,
									OraclePackage.Literals.TABLE_SPACE_RELATION__INDEX_SPACE},
							true, new String[0], new String[0],
							resource, descriptionLast);
					contents.put("关联表空间", table);
					
					title = "数据库表空间";
					
					//根据contents中的内容决定需要有多少个sheet页
					int[] starts = new int[contents.keySet().size()];
					for (int i = 0; i < starts.length ; i++) {
						starts[i] = 1;//sheet页的起始位置
					}
					excelStream = new FileOutputStream(path);
					POIUtils.putExcelString(excelStream, title, contents,revHises, contents
							.keySet().toArray(new String[0]), starts, starts);
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new InvocationTargetException(e);
				} finally {
					IOUtils.closeQuietly(excelStream);
				}
				monitor.done();
			}
		};
		MessageDialog msgdialog = null;
		try {
			IProgressService progress = MetadataUI.getDefault().getWorkbench()
					.getProgressService();
			progress.run(true, false, operation);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			String message = "导出文件已被打开或无写入权限，请重新命名或关闭后再导出！";
			Throwable exception = e.getTargetException();
			if(exception instanceof ExportExcelException){
				message = ((ExportExcelException)exception).getMessage();
			}
			msgdialog = new MessageDialog(site.getShell(), "导出失败", null,
					message, MessageDialog.WARNING,
					new String[] { "确定" }, 0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (null != msgdialog)
			msgdialog.open();
		else {
			msgdialog = new MessageDialog(null, "导出成功", null,
					String.format("导出路径为%s,是否打开文件？", path), MessageDialog.INFORMATION, new String[] {
							"确定","取消"}, 0);
			if(Window.OK == msgdialog.open()){
				openFile(path);
			}
		}
	}
	//命令行打开文件
	private void openFile(final String path) {    
	    Runtime rn = Runtime.getRuntime();    
	    String cmd="cmd.exe /c start \"\" \"" + path + "\"";  
	    try {    
	        rn.exec(cmd);  
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }    
	}
	
	/***
	 *自定义异常，在最后导出失败弹出框中显示自定义消息。
	 */
	protected class ExportExcelException extends Exception{

		private static final long serialVersionUID = 988537005828794522L;
		
		public ExportExcelException(String message) {
			super(message);
		}
		
	}

	@Override
	public void update() {
		
	}
}
