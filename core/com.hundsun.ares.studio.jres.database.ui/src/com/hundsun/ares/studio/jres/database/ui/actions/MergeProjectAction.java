/**
 * 源程序名称：MergeProjectAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.actions;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListSelectionDialog;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.core.ARESProject;
import com.hundsun.ares.studio.jres.database.pdm.PDMSubSystemMerge;
import com.hundsun.ares.studio.jres.database.ui.model.MergePojo;
import com.hundsun.ares.studio.jres.database.utils.FileUtils;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.ui.ARESElementLabelProvider;
import com.hundsun.ares.studio.ui.ARESElementSorter;

/**
 * @author yanwj06282
 *
 */
public class MergeProjectAction implements IObjectActionDelegate,
												IWorkbenchWindowActionDelegate{

	String newProName;
	String targetDir;
	IProject[] projects;
	public static final String FILE_NAME = "\\标准字段合并评审记录.xls";
	
	@Override
	public void run(IAction action) {
		
		final ListSelectionDialog dialog = new ListSelectionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), projects, new ArrayContentProvider(), new ARESElementLabelProvider(), "选择需要合并的项目") {
			
			protected Control createDialogArea(Composite parent) {
		    	final Control ctrl = super.createDialogArea(parent);
		    	final Composite com = new Composite((Composite) ctrl, SWT.NONE);
		    	GridLayoutFactory.fillDefaults().numColumns(4).applyTo(com);
		    	Label proLabel = new Label(com, SWT.NONE);
		    	proLabel.setText("目标工程名:");
		    	final Combo combo = new Combo(com, SWT.READ_ONLY);
		    	final List<String> pros = new ArrayList<String>();
		    	IProject[] enablepros = getEnableProjects();
		    	projects = getEnableProjects();
		    	for(IProject pro : enablepros){
		    		pros.add(pro.getName());
		    	}
		    	combo.setItems(pros.toArray(new String[0]));
		    	if (pros.size() > 0) {
		    		newProName = pros.get(0);
		    		combo.setText(pros.get(0));
		    		getViewer().setInput(getProject());
				}
		    	combo.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						newProName = combo.getText();
						getViewer().setInput(getProject());
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						newProName = combo.getText();
					}
				});
		    	Label targetLabel = new Label(com, SWT.NONE);
		    	targetLabel.setText("标准字段合并评审记录输出目录:");
		    	final Text targetText = new Text(com, SWT.BORDER);
		    	if (enablepros.length > 0) {
		    		targetDir = enablepros[0].getLocation().toOSString() + FILE_NAME;
		    		targetText.setText(targetDir);
		    	}
		    	Button targetButton = new Button(com,SWT.NORMAL);
				targetButton.setText("选择");
				targetButton.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {
						DirectoryDialog dialog = new DirectoryDialog(com.getShell());
						String select = dialog.open();
						if (StringUtils.isNotBlank(select)) {
							targetDir = select + FILE_NAME;
							targetText.setText(targetDir);
						}
					}
				});
				targetText.addModifyListener(new ModifyListener() {
					
					@Override
					public void modifyText(ModifyEvent e) {
						targetDir = targetText.getText();
					}
				});
				
				GridDataFactory.fillDefaults().applyTo(com);
				GridDataFactory.swtDefaults().applyTo(proLabel);
				GridDataFactory.swtDefaults().span(4, 1).applyTo(targetLabel);
		    	GridDataFactory.fillDefaults().span(3, 1).applyTo(combo);;
		    	GridDataFactory.fillDefaults().span(3, 1).grab(true, false).applyTo(targetText);
		    	GridDataFactory.swtDefaults().applyTo(targetButton);
		    	Dialog.applyDialogFont((Composite) ctrl);
		    	getViewer().setSorter(new ARESElementSorter());
		    	return (Composite) ctrl;
		    }
		};
		dialog.setTitle("合并子系统");
		if (dialog.open() == Dialog.OK) {
			Job job = new Job("合并子系统") {
				
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					monitor.beginTask("开始。。。", dialog.getResult().length + 1);
					Map<String ,MergePojo> pojoMap = new HashMap<String, MergePojo>();
					IProject project = merge(dialog.getResult() , newProName ,pojoMap ,monitor);
					IARESProject aresproject = ARESCore.create(project);
					PDMSubSystemMerge subSystemMerge = new PDMSubSystemMerge();
					StringBuffer errorMsg = new StringBuffer();
					try {
						subSystemMerge.subSystemMerge(pojoMap, aresproject,targetDir, monitor, errorMsg);
					} catch (Exception e) {
						e.printStackTrace();
					}
					monitor.done();
					return Status.OK_STATUS;
				}
			};
			job.setUser(true);
			job.schedule();
		}
	}

	private IProject[] getProject(){
		List<IProject> ps = new ArrayList<IProject>();
		
		for(IProject p : getEnableProjects()){
			if (!StringUtils.equals(p.getName(), getNewProName())) {
				ps.add(p);
			}
		}
		return ps.toArray(new IProject[0]);
	}
	
	
	/**
	 * 获取合并操作总工程名
	 * 
	 * @return the newProName
	 */
	public String getNewProName() {
		return newProName;
	}



	/**
	 * 获取评审文件生成路径
	 * 
	 * @return the targetDir
	 */
	public String getTargetDir() {
		return targetDir;
	}



	/**
	 * 合并工程
	 * 
	 * @param objs
	 * @param newProName
	 * @param stdFieldMap
	 * @param busTypeMap
	 */
	private IProject merge(final Object[] objs ,final String newProName ,Map<String ,MergePojo> pojoMap ,IProgressMonitor monitor){
		IProject newPro = null;
		//创建一个新工程
		{
			newPro = ResourcesPlugin.getWorkspace().getRoot().getProject(newProName);
		}
		List<StandardField> stds = new ArrayList<StandardField>();
		List<BusinessDataType> bts = new ArrayList<BusinessDataType>();
		for(Object obj : objs){
			if (obj instanceof IProject) {
				monitor.subTask("合并子工程：" +((IProject) obj).getName());
				//处理数据库的标准字段
				MergePojo pojo = new MergePojo();
				IARESProject project = ARESCore.create((IProject)obj);
				pojo.setProject(project);
				pojoMap.put(((IProject) obj).getName(), pojo);
				try {
					IARESResource stdRes = project.findResource(IMetadataResType.StdField, IMetadataResType.StdField);
					IARESResource busTypeRes = project.findResource(IMetadataResType.BizType, IMetadataResType.BizType);
					//标准字段
					if (stdRes !=  null) {
						monitor.subTask("计算标准字段。。。");
						StandardFieldList stdList = stdRes.getInfo(StandardFieldList.class);
						if (stdList != null) {
							pojo.getStdFields().addAll(stdList.getItems());
							stds.addAll(stdList.getItems());
						}
					}
					//业务数据类型
					if (busTypeRes != null) {
						monitor.subTask("计算业务数据类型。。。");
						BusinessDataTypeList busTypeList = busTypeRes.getInfo(BusinessDataTypeList.class);
						if (busTypeList != null) {
							pojo.getBusTypes().addAll(busTypeList.getItems());
							bts.addAll(busTypeList.getItems());
						}
					}
					//子系统
					monitor.subTask("计算子系统。。。");
					for(IARESModule subsys : project.getModuleRoot("database").getModules()){
						String moduleName = subsys.getElementName();
						if (StringUtils.isNotBlank(moduleName) && StringUtils.indexOf(moduleName, ".") == -1) {
							pojo.getSubSyses().add(subsys.getElementName());
						}
					}
					
					//处理其他资源（拷贝）
					monitor.subTask("合并数据库表。。。");
					String oldPath = ((IProject) obj).getFolder("database").getLocationURI().getPath();
					String newPath = newPro.getFolder("database").getLocationURI().getPath();
					if (!StringUtils.equals(oldPath, newPath)) {
						FileUtils.copyFolder(oldPath, newPath);
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
				monitor.worked(1);
			}
		}
		//拷贝完成手动刷新
		try {
			monitor.subTask("刷新。。。");
			newPro.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			monitor.worked(1);
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		return newPro;
	}
	
	private IProject[] getEnableProjects(){
		List<IProject> pros = new ArrayList<IProject>();
		IProject[] iprojects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for(IProject p : iprojects){
			if ( p.isOpen() && ARESProject.hasARESNature(p)) {
				pros.add(p);
			}
		}
		return pros.toArray(new IProject[0]);
	}
	
	public static void createAndOpenProject(IProject project, boolean useDefaultLocation, URI locationURI, IProgressMonitor monitor) {
		try {
			monitor.beginTask("创建工程", 10);
			
			if (useDefaultLocation) {
				// 工程直接在工作区间内创建
				project.create(new SubProgressMonitor(monitor, 6));
			} else {
				IProjectDescription desc = project.getWorkspace().newProjectDescription(project.getName());
				desc.setLocationURI(locationURI);
				project.create(desc, new SubProgressMonitor(monitor, 6));
			}
			
			project.open(new SubProgressMonitor(monitor, 2));
			
			project.setDefaultCharset("UTF-8", new SubProgressMonitor(monitor, 2));
			
		} catch (CoreException e) {
			e.printStackTrace();
		} finally {
			monitor.done();
		}
	}
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		
	}

	class MergeProjectProvider implements ITreeContentProvider{

		@Override
		public void dispose() {
		}
		
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return null;
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			return null;
		}

		@Override
		public Object getParent(Object element) {
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			return false;
		}
		
	}
	
}
