/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.wizard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.IPageChangeProvider;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.ui.template.Template;

/**
 * 模板选择页面
 * @author sundl
 */
public class TemplateSelectWizardPage extends WizardPage{

	private static final Logger console = ConsoleHelper.getLogger();
	
	private List<Template> templates = new ArrayList<Template>();
	private String remoteUrl;
	private Template selectedTemplate;
	private TableViewer templateViewer;
	private FormBrowser formBrowser;
	
	private Job refreshJob;
	
	protected TemplateSelectWizardPage() {
		super("template_select");
		setDescription("选择模板");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().applyTo(composite);
		GridLayoutFactory.fillDefaults().applyTo(composite);
		
		SashForm sashForm = new SashForm(composite, SWT.HORIZONTAL);
		//sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridDataFactory.fillDefaults().hint(300, 200).grab(true, true).applyTo(sashForm);
		
		createTableViewer(sashForm);
		
		formBrowser = new FormBrowser(SWT.V_SCROLL | SWT.BORDER);
		formBrowser.createControl(sashForm);
		formBrowser.setText("");
		
		templateViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection ss = (IStructuredSelection) event.getSelection();
				Object element = ss.getFirstElement();
				if (element instanceof Template) {
					formBrowser.setText(StringUtils.defaultString(((Template) element).getDescription()));
					selectedTemplate = (Template) element;
					setPageComplete(true);
				}
			}
		});
		
		createRemoteGroup(composite);
		createRefreshGroup(composite);

		setPageComplete(false);
		setControl(composite);
		
		IWizardContainer container = getWizard().getContainer();
		if (container instanceof IPageChangeProvider) {
			((IPageChangeProvider) container).addPageChangedListener(new IPageChangedListener() {
				@Override
				public void pageChanged(PageChangedEvent event) {
					if (event.getSelectedPage() == TemplateSelectWizardPage.this) {
						refreshTemplateList();
					}
				}
			});
		}
	}
	
	public Template getSelectedTemplate() {
		return selectedTemplate;
	}
	
	private void createTableViewer(Composite parent) {
		templateViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.BORDER | SWT.V_SCROLL);
		templateViewer.setContentProvider(new ArrayContentProvider());
		templateViewer.setLabelProvider(new TableLabelProvider());
		//templateViewer.setInput(loadTemplateList());
	}
	
	private void createRemoteGroup(Composite composite) {
		Composite remoteGroup = new Composite(composite, SWT.NONE);
		GridDataFactory.fillDefaults().applyTo(remoteGroup);
		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(remoteGroup);
		
		Label lbRemote = new Label(remoteGroup, SWT.NONE);
		lbRemote.setText("在线模板库地址:");
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).applyTo(lbRemote);
		
		final Text textUrl = new Text(remoteGroup, SWT.BORDER);
		IDialogSettings ds = getDialogSettings();
		String url = StringUtils.defaultString(ds.get("remote_url"), "ftp://aresstudio:at2re@192.168.51.31/ProjectTemplate/");
		textUrl.setText(url);
		remoteUrl = url;
		GridDataFactory.fillDefaults().grab(true, false).applyTo(textUrl);
		textUrl.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				remoteUrl = textUrl.getText();
				IDialogSettings settings = getDialogSettings();
				settings.put("remote_url", remoteUrl);
			}
		});
		
	}
	
	private void createRefreshGroup(Composite composite) {
		Composite group = new Composite(composite, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(group);
		GridDataFactory.fillDefaults().applyTo(group);
		
		Button btRefresh = new Button(group, SWT.PUSH);
		btRefresh.setText("刷新模板列表...");
		btRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshTemplateList();
			}
		});
		
		Button btAddLocal = new Button(group, SWT.PUSH);
		btAddLocal.setText("添加本地模板...");
		btAddLocal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getShell());
				dialog.setFilterExtensions(new String[] {"*.zip", "*.*"});
				String path = dialog.open();
				if (path == null)
					return;
				File file = new File(path);
				if (!file.exists()) {
					MessageDialog.openError(getShell(), "错误", "文件不存在！");
				}
				
				if (isTemplateAreadyInList(path)) {
					MessageDialog.openError(getShell(), "错误", "模板文件已经存在列表中！");
					return;
				}
				
				try {
					Template template = readTemplateInfo(file);
					if (template != null) {
						templates.add(template);
						templateViewer.refresh();
						saveLocalTemplates();
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
					MessageDialog.openError(getShell(), "错误", String.format("读取文件%失败，可能是文件格式不正确，或者文件被其他程序占用", path));
				} 
			}
		});
	}
	
	private boolean isTemplateAreadyInList(String path) {
		for (Template t : templates) {
			if (t.isLocal() && StringUtils.equals(t.getPath(), path))
				return true;
		}
		return false;
	}
	
	private void createRefreshJob() {
		refreshJob = new Job("读取模板列表") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("读取模板列表", IProgressMonitor.UNKNOWN);
				
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						templateViewer.setInput(new Template[0]);
						formBrowser.setText("正在读取模板列表...");
					}
				});
				
				try {
					templates.clear();
					List<Template> remotTemplates = loadRemoteTemplates();
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
					templates.addAll(remotTemplates);
					
					List<Template> localTemplates = loadLocalTemplates();
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
					templates.addAll(localTemplates);
				} catch (IOException e) {
					e.printStackTrace();
					Display.getDefault().syncExec(new Runnable() {
						@Override
						public void run() {
							MessageDialog.openError(getShell(), "错误", "读取在线模板列表时出现错误，可能是地址不正确或网络不通.");
						}
					});
					console.error("读取在线模板列表时出现错误，可能是地址不正确或网络不通.", e);
				}
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
				
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						templateViewer.setInput(templates);
						formBrowser.setText("");
					}
				});
				return Status.OK_STATUS;
			}
		};
		refreshJob.setUser(true);
	}
	
	private void refreshTemplateList() {
		if (refreshJob == null)
			createRefreshJob();
		refreshJob.cancel();
		refreshJob.schedule();
	}
	
	/**
	 * 加载远程模板列表
	 * @return
	 * @throws IOException 
	 */
	private List<Template> loadRemoteTemplates() throws IOException {
		List<Template> templates = new ArrayList<Template>();
		String indexFileUrl = remoteUrl + "/" + "index.xml";
		URL url = new URL(indexFileUrl);
		InputStream is = url.openStream();
		Resource resource = new XMIResourceImpl();
		resource.load(is, null);
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof Template) {
				Template template = (Template) eObject;
				template.setPath(remoteUrl + "/" + template.getPath());
				templates.add((Template) eObject);
			}
		}
		return templates;
	}
	
	private List<Template> loadLocalTemplates() {
		List<Template> templates = new ArrayList<Template>();
		IDialogSettings settings = getDialogSettings();
		String[] paths = settings.getArray("local_templates");
		if (paths != null) {
			for (String path : paths) {
				if (path == null)
					continue;
				
				File file = new File(path);
				if (!file.exists()) {
					continue;
				}
				
				try {
					Template t = readTemplateInfo(file);
					if (t != null)
						templates.add(t);
				} catch (IOException e) {
					e.printStackTrace();
					console.error(String.format("读取模板文件%s的时候出错，可能文件格式不正确或文件被其他程序占用.", file.getPath()), e);
				}
			}
		}
		return templates;
	}
	
	private void saveLocalTemplates() {
		List<String> localTemplates = new ArrayList<String>();
		for (Template t : templates) {
			if (t.isLocal())
				localTemplates.add(t.getPath());
		}
		IDialogSettings settings = getDialogSettings();
		settings.put("local_templates", localTemplates.toArray(new String[0]));
	}
	
	private Template readTemplateInfo(File file) throws IOException {
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(file);
			ZipArchiveEntry entry = zipFile.getEntry("template.xml");
			if (entry != null) {
				InputStream is = zipFile.getInputStream(entry);
				Template template = readTemplateInfo(is);
				template.setLocal(true);
				template.setPath(file.getPath());
				return template;
			}
		} finally {
			ZipFile.closeQuietly(zipFile);
		}
		return null;
	}

	private Template readTemplateInfo(InputStream inputStream) throws IOException {
		Resource resource = new XMIResourceImpl();
		resource.load(inputStream, null);
		if (resource.getContents().size() > 0) {
			return (Template) resource.getContents().get(0);
		}
		return null;
	}
	
	static class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object o, int index) {
			if (o instanceof Template) {
				Template t = (Template) o;
				String text = t.getName();
				if (t.isLocal())
					text += "(本地)";
				return text;
			}
			return getText(o);
		}

		public Image getColumnImage(Object o, int index) {
			return getImage(o);
		}
	}
}
