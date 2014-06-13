/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.util;

import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.model.ICommonModel;

/**
 * 资源操作相关工具类
 * @author sundl
 */
public class ResourcesUtil {

	public static void addNatureToProject(IProject proj, String natureId, IProgressMonitor monitor) throws CoreException {
		IProjectDescription description = proj.getDescription();
		String[] prevNatures = description.getNatureIds();
		String[] newNatures = new String[prevNatures.length + 1];
		System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
		newNatures[prevNatures.length] = natureId;
		description.setNatureIds(newNatures);
		proj.setDescription(description, monitor);
	}
	
	/**
	 * 创建文件夹，并且自动创建父文件夹，如果不存在的话。 
	 * @param folder
	 * @throws CoreException
	 */
	public static void safelyCreateFolder(IFolder folder) throws CoreException {
		if (folder.exists())
			return;
		IContainer parent = folder.getParent();
		
		if (parent.exists()) {
			folder.create(true, true, null);	
			return;
		} else {
			if (parent.getType() == IResource.FOLDER) {
				safelyCreateFolder((IFolder)parent);
				folder.create(true, true, null);
			} else {
				// this should never happen...
				throw new CoreException(new Status(IStatus.ERROR, ARESCore.PLUGIN_ID, "Unsupported resource operation..."));
			}
		}
	}
	
	/**
	 * 创建文件，并自动创建父容器。（如果父容器是项目，且不存在的话，则不能自动创建，并抛出异常）
	 * @param file
	 * @param source
	 * @param overwrite 遇到已经存在的情况是否覆盖
	 * @throws CoreException
	 */
	public static void safelyCreateFile(IFile file, InputStream source, boolean overwirte, IProgressMonitor monitor) throws CoreException {
		if (file.exists()) {
			if (overwirte)
				file.setContents(source, true, true, monitor);
			return;
		}
		
		IContainer parent = file.getParent();
		if (parent.exists()) {
			file.create(source, true, monitor);
		} else {
			if (parent.getType() == IResource.FOLDER) {
				safelyCreateFolder((IFolder)parent);
				file.create(source, true, monitor);
			} else {
				// this should never happen...
				throw new CoreException(new Status(IStatus.ERROR, ARESCore.PLUGIN_ID, "Unsupported resource operation..."));
			}
		}
	}
	
	public static boolean fileExists(IContainer container, String name) {
		
		try {
			for (IResource res : container.members()) {
				if (res.getType() == IResource.FILE) {
					if (res.getName().equalsIgnoreCase(name)) {
						return true;
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public static boolean folderExists(IContainer container, String name) {
		
		try {
			for (IResource res : container.members()) {
				if (res.getType() == IResource.FOLDER) {
					if (res.getName().equalsIgnoreCase(name)) {
						return true;
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 获取资源中文名的工具方法。
	 * @param res
	 * @return
	 */
	public static String getCName(IARESResource res) {
		Object info = null;
		try {
			info = res.getInfo(Object.class);
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		if (info != null) {
			ICommonModel model = getCommonModel(info);
			if (model != null)
				return model.getString(ICommonModel.CNAME);
		}
		return null;
	}
	
	// 将给定的对象转换为ICommonModel
	public static ICommonModel getCommonModel(Object info) {
		ICommonModel commonModel = null;
		if (info == null) {
			commonModel = null;
		} else if (info instanceof ICommonModel) {
			commonModel = (ICommonModel)info;
		} else if (info instanceof IAdaptable) {
			IAdaptable adapter = (IAdaptable)info;
			commonModel = (ICommonModel) adapter.getAdapter(ICommonModel.class);
		} else {
			IAdapterManager manager = Platform.getAdapterManager();
			commonModel = (ICommonModel)manager.getAdapter(info, ICommonModel.class);
		}
		return commonModel;
	}
	
	/**
	 * 判断是否在同一个项目中,element类型可以是 {@link IResource}, {@link IACElement}.
	 * @param elements 要判断的元素数组
	 * @return 如果是则返回<code>true</code>，如果有元素不是上述类型之一，或者是IACElement类型，
	 * 但getResource()返回了<code>null</code>，此方法返回<code>false</code>.
	 */
	public static boolean isInSameProject(Object[] elements) {
		IProject project = null;
		for(Object obj : elements) {
			IResource res = null;
			if(obj instanceof IResource) {
				res = (IResource)obj;
			} else if(obj instanceof IAdaptable) {
				res = (IResource)((IAdaptable)obj).getAdapter(IResource.class);
			}
			
			if(res == null) {
				continue;
			} else {
				if(project == null) {
					project = res.getProject();
				} else {
					if(!project.equals(res.getProject())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 从一个对象适配出指定类型的对象
	 * 
	 * @param <T>
	 * @param element
	 * @param adapterType
	 * @param forceLoad 是否加载未启动插件
	 * @return
	 */
	public static <T> T getAdapter(Object element, Class<T> adapterType, boolean forceLoad) {
		
		if (element == null) {
			return null;
		}
		
		if (adapterType.isInstance(element)) {
			return (T) element;
		}
		
		if (element instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) element;
	        Object o = adaptable.getAdapter(adapterType);
	        if (o != null) {
	        	return (T) o;
	        }
		}
		if (forceLoad) {
			return (T) Platform.getAdapterManager().loadAdapter(element, adapterType.getName());
		}
		return (T) Platform.getAdapterManager().getAdapter(element, adapterType);
	}

	public static IARESProject getARESProject(IARESBundle bundle) {
		if (bundle instanceof IARESProject) {
			return (IARESProject) bundle;
		} else if (bundle instanceof IReferencedLibrary) {
			return ((IReferencedLibrary) bundle).getARESProject();
		} else {
			throw new UnsupportedOperationException("不支持转换");
		}
	}
	
	//获取模块中文名
	public static String getChineseFileName (String separator ,IARESModule module){
		StringBuffer sb = new StringBuffer();
		getModuleChineseName(module, sb ,separator);
		String[] ps = StringUtils.split(sb.toString(), separator);
		StringBuffer sbf = new StringBuffer();
		for (int i = ps.length-1; i > -1 && ps.length > 0; i--) {
			if (StringUtils.isNotBlank(sbf.toString())) {
				sbf.append(separator);
			}
			sbf.append(ps[i]);
		}
		return sbf.toString();
	}
	
	public static void getModuleChineseName (IARESModule module , StringBuffer sb , String separator){
		if (module != null) {
			IARESResource property = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
			if (property != null && property.exists()) {
				String cname = getCName(property);
				if(cname != null){
					if (StringUtils.isNotBlank(sb.toString())) {
						sb.append(separator);
					}
					sb.append(cname);
				}
			}
			getModuleChineseName(module.getParentModule(), sb ,separator);
		}
	}
	
	public static String getModuleChineseName (IARESModule module, String separator){
		StringBuffer sb = new StringBuffer();
		getModuleChineseName(module, sb, separator);
		return sb.toString();
	}

}
