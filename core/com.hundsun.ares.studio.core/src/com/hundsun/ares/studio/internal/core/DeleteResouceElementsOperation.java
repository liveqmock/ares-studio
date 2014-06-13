/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModelStatusConstants;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IResPathEntry;

/**
 * 删除ARES元素，及其Children的操作。
 * @author sundl
 */
public class DeleteResouceElementsOperation extends MultiOperation {

	/**
	 * 执行这个操作会删除给定的元素，不能为null，不能为空。
	 * @param elementsToProcess
	 * @param force
	 */
	public DeleteResouceElementsOperation(IARESElement[] elementsToProcess, boolean force) {
		super(elementsToProcess, null, force);		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.MultiOperation#getMainTaskName()
	 */
	@Override
	protected String getMainTaskName() {
		return "删除";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.MultiOperation#processElement(com.hundsun.ares.studio.core.IARESElement)
	 */
	@Override
	protected void processElement(IARESElement element) throws ARESModelException {
		if (element instanceof IARESModuleRoot) {
			IARESModuleRoot root = (IARESModuleRoot) element;
			IPath path = root.getPath();
			// path = path.makeRelativeTo(root.getARESProject().getProject().getFullPath());
			path = path.removeFirstSegments(1);
			IResPathEntry entry = ARESCore.newSourceEntry(root.getType(), path);
			IARESProject project = root.getARESProject();
			IResPathEntry[] oldPath = project.getRawResPath();
			IResPathEntry[] newPath = (IResPathEntry[]) ArrayUtils.removeElement(oldPath, entry);
			project.setRawResPath(newPath, null);
		} 
		deleteResource(element.getResource(), this.force ? IResource.FORCE | IResource.KEEP_HISTORY : IResource.KEEP_HISTORY);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.MultiOperation#vertify(com.hundsun.ares.studio.core.IARESElement)
	 */
	@Override
	protected void vertify(IARESElement element) throws ARESModelException {
		if (element == null || !element.exists()) {
			error(IARESModelStatusConstants.ELEMENT_DOES_NOT_EXIST, element);
		}
		
		int type = element.getElementType();
		/*if (type == IARESElement.COMMON_MODULE_ROOT) {
			error(IARESModelStatusConstants.INVALID_ELEMENT_TYPES, element);
		} else*/ if (type == IARESElement.COMMON_MODULE) {
			IARESModule module = (IARESModule)element;
			if (module.isDefaultModule()) {
				error(IARESModelStatusConstants.INVALID_ELEMENT_TYPES, element);
			}
		}
		
	}

}
