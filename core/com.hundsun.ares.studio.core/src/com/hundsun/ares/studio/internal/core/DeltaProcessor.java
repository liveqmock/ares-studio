/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESElementChangedEvent;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESElementChangeListener;
import com.hundsun.ares.studio.core.IARESModel;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IOpenable;
import com.hundsun.ares.studio.core.IResPathEntry;

/**
 * 默认的DeltaProcessor
 * @author sundl
 */
public class DeltaProcessor implements IResourceChangeListener{
	
	private static final Logger logger = Logger.getLogger(DeltaProcessor.class);
	
	List<IARESElementChangeListener> listeners = new ArrayList<IARESElementChangeListener>();
	
	// 记录某个项目的引用资源包的添加删除，暂时用来
	Map<IProject, Boolean> libChangeState = new HashMap<IProject, Boolean>();

	public void resourceChanged(IResourceChangeEvent event) {
		logger.debug("Delta processing begin...");
		
		// 避免log的任何异常引起缓存错误。
		try {
			logEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 清空状态
		libChangeState.clear();
		
		IResource resource = event.getResource();
		IResourceDelta delta = event.getDelta();
		
		switch (event.getType()) {
		case IResourceChangeEvent.PRE_DELETE:
		case IResourceChangeEvent.PRE_CLOSE:
			// project-delete event should be processed before the underlying resource is really deleted.
			if (resource.getType() == IResource.PROJECT ) {
				projectPreDelete(event);
			}
			return;
		case IResourceChangeEvent.POST_CHANGE:
			checkProjectChanges(delta);
			processResourceDelta(delta);
			break;
		}
		// TODO 优化，某些时候不需要发送通知事件
		fireChangeEvent(new ARESElementChangedEvent());
		logger.debug("Delta processing end...");
	}
	
	private void checkProjectChanges(IResourceDelta delta) {
		IResource resource = delta.getResource();
		IResourceDelta[] children = null;

		switch (resource.getType()) {
			case IResource.ROOT :
				children = delta.getAffectedChildren();
				break;
			case IResource.PROJECT :
				IProject project = (IProject)resource;
				IARESProject aresProject = ARESCore.create(project);
				//if (aresProject.exists()) {
				switch (delta.getKind()) {
					case IResourceDelta.CHANGED:
						// 2012-03-29 sundl 分开处理OPEN和Description事件；并且替换exist方法为调用hasNature
						if ((delta.getFlags() & IResourceDelta.OPEN) != 0 ) {
							// OPEN-CLOSE的情况
							if (project.isOpen() && ARESProject.hasARESNature(project)) {
								addToParentInfo(aresProject);
							} else {
								try {
									aresProject.close();
								} catch (ARESModelException e) {
									// project doesn't exist or is not open: ignore
								}
								removeFromParentInfo(aresProject);
							}
						} else if ((delta.getFlags() & IResourceDelta.DESCRIPTION) != 0) {
							// 处理添加删除ARES Nature的情况
							boolean wasARESProject = wasARESProject(project);
							boolean isARESPRoject = ARESProject.hasARESNature(project);
							if (wasARESProject != isARESPRoject) {
								if (isARESPRoject) {
									addToParentInfo(aresProject);
								} else {
									try {
										aresProject.close();
									} catch (ARESModelException e) {
										// project doesn't exist or is not open: ignore
									}
									removeFromParentInfo(aresProject);
								}
							}
						}
				}	
			break;
		}
		
		if (children != null) {
			for (int i = 0; i < children.length; i++) {
				checkProjectChanges(children[i]);
			}
		}
	}
	
	private boolean wasARESProject(IProject project) {
		try {
			IARESProject[] aresProjects = ARESModelManager.getManager().getModel().getARESProjects();
			for (IARESProject aresProj : aresProjects) {
				if (project.equals(aresProj.getProject()))
					return true;
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected void projectPreDelete(IResourceChangeEvent event) {
		try {
			IResource resource = event.getResource();
			// 这里判断项目类型,估计还需要修改；因为exists方法的不确定性，可能需要直接project.hasNature
			IARESProject ap = ARESCore.create((IProject)resource);
			if (ap != null && ap.exists()) {
				ap.close();
				removeFromParentInfo(ap);
			}
		} catch (CoreException e) {
			// project doesn't exist or is not open: ignore
		}
	}
	
	protected void processResourceDelta(IResourceDelta changes) {
		try {
			IARESModel model = ARESCore.getModel();
			if (!model.isOpen()) {
				model.open(null);
			}
			
			IResourceDelta[] deltas = changes.getAffectedChildren(IResourceDelta.ADDED | IResourceDelta.REMOVED | IResourceDelta.CHANGED);
			for (IResourceDelta delta : deltas) {
				IResource res = delta.getResource();
				IProject project = (IProject)res;
				// 所有的项目都要有一个ARES Nature
				if (ARESProject.hasARESNature(project)) {
					traverseDelta(delta);
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void traverseDelta(IResourceDelta delta) {
		boolean processChildren = updateCurrentDelta(delta);
		if (processChildren) {
			IResourceDelta[] children = delta.getAffectedChildren();
			for (IResourceDelta child : children) {
				traverseDelta(child);
			}
		}
	}
	
	protected boolean updateCurrentDelta(IResourceDelta delta) {
		logger.trace("update delta: " + toDebugString(delta));
		IResource resource = delta.getResource();
		
		// 2012-2-28 sundl 增加调用respath provider判断是否有respath的变化。
		final IProject project = resource.getProject();
		
		// 先检查.respath文件，分开处理，respath文件就在这里处理
		if (resource.getProjectRelativePath().equals(new Path(ARESProject.RES_PATH_FILE))) {
			logger.trace(String.format(".respath file in project \"%s\" changed!", project.getName()));
			updateResPath(ARESCore.create(project));
			return false;
		}
		
//		boolean respathChanged = false;
//		// 如果respath文件没有发生变化，再调用respath provider
//		RespathProviderRegistry reg = RespathProviderRegistry.getInstance();
//		for (IRespathProviderDescriptor provider : reg.getProviders()) {
//			IRespathProvider rpProvider = provider.getProvider();
//			if (rpProvider.containsRespathChange(delta)) {
//				respathChanged = true;
//				logger.trace(String.format("respath provider \"%s\" reports that respath changed!", provider.getName()));
//				break;
//			}
//		}
		
//		if (respathChanged) {
//			Job job = new Job("") {
//				@Override
//				protected IStatus run(IProgressMonitor monitor) {
//					try {
//						IARESProject aresProj = ARESCore.create(project);
//						IResPathEntry[] oldPath = aresProj.getRawResPath();
//						IResPathEntry[] newPath = ((ARESProject) aresProj).readResPath();
//						resPathChanged(aresProj, oldPath, newPath, false);
//						libChangeState.put(project, true);
//					} catch (ARESModelException e) {
//						e.printStackTrace();
//					}
//					fireChangeEvent();
//					return Status.OK_STATUS;
//				}
//			};
//			job.schedule();
//			return false;
//		}

//		if (respathChanged) {
//			try {
//				IARESProject aresProj = ARESCore.create(project);
//				IResPathEntry[] oldPath = aresProj.getRawResPath();
//				IResPathEntry[] newPath = ((ARESProject) aresProj).readResPath();
//				resPathChanged(aresProj, oldPath, newPath, false);
//				libChangeState.put(project, true);
//			} catch (ARESModelException e) {
//				e.printStackTrace();
//			}
//			return false;
//		}
		
		IARESElement element = ARESCore.create(resource);
		if (element == null)
			return false;
		switch(delta.getKind()) {
		case IResourceDelta.ADDED:			
			elementAdded(element, delta);
			logger.trace("update delta: element added");
			return element.getElementType() == IARESElement.COMMON_MODULE;
		case IResourceDelta.REMOVED:
			elementRemoved(element, delta);
			logger.trace("update delta: element removed");
			// 对于Module要继续处理，因为在内存中，模块都是平级的。 
			return element.getElementType() == IARESElement.COMMON_MODULE;
		case IResourceDelta.CHANGED:
			logger.trace("update delta: element changed");
			int flags = delta.getFlags();
			if ((flags & IResourceDelta.CONTENT) != 0) {			
				if (element instanceof IOpenable) {
					try {
						((IOpenable) element).close();
						logger.trace("update delta: element closed...");
					} catch (ARESModelException e) {
					}
				}
			}			
			return true;
		}
		return true;
	}
	
	/**
	 * 扩展的MavenProvider由于变化的监听是异步模式，所以这里需要开放一个借口供其调用，让它可以主动在pom变化以后刷新respath.
	 * @param project
	 */
	public void updateResPath(IARESProject project) {
		try {
			IResPathEntry[] oldPath = project.getRawResPath();
			IResPathEntry[] newPath = ((ARESProject) project).readResPath();
			resPathChanged(project, oldPath, newPath, true);
			libChangeState.put(project.getProject(), true);
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasLibPathChanged(IProject project) {
		Boolean changed = libChangeState.get(project);
		if (changed != null) {
			return changed;
		}
		return false;
	}
	
	private void elementAdded(IARESElement element, IResourceDelta delta) {
		try {
			if ((delta.getFlags() & IResourceDelta.MOVED_FROM) == 0) {
				addToParentInfo(element);
				if (element instanceof IOpenable) {
					((IOpenable) element).close();
				}
			} else {
				addToParentInfo(element);
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
	}
	
	protected void elementRemoved(IARESElement element, IResourceDelta delta) {
		if (element instanceof IOpenable) {
			try {
				((IOpenable)element).close();
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		removeFromParentInfo(element);
	} 
	
	protected void addToParentInfo(IARESElement child) {
		Openable parent = (Openable)child.getParent();
		if(parent != null && parent.isOpen()) {
			ARESElementInfo info;
			try {
				info = (ARESElementInfo)parent.getElementInfo();
				info.addChild(child);
			} catch (ARESModelException e) {
				e.printStackTrace();
			} 
		}
	}
	
	/*
	 * Removes the given element from its parents cache of children. If the
	 * element does not have a parent, or the parent is not currently open,
	 * this has no effect.
	 */
	protected void removeFromParentInfo(IARESElement element) {
		IARESElement parent = element.getParent();
		if (parent instanceof IOpenable) {
			Openable openable = (Openable)parent;		
			if (openable.isOpen()) {
				try {
					OpenableElementInfo info = (OpenableElementInfo) openable.getElementInfo();
					info.removeChild(element);
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
	// 2012-03-08 sundl 增加参数，可以让监听器确定更改类型
	protected void fireChangeEvent(ARESElementChangedEvent event) {	
		try {
			for (IARESElementChangeListener listener : listeners) {
				listener.elementChanged(event);
			}		
		} catch (Exception e) {
			logger.warn("", e);
			// TODO: handle exception
		}
	}
	
	public void entriesAdded(IARESProject project, IResPathEntry[] entries) {
		for (IResPathEntry entry : entries) {
			IOpenable child = null;
			if (entry.getEntryKind() == IResPathEntry.RPE_SOURCE) {
				child = project.getModuleRoot(entry);
			} else if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY){
				child = project.getReferencedLibrary(entry);
			}
			if (child == null)
				continue;
			
			addToParentInfo((IARESElement)child);
		}
		fireChangeEvent(new ARESElementChangedEvent());
	}
	
	public void entriesRemoved(IARESProject project, IResPathEntry[] entries) {
		for (IResPathEntry entry : entries) {
			IOpenable child = null;
			if (entry.getEntryKind() == IResPathEntry.RPE_SOURCE) {
				child = project.getModuleRoot(entry);
			} else if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY){
				child = project.getReferencedLibrary(entry);
			}
			try {
				if (child != null) {
					child.close();
					removeFromParentInfo((IARESElement)child);
				}
					
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
			
		}
		fireChangeEvent(new ARESElementChangedEvent());
	}
	
	public void resPathChanged(IARESProject project, IResPathEntry[] oldPath, IResPathEntry[] newPath, boolean fireEvent) {
		List<IResPathEntry> deletedEntries = new ArrayList<IResPathEntry>();
		List<IResPathEntry> addedEntries = new ArrayList<IResPathEntry>();
		
		for (IResPathEntry entry : newPath) {
			if (!ArrayUtils.contains(oldPath, entry)) {
				addedEntries.add(entry);
			}
		}
		for (IResPathEntry entry : oldPath) {
			if (!ArrayUtils.contains(newPath, entry)) {
				deletedEntries.add(entry);
			}
		}
		
		for (IResPathEntry entry : addedEntries) {
			IOpenable child = null;
			if (entry.getEntryKind() == IResPathEntry.RPE_SOURCE) {
				child = project.getModuleRoot(entry);
			} else if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY){
				child = project.getReferencedLibrary(entry);
			}
			
			if (child != null)
				addToParentInfo((IARESElement)child);
		}
		
		for (IResPathEntry entry : deletedEntries) {
			IOpenable child = null;
			if (entry.getEntryKind() == IResPathEntry.RPE_SOURCE) {
				child = project.getModuleRoot(entry);
			} else if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY){
				child = project.getReferencedLibrary(entry);
			}
			try {
				if (child != null) {
					child.close();
					removeFromParentInfo((IARESElement)child);
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		
		try {
			AresProjectInfo projectInfo = (AresProjectInfo) ((ARESProject)project).getElementInfo();
			projectInfo.resPath = newPath;
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		// 2012-03-08 sundl 增加参数，事件确定为respath变化事件，实现可以监听的功能
		if (fireEvent)
			fireChangeEvent(new ARESElementChangedEvent(project, ARESElementChangedEvent.RES_PATH));
	}
	
	private void logEvent(IResourceChangeEvent event) {
		logger.trace("Event kind: " + event.getBuildKind());		
		IResourceDelta delta = event.getDelta();
		if (delta != null)
			logger.trace("Delta: " + toDeepDebugString(delta));
	}
	
	/** 
	 * Returns a string representation of this delta's
	 * immediate structure suitable for debug purposes.
	 */
	public String toDebugString(IResourceDelta delta) {
		final StringBuffer buffer = new StringBuffer();
		writeDebugString(buffer, delta);
		return buffer.toString();
	}

	/** 
	 * Returns a string representation of this delta's
	 * deep structure suitable for debug purposes.
	 */
	public String toDeepDebugString(IResourceDelta delta) {
		final StringBuffer buffer = new StringBuffer("\n"); //$NON-NLS-1$
		writeDebugString(buffer, delta);
		IResourceDelta[] children = delta.getAffectedChildren();
		for (int i = 0; i < children.length; ++i)
			buffer.append(toDeepDebugString(children[i]));
		return buffer.toString();
	}

	/** 
	 * Writes a string representation of this delta's
	 * immediate structure on the given string buffer.
	 */
	public void writeDebugString(StringBuffer buffer, IResourceDelta delta) {
		buffer.append(delta.getFullPath());
		buffer.append('[');
		switch (delta.getKind()) {
			case IResourceDelta.ADDED :
				buffer.append('+');
				break;
			case IResourceDelta.ADDED_PHANTOM :
				buffer.append('>');
				break;
			case IResourceDelta.REMOVED :
				buffer.append('-');
				break;
			case IResourceDelta.REMOVED_PHANTOM :
				buffer.append('<');
				break;
			case IResourceDelta.CHANGED :
				buffer.append('*');
				break;
			case IResourceDelta.NO_CHANGE :
				buffer.append('~');
				break;
			default :
				buffer.append('?');
				break;
		}
		buffer.append("]: {"); //$NON-NLS-1$
		int changeFlags = delta.getFlags();
		boolean prev = false;
		if ((changeFlags & IResourceDelta.CONTENT) != 0) {
			if (prev)
				buffer.append(" | "); //$NON-NLS-1$
			buffer.append("CONTENT"); //$NON-NLS-1$
			prev = true;
		}
		if ((changeFlags & IResourceDelta.LOCAL_CHANGED) != 0) {
			if (prev)
				buffer.append(" | "); //$NON-NLS-1$
			buffer.append("LOCAL_CHANGED"); //$NON-NLS-1$
			prev = true;
		}
		if ((changeFlags & IResourceDelta.MOVED_FROM) != 0) {
			if (prev)
				buffer.append(" | "); //$NON-NLS-1$
			buffer.append("MOVED_FROM(" + delta.getMovedFromPath() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			prev = true;
		}
		if ((changeFlags & IResourceDelta.MOVED_TO) != 0) {
			if (prev)
				buffer.append(" | "); //$NON-NLS-1$
			buffer.append("MOVED_TO(" + delta.getMovedToPath() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			prev = true;
		}
		if ((changeFlags & IResourceDelta.OPEN) != 0) {
			if (prev)
				buffer.append(" | "); //$NON-NLS-1$
			buffer.append("OPEN"); //$NON-NLS-1$
			prev = true;
		}
		if ((changeFlags & IResourceDelta.TYPE) != 0) {
			if (prev)
				buffer.append(" | "); //$NON-NLS-1$
			buffer.append("TYPE"); //$NON-NLS-1$
			prev = true;
		}
		if ((changeFlags & IResourceDelta.SYNC) != 0) {
			if (prev)
				buffer.append(" | "); //$NON-NLS-1$
			buffer.append("SYNC"); //$NON-NLS-1$
			prev = true;
		}
//		if ((changeFlags & IResourceDelta.MARKERS) != 0) {
//			if (prev)
//				buffer.append(" | "); //$NON-NLS-1$
//			buffer.append("MARKERS"); //$NON-NLS-1$
////			writeMarkerDebugString(buffer);
//			prev = true;
//		}
		if ((changeFlags & IResourceDelta.REPLACED) != 0) {
			if (prev)
				buffer.append(" | "); //$NON-NLS-1$
			buffer.append("REPLACED"); //$NON-NLS-1$
			prev = true;
		}
		if ((changeFlags & IResourceDelta.DESCRIPTION) != 0) {
			if (prev)
				buffer.append(" | "); //$NON-NLS-1$
			buffer.append("DESCRIPTION"); //$NON-NLS-1$
			prev = true;
		}
		if ((changeFlags & IResourceDelta.ENCODING) != 0) {
			if (prev)
				buffer.append(" | "); //$NON-NLS-1$
			buffer.append("ENCODING"); //$NON-NLS-1$
			prev = true;
		}
		buffer.append("}"); //$NON-NLS-1$
//		if (delta.isTeamPrivate())
//			buffer.append(" (team private)"); //$NON-NLS-1$
//		if (delta.isHidden())
//			buffer.append(" (hidden)"); //$NON-NLS-1$
	}

//	public void writeMarkerDebugString(StringBuffer buffer, IResourceDelta delta) {
//		Map markerDeltas = deltaInfo.getMarkerDeltas();
//		if (markerDeltas == null || markerDeltas.isEmpty())
//			return;
//		buffer.append('[');
//		for (Iterator e = markerDeltas.keySet().iterator(); e.hasNext();) {
//			IPath key = (IPath) e.next();
//			if (getResource().getFullPath().equals(key)) {
//				IMarkerSetElement[] deltas = ((MarkerSet) markerDeltas.get(key)).elements();
//				boolean addComma = false;
//				for (int i = 0; i < deltas.length; i++) {
//					IMarkerDelta delta = (IMarkerDelta) deltas[i];
//					if (addComma)
//						buffer.append(',');
//					switch (delta.getKind()) {
//						case IResourceDelta.ADDED :
//							buffer.append('+');
//							break;
//						case IResourceDelta.REMOVED :
//							buffer.append('-');
//							break;
//						case IResourceDelta.CHANGED :
//							buffer.append('*');
//							break;
//					}
//					buffer.append(delta.getId());
//					addComma = true;
//				}
//			}
//		}
//		buffer.append(']');
//	}
}
