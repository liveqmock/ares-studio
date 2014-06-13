package com.hundsun.ares.studio.ui.editor.outline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.SubActionBars;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * 对于多页编辑器，如果需要每个页面对应自己的大纲视图，则可以在主编辑器内返回这个
 * 大纲视图类。需要在多页编辑器中{@link MultiPageEditorPart#pageChange(int newPageIndex)}
 * 调用{@link #setPageActive(IContentOutlinePage)}。而且还需要注意不要对于同一个页面有多个大纲对象实例
 * @author gongyf
 *
 */
public class ContentOutlinePageForMultiPageEditorPart extends Page implements IContentOutlinePage, ISelectionChangedListener, IAdaptable {

	private IActionBars actionBars;
	private PageBook pagebook;
	private ISelection selection;
	private ArrayList<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();;
	private IContentOutlinePage currentPage;
	private IContentOutlinePage emptyPage;
	private Map<IContentOutlinePage, SubActionBars> pageToActionBarsMap = new HashMap<IContentOutlinePage, SubActionBars>();
	
	@Override
	public void createControl(Composite parent) {
		pagebook = new PageBook(parent, SWT.NONE);
	}

	@Override
	public Control getControl() {
		return pagebook;
	}

	@Override
	public void setFocus() {
		if (currentPage != null)
			currentPage.setFocus();
	}
	
	public Object getAdapter(Class adapter) {
		if (currentPage instanceof IAdaptable) {
			return ((IAdaptable) currentPage).getAdapter(adapter);
		}
		return null;
	}
	
	@Override
	public void dispose() {
		
		if (pagebook != null && !pagebook.isDisposed())
			pagebook.dispose();
		if (emptyPage != null) {
			emptyPage.dispose();
			emptyPage = null;
		}
		pagebook = null;
		listeners = null;
		super.dispose();
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		listeners.add(listener);
	}

	public ISelection getSelection() {
		return selection;
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		listeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
		this.selection = selection;
		SelectionChangedEvent e = new SelectionChangedEvent(this, selection);
		for (ISelectionChangedListener listener : listeners) {
			listener.selectionChanged(e);
		}
	}

	public void selectionChanged(SelectionChangedEvent event) {
		setSelection(event.getSelection());
	}

	
	public void setActionBars(IActionBars actionBars) {
		this.actionBars = actionBars;
		if (currentPage != null)
			setPageActive(currentPage);

	}

	public IActionBars getActionBars() {
		return actionBars;
	}
	
	private IContentOutlinePage getEmptyPage() {
		if (emptyPage == null)
			emptyPage = new EmptyOutlinePage();
		return emptyPage;
	}
	
	public void setPageActive(IContentOutlinePage page) {
		if (page == null) {
			page = getEmptyPage();
		}
		if (currentPage != null) {
			currentPage.removeSelectionChangedListener(this);
		}
		
		page.addSelectionChangedListener(this);
		IContentOutlinePage oldPage = this.currentPage;
		this.currentPage = page;
		if (pagebook == null) {
			// still not being made
			return;
		}
		Control control = page.getControl();
		if (control == null || control.isDisposed()) {
			if (page instanceof IPageBookViewPage) {
				try {
					SubPageSite subPageSite = new SubPageSite(getSite(), getActionBars());
					((IPageBookViewPage) page).init(subPageSite);
					pageToActionBarsMap.put(page, subPageSite.getActionBars());
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
			// first time
			page.createControl(pagebook);
			if (!(page instanceof IPageBookViewPage)) {
				SubActionBars subActionBars = new SubActionBars(getActionBars());
				page.setActionBars(subActionBars);
				pageToActionBarsMap.put(page, subActionBars);
			}
			control = page.getControl();
		}
		pagebook.showPage(control);
		
		// 对ActionBar的更新
		SubActionBars subActionBars = pageToActionBarsMap.get(oldPage);
		if (subActionBars != null) {
			subActionBars.deactivate();
		}
		
		subActionBars = pageToActionBarsMap.get(page);
		if (subActionBars != null) {
			subActionBars.activate();
		}
		
		// 将快捷键重新注册
		if (subActionBars != null) {
			getActionBars().clearGlobalActionHandlers();
			Map newActionHandlers = subActionBars.getGlobalActionHandlers();
			if (newActionHandlers != null) {
				Set keys = newActionHandlers.entrySet();
				Iterator iter = keys.iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					getActionBars().setGlobalActionHandler((String) entry.getKey(),
							(IAction) entry.getValue());
				}
			}
		}
		
		getActionBars().updateActionBars();
	}
	
	static class EmptyOutlinePage implements IContentOutlinePage {
		private Composite control;

		/**
		 * 
		 */
		public EmptyOutlinePage() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite)
		 */
		public void createControl(Composite parent) {
			control = new Composite(parent, SWT.NULL);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.part.IPage#dispose()
		 */
		public void dispose() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.part.IPage#getControl()
		 */
		public Control getControl() {
			return control;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.part.IPage#setActionBars(org.eclipse.ui.IActionBars)
		 */
		public void setActionBars(IActionBars actionBars) {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.part.IPage#setFocus()
		 */
		public void setFocus() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
		 */
		public void addSelectionChangedListener(ISelectionChangedListener listener) {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
		 */
		public ISelection getSelection() {
			return new ISelection() {
				public boolean isEmpty() {
					return true;
				}
			};
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
		 */
		public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
		 */
		public void setSelection(ISelection selection) {
		}
	}
}
