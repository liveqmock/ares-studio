package com.hundsun.ares.studio.ui.editor.actions;

import java.util.Collection;
import java.util.EventObject;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Jres比较通用的PasteAction. 
 * 与EMF本身的PasteAction不同的是，它在创建paste Commandd的时候，直接使用Viewer的input作为Owner；
 * 所欲对于那些模型本身有结构的情况，不适用这个Action.
 * @author sundl
 */
public abstract class JresPasteAction extends PasteAction {

	private static final Logger logger = Logger.getLogger(JresPasteAction.class);
	
	protected IWorkbenchPart part;
	@Override
	public Command createCommand(Collection<?> selection) {
		if (selection.size() == 1) {
			return PasteFromClipboardCommand.create(domain, getOwner(selection), getFeature());
		} else {
			return UnexecutableCommand.INSTANCE;
		}
	}
	
	/**
	 * @since 2.1.0
	 */
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		this.part = workbenchPart;
		super.setActiveWorkbenchPart(workbenchPart);
		// Paste Action的可用性不仅和Selection有关，还和CommandStack有关，因为复制的改变也会影响粘贴是否可用
		if (domain != null) {
			domain.getCommandStack().addCommandStackListener(new CommandStackListener() {
				@Override
				public void commandStackChanged(EventObject event) {
					selectionChanged(getStructuredSelection());
					logger.debug("Command stack changed, update action enablement... Class: [" + JresPasteAction.this.getClass().getSimpleName() + "] " + isEnabled());
				}
			});
		}
	}
	
	protected ColumnViewer getColumnViewer() {
		ISelectionProvider selectionProvider = part.getSite().getSelectionProvider();
		if (selectionProvider instanceof ColumnViewer) {
			return (ColumnViewer) selectionProvider;
		}
		return null;
	}
	
	@Override
	public boolean updateSelection(IStructuredSelection selection) {
		boolean enabled = super.updateSelection(selection);
		logger.debug("Paste action selection changed: [" + this.getClass().getSimpleName() + "] " + enabled);
		return enabled;
	}

	/**
	 * 获取粘贴的时候使用的Owner。
	 * 默认的规则为：
	 * 如果SelectionProvider是一个Viewer，则返回这个Viewer的Input；
	 * 否则返回selection对象
	 * @param selection
	 * @return
	 */
	protected Object getOwner(Collection<?> selection) {
		Object sel = selection.iterator().next();
		if (sel instanceof EObject) {
			return ((EObject) sel).eContainer();
		}
		return sel;
	}
	
	/**
	 * 获取粘贴的时候使用的Feature,默认返回null，即让EMF自己判断应该粘贴到哪个Feature.
	 * 修改这个方法可以改变Command的可用性(即，如果返回了一个feature，而实际上剪切板中的内容无法粘贴到这个feature，command会不可用)
	 * @return
	 */
	protected Object getFeature() {
		return null;
	}
	
}
