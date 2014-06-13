package com.hundsun.ares.studio.jres.basicdata.ui.editor.pages;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.logic.util.EPackageUtil;
import com.hundsun.ares.studio.jres.basicdata.ui.viewer.ViewerUpdateStrategy;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.assist.IARESResourceAssistantProvider;
import com.hundsun.ares.studio.ui.assist.TextContentAssistEx;
import com.hundsun.ares.studio.ui.dialog.ARESResourceSelectionDialog;
import com.hundsun.ares.studio.ui.editor.FormControlProblemView;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.blocks.JresDetailsPage;
import com.hundsun.ares.studio.ui.util.ARESUIUtil;
import com.hundsun.ares.studio.ui.validate.KeyParameter;

public class MaterSlaveLinkTableDetailPage extends JresDetailsPage {
	/**数据类型*/
	Text txtType;
	/**URL*/
	Text txtUrl;
	/**主表*/
	Text txtMaster;
	/**从表*/
	Text txtSlavel;
	/**关联表*/
	Text txtLink;
	IARESResource resource;
	TableViewer viewer;
	public MaterSlaveLinkTableDetailPage(IARESResource resource, TableViewer viewer) {
		this.resource = resource;
		this.viewer = viewer;
	}

	@Override
	protected String getSectionName() {
		return "主从关联表关联信息";
	}

	@Override
	protected String getDescription() {
		return null;
	}

	@Override
	protected void createSectionContents(final Composite client) {
		IARESResourceAssistantProvider provider = new BasicDataResourceAssistantProvider(resource.getARESProject(),EPackageUtil.getBasicDataType(resource.getARESProject()));
		FormToolkit toolkit = form.getToolkit();
		
		Label lbType = toolkit.createLabel(client, "数据类型",SWT.None);
		txtType = toolkit.createText(client, "",SWT.BORDER);
		txtType.setEnabled(false);
		
		Label lbUrl = toolkit.createLabel(client, "URL",SWT.None);
		txtUrl = toolkit.createText(client, "", SWT.BORDER);
		txtUrl.setEnabled(false);
		
		Button btnBrown = toolkit.createButton(client, "浏览...", SWT.PUSH);
		
		Hyperlink lbMaster = toolkit.createHyperlink(client, "主表", SWT.NONE);
		txtMaster = new TextContentAssistEx(client, SWT.BORDER, provider);//toolkit.createText(client, "", SWT.BORDER);
		
		Hyperlink lbSlave = toolkit.createHyperlink(client, "从表", SWT.NONE);
		txtSlavel = new TextContentAssistEx(client, SWT.BORDER, provider);//toolkit.createText(client, "", SWT.BORDER);
		
		Hyperlink lbLink = toolkit.createHyperlink(client, "关联表", SWT.NONE);
		txtLink = new TextContentAssistEx(client, SWT.BORDER, provider);//toolkit.createText(client, "", SWT.BORDER);
		
		GridLayoutFactory.swtDefaults().numColumns(3).equalWidth(false).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbType);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbUrl);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(btnBrown);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbMaster);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbSlave);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbLink);
		
		GridDataFactory.fillDefaults().grab(true, false).span(2, -1).applyTo(txtType);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtUrl);
		GridDataFactory.fillDefaults().grab(true, false).span(2, -1).applyTo(txtMaster);
		GridDataFactory.fillDefaults().grab(true, false).span(2, -1).applyTo(txtSlavel);
		GridDataFactory.fillDefaults().grab(true, false).span(2, -1).applyTo(txtLink);
		
		btnBrown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ARESResourceSelectionDialog dialog = new ARESResourceSelectionDialog(client.getShell(), resource.getARESProject(), "masterslavelinktabledata");
				dialog.setInitialPattern("**");
				if(Window.OK == dialog.open()){
					Object obj = dialog.getFirstResult();
					if(obj != null && obj instanceof IARESResource){
						IARESResource sel = (IARESResource)obj;
						String path = sel.getResource().getProjectRelativePath().toOSString();
						txtUrl.setText(path);
					}
				}
			}
		});
		lbLink.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				linkARESResource(txtLink.getText());
			}
		});
		lbSlave.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				linkARESResource(txtSlavel.getText());
			}
		});
		lbMaster.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				linkARESResource(txtMaster.getText());
			}
		});
	}

	private void linkARESResource(String resName) {
		ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), EPackageUtil.getBasicDataType(resource.getARESProject()), resName, true);
		if(info != null){
			try {
				ARESUIUtil.openEditor(info.getResource());
			} catch (PartInitException e) {
				e.printStackTrace();
			}	
		}
	}
	
	@Override
	protected DataBindingContext databinding() {
		DataBindingContext context = new DataBindingContext();
		context.bindValue(
				SWTObservables.observeText(txtType, FormWidgetUtils.getDefaultTextDataBingingEvents()), 
				EMFEditObservables.observeValue(page.getEditingDomain(), (EObject)input, BasicdataPackage.Literals.PACKAGE_DEFINE__TYPE),
				EPackageUtil.getTypeTargetToModelStrategy(),
				EPackageUtil.getTypeModelToTargetStrategy());
		context.bindValue(
				SWTObservables.observeText(txtUrl, FormWidgetUtils.getDefaultTextDataBingingEvents()), 
				EMFEditObservables.observeValue(page.getEditingDomain(), (EObject)input, BasicdataPackage.Literals.PACKAGE_DEFINE__URL));
		context.bindValue(
				SWTObservables.observeText(txtMaster, FormWidgetUtils.getDefaultTextDataBingingEvents()), 
				EMFEditObservables.observeValue(page.getEditingDomain(), (EObject)input, BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__MASTER),
				null,
				new ViewerUpdateStrategy(viewer, input));
		
		KeyParameter key = new KeyParameter(input, BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__MASTER);
		page.getProblemPool().addView(new FormControlProblemView(key, txtMaster));
		refreshInitProblem(key,txtMaster);
		
		context.bindValue(
				SWTObservables.observeText(txtSlavel, FormWidgetUtils.getDefaultTextDataBingingEvents()), 
				EMFEditObservables.observeValue(page.getEditingDomain(), (EObject)input, BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__SLAVE),
				null,
				new ViewerUpdateStrategy(viewer, input));
		
		key = new KeyParameter(input, BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__SLAVE);
		page.getProblemPool().addView(new FormControlProblemView(key, txtSlavel));
		refreshInitProblem(key, txtSlavel);
		
		context.bindValue(
				SWTObservables.observeText(txtLink, FormWidgetUtils.getDefaultTextDataBingingEvents()), 
				EMFEditObservables.observeValue(page.getEditingDomain(), (EObject)input, BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__LINK),
				null,
				new ViewerUpdateStrategy(viewer, input));
		
		key = new KeyParameter(input, BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__LINK);
		page.getProblemPool().addView(new FormControlProblemView(key, txtLink));
		refreshInitProblem(key, txtLink);
		return context;
	}

	/**
	 * 刷新初始错误信息
	 * @param control 
	 * @param key 
	 */
	private void refreshInitProblem(KeyParameter key, Control control) {
		Object[] problems = page.getProblemPool().getProblem(key);
		IMessageManager manager = page.getManagedForm().getMessageManager();//form.getMessageManager();
		if (problems != null && problems.length > 0) {
			Diagnostic diagnostic = ((Diagnostic)problems[0]);
			manager.addMessage(key, diagnostic.getMessage(), diagnostic,
					FormControlProblemView.convertDiagnosticSeverity(diagnostic.getSeverity()), control);
		} else {
			manager.removeMessage(key, control);
		}
	}
}
