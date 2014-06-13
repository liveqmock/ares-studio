/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.IARESResourceEditorInput;
import com.hundsun.ares.studio.ui.page.FromPageWithMyDirtySystem;

/**
 * ares基础编辑器
 * 提供模型通用序列化
 * 提供撤销重做上下文
 * 提供只读状态控制
 * @author maxh
 *
 */
public abstract class AbstractHSFormEditorNoUndocontext<T> extends AbstractHSExtendPointFormEditorNoUndocontext<T> {

	private static Logger logger = Logger.getLogger(AbstractHSFormEditor.class.getName());
	
	protected IARESResource resource;

	/**
	 * 获得关联的resource
	 * @return the resource
	 */
	public IARESResource getResource() {
		return resource;
	}
	
	@Override
	protected void addPageContext(FromPageWithMyDirtySystem page){
		super.addPageContext(page);
		page.setResource(resource);
	}
	
	protected String getPartTitleName(){
		if (resource != null) {
			return resource.getName();
		}
		return super.getPartTitleName();
	}
	
	@Override
	protected boolean isReadOnly() {
		if (resource != null)
			return resource.isReadOnly();
		return super.isReadOnly();
	}

	@Override
	public void setInput(IEditorInput input) {
		super.setInput(input);
		
		if (editingFile != null) {
			try {
				IARESElement element = ARESCore.create(editingFile);
				if (element instanceof IARESResource) {
					resource = (IARESResource) element;
				}
			} catch (Exception e) {
				logger.error("read model from platform fail!", e);
			}
		} else if (input instanceof IARESResourceEditorInput) {
			resource = ((IARESResourceEditorInput)input).getARESResource();
			this.aresProject = resource.getARESProject();
		}
		
		if (resource != null) {
			try {
				info = (T) resource.getWorkingCopy(getModelType());
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		
		//如果无法从资源框架获取模型 就new一个
		try {
			if(info == null && editingFile != null){
				info = (T)getModelType().newInstance();
			}
		} catch (Exception e) {
			logger.error("create new instance failed!", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		if (getEditorInput() instanceof IFileEditorInput) {
			IFile file = ((IFileEditorInput)getEditorInput()).getFile();
			IARESElement element = ARESCore.create(file);
			if (element instanceof IARESResource) {
				try {
					((IARESResource)element).save(info, true, monitor);
				} catch (ARESModelException e) {
					logger.error("文件保存错误", e);
				}
			}
		}
		dirty.setValue(false);
	}
	
	/**
	 * 吕高 2010-8-17 17:08:59
	 * 只是保存文件，不改变文件的脏状态
	 * 改变文件的脏状态，会引起emf的一些动作
	 * 在没有设定好新的资源前会引起异常
	 * @param monitor
	 */
	protected void doSaveFile(IProgressMonitor monitor) {
		if (getEditorInput() instanceof IFileEditorInput) {
			IFile file = ((IFileEditorInput)getEditorInput()).getFile();
			IARESElement element = ARESCore.create(file);
			if (element instanceof IARESResource) {
				try {
					((IARESResource)element).save(info, true, monitor);
				} catch (ARESModelException e) {
					logger.error("文件保存错误", e);
				}
			}
		}
	}

}
