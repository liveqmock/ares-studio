/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.SelectionListenerAction;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.internal.core.DeleteResouceElementsOperation;
import com.hundsun.ares.studio.ui.refactoring.RefactoringUtil;

/**
 * 删除的Action。
 * 
 * @author sundl
 */
public class ARESDeleteAction extends SelectionListenerAction {

	private static Logger logger = Logger.getLogger(ARESDeleteAction.class);
	
	private Shell shell;

	public ARESDeleteAction(Shell shell) {
		super("删除");
	}

	@Override
	protected boolean updateSelection(IStructuredSelection selection) {
		Object[] resources = getSelectedResources().toArray();
		IARESElement[] areses = ARESElementUtil.toARESElement(resources);

		if (areses.length == resources.length && areses.length == selection.size() && areses.length > 0) {
			return RefactoringUtil.canDelete(areses);
		}
		return false;
	}

	@Override
	public void run() {
		// must select some resources
		Object[] resources = getSelectedResources().toArray();
		if (resources.length <= 0) {
			return;
		}

		final IARESElement[] elements = ARESElementUtil.toARESElement(resources);
		// 确认
		boolean confirm = MessageDialog.openConfirm(shell, "确认删除", "确定要删除选中的资源吗?");

		if (confirm) {			
			IRunnableWithProgress op = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) {
					DeleteResouceElementsOperation operation = new DeleteResouceElementsOperation(elements, true);
					try {
						operation.runOperation(monitor);
					} catch (CoreException e) {
						logger.warn("删除资源的时候出错.", e);
					}
				}
			};

			try {
				PlatformUI.getWorkbench().getProgressService().run(true, true, op);
			} catch (InterruptedException e) {
			} catch (InvocationTargetException e) {
			}
			
		}
	}
}
