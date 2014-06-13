package com.hundsun.ares.studio.biz.ui.wizard;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.AresModelViewer;

public class ImportWizardPage extends WizardPage {
	
	AresModelViewer viewer;
	private Button btFile;
	private Text txFile;
	private Button btBrowserFile;
	private Button btFolder;
	private Text txFolder;
	private Button btBrowserFolder;
	Button btNeedReport;
	
	private IARESElement selection;

	protected ImportWizardPage(String pageName, IARESElement selection) {
		super(pageName);
		this.selection = selection;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().applyTo(composite);
		GridLayoutFactory.fillDefaults().applyTo(composite);
		
		viewer = new AresModelViewer(composite, SWT.NONE);
		viewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof IARESProject) {
					return true;
				}
				return false;
			}
		});

		viewer.setInput(ARESCore.getModel());
		
		if (selection != null) {
			viewer.setSelection(new StructuredSelection(selection.getARESProject()));
		}
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				validate();
			}
		});
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());
		
		Composite pathComp = new Composite(composite, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).indent(0, 0).applyTo(pathComp);
		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(pathComp);
		
		createFileSelector(pathComp);
		createFolderSelector(pathComp);
		createReportSelector(composite);
		
		setControl(composite);
		setPageComplete(false);
		setMessage("请选择要导入的文件或目录");
	}
	
	private void createFileSelector(Composite parent) {
		btFile = new Button(parent, SWT.RADIO);
		btFile.setText("选择文件");
		GridDataFactory.fillDefaults().applyTo(btFile);
		
		txFile = new Text(parent, SWT.BORDER);
		txFile.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txFile);
		
		btBrowserFile = new Button(parent, SWT.PUSH);
		GridDataFactory.fillDefaults().applyTo(btBrowserFile);
		btBrowserFile.setText("浏览");
		btBrowserFile.addSelectionListener(new SelectionAdapter() {

			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getShell());
				String path = dialog.open();
				if (path != null)
					txFile.setText(path);
			}
			
		});
		
		btFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btFile.getSelection()) {
					txFile.setEnabled(true);
					btBrowserFile.setEnabled(true);
					btFolder.setSelection(false);
				} else {
					txFile.setText(StringUtils.EMPTY);
					txFile.setEnabled(false);
					btBrowserFile.setEnabled(false);
				}
			}
		});
		
		btFile.setSelection(false);
	}
	
	private void createFolderSelector(Composite parent) {
		btFolder = new Button(parent, SWT.RADIO);
		btFolder.setText("选择目录");
		GridDataFactory.fillDefaults().applyTo(btFolder);
		
		txFolder = new Text(parent, SWT.BORDER);
		txFolder.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txFolder);
		
		btBrowserFolder = new Button(parent, SWT.PUSH);
		GridDataFactory.fillDefaults().applyTo(btBrowserFolder);
		btBrowserFolder.setText("浏览");
		btBrowserFolder.addSelectionListener(new SelectionAdapter() {

			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(getShell());
				txFolder.setText(dialog.open());
			}
			
		});
		
		btFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btFolder.getSelection()) {
					txFolder.setEnabled(true);
					btBrowserFolder.setEnabled(true);
					btFile.setSelection(false);
				} else {
					txFolder.setText(StringUtils.EMPTY);
					txFolder.setEnabled(false);
					btBrowserFolder.setEnabled(false);
				}
			}
		});
		
		String selectionType = getDialogSettings().get("type");
		if (StringUtils.equalsIgnoreCase(selectionType, "file")) {
			btFolder.setSelection(false);
			txFolder.setEnabled(false);
			btBrowserFolder.setEnabled(false);
			btFile.setSelection(true);
			txFile.setEnabled(true);
			btBrowserFile.setEnabled(true);
		} else {
			btFolder.setSelection(true);
			txFolder.setEnabled(true);
			btBrowserFolder.setEnabled(true);
			btFile.setSelection(false);
			txFile.setEnabled(false);
			btBrowserFile.setEnabled(false);
		}
	}
	
	private void createReportSelector(Composite parent) {
		btNeedReport = new Button(parent, SWT.CHECK);
		btNeedReport.setText("生成导入报告");
		GridDataFactory.fillDefaults().applyTo(btNeedReport);
		
		if (getDialogSettings().get("report") == null || getDialogSettings().getBoolean("report")) {
			btNeedReport.setSelection(true);
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {
		if (btFile.getSelection()) {
			getDialogSettings().put("type", "file");
		} else {
			getDialogSettings().put("type", "folder");
		}
		
		if (btNeedReport.getSelection()) {
			getDialogSettings().put("report", true);
		} else {
			getDialogSettings().put("report", false);
		}
		super.dispose();
	}

	private void validate() {
		IStructuredSelection ss = (IStructuredSelection) viewer.getSelection();
		Object obj = ss.getFirstElement();
		if (obj == null) {
			setErrorMessage("请选择要导入到哪个项目中");
			setPageComplete(false);
			return;
		}
		
		if (btFile.getSelection()) {
			if (StringUtils.isEmpty(txFile.getText())) {
				setErrorMessage("请选择要导入的文件");
				setPageComplete(false);
				return;
			}
			
			File file = new File(txFile.getText());
			if (!file.exists()) {
				setErrorMessage(String.format("文件%s不存在！", txFile.getText()));
				setPageComplete(false);
				return;
			}
		} else {
			if (StringUtils.isEmpty(txFolder.getText())) {
				setErrorMessage("请选择要导入文件所在的目录");
				setPageComplete(false);
				return;
			}
			
			File file = new File(txFolder.getText());
			if (!file.exists()) {
				setErrorMessage(String.format("目录%s不存在！", txFolder.getText()));
				setPageComplete(false);
				return;
			}
		}
		
		setErrorMessage(null);
		setPageComplete(true);
	}
	
	public File[] getFiles() {
		if (btFile.getSelection()) {
			File file = new File(txFile.getText());
			File[] files = new File[] {file};
			return files;
		} else {
			File folder = new File(txFolder.getText());
			// 如果给定一个文件夹，取下面所有的文件，目前只取第一层，不会递归
			if (folder.isDirectory()) {
				return folder.listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						if (!StringUtils.endsWith( pathname.getName(), ".xls")) {
							return false;
						}
						
						if (pathname.isDirectory())
							return false;
						
						return true;
					}
				});
			}
		}
		return null;
	}
}
