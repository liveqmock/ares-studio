/**
 * 源程序名称：ModifyColumnTypeWizardComposite.java
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

import com.hundsun.ares.studio.jres.clearinghouse.command.ModifyColumnTypeCommand;
import com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author sundl
 *
 */
public class ModifyColumnTypeWizardComposite extends ModifyColumnTypeComposite implements IWizardComposite{

	public ModifyColumnTypeWizardComposite(Composite parent, EMFFormEditor editor, Modification action) {
		super(parent, (TableResourceData) editor.getInfo(), editor.getARESResource(), action);
	}
	
	@Override
	public Command getCommand() {
		return new ModifyColumnTypeCommand(tableData, input);
	}
}
