/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.FileEditorInput;

import com.hundsun.ares.studio.ui.IARESResourceEditorInput;

/**
 * 管理EditorInput的信息,例如最后修改时间等,用来实现同步编辑器外部修改等功能。
 * @author sundl
 */
public class EditorInputInfoManager {
	
	private static EditorInputInfoManager instance;
	
	public static EditorInputInfoManager getInstance() {
		if (instance == null) 
			instance = new EditorInputInfoManager();
		return instance;
	}
	
	private EditorInputInfoManager() {}
	
	public class FileSynchronizer implements IResourceChangeListener, IResourceDeltaVisitor {
		
		private IEditorInput editorInput;
		protected boolean fIsInstalled= false;
		
		public FileSynchronizer(IEditorInput editorInput) {
			this.editorInput = editorInput;
		}
		
		protected IFile getFile() {
			if (editorInput instanceof IFileEditorInput) {
				return ((IFileEditorInput)editorInput).getFile();
			} else if (editorInput instanceof IARESResourceEditorInput) {
				return (IFile) ((IARESResourceEditorInput)editorInput).getARESResource().getLib().getResource();
			}
			return null;
		}
		
		public void install() {
			getFile().getWorkspace().addResourceChangeListener(this);
			fIsInstalled= true;
		}
		
		public void uninstall() {
			getFile().getWorkspace().removeResourceChangeListener(this);
			fIsInstalled= false;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IFile file = getFile();
			if(delta == null || file == null) {
				return false;
			}
			
			delta = delta.findMember(file.getFullPath());
			if(delta == null) {
				return false;
			}
			
			switch(delta.getKind()) {
				case IResourceDelta.CHANGED:
					if((IResourceDelta.CONTENT & delta.getFlags()) != 0) {
						// 内容变化，通知编辑器做出响应。
					}
					break;
				case IResourceDelta.REMOVED:
					if((IResourceDelta.MOVED_TO & delta.getFlags()) != 0) {
						// 文件被移动，通知编辑器做出响应。
						if (editorInput instanceof IFileEditorInput) {
							IPath newPath = delta.getMovedToPath();
							IFile newFile = file.getWorkspace().getRoot().getFile(newPath);
							fireElementMoved(editorInput, new FileEditorInput(newFile));
						}
					} else {
						fireElementDeleted(editorInput);
					}
					break;			
			}
			
			return false;
		}

		public void resourceChanged(IResourceChangeEvent e) {
			IResourceDelta delta= e.getDelta();
			try {
				if (delta != null && fIsInstalled)
					delta.accept(this);
			} catch (CoreException x) {
				//handleCoreException(x, "FileDocumentProvider.resourceChanged"); //$NON-NLS-1$
			}
		}

	}
	
	protected class ElementInfo {
		
		/** Key, 通常是IEditorInput */
		public IEditorInput element;
		
		/** IEditorInput对应的Info对象 */
		public Object info;
		
		/** 最后修改时间 **/
		public long  modifyStamp = IResource.NULL_STAMP;
		
		public FileSynchronizer fileSynchronizer;
		
		public ElementInfo(IEditorInput element) {
			this.element = element;
			fileSynchronizer = new FileSynchronizer(element);
			fileSynchronizer.install();
		}
		
	}
	
	// input ---> info
	private Map<IEditorInput, ElementInfo> elementsInfo = new HashMap<IEditorInput, EditorInputInfoManager.ElementInfo>();
	
	private List<IEditorInputStateListener> listeners = new ArrayList<IEditorInputStateListener>();

	public void addListener(IEditorInputStateListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(IEditorInputStateListener listener) {
		this.listeners.remove(listener);
	}
	
	public void connect(IEditorInput element) {
		ElementInfo info = elementsInfo.get(element);
		if (info == null) {
			info = createElementInfo(element);
			elementsInfo.put(element, info);
		} 
	}
	
	private ElementInfo createElementInfo(IEditorInput input) {
		return new ElementInfo(input);
	}
	
	/**
	 * Computes the initial modification stamp for the given resource.
	 *
	 * @param resource the resource
	 * @return the modification stamp
	 */
	protected long computeModificationStamp(IResource resource) {
		long modificationStamp= resource.getModificationStamp();

		IPath path= resource.getLocation();
		if (path == null)
			return modificationStamp;

		modificationStamp= path.toFile().lastModified();
		return modificationStamp;
	}
	
	public void disconnect(IEditorInput element) {
		ElementInfo info = elementsInfo.get(element);
		if (info != null) {
			disposeElementInfo(info);
			elementsInfo.remove(element);
		}
	}
	
	private void disposeElementInfo(ElementInfo info) {
		info.fileSynchronizer.uninstall();
	}
	
	public Object getInfo(IEditorInput element) {
		return null;
	}
		
	/**
	 * 写文件并且保存修改时间
	 * @param element
	 * @param info
	 */
	public void saveInfo(Object element, Object info) {
		ElementInfo elementInfo = getElementInfo(element);
		if (info == null)
			return;
		
		if (element instanceof IFileEditorInput) {
			IFile file = ((IFileEditorInput) element).getFile();
			elementInfo.modifyStamp = computeModificationStamp(file);
		}
	}
	
	public long getModifyStamp(Object element) {
		ElementInfo elementInfo = getElementInfo(element);
		if (elementInfo != null)
			return elementInfo.modifyStamp;
		return 0;
	}
	
	protected ElementInfo getElementInfo(Object element) {
		return elementsInfo.get(element);
	}
	
	private void fireElementDeleted(IEditorInput editorInput) {
		for (IEditorInputStateListener listener : listeners) {
			listener.editorInputDeleted(editorInput);
		}
	}
	
	private void fireElementMoved(IEditorInput oldInput, IEditorInput newInput) {
		for (IEditorInputStateListener listener : listeners) {
			listener.editorInputMoved(oldInput, newInput);
		}
	}
	
}
