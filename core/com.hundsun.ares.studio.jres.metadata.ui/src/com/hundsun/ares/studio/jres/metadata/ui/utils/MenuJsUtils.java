package com.hundsun.ares.studio.jres.metadata.ui.utils;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.editors.MenuEditor;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

public class MenuJsUtils {

	public static void refreshFunctions(List<Function> funcs, IARESResource res)
			throws ARESModelException {
		IEditorPart editorPart = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editorPart == null) {
			MessageDialog.openError(null, "错误", "请打开当前工程的菜单与功能编辑器!");
		} else if (editorPart instanceof MenuEditor) {
			if (!((MenuEditor) editorPart).getARESResource().getARESProject() .equals(res
					.getARESProject()) ) {
				MessageDialog.openError(null, "错误", "请打开当前工程的菜单与功能编辑器且处于激活状态!");
			} else if (((MenuEditor) editorPart).getARESResource().getARESProject() .equals(res
					.getARESProject()) ) {
				TransactionalEditingDomain domain = ((MenuEditor) editorPart)
						.getEditingDomain();
				Command command = SetCommand.create(domain,
						((MenuEditor) editorPart).getInfo(),
						MetadataPackage.Literals.MENU_LIST__FUNCTIONS, funcs);
				domain.getCommandStack().execute(command);
			}
		}

	}
}
