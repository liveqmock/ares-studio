package com.hundsun.ares.studio.jres.metadata.ui.refactor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.NullChange;
import org.eclipse.ltk.core.refactoring.resource.ResourceChange;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.Operation;

/**
 * @author wangxh
 *
 */
public class ProjectChange extends ResourceChange {
	//引用了重命名的工程脚本的元数据资源
	IARESResource res;
	//重命名前的工程相对全路径
	String oldPath;
	//重命名后的工程相对全路径
	String newPath;
	//元数据资源相对全路径
	String filePath;
	
	public ProjectChange(IARESResource res,String oldPath,
			String newPath) {
		this.res = res;
		this.oldPath = oldPath;
		this.newPath = newPath;
		this.filePath = res.getResource().getFullPath().toString();
	}

	@Override
	protected IResource getModifiedResource() {
		return res.getResource();
	}

	@Override
	public String getName() {
		String name = ARESResRegistry.getInstance().getResDescriptor(res).getName();
		return String.format("更新项目%1$s下的%2$s的脚本路径",res.getARESProject().getElementName(),name);
	}

	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		if(res == null || !res.exists()){
			//项目重命名在前，此时项目已经重命名完，所以该工程里的资源需要重新获取路径
			IPath path = Path.fromPortableString(filePath.replaceFirst(oldPath, newPath));
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if(file.exists()){
				res = (IARESResource) ARESCore.create(file);
			}
		}
		if(res != null && res.exists()){
			MetadataResourceData info = res.getInfo(MetadataResourceData.class);
			EList<Operation> ops = info.getOperations();
			for(Operation op : ops){
				//重命名操作列表引用的脚本路径
				String file = op.getFile();
				if(StringUtils.isNotBlank(file) && Path.fromPortableString(oldPath).isPrefixOf(Path.fromPortableString(file))){
					op.setFile(op.getFile().replaceFirst(oldPath, newPath));
				}
			}
			res.save(info, true, null);
			return new ProjectChange(res,oldPath, newPath);
		}
		return new NullChange();
	}

}
