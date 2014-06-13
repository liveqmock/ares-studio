/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.reference.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author gongyf
 *
 */
public class ARESElementDeltaVisitor implements IResourceDeltaVisitor {

	List<IARESElement> addedElements = new ArrayList<IARESElement>();
	List<IARESElement> removedElements = new ArrayList<IARESElement>();
	List<IARESElement> changedElements = new ArrayList<IARESElement>();
	List<IARESElement> addedOrChangedElements = new ArrayList<IARESElement>();
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	 */
	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {

		IResource resource = delta.getResource();
		int type = resource.getType();
		switch (type) {
		case IResource.PROJECT: {
			IARESProject aresProject = ARESCore.create((IProject)resource);
			if (delta.getKind() == IResourceDelta.REMOVED
					|| (delta.getKind() == IResourceDelta.CHANGED
							&& ((delta.getFlags() & IResourceDelta.OPEN) != 0) && ((IProject) resource)
							.isOpen())) {
				removedElements.add(aresProject);
			} else if (delta.getKind() == IResourceDelta.ADDED) {
				addedElements.add(aresProject);
				addedOrChangedElements.add(aresProject);
			} else {
				// 是否引用关系变化
				
			}
		}
		case IResource.FOLDER: {
			// 只处理模块跟下的文件
			IARESElement element = ARESCore.create(resource);
			if (element == null) {
				return false;
			}

			return true;
		}
		case IResource.FILE: {
			IARESElement element = ARESCore.create(resource);
			if (element instanceof IARESResource) {
				switch (delta.getKind()) {
				case IResourceDelta.ADDED:
					// 添加该资源所包含的可引用信息
					addedElements.add(element);
					addedOrChangedElements.add(element);
					break;
				case IResourceDelta.REMOVED:
					// 删除该资源所提供的可引用信息
					removedElements.add(element);
					break;
				case IResourceDelta.CHANGED:
					if ((delta.getFlags() & IResourceDelta.CONTENT) != 0) {
						// 更新该资源关联的引用信息，可以先删除后增加的方式，或者采用对比后只更新变化的部分
						changedElements.add(element);
						addedOrChangedElements.add(element);
					}
				default:
					break;
				}
			}
			return true;
		}
		}
		
		return true;
	}

	/**
	 * @return the addedElements
	 */
	public List<IARESElement> getAddedElements() {
		return addedElements;
	}
	
	/**
	 * @return the changedElements
	 */
	public List<IARESElement> getChangedElements() {
		return changedElements;
	}
	
	/**
	 * @return the removedElements
	 */
	public List<IARESElement> getRemovedElements() {
		return removedElements;
	}
	
	/**
	 * @return the addedOrChangedElements
	 */
	public List<IARESElement> getAddedOrChangedElements() {
		return addedOrChangedElements;
	}
}
