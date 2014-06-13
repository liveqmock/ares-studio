/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.internal.runtime.AdapterManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.model.converter.IModelConverter;
import com.hundsun.ares.studio.core.model.converter.IModelConverter2;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;

/**
 * 资源。
 * @author sundl
 */
public class ARESResource extends Openable implements IARESResource {
	static final Logger console = ConsoleHelper.getLogger();
	private static Logger logger = Logger.getLogger(ARESResource.class);
	 
	private IFile file;
	
	public ARESResource() {
		this(null);
	}
	
	public ARESResource(IARESElement parent) {
		super(parent);		
	}
	
	public ARESResource(IARESElement parent, IFile file) {
		this(parent);
		this.file = file;
	}

	void init(IARESModule module, IFile file) {
		this.parent = module;
		this.file = file;
	}
	
	@Override
	protected boolean buildStructure(OpenableElementInfo info)
			throws ARESModelException {
		ARESResourceInfo cResInfo = (ARESResourceInfo)info;
		IModelConverter converter = getResDescriptor().getConverter();
		
		// 2011年7月13日15:53:51 gongyf 添加对新读取接口的支持
		try {
			if (converter instanceof IModelConverter2) {
				cResInfo.realInfo = ((IModelConverter2) converter).read(this);
			} else {
				InputStream is = null;
				try {
					is = openStream();
					logger.trace("begin read Info... old info: " + cResInfo);
					if (is != null) {
						cResInfo.realInfo = getResDescriptor().createInfo();
						converter.read(is, cResInfo.realInfo);
						logger.trace("end read Info... new info: " + cResInfo);	
					} else {
						logger.trace("read info failed... no input stream");
					}
				} finally {
					IOUtils.closeQuietly(is);
				}
				
			}
		} catch (Exception e1) {
			logger.error(String.format("读取资源%s的时候出错", getPath()), e1);
			return false;
		}
		return true;
	}

	@Override
	protected Object createElementInfo() {
		ARESResourceInfo info = new ARESResourceInfo();
		logger.trace("Create Ares Resource Info: " + info.toString());
		return info;
	}

