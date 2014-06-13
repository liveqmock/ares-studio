/**
 * 源程序名称：DBTableExporttWizard.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.wizard;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
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

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.database.ui.pages.GenDbTableSelectPage;
import com.hundsun.ares.studio.jres.database.utils.GenDbExportServiceUtils;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.ui.util.DialogHelper;

/**
 * @author yanwj06282
 *
 */
public class DBTableExporttWizard extends Wizard implements IExportWizard {

	private Logger logger = Logger.getLogger(DBTableExporttWizard.class);
	String path;
	
	private IStructuredSelection selection;
	private GenDbTableSelectPage page;
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("导出数据库");
		this.selection = selection;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		page.getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(DatabaseUI.PLUGIN_ID, "icons/table.gif").createImage());
	}
	
	@Override
	public void addPages() {
		Object obj = selection.getFirstElement();
		IARESProject project = null;
		if(obj != null){
			project = ARESElementUtil.toARESElement(obj).getARESProject();
		}
		if(project != null){
			IFolder rootFolder = ARESElementUtil.getModuleRootFolder(project,IDBConstant.ROOT_TYPE);
			if(rootFolder != null){
				addPage(page = new GenDbTableSelectPage("", selection));
				return;
			}
		}
		MessageDialog.openError(getShell(), "导出错误", "当前工程不存在数据库模块根");
	}
	
	@Override
	public boolean performFinish() {
		{
			path = page.getPath();
			GenDbExportServiceUtils.saveParamter(path);
			final List<IARESResource> reses = page.getResult();
			
			final List<String> files = new ArrayList<String>();
			
			IRunnableWithProgress runnable = new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {
					files.addAll(GenDbExportServiceUtils.export(format(reses), path ,page.needDevfCol(), page.needSplitDoc(),monitor));
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
					Throwable cause = e.getTargetException().getCause();
					if(cause != null && cause instanceof FileNotFoundException){
						msgdialog = new MessageDialog(null, "导出失败", null,
								"导出文件已被打开或无写入权限，请重新命名或关闭后再导出！", MessageDialog.WARNING,
								new String[] { "确定" }, 0);
					}else{
						msgdialog = new MessageDialog(null, "导出失败", null,
								"失败原因：" + e.getTargetException().getMessage(), MessageDialog.WARNING,
								new String[] { "确定" }, 0);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (null != msgdialog){
					msgdialog.open();
					return false;
				}else {
					StringBuffer filesPath = new StringBuffer();
					for(int index=0; index<files.size(); index++){
						String file = files.get(index);
						if(index > 0){
							filesPath.append(";\r\n" + file);
						}else{
							filesPath.append(file);
						}
					}
					msgdialog = new MessageDialog(null, "导出成功", null,
							String.format("导出路径为%s,是否打开文件？", filesPath.toString()), MessageDialog.INFORMATION, new String[] {
									"确定","取消"}, 0);
					if(Window.OK == msgdialog.open()){
						for(String file : files){
							openFile(file);
						}
					}
					return true;
				}
			} catch (Exception e) {
				DialogHelper.showErrorMessage(e.getMessage());
				return false;
			}
		}
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

	private Map<String ,List<IARESResource>> format (List<IARESResource> reses){
		Map<String ,List<IARESResource>> map = new HashMap<String, List<IARESResource>>();
		for (IARESResource res : reses){
			try {
				getIARESResource(map, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getIARESResource (Map<String ,List<IARESResource>> map ,IARESResource res) throws Exception{
		IARESModule module = res.getModule();
		String fullQualified = module.getElementName();
		String key = StringUtils.replace(fullQualified, ".", "/");
		if (map.get(key) == null) {
			map.put(key, new ArrayList(Arrays.asList(res)));
		}else {
			map.get(key).add(res);
		}
	}
	
}
