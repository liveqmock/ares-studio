package com.hundsun.ares.studio.biz.ui.editor.page;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TriggerListener;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.ui.block.ErrorInfoBlock;
import com.hundsun.ares.studio.biz.ui.block.InputParameterBlock;
import com.hundsun.ares.studio.biz.ui.block.OutputParameterBlock;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;

/**
 * @author gongyf
 * @author sundl
 */
public class InterfacePage extends DataBindingFormPage {

	protected InputParameterBlock inputParamBlock;
	protected OutputParameterBlock outputParamBlock;
	protected ErrorInfoBlock errorInfoBlock;
	
	protected EStructuralFeature interfaceFeature;
	
	private TriggerListener trigger = new TriggerListener() {
		@Override
		protected Command trigger(TransactionalEditingDomain domain, Notification notification) {
			if (notification.getNotifier() instanceof Parameter ) {
				if ( BizPackage.Literals.PARAMETER__TYPE.equals(notification.getFeature())) {
					// 在输入输出参数中设置类型,将自动把对应的java类型代入
					final Parameter pd = (Parameter) notification.getNotifier();
					// sundl 只有非标准字段才需要进行这样的处理
					if (pd.getParamType() != ParamType.NON_STD_FIELD)
						return null;
					
					final String type = pd.getType();
					return new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							IMetadataService service = DataServiceManager.getInstance().getService(
									getEditor().getARESResource().getARESProject(), IMetadataService.class);
							IBusinessDataType bizType = service.getBusinessDataType(type);
							if (bizType != null) {
//								pd.setRealType(bizType.getRealType("java"));
								pd.setRealType(bizType.getRealType(getDataType()));
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	protected String getDataType(){
		return MetadataServiceProvider.C_TYPE;
	}
	
	/**
	 * 创建一个页面的实例；
	 * Page只能取到Editor的Info，而如果Editor的Info不是直接继承的BizInterface接口的话（用的组合的方式），此时就必须传入一个EStructuredFeature，
	 * 对应Info用来组合BizInterface的那个EMF属性
	 * @param interfaceFeature 
	 * @param editor
	 * @param id
	 * @param title
	 */
	public InterfacePage(EStructuralFeature interfaceFeature, EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
		this.interfaceFeature = interfaceFeature;
		getEditingDomain().addResourceSetListener(trigger);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		//composite.setLayout(new FillLayout());
		GridLayoutFactory.swtDefaults().applyTo(composite);
		Section inputSection = createInputSection(composite, toolkit);
		Section outputSection = createOuputSection(composite, toolkit);
		Section errorInfoSection = createErrorInfoSection(composite, toolkit);
		
		GridDataFactory.fillDefaults().grab(true, false).applyTo(inputSection);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(outputSection);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(errorInfoSection);
		
		composite.getParent().layout();
	}
	
	
	protected Section createInputSection(Composite composite, FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("输入参数");
		
		Composite content = toolkit.createComposite(section);
		GridLayoutFactory.fillDefaults().applyTo(content);
		
		// 是否集合的标记
//		isInputCollectionBtn = toolkit.createButton(content, "数据集", SWT.CHECK);
//		GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(true, false).applyTo(isInputCollectionBtn);

		Composite block = toolkit.createComposite(content);
		GridDataFactory.fillDefaults().indent(0, 0).grab(true, true).applyTo(block);
		block.setLayout(new FillLayout());
		
		// 列表
		inputParamBlock = new InputParameterBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		inputParamBlock.setEditableControl(getEditableControl());
		inputParamBlock.setDataType(getDataType());
		customizeInputParamBlock();
		inputParamBlock.createControl(block, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(inputParamBlock);
		
		addPropertyListener(inputParamBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(inputParamBlock);
		
		section.setClient(content);
		return section;
	}
	

	/***
	 * 用于子类定制一些InputBlock的属性
	 */
	protected void customizeInputParamBlock() {
		
	}

	protected Section createOuputSection(Composite composite, FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("输出参数");
		
		Composite client = toolkit.createComposite(section);
		GridLayoutFactory.fillDefaults().applyTo(client);
		
		// 是否集合的标记
//		isOutputCollectionBtn = toolkit.createButton(client, "数据集", SWT.CHECK);
//		GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(true, false).applyTo(isOutputCollectionBtn);
		
		Composite block = toolkit.createComposite(client);
		GridDataFactory.fillDefaults().indent(0, 0).grab(true, true).applyTo(block);
		block.setLayout(new FillLayout());

		outputParamBlock = new OutputParameterBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		outputParamBlock.setEditableControl(getEditableControl());
		outputParamBlock.setDataType(getDataType());
		customizeOutputParamBlock();
		outputParamBlock.createControl(block, toolkit);
		
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(outputParamBlock);
		//GridDataFactory.fillDefaults().applyTo(outputParamBlock.getControl());
		
		addPropertyListener(outputParamBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(outputParamBlock);
		
		section.setClient(client);

		return section;		
	}

	/***
	 * 设置输出参数列表添加按钮组的内容
	 */
	protected void customizeOutputParamBlock() {
		
	}
	
	protected Section createErrorInfoSection(Composite composite, FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("出错说明");
		section.setExpanded(false);
		
		Composite client = toolkit.createComposite(section);
		//GridLayoutFactory.fillDefaults().applyTo(client);
		client.setLayout(new FillLayout());
		
		errorInfoBlock = new ErrorInfoBlock(BizPackage.Literals.BIZ_INTERFACE__ERROR_INFOS, getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		errorInfoBlock.setEditableControl(getEditableControl());
		errorInfoBlock.createControl(client, toolkit);
		
		addPropertyListener(errorInfoBlock);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(errorInfoBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(errorInfoBlock);

		section.setClient(client);
		return section;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.DataBindingFormPage#doDataBingingOnControls()
	 */
	@Override
	protected void doDataBingingOnControls() {
		// 使用数据绑定来处理
//		bingSelection(isOutputCollectionBtn, getInfo(),	BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION);
//		bingSelection(isInputCollectionBtn, getInfo(), BizPackage.Literals.BIZ_INTERFACE__INPUT_COLLECTION);
	}

	public void infoChange() {
		if ( inputParamBlock != null ) {
			inputParamBlock.setInput(getInterface());
		}
		if ( outputParamBlock != null ) {
			outputParamBlock.setInput(getInterface());
		}
		if (errorInfoBlock != null) {
			errorInfoBlock.setInput(getInterface());
		}
		super.infoChange();
	}
	
	protected BizInterface getInterface() {
		JRESResourceInfo info = getInfo();
		if(null == interfaceFeature) {
			return (BizInterface) info;
		}
		return (BizInterface) info.eGet(this.interfaceFeature);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#dispose()
	 */
	@Override
	public void dispose() {
		getEditingDomain().removeResourceSetListener(trigger);
		
		removePropertyListener(inputParamBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(inputParamBlock);
		
		removePropertyListener(outputParamBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(outputParamBlock);
		
		removePropertyListener(errorInfoBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(errorInfoBlock);

		super.dispose();
	}

}
