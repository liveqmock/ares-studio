/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

import com.hundsun.ares.studio.ui.editor.EMFKeyConstructor;
import com.hundsun.ares.studio.ui.editor.FormProblemPool;
import com.hundsun.ares.studio.ui.editor.validate.DefaultValidateControl;
import com.hundsun.ares.studio.ui.editor.validate.EMFAllValidateUnit;
import com.hundsun.ares.studio.ui.validate.IProblemPool;
import com.hundsun.ares.studio.ui.validate.IValidateControl;
import com.hundsun.ares.studio.validate.ValidateUtil;

/**
 * 可以进行错误检查的扩展页，要求可以获取IARESResource
 * @author gongyf
 *
 */
public abstract class EMFESSFormPageWithValidate<T> extends
		EMFExtendSectionScrolledFormPage<T> {

	private IValidateControl validateControl;
	private IProblemPool problemPool;
	private boolean validateSystemInitialized = false;
	
	/**
	 * 初始化错误检查机制，异步是为了防止在主线程上卡死
	 */
	private Job validateSystemInitJob = new Job("错误检查初始化") {
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			
			validateControl = createValidateControl();
			connectValidateControlToProblemPool();
			configureValidateControl();
			
			validateSystemInitialized = true;
			
			validate();
			
			return Status.OK_STATUS;
		}
	};
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public EMFESSFormPageWithValidate(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	/**
	 * @return the validateControl
	 */
	public IValidateControl getValidateControl() {
		return validateControl;
	}
	
	protected void disposeValidateControl() {
		if (validateControl != null) {
			validateControl.destroyAll();
		}
		
	}
	
	protected IProblemPool createProblemPool() {
		IProblemPool problemPool = new FormProblemPool(getManagedForm().getMessageManager());
		problemPool.setKeyConstructor(new EMFKeyConstructor());
		return problemPool;
	}
	
	/**
	 * @return the problemPool
	 */
	public IProblemPool getProblemPool() {
		return problemPool;
	}
	
	
	protected void connectValidateControlToProblemPool() {
		validateControl.setProblemPool(problemPool);
	}
	
	public void validate() {
		// 只有错误检查系统初始化后并且编辑器不是在引用包中打开的情况下才做错误检查
		if (validateSystemInitialized && !isInReferencedLibrary()) {
			getValidateControl().refresh();
		}
	}
	
	/**
	 * 配置错误检查控制器
	 * 添加检查单元
	 * 设置检查上下文
	 */
	protected void configureValidateControl() {
		getValidateControl().addValidateUnit(new EMFAllValidateUnit(getModel()));
		getValidateControl().setContext(ValidateUtil.getValidateContext(getResource()));
	}
	
	protected void resourceSetChanged(ResourceSetChangeEvent event) {
		super.resourceSetChanged(event);
		if (isNeedValidate(event)) {
			validate();
		}
	}
	
	protected boolean isNeedValidate(ResourceSetChangeEvent event) {
		return true;
	}

	protected IValidateControl createValidateControl() {
		return new DefaultValidateControl();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	final protected void createFormContent(IManagedForm managedForm) {

		problemPool = createProblemPool();
		
		getEditingDomain().getCommandStack().addCommandStackListener(this);
		
		super.createFormContent(managedForm);
		
		validateSystemInitJob.schedule();
		
		validate();
	}
	
}
