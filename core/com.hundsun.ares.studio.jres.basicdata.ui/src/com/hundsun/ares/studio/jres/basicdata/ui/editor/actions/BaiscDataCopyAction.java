package com.hundsun.ares.studio.jres.basicdata.ui.editor.actions;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;

public class BaiscDataCopyAction extends CommandActionHandler implements
		IUpdateAction {
	ColumnViewer viewer;
	public BaiscDataCopyAction(ColumnViewer viewer,EditingDomain domain) {
		super(domain, "¸´ÖÆ");
		setId(IActionIDConstant.CV_COPY);
		this.viewer = viewer;
	}

	@Override
	public Command createCommand(Collection<?> selection) {
		return CopyToClipboardCommand.create(domain, selection);
	}

	/**
	 * @deprecated As of EMF 2.1.0, replaced by {@link #setActiveWorkbenchPart}.
	 */
	@Deprecated
	public void setActiveEditor(IEditorPart editorPart) {
		setActiveWorkbenchPart(editorPart);
	}

	/**
	 * @since 2.1.0
	 */
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart)
					.getEditingDomain();
		}
	}

	@Override
	public void update() {
		if(!this.viewer.getSelection().isEmpty()){
			IStructuredSelection selection = (IStructuredSelection)this.viewer.getSelection();
			List objs = selection.toList();
			boolean hasCategory = false;
			if(objs.size() > 0){
				for(Object obj : objs){
					if(obj instanceof MetadataCategory){
						hasCategory = true;
						break;
					}
				}
			}
			if (hasCategory) {
				command = null;
			}else {
				command = createCommand(objs);
			}
		}
		if(null != command && command.canExecute()){
			setEnabled(true);
		}else{
			setEnabled(false);
		}
	}
}