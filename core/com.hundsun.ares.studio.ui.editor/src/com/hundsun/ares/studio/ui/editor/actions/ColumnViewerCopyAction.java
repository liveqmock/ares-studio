/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.ui.editor.viewers.IColumnViewerTextCopySupport;
import com.hundsun.ares.studio.ui.util.ARESEMFClipboard;

/**
 * 
 * 目前只支持EMF对象的复制
 * @author gongyf
 *
 */
public class ColumnViewerCopyAction extends Action implements IUpdateAction {

	private ColumnViewer viewer;
	
	/**
	 * @param viewer
	 */
	public ColumnViewerCopyAction(ColumnViewer viewer) {
		super();
		this.viewer = viewer;
		
		setText("复制");
		setEnabled(false);
		
		setId(IActionIDConstant.CV_COPY);
		
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.IUpdateAction#update()
	 */
	@Override
	public void update() {
		setEnabled(calculateEnabled());
	}
	
	/**
	 * @return the viewer
	 */
	public ColumnViewer getViewer() {
		return viewer;
	}
	
	/**
	 * 不会返回null
	 * @return
	 */
	protected List<EObject> getSelectedObjects() {
		ISelection selection = getViewer().getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			List<EObject> selectedObjects = new ArrayList<EObject>();
			for (Object obj : ((IStructuredSelection) selection).toList()) {
				if (obj instanceof EObject) {
					selectedObjects.add((EObject) obj);
				}
			}
			return selectedObjects;
		}
		return Collections.emptyList();
	}

	protected boolean calculateEnabled() {
		return !getSelectedObjects().isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		final List<EObject> selectedObjects = getSelectedObjects();

		try {
			new ProgressMonitorDialog(getViewer().getControl().getShell()).run(false, false, new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {
					monitor.beginTask("复制表格内容", IProgressMonitor.UNKNOWN);
					
					try {
						// 将选中的文件也进行复制
						StringBuffer sb = new StringBuffer();
						int columnCount = 0;
						if (viewer instanceof TableViewer) {
							columnCount = ((TableViewer) viewer).getTable().getColumnCount();
						} else if (viewer instanceof TreeViewer) {
							columnCount = ((TreeViewer) viewer).getTree().getColumnCount();
						}
						
						for (EObject eObject : selectedObjects) {
							for (int i = 0; i < columnCount; i++) {
								ColumnLabelProvider lp = (ColumnLabelProvider) viewer.getLabelProvider(i);
								if (i > 0) {
									sb.append("\t");
								}
								if (lp instanceof IColumnViewerTextCopySupport) {
									sb.append(escape(StringUtils.defaultString(((IColumnViewerTextCopySupport) lp).getCopyText(eObject))));
								} else {
									sb.append(escape(StringUtils.defaultString( lp.getText(eObject))));
								}
							}
							sb.append("\n");
						}

						ARESEMFClipboard.getInstance().copyToClipboard(selectedObjects.toArray(new EObject[selectedObjects.size()]), sb.toString(), null);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						monitor.done();
					}
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}
	
	private String escape(String text) {
		return StringUtils.replaceEach(text, new String[]{"\t", "\n", "\r"}, new String[]{" ", " ", " "});
	}
	
}
