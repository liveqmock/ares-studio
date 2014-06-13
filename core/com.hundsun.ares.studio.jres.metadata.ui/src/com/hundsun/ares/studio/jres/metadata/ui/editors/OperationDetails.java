/**
 * 源程序名称：OperationDetails.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.script.ScriptPlugin;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;
import com.hundsun.ares.studio.ui.editor.blocks.JresDetailsPage;

/**
 * 用户操作detail界面
 * 
 * @author qinyuan
 * 
 */
public class OperationDetails extends JresDetailsPage {

	protected Text txtLabelCode;
	protected Text txtFile;
	private Button btnFile;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresDetailsPage#
	 * getSectionName()
	 */
	@Override
	protected String getSectionName() {
		return "操作逻辑脚本";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresDetailsPage#
	 * getDescription()
	 */
	@Override
	protected String getDescription() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresDetailsPage#
	 * createSectionContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createSectionContents(Composite client) {
		// Label lFile = new Label(client, SWT.NONE);
		// lFile.setText("脚本文件：");

		Hyperlink hyperlink = new Hyperlink(client, SWT.NONE);
		hyperlink.setUnderlined(true);
		hyperlink.setText("脚本文件：");
		hyperlink.addHyperlinkListener(new HyperlinkAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.ui.forms.events.HyperlinkAdapter#linkActivated(org
			 * .eclipse.ui.forms.events.HyperlinkEvent)
			 */
			@Override
			public void linkActivated(HyperlinkEvent e) {
				linkJSResource("js");
			}
		});

		txtFile = new Text(client, SWT.NONE | SWT.BORDER);

