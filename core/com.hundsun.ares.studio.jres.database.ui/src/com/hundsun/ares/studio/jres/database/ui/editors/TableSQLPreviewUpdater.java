/**
 * 源程序名称：TableSQLPreviewUpdater.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;

import com.hundsun.ares.studio.jres.database.utils.DBTableGenCodeUtils;

/**
 * @author sundl
 *
 */
public class TableSQLPreviewUpdater implements IPageChangedListener{
	
	private Job job;

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IPageChangedListener#pageChanged(org.eclipse.jface.dialogs.PageChangedEvent)
	 */
	@Override
	public void pageChanged(PageChangedEvent event) {
		if (event.getSelectedPage() instanceof TableSQLPreviewPage) {
			final TableSQLPreviewPage page = (TableSQLPreviewPage) event.getSelectedPage();
			final EObject eObj = page.getEditor().getInfo();
			page.setGenerating(true);
			page.update();
			
			if (job == null) {
				job = new Job("generating sql...") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
						
						String fullScript = DBTableGenCodeUtils.genTableFullCode(page.getEditor().getARESResource(), eObj).toString();
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
						
						page.setFullScript(fullScript);
						
						String patchScript = DBTableGenCodeUtils.genTablePatchCode(page.getEditor().getARESResource(), eObj).toString();
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
						
						page.setPatchScript(patchScript);
						
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
						
						page.setGenerating(false);
						page.getSite().getShell().getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								page.update();
							}
						});
						return Status.OK_STATUS;
					}
				};
				job.setSystem(true);
			}
			
			job.cancel();
			job.schedule();
		}		
	}

}
