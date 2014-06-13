package com.hundsun.ares.studio.jres.obj.ui.editor;

import java.util.EventObject;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TriggerListener;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelComposite;

/**
 * 对象编辑页面
 * @author sundl
 */
public class ObjectPage extends DataBindingFormPage {

	private Text txtObjId;
	public Text txtEnglishName;
	private Text txtName;
	private Text txtDesc; 
	private ExtensibleModelComposite emc;

	
	private TriggerListener trigger = new TriggerListener() {
		
		@Override
		protected Command trigger(TransactionalEditingDomain domain,
				Notification notification) {

			if (notification.getNotifier() instanceof  ARESObject) {
				if (BizPackage.Literals.PARAMETER__TYPE.equals(notification.getFeature())) {
					// 在输入输出参数中设置类型,将自动把对应的java类型代入
					final Parameter pd = (Parameter) notification.getNotifier();
					final String type = pd.getType();
					return new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							IMetadataService service = DataServiceManager.getInstance().getService(
									getEditor().getARESResource().getARESProject(), IMetadataService.class);
							IBusinessDataType bizType = service.getBusinessDataType(type);
							if (bizType != null) {
								pd.setRealType(bizType.getRealType("java"));
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public ObjectPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
		getEditingDomain().addResourceSetListener(trigger);
	}

	@Override
	protected void doDataBingingOnControls() {
		bingText(txtObjId, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID);
		bingText(txtName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME);
		bingText(txtDesc, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION);
		bingText(txtEnglishName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__NAME);
	}

	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		Section baseSection = createBaseInfoSection(composite, toolkit);
		Section extendSection = createExtendedInfoSection(composite, toolkit);
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(baseSection);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(extendSection);
		
		composite.getParent().layout();
	}

	protected Section createBaseInfoSection(Composite composite, FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("基本信息");
		
		Composite content = toolkit.createComposite(section, SWT.NONE);
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).applyTo(content);
		
		content.setLayout(new GridLayout(4, false));
		
		Label lbObjectNo = toolkit.createLabel(content, "对象号");
		GridDataFactory.fillDefaults().applyTo(lbObjectNo);
		txtObjId = toolkit.createText(content, "" ,SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtObjId);
		
		Label lblEnglishName = toolkit.createLabel(content, "英文名：");
		txtEnglishName = toolkit.createText(content, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles()|SWT.READ_ONLY);
		GridDataFactory.fillDefaults().applyTo(lblEnglishName);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtEnglishName);
		
		Label lblName = toolkit.createLabel(content, "中文名：");
		txtName = toolkit.createText(content, "", SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(lblName);
		GridDataFactory.fillDefaults().grab(true, true).hint(10, SWT.DEFAULT).applyTo(txtName);
		
		Label lblVersion = toolkit.createLabel(content, "版本号：");
		Text txtVersion = toolkit.createText(content, "", SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(lblVersion);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(txtVersion);
		
		Label lblUpdate = toolkit.createLabel(content, "更新时间：");
		Text txtUpdate = toolkit.createText(content, "", SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(lblUpdate);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(txtUpdate);
		
		Composite cc = new Composite(content, SWT.NONE);
		GridDataFactory.swtDefaults().grab(true, false).hint(-1, 20).span(2, 1).applyTo(cc);
		
		{
			//找出最新的版本号
			RevisionHistory his = RevisionHistoryUtil.getMaxVersionHisInfo(getEditor().getARESResource().getModule() ,IBizResType.Object);
			if (his != null) {
				txtVersion.setText(his.getVersion());
				txtUpdate.setText(his.getModifiedDate());
			}else {
				his = RevisionHistoryUtil.getMaxVersionHisInfo(getEditor().getARESResource().getModule());
				if (his != null) {
					txtVersion.setText(his.getVersion());
					txtUpdate.setText(his.getModifiedDate());
				}else {
					String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(getEditor().getARESResource().getARESProject());
					if (StringUtils.isBlank(projectVersion)) {
						projectVersion = "1.0.0.1";
					}
					txtVersion.setText(projectVersion);
				}
			}
		}
		
		Label lblDesc = toolkit.createLabel(content, "说明：");
		txtDesc = toolkit.createText(content, "", SWT.H_SCROLL | SWT.V_SCROLL| SWT.MULTI | SWT.WRAP | SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(lblDesc);
		GridDataFactory.fillDefaults().grab(true, true).hint(500, 50).span(3, 1).applyTo(txtDesc);
		
		txtEnglishName.setEditable(false);
		txtVersion.setEditable(false);
		txtUpdate.setEditable(false);
		txtEnglishName.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtVersion.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtUpdate.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		
		//只读控制
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtObjId));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtName));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtDesc));
		
		section.setClient(content);
		
		return section;
	}
	
	@Override
	public void infoChange() {
		emc.setInput(getEditor().getARESResource(), getInfo());
		super.infoChange();
		
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		emc.refresh();
	}
	
	protected Section createExtendedInfoSection(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("扩展信息");
		
		emc = new ExtensibleModelComposite(section, toolkit);
		emc.setProblemPool(getProblemPool());
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(emc));
		
		section.setClient(emc);
		return section;
	}
	
}