		btnFile = new Button(client, SWT.NONE);
		btnFile.setText("浏览");
		btnFile.addSelectionListener(new SelectionAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				btnFileHandled(e);
			}
		});

		Label lb = new Label(client, SWT.NONE);
		lb.setText("脚本内容：");

		// TODO#界面逻辑#秦元#普通#王彬#已编码 #2011-07-25#3 #10 #实现用户操作detail界面
		// 界面要求一个label，再加一个文本编辑器框
		txtLabelCode = new Text(client, SWT.NONE | SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL);
		txtLabelCode.setEditable(false);
		// txtLabelCode.setTextLimit(20000);
		GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.BEGINNING)
				.applyTo(hyperlink);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER)
				.grab(true, false).applyTo(txtFile);
		GridDataFactory.swtDefaults().align(SWT.END, SWT.END).applyTo(btnFile);

		GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.BEGINNING)
				.applyTo(lb);
		GridDataFactory.swtDefaults().hint(220, 300)
				.align(SWT.FILL, SWT.CENTER).span(2, 1).grab(true, false)
				.applyTo(txtLabelCode);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.ui.page.masterdetail.JresDetailsPage#databinding
	 * ()
	 */
	@Override
	protected DataBindingContext databinding() {
		DataBindingContext context = new DataBindingContext();

		context.bindValue(SWTObservables.observeText(txtFile, SWT.Modify),
				EMFEditObservables.observeValue(page.getEditingDomain(),
						(EObject) input,
						MetadataPackage.Literals.OPERATION__FILE));
		initTxtLabelCode();
		/*
		 * context.bindValue(SWTObservables.observeText(txtLabelCode,
		 * SWT.Modify), EMFEditObservables.observeValue(page.getEditingDomain(),
		 * (EObject) input, MetadataPackage.Literals.OPERATION__CODE));
		 */
		return context;
	}

	protected void btnFileHandled(SelectionEvent e) {
		CommonElementContentProvider cp = new CommonElementContentProvider(){
			@Override
			protected Object[] getProjectChildren(
					IARESProject project) {
				List<IARESModuleRoot> roots = new ArrayList<IARESModuleRoot>();
				try {
					for(IARESModuleRoot root : project.getModuleRoots()){
						if(StringUtils.equals(root.getType(), ScriptPlugin.TOOL_MODULE_ROOT_TYPE)){
							roots.add(root);
						}
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
				return roots.toArray(new IARESModuleRoot[roots.size()]);
			}
		};
		CommonElementLabelProvider lp = new CommonElementLabelProvider(cp);
		ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
				btnFile.getShell(),
				lp,//WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
				cp);//new WorkbenchContentProvider());
		fileDialog.addFilter(new JSViewerFilter());
		fileDialog.setInput(ARESCore.getModel());
		int returnValue = fileDialog.open();
		if (Dialog.OK == returnValue) {
			Object result = fileDialog.getFirstResult();
			if (result instanceof IARESResource) {
				String filePath =((IARESResource)(result)).getResource().getFullPath().toString();
				if (null != filePath && !"".equals(filePath)) {
					String scriptContent = StringUtil.getFileContent(((IARESResource)(result)).getResource().getLocation().toString());
					if (scriptContent != null) {
						txtFile.setText(filePath);
						this.txtLabelCode.setText(scriptContent);
					}

				}
			}
		}
		/*
		 * FileDialog fileDialog = new FileDialog(btnFile.getShell());
		 * fileDialog.setFilterExtensions(new String[] { "*.js" }); String
		 * filePath = fileDialog.open(); if (null != filePath &&
		 * !"".equals(filePath)) { String scriptContent =
		 * JRESUtils.getFileContent(filePath); if (scriptContent != null) {
		 * txtFile.setText(filePath); this.txtLabelCode.setText(scriptContent);
		 * }
		 * 
		 * }
		 */}

	@Override
	protected Layout getSectionLayout() {
		return new GridLayout(3, false);
	}

	private void initTxtLabelCode() {
		String filePath = txtFile.getText();
		if (null != filePath && !"".equals(filePath)) {
			String scriptContent = StringUtil.getFileContent(getRelPath(filePath));
			if (scriptContent != null) {
				this.txtLabelCode.setText(scriptContent);
			}

		}else{
			this.txtLabelCode.setText("");
		}
	}

	/**
	 * 链接ARES资源。
	 */
	private void linkJSResource(String resType) {
		try {

			if (!txtFile.getText().equals("")) {
				IARESBundle[] elements = ARESElementUtil
						.getRefARESProjects(getARESProject());
				String projectName = this.getARESProject().getProject()
						.getName();

				for (IARESBundle ele : elements) {
					if (ele instanceof IARESProject
							&& txtFile.getText().indexOf(projectName) > 0) {
						String subPath = (txtFile.getText().substring(txtFile
								.getText().indexOf(projectName))).replace(
								projectName, "");
						IResource resource = ((IARESProject) ele).getProject()
								.findMember(subPath);
						if (resource != null) {
							IDE.openEditor(
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getActivePage(), (IFile) resource,
									false);
							break;
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected IARESProject getARESProject() {
		return this.page.getEditor().getARESResource().getARESProject();
	}

	private class JSViewerFilter extends ViewerFilter {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers
		 * .Viewer, java.lang.Object, java.lang.Object)
		 */
		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			if (element instanceof IARESResource) {
				String fileExtension = ((IARESResource) element).getType();
				if ("js".equalsIgnoreCase(fileExtension)) {
					return true;
				} else {
					return false;
				}
			}
			return true;
		}

	}
	private String getRelPath(String scriptFileLocation){
		String newScriptFileLocation = scriptFileLocation.substring(scriptFileLocation.indexOf("/")+1) ;
		String projectName =newScriptFileLocation.substring(0,newScriptFileLocation.indexOf("/")) ;
		
		IProject[] projects =ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for(IProject project :projects){
			if(project.getName().equals(projectName)){
				return project.getLocation().toString()+newScriptFileLocation.substring(newScriptFileLocation.indexOf("/"));
			}
		}
		return scriptFileLocation;
		
	}


}
