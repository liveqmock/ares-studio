/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * 包含元素选择和一个名字输入框。
 * @author maxh
 */
public abstract class ElementSelectionWizardPageWithNameInput extends CommonElementSelectionPage {

	protected Text txt_Name;
	protected IResource resource;
	protected String newName = "";
	
	public ElementSelectionWizardPageWithNameInput(String pageName,
			IWorkbench workbench, IARESElement selection) {
		super(pageName, workbench, selection);
	}
	
	public String getNewName() {
		if(null != txt_Name&&!txt_Name.isDisposed()){
			newName = txt_Name.getText();
		}
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	protected ModifyListener modifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			newName = txt_Name.getText();
			updateComplete();
		}
	};

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(composite);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
	
		Composite treeContainer = new Composite(composite, SWT.NONE);
		GridLayoutFactory.fillDefaults().applyTo(treeContainer);
		
		super.createControl(treeContainer);
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalIndent = 0;
		gd.horizontalIndent = 0;
		gd.horizontalSpan = 2;
		gd.heightHint = 300;
		gd.widthHint = 260;
		treeContainer.setLayoutData(gd);

		createText(composite);
		
		createClientContent(composite);
		
		if (selection != null) {
			viewer.setSelection(new StructuredSelection(selection), true);
		}

		setControl(composite);

		setPageComplete(validate());
	}
	
	protected void createText(Composite composite){
		Label moduleNameLabel = new Label(composite, SWT.NONE);
		moduleNameLabel.setText("名字:");
		GridData gd = new GridData();
		moduleNameLabel.setLayoutData(gd);
		
		txt_Name = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		txt_Name.setLayoutData(gd);
		txt_Name.addModifyListener(modifyListener);
		txt_Name.setFocus();
		txt_Name.setText(newName);
		txt_Name.setSelection(newName.length());
	}

	/**
	 * 构建用户自定义区域
	 * @param composite
	 */
	protected void createClientContent(Composite composite) {
		// do nothing
	}

	protected boolean inModule() {
		if (selection instanceof IARESModule) {
			return true;
		}
		return false;
	}

	public boolean validate() {
		boolean superValidate = super.validate();
		if (!superValidate) {
			return false;
		}
		
		if (getNewName().equals("")) {
			setErrorMessage("名字不能为空");
			return false;
		} else {
			Pattern pt = getNamePattern();
			if (pt != null) {
				if (!pt.matcher(getNewName()).matches()) {
					setErrorMessage("名字不合法(" + pt.toString() + ")");
					return false;
				}
			}
		}
		
		setErrorMessage(null);
		return true;
	}
	
	/** 名字验证表达式, 默认为资源的名字规则; 子类可以重写此方法, 返回null代表不限制    */
	protected Pattern getNamePattern() {
		return IARESResource.RES_NAME_PATTERN;
	}

	public abstract boolean finishPage();
	

	protected void openEditorAndReveal() {
		if (resource != null && resource instanceof IFile && workbench != null) {
			IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
			try {
				IDE.openEditor(page, (IFile) resource);
				BasicNewResourceWizard.selectAndReveal(resource, workbench.getActiveWorkbenchWindow());
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

	public IResource getCreatedResource() {
		return resource;
	}

	protected void createFolder(IFolder folder) {
		if (!folder.exists()) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	protected void createFile(IFile file, InputStream content) {
		if (!file.exists()) {
			try {
				file.create(content, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	protected void createEmptyFile(IFile file) {
		ByteArrayInputStream bis = new ByteArrayInputStream(new byte[0]);
		createFile(file, bis);
	}

}

