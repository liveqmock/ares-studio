/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IReferencedLibrary;

/**
 * 引用包里的模块根。
 * @author sundl
 */
public class ArchiveARESModuleRoot extends ARESModuleRoot implements IARESModuleRoot {

	public ArchiveARESModuleRoot(IARESElement parent, IPath path, String type) {
		this.parent = parent;
		this.path = path;
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#buildStructure(com.hundsun.ares.devtool.v2.internal.core.OpenableElementInfo)
	 */
	@Override
	protected boolean buildStructure(OpenableElementInfo info) throws ARESModelException {
		ReferencedLibraryInfo libInfo = (ReferencedLibraryInfo) ((ReferencedLibrary)parent).getElementInfo();
		List<IARESModule> modules = libInfo.rootsInfo.get(this);
		if (modules != null)
			info.setChildren(modules.toArray(new IARESElement[0]));
		return true;
	}	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#validateExistence(org.eclipse.core.resources.IResource)
	 */
	protected IStatus validateExistence(IResource underlyingResource) {
		return ARESModelStatus.VERIFIED_OK;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
	 */
	public IResource getCorrespondingResource() throws ARESModelException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementType()
	 */
	public int getElementType() {
		return COMMON_MODULE_ROOT;
	}

	public IARESModule getModule(String[] moduleName) {
		return new ArchiveARESModule(this, moduleName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	public IPath getPath() {
		return path;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	public IResource getResource() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModuleRoot#getType()
	 */
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModuleRoot#isArchive()
	 */
	public boolean isArchive() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModuleRoot#getLib()
	 */
	public IReferencedLibrary getLib() {
		return (IReferencedLibrary)parent;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModuleRoot#createModule(java.lang.String)
	 */
	public IARESModule createModule(String name) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModuleRoot#getKind()
	 */
	public int getKind() {
		return KIND_BINARY;
	}

}
