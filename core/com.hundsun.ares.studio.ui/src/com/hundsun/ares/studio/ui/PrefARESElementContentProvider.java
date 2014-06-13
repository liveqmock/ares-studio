/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;

/**
 * 
 * @author sundl
 */
public abstract class PrefARESElementContentProvider extends ARESElementContentProvider {

	protected TreeViewer viewer;
	protected Object input;
	
	private IPropertyChangeListener propertyListener;

	public PrefARESElementContentProvider() {
		IPreferenceStore store = ARESUI.getDefault().getPreferenceStore();
		propertyListener = new IPropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent event) {
				propertyChanged(event);
			}
		};
		
		store.addPropertyChangeListener(propertyListener);
	}
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { 
		this.viewer = (TreeViewer)viewer;
		this.input = newInput;
		super.inputChanged(viewer, oldInput, newInput);
	}
	
	protected abstract void propertyChanged(PropertyChangeEvent event);

	protected void safeRefresh() {
		Control ctrl = viewer.getTree();
		if (!ctrl.isDisposed()) {
			if (ctrl.getDisplay().getThread() == Thread.currentThread()) {
				refresh();
			} else {
				viewer.getTree().getDisplay().asyncExec(new Runnable() {
					public void run() {
						refresh();
					}
				});
			}
		}
	}
	
	protected void refresh() {
		viewer.getTree().setRedraw(false);
		viewer.refresh();
		viewer.getTree().setRedraw(true);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		IPreferenceStore store = ARESUI.getDefault().getPreferenceStore();
		store.removePropertyChangeListener(propertyListener);
	}
	
}
