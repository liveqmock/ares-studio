/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.model.converter.ProjectPropertyConverter;
import com.hundsun.ares.studio.core.registry.AresProjectRegistry;
import com.hundsun.ares.studio.core.registry.DefautlModuleRootRegistry;
import com.hundsun.ares.studio.core.registry.IARESProjectDescriptor;
import com.hundsun.ares.studio.core.registry.IDefaultModuleRootDescriptor;
import com.hundsun.ares.studio.core.util.ResourcesUtil;
import com.hundsun.ares.studio.core.util.Util;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;

/**
 * 通用项目向导
 * @author sundl
 */
public abstract class CommonProjectWizard extends Wizard implements INewWizard {
	
	private static final Logger logger = Logger.getLogger(CommonProjectWizard.class);
	
	protected WizardNewProjectCreationPage page;
	protected IARESProjectDescriptor aresProjectDescriptor = getARESProjectDescriptor();
	
	// TODO 优化 暂时由于线程访问的问题，在线程外初始化两个变量供线程内使用。
	protected IProject projectHadler;
	protected URI uri;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("新建 "+ aresProjectDescriptor.getName() + "项目");
	}
	
	@Override
	public void addPages() {
		page = new CommonProjectCreationWizardPage("main");
		addPage(page);
		if (aresProjectDescriptor != null)
			page.setDescription("新建一个 "+ aresProjectDescriptor.getName() + "项目");
	}

	/**
	 * Subclasses should override to perform the actions of the wizard.
	 * This method is run in the wizard container's context as a workspace runnable.
	 * @param monitor
	 * @throws InterruptedException
	 * @throws CoreException
	 */
	protected void finishPage(IProgressMonitor monitor) throws InterruptedException, CoreException {
		// create a general project.
		createProject(projectHadler, uri, monitor);
		if (!projectHadler.isOpen()) 
			projectHadler.open(null);
		
		
		if (aresProjectDescriptor != null) {			
			// add natures...
			IARESProjectDescriptor projectType = aresProjectDescriptor;
			String[] natures = projectType.getNatures();
			addNatures(natures);
			logger.debug("Add natures (" + Util.concatWith(natures, '.') + ") on project " + projectHadler.getName() );
			
			// create default roots according to the extensions.
			Collection<IDefaultModuleRootDescriptor> defaultRoots = DefautlModuleRootRegistry.getInstance().get(getProjectTypeId());
			List<IResPathEntry> entries = new ArrayList<IResPathEntry>();
			for (IDefaultModuleRootDescriptor rDesc : defaultRoots) {
				String path = rDesc.getPath();
				String rootType = rDesc.getRootType();
				IResPathEntry entry = ARESCore.newSourceEntry(rootType, new Path(path));
				entries.add(entry);
			}
			initResPath(entries);
			initOtherInfo(monitor);
			createProjectPropertiyFile(projectHadler);
		} else {
			
		}
		endCreateProject(monitor);
	}
	
	/**
	 * 
	 */
	protected void endCreateProject(IProgressMonitor monitor) {
	}

	private IARESProjectDescriptor getARESProjectDescriptor() {
		Collection<IARESProjectDescriptor> pDescs = AresProjectRegistry.getInstance().get(getProjectTypeId());
		if (pDescs.size() == 1) {		
			IARESProjectDescriptor projectType = pDescs.toArray(new IARESProjectDescriptor[1])[0];
			return projectType;
		} else {
			logger.error("创建类型为: " + getProjectTypeId() + "的Ares项目，但该类型的注册信息为：" + pDescs.size() + "个");
		}
		return null;
	}
	
	/**
	 * 项目类型ID<br>
	 * @see 扩展点： aresProject
	 * @return
	 */
	protected abstract String getProjectTypeId();
	
	protected void addNatures(String[] natures) throws CoreException {
		// first, add the ares-nature.
		ResourcesUtil.addNatureToProject(projectHadler, ARESCore.NATURE_ID, null);
		// add the extension nature...
		for (String id : natures) {
			ResourcesUtil.addNatureToProject(projectHadler, id, null);
		}
	}
	
	protected void initResPath(List<IResPathEntry> entries) throws ARESModelException {
//		// first, create the folders that does not exist.
//		for (IResPathEntry entry : entries) {
//			if (entry.getEntryKind() == IResPathEntry.RPE_SOURCE) {
//				IPath path = entry.getPath();
//				IFolder folder = projectHadler.getFolder(path);
//				if (!folder.exists()) {
//					try {
//						folder.create(true, true, null);
//					} catch (CoreException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		
//		// write the .classpath entry
//		ARESProject cProj = new ARESProject(ARESCore.getModel(), projectHadler);
//		cProj.writeResPath(entries.toArray(new IResPathEntry[0]));
		for (IResPathEntry entry : entries) {
			IARESProject aresProject = ARESCore.create(projectHadler);
			aresProject.createRoot(entry.getType(), entry.getPath().toString(), new NullProgressMonitor());
		}
	}
	
	@Override
	public boolean performFinish() {
		projectHadler = page.getProjectHandle();
		uri = null;
		if (!page.useDefaults()) {
			uri = page.getLocationURI();
		}
		
		final IWorkspaceRunnable op = new IWorkspaceRunnable() {
			
			public void run(IProgressMonitor monitor) throws CoreException {
				try {
					finishPage(monitor);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		try {
			ISchedulingRule rule= null;
			Job job= Job.getJobManager().currentJob();
			if (job != null)
				rule= job.getRule();
			IRunnableWithProgress runnable= null;
//			if (rule != null)
//				runnable= new WorkbenchRunnableAdapter(op, rule, true);
//			else
//				runnable= new WorkbenchRunnableAdapter(op, getSchedulingRule());
			runnable = new IRunnableWithProgress() {
				
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {
					try {
						ResourcesPlugin.getWorkspace().run(op, monitor);
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			};
			getContainer().run(true, true, runnable);
		} catch (InvocationTargetException e) {
			//handleFinishException(getShell(), e);
			return false;
		} catch  (InterruptedException e) {
			return false;
		}
		return true;
	}	
	

	/**
	 * 子类可以初始化
	 */
	protected void initOtherInfo(IProgressMonitor monitor) {
	}
	

	protected void createProjectPropertiyFile(IProject project) {
		IFile file = project.getFile(IARESProjectProperty.PRO_FILE);
		IARESProjectProperty pro = new ARESProjectProperty();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try {
			ProjectPropertyConverter.getInstance().write(bos, pro);
			file.create(new ByteArrayInputStream(bos.toByteArray()), true, null);
		} catch (CoreException e) {
			logger.error("创建项目属性文件出错.", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createProject(IProject project, URI location, IProgressMonitor monitor) throws CoreException {
		if (!Platform.getLocation().equals(location)) {
			IProjectDescription desc = project.getWorkspace().newProjectDescription(project.getName());
			desc.setLocationURI(location);
			project.create(desc, monitor);
		} else
			project.create(monitor);
	}

}

class NewCommonProjectWizardPage extends WizardNewProjectCreationPage{

	public NewCommonProjectWizardPage(String pageName) {
		super(pageName);		
	}
	
}