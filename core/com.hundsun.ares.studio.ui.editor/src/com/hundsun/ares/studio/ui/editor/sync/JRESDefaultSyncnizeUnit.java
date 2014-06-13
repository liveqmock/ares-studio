/**
 * 源程序名称：JRESDefaultSyncnizeUnit.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.sync;

import org.apache.commons.lang.ObjectUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import com.hundsun.ares.studio.ui.IARESResourceEditorInput;

/**
 * @author lvgao
 *
 */
public  class JRESDefaultSyncnizeUnit implements IFileSyncnizeUnit{
	
	private IEditorPart editor;
	private long modifiedStamp = -1;
	
	public JRESDefaultSyncnizeUnit(IEditorPart editor){
		this.editor = editor;
	}



	private void closeEditor() {
		//关闭编辑器
		editor.getSite().getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				editor.getSite().getWorkbenchWindow().getActivePage().closeEditor(editor, false);
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.util.IFileSyncnizeUnit#beforeSave()
	 */
	@Override
	public void beforeSave() {
		if (getFile() == null) {
			// 没有对应的资源文件则不处理保存前事件
			return;
		}
		modifiedStamp = getFile().getModificationStamp() + 1;	
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.util.IFileSyncnizeUnit#isSaveChange(org.eclipse.core.resources.IResourceDelta)
	 */
	private boolean isSaveChange(IResourceDelta delta) {
		switch (delta.getKind()) {
		case IResourceDelta.CHANGED:
			break;
		case IResourceDelta.REMOVED:
			//如果为删除直接返回
			return false;
		}
		IFile resource = (IFile) delta.getResource();
		if (resource.getModificationStamp() == modifiedStamp) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.util.IFileSyncnizeUnit#getFile()
	 */
	public IFile getFile() {
		IEditorInput editorInput = editor.getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			return ((IFileEditorInput)editorInput).getFile();
		} else if (editorInput instanceof IARESResourceEditorInput) {
			return (IFile) ((IARESResourceEditorInput)editorInput).getARESResource().getLib().getResource();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.util.IFileSyncnizeUnit#handleAction(org.eclipse.core.resources.IResourceDelta)
	 */
	@Override
	public void handleAction(IResourceDelta delta) {
		try {
			delta.accept(new IResourceDeltaVisitor() {
				
				@Override
				public boolean visit(IResourceDelta delta) throws CoreException {
					IResource resource = delta.getResource();
					IFile file = getFile();
					if (resource instanceof IWorkspaceRoot) {
						return true;
					} else if (resource instanceof IProject) {
						if (resource.equals(file.getProject())) {
							// 项目被删除或者关闭时关闭编辑器
							switch (delta.getKind()) {
							case IResourceDelta.REMOVED:
								closeEditor();
								return false;
							case IResourceDelta.CHANGED:
								// 关闭的时候也要关闭
								if ((delta.getFlags() & IResourceDelta.OPEN) != 0) {
									if ( !((IProject) resource).isOpen() ) {
										closeEditor();
										return false;
									}
								}
							}
							return true;
						} else {
							return false;
						}

					} else if (resource instanceof IContainer) {
						if (resource.getFullPath().isPrefixOf(file.getFullPath())) {
							if (delta.getKind() == IResourceDelta.REMOVED ) {
								closeEditor();
								return false;
							}
							return true;
						} else {
							return false;
						}
						
					} else if (resource instanceof IFile) {
						if (!ObjectUtils.equals(file, resource)) {
							return false;
						}
						switch (delta.getKind()) {
						case IResourceDelta.REMOVED:
							closeEditor();
							return false;
						case IResourceDelta.CHANGED:
							if ((delta.getFlags() & IResourceDelta.CONTENT) != 0 && resource.getModificationStamp() != modifiedStamp) {
								closeEditor();
								return false;
							}
						}
					} else {
						return false;
					}
					
					return true;
				}
			});
		} catch (CoreException e) {
		}
		
		
//		IFile file = getFile();
//		if(file != null) {
//			Map<Object, Object> context = new HashMap<Object, Object>();
//			IResourceDelta tmpdelta = delta.findMember(file.getFullPath());
//			if(tmpdelta != null ){
//				
//				if(isSaveChange(tmpdelta)){
//					//处理保存更改
//					handleSaveChange(context);
//				}else{
//					//处理非保存更改
//					handleOutAction(context);
//				}
//			} else {
//				IResourceDelta[] children = delta.getAffectedChildren(IResourceDelta.REMOVED | IResourceDelta.CHANGED);
//				
//				for (IResourceDelta child : children) {
//					if (child.getResource() instanceof IContainer) {
//						IContainer container = (IContainer) child.getResource();
//						if (container.getFullPath().isPrefixOf(file.getFullPath())) {
//							if ((child.getFlags() & IResourceDelta.OPEN) != 0 && container instanceof IProject) {
//								// 项目的打开状态变化了
//								if ( !((IProject)container).isOpen() ) {
//									handleOutAction(context);
//								}
//							} else if (child.getKind() == IResourceDelta.REMOVED) {
//								handleOutAction(context);
//							}
//						}
//					}
//				}
//			}
//		}

		
	}

}
