/**
 * 源程序名称：ModifyColumnNullableWizardComposite.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.biz.stock.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import org.eclipse.emf.common.command.Command;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.jres.clearinghouse.command.ModifyColumnNullableCommand;
import com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author sundl
 *
 */
public class ModifyColumnNullableWizardComposite extends ModifyColumnNullableComposite implements IWizardComposite{

	public ModifyColumnNullableWizardComposite(Composite parent, EMFFormEditor editorPart, Modification action) {
		super(parent, (TableResourceData) editorPart.getInfo(), editorPart.getARESResource(), action);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite#getCommand()
	 */
	@Override
	public Command getCommand() {
		return new ModifyColumnNullableCommand(tableData, input);
	}

}
