/**
 * 源程序名称：GenDatabaseSQLAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;

import com.hundsun.ares.studio.jres.database.ui.wizard.GenSQLWizard;
import com.hundsun.ares.studio.ui.action.PopupAction;


/**
 * @author gongyf
 *
 */
public class GenDatabaseSQLAction extends PopupAction {

	/**
	 * 
	 */
	public GenDatabaseSQLAction() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		if (getSelection() instanceof IStructuredSelection && !((IStructuredSelection) getSelection()).isEmpty()) {
			
			WizardDialog dlg = new WizardDialog(getShell(), new GenSQLWizard(getSelection()));
			dlg.open();
			
//			ProgressMonitorDialog dlg = new ProgressMonitorDialog(getShell());
//			try {
//				// 在ui线程中调用，主要是为了脚本能够调用对话框
//				dlg.run(false, false, new IRunnableWithProgress() {
//					
//					@Override
//					public void run(IProgressMonitor monitor) throws InvocationTargetException,
//							InterruptedException {
//						monitor.beginTask("生成SQL脚本", IProgressMonitor.UNKNOWN);
//						
//						try {
//							IResourceCompilerFactory factory = CompileManager.getInstance().getFactoryByType(getCompileType());
//							if (factory != null) {
//								
//								// 上下文中需要输入IARESResource对象
//								Map<Object, Object> context = new HashMap<Object, Object>();
//								
//								Object obj = ((IStructuredSelection)getSelection()).getFirstElement();
//								
//								final CompilationResult result = factory.create(obj).compile(obj, context);
//								
//								if (result.getStatus().getSeverity() != IStatus.OK) {
//									throw new InvocationTargetException(result.getStatus().getException(), result.getStatus().getMessage());
//									
//								}
//							} else {
//								throw new InterruptedException("无法找到编译器工厂");
//								
//							}
//						} finally {
//							monitor.done();
//						}
//					}
//				});
//			} catch (Exception e) {
//				MessageDialog.openError(getShell(), "错误", e.getMessage());
//			} 
		}
	}

}
