/**
 * 源程序名称：CellLinkMouseListener.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.viewers.link;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.builder.IAresMarkers;
import com.hundsun.ares.studio.core.model.util.Pair;

/**
 * @author gongyf
 *
 */
public class CellLinkMouseListener extends MouseAdapter {
	
	private Logger logger = Logger.getLogger(getClass());
	private ColumnViewer viewer;
	private ICellLinkProvider provider;
	
	/**
	 * @param viewer
	 * @param provider
	 */
	public CellLinkMouseListener(ColumnViewer viewer, ICellLinkProvider provider) {
		super();
		this.viewer = viewer;
		this.provider = provider;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseAdapter#mouseUp(org.eclipse.swt.events.MouseEvent)
	 */
	@Override
	public void mouseUp(MouseEvent e) {
		if ((e.stateMask & SWT.CTRL) != 0 && e.button == 1) {
			// 如果按下Ctrl，并点击左键
			ViewerCell cell = viewer.getCell(new Point(e.x, e.y));
			if (cell == null) {
				return;
			}
			Pair<EObject, IARESResource> object = provider.getLinkedObject(cell);
			if (object == null || object.first == null || object.second == null) {
				return;
			}
			try {
				IMarker marker = object.second.getResource().createMarker(IAresMarkers.BOOK_MARKER_ID);
				marker.setAttribute(IMarker.LOCATION, object.first.eResource().getURIFragment(object.first));
				
				String editorId = IDE.getEditorDescriptor(object.second.getElementName()).getId();
				marker.setAttribute(IDE.EDITOR_ID_ATTR, editorId);
				
				IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), marker);
				marker.delete();
			} catch (PartInitException ex) {
				logger.error(ex.getMessage(), ex);
			} catch (CoreException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
	}
}
