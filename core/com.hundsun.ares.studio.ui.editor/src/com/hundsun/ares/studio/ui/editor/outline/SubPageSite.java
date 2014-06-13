package com.hundsun.ares.studio.ui.editor.outline;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.SubActionBars;
import org.eclipse.ui.part.IPageSite;

public class SubPageSite implements IPageSite {

	IPageSite parentSite;
	ISelectionProvider selectionProvider;
	SubActionBars subActionBars;
	
	/**
	 * @param parentSite
	 */
	public SubPageSite(IPageSite parentSite, IActionBars actionBars) {
		super();
		this.parentSite = parentSite;
		
		subActionBars = new SubActionBars(parentSite.getActionBars(), parentSite);
	}

	public IPageSite getParentSite() {
		return parentSite;
	}
	
	public SubActionBars getActionBars() {
		return subActionBars;
	}

	public void registerContextMenu(String menuId, MenuManager menuManager,
			ISelectionProvider selectionProvider) {
		
	}

	public IWorkbenchPage getPage() {
		return parentSite.getPage();
	}

	public ISelectionProvider getSelectionProvider() {
		return selectionProvider;
	}

	public Shell getShell() {
		return parentSite.getShell();
	}

	public IWorkbenchWindow getWorkbenchWindow() {
		return parentSite.getWorkbenchWindow();
	}

	public void setSelectionProvider(ISelectionProvider provider) {
		this.selectionProvider = provider;
	}

	public Object getAdapter(Class adapter) {
		return parentSite.getAdapter(adapter);
	}

	public Object getService(Class api) {
		return parentSite.getService(api);
	}

	public boolean hasService(Class api) {
		return parentSite.hasService(api);
	}

	

}
