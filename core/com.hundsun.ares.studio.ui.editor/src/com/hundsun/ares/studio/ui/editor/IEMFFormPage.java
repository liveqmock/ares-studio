/**
 * 源程序名称：IEMFFormPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor;

import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.forms.editor.IFormPage;

import com.hundsun.ares.studio.ui.editor.editable.IEditableControl;
import com.hundsun.ares.studio.ui.validate.IProblemPool;
import com.hundsun.ares.studio.ui.validate.IValidateControl;

/**
 * @author gongyf
 *
 */
public interface IEMFFormPage extends IFormPage, IEditingDomainProvider {
	/**
	 * 指示模型信息变更，需要进行刷新操作
	 */
	public abstract void infoChange();

	public abstract void validate();

	public abstract IProblemPool getProblemPool();

	public abstract IValidateControl getValidateControl();

	public abstract TransactionalEditingDomain getEditingDomain();

	public abstract EMFFormEditor getEditor();

	public abstract void setEditableControl(IEditableControl editableControl);
}
