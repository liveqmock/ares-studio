/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModelStatusConstants;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ResourcesUtil;

/**
 * 复制元素的操作。
 * 同时应该根据是否是移动，是否覆盖等做不同的操作（一般由子类来实现）。
 * @author sundl
 */
public class CopyResourceElementsOperation extends MultiOperation {

	private static final Logger logger = Logger.getLogger(CopyResourceElementsOperation.class);
	
	public CopyResourceElementsOperation(IARESElement[] elementsToProcess, IARESElement[] newParents, boolean force) {
		super(elementsToProcess, newParents, force);
	}
	
	protected void processElement(IARESElement element) throws ARESModelException {
		switch (element.getElementType()) {
		case IARESElement.ARES_RESOURCE:
			processARESResource((IARESResource)element);
			break;
		case IARESElement.COMMON_MODULE:
			processARESModule((IARESModule)element);
			break;
		default:
			throw new ARESModelException(new ARESModelStatus(IARESModelStatusConstants.INVALID_ELEMENT_TYPES));
		}
		logger.trace(element.getElementName() + " processed.");
	}
	
	private void processARESResource(IARESResource aresRes) throws ARESModelException {
		String newName = getNewNameFor(aresRes);
		IARESElement destContainer = getDestinationParent(aresRes);
		
		IPath newPath = null;
		if (newName != null) {
			newPath = destContainer.getPath().append(newName);
		} else {
			newPath = destContainer.getPath().append(aresRes.getElementName());
		}

		if (aresRes.getRoot().isArchive())
			copyResourceFromLib(aresRes, newPath);
		else 
			copyResourceFromFile(aresRes, newPath);
	}
	
	// 普通资源
	private void copyResourceFromFile(IARESResource aresRes, IPath destPath) throws ARESModelException {
		IFile file = (IFile) aresRes.getResource();
		IFile destFile = file.getWorkspace().getRoot().getFile(destPath);
		
		try {		
			if (destFile != null)
				destFile.delete(true, null);
			
			if (isMove())
				file.move(destFile.getFullPath(), true, null);
			else
				file.copy(destFile.getFullPath(), true, null);
			
		} catch (CoreException e) {
			throw new ARESModelException(e);
		}
	}
	
	// 引用资源包里的资源
	private void copyResourceFromLib(IARESResource aresRes, IPath destPath) throws ARESModelException {
		IWorkspaceRoot workspaceRoot = aresRes.getARESModel().getWorkspace().getRoot();
		IFile destFile = workspaceRoot.getFile(destPath);
		try {
			ResourcesUtil.safelyCreateFile(destFile, aresRes.openStream(), true, null);
		} catch (CoreException e) {
			// e.printStackTrace();
		}
	}
	
	private void processARESModule(IARESModule module) throws ARESModelException {
		IARESElement destContainer = getDestinationParent(module);

		IFolder destFolder = (IFolder) destContainer.getResource();
		copyModule(destFolder, module);
		if (isMove()) {
			try {
				module.getResource().delete(true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void copyModule(IFolder destFolder, IARESModule module) throws ARESModelException {
		String newName = getNewNameFor(module);
		String moduleName = module.getShortName();

		IFolder moduleFolder;
		IPath newPath = null;
		if (newName != null) {
			moduleFolder = destFolder.getFolder(newName);
			newPath = destFolder.getFullPath().append(newName);
		} else {
			moduleFolder = destFolder.getFolder(moduleName);
			newPath = destFolder.getFullPath().append(moduleName);
		}
		
		try {
			ResourcesUtil.safelyCreateFolder(moduleFolder);
//			List<IFile> filesToCopy = new ArrayList<IFile>();
//			for (IARESResource ares : module.getARESResources()) {
//				filesToCopy.add((IFile) ares.getResource());
//			}
//			filesToCopy.addAll(Arrays.asList(module.getNonARESFiles()));
//			
//			for (IFile file : filesToCopy) {
//				IFile destFile = moduleFolder.getFile(file.getName());
//				
//				if (destFile != null && destFile.exists())
//					destFile.delete(true, null);
//				
//				if (isMove())
//					file.move(newPath.append(file.getName()), true, null);
//				else
//					file.copy(newPath.append(file.getName()), true, null);
//			}
			for (IARESResource ares : module.getARESResources()) {
				IPath newResPath = moduleFolder.getFullPath().append(ares.getElementName());
				if (ares.getRoot().isArchive())
					copyResourceFromLib(ares, newResPath);
				else 
					copyResourceFromFile(ares, newResPath);
			}
		} catch (CoreException e) {
			throw new ARESModelException(e);
		}
		
		for (IARESModule sub : getDirectChildren(module)) {
			copyModule(moduleFolder, sub);
		}
	}
	
//	private void copyModuleFromLib(IARESModule module, IFolder dest) {
//		
//	}
	
	private IARESModule[] getDirectChildren(IARESModule module) throws ARESModelException {
		List<IARESModule> results = new ArrayList<IARESModule>();
		IARESModule[] children = module.getSubModules();
		for (IARESModule child : children) {
			if (child.getParentModule().equals(module))
				results.add(child);
		}
		return results.toArray(new IARESModule[0]);
	}
	
	protected boolean isMove() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.MultiOperation#getMainTaskName()
	 */
	@Override
	protected String getMainTaskName() {
		return "Copy";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.MultiOperation#vertify(com.hundsun.ares.studio.core.IARESElement)
	 */
	@Override
	protected void vertify(IARESElement element) throws ARESModelException {
		if (!element.exists()) {
			throw new ARESModelException(new ARESModelStatus(IARESModelStatusConstants.ELEMENT_DOES_NOT_EXIST));
		}
	}
	
}
