/**
 * 源程序名称：JumpRefItemAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.builder.IAresMarkers;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;

/**
 * @author wangxh
 *
 */
public class JumpRefItemAction extends Action {
	IARESResource res;
	MetadataItem item;
	private Logger logger = Logger.getLogger(getClass());
	public JumpRefItemAction(IARESResource res, MetadataItem item) {
		super();
		this.item = item;
		this.res = res;
	}
	@Override
	public void run() {
		try {
			try {
				IMarker marker = res.getResource().createMarker(IAresMarkers.BOOK_MARKER_ID);
				marker.setAttribute(IMarker.LOCATION, item.eResource().getURIFragment(item));
				IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), marker);
				marker.delete();
			} catch (PartInitException e) {
				logger.error(e.getMessage(), e);
			} catch (CoreException e) {
				logger.error(e.getMessage(), e);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
