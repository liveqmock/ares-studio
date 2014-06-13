package com.hundsun.ares.studio.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.internal.core.ReferencedLibraryInfo;
import com.hundsun.ares.studio.internal.core.ReferencedLibrayUtil;
import com.hundsun.ares.studio.ui.GenerateReferencedLibOperation;

/**
 * 导出引用包的向导,一般子类需要扩展这个类进行必要的定制后使用。
 * @author sundl
 */
public class AresLibExportWizard extends Wizard implements IExportWizard {

	protected IARESProject project;
	protected AresLibExportWizardPage page;
	protected IWorkbench workbench;
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		Object obj = selection.getFirstElement();
		if (obj instanceof IARESProject) {
			this.project = (IARESProject)obj;
		} else if (obj instanceof IARESElement) {
			this.project = ((IARESElement) obj).getARESProject();
		} else if (obj instanceof IProject) {
			this.project = ARESCore.create((IProject)obj);
		} else if (obj instanceof IResource) {
			this.project = ARESCore.create(((IResource)obj).getProject());
		} else if (obj instanceof IAdaptable) {
			this.project = (IARESProject) ((IAdaptable)obj).getAdapter(IARESProject.class);
		}
		setWindowTitle("导出引用资源包");
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		page = new AresLibExportWizardPage("page1", workbench, project);
		addPage(page);
	}
	
	/**
	 * 生成引用包需要的一个描述信息对象，子类可以重写这个方法进行定制，比如设置引用包的类型。
	 * @return
	 */
	protected ReferencedLibraryInfo getRefLibInfo() {
		IARESProject project = null;
		IARESElement element = page.getSelectedElement();
		if (element != null)
			project = element.getARESProject();
		
		return ReferencedLibrayUtil.createRefLibInfoObject(project, page.getPublisher());
	}

	@Override
	public boolean performFinish() {
		ReferencedLibraryInfo info = getRefLibInfo();
		if (info != null) {
			IRunnableWithProgress op = createExportOperation(info);
			try {
				getContainer().run(false, false, op);
				return true;
			} catch (InvocationTargetException e) {
				openMessageBox(e);
				e.printStackTrace();
			} catch (InterruptedException e) {
				openMessageBox(e);
				e.printStackTrace();
			}
		}
		return false;
	}
	
	protected IRunnableWithProgress createExportOperation(ReferencedLibraryInfo info) {
		return new GenerateReferencedLibOperation(project, info, page.getPath());
	}
	
	private void openMessageBox(Exception e){
		String msg = null;
		if(null == e.getMessage()){
			if(null != e.getCause()){
				msg = e.getCause().getMessage();
			}
		}else{
			msg = e.getMessage();
		}
		MessageBox box = new MessageBox(new Shell());
		box.setText("生成错误");
		box.setMessage(String.format("生成引用资源包失败，详细信息:%s", msg));
		box.open();
	}

}