	protected IResDescriptor getResDescriptor() {
		ARESResRegistry reg = ARESResRegistry.getInstance();
		IResDescriptor desc = reg.getResDescriptor(getElementName());
		if (desc == null)
			desc = reg.getResDescriptor(getType());
		return desc;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#validateExistence(org.eclipse.core.resources.IResource)
	 */
	@Override
	protected IStatus validateExistence(IResource underlyingResource) {
		if (!underlyingResource.exists()) {
			return newDoesNotExistStatus();
		}
		return ARESModelStatus.VERIFIED_OK;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonResource#getModule()
	 */
	public IARESModule getModule() {
		return (IARESModule)parent;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonResource#getRoot()
	 */
	public IARESModuleRoot getRoot() {
		return (IARESModuleRoot)getModule().getParent();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#getFullyQualifiedName()
	 */
	public String getFullyQualifiedName() {
		IARESElement parent = getParent();
		if (parent instanceof IARESModule) {
			if (((IARESModule)parent).isDefaultModule()) {
				return getName();
			}
		}
		return parent.getElementName() + "." + getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.PlatformObject#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class adapter) {
		Object obj = null;
		try {
			obj = getInfo(adapter);
		} catch (ARESModelException e) {
		}
		if (obj != null) {
			return obj;
		}
		return super.getAdapter(adapter);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#getInfo()
	 */
	public Object getInfo() throws ARESModelException {
		ARESResourceInfo cInfo = (ARESResourceInfo)getElementInfo();
		logger.trace("getInfo: " + cInfo.realInfo);
		return cInfo.realInfo;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#getName()
	 */
	public String getName() {
		// 如果文件名注册为资源类型，则文件名作为资源名
		ARESResRegistry reg = ARESResRegistry.getInstance();
		IResDescriptor desc = reg.getResDescriptor(getElementName());
		if (desc != null) 
			return getElementName();
		
		return file.getName().substring(0, file.getName().lastIndexOf("."));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#getType()
	 */
	public String getType() {
		// 资源类型注册文件名的时候
		ARESResRegistry reg = ARESResRegistry.getInstance();
		IResDescriptor desc = reg.getResDescriptor(getElementName());
		if (desc != null) 
			return getElementName();
		
		return ((IFile)file).getFileExtension();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#getWorkingCopy()
	 */
	public Object getWorkingCopy() throws ARESModelException {
		Object realInfo = null;
		// 2011年7月13日15:57:27 修正打开输入流后未关闭的bug
		InputStream is = null;
		try {
			is = openStream();
			IModelConverter converter = getResDescriptor().getConverter();
			if (converter instanceof IModelConverter2) {
				realInfo = ((IModelConverter2) converter).read(this);
			} else {
				// 对于老构件，因为没有采用标准做法，只能调用read(inputstream, object) 方法
				realInfo = getResDescriptor().createInfo();
				converter.read(is, realInfo);
			}
			
		} catch (Exception e) {
			throw new  ARESModelException(e,IStatus.ERROR);
		} finally {
			IOUtils.closeQuietly(is);
		}
		return realInfo;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#openStream()
	 */
	public InputStream openStream() throws ARESModelException {
		try {
			return file.getContents();
		}
		catch (CoreException e) {
			e.printStackTrace();
			console.info(e.getMessage());
			throw new ARESModelException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#save(java.lang.Object, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void save(Object info, boolean force, IProgressMonitor monitor)
			throws ARESModelException {
		
//		Object saveInfo = getResDescriptor().createInfo();
//		// 模型也需要适配
//		if (!info.getClass().isInstance(saveInfo) ) {
//			if (info instanceof IAdaptable) {
//				saveInfo = ((IAdaptable) info).getAdapter(saveInfo.getClass());
//			} else {
//				saveInfo = AdapterManager.getDefault().getAdapter(info, saveInfo.getClass());
//			}
//		} else {
//			// 如果类型符合则直接使用传入的值即可
//			saveInfo = info;
//		}
//		
//		Assert.isNotNull(saveInfo, "保存的Info为null");
		byte[] contents = null;
		IModelConverter converter = getResDescriptor().getConverter();
		try {
			if (converter instanceof IModelConverter2) {
				contents = ((IModelConverter2) converter).write(this, info);
			} else {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				converter.write(bos, info);
				contents = bos.toByteArray();
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			file.setContents(new ByteArrayInputStream(contents), force, true, monitor);
		} catch (CoreException e) {
			logger.error(String.format("写入资源%s的时候出错", getPath()), e);
			throw new ARESModelException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
	 */
	public IResource getCorrespondingResource() throws ARESModelException {
		return getResource();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementName()
	 */
	public String getElementName() {
		return file.getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementType()
	 */
	public int getElementType() {
		return IARESElement.ARES_RESOURCE;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	public IResource getResource() {
		return file;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#copy(com.hundsun.ares.studio.core.IARESElement, java.lang.String, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void copy(IARESElement container, String rename, boolean replace,
			IProgressMonitor monitor) throws ARESModelException {
		IARESElement[] elements = new IARESElement[] {this};
		IARESElement[] containers = new IARESElement[] {container};
		String[] renamings = null;
		if (rename != null) 
			renamings = new String[] {rename};
		
		getARESModel().copy(elements, containers, renamings, replace, null);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#delete(boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void delete(boolean force, IProgressMonitor monitor)
			throws ARESModelException {
		IARESElement[] elements = new IARESElement[] {this};
		getARESModel().delete(elements, force, monitor);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#move(com.hundsun.ares.studio.core.IARESElement, java.lang.String, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void move(IARESElement container, String rename, boolean replace,
			IProgressMonitor monitor) throws ARESModelException {
		IARESElement[] elements = new IARESElement[] {this};
		IARESElement[] containers = new IARESElement[] {container};
		String[] renamings = null;
		if (rename != null) 
			renamings = new String[] {rename};
		
		getARESModel().move(elements, containers, renamings, replace, monitor);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#rename(java.lang.String, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void rename(String name, boolean replace, IProgressMonitor monitor)
			throws ARESModelException {
		IARESElement[] elements = new IARESElement[] {this};
		String[] renamings = null;
		if (name != null) 
			renamings = new String[] {name + "." + getType()};
		getARESModel().rename(elements, renamings, replace, monitor);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#check()
	 */
	public IARESProblem[] check() {
		return new IARESProblem[0];
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonResource#getLib()
	 */
	public IReferencedLibrary getLib() {
		return getRoot().getLib();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	public IPath getPath() {
		return file.getFullPath();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleMementoDelimiter()
	 */
	@Override
	protected char getHandleMementoDelimiter() {
		return AEM_RESOURCE;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleFromMemento(java.lang.String, com.hundsun.ares.studio.internal.core.MementoTokenizer)
	 */
	@Override
	public IARESElement getHandleFromMemento(String token, MementoTokenizer memento) {
		// 应该不会调用到这里
		Assert.isTrue(false, "不应调用的方法...");
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#getInfo(java.lang.Class)
	 */
	public <T> T getInfo(Class<T> clazz) throws ARESModelException {
		Object info = getInfo();
		if (info == null)
			return null;
		
		if (clazz.isInstance(info)) {
			return (T) info;
		}
		
		if (info instanceof IAdaptable) {
			return (T) ((IAdaptable) info).getAdapter(clazz);
		}
		return (T) AdapterManager.getDefault().getAdapter(info, clazz);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#getWorkingCopy(java.lang.Class)
	 */
	public <T> T getWorkingCopy(Class<T> clazz) throws ARESModelException {
		Object info = getWorkingCopy();
		if (info == null)
			return null;
		
		if (clazz.isInstance(info)) {
			return (T) info;
		}
		
		if (info instanceof IAdaptable) {
			return (T) ((IAdaptable) info).getAdapter(clazz);
		}
		
		return (T) AdapterManager.getDefault().getAdapter(info, clazz);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#getBundle()
	 */
	@Override
	public IARESBundle getBundle() {
		return getARESProject();
	}

}
